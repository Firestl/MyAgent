package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: OfflineLocManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class ba {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3668a = 1000;
    public static boolean b = false;
    public static int c = 20;
    public static int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static WeakReference<ax> f3669e = null;
    public static int f = 10;

    /* JADX INFO: compiled from: OfflineLocManager.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3670a;
        public Context b;
        public az c;

        public a(Context context, int i) {
            this.b = context;
            this.f3670a = i;
        }

        public a(Context context, az azVar) {
            this(context, 1);
            this.c = azVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.f3670a;
            if (i == 1) {
                try {
                    synchronized (ba.class) {
                        String string = Long.toString(System.currentTimeMillis());
                        ax axVarA = bd.a(ba.f3669e);
                        bd.a(this.b, axVarA, z.i, ba.f3668a, PKIFailureInfo.badSenderNonce, Constants.VIA_SHARE_TYPE_INFO);
                        if (axVarA.f3665e == null) {
                            axVarA.f3665e = new aj(new al(new an(new al())));
                        }
                        ay.a(string, this.c.a(), axVarA);
                    }
                    return;
                } catch (Throwable th) {
                    ab.b(th, "ofm", "aple");
                    return;
                }
            }
            if (i == 2) {
                try {
                    ax axVarA2 = bd.a(ba.f3669e);
                    bd.a(this.b, axVarA2, z.i, ba.f3668a, PKIFailureInfo.badSenderNonce, Constants.VIA_SHARE_TYPE_INFO);
                    axVarA2.h = 14400000;
                    if (axVarA2.g == null) {
                        axVarA2.g = new bh(new bg(this.b, new bl(), new aj(new al(new an())), new String(v.a(10)), k.f(this.b), n.x(this.b), n.m(this.b), n.h(this.b), n.a(), Build.MANUFACTURER, Build.DEVICE, n.A(this.b), k.c(this.b), Build.MODEL, k.d(this.b), k.b(this.b)));
                    }
                    if (TextUtils.isEmpty(axVarA2.i)) {
                        axVarA2.i = "fKey";
                    }
                    axVarA2.f = new bp(this.b, axVarA2.h, axVarA2.i, new bn(this.b, ba.b, ba.f * 1024, ba.c * 1024, "offLocKey", ba.d * 1024));
                    ay.a(axVarA2);
                } catch (Throwable th2) {
                    ab.b(th2, "ofm", "uold");
                }
            }
        }
    }

    public static synchronized void a(int i, boolean z, int i2, int i3) {
        f3668a = i;
        b = z;
        if (i2 < 10 || i2 > 100) {
            i2 = 20;
        }
        c = i2;
        if (i2 / 5 > f) {
            f = i2 / 5;
        }
        d = i3;
    }

    public static void a(Context context) {
        ab.d().submit(new a(context, 2));
    }

    public static synchronized void a(az azVar, Context context) {
        ab.d().submit(new a(context, azVar));
    }
}
