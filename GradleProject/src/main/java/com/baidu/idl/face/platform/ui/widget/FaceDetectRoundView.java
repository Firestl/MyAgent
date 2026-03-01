package com.baidu.idl.face.platform.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import com.baidu.idl.face.platform.utils.DensityUtils;
import io.dcloud.common.util.TitleNViewUtil;

/* JADX INFO: loaded from: classes.dex */
public class FaceDetectRoundView extends View {
    public static final int CIRCLE_SPACE = 5;
    public static final float HEIGHT_EXT_RATIO = 0.2f;
    public static final float HEIGHT_RATIO = 0.1f;
    public static final int PATH_SMALL_SPACE = 12;
    public static final int PATH_SPACE = 16;
    public static final int PATH_WIDTH = 4;
    public static final float SURFACE_HEIGHT = 1000.0f;
    public static final float SURFACE_RATIO = 0.75f;
    public static final float WIDTH_SPACE_RATIO = 0.33f;
    public Paint mBGPaint;
    public Rect mFaceDetectRect;
    public Rect mFaceRect;
    public Paint mFaceRectPaint;
    public Paint mFaceRoundPaint;
    public PathEffect mFaceRoundPathEffect;
    public boolean mIsDrawDash;
    public Paint mPathPaint;
    public float mR;
    public float mX;
    public float mY;
    public static final String TAG = FaceDetectRoundView.class.getSimpleName();
    public static final int COLOR_BG = Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR);
    public static final int COLOR_RECT = Color.parseColor(TitleNViewUtil.TRANSPARENT_BUTTON_TEXT_COLOR);
    public static final int COLOR_ROUND = Color.parseColor("#FF6032");

    public FaceDetectRoundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFaceRoundPathEffect = null;
        this.mIsDrawDash = true;
        setLayerType(1, null);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float fDip2px = DensityUtils.dip2px(context, 16.0f);
        float fDip2px2 = DensityUtils.dip2px(context, 12.0f);
        float fDip2px3 = DensityUtils.dip2px(context, 4.0f);
        float[] fArr = new float[2];
        fArr[0] = fDip2px;
        fArr[1] = ((float) displayMetrics.heightPixels) < 1000.0f ? fDip2px2 : fDip2px;
        this.mFaceRoundPathEffect = new DashPathEffect(fArr, 1.0f);
        Paint paint = new Paint(1);
        this.mBGPaint = paint;
        paint.setColor(COLOR_BG);
        this.mBGPaint.setStyle(Paint.Style.FILL);
        this.mBGPaint.setAntiAlias(true);
        this.mBGPaint.setDither(true);
        Paint paint2 = new Paint(1);
        this.mPathPaint = paint2;
        paint2.setColor(COLOR_ROUND);
        this.mPathPaint.setStrokeWidth(fDip2px3);
        this.mPathPaint.setStyle(Paint.Style.STROKE);
        this.mPathPaint.setAntiAlias(true);
        this.mPathPaint.setDither(true);
        Paint paint3 = new Paint(1);
        this.mFaceRectPaint = paint3;
        paint3.setColor(COLOR_RECT);
        this.mFaceRectPaint.setStrokeWidth(fDip2px3);
        this.mFaceRectPaint.setStyle(Paint.Style.STROKE);
        this.mFaceRectPaint.setAntiAlias(true);
        this.mFaceRectPaint.setDither(true);
        Paint paint4 = new Paint(1);
        this.mFaceRoundPaint = paint4;
        paint4.setColor(COLOR_ROUND);
        this.mFaceRoundPaint.setStyle(Paint.Style.FILL);
        this.mFaceRoundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mFaceRoundPaint.setAntiAlias(true);
        this.mFaceRoundPaint.setDither(true);
    }

    public static Rect getPreviewDetectRect(int i, int i2, int i3) {
        float f = i / 2;
        float f2 = f - (0.33f * f);
        float f3 = i2 / 2;
        float f4 = i3 / 2;
        float f5 = f4 - (0.1f * f4);
        if (f3 <= f2) {
            f2 = f3;
        }
        float f6 = (0.2f * f2) + f2;
        return new Rect((int) (f3 - f2), (int) (f5 - f6), (int) (f3 + f2), (int) (f5 + f6));
    }

    public Rect getFaceRoundRect() {
        Rect rect = this.mFaceRect;
        if (rect != null) {
            Log.e(TAG, rect.toString());
        }
        return this.mFaceRect;
    }

    public float getRound() {
        return this.mR;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0);
        canvas.drawPaint(this.mBGPaint);
        if (this.mIsDrawDash) {
            this.mPathPaint.setPathEffect(this.mFaceRoundPathEffect);
        } else {
            this.mPathPaint.setPathEffect(null);
        }
        canvas.drawCircle(this.mX, this.mY, this.mR + 5.0f, this.mPathPaint);
        canvas.drawCircle(this.mX, this.mY, this.mR, this.mFaceRoundPaint);
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f = (i3 - i) / 2.0f;
        float f2 = (i4 - i2) / 2.0f;
        float f3 = f2 - (0.1f * f2);
        float f4 = f - (0.33f * f);
        if (this.mFaceRect == null) {
            this.mFaceRect = new Rect((int) (f - f4), (int) (f3 - f4), (int) (f + f4), (int) (f3 + f4));
        }
        if (this.mFaceDetectRect == null) {
            float f5 = (0.2f * f4) + f4;
            this.mFaceDetectRect = new Rect((int) (f - f4), (int) (f3 - f5), (int) (f + f4), (int) (f5 + f3));
        }
        this.mX = f;
        this.mY = f3;
        this.mR = f4;
    }

    public void processDrawState(boolean z) {
        this.mIsDrawDash = z;
        postInvalidate();
    }
}
