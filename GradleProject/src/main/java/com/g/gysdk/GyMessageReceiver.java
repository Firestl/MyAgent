package com.g.gysdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.g.gysdk.a.aj;
import com.g.gysdk.a.am;
import com.g.gysdk.a.d;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class GyMessageReceiver extends BroadcastReceiver {
    public static final int EVENT_onInit = 1;

    public static final String getBroadcastPermission(Context context) {
        return context.getPackageName() + ".permission.GYRECEIVER";
    }

    public static final void sendMessage(int i, GYResponse gYResponse) {
        Context context = d.b;
        if (context == null) {
            aj.c("GyMessageReceiver.sendMessage context == null");
            return;
        }
        try {
            Intent intent = new Intent("com.getui.gy.action." + d.f2000e);
            intent.putExtra("event", i);
            intent.putExtra("response", gYResponse);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, getBroadcastPermission(context));
            aj.a("GyMessageReceiver.sendMessage success");
        } catch (Exception e2) {
            aj.a("GyMessageReceiver.sendMessage exception:", e2);
        }
    }

    public void onError(Context context, GYResponse gYResponse) {
    }

    public void onGyUidReceived(Context context, String str) {
    }

    public void onInit(Context context, boolean z) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String str = intent.getPackage();
            if (TextUtils.isEmpty(str) || !TextUtils.equals(str, context.getPackageName())) {
                return;
            }
            if (("com.getui.gy.action." + d.f2000e).equals(intent.getAction())) {
                GYResponse gYResponse = (GYResponse) intent.getSerializableExtra("response");
                if (intent.getIntExtra("event", 0) != 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onReceive unknown event:");
                    sb.append(gYResponse != null ? gYResponse.toString() : com.igexin.push.core.b.m);
                    aj.c(sb.toString());
                    return;
                }
                onInit(context, gYResponse.isSuccess());
                if (gYResponse.isSuccess()) {
                    onGyUidReceived(context, gYResponse.getGyuid());
                } else {
                    onError(context, gYResponse);
                }
            }
        } catch (Exception e2) {
            aj.a("onReceive exception:", e2);
            onError(context, new b(GyCode.UNKNOWN_ERROR, GyErrorCode.UNKNOWN_ERROR, am.a(e2)).c());
        }
    }
}
