package io.dcloud.feature.audio.recorder;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class HighGradeRecorder extends AbsRecorder {
    public static final int ACTION_RESET = 1;
    public static final int ACTION_STOP = 2;
    public int mMaxDuration;
    public RecordOption mOption;
    public Callback mStateListener;
    public String outputFilePath;
    public int stateBeforeFocusChange;
    public RecorderTask audioRecorder = null;
    public int state = -1;

    public interface Callback {
        void onMaxDurationReached();

        void onPause();

        void onRecording(double d, double d2);

        void onReset();

        void onResume();

        void onStart();

        void onStop(int i);
    }

    public class State {
        public static final int INITIALIZED = 0;
        public static final int PAUSED = 3;
        public static final int PREPARED = 1;
        public static final int RECORDING = 2;
        public static final int STOPPED = 4;
        public static final int UNINITIALIZED = -1;

        public State() {
        }
    }

    public int getRecorderState() {
        return this.state;
    }

    public int getmMaxDuration() {
        return this.mMaxDuration;
    }

    public void onstart() {
        if (this.state == 1) {
            this.state = 2;
            Callback callback = this.mStateListener;
            if (callback != null) {
                callback.onStart();
            }
        }
    }

    @Override // io.dcloud.feature.audio.recorder.AbsRecorder
    public void pause() {
        RecorderTask recorderTask = this.audioRecorder;
        if (recorderTask == null || this.state != 2) {
            return;
        }
        recorderTask.pauseRecord();
        this.state = 3;
        Callback callback = this.mStateListener;
        if (callback != null) {
            callback.onPause();
        }
    }

    @Override // io.dcloud.feature.audio.recorder.AbsRecorder
    public void release() {
    }

    public void reset() {
        RecorderTask recorderTask = this.audioRecorder;
        if (recorderTask == null) {
            return;
        }
        if (recorderTask != null && this.state != 4) {
            stop(1);
        }
        this.audioRecorder = null;
        Callback callback = this.mStateListener;
        if (callback != null) {
            callback.onReset();
        }
    }

    @Override // io.dcloud.feature.audio.recorder.AbsRecorder
    public void resume() {
        RecorderTask recorderTask = this.audioRecorder;
        if (recorderTask == null || this.state != 3) {
            return;
        }
        recorderTask.resumeRecord();
        this.state = 2;
        Callback callback = this.mStateListener;
        if (callback != null) {
            callback.onResume();
        }
    }

    public HighGradeRecorder setCallback(Callback callback) {
        this.mStateListener = callback;
        return this;
    }

    public HighGradeRecorder setMaxDuration(int i) {
        this.mMaxDuration = i * 1000;
        return this;
    }

    public HighGradeRecorder setOutputFile(String str) {
        this.outputFilePath = str;
        return this;
    }

    public HighGradeRecorder setRecordOption(RecordOption recordOption) {
        this.outputFilePath = recordOption.mFileName;
        this.mOption = recordOption;
        return this;
    }

    @Override // io.dcloud.feature.audio.recorder.AbsRecorder
    public void start() {
        int i = this.state;
        if (i != 0 && i != 4 && i != 1 && i != -1) {
            if (i == 3) {
                resume();
                return;
            }
            return;
        }
        RecorderTask recorderTask = new RecorderTask(new File(this.outputFilePath), this, this.mOption);
        this.audioRecorder = recorderTask;
        recorderTask.setCallback(this.mStateListener);
        this.audioRecorder.setMaxDuration(this.mMaxDuration);
        this.audioRecorder.start();
        this.state = 1;
        this.audioRecorder.startRecording();
    }

    @Override // io.dcloud.feature.audio.recorder.AbsRecorder
    public void stop() {
        stop(2);
    }

    public void stop(int i) {
        RecorderTask recorderTask = this.audioRecorder;
        if (recorderTask == null || this.state != 2) {
            return;
        }
        recorderTask.stopRecord();
        this.state = 4;
        Callback callback = this.mStateListener;
        if (callback != null) {
            callback.onStop(i);
        }
    }
}
