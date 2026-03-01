package com.baidu.speech.asr;

/* JADX INFO: loaded from: classes.dex */
public class PrivateConstant {
    public static String APIKEY = "apikey";
    public static int ASR_AUDIO_HANDLER_ERROR = 20;
    public static int ASR_HTTP_ERROR = 32;
    public static int ASR_LOCAL_NETWORK_ERROR = 31;
    public static int ASR_OFFLINE_ENGINE_ERROR = 34;
    public static int ASR_ONLINE_ENGINE_ERROR = 30;
    public static int ASR_OTHER_ERROR = 40;
    public static int ASR_RECORDER_ERROR = 10;
    public static int ASR_SERVER_ERROR = 33;
    public static String AUTH = "decoder-server.auth";
    public static String BUA = "bua";
    public static String COK = "cok";
    public static String ENABLE_EARLY_RETURN = "enable-early-return";
    public static String ENABLE_LONG_SPEECH = "enable.long-speech";
    public static String FRM = "frm";
    public static String KEY_WORD = "keyword";
    public static String LM_RES_FILE_PATH = "lm-res-file-path";
    public static String OFFLINE_ENGINE = "kws_param_key_type.int";
    public static String OFF_GRAMM_LM = "off_gramm_lm";
    public static String PFM = "decoder-server.pfm";
    public static String PTC = "decoder-server.ptc";
    public static String PU = "pu";
    public static String RSV = "rsv";
    public static String SERVER_VAD = "server-vad";
    public static String UID = "decoder-server.uid";
    public static String UPLOAD_CONTRACT_DATA = "upload-contract-data";
    public static String UPLOAD_SLOT_NAME = "upload-slot-name";
    public static String UPLOAD_WORDS = "upload-words";
    public static String VER = "decoder-server.ver";

    public enum VoiceRecognitionClientWorkStatus {
        EVoiceRecognitionClientWorkStatusStartWorkIng,
        EVoiceRecognitionClientWorkStatusStart,
        EVoiceRecognitionClientWorkStatusEnd,
        EVoiceRecognitionClientWorkStatusNewRecordData,
        EVoiceRecognitionClientWorkStatusFlushData,
        EVoiceRecognitionClientWorkStatusFinish,
        EVoiceRecognitionClientWorkStatusMeterLevel,
        EVoiceRecognitionClientWorkStatusCancel,
        EVoiceRecognitionClientWorkStatusError,
        EVoiceRecognitionClientWorkStatusLoaded,
        EVoiceRecognitionClientWorkStatusUnLoaded
    }
}
