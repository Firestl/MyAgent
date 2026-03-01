package com.alibaba.dt.AChartsLib.interfaces;

import android.view.GestureDetector;
import android.view.View;
import com.alibaba.dt.AChartsLib.charts.Chart;

/* JADX INFO: loaded from: classes.dex */
public abstract class ChartTouchListener<T extends Chart<?>> extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1545a;
    public GestureDetector b;
    public T c;

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING,
        SELECT
    }

    public ChartTouchListener(T t) {
        ChartGesture chartGesture = ChartGesture.NONE;
        this.f1545a = 0;
        this.c = t;
        this.b = new GestureDetector(t.getContext(), this);
    }
}
