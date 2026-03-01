package io.dcloud.feature.audio.recorder;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.feature.audio.aac.AacEncode;
import io.dcloud.feature.audio.mp3.SimpleLame;
import io.dcloud.feature.audio.recorder.HighGradeRecorder;
import java.io.File;
import java.io.FileNotFoundException;
import net.ossrs.yasea.SrsFlvMuxer;

/* JADX INFO: loaded from: classes2.dex */
public class RecorderTask extends Thread {
    public static final int DEFAULT_LAME_MP3_BIT_RATE = 128;
    public static final int DEFAULT_LAME_MP3_QUALITY = 7;
    public static final int FRAME_COUNT = 220;
    public static final String TAG = "RecorderTask";
    public byte[] mAacBuffer;
    public double mDuration;
    public HighGradeRecorder.Callback mDurationListener;
    public DataEncodeThread mEncodeThread;
    public String mFormat;
    public short[] mPCMBuffer;
    public HighGradeRecorder mRecorder;
    public int maxDuration;
    public File outputFile;
    public boolean reallyStart;
    public int[] sampleRates;
    public int waitingTime;
    public final int[] configs = {16, 12};
    public final int[] formats = {2, 3};
    public AudioRecord audioRecord = null;
    public int bufsize = -2;
    public boolean mShouldRun = false;
    public boolean mShouldRecord = false;
    public long startTime = 0;
    public long duration = 0;
    public Handler handler = new Handler();

    public RecorderTask(File file, HighGradeRecorder highGradeRecorder, RecordOption recordOption) {
        this.sampleRates = new int[]{44100, SrsFlvMuxer.SrsCodecAudioSampleRate.R22050, SrsFlvMuxer.SrsCodecAudioSampleRate.R11025, 8000};
        this.outputFile = file;
        this.mRecorder = highGradeRecorder;
        this.mFormat = recordOption.mFormat;
        if (!recordOption.isRateDeft) {
            this.sampleRates = new int[]{recordOption.mSamplingRate, 44100, SrsFlvMuxer.SrsCodecAudioSampleRate.R22050, SrsFlvMuxer.SrsCodecAudioSampleRate.R11025, 8000};
        }
        if (highGradeRecorder.getRecorderState() == 1) {
            this.waitingTime = 1000;
        } else {
            this.waitingTime = 10000;
        }
    }

    private double calVolume(short[] sArr, double d) {
        long j = 0;
        for (int i = 0; i < sArr.length; i++) {
            j += (long) (sArr[i] * sArr[i]);
        }
        return Math.log10(j / d) * 10.0d;
    }

    private void cancel() {
        stopRecord();
    }

    private void createRecord(int i, int i2, int i3) {
        this.audioRecord = new AudioRecord(1, i, i2, i3, this.bufsize);
    }

    private void init() {
        int i = this.audioRecord.getAudioFormat() != 2 ? 1 : 2;
        int i2 = this.bufsize / i;
        int i3 = i2 % FRAME_COUNT;
        if (i3 != 0) {
            this.bufsize = (i2 + (220 - i3)) * i;
        }
        int i4 = this.bufsize;
        this.mPCMBuffer = new short[i4];
        this.mAacBuffer = new byte[i4];
        if (this.mFormat.equalsIgnoreCase("aac")) {
            AacEncode.getAacEncode(this.audioRecord.getSampleRate(), this.audioRecord.getChannelCount());
        } else {
            SimpleLame.init(this.audioRecord.getSampleRate(), this.audioRecord.getChannelCount(), this.audioRecord.getSampleRate(), 128, 7);
        }
        try {
            if (!this.outputFile.exists()) {
                File parentFile = this.outputFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.outputFile.createNewFile();
            }
            DataEncodeThread dataEncodeThread = new DataEncodeThread(this.outputFile, this.bufsize, this.mFormat);
            this.mEncodeThread = dataEncodeThread;
            dataEncodeThread.start();
            AudioRecord audioRecord = this.audioRecord;
            DataEncodeThread dataEncodeThread2 = this.mEncodeThread;
            audioRecord.setRecordPositionUpdateListener(dataEncodeThread2, dataEncodeThread2.getHandler());
            this.audioRecord.setPositionNotificationPeriod(FRAME_COUNT);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private boolean isFound() {
        boolean z = false;
        int i = 0;
        while (!z) {
            int[] iArr = this.formats;
            if (i >= iArr.length) {
                break;
            }
            int i2 = iArr[i];
            int i3 = 0;
            while (!z) {
                int[] iArr2 = this.sampleRates;
                if (i3 < iArr2.length) {
                    int i4 = iArr2[i3];
                    int i5 = 0;
                    while (true) {
                        if (!z) {
                            int[] iArr3 = this.configs;
                            if (i5 < iArr3.length) {
                                int i6 = iArr3[i5];
                                Logger.e(TAG, "Trying to create AudioRecord use: " + i2 + "/" + i6 + "/" + i4);
                                this.bufsize = AudioRecord.getMinBufferSize(i4, i6, i2);
                                StringBuilder sb = new StringBuilder();
                                sb.append("Bufsize: ");
                                sb.append(this.bufsize);
                                Logger.e(TAG, sb.toString());
                                int i7 = this.bufsize;
                                if (-2 == i7) {
                                    Logger.i(TAG, "invaild params!");
                                } else if (-1 == i7) {
                                    Logger.i(TAG, "Unable to query hardware!");
                                } else {
                                    try {
                                        createRecord(i4, i6, i2);
                                        if (this.audioRecord.getState() == 1) {
                                            z = true;
                                            break;
                                        }
                                    } catch (IllegalStateException unused) {
                                        Logger.i(TAG, "Failed to set up recorder!");
                                        this.audioRecord = null;
                                    }
                                }
                                i5++;
                            }
                        }
                    }
                    i3++;
                }
            }
            i++;
        }
        return z;
    }

    private int mapFormat(int i) {
        if (i != 2) {
            return i != 3 ? 0 : 8;
        }
        return 16;
    }

    public int getDuration() {
        return (int) this.mDuration;
    }

    public boolean isRecording() {
        return this.mShouldRecord;
    }

    public void pauseRecord() {
        this.mShouldRecord = false;
    }

    public void resumeRecord() {
        this.mShouldRecord = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        if (!isFound()) {
            Logger.e(TAG, "Sample rate, channel config or format not supported!");
            return;
        }
        init();
        this.mShouldRun = true;
        int sampleRate = ((this.audioRecord.getSampleRate() * mapFormat(this.audioRecord.getAudioFormat())) / 8) * this.audioRecord.getChannelCount();
        this.mDuration = 0.0d;
        boolean z = false;
        while (this.mShouldRun) {
            boolean z2 = this.mShouldRecord;
            if (z2 != z) {
                if (z2) {
                    this.startTime = System.currentTimeMillis();
                    try {
                        this.audioRecord.startRecording();
                        if (this.mDuration == 0.0d) {
                            this.reallyStart = true;
                            RecorderUtil.postTaskSafely(new Runnable() { // from class: io.dcloud.feature.audio.recorder.RecorderTask.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    RecorderTask.this.mRecorder.onstart();
                                }
                            });
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    this.audioRecord.stop();
                }
                z = this.mShouldRecord;
            }
            if (this.mShouldRecord) {
                if (this.mFormat.equalsIgnoreCase("aac")) {
                    int i = this.audioRecord.read(this.mAacBuffer, 0, this.bufsize);
                    if (i > 0) {
                        this.mEncodeThread.addTask(this.mAacBuffer, i);
                    }
                } else {
                    int i2 = this.audioRecord.read(this.mPCMBuffer, 0, this.bufsize);
                    if (i2 > 0) {
                        double d = i2;
                        double d2 = ((1000.0d * d) * 2.0d) / ((double) sampleRate);
                        final double dCalVolume = calVolume(this.mPCMBuffer, d);
                        this.mDuration += d2;
                        if (this.mDurationListener != null) {
                            RecorderUtil.postTaskSafely(new Runnable() { // from class: io.dcloud.feature.audio.recorder.RecorderTask.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    RecorderTask.this.mDurationListener.onRecording(RecorderTask.this.mDuration, dCalVolume);
                                    if (RecorderTask.this.maxDuration <= 0 || RecorderTask.this.mDuration < RecorderTask.this.maxDuration) {
                                        return;
                                    }
                                    RecorderTask.this.mRecorder.stop(2);
                                    RecorderTask.this.mDurationListener.onMaxDurationReached();
                                }
                            });
                        }
                        AudioRecord audioRecord = this.audioRecord;
                        if (audioRecord == null || audioRecord.getChannelCount() != 1) {
                            AudioRecord audioRecord2 = this.audioRecord;
                            if (audioRecord2 != null && audioRecord2.getChannelCount() == 2) {
                                int i3 = i2 / 2;
                                short[] sArr = new short[i3];
                                short[] sArr2 = new short[i3];
                                for (int i4 = 0; i4 < i3; i4 += 2) {
                                    short[] sArr3 = this.mPCMBuffer;
                                    int i5 = i4 * 2;
                                    sArr[i4] = sArr3[i5];
                                    int i6 = i5 + 1;
                                    if (i6 < i2) {
                                        sArr[i4 + 1] = sArr3[i6];
                                    }
                                    int i7 = i5 + 2;
                                    if (i7 < i2) {
                                        sArr2[i4] = sArr3[i7];
                                    }
                                    int i8 = i5 + 3;
                                    if (i8 < i2) {
                                        sArr2[i4 + 1] = sArr3[i8];
                                    }
                                }
                                this.mEncodeThread.addTask(sArr, sArr2, i3);
                            }
                        } else {
                            this.mEncodeThread.addTask(this.mPCMBuffer, i2);
                        }
                    }
                }
            }
        }
    }

    public void setCallback(HighGradeRecorder.Callback callback) {
        this.mDurationListener = callback;
    }

    public void setMaxDuration(int i) {
        this.maxDuration = i;
    }

    public void startRecording() {
        this.mShouldRecord = true;
    }

    public void stopRecord() {
        this.mShouldRecord = false;
        this.mShouldRun = false;
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.stop();
            this.audioRecord.release();
            this.audioRecord = null;
        }
        Message.obtain(this.mEncodeThread.getHandler(), 1).sendToTarget();
    }
}
