package com.dcloud.android.widget.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import supwisdom.iv;
import supwisdom.jv;
import supwisdom.kv;
import supwisdom.lv;
import supwisdom.mv;
import supwisdom.nv;

/* JADX INFO: loaded from: classes.dex */
public class PhotoView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public nv f1797a;

    public PhotoView(Context context) {
        this(context, null);
    }

    public final void a() {
        this.f1797a = new nv(this);
        super.setScaleType(ImageView.ScaleType.MATRIX);
    }

    public nv getAttacher() {
        return this.f1797a;
    }

    public RectF getDisplayRect() {
        return this.f1797a.d();
    }

    @Override // android.widget.ImageView
    public Matrix getImageMatrix() {
        return this.f1797a.f();
    }

    public float getMaximumScale() {
        return this.f1797a.g();
    }

    public float getMediumScale() {
        return this.f1797a.h();
    }

    public float getMinimumScale() {
        return this.f1797a.i();
    }

    public float getScale() {
        return this.f1797a.j();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.f1797a.k();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.f1797a.a(z);
    }

    @Override // android.widget.ImageView
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (frame) {
            this.f1797a.m();
        }
        return frame;
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        nv nvVar = this.f1797a;
        if (nvVar != null) {
            nvVar.m();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        nv nvVar = this.f1797a;
        if (nvVar != null) {
            nvVar.m();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        nv nvVar = this.f1797a;
        if (nvVar != null) {
            nvVar.m();
        }
    }

    public void setMaximumScale(float f) {
        this.f1797a.a(f);
    }

    public void setMediumScale(float f) {
        this.f1797a.b(f);
    }

    public void setMinimumScale(float f) {
        this.f1797a.c(f);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f1797a.a(onClickListener);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.f1797a.a(onDoubleTapListener);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f1797a.a(onLongClickListener);
    }

    public void setOnMatrixChangeListener(iv ivVar) {
        this.f1797a.a(ivVar);
    }

    public void setOnOutsidePhotoTapListener(jv jvVar) {
        this.f1797a.a(jvVar);
    }

    public void setOnPhotoTapListener(kv kvVar) {
        this.f1797a.a(kvVar);
    }

    public void setOnScaleChangeListener(lv lvVar) {
        this.f1797a.a(lvVar);
    }

    public void setOnSingleFlingListener(mv mvVar) {
        this.f1797a.a(mvVar);
    }

    public void setRotationBy(float f) {
        this.f1797a.d(f);
    }

    public void setRotationTo(float f) {
        this.f1797a.e(f);
    }

    public void setScale(float f) {
        this.f1797a.f(f);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        nv nvVar = this.f1797a;
        if (nvVar != null) {
            nvVar.a(scaleType);
        }
    }

    public void setZoomTransitionDuration(int i) {
        this.f1797a.a(i);
    }

    public void setZoomable(boolean z) {
        this.f1797a.b(z);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public PhotoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }
}
