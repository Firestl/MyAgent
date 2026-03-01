package com.sangfor.sdk.netruletoast.view;

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
public class LimitNewLineTextView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f3929a;
    public int b;
    public String c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3930e;
    public float f;
    public Paint.FontMetricsInt g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public LimitNewLineTextView(Context context) {
        this(context, null);
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
        this.f3929a = paint;
        paint.setTextSize(this.d);
        this.f3929a.setAntiAlias(true);
        this.f3929a.setStrokeWidth(2.0f);
        this.f3929a.setColor(Color.parseColor(this.c));
        this.f3929a.setTextAlign(Paint.Align.LEFT);
        Paint.FontMetricsInt fontMetricsInt = this.f3929a.getFontMetricsInt();
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

    public LimitNewLineTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LimitNewLineTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.c = "#ffffff";
        this.d = 60;
        this.f3930e = "";
        this.n = 2;
        a(context, attributeSet);
        b();
    }

    public final float a() {
        int i = this.n;
        return (this.i * i) + (this.f * (i - 1)) + this.m + this.l;
    }

    public final void b(Canvas canvas, String str, float f) {
        StringBuilder sb = new StringBuilder();
        sb.append("受网络隔离规则限制,\"");
        String strSubstring = this.f3930e;
        if (TextUtils.isEmpty(strSubstring)) {
            SFLogN.b("LimitNewLineTextView", "handlerMoreThanLimit fail", "message is empty");
            return;
        }
        String str2 = "..." + str;
        int iA = a("受网络隔离规则限制,\"" + strSubstring + str2);
        while (iA > this.n) {
            if (strSubstring.length() > 1) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                iA = a("受网络隔离规则限制,\"" + strSubstring + str2);
            } else {
                SFLogN.b("LimitNewLineTextView", "handlerMoreThanLimit fail", "The view cannot show message");
                return;
            }
        }
        sb.append(strSubstring + str2);
        a(canvas, sb.toString().trim(), f);
    }

    public final void a(Canvas canvas) {
        int i = this.l + (this.i / 2);
        int i2 = this.g.bottom;
        float f = ((i + ((i2 - r1.top) / 2)) - i2) + 0.0f;
        int iA = a("受网络隔离规则限制,\"" + this.f3930e + "\"请求被拦截");
        if (iA == 1) {
            a(canvas, "受网络隔离规则限制,\"" + this.f3930e + "\"请求被拦截");
            return;
        }
        if (iA <= this.n) {
            a(canvas, "受网络隔离规则限制,\"" + this.f3930e + "\"请求被拦截", f);
            return;
        }
        b(canvas, "\"请求被拦截", f);
    }

    public final void a(Canvas canvas, String str) {
        Paint.FontMetrics fontMetrics = this.f3929a.getFontMetrics();
        canvas.drawText(str, this.j, (getHeight() / 2) - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f), this.f3929a);
    }

    public final int a(String str) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        int i = str.length() > 0 ? 1 : 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (this.f3929a.measureText(sb.toString()) + this.f3929a.measureText(String.valueOf(charArray[i2])) <= this.h) {
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
            if (this.f3929a.measureText(sb.toString()) + this.f3929a.measureText(String.valueOf(charArray[i])) <= this.h) {
                sb.append(charArray[i]);
            } else {
                canvas.drawText(sb.toString(), this.j, f, this.f3929a);
                f += this.i + this.f;
                sb = new StringBuilder();
                sb.append(charArray[i]);
            }
        }
        if (sb.toString().length() > 0) {
            canvas.drawText(sb.toString(), this.j, f, this.f3929a);
        }
    }
}
