package com.igexin.c.a.c.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.igexin.push.core.ServiceManager;

/* JADX INFO: loaded from: classes2.dex */
public class d extends Handler {
    public static final int b = 1;
    public static final int c = 2;
    public static final String d = "log_data";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3188e = d.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Messenger f3189a;
    public final StringBuffer f;
    public Messenger g;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f3190a = new d(0);
    }

    public d() {
        super(Looper.getMainLooper());
        this.f3189a = new Messenger(this);
        this.f = new StringBuffer();
    }

    public /* synthetic */ d(byte b2) {
        this();
    }

    public static d a() {
        return a.f3190a;
    }

    private void a(Message message) {
        this.g = message.replyTo;
        try {
            if (this.f.length() > 0) {
                b(this.f.toString());
                this.f.setLength(0);
                this.f.trimToSize();
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    private void b(String str) {
        try {
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            Bundle bundle = new Bundle();
            bundle.putString(d, str);
            messageObtain.setData(bundle);
            this.g.send(messageObtain);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    private boolean b() {
        return this.f.length() > 0;
    }

    private IBinder c() {
        return this.f3189a.getBinder();
    }

    public final void a(String str) {
        if (com.igexin.push.g.c.a(ServiceManager.b)) {
            if (this.g != null) {
                b(str);
                return;
            }
            if (this.f.length() + str.length() < 2560) {
                StringBuffer stringBuffer = this.f;
                stringBuffer.append(str);
                stringBuffer.append("\n");
            } else {
                if (this.f.length() > 2560 || this.f.length() + 135 <= 2560) {
                    return;
                }
                StringBuffer stringBuffer2 = this.f;
                stringBuffer2.append("Warning! the log cache is too long to show the full content,we suggest you call initialize and setDebugLogger in a short time interval.");
                stringBuffer2.append("\n");
            }
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        boolean z = true;
        if (message.what != 1) {
            super.handleMessage(message);
            return;
        }
        this.g = message.replyTo;
        try {
            if (this.f.length() <= 0) {
                z = false;
            }
            if (z) {
                b(this.f.toString());
                this.f.setLength(0);
                this.f.trimToSize();
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }
}
