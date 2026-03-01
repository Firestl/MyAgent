package com.baidu.speech.core;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.speech.asr.ASRListener;
import com.baidu.speech.asr.EventContext;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.speech.core.BDSParamBase;
import com.baidu.speech.core.BDSSDKLoader;
import com.baidu.speech.utils.AsrError;
import com.baidu.speech.utils.LogUtil;
import com.baidu.speech.utils.Utility;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.igexin.push.core.b;
import com.lzy.okgo.cookie.SerializableCookie;
import com.taobao.weex.el.parse.Operators;
import com.tencent.open.SocialConstants;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ASREngine implements BDSSDKLoader.BDSCoreEventListener {
    public static String ASR_CMD_CANCEL = "asr.cancel";
    public static String ASR_CMD_CONFIG = "asr.config";
    public static String ASR_CMD_LOAD_ENGINE = "asr.kws.load";
    public static String ASR_CMD_START = "asr.start";
    public static String ASR_CMD_STOP = "asr.stop";
    public static String ASR_CMD_UNLOAD_ENGINE = "asr.kws.unload";
    public static String ASR_CMD_UPLOAD_CANCEL = "asr.upload.cancel";
    public static String ASR_CMD_UPLOAD_CONTRACT = "asr.upload.contract";
    public static String ASR_CMD_UPLOAD_WORDS = "asr.upload.words";
    public static String ASR_PARAM_KEY_ACCEPT_AUDIO_DATA = "asr_param_key_accept_audio_data.bool";
    public static String ASR_PARAM_KEY_API_SECRET_KEYS = "asr_param_key_api_secret_key.vector<string>";
    public static String ASR_PARAM_KEY_APP = "asr_param_key_app.string";
    public static String ASR_PARAM_KEY_AUDIO_FILE_PATH = "mic_audio_file_path.string";
    public static String ASR_PARAM_KEY_AUDIO_mills = "mic_audio_mills.string";
    public static String ASR_PARAM_KEY_BROWSER_USER_AGENT = "asr_param_key_browser_user_agent.string";
    public static String ASR_PARAM_KEY_BUA = "asr_param_key_bua.string";
    public static String ASR_PARAM_KEY_CHUNK_ENABLE = "asr_param_key_chunk_enable.bool";
    public static String ASR_PARAM_KEY_CHUNK_KEY = "asr_param_key_chunk_key.string";
    public static String ASR_PARAM_KEY_CHUNK_PARAM = "asr_param_key_chunk_param.string";
    public static String ASR_PARAM_KEY_CHUNK_TTS = "asr_param_key_chunk_tts.string";
    public static String ASR_PARAM_KEY_CITY_ID = "asr_param_key_city_id.int";
    public static String ASR_PARAM_KEY_COK = "asr_param_key_cok.string";
    public static String ASR_PARAM_KEY_COMPRESSION_TYPE = "asr_param_key_compression_type.int";
    public static String ASR_PARAM_KEY_DEV = "asr_param_key_dev.string";
    public static String ASR_PARAM_KEY_DISABLE_PUNCTUATION = "asr_param_key_disable_punctuation.bool";
    public static String ASR_PARAM_KEY_DNN_HEAD_SIL_DURATION = "asr_param_key_dnn_head_sil_duration.int";
    public static String ASR_PARAM_KEY_DNN_MIN_SP_DURATION = "asr_param_key_dnn_min_sp_duration.int";
    public static String ASR_PARAM_KEY_DNN_SIL_THRESHOLD = "asr_param_key_dnn_sil_threshold.float";
    public static String ASR_PARAM_KEY_DNN_SPEECH_THRESHOLD = "asr_param_key_dnn_speech_threshold.float";
    public static String ASR_PARAM_KEY_ENABLE_CONTACTS = "asr_param_key_enable_contacts.bool";
    public static String ASR_PARAM_KEY_ENABLE_DRC = "asr_param_key_enable_drc.bool";
    public static String ASR_PARAM_KEY_ENABLE_EARLY_RETURN = "asr_param_key_enable_early_return.bool";
    public static String ASR_PARAM_KEY_ENABLE_HTTPDNS = "asr_param_key_enable_httpdns.bool";
    public static String ASR_PARAM_KEY_ENABLE_LOCAL_VAD = "asr_param_key_enable_local_vad.bool";
    public static String ASR_PARAM_KEY_ENABLE_LONG_SPEECH = "asr_param_key_enable_long_speech.bool";
    public static String ASR_PARAM_KEY_ENABLE_MODEL_VAD = "asr_param_key_enable_model_vad.int";
    public static String ASR_PARAM_KEY_ENABLE_NLU = "asr_param_key_enable_nlu.bool";
    public static String ASR_PARAM_KEY_ENABLE_NUMBERFORMAT = "asr_param_key_enable_numberformat.bool";
    public static String ASR_PARAM_KEY_ENABLE_SERVER_VAD = "asr_param_key_enable_server_vad.bool";
    public static String ASR_PARAM_KEY_FRM = "asr_param_key_frm.string";
    public static String ASR_PARAM_KEY_GLB = "asr_param_key_glb.string";
    public static String ASR_PARAM_KEY_KWS_PROTOCOL = "asr_param_key_kws_protocol.int";
    public static String ASR_PARAM_KEY_LANGUAGE = "asr_param_key_language.int";
    public static String ASR_PARAM_KEY_LM_ID = "asr_param_key_lm_id.int";
    public static String ASR_PARAM_KEY_LTP = "asr_param_key_ltp.string";
    public static String ASR_PARAM_KEY_MAX_WAIT_DURATION = "asr_param_key_max_wait_duration.int";
    public static String ASR_PARAM_KEY_MODEL_VAD_DAT_FILE = "asr_param_key_model_vad_dat_file.string";
    public static String ASR_PARAM_KEY_MULTI_START_AND_END = "asr_param_key_multi_start_and_end.bool";
    public static String ASR_PARAM_KEY_NETWORK_STATUS = "asr_param_key_network_status.int";
    public static String ASR_PARAM_KEY_OFFLINE_APP_CODE = "offline_param_key_app_code.string";
    public static String ASR_PARAM_KEY_OFFLINE_ENGINE_DAT_FILE_PATH = "kws_param_key_dat_filepath.string";
    public static String ASR_PARAM_KEY_OFFLINE_ENGINE_GRAMMER_FILE_PATH = "kws_param_key_grammer_filepath.string";
    public static String ASR_PARAM_KEY_OFFLINE_ENGINE_TYPE = "kws_param_key_type.int";
    public static String ASR_PARAM_KEY_PAM = "asr_param_key_pam.string";
    public static String ASR_PARAM_KEY_PLATFORM = "asr_param_key_platform.string";
    public static String ASR_PARAM_KEY_PRODUCT_ID = "asr_param_key_product_id.string";
    public static String ASR_PARAM_KEY_PROPERTY_LIST = "asr_param_key_property_list.vector<int>";
    public static String ASR_PARAM_KEY_PROTOCOL = "asr_param_key_protocol.int";
    public static String ASR_PARAM_KEY_PU = "asr_param_key_pu.string";
    public static String ASR_PARAM_KEY_PUNCTUATION_EXT_MODE = "asr_param_key_punctuation_ext_mode.int";
    public static String ASR_PARAM_KEY_REALTIME_DATA = "asr_param_key_realtime_data.string";
    public static String ASR_PARAM_KEY_RSV = "asr_param_key_rsv.map<string,string>";
    public static String ASR_PARAM_KEY_SAMPLE_RATE = "asr_param_key_sample_rate.int";
    public static String ASR_PARAM_KEY_SDK_VERSION = "asr_param_key_sdk_version.string";
    public static String ASR_PARAM_KEY_SERVER_AGENT_URL = "asr_param_key_server_agent_url.string";
    public static String ASR_PARAM_KEY_SERVER_URL = "asr_param_key_server_url.string";
    public static String ASR_PARAM_KEY_START_TONE = "asr_param_key_start_tone.int";
    public static String ASR_PARAM_KEY_STC = "asr_param_key_stc.string";
    public static String ASR_PARAM_KEY_STRATEGY = "asr_param_key_strategy.int";
    public static String ASR_PARAM_KEY_TXT = "asr_param_key_txt.string";
    public static String ASR_PARAM_KEY_UID_STRING = "uid.string";
    public static String ASR_PARAM_KEY_VAD_ENABLE_LONG_PRESS = "vad_enable_long_press.bool";
    public static String ASR_PARAM_KEY_VAD_ENDPOINT_TIMEOUT = "asr_param_key_vad_endpoint_timeout.int";
    public static String BDS_ASR_OFFLINE_ENGINE_GRAMMER_SLOT = "kws_param_key_slot.string";
    public static String BDS_ASR_OFFLINE_ENGINE_TRIGGERED_WAKEUP_WORD = "kws_param_key_triggered_wakeup_word.string";
    public static String COMMON_PARAM_KEY_DEBUG_LOG_LEVEL = "common_param_key_debug_log_level.int";
    public static final boolean DEBUG = true;
    public static final int ERROR_AUDIO = 3;
    public static final int ERROR_CLIENT = 5;
    public static final int ERROR_INSUFFICIENT_PERMISSIONS = 9;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_NETWORK_TIMEOUT = 1;
    public static final int ERROR_NO_MATCH = 7;
    public static final int ERROR_RECOGNIZER_BUSY = 8;
    public static final int ERROR_SERVER = 4;
    public static final int ERROR_SPEECH_TIMEOUT = 6;
    public static final int EVoiceRecognitionClientWorkStatusCancel = 7;
    public static final int EVoiceRecognitionClientWorkStatusChunkEnd = 14;
    public static final int EVoiceRecognitionClientWorkStatusChunkNlu = 13;
    public static final int EVoiceRecognitionClientWorkStatusChunkThirdData = 12;
    public static final int EVoiceRecognitionClientWorkStatusChunkTtsData = 19;
    public static final int EVoiceRecognitionClientWorkStatusEnd = 2;
    public static final int EVoiceRecognitionClientWorkStatusError = 8;
    public static final int EVoiceRecognitionClientWorkStatusExit = 18;
    public static final int EVoiceRecognitionClientWorkStatusFinish = 5;
    public static final int EVoiceRecognitionClientWorkStatusFlushData = 4;
    public static final int EVoiceRecognitionClientWorkStatusLOG = 11;
    public static final int EVoiceRecognitionClientWorkStatusLoaded = 9;
    public static final int EVoiceRecognitionClientWorkStatusLongSpeechEnd = 17;
    public static final int EVoiceRecognitionClientWorkStatusMeterLevel = 6;
    public static final int EVoiceRecognitionClientWorkStatusNewRecordData = 3;
    public static final int EVoiceRecognitionClientWorkStatusStart = 1;
    public static final int EVoiceRecognitionClientWorkStatusStartWorkIng = 0;
    public static final int EVoiceRecognitionClientWorkStatusUnLoaded = 10;
    public static String MIC_PARAM_KEY_SOCKET_PORT = "mic_param_key_socket_port.int";
    public static String OFFLINE_PARAM_KEY_LICENSE_FILE_PATH = "offline_param_key_license_filepath.string";
    public static final String TAG = "ASREngine";
    public static String UNIT_PARAM_BOT_SESSION_LIST = "bot_session_list.string";
    public static boolean hasBegin = false;
    public static boolean hasEnd = false;
    public static boolean hasPartialResult = false;
    public static final MediaPlayer player = new MediaPlayer();
    public String mApp;
    public Context mContext;
    public EventContext mEventContext;
    public ASRListener mListener;
    public JSONObject mParams;
    public String mPlatform;
    public String mVersion;
    public BDSSDKLoader.BDSSDKInterface m_ASRcore;
    public Future<JSONObject> nlpFeature;
    public StringBuffer unitThirdBufferData;
    public JSONObject usingSimpleNlp;
    public String mSerialNumber = "";
    public String mOutFile = null;
    public int mVolumeFeedbackCount = 0;
    public int mVolumeFreq = 5;
    public String mUserData = null;
    public boolean mFeedBackAudio = false;
    public boolean mEnableChunk = false;
    public boolean mEnableLogFeedBack = true;
    public boolean mEnableLongPress = false;
    public boolean mExceptioned = false;
    public boolean mIsWorking = false;
    public boolean mCalledStop = false;
    public String mLastRecognitionResult = "";
    public int mStreamType = -1;
    public int decodertemp = 0;
    public boolean isOfflineLast = false;
    public boolean enableLongSpeech = false;
    public boolean isUnitFirstPackage = true;
    public ExecutorService nluBuilderThread = Executors.newSingleThreadExecutor();
    public Map<String, JSONObject> mOriginNlp = new HashMap();

    public ASREngine(Context context) throws Exception {
        this.mContext = null;
        this.mContext = context;
        this.mEventContext = new EventContext(context);
        BDSSDKLoader.loadLibraries();
        try {
            BDSSDKLoader.BDSSDKInterface sDKObjectForSDKType = BDSSDKLoader.getSDKObjectForSDKType("ASRCore", this.mContext);
            this.m_ASRcore = sDKObjectForSDKType;
            if (sDKObjectForSDKType == null) {
                throw new Exception("ASR core support is not linked in package");
            }
            if (!sDKObjectForSDKType.instanceInitialized()) {
                throw new Exception("Failed initialize ASR Core native layer");
            }
            this.m_ASRcore.setListener(this);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new Exception("Can't found ASR Core native method");
        }
    }

    private String adaptiveOfflineResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("origin_result");
            if (!jSONObject.has("raw_text")) {
                return str;
            }
            return new JSONObject(str).put("results_recognition", new JSONArray().put(jSONObject.getString("raw_text"))).toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private void asrCallBack(BDSMessage bDSMessage, ASRListener aSRListener) throws Throwable {
        String strGenerateChunkPartialResult;
        byte[] bArr;
        int i;
        int i2;
        String str;
        JSONObject jSONObject;
        String strGenerateEndResult;
        if (bDSMessage.m_messageName.equals(SpeechConstant.ASR_CALLBACk_NAME)) {
            boolean z = true;
            LogUtil.v(TAG, "ASRCallBack :" + bDSMessage.toString());
            int i3 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_STATUS)).iValue;
            Log.v(TAG, "ASRCallBack :" + i3);
            String strGenerateErrorResult = "";
            switch (i3) {
                case 0:
                    hasBegin = false;
                    hasPartialResult = false;
                    hasEnd = false;
                    strGenerateChunkPartialResult = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_RESULT)).iValue;
                    bArr = null;
                    i = 0;
                    i2 = 0;
                    str = SpeechConstant.CALLBACK_EVENT_ASR_READY;
                    aSRListener.onEvent(str, strGenerateChunkPartialResult, bArr, i, i2);
                    break;
                case 1:
                    this.mSerialNumber = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_RESULT)).iValue;
                    HashMap map = new HashMap();
                    map.put("sn", this.mSerialNumber);
                    aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_SERIALNUMBER, new JSONObject(map).toString(), null, 0, 0);
                    if (!hasBegin) {
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN, null, null, 0, 0);
                        hasBegin = true;
                    }
                    break;
                case 2:
                    if (!hasEnd) {
                        play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_END, -1)), false);
                        try {
                            String str2 = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_RESULT)).iValue;
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("vad_silent_start", str2);
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_END, jSONObject2.toString(), null, 0, 0);
                            hasEnd = true;
                            hasBegin = true;
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    break;
                case 3:
                    byte[] bArr2 = bDSMessage.m_messageData;
                    if (this.mFeedBackAudio && bArr2 != null) {
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_AUDIO, null, bArr2, 0, bArr2.length);
                    }
                    saveOutFile(bArr2);
                    break;
                case 4:
                    hasPartialResult = true;
                    if (!hasBegin) {
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN, null, null, 0, 0);
                        hasBegin = true;
                    }
                    String str3 = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_RESULT)).iValue;
                    this.mLastRecognitionResult = str3;
                    strGenerateChunkPartialResult = generateChunkPartialResult(adaptiveOfflineResult(str3));
                    bArr = null;
                    i = 0;
                    i2 = 0;
                    str = SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL;
                    aSRListener.onEvent(str, strGenerateChunkPartialResult, bArr, i, i2);
                    break;
                case 5:
                    if (!hasEnd) {
                        play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_END, -1)), false);
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_END, null, null, 0, 0);
                        hasEnd = true;
                    }
                    play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_SUCCESS, -1)), false);
                    this.mLastRecognitionResult = "";
                    String strAdaptiveOfflineResult = adaptiveOfflineResult((String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_RESULT)).iValue);
                    try {
                        if (new JSONObject(strAdaptiveOfflineResult).optJSONArray("results_recognition").length() != 0) {
                            z = false;
                        }
                    } catch (JSONException unused) {
                    }
                    if (!z) {
                        String strGenerateChunkFinalResult = generateChunkFinalResult(strAdaptiveOfflineResult);
                        if (!this.mEnableChunk) {
                            try {
                                jSONObject = new JSONObject(strGenerateChunkFinalResult);
                                jSONObject.put(SocialConstants.PARAM_APP_DESC, "success");
                                jSONObject.put("error", 0);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                jSONObject = new JSONObject();
                            }
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, jSONObject.toString(), null, 0, 0, true);
                            this.mIsWorking = false;
                            break;
                        } else {
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, strGenerateChunkFinalResult, null, 0, 0, true);
                            if (this.isOfflineLast) {
                                HashMap map2 = new HashMap();
                                map2.put("error", 0);
                                map2.put(SocialConstants.PARAM_APP_DESC, "Speech Recognize success.");
                                HashMap map3 = new HashMap();
                                map3.put("err_no", 0);
                                map3.put("error", "Speech Recognize success.");
                                map2.put("origin_result", new JSONObject(map3));
                                aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, new JSONObject(map2).toString(), null, 0, 0);
                                this.mIsWorking = false;
                                this.isOfflineLast = false;
                            } else if (this.enableLongSpeech) {
                                aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, generateEndResult(strGenerateChunkFinalResult.replaceAll("^.*\"sn\": ?\"(.+)\".*$", "$1")), null, 0, 0);
                            }
                        }
                        hasPartialResult = false;
                    } else {
                        play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_ERROR, -1)), false);
                        try {
                            JSONObject jSONObject3 = new JSONObject();
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("sn", this.mSerialNumber);
                            jSONObject4.put("error", 7);
                            jSONObject3.put(SocialConstants.PARAM_APP_DESC, "Speech Quality Problem");
                            jSONObject3.put("origin_result", jSONObject4);
                            jSONObject3.put("error", 7);
                            jSONObject3.put(SocialConstants.PARAM_APP_DESC, "Speech Quality Problem");
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, jSONObject3.toString(), null, 0, 0, true);
                            this.mIsWorking = false;
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            return;
                        }
                    }
                    break;
                case 6:
                    int i4 = this.mVolumeFeedbackCount + 1;
                    this.mVolumeFeedbackCount = i4;
                    if (i4 % this.mVolumeFreq == 0) {
                        int i5 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.strCALLBACK_ASR_LEVEL)).iValue / 100;
                        int iMin = (int) (Math.min(5000.0f, i5) / 50.0f);
                        HashMap map4 = new HashMap();
                        map4.put("volume", Integer.valueOf(i5));
                        map4.put("volume-percent", Integer.valueOf(iMin));
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_VOLUME, new JSONObject(map4).toString(), null, 0, 0);
                        break;
                    }
                    break;
                case 7:
                    play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_CANCEL, -1)), false);
                    aSRListener.onEvent("asr.cancel", null, null, 0, 0);
                    hasBegin = false;
                    break;
                case 8:
                    play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_ERROR, -1)), false);
                    hasBegin = false;
                    int i6 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_DOMAIN)).iValue;
                    String str4 = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_DESC)).iValue;
                    int i7 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_CODE)).iValue;
                    Log.isLoggable(TAG, 3);
                    Log.e(TAG, "EVoiceRecognitionClientWorkStatusError errorDomain : " + i6 + " errorCode : " + i7 + " desc : " + str4 + " mLastRecognitionResult: " + this.mLastRecognitionResult);
                    if (this.mLastRecognitionResult.isEmpty() || i6 != 20 || i7 == 1 || !hasPartialResult) {
                        try {
                            strGenerateErrorResult = generateErrorResult(i6, i7, str4);
                            break;
                        } catch (Exception unused2) {
                        }
                        strGenerateEndResult = strGenerateErrorResult;
                    } else {
                        play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_END, -1)), false);
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_END, null, null, 0, 0);
                        if (this.mEnableChunk) {
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, generateChunkFinalResult(this.mLastRecognitionResult), null, 0, 0, true);
                            play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_SUCCESS, -1)), false);
                            strGenerateEndResult = generateEndResult(this.mSerialNumber);
                        } else {
                            play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_SUCCESS, -1)), false);
                            strGenerateEndResult = this.mLastRecognitionResult;
                        }
                    }
                    aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, strGenerateEndResult, null, 0, 0);
                    this.mIsWorking = false;
                    break;
                case 9:
                    strGenerateChunkPartialResult = null;
                    bArr = null;
                    i = 0;
                    i2 = 0;
                    str = SpeechConstant.CALLBACK_EVENT_ASR_LOADED;
                    aSRListener.onEvent(str, strGenerateChunkPartialResult, bArr, i, i2);
                    break;
                case 10:
                    aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED, null, null, 0, 0);
                    break;
                case 11:
                    if (this.mEnableLogFeedBack) {
                        strGenerateChunkPartialResult = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ASR_RESULT)).iValue;
                        bArr = null;
                        i = 0;
                        i2 = 0;
                        str = SpeechConstant.CALLBACK_EVENT_ASR_LOG;
                        aSRListener.onEvent(str, strGenerateChunkPartialResult, bArr, i, i2);
                        break;
                    }
                    break;
                case 12:
                    if (this.mEnableChunk) {
                        String strGenerateThirdResult = generateThirdResult();
                        byte[] bArr3 = bDSMessage.m_messageData;
                        byte[] bArr4 = new byte[8];
                        for (int i8 = 0; i8 < 8; i8++) {
                            bArr4[i8] = bArr3[i8 + 4];
                        }
                        if (checkThirdDataUnit(new String(bArr4))) {
                            LogUtil.d(TAG, "unit data");
                            String unitString = getUnitString(bArr3, strGenerateThirdResult);
                            if (bArr3.length <= 12) {
                                aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_UNIT_FINISH, unitString, bArr3, 0, bArr3.length);
                            }
                        } else if (bArr3 != null) {
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, strGenerateThirdResult, bArr3, 0, bArr3.length);
                        }
                    }
                    break;
                case 13:
                    if (this.mEnableChunk) {
                        String strGenerateNluResult = generateNluResult();
                        byte[] bArr5 = bDSMessage.m_messageData;
                        if (bArr5 != null) {
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, strGenerateNluResult, bArr5, 0, bArr5.length);
                        }
                    }
                    break;
                case 14:
                    if (this.enableLongSpeech) {
                        boolean z2 = this.mCalledStop;
                    } else {
                        aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, generateEndResult(this.mSerialNumber), null, 0, 0);
                        this.mIsWorking = false;
                        play(this.mContext, Integer.valueOf(this.mParams.optInt(SpeechConstant.SOUND_SUCCESS, -1)), false);
                    }
                    break;
                case 17:
                    aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH, null, null, 0, 0);
                    this.mIsWorking = false;
                    break;
                case 18:
                    aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_EXIT, null, null, 0, 0);
                    break;
                case 19:
                    if (this.mEnableChunk) {
                        String strGenerateTtsResult = generateTtsResult();
                        byte[] bArr6 = bDSMessage.m_messageData;
                        if (bArr6 != null) {
                            aSRListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL, strGenerateTtsResult, bArr6, 0, bArr6.length);
                        }
                    }
                    break;
            }
        }
    }

    private boolean checkThirdDataUnit(String str) {
        if (!str.contains("unit")) {
            return false;
        }
        LogUtil.d(TAG, "unit data");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0020 -> B:31:0x0042). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void clearOutFile() throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.String r0 = r4.mOutFile
            if (r0 == 0) goto L42
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L42
            r0 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L2a
            java.lang.String r3 = r4.mOutFile     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L2a
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L2a
            byte[] r0 = r1.getBytes()     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L36
            r2.write(r0)     // Catch: java.io.IOException -> L24 java.lang.Throwable -> L36
            r2.close()     // Catch: java.io.IOException -> L1f
            goto L42
        L1f:
            r0 = move-exception
            r0.printStackTrace()
            goto L42
        L24:
            r0 = move-exception
            goto L2d
        L26:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L37
        L2a:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L2d:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r2 == 0) goto L42
            r2.close()     // Catch: java.io.IOException -> L1f
            goto L42
        L36:
            r0 = move-exception
        L37:
            if (r2 == 0) goto L41
            r2.close()     // Catch: java.io.IOException -> L3d
            goto L41
        L3d:
            r1 = move-exception
            r1.printStackTrace()
        L41:
            throw r0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.clearOutFile():void");
    }

    private void fillNlpResult(String str, JSONObject jSONObject) throws Exception {
        JSONArray jSONArrayOptJSONArray;
        JSONArray jSONArrayOptJSONArray2;
        String str2;
        String str3;
        Future<JSONObject> future = this.nlpFeature;
        if (future != null) {
            this.usingSimpleNlp = future.get();
            this.nlpFeature = null;
        }
        if (this.usingSimpleNlp == null) {
            return;
        }
        char c = 0;
        if (jSONObject.optInt("error", 0) != 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("results_recognition")) == null || jSONArrayOptJSONArray.length() == 0) {
            return;
        }
        String strOptString = jSONArrayOptJSONArray.optString(0);
        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(str) && strOptString.length() > str.length()) {
            strOptString = strOptString.substring(str.length());
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = this.usingSimpleNlp.getJSONObject("rules");
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONArray jSONArray2 = jSONObject2.getJSONArray(next);
            int i = 0;
            while (i < jSONArray2.length()) {
                JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                String string = jSONObject3.getString("pattern");
                JSONArray jSONArray3 = jSONObject3.getJSONArray("groups");
                Matcher matcher = Pattern.compile(string).matcher(strOptString);
                while (matcher.find()) {
                    JSONObject jSONObject4 = new JSONObject();
                    String[] strArrSplit = next.split("\\.");
                    if (strArrSplit.length >= 2) {
                        str3 = strArrSplit[c];
                        str2 = strArrSplit[1];
                    } else {
                        str2 = next;
                        str3 = str2;
                    }
                    jSONObject4.put(SerializableCookie.DOMAIN, str3);
                    jSONObject4.put(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, str2);
                    jSONObject4.put("parser", "bsg");
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject4.put("object", jSONObject5);
                    int iGroupCount = matcher.groupCount();
                    int i2 = 0;
                    while (i2 < iGroupCount) {
                        String string2 = jSONArray3.getString(i2);
                        i2++;
                        jSONObject5.put(string2, matcher.group(i2));
                        jSONObject2 = jSONObject2;
                    }
                    jSONArray.put(jSONObject4);
                    c = 0;
                }
                i++;
                c = 0;
            }
        }
        String str4 = (String) this.mEventContext.searchItemFromJson(new JSONObject(jSONObject.optString("origin_result")), "json_res");
        if (!TextUtils.isEmpty(str4) && (jSONArrayOptJSONArray2 = new JSONObject(str4).optJSONArray("results")) != null) {
            for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                jSONArray.put(jSONArrayOptJSONArray2.getJSONObject(i3));
            }
        }
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("raw_text", strOptString);
        jSONObject6.put("results", jSONArray);
        jSONObject.put("results_nlu", jSONObject6);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e A[Catch: Exception -> 0x0116, TryCatch #0 {Exception -> 0x0116, blocks: (B:6:0x0014, B:8:0x0020, B:10:0x0026, B:12:0x0030, B:13:0x0038, B:20:0x0057, B:22:0x005e, B:24:0x0068, B:25:0x006e, B:33:0x009c, B:35:0x00a0, B:37:0x00ae, B:39:0x00bc, B:45:0x00d6, B:47:0x0107, B:48:0x010c, B:26:0x0071, B:28:0x0077, B:30:0x0081, B:32:0x0093, B:14:0x003d, B:16:0x0043, B:18:0x004d), top: B:53:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0071 A[Catch: Exception -> 0x0116, TryCatch #0 {Exception -> 0x0116, blocks: (B:6:0x0014, B:8:0x0020, B:10:0x0026, B:12:0x0030, B:13:0x0038, B:20:0x0057, B:22:0x005e, B:24:0x0068, B:25:0x006e, B:33:0x009c, B:35:0x00a0, B:37:0x00ae, B:39:0x00bc, B:45:0x00d6, B:47:0x0107, B:48:0x010c, B:26:0x0071, B:28:0x0077, B:30:0x0081, B:32:0x0093, B:14:0x003d, B:16:0x0043, B:18:0x004d), top: B:53:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a0 A[Catch: Exception -> 0x0116, TryCatch #0 {Exception -> 0x0116, blocks: (B:6:0x0014, B:8:0x0020, B:10:0x0026, B:12:0x0030, B:13:0x0038, B:20:0x0057, B:22:0x005e, B:24:0x0068, B:25:0x006e, B:33:0x009c, B:35:0x00a0, B:37:0x00ae, B:39:0x00bc, B:45:0x00d6, B:47:0x0107, B:48:0x010c, B:26:0x0071, B:28:0x0077, B:30:0x0081, B:32:0x0093, B:14:0x003d, B:16:0x0043, B:18:0x004d), top: B:53:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String generateChunkFinalResult(java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.generateChunkFinalResult(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b A[Catch: Exception -> 0x0103, TryCatch #0 {Exception -> 0x0103, blocks: (B:6:0x0012, B:8:0x001e, B:10:0x0024, B:12:0x002e, B:13:0x0036, B:20:0x0055, B:22:0x005b, B:24:0x0065, B:33:0x0098, B:35:0x009c, B:37:0x00ac, B:42:0x00c3, B:44:0x00f4, B:45:0x00f9, B:25:0x006c, B:27:0x0072, B:29:0x007c, B:31:0x008e, B:14:0x003b, B:16:0x0041, B:18:0x004b), top: B:50:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006c A[Catch: Exception -> 0x0103, TryCatch #0 {Exception -> 0x0103, blocks: (B:6:0x0012, B:8:0x001e, B:10:0x0024, B:12:0x002e, B:13:0x0036, B:20:0x0055, B:22:0x005b, B:24:0x0065, B:33:0x0098, B:35:0x009c, B:37:0x00ac, B:42:0x00c3, B:44:0x00f4, B:45:0x00f9, B:25:0x006c, B:27:0x0072, B:29:0x007c, B:31:0x008e, B:14:0x003b, B:16:0x0041, B:18:0x004b), top: B:50:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String generateChunkPartialResult(java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.generateChunkPartialResult(java.lang.String):java.lang.String");
    }

    private String generateEndResult(String str) {
        HashMap map = new HashMap();
        map.put("error", 0);
        map.put(SocialConstants.PARAM_APP_DESC, "Speech Recognize success.");
        HashMap map2 = new HashMap();
        map2.put("sn", str);
        map2.put("err_no", 0);
        map2.put("error", "Speech Recognize success.");
        map.put("origin_result", new JSONObject(map2));
        return new JSONObject(map).toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String generateErrorResult(int r10, int r11, java.lang.String r12) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.generateErrorResult(int, int, java.lang.String):java.lang.String");
    }

    private String generateErrorResult(int i, String str) throws Exception {
        String descFromCode;
        String descFromCode2 = "";
        if (str != null && str.contains(Operators.ARRAY_START_STR) && str.contains(Operators.ARRAY_END_STR)) {
            try {
                String strSubstring = str.substring(str.indexOf(Operators.ARRAY_START_STR), str.length());
                try {
                    if (AsrError.getDescFromCode(i) != null && !AsrError.getDescFromCode(i).contains(b.m)) {
                        descFromCode2 = AsrError.getDescFromCode(i);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                descFromCode = descFromCode2 + strSubstring;
            } catch (Exception e3) {
                e3.printStackTrace();
                descFromCode = descFromCode2;
            }
        } else {
            descFromCode = AsrError.getDescFromCode(i);
        }
        int i2 = i / 1000;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("sn", this.mSerialNumber);
        jSONObject2.put("error", i2);
        jSONObject2.put(SocialConstants.PARAM_APP_DESC, descFromCode);
        jSONObject2.put("sub_error", i);
        jSONObject.put("origin_result", jSONObject2);
        jSONObject.put("error", i2);
        jSONObject.put(SocialConstants.PARAM_APP_DESC, descFromCode);
        jSONObject.put("sub_error", i);
        return jSONObject.toString();
    }

    private String generateNluResult() {
        HashMap map = new HashMap();
        map.put("result_type", "nlu_result");
        map.put("best_result", "");
        map.put("origin_result", "");
        return new JSONObject(map).toString();
    }

    private String generateThirdResult() {
        HashMap map = new HashMap();
        map.put("result_type", "third_result");
        map.put("best_result", "");
        map.put("origin_result", "");
        return new JSONObject(map).toString();
    }

    private String generateTtsResult() {
        HashMap map = new HashMap();
        map.put("result_type", "tts_result");
        map.put("best_result", "");
        map.put("origin_result", "");
        return new JSONObject(map).toString();
    }

    private int getLanguageFlag(String str) {
        if (str == null || str.equals("") || str.equals("cmn-Hans-CN")) {
            return 0;
        }
        if (str.equals("yue-Hans-CN")) {
            return 1;
        }
        if (str.equals("en-GB")) {
            return 2;
        }
        return str.equals("sichuan-Hans-CN") ? 3 : 0;
    }

    private String getNlpResult(String str, JSONObject jSONObject) throws Exception {
        JSONArray jSONArrayOptJSONArray;
        String str2;
        String str3;
        Future<JSONObject> future = this.nlpFeature;
        if (future != null) {
            this.usingSimpleNlp = future.get();
            this.nlpFeature = null;
        }
        if (this.usingSimpleNlp == null) {
            return null;
        }
        char c = 0;
        if (jSONObject.optInt("error", 0) != 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("results_recognition")) == null || jSONArrayOptJSONArray.length() == 0) {
            return null;
        }
        String strOptString = jSONArrayOptJSONArray.optString(0);
        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(str) && strOptString.length() > str.length()) {
            strOptString = strOptString.substring(str.length());
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = this.usingSimpleNlp.getJSONObject("rules");
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONArray jSONArray2 = jSONObject2.getJSONArray(next);
            int i = 0;
            while (i < jSONArray2.length()) {
                JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                String string = jSONObject3.getString("pattern");
                JSONArray jSONArray3 = jSONObject3.getJSONArray("groups");
                Matcher matcher = Pattern.compile(string).matcher(strOptString);
                while (matcher.find()) {
                    JSONObject jSONObject4 = new JSONObject();
                    String[] strArrSplit = next.split("\\.");
                    if (strArrSplit.length >= 2) {
                        str3 = strArrSplit[c];
                        str2 = strArrSplit[1];
                    } else {
                        str2 = next;
                        str3 = str2;
                    }
                    jSONObject4.put(SerializableCookie.DOMAIN, str3);
                    jSONObject4.put(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, str2);
                    jSONObject4.put("parser", "bsg");
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject4.put("object", jSONObject5);
                    int iGroupCount = matcher.groupCount();
                    int i2 = 0;
                    while (i2 < iGroupCount) {
                        String string2 = jSONArray3.getString(i2);
                        i2++;
                        jSONObject5.put(string2, matcher.group(i2));
                        jSONObject2 = jSONObject2;
                    }
                    jSONArray.put(jSONObject4);
                    c = 0;
                }
                i++;
                c = 0;
            }
        }
        String str4 = (String) this.mEventContext.searchItemFromJson(new JSONObject(jSONObject.optString("origin_result")), "json_res");
        if (!TextUtils.isEmpty(str4)) {
            JSONArray jSONArray4 = (JSONArray) this.mEventContext.searchItemFromJson(new JSONObject(str4), "results");
            if (jSONArray4 != null) {
                for (int i3 = 0; i3 < jSONArray4.length(); i3++) {
                    jSONArray.put(jSONArray4.getJSONObject(i3));
                }
            }
        }
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("raw_text", strOptString);
        jSONObject6.put("results", jSONArray);
        return jSONObject6.toString();
    }

    private int getSampleRateFlag(int i) {
        if (i == 8000) {
            return 1;
        }
        return i == 16000 ? 2 : 0;
    }

    private String getUnitString(byte[] bArr, String str) {
        if (!this.isUnitFirstPackage) {
            str = new String(bArr);
            if (str.length() > 0) {
                str = str.substring(12, str.length());
            }
            this.unitThirdBufferData.append(str);
        }
        if (this.isUnitFirstPackage && bArr.length > 12) {
            this.isUnitFirstPackage = false;
            String str2 = new String(bArr);
            str = str2.substring(12, str2.length());
            this.unitThirdBufferData = new StringBuffer(str);
        }
        if (bArr.length > 12) {
            return str;
        }
        this.isUnitFirstPackage = true;
        return this.unitThirdBufferData.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x065e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x06c3  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x06c6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x06f2  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x06f4  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0787  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x078a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x07d1  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0846  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0859 A[Catch: all -> 0x087e, TRY_LEAVE, TryCatch #2 {all -> 0x087e, blocks: (B:161:0x0851, B:163:0x0859), top: B:173:0x0851 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x087d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.speech.core.BDSErrorDescription initConfig(com.baidu.speech.core.BDSErrorDescription r22, org.json.JSONObject r23) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 2194
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.initConfig(com.baidu.speech.core.BDSErrorDescription, org.json.JSONObject):com.baidu.speech.core.BDSErrorDescription");
    }

    private void initGrammer(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString(SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH);
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        final JSONObject jSONObjectLoadJsonFromUri = this.mOriginNlp.get(strOptString);
        if (jSONObjectLoadJsonFromUri == null) {
            try {
                jSONObjectLoadJsonFromUri = this.mEventContext.loadJsonFromUri(strOptString, false, true);
            } catch (Exception unused) {
                Log.i(TAG, "bad grammar(as base64): " + strOptString);
            }
        }
        if (jSONObjectLoadJsonFromUri == null) {
            try {
                jSONObjectLoadJsonFromUri = this.mEventContext.loadJsonFromUri(strOptString, false, false);
            } catch (Exception unused2) {
                Log.i(TAG, "bad grammar(as text): " + strOptString);
            }
        }
        if (jSONObjectLoadJsonFromUri != null && this.mOriginNlp.get(strOptString) == null) {
            this.mOriginNlp.put(strOptString, jSONObjectLoadJsonFromUri);
        }
        if (jSONObjectLoadJsonFromUri != null) {
            final JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(SpeechConstant.SLOT_DATA);
            if (jSONObjectOptJSONObject == null) {
                String strOptString2 = jSONObject.optString(SpeechConstant.SLOT_DATA);
                if (!TextUtils.isEmpty(strOptString2)) {
                    jSONObjectOptJSONObject = new JSONObject(strOptString2);
                }
            }
            this.nlpFeature = this.nluBuilderThread.submit(new Callable<JSONObject>() { // from class: com.baidu.speech.core.ASREngine.1
                @Override // java.util.concurrent.Callable
                public JSONObject call() throws Exception {
                    ASREngine.resetNlpGrammar(ASREngine.this.mEventContext, jSONObjectLoadJsonFromUri, jSONObjectOptJSONObject);
                    return jSONObjectLoadJsonFromUri;
                }
            });
        }
    }

    private boolean isIllegalResult(String str) {
        return str != null && str.contains("1。00。");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadGrammar(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "nlu"
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L23
            r2.<init>(r6)     // Catch: java.lang.Exception -> L23
            java.lang.String r3 = "enable"
            java.lang.String r4 = r2.optString(r0)     // Catch: java.lang.Exception -> L23
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> L23
            if (r3 != 0) goto L20
            java.lang.String r3 = "enable-all"
            java.lang.String r0 = r2.optString(r0)     // Catch: java.lang.Exception -> L23
            boolean r0 = r3.equals(r0)     // Catch: java.lang.Exception -> L23
            if (r0 == 0) goto L27
        L20:
            r0 = 1
            r1 = 1
            goto L27
        L23:
            r0 = move-exception
            r0.printStackTrace()
        L27:
            if (r1 == 0) goto L31
            r5.initGrammer(r6)     // Catch: java.lang.Exception -> L2d
            goto L31
        L2d:
            r6 = move-exception
            r6.printStackTrace()
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.loadGrammar(java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0116 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String loadSourceFromUri(java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.loadSourceFromUri(java.lang.String):java.lang.String");
    }

    private int parseDecoder(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.optInt("basic.decoder", jSONObject.optInt(SpeechConstant.DECODER));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private void play(Context context, Object obj, boolean z) {
        MediaPlayer mediaPlayer;
        Integer num;
        if (obj == null) {
            return;
        }
        if (!(obj instanceof Integer) || ((num = (Integer) obj) != null && num.intValue() > 0)) {
            try {
                String str = "" + obj;
                if (str.matches("^(0x)?\\d+$")) {
                    player.reset();
                    AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(Integer.parseInt(str));
                    player.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                    assetFileDescriptorOpenRawResourceFd.close();
                    if (this.mStreamType >= 0) {
                        player.setAudioStreamType(this.mStreamType);
                    }
                    mediaPlayer = player;
                } else {
                    player.reset();
                    player.setDataSource(context, Uri.parse(str));
                    if (this.mStreamType >= 0) {
                        player.setAudioStreamType(this.mStreamType);
                    }
                    mediaPlayer = player;
                }
                mediaPlayer.prepare();
                player.start();
                if (z) {
                    while (player.isPlaying()) {
                        try {
                            Thread.sleep(1L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private BDSErrorDescription postEvent(BDSErrorDescription bDSErrorDescription, String str) {
        String str2;
        BDSMessage bDSMessage = new BDSMessage();
        bDSMessage.m_messageName = str;
        HashMap<String, BDSParamBase> map = new HashMap<>();
        bDSMessage.m_messageParams = map;
        map.put(ASR_PARAM_KEY_PLATFORM, BDSParamBase.objectParam(this.mPlatform, "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_SDK_VERSION, BDSParamBase.objectParam(this.mVersion, "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_APP, BDSParamBase.objectParam(this.mApp, "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_NETWORK_STATUS, BDSParamBase.intParam(Utility.getWifiOr2gOr3G(this.mContext)));
        if ((str.equals(ASR_CMD_START) || str.equals(ASR_CMD_STOP) || str.equals(ASR_CMD_CANCEL)) && (str2 = this.mUserData) != null) {
            bDSMessage.m_messageParams.put(ASR_PARAM_KEY_REALTIME_DATA, BDSParamBase.objectParam(str2, "java.lang.String"));
            this.mUserData = null;
        }
        if (str.equals(ASR_CMD_CONFIG)) {
            bDSMessage.m_messageParams.put(ASR_PARAM_KEY_VAD_ENABLE_LONG_PRESS, BDSParamBase.boolParam(this.mEnableLongPress));
        }
        LogUtil.v(TAG, " cmd:" + str + " msg:" + bDSMessage.toString());
        try {
            int iPostMessage = this.m_ASRcore.postMessage(bDSMessage);
            if (iPostMessage == 0) {
                if (str.equals(ASR_CMD_START)) {
                    this.mIsWorking = true;
                }
                if (str.equals(ASR_CMD_CANCEL)) {
                    this.mIsWorking = false;
                }
                return bDSErrorDescription;
            }
            BDSErrorDescription bDSErrorDescription2 = new BDSErrorDescription();
            bDSErrorDescription2.errorCode = -2;
            bDSErrorDescription2.errorDomain = 1;
            bDSErrorDescription2.errorDescription = "JNI: readyParamsAsrStart Call to Native layer returned error! err( " + iPostMessage + " )";
            return bDSErrorDescription2;
        } catch (Throwable th) {
            th.printStackTrace();
            BDSErrorDescription bDSErrorDescription3 = new BDSErrorDescription();
            bDSErrorDescription3.errorCode = -2;
            bDSErrorDescription3.errorDomain = 1;
            bDSErrorDescription3.errorDescription = "JNI: readyParamsAsrStart Call to Native layer returned error! err";
            return bDSErrorDescription3;
        }
    }

    public static void resetNlpGrammar(EventContext eventContext, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        System.currentTimeMillis();
        if (jSONObject2 != null) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("slots");
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject3.put(next, jSONObject2.getJSONArray(next));
            }
        }
        HashMap map = new HashMap();
        JSONObject jSONObject4 = jSONObject.getJSONObject("slots");
        Iterator<String> itKeys2 = jSONObject4.keys();
        while (itKeys2.hasNext()) {
            String next2 = itKeys2.next();
            JSONArray jSONArray = jSONObject4.getJSONArray(next2);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                if (!string.equals(".+")) {
                    string = string.replaceAll("[\u0000-/]|[:-@]|[\\[-`]|[{-\u00ad]", "");
                }
                arrayList.add(string);
            }
            map.put(String.format("<%s>", next2), String.format("(%s)", eventContext.join(arrayList, "|")));
        }
        JSONObject jSONObject5 = jSONObject.getJSONObject("rules");
        Iterator<String> itKeys3 = jSONObject5.keys();
        while (itKeys3.hasNext()) {
            JSONArray jSONArray2 = jSONObject5.getJSONArray(itKeys3.next());
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject6 = jSONArray2.getJSONObject(i2);
                String string2 = jSONObject6.getString("origin");
                for (Map.Entry entry : map.entrySet()) {
                    string2 = string2.replaceAll((String) entry.getKey(), (String) entry.getValue());
                }
                jSONObject6.put("pattern", "^" + string2 + Operators.DOLLAR_STR);
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x001f -> B:32:0x0040). Please report as a decompilation issue!!! */
    private void saveOutFile(byte[] bArr) throws Throwable {
        String str = this.mOutFile;
        if (str == null || str.equals("") || bArr == null) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(this.mOutFile, true);
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.close();
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void updateUserData(String str) {
        if (str != null) {
            try {
                if (str.equals("")) {
                    this.mUserData = null;
                } else {
                    this.mUserData = new JSONObject(str).optString("realtime-data");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.mUserData = null;
            }
        } else {
            this.mUserData = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x0228  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.baidu.speech.core.BDSErrorDescription postEvent(java.lang.String r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 577
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.core.ASREngine.postEvent(java.lang.String, java.lang.String):com.baidu.speech.core.BDSErrorDescription");
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSCoreEventListener
    public void receiveCoreEvent(BDSMessage bDSMessage, BDSSDKLoader.BDSSDKInterface bDSSDKInterface) throws Throwable {
        ASRListener aSRListener = this.mListener;
        if (aSRListener == null || bDSMessage == null) {
            return;
        }
        asrCallBack(bDSMessage, aSRListener);
    }

    public void setListener(ASRListener aSRListener) {
        this.mListener = aSRListener;
    }
}
