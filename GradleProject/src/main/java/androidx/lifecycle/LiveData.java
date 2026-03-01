package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import supwisdom.b4;
import supwisdom.de;
import supwisdom.ud;
import supwisdom.x3;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {
    public static final Object i = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1311a = new Object();
    public b4<de<? super T>, LiveData<T>.b> b = new b4<>();
    public int c = 0;
    public volatile Object d = i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f1312e = i;
    public int f = -1;
    public boolean g;
    public boolean h;

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1311a) {
                obj = LiveData.this.f1312e;
                LiveData.this.f1312e = LiveData.i;
            }
            LiveData.this.a(obj);
        }
    }

    public abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final de<? super T> f1315a;
        public boolean b;
        public int c = -1;

        public b(de<? super T> deVar) {
            this.f1315a = deVar;
        }

        public void a() {
        }

        public void a(boolean z) {
            if (z == this.b) {
                return;
            }
            this.b = z;
            boolean z2 = LiveData.this.c == 0;
            LiveData.this.c += this.b ? 1 : -1;
            if (z2 && this.b) {
                LiveData.this.a();
            }
            LiveData liveData = LiveData.this;
            if (liveData.c == 0 && !this.b) {
                liveData.b();
            }
            if (this.b) {
                LiveData.this.b(this);
            }
        }

        public abstract boolean b();

        public boolean g(xd xdVar) {
            return false;
        }
    }

    public LiveData() {
        new a();
    }

    public void a() {
    }

    public final void a(LiveData<T>.b bVar) {
        if (bVar.b) {
            if (!bVar.b()) {
                bVar.a(false);
                return;
            }
            int i2 = bVar.c;
            int i3 = this.f;
            if (i2 >= i3) {
                return;
            }
            bVar.c = i3;
            bVar.f1315a.a((Object) this.d);
        }
    }

    public void b() {
    }

    public void b(LiveData<T>.b bVar) {
        if (this.g) {
            this.h = true;
            return;
        }
        this.g = true;
        do {
            this.h = false;
            if (bVar != null) {
                a((b) bVar);
                bVar = null;
            } else {
                b4<de<? super T>, LiveData<T>.b>.d dVarG = this.b.g();
                while (dVarG.hasNext()) {
                    a((b) dVarG.next().getValue());
                    if (this.h) {
                        break;
                    }
                }
            }
        } while (this.h);
        this.g = false;
    }

    public class LifecycleBoundObserver extends LiveData<T>.b implements ud {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final xd f1313e;

        public LifecycleBoundObserver(xd xdVar, de<? super T> deVar) {
            super(deVar);
            this.f1313e = xdVar;
        }

        @Override // supwisdom.vd
        public void a(xd xdVar, Lifecycle.Event event) {
            if (this.f1313e.getLifecycle().a() == Lifecycle.State.DESTROYED) {
                LiveData.this.a((de) this.f1315a);
            } else {
                a(b());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean b() {
            return this.f1313e.getLifecycle().a().isAtLeast(Lifecycle.State.STARTED);
        }

        @Override // androidx.lifecycle.LiveData.b
        public boolean g(xd xdVar) {
            return this.f1313e == xdVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        public void a() {
            this.f1313e.getLifecycle().b(this);
        }
    }

    public void a(xd xdVar, de<? super T> deVar) {
        a("observe");
        if (xdVar.getLifecycle().a() == Lifecycle.State.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(xdVar, deVar);
        LiveData<T>.b bVarB = this.b.b(deVar, lifecycleBoundObserver);
        if (bVarB != null && !bVarB.g(xdVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarB != null) {
            return;
        }
        xdVar.getLifecycle().a(lifecycleBoundObserver);
    }

    public void a(de<? super T> deVar) {
        a("removeObserver");
        LiveData<T>.b bVarRemove = this.b.remove(deVar);
        if (bVarRemove == null) {
            return;
        }
        bVarRemove.a();
        bVarRemove.a(false);
    }

    public void a(T t) {
        a("setValue");
        this.f++;
        this.d = t;
        b(null);
    }

    public static void a(String str) {
        if (x3.b().a()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }
}
