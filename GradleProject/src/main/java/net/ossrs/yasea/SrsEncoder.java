package net.ossrs.yasea;

import android.graphics.Rect;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public class SrsEncoder {
    public static final int ABITRATE = 65536;
    public static final String ACODEC = "audio/mp4a-latm";
    public static final int ASAMPLERATE = 44100;
    public static final String TAG = "SrsEncoder";
    public static final String VCODEC = "video/avc";
    public static final int VFPS = 24;
    public static final int VGOP = 48;
    public static int aChannelConfig = 12;
    public static int vBitrate = 1228800;
    public static int vLandscapeHeight = 360;
    public static int vLandscapeWidth = 640;
    public static int vOutHeight = 640;
    public static int vOutWidth = 360;
    public static int vPortraitHeight = 640;
    public static int vPortraitWidth = 360;
    public static int vPrevHeight = 360;
    public static int vPrevWidth = 640;
    public static String x264Preset = "veryfast";
    public MediaCodec aencoder;
    public int audioFlvTrack;
    public int audioMp4Track;
    public SrsFlvMuxer flvMuxer;
    public SrsEncodeHandler mHandler;
    public long mPausetime;
    public long mPresentTimeUs;
    public SrsMp4Muxer mp4Muxer;
    public MediaCodec vencoder;
    public int videoFlvTrack;
    public int videoMp4Track;
    public MediaCodecInfo vmci;
    public boolean networkWeakTriggered = false;
    public boolean mCameraFaceFront = true;
    public boolean useSoftEncoder = false;
    public boolean canSoftEncode = false;
    public int mVideoColorFormat = chooseVideoEncoder();

    static {
        System.loadLibrary("yuv");
        System.loadLibrary("enc");
    }

    public SrsEncoder(SrsEncodeHandler srsEncodeHandler) {
        this.mHandler = srsEncodeHandler;
    }

    private native byte[] ARGBToI420(int[] iArr, int i, int i2, boolean z, int i3);

    private native byte[] ARGBToI420Scaled(int[] iArr, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7);

    private native byte[] ARGBToNV12(int[] iArr, int i, int i2, boolean z, int i3);

    private native byte[] ARGBToNV12Scaled(int[] iArr, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7);

    private native byte[] NV21ToI420Scaled(byte[] bArr, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7);

    private native byte[] NV21ToNV12Scaled(byte[] bArr, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7);

    private native int RGBASoftEncode(byte[] bArr, int i, int i2, boolean z, int i3, long j);

    private native byte[] RGBAToI420(byte[] bArr, int i, int i2, boolean z, int i3);

    private native byte[] RGBAToNV12(byte[] bArr, int i, int i2, boolean z, int i3);

    private MediaCodecInfo chooseVideoEncoder(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (int i2 = 0; i2 < supportedTypes.length; i2++) {
                    if (supportedTypes[i2].equalsIgnoreCase("video/avc")) {
                        Log.i(TAG, String.format("vencoder %s types: %s", codecInfoAt.getName(), supportedTypes[i2]));
                        if (str == null || codecInfoAt.getName().contains(str)) {
                            return codecInfoAt;
                        }
                    }
                }
            }
        }
        return null;
    }

    private native void closeSoftEncoder();

    private int getPcmBufferSize() {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 12, 2) + 8191;
        return minBufferSize - (minBufferSize % 8192);
    }

    private byte[] hwArgbFrame(int[] iArr, int i, int i2) {
        int i3 = this.mVideoColorFormat;
        if (i3 == 19) {
            return ARGBToI420(iArr, i, i2, false, 0);
        }
        if (i3 == 21) {
            return ARGBToNV12(iArr, i, i2, false, 0);
        }
        throw new IllegalStateException("Unsupported color format!");
    }

    private byte[] hwArgbFrameScaled(int[] iArr, int i, int i2, Rect rect) {
        int i3 = this.mVideoColorFormat;
        if (i3 == 19) {
            return ARGBToI420Scaled(iArr, i, i2, false, 0, rect.left, rect.top, rect.width(), rect.height());
        }
        if (i3 == 21) {
            return ARGBToNV12Scaled(iArr, i, i2, false, 0, rect.left, rect.top, rect.width(), rect.height());
        }
        throw new IllegalStateException("Unsupported color format!");
    }

    private byte[] hwRgbaFrame(byte[] bArr, int i, int i2) {
        int i3 = this.mVideoColorFormat;
        if (i3 == 19) {
            return RGBAToI420(bArr, i, i2, true, 180);
        }
        if (i3 == 21) {
            return RGBAToNV12(bArr, i, i2, true, 180);
        }
        throw new IllegalStateException("Unsupported color format!");
    }

    private byte[] hwYUVNV21FrameScaled(byte[] bArr, int i, int i2, Rect rect) {
        int i3 = this.mVideoColorFormat;
        if (i3 == 19) {
            return NV21ToI420Scaled(bArr, i, i2, true, 180, rect.left, rect.top, rect.width(), rect.height());
        }
        if (i3 == 21) {
            return NV21ToNV12Scaled(bArr, i, i2, true, 180, rect.left, rect.top, rect.width(), rect.height());
        }
        throw new IllegalStateException("Unsupported color format!");
    }

    private void onEncodedAacFrame(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        this.mp4Muxer.writeSampleData(this.audioMp4Track, byteBuffer.duplicate(), bufferInfo);
        this.flvMuxer.writeSampleData(this.audioFlvTrack, byteBuffer, bufferInfo);
    }

    private void onEncodedAnnexbFrame(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        this.mp4Muxer.writeSampleData(this.videoMp4Track, byteBuffer.duplicate(), bufferInfo);
        this.flvMuxer.writeSampleData(this.videoFlvTrack, byteBuffer, bufferInfo);
    }

    private void onProcessedYuvFrame(byte[] bArr, long j) {
        ByteBuffer[] inputBuffers = this.vencoder.getInputBuffers();
        ByteBuffer[] outputBuffers = this.vencoder.getOutputBuffers();
        int iDequeueInputBuffer = this.vencoder.dequeueInputBuffer(-1L);
        if (iDequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr, 0, bArr.length);
            this.vencoder.queueInputBuffer(iDequeueInputBuffer, 0, bArr.length, j, 0);
        }
        while (true) {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.vencoder.dequeueOutputBuffer(bufferInfo, 0L);
            if (iDequeueOutputBuffer < 0) {
                return;
            }
            onEncodedAnnexbFrame(outputBuffers[iDequeueOutputBuffer], bufferInfo);
            this.vencoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
        }
    }

    private void onSoftEncodedData(byte[] bArr, long j, boolean z) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.offset = 0;
        bufferInfo.size = bArr.length;
        bufferInfo.presentationTimeUs = j;
        bufferInfo.flags = z ? 1 : 0;
        onEncodedAnnexbFrame(byteBufferWrap, bufferInfo);
    }

    private native boolean openSoftEncoder();

    private native void setEncoderBitrate(int i);

    private native void setEncoderFps(int i);

    private native void setEncoderGop(int i);

    private native void setEncoderPreset(String str);

    private native void setEncoderResolution(int i, int i2);

    private void swRgbaFrame(byte[] bArr, int i, int i2, long j) {
        RGBASoftEncode(bArr, i, i2, true, 180, j);
    }

    public boolean canHardEncode() {
        return this.vencoder != null;
    }

    public boolean canSoftEncode() {
        return this.canSoftEncode;
    }

    public AudioRecord chooseAudioRecord() {
        AudioRecord audioRecord = new AudioRecord(7, 44100, 12, 2, getPcmBufferSize() * 4);
        if (audioRecord.getState() == 1) {
            aChannelConfig = 12;
            return audioRecord;
        }
        AudioRecord audioRecord2 = new AudioRecord(7, 44100, 16, 2, getPcmBufferSize() * 4);
        if (audioRecord2.getState() != 1) {
            return null;
        }
        aChannelConfig = 16;
        return audioRecord2;
    }

    public int getOutputHeight() {
        return vOutHeight;
    }

    public int getOutputWidth() {
        return vOutWidth;
    }

    public int getPreviewHeight() {
        return vPrevHeight;
    }

    public int getPreviewWidth() {
        return vPrevWidth;
    }

    public boolean isEnabled() {
        return canHardEncode() || canSoftEncode();
    }

    public boolean isSoftEncoder() {
        return this.useSoftEncoder;
    }

    public void onGetArgbFrame(int[] iArr, int i, int i2, Rect rect) {
        AtomicInteger videoFrameCacheNumber = this.flvMuxer.getVideoFrameCacheNumber();
        if (videoFrameCacheNumber == null || videoFrameCacheNumber.get() >= 24) {
            this.mHandler.notifyNetworkWeak();
            this.networkWeakTriggered = true;
            return;
        }
        long jNanoTime = (System.nanoTime() / 1000) - this.mPresentTimeUs;
        if (this.useSoftEncoder) {
            throw new UnsupportedOperationException("Not implemented");
        }
        byte[] bArrHwArgbFrameScaled = hwArgbFrameScaled(iArr, i, i2, rect);
        if (bArrHwArgbFrameScaled != null) {
            onProcessedYuvFrame(bArrHwArgbFrameScaled, jNanoTime);
        } else {
            this.mHandler.notifyEncodeIllegalArgumentException(new IllegalArgumentException("libyuv failure"));
        }
        if (this.networkWeakTriggered) {
            this.networkWeakTriggered = false;
            this.mHandler.notifyNetworkResume();
        }
    }

    public void onGetPcmFrame(byte[] bArr, int i) {
        AtomicInteger videoFrameCacheNumber = this.flvMuxer.getVideoFrameCacheNumber();
        if (videoFrameCacheNumber == null || videoFrameCacheNumber.get() >= 24) {
            return;
        }
        ByteBuffer[] inputBuffers = this.aencoder.getInputBuffers();
        ByteBuffer[] outputBuffers = this.aencoder.getOutputBuffers();
        int iDequeueInputBuffer = this.aencoder.dequeueInputBuffer(-1L);
        if (iDequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = inputBuffers[iDequeueInputBuffer];
            byteBuffer.clear();
            byteBuffer.put(bArr, 0, i);
            this.aencoder.queueInputBuffer(iDequeueInputBuffer, 0, i, (System.nanoTime() / 1000) - this.mPresentTimeUs, 0);
        }
        while (true) {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.aencoder.dequeueOutputBuffer(bufferInfo, 0L);
            if (iDequeueOutputBuffer < 0) {
                return;
            }
            onEncodedAacFrame(outputBuffers[iDequeueOutputBuffer], bufferInfo);
            this.aencoder.releaseOutputBuffer(iDequeueOutputBuffer, false);
        }
    }

    public void onGetRgbaFrame(byte[] bArr, int i, int i2) {
        AtomicInteger videoFrameCacheNumber = this.flvMuxer.getVideoFrameCacheNumber();
        if (videoFrameCacheNumber == null || videoFrameCacheNumber.get() >= 24) {
            this.mHandler.notifyNetworkWeak();
            this.networkWeakTriggered = true;
            return;
        }
        long jNanoTime = (System.nanoTime() / 1000) - this.mPresentTimeUs;
        if (this.useSoftEncoder) {
            swRgbaFrame(bArr, i, i2, jNanoTime);
        } else {
            byte[] bArrHwRgbaFrame = hwRgbaFrame(bArr, i, i2);
            if (bArrHwRgbaFrame != null) {
                onProcessedYuvFrame(bArrHwRgbaFrame, jNanoTime);
            } else {
                this.mHandler.notifyEncodeIllegalArgumentException(new IllegalArgumentException("libyuv failure"));
            }
        }
        if (this.networkWeakTriggered) {
            this.networkWeakTriggered = false;
            this.mHandler.notifyNetworkResume();
        }
    }

    public void onGetYuvNV21Frame(byte[] bArr, int i, int i2, Rect rect) {
        AtomicInteger videoFrameCacheNumber = this.flvMuxer.getVideoFrameCacheNumber();
        if (videoFrameCacheNumber == null || videoFrameCacheNumber.get() >= 24) {
            this.mHandler.notifyNetworkWeak();
            this.networkWeakTriggered = true;
            return;
        }
        long jNanoTime = (System.nanoTime() / 1000) - this.mPresentTimeUs;
        if (this.useSoftEncoder) {
            throw new UnsupportedOperationException("Not implemented");
        }
        byte[] bArrHwYUVNV21FrameScaled = hwYUVNV21FrameScaled(bArr, i, i2, rect);
        if (bArrHwYUVNV21FrameScaled != null) {
            onProcessedYuvFrame(bArrHwYUVNV21FrameScaled, jNanoTime);
        } else {
            this.mHandler.notifyEncodeIllegalArgumentException(new IllegalArgumentException("libyuv failure"));
        }
        if (this.networkWeakTriggered) {
            this.networkWeakTriggered = false;
            this.mHandler.notifyNetworkResume();
        }
    }

    public void pause() {
        this.mPausetime = System.nanoTime() / 1000;
    }

    public void resume() {
        this.mPresentTimeUs += (System.nanoTime() / 1000) - this.mPausetime;
        this.mPausetime = 0L;
    }

    public void setCameraBackFace() {
        this.mCameraFaceFront = false;
    }

    public void setCameraFrontFace() {
        this.mCameraFaceFront = true;
    }

    public void setFlvMuxer(SrsFlvMuxer srsFlvMuxer) {
        this.flvMuxer = srsFlvMuxer;
    }

    public void setLandscapeResolution(int i, int i2) {
        vOutWidth = i;
        vOutHeight = i2;
        vLandscapeWidth = i;
        vLandscapeHeight = i2;
        vPortraitWidth = i2;
        vPortraitHeight = i;
    }

    public void setMp4Muxer(SrsMp4Muxer srsMp4Muxer) {
        this.mp4Muxer = srsMp4Muxer;
    }

    public void setPortraitResolution(int i, int i2) {
        vOutWidth = i;
        vOutHeight = i2;
        vPortraitWidth = i;
        vPortraitHeight = i2;
        vLandscapeWidth = i2;
        vLandscapeHeight = i;
    }

    public void setPreviewResolution(int i, int i2) {
        vPrevWidth = i;
        vPrevHeight = i2;
    }

    public void setScreenOrientation(int i) {
        if (i == 1) {
            vOutWidth = vPortraitWidth;
            vOutHeight = vPortraitHeight;
        } else if (i == 2) {
            vOutWidth = vLandscapeWidth;
            vOutHeight = vLandscapeHeight;
        }
        if (!this.useSoftEncoder && (vOutWidth % 32 != 0 || vOutHeight % 32 != 0)) {
            this.vmci.getName().contains("MTK");
        }
        setEncoderResolution(vOutWidth, vOutHeight);
    }

    public void setVideoHDMode() {
        vBitrate = 1228800;
        x264Preset = "veryfast";
    }

    public void setVideoSmoothMode() {
        vBitrate = 512000;
        x264Preset = "superfast";
    }

    public boolean start() {
        if (this.flvMuxer != null && this.mp4Muxer != null) {
            this.mPresentTimeUs = System.nanoTime() / 1000;
            if (!this.useSoftEncoder && (vOutWidth % 32 != 0 || vOutHeight % 32 != 0)) {
                this.vmci.getName().contains("MTK");
            }
            setEncoderResolution(vOutWidth, vOutHeight);
            setEncoderFps(24);
            setEncoderGop(48);
            setEncoderBitrate(vBitrate);
            setEncoderPreset(x264Preset);
            if (this.useSoftEncoder) {
                boolean zOpenSoftEncoder = openSoftEncoder();
                this.canSoftEncode = zOpenSoftEncoder;
                if (!zOpenSoftEncoder) {
                    return false;
                }
            }
            try {
                MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
                this.aencoder = mediaCodecCreateEncoderByType;
                if (mediaCodecCreateEncoderByType == null) {
                    return false;
                }
                MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, aChannelConfig == 12 ? 2 : 1);
                mediaFormatCreateAudioFormat.setInteger("bitrate", 65536);
                mediaFormatCreateAudioFormat.setInteger("max-input-size", 0);
                this.aencoder.configure(mediaFormatCreateAudioFormat, (Surface) null, (MediaCrypto) null, 1);
                this.audioFlvTrack = this.flvMuxer.addTrack(mediaFormatCreateAudioFormat);
                this.audioMp4Track = this.mp4Muxer.addTrack(mediaFormatCreateAudioFormat);
                try {
                    this.vencoder = MediaCodec.createByCodecName(this.vmci.getName());
                    MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", vOutWidth, vOutHeight);
                    mediaFormatCreateVideoFormat.setInteger("color-format", this.mVideoColorFormat);
                    mediaFormatCreateVideoFormat.setInteger("max-input-size", 0);
                    mediaFormatCreateVideoFormat.setInteger("bitrate", vBitrate);
                    mediaFormatCreateVideoFormat.setInteger("frame-rate", 24);
                    mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 2);
                    this.vencoder.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                    this.videoFlvTrack = this.flvMuxer.addTrack(mediaFormatCreateVideoFormat);
                    this.videoMp4Track = this.mp4Muxer.addTrack(mediaFormatCreateVideoFormat);
                    this.vencoder.start();
                    this.aencoder.start();
                    return true;
                } catch (IOException e2) {
                    Log.e(TAG, "create vencoder failed.");
                    e2.printStackTrace();
                    return false;
                }
            } catch (IOException e3) {
                Log.e(TAG, "create aencoder failed.");
                e3.printStackTrace();
            }
        }
        return false;
    }

    public void stop() {
        if (this.useSoftEncoder) {
            closeSoftEncoder();
            this.canSoftEncode = false;
        }
        if (this.aencoder != null) {
            Log.i(TAG, "stop aencoder");
            try {
                this.aencoder.stop();
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
            this.aencoder.release();
            this.aencoder = null;
        }
        if (this.vencoder != null) {
            Log.i(TAG, "stop vencoder");
            try {
                this.vencoder.stop();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            }
            this.vencoder.release();
            this.vencoder = null;
        }
    }

    public void switchToHardEncoder() {
        this.useSoftEncoder = false;
    }

    public void switchToSoftEncoder() {
        this.useSoftEncoder = true;
    }

    private int chooseVideoEncoder() {
        MediaCodecInfo mediaCodecInfoChooseVideoEncoder = chooseVideoEncoder(null);
        this.vmci = mediaCodecInfoChooseVideoEncoder;
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfoChooseVideoEncoder.getCapabilitiesForType("video/avc");
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = capabilitiesForType.colorFormats;
            if (i >= iArr.length) {
                break;
            }
            int i3 = iArr[i];
            Log.i(TAG, String.format("vencoder %s supports color fomart 0x%x(%d)", this.vmci.getName(), Integer.valueOf(i3), Integer.valueOf(i3)));
            if (i3 >= 19 && i3 <= 21 && i3 > i2) {
                i2 = i3;
            }
            i++;
        }
        int i4 = 0;
        while (true) {
            MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = capabilitiesForType.profileLevels;
            if (i4 >= codecProfileLevelArr.length) {
                Log.i(TAG, String.format("vencoder %s choose color format 0x%x(%d)", this.vmci.getName(), Integer.valueOf(i2), Integer.valueOf(i2)));
                return i2;
            }
            MediaCodecInfo.CodecProfileLevel codecProfileLevel = codecProfileLevelArr[i4];
            Log.i(TAG, String.format("vencoder %s support profile %d, level %d", this.vmci.getName(), Integer.valueOf(codecProfileLevel.profile), Integer.valueOf(codecProfileLevel.level)));
            i4++;
        }
    }

    public void onGetArgbFrame(int[] iArr, int i, int i2) {
        AtomicInteger videoFrameCacheNumber = this.flvMuxer.getVideoFrameCacheNumber();
        if (videoFrameCacheNumber != null && videoFrameCacheNumber.get() < 24) {
            long jNanoTime = (System.nanoTime() / 1000) - this.mPresentTimeUs;
            if (!this.useSoftEncoder) {
                byte[] bArrHwArgbFrame = hwArgbFrame(iArr, i, i2);
                if (bArrHwArgbFrame != null) {
                    onProcessedYuvFrame(bArrHwArgbFrame, jNanoTime);
                } else {
                    this.mHandler.notifyEncodeIllegalArgumentException(new IllegalArgumentException("libyuv failure"));
                }
                if (this.networkWeakTriggered) {
                    this.networkWeakTriggered = false;
                    this.mHandler.notifyNetworkResume();
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("Not implemented");
        }
        this.mHandler.notifyNetworkWeak();
        this.networkWeakTriggered = true;
    }
}
