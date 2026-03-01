package net.ossrs.yasea;

import android.hardware.Camera;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.os.Process;
import com.seu.magicfilter.utils.MagicFilterType;
import java.io.File;
import net.ossrs.yasea.SrsCameraView;
import supwisdom.i00;

/* JADX INFO: loaded from: classes3.dex */
public class SrsPublisher {
    public static AcousticEchoCanceler aec;
    public static AutomaticGainControl agc;
    public static AudioRecord mic;
    public Thread aworker;
    public long lastTimeMillis;
    public SrsCameraView mCameraView;
    public SrsEncoder mEncoder;
    public SrsFlvMuxer mFlvMuxer;
    public SrsMp4Muxer mMp4Muxer;
    public double mSamplingFps;
    public int videoFrameCount;
    public byte[] mPcmBuffer = new byte[4096];
    public boolean sendVideoOnly = false;
    public boolean sendAudioOnly = false;

    public SrsPublisher(SrsCameraView srsCameraView) {
        this.mCameraView = srsCameraView;
        srsCameraView.setPreviewCallback(new SrsCameraView.PreviewCallback() { // from class: net.ossrs.yasea.SrsPublisher.1
            @Override // net.ossrs.yasea.SrsCameraView.PreviewCallback
            public void onGetRgbaFrame(byte[] bArr, int i, int i2) {
                SrsPublisher.this.calcSamplingFps();
                if (SrsPublisher.this.sendAudioOnly) {
                    return;
                }
                SrsPublisher.this.mEncoder.onGetRgbaFrame(bArr, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calcSamplingFps() {
        int i = this.videoFrameCount;
        if (i == 0) {
            this.lastTimeMillis = System.nanoTime() / 1000000;
            this.videoFrameCount++;
            return;
        }
        int i2 = i + 1;
        this.videoFrameCount = i2;
        if (i2 >= 48) {
            this.mSamplingFps = (((double) this.videoFrameCount) * 1000.0d) / ((System.nanoTime() / 1000000) - this.lastTimeMillis);
            this.videoFrameCount = 0;
        }
    }

    private void resumeEncode() {
        startAudio();
        this.mCameraView.enableEncoding();
    }

    public Camera getCamera() {
        return this.mCameraView.getCamera();
    }

    public int getCameraId() {
        return this.mCameraView.getCameraId();
    }

    public int getPreviewHeight() {
        return this.mEncoder.getPreviewHeight();
    }

    public int getPreviewWidth() {
        return this.mEncoder.getPreviewWidth();
    }

    public int getVideoFrameCacheCount() {
        SrsFlvMuxer srsFlvMuxer = this.mFlvMuxer;
        if (srsFlvMuxer != null) {
            return srsFlvMuxer.getVideoFrameCacheNumber().get();
        }
        return 0;
    }

    public double getmSamplingFps() {
        return this.mSamplingFps;
    }

    public boolean isAllFramesUploaded() {
        return this.mFlvMuxer.getVideoFrameCacheNumber().get() == 0;
    }

    public boolean isSoftEncoder() {
        return this.mEncoder.isSoftEncoder();
    }

    public void pauseEncode() {
        stopAudio();
        this.mCameraView.disableEncoding();
        this.mCameraView.stopTorch();
    }

    public void pausePublish() {
        if (this.mFlvMuxer != null) {
            this.mEncoder.pause();
            pauseEncode();
        }
    }

    public void pauseRecord() {
        SrsMp4Muxer srsMp4Muxer = this.mMp4Muxer;
        if (srsMp4Muxer != null) {
            srsMp4Muxer.pause();
        }
    }

    public void resumePublish() {
        if (this.mFlvMuxer != null) {
            this.mEncoder.resume();
            resumeEncode();
        }
    }

    public void resumeRecord() {
        SrsMp4Muxer srsMp4Muxer = this.mMp4Muxer;
        if (srsMp4Muxer != null) {
            srsMp4Muxer.resume();
        }
    }

    public void setEncodeHandler(SrsEncodeHandler srsEncodeHandler) {
        SrsEncoder srsEncoder = new SrsEncoder(srsEncodeHandler);
        this.mEncoder = srsEncoder;
        SrsFlvMuxer srsFlvMuxer = this.mFlvMuxer;
        if (srsFlvMuxer != null) {
            srsEncoder.setFlvMuxer(srsFlvMuxer);
        }
        SrsMp4Muxer srsMp4Muxer = this.mMp4Muxer;
        if (srsMp4Muxer != null) {
            this.mEncoder.setMp4Muxer(srsMp4Muxer);
        }
    }

    public void setOutputResolution(int i, int i2) {
        if (i <= i2) {
            this.mEncoder.setPortraitResolution(i, i2);
        } else {
            this.mEncoder.setLandscapeResolution(i, i2);
        }
    }

    public void setPreviewResolution(int i, int i2) {
        int[] previewResolution = this.mCameraView.setPreviewResolution(i, i2);
        this.mEncoder.setPreviewResolution(previewResolution[0], previewResolution[1]);
    }

    public void setRecordHandler(SrsRecordHandler srsRecordHandler) {
        SrsMp4Muxer srsMp4Muxer = new SrsMp4Muxer(srsRecordHandler);
        this.mMp4Muxer = srsMp4Muxer;
        SrsEncoder srsEncoder = this.mEncoder;
        if (srsEncoder != null) {
            srsEncoder.setMp4Muxer(srsMp4Muxer);
        }
    }

    public void setRtmpHandler(i00 i00Var) {
        SrsFlvMuxer srsFlvMuxer = new SrsFlvMuxer(i00Var);
        this.mFlvMuxer = srsFlvMuxer;
        SrsEncoder srsEncoder = this.mEncoder;
        if (srsEncoder != null) {
            srsEncoder.setFlvMuxer(srsFlvMuxer);
        }
    }

    public void setScreenOrientation(int i) {
        this.mCameraView.setPreviewOrientation(i);
        this.mEncoder.setScreenOrientation(i);
    }

    public void setSendAudioOnly(boolean z) {
        this.sendAudioOnly = z;
    }

    public void setSendVideoOnly(boolean z) {
        AudioRecord audioRecord = mic;
        if (audioRecord != null) {
            if (z) {
                audioRecord.stop();
                this.mPcmBuffer = new byte[4096];
            } else {
                audioRecord.startRecording();
            }
        }
        this.sendVideoOnly = z;
    }

    public void setVideoHDMode() {
        this.mEncoder.setVideoHDMode();
    }

    public void setVideoSmoothMode() {
        this.mEncoder.setVideoSmoothMode();
    }

    public void startAudio() {
        AudioRecord audioRecordChooseAudioRecord = this.mEncoder.chooseAudioRecord();
        mic = audioRecordChooseAudioRecord;
        if (audioRecordChooseAudioRecord == null) {
            return;
        }
        if (AcousticEchoCanceler.isAvailable()) {
            AcousticEchoCanceler acousticEchoCancelerCreate = AcousticEchoCanceler.create(mic.getAudioSessionId());
            aec = acousticEchoCancelerCreate;
            if (acousticEchoCancelerCreate != null) {
                acousticEchoCancelerCreate.setEnabled(true);
            }
        }
        if (AutomaticGainControl.isAvailable()) {
            AutomaticGainControl automaticGainControlCreate = AutomaticGainControl.create(mic.getAudioSessionId());
            agc = automaticGainControlCreate;
            if (automaticGainControlCreate != null) {
                automaticGainControlCreate.setEnabled(true);
            }
        }
        Thread thread = new Thread(new Runnable() { // from class: net.ossrs.yasea.SrsPublisher.2
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(-16);
                SrsPublisher.mic.startRecording();
                while (!Thread.interrupted()) {
                    if (SrsPublisher.this.sendVideoOnly) {
                        SrsPublisher.this.mEncoder.onGetPcmFrame(SrsPublisher.this.mPcmBuffer, SrsPublisher.this.mPcmBuffer.length);
                        try {
                            Thread.sleep(20L);
                        } catch (InterruptedException unused) {
                            return;
                        }
                    } else {
                        int i = SrsPublisher.mic.read(SrsPublisher.this.mPcmBuffer, 0, SrsPublisher.this.mPcmBuffer.length);
                        if (i > 0) {
                            SrsPublisher.this.mEncoder.onGetPcmFrame(SrsPublisher.this.mPcmBuffer, i);
                        }
                    }
                }
            }
        });
        this.aworker = thread;
        thread.start();
    }

    public void startCamera() {
        this.mCameraView.startCamera();
    }

    public void startEncode() {
        if (this.mEncoder.start()) {
            this.mCameraView.enableEncoding();
            startAudio();
        }
    }

    public void startPublish(String str) {
        SrsFlvMuxer srsFlvMuxer = this.mFlvMuxer;
        if (srsFlvMuxer != null) {
            srsFlvMuxer.start(str);
            this.mFlvMuxer.setVideoResolution(this.mEncoder.getOutputWidth(), this.mEncoder.getOutputHeight());
            startEncode();
        }
    }

    public boolean startRecord(String str) {
        SrsMp4Muxer srsMp4Muxer = this.mMp4Muxer;
        return srsMp4Muxer != null && srsMp4Muxer.record(new File(str));
    }

    public void stopAudio() {
        Thread thread = this.aworker;
        if (thread != null) {
            thread.interrupt();
            try {
                this.aworker.join();
            } catch (InterruptedException unused) {
                this.aworker.interrupt();
            }
            this.aworker = null;
        }
        AudioRecord audioRecord = mic;
        if (audioRecord != null) {
            audioRecord.setRecordPositionUpdateListener(null);
            mic.stop();
            mic.release();
            mic = null;
        }
        AcousticEchoCanceler acousticEchoCanceler = aec;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.setEnabled(false);
            aec.release();
            aec = null;
        }
        AutomaticGainControl automaticGainControl = agc;
        if (automaticGainControl != null) {
            automaticGainControl.setEnabled(false);
            agc.release();
            agc = null;
        }
    }

    public void stopCamera() {
        this.mCameraView.stopCamera();
    }

    public void stopEncode() {
        stopAudio();
        stopCamera();
        this.mEncoder.stop();
    }

    public void stopPublish() {
        if (this.mFlvMuxer != null) {
            stopEncode();
            this.mFlvMuxer.stop();
        }
    }

    public void stopRecord() {
        SrsMp4Muxer srsMp4Muxer = this.mMp4Muxer;
        if (srsMp4Muxer != null) {
            srsMp4Muxer.stop();
        }
    }

    public void switchCameraFace(int i) {
        this.mCameraView.stopCamera();
        this.mCameraView.setCameraId(i);
        if (i == 0) {
            this.mEncoder.setCameraBackFace();
        } else {
            this.mEncoder.setCameraFrontFace();
        }
        SrsEncoder srsEncoder = this.mEncoder;
        if (srsEncoder != null && srsEncoder.isEnabled()) {
            this.mCameraView.enableEncoding();
        }
        this.mCameraView.startCamera();
    }

    public boolean switchCameraFilter(MagicFilterType magicFilterType, int i) {
        return this.mCameraView.setFilter(magicFilterType, i);
    }

    public void switchToHardEncoder() {
        this.mEncoder.switchToHardEncoder();
    }

    public void switchToSoftEncoder() {
        this.mEncoder.switchToSoftEncoder();
    }

    public boolean switchCameraFilter(MagicFilterType magicFilterType) {
        return this.mCameraView.setFilter(magicFilterType, 0);
    }
}
