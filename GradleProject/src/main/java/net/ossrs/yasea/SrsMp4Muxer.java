package net.ossrs.yasea;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import com.umeng.analytics.pro.co;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.ossrs.yasea.SrsFlvMuxer;
import supwisdom.at;
import supwisdom.bt;
import supwisdom.ct;
import supwisdom.ct0;
import supwisdom.dt;
import supwisdom.dt0;
import supwisdom.et;
import supwisdom.ft;
import supwisdom.gt;
import supwisdom.gt0;
import supwisdom.hs;
import supwisdom.ht;
import supwisdom.it;
import supwisdom.it0;
import supwisdom.js;
import supwisdom.jt;
import supwisdom.jt0;
import supwisdom.ks;
import supwisdom.kt;
import supwisdom.lt;
import supwisdom.mt;
import supwisdom.mt0;
import supwisdom.ns;
import supwisdom.nt;
import supwisdom.nt0;
import supwisdom.os;
import supwisdom.ot;
import supwisdom.qs;
import supwisdom.qt;
import supwisdom.rs;
import supwisdom.ss;
import supwisdom.ts;
import supwisdom.us;
import supwisdom.ws;
import supwisdom.xs;
import supwisdom.ys;
import supwisdom.zs;

/* JADX INFO: loaded from: classes3.dex */
public class SrsMp4Muxer {
    public static final int AUDIO_TRACK = 101;
    public static final String TAG = "SrsMp4Muxer";
    public static final int VIDEO_TRACK = 100;
    public static Map<Integer, Integer> samplingFrequencyIndexMap;
    public SrsRawH264Stream avc;
    public SrsRecordHandler mHandler;
    public File mRecFile;
    public Mp4Movie mp4Movie;
    public Thread worker;
    public MediaFormat videoFormat = null;
    public MediaFormat audioFormat = null;
    public boolean aacSpecConfig = false;
    public ByteBuffer h264_sps = null;
    public ByteBuffer h264_pps = null;
    public ArrayList<byte[]> spsList = new ArrayList<>();
    public ArrayList<byte[]> ppsList = new ArrayList<>();
    public volatile boolean bRecording = false;
    public volatile boolean bPaused = false;
    public volatile boolean needToFindKeyFrame = true;
    public final Object writeLock = new Object();
    public ConcurrentLinkedQueue<SrsEsFrame> frameCache = new ConcurrentLinkedQueue<>();
    public InterleaveChunkMdat mdat = null;
    public FileOutputStream fos = null;
    public FileChannel fc = null;
    public volatile long recFileSize = 0;
    public volatile long mdatOffset = 0;
    public volatile long flushBytes = 0;
    public HashMap<Track, long[]> track2SampleSizes = new HashMap<>();

    public class InterleaveChunkMdat implements os {
        public long contentSize;
        public boolean first;
        public ByteBuffer header;
        public qs parent;

        public InterleaveChunkMdat() {
            this.first = true;
            this.header = ByteBuffer.allocate(16);
            this.contentSize = 1073741824L;
        }

        private boolean isSmallBox(long j) {
            return j + ((long) this.header.limit()) < 4294967296L;
        }

        @Override // supwisdom.os
        public void getBox(WritableByteChannel writableByteChannel) {
            this.header.rewind();
            long size = getSize();
            if (isSmallBox(size)) {
                ks.a(this.header, size);
            } else {
                ks.a(this.header, 1L);
            }
            this.header.put(js.a("mdat"));
            if (isSmallBox(size)) {
                this.header.put(new byte[8]);
            } else {
                ks.b(this.header, size);
            }
            this.header.rewind();
            try {
                writableByteChannel.write(this.header);
            } catch (IOException e2) {
                SrsMp4Muxer.this.mHandler.notifyRecordIOException(e2);
            }
        }

        public long getContentSize() {
            return this.contentSize;
        }

        public int getHeaderSize() {
            return this.header.limit();
        }

        public qs getParent() {
            return this.parent;
        }

        @Override // supwisdom.os
        public long getSize() {
            return ((long) this.header.limit()) + this.contentSize;
        }

        public String getType() {
            return "mdat";
        }

        public void parse(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, long j, hs hsVar) throws IOException {
        }

        public void setContentSize(long j) {
            this.contentSize = j;
        }

        @Override // supwisdom.os
        public void setParent(qs qsVar) {
            this.parent = qsVar;
        }
    }

    public class Mp4Movie {
        public nt0 matrix;
        public HashMap<Integer, Track> tracks;

        public Mp4Movie() {
            this.matrix = nt0.j;
            this.tracks = new HashMap<>();
        }

        public void addSample(int i, long j, MediaCodec.BufferInfo bufferInfo) {
            this.tracks.get(Integer.valueOf(i)).addSample(j, bufferInfo);
        }

        public void addTrack(MediaFormat mediaFormat, boolean z) {
            if (mediaFormat != null) {
                if (z) {
                    this.tracks.put(101, SrsMp4Muxer.this.new Track(this.tracks.size(), mediaFormat, true));
                } else {
                    this.tracks.put(100, SrsMp4Muxer.this.new Track(this.tracks.size(), mediaFormat, false));
                }
            }
        }

        public nt0 getMatrix() {
            return this.matrix;
        }

        public HashMap<Integer, Track> getTracks() {
            return this.tracks;
        }

        public void removeTrack(int i) {
            this.tracks.remove(Integer.valueOf(i));
        }
    }

    public class Sample {
        public long offset;
        public long size;

        public Sample(long j, long j2) {
            this.offset = 0L;
            this.size = 0L;
            this.offset = j;
            this.size = j2;
        }

        public long getOffset() {
            return this.offset;
        }

        public long getSize() {
            return this.size;
        }
    }

    public class SrsAnnexbSearch {
        public boolean match;
        public int nb_start_code;

        public SrsAnnexbSearch() {
            this.nb_start_code = 0;
            this.match = false;
        }
    }

    public class SrsAvcNaluType {
        public static final int AccessUnitDelimiter = 9;
        public static final int CodedSliceExt = 20;
        public static final int DataPartitionA = 2;
        public static final int DataPartitionB = 3;
        public static final int DataPartitionC = 4;
        public static final int EOSequence = 10;
        public static final int EOStream = 11;
        public static final int FilterData = 12;
        public static final int IDR = 5;
        public static final int LayerWithoutPartition = 19;
        public static final int NonIDR = 1;
        public static final int PPS = 8;
        public static final int PrefixNALU = 14;
        public static final int Reserved = 0;
        public static final int SEI = 6;
        public static final int SPS = 7;
        public static final int SPSExt = 13;
        public static final int SubsetSPS = 15;

        public SrsAvcNaluType() {
        }
    }

    public class SrsEsFrame {
        public ByteBuffer bb;
        public MediaCodec.BufferInfo bi;
        public boolean isKeyFrame;
        public int track;

        public SrsEsFrame() {
        }

        public boolean is_audio() {
            return this.track == 101;
        }

        public boolean is_video() {
            return this.track == 100;
        }
    }

    public class SrsEsFrameBytes {
        public ByteBuffer data;
        public int size;

        public SrsEsFrameBytes() {
        }
    }

    public class SrsRawH264Stream {
        public SrsRawH264Stream() {
        }

        public SrsEsFrameBytes annexb_demux(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
            SrsEsFrameBytes srsEsFrameBytes = new SrsEsFrameBytes();
            if (byteBuffer.position() < bufferInfo.size) {
                SrsAnnexbSearch srsAnnexbSearchSrs_avc_startswith_annexb = srs_avc_startswith_annexb(byteBuffer, bufferInfo);
                if (!srsAnnexbSearchSrs_avc_startswith_annexb.match || srsAnnexbSearchSrs_avc_startswith_annexb.nb_start_code < 3) {
                    Log.e(SrsMp4Muxer.TAG, "annexb not match.");
                    SrsMp4Muxer.this.mHandler.notifyRecordIllegalArgumentException(new IllegalArgumentException(String.format("annexb not match for %dB, pos=%d", Integer.valueOf(bufferInfo.size), Integer.valueOf(byteBuffer.position()))));
                }
                byteBuffer.slice();
                for (int i = 0; i < srsAnnexbSearchSrs_avc_startswith_annexb.nb_start_code; i++) {
                    byteBuffer.get();
                }
                srsEsFrameBytes.data = byteBuffer.slice();
                int iPosition = byteBuffer.position();
                while (byteBuffer.position() < bufferInfo.size && !srs_avc_startswith_annexb(byteBuffer, bufferInfo).match) {
                    byteBuffer.get();
                }
                srsEsFrameBytes.size = byteBuffer.position() - iPosition;
            }
            return srsEsFrameBytes;
        }

        public boolean is_pps(SrsEsFrameBytes srsEsFrameBytes) {
            return srsEsFrameBytes.size >= 1 && (srsEsFrameBytes.data.get(0) & co.j) == 8;
        }

        public boolean is_sps(SrsEsFrameBytes srsEsFrameBytes) {
            return srsEsFrameBytes.size >= 1 && (srsEsFrameBytes.data.get(0) & co.j) == 7;
        }

        public SrsAnnexbSearch srs_avc_startswith_annexb(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
            SrsAnnexbSearch srsAnnexbSearch = new SrsAnnexbSearch();
            srsAnnexbSearch.match = false;
            int iPosition = byteBuffer.position();
            while (true) {
                if (iPosition < bufferInfo.size - 3 && byteBuffer.get(iPosition) == 0) {
                    int i = iPosition + 1;
                    if (byteBuffer.get(i) != 0) {
                        break;
                    }
                    if (byteBuffer.get(iPosition + 2) == 1) {
                        srsAnnexbSearch.match = true;
                        srsAnnexbSearch.nb_start_code = (iPosition + 3) - byteBuffer.position();
                        break;
                    }
                    iPosition = i;
                } else {
                    break;
                }
            }
            return srsAnnexbSearch;
        }
    }

    public class Track {
        public long duration;
        public boolean first;
        public String handler;
        public ns headerBox;
        public int height;
        public boolean isAudio;
        public long lastPresentationTimeUs;
        public ct sampleDescriptionBox;
        public ArrayList<Long> sampleDurations;
        public LinkedList<Integer> syncSamples;
        public int timeScale;
        public int trackId;
        public float volume;
        public int width;
        public ArrayList<Sample> samples = new ArrayList<>();
        public Date creationTime = new Date();

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public Track(int i, MediaFormat mediaFormat, boolean z) {
            this.trackId = 0;
            this.duration = 0L;
            this.headerBox = null;
            this.sampleDescriptionBox = null;
            this.syncSamples = null;
            this.volume = 0.0f;
            ArrayList<Long> arrayList = new ArrayList<>();
            this.sampleDurations = arrayList;
            this.isAudio = false;
            this.lastPresentationTimeUs = 0L;
            this.first = true;
            this.trackId = i;
            this.isAudio = z;
            if (!z) {
                arrayList.add(3015L);
                this.duration = 3015L;
                this.width = mediaFormat.getInteger("width");
                this.height = mediaFormat.getInteger("height");
                this.timeScale = 90000;
                this.syncSamples = new LinkedList<>();
                this.handler = "vide";
                this.headerBox = new mt();
                this.sampleDescriptionBox = new ct();
                if (mediaFormat.getString("mime").contentEquals("video/avc")) {
                    qt qtVar = new qt("avc1");
                    qtVar.a(1);
                    qtVar.b(24);
                    qtVar.c(1);
                    qtVar.a(72.0d);
                    qtVar.b(72.0d);
                    qtVar.e(this.width);
                    qtVar.d(this.height);
                    qtVar.a("AVC Coding");
                    nt ntVar = new nt();
                    ntVar.f(1);
                    ntVar.b(SrsMp4Muxer.this.h264_sps.get(1));
                    ntVar.h(0);
                    ntVar.a(SrsMp4Muxer.this.h264_sps.get(3));
                    ntVar.g(3);
                    ntVar.b(SrsMp4Muxer.this.spsList);
                    ntVar.a(SrsMp4Muxer.this.ppsList);
                    ntVar.d(-1);
                    ntVar.c(-1);
                    ntVar.e(-1);
                    ntVar.a(false);
                    qtVar.a(ntVar);
                    this.sampleDescriptionBox.a(qtVar);
                    return;
                }
                return;
            }
            arrayList.add(1024L);
            this.duration = 1024L;
            this.volume = 1.0f;
            this.timeScale = mediaFormat.getInteger("sample-rate");
            this.handler = "soun";
            this.headerBox = new gt();
            this.sampleDescriptionBox = new ct();
            ot otVar = new ot("mp4a");
            otVar.b(mediaFormat.getInteger("channel-count"));
            otVar.a(mediaFormat.getInteger("sample-rate"));
            otVar.a(1);
            otVar.c(16);
            ct0 ct0Var = new ct0();
            it0 it0Var = new it0();
            it0Var.a(0);
            jt0 jt0Var = new jt0();
            jt0Var.a(2);
            it0Var.a(jt0Var);
            gt0 gt0Var = new gt0();
            gt0Var.b(64);
            gt0Var.c(5);
            gt0Var.a(1536);
            gt0Var.b(96000L);
            gt0Var.a(96000L);
            dt0 dt0Var = new dt0();
            dt0Var.a(2);
            dt0Var.c(((Integer) SrsMp4Muxer.samplingFrequencyIndexMap.get(Integer.valueOf((int) otVar.g()))).intValue());
            dt0Var.b(otVar.f());
            gt0Var.a(dt0Var);
            it0Var.a(gt0Var);
            ByteBuffer byteBufferA = it0Var.a();
            ct0Var.a(it0Var);
            ct0Var.d(byteBufferA);
            otVar.a(ct0Var);
            this.sampleDescriptionBox.a(otVar);
        }

        public void addSample(long j, MediaCodec.BufferInfo bufferInfo) {
            long j2 = bufferInfo.presentationTimeUs - this.lastPresentationTimeUs;
            if (j2 < 0) {
                return;
            }
            boolean z = (this.isAudio || (bufferInfo.flags & 1) == 0) ? false : true;
            this.samples.add(SrsMp4Muxer.this.new Sample(j, bufferInfo.size));
            LinkedList<Integer> linkedList = this.syncSamples;
            if (linkedList != null && z) {
                linkedList.add(Integer.valueOf(this.samples.size()));
            }
            long j3 = ((j2 * ((long) this.timeScale)) + 500000) / 1000000;
            this.lastPresentationTimeUs = bufferInfo.presentationTimeUs;
            if (!this.first) {
                ArrayList<Long> arrayList = this.sampleDurations;
                arrayList.add(arrayList.size() - 1, Long.valueOf(j3));
                this.duration += j3;
            }
            this.first = false;
        }

        public void clearSample() {
            this.first = true;
            this.samples.clear();
            this.syncSamples.clear();
            this.sampleDurations.clear();
        }

        public Date getCreationTime() {
            return this.creationTime;
        }

        public long getDuration() {
            return this.duration;
        }

        public String getHandler() {
            return this.handler;
        }

        public int getHeight() {
            return this.height;
        }

        public ns getMediaHeaderBox() {
            return this.headerBox;
        }

        public ct getSampleDescriptionBox() {
            return this.sampleDescriptionBox;
        }

        public ArrayList<Long> getSampleDurations() {
            return this.sampleDurations;
        }

        public ArrayList<Sample> getSamples() {
            return this.samples;
        }

        public long[] getSyncSamples() {
            LinkedList<Integer> linkedList = this.syncSamples;
            if (linkedList == null || linkedList.isEmpty()) {
                return null;
            }
            long[] jArr = new long[this.syncSamples.size()];
            for (int i = 0; i < this.syncSamples.size(); i++) {
                jArr[i] = this.syncSamples.get(i).intValue();
            }
            return jArr;
        }

        public int getTimeScale() {
            return this.timeScale;
        }

        public int getTrackId() {
            return this.trackId;
        }

        public float getVolume() {
            return this.volume;
        }

        public int getWidth() {
            return this.width;
        }

        public boolean isAudio() {
            return this.isAudio;
        }
    }

    static {
        HashMap map = new HashMap();
        samplingFrequencyIndexMap = map;
        map.put(96000, 0);
        samplingFrequencyIndexMap.put(88200, 1);
        samplingFrequencyIndexMap.put(64000, 2);
        samplingFrequencyIndexMap.put(48000, 3);
        samplingFrequencyIndexMap.put(44100, 4);
        samplingFrequencyIndexMap.put(32000, 5);
        samplingFrequencyIndexMap.put(24000, 6);
        samplingFrequencyIndexMap.put(Integer.valueOf(SrsFlvMuxer.SrsCodecAudioSampleRate.R22050), 7);
        samplingFrequencyIndexMap.put(16000, 8);
        samplingFrequencyIndexMap.put(12000, 9);
        samplingFrequencyIndexMap.put(Integer.valueOf(SrsFlvMuxer.SrsCodecAudioSampleRate.R11025), 10);
        samplingFrequencyIndexMap.put(8000, 11);
    }

    public SrsMp4Muxer(SrsRecordHandler srsRecordHandler) {
        this.avc = new SrsRawH264Stream();
        this.mp4Movie = new Mp4Movie();
        this.mHandler = srsRecordHandler;
    }

    private us createFileTypeBox() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("isom");
        linkedList.add("3gp4");
        return new us("isom", 0L, linkedList);
    }

    private void createMovie(File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.fos = fileOutputStream;
            this.fc = fileOutputStream.getChannel();
            this.mdat = new InterleaveChunkMdat();
            this.mdatOffset = 0L;
            us usVarCreateFileTypeBox = createFileTypeBox();
            usVarCreateFileTypeBox.getBox(this.fc);
            this.recFileSize += usVarCreateFileTypeBox.getSize();
        } catch (IOException e2) {
            e2.printStackTrace();
            this.mHandler.notifyRecordIOException(e2);
        }
    }

    private at createMovieBox(Mp4Movie mp4Movie) {
        at atVar = new at();
        bt btVar = new bt();
        btVar.a(new Date());
        btVar.b(new Date());
        btVar.a(nt0.j);
        long timescale = getTimescale(mp4Movie);
        long j = 0;
        for (Track track : mp4Movie.getTracks().values()) {
            long duration = (track.getDuration() * timescale) / ((long) track.getTimeScale());
            if (duration > j) {
                j = duration;
            }
        }
        btVar.a(j);
        btVar.c(timescale);
        btVar.b(mp4Movie.getTracks().size() + 1);
        atVar.a(btVar);
        Iterator<Track> it = mp4Movie.getTracks().values().iterator();
        while (it.hasNext()) {
            atVar.a(createTrackBox(it.next(), mp4Movie));
        }
        return atVar;
    }

    private os createStbl(Track track) {
        et etVar = new et();
        createStsd(track, etVar);
        createStts(track, etVar);
        createStss(track, etVar);
        createStsc(track, etVar);
        createStsz(track, etVar);
        createStco(track, etVar);
        return etVar;
    }

    private void createStco(Track track, et etVar) {
        ArrayList arrayList = new ArrayList();
        long size = -1;
        for (Sample sample : track.getSamples()) {
            long offset = sample.getOffset();
            if (size != -1 && size != offset) {
                size = -1;
            }
            if (size == -1) {
                arrayList.add(Long.valueOf(offset));
            }
            size = sample.getSize() + offset;
        }
        long[] jArr = new long[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            jArr[i] = ((Long) arrayList.get(i)).longValue();
        }
        ht htVar = new ht();
        htVar.a(jArr);
        etVar.a(htVar);
    }

    private void createStsc(Track track, et etVar) {
        ft ftVar = new ft();
        ftVar.a(new LinkedList());
        int size = track.getSamples().size();
        int i = -1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < size) {
            Sample sample = track.getSamples().get(i2);
            i3++;
            if (i2 == size + (-1) || sample.getOffset() + sample.getSize() != track.getSamples().get(i2 + 1).getOffset()) {
                if (i != i3) {
                    ftVar.g().add(new ft.a(i4, i3, 1L));
                    i = i3;
                }
                i4++;
                i3 = 0;
            }
            i2++;
        }
        etVar.a(ftVar);
    }

    private void createStsd(Track track, et etVar) {
        etVar.a(track.getSampleDescriptionBox());
    }

    private void createStss(Track track, et etVar) {
        long[] syncSamples = track.getSyncSamples();
        if (syncSamples == null || syncSamples.length <= 0) {
            return;
        }
        it itVar = new it();
        itVar.a(syncSamples);
        etVar.a(itVar);
    }

    private void createStsz(Track track, et etVar) {
        dt dtVar = new dt();
        dtVar.a(this.track2SampleSizes.get(track));
        etVar.a(dtVar);
    }

    private void createStts(Track track, et etVar) {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = track.getSampleDurations().iterator();
        jt.a aVar = null;
        while (it.hasNext()) {
            long jLongValue = it.next().longValue();
            if (aVar == null || aVar.b() != jLongValue) {
                aVar = new jt.a(1L, jLongValue);
                arrayList.add(aVar);
            } else {
                aVar.a(aVar.a() + 1);
            }
        }
        jt jtVar = new jt();
        jtVar.a(arrayList);
        etVar.a(jtVar);
    }

    private kt createTrackBox(Track track, Mp4Movie mp4Movie) {
        kt ktVar = new kt();
        lt ltVar = new lt();
        ltVar.a(true);
        ltVar.b(true);
        ltVar.c(true);
        if (track.isAudio()) {
            ltVar.a(nt0.j);
        } else {
            ltVar.a(mp4Movie.getMatrix());
        }
        ltVar.b(0);
        ltVar.a(track.getCreationTime());
        ltVar.b(track.getCreationTime());
        ltVar.a((track.getDuration() * getTimescale(mp4Movie)) / ((long) track.getTimeScale()));
        ltVar.a(track.getHeight());
        ltVar.b(track.getWidth());
        ltVar.c(0);
        ltVar.b(new Date());
        ltVar.b(track.getTrackId() + 1);
        ltVar.a(track.getVolume());
        ktVar.a(ltVar);
        xs xsVar = new xs();
        ktVar.a(xsVar);
        ys ysVar = new ys();
        ysVar.a(track.getCreationTime());
        ysVar.b(track.getCreationTime());
        ysVar.a(track.getDuration());
        ysVar.b(track.getTimeScale());
        ysVar.a("eng");
        xsVar.a(ysVar);
        ws wsVar = new ws();
        wsVar.b(track.isAudio() ? "SoundHandle" : "VideoHandle");
        wsVar.a(track.getHandler());
        xsVar.a(wsVar);
        zs zsVar = new zs();
        zsVar.a(track.getMediaHeaderBox());
        ss ssVar = new ss();
        ts tsVar = new ts();
        ssVar.a(tsVar);
        rs rsVar = new rs();
        rsVar.a(1);
        tsVar.a(rsVar);
        zsVar.a(ssVar);
        zsVar.a(createStbl(track));
        xsVar.a(zsVar);
        return ktVar;
    }

    private void finishMovie() {
        try {
            if (this.flushBytes > 0) {
                this.fos.flush();
                this.flushBytes = 0L;
            }
            if (this.mdat.getSize() != 0) {
                long jPosition = this.fc.position();
                this.fc.position(this.mdatOffset);
                this.mdat.setContentSize((this.recFileSize - ((long) this.mdat.getHeaderSize())) - this.mdatOffset);
                this.mdat.getBox(this.fc);
                this.fc.position(jPosition);
                this.mdat.setContentSize(0L);
                this.fos.flush();
            }
            for (Track track : this.mp4Movie.getTracks().values()) {
                ArrayList<Sample> samples = track.getSamples();
                int size = samples.size();
                long[] jArr = new long[size];
                for (int i = 0; i < size; i++) {
                    jArr[i] = samples.get(i).getSize();
                }
                this.track2SampleSizes.put(track, jArr);
            }
            createMovieBox(this.mp4Movie).getBox(this.fc);
            this.fos.flush();
            this.fc.close();
            this.fos.close();
            this.mp4Movie.getTracks().clear();
            this.track2SampleSizes.clear();
            this.recFileSize = 0L;
            this.flushBytes = 0L;
        } catch (IOException e2) {
            this.mHandler.notifyRecordIOException(e2);
        }
    }

    private long getTimescale(Mp4Movie mp4Movie) {
        long timeScale = !mp4Movie.getTracks().isEmpty() ? mp4Movie.getTracks().values().iterator().next().getTimeScale() : 0L;
        Iterator<Track> it = mp4Movie.getTracks().values().iterator();
        while (it.hasNext()) {
            timeScale = mt0.a(it.next().getTimeScale(), timeScale);
        }
        return timeScale;
    }

    private void writeAudioSample(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (this.aacSpecConfig) {
            writeFrameByte(101, byteBuffer, bufferInfo, false);
        } else {
            this.aacSpecConfig = true;
        }
    }

    private void writeFrameByte(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, boolean z) {
        SrsEsFrame srsEsFrame = new SrsEsFrame();
        srsEsFrame.bb = byteBuffer;
        srsEsFrame.bi = bufferInfo;
        srsEsFrame.isKeyFrame = z;
        srsEsFrame.track = i;
        if (!this.bRecording || this.bPaused) {
            return;
        }
        if (!this.needToFindKeyFrame) {
            this.frameCache.add(srsEsFrame);
            synchronized (this.writeLock) {
                this.writeLock.notifyAll();
            }
            return;
        }
        if (srsEsFrame.isKeyFrame) {
            this.needToFindKeyFrame = false;
            this.frameCache.add(srsEsFrame);
            synchronized (this.writeLock) {
                this.writeLock.notifyAll();
            }
        }
    }

    private void writeVideoSample(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        int i = byteBuffer.get(4) & co.j;
        if (i == 5 || i == 1) {
            writeFrameByte(100, byteBuffer, bufferInfo, i == 5);
            return;
        }
        while (byteBuffer.position() < bufferInfo.size) {
            SrsEsFrameBytes srsEsFrameBytesAnnexb_demux = this.avc.annexb_demux(byteBuffer, bufferInfo);
            if (this.avc.is_sps(srsEsFrameBytesAnnexb_demux)) {
                if (!srsEsFrameBytesAnnexb_demux.data.equals(this.h264_sps)) {
                    byte[] bArr = new byte[srsEsFrameBytesAnnexb_demux.size];
                    srsEsFrameBytesAnnexb_demux.data.get(bArr);
                    this.h264_sps = ByteBuffer.wrap(bArr);
                    this.spsList.clear();
                    this.spsList.add(bArr);
                }
            } else if (this.avc.is_pps(srsEsFrameBytesAnnexb_demux) && !srsEsFrameBytesAnnexb_demux.data.equals(this.h264_pps)) {
                byte[] bArr2 = new byte[srsEsFrameBytesAnnexb_demux.size];
                srsEsFrameBytesAnnexb_demux.data.get(bArr2);
                this.h264_pps = ByteBuffer.wrap(bArr2);
                this.ppsList.clear();
                this.ppsList.add(bArr2);
            }
        }
    }

    public int addTrack(MediaFormat mediaFormat) {
        if (mediaFormat.getString("mime").contentEquals("video/avc")) {
            this.videoFormat = mediaFormat;
            return 100;
        }
        this.audioFormat = mediaFormat;
        return 101;
    }

    public void pause() {
        if (this.bRecording) {
            this.bPaused = true;
            this.mHandler.notifyRecordPause();
        }
    }

    public boolean record(File file) {
        if (this.videoFormat == null && this.audioFormat == null) {
            return false;
        }
        this.mRecFile = file;
        createMovie(file);
        this.mHandler.notifyRecordStarted(this.mRecFile.getPath());
        if (!this.spsList.isEmpty() && !this.ppsList.isEmpty()) {
            this.mp4Movie.addTrack(this.videoFormat, false);
        }
        this.mp4Movie.addTrack(this.audioFormat, true);
        Thread thread = new Thread(new Runnable() { // from class: net.ossrs.yasea.SrsMp4Muxer.1
            @Override // java.lang.Runnable
            public void run() {
                SrsMp4Muxer.this.bRecording = true;
                while (SrsMp4Muxer.this.bRecording) {
                    while (!SrsMp4Muxer.this.frameCache.isEmpty()) {
                        SrsEsFrame srsEsFrame = (SrsEsFrame) SrsMp4Muxer.this.frameCache.poll();
                        SrsMp4Muxer.this.writeSampleData(srsEsFrame.bb, srsEsFrame.bi, srsEsFrame.is_audio());
                    }
                    synchronized (SrsMp4Muxer.this.writeLock) {
                        try {
                            SrsMp4Muxer.this.writeLock.wait(500L);
                        } catch (InterruptedException unused) {
                            SrsMp4Muxer.this.worker.interrupt();
                        }
                    }
                }
            }
        });
        this.worker = thread;
        thread.start();
        return true;
    }

    public void resume() {
        if (this.bRecording) {
            this.bPaused = false;
            this.needToFindKeyFrame = true;
            this.mHandler.notifyRecordResume();
        }
    }

    public void stop() {
        this.bRecording = false;
        this.bPaused = false;
        this.needToFindKeyFrame = true;
        this.aacSpecConfig = false;
        this.frameCache.clear();
        Thread thread = this.worker;
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                this.worker.interrupt();
            }
            this.worker = null;
            finishMovie();
            this.mHandler.notifyRecordFinished(this.mRecFile.getPath());
        }
        Log.i(TAG, "SrsMp4Muxer closed");
    }

    public void writeSampleData(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (100 == i) {
            writeVideoSample(byteBuffer, bufferInfo);
        } else {
            writeAudioSample(byteBuffer, bufferInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeSampleData(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, boolean z) {
        int i = z ? 101 : 100;
        if (this.mp4Movie.getTracks().containsKey(Integer.valueOf(i))) {
            try {
                if (this.mdat.first) {
                    this.mdat.setContentSize(0L);
                    this.mdat.getBox(this.fc);
                    this.mdatOffset = this.recFileSize;
                    this.recFileSize += (long) this.mdat.getHeaderSize();
                    this.mdat.first = false;
                }
                this.mp4Movie.addSample(i, this.recFileSize, bufferInfo);
                byteBuffer.position(bufferInfo.offset + (z ? 0 : 4));
                byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                if (!z) {
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
                    byteBufferAllocate.position(0);
                    byteBufferAllocate.putInt(bufferInfo.size - 4);
                    byteBufferAllocate.position(0);
                    this.recFileSize += (long) this.fc.write(byteBufferAllocate);
                }
                long jWrite = this.fc.write(byteBuffer);
                this.recFileSize += jWrite;
                this.flushBytes += jWrite;
                if (this.flushBytes > 65536) {
                    this.fos.flush();
                    this.flushBytes = 0L;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                this.mHandler.notifyRecordIOException(e2);
            }
        }
    }
}
