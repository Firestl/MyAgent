package com.igexin.c.a.d;

import com.igexin.c.a.d.f;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public class e<E extends f> {
    public static final /* synthetic */ boolean h = !e.class.desiredAssertionStatus();
    public static final String i = "ScheduleQueue";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final transient ReentrantLock f3197a;
    public final transient Condition b;
    public final TreeSet<E> c;
    public final AtomicInteger d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3198e;
    public g f;
    public final AtomicLong g;
    public long j;

    public e(Comparator<? super E> comparator, g gVar) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f3197a = reentrantLock;
        this.b = reentrantLock.newCondition();
        this.d = new AtomicInteger(0);
        this.g = new AtomicLong(-1L);
        this.c = new TreeSet<>(comparator);
        this.f = gVar;
    }

    private E b() {
        try {
            return this.c.first();
        } catch (NoSuchElementException unused) {
            return null;
        }
    }

    private E c() {
        E e2 = (E) b();
        if (e2 == null) {
            return null;
        }
        if (this.c.remove(e2)) {
            return e2;
        }
        com.igexin.c.a.c.a.a("ScheduleQueue", "Queue Poll Error@");
        return null;
    }

    private E d() {
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            f fVarB = b();
            if (fVarB != null) {
                if (fVarB.a(TimeUnit.MILLISECONDS) > 0) {
                    fVarB.N |= 134217728;
                } else {
                    fVarB.N &= 1090519038;
                }
                if (fVarB.N >= 0) {
                    E e2 = (E) c();
                    if (!h && e2 == null) {
                        throw new AssertionError();
                    }
                    if (!e()) {
                        this.b.signalAll();
                    }
                    return e2;
                }
            }
            reentrantLock.unlock();
            return null;
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean e() {
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            return this.c.isEmpty();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void f() {
        this.c.clear();
    }

    public final int a(E e2, long j, TimeUnit timeUnit) {
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            if (!this.c.contains(e2)) {
                reentrantLock.unlock();
                return -1;
            }
            this.c.remove(e2);
            e2.w = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(j, timeUnit);
            e2.getClass().getSimpleName();
            e2.hashCode();
            e2.a(TimeUnit.SECONDS);
            return a(e2) ? 1 : -2;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final E a() throws InterruptedException {
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                f fVarB = b();
                boolean z = true;
                if (fVarB != null) {
                    long jA = fVarB.a(TimeUnit.NANOSECONDS);
                    if (!fVarB.m && !fVarB.n) {
                        z = false;
                    }
                    if (jA <= 0 || z) {
                        break;
                    }
                    fVarB.getClass().getSimpleName();
                    fVarB.hashCode();
                    TimeUnit.SECONDS.convert(jA, TimeUnit.NANOSECONDS);
                    this.g.set(fVarB.w);
                    com.igexin.c.a.c.a.a("schedule take|needAlarm = " + this.f.D + "|" + fVarB.getClass().getName() + CellDataManager.VIRTUAL_COMPONENT_SEPRATOR + fVarB.hashCode(), new Object[0]);
                    if (this.f.D) {
                        this.f.a(fVarB.w);
                    }
                    this.b.awaitNanos(jA);
                } else {
                    this.d.set(1);
                    this.f3198e = 0;
                    this.b.await();
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        E e2 = (E) c();
        if (!h && e2 == null) {
            throw new AssertionError();
        }
        if (!e()) {
            this.b.signalAll();
        }
        if (this.j > 0) {
            System.currentTimeMillis();
        }
        this.g.set(-1L);
        return e2;
    }

    public final boolean a(E e2) {
        if (e2 == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            f fVarB = b();
            int i2 = this.f3198e + 1;
            this.f3198e = i2;
            e2.x = i2;
            if (!this.c.add(e2)) {
                e2.x--;
                return false;
            }
            int i3 = e2.N + 1;
            e2.N = i3;
            e2.N = i3 & 1090519038;
            if (fVarB == null || this.c.comparator().compare(e2, fVarB) < 0) {
                this.b.signalAll();
            }
            return true;
        } catch (Exception e3) {
            com.igexin.c.a.c.a.a(e3);
            com.igexin.c.a.c.a.a("ScheduleQueue|offer|error", new Object[0]);
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean a(Class cls) {
        if (cls == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            ArrayList arrayList = new ArrayList();
            cls.getName();
            for (E e2 : this.c) {
                if (e2.getClass() == cls) {
                    arrayList.add(e2);
                }
            }
            cls.getName();
            arrayList.size();
            this.c.removeAll(arrayList);
            reentrantLock.unlock();
            return true;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final boolean b(E e2) {
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            if (this.c.contains(e2) && this.c.remove(e2)) {
                return a(e2);
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean c(E e2) {
        if (e2 == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f3197a;
        reentrantLock.lock();
        try {
            e2.getClass().getName();
            if (!this.c.contains(e2) || !this.c.remove(e2)) {
                return false;
            }
            e2.getClass().getName();
            e2.hashCode();
            reentrantLock.unlock();
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }
}
