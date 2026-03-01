package io.dcloud.feature.gallery.imageedit.c.j;

import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    public static final Matrix d = new Matrix();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f6546a;
    public float b;
    public float c;

    public d(View view) {
        this.f6546a = view;
    }

    public boolean a(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.b = motionEvent.getX();
            this.c = motionEvent.getY();
            Matrix matrix = d;
            matrix.reset();
            matrix.setRotate(view.getRotation());
            return true;
        }
        if (actionMasked != 2) {
            return false;
        }
        float[] fArr = {motionEvent.getX() - this.b, motionEvent.getY() - this.c};
        d.mapPoints(fArr);
        view.setTranslationX(this.f6546a.getTranslationX() + fArr[0]);
        view.setTranslationY(this.f6546a.getTranslationY() + fArr[1]);
        return true;
    }
}
