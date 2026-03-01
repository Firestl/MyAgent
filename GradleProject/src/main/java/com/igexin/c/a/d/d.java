package com.igexin.c.a.d;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentLinkedQueue<com.igexin.c.a.d.a.e> f3196a = new ConcurrentLinkedQueue<>();
    public final ConcurrentLinkedQueue<com.igexin.c.a.d.a.e> b = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<com.igexin.c.a.d.a.e> c = this.f3196a;

    private synchronized Iterator<com.igexin.c.a.d.a.e> e() {
        return this.c.iterator();
    }

    public final synchronized void a() {
        this.c = this.f3196a;
    }

    public final synchronized void a(com.igexin.c.a.d.a.e eVar) {
        this.c.offer(eVar);
    }

    public final synchronized void b() {
        ConcurrentLinkedQueue<com.igexin.c.a.d.a.e> concurrentLinkedQueue = this.b;
        this.c = concurrentLinkedQueue;
        concurrentLinkedQueue.addAll(this.f3196a);
        this.f3196a.clear();
    }

    public final synchronized boolean c() {
        return this.c.isEmpty();
    }

    public final synchronized com.igexin.c.a.d.a.e d() {
        return this.c.poll();
    }
}
