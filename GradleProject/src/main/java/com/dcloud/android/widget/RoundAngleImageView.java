package com.dcloud.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import io.dcloud.common.adapter.util.CanvasHelper;

/* JADX INFO: loaded from: classes.dex */
public class RoundAngleImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Path f1796a;

    public RoundAngleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1796a = new Path();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        canvas.save();
        this.f1796a.addRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), CanvasHelper.dip2px(getContext(), 8.0f), CanvasHelper.dip2px(getContext(), 8.0f), Path.Direction.CCW);
        canvas.clipPath(this.f1796a);
        if (getDrawable() == null) {
            canvas.drawColor(-3355444);
        }
        super.onDraw(canvas);
        canvas.restore();
    }
}
