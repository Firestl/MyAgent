package com.baidu.speech;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.baidu.speech.aidl.EventListener;
import com.baidu.speech.aidl.EventManagerFactory;
import com.baidu.speech.aidl.EventRecognitionService;
import com.baidu.speech.asr.EventManagerAsr;
import com.baidu.speech.asr.EventManagerSlot;
import com.baidu.speech.asr.EventManagerWp;
import com.baidu.speech.asr.SpeechConstant;
import com.baidu.speech.audio.MicrophoneServer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EventManagerFactory {
    public static final String TAG = "EventManagerFactory";
    public static boolean asrUsing = false;
    public static boolean kwsLoaded = false;
    public static boolean wpUsing = false;

    public static class EventManagerRemote2Local implements EventManager {
        public Context context;
        public EventListener mLis;
        public String name;
        public com.baidu.speech.aidl.EventManager remoteEM;
        public ExecutorService executor = Executors.newCachedThreadPool();
        public final ServiceConnection conn = new ServiceConnection() { // from class: com.baidu.speech.EventManagerFactory.EventManagerRemote2Local.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                com.baidu.speech.aidl.EventManagerFactory eventManagerFactoryAsInterface = EventManagerFactory.Stub.asInterface(iBinder);
                try {
                    if (EventManagerRemote2Local.this.remoteEM == null) {
                        EventManagerRemote2Local.this.setRemoteEM(eventManagerFactoryAsInterface.create(EventManagerRemote2Local.this.name));
                    }
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                if (EventManagerFactory.kwsLoaded && EventManagerRemote2Local.this.mLis != null) {
                    EventManagerRemote2Local.this.mLis.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED, null, null, 0, 0);
                }
                if (EventManagerFactory.asrUsing && EventManagerRemote2Local.this.mLis != null) {
                    EventManagerRemote2Local.this.mLis.onEvent(SpeechConstant.CALLBACK_EVENT_ASR_EXIT, null, null, 0, 0);
                }
                if (EventManagerFactory.wpUsing && EventManagerRemote2Local.this.mLis != null) {
                    EventManagerRemote2Local.this.mLis.onEvent(SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED, null, null, 0, 0);
                }
                EventManagerRemote2Local.this.remoteEM = null;
            }
        };

        /* JADX INFO: renamed from: com.baidu.speech.EventManagerFactory$EventManagerRemote2Local$2, reason: invalid class name */
        public class AnonymousClass2 implements Runnable {
            public final /* synthetic */ String val$cmd;
            public final /* synthetic */ byte[] val$data;
            public final /* synthetic */ int val$length;
            public final /* synthetic */ int val$offset;
            public final /* synthetic */ String val$params;

            public AnonymousClass2(String str, String str2, byte[] bArr, int i, int i2) {
                this.val$params = str;
                this.val$cmd = str2;
                this.val$data = bArr;
                this.val$offset = i;
                this.val$length = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject;
                if (EventManagerRemote2Local.this.remoteEM == null) {
                    new Handler(Looper.getMainLooper()).postDelayed(this, 10L);
                    return;
                }
                String string = this.val$params;
                if (SpeechConstant.ASR_START.equals(this.val$cmd) || SpeechConstant.WAKEUP_START.equals(this.val$cmd)) {
                    try {
                        jSONObject = new JSONObject(this.val$params);
                    } catch (Exception unused) {
                        jSONObject = new JSONObject();
                    }
                    try {
                        String strOptString = jSONObject.optString(SpeechConstant.IN_FILE);
                        if (!jSONObject.has("audio.socketport") && !TextUtils.isEmpty(strOptString)) {
                            jSONObject.put("audio.socketport", MicrophoneServer.create(strOptString, jSONObject.has(SpeechConstant.AUDIO_SOURCE) ? jSONObject.optInt(SpeechConstant.AUDIO_SOURCE) : 1));
                            string = jSONObject.toString();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                String str = string;
                try {
                    EventManagerRemote2Local.this.remoteEM.registerListener(new EventListener.Stub() { // from class: com.baidu.speech.EventManagerFactory.EventManagerRemote2Local.2.1
                        @Override // com.baidu.speech.aidl.EventListener
                        public void onEvent(final String str2, final String str3, final byte[] bArr, final int i, final int i2) throws RemoteException {
                            JSONObject jSONObject2;
                            boolean zOptBoolean = false;
                            if (SpeechConstant.CALLBACK_EVENT_ASR_EXIT.equals(str2)) {
                                boolean unused2 = EventManagerFactory.asrUsing = false;
                            } else if (SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED.equals(str2)) {
                                boolean unused3 = EventManagerFactory.wpUsing = false;
                            } else if (SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED.equals(str2)) {
                                boolean unused4 = EventManagerFactory.kwsLoaded = false;
                            }
                            if (!SpeechConstant.CALLBACK_EVENT_WAKEUP_STOPED.equals(str2)) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.baidu.speech.EventManagerFactory.EventManagerRemote2Local.2.1.3
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        EventManagerRemote2Local.this.mLis.onEvent(str2, str3, bArr, i, i2);
                                    }
                                });
                                return;
                            }
                            JSONObject jSONObject3 = null;
                            try {
                                jSONObject2 = new JSONObject(str3 == null ? "{}" : str3);
                                try {
                                    zOptBoolean = jSONObject2.optBoolean("_free");
                                    jSONObject2.remove("_free");
                                } catch (JSONException e3) {
                                    e = e3;
                                    jSONObject3 = jSONObject2;
                                    e.printStackTrace();
                                    jSONObject2 = jSONObject3;
                                }
                            } catch (JSONException e4) {
                                e = e4;
                            }
                            final String string2 = jSONObject2.toString();
                            Looper mainLooper = Looper.getMainLooper();
                            if (zOptBoolean) {
                                new Handler(mainLooper).postDelayed(new Runnable() { // from class: com.baidu.speech.EventManagerFactory.EventManagerRemote2Local.2.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        EventManagerRemote2Local.this.mLis.onEvent(str2, string2, bArr, i, i2);
                                    }
                                }, 200L);
                            } else {
                                new Handler(mainLooper).post(new Runnable() { // from class: com.baidu.speech.EventManagerFactory.EventManagerRemote2Local.2.1.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        EventManagerRemote2Local.this.mLis.onEvent(str2, string2, bArr, i, i2);
                                    }
                                });
                            }
                        }
                    });
                    EventManagerRemote2Local.this.remoteEM.send(this.val$cmd, str, this.val$data, this.val$offset, this.val$length);
                } catch (RemoteException e3) {
                    e3.printStackTrace();
                    EventManagerRemote2Local.this.remoteEM = null;
                }
            }
        }

        public EventManagerRemote2Local(Context context, String str) {
            this.context = context;
            this.name = str;
        }

        @Override // com.baidu.speech.EventManager
        public void registerListener(EventListener eventListener) {
            this.mLis = eventListener;
        }

        @Override // com.baidu.speech.EventManager
        public void send(String str, String str2, byte[] bArr, int i, int i2) {
            this.context.bindService(new Intent(this.context, (Class<?>) EventRecognitionService.class), this.conn, 1);
            byte[] bArr2 = bArr == null ? new byte[0] : bArr;
            if (SpeechConstant.ASR_START.equals(str) || SpeechConstant.ASR_KWS_LOAD_ENGINE.equals(str)) {
                boolean unused = EventManagerFactory.asrUsing = true;
            } else if (SpeechConstant.WAKEUP_START.equals(str)) {
                boolean unused2 = EventManagerFactory.wpUsing = true;
            } else if (SpeechConstant.ASR_KWS_LOAD_ENGINE.equals(str)) {
                boolean unused3 = EventManagerFactory.kwsLoaded = true;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new AnonymousClass2(str2, str, bArr2, i, i2), 0L);
        }

        public void setRemoteEM(com.baidu.speech.aidl.EventManager eventManager) {
            this.remoteEM = eventManager;
        }

        @Override // com.baidu.speech.EventManager
        public void unregisterListener(EventListener eventListener) {
            this.mLis = null;
        }
    }

    public static final EventManager create(Context context, String str) {
        return create(context, str, false);
    }

    public static final EventManager create(Context context, String str, boolean z) {
        if (context != null && str != null && !str.equals("")) {
            Context applicationContext = context.getApplicationContext();
            if (z) {
                return new EventManagerRemote2Local(applicationContext, str);
            }
            if (str.equals("asr")) {
                return new EventManagerAsr(applicationContext);
            }
            if (str.equals("wp")) {
                return new EventManagerWp(applicationContext);
            }
            if (str.equals("slot")) {
                return new EventManagerSlot(applicationContext);
            }
        }
        return null;
    }
}
