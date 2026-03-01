package supwisdom;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;
import supwisdom.vc;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class uc extends oa {
    public static final Rect k = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final vc.a<xb> l = new a();
    public static final vc.b<q4<xb>, xb> m = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AccessibilityManager f9394e;
    public final View f;
    public c g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f9393a = new Rect();
    public final Rect b = new Rect();
    public final Rect c = new Rect();
    public final int[] d = new int[2];
    public int h = Integer.MIN_VALUE;
    public int i = Integer.MIN_VALUE;
    public int j = Integer.MIN_VALUE;

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class a implements vc.a<xb> {
        @Override // supwisdom.vc.a
        public void a(xb xbVar, Rect rect) {
            xbVar.a(rect);
        }
    }

    public uc(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f = view;
        this.f9394e = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (lb.l(view) == 0) {
            lb.h(view, 1);
        }
    }

    public static int i(int i) {
        if (i == 19) {
            return 33;
        }
        if (i != 21) {
            return i != 22 ? 130 : 66;
        }
        return 17;
    }

    public abstract int a(float f, float f2);

    public void a(int i, AccessibilityEvent accessibilityEvent) {
    }

    public abstract void a(int i, xb xbVar);

    public void a(int i, boolean z) {
    }

    public void a(AccessibilityEvent accessibilityEvent) {
    }

    public abstract void a(List<Integer> list);

    public void a(xb xbVar) {
    }

    public abstract boolean a(int i, int i2, Bundle bundle);

    public final boolean a(MotionEvent motionEvent) {
        if (!this.f9394e.isEnabled() || !this.f9394e.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iA = a(motionEvent.getX(), motionEvent.getY());
            h(iA);
            return iA != Integer.MIN_VALUE;
        }
        if (action != 10 || this.j == Integer.MIN_VALUE) {
            return false;
        }
        h(Integer.MIN_VALUE);
        return true;
    }

    public final boolean b(int i, Rect rect) {
        xb xbVar;
        q4<xb> q4VarC = c();
        int i2 = this.i;
        xb xbVarA = i2 == Integer.MIN_VALUE ? null : q4VarC.a(i2);
        if (i == 1 || i == 2) {
            xbVar = (xb) vc.a(q4VarC, m, l, xbVarA, i, lb.n(this.f) == 1, false);
        } else {
            if (i != 17 && i != 33 && i != 66 && i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i3 = this.i;
            if (i3 != Integer.MIN_VALUE) {
                a(i3, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                a(this.f, i, rect2);
            }
            xbVar = (xb) vc.a(q4VarC, m, l, xbVarA, rect2, i);
        }
        return g(xbVar != null ? q4VarC.c(q4VarC.a(xbVar)) : Integer.MIN_VALUE);
    }

    public final q4<xb> c() {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        q4<xb> q4Var = new q4<>();
        for (int i = 0; i < arrayList.size(); i++) {
            q4Var.c(i, d(i));
        }
        return q4Var;
    }

    public final xb d(int i) {
        xb xbVarA = xb.A();
        xbVarA.h(true);
        xbVarA.i(true);
        xbVarA.a("android.view.View");
        xbVarA.c(k);
        xbVarA.d(k);
        xbVarA.e(this.f);
        a(i, xbVarA);
        if (xbVarA.j() == null && xbVarA.f() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        xbVarA.a(this.b);
        if (this.b.equals(k)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iC = xbVarA.c();
        if ((iC & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iC & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        xbVarA.e(this.f.getContext().getPackageName());
        xbVarA.c(this.f, i);
        if (this.h == i) {
            xbVarA.a(true);
            xbVarA.a(128);
        } else {
            xbVarA.a(false);
            xbVarA.a(64);
        }
        boolean z = this.i == i;
        if (z) {
            xbVarA.a(2);
        } else if (xbVarA.r()) {
            xbVarA.a(1);
        }
        xbVarA.j(z);
        this.f.getLocationOnScreen(this.d);
        xbVarA.b(this.f9393a);
        if (this.f9393a.equals(k)) {
            xbVarA.a(this.f9393a);
            if (xbVarA.b != -1) {
                xb xbVarA2 = xb.A();
                for (int i2 = xbVarA.b; i2 != -1; i2 = xbVarA2.b) {
                    xbVarA2.b(this.f, -1);
                    xbVarA2.c(k);
                    a(i2, xbVarA2);
                    xbVarA2.a(this.b);
                    Rect rect = this.f9393a;
                    Rect rect2 = this.b;
                    rect.offset(rect2.left, rect2.top);
                }
                xbVarA2.y();
            }
            this.f9393a.offset(this.d[0] - this.f.getScrollX(), this.d[1] - this.f.getScrollY());
        }
        if (this.f.getLocalVisibleRect(this.c)) {
            this.c.offset(this.d[0] - this.f.getScrollX(), this.d[1] - this.f.getScrollY());
            if (this.f9393a.intersect(this.c)) {
                xbVarA.d(this.f9393a);
                if (a(this.f9393a)) {
                    xbVarA.q(true);
                }
            }
        }
        return xbVarA;
    }

    public xb e(int i) {
        return i == -1 ? b() : d(i);
    }

    public final boolean f(int i) {
        int i2;
        if (!this.f9394e.isEnabled() || !this.f9394e.isTouchExplorationEnabled() || (i2 = this.h) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            a(i2);
        }
        this.h = i;
        this.f.invalidate();
        c(i, 32768);
        return true;
    }

    public final boolean g(int i) {
        int i2;
        if ((!this.f.isFocused() && !this.f.requestFocus()) || (i2 = this.i) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            b(i2);
        }
        this.i = i;
        a(i, true);
        c(i, 8);
        return true;
    }

    @Override // supwisdom.oa
    public yb getAccessibilityNodeProvider(View view) {
        if (this.g == null) {
            this.g = new c();
        }
        return this.g;
    }

    public final void h(int i) {
        int i2 = this.j;
        if (i2 == i) {
            return;
        }
        this.j = i;
        c(i, 128);
        c(i2, 256);
    }

    @Override // supwisdom.oa
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        a(accessibilityEvent);
    }

    @Override // supwisdom.oa
    public void onInitializeAccessibilityNodeInfo(View view, xb xbVar) {
        super.onInitializeAccessibilityNodeInfo(view, xbVar);
        a(xbVar);
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class b implements vc.b<q4<xb>, xb> {
        @Override // supwisdom.vc.b
        public xb a(q4<xb> q4Var, int i) {
            return q4Var.e(i);
        }

        @Override // supwisdom.vc.b
        public int a(q4<xb> q4Var) {
            return q4Var.c();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class c extends yb {
        public c() {
        }

        @Override // supwisdom.yb
        public xb a(int i) {
            return xb.a(uc.this.e(i));
        }

        @Override // supwisdom.yb
        public xb b(int i) {
            int i2 = i == 2 ? uc.this.h : uc.this.i;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i2);
        }

        @Override // supwisdom.yb
        public boolean a(int i, int i2, Bundle bundle) {
            return uc.this.b(i, i2, bundle);
        }
    }

    public final boolean a(KeyEvent keyEvent) {
        int i = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 61) {
            if (keyCode != 66) {
                switch (keyCode) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        if (!keyEvent.hasNoModifiers()) {
                            return false;
                        }
                        int i2 = i(keyCode);
                        int repeatCount = keyEvent.getRepeatCount() + 1;
                        boolean z = false;
                        while (i < repeatCount && b(i2, (Rect) null)) {
                            i++;
                            z = true;
                        }
                        return z;
                    case 23:
                        break;
                    default:
                        return false;
                }
            }
            if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
                return false;
            }
            a();
            return true;
        }
        if (keyEvent.hasNoModifiers()) {
            return b(2, (Rect) null);
        }
        if (keyEvent.hasModifiers(1)) {
            return b(1, (Rect) null);
        }
        return false;
    }

    public final boolean c(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.f9394e.isEnabled() || (parent = this.f.getParent()) == null) {
            return false;
        }
        return ob.a(parent, this.f, a(i, i2));
    }

    public final AccessibilityEvent c(int i) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i);
        this.f.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    public final boolean c(int i, int i2, Bundle bundle) {
        if (i2 == 1) {
            return g(i);
        }
        if (i2 == 2) {
            return b(i);
        }
        if (i2 == 64) {
            return f(i);
        }
        if (i2 != 128) {
            return a(i, i2, bundle);
        }
        return a(i);
    }

    public final AccessibilityEvent b(int i, int i2) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        xb xbVarE = e(i);
        accessibilityEventObtain.getText().add(xbVarE.j());
        accessibilityEventObtain.setContentDescription(xbVarE.f());
        accessibilityEventObtain.setScrollable(xbVarE.v());
        accessibilityEventObtain.setPassword(xbVarE.u());
        accessibilityEventObtain.setEnabled(xbVarE.q());
        accessibilityEventObtain.setChecked(xbVarE.o());
        a(i, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(xbVarE.e());
        zb.a(accessibilityEventObtain, this.f, i);
        accessibilityEventObtain.setPackageName(this.f.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    public final void a(boolean z, int i, Rect rect) {
        int i2 = this.i;
        if (i2 != Integer.MIN_VALUE) {
            b(i2);
        }
        if (z) {
            b(i, rect);
        }
    }

    public final void a(int i, Rect rect) {
        e(i).a(rect);
    }

    public static Rect a(View view, int i, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i == 17) {
            rect.set(width, 0, width, height);
        } else if (i == 33) {
            rect.set(0, height, width, height);
        } else if (i == 66) {
            rect.set(-1, 0, -1, height);
        } else if (i == 130) {
            rect.set(0, -1, width, -1);
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return rect;
    }

    public final xb b() {
        xb xbVarG = xb.g(this.f);
        lb.a(this.f, xbVarG);
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        if (xbVarG.d() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            xbVarG.a(this.f, ((Integer) arrayList.get(i)).intValue());
        }
        return xbVarG;
    }

    public final boolean a() {
        int i = this.i;
        return i != Integer.MIN_VALUE && a(i, 16, (Bundle) null);
    }

    public final AccessibilityEvent a(int i, int i2) {
        if (i != -1) {
            return b(i, i2);
        }
        return c(i2);
    }

    public final boolean a(int i, Bundle bundle) {
        return lb.a(this.f, i, bundle);
    }

    public final boolean a(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.f.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    public boolean b(int i, int i2, Bundle bundle) {
        if (i != -1) {
            return c(i, i2, bundle);
        }
        return a(i2, bundle);
    }

    public final boolean b(int i) {
        if (this.i != i) {
            return false;
        }
        this.i = Integer.MIN_VALUE;
        a(i, false);
        c(i, 8);
        return true;
    }

    public final boolean a(int i) {
        if (this.h != i) {
            return false;
        }
        this.h = Integer.MIN_VALUE;
        this.f.invalidate();
        c(i, 65536);
        return true;
    }
}
