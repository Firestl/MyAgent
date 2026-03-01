package io.dcloud.feature.gallery.imageedit.c.f;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import io.dcloud.feature.gallery.imageedit.c.i.b;

/* JADX INFO: loaded from: classes2.dex */
public class a extends ValueAnimator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6535a = false;
    public b b;

    public a() {
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void a(io.dcloud.feature.gallery.imageedit.c.i.a aVar, io.dcloud.feature.gallery.imageedit.c.i.a aVar2) {
        setObjectValues(aVar, aVar2);
        this.f6535a = io.dcloud.feature.gallery.imageedit.c.i.a.a(aVar, aVar2);
    }

    @Override // android.animation.ValueAnimator
    public void setObjectValues(Object... objArr) {
        super.setObjectValues(objArr);
        if (this.b == null) {
            this.b = new b();
        }
        setEvaluator(this.b);
    }

    public boolean a() {
        return this.f6535a;
    }
}
