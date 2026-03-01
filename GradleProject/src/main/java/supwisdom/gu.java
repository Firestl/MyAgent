package supwisdom;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public class gu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7771a;
    public ViewParent b;
    public boolean c;
    public int[] d;

    public gu(View view) {
        this.f7771a = view;
    }

    public boolean a(float f, float f2, boolean z) {
        ViewParent viewParent;
        if (!b() || (viewParent = this.b) == null) {
            return false;
        }
        return qu.a(viewParent, this.f7771a, f, f2, z);
    }

    public boolean b() {
        return this.c;
    }

    public void c() {
        ViewParent viewParent = this.b;
        if (viewParent != null) {
            qu.a(viewParent, this.f7771a);
            this.b = null;
        }
    }

    public boolean a(float f, float f2) {
        ViewParent viewParent;
        if (!b() || (viewParent = this.b) == null) {
            return false;
        }
        return qu.a(viewParent, this.f7771a, f, f2);
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2) {
        int i3;
        int i4;
        if (!b() || this.b == null) {
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
            this.f7771a.getLocationInWindow(iArr2);
            i3 = iArr2[0];
            i4 = iArr2[1];
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (iArr == null) {
            if (this.d == null) {
                this.d = new int[2];
            }
            iArr = this.d;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        qu.a(this.b, this.f7771a, i, i2, iArr);
        if (iArr2 != null) {
            this.f7771a.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i3;
            iArr2[1] = iArr2[1] - i4;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        if (b() && this.b != null) {
            if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
                if (iArr != null) {
                    this.f7771a.getLocationInWindow(iArr);
                    i5 = iArr[0];
                    i6 = iArr[1];
                } else {
                    i5 = 0;
                    i6 = 0;
                }
                qu.a(this.b, this.f7771a, i, i2, i3, i4);
                if (iArr != null) {
                    this.f7771a.getLocationInWindow(iArr);
                    iArr[0] = iArr[0] - i5;
                    iArr[1] = iArr[1] - i6;
                }
                return true;
            }
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
        }
        return false;
    }

    public boolean a() {
        return this.b != null;
    }

    public void a(boolean z) {
        if (this.c) {
            ku.d(this.f7771a);
        }
        this.c = z;
    }

    public boolean a(int i) {
        if (a()) {
            return true;
        }
        if (!b()) {
            return false;
        }
        View view = this.f7771a;
        for (ViewParent parent = this.f7771a.getParent(); parent != null; parent = parent.getParent()) {
            if (qu.b(parent, view, this.f7771a, i)) {
                this.b = parent;
                qu.a(parent, view, this.f7771a, i);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }
}
