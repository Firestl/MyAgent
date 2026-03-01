package com.baidu.speech.asr;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.utils.LogUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class EventManagerWp implements EventManager {
    public static final String TAG = "EventManagerWp";
    public static final String version = "1.1.0.20161226";
    public Exception initException;
    public Context mContext;
    public WakeUpControl mEnginer;
    public ArrayList<EventListener> listeners = new ArrayList<>();
    public Handler mHandler = new Handler(Looper.getMainLooper());

    public EventManagerWp(Context context) {
        this.initException = null;
        this.mContext = context;
        try {
            this.mEnginer = new WakeUpControl(context);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.initException = e2;
        }
    }

    public static final String getSDKVersion() {
        return version;
    }

    @Override // com.baidu.speech.EventManager
    public void registerListener(EventListener eventListener) {
        if (eventListener == null || this.listeners.contains(eventListener)) {
            return;
        }
        this.listeners.add(eventListener);
    }

    @Override // com.baidu.speech.EventManager
    public void send(String str, String str2, byte[] bArr, int i, int i2) throws Throwable {
        LogUtil.v(TAG, "send cmd : " + str + " send params : " + str2);
        AnalysisInterceptor.getInstance(this.mContext).send(str, str2, bArr, i, i2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (SpeechConstant.WAKEUP_START.equals(str) && this.initException != null) {
            for (final EventListener eventListener : this.listeners) {
                this.mHandler.post(new Runnable() { // from class: com.baidu.speech.asr.EventManagerWp.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (eventListener != null) {
                            LogUtil.v(EventManagerWp.TAG, "onEvent mCommand : wp.error and wp.exit  onEvent mParam : " + EventManagerWp.this.initException.getMessage());
                            eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_ERROR, EventManagerWp.this.initException.getMessage(), null, 0, 0);
                            eventListener.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED, EventManagerWp.this.initException.getMessage(), null, 0, 0);
                        }
                    }
                });
            }
        }
        WakeUpControl wakeUpControl = this.mEnginer;
        if (wakeUpControl == null || str == null) {
            return;
        }
        wakeUpControl.setListener(new EventListener() { // from class: com.baidu.speech.asr.EventManagerWp.2
            @Override // com.baidu.speech.EventListener
            public void onEvent(final String str3, final String str4, final byte[] bArr2, final int i3, final int i4) {
                synchronized (EventManagerWp.this.listeners) {
                    for (final EventListener eventListener2 : EventManagerWp.this.listeners) {
                        EventManagerWp.this.mHandler.post(new Runnable() { // from class: com.baidu.speech.asr.EventManagerWp.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (eventListener2 != null) {
                                    LogUtil.v(EventManagerWp.TAG, "onEvent mCommand : " + str3 + " onEvent mParam : " + str4);
                                    eventListener2.onEvent(str3, str4, bArr2, i3, i4);
                                    AnalysisInterceptor.getInstance(EventManagerWp.this.mContext).onEvent(str3, str4, bArr2, i3, i4, false);
                                }
                            }
                        });
                    }
                }
            }
        });
        this.mEnginer.postEvent(str, str2);
    }

    @Override // com.baidu.speech.EventManager
    public void unregisterListener(EventListener eventListener) {
        this.listeners.remove(eventListener);
    }
}
