package io.dcloud.feature.internal.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public class b extends View {
    public static float D = 2.0f;
    public Path A;
    public int B;
    public PaintFlagsDrawFilter C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public DisplayMetrics f6562a;
    public Bitmap b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6563e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public RectF x;
    public RectF y;
    public Paint z;

    public b(Context context, boolean z) {
        super(context);
        this.b = null;
        this.z = new Paint();
        this.A = new Path();
        this.B = -1;
        this.C = null;
        if (z) {
            D = 6.0f;
        }
        this.C = new PaintFlagsDrawFilter(0, 3);
    }

    private void b(Canvas canvas) {
        this.z.reset();
        this.z.setAntiAlias(true);
        this.z.setStyle(Paint.Style.STROKE);
        this.z.setStrokeWidth(this.h);
        this.z.setColor(this.v);
        canvas.drawArc(this.y, this.t, this.u, false, this.z);
        int i = (int) (this.u + D);
        this.u = i;
        if (i > 360) {
            this.u = i - 360;
        }
        invalidate();
    }

    private void c(Canvas canvas) {
        this.z.reset();
        Bitmap bitmap = this.b;
        if (bitmap == null || bitmap.isRecycled()) {
            this.z.setColor(-1118482);
            this.z.setAntiAlias(true);
            this.z.setStyle(Paint.Style.FILL);
            RectF rectF = this.x;
            float fWidth = rectF.left + (rectF.width() / 2.0f);
            RectF rectF2 = this.x;
            canvas.drawCircle(fWidth, rectF2.top + (rectF2.height() / 2.0f), this.x.width() / 2.0f, this.z);
            return;
        }
        canvas.save();
        try {
            canvas.clipPath(this.A);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.z.setAntiAlias(true);
        canvas.setDrawFilter(this.C);
        canvas.drawBitmap(this.b, (Rect) null, this.x, this.z);
        canvas.restore();
        this.z.setStrokeWidth((this.g * 4) + (Build.VERSION.SDK_INT > 19 ? 3 : 40));
        this.z.setStyle(Paint.Style.STROKE);
        this.z.setAntiAlias(true);
        this.z.setColor(this.B);
        RectF rectF3 = this.x;
        float fWidth2 = rectF3.left + (rectF3.width() / 2.0f);
        RectF rectF4 = this.x;
        canvas.drawCircle(fWidth2, rectF4.top + (rectF4.height() / 2.0f), ((this.x.width() / 2.0f) + (r3 / 2)) - (r0 / 8), this.z);
    }

    public float a(float f) {
        if (this.f6562a == null) {
            this.f6562a = getResources().getDisplayMetrics();
        }
        return TypedValue.applyDimension(1, f, this.f6562a);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        c(canvas);
        a(canvas);
        b(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(this.c, this.d);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        this.B = i;
    }

    public void setBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = this.b;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.b.recycle();
        }
        this.b = bitmap;
    }

    public void a(Bitmap bitmap, int i, int i2, int i3, int i4, int i5) {
        int left = getLeft();
        int top = getTop();
        this.b = bitmap;
        this.c = i;
        this.d = i2;
        this.g = i3;
        int i6 = i3 * 8;
        int i7 = i - i6;
        this.f6563e = i7;
        int i8 = i2 - i6;
        this.f = i8;
        this.l = ((i - i7) / 2) + left;
        this.m = ((i2 - i8) / 2) + top;
        this.x = new RectF(this.l, this.m, r6 + this.f6563e, r8 + this.f);
        this.A.reset();
        this.A.addRoundRect(this.x, this.l + (this.f6563e / 2), this.m + (this.f / 2), Path.Direction.CCW);
        int i9 = this.c;
        int i10 = i9 / 2;
        int i11 = this.g;
        this.k = i10 - i11;
        this.v = i5;
        this.w = i4;
        this.i = i10 + left;
        int i12 = this.d;
        this.j = (i12 / 2) + top;
        this.t = 270;
        this.h = i11;
        int i13 = (i11 - i11) / 2;
        int i14 = (left + i11) - i13;
        this.n = i14;
        int i15 = (top + i11) - i13;
        this.o = i15;
        int i16 = i11 * 2;
        int i17 = i13 * 2;
        int i18 = (i9 - i16) + i17;
        this.r = i18;
        int i19 = (i12 - i16) + i17;
        this.s = i19;
        this.p = i14 + i18;
        this.q = i15 + i19;
        this.y = new RectF(this.n, this.o, this.p, this.q);
    }

    private void a(Canvas canvas) {
        this.z.reset();
        this.z.setStrokeWidth(this.g);
        this.z.setStyle(Paint.Style.STROKE);
        this.z.setAntiAlias(true);
        this.z.setColor(this.w);
        canvas.drawCircle(this.i, this.j, this.k, this.z);
    }
}
