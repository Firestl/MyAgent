package io.dcloud.common.adapter.ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.adapter.util.CanvasHelper;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.js.map.amap.util.AMapUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FrameBitmapView extends View {
    public static String BOLD = "bold";
    public static String ITALIC = "italic";
    public static String NORMAL = "normal";
    public boolean isInit;
    public float mBitmapCX;
    public float mBitmapCY;
    public int mCutIndex;
    public RectF mCutRectF;
    public float mFontCX;
    public float mFontCY;
    public int mHeight;
    public ClearAnimationListener mListener;
    public INativeBitmap mNativeBitmap;
    public Paint mPaint;
    public float mScale;
    public boolean mStopAnimation;
    public RectF mTextRect;
    public String mTextValue;
    public String mTexts;
    public int mWidth;

    public interface ClearAnimationListener {
        void onAnimationEnd();
    }

    public FrameBitmapView(Activity activity) {
        super(activity);
        this.mTextRect = null;
        this.mCutIndex = 0;
        this.isInit = false;
        this.mStopAnimation = false;
        this.mPaint = new Paint();
    }

    public static /* synthetic */ int access$208(FrameBitmapView frameBitmapView) {
        int i = frameBitmapView.mCutIndex;
        frameBitmapView.mCutIndex = i + 1;
        return i;
    }

    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
    }

    public static float getFontLeading(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.leading - fontMetrics.ascent;
    }

    public static float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    private void initBitmapXY() {
        float f = this.mWidth;
        float f2 = this.mHeight;
        if (this.mNativeBitmap.getBitmap() != null) {
            this.mBitmapCX = (f / 2.0f) - (this.mNativeBitmap.getBitmap().getWidth() / 2);
            this.mBitmapCY = (f2 / 2.0f) - (this.mNativeBitmap.getBitmap().getHeight() / 2);
        }
    }

    private void initTextData() {
        String strOptString;
        String strOptString2;
        String strOptString3;
        String strOptString4 = "";
        if (TextUtils.isEmpty(this.mTexts)) {
            if (this.mNativeBitmap == null) {
                setVisibility(8);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.mTexts);
            this.mTextValue = jSONObject.optString("value", "");
            String strOptString5 = this.mWidth + "px";
            String strOptString6 = "44px";
            String strOptString7 = "16px";
            String strOptString8 = AMapUtil.HtmlBlack;
            String strOptString9 = NORMAL;
            String strOptString10 = "center";
            String strOptString11 = "0px";
            if (jSONObject.has("textRect")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("textRect");
                strOptString = jSONObject2.optString("top", "0px");
                strOptString2 = jSONObject2.optString("left", "0px");
                strOptString5 = jSONObject2.optString("width", strOptString5);
                strOptString6 = jSONObject2.optString("height", "44px");
            } else {
                strOptString = "0px";
                strOptString2 = strOptString;
            }
            if (jSONObject.has("textStyles")) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("textStyles");
                strOptString7 = jSONObject3.optString(AbsoluteConst.JSON_KEY_SIZE, "16px");
                strOptString8 = jSONObject3.optString("color", AMapUtil.HtmlBlack);
                strOptString3 = jSONObject3.optString("weight", strOptString9);
                strOptString9 = jSONObject3.optString("style", strOptString9);
                strOptString4 = jSONObject3.optString("family", "");
                strOptString10 = jSONObject3.optString(AbsoluteConst.JSON_KEY_ALIGN, "center");
                strOptString11 = jSONObject3.optString("margin", "0px");
            } else {
                strOptString3 = strOptString9;
            }
            int i = this.mWidth;
            int iConvertToScreenInt = PdrUtil.convertToScreenInt(strOptString2, i, i, this.mScale);
            int i2 = this.mHeight;
            int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(strOptString, i2, i2, this.mScale);
            int i3 = this.mWidth;
            int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(strOptString5, i3, i3, this.mScale);
            int i4 = this.mHeight;
            int iConvertToScreenInt4 = PdrUtil.convertToScreenInt(strOptString6, i4, i4, this.mScale);
            int i5 = this.mHeight;
            int iConvertToScreenInt5 = PdrUtil.convertToScreenInt(strOptString7, i5, i5, this.mScale);
            int iConvertToScreenInt6 = PdrUtil.convertToScreenInt(strOptString11, iConvertToScreenInt3, iConvertToScreenInt4, this.mScale);
            this.mPaint.setTextSize(iConvertToScreenInt5);
            this.mPaint.setColor(PdrUtil.stringToColor(strOptString8));
            if (!TextUtils.isEmpty(strOptString4)) {
                this.mPaint.setTypeface(Typeface.create(strOptString4, 0));
            }
            this.mTextRect = new RectF(iConvertToScreenInt + iConvertToScreenInt6, iConvertToScreenInt2 + iConvertToScreenInt6, iConvertToScreenInt3 - iConvertToScreenInt6, iConvertToScreenInt4 - iConvertToScreenInt6);
            this.mPaint.setFakeBoldText(strOptString3.equals(BOLD));
            if (strOptString9.equals(ITALIC)) {
                this.mPaint.setTextSkewX(-0.5f);
            }
            float fontlength = getFontlength(this.mPaint, this.mTextValue);
            float fontHeight = getFontHeight(this.mPaint);
            if (strOptString10.equals("right")) {
                RectF rectF = this.mTextRect;
                this.mFontCX = rectF.right - fontlength;
                this.mFontCY = rectF.top + (((int) (rectF.height() - fontHeight)) / 2);
            } else if (strOptString10.equals("left")) {
                RectF rectF2 = this.mTextRect;
                this.mFontCX = rectF2.left;
                this.mFontCY = rectF2.top + (((int) (rectF2.height() - fontHeight)) / 2);
            } else {
                this.mFontCX = this.mTextRect.left + (((int) (r2.width() - fontlength)) / 2);
                this.mFontCY = this.mTextRect.top + (((int) (r0.height() - fontHeight)) / 2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void clearData() {
        this.mNativeBitmap = null;
        this.mTextValue = null;
        this.mFontCX = 0.0f;
        this.mFontCY = 0.0f;
        this.mCutIndex = 0;
        this.mCutRectF = null;
        this.mListener = null;
        this.mStopAnimation = false;
        this.isInit = false;
    }

    public void configurationChanged(int i, int i2) {
        if (this.mNativeBitmap != null) {
            this.mWidth = i;
            this.mHeight = i2;
            initBitmapXY();
            initTextData();
            invalidate();
        }
    }

    public void injectionData(Object obj, String str, int i, int i2, float f) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mTexts = str;
        this.mScale = f;
        this.mNativeBitmap = (INativeBitmap) obj;
        this.isInit = true;
        initBitmapXY();
        initTextData();
        bringToFront();
        invalidate();
    }

    public boolean isInit() {
        return this.isInit;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.mPaint.setAntiAlias(true);
        canvas.save();
        RectF rectF = this.mCutRectF;
        if (rectF != null) {
            canvas.clipRect(rectF);
        }
        canvas.restore();
        INativeBitmap iNativeBitmap = this.mNativeBitmap;
        if (iNativeBitmap != null && iNativeBitmap.getBitmap() != null) {
            canvas.save();
            canvas.drawBitmap(this.mNativeBitmap.getBitmap(), this.mBitmapCX, this.mBitmapCY, this.mPaint);
            canvas.restore();
        }
        if (TextUtils.isEmpty(this.mTextValue)) {
            return;
        }
        canvas.save();
        canvas.clipRect(this.mTextRect);
        CanvasHelper.drawString(canvas, this.mTextValue, (int) this.mFontCX, (int) this.mFontCY, 17, this.mPaint);
        canvas.restore();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void runClearAnimation(int i, int i2, ClearAnimationListener clearAnimationListener) {
        this.mListener = clearAnimationListener;
        runClearAnimation(i, i2);
    }

    public void setStopAnimation(boolean z) {
        this.mStopAnimation = z;
    }

    public void runClearAnimation(final int i, final int i2) {
        postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.FrameBitmapView.1
            @Override // java.lang.Runnable
            public void run() {
                if (FrameBitmapView.this.mStopAnimation) {
                    if (FrameBitmapView.this.mListener != null) {
                        FrameBitmapView.this.mListener.onAnimationEnd();
                        return;
                    }
                    return;
                }
                FrameBitmapView.access$208(FrameBitmapView.this);
                int height = FrameBitmapView.this.mNativeBitmap.getBitmap().getHeight();
                int width = FrameBitmapView.this.mNativeBitmap.getBitmap().getWidth();
                int i3 = height / i;
                FrameBitmapView.this.mCutRectF = new RectF(0.0f, i3 * FrameBitmapView.this.mCutIndex, width, height);
                FrameBitmapView.this.invalidate();
                int i4 = FrameBitmapView.this.mCutIndex;
                int i5 = i;
                if (i4 < i5) {
                    FrameBitmapView.this.runClearAnimation(i5, i2);
                } else if (FrameBitmapView.this.mListener != null) {
                    FrameBitmapView.this.mListener.onAnimationEnd();
                }
            }
        }, i2);
    }
}
