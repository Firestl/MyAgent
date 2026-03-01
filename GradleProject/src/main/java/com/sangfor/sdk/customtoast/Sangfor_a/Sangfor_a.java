package com.sangfor.sdk.customtoast.Sangfor_a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.l91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class Sangfor_a extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f3919a;
    public int b;
    public String c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3920e;
    public float f;
    public Paint.FontMetricsInt g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public Sangfor_a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        if (this.c == null) {
            this.c = "#ffffff";
        }
        this.d = l91.a(14.0f);
        this.f = 0.0f;
    }

    public final void b() {
        Paint paint = new Paint();
        this.f3919a = paint;
        paint.setTextSize(this.d);
        this.f3919a.setAntiAlias(true);
        this.f3919a.setStrokeWidth(2.0f);
        this.f3919a.setColor(Color.parseColor(this.c));
        this.f3919a.setTextAlign(Paint.Align.LEFT);
        Paint.FontMetricsInt fontMetricsInt = this.f3919a.getFontMetricsInt();
        this.g = fontMetricsInt;
        this.i = Math.abs(fontMetricsInt.top - fontMetricsInt.bottom);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        a(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        this.b = measuredWidth;
        this.h = (measuredWidth - this.j) - this.k;
        setMeasuredDimension(this.b, (int) a());
    }

    public Sangfor_a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.c = "#ffffff";
        this.d = 60;
        this.f3920e = "";
        this.n = 2;
        a(context, attributeSet);
        b();
    }

    public final float a() {
        int i = this.n;
        return (this.i * i) + (this.f * (i - 1)) + this.m + this.l;
    }

    public final void a(Canvas canvas) {
        int i = this.l + (this.i / 2);
        int i2 = this.g.bottom;
        float f = ((i + ((i2 - r1.top) / 2)) - i2) + 0.0f;
        int iA = a(this.f3920e);
        if (iA == 1) {
            a(canvas, this.f3920e);
        } else if (iA <= this.n) {
            a(canvas, this.f3920e, f);
        } else {
            a(canvas, f);
        }
    }

    public final void a(Canvas canvas, String str) {
        Paint.FontMetrics fontMetrics = this.f3919a.getFontMetrics();
        canvas.drawText(str, this.j, (getHeight() / 2) - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f), this.f3919a);
    }

    public final int a(String str) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        int i = str.length() > 0 ? 1 : 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (this.f3919a.measureText(sb.toString()) + this.f3919a.measureText(String.valueOf(charArray[i2])) <= this.h) {
                sb.append(charArray[i2]);
            } else {
                sb = new StringBuilder();
                sb.append(charArray[i2]);
                i++;
            }
        }
        return i;
    }

    public final void a(Canvas canvas, String str, float f) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (this.f3919a.measureText(sb.toString()) + this.f3919a.measureText(String.valueOf(charArray[i])) <= this.h) {
                sb.append(charArray[i]);
            } else {
                canvas.drawText(sb.toString(), this.j, f, this.f3919a);
                f += this.i + this.f;
                sb = new StringBuilder();
                sb.append(charArray[i]);
            }
        }
        if (sb.toString().length() > 0) {
            canvas.drawText(sb.toString(), this.j, f, this.f3919a);
        }
    }

    public final void a(Canvas canvas, float f) {
        StringBuilder sb = new StringBuilder();
        String strSubstring = this.f3920e;
        if (TextUtils.isEmpty(strSubstring)) {
            SFLogN.b("LimitNewLineTextView", "handlerMoreThanLimit fail", "message is empty");
            return;
        }
        int iA = a(strSubstring);
        while (iA > this.n) {
            if (strSubstring.length() > 1) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                iA = a(strSubstring + "...");
            } else {
                SFLogN.b("LimitNewLineTextView", "handlerMoreThanLimit fail", "The view cannot show message");
                return;
            }
        }
        sb.append(strSubstring + "...");
        a(canvas, sb.toString().trim(), f);
    }
}
