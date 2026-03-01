package com.sangfor.sdk.randcodeui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import supwisdom.l91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class SangforSdkTextView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3935a;

    public SangforSdkTextView(Context context) {
        super(context);
    }

    public void a(String str) {
        this.f3935a = str;
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        a(canvas);
    }

    public SangforSdkTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SangforSdkTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final void a(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(l91.a(12.0f));
        paint.setColor(-1);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        canvas.drawText(this.f3935a, (getWidth() / 2) - (paint.measureText(this.f3935a) / 2.0f), (getHeight() / 2) - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f), paint);
    }
}
