package supwisdom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: Action.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class rg1<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Picasso f9060a;
    public final jh1 b;
    public final WeakReference<T> c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9061e;
    public final int f;
    public final int g;
    public final Drawable h;
    public final String i;
    public final Object j;
    public boolean k;
    public boolean l;

    /* JADX INFO: compiled from: Action.java */
    public static class a<M> extends WeakReference<M> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final rg1 f9062a;

        public a(rg1 rg1Var, M m, ReferenceQueue<? super M> referenceQueue) {
            super(m, referenceQueue);
            this.f9062a = rg1Var;
        }
    }

    public rg1(Picasso picasso, T t, jh1 jh1Var, int i, int i2, int i3, Drawable drawable, String str, Object obj, boolean z) {
        this.f9060a = picasso;
        this.b = jh1Var;
        this.c = t == null ? null : new a(this, t, picasso.k);
        this.f9061e = i;
        this.f = i2;
        this.d = z;
        this.g = i3;
        this.h = drawable;
        this.i = str;
        this.j = obj == null ? this : obj;
    }

    public void a() {
        this.l = true;
    }

    public abstract void a(Bitmap bitmap, Picasso.LoadedFrom loadedFrom);

    public abstract void b();

    public String c() {
        return this.i;
    }

    public int d() {
        return this.f9061e;
    }

    public int e() {
        return this.f;
    }

    public Picasso f() {
        return this.f9060a;
    }

    public Picasso.Priority g() {
        return this.b.r;
    }

    public jh1 h() {
        return this.b;
    }

    public Object i() {
        return this.j;
    }

    public T j() {
        WeakReference<T> weakReference = this.c;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public boolean k() {
        return this.l;
    }

    public boolean l() {
        return this.k;
    }
}
