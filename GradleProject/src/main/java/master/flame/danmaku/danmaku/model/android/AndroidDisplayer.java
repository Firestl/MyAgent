package master.flame.danmaku.danmaku.model.android;

import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import org.bouncycastle.math.ec.rfc7748.X25519Field;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidDisplayer extends AbsDisplayer<Canvas, Typeface> {
    public Canvas canvas;
    public int height;
    public int width;
    public Camera camera = new Camera();
    public Matrix matrix = new Matrix();
    public final DisplayerConfig mDisplayConfig = new DisplayerConfig();
    public BaseCacheStuffer sStuffer = new SimpleTextCacheStuffer();
    public float density = 1.0f;
    public int densityDpi = 160;
    public float scaledDensity = 1.0f;
    public int mSlopPixel = 0;
    public boolean mIsHardwareAccelerated = true;
    public int mMaximumBitmapWidth = 2048;
    public int mMaximumBitmapHeight = 2048;

    public static class DisplayerConfig {
        public static final int BORDER_WIDTH = 4;
        public Paint ALPHA_PAINT;
        public Paint BORDER_PAINT;
        public final TextPaint PAINT;
        public final TextPaint PAINT_DUPLICATE;
        public Paint UNDERLINE_PAINT;
        public boolean isTranslucent;
        public float sLastScaleTextSize;
        public final Map<Float, Float> sCachedScaleSize = new HashMap(10);
        public int UNDERLINE_HEIGHT = 4;
        public float SHADOW_RADIUS = 4.0f;
        public float STROKE_WIDTH = 3.5f;
        public float sProjectionOffsetX = 1.0f;
        public float sProjectionOffsetY = 1.0f;
        public int sProjectionAlpha = 204;
        public boolean CONFIG_HAS_SHADOW = false;
        public boolean HAS_SHADOW = false;
        public boolean CONFIG_HAS_STROKE = true;
        public boolean HAS_STROKE = true;
        public boolean CONFIG_HAS_PROJECTION = false;
        public boolean HAS_PROJECTION = false;
        public boolean CONFIG_ANTI_ALIAS = true;
        public boolean ANTI_ALIAS = true;
        public int transparency = AlphaValue.MAX;
        public float scaleTextSize = 1.0f;
        public boolean isTextScaled = false;

        public DisplayerConfig() {
            TextPaint textPaint = new TextPaint();
            this.PAINT = textPaint;
            textPaint.setStrokeWidth(this.STROKE_WIDTH);
            this.PAINT_DUPLICATE = new TextPaint(this.PAINT);
            this.ALPHA_PAINT = new Paint();
            Paint paint = new Paint();
            this.UNDERLINE_PAINT = paint;
            paint.setStrokeWidth(this.UNDERLINE_HEIGHT);
            this.UNDERLINE_PAINT.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.BORDER_PAINT = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            this.BORDER_PAINT.setStrokeWidth(4.0f);
        }

        private void applyTextScaleConfig(BaseDanmaku baseDanmaku, Paint paint) {
            if (this.isTextScaled) {
                Float fValueOf = this.sCachedScaleSize.get(Float.valueOf(baseDanmaku.textSize));
                if (fValueOf == null || this.sLastScaleTextSize != this.scaleTextSize) {
                    float f = this.scaleTextSize;
                    this.sLastScaleTextSize = f;
                    fValueOf = Float.valueOf(baseDanmaku.textSize * f);
                    this.sCachedScaleSize.put(Float.valueOf(baseDanmaku.textSize), fValueOf);
                }
                paint.setTextSize(fValueOf.floatValue());
            }
        }

        public void applyPaintConfig(BaseDanmaku baseDanmaku, Paint paint, boolean z) {
            if (this.isTranslucent) {
                if (z) {
                    paint.setStyle(this.HAS_PROJECTION ? Paint.Style.FILL : Paint.Style.STROKE);
                    paint.setColor(baseDanmaku.textShadowColor & X25519Field.M24);
                    paint.setAlpha(this.HAS_PROJECTION ? (int) (this.sProjectionAlpha * (this.transparency / AlphaValue.MAX)) : this.transparency);
                    return;
                } else {
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(baseDanmaku.textColor & X25519Field.M24);
                    paint.setAlpha(this.transparency);
                    return;
                }
            }
            if (z) {
                paint.setStyle(this.HAS_PROJECTION ? Paint.Style.FILL : Paint.Style.STROKE);
                paint.setColor(baseDanmaku.textShadowColor & X25519Field.M24);
                paint.setAlpha(this.HAS_PROJECTION ? this.sProjectionAlpha : AlphaValue.MAX);
            } else {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(baseDanmaku.textColor & X25519Field.M24);
                paint.setAlpha(AlphaValue.MAX);
            }
        }

        public void clearTextHeightCache() {
            this.sCachedScaleSize.clear();
        }

        public void definePaintParams(boolean z) {
            this.HAS_STROKE = this.CONFIG_HAS_STROKE;
            this.HAS_SHADOW = this.CONFIG_HAS_SHADOW;
            this.HAS_PROJECTION = this.CONFIG_HAS_PROJECTION;
            this.ANTI_ALIAS = z && this.CONFIG_ANTI_ALIAS;
        }

        public Paint getBorderPaint(BaseDanmaku baseDanmaku) {
            this.BORDER_PAINT.setColor(baseDanmaku.borderColor);
            return this.BORDER_PAINT;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.text.TextPaint getPaint(master.flame.danmaku.danmaku.model.BaseDanmaku r4, boolean r5) {
            /*
                r3 = this;
                if (r5 == 0) goto L5
                android.text.TextPaint r5 = r3.PAINT
                goto Lc
            L5:
                android.text.TextPaint r5 = r3.PAINT_DUPLICATE
                android.text.TextPaint r0 = r3.PAINT
                r5.set(r0)
            Lc:
                float r0 = r4.textSize
                r5.setTextSize(r0)
                r3.applyTextScaleConfig(r4, r5)
                boolean r0 = r3.HAS_SHADOW
                if (r0 == 0) goto L28
                float r0 = r3.SHADOW_RADIUS
                r1 = 0
                int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r2 <= 0) goto L28
                int r4 = r4.textShadowColor
                if (r4 != 0) goto L24
                goto L28
            L24:
                r5.setShadowLayer(r0, r1, r1, r4)
                goto L2b
            L28:
                r5.clearShadowLayer()
            L2b:
                boolean r4 = r3.ANTI_ALIAS
                r5.setAntiAlias(r4)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.danmaku.model.android.AndroidDisplayer.DisplayerConfig.getPaint(master.flame.danmaku.danmaku.model.BaseDanmaku, boolean):android.text.TextPaint");
        }

        public float getStrokeWidth() {
            if (this.HAS_SHADOW && this.HAS_STROKE) {
                return Math.max(this.SHADOW_RADIUS, this.STROKE_WIDTH);
            }
            if (this.HAS_SHADOW) {
                return this.SHADOW_RADIUS;
            }
            if (this.HAS_STROKE) {
                return this.STROKE_WIDTH;
            }
            return 0.0f;
        }

        public Paint getUnderlinePaint(BaseDanmaku baseDanmaku) {
            this.UNDERLINE_PAINT.setColor(baseDanmaku.underlineColor);
            return this.UNDERLINE_PAINT;
        }

        public boolean hasStroke(BaseDanmaku baseDanmaku) {
            return (this.HAS_STROKE || this.HAS_PROJECTION) && this.STROKE_WIDTH > 0.0f && baseDanmaku.textShadowColor != 0;
        }

        public void setFakeBoldText(boolean z) {
            this.PAINT.setFakeBoldText(z);
        }

        public void setProjectionConfig(float f, float f2, int i) {
            if (this.sProjectionOffsetX == f && this.sProjectionOffsetY == f2 && this.sProjectionAlpha == i) {
                return;
            }
            if (f <= 1.0f) {
                f = 1.0f;
            }
            this.sProjectionOffsetX = f;
            if (f2 <= 1.0f) {
                f2 = 1.0f;
            }
            this.sProjectionOffsetY = f2;
            if (i < 0) {
                i = 0;
            } else if (i > 255) {
                i = 255;
            }
            this.sProjectionAlpha = i;
        }

        public void setScaleTextSizeFactor(float f) {
            this.isTextScaled = f != 1.0f;
            this.scaleTextSize = f;
        }

        public void setShadowRadius(float f) {
            this.SHADOW_RADIUS = f;
        }

        public void setStrokeWidth(float f) {
            this.PAINT.setStrokeWidth(f);
            this.STROKE_WIDTH = f;
        }

        public void setTransparency(int i) {
            this.isTranslucent = i != AlphaValue.MAX;
            this.transparency = i;
        }

        public void setTypeface(Typeface typeface) {
            this.PAINT.setTypeface(typeface);
        }
    }

    private void calcPaintWH(BaseDanmaku baseDanmaku, TextPaint textPaint, boolean z) {
        this.sStuffer.measure(baseDanmaku, textPaint, z);
        setDanmakuPaintWidthAndHeight(baseDanmaku, baseDanmaku.paintWidth, baseDanmaku.paintHeight);
    }

    @SuppressLint({"NewApi"})
    public static final int getMaximumBitmapHeight(Canvas canvas) {
        return Build.VERSION.SDK_INT >= 14 ? canvas.getMaximumBitmapHeight() : canvas.getHeight();
    }

    @SuppressLint({"NewApi"})
    public static final int getMaximumBitmapWidth(Canvas canvas) {
        return Build.VERSION.SDK_INT >= 14 ? canvas.getMaximumBitmapWidth() : canvas.getWidth();
    }

    private synchronized TextPaint getPaint(BaseDanmaku baseDanmaku, boolean z) {
        return this.mDisplayConfig.getPaint(baseDanmaku, z);
    }

    private void resetPaintAlpha(Paint paint) {
        int alpha = paint.getAlpha();
        int i = AlphaValue.MAX;
        if (alpha != i) {
            paint.setAlpha(i);
        }
    }

    private void restoreCanvas(Canvas canvas) {
        canvas.restore();
    }

    private int saveCanvas(BaseDanmaku baseDanmaku, Canvas canvas, float f, float f2) {
        this.camera.save();
        this.camera.rotateY(-baseDanmaku.rotationY);
        this.camera.rotateZ(-baseDanmaku.rotationZ);
        this.camera.getMatrix(this.matrix);
        this.matrix.preTranslate(-f, -f2);
        this.matrix.postTranslate(f, f2);
        this.camera.restore();
        int iSave = canvas.save();
        canvas.concat(this.matrix);
        return iSave;
    }

    private void setDanmakuPaintWidthAndHeight(BaseDanmaku baseDanmaku, float f, float f2) {
        int i = baseDanmaku.padding;
        float f3 = f + (i * 2);
        float f4 = f2 + (i * 2);
        if (baseDanmaku.borderColor != 0) {
            float f5 = 8;
            f3 += f5;
            f4 += f5;
        }
        baseDanmaku.paintWidth = f3 + getStrokeWidth();
        baseDanmaku.paintHeight = f4;
    }

    private void update(Canvas canvas) {
        this.canvas = canvas;
        if (canvas != null) {
            this.width = canvas.getWidth();
            this.height = canvas.getHeight();
            if (this.mIsHardwareAccelerated) {
                this.mMaximumBitmapWidth = getMaximumBitmapWidth(canvas);
                this.mMaximumBitmapHeight = getMaximumBitmapHeight(canvas);
            }
        }
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void clearTextHeightCache() {
        this.sStuffer.clearCaches();
        this.mDisplayConfig.clearTextHeightCache();
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int draw(BaseDanmaku baseDanmaku) {
        Paint paint;
        boolean z;
        boolean z2;
        float top = baseDanmaku.getTop();
        float left = baseDanmaku.getLeft();
        if (this.canvas == null) {
            return 0;
        }
        Paint paint2 = null;
        int i = 1;
        if (baseDanmaku.getType() != 7) {
            paint = null;
            z = false;
        } else {
            if (baseDanmaku.getAlpha() == AlphaValue.TRANSPARENT) {
                return 0;
            }
            if (baseDanmaku.rotationZ == 0.0f && baseDanmaku.rotationY == 0.0f) {
                z2 = false;
            } else {
                saveCanvas(baseDanmaku, this.canvas, left, top);
                z2 = true;
            }
            if (baseDanmaku.getAlpha() != AlphaValue.MAX) {
                paint2 = this.mDisplayConfig.ALPHA_PAINT;
                paint2.setAlpha(baseDanmaku.getAlpha());
            }
            paint = paint2;
            z = z2;
        }
        if (paint != null && paint.getAlpha() == AlphaValue.TRANSPARENT) {
            return 0;
        }
        if (!this.sStuffer.drawCache(baseDanmaku, this.canvas, left, top, paint, this.mDisplayConfig.PAINT)) {
            if (paint != null) {
                this.mDisplayConfig.PAINT.setAlpha(paint.getAlpha());
            } else {
                resetPaintAlpha(this.mDisplayConfig.PAINT);
            }
            drawDanmaku(baseDanmaku, this.canvas, left, top, false);
            i = 2;
        }
        if (z) {
            restoreCanvas(this.canvas);
        }
        return i;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public BaseCacheStuffer getCacheStuffer() {
        return this.sStuffer;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public float getDensity() {
        return this.density;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getDensityDpi() {
        return this.densityDpi;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getHeight() {
        return this.height;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getMaximumCacheHeight() {
        return this.mMaximumBitmapHeight;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getMaximumCacheWidth() {
        return this.mMaximumBitmapWidth;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public float getScaledDensity() {
        return this.scaledDensity;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getSlopPixel() {
        return this.mSlopPixel;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public float getStrokeWidth() {
        return this.mDisplayConfig.getStrokeWidth();
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public int getWidth() {
        return this.width;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer, master.flame.danmaku.danmaku.model.IDisplayer
    public boolean isHardwareAccelerated() {
        return this.mIsHardwareAccelerated;
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void measure(BaseDanmaku baseDanmaku, boolean z) {
        TextPaint paint = getPaint(baseDanmaku, z);
        if (this.mDisplayConfig.HAS_STROKE) {
            this.mDisplayConfig.applyPaintConfig(baseDanmaku, paint, true);
        }
        calcPaintWH(baseDanmaku, paint, z);
        if (this.mDisplayConfig.HAS_STROKE) {
            this.mDisplayConfig.applyPaintConfig(baseDanmaku, paint, false);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void prepare(BaseDanmaku baseDanmaku, boolean z) {
        BaseCacheStuffer baseCacheStuffer = this.sStuffer;
        if (baseCacheStuffer != null) {
            baseCacheStuffer.prepare(baseDanmaku, z);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void recycle(BaseDanmaku baseDanmaku) {
        BaseCacheStuffer baseCacheStuffer = this.sStuffer;
        if (baseCacheStuffer != null) {
            baseCacheStuffer.releaseResource(baseDanmaku);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void resetSlopPixel(float f) {
        float fMax = Math.max(f, getWidth() / 682.0f) * 25.0f;
        this.mSlopPixel = (int) fMax;
        if (f > 1.0f) {
            this.mSlopPixel = (int) (fMax * f);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setCacheStuffer(BaseCacheStuffer baseCacheStuffer) {
        if (baseCacheStuffer != this.sStuffer) {
            this.sStuffer = baseCacheStuffer;
        }
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setDanmakuStyle(int i, float[] fArr) {
        if (i != -1) {
            if (i == 0) {
                DisplayerConfig displayerConfig = this.mDisplayConfig;
                displayerConfig.CONFIG_HAS_SHADOW = false;
                displayerConfig.CONFIG_HAS_STROKE = false;
                displayerConfig.CONFIG_HAS_PROJECTION = false;
                return;
            }
            if (i == 1) {
                DisplayerConfig displayerConfig2 = this.mDisplayConfig;
                displayerConfig2.CONFIG_HAS_SHADOW = true;
                displayerConfig2.CONFIG_HAS_STROKE = false;
                displayerConfig2.CONFIG_HAS_PROJECTION = false;
                setShadowRadius(fArr[0]);
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                DisplayerConfig displayerConfig3 = this.mDisplayConfig;
                displayerConfig3.CONFIG_HAS_SHADOW = false;
                displayerConfig3.CONFIG_HAS_STROKE = false;
                displayerConfig3.CONFIG_HAS_PROJECTION = true;
                setProjectionConfig(fArr[0], fArr[1], (int) fArr[2]);
                return;
            }
        }
        DisplayerConfig displayerConfig4 = this.mDisplayConfig;
        displayerConfig4.CONFIG_HAS_SHADOW = false;
        displayerConfig4.CONFIG_HAS_STROKE = true;
        displayerConfig4.CONFIG_HAS_PROJECTION = false;
        setPaintStorkeWidth(fArr[0]);
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setDensities(float f, int i, float f2) {
        this.density = f;
        this.densityDpi = i;
        this.scaledDensity = f2;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setFakeBoldText(boolean z) {
        this.mDisplayConfig.setFakeBoldText(z);
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setHardwareAccelerated(boolean z) {
        this.mIsHardwareAccelerated = z;
    }

    public void setPaintStorkeWidth(float f) {
        this.mDisplayConfig.setStrokeWidth(f);
    }

    public void setProjectionConfig(float f, float f2, int i) {
        this.mDisplayConfig.setProjectionConfig(f, f2, i);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setScaleTextSizeFactor(float f) {
        this.mDisplayConfig.setScaleTextSizeFactor(f);
    }

    public void setShadowRadius(float f) {
        this.mDisplayConfig.setShadowRadius(f);
    }

    @Override // master.flame.danmaku.danmaku.model.IDisplayer
    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setTransparency(int i) {
        this.mDisplayConfig.setTransparency(i);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public synchronized void drawDanmaku(BaseDanmaku baseDanmaku, Canvas canvas, float f, float f2, boolean z) {
        if (this.sStuffer != null) {
            this.sStuffer.drawDanmaku(baseDanmaku, canvas, f, f2, z, this.mDisplayConfig);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public Canvas getExtraData() {
        return this.canvas;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setExtraData(Canvas canvas) {
        update(canvas);
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDisplayer
    public void setTypeFace(Typeface typeface) {
        this.mDisplayConfig.setTypeface(typeface);
    }
}
