package supwisdom;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class s3 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static s3 j;
    public static s3 k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f9112a;
    public final CharSequence b;
    public final int c;
    public final Runnable d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Runnable f9113e = new b();
    public int f;
    public int g;
    public t3 h;
    public boolean i;

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            s3.this.a(false);
        }
    }

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            s3.this.c();
        }
    }

    public s3(View view, CharSequence charSequence) {
        this.f9112a = view;
        this.b = charSequence;
        this.c = mb.a(ViewConfiguration.get(view.getContext()));
        b();
        this.f9112a.setOnLongClickListener(this);
        this.f9112a.setOnHoverListener(this);
    }

    public static void a(View view, CharSequence charSequence) {
        s3 s3Var = j;
        if (s3Var != null && s3Var.f9112a == view) {
            a((s3) null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new s3(view, charSequence);
            return;
        }
        s3 s3Var2 = k;
        if (s3Var2 != null && s3Var2.f9112a == view) {
            s3Var2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public final void b() {
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
    }

    public void c() {
        if (k == this) {
            k = null;
            t3 t3Var = this.h;
            if (t3Var != null) {
                t3Var.a();
                this.h = null;
                b();
                this.f9112a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            a((s3) null);
        }
        this.f9112a.removeCallbacks(this.f9113e);
    }

    public final void d() {
        this.f9112a.postDelayed(this.d, ViewConfiguration.getLongPressTimeout());
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f9112a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.f9112a.isEnabled() && this.h == null && a(motionEvent)) {
            a(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        a(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        c();
    }

    public void a(boolean z) {
        long j2;
        int longPressTimeout;
        long j3;
        if (lb.K(this.f9112a)) {
            a((s3) null);
            s3 s3Var = k;
            if (s3Var != null) {
                s3Var.c();
            }
            k = this;
            this.i = z;
            t3 t3Var = new t3(this.f9112a.getContext());
            this.h = t3Var;
            t3Var.a(this.f9112a, this.f, this.g, this.i, this.b);
            this.f9112a.addOnAttachStateChangeListener(this);
            if (this.i) {
                j3 = 2500;
            } else {
                if ((lb.D(this.f9112a) & 1) == 1) {
                    j2 = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j2 = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j3 = j2 - ((long) longPressTimeout);
            }
            this.f9112a.removeCallbacks(this.f9113e);
            this.f9112a.postDelayed(this.f9113e, j3);
        }
    }

    public static void a(s3 s3Var) {
        s3 s3Var2 = j;
        if (s3Var2 != null) {
            s3Var2.a();
        }
        j = s3Var;
        if (s3Var != null) {
            s3Var.d();
        }
    }

    public final void a() {
        this.f9112a.removeCallbacks(this.d);
    }

    public final boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f) <= this.c && Math.abs(y - this.g) <= this.c) {
            return false;
        }
        this.f = x;
        this.g = y;
        return true;
    }
}
