package com.efs.sdk.pa.a;

import android.os.SystemClock;
import android.util.Printer;
import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Printer {
    public long f;
    public boolean b = false;
    public String c = null;
    public long d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1926e = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Vector<d> f1925a = new Vector<>();

    @Override // android.util.Printer
    public final void println(String str) {
        if (str.startsWith(Operators.G)) {
            this.d = SystemClock.elapsedRealtime();
            this.f1926e = SystemClock.currentThreadTimeMillis();
            this.c = str;
            this.b = true;
            Iterator<d> it = this.f1925a.iterator();
            while (it.hasNext()) {
                it.next();
            }
            return;
        }
        if (this.b && str.startsWith(Operators.L)) {
            this.b = false;
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.d;
            if (jElapsedRealtime > this.f) {
                long jCurrentThreadTimeMillis = SystemClock.currentThreadTimeMillis() - this.f1926e;
                Iterator<d> it2 = this.f1925a.iterator();
                while (it2.hasNext()) {
                    it2.next().a(this.c, jElapsedRealtime, jCurrentThreadTimeMillis);
                }
            }
        }
    }
}
