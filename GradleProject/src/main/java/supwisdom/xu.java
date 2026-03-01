package supwisdom;

import android.content.Context;
import android.os.Build;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* JADX INFO: loaded from: classes.dex */
public class xu {
    public static final a b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f9812a;

    public interface a {
        Object a(Context context, Interpolator interpolator);

        void a(Object obj, int i, int i2, int i3, int i4, int i5);

        boolean a(Object obj);

        int b(Object obj);

        int c(Object obj);

        void d(Object obj);

        int e(Object obj);

        int f(Object obj);

        boolean g(Object obj);
    }

    public static class b implements a {
        @Override // supwisdom.xu.a
        public boolean a(Object obj) {
            return ((Scroller) obj).computeScrollOffset();
        }

        @Override // supwisdom.xu.a
        public int b(Object obj) {
            return ((Scroller) obj).getFinalX();
        }

        @Override // supwisdom.xu.a
        public int c(Object obj) {
            return ((Scroller) obj).getFinalY();
        }

        @Override // supwisdom.xu.a
        public void d(Object obj) {
            ((Scroller) obj).abortAnimation();
        }

        @Override // supwisdom.xu.a
        public int e(Object obj) {
            return ((Scroller) obj).getCurrY();
        }

        @Override // supwisdom.xu.a
        public int f(Object obj) {
            return ((Scroller) obj).getCurrX();
        }

        @Override // supwisdom.xu.a
        public boolean g(Object obj) {
            return ((Scroller) obj).isFinished();
        }

        @Override // supwisdom.xu.a
        public Object a(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        @Override // supwisdom.xu.a
        public void a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
        }
    }

    public static class c implements a {
        @Override // supwisdom.xu.a
        public boolean a(Object obj) {
            return yu.b(obj);
        }

        @Override // supwisdom.xu.a
        public int b(Object obj) {
            return yu.e(obj);
        }

        @Override // supwisdom.xu.a
        public int c(Object obj) {
            return yu.f(obj);
        }

        @Override // supwisdom.xu.a
        public void d(Object obj) {
            yu.a(obj);
        }

        @Override // supwisdom.xu.a
        public int e(Object obj) {
            return yu.d(obj);
        }

        @Override // supwisdom.xu.a
        public int f(Object obj) {
            return yu.c(obj);
        }

        @Override // supwisdom.xu.a
        public boolean g(Object obj) {
            return yu.g(obj);
        }

        @Override // supwisdom.xu.a
        public Object a(Context context, Interpolator interpolator) {
            return yu.a(context, interpolator);
        }

        @Override // supwisdom.xu.a
        public void a(Object obj, int i, int i2, int i3, int i4, int i5) {
            yu.a(obj, i, i2, i3, i4, i5);
        }
    }

    public static class d extends c {
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 14) {
            b = new d();
        } else if (i >= 9) {
            b = new c();
        } else {
            b = new b();
        }
    }

    public xu(Context context, Interpolator interpolator) {
        this.f9812a = b.a(context, interpolator);
    }

    public void a() {
        b.d(this.f9812a);
    }

    public boolean b() {
        return b.a(this.f9812a);
    }

    public int c() {
        return b.f(this.f9812a);
    }

    public int d() {
        return b.e(this.f9812a);
    }

    public int e() {
        return b.b(this.f9812a);
    }

    public int f() {
        return b.c(this.f9812a);
    }

    public boolean g() {
        return b.g(this.f9812a);
    }

    public static xu a(Context context, Interpolator interpolator) {
        return new xu(context, interpolator);
    }

    public void a(int i, int i2, int i3, int i4, int i5) {
        b.a(this.f9812a, i, i2, i3, i4, i5);
    }
}
