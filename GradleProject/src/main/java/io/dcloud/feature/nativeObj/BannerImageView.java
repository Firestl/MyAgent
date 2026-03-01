package io.dcloud.feature.nativeObj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import io.dcloud.feature.nativeObj.data.NativeImageDataItem;

/* JADX INFO: loaded from: classes3.dex */
public class BannerImageView extends ImageView {
    public NativeImageDataItem mImageData;
    public Paint mPaint;

    public BannerImageView(Context context, NativeImageDataItem nativeImageDataItem) {
        super(context);
        this.mPaint = new Paint();
        this.mImageData = nativeImageDataItem;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        float height;
        float width;
        float height2;
        float width2;
        float f;
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            return;
        }
        Bitmap bitmap = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getBitmap() : null;
        if (bitmap == null) {
            return;
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        float f2 = getContext().getResources().getDisplayMetrics().density;
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        if (this.mImageData.height.equals("auto") && this.mImageData.width.equals("auto")) {
            float height3 = getHeight() < getWidth() ? getHeight() / intrinsicHeight : getWidth() / intrinsicWidth;
            width = intrinsicWidth * height3;
            height2 = height3 * intrinsicHeight;
        } else {
            if (this.mImageData.height.equals("auto")) {
                float width3 = this.mImageData.getWidth(getWidth(), f2);
                height = intrinsicHeight * (width3 / intrinsicWidth);
                width = width3;
            } else if (this.mImageData.width.equals("auto")) {
                height2 = this.mImageData.getHeight(getHeight(), f2);
                width = intrinsicWidth * (height2 / intrinsicHeight);
            } else {
                height = this.mImageData.getHeight(getHeight(), f2);
                width = this.mImageData.getWidth(getWidth(), f2);
            }
            height2 = height;
        }
        float height4 = 0.0f;
        if (width == getWidth() || this.mImageData.align.equals("left")) {
            width2 = width;
            f = 0.0f;
        } else if (this.mImageData.align.equals("right")) {
            width2 = getWidth();
            f = width2 - width;
        } else {
            float width4 = (getWidth() - width) / 2.0f;
            float f3 = width + width4;
            f = width4;
            width2 = f3;
        }
        if (height2 != getHeight() && !this.mImageData.verticalAlign.equals("top")) {
            if (this.mImageData.verticalAlign.equals("bottom")) {
                float height5 = getHeight();
                height4 = height5 - height2;
                height2 = height5;
            } else {
                height4 = (getHeight() - height2) / 2.0f;
                height2 += height4;
            }
        }
        canvas.drawBitmap(bitmap, (Rect) null, new RectF(f, height4, width2, height2), this.mPaint);
        canvas.restoreToCount(saveCount);
    }
}
