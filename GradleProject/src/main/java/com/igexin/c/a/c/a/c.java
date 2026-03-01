package com.igexin.c.a.c.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.igexin.push.core.ServiceManager;
import com.igexin.sdk.IUserLoggerInterface;
import com.igexin.sdk.PushManager;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends Handler implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3185a = "SERVER_LOG";
    public static final String c = "LogController";
    public b b;
    public Messenger d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Messenger f3186e;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f3187a = new c(0);
    }

    public c() {
        super(Looper.getMainLooper());
        this.b = new com.igexin.c.a.c.a.a();
    }

    public /* synthetic */ c(byte b) {
        this();
    }

    public static c a() {
        return a.f3187a;
    }

    private void a(Context context, IUserLoggerInterface iUserLoggerInterface) {
        if (iUserLoggerInterface == null) {
            e.a(c, "user logger register parameter can not be null!");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        a(applicationContext);
        this.b.a(iUserLoggerInterface);
        this.b.a();
        a("[LogController] Sdk version = " + PushManager.getInstance().getVersion(applicationContext));
    }

    public final void a(Context context) {
        com.igexin.c.a.c.a.a("try to bind log server", new Object[0]);
        try {
            Intent intent = new Intent(context, (Class<?>) ServiceManager.getInstance().b(context));
            intent.setType(f3185a);
            context.bindService(intent, this, 1);
        } catch (Exception e2) {
            e.a(c, "bind service error = " + e2.toString());
        }
    }

    public final void a(String str) {
        b bVar = this.b;
        if (bVar != null) {
            bVar.a(str);
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what == 2) {
            String string = message.getData().getString(d.d);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (!string.contains("\n")) {
                this.b.a(string);
                return;
            }
            for (String str : string.split("\n")) {
                this.b.a(str);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.igexin.c.a.c.a.a("remote log service connected ", new Object[0]);
        try {
            this.f3186e = new Messenger(iBinder);
            if (this.d == null) {
                this.d = new Messenger(this);
            }
            Message messageObtain = Message.obtain();
            messageObtain.replyTo = this.d;
            messageObtain.what = 1;
            this.f3186e.send(messageObtain);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(c, "Client sent Message to Service error = ".concat(String.valueOf(e2)));
            a("Client sent Message to Service error = ".concat(String.valueOf(e2)));
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f3186e = null;
    }
}
