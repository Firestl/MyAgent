package com.baidu.speech.asr;

import android.content.Context;
import android.util.Log;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.utils.analysis.Analysis;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AnalysisInterceptor implements EventManager, ASRListener {
    public static final boolean DEBUG = false;
    public static final String TAG = "Analysis";
    public static AnalysisInterceptor instance;
    public final Context context;
    public Analysis.Item usingAnalysisItem;

    public AnalysisInterceptor(Context context) {
        this.context = context;
    }

    public static synchronized AnalysisInterceptor getInstance(Context context) {
        if (instance == null) {
            instance = new AnalysisInterceptor(context.getApplicationContext());
        }
        return instance;
    }

    @Override // com.baidu.speech.asr.ASRListener
    public void onEvent(String str, String str2, byte[] bArr, int i, int i2) {
    }

    @Override // com.baidu.speech.asr.ASRListener
    public void onEvent(String str, String str2, byte[] bArr, int i, int i2, boolean z) {
        try {
            if (SpeechConstant.CALLBACK_EVENT_ASR_READY.equals(str)) {
                JSONObject jSONObject = new JSONObject(str2);
                this.usingAnalysisItem.pid = jSONObject.getInt("pid");
                this.usingAnalysisItem.decoder = jSONObject.optInt("decoder-server.decoder", jSONObject.optInt(SpeechConstant.DECODER, 0));
            }
            if (SpeechConstant.CALLBACK_EVENT_ASR_FINISH.equals(str)) {
                JSONObject jSONObject2 = new JSONObject(str2);
                this.usingAnalysisItem.error_code = jSONObject2.optInt("sub_error", 0);
            }
            if (SpeechConstant.CALLBACK_EVENT_ASR_EXIT.equals(str)) {
                Analysis.log(this.context, this.usingAnalysisItem);
                Analysis.asyncUploadAll(this.context);
            }
        } catch (Exception e2) {
            if (Log.isLoggable("Analysis", 3)) {
                Log.d("Analysis", "", e2);
            }
            e2.printStackTrace();
        }
    }

    @Override // com.baidu.speech.EventManager
    public void registerListener(EventListener eventListener) {
    }

    @Override // com.baidu.speech.EventManager
    public void send(String str, String str2, byte[] bArr, int i, int i2) {
        try {
            if (SpeechConstant.ASR_START.equals(str)) {
                JSONObject jSONObject = new JSONObject(str2);
                int iOptInt = jSONObject.optInt("appid", 0);
                Analysis.Item item = new Analysis.Item();
                this.usingAnalysisItem = item;
                item.appId = iOptInt;
                item.pkg = this.context.getPackageName();
                this.usingAnalysisItem.time = System.currentTimeMillis();
                int iOptInt2 = jSONObject.optInt(SpeechConstant.VAD_ENDPOINT_TIMEOUT, -1);
                this.usingAnalysisItem.type = iOptInt2 == 0 ? Analysis.Item.TYPE_ASR_LONGSPEECH : Analysis.Item.TYPE_NORMAL;
            }
            if (SpeechConstant.WAKEUP_START.equals(str)) {
                JSONObject jSONObject2 = new JSONObject(str2);
                Analysis.Item item2 = new Analysis.Item();
                item2.pid = 0;
                item2.error_code = 0;
                item2.appId = jSONObject2.getInt("appid");
                item2.time = System.currentTimeMillis();
                item2.pkg = this.context.getPackageName();
                item2.type = Analysis.Item.TYPE_WAKEUP;
                Analysis.log(this.context, item2);
                Analysis.asyncUploadAll(this.context);
            }
        } catch (Exception e2) {
            if (Log.isLoggable("Analysis", 3)) {
                Log.w("Analysis", "", e2);
            }
            e2.printStackTrace();
        }
    }

    @Override // com.baidu.speech.EventManager
    public void unregisterListener(EventListener eventListener) {
    }
}
