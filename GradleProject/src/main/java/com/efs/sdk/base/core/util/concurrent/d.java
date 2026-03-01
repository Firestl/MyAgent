package com.efs.sdk.base.core.util.concurrent;

import com.efs.sdk.base.core.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d<T> implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<b<T>> f1865a = new ArrayList(5);
    public c<T> b;

    public d(c<T> cVar) {
        this.b = cVar;
    }

    public final void a(List<b<T>> list) {
        this.f1865a.addAll(list);
    }

    @Override // java.lang.Runnable
    public void run() {
        a();
    }

    public final T a() {
        T tA = null;
        try {
            Iterator<b<T>> it = this.f1865a.iterator();
            while (it.hasNext()) {
                it.next();
            }
            tA = this.b.a();
            Iterator<b<T>> it2 = this.f1865a.iterator();
            while (it2.hasNext()) {
                it2.next().a(this.b, tA);
            }
            Iterator<b<T>> it3 = this.f1865a.iterator();
            while (it3.hasNext()) {
                it3.next().result(tA);
            }
        } catch (Throwable th) {
            Log.w("efs.util.concurrent", th);
            Iterator<b<T>> it4 = this.f1865a.iterator();
            while (it4.hasNext()) {
                it4.next();
            }
        }
        return tA;
    }
}
