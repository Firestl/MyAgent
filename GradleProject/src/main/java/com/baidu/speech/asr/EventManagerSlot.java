package com.baidu.speech.asr;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class EventManagerSlot implements EventManager {
    public Context mContext;
    public SlotControl mSlotControl;
    public ArrayList<EventListener> listeners = new ArrayList<>();
    public Handler mHandler = new Handler(Looper.getMainLooper());

    public EventManagerSlot(Context context) {
        this.mContext = context;
        try {
            this.mSlotControl = new SlotControl(this.mContext);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.baidu.speech.EventManager
    public void registerListener(EventListener eventListener) {
        if (eventListener != null) {
            this.listeners.add(eventListener);
        }
    }

    @Override // com.baidu.speech.EventManager
    public void send(String str, String str2, byte[] bArr, int i, int i2) {
        SlotControl slotControl;
        if (TextUtils.isEmpty(str) || (slotControl = this.mSlotControl) == null) {
            return;
        }
        slotControl.setListener(new EventListener() { // from class: com.baidu.speech.asr.EventManagerSlot.1
            @Override // com.baidu.speech.EventListener
            public void onEvent(final String str3, final String str4, final byte[] bArr2, final int i3, final int i4) {
                synchronized (EventManagerSlot.this.listeners) {
                    for (final EventListener eventListener : EventManagerSlot.this.listeners) {
                        EventManagerSlot.this.mHandler.post(new Runnable() { // from class: com.baidu.speech.asr.EventManagerSlot.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                EventListener eventListener2 = eventListener;
                                if (eventListener2 != null) {
                                    eventListener2.onEvent(str3, str4, bArr2, i3, i4);
                                }
                            }
                        });
                    }
                }
            }
        });
        this.mSlotControl.postEvent(str, str2);
    }

    @Override // com.baidu.speech.EventManager
    public void unregisterListener(EventListener eventListener) {
        this.listeners.remove(eventListener);
    }
}
