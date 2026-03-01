package io.dcloud.feature.nativeObj.photoview.subscaleview;

import android.graphics.PointF;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class ImageViewState implements Serializable {
    public final float centerX;
    public final float centerY;
    public final int orientation;
    public final float scale;

    public ImageViewState(float f, PointF pointF, int i) {
        this.scale = f;
        this.centerX = pointF.x;
        this.centerY = pointF.y;
        this.orientation = i;
    }

    public PointF getCenter() {
        return new PointF(this.centerX, this.centerY);
    }

    public int getOrientation() {
        return this.orientation;
    }

    public float getScale() {
        return this.scale;
    }
}
