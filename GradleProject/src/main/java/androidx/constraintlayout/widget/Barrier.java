package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import supwisdom.c6;
import supwisdom.e7;
import supwisdom.f6;
import supwisdom.j6;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int j;
    public int k;
    public c6 l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public final void a(ConstraintWidget constraintWidget, int i, boolean z) {
        this.k = i;
        if (Build.VERSION.SDK_INT < 17) {
            int i2 = this.j;
            if (i2 == 5) {
                this.k = 0;
            } else if (i2 == 6) {
                this.k = 1;
            }
        } else if (z) {
            int i3 = this.j;
            if (i3 == 5) {
                this.k = 1;
            } else if (i3 == 6) {
                this.k = 0;
            }
        } else {
            int i4 = this.j;
            if (i4 == 5) {
                this.k = 0;
            } else if (i4 == 6) {
                this.k = 1;
            }
        }
        if (constraintWidget instanceof c6) {
            ((c6) constraintWidget).y(this.k);
        }
    }

    public boolean c() {
        return this.l.U();
    }

    public int getMargin() {
        return this.l.W();
    }

    public int getType() {
        return this.j;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.l.e(z);
    }

    public void setDpMargin(int i) {
        this.l.z((int) ((i * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i) {
        this.l.z(i);
    }

    public void setType(int i) {
        this.j = i;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void a(ConstraintWidget constraintWidget, boolean z) {
        a(constraintWidget, this.j, z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        this.l = new c6();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.e(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.l.z(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.d = this.l;
        b();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void a(e7.a aVar, j6 j6Var, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        super.a(aVar, j6Var, layoutParams, sparseArray);
        if (j6Var instanceof c6) {
            c6 c6Var = (c6) j6Var;
            a(c6Var, aVar.d.b0, ((f6) j6Var.w()).d0());
            c6Var.e(aVar.d.j0);
            c6Var.z(aVar.d.c0);
        }
    }
}
