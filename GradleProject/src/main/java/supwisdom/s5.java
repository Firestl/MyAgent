package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TouchResponse.java */
/* JADX INFO: loaded from: classes.dex */
public class s5 {
    public static final float[][] v = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    public static final float[][] w = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    public float m;
    public float n;
    public final MotionLayout o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9122a = 0;
    public int b = 0;
    public int c = 0;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9123e = -1;
    public int f = -1;
    public float g = 0.5f;
    public float h = 0.5f;
    public float i = 0.0f;
    public float j = 1.0f;
    public boolean k = false;
    public float[] l = new float[2];
    public float p = 4.0f;
    public float q = 1.2f;
    public boolean r = true;
    public float s = 1.0f;
    public int t = 0;
    public float u = 10.0f;

    /* JADX INFO: compiled from: TouchResponse.java */
    public class a implements View.OnTouchListener {
        public a(s5 s5Var) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* JADX INFO: compiled from: TouchResponse.java */
    public class b implements NestedScrollView.b {
        public b(s5 s5Var) {
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        }
    }

    public s5(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.o = motionLayout;
        a(context, Xml.asAttributeSet(xmlPullParser));
    }

    public void a(boolean z) {
        if (z) {
            float[][] fArr = w;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = v;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = w;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = v;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = v;
        int i = this.f9122a;
        this.h = fArr5[i][0];
        this.g = fArr5[i][1];
        float[][] fArr6 = w;
        int i2 = this.b;
        this.i = fArr6[i2][0];
        this.j = fArr6[i2][1];
    }

    public float b(float f, float f2) {
        this.o.a(this.d, this.o.getProgress(), this.h, this.g, this.l);
        if (this.i != 0.0f) {
            float[] fArr = this.l;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * this.i) / this.l[0];
        }
        float[] fArr2 = this.l;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.j) / this.l[1];
    }

    public void c(float f, float f2) {
        float progress = this.o.getProgress();
        if (!this.k) {
            this.k = true;
            this.o.setProgress(progress);
        }
        this.o.a(this.d, progress, this.h, this.g, this.l);
        float f3 = this.i;
        float[] fArr = this.l;
        if (Math.abs((f3 * fArr[0]) + (this.j * fArr[1])) < 0.01d) {
            float[] fArr2 = this.l;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f4 = this.i;
        float fMax = Math.max(Math.min(progress + (f4 != 0.0f ? (f * f4) / this.l[0] : (f2 * this.j) / this.l[1]), 1.0f), 0.0f);
        if (fMax != this.o.getProgress()) {
            this.o.setProgress(fMax);
        }
    }

    public void d(float f, float f2) {
        this.k = false;
        float progress = this.o.getProgress();
        this.o.a(this.d, progress, this.h, this.g, this.l);
        float f3 = this.i;
        float[] fArr = this.l;
        float f4 = fArr[0];
        float f5 = this.j;
        float f6 = fArr[1];
        float f7 = f3 != 0.0f ? (f * f3) / fArr[0] : (f2 * f5) / fArr[1];
        if (!Float.isNaN(f7)) {
            progress += f7 / 3.0f;
        }
        if (progress != 0.0f) {
            if ((this.c != 3) && (progress != 1.0f)) {
                this.o.a(this.c, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f7);
            }
        }
    }

    public void e(float f, float f2) {
        this.m = f;
        this.n = f2;
    }

    public void f(float f, float f2) {
        this.m = f;
        this.n = f2;
        this.k = false;
    }

    public String toString() {
        return this.i + " , " + this.j;
    }

    public int e() {
        return this.f9123e;
    }

    public void f() {
        View viewFindViewById;
        int i = this.d;
        if (i != -1) {
            viewFindViewById = this.o.findViewById(i);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + z4.a(this.o.getContext(), this.d));
            }
        } else {
            viewFindViewById = null;
        }
        if (viewFindViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) viewFindViewById;
            nestedScrollView.setOnTouchListener(new a(this));
            nestedScrollView.setOnScrollChangeListener(new b(this));
        }
    }

    public float b() {
        return this.q;
    }

    public boolean d() {
        return this.r;
    }

    public RectF b(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.f9123e;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnSwipe);
        a(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void a(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.d = typedArray.getResourceId(index, this.d);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i2 = typedArray.getInt(index, this.f9122a);
                this.f9122a = i2;
                float[][] fArr = v;
                this.h = fArr[i2][0];
                this.g = fArr[i2][1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i3 = typedArray.getInt(index, this.b);
                this.b = i3;
                float[][] fArr2 = w;
                this.i = fArr2[i3][0];
                this.j = fArr2[i3][1];
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.p = typedArray.getFloat(index, this.p);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.q = typedArray.getFloat(index, this.q);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.r = typedArray.getBoolean(index, this.r);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.s = typedArray.getFloat(index, this.s);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.f9123e = typedArray.getResourceId(index, this.f9123e);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.c = typedArray.getInt(index, this.c);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.t = typedArray.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.f = typedArray.getResourceId(index, 0);
            }
        }
    }

    public float c() {
        return this.p;
    }

    public void a(MotionEvent motionEvent, MotionLayout.f fVar, int i, p5 p5Var) {
        float f;
        int i2;
        float f2;
        fVar.a(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.m = motionEvent.getRawX();
            this.n = motionEvent.getRawY();
            this.k = false;
            return;
        }
        if (action == 1) {
            this.k = false;
            fVar.a(1000);
            float fB = fVar.b();
            float fA = fVar.a();
            float progress = this.o.getProgress();
            int i3 = this.d;
            if (i3 != -1) {
                this.o.a(i3, progress, this.h, this.g, this.l);
            } else {
                float fMin = Math.min(this.o.getWidth(), this.o.getHeight());
                float[] fArr = this.l;
                fArr[1] = this.j * fMin;
                fArr[0] = fMin * this.i;
            }
            float f3 = this.i;
            float[] fArr2 = this.l;
            float f4 = fArr2[0];
            float f5 = fArr2[1];
            if (f3 != 0.0f) {
                f = fB / fArr2[0];
            } else {
                f = fA / fArr2[1];
            }
            float f6 = !Float.isNaN(f) ? (f / 3.0f) + progress : progress;
            if (f6 == 0.0f || f6 == 1.0f || (i2 = this.c) == 3) {
                if (0.0f >= f6 || 1.0f <= f6) {
                    this.o.setState(MotionLayout.j.FINISHED);
                    return;
                }
                return;
            }
            this.o.a(i2, ((double) f6) < 0.5d ? 0.0f : 1.0f, f);
            if (0.0f >= progress || 1.0f <= progress) {
                this.o.setState(MotionLayout.j.FINISHED);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.n;
        float rawX = motionEvent.getRawX() - this.m;
        if (Math.abs((this.i * rawX) + (this.j * rawY)) > this.u || this.k) {
            float progress2 = this.o.getProgress();
            if (!this.k) {
                this.k = true;
                this.o.setProgress(progress2);
            }
            int i4 = this.d;
            if (i4 != -1) {
                this.o.a(i4, progress2, this.h, this.g, this.l);
            } else {
                float fMin2 = Math.min(this.o.getWidth(), this.o.getHeight());
                float[] fArr3 = this.l;
                fArr3[1] = this.j * fMin2;
                fArr3[0] = fMin2 * this.i;
            }
            float f7 = this.i;
            float[] fArr4 = this.l;
            if (Math.abs(((f7 * fArr4[0]) + (this.j * fArr4[1])) * this.s) < 0.01d) {
                float[] fArr5 = this.l;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            if (this.i != 0.0f) {
                f2 = rawX / this.l[0];
            } else {
                f2 = rawY / this.l[1];
            }
            float fMax = Math.max(Math.min(progress2 + f2, 1.0f), 0.0f);
            if (fMax != this.o.getProgress()) {
                this.o.setProgress(fMax);
                fVar.a(1000);
                this.o.v = this.i != 0.0f ? fVar.b() / this.l[0] : fVar.a() / this.l[1];
            } else {
                this.o.v = 0.0f;
            }
            this.m = motionEvent.getRawX();
            this.n = motionEvent.getRawY();
        }
    }

    public RectF a(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.f;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public float a(float f, float f2) {
        return (f * this.i) + (f2 * this.j);
    }

    public int a() {
        return this.t;
    }
}
