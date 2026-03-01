package net.ossrs.yasea;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import com.igexin.c.a.d.g;
import com.umeng.analytics.pro.co;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import net.ossrs.yasea.SrsAllocator;
import org.bouncycastle.util.encoders.UTF8;
import supwisdom.h00;
import supwisdom.i00;

/* JADX INFO: loaded from: classes3.dex */
public class SrsFlvMuxer {
    public static final int AUDIO_ALLOC_SIZE = 4096;
    public static final int AUDIO_TRACK = 101;
    public static final String TAG = "SrsFlvMuxer";
    public static final int VIDEO_ALLOC_SIZE = 131072;
    public static final int VIDEO_TRACK = 100;
    public SrsFlvFrame mAudioSequenceHeader;
    public SrsFlvFrame mVideoSequenceHeader;
    public h00 publisher;
    public Thread worker;
    public volatile boolean started = false;
    public final Object txFrameLock = new Object();
    public SrsFlv flv = new SrsFlv();
    public boolean needToFindKeyFrame = true;
    public SrsAllocator mVideoAllocator = new SrsAllocator(131072);
    public SrsAllocator mAudioAllocator = new SrsAllocator(4096);
    public ConcurrentLinkedQueue<SrsFlvFrame> mFlvTagCache = new ConcurrentLinkedQueue<>();

    public class SrsAacObjectType {
        public static final int AacHE = 5;
        public static final int AacHEV2 = 29;
        public static final int AacLC = 2;
        public static final int AacMain = 1;
        public static final int AacSSR = 3;
        public static final int Reserved = 0;

        public SrsAacObjectType() {
        }
    }

    public class SrsAacProfile {
        public static final int LC = 1;
        public static final int Main = 0;
        public static final int Reserved = 3;
        public static final int SSR = 2;

        public SrsAacProfile() {
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

    public class SrsCodecAudioSampleRate {
        public static final int R11025 = 11025;
        public static final int R16000 = 16000;
        public static final int R22050 = 22050;
        public static final int R32000 = 32000;
        public static final int R44100 = 44100;
        public static final int R5512 = 5512;

        public SrsCodecAudioSampleRate() {
        }
    }

    public class SrsCodecFlvTag {
        public static final int Audio = 8;
        public static final int Reserved = 0;
        public static final int Script = 18;
        public static final int Video = 9;

        public SrsCodecFlvTag() {
        }
    }

    public class SrsCodecVideo {
        public static final int AVC = 7;
        public static final int Disabled = 8;
        public static final int On2VP6 = 4;
        public static final int On2VP6WithAlphaChannel = 5;
        public static final int Reserved = 0;
        public static final int Reserved1 = 1;
        public static final int Reserved2 = 9;
        public static final int ScreenVideo = 3;
        public static final int ScreenVideoVersion2 = 6;
        public static final int SorensonH263 = 2;

        public SrsCodecVideo() {
        }
    }

    public class SrsCodecVideoAVCFrame {
        public static final int DisposableInterFrame = 3;
        public static final int GeneratedKeyFrame = 4;
        public static final int InterFrame = 2;
        public static final int KeyFrame = 1;
        public static final int Reserved = 0;
        public static final int Reserved1 = 6;
        public static final int VideoInfoFrame = 5;

        public SrsCodecVideoAVCFrame() {
        }
    }

    public class SrsCodecVideoAVCType {
        public static final int NALU = 1;
        public static final int Reserved = 3;
        public static final int SequenceHeader = 0;
        public static final int SequenceHeaderEOF = 2;

        public SrsCodecVideoAVCType() {
        }
    }

    public class SrsFlv {
        public boolean aac_specific_config_got;
        public int achannel;
        public int asample_rate;
        public MediaFormat audioTrack;
        public SrsAllocator.Allocation audio_tag;
        public SrsRawH264Stream avc;
        public ByteBuffer h264_pps;
        public boolean h264_pps_changed;
        public ByteBuffer h264_sps;
        public boolean h264_sps_changed;
        public boolean h264_sps_pps_sent;
        public ArrayList<SrsFlvFrameBytes> ipbs = new ArrayList<>();
        public MediaFormat videoTrack;
        public SrsAllocator.Allocation video_tag;

        public SrsFlv() {
            this.avc = new SrsRawH264Stream();
            reset();
        }

        private void flvTagCacheAdd(SrsFlvFrame srsFlvFrame) {
            if (SrsFlvMuxer.this.started) {
                SrsFlvMuxer.this.mFlvTagCache.add(srsFlvFrame);
                if (srsFlvFrame.isVideo()) {
                    SrsFlvMuxer.this.getVideoFrameCacheNumber().incrementAndGet();
                }
            }
            synchronized (SrsFlvMuxer.this.txFrameLock) {
                SrsFlvMuxer.this.txFrameLock.notifyAll();
            }
        }

        private void writeAdtsHeader(byte[] bArr, int i) {
            bArr[i] = -1;
            int i2 = i + 1;
            bArr[i2] = -16;
            bArr[i2] = (byte) (bArr[i2] | 0);
            bArr[i2] = (byte) (bArr[i2] | 0);
            bArr[i2] = (byte) (bArr[i2] | 1);
            int i3 = i + 2;
            bArr[i3] = UTF8.S_P3B;
            bArr[i3] = (byte) (bArr[i3] | 16);
            bArr[i3] = (byte) (bArr[i3] | 0);
            int i4 = i + 3;
            bArr[i4] = g.n;
            bArr[i4] = (byte) (bArr[i4] | 0);
            bArr[i4] = (byte) (bArr[i4] | 0);
            bArr[i4] = (byte) (bArr[i4] | 0);
            bArr[i4] = (byte) (bArr[i4] | 0);
            bArr[i4] = (byte) (bArr[i4] | (((bArr.length - 2) & 6144) >> 11));
            bArr[i + 4] = (byte) (((bArr.length - 2) & 2040) >> 3);
            int i5 = i + 5;
            bArr[i5] = (byte) (((bArr.length - 2) & 7) << 5);
            bArr[i5] = (byte) (bArr[i5] | co.j);
            int i6 = i + 6;
            bArr[i6] = -4;
            bArr[i6] = (byte) (bArr[i6] | 0);
        }

        private void writeH264IpbFrame(ArrayList<SrsFlvFrameBytes> arrayList, int i, int i2, int i3) {
            if (this.h264_sps_pps_sent) {
                SrsAllocator.Allocation allocationMuxFlvTag = this.avc.muxFlvTag(arrayList, i, 1, i2, i3);
                this.video_tag = allocationMuxFlvTag;
                writeRtmpPacket(9, i2, i, 1, allocationMuxFlvTag);
            }
        }

        private void writeH264SpsPps(int i, int i2) {
            if ((this.h264_sps_pps_sent && !this.h264_sps_changed && !this.h264_pps_changed) || this.h264_pps == null || this.h264_sps == null) {
                return;
            }
            ArrayList<SrsFlvFrameBytes> arrayList = new ArrayList<>();
            this.avc.muxSequenceHeader(this.h264_sps, this.h264_pps, i, i2, arrayList);
            SrsAllocator.Allocation allocationMuxFlvTag = this.avc.muxFlvTag(arrayList, 1, 0, i, i2);
            this.video_tag = allocationMuxFlvTag;
            writeRtmpPacket(9, i, 1, 0, allocationMuxFlvTag);
            this.h264_sps_changed = false;
            this.h264_pps_changed = false;
            this.h264_sps_pps_sent = true;
            Log.i("SrsFlvMuxer", String.format("flv: h264 sps/pps sent, sps=%dB, pps=%dB", Integer.valueOf(this.h264_sps.array().length), Integer.valueOf(this.h264_pps.array().length)));
        }

        private void writeRtmpPacket(int i, int i2, int i3, int i4, SrsAllocator.Allocation allocation) {
            SrsFlvFrame srsFlvFrame = new SrsFlvFrame();
            srsFlvFrame.flvTag = allocation;
            srsFlvFrame.type = i;
            srsFlvFrame.dts = i2;
            srsFlvFrame.frame_type = i3;
            srsFlvFrame.avc_aac_type = i4;
            if (!srsFlvFrame.isVideo()) {
                if (srsFlvFrame.isAudio()) {
                    flvTagCacheAdd(srsFlvFrame);
                }
            } else if (!SrsFlvMuxer.this.needToFindKeyFrame) {
                flvTagCacheAdd(srsFlvFrame);
            } else if (srsFlvFrame.isKeyFrame()) {
                SrsFlvMuxer.this.needToFindKeyFrame = false;
                flvTagCacheAdd(srsFlvFrame);
            }
        }

        public void reset() {
            this.h264_sps_changed = false;
            this.h264_pps_changed = false;
            this.h264_sps_pps_sent = false;
            this.aac_specific_config_got = false;
            ByteBuffer byteBuffer = this.h264_sps;
            if (byteBuffer != null) {
                Arrays.fill(byteBuffer.array(), (byte) 0);
                this.h264_sps.clear();
            }
            ByteBuffer byteBuffer2 = this.h264_pps;
            if (byteBuffer2 != null) {
                Arrays.fill(byteBuffer2.array(), (byte) 0);
                this.h264_pps.clear();
            }
        }

        public void setAudioTrack(MediaFormat mediaFormat) {
            this.audioTrack = mediaFormat;
            this.achannel = mediaFormat.getInteger("channel-count");
            this.asample_rate = mediaFormat.getInteger("sample-rate");
        }

        public void setVideoTrack(MediaFormat mediaFormat) {
            this.videoTrack = mediaFormat;
        }

        public void writeAudioSample(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
            byte b;
            int i = (int) (bufferInfo.presentationTimeUs / 1000);
            SrsAllocator.Allocation allocationAllocate = SrsFlvMuxer.this.mAudioAllocator.allocate(bufferInfo.size + 2);
            this.audio_tag = allocationAllocate;
            int i2 = 3;
            if (this.aac_specific_config_got) {
                byteBuffer.get(allocationAllocate.array(), 2, bufferInfo.size);
                this.audio_tag.appendOffset(bufferInfo.size + 2);
                b = 1;
            } else {
                byte b2 = (byte) (byteBuffer.get(0) & 248);
                int i3 = this.asample_rate;
                int i4 = i3 == 22050 ? 7 : i3 == 11025 ? 10 : i3 == 32000 ? 5 : i3 == 16000 ? 8 : 4;
                this.audio_tag.put((byte) (b2 | ((i4 >> 1) & 7)), 2);
                this.audio_tag.put((byte) (((byte) ((i4 << 7) & 128)) | (((this.achannel == 2 ? 2 : 1) << 3) & 120)), 3);
                this.aac_specific_config_got = true;
                writeAdtsHeader(this.audio_tag.array(), 4);
                this.audio_tag.appendOffset(7);
                b = 0;
            }
            int i5 = this.achannel == 2 ? 1 : 0;
            int i6 = this.asample_rate;
            if (i6 == 22050) {
                i2 = 2;
            } else if (i6 == 11025) {
                i2 = 1;
            } else if (i6 == 5512) {
                i2 = 0;
            }
            this.audio_tag.put((byte) (((byte) (((byte) (((byte) (i5 & 1)) | 2)) | ((i2 << 2) & 12))) | 160), 0);
            this.audio_tag.put(b, 1);
            writeRtmpPacket(8, i, 0, b, this.audio_tag);
        }

        public void writeVideoSample(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
            if (bufferInfo.size < 4) {
                return;
            }
            int i = (int) (bufferInfo.presentationTimeUs / 1000);
            int i2 = 2;
            SrsFlvFrameBytes srsFlvFrameBytesDemuxAnnexb = this.avc.demuxAnnexb(byteBuffer, bufferInfo, true);
            int i3 = srsFlvFrameBytesDemuxAnnexb.data.get(0) & co.j;
            if (i3 == 5) {
                i2 = 1;
            } else {
                if (i3 == 7 || i3 == 8) {
                    SrsFlvFrameBytes srsFlvFrameBytesDemuxAnnexb2 = this.avc.demuxAnnexb(byteBuffer, bufferInfo, false);
                    srsFlvFrameBytesDemuxAnnexb.size = (srsFlvFrameBytesDemuxAnnexb.size - srsFlvFrameBytesDemuxAnnexb2.size) - 4;
                    if (!srsFlvFrameBytesDemuxAnnexb.data.equals(this.h264_sps)) {
                        byte[] bArr = new byte[srsFlvFrameBytesDemuxAnnexb.size];
                        srsFlvFrameBytesDemuxAnnexb.data.get(bArr);
                        this.h264_sps_changed = true;
                        this.h264_sps = ByteBuffer.wrap(bArr);
                    }
                    SrsFlvFrameBytes srsFlvFrameBytesDemuxAnnexb3 = this.avc.demuxAnnexb(byteBuffer, bufferInfo, false);
                    if (srsFlvFrameBytesDemuxAnnexb3.size > 0 && 6 == (srsFlvFrameBytesDemuxAnnexb3.data.get(0) & co.j)) {
                        srsFlvFrameBytesDemuxAnnexb2.size = (srsFlvFrameBytesDemuxAnnexb2.size - srsFlvFrameBytesDemuxAnnexb3.size) - 3;
                    }
                    if (srsFlvFrameBytesDemuxAnnexb2.data.equals(this.h264_pps)) {
                        return;
                    }
                    byte[] bArr2 = new byte[srsFlvFrameBytesDemuxAnnexb2.size];
                    srsFlvFrameBytesDemuxAnnexb2.data.get(bArr2);
                    this.h264_pps_changed = true;
                    this.h264_pps = ByteBuffer.wrap(bArr2);
                    writeH264SpsPps(i, i);
                    return;
                }
                if (i3 != 1) {
                    return;
                }
            }
            this.ipbs.add(this.avc.muxNaluHeader(srsFlvFrameBytesDemuxAnnexb));
            this.ipbs.add(srsFlvFrameBytesDemuxAnnexb);
            writeH264IpbFrame(this.ipbs, i2, i, i);
            this.ipbs.clear();
        }
    }

    public class SrsFlvFrame {
        public int avc_aac_type;
        public int dts;
        public SrsAllocator.Allocation flvTag;
        public int frame_type;
        public int type;

        public SrsFlvFrame() {
        }

        public boolean isAudio() {
            return this.type == 8;
        }

        public boolean isKeyFrame() {
            return isVideo() && this.frame_type == 1;
        }

        public boolean isSequenceHeader() {
            return this.avc_aac_type == 0;
        }

        public boolean isVideo() {
            return this.type == 9;
        }
    }

    public class SrsFlvFrameBytes {
        public ByteBuffer data;
        public int size;

        public SrsFlvFrameBytes() {
        }
    }

    public class SrsRawAacStreamCodec {
        public int aac_object;
        public byte aac_packet_type;
        public byte channel_configuration;
        public byte[] frame;
        public short frame_length;
        public byte protection_absent;
        public byte sampling_frequency_index;
        public byte sound_format;
        public byte sound_rate;
        public byte sound_size;
        public byte sound_type;

        public SrsRawAacStreamCodec() {
        }
    }

    public class SrsRawH264Stream {
        public static final String TAG = "SrsFlvMuxer";
        public SrsAnnexbSearch annexb;
        public SrsFlvFrameBytes pps_bb;
        public SrsFlvFrameBytes pps_hdr;
        public SrsFlvFrameBytes seq_hdr;
        public SrsFlvFrameBytes sps_bb;
        public SrsFlvFrameBytes sps_hdr;

        public SrsRawH264Stream() {
            this.annexb = new SrsAnnexbSearch();
            this.seq_hdr = new SrsFlvFrameBytes();
            this.sps_hdr = new SrsFlvFrameBytes();
            this.sps_bb = new SrsFlvFrameBytes();
            this.pps_hdr = new SrsFlvFrameBytes();
            this.pps_bb = new SrsFlvFrameBytes();
        }

        private SrsAnnexbSearch searchAnnexb(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
            SrsAnnexbSearch srsAnnexbSearch = this.annexb;
            srsAnnexbSearch.match = false;
            srsAnnexbSearch.nb_start_code = 0;
            int iPosition = byteBuffer.position();
            while (true) {
                if (iPosition >= bufferInfo.size - 4) {
                    break;
                }
                if (byteBuffer.get(iPosition) == 0 && byteBuffer.get(iPosition + 1) == 0) {
                    int i = iPosition + 2;
                    if (byteBuffer.get(i) != 1) {
                        if (byteBuffer.get(i) == 0 && byteBuffer.get(iPosition + 3) == 1) {
                            SrsAnnexbSearch srsAnnexbSearch2 = this.annexb;
                            srsAnnexbSearch2.match = true;
                            srsAnnexbSearch2.nb_start_code = (iPosition + 4) - byteBuffer.position();
                            break;
                        }
                    } else {
                        SrsAnnexbSearch srsAnnexbSearch3 = this.annexb;
                        srsAnnexbSearch3.match = true;
                        srsAnnexbSearch3.nb_start_code = (iPosition + 3) - byteBuffer.position();
                        break;
                    }
                }
                iPosition++;
            }
            return this.annexb;
        }

        private SrsAnnexbSearch searchStartcode(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
            SrsAnnexbSearch srsAnnexbSearch = this.annexb;
            srsAnnexbSearch.match = false;
            srsAnnexbSearch.nb_start_code = 0;
            if (bufferInfo.size - 4 > 0) {
                if (byteBuffer.get(0) == 0 && byteBuffer.get(1) == 0 && byteBuffer.get(2) == 0 && byteBuffer.get(3) == 1) {
                    SrsAnnexbSearch srsAnnexbSearch2 = this.annexb;
                    srsAnnexbSearch2.match = true;
                    srsAnnexbSearch2.nb_start_code = 4;
                } else if (byteBuffer.get(0) == 0 && byteBuffer.get(1) == 0 && byteBuffer.get(2) == 1) {
                    SrsAnnexbSearch srsAnnexbSearch3 = this.annexb;
                    srsAnnexbSearch3.match = true;
                    srsAnnexbSearch3.nb_start_code = 3;
                }
            }
            return this.annexb;
        }

        public SrsFlvFrameBytes demuxAnnexb(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, boolean z) {
            SrsFlvFrameBytes srsFlvFrameBytes = new SrsFlvFrameBytes();
            if (byteBuffer.position() < bufferInfo.size - 4) {
                SrsAnnexbSearch srsAnnexbSearchSearchStartcode = z ? searchStartcode(byteBuffer, bufferInfo) : searchAnnexb(byteBuffer, bufferInfo);
                if (!srsAnnexbSearchSearchStartcode.match || srsAnnexbSearchSearchStartcode.nb_start_code < 3) {
                    Log.e("SrsFlvMuxer", "annexb not match.");
                } else {
                    for (int i = 0; i < srsAnnexbSearchSearchStartcode.nb_start_code; i++) {
                        byteBuffer.get();
                    }
                    srsFlvFrameBytes.data = byteBuffer.slice();
                    srsFlvFrameBytes.size = bufferInfo.size - byteBuffer.position();
                }
            }
            return srsFlvFrameBytes;
        }

        public boolean isPps(SrsFlvFrameBytes srsFlvFrameBytes) {
            return srsFlvFrameBytes.size >= 1 && (srsFlvFrameBytes.data.get(0) & co.j) == 8;
        }

        public boolean isSps(SrsFlvFrameBytes srsFlvFrameBytes) {
            return srsFlvFrameBytes.size >= 1 && (srsFlvFrameBytes.data.get(0) & co.j) == 7;
        }

        public SrsAllocator.Allocation muxFlvTag(ArrayList<SrsFlvFrameBytes> arrayList, int i, int i2, int i3, int i4) {
            int i5 = 5;
            for (int i6 = 0; i6 < arrayList.size(); i6++) {
                i5 += arrayList.get(i6).size;
            }
            SrsAllocator.Allocation allocationAllocate = SrsFlvMuxer.this.mVideoAllocator.allocate(i5);
            allocationAllocate.put((byte) ((i << 4) | 7));
            allocationAllocate.put((byte) i2);
            int i7 = i4 - i3;
            allocationAllocate.put((byte) (i7 >> 16));
            allocationAllocate.put((byte) (i7 >> 8));
            allocationAllocate.put((byte) i7);
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                SrsFlvFrameBytes srsFlvFrameBytes = arrayList.get(i8);
                srsFlvFrameBytes.data.get(allocationAllocate.array(), allocationAllocate.size(), srsFlvFrameBytes.size);
                allocationAllocate.appendOffset(srsFlvFrameBytes.size);
            }
            return allocationAllocate;
        }

        public SrsFlvFrameBytes muxNaluHeader(SrsFlvFrameBytes srsFlvFrameBytes) {
            SrsFlvFrameBytes srsFlvFrameBytes2 = new SrsFlvFrameBytes();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            srsFlvFrameBytes2.data = byteBufferAllocate;
            srsFlvFrameBytes2.size = 4;
            byteBufferAllocate.putInt(srsFlvFrameBytes.size);
            srsFlvFrameBytes2.data.rewind();
            return srsFlvFrameBytes2;
        }

        public void muxSequenceHeader(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, ArrayList<SrsFlvFrameBytes> arrayList) {
            SrsFlvFrameBytes srsFlvFrameBytes = this.seq_hdr;
            if (srsFlvFrameBytes.data == null) {
                srsFlvFrameBytes.data = ByteBuffer.allocate(5);
                this.seq_hdr.size = 5;
            }
            this.seq_hdr.data.rewind();
            byte b = byteBuffer.get(1);
            byte b2 = byteBuffer.get(3);
            this.seq_hdr.data.put((byte) 1);
            this.seq_hdr.data.put(b);
            this.seq_hdr.data.put((byte) 0);
            this.seq_hdr.data.put(b2);
            this.seq_hdr.data.put((byte) 3);
            this.seq_hdr.data.rewind();
            arrayList.add(this.seq_hdr);
            SrsFlvFrameBytes srsFlvFrameBytes2 = this.sps_hdr;
            if (srsFlvFrameBytes2.data == null) {
                srsFlvFrameBytes2.data = ByteBuffer.allocate(3);
                this.sps_hdr.size = 3;
            }
            this.sps_hdr.data.rewind();
            this.sps_hdr.data.put((byte) 1);
            this.sps_hdr.data.putShort((short) byteBuffer.array().length);
            this.sps_hdr.data.rewind();
            arrayList.add(this.sps_hdr);
            this.sps_bb.size = byteBuffer.array().length;
            this.sps_bb.data = byteBuffer.duplicate();
            arrayList.add(this.sps_bb);
            SrsFlvFrameBytes srsFlvFrameBytes3 = this.pps_hdr;
            if (srsFlvFrameBytes3.data == null) {
                srsFlvFrameBytes3.data = ByteBuffer.allocate(3);
                this.pps_hdr.size = 3;
            }
            this.pps_hdr.data.rewind();
            this.pps_hdr.data.put((byte) 1);
            this.pps_hdr.data.putShort((short) byteBuffer2.array().length);
            this.pps_hdr.data.rewind();
            arrayList.add(this.pps_hdr);
            this.pps_bb.size = byteBuffer2.array().length;
            this.pps_bb.data = byteBuffer2.duplicate();
            arrayList.add(this.pps_bb);
        }
    }

    public SrsFlvMuxer(i00 i00Var) {
        this.publisher = new h00(i00Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean connect(String str) {
        Log.i("SrsFlvMuxer", String.format("worker: connecting to RTMP server by url=%s\n", str));
        boolean zB = this.publisher.a(str) ? this.publisher.b("live") : false;
        this.mVideoSequenceHeader = null;
        this.mAudioSequenceHeader = null;
        return zB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnect() {
        try {
            this.publisher.a();
        } catch (IllegalStateException unused) {
        }
        this.mVideoSequenceHeader = null;
        this.mAudioSequenceHeader = null;
        Log.i("SrsFlvMuxer", "worker: disconnect ok.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFlvTag(SrsFlvFrame srsFlvFrame) {
        if (srsFlvFrame == null) {
            return;
        }
        if (srsFlvFrame.isVideo()) {
            if (srsFlvFrame.isKeyFrame()) {
                Log.i("SrsFlvMuxer", String.format("worker: send frame type=%d, dts=%d, size=%dB", Integer.valueOf(srsFlvFrame.type), Integer.valueOf(srsFlvFrame.dts), Integer.valueOf(srsFlvFrame.flvTag.array().length)));
            }
            this.publisher.b(srsFlvFrame.flvTag.array(), srsFlvFrame.flvTag.size(), srsFlvFrame.dts);
            this.mVideoAllocator.release(srsFlvFrame.flvTag);
            return;
        }
        if (srsFlvFrame.isAudio()) {
            this.publisher.a(srsFlvFrame.flvTag.array(), srsFlvFrame.flvTag.size(), srsFlvFrame.dts);
            this.mAudioAllocator.release(srsFlvFrame.flvTag);
        }
    }

    public int addTrack(MediaFormat mediaFormat) {
        if (mediaFormat.getString("mime").contentEquals("video/avc")) {
            this.flv.setVideoTrack(mediaFormat);
            return 100;
        }
        this.flv.setAudioTrack(mediaFormat);
        return 101;
    }

    public AtomicInteger getVideoFrameCacheNumber() {
        h00 h00Var = this.publisher;
        if (h00Var == null) {
            return null;
        }
        return h00Var.b();
    }

    public void setVideoResolution(int i, int i2) {
        h00 h00Var = this.publisher;
        if (h00Var != null) {
            h00Var.a(i, i2);
        }
    }

    public void start(final String str) {
        this.started = true;
        Thread thread = new Thread(new Runnable() { // from class: net.ossrs.yasea.SrsFlvMuxer.1
            @Override // java.lang.Runnable
            public void run() {
                if (SrsFlvMuxer.this.connect(str)) {
                    while (!Thread.interrupted()) {
                        while (!SrsFlvMuxer.this.mFlvTagCache.isEmpty()) {
                            SrsFlvFrame srsFlvFrame = (SrsFlvFrame) SrsFlvMuxer.this.mFlvTagCache.poll();
                            if (srsFlvFrame.isSequenceHeader()) {
                                if (srsFlvFrame.isVideo()) {
                                    SrsFlvMuxer.this.mVideoSequenceHeader = srsFlvFrame;
                                    SrsFlvMuxer srsFlvMuxer = SrsFlvMuxer.this;
                                    srsFlvMuxer.sendFlvTag(srsFlvMuxer.mVideoSequenceHeader);
                                } else if (srsFlvFrame.isAudio()) {
                                    SrsFlvMuxer.this.mAudioSequenceHeader = srsFlvFrame;
                                    SrsFlvMuxer srsFlvMuxer2 = SrsFlvMuxer.this;
                                    srsFlvMuxer2.sendFlvTag(srsFlvMuxer2.mAudioSequenceHeader);
                                }
                            } else if (srsFlvFrame.isVideo() && SrsFlvMuxer.this.mVideoSequenceHeader != null) {
                                SrsFlvMuxer.this.sendFlvTag(srsFlvFrame);
                            } else if (srsFlvFrame.isAudio() && SrsFlvMuxer.this.mAudioSequenceHeader != null) {
                                SrsFlvMuxer.this.sendFlvTag(srsFlvFrame);
                            }
                        }
                        synchronized (SrsFlvMuxer.this.txFrameLock) {
                            try {
                                SrsFlvMuxer.this.txFrameLock.wait(500L);
                            } catch (InterruptedException unused) {
                                SrsFlvMuxer.this.worker.interrupt();
                            }
                        }
                    }
                }
            }
        });
        this.worker = thread;
        thread.start();
    }

    public void stop() {
        this.started = false;
        this.mFlvTagCache.clear();
        Thread thread = this.worker;
        if (thread != null) {
            thread.interrupt();
            try {
                this.worker.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                this.worker.interrupt();
            }
            this.worker = null;
        }
        this.flv.reset();
        this.needToFindKeyFrame = true;
        Log.i("SrsFlvMuxer", "SrsFlvMuxer closed");
        new Thread(new Runnable() { // from class: net.ossrs.yasea.SrsFlvMuxer.2
            @Override // java.lang.Runnable
            public void run() {
                SrsFlvMuxer.this.disconnect();
            }
        }).start();
    }

    public void writeSampleData(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (bufferInfo.offset > 0) {
            Log.w("SrsFlvMuxer", String.format("encoded frame %dB, offset=%d pts=%dms", Integer.valueOf(bufferInfo.size), Integer.valueOf(bufferInfo.offset), Long.valueOf(bufferInfo.presentationTimeUs / 1000)));
        }
        if (100 == i) {
            this.flv.writeVideoSample(byteBuffer, bufferInfo);
        } else {
            this.flv.writeAudioSample(byteBuffer, bufferInfo);
        }
    }
}
