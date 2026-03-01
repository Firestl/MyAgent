package com.alibaba.dt.AChartsLib.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.alibaba.dt.AChartsLib.charts.AxisChart.BaseAxisChart;
import com.alibaba.dt.AChartsLib.interfaces.ChartTouchListener;
import io.dcloud.common.util.TitleNViewUtil;
import supwisdom.bk;
import supwisdom.bo;
import supwisdom.dl;
import supwisdom.ek;
import supwisdom.en;
import supwisdom.fn;
import supwisdom.gn;
import supwisdom.nl;
import supwisdom.pn;
import supwisdom.zn;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<V>] */
/* JADX INFO: loaded from: classes.dex */
public class Chart<V extends ek> extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public bk f1531a;
    public bo b;
    public nl c;
    public dl d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public zn f1532e;
    public Context f;
    public float g;
    public float h;
    public Integer i;
    public Integer j;
    public boolean k;
    public gn l;
    public en m;
    public pn n;
    public V o;
    public boolean p;
    public boolean q;
    public fn r;
    public ChartTouchListener s;
    public long t;
    public float u;
    public float v;
    public float w;
    public float x;
    public Matrix y;

    public Chart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1531a = new bk(this);
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = null;
        this.j = null;
        this.k = false;
        this.p = true;
        this.q = false;
        this.u = 1.0f;
        this.v = 1.0f;
        this.y = new Matrix();
        a(context, attributeSet);
    }

    public void a(Context context, AttributeSet attributeSet) {
        this.f = context;
        setWillNotDraw(false);
        setLayerType(1, null);
        this.b = new bo(this);
        this.f1532e = new zn(this);
        this.n = new pn(this.f);
        this.d = new dl(this);
        this.c = new nl();
        Display defaultDisplay = ((WindowManager) this.f.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        measure(point.x, point.y);
        requestLayout();
    }

    public boolean b() {
        return this.k;
    }

    public boolean c() {
        return this.q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        return a();
    }

    public bk getChartAnimator() {
        return this.f1531a;
    }

    public nl getChartConfig() {
        return this.c;
    }

    public V getChartData() {
        return this.o;
    }

    public pn getChartPalette() {
        return this.n;
    }

    public dl getChartStrategy() {
        return this.d;
    }

    public float getContentHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public float getContentWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public float getCurrentScaleX() {
        return this.v;
    }

    public float getCurrentScaleY() {
        return this.u;
    }

    public float getEndIndex() {
        return this.x;
    }

    public en getGestureSubViewListener() {
        return this.m;
    }

    public fn getItemClickedListener() {
        return this.r;
    }

    public Matrix getScaleAndTransMatrix() {
        return this.y;
    }

    public Integer getSelecetedSetIndex() {
        return this.j;
    }

    public Integer getSelectedIndex() {
        return this.i;
    }

    public gn getSelectedListener() {
        return this.l;
    }

    public float getStartIndex() {
        return this.w;
    }

    public zn getTransformUtil() {
        return this.f1532e;
    }

    public bo getViewportHandler() {
        return this.b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        postInvalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d.c();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (getChartConfig().h) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR));
            paint.setStyle(Paint.Style.FILL);
            RectF rectF = new RectF();
            rectF.left = 0.0f;
            rectF.top = 0.0f;
            rectF.right = getContentWidth();
            rectF.bottom = getContentHeight();
            canvas.drawRect(rectF, paint);
        }
        this.d.a(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            getChildAt(i5).layout(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this instanceof BaseAxisChart) {
            this.d.c();
            ((BaseAxisChart) this).getmChartGraphicBuffer().a(true);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.c.f8539e) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction() & 255;
        boolean z = false;
        if (action == 0) {
            this.q = true;
            this.g = motionEvent.getX();
            this.h = motionEvent.getY();
            this.t = System.currentTimeMillis();
        } else if (action == 1) {
            if (System.currentTimeMillis() - this.t > 300) {
                this.q = false;
            }
            if (this.q) {
                a(this);
            }
        } else if (action == 2 && (motionEvent.getX() - this.g > 30.0f || motionEvent.getY() - this.h > 30.0f)) {
            this.q = false;
        }
        this.d.a(null, motionEvent);
        if (getItemClickedListener() != null && c() && (motionEvent.getAction() & 255) == 1 && !a(motionEvent)) {
            getItemClickedListener().a(getSelectedIndex(), null, null, null);
            z = true;
        }
        if (!z && getSelectedListener() != null) {
            getSelectedListener().a(getSelectedIndex(), null, null, null);
        }
        return true;
    }

    public void setChartConfig(nl nlVar) {
        this.c = nlVar;
    }

    public void setCurrentScaleX(float f) {
        this.v = f;
    }

    public void setCurrentScaleY(float f) {
        this.u = f;
    }

    public void setEndIndex(float f) {
        this.x = f;
    }

    public void setGestureSubViewListener(en enVar) {
        this.m = enVar;
    }

    public synchronized void setIsTouchable(boolean z) {
        this.p = z;
    }

    public void setItemClickedListener(fn fnVar) {
        this.r = fnVar;
    }

    public void setScaleAndTransMatrix(Matrix matrix) {
        this.y = matrix;
    }

    public void setSelecetedSetIndex(Integer num) {
        this.j = num;
        setSelectMode(true);
        postInvalidate();
    }

    public void setSelectMode(boolean z) {
        this.k = z;
    }

    public void setSelectedIndex(Integer num) {
        this.i = num;
        setSelectMode(true);
        postInvalidate();
    }

    public void setSelectedListener(gn gnVar) {
        this.l = gnVar;
    }

    public void setStartIndex(float f) {
        this.w = f;
    }

    public Chart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1531a = new bk(this);
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = null;
        this.j = null;
        this.k = false;
        this.p = true;
        this.q = false;
        this.u = 1.0f;
        this.v = 1.0f;
        this.y = new Matrix();
        a(context, attributeSet);
    }

    public boolean a() {
        return this.p;
    }

    public final void a(View view) {
        if ((view instanceof ViewGroup) && (view instanceof View)) {
            view.performClick();
            if (view.getParent() == null || !(view.getParent() instanceof ViewGroup)) {
                return;
            }
            a((View) view.getParent());
        }
    }

    public boolean a(MotionEvent motionEvent) {
        if (getChartData() == null) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return x < getViewportHandler().c() || x > ((float) getMeasuredWidth()) - getViewportHandler().d() || y < getViewportHandler().e() || y > ((float) getMeasuredHeight()) - getViewportHandler().b();
    }
}
