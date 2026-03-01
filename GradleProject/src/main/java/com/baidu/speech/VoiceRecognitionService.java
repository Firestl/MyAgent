package com.baidu.speech;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.speech.RecognitionService;
import android.util.AndroidRuntimeException;
import com.baidu.speech.asr.SpeechConstant;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class VoiceRecognitionService extends RecognitionService {
    public static final int EVENT_ENGINE_SWITCH = 12;
    public static final int EVENT_ERROR = 11;
    public static final int EVENT_THIRD_DATA = 12;
    public static final String VERSION_NAME = "3.4.1.107";
    public boolean internal;
    public EventManager mEventManagerAsr;
    public Bundle mFinalBundle;
    public boolean mLongSpeech;
    public MyListener mUsingListener;
    public static final String TAG = "VoiceRecognitionService";
    public static final Logger logger = Logger.getLogger(TAG);

    public class MyListener implements EventListener {
        public RecognitionService.Callback mListener;

        public MyListener() {
        }

        private final void callbackOnEvent(RecognitionService.Callback callback, int i, Bundle bundle) {
            try {
                Field declaredField = callback.getClass().getDeclaredField("mListener");
                declaredField.setAccessible(true);
                Class.forName("android.speech.IRecognitionListener").getMethod("onEvent", Integer.TYPE, Bundle.class).invoke(declaredField.get(callback), Integer.valueOf(i), bundle);
            } catch (Exception e2) {
                e2.printStackTrace();
                VoiceRecognitionService.logger.log(Level.WARNING, "", (Throwable) e2);
            }
        }

        @Override // com.baidu.speech.EventListener
        public void onEvent(String str, String str2, byte[] bArr, int i, int i2) {
            VoiceRecognitionService voiceRecognitionService;
            RecognitionService.Callback callback = this.mListener;
            if (callback == null) {
                return;
            }
            try {
                if (SpeechConstant.CALLBACK_EVENT_ASR_READY.equals(str)) {
                    callback.readyForSpeech(new Bundle());
                    return;
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_BEGIN.equals(str)) {
                    callback.beginningOfSpeech();
                    return;
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_AUDIO.equals(str)) {
                    callback.bufferReceived(bArr);
                    return;
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_VOLUME.equals(str)) {
                    callback.rmsChanged((float) new JSONObject(str2).optDouble("volume"));
                    return;
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_END.equals(str)) {
                    callback.endOfSpeech();
                    return;
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL.equals(str)) {
                    JSONObject jSONObject = new JSONObject(str2);
                    String strOptString = jSONObject.optString("result_type");
                    Bundle bundleFromJson = VoiceRecognitionService.fromJson(jSONObject);
                    if (strOptString == null || strOptString == "") {
                        return;
                    }
                    if (strOptString.equals("partial_result")) {
                        callback.partialResults(bundleFromJson);
                        return;
                    }
                    if (strOptString.equals("final_result")) {
                        VoiceRecognitionService.this.mFinalBundle = bundleFromJson;
                        return;
                    } else {
                        if (strOptString.equals("third_result")) {
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("third_data", bArr);
                            callbackOnEvent(callback, 12, bundle);
                            return;
                        }
                        return;
                    }
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_FINISH.equals(str)) {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    int i3 = jSONObject2.getInt("error");
                    if (i3 != 0) {
                        callback.error(i3);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("error", jSONObject2.getInt("sub_error"));
                        bundle2.putString("reason", jSONObject2.getString(SocialConstants.PARAM_APP_DESC));
                        callbackOnEvent(callback, 11, bundle2);
                        return;
                    }
                    if (VoiceRecognitionService.this.mLongSpeech) {
                        return;
                    }
                    callback.results(VoiceRecognitionService.this.mFinalBundle);
                    voiceRecognitionService = VoiceRecognitionService.this;
                } else {
                    if (!SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH.equals(str)) {
                        return;
                    }
                    callback.results(VoiceRecognitionService.this.mFinalBundle);
                    voiceRecognitionService = VoiceRecognitionService.this;
                }
                voiceRecognitionService.mFinalBundle = null;
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }

        public void setCallbackListener(RecognitionService.Callback callback) {
            this.mListener = callback;
        }
    }

    private JSONObject convertIntentToJson(Intent intent) {
        HashMap map = new HashMap();
        intent.getStringExtra("a");
        Bundle extras = intent.getExtras();
        for (String str : extras.keySet()) {
            Object obj = extras.get(str);
            if (str.equals("args") && (obj instanceof String)) {
                for (String str2 : ((String) obj).split("--")) {
                    int iIndexOf = str2.trim().indexOf(Operators.SPACE_STR);
                    if (iIndexOf < 0) {
                        iIndexOf = str2.indexOf("\t");
                    }
                    if (iIndexOf < 0) {
                        iIndexOf = str2.indexOf(ContainerUtils.KEY_VALUE_DELIMITER);
                    }
                    if (iIndexOf > 0) {
                        map.put(str2.substring(0, iIndexOf).trim(), str2.substring(iIndexOf + 1).trim());
                    }
                }
            } else {
                map.put(str, obj);
            }
        }
        return new JSONObject(map);
    }

    public static Bundle fromJson(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(next);
            String strOptString = jSONObject.optString(next);
            int i = 0;
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() <= 0) {
                bundle.putStringArray(next, new String[0]);
            } else if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.optString(0) != null) {
                ArrayList<String> arrayList = new ArrayList<>();
                while (i < jSONArrayOptJSONArray.length()) {
                    arrayList.add(jSONArrayOptJSONArray.optString(i));
                    i++;
                }
                bundle.putStringArrayList(next, arrayList);
            } else if (jSONArrayOptJSONArray != null && !Double.isNaN(jSONArrayOptJSONArray.optDouble(0))) {
                double[] dArr = new double[jSONArrayOptJSONArray.length()];
                while (i < jSONArrayOptJSONArray.length()) {
                    dArr[i] = jSONArrayOptJSONArray.optDouble(i);
                    i++;
                }
                bundle.putDoubleArray(next, dArr);
            } else if (strOptString != null) {
                bundle.putString(next, strOptString);
            }
        }
        return bundle;
    }

    public static String getSdkVersion() {
        return VERSION_NAME;
    }

    @Override // android.speech.RecognitionService
    public void onCancel(RecognitionService.Callback callback) {
        this.mEventManagerAsr.send("asr.cancel", "{}", null, 0, 0);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        synchronized (VoiceRecognitionService.class) {
            if (this.mEventManagerAsr == null) {
                this.mEventManagerAsr = EventManagerFactory.create(getApplicationContext(), "asr");
                MyListener myListener = new MyListener();
                this.mUsingListener = myListener;
                this.mEventManagerAsr.registerListener(myListener);
                SpeechConstant.PUBLIC_DECODER = false;
            }
        }
        logger.info(String.format("onCreate(), hashcode=%s", Integer.valueOf(hashCode())));
        try {
            Class.forName("com.baidu.android.voicedemo.SettingMore");
            this.internal = true;
        } catch (Exception unused) {
        }
        logger.info("internal=" + this.internal);
        try {
            if (getPackageManager().getServiceInfo(new ComponentName(getPackageName(), getClass().getName()), 128).exported) {
                throw new AndroidRuntimeException(getClass().getName() + ", 'android:exported' should be false, please modify AndroidManifest.xml");
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.speech.RecognitionService, android.app.Service
    public void onDestroy() {
        this.mEventManagerAsr.send(SpeechConstant.ASR_KWS_UNLOAD_ENGINE, "{}", null, 0, 0);
        super.onDestroy();
    }

    @Override // android.speech.RecognitionService
    public void onStartListening(Intent intent, RecognitionService.Callback callback) {
        if (!intent.hasExtra(SpeechConstant.AUDIO_MILLS)) {
            intent.putExtra(SpeechConstant.AUDIO_MILLS, System.currentTimeMillis());
        }
        this.mLongSpeech = intent.getIntExtra(SpeechConstant.VAD_ENDPOINT_TIMEOUT, -1) == 0;
        JSONObject jSONObjectConvertIntentToJson = convertIntentToJson(intent);
        try {
            this.mUsingListener.setCallbackListener(callback);
            if (intent.getIntExtra(SpeechConstant.DECODER, 0) != 0) {
                this.mEventManagerAsr.send(SpeechConstant.ASR_KWS_LOAD_ENGINE, jSONObjectConvertIntentToJson.toString(4), null, 0, 0);
            }
            this.mEventManagerAsr.send(SpeechConstant.ASR_START, jSONObjectConvertIntentToJson.toString(4), null, 0, 0);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.speech.RecognitionService
    public void onStopListening(RecognitionService.Callback callback) {
        this.mEventManagerAsr.send(SpeechConstant.ASR_STOP, "{}", null, 0, 0);
    }
}
