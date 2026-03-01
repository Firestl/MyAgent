package com.android.dingtalk.share.ddsharemodule.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import supwisdom.tr;

/* JADX INFO: loaded from: classes.dex */
public final class DDMessage$Receiver extends BroadcastReceiver {
    public static final Map<String, tr> b = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final tr f1654a;

    public DDMessage$Receiver() {
        this(null);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Log.d("DDMessage", "receive intent=" + intent);
        tr trVar = this.f1654a;
        if (trVar != null) {
            trVar.a(intent);
            Log.d("DDMessage", "mm message self-handled");
            return;
        }
        tr trVar2 = b.get(intent.getAction());
        if (trVar2 != null) {
            trVar2.a(intent);
            Log.d("DDMessage", "mm message handled");
        }
    }

    public DDMessage$Receiver(tr trVar) {
        this.f1654a = trVar;
    }
}
