package com.igexin.push.c;

import android.annotation.SuppressLint;
import android.os.Build;
import com.igexin.push.c.b;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3261e = "DT_DetectRunTask";
    public static final long f = 60;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Future<d> f3262a;
    public d b;
    public i c;
    public boolean d;

    /* JADX INFO: renamed from: com.igexin.push.c.e$1, reason: invalid class name */
    public class AnonymousClass1 implements Callable<d> {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        @SuppressLint({"NewApi"})
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d call() throws Throwable {
            Thread.currentThread().hashCode();
            com.igexin.b.a.a().f3132a.getActiveCount();
            if (!Thread.currentThread().isInterrupted()) {
                Socket socket = null;
                try {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        com.igexin.c.a.c.a.a(e.f3261e, Thread.currentThread().getName() + " is interrupted ######");
                        Thread.currentThread().hashCode();
                        com.igexin.b.a.a().f3132a.getActiveCount();
                        return null;
                    }
                    synchronized (i.class) {
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    String[] strArrA = com.igexin.c.a.b.g.a(e.this.b.a());
                    Socket socket2 = new Socket();
                    try {
                        socket2.connect(new InetSocketAddress(strArrA[1], e.this.b.b), b.b);
                        long jCurrentTimeMillis2 = System.currentTimeMillis();
                        String strA = e.a(socket2.getInetAddress());
                        e.this.b.a("socket://" + strA + Constants.COLON_SEPARATOR + e.this.b.b, jCurrentTimeMillis2 - jCurrentTimeMillis, jCurrentTimeMillis2);
                        e.this.c();
                        com.igexin.c.a.c.a.a("DT_DetectRunTask|detect " + e.this.c() + "|time = " + e.this.b.c(), new Object[0]);
                        synchronized (i.class) {
                            if (e.this.c != null && !Thread.currentThread().isInterrupted()) {
                                e.this.c.a(b.a.f3256a, e.this.b);
                            }
                            if (!socket2.isClosed()) {
                                try {
                                    socket2.close();
                                } catch (Exception e3) {
                                    e = e3;
                                    com.igexin.c.a.c.a.a(e);
                                }
                            }
                        }
                    } catch (Exception e4) {
                        e = e4;
                        socket = socket2;
                        com.igexin.c.a.c.a.b(e.f3261e, "|detect " + e.this.c() + "thread -->" + e.getMessage());
                        synchronized (i.class) {
                            if (e.this.c != null) {
                                e.this.b.b();
                                e.this.c.a(b.a.c, e.this.b);
                            }
                            if (socket != null && !socket.isClosed()) {
                                try {
                                    socket.close();
                                } catch (Exception e5) {
                                    e = e5;
                                    com.igexin.c.a.c.a.a(e);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        socket = socket2;
                        if (socket != null && !socket.isClosed()) {
                            try {
                                socket.close();
                            } catch (Exception e6) {
                                com.igexin.c.a.c.a.a(e6);
                            }
                        }
                        Thread.currentThread().hashCode();
                        com.igexin.b.a.a().f3132a.getActiveCount();
                        throw th;
                    }
                    Thread.currentThread().hashCode();
                    com.igexin.b.a.a().f3132a.getActiveCount();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            return e.this.b;
        }
    }

    public static String a(InetAddress inetAddress) throws NoSuchMethodException {
        Class<?> cls;
        try {
            cls = Class.forName("java.net.InetAddress");
        } catch (Throwable unused) {
        }
        if (Build.VERSION.SDK_INT >= 23) {
            Method declaredMethod = cls.getDeclaredMethod("holder", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(inetAddress, new Object[0]);
            Field declaredField = objInvoke.getClass().getDeclaredField("address");
            declaredField.setAccessible(true);
            int iIntValue = ((Integer) declaredField.get(objInvoke)).intValue();
            String str = ((iIntValue >>> 24) & 255) + Operators.DOT_STR + ((iIntValue >>> 16) & 255) + Operators.DOT_STR + ((iIntValue >>> 8) & 255) + Operators.DOT_STR + (iIntValue & 255);
            com.igexin.c.a.c.a.b(f3261e, "i new Str: ".concat(String.valueOf(str)));
            return str;
        }
        Field declaredField2 = cls.getDeclaredField("ipaddress");
        declaredField2.setAccessible(true);
        byte[] bArr = (byte[]) declaredField2.get(inetAddress);
        if (bArr.length >= 4) {
            String str2 = (bArr[3] & 255) + Operators.DOT_STR + (bArr[2] & 255) + Operators.DOT_STR + (bArr[1] & 255) + Operators.DOT_STR + (bArr[0] & 255);
            com.igexin.c.a.c.a.b(f3261e, "i old Str: ".concat(String.valueOf(str2)));
            return str2;
        }
        if (!com.igexin.push.config.d.ah) {
            throw new NoSuchMethodException("can't get ad by new method");
        }
        com.igexin.c.a.c.a.b(f3261e, "get ad by original method");
        return inetAddress.getHostAddress();
    }

    private void a(d dVar) {
        this.b = dVar;
    }

    private void a(boolean z) {
        this.d = z;
    }

    private d d() {
        return this.b;
    }

    private void e() {
        synchronized (i.class) {
            if (this.c != null) {
                this.f3262a = com.igexin.b.a.a().f3132a.submit(new AnonymousClass1());
            }
        }
    }

    private void f() {
        this.f3262a = com.igexin.b.a.a().f3132a.submit(new AnonymousClass1());
    }

    private void g() {
        try {
            if (this.f3262a == null || this.f3262a.isCancelled() || this.f3262a.isDone()) {
                return;
            }
            this.f3262a.cancel(true);
            this.f3262a = null;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    public final void a() {
        c();
        com.igexin.c.a.c.a.a("DT_DetectRunTask|stop " + c() + " task", new Object[0]);
        g();
    }

    public final void a(i iVar) {
        synchronized (i.class) {
            this.c = iVar;
        }
    }

    public final void b() {
        a((i) null);
        g();
    }

    public final String c() {
        return this.b.a() + "|" + this.b.f3259a;
    }
}
