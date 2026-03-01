package com.baidu.speech.asr;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.core.ASREngine;
import com.baidu.speech.utils.LogUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class EventManagerAsr implements EventManager, ASRListener {
    public static final String TAG = "EventManagerAsr";
    public static final String version = "3.4.0.200";
    public Exception initException;
    public ASREngine mEnginer;
    public Context mcontext;
    public ArrayList<EventListener> listeners = new ArrayList<>();
    public Handler mHandler = new Handler(Looper.getMainLooper());
    public ArrayList<ASRMessage> mMessageQueue = new ArrayList<>();

    public EventManagerAsr(Context context) {
        this.initException = null;
        this.mcontext = context;
        try {
            this.mEnginer = new ASREngine(context);
            this.mMessageQueue.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
            this.initException = e2;
        }
    }

    public static final String getSDKVersion() {
        return version;
    }

    private void updateMessageQueue(ASRMessage aSRMessage) {
        synchronized (this.mMessageQueue) {
            if (aSRMessage.mIsVip) {
                this.mMessageQueue.clear();
            }
            this.mMessageQueue.add(aSRMessage);
        }
    }

    @Override // com.baidu.speech.asr.ASRListener
    public void onEvent(String str, String str2, byte[] bArr, int i, int i2) {
        onEvent(str, str2, bArr, i, i2, false);
    }

    @Override // com.baidu.speech.asr.ASRListener
    public void onEvent(String str, String str2, byte[] bArr, int i, int i2, boolean z) {
        AnalysisInterceptor.getInstance(this.mcontext).onEvent(str, str2, bArr, i, i2, z);
        updateMessageQueue(new ASRMessage(str, str2, bArr, i, i2, z));
        synchronized (this.listeners) {
            synchronized (this.mMessageQueue) {
                if (this.mMessageQueue.size() <= 0) {
                    return;
                }
                final ASRMessage aSRMessageRemove = this.mMessageQueue.remove(0);
                if (aSRMessageRemove != null) {
                    for (final EventListener eventListener : this.listeners) {
                        this.mHandler.post(new Runnable() { // from class: com.baidu.speech.asr.EventManagerAsr.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (eventListener != null) {
                                    LogUtil.v(EventManagerAsr.TAG, "onEvent mCommand : " + aSRMessageRemove.mCommand + " onEvent mParam : " + aSRMessageRemove.mParam);
                                    EventListener eventListener2 = eventListener;
                                    ASRMessage aSRMessage = aSRMessageRemove;
                                    eventListener2.onEvent(aSRMessage.mCommand, aSRMessage.mParam, aSRMessage.mData, aSRMessage.mOffset, aSRMessage.mLength);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    @Override // com.baidu.speech.EventManager
    public void registerListener(EventListener eventListener) {
        if (eventListener == null || this.listeners.contains(eventListener)) {
            return;
        }
        this.listeners.add(eventListener);
    }

    @Override // com.baidu.speech.EventManager
    public void send(String str, String str2, byte[] bArr, int i, int i2) {
        LogUtil.v(TAG, "send cmd : " + str + " send params : " + str2);
        AnalysisInterceptor.getInstance(this.mcontext).send(str, str2, bArr, i, i2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.equals(SpeechConstant.ASR_START) && this.initException != null) {
            for (final EventListener eventListener : this.listeners) {
                this.mHandler.post(new Runnable() { // from class: com.baidu.speech.asr.EventManagerAsr.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (eventListener != null) {
                            LogUtil.v(EventManagerAsr.TAG, "onEvent mCommand : asr.finish and asr.exit  onEvent mParam : " + EventManagerAsr.this.initException.getMessage());
                            eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_FINISH, EventManagerAsr.this.initException.getMessage(), null, 0, 0);
                            eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_EXIT, EventManagerAsr.this.initException.getMessage(), null, 0, 0);
                        }
                    }
                });
            }
        }
        ASREngine aSREngine = this.mEnginer;
        if (aSREngine != null) {
            aSREngine.setListener(this);
            this.mEnginer.postEvent(str, str2);
        }
    }

    @Override // com.baidu.speech.EventManager
    public void unregisterListener(EventListener eventListener) {
        this.listeners.remove(eventListener);
    }
}
