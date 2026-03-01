package supwisdom;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class tb {
    public static final tb b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f9271a;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Field f9272a;
        public static Field b;
        public static Field c;
        public static boolean d;

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                f9272a = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                b = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                c = declaredField3;
                declaredField3.setAccessible(true);
                d = true;
            } catch (ReflectiveOperationException e2) {
                Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + e2.getMessage(), e2);
            }
        }

        public static tb a(View view) {
            if (d && view.isAttachedToWindow()) {
                try {
                    Object obj = f9272a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) b.get(obj);
                        Rect rect2 = (Rect) c.get(obj);
                        if (rect != null && rect2 != null) {
                            b bVar = new b();
                            bVar.a(o8.a(rect));
                            bVar.b(o8.a(rect2));
                            tb tbVarA = bVar.a();
                            tbVarA.a(tbVarA);
                            tbVarA.a(view.getRootView());
                            return tbVarA;
                        }
                    }
                } catch (IllegalAccessException e2) {
                    Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e2.getMessage(), e2);
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f f9273a;

        public b() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.f9273a = new e();
                return;
            }
            if (i >= 29) {
                this.f9273a = new d();
            } else if (i >= 20) {
                this.f9273a = new c();
            } else {
                this.f9273a = new f();
            }
        }

        @Deprecated
        public b a(o8 o8Var) {
            this.f9273a.b(o8Var);
            return this;
        }

        @Deprecated
        public b b(o8 o8Var) {
            this.f9273a.d(o8Var);
            return this;
        }

        public tb a() {
            return this.f9273a.b();
        }

        public b(tb tbVar) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.f9273a = new e(tbVar);
                return;
            }
            if (i >= 29) {
                this.f9273a = new d(tbVar);
            } else if (i >= 20) {
                this.f9273a = new c(tbVar);
            } else {
                this.f9273a = new f(tbVar);
            }
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static Field f9274e = null;
        public static boolean f = false;
        public static Constructor<WindowInsets> g = null;
        public static boolean h = false;
        public WindowInsets c;
        public o8 d;

        public c() {
            this.c = c();
        }

        public static WindowInsets c() {
            if (!f) {
                try {
                    f9274e = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f = true;
            }
            Field field = f9274e;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e3) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
                }
            }
            if (!h) {
                try {
                    g = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                h = true;
            }
            Constructor<WindowInsets> constructor = g;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // supwisdom.tb.f
        public void b(o8 o8Var) {
            this.d = o8Var;
        }

        @Override // supwisdom.tb.f
        public void d(o8 o8Var) {
            WindowInsets windowInsets = this.c;
            if (windowInsets != null) {
                this.c = windowInsets.replaceSystemWindowInsets(o8Var.f8643a, o8Var.b, o8Var.c, o8Var.d);
            }
        }

        @Override // supwisdom.tb.f
        public tb b() {
            a();
            tb tbVarA = tb.a(this.c);
            tbVarA.a(this.b);
            tbVarA.b(this.d);
            return tbVarA;
        }

        public c(tb tbVar) {
            super(tbVar);
            this.c = tbVar.l();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d extends f {
        public final WindowInsets.Builder c;

        public d() {
            this.c = new WindowInsets.Builder();
        }

        @Override // supwisdom.tb.f
        public void a(o8 o8Var) {
            this.c.setMandatorySystemGestureInsets(o8Var.a());
        }

        @Override // supwisdom.tb.f
        public void b(o8 o8Var) {
            this.c.setStableInsets(o8Var.a());
        }

        @Override // supwisdom.tb.f
        public void c(o8 o8Var) {
            this.c.setSystemGestureInsets(o8Var.a());
        }

        @Override // supwisdom.tb.f
        public void d(o8 o8Var) {
            this.c.setSystemWindowInsets(o8Var.a());
        }

        @Override // supwisdom.tb.f
        public void e(o8 o8Var) {
            this.c.setTappableElementInsets(o8Var.a());
        }

        @Override // supwisdom.tb.f
        public tb b() {
            a();
            tb tbVarA = tb.a(this.c.build());
            tbVarA.a(this.b);
            return tbVarA;
        }

        public d(tb tbVar) {
            WindowInsets.Builder builder;
            super(tbVar);
            WindowInsets windowInsetsL = tbVar.l();
            if (windowInsetsL != null) {
                builder = new WindowInsets.Builder(windowInsetsL);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.c = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends d {
        public e() {
        }

        public e(tb tbVar) {
            super(tbVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final tb f9275a;
        public o8[] b;

        public f() {
            this(new tb((tb) null));
        }

        public final void a() {
            o8[] o8VarArr = this.b;
            if (o8VarArr != null) {
                o8 o8VarA = o8VarArr[m.a(1)];
                o8 o8VarA2 = this.b[m.a(2)];
                if (o8VarA2 == null) {
                    o8VarA2 = this.f9275a.a(2);
                }
                if (o8VarA == null) {
                    o8VarA = this.f9275a.a(1);
                }
                d(o8.a(o8VarA, o8VarA2));
                o8 o8Var = this.b[m.a(16)];
                if (o8Var != null) {
                    c(o8Var);
                }
                o8 o8Var2 = this.b[m.a(32)];
                if (o8Var2 != null) {
                    a(o8Var2);
                }
                o8 o8Var3 = this.b[m.a(64)];
                if (o8Var3 != null) {
                    e(o8Var3);
                }
            }
        }

        public void a(o8 o8Var) {
        }

        public tb b() {
            a();
            return this.f9275a;
        }

        public void b(o8 o8Var) {
        }

        public void c(o8 o8Var) {
        }

        public void d(o8 o8Var) {
        }

        public void e(o8 o8Var) {
        }

        public f(tb tbVar) {
            this.f9275a = tbVar;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends l {
        public static boolean h = false;
        public static Method i;
        public static Class<?> j;
        public static Class<?> k;
        public static Field l;
        public static Field m;
        public final WindowInsets c;
        public o8[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public o8 f9276e;
        public tb f;
        public o8 g;

        public g(tb tbVar, WindowInsets windowInsets) {
            super(tbVar);
            this.f9276e = null;
            this.c = windowInsets;
        }

        @SuppressLint({"PrivateApi"})
        public static void m() {
            try {
                i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                j = Class.forName("android.view.ViewRootImpl");
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                k = cls;
                l = cls.getDeclaredField("mVisibleInsets");
                m = j.getDeclaredField("mAttachInfo");
                l.setAccessible(true);
                m.setAccessible(true);
            } catch (ReflectiveOperationException e2) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
            }
            h = true;
        }

        @Override // supwisdom.tb.l
        public o8 a(int i2) {
            return a(i2, false);
        }

        public o8 b(int i2, boolean z) {
            o8 o8VarE;
            int i3;
            if (i2 == 1) {
                return z ? o8.a(0, Math.max(l().b, h().b), 0, 0) : o8.a(0, h().b, 0, 0);
            }
            if (i2 == 2) {
                if (z) {
                    o8 o8VarL = l();
                    o8 o8VarF = f();
                    return o8.a(Math.max(o8VarL.f8643a, o8VarF.f8643a), 0, Math.max(o8VarL.c, o8VarF.c), Math.max(o8VarL.d, o8VarF.d));
                }
                o8 o8VarH = h();
                tb tbVar = this.f;
                o8VarE = tbVar != null ? tbVar.e() : null;
                int iMin = o8VarH.d;
                if (o8VarE != null) {
                    iMin = Math.min(iMin, o8VarE.d);
                }
                return o8.a(o8VarH.f8643a, 0, o8VarH.c, iMin);
            }
            if (i2 != 8) {
                if (i2 == 16) {
                    return g();
                }
                if (i2 == 32) {
                    return e();
                }
                if (i2 == 64) {
                    return i();
                }
                if (i2 != 128) {
                    return o8.f8642e;
                }
                tb tbVar2 = this.f;
                qa qaVarD = tbVar2 != null ? tbVar2.d() : d();
                return qaVarD != null ? o8.a(qaVarD.b(), qaVarD.d(), qaVarD.c(), qaVarD.a()) : o8.f8642e;
            }
            o8[] o8VarArr = this.d;
            o8VarE = o8VarArr != null ? o8VarArr[m.a(8)] : null;
            if (o8VarE != null) {
                return o8VarE;
            }
            o8 o8VarH2 = h();
            o8 o8VarL2 = l();
            int i4 = o8VarH2.d;
            if (i4 > o8VarL2.d) {
                return o8.a(0, 0, 0, i4);
            }
            o8 o8Var = this.g;
            return (o8Var == null || o8Var.equals(o8.f8642e) || (i3 = this.g.d) <= o8VarL2.d) ? o8.f8642e : o8.a(0, 0, 0, i3);
        }

        @Override // supwisdom.tb.l
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.g, ((g) obj).g);
            }
            return false;
        }

        @Override // supwisdom.tb.l
        public final o8 h() {
            if (this.f9276e == null) {
                this.f9276e = o8.a(this.c.getSystemWindowInsetLeft(), this.c.getSystemWindowInsetTop(), this.c.getSystemWindowInsetRight(), this.c.getSystemWindowInsetBottom());
            }
            return this.f9276e;
        }

        @Override // supwisdom.tb.l
        public boolean k() {
            return this.c.isRound();
        }

        public final o8 l() {
            tb tbVar = this.f;
            return tbVar != null ? tbVar.e() : o8.f8642e;
        }

        @SuppressLint({"WrongConstant"})
        public final o8 a(int i2, boolean z) {
            o8 o8VarA = o8.f8642e;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    o8VarA = o8.a(o8VarA, b(i3, z));
                }
            }
            return o8VarA;
        }

        public g(tb tbVar, g gVar) {
            this(tbVar, new WindowInsets(gVar.c));
        }

        @Override // supwisdom.tb.l
        public tb a(int i2, int i3, int i4, int i5) {
            b bVar = new b(tb.a(this.c));
            bVar.b(tb.a(h(), i2, i3, i4, i5));
            bVar.a(tb.a(f(), i2, i3, i4, i5));
            return bVar.a();
        }

        @Override // supwisdom.tb.l
        public void a(tb tbVar) {
            tbVar.a(this.f);
            tbVar.a(this.g);
        }

        @Override // supwisdom.tb.l
        public void a(o8 o8Var) {
            this.g = o8Var;
        }

        @Override // supwisdom.tb.l
        public void a(View view) {
            o8 o8VarB = b(view);
            if (o8VarB == null) {
                o8VarB = o8.f8642e;
            }
            a(o8VarB);
        }

        @Override // supwisdom.tb.l
        public void a(o8[] o8VarArr) {
            this.d = o8VarArr;
        }

        @Override // supwisdom.tb.l
        public void b(tb tbVar) {
            this.f = tbVar;
        }

        public final o8 b(View view) {
            if (Build.VERSION.SDK_INT < 30) {
                if (!h) {
                    m();
                }
                Method method = i;
                if (method != null && k != null && l != null) {
                    try {
                        Object objInvoke = method.invoke(view, new Object[0]);
                        if (objInvoke == null) {
                            Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                            return null;
                        }
                        Rect rect = (Rect) l.get(m.get(objInvoke));
                        if (rect != null) {
                            return o8.a(rect);
                        }
                        return null;
                    } catch (ReflectiveOperationException e2) {
                        Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
                    }
                }
                return null;
            }
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {
        public o8 n;

        public h(tb tbVar, WindowInsets windowInsets) {
            super(tbVar, windowInsets);
            this.n = null;
        }

        @Override // supwisdom.tb.l
        public tb b() {
            return tb.a(this.c.consumeStableInsets());
        }

        @Override // supwisdom.tb.l
        public tb c() {
            return tb.a(this.c.consumeSystemWindowInsets());
        }

        @Override // supwisdom.tb.l
        public final o8 f() {
            if (this.n == null) {
                this.n = o8.a(this.c.getStableInsetLeft(), this.c.getStableInsetTop(), this.c.getStableInsetRight(), this.c.getStableInsetBottom());
            }
            return this.n;
        }

        @Override // supwisdom.tb.l
        public boolean j() {
            return this.c.isConsumed();
        }

        @Override // supwisdom.tb.l
        public void b(o8 o8Var) {
            this.n = o8Var;
        }

        public h(tb tbVar, h hVar) {
            super(tbVar, hVar);
            this.n = null;
            this.n = hVar.n;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i extends h {
        public i(tb tbVar, WindowInsets windowInsets) {
            super(tbVar, windowInsets);
        }

        @Override // supwisdom.tb.l
        public tb a() {
            return tb.a(this.c.consumeDisplayCutout());
        }

        @Override // supwisdom.tb.l
        public qa d() {
            return qa.a(this.c.getDisplayCutout());
        }

        @Override // supwisdom.tb.g, supwisdom.tb.l
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return Objects.equals(this.c, iVar.c) && Objects.equals(this.g, iVar.g);
        }

        @Override // supwisdom.tb.l
        public int hashCode() {
            return this.c.hashCode();
        }

        public i(tb tbVar, i iVar) {
            super(tbVar, iVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class k extends j {
        public static final tb r = tb.a(WindowInsets.CONSUMED);

        public k(tb tbVar, WindowInsets windowInsets) {
            super(tbVar, windowInsets);
        }

        @Override // supwisdom.tb.g, supwisdom.tb.l
        public o8 a(int i) {
            return o8.a(this.c.getInsets(n.a(i)));
        }

        @Override // supwisdom.tb.g, supwisdom.tb.l
        public final void a(View view) {
        }

        public k(tb tbVar, k kVar) {
            super(tbVar, kVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class l {
        public static final tb b = new b().a().a().b().c();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final tb f9277a;

        public l(tb tbVar) {
            this.f9277a = tbVar;
        }

        public tb a() {
            return this.f9277a;
        }

        public void a(View view) {
        }

        public void a(o8 o8Var) {
        }

        public void a(tb tbVar) {
        }

        public void a(o8[] o8VarArr) {
        }

        public tb b() {
            return this.f9277a;
        }

        public void b(o8 o8Var) {
        }

        public void b(tb tbVar) {
        }

        public tb c() {
            return this.f9277a;
        }

        public qa d() {
            return null;
        }

        public o8 e() {
            return h();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof l)) {
                return false;
            }
            l lVar = (l) obj;
            return k() == lVar.k() && j() == lVar.j() && ia.a(h(), lVar.h()) && ia.a(f(), lVar.f()) && ia.a(d(), lVar.d());
        }

        public o8 f() {
            return o8.f8642e;
        }

        public o8 g() {
            return h();
        }

        public o8 h() {
            return o8.f8642e;
        }

        public int hashCode() {
            return ia.a(Boolean.valueOf(k()), Boolean.valueOf(j()), h(), f(), d());
        }

        public o8 i() {
            return h();
        }

        public boolean j() {
            return false;
        }

        public boolean k() {
            return false;
        }

        public tb a(int i, int i2, int i3, int i4) {
            return b;
        }

        public o8 a(int i) {
            return o8.f8642e;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class m {
        public static int a(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 8) {
                return 3;
            }
            if (i == 16) {
                return 4;
            }
            if (i == 32) {
                return 5;
            }
            if (i == 64) {
                return 6;
            }
            if (i == 128) {
                return 7;
            }
            if (i == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class n {
        public static int a(int i) {
            int iStatusBars;
            int i2 = 0;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i & i3) != 0) {
                    if (i3 == 1) {
                        iStatusBars = WindowInsets.Type.statusBars();
                    } else if (i3 == 2) {
                        iStatusBars = WindowInsets.Type.navigationBars();
                    } else if (i3 == 4) {
                        iStatusBars = WindowInsets.Type.captionBar();
                    } else if (i3 == 8) {
                        iStatusBars = WindowInsets.Type.ime();
                    } else if (i3 == 16) {
                        iStatusBars = WindowInsets.Type.systemGestures();
                    } else if (i3 == 32) {
                        iStatusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i3 == 64) {
                        iStatusBars = WindowInsets.Type.tappableElement();
                    } else if (i3 == 128) {
                        iStatusBars = WindowInsets.Type.displayCutout();
                    }
                    i2 |= iStatusBars;
                }
            }
            return i2;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = k.r;
        } else {
            b = l.b;
        }
    }

    public tb(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.f9271a = new k(this, windowInsets);
            return;
        }
        if (i2 >= 29) {
            this.f9271a = new j(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f9271a = new i(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f9271a = new h(this, windowInsets);
        } else if (i2 >= 20) {
            this.f9271a = new g(this, windowInsets);
        } else {
            this.f9271a = new l(this);
        }
    }

    public static tb a(WindowInsets windowInsets) {
        return a(windowInsets, null);
    }

    @Deprecated
    public tb b(int i2, int i3, int i4, int i5) {
        b bVar = new b(this);
        bVar.b(o8.a(i2, i3, i4, i5));
        return bVar.a();
    }

    @Deprecated
    public tb c() {
        return this.f9271a.c();
    }

    public qa d() {
        return this.f9271a.d();
    }

    @Deprecated
    public o8 e() {
        return this.f9271a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof tb) {
            return ia.a(this.f9271a, ((tb) obj).f9271a);
        }
        return false;
    }

    @Deprecated
    public int f() {
        return this.f9271a.h().d;
    }

    @Deprecated
    public int g() {
        return this.f9271a.h().f8643a;
    }

    @Deprecated
    public int h() {
        return this.f9271a.h().c;
    }

    public int hashCode() {
        l lVar = this.f9271a;
        if (lVar == null) {
            return 0;
        }
        return lVar.hashCode();
    }

    @Deprecated
    public int i() {
        return this.f9271a.h().b;
    }

    @Deprecated
    public boolean j() {
        return !this.f9271a.h().equals(o8.f8642e);
    }

    public boolean k() {
        return this.f9271a.j();
    }

    public WindowInsets l() {
        l lVar = this.f9271a;
        if (lVar instanceof g) {
            return ((g) lVar).c;
        }
        return null;
    }

    public static tb a(WindowInsets windowInsets, View view) {
        na.a(windowInsets);
        tb tbVar = new tb(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            tbVar.a(lb.t(view));
            tbVar.a(view.getRootView());
        }
        return tbVar;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class j extends i {
        public o8 o;
        public o8 p;
        public o8 q;

        public j(tb tbVar, WindowInsets windowInsets) {
            super(tbVar, windowInsets);
            this.o = null;
            this.p = null;
            this.q = null;
        }

        @Override // supwisdom.tb.g, supwisdom.tb.l
        public tb a(int i, int i2, int i3, int i4) {
            return tb.a(this.c.inset(i, i2, i3, i4));
        }

        @Override // supwisdom.tb.h, supwisdom.tb.l
        public void b(o8 o8Var) {
        }

        @Override // supwisdom.tb.l
        public o8 e() {
            if (this.p == null) {
                this.p = o8.a(this.c.getMandatorySystemGestureInsets());
            }
            return this.p;
        }

        @Override // supwisdom.tb.l
        public o8 g() {
            if (this.o == null) {
                this.o = o8.a(this.c.getSystemGestureInsets());
            }
            return this.o;
        }

        @Override // supwisdom.tb.l
        public o8 i() {
            if (this.q == null) {
                this.q = o8.a(this.c.getTappableElementInsets());
            }
            return this.q;
        }

        public j(tb tbVar, j jVar) {
            super(tbVar, jVar);
            this.o = null;
            this.p = null;
            this.q = null;
        }
    }

    @Deprecated
    public tb b() {
        return this.f9271a.b();
    }

    public void b(o8 o8Var) {
        this.f9271a.b(o8Var);
    }

    @Deprecated
    public tb a() {
        return this.f9271a.a();
    }

    public tb a(int i2, int i3, int i4, int i5) {
        return this.f9271a.a(i2, i3, i4, i5);
    }

    public o8 a(int i2) {
        return this.f9271a.a(i2);
    }

    public tb(tb tbVar) {
        if (tbVar != null) {
            l lVar = tbVar.f9271a;
            if (Build.VERSION.SDK_INT >= 30 && (lVar instanceof k)) {
                this.f9271a = new k(this, (k) lVar);
            } else if (Build.VERSION.SDK_INT >= 29 && (lVar instanceof j)) {
                this.f9271a = new j(this, (j) lVar);
            } else if (Build.VERSION.SDK_INT >= 28 && (lVar instanceof i)) {
                this.f9271a = new i(this, (i) lVar);
            } else if (Build.VERSION.SDK_INT >= 21 && (lVar instanceof h)) {
                this.f9271a = new h(this, (h) lVar);
            } else if (Build.VERSION.SDK_INT >= 20 && (lVar instanceof g)) {
                this.f9271a = new g(this, (g) lVar);
            } else {
                this.f9271a = new l(this);
            }
            lVar.a(this);
            return;
        }
        this.f9271a = new l(this);
    }

    public static o8 a(o8 o8Var, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, o8Var.f8643a - i2);
        int iMax2 = Math.max(0, o8Var.b - i3);
        int iMax3 = Math.max(0, o8Var.c - i4);
        int iMax4 = Math.max(0, o8Var.d - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? o8Var : o8.a(iMax, iMax2, iMax3, iMax4);
    }

    public void a(o8[] o8VarArr) {
        this.f9271a.a(o8VarArr);
    }

    public void a(tb tbVar) {
        this.f9271a.b(tbVar);
    }

    public void a(o8 o8Var) {
        this.f9271a.a(o8Var);
    }

    public void a(View view) {
        this.f9271a.a(view);
    }
}
