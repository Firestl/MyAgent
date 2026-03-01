package pl.droidsonroids.gif.transforms;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes3.dex */
public class CornerRadiusTransform implements Transform {
    public float mCornerRadius;
    public final RectF mDstRectF = new RectF();
    public Shader mShader;

    public CornerRadiusTransform(float f) {
        setCornerRadiusSafely(f);
    }

    private void setCornerRadiusSafely(float f) {
        float fMax = Math.max(0.0f, f);
        if (fMax != this.mCornerRadius) {
            this.mCornerRadius = fMax;
            this.mShader = null;
        }
    }

    public RectF getBounds() {
        return this.mDstRectF;
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    @Override // pl.droidsonroids.gif.transforms.Transform
    public void onBoundsChange(Rect rect) {
        this.mDstRectF.set(rect);
        this.mShader = null;
    }

    @Override // pl.droidsonroids.gif.transforms.Transform
    public void onDraw(Canvas canvas, Paint paint, Bitmap bitmap) {
        if (this.mCornerRadius == 0.0f) {
            canvas.drawBitmap(bitmap, (Rect) null, this.mDstRectF, paint);
            return;
        }
        if (this.mShader == null) {
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.mShader = new BitmapShader(bitmap, tileMode, tileMode);
            Matrix matrix = new Matrix();
            RectF rectF = this.mDstRectF;
            matrix.setTranslate(rectF.left, rectF.top);
            matrix.preScale(this.mDstRectF.width() / bitmap.getWidth(), this.mDstRectF.height() / bitmap.getHeight());
            this.mShader.setLocalMatrix(matrix);
        }
        paint.setShader(this.mShader);
        RectF rectF2 = this.mDstRectF;
        float f = this.mCornerRadius;
        canvas.drawRoundRect(rectF2, f, f, paint);
    }

    public void setCornerRadius(float f) {
        setCornerRadiusSafely(f);
    }
}
