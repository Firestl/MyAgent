package com.baidu.speech.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.speech.aidl.EventManager;
import com.baidu.speech.aidl.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EventRecognitionService extends Service {
    public static final String TAG = "EventRecognitionService";
    public long startTime = 0;
    public boolean asrUsing = false;
    public boolean wpUsing = false;
    public int RESTART_TIME = 3600000;
    public IBinder iBinder = new EventManagerFactory.Stub() { // from class: com.baidu.speech.aidl.EventRecognitionService.1
        @Override // com.baidu.speech.aidl.EventManagerFactory
        public EventManager create(final String str) throws RemoteException {
            EventManager.Stub stub = new EventManager.Stub() { // from class: com.baidu.speech.aidl.EventRecognitionService.1.1
                public com.baidu.speech.EventManager impl;
                public ArrayList<EventListenerProxy> usingLis = new ArrayList<>();

                {
                    this.impl = com.baidu.speech.EventManagerFactory.create(EventRecognitionService.this.getApplicationContext(), str, false);
                }

                @Override // com.baidu.speech.aidl.EventManager
                public void registerListener(EventListener eventListener) throws RemoteException {
                    EventListenerProxy eventListenerProxy = EventRecognitionService.this.new EventListenerProxy(eventListener);
                    unregisterListener(eventListener);
                    this.usingLis.add(eventListenerProxy);
                    this.impl.registerListener(eventListenerProxy);
                }

                @Override // com.baidu.speech.aidl.EventManager
                public void send(String str2, String str3, byte[] bArr, int i, int i2) throws RemoteException {
                    if (SpeechConstant.ASR_START.equals(str2) || SpeechConstant.ASR_KWS_LOAD_ENGINE.equals(str2)) {
                        EventRecognitionService.this.asrUsing = true;
                    } else if (SpeechConstant.WAKEUP_START.equals(str2)) {
                        EventRecognitionService.this.wpUsing = true;
                    }
                    this.impl.send(str2, str3, bArr, i, i2);
                }

                @Override // com.baidu.speech.aidl.EventManager
                public void unregisterListener(EventListener eventListener) throws RemoteException {
                    Iterator<EventListenerProxy> it = this.usingLis.iterator();
                    while (it.hasNext()) {
                        this.impl.unregisterListener(it.next());
                    }
                    this.usingLis.clear();
                }
            };
            EventRecognitionService.this.startTime = System.currentTimeMillis();
            return stub;
        }
    };

    public class EventListenerProxy implements com.baidu.speech.EventListener {
        public EventListener listener;

        public EventListenerProxy(EventListener eventListener) {
            this.listener = eventListener;
        }

        @Override // com.baidu.speech.EventListener
        public void onEvent(String str, String str2, byte[] bArr, int i, int i2) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis() - EventRecognitionService.this.startTime;
                if (bArr == null) {
                    bArr = new byte[0];
                }
                if (SpeechConstant.CALLBACK_EVENT_ASR_EXIT.equals(str)) {
                    EventRecognitionService.this.asrUsing = false;
                } else if (SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED.equals(str)) {
                    EventRecognitionService.this.wpUsing = false;
                }
                if (EventRecognitionService.this.asrUsing || EventRecognitionService.this.wpUsing) {
                    this.listener.onEvent(str, str2, bArr, i, i2);
                    return;
                }
                boolean z = jCurrentTimeMillis > ((long) EventRecognitionService.this.RESTART_TIME);
                try {
                    JSONObject jSONObject = new JSONObject(str2 == null ? "{}" : str2);
                    jSONObject.put("_free", z);
                    str2 = jSONObject.toString();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.listener.onEvent(str, str2, bArr, i, i2);
                if (z) {
                    Log.d(EventRecognitionService.TAG, "wake up idle, exit!");
                    System.exit(0);
                }
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        }
    }

    public EventRecognitionService() {
        new Handler().postDelayed(new Runnable() { // from class: com.baidu.speech.aidl.EventRecognitionService.2
            @Override // java.lang.Runnable
            public void run() {
            }
        }, 5000L);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.iBinder;
    }
}
