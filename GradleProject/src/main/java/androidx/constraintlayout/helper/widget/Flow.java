package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.VirtualLayout;
import supwisdom.e7;
import supwisdom.g6;
import supwisdom.j6;
import supwisdom.l6;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public g6 l;

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void a(ConstraintWidget constraintWidget, boolean z) {
        this.l.e(z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i, int i2) {
        a(this.l, i, i2);
    }

    public void setFirstHorizontalBias(float f) {
        this.l.e(f);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.l.F(i);
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.l.f(f);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.l.G(i);
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.l.H(i);
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.l.g(f);
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.l.I(i);
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.l.J(i);
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.l.M(i);
        requestLayout();
    }

    public void setOrientation(int i) {
        this.l.N(i);
        requestLayout();
    }

    public void setPadding(int i) {
        this.l.y(i);
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.l.z(i);
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.l.B(i);
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.l.C(i);
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.l.E(i);
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.l.O(i);
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.l.j(f);
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.l.P(i);
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.l.Q(i);
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.l.R(i);
        requestLayout();
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void a(l6 l6Var, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (l6Var == null) {
            setMeasuredDimension(0, 0);
        } else {
            l6Var.b(mode, size, mode2, size2);
            setMeasuredDimension(l6Var.V(), l6Var.U());
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void a(e7.a aVar, j6 j6Var, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        super.a(aVar, j6Var, layoutParams, sparseArray);
        if (j6Var instanceof g6) {
            g6 g6Var = (g6) j6Var;
            int i = layoutParams.R;
            if (i != -1) {
                g6Var.N(i);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void a(AttributeSet attributeSet) {
        super.a(attributeSet);
        this.l = new g6();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_android_orientation) {
                    this.l.N(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_padding) {
                    this.l.y(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingStart) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        this.l.D(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        this.l.A(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                    }
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.l.B(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.l.E(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.l.C(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.l.z(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.l.R(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.l.J(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.l.Q(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.l.F(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.l.K(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.l.G(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.l.L(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.l.g(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.l.e(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.l.h(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.l.f(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.l.i(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.l.j(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.l.H(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.l.O(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.l.I(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.l.P(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.l.M(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.d = this.l;
        b();
    }
}
