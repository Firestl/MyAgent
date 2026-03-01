package io.dcloud.feature.gallery.imageedit.c.j;

import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.gallery.imageedit.view.IMGStickerView;

/* JADX INFO: loaded from: classes2.dex */
public class b implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f6543a;
    public IMGStickerView b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f6544e;
    public double f;
    public Matrix g = new Matrix();

    public b(IMGStickerView iMGStickerView, View view) {
        this.f6543a = view;
        this.b = iMGStickerView;
        view.setOnTouchListener(this);
    }

    public static double a(float f, float f2) {
        return Math.toDegrees(Math.atan2(f, f2));
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.d = 0.0f;
            this.c = 0.0f;
            float x2 = (this.f6543a.getX() + x) - this.b.getPivotX();
            float y2 = (this.f6543a.getY() + y) - this.b.getPivotY();
            Log.d("IMGStickerAdjustHelper", StringUtil.format("X=%f,Y=%f", Float.valueOf(x2), Float.valueOf(y2)));
            this.f6544e = a(0.0f, 0.0f, x2, y2);
            this.f = a(y2, x2);
            this.g.setTranslate(x2 - x, y2 - y);
            Log.d("IMGStickerAdjustHelper", StringUtil.format("degrees=%f", Double.valueOf(a(y2, x2))));
            this.g.postRotate((float) (-a(y2, x2)), this.c, this.d);
            return true;
        }
        if (action != 2) {
            return false;
        }
        float[] fArr = {motionEvent.getX(), motionEvent.getY()};
        float x3 = (this.f6543a.getX() + fArr[0]) - this.b.getPivotX();
        float y3 = (this.f6543a.getY() + fArr[1]) - this.b.getPivotY();
        Log.d("IMGStickerAdjustHelper", StringUtil.format("X=%f,Y=%f", Float.valueOf(x3), Float.valueOf(y3)));
        double dA = a(0.0f, 0.0f, x3, y3);
        double dA2 = a(y3, x3);
        this.b.a((float) (dA / this.f6544e));
        Log.d("IMGStickerAdjustHelper", "    D   = " + (dA2 - this.f));
        IMGStickerView iMGStickerView = this.b;
        iMGStickerView.setRotation((float) ((((double) iMGStickerView.getRotation()) + dA2) - this.f));
        this.f6544e = dA;
        return true;
    }

    public static double a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return Math.sqrt((f5 * f5) + (f6 * f6));
    }
}
