package supwisdom;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import supwisdom.d1;

/* JADX INFO: compiled from: StateListDrawable.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class f1 extends d1 {
    public a m;
    public boolean n;

    /* JADX INFO: compiled from: StateListDrawable.java */
    public static class a extends d1.c {
        public int[][] J;

        public a(a aVar, f1 f1Var, Resources resources) {
            super(aVar, f1Var, resources);
            if (aVar != null) {
                this.J = aVar.J;
            } else {
                this.J = new int[d()][];
            }
        }

        public int a(int[] iArr, Drawable drawable) {
            int iA = a(drawable);
            this.J[iA] = iArr;
            return iA;
        }

        @Override // supwisdom.d1.c
        public void n() {
            int[][] iArr = this.J;
            int[][] iArr2 = new int[iArr.length][];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.J;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.J = iArr2;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new f1(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new f1(this, resources);
        }

        public int a(int[] iArr) {
            int[][] iArr2 = this.J;
            int iE = e();
            for (int i = 0; i < iE; i++) {
                if (StateSet.stateSetMatches(iArr2[i], iArr)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // supwisdom.d1.c
        public void a(int i, int i2) {
            super.a(i, i2);
            int[][] iArr = new int[i2][];
            System.arraycopy(this.J, 0, iArr, 0, i);
            this.J = iArr;
        }
    }

    public f1(a aVar, Resources resources) {
        a(new a(aVar, this, resources));
        onStateChange(getState());
    }

    @Override // supwisdom.d1, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // supwisdom.d1, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.n) {
            super.mutate();
            if (this == this) {
                this.m.n();
                this.n = true;
            }
        }
        return this;
    }

    @Override // supwisdom.d1, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        int iA = this.m.a(iArr);
        if (iA < 0) {
            iA = this.m.a(StateSet.WILD_CARD);
        }
        return a(iA) || zOnStateChange;
    }

    public int[] a(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i = 0;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i2);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i3 = i + 1;
                if (!attributeSet.getAttributeBooleanValue(i2, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i] = attributeNameResource;
                i = i3;
            }
        }
        return StateSet.trimStateSet(iArr, i);
    }

    public f1(a aVar) {
        if (aVar != null) {
            a(aVar);
        }
    }

    @Override // supwisdom.d1
    public a a() {
        return new a(this.m, this, null);
    }

    @Override // supwisdom.d1
    public void a(d1.c cVar) {
        super.a(cVar);
        if (cVar instanceof a) {
            this.m = (a) cVar;
        }
    }
}
