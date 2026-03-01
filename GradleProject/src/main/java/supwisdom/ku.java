package supwisdom;

import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class ku {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f8206a;

    public static class b extends a {
        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void a(ViewGroup viewGroup, boolean z) {
            lu.a(viewGroup, z);
        }
    }

    public static class c extends b {
    }

    public static class d extends c {
        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void a(View view, int i, Paint paint) {
            mu.a(view, i, paint);
        }

        @Override // supwisdom.ku.a, supwisdom.ku.k
        public float b(View view) {
            return mu.a(view);
        }

        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void c(View view, float f) {
            mu.a(view, f);
        }

        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void b(View view, float f) {
            mu.b(view, f);
        }
    }

    public static class e extends d {
        @Override // supwisdom.ku.a, supwisdom.ku.k
        public boolean a(View view, int i) {
            return nu.a(view, i);
        }
    }

    public static class f extends e {
        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void a(View view) {
            ou.a(view);
        }
    }

    public static class g extends f {
    }

    public static class h extends g {
    }

    public static class i extends h {
    }

    public static class j extends i {
        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void a(View view, float f) {
            pu.a(view, f);
        }

        @Override // supwisdom.ku.a, supwisdom.ku.k
        public boolean c(View view) {
            return pu.a(view);
        }

        @Override // supwisdom.ku.a, supwisdom.ku.k
        public void d(View view) {
            pu.b(view);
        }
    }

    public interface k {
        void a(View view);

        void a(View view, float f);

        void a(View view, int i, Paint paint);

        void a(ViewGroup viewGroup, boolean z);

        boolean a(View view, int i);

        float b(View view);

        void b(View view, float f);

        void c(View view, float f);

        boolean c(View view);

        void d(View view);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f8206a = new j();
            return;
        }
        if (i2 >= 19) {
            f8206a = new i();
            return;
        }
        if (i2 >= 17) {
            f8206a = new g();
            return;
        }
        if (i2 >= 16) {
            f8206a = new f();
            return;
        }
        if (i2 >= 14) {
            f8206a = new e();
            return;
        }
        if (i2 >= 11) {
            f8206a = new d();
            return;
        }
        if (i2 >= 9) {
            f8206a = new c();
        } else if (i2 >= 7) {
            f8206a = new b();
        } else {
            f8206a = new a();
        }
    }

    public static boolean a(View view, int i2) {
        return f8206a.a(view, i2);
    }

    public static boolean b(View view) {
        return f8206a.c(view);
    }

    public static void c(View view) {
        f8206a.a(view);
    }

    public static void d(View view) {
        f8206a.d(view);
    }

    public static class a implements k {
        @Override // supwisdom.ku.k
        public void a(View view, float f) {
        }

        @Override // supwisdom.ku.k
        public void a(View view, int i, Paint paint) {
        }

        @Override // supwisdom.ku.k
        public void a(ViewGroup viewGroup, boolean z) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // supwisdom.ku.k
        public boolean a(View view, int i) {
            return (view instanceof ju) && a((ju) view, i);
        }

        @Override // supwisdom.ku.k
        public float b(View view) {
            return 0.0f;
        }

        @Override // supwisdom.ku.k
        public void b(View view, float f) {
        }

        @Override // supwisdom.ku.k
        public void c(View view, float f) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // supwisdom.ku.k
        public boolean c(View view) {
            if (view instanceof fu) {
                return ((fu) view).isNestedScrollingEnabled();
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // supwisdom.ku.k
        public void d(View view) {
            if (view instanceof fu) {
                ((fu) view).stopNestedScroll();
            }
        }

        public final boolean a(ju juVar, int i) {
            int iC = juVar.c();
            int iA = juVar.a() - juVar.b();
            if (iA == 0) {
                return false;
            }
            return i < 0 ? iC > 0 : iC < iA - 1;
        }

        @Override // supwisdom.ku.k
        public void a(View view) {
            view.invalidate();
        }
    }

    public static float a(View view) {
        return f8206a.b(view);
    }

    public static void b(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (i2 == 0 || Build.VERSION.SDK_INT >= 11) {
            return;
        }
        view.invalidate();
    }

    public static void c(View view, float f2) {
        f8206a.b(view, f2);
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        f8206a.a(viewGroup, z);
    }

    public static void a(View view, float f2) {
        f8206a.a(view, f2);
    }

    public static void a(View view, int i2, Paint paint) {
        f8206a.a(view, i2, paint);
    }

    public static void b(View view, float f2) {
        f8206a.c(view, f2);
    }
}
