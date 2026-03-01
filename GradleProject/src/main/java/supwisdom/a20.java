package supwisdom;

import java.lang.Exception;
import java.util.LinkedList;
import supwisdom.y10;
import supwisdom.z10;

/* JADX INFO: compiled from: SimpleDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a20<I extends y10, O extends z10, E extends Exception> implements w10<I, O, E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Thread f6847a;
    public final Object b = new Object();
    public final LinkedList<I> c = new LinkedList<>();
    public final LinkedList<O> d = new LinkedList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final I[] f6848e;
    public final O[] f;
    public int g;
    public int h;
    public I i;
    public E j;
    public boolean k;
    public boolean l;
    public int m;

    /* JADX INFO: compiled from: SimpleDecoder.java */
    public class a extends Thread {
        public a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            a20.this.k();
        }
    }

    public a20(I[] iArr, O[] oArr) {
        this.f6848e = iArr;
        this.g = iArr.length;
        for (int i = 0; i < this.g; i++) {
            ((I[]) this.f6848e)[i] = g();
        }
        this.f = oArr;
        this.h = oArr.length;
        for (int i2 = 0; i2 < this.h; i2++) {
            ((O[]) this.f)[i2] = h();
        }
        a aVar = new a();
        this.f6847a = aVar;
        aVar.start();
    }

    public abstract E a(I i, O o, boolean z);

    @Override // supwisdom.w10
    public final void c() {
        synchronized (this.b) {
            this.k = true;
            this.m = 0;
            if (this.i != null) {
                b(this.i);
                this.i = null;
            }
            while (!this.c.isEmpty()) {
                b(this.c.removeFirst());
            }
            while (!this.d.isEmpty()) {
                b(this.d.removeFirst());
            }
        }
    }

    @Override // supwisdom.w10
    public void d() {
        synchronized (this.b) {
            this.l = true;
            this.b.notify();
        }
        try {
            this.f6847a.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    @Override // supwisdom.w10
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final I a() throws Exception {
        I i;
        synchronized (this.b) {
            i();
            e80.b(this.i == null);
            if (this.g == 0) {
                i = null;
            } else {
                I[] iArr = this.f6848e;
                int i2 = this.g - 1;
                this.g = i2;
                i = iArr[i2];
            }
            this.i = i;
        }
        return i;
    }

    @Override // supwisdom.w10
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final O b() throws Exception {
        synchronized (this.b) {
            i();
            if (this.d.isEmpty()) {
                return null;
            }
            return this.d.removeFirst();
        }
    }

    public abstract I g();

    public abstract O h();

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Exception */
    public final void i() throws Exception {
        E e2 = this.j;
        if (e2 != null) {
            throw e2;
        }
    }

    public final void j() {
        if (m()) {
            this.b.notify();
        }
    }

    public final void k() {
        do {
            try {
            } catch (InterruptedException e2) {
                throw new IllegalStateException(e2);
            }
        } while (l());
    }

    public final boolean l() throws InterruptedException {
        synchronized (this.b) {
            while (!this.l && !m()) {
                this.b.wait();
            }
            if (this.l) {
                return false;
            }
            I iRemoveFirst = this.c.removeFirst();
            O[] oArr = this.f;
            int i = this.h - 1;
            this.h = i;
            O o = oArr[i];
            boolean z = this.k;
            this.k = false;
            if (iRemoveFirst.d()) {
                o.c(4);
            } else {
                if (iRemoveFirst.c()) {
                    o.c(Integer.MIN_VALUE);
                }
                E e2 = (E) a(iRemoveFirst, o, z);
                this.j = e2;
                if (e2 != null) {
                    synchronized (this.b) {
                    }
                    return false;
                }
            }
            synchronized (this.b) {
                if (this.k) {
                    b(o);
                } else if (o.c()) {
                    this.m++;
                    b(o);
                } else {
                    o.c = this.m;
                    this.m = 0;
                    this.d.addLast(o);
                }
                b(iRemoveFirst);
            }
            return true;
        }
    }

    public final boolean m() {
        return !this.c.isEmpty() && this.h > 0;
    }

    public final void b(I i) {
        i.a();
        I[] iArr = this.f6848e;
        int i2 = this.g;
        this.g = i2 + 1;
        iArr[i2] = i;
    }

    public final void a(int i) {
        e80.b(this.g == this.f6848e.length);
        for (I i2 : this.f6848e) {
            i2.f(i);
        }
    }

    public final void b(O o) {
        o.a();
        O[] oArr = this.f;
        int i = this.h;
        this.h = i + 1;
        oArr[i] = o;
    }

    @Override // supwisdom.w10
    public final void a(I i) throws Exception {
        synchronized (this.b) {
            i();
            e80.a(i == this.i);
            this.c.addLast(i);
            j();
            this.i = null;
        }
    }

    public void a(O o) {
        synchronized (this.b) {
            b(o);
            j();
        }
    }
}
