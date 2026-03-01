package supwisdom;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f7045a;
    public ViewParent b;
    public final View c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f7046e;

    public bb(View view) {
        this.c = view;
    }

    public void a(boolean z) {
        if (this.d) {
            lb.T(this.c);
        }
        this.d = z;
    }

    public boolean b() {
        return b(0);
    }

    public boolean c() {
        return this.d;
    }

    public void d() {
        d(0);
    }

    public boolean b(int i) {
        return a(i) != null;
    }

    public boolean c(int i) {
        return a(i, 0);
    }

    public void d(int i) {
        ViewParent viewParentA = a(i);
        if (viewParentA != null) {
            ob.a(viewParentA, this.c, i);
            a(i, (ViewParent) null);
        }
    }

    public final boolean b(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent viewParentA;
        int i6;
        int i7;
        int[] iArr3;
        if (!c() || (viewParentA = a(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr2 == null) {
            int[] iArrA = a();
            iArrA[0] = 0;
            iArrA[1] = 0;
            iArr3 = iArrA;
        } else {
            iArr3 = iArr2;
        }
        ob.a(viewParentA, this.c, i, i2, i3, i4, i5, iArr3);
        if (iArr != null) {
            this.c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    public boolean a(int i, int i2) {
        if (b(i2)) {
            return true;
        }
        if (!c()) {
            return false;
        }
        View view = this.c;
        for (ViewParent parent = this.c.getParent(); parent != null; parent = parent.getParent()) {
            if (ob.b(parent, view, this.c, i, i2)) {
                a(i2, parent);
                ob.a(parent, view, this.c, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        return b(i, i2, i3, i4, iArr, 0, null);
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return b(i, i2, i3, i4, iArr, i5, null);
    }

    public void a(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        b(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2) {
        return a(i, i2, iArr, iArr2, 0);
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent viewParentA;
        int i4;
        int i5;
        if (!c() || (viewParentA = a(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            iArr = a();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ob.a(viewParentA, this.c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean a(float f, float f2, boolean z) {
        ViewParent viewParentA;
        if (!c() || (viewParentA = a(0)) == null) {
            return false;
        }
        return ob.a(viewParentA, this.c, f, f2, z);
    }

    public boolean a(float f, float f2) {
        ViewParent viewParentA;
        if (!c() || (viewParentA = a(0)) == null) {
            return false;
        }
        return ob.a(viewParentA, this.c, f, f2);
    }

    public final ViewParent a(int i) {
        if (i == 0) {
            return this.f7045a;
        }
        if (i != 1) {
            return null;
        }
        return this.b;
    }

    public final void a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f7045a = viewParent;
        } else {
            if (i != 1) {
                return;
            }
            this.b = viewParent;
        }
    }

    public final int[] a() {
        if (this.f7046e == null) {
            this.f7046e = new int[2];
        }
        return this.f7046e;
    }
}
