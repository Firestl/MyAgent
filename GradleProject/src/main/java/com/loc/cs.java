package com.loc;

import android.os.SystemClock;
import android.util.LongSparseArray;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: RssiInfoManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cs {
    public static volatile cs g;
    public static Object h = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f3706e = new Object();
    public Object f = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public LongSparseArray<a> f3705a = new LongSparseArray<>();
    public LongSparseArray<a> b = new LongSparseArray<>();
    public LongSparseArray<a> c = new LongSparseArray<>();
    public LongSparseArray<a> d = new LongSparseArray<>();

    /* JADX INFO: compiled from: RssiInfoManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3707a;
        public long b;
        public boolean c;

        public a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }
    }

    public static cs a() {
        if (g == null) {
            synchronized (h) {
                if (g == null) {
                    g = new cs();
                }
            }
        }
        return g;
    }

    public static short a(LongSparseArray<a> longSparseArray, long j) {
        synchronized (longSparseArray) {
            a aVar = longSparseArray.get(j);
            if (aVar == null) {
                return (short) 0;
            }
            short sMax = (short) Math.max(1L, Math.min(32767L, (SystemClock.elapsedRealtime() - aVar.b) / 1000));
            if (!aVar.c) {
                sMax = (short) (-sMax);
            }
            return sMax;
        }
    }

    public static void a(List<cr> list, LongSparseArray<a> longSparseArray, LongSparseArray<a> longSparseArray2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int size = longSparseArray.size();
        byte b = 0;
        Iterator<cr> it = list.iterator();
        if (size == 0) {
            while (it.hasNext()) {
                cr next = it.next();
                a aVar = new a(b);
                aVar.f3707a = next.b();
                aVar.b = jElapsedRealtime;
                aVar.c = false;
                longSparseArray2.put(next.a(), aVar);
            }
            return;
        }
        while (it.hasNext()) {
            cr next2 = it.next();
            long jA = next2.a();
            a aVar2 = longSparseArray.get(jA);
            if (aVar2 == null) {
                aVar2 = new a(b);
            } else {
                if (aVar2.f3707a != next2.b()) {
                }
                longSparseArray2.put(jA, aVar2);
            }
            aVar2.f3707a = next2.b();
            aVar2.b = jElapsedRealtime;
            aVar2.c = true;
            longSparseArray2.put(jA, aVar2);
        }
    }

    public final short a(long j) {
        return a(this.f3705a, j);
    }

    public final void a(List<cr> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.f3706e) {
            a(list, this.f3705a, this.b);
            LongSparseArray<a> longSparseArray = this.f3705a;
            this.f3705a = this.b;
            this.b = longSparseArray;
            longSparseArray.clear();
        }
    }

    public final short b(long j) {
        return a(this.c, j);
    }

    public final void b(List<cr> list) {
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.f) {
            a(list, this.c, this.d);
            LongSparseArray<a> longSparseArray = this.c;
            this.c = this.d;
            this.d = longSparseArray;
            longSparseArray.clear();
        }
    }
}
