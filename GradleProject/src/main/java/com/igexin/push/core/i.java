package com.igexin.push.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import com.igexin.push.core.d;

/* JADX INFO: loaded from: classes2.dex */
public class i extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3463a = "GTReceiver";
    public static volatile i b;

    public static i a() {
        if (b == null) {
            synchronized (i.class) {
                if (b == null) {
                    b = new i();
                }
            }
        }
        return b;
    }

    public static void a(Intent intent) {
        try {
            intent.getAction();
            intent.getComponent();
            com.igexin.c.a.c.a.a("----------------------------------------------------------------------------------", new Object[0]);
            com.igexin.c.a.c.a.a("GTReceiver|action = " + intent.getAction() + ", component = " + intent.getComponent(), new Object[0]);
            Bundle extras = intent.getExtras();
            if (extras == null) {
                com.igexin.c.a.c.a.a("GTReceiver|no extras", new Object[0]);
                return;
            }
            for (String str : extras.keySet()) {
                extras.get(str);
                com.igexin.c.a.c.a.a("GTReceiver|key [" + str + "]: " + extras.get(str), new Object[0]);
            }
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            try {
                intent.getAction();
                intent.getComponent();
                com.igexin.c.a.c.a.a("----------------------------------------------------------------------------------", new Object[0]);
                com.igexin.c.a.c.a.a("GTReceiver|action = " + intent.getAction() + ", component = " + intent.getComponent(), new Object[0]);
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String str : extras.keySet()) {
                        extras.get(str);
                        com.igexin.c.a.c.a.a("GTReceiver|key [" + str + "]: " + extras.get(str), new Object[0]);
                    }
                } else {
                    com.igexin.c.a.c.a.a("GTReceiver|no extras", new Object[0]);
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
        StringBuilder sb = new StringBuilder("GTReceiver InternalPublicReceiver:");
        sb.append(intent != null ? intent.getAction() : b.m);
        com.igexin.c.a.c.a.a(sb.toString(), new Object[0]);
        if (intent != null) {
            intent.getAction();
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = "com.igexin.action.notification.click".equals(intent.getAction()) ? b.Q : b.R;
        messageObtain.obj = intent;
        d.a.f3384a.a(messageObtain);
    }
}
