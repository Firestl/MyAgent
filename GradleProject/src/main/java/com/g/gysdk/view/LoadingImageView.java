package com.g.gysdk.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.g.gysdk.R;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
@Deprecated
public class LoadingImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Animation f2073a;
    public LinearInterpolator b;

    public LoadingImageView(Context context) {
        super(context);
        this.f2073a = null;
        this.b = null;
        a();
    }

    public LoadingImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2073a = null;
        this.b = null;
        a();
    }

    public LoadingImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2073a = null;
        this.b = null;
        a();
    }

    public void a() {
        this.f2073a = AnimationUtils.loadAnimation(getContext(), R.anim.gy_anim_loading);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        this.b = linearInterpolator;
        this.f2073a.setInterpolator(linearInterpolator);
    }

    public void b() {
        setVisibility(0);
        startAnimation(this.f2073a);
    }

    public void c() {
        setVisibility(8);
        clearAnimation();
    }
}
