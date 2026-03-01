package com.baidu.speech.asr;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.baidu.speech.EventListener;
import com.baidu.speech.audio.MicrophoneServer;
import com.baidu.speech.core.BDSErrorDescription;
import com.baidu.speech.core.BDSMessage;
import com.baidu.speech.core.BDSParamBase;
import com.baidu.speech.core.BDSSDKLoader;
import com.baidu.speech.utils.AsrError;
import com.baidu.speech.utils.LogUtil;
import com.baidu.speech.utils.Policy;
import com.taobao.weex.ui.component.WXImage;
import com.tencent.open.SocialConstants;
import io.dcloud.common.DHInterface.IApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WakeUpControl implements BDSSDKLoader.BDSCoreEventListener {
    public static String ASR_PARAM_KEY_OFFLINE_APP_CODE = "offline_param_key_app_code.string";
    public static String ASR_PARAM_KEY_PLATFORM = "asr_param_key_platform.string";
    public static String ASR_PARAM_KEY_SDK_VERSION = "asr_param_key_sdk_version.string";
    public static String COMMON_PARAM_KEY_DEBUG_LOG_LEVEL = "common_param_key_debug_log_level.int";
    public static final int EWakeupEngineWorkStatusError = 6;
    public static final int EWakeupEngineWorkStatusLoaded = 3;
    public static final int EWakeupEngineWorkStatusNewData = 7;
    public static final int EWakeupEngineWorkStatusReadyed = 1;
    public static final int EWakeupEngineWorkStatusStarted = 0;
    public static final int EWakeupEngineWorkStatusStopped = 2;
    public static final int EWakeupEngineWorkStatusTriggered = 5;
    public static final int EWakeupEngineWorkStatusUnLoaded = 4;
    public static String MIC_PARAM_KEY_AUDIO_FILE_PATH = "mic_audio_file_path.string";
    public static String MIC_PARAM_KEY_SOCKET_PORT = "mic_param_key_socket_port.int";
    public static String OFFLINE_PARAM_KEY_LICENSE_FILE_PATH = "offline_param_key_license_filepath.string";
    public static final String TAG = "WakeUpControl";
    public static String WAK_CMD_CONFIG = "wak.config";
    public static String WAK_CMD_DATA = "wak.data";
    public static String WAK_CMD_LOAD_ENGINE = "wak.load";
    public static String WAK_CMD_START = "wak.start";
    public static String WAK_CMD_STOP = "wak.stop";
    public static String WAK_CMD_UNLOAD_ENGINE = "wak.unload";
    public static String WP_PARAM_KEY_ENABLE_MODEL_VAD = "wakeup_param_key_mode.int";
    public static String WP_PARAM_KEY_ENABLE_VAD = "wakeup_param_key_enable_vad.bool";
    public static String WP_PARAM_KEY_VAD_DAT_FILE_PATH = "wakeup_param_key_vad_dat_file_path.string";
    public static String WP_PARAM_KEY_WAKEUP_ACCEPT_AUDIO_DATA = "wakeup_param_key_accept_audio_data.bool";
    public static String WP_PARAM_KEY_WAKEUP_DAT_FILE_PATH = "wakeup_param_key_dat_filepath.string";
    public static String WP_PARAM_KEY_WAKEUP_KWD = "wakeup_param_key_kwd.bool";
    public static String WP_PARAM_KEY_WAKEUP_MODE = "wakeup_param_key_mode.int";
    public static String WP_PARAM_KEY_WAKEUP_WORDS = "wakeup_param_key_words.vector<string>";
    public static String WP_PARAM_KEY_WAKEUP_WORDS_FILE_PATH = "wakeup_param_key_words_filepath.string";
    public Context context;
    public boolean mFeedBackAudio;
    public EventListener mListener;
    public JSONObject mParams;
    public BDSSDKLoader.BDSSDKInterface m_Wakeupcore;
    public String outFile = null;
    public boolean mIsWorking = false;

    public enum DebugLogLevel {
        EVRDebugLogLevelOff,
        EVRDebugLogLevelFatal,
        EVRDebugLogLevelError,
        EVRDebugLogLevelWarning,
        EVRDebugLogLevelInformation,
        EVRDebugLogLevelDebug,
        EVRDebugLogLevelTrace
    }

    public WakeUpControl(Context context) throws Exception {
        this.context = context;
        BDSSDKLoader.loadLibraries();
        try {
            BDSSDKLoader.BDSSDKInterface sDKObjectForSDKType = BDSSDKLoader.getSDKObjectForSDKType("WakeupCore", context);
            this.m_Wakeupcore = sDKObjectForSDKType;
            if (sDKObjectForSDKType == null) {
                throw new Exception("ASR core support is not linked in package");
            }
            if (!sDKObjectForSDKType.instanceInitialized()) {
                throw new Exception("Failed initialize ASR Core native layer");
            }
            this.m_Wakeupcore.setListener(this);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new Exception("Can't found ASR Core native method");
        }
    }

    private void asrCallBack(BDSMessage bDSMessage, EventListener eventListener) throws Throwable {
        String str;
        byte[] bArr;
        int i;
        int i2;
        String str2;
        String strGenerateErrorResult;
        if (bDSMessage.m_messageName.equals(SpeechConstant.WAKEUP_CALLBACK_NAME)) {
            int i3 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_WAK_STATUS)).iValue;
            LogUtil.v(TAG, "WPCallBack:" + bDSMessage.toString());
            if (i3 == 0) {
                eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_STARTED, null, null, 0, 0);
                return;
            }
            if (i3 == 1) {
                str = null;
                bArr = null;
                i = 0;
                i2 = 0;
                str2 = SpeechConstant.CALLBACK_EVENT_WAKEUP_READY;
            } else {
                if (i3 == 2) {
                    this.mIsWorking = false;
                    eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED, null, null, 0, 0);
                    return;
                }
                if (i3 == 5) {
                    String str3 = (String) ((BDSParamBase.BDSObjectParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_WAK_RESULT)).iValue;
                    HashMap map = new HashMap();
                    map.put("word", str3);
                    map.put("errorCode", 0);
                    map.put(WXImage.ERRORDESC, "wakup success");
                    eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS, new JSONObject(map).toString(), null, 0, 0);
                    return;
                }
                if (i3 != 6) {
                    if (i3 != 7) {
                        return;
                    }
                    byte[] bArr2 = bDSMessage.m_messageData;
                    if (this.mFeedBackAudio && bArr2 != null) {
                        eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_AUDIO, null, bArr2, 0, bArr2.length);
                    }
                    saveOutFile(bArr2);
                    return;
                }
                this.mIsWorking = false;
                int i4 = ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_DOMAIN)).iValue;
                try {
                    strGenerateErrorResult = generateErrorResult(i4, ((BDSParamBase.BDSIntParam) bDSMessage.m_messageParams.get(SpeechConstant.CALLBACK_ERROR_CODE)).iValue);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    strGenerateErrorResult = "";
                }
                str = strGenerateErrorResult;
                bArr = null;
                i = 0;
                i2 = 0;
                str2 = SpeechConstant.CALLBACK_EVENT_WAKEUP_ERROR;
            }
            eventListener.onEvent(str2, str, bArr, i, i2);
        }
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
            java.lang.String r0 = r4.outFile
            if (r0 == 0) goto L42
            java.lang.String r1 = ""
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L42
            r0 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L2a
            java.lang.String r3 = r4.outFile     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L2a
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
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.asr.WakeUpControl.clearOutFile():void");
    }

    private String generateErrorResult(int i) {
        String descFromCode = AsrError.getDescFromCode(i);
        int i2 = i / 1000;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error", i2);
            jSONObject.put(SocialConstants.PARAM_APP_DESC, descFromCode);
            jSONObject.put("sub_error", i);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String generateErrorResult(int r3, int r4) {
        /*
            r2 = this;
            java.lang.String r0 = com.baidu.speech.utils.AsrError.getDescFromCode(r4)
            if (r0 == 0) goto L7
            goto L5b
        L7:
            r0 = 38
            r1 = 1
            if (r3 != r0) goto L51
            if (r1 != r4) goto L11
            r4 = 11001(0x2af9, float:1.5416E-41)
            goto L5b
        L11:
            r3 = 2
            if (r3 != r4) goto L17
            r4 = 11002(0x2afa, float:1.5417E-41)
            goto L5b
        L17:
            r3 = 3
            if (r3 != r4) goto L1d
            r4 = 11003(0x2afb, float:1.5418E-41)
            goto L5b
        L1d:
            r3 = 4
            if (r3 != r4) goto L23
            r4 = 11004(0x2afc, float:1.542E-41)
            goto L5b
        L23:
            r3 = 5
            if (r3 != r4) goto L29
            r4 = 11005(0x2afd, float:1.5421E-41)
            goto L5b
        L29:
            r3 = 6
            if (r3 != r4) goto L2f
            r4 = 11006(0x2afe, float:1.5423E-41)
            goto L5b
        L2f:
            r3 = 7
            if (r3 != r4) goto L35
            r4 = 11007(0x2aff, float:1.5424E-41)
            goto L5b
        L35:
            r3 = 8
            if (r3 != r4) goto L3c
            r4 = 11008(0x2b00, float:1.5425E-41)
            goto L5b
        L3c:
            r3 = 9
            if (r3 != r4) goto L43
            r4 = 11009(0x2b01, float:1.5427E-41)
            goto L5b
        L43:
            r3 = 10
            if (r3 != r4) goto L4a
            r4 = 11010(0x2b02, float:1.5428E-41)
            goto L5b
        L4a:
            r3 = 11
            if (r3 != r4) goto L5a
            r4 = 11011(0x2b03, float:1.543E-41)
            goto L5b
        L51:
            r0 = 20
            if (r3 != r0) goto L5a
            if (r1 != r4) goto L5a
            r4 = 3100(0xc1c, float:4.344E-42)
            goto L5b
        L5a:
            r4 = -1
        L5b:
            java.lang.String r3 = r2.generateErrorResult(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.asr.WakeUpControl.generateErrorResult(int, int):java.lang.String");
    }

    private File getDiskCacheDir(Context context) {
        return context.getCacheDir();
    }

    private BDSErrorDescription initWp(BDSErrorDescription bDSErrorDescription, JSONObject jSONObject) {
        HashMap<String, BDSParamBase> map;
        String str;
        BDSParamBase bDSParamBaseBoolParam;
        String str2 = null;
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                str2 = applicationInfo.metaData.getInt("com.baidu.speech.APP_ID") + "";
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        String strOptString = jSONObject.optString("appid", str2);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(SpeechConstant.WP_WORDS);
        Vector vector = new Vector();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    vector.add(jSONArrayOptJSONArray.getString(i));
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
        String strOptString2 = jSONObject.optString(SpeechConstant.WP_DAT_FILEPATH, jSONObject.optString("wp.res-file", jSONObject.optString("res-file", String.format("%s/%s", this.context.getApplicationInfo().nativeLibraryDir, "libbd_easr_s1_merge_normal_20151216.dat.so"))));
        String strOptString3 = jSONObject.optString("wp.kws-file", jSONObject.optString(SpeechConstant.WP_WORDS_FILE));
        String strOptString4 = jSONObject.optString(SpeechConstant.IN_FILE);
        String strOptString5 = jSONObject.optString("decoder-offline.license-file-path", jSONObject.optString("license-file-path", jSONObject.optString(IApp.ConfigProperty.CONFIG_LICENSE)));
        int iOptInt = jSONObject.optInt(SpeechConstant.LOG_LEVEL, -1);
        if (Log.isLoggable(LogUtil.LOGTAG, 3)) {
            iOptInt = 5;
        } else if (Log.isLoggable(LogUtil.LOGTAG, 2)) {
            iOptInt = 6;
        }
        this.mFeedBackAudio = jSONObject.optBoolean(SpeechConstant.ACCEPT_AUDIO_DATA, false);
        this.outFile = jSONObject.optString(SpeechConstant.OUT_FILE);
        BDSMessage bDSMessage = new BDSMessage();
        bDSMessage.m_messageName = WAK_CMD_CONFIG;
        HashMap<String, BDSParamBase> map2 = new HashMap<>();
        bDSMessage.m_messageParams = map2;
        map2.put(ASR_PARAM_KEY_OFFLINE_APP_CODE, BDSParamBase.objectParam(strOptString, "java.lang.String"));
        bDSMessage.m_messageParams.put(WP_PARAM_KEY_WAKEUP_DAT_FILE_PATH, BDSParamBase.objectParam(loadSourceFromUri(strOptString2), "java.lang.String"));
        bDSMessage.m_messageParams.put(WP_PARAM_KEY_WAKEUP_WORDS, BDSParamBase.objectParam(vector, "java.util.Vector;"));
        bDSMessage.m_messageParams.put(WP_PARAM_KEY_WAKEUP_WORDS_FILE_PATH, BDSParamBase.objectParam(loadSourceFromUri(strOptString3), "java.lang.String"));
        bDSMessage.m_messageParams.put(OFFLINE_PARAM_KEY_LICENSE_FILE_PATH, BDSParamBase.objectParam(loadSourceFromUri(strOptString5), "java.lang.String"));
        bDSMessage.m_messageParams.put(MIC_PARAM_KEY_AUDIO_FILE_PATH, BDSParamBase.objectParam(strOptString4, "java.lang.String"));
        if (jSONObject.optBoolean(SpeechConstant.WP_VAD_ENABLE, false)) {
            bDSMessage.m_messageParams.put(WP_PARAM_KEY_ENABLE_VAD, BDSParamBase.boolParam(true));
            bDSMessage.m_messageParams.put(WP_PARAM_KEY_ENABLE_MODEL_VAD, BDSParamBase.intParam(2));
            map = bDSMessage.m_messageParams;
            str = WP_PARAM_KEY_VAD_DAT_FILE_PATH;
            bDSParamBaseBoolParam = BDSParamBase.objectParam(this.context.getApplicationInfo().nativeLibraryDir, "java.lang.String");
        } else {
            map = bDSMessage.m_messageParams;
            str = WP_PARAM_KEY_ENABLE_VAD;
            bDSParamBaseBoolParam = BDSParamBase.boolParam(false);
        }
        map.put(str, bDSParamBaseBoolParam);
        if (jSONObject.optBoolean("wp.kwd_enable", false)) {
            bDSMessage.m_messageParams.put(WP_PARAM_KEY_WAKEUP_KWD, BDSParamBase.boolParam(true));
        }
        bDSMessage.m_messageParams.put(MIC_PARAM_KEY_SOCKET_PORT, BDSParamBase.intParam(jSONObject.optInt("audio.socketport")));
        if (iOptInt != -1) {
            bDSMessage.m_messageParams.put(COMMON_PARAM_KEY_DEBUG_LOG_LEVEL, BDSParamBase.intParam(iOptInt));
        }
        bDSMessage.m_messageParams.put(WP_PARAM_KEY_WAKEUP_ACCEPT_AUDIO_DATA, BDSParamBase.boolParam(this.mFeedBackAudio));
        boolean zOptBoolean = jSONObject.optBoolean(SpeechConstant.ACCEPT_AUDIO_VOLUME, true);
        if (!zOptBoolean) {
            bDSMessage.m_messageParams.put("mic_accept_audio_volume.bool", BDSParamBase.boolParam(zOptBoolean));
        }
        int iOptInt2 = jSONObject.optInt("wp.mode", 0);
        if (iOptInt2 > 0) {
            bDSMessage.m_messageParams.put(WP_PARAM_KEY_WAKEUP_MODE, BDSParamBase.intParam(iOptInt2));
        }
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_PLATFORM, BDSParamBase.objectParam("Android", "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_SDK_VERSION, BDSParamBase.objectParam("C++ ASR core", "java.lang.String"));
        try {
            int iPostMessage = this.m_Wakeupcore.postMessage(bDSMessage);
            if (iPostMessage == 0) {
                return bDSErrorDescription;
            }
            BDSErrorDescription bDSErrorDescription2 = new BDSErrorDescription();
            bDSErrorDescription2.errorCode = -2;
            bDSErrorDescription2.errorDomain = 1;
            bDSErrorDescription2.errorDescription = "JNI: readyParamsWpStart Call to Native layer returned error! err( " + iPostMessage + " )";
            return bDSErrorDescription2;
        } catch (Throwable th) {
            th.printStackTrace();
            BDSErrorDescription bDSErrorDescription3 = new BDSErrorDescription();
            bDSErrorDescription3.errorCode = -2;
            bDSErrorDescription3.errorDomain = 1;
            bDSErrorDescription3.errorDescription = "JNI: readyParamsWpStart Call to Native layer returned error! err";
            return bDSErrorDescription3;
        }
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
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.speech.asr.WakeUpControl.loadSourceFromUri(java.lang.String):java.lang.String");
    }

    private BDSErrorDescription postEvent(BDSErrorDescription bDSErrorDescription, String str) {
        BDSMessage bDSMessage = new BDSMessage();
        if (str.contains("wp")) {
            bDSMessage.m_messageName = str.replace("wp", "wak");
        } else {
            bDSMessage.m_messageName = str;
        }
        bDSMessage.m_messageParams = new HashMap<>();
        this.mParams.optString(SpeechConstant.APP_NAME, Policy.app(this.context));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_PLATFORM, BDSParamBase.objectParam("Android", "java.lang.String"));
        bDSMessage.m_messageParams.put(ASR_PARAM_KEY_SDK_VERSION, BDSParamBase.objectParam("C++ ASR core", "java.lang.String"));
        LogUtil.v(TAG, " wakeup postEvent to c++  cmd:" + str + " Message:" + bDSMessage.toString());
        try {
            if (this.m_Wakeupcore.postMessage(bDSMessage) == 0) {
                return bDSErrorDescription;
            }
            BDSErrorDescription bDSErrorDescription2 = new BDSErrorDescription();
            bDSErrorDescription2.errorCode = -2;
            bDSErrorDescription2.errorDomain = 1;
            bDSErrorDescription2.errorDescription = "JNI: readyParamsAsrStart Call to Native layer returned error! err";
            return bDSErrorDescription2;
        } catch (Throwable th) {
            th.printStackTrace();
            BDSErrorDescription bDSErrorDescription3 = new BDSErrorDescription();
            bDSErrorDescription3.errorCode = -2;
            bDSErrorDescription3.errorDomain = 1;
            bDSErrorDescription3.errorDescription = "JNI: readyParamsWpStart Call to Native layer returned error! err";
            return bDSErrorDescription3;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x001f -> B:32:0x0040). Please report as a decompilation issue!!! */
    private void saveOutFile(byte[] bArr) throws Throwable {
        String str = this.outFile;
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
                FileOutputStream fileOutputStream2 = new FileOutputStream(this.outFile, true);
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

    public BDSErrorDescription postEvent(String str, String str2) throws Throwable {
        BDSErrorDescription bDSErrorDescription;
        LogUtil.v(TAG, " wakeup postEvent  cmd:" + str + " params:" + str2);
        try {
        } catch (Throwable th) {
            th.printStackTrace();
            bDSErrorDescription = new BDSErrorDescription();
        }
        if (!this.m_Wakeupcore.instanceInitialized()) {
            bDSErrorDescription = new BDSErrorDescription();
            bDSErrorDescription.errorCode = -1;
            bDSErrorDescription.errorDomain = 1;
            bDSErrorDescription.errorDescription = "JNI: ASR Core native layer is not initialized!";
            return bDSErrorDescription;
        }
        if (str2 != null) {
            try {
                if (str2.equals("")) {
                    this.mParams = new JSONObject();
                } else {
                    this.mParams = new JSONObject(str2);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                this.mParams = new JSONObject();
            }
        } else {
            this.mParams = new JSONObject();
        }
        if (str.equals(SpeechConstant.WAKEUP_START)) {
            if (this.mIsWorking) {
                return null;
            }
            this.mIsWorking = true;
            try {
                if (!this.mParams.has("audio.socketport")) {
                    this.mParams.put("audio.socketport", MicrophoneServer.create(this.mParams.optString(SpeechConstant.IN_FILE), this.mParams.has(SpeechConstant.AUDIO_SOURCE) ? this.mParams.optInt(SpeechConstant.AUDIO_SOURCE) : 1));
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            initWp(null, this.mParams);
            postEvent((BDSErrorDescription) null, WAK_CMD_LOAD_ENGINE);
            clearOutFile();
        } else if (str.equals(SpeechConstant.WAKEUP_STOP)) {
            postEvent((BDSErrorDescription) null, WAK_CMD_UNLOAD_ENGINE);
        }
        return postEvent((BDSErrorDescription) null, str);
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSCoreEventListener
    public void receiveCoreEvent(BDSMessage bDSMessage, BDSSDKLoader.BDSSDKInterface bDSSDKInterface) throws Throwable {
        EventListener eventListener = this.mListener;
        if (eventListener == null || bDSMessage == null) {
            return;
        }
        asrCallBack(bDSMessage, eventListener);
    }

    public void setListener(EventListener eventListener) {
        this.mListener = eventListener;
    }
}
