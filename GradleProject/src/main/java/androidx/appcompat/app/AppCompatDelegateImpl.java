package androidx.appcompat.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.lifecycle.Lifecycle;
import java.lang.Thread;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import supwisdom.a1;
import supwisdom.a3;
import supwisdom.b1;
import supwisdom.c2;
import supwisdom.d2;
import supwisdom.gb;
import supwisdom.i1;
import supwisdom.ia;
import supwisdom.k1;
import supwisdom.k8;
import supwisdom.l1;
import supwisdom.lb;
import supwisdom.m1;
import supwisdom.mc;
import supwisdom.n1;
import supwisdom.o2;
import supwisdom.o7;
import supwisdom.p1;
import supwisdom.p3;
import supwisdom.p4;
import supwisdom.pb;
import supwisdom.q0;
import supwisdom.qb;
import supwisdom.rb;
import supwisdom.s0;
import supwisdom.t0;
import supwisdom.ta;
import supwisdom.tb;
import supwisdom.u0;
import supwisdom.u1;
import supwisdom.u3;
import supwisdom.ua;
import supwisdom.v0;
import supwisdom.v3;
import supwisdom.w0;
import supwisdom.w1;
import supwisdom.w2;
import supwisdom.x0;
import supwisdom.xd;
import supwisdom.y7;
import supwisdom.z0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDelegateImpl extends t0 implements w1.a, LayoutInflater.Factory2 {
    public static final p4<String, Integer> a0 = new p4<>();
    public static final boolean b0;
    public static final int[] c0;
    public static final boolean d0;
    public static final boolean e0;
    public static boolean f0;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public PanelFeatureState[] G;
    public PanelFeatureState H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public int N;
    public int O;
    public boolean P;
    public boolean Q;
    public m R;
    public m S;
    public boolean T;
    public int U;
    public final Runnable V;
    public boolean W;
    public Rect X;
    public Rect Y;
    public v0 Z;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f1098e;
    public Window f;
    public k g;
    public final s0 h;
    public ActionBar i;
    public MenuInflater j;
    public CharSequence k;
    public w2 l;
    public i m;
    public t n;
    public i1 o;
    public ActionBarContextView p;
    public PopupWindow q;
    public Runnable r;
    public pb s;
    public boolean t;
    public boolean u;
    public ViewGroup v;
    public TextView w;
    public View x;
    public boolean y;
    public boolean z;

    public class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f1102a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f1102a = uncaughtExceptionHandler;
        }

        public final boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f1102a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f1102a.uncaughtException(thread, notFoundException);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.U & 1) != 0) {
                appCompatDelegateImpl.f(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.U & 4096) != 0) {
                appCompatDelegateImpl2.f(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.T = false;
            appCompatDelegateImpl3.U = 0;
        }
    }

    public class c implements gb {
        public c() {
        }

        @Override // supwisdom.gb
        public tb a(View view, tb tbVar) {
            int i = tbVar.i();
            int iA = AppCompatDelegateImpl.this.a(tbVar, (Rect) null);
            if (i != iA) {
                tbVar = tbVar.b(tbVar.g(), iA, tbVar.h(), tbVar.f());
            }
            return lb.b(view, tbVar);
        }
    }

    public class d implements a3.a {
        public d() {
        }

        @Override // supwisdom.a3.a
        public void a(Rect rect) {
            rect.top = AppCompatDelegateImpl.this.a((tb) null, rect);
        }
    }

    public class e implements ContentFrameLayout.a {
        public e() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.q();
        }
    }

    public class f implements Runnable {

        public class a extends rb {
            public a() {
            }

            @Override // supwisdom.qb
            public void b(View view) {
                AppCompatDelegateImpl.this.p.setAlpha(1.0f);
                AppCompatDelegateImpl.this.s.a((qb) null);
                AppCompatDelegateImpl.this.s = null;
            }

            @Override // supwisdom.rb, supwisdom.qb
            public void c(View view) {
                AppCompatDelegateImpl.this.p.setVisibility(0);
            }
        }

        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.q.showAtLocation(appCompatDelegateImpl.p, 55, 0, 0);
            AppCompatDelegateImpl.this.r();
            if (!AppCompatDelegateImpl.this.C()) {
                AppCompatDelegateImpl.this.p.setAlpha(1.0f);
                AppCompatDelegateImpl.this.p.setVisibility(0);
                return;
            }
            AppCompatDelegateImpl.this.p.setAlpha(0.0f);
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            pb pbVarA = lb.a(appCompatDelegateImpl2.p);
            pbVarA.a(1.0f);
            appCompatDelegateImpl2.s = pbVarA;
            AppCompatDelegateImpl.this.s.a(new a());
        }
    }

    public class g extends rb {
        public g() {
        }

        @Override // supwisdom.qb
        public void b(View view) {
            AppCompatDelegateImpl.this.p.setAlpha(1.0f);
            AppCompatDelegateImpl.this.s.a((qb) null);
            AppCompatDelegateImpl.this.s = null;
        }

        @Override // supwisdom.rb, supwisdom.qb
        public void c(View view) {
            AppCompatDelegateImpl.this.p.setVisibility(0);
            AppCompatDelegateImpl.this.p.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.p.getParent() instanceof View) {
                lb.R((View) AppCompatDelegateImpl.this.p.getParent());
            }
        }
    }

    public class h implements q0 {
        public h(AppCompatDelegateImpl appCompatDelegateImpl) {
        }
    }

    public class j implements i1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public i1.a f1111a;

        public class a extends rb {
            public a() {
            }

            @Override // supwisdom.qb
            public void b(View view) {
                AppCompatDelegateImpl.this.p.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.q;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.p.getParent() instanceof View) {
                    lb.R((View) AppCompatDelegateImpl.this.p.getParent());
                }
                AppCompatDelegateImpl.this.p.removeAllViews();
                AppCompatDelegateImpl.this.s.a((qb) null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.s = null;
                lb.R(appCompatDelegateImpl2.v);
            }
        }

        public j(i1.a aVar) {
            this.f1111a = aVar;
        }

        @Override // supwisdom.i1.a
        public boolean a(i1 i1Var, Menu menu) {
            return this.f1111a.a(i1Var, menu);
        }

        @Override // supwisdom.i1.a
        public boolean b(i1 i1Var, Menu menu) {
            lb.R(AppCompatDelegateImpl.this.v);
            return this.f1111a.b(i1Var, menu);
        }

        @Override // supwisdom.i1.a
        public boolean a(i1 i1Var, MenuItem menuItem) {
            return this.f1111a.a(i1Var, menuItem);
        }

        @Override // supwisdom.i1.a
        public void a(i1 i1Var) {
            this.f1111a.a(i1Var);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.q != null) {
                appCompatDelegateImpl.f.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.r);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.p != null) {
                appCompatDelegateImpl2.r();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                pb pbVarA = lb.a(appCompatDelegateImpl3.p);
                pbVarA.a(0.0f);
                appCompatDelegateImpl3.s = pbVarA;
                AppCompatDelegateImpl.this.s.a(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            s0 s0Var = appCompatDelegateImpl4.h;
            if (s0Var != null) {
                s0Var.onSupportActionModeFinished(appCompatDelegateImpl4.o);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.o = null;
            lb.R(appCompatDelegateImpl5.v);
        }
    }

    public class l extends m {
        public final PowerManager c;

        public l(Context context) {
            super();
            this.c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public IntentFilter b() {
            if (Build.VERSION.SDK_INT < 21) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public int c() {
            return (Build.VERSION.SDK_INT < 21 || !this.c.isPowerSaveMode()) ? 1 : 2;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.l();
        }
    }

    public abstract class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public BroadcastReceiver f1113a;

        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                m.this.d();
            }
        }

        public m() {
        }

        public void a() {
            BroadcastReceiver broadcastReceiver = this.f1113a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f1098e.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f1113a = null;
            }
        }

        public abstract IntentFilter b();

        public abstract int c();

        public abstract void d();

        public void e() {
            a();
            IntentFilter intentFilterB = b();
            if (intentFilterB == null || intentFilterB.countActions() == 0) {
                return;
            }
            if (this.f1113a == null) {
                this.f1113a = new a();
            }
            AppCompatDelegateImpl.this.f1098e.registerReceiver(this.f1113a, intentFilterB);
        }
    }

    public class n extends m {
        public final z0 c;

        public n(z0 z0Var) {
            super();
            this.c = z0Var;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(com.igexin.push.core.b.N);
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public int c() {
            return this.c.b() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.m
        public void d() {
            AppCompatDelegateImpl.this.l();
        }
    }

    public static class o {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i = configuration.densityDpi;
            int i2 = configuration2.densityDpi;
            if (i != i2) {
                configuration3.densityDpi = i2;
            }
        }
    }

    public static class p {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    public static class q {
        public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i = configuration.colorMode & 3;
            int i2 = configuration2.colorMode;
            if (i != (i2 & 3)) {
                configuration3.colorMode |= i2 & 3;
            }
            int i3 = configuration.colorMode & 12;
            int i4 = configuration2.colorMode;
            if (i3 != (i4 & 12)) {
                configuration3.colorMode |= i4 & 12;
            }
        }
    }

    public static class r {
        public static void a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    public class s extends ContentFrameLayout {
        public s(Context context) {
            super(context);
        }

        public final boolean a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.e(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i) {
            setBackgroundDrawable(b1.c(getContext(), i));
        }
    }

    static {
        b0 = Build.VERSION.SDK_INT < 21;
        c0 = new int[]{R.attr.windowBackground};
        d0 = !"robolectric".equals(Build.FINGERPRINT);
        e0 = Build.VERSION.SDK_INT >= 17;
        if (!b0 || f0) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
        f0 = true;
    }

    public AppCompatDelegateImpl(Activity activity, s0 s0Var) {
        this(activity, null, s0Var, activity);
    }

    public boolean A() {
        i1 i1Var = this.o;
        if (i1Var != null) {
            i1Var.a();
            return true;
        }
        ActionBar actionBarD = d();
        return actionBarD != null && actionBarD.f();
    }

    public final ActionBar B() {
        return this.i;
    }

    public final boolean C() {
        ViewGroup viewGroup;
        return this.u && (viewGroup = this.v) != null && lb.M(viewGroup);
    }

    public final void D() {
        if (this.u) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final AppCompatActivity E() {
        for (Context baseContext = this.f1098e; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof AppCompatActivity) {
                return (AppCompatActivity) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    @Override // supwisdom.t0
    public void a(Bundle bundle) {
        this.J = true;
        a(false);
        t();
        Object obj = this.d;
        if (obj instanceof Activity) {
            String strB = null;
            try {
                strB = o7.b((Activity) obj);
            } catch (IllegalArgumentException unused) {
            }
            if (strB != null) {
                ActionBar actionBarB = B();
                if (actionBarB == null) {
                    this.W = true;
                } else {
                    actionBarB.c(true);
                }
            }
            t0.a(this);
        }
        this.K = true;
    }

    public void a(ViewGroup viewGroup) {
    }

    @Override // supwisdom.t0
    public Context b(Context context) {
        this.J = true;
        int iA = a(context, n());
        if (e0 && (context instanceof ContextThemeWrapper)) {
            try {
                r.a((ContextThemeWrapper) context, a(context, iA, (Configuration) null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof k1) {
            try {
                ((k1) context).a(a(context, iA, (Configuration) null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!d0) {
            super.b(context);
            return context;
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationA = a(context, iA, configuration.equals(configuration2) ? null : a(configuration, configuration2));
            k1 k1Var = new k1(context, androidx.appcompat.R.style.Theme_AppCompat_Empty);
            k1Var.a(configurationA);
            boolean z = false;
            try {
                z = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z) {
                k8.d.a(k1Var.getTheme());
            }
            super.b(k1Var);
            return k1Var;
        } catch (PackageManager.NameNotFoundException e2) {
            throw new RuntimeException("Application failed to obtain resources from itself", e2);
        }
    }

    @Override // supwisdom.t0
    public MenuInflater c() {
        if (this.j == null) {
            x();
            ActionBar actionBar = this.i;
            this.j = new n1(actionBar != null ? actionBar.h() : this.f1098e);
        }
        return this.j;
    }

    @Override // supwisdom.t0
    public void c(Bundle bundle) {
    }

    @Override // supwisdom.t0
    public ActionBar d() {
        x();
        return this.i;
    }

    @Override // supwisdom.t0
    public void e() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1098e);
        if (layoutInflaterFrom.getFactory() == null) {
            ua.b(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // supwisdom.t0
    public void f() {
        ActionBar actionBarD = d();
        if (actionBarD == null || !actionBarD.j()) {
            g(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // supwisdom.t0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.d
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L9
            supwisdom.t0.b(r3)
        L9:
            boolean r0 = r3.T
            if (r0 == 0) goto L18
            android.view.Window r0 = r3.f
            android.view.View r0 = r0.getDecorView()
            java.lang.Runnable r1 = r3.V
            r0.removeCallbacks(r1)
        L18:
            r0 = 0
            r3.L = r0
            r0 = 1
            r3.M = r0
            int r0 = r3.N
            r1 = -100
            if (r0 == r1) goto L48
            java.lang.Object r0 = r3.d
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L48
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L48
            supwisdom.p4<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.a0
            java.lang.Object r1 = r3.d
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.N
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L57
        L48:
            supwisdom.p4<java.lang.String, java.lang.Integer> r0 = androidx.appcompat.app.AppCompatDelegateImpl.a0
            java.lang.Object r1 = r3.d
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L57:
            androidx.appcompat.app.ActionBar r0 = r3.i
            if (r0 == 0) goto L5e
            r0.k()
        L5e:
            r3.o()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.g():void");
    }

    @Override // supwisdom.t0
    public void h() {
        ActionBar actionBarD = d();
        if (actionBarD != null) {
            actionBarD.d(true);
        }
    }

    @Override // supwisdom.t0
    public void i() {
        this.L = true;
        l();
    }

    @Override // supwisdom.t0
    public void j() {
        this.L = false;
        ActionBar actionBarD = d();
        if (actionBarD != null) {
            actionBarD.d(false);
        }
    }

    public boolean l() {
        return a(true);
    }

    public final void m() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.v.findViewById(R.id.content);
        View decorView = this.f.getDecorView();
        contentFrameLayout.a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f1098e.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
            typedArrayObtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
            typedArrayObtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
            typedArrayObtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
            typedArrayObtainStyledAttributes.getValue(androidx.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public final int n() {
        int i2 = this.N;
        return i2 != -100 ? i2 : t0.k();
    }

    public final void o() {
        m mVar = this.R;
        if (mVar != null) {
            mVar.a();
        }
        m mVar2 = this.S;
        if (mVar2 != null) {
            mVar2.a();
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return a(view, str, context, attributeSet);
    }

    public final ViewGroup p() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f1098e.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
        if (!typedArrayObtainStyledAttributes.hasValue(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowNoTitle, false)) {
            b(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBar, false)) {
            b(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            b(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            b(10);
        }
        this.D = typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        t();
        this.f.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1098e);
        if (this.E) {
            viewGroup = this.C ? (ViewGroup) layoutInflaterFrom.inflate(androidx.appcompat.R.layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(androidx.appcompat.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.D) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(androidx.appcompat.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.B = false;
            this.A = false;
        } else if (this.A) {
            TypedValue typedValue = new TypedValue();
            this.f1098e.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new k1(this.f1098e, typedValue.resourceId) : this.f1098e).inflate(androidx.appcompat.R.layout.abc_screen_toolbar, (ViewGroup) null);
            w2 w2Var = (w2) viewGroup.findViewById(androidx.appcompat.R.id.decor_content_parent);
            this.l = w2Var;
            w2Var.setWindowCallback(w());
            if (this.B) {
                this.l.a(109);
            }
            if (this.y) {
                this.l.a(2);
            }
            if (this.z) {
                this.l.a(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.A + ", windowActionBarOverlay: " + this.B + ", android:windowIsFloating: " + this.D + ", windowActionModeOverlay: " + this.C + ", windowNoTitle: " + this.E + " }");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            lb.a(viewGroup, new c());
        } else if (viewGroup instanceof a3) {
            ((a3) viewGroup).setOnFitSystemWindowsListener(new d());
        }
        if (this.l == null) {
            this.w = (TextView) viewGroup.findViewById(androidx.appcompat.R.id.title);
        }
        v3.b(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(androidx.appcompat.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new e());
        return viewGroup;
    }

    public void q() {
        w1 w1Var;
        w2 w2Var = this.l;
        if (w2Var != null) {
            w2Var.g();
        }
        if (this.q != null) {
            this.f.getDecorView().removeCallbacks(this.r);
            if (this.q.isShowing()) {
                try {
                    this.q.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.q = null;
        }
        r();
        PanelFeatureState panelFeatureStateA = a(0, false);
        if (panelFeatureStateA == null || (w1Var = panelFeatureStateA.j) == null) {
            return;
        }
        w1Var.close();
    }

    public void r() {
        pb pbVar = this.s;
        if (pbVar != null) {
            pbVar.a();
        }
    }

    public final void s() {
        if (this.u) {
            return;
        }
        this.v = p();
        CharSequence charSequenceV = v();
        if (!TextUtils.isEmpty(charSequenceV)) {
            w2 w2Var = this.l;
            if (w2Var != null) {
                w2Var.setWindowTitle(charSequenceV);
            } else if (B() != null) {
                B().a(charSequenceV);
            } else {
                TextView textView = this.w;
                if (textView != null) {
                    textView.setText(charSequenceV);
                }
            }
        }
        m();
        a(this.v);
        this.u = true;
        PanelFeatureState panelFeatureStateA = a(0, false);
        if (this.M) {
            return;
        }
        if (panelFeatureStateA == null || panelFeatureStateA.j == null) {
            g(108);
        }
    }

    public final void t() {
        if (this.f == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                a(((Activity) obj).getWindow());
            }
        }
        if (this.f == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final Context u() {
        ActionBar actionBarD = d();
        Context contextH = actionBarD != null ? actionBarD.h() : null;
        return contextH == null ? this.f1098e : contextH;
    }

    public final CharSequence v() {
        Object obj = this.d;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.k;
    }

    public final Window.Callback w() {
        return this.f.getCallback();
    }

    public final void x() {
        s();
        if (this.A && this.i == null) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                this.i = new a1((Activity) this.d, this.B);
            } else if (obj instanceof Dialog) {
                this.i = new a1((Dialog) this.d);
            }
            ActionBar actionBar = this.i;
            if (actionBar != null) {
                actionBar.c(this.W);
            }
        }
    }

    public final boolean y() {
        if (!this.Q && (this.d instanceof Activity)) {
            PackageManager packageManager = this.f1098e.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f1098e, this.d.getClass()), Build.VERSION.SDK_INT >= 29 ? 269221888 : Build.VERSION.SDK_INT >= 24 ? 786432 : 0);
                this.P = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                this.P = false;
            }
        }
        this.Q = true;
        return this.P;
    }

    public boolean z() {
        return this.t;
    }

    public final class i implements c2.a {
        public i() {
        }

        @Override // supwisdom.c2.a
        public boolean a(w1 w1Var) {
            Window.Callback callbackW = AppCompatDelegateImpl.this.w();
            if (callbackW == null) {
                return true;
            }
            callbackW.onMenuOpened(108, w1Var);
            return true;
        }

        @Override // supwisdom.c2.a
        public void a(w1 w1Var, boolean z) {
            AppCompatDelegateImpl.this.b(w1Var);
        }
    }

    public AppCompatDelegateImpl(Dialog dialog, s0 s0Var) {
        this(dialog.getContext(), dialog.getWindow(), s0Var, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public static final class PanelFeatureState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1099a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1100e;
        public int f;
        public ViewGroup g;
        public View h;
        public View i;
        public w1 j;
        public u1 k;
        public Context l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public boolean q = false;
        public boolean r;
        public Bundle s;

        @SuppressLint({"BanParcelableUsage"})
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new a();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1101a;
            public boolean b;
            public Bundle c;

            public class a implements Parcelable.ClassLoaderCreator<SavedState> {
                @Override // android.os.Parcelable.Creator
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.ClassLoaderCreator
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }
            }

            public static SavedState a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f1101a = parcel.readInt();
                boolean z = parcel.readInt() == 1;
                savedState.b = z;
                if (z) {
                    savedState.c = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1101a);
                parcel.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    parcel.writeBundle(this.c);
                }
            }
        }

        public PanelFeatureState(int i) {
            this.f1099a = i;
        }

        public boolean a() {
            if (this.h == null) {
                return false;
            }
            return this.i != null || this.k.c().getCount() > 0;
        }

        public void a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(androidx.appcompat.R.attr.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                themeNewTheme.applyStyle(i, true);
            }
            themeNewTheme.resolveAttribute(androidx.appcompat.R.attr.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            } else {
                themeNewTheme.applyStyle(androidx.appcompat.R.style.Theme_AppCompat_CompactMenu, true);
            }
            k1 k1Var = new k1(context, 0);
            k1Var.getTheme().setTo(themeNewTheme);
            this.l = k1Var;
            TypedArray typedArrayObtainStyledAttributes = k1Var.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme);
            this.b = typedArrayObtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AppCompatTheme_panelBackground, 0);
            this.f = typedArrayObtainStyledAttributes.getResourceId(androidx.appcompat.R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(w1 w1Var) {
            u1 u1Var;
            w1 w1Var2 = this.j;
            if (w1Var == w1Var2) {
                return;
            }
            if (w1Var2 != null) {
                w1Var2.b(this.k);
            }
            this.j = w1Var;
            if (w1Var == null || (u1Var = this.k) == null) {
                return;
            }
            w1Var.a(u1Var);
        }

        public d2 a(c2.a aVar) {
            if (this.j == null) {
                return null;
            }
            if (this.k == null) {
                u1 u1Var = new u1(this.l, androidx.appcompat.R.layout.abc_list_menu_item_layout);
                this.k = u1Var;
                u1Var.a(aVar);
                this.j.a(this.k);
            }
            return this.k.a(this.g);
        }
    }

    public AppCompatDelegateImpl(Context context, Window window, s0 s0Var, Object obj) {
        Integer num;
        AppCompatActivity appCompatActivityE;
        this.s = null;
        this.t = true;
        this.N = -100;
        this.V = new b();
        this.f1098e = context;
        this.h = s0Var;
        this.d = obj;
        if (this.N == -100 && (obj instanceof Dialog) && (appCompatActivityE = E()) != null) {
            this.N = appCompatActivityE.getDelegate().b();
        }
        if (this.N == -100 && (num = a0.get(this.d.getClass().getName())) != null) {
            this.N = num.intValue();
            a0.remove(this.d.getClass().getName());
        }
        if (window != null) {
            a(window);
        }
        o2.c();
    }

    @Override // supwisdom.t0
    public void d(int i2) {
        this.O = i2;
    }

    public void h(int i2) {
        ActionBar actionBarD;
        if (i2 != 108 || (actionBarD = d()) == null) {
            return;
        }
        actionBarD.b(true);
    }

    public void i(int i2) {
        if (i2 == 108) {
            ActionBar actionBarD = d();
            if (actionBarD != null) {
                actionBarD.b(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            PanelFeatureState panelFeatureStateA = a(i2, true);
            if (panelFeatureStateA.o) {
                a(panelFeatureStateA, false);
            }
        }
    }

    public class k extends p1 {
        public k(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode a(ActionMode.Callback callback) {
            m1.a aVar = new m1.a(AppCompatDelegateImpl.this.f1098e, callback);
            i1 i1VarA = AppCompatDelegateImpl.this.a(aVar);
            if (i1VarA != null) {
                return aVar.b(i1VarA);
            }
            return null;
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.c(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof w1)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImpl.this.h(i);
            return true;
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImpl.this.i(i);
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            w1 w1Var = menu instanceof w1 ? (w1) menu : null;
            if (i == 0 && w1Var == null) {
                return false;
            }
            if (w1Var != null) {
                w1Var.d(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (w1Var != null) {
                w1Var.d(false);
            }
            return zOnPreparePanel;
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            w1 w1Var;
            PanelFeatureState panelFeatureStateA = AppCompatDelegateImpl.this.a(0, true);
            if (panelFeatureStateA == null || (w1Var = panelFeatureStateA.j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, w1Var, i);
            }
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.z() ? a(callback) : super.onWindowStartingActionMode(callback);
        }

        @Override // supwisdom.p1, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            if (AppCompatDelegateImpl.this.z() && i == 0) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback, i);
        }
    }

    public boolean d(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z = this.I;
            this.I = false;
            PanelFeatureState panelFeatureStateA = a(0, false);
            if (panelFeatureStateA != null && panelFeatureStateA.o) {
                if (!z) {
                    a(panelFeatureStateA, true);
                }
                return true;
            }
            if (A()) {
                return true;
            }
        } else if (i2 == 82) {
            e(0, keyEvent);
            return true;
        }
        return false;
    }

    public void f(int i2) {
        PanelFeatureState panelFeatureStateA;
        PanelFeatureState panelFeatureStateA2 = a(i2, true);
        if (panelFeatureStateA2.j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateA2.j.e(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateA2.s = bundle;
            }
            panelFeatureStateA2.j.s();
            panelFeatureStateA2.j.clear();
        }
        panelFeatureStateA2.r = true;
        panelFeatureStateA2.q = true;
        if ((i2 != 108 && i2 != 0) || this.l == null || (panelFeatureStateA = a(0, false)) == null) {
            return;
        }
        panelFeatureStateA.m = false;
        b(panelFeatureStateA, (KeyEvent) null);
    }

    public final int j(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i2 != 9) {
            return i2;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    public final class t implements c2.a {
        public t() {
        }

        @Override // supwisdom.c2.a
        public void a(w1 w1Var, boolean z) {
            w1 w1VarM = w1Var.m();
            boolean z2 = w1VarM != w1Var;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z2) {
                w1Var = w1VarM;
            }
            PanelFeatureState panelFeatureStateA = appCompatDelegateImpl.a((Menu) w1Var);
            if (panelFeatureStateA != null) {
                if (!z2) {
                    AppCompatDelegateImpl.this.a(panelFeatureStateA, z);
                } else {
                    AppCompatDelegateImpl.this.a(panelFeatureStateA.f1099a, panelFeatureStateA, w1VarM);
                    AppCompatDelegateImpl.this.a(panelFeatureStateA, true);
                }
            }
        }

        @Override // supwisdom.c2.a
        public boolean a(w1 w1Var) {
            Window.Callback callbackW;
            if (w1Var != w1Var.m()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.A || (callbackW = appCompatDelegateImpl.w()) == null || AppCompatDelegateImpl.this.M) {
                return true;
            }
            callbackW.onMenuOpened(108, w1Var);
            return true;
        }
    }

    @Override // supwisdom.t0
    public void c(int i2) {
        s();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f1098e).inflate(i2, viewGroup);
        this.g.a().onContentChanged();
    }

    public void e(int i2) {
        a(a(i2, true), true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e(int r4, android.view.KeyEvent r5) {
        /*
            r3 = this;
            supwisdom.i1 r0 = r3.o
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState r2 = r3.a(r4, r0)
            if (r4 != 0) goto L43
            supwisdom.w2 r4 = r3.l
            if (r4 == 0) goto L43
            boolean r4 = r4.c()
            if (r4 == 0) goto L43
            android.content.Context r4 = r3.f1098e
            android.view.ViewConfiguration r4 = android.view.ViewConfiguration.get(r4)
            boolean r4 = r4.hasPermanentMenuKey()
            if (r4 != 0) goto L43
            supwisdom.w2 r4 = r3.l
            boolean r4 = r4.a()
            if (r4 != 0) goto L3c
            boolean r4 = r3.M
            if (r4 != 0) goto L62
            boolean r4 = r3.b(r2, r5)
            if (r4 == 0) goto L62
            supwisdom.w2 r4 = r3.l
            boolean r0 = r4.f()
            goto L6a
        L3c:
            supwisdom.w2 r4 = r3.l
            boolean r0 = r4.e()
            goto L6a
        L43:
            boolean r4 = r2.o
            if (r4 != 0) goto L64
            boolean r4 = r2.n
            if (r4 == 0) goto L4c
            goto L64
        L4c:
            boolean r4 = r2.m
            if (r4 == 0) goto L62
            boolean r4 = r2.r
            if (r4 == 0) goto L5b
            r2.m = r1
            boolean r4 = r3.b(r2, r5)
            goto L5c
        L5b:
            r4 = 1
        L5c:
            if (r4 == 0) goto L62
            r3.a(r2, r5)
            goto L6a
        L62:
            r0 = 0
            goto L6a
        L64:
            boolean r4 = r2.o
            r3.a(r2, r0)
            r0 = r4
        L6a:
            if (r0 == 0) goto L87
            android.content.Context r4 = r3.f1098e
            android.content.Context r4 = r4.getApplicationContext()
            java.lang.String r5 = "audio"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.media.AudioManager r4 = (android.media.AudioManager) r4
            if (r4 == 0) goto L80
            r4.playSoundEffect(r1)
            goto L87
        L80:
            java.lang.String r4 = "AppCompatDelegate"
            java.lang.String r5 = "Couldn't get audio manager"
            android.util.Log.w(r4, r5)
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.e(int, android.view.KeyEvent):boolean");
    }

    @Override // supwisdom.t0
    public void a(Toolbar toolbar) {
        if (this.d instanceof Activity) {
            ActionBar actionBarD = d();
            if (!(actionBarD instanceof a1)) {
                this.j = null;
                if (actionBarD != null) {
                    actionBarD.k();
                }
                if (toolbar != null) {
                    x0 x0Var = new x0(toolbar, v(), this.g);
                    this.i = x0Var;
                    this.f.setCallback(x0Var.o());
                } else {
                    this.i = null;
                    this.f.setCallback(this.g);
                }
                f();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    public boolean c(int i2, KeyEvent keyEvent) {
        ActionBar actionBarD = d();
        if (actionBarD != null && actionBarD.a(i2, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.H;
        if (panelFeatureState != null && a(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.H;
            if (panelFeatureState2 != null) {
                panelFeatureState2.n = true;
            }
            return true;
        }
        if (this.H == null) {
            PanelFeatureState panelFeatureStateA = a(0, true);
            b(panelFeatureStateA, keyEvent);
            boolean zA = a(panelFeatureStateA, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateA.m = false;
            if (zA) {
                return true;
            }
        }
        return false;
    }

    public final m d(Context context) {
        if (this.R == null) {
            this.R = new n(z0.a(context));
        }
        return this.R;
    }

    public final void g(int i2) {
        this.U = (1 << i2) | this.U;
        if (this.T) {
            return;
        }
        lb.a(this.f.getDecorView(), this.V);
        this.T = true;
    }

    public final boolean c(PanelFeatureState panelFeatureState) {
        Context context = this.f1098e;
        int i2 = panelFeatureState.f1099a;
        if ((i2 == 0 || i2 == 108) && this.l != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
            Resources.Theme themeNewTheme = null;
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                k1 k1Var = new k1(context, 0);
                k1Var.getTheme().setTo(themeNewTheme);
                context = k1Var;
            }
        }
        w1 w1Var = new w1(context);
        w1Var.a(this);
        panelFeatureState.a(w1Var);
        return true;
    }

    @Override // supwisdom.t0
    public <T extends View> T a(int i2) {
        s();
        return (T) this.f.findViewById(i2);
    }

    @Override // supwisdom.t0
    public void a(Configuration configuration) {
        ActionBar actionBarD;
        if (this.A && this.u && (actionBarD = d()) != null) {
            actionBarD.a(configuration);
        }
        o2.b().a(this.f1098e);
        a(false);
    }

    @Override // supwisdom.t0
    public void b(Bundle bundle) {
        s();
    }

    @Override // supwisdom.t0
    public void b(View view, ViewGroup.LayoutParams layoutParams) {
        s();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    @Override // supwisdom.t0
    public void a(View view) {
        s();
        ViewGroup viewGroup = (ViewGroup) this.v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.g.a().onContentChanged();
    }

    @Override // supwisdom.t0
    public boolean b(int i2) {
        int iJ = j(i2);
        if (this.E && iJ == 108) {
            return false;
        }
        if (this.A && iJ == 1) {
            this.A = false;
        }
        if (iJ == 1) {
            D();
            this.E = true;
            return true;
        }
        if (iJ == 2) {
            D();
            this.y = true;
            return true;
        }
        if (iJ == 5) {
            D();
            this.z = true;
            return true;
        }
        if (iJ == 10) {
            D();
            this.C = true;
            return true;
        }
        if (iJ == 108) {
            D();
            this.A = true;
            return true;
        }
        if (iJ != 109) {
            return this.f.requestFeature(iJ);
        }
        D();
        this.B = true;
        return true;
    }

    @Override // supwisdom.t0
    public void a(View view, ViewGroup.LayoutParams layoutParams) {
        s();
        ((ViewGroup) this.v.findViewById(R.id.content)).addView(view, layoutParams);
        this.g.a().onContentChanged();
    }

    public final void a(Window window) {
        if (this.f == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof k)) {
                k kVar = new k(callback);
                this.g = kVar;
                window.setCallback(kVar);
                p3 p3VarA = p3.a(this.f1098e, (AttributeSet) null, c0);
                Drawable drawableC = p3VarA.c(0);
                if (drawableC != null) {
                    window.setBackgroundDrawable(drawableC);
                }
                p3VarA.b();
                this.f = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    public final m c(Context context) {
        if (this.S == null) {
            this.S = new l(context);
        }
        return this.S;
    }

    public i1 b(i1.a aVar) {
        i1 i1VarOnWindowStartingSupportActionMode;
        Context k1Var;
        s0 s0Var;
        r();
        i1 i1Var = this.o;
        if (i1Var != null) {
            i1Var.a();
        }
        if (!(aVar instanceof j)) {
            aVar = new j(aVar);
        }
        s0 s0Var2 = this.h;
        if (s0Var2 == null || this.M) {
            i1VarOnWindowStartingSupportActionMode = null;
        } else {
            try {
                i1VarOnWindowStartingSupportActionMode = s0Var2.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
                i1VarOnWindowStartingSupportActionMode = null;
            }
        }
        if (i1VarOnWindowStartingSupportActionMode != null) {
            this.o = i1VarOnWindowStartingSupportActionMode;
        } else {
            if (this.p == null) {
                if (this.D) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f1098e.getTheme();
                    theme.resolveAttribute(androidx.appcompat.R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f1098e.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        k1Var = new k1(this.f1098e, 0);
                        k1Var.getTheme().setTo(themeNewTheme);
                    } else {
                        k1Var = this.f1098e;
                    }
                    this.p = new ActionBarContextView(k1Var);
                    PopupWindow popupWindow = new PopupWindow(k1Var, (AttributeSet) null, androidx.appcompat.R.attr.actionModePopupWindowStyle);
                    this.q = popupWindow;
                    mc.a(popupWindow, 2);
                    this.q.setContentView(this.p);
                    this.q.setWidth(-1);
                    k1Var.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarSize, typedValue, true);
                    this.p.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, k1Var.getResources().getDisplayMetrics()));
                    this.q.setHeight(-2);
                    this.r = new f();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.v.findViewById(androidx.appcompat.R.id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(u()));
                        this.p = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.p != null) {
                r();
                this.p.d();
                l1 l1Var = new l1(this.p.getContext(), this.p, aVar, this.q == null);
                if (aVar.a(l1Var, l1Var.c())) {
                    l1Var.i();
                    this.p.a(l1Var);
                    this.o = l1Var;
                    if (C()) {
                        this.p.setAlpha(0.0f);
                        pb pbVarA = lb.a(this.p);
                        pbVarA.a(1.0f);
                        this.s = pbVarA;
                        pbVarA.a(new g());
                    } else {
                        this.p.setAlpha(1.0f);
                        this.p.setVisibility(0);
                        this.p.sendAccessibilityEvent(32);
                        if (this.p.getParent() instanceof View) {
                            lb.R((View) this.p.getParent());
                        }
                    }
                    if (this.q != null) {
                        this.f.getDecorView().post(this.r);
                    }
                } else {
                    this.o = null;
                }
            }
        }
        i1 i1Var2 = this.o;
        if (i1Var2 != null && (s0Var = this.h) != null) {
            s0Var.onSupportActionModeStarted(i1Var2);
        }
        return this.o;
    }

    @Override // supwisdom.t0
    public final void a(CharSequence charSequence) {
        this.k = charSequence;
        w2 w2Var = this.l;
        if (w2Var != null) {
            w2Var.setWindowTitle(charSequence);
            return;
        }
        if (B() != null) {
            B().a(charSequence);
            return;
        }
        TextView textView = this.w;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // supwisdom.w1.a
    public boolean a(w1 w1Var, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateA;
        Window.Callback callbackW = w();
        if (callbackW == null || this.M || (panelFeatureStateA = a((Menu) w1Var.m())) == null) {
            return false;
        }
        return callbackW.onMenuItemSelected(panelFeatureStateA.f1099a, menuItem);
    }

    @Override // supwisdom.w1.a
    public void a(w1 w1Var) {
        b(true);
    }

    @Override // supwisdom.t0
    public i1 a(i1.a aVar) {
        s0 s0Var;
        if (aVar != null) {
            i1 i1Var = this.o;
            if (i1Var != null) {
                i1Var.a();
            }
            j jVar = new j(aVar);
            ActionBar actionBarD = d();
            if (actionBarD != null) {
                i1 i1VarA = actionBarD.a(jVar);
                this.o = i1VarA;
                if (i1VarA != null && (s0Var = this.h) != null) {
                    s0Var.onSupportActionModeStarted(i1VarA);
                }
            }
            if (this.o == null) {
                this.o = b(jVar);
            }
            return this.o;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    public boolean a(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.d;
        if (((obj instanceof ta.a) || (obj instanceof u0)) && (decorView = this.f.getDecorView()) != null && ta.a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.g.a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? a(keyCode, keyEvent) : d(keyCode, keyEvent);
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.I = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            b(0, keyEvent);
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean zA = false;
        if (this.Z == null) {
            String string = this.f1098e.obtainStyledAttributes(androidx.appcompat.R.styleable.AppCompatTheme).getString(androidx.appcompat.R.styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.Z = new v0();
            } else {
                try {
                    this.Z = (v0) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.Z = new v0();
                }
            }
        }
        if (b0) {
            if (attributeSet instanceof XmlPullParser) {
                if (((XmlPullParser) attributeSet).getDepth() > 1) {
                    zA = true;
                }
            } else {
                zA = a((ViewParent) view);
            }
            z = zA;
        } else {
            z = false;
        }
        return this.Z.createView(view, str, context, attributeSet, z, b0, true, u3.b());
    }

    public final boolean a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || lb.K((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    public final void a(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.o || this.M) {
            return;
        }
        if (panelFeatureState.f1099a == 0) {
            if ((this.f1098e.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackW = w();
        if (callbackW != null && !callbackW.onMenuOpened(panelFeatureState.f1099a, panelFeatureState.j)) {
            a(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f1098e.getSystemService("window");
        if (windowManager != null && b(panelFeatureState, keyEvent)) {
            if (panelFeatureState.g != null && !panelFeatureState.q) {
                View view = panelFeatureState.i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                panelFeatureState.n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.f1100e, 1002, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.c;
                layoutParams2.windowAnimations = panelFeatureState.f;
                windowManager.addView(panelFeatureState.g, layoutParams2);
                panelFeatureState.o = true;
            }
            ViewGroup viewGroup = panelFeatureState.g;
            if (viewGroup == null) {
                if (!b(panelFeatureState) || panelFeatureState.g == null) {
                    return;
                }
            } else if (panelFeatureState.q && viewGroup.getChildCount() > 0) {
                panelFeatureState.g.removeAllViews();
            }
            if (a(panelFeatureState) && panelFeatureState.a()) {
                ViewGroup.LayoutParams layoutParams3 = panelFeatureState.h.getLayoutParams();
                if (layoutParams3 == null) {
                    layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
                }
                panelFeatureState.g.setBackgroundResource(panelFeatureState.b);
                ViewParent parent = panelFeatureState.h.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(panelFeatureState.h);
                }
                panelFeatureState.g.addView(panelFeatureState.h, layoutParams3);
                if (!panelFeatureState.h.hasFocus()) {
                    panelFeatureState.h.requestFocus();
                }
            } else {
                panelFeatureState.q = true;
                return;
            }
            i2 = -2;
            panelFeatureState.n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, panelFeatureState.d, panelFeatureState.f1100e, 1002, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.c;
            layoutParams22.windowAnimations = panelFeatureState.f;
            windowManager.addView(panelFeatureState.g, layoutParams22);
            panelFeatureState.o = true;
        }
    }

    public final boolean b(PanelFeatureState panelFeatureState) {
        panelFeatureState.a(u());
        panelFeatureState.g = new s(panelFeatureState.l);
        panelFeatureState.c = 81;
        return true;
    }

    public final void b(boolean z) {
        w2 w2Var = this.l;
        if (w2Var != null && w2Var.c() && (!ViewConfiguration.get(this.f1098e).hasPermanentMenuKey() || this.l.d())) {
            Window.Callback callbackW = w();
            if (this.l.a() && z) {
                this.l.e();
                if (this.M) {
                    return;
                }
                callbackW.onPanelClosed(108, a(0, true).j);
                return;
            }
            if (callbackW == null || this.M) {
                return;
            }
            if (this.T && (this.U & 1) != 0) {
                this.f.getDecorView().removeCallbacks(this.V);
                this.V.run();
            }
            PanelFeatureState panelFeatureStateA = a(0, true);
            w1 w1Var = panelFeatureStateA.j;
            if (w1Var == null || panelFeatureStateA.r || !callbackW.onPreparePanel(0, panelFeatureStateA.i, w1Var)) {
                return;
            }
            callbackW.onMenuOpened(108, panelFeatureStateA.j);
            this.l.f();
            return;
        }
        PanelFeatureState panelFeatureStateA2 = a(0, true);
        panelFeatureStateA2.q = true;
        a(panelFeatureStateA2, false);
        a(panelFeatureStateA2, (KeyEvent) null);
    }

    public final boolean b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        w2 w2Var;
        w2 w2Var2;
        w2 w2Var3;
        if (this.M) {
            return false;
        }
        if (panelFeatureState.m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.H;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            a(panelFeatureState2, false);
        }
        Window.Callback callbackW = w();
        if (callbackW != null) {
            panelFeatureState.i = callbackW.onCreatePanelView(panelFeatureState.f1099a);
        }
        int i2 = panelFeatureState.f1099a;
        boolean z = i2 == 0 || i2 == 108;
        if (z && (w2Var3 = this.l) != null) {
            w2Var3.b();
        }
        if (panelFeatureState.i == null && (!z || !(B() instanceof x0))) {
            if (panelFeatureState.j == null || panelFeatureState.r) {
                if (panelFeatureState.j == null && (!c(panelFeatureState) || panelFeatureState.j == null)) {
                    return false;
                }
                if (z && this.l != null) {
                    if (this.m == null) {
                        this.m = new i();
                    }
                    this.l.a(panelFeatureState.j, this.m);
                }
                panelFeatureState.j.s();
                if (!callbackW.onCreatePanelMenu(panelFeatureState.f1099a, panelFeatureState.j)) {
                    panelFeatureState.a((w1) null);
                    if (z && (w2Var = this.l) != null) {
                        w2Var.a(null, this.m);
                    }
                    return false;
                }
                panelFeatureState.r = false;
            }
            panelFeatureState.j.s();
            Bundle bundle = panelFeatureState.s;
            if (bundle != null) {
                panelFeatureState.j.c(bundle);
                panelFeatureState.s = null;
            }
            if (!callbackW.onPreparePanel(0, panelFeatureState.i, panelFeatureState.j)) {
                if (z && (w2Var2 = this.l) != null) {
                    w2Var2.a(null, this.m);
                }
                panelFeatureState.j.r();
                return false;
            }
            boolean z2 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.p = z2;
            panelFeatureState.j.setQwertyMode(z2);
            panelFeatureState.j.r();
        }
        panelFeatureState.m = true;
        panelFeatureState.n = false;
        this.H = panelFeatureState;
        return true;
    }

    public final boolean a(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.i;
        if (view != null) {
            panelFeatureState.h = view;
            return true;
        }
        if (panelFeatureState.j == null) {
            return false;
        }
        if (this.n == null) {
            this.n = new t();
        }
        View view2 = (View) panelFeatureState.a(this.n);
        panelFeatureState.h = view2;
        return view2 != null;
    }

    public void a(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup viewGroup;
        w2 w2Var;
        if (z && panelFeatureState.f1099a == 0 && (w2Var = this.l) != null && w2Var.a()) {
            b(panelFeatureState.j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f1098e.getSystemService("window");
        if (windowManager != null && panelFeatureState.o && (viewGroup = panelFeatureState.g) != null) {
            windowManager.removeView(viewGroup);
            if (z) {
                a(panelFeatureState.f1099a, panelFeatureState, (Menu) null);
            }
        }
        panelFeatureState.m = false;
        panelFeatureState.n = false;
        panelFeatureState.o = false;
        panelFeatureState.h = null;
        panelFeatureState.q = true;
        if (this.H == panelFeatureState) {
            this.H = null;
        }
    }

    public void a(int i2, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i2 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.G;
                if (i2 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i2];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.o) && !this.M) {
            this.g.a().onPanelClosed(i2, menu);
        }
    }

    public PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.G;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
            if (panelFeatureState != null && panelFeatureState.j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    public PanelFeatureState a(int i2, boolean z) {
        PanelFeatureState[] panelFeatureStateArr = this.G;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i2) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i2 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.G = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i2];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i2);
        panelFeatureStateArr[i2] = panelFeatureState2;
        return panelFeatureState2;
    }

    public void b(w1 w1Var) {
        if (this.F) {
            return;
        }
        this.F = true;
        this.l.g();
        Window.Callback callbackW = w();
        if (callbackW != null && !this.M) {
            callbackW.onPanelClosed(108, w1Var);
        }
        this.F = false;
    }

    public final boolean a(PanelFeatureState panelFeatureState, int i2, KeyEvent keyEvent, int i3) {
        w1 w1Var;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.m || b(panelFeatureState, keyEvent)) && (w1Var = panelFeatureState.j) != null) {
            zPerformShortcut = w1Var.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.l == null) {
            a(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    public final boolean b(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateA = a(i2, true);
        if (panelFeatureStateA.o) {
            return false;
        }
        return b(panelFeatureStateA, keyEvent);
    }

    public final int a(tb tbVar, Rect rect) {
        int i2;
        boolean z;
        boolean z2;
        if (tbVar != null) {
            i2 = tbVar.i();
        } else {
            i2 = rect != null ? rect.top : 0;
        }
        ActionBarContextView actionBarContextView = this.p;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.p.getLayoutParams();
            if (this.p.isShown()) {
                if (this.X == null) {
                    this.X = new Rect();
                    this.Y = new Rect();
                }
                Rect rect2 = this.X;
                Rect rect3 = this.Y;
                if (tbVar == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(tbVar.g(), tbVar.i(), tbVar.h(), tbVar.f());
                }
                v3.a(this.v, rect2, rect3);
                int i3 = rect2.top;
                int i4 = rect2.left;
                int i5 = rect2.right;
                tb tbVarT = lb.t(this.v);
                int iG = tbVarT == null ? 0 : tbVarT.g();
                int iH = tbVarT == null ? 0 : tbVarT.h();
                if (marginLayoutParams.topMargin == i3 && marginLayoutParams.leftMargin == i4 && marginLayoutParams.rightMargin == i5) {
                    z2 = false;
                } else {
                    marginLayoutParams.topMargin = i3;
                    marginLayoutParams.leftMargin = i4;
                    marginLayoutParams.rightMargin = i5;
                    z2 = true;
                }
                if (i3 > 0 && this.x == null) {
                    View view = new View(this.f1098e);
                    this.x = view;
                    view.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iG;
                    layoutParams.rightMargin = iH;
                    this.v.addView(this.x, -1, layoutParams);
                } else {
                    View view2 = this.x;
                    if (view2 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                        if (marginLayoutParams2.height != marginLayoutParams.topMargin || marginLayoutParams2.leftMargin != iG || marginLayoutParams2.rightMargin != iH) {
                            marginLayoutParams2.height = marginLayoutParams.topMargin;
                            marginLayoutParams2.leftMargin = iG;
                            marginLayoutParams2.rightMargin = iH;
                            this.x.setLayoutParams(marginLayoutParams2);
                        }
                    }
                }
                z = this.x != null;
                if (z && this.x.getVisibility() != 0) {
                    b(this.x);
                }
                if (!this.C && z) {
                    i2 = 0;
                }
                z = z;
                z = z2;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z = false;
            } else {
                z = false;
                z = false;
            }
            if (z) {
                this.p.setLayoutParams(marginLayoutParams);
            }
        }
        View view3 = this.x;
        if (view3 != null) {
            view3.setVisibility(z ? 0 : 8);
        }
        return i2;
    }

    public final void b(View view) {
        int iA;
        if ((lb.D(view) & 8192) != 0) {
            iA = y7.a(this.f1098e, androidx.appcompat.R.color.abc_decor_view_status_guard_light);
        } else {
            iA = y7.a(this.f1098e, androidx.appcompat.R.color.abc_decor_view_status_guard);
        }
        view.setBackgroundColor(iA);
    }

    @Override // supwisdom.t0
    public int b() {
        return this.N;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(int r7, boolean r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.f1098e
            r1 = 0
            android.content.res.Configuration r0 = r6.a(r0, r7, r1)
            boolean r2 = r6.y()
            android.content.Context r3 = r6.f1098e
            android.content.res.Resources r3 = r3.getResources()
            android.content.res.Configuration r3 = r3.getConfiguration()
            int r3 = r3.uiMode
            r3 = r3 & 48
            int r0 = r0.uiMode
            r0 = r0 & 48
            r4 = 1
            if (r3 == r0) goto L47
            if (r8 == 0) goto L47
            if (r2 != 0) goto L47
            boolean r8 = r6.J
            if (r8 == 0) goto L47
            boolean r8 = androidx.appcompat.app.AppCompatDelegateImpl.d0
            if (r8 != 0) goto L30
            boolean r8 = r6.K
            if (r8 == 0) goto L47
        L30:
            java.lang.Object r8 = r6.d
            boolean r5 = r8 instanceof android.app.Activity
            if (r5 == 0) goto L47
            android.app.Activity r8 = (android.app.Activity) r8
            boolean r8 = r8.isChild()
            if (r8 != 0) goto L47
            java.lang.Object r8 = r6.d
            android.app.Activity r8 = (android.app.Activity) r8
            supwisdom.j7.d(r8)
            r8 = 1
            goto L48
        L47:
            r8 = 0
        L48:
            if (r8 != 0) goto L50
            if (r3 == r0) goto L50
            r6.a(r0, r2, r1)
            goto L51
        L50:
            r4 = r8
        L51:
            if (r4 == 0) goto L5e
            java.lang.Object r8 = r6.d
            boolean r0 = r8 instanceof androidx.appcompat.app.AppCompatActivity
            if (r0 == 0) goto L5e
            androidx.appcompat.app.AppCompatActivity r8 = (androidx.appcompat.app.AppCompatActivity) r8
            r8.onNightModeChanged(r7)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegateImpl.b(int, boolean):boolean");
    }

    public final boolean a(boolean z) {
        if (this.M) {
            return false;
        }
        int iN = n();
        boolean zB = b(a(this.f1098e, iN), z);
        if (iN == 0) {
            d(this.f1098e).e();
        } else {
            m mVar = this.R;
            if (mVar != null) {
                mVar.a();
            }
        }
        if (iN == 3) {
            c(this.f1098e).e();
        } else {
            m mVar2 = this.S;
            if (mVar2 != null) {
                mVar2.a();
            }
        }
        return zB;
    }

    public int a(Context context, int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 == 0) {
                if (Build.VERSION.SDK_INT < 23 || ((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    return d(context).c();
                }
                return -1;
            }
            if (i2 != 1 && i2 != 2) {
                if (i2 == 3) {
                    return c(context).c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i2;
    }

    public final Configuration a(Context context, int i2, Configuration configuration) {
        int i3;
        if (i2 != 1) {
            i3 = i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32;
        } else {
            i3 = 16;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(int i2, boolean z, Configuration configuration) {
        Resources resources = this.f1098e.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i2 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        if (Build.VERSION.SDK_INT < 26) {
            w0.a(resources);
        }
        int i3 = this.O;
        if (i3 != 0) {
            this.f1098e.setTheme(i3);
            if (Build.VERSION.SDK_INT >= 23) {
                this.f1098e.getTheme().applyStyle(this.O, true);
            }
        }
        if (z) {
            Object obj = this.d;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof xd) {
                    if (((xd) activity).getLifecycle().a().isAtLeast(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.L) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    @Override // supwisdom.t0
    public final q0 a() {
        return new h(this);
    }

    public static Configuration a(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = 0.0f;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f2 = configuration.fontScale;
            float f3 = configuration2.fontScale;
            if (f2 != f3) {
                configuration3.fontScale = f3;
            }
            int i2 = configuration.mcc;
            int i3 = configuration2.mcc;
            if (i2 != i3) {
                configuration3.mcc = i3;
            }
            int i4 = configuration.mnc;
            int i5 = configuration2.mnc;
            if (i4 != i5) {
                configuration3.mnc = i5;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                p.a(configuration, configuration2, configuration3);
            } else if (!ia.a(configuration.locale, configuration2.locale)) {
                configuration3.locale = configuration2.locale;
            }
            int i6 = configuration.touchscreen;
            int i7 = configuration2.touchscreen;
            if (i6 != i7) {
                configuration3.touchscreen = i7;
            }
            int i8 = configuration.keyboard;
            int i9 = configuration2.keyboard;
            if (i8 != i9) {
                configuration3.keyboard = i9;
            }
            int i10 = configuration.keyboardHidden;
            int i11 = configuration2.keyboardHidden;
            if (i10 != i11) {
                configuration3.keyboardHidden = i11;
            }
            int i12 = configuration.navigation;
            int i13 = configuration2.navigation;
            if (i12 != i13) {
                configuration3.navigation = i13;
            }
            int i14 = configuration.navigationHidden;
            int i15 = configuration2.navigationHidden;
            if (i14 != i15) {
                configuration3.navigationHidden = i15;
            }
            int i16 = configuration.orientation;
            int i17 = configuration2.orientation;
            if (i16 != i17) {
                configuration3.orientation = i17;
            }
            int i18 = configuration.screenLayout & 15;
            int i19 = configuration2.screenLayout;
            if (i18 != (i19 & 15)) {
                configuration3.screenLayout |= i19 & 15;
            }
            int i20 = configuration.screenLayout & 192;
            int i21 = configuration2.screenLayout;
            if (i20 != (i21 & 192)) {
                configuration3.screenLayout |= i21 & 192;
            }
            int i22 = configuration.screenLayout & 48;
            int i23 = configuration2.screenLayout;
            if (i22 != (i23 & 48)) {
                configuration3.screenLayout |= i23 & 48;
            }
            int i24 = configuration.screenLayout & 768;
            int i25 = configuration2.screenLayout;
            if (i24 != (i25 & 768)) {
                configuration3.screenLayout |= i25 & 768;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                q.a(configuration, configuration2, configuration3);
            }
            int i26 = configuration.uiMode & 15;
            int i27 = configuration2.uiMode;
            if (i26 != (i27 & 15)) {
                configuration3.uiMode |= i27 & 15;
            }
            int i28 = configuration.uiMode & 48;
            int i29 = configuration2.uiMode;
            if (i28 != (i29 & 48)) {
                configuration3.uiMode |= i29 & 48;
            }
            int i30 = configuration.screenWidthDp;
            int i31 = configuration2.screenWidthDp;
            if (i30 != i31) {
                configuration3.screenWidthDp = i31;
            }
            int i32 = configuration.screenHeightDp;
            int i33 = configuration2.screenHeightDp;
            if (i32 != i33) {
                configuration3.screenHeightDp = i33;
            }
            int i34 = configuration.smallestScreenWidthDp;
            int i35 = configuration2.smallestScreenWidthDp;
            if (i34 != i35) {
                configuration3.smallestScreenWidthDp = i35;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                o.a(configuration, configuration2, configuration3);
            }
        }
        return configuration3;
    }
}
