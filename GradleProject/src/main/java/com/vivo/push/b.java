package com.vivo.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.vivo.push.util.s;
import com.vivo.push.util.z;
import com.vivo.vms.IPCInvoke;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: IPCManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f5557a = new Object();
    public static Map<String, b> b = new HashMap();
    public boolean c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f5558e;
    public volatile IPCInvoke g;
    public String i;
    public Handler j;
    public Object h = new Object();
    public AtomicInteger f = new AtomicInteger(1);

    public b(Context context, String str) {
        this.d = null;
        this.j = null;
        this.f5558e = context;
        this.i = str;
        this.j = new Handler(Looper.getMainLooper(), new c(this));
        String strB = s.b(context);
        this.d = strB;
        if (!TextUtils.isEmpty(strB) && !TextUtils.isEmpty(this.i)) {
            this.c = z.a(context, this.d) >= 1260;
            b();
            return;
        }
        com.vivo.push.util.o.c(this.f5558e, "init error : push pkgname is " + this.d + " ; action is " + this.i);
        this.c = false;
    }

    private void d() {
        this.j.removeMessages(1);
        this.j.sendEmptyMessageDelayed(1, 3000L);
    }

    private void e() {
        this.j.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            this.f5558e.unbindService(this);
        } catch (Exception e2) {
            com.vivo.push.util.o.a("AidlManager", "On unBindServiceException:" + e2.getMessage());
        }
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        com.vivo.push.util.o.b("AidlManager", "onBindingDied : ".concat(String.valueOf(componentName)));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        e();
        this.g = IPCInvoke.Stub.asInterface(iBinder);
        if (this.g == null) {
            com.vivo.push.util.o.d("AidlManager", "onServiceConnected error : aidl must not be null.");
            f();
            this.f.set(1);
            return;
        }
        if (this.f.get() == 2) {
            a(4);
        } else if (this.f.get() != 4) {
            f();
        }
        synchronized (this.h) {
            this.h.notifyAll();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.g = null;
        a(1);
    }

    public static b a(Context context, String str) {
        b bVar = b.get(str);
        if (bVar == null) {
            synchronized (f5557a) {
                bVar = b.get(str);
                if (bVar == null) {
                    bVar = new b(context, str);
                    b.put(str, bVar);
                }
            }
        }
        return bVar;
    }

    private void b() {
        int i = this.f.get();
        com.vivo.push.util.o.d("AidlManager", "Enter connect, Connection Status: ".concat(String.valueOf(i)));
        if (i == 4 || i == 2 || i == 3 || i == 5 || !this.c) {
            return;
        }
        a(2);
        if (c()) {
            d();
        } else {
            a(1);
            com.vivo.push.util.o.a("AidlManager", "bind core service fail");
        }
    }

    private boolean c() {
        Intent intent = new Intent(this.i);
        intent.setPackage(this.d);
        try {
            return this.f5558e.bindService(intent, this, 1);
        } catch (Exception e2) {
            com.vivo.push.util.o.a("AidlManager", "bind core error", e2);
            return false;
        }
    }

    public final boolean a() {
        String strB = s.b(this.f5558e);
        this.d = strB;
        if (TextUtils.isEmpty(strB)) {
            com.vivo.push.util.o.c(this.f5558e, "push pkgname is null");
            return false;
        }
        boolean z = z.a(this.f5558e, this.d) >= 1260;
        this.c = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.f.set(i);
    }

    public final boolean a(Bundle bundle) {
        b();
        if (this.f.get() == 2) {
            synchronized (this.h) {
                try {
                    this.h.wait(2000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            int i = this.f.get();
            if (i == 4) {
                this.j.removeMessages(2);
                this.j.sendEmptyMessageDelayed(2, com.igexin.push.config.c.k);
                this.g.asyncCall(bundle, null);
                return true;
            }
            com.vivo.push.util.o.d("AidlManager", "invoke error : connect status = ".concat(String.valueOf(i)));
            return false;
        } catch (Exception e3) {
            com.vivo.push.util.o.a("AidlManager", "invoke error ", e3);
            int i2 = this.f.get();
            com.vivo.push.util.o.d("AidlManager", "Enter disconnect, Connection Status: ".concat(String.valueOf(i2)));
            if (i2 == 2) {
                e();
                a(1);
                return false;
            }
            if (i2 == 3) {
                a(1);
                return false;
            }
            if (i2 != 4) {
                return false;
            }
            a(1);
            f();
            return false;
        }
    }
}
