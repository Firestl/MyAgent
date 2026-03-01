package com.baidu.aip.core.recog;

import android.content.Context;
import com.baidu.aip.core.recog.listener.IRecogListener;
import com.baidu.aip.core.recog.listener.RecogEventAdapter;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.feature.R;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MyRecognizer {
    public static final String TAG = "MyRecognizer";
    public static volatile boolean isInited = false;
    public static boolean isOfflineEngineLoaded = false;
    public EventManager asr;
    public EventListener eventListener;

    public MyRecognizer(Context context, IRecogListener iRecogListener) {
        this(context, new RecogEventAdapter(iRecogListener));
    }

    public void cancel() {
        if (!isInited) {
            throw new RuntimeException("release() was called");
        }
        this.asr.send("asr.cancel", "{}", null, 0, 0);
    }

    public void loadOfflineEngine(Map<String, Object> map) {
        this.asr.send(SpeechConstant.ASR_KWS_LOAD_ENGINE, new JSONObject(map).toString(), null, 0, 0);
        isOfflineEngineLoaded = true;
    }

    public void release() {
        if (this.asr == null) {
            return;
        }
        cancel();
        if (isOfflineEngineLoaded) {
            this.asr.send(SpeechConstant.ASR_KWS_UNLOAD_ENGINE, null, null, 0, 0);
            isOfflineEngineLoaded = false;
        }
        this.asr.unregisterListener(this.eventListener);
        this.asr = null;
        isInited = false;
    }

    public void setEventListener(IRecogListener iRecogListener) {
        if (!isInited) {
            throw new RuntimeException("release() was called");
        }
        RecogEventAdapter recogEventAdapter = new RecogEventAdapter(iRecogListener);
        this.eventListener = recogEventAdapter;
        this.asr.registerListener(recogEventAdapter);
    }

    public void start(Map<String, Object> map) {
        this.asr.send(SpeechConstant.ASR_START, new JSONObject(map).toString(), null, 0, 0);
    }

    public void stop() {
        if (!isInited) {
            throw new RuntimeException("release() was called");
        }
        this.asr.send(SpeechConstant.ASR_STOP, "{}", null, 0, 0);
    }

    public MyRecognizer(Context context, EventListener eventListener) {
        if (isInited) {
            throw new RuntimeException(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_speech_baidu_exception_not_call_release));
        }
        isInited = true;
        this.eventListener = eventListener;
        EventManager eventManagerCreate = EventManagerFactory.create(context, "asr");
        this.asr = eventManagerCreate;
        eventManagerCreate.registerListener(eventListener);
    }
}
