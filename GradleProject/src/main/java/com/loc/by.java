package com.loc;

import android.os.SystemClock;
import com.loc.bx;
import java.util.List;

/* JADX INFO: compiled from: FpsCollector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class by {
    public static volatile by g;
    public static Object h = new Object();
    public long c;
    public dc d;
    public dc f = new dc();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public bx f3697a = new bx();
    public bz b = new bz();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public bu f3698e = new bu();

    /* JADX INFO: compiled from: FpsCollector.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public dc f3699a;
        public List<dd> b;
        public long c;
        public long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3700e;
        public long f;
        public byte g;
        public String h;
        public List<cw> i;
        public boolean j;
    }

    public static by a() {
        if (g == null) {
            synchronized (h) {
                if (g == null) {
                    g = new by();
                }
            }
        }
        return g;
    }

    public final ca a(a aVar) {
        ca caVar = null;
        if (aVar == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        dc dcVar = this.d;
        if (dcVar == null || aVar.f3699a.a(dcVar) >= 10.0d) {
            bx.a aVarA = this.f3697a.a(aVar.f3699a, aVar.j, aVar.g, aVar.h, aVar.i);
            List<dd> listA = this.b.a(aVar.f3699a, aVar.b, aVar.f3700e, aVar.d, jCurrentTimeMillis);
            if (aVarA != null || listA != null) {
                dc dcVar2 = this.f;
                dc dcVar3 = aVar.f3699a;
                long j = aVar.f;
                dcVar2.k = j;
                dcVar2.b = j;
                dcVar2.c = jCurrentTimeMillis;
                dcVar2.f3721e = dcVar3.f3721e;
                dcVar2.d = dcVar3.d;
                dcVar2.f = dcVar3.f;
                dcVar2.i = dcVar3.i;
                dcVar2.g = dcVar3.g;
                dcVar2.h = dcVar3.h;
                caVar = new ca(0, this.f3698e.a(dcVar2, aVarA, aVar.c, listA));
            }
            this.d = aVar.f3699a;
            this.c = jElapsedRealtime;
        }
        return caVar;
    }
}
