package io.dcloud.feature.audio.aac;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import net.ossrs.yasea.SrsFlvMuxer;

/* JADX INFO: loaded from: classes2.dex */
public class AacEncode {
    public static AacEncode mInstance;
    public MediaCodec.BufferInfo bufferInfo;
    public ByteBuffer[] inputBuffers;
    public int mChannelCount;
    public int mSampleRate;
    public MediaCodec mediaCodec;
    public ByteBuffer[] outputBuffers;
    public long presentationTimeUs = 0;
    public ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public HashMap<Integer, Integer> freqidxs = new HashMap<>();

    public AacEncode(int i, int i2) {
        this.inputBuffers = null;
        this.outputBuffers = null;
        try {
            this.mediaCodec = MediaCodec.createEncoderByType("audio/mp4a-latm");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.mSampleRate = i;
        this.mChannelCount = i2;
        initFreqidxs();
        MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, i2);
        mediaFormatCreateAudioFormat.setString("mime", "audio/mp4a-latm");
        mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
        mediaFormatCreateAudioFormat.setInteger("bitrate", new int[]{64000, 96000, 128000}[1]);
        mediaFormatCreateAudioFormat.setInteger("max-input-size", 1048576);
        this.mediaCodec.configure(mediaFormatCreateAudioFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mediaCodec.start();
        this.inputBuffers = this.mediaCodec.getInputBuffers();
        this.outputBuffers = this.mediaCodec.getOutputBuffers();
        this.bufferInfo = new MediaCodec.BufferInfo();
    }

    private void addADTStoPacket(byte[] bArr, int i) {
        int iIntValue = this.freqidxs.get(Integer.valueOf(this.mSampleRate)).intValue();
        int i2 = this.mChannelCount;
        bArr[0] = -1;
        bArr[1] = -7;
        bArr[2] = (byte) ((iIntValue << 2) + 64 + (i2 >> 2));
        bArr[3] = (byte) (((i2 & 3) << 6) + (i >> 11));
        bArr[4] = (byte) ((i & 2047) >> 3);
        bArr[5] = (byte) (((i & 7) << 5) + 31);
        bArr[6] = -4;
    }

    private long computePresentationTime(long j) {
        return ((j * 90000) * 1024) / ((long) this.mSampleRate);
    }

    public static AacEncode getAacEncode(int i, int i2) {
        if (mInstance == null) {
            mInstance = new AacEncode(i, i2);
        }
        return mInstance;
    }

    public void close() {
        try {
            this.mediaCodec.stop();
            this.mediaCodec.release();
            this.outputStream.flush();
            this.outputStream.close();
            mInstance = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void initFreqidxs() {
        this.freqidxs.put(96000, 0);
        this.freqidxs.put(88200, 1);
        this.freqidxs.put(64000, 2);
        this.freqidxs.put(48000, 3);
        this.freqidxs.put(44100, 4);
        this.freqidxs.put(32000, 5);
        this.freqidxs.put(24000, 6);
        this.freqidxs.put(Integer.valueOf(SrsFlvMuxer.SrsCodecAudioSampleRate.R22050), 7);
        this.freqidxs.put(16000, 8);
        this.freqidxs.put(12000, 9);
        this.freqidxs.put(Integer.valueOf(SrsFlvMuxer.SrsCodecAudioSampleRate.R11025), 10);
        this.freqidxs.put(8000, 11);
        this.freqidxs.put(7350, 12);
    }

    public byte[] offerEncoder(byte[] bArr) throws Exception {
        int iDequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(-1L);
        if (iDequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = this.inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr);
            byteBuffer.limit(bArr.length);
            this.mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, bArr.length, computePresentationTime(this.presentationTimeUs), 0);
            this.presentationTimeUs++;
        }
        int iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(this.bufferInfo, 0L);
        while (iDequeueOutputBuffer >= 0) {
            MediaCodec.BufferInfo bufferInfo = this.bufferInfo;
            int i = bufferInfo.size;
            int i2 = i + 7;
            ByteBuffer byteBuffer2 = this.outputBuffers[iDequeueOutputBuffer];
            byteBuffer2.position(bufferInfo.offset);
            byteBuffer2.limit(this.bufferInfo.offset + i);
            byte[] bArr2 = new byte[i2];
            addADTStoPacket(bArr2, i2);
            byteBuffer2.get(bArr2, 7, i);
            byteBuffer2.position(this.bufferInfo.offset);
            this.outputStream.write(bArr2);
            this.mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
            iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(this.bufferInfo, 0L);
        }
        byte[] byteArray = this.outputStream.toByteArray();
        this.outputStream.flush();
        this.outputStream.reset();
        return byteArray;
    }

    public static AacEncode getAacEncode() {
        return mInstance;
    }
}
