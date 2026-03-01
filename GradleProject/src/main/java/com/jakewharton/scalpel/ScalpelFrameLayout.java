package com.jakewharton.scalpel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;

/* JADX INFO: loaded from: classes2.dex */
public class ScalpelFrameLayout extends FrameLayout {
    public float A;
    public float B;
    public int C;
    public int D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f3614a;
    public final Paint b;
    public final Camera c;
    public final Matrix d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f3615e;
    public final BitSet f;
    public final SparseArray<String> g;
    public final Deque<b> h;
    public final c<b> i;
    public final Resources j;
    public final float k;
    public final float l;
    public final float m;
    public final float n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public float s;
    public float t;
    public int u;
    public float v;
    public float w;
    public int x;
    public float y;
    public float z;

    public class a extends c<b> {
        public a(int i) {
            super(i);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.jakewharton.scalpel.ScalpelFrameLayout.c
        public b a() {
            return new b(null);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f3616a;
        public int b;

        public b() {
        }

        public void a(View view, int i) {
            this.f3616a = view;
            this.b = i;
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        public void a() {
            this.f3616a = null;
            this.b = -1;
        }
    }

    public static abstract class c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Deque<T> f3617a;

        public c(int i) {
            this.f3617a = new ArrayDeque(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.f3617a.addLast(a());
            }
        }

        public abstract T a();

        public void a(T t) {
            this.f3617a.addLast(t);
        }

        public T b() {
            return this.f3617a.isEmpty() ? a() : this.f3617a.removeLast();
        }
    }

    public ScalpelFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final String a(int i) {
        String resourceEntryName = this.g.get(i);
        if (resourceEntryName == null) {
            try {
                resourceEntryName = this.j.getResourceEntryName(i);
            } catch (Resources.NotFoundException unused) {
                resourceEntryName = String.format("0x%8x", Integer.valueOf(i));
            }
            this.g.put(i, resourceEntryName);
        }
        return resourceEntryName;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int id;
        if (!this.o) {
            super.draw(canvas);
            return;
        }
        getLocationInWindow(this.f3615e);
        int[] iArr = this.f3615e;
        float f = iArr[0];
        float f2 = iArr[1];
        int iSave = canvas.save();
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        this.c.save();
        this.c.rotate(this.z, this.y, 0.0f);
        this.c.getMatrix(this.d);
        this.c.restore();
        this.d.preTranslate(-width, -height);
        this.d.postTranslate(width, height);
        canvas.concat(this.d);
        float f3 = this.A;
        canvas.scale(f3, f3, width, height);
        if (!this.h.isEmpty()) {
            throw new AssertionError("View queue is not empty.");
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            b bVarB = this.i.b();
            bVarB.a(getChildAt(i), 0);
            this.h.add(bVarB);
        }
        while (!this.h.isEmpty()) {
            b bVarRemoveFirst = this.h.removeFirst();
            View view = bVarRemoveFirst.f3616a;
            int i2 = bVarRemoveFirst.b;
            bVarRemoveFirst.a();
            this.i.a(bVarRemoveFirst);
            boolean z = view instanceof ViewGroup;
            if (z) {
                ViewGroup viewGroup = (ViewGroup) view;
                this.f.clear();
                int childCount2 = viewGroup.getChildCount();
                for (int i3 = 0; i3 < childCount2; i3++) {
                    View childAt = viewGroup.getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        this.f.set(i3);
                        childAt.setVisibility(4);
                    }
                }
            }
            int iSave2 = canvas.save();
            float f4 = this.y / 60.0f;
            float f5 = this.z / 60.0f;
            float f6 = i2;
            float f7 = this.B;
            float f8 = this.k;
            canvas.translate(f6 * f7 * f8 * f4, -(f6 * f7 * f8 * f5));
            view.getLocationInWindow(this.f3615e);
            int[] iArr2 = this.f3615e;
            canvas.translate(iArr2[0] - f, iArr2[1] - f2);
            this.f3614a.set(0, 0, view.getWidth(), view.getHeight());
            canvas.drawRect(this.f3614a, this.b);
            if (this.p) {
                view.draw(canvas);
            }
            if (this.q && (id = view.getId()) != -1) {
                canvas.drawText(a(id), this.m, this.n, this.b);
            }
            canvas.restoreToCount(iSave2);
            if (z) {
                ViewGroup viewGroup2 = (ViewGroup) view;
                int childCount3 = viewGroup2.getChildCount();
                for (int i4 = 0; i4 < childCount3; i4++) {
                    if (this.f.get(i4)) {
                        View childAt2 = viewGroup2.getChildAt(i4);
                        childAt2.setVisibility(0);
                        b bVarB2 = this.i.b();
                        bVarB2.a(childAt2, i2 + 1);
                        this.h.add(bVarB2);
                    }
                }
            }
        }
        canvas.restoreToCount(iSave);
    }

    public int getChromeColor() {
        return this.C;
    }

    public int getChromeShadowColor() {
        return this.D;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.o || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0197  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instruction units count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jakewharton.scalpel.ScalpelFrameLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setChromeColor(int i) {
        if (this.C != i) {
            this.b.setColor(i);
            this.C = i;
            invalidate();
        }
    }

    public void setChromeShadowColor(int i) {
        if (this.D != i) {
            this.b.setShadowLayer(1.0f, -1.0f, 1.0f, i);
            this.D = i;
            invalidate();
        }
    }

    public void setDrawIds(boolean z) {
        if (this.q != z) {
            this.q = z;
            invalidate();
        }
    }

    public void setDrawViews(boolean z) {
        if (this.p != z) {
            this.p = z;
            invalidate();
        }
    }

    public void setLayerInteractionEnabled(boolean z) {
        if (this.o != z) {
            this.o = z;
            setWillNotDraw(!z);
            invalidate();
        }
    }

    public ScalpelFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3614a = new Rect();
        this.b = new Paint(1);
        this.c = new Camera();
        this.d = new Matrix();
        this.f3615e = new int[2];
        this.f = new BitSet(25);
        this.g = new SparseArray<>();
        this.h = new ArrayDeque();
        this.i = new a(25);
        this.p = true;
        this.r = -1;
        this.u = -1;
        this.x = 0;
        this.y = 15.0f;
        this.z = -10.0f;
        this.A = 0.6f;
        this.B = 25.0f;
        this.j = context.getResources();
        this.k = context.getResources().getDisplayMetrics().density;
        this.l = ViewConfiguration.get(context).getScaledTouchSlop();
        float f = this.k;
        this.n = 10.0f * f;
        this.m = f * 2.0f;
        setChromeColor(-7829368);
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setTextSize(this.n);
        setChromeShadowColor(-16777216);
        if (Build.VERSION.SDK_INT >= 16) {
            this.b.setTypeface(Typeface.create("sans-serif-condensed", 0));
        }
    }
}
