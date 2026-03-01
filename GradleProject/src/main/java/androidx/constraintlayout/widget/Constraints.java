package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import supwisdom.e7;

/* JADX INFO: loaded from: classes.dex */
public class Constraints extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e7 f1253a;

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        super.setVisibility(8);
    }

    public final void a(AttributeSet attributeSet) {
        Log.v("Constraints", " ################# init");
    }

    public e7 getConstraintSet() {
        if (this.f1253a == null) {
            this.f1253a = new e7();
        }
        this.f1253a.a(this);
        return this.f1253a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.LayoutParams(layoutParams);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
        super.setVisibility(8);
    }

    public static class LayoutParams extends ConstraintLayout.LayoutParams {
        public float A0;
        public float o0;
        public boolean p0;
        public float q0;
        public float r0;
        public float s0;
        public float t0;
        public float u0;
        public float v0;
        public float w0;
        public float x0;
        public float y0;
        public float z0;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.o0 = 1.0f;
            this.p0 = false;
            this.q0 = 0.0f;
            this.r0 = 0.0f;
            this.s0 = 0.0f;
            this.t0 = 0.0f;
            this.u0 = 1.0f;
            this.v0 = 1.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
            this.z0 = 0.0f;
            this.A0 = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.o0 = 1.0f;
            this.p0 = false;
            this.q0 = 0.0f;
            this.r0 = 0.0f;
            this.s0 = 0.0f;
            this.t0 = 0.0f;
            this.u0 = 1.0f;
            this.v0 = 1.0f;
            this.w0 = 0.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
            this.z0 = 0.0f;
            this.A0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintSet_android_alpha) {
                    this.o0 = typedArrayObtainStyledAttributes.getFloat(index, this.o0);
                } else if (index == R.styleable.ConstraintSet_android_elevation) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.q0 = typedArrayObtainStyledAttributes.getFloat(index, this.q0);
                        this.p0 = true;
                    }
                } else if (index == R.styleable.ConstraintSet_android_rotationX) {
                    this.s0 = typedArrayObtainStyledAttributes.getFloat(index, this.s0);
                } else if (index == R.styleable.ConstraintSet_android_rotationY) {
                    this.t0 = typedArrayObtainStyledAttributes.getFloat(index, this.t0);
                } else if (index == R.styleable.ConstraintSet_android_rotation) {
                    this.r0 = typedArrayObtainStyledAttributes.getFloat(index, this.r0);
                } else if (index == R.styleable.ConstraintSet_android_scaleX) {
                    this.u0 = typedArrayObtainStyledAttributes.getFloat(index, this.u0);
                } else if (index == R.styleable.ConstraintSet_android_scaleY) {
                    this.v0 = typedArrayObtainStyledAttributes.getFloat(index, this.v0);
                } else if (index == R.styleable.ConstraintSet_android_transformPivotX) {
                    this.w0 = typedArrayObtainStyledAttributes.getFloat(index, this.w0);
                } else if (index == R.styleable.ConstraintSet_android_transformPivotY) {
                    this.x0 = typedArrayObtainStyledAttributes.getFloat(index, this.x0);
                } else if (index == R.styleable.ConstraintSet_android_translationX) {
                    this.y0 = typedArrayObtainStyledAttributes.getFloat(index, this.y0);
                } else if (index == R.styleable.ConstraintSet_android_translationY) {
                    this.z0 = typedArrayObtainStyledAttributes.getFloat(index, this.z0);
                } else if (index == R.styleable.ConstraintSet_android_translationZ && Build.VERSION.SDK_INT >= 21) {
                    this.A0 = typedArrayObtainStyledAttributes.getFloat(index, this.A0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }
}
