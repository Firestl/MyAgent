package com.baidu.speech.asr;

/* JADX INFO: loaded from: classes.dex */
public class ErrorCode {
    public static final int RECOGNIZER_OK = 0;
    public static final int RECORDING_EXCEPTION = 1001;
    public static final int RECORDING_FILE_OPEN_FAIL = 1005;
    public static final int RECORDING_INTERRUPT = 1003;
    public static final int RECORDING_NO_PERMISSION = 1002;
    public static final int RECORDING_OPEN_FAIL = 1004;

    public String getDesc(int i) {
        return "";
    }

    public String getMessage(int i) {
        String desc = getDesc(i);
        if (desc != null) {
            return desc;
        }
        return "错误:" + i;
    }
}
