package com.supwisdom.superapp.speech;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.umeng.analytics.pro.db;
import supwisdom.nj1;

/* JADX INFO: loaded from: classes2.dex */
public class SDKAnimationView extends View {
    public static final int[] H = {14336, 31744, 31992, 14844, 230300, 509804, 511852, 236552, 8184, 237336, 511980, 509932, 230156, 14844, 31992, 31744, 14336};
    public static byte[] I = new byte[69];
    public static byte[] J = {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11};
    public static final byte[] K;
    public static final byte[] L;
    public static final byte[] M;
    public static final byte[] N;
    public static final byte[] O;
    public static final byte[] P;
    public static final byte[] Q;
    public static final byte[] R;
    public static final byte[] S;
    public static final byte[] T;
    public static final byte[] U;
    public static final byte[] V;
    public static final byte[] W;
    public static final byte[] a0;
    public static final byte[] b0;
    public static final byte[] c0;
    public static final byte[] d0;
    public static final byte[] e0;
    public static final byte[] f0;
    public static final byte[] g0;
    public static final byte[] h0;
    public static byte[][] i0;
    public static byte[][] j0;
    public static byte[][] k0;
    public Paint A;
    public Bitmap B;
    public Canvas C;
    public ColorFilter D;
    public float E;
    public Runnable F;
    public Runnable G;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[][] f4014a;
    public int b;
    public byte[] c;
    public byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f4015e;
    public int f;
    public int g;
    public long h;
    public long i;
    public long j;
    public long k;
    public int l;
    public int m;
    public Paint n;
    public Paint o;
    public Paint p;
    public Paint q;
    public Paint r;
    public Drawable s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int currentDBLevelMeter = (int) (0 + ((SDKAnimationView.this.getCurrentDBLevelMeter() * 6.0f) / 100.0f));
            if (currentDBLevelMeter > SDKAnimationView.this.t) {
                SDKAnimationView.this.t = currentDBLevelMeter;
            } else {
                SDKAnimationView sDKAnimationView = SDKAnimationView.this;
                sDKAnimationView.t = Math.max(currentDBLevelMeter, sDKAnimationView.t - 1);
            }
            SDKAnimationView sDKAnimationView2 = SDKAnimationView.this;
            sDKAnimationView2.t = Math.min(6, sDKAnimationView2.t);
            if (SDKAnimationView.this.t == 0 && ((int) (Math.random() * 4.0d)) == 0) {
                SDKAnimationView.this.t = 1;
            }
            SDKAnimationView sDKAnimationView3 = SDKAnimationView.this;
            sDKAnimationView3.setVolumeLevel(sDKAnimationView3.t);
            SDKAnimationView sDKAnimationView4 = SDKAnimationView.this;
            sDKAnimationView4.removeCallbacks(sDKAnimationView4.F);
            SDKAnimationView sDKAnimationView5 = SDKAnimationView.this;
            sDKAnimationView5.postDelayed(sDKAnimationView5.F, 50L);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SDKAnimationView.this.invalidate();
            SDKAnimationView.this.post(this);
        }
    }

    static {
        byte[] bArr = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
        K = bArr;
        byte[] bArr2 = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
        L = bArr2;
        byte[] bArr3 = {4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 5, 5, 5, 5, 6, 6, 6, 6, 5, 5, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 8, 8, 9, 9, 9, 9, 9, 8, 8, 8, 7, 6, 6, 5, 5, 5, 4, 4, 4, 5, 5, 6, 6, 6, 6, 5, 5, 5, 5, 6, 6, 6, 5, 5, 5, 4, 4, 4, 4};
        M = bArr3;
        byte[] bArr4 = {7, 8, 8, 7, 7, 7, 8, 8, 9, 9, 9, 8, 8, 7, 6, 6, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 9, 10, 10, 11, 11, 11, 12, 12, 12, 11, 11, 11, 10, 10, 9, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5, 5, 6, 6, 7, 8, 8, 9, 9, 9, 8, 8, 7, 7, 7, 8, 8, 7};
        N = bArr4;
        byte[] bArr5 = {9, 9, 9, 10, 10, 11, 11, 12, 12, 12, 12, 11, 11, 10, 10, 9, 9, 9, 8, 8, 8, 8, 9, 9, 9, 10, 10, 11, 12, 12, 13, 13, db.l, db.l, db.l, db.l, db.l, 13, 13, 12, 12, 11, 10, 10, 9, 9, 9, 8, 8, 8, 8, 9, 9, 9, 10, 10, 11, 11, 12, 12, 12, 12, 11, 11, 10, 10, 9, 9, 9};
        O = bArr5;
        byte[] bArr6 = {11, 11, 11, 12, 12, 13, 13, db.l, db.l, db.l, 15, 15, 15, db.l, db.l, db.l, 13, 13, 13, 12, 12, 12, 12, 12, 13, 13, 13, db.l, db.l, 15, 15, 15, 16, 16, 16, 16, 16, 15, 15, 15, db.l, db.l, 13, 13, 13, 12, 12, 12, 12, 12, 13, 13, 13, db.l, db.l, db.l, 15, 15, 15, db.l, db.l, db.l, 13, 13, 12, 12, 11, 11, 11};
        P = bArr6;
        byte[] bArr7 = {13, 13, db.l, db.l, 15, 15, 16, 16, 16, 17, 17, 17, 16, 16, 16, 15, 15, 15, db.l, db.l, db.l, db.l, 15, 15, 15, 16, 16, 17, 17, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 19, 19, 19, 19, 19, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 17, 17, 16, 16, 15, 15, 15, db.l, db.l, db.l, db.l, 15, 15, 15, 16, 16, 16, 17, 17, 17, 16, 16, 16, 15, 15, db.l, db.l, 13, 13};
        Q = bArr7;
        byte[] bArr8 = {3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 3, 3, 3, 3};
        R = bArr8;
        byte[] bArr9 = {3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3};
        S = bArr9;
        byte[] bArr10 = {5, 5, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 5, 5, 6, 6, 6, 5, 5, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3};
        T = bArr10;
        byte[] bArr11 = {5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 9, 10, 10, 11, 11, 11, 12, 12, 12, 11, 11, 11, 10, 10, 9, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 9, 10, 10, 11, 11, 11, 12, 12, 12, 11, 11, 11, 10, 10, 9, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5};
        U = bArr11;
        byte[] bArr12 = {9, 9, 8, 8, 8, 8, 9, 9, 9, 10, 10, 11, 12, 12, 13, 13, db.l, db.l, db.l, db.l, db.l, 13, 13, 12, 12, 11, 11, 10, 10, 9, 9, 9, 8, 8, 8, 8, 8, 9, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, db.l, db.l, db.l, db.l, db.l, 13, 13, 12, 12, 11, 10, 10, 9, 9, 9, 8, 8, 8, 8, 9, 9};
        V = bArr12;
        byte[] bArr13 = {13, 13, 13, 13, db.l, db.l, db.l, 13, 13, 13, 13, 13, db.l, db.l, 15, 15, 15, 16, 16, 16, 16, 16, 15, 15, 15, db.l, db.l, db.l, 13, 13, 13, 13, 12, 12, 12, 12, 12, 13, 13, 13, 13, db.l, db.l, db.l, 15, 15, 15, 16, 16, 16, 16, 16, 15, 15, 15, db.l, db.l, 13, 13, 13, 13, 13, db.l, db.l, db.l, 13, 13, 13, 13};
        W = bArr13;
        byte[] bArr14 = {15, 15, db.l, db.l, db.l, db.l, 15, 15, 15, 16, 16, 17, 17, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 19, 19, 19, 19, 19, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 17, 17, 16, 16, 15, 15, 15, db.l, db.l, db.l, db.l, db.l, db.l, db.l, 15, 15, 15, 16, 16, 17, 17, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 19, 19, 19, 19, 19, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 17, 17, 16, 16, 15, 15, 15, db.l, db.l, db.l, db.l, 15, 15};
        a0 = bArr14;
        byte[] bArr15 = {3, 3, 3, 4, 4, 4, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 4, 4, 4, 3, 3, 3};
        b0 = bArr15;
        byte[] bArr16 = {3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3};
        c0 = bArr16;
        byte[] bArr17 = {5, 5, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 7, 7, 7, 8, 8, 9, 9, 9, 9, 9, 8, 8, 7, 7, 7, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 8, 8, 8, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 5, 5};
        d0 = bArr17;
        byte[] bArr18 = {6, 6, 6, 7, 7, 8, 9, 9, 10, 10, 10, 11, 11, 11, 10, 10, 10, 9, 9, 8, 8, 7, 7, 7, 7, 8, 8, 9, 10, 10, 11, 11, 11, 12, 12, 12, 11, 11, 11, 10, 10, 9, 8, 8, 7, 7, 7, 7, 8, 8, 9, 9, 10, 10, 10, 11, 11, 11, 10, 10, 10, 9, 9, 8, 7, 7, 6, 6, 6};
        e0 = bArr18;
        byte[] bArr19 = {8, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 13, 13, 12, 12, 11, 11, 10, 10, 9, 9, 9, 9, 10, 10, 11, 12, 12, 13, 13, db.l, db.l, db.l, db.l, db.l, 13, 13, 12, 12, 11, 10, 10, 9, 9, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 13, 13, 12, 12, 11, 11, 10, 10, 9, 9, 8, 8, 8};
        f0 = bArr19;
        byte[] bArr20 = {11, 11, 11, 11, 11, 12, 12, 12, 13, 13, 13, db.l, db.l, db.l, 15, 15, 15, 15, 15, db.l, db.l, db.l, 13, 13, 13, 13, db.l, db.l, db.l, 15, 15, 15, 16, 16, 16, 16, 16, 15, 15, 15, db.l, db.l, db.l, 13, 13, 13, 13, db.l, db.l, db.l, 15, 15, 15, 15, 15, db.l, db.l, db.l, 13, 13, 13, 12, 12, 12, 11, 11, 11, 11, 11};
        g0 = bArr20;
        byte[] bArr21 = {db.l, db.l, db.l, 15, 15, 16, 16, 17, 17, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 17, 17, 16, 16, 15, 15, db.l, db.l, db.l, 15, 15, 15, 16, 16, 17, 17, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 19, 19, 19, 19, 19, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 17, 17, 16, 16, 15, 15, 15, db.l, db.l, db.l, 15, 15, 16, 16, 17, 17, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, SharedPreferencesNewImpl.FINISH_MARK, 17, 17, 16, 16, 15, 15, db.l, db.l, db.l};
        h0 = bArr21;
        i0 = new byte[][]{bArr, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7};
        j0 = new byte[][]{bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, bArr14};
        k0 = new byte[][]{bArr15, bArr16, bArr17, bArr18, bArr19, bArr20, bArr21};
    }

    public SDKAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.c = new byte[69];
        this.d = new byte[69];
        this.f4015e = 0.0d;
        this.f = 0;
        this.g = -1;
        this.F = new a();
        this.G = new b();
        Paint paint = new Paint();
        this.n = paint;
        paint.setStrokeWidth(1.0f);
        this.o = new Paint();
        this.r = new Paint();
        this.p = new Paint();
        this.q = new Paint();
        byte[][] bArr = i0;
        this.f4014a = bArr;
        this.c = bArr[0];
        this.d = bArr[0];
        setThemeStyle(16777217);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getCurrentDBLevelMeter() {
        return this.E;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVolumeLevel(int i) {
        byte[][] bArr = this.f4014a;
        if (bArr == null || i < 0 || i >= bArr.length) {
            return;
        }
        this.c = this.d;
        this.i = System.currentTimeMillis();
        int iRandom = (int) (Math.random() * 2.0d);
        if (iRandom == 0) {
            this.f4014a = i0;
        } else if (iRandom == 1) {
            this.f4014a = j0;
        } else if (iRandom != 2) {
            this.f4014a = i0;
        } else {
            this.f4014a = k0;
        }
        this.d = this.f4014a[i];
    }

    public void d() {
        this.g = 3;
        this.k = System.currentTimeMillis();
        this.l = 0;
        this.m = 0;
        removeCallbacks(this.G);
        removeCallbacks(this.F);
        post(this.G);
    }

    public void e() {
        this.g = 2;
        removeCallbacks(this.G);
        removeCallbacks(this.F);
        post(this.G);
        post(this.F);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        e(this.C);
        canvas.drawBitmap(this.B, 0.0f, 0.0f, this.A);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        this.f = size;
        double d = ((double) size) / 69.0d;
        this.f4015e = d;
        setMeasuredDimension(size, (int) (d * 21.0d));
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.B = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.C = new Canvas(this.B);
        Paint paint = new Paint();
        this.A = paint;
        paint.setColorFilter(this.D);
    }

    public void setCurrentDBLevelMeter(float f) {
        this.E = f;
    }

    public void setHsvFilter(ColorFilter colorFilter) {
        this.D = colorFilter;
    }

    public void setThemeStyle(int i) {
        boolean zA = nj1.a(i);
        this.b = zA ? -14869219 : -592138;
        this.n.setColor(zA ? -14869219 : -1);
        this.o.setColor(zA ? -13684945 : -1250068);
        this.r.setColor(zA ? -16499830 : -2428673);
        this.u = zA ? -12221715 : -8015623;
        this.v = zA ? 4555501 : -6628366;
        this.w = zA ? -16433782 : -3216385;
        this.x = zA ? 343434 : -1;
        this.y = zA ? -16433782 : -3282177;
        this.z = zA ? 343434 : 13495039;
        this.s = getResources().getDrawable(zA ? getContext().getResources().getIdentifier("bdspeech_mask_deep", "drawable", getContext().getPackageName()) : getContext().getResources().getIdentifier("bdspeech_mask_light", "drawable", getContext().getPackageName()));
    }

    public void c() {
        this.g = 1;
        this.h = System.currentTimeMillis();
        removeCallbacks(this.G);
        removeCallbacks(this.F);
        post(this.G);
    }

    public void a() {
        removeCallbacks(this.G);
        removeCallbacks(this.F);
        this.g = 0;
    }

    public void b() {
        this.g = 4;
        this.h = System.currentTimeMillis();
        removeCallbacks(this.G);
        removeCallbacks(this.F);
        post(this.G);
    }

    public final void a(Canvas canvas, int i) {
        this.o.setAlpha(i);
        this.r.setAlpha(i);
        for (int i2 = 27; i2 < H.length + 27; i2++) {
            for (int i3 = 0; i3 < 21; i3++) {
                if (((H[i2 - 27] >> i3) & 1) == 1) {
                    int i4 = (int) (this.j - this.i);
                    if (i4 > 50) {
                        i4 = 50;
                    }
                    byte[] bArr = this.c;
                    int i5 = i2 - 1;
                    int i6 = (int) (((double) bArr[i5]) + ((((double) (this.d[i5] - bArr[i5])) * ((double) i4)) / 50.0d));
                    if (i3 >= i6) {
                        double d = this.f4015e;
                        int i7 = 21 - i3;
                        canvas.drawRect((int) (((double) i5) * d), (int) (((double) (i7 - 1)) * d), (int) (((double) i2) * d), (int) (d * ((double) i7)), this.o);
                    } else if (i3 < i6 - 1) {
                        double d2 = this.f4015e;
                        int i8 = 21 - i3;
                        canvas.drawRect((int) (((double) i5) * d2), (int) (((double) (i8 - 1)) * d2), (int) (((double) i2) * d2), (int) (d2 * ((double) i8)), this.r);
                    }
                }
            }
        }
    }

    public void e(Canvas canvas) {
        double d;
        double d2;
        int iCurrentTimeMillis;
        double d3;
        double d4;
        int iCurrentTimeMillis2;
        canvas.drawColor(this.b);
        int i = this.g;
        if (i == 1) {
            byte[] bArr = J;
            this.c = bArr;
            this.d = bArr;
            long jCurrentTimeMillis = System.currentTimeMillis() - this.h;
            if (jCurrentTimeMillis < 1200) {
                iCurrentTimeMillis = (int) ((((double) ((int) ((System.currentTimeMillis() - this.h) % 1200))) / 1200.0d) * 255.0d);
            } else {
                int i2 = (int) (jCurrentTimeMillis % 1200);
                if (i2 < 600) {
                    d2 = (((double) i2) / 600.0d) * 0.800000011920929d;
                    d = 1.0d;
                } else {
                    d = 1.0d;
                    d2 = (((double) (1200 - i2)) / 600.0d) * 0.800000011920929d;
                }
                iCurrentTimeMillis = (int) ((d - d2) * 255.0d);
            }
            b(canvas, iCurrentTimeMillis);
            a(canvas, iCurrentTimeMillis);
        } else if (i == 2) {
            this.j = System.currentTimeMillis();
            b(canvas, 255);
            a(canvas, 255);
        } else if (i == 3) {
            if (System.currentTimeMillis() - this.k > 20) {
                this.k = System.currentTimeMillis();
                if (this.m == 0) {
                    int i3 = this.l + 1;
                    this.l = i3;
                    if (i3 >= 26) {
                        this.m = 1;
                    }
                } else {
                    int i4 = this.l - 1;
                    this.l = i4;
                    if (i4 <= -5) {
                        this.m = 0;
                    }
                }
            }
            d(canvas);
            c(canvas);
        } else if (i == 4) {
            byte[] bArr2 = I;
            this.c = bArr2;
            this.d = bArr2;
            long jCurrentTimeMillis2 = System.currentTimeMillis() - this.h;
            if (jCurrentTimeMillis2 < 1200) {
                iCurrentTimeMillis2 = (int) ((((double) ((int) ((System.currentTimeMillis() - this.h) % 1200))) / 1200.0d) * 255.0d);
            } else {
                int i5 = (int) (jCurrentTimeMillis2 % 1200);
                if (i5 < 600) {
                    d4 = (((double) i5) / 600.0d) * 0.800000011920929d;
                    d3 = 1.0d;
                } else {
                    d3 = 1.0d;
                    d4 = (((double) (1200 - i5)) / 600.0d) * 0.800000011920929d;
                }
                iCurrentTimeMillis2 = (int) ((d3 - d4) * 255.0d);
            }
            a(canvas, iCurrentTimeMillis2);
        }
        a(canvas);
        b(canvas);
    }

    public final void c(Canvas canvas) {
        this.o.setAlpha(255);
        this.r.setAlpha(255);
        for (int i = 27; i < H.length + 27; i++) {
            for (int i2 = 0; i2 < 21; i2++) {
                if (((H[i - 27] >> i2) & 1) == 1) {
                    int i3 = this.l;
                    if (i2 >= i3) {
                        double d = this.f4015e;
                        int i4 = 21 - i2;
                        canvas.drawRect((int) (((double) (i - 1)) * d), (int) (((double) (i4 - 1)) * d), (int) (((double) i) * d), (int) (d * ((double) i4)), this.o);
                    } else if (i2 < i3 - 1) {
                        double d2 = this.f4015e;
                        int i5 = 21 - i2;
                        canvas.drawRect((int) (((double) (i - 1)) * d2), (int) (((double) (i5 - 1)) * d2), (int) (((double) i) * d2), (int) (d2 * ((double) i5)), this.r);
                    }
                }
            }
        }
    }

    public final void b(Canvas canvas) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.setBounds(0, 0, this.f, getHeight());
            this.s.draw(canvas);
        }
    }

    public final void d(Canvas canvas) {
        if (this.m == 0) {
            this.q.setShader(new LinearGradient(0.0f, 1.0f, 0.0f, (int) (this.f4015e * 5.0d), this.y, this.z, Shader.TileMode.MIRROR));
            canvas.save();
            canvas.translate(0.0f, (int) (this.f4015e * ((double) (21 - (this.l - 1)))));
            canvas.drawRect(0.0f, 0.0f, this.f, (int) (this.f4015e * 5.0d), this.q);
            canvas.restore();
        } else {
            this.q.setShader(new LinearGradient(0.0f, 1.0f, 0.0f, (int) (this.f4015e * 5.0d), this.z, this.y, Shader.TileMode.MIRROR));
            canvas.save();
            canvas.translate(0.0f, (int) (this.f4015e * ((double) (21 - (this.l + 5)))));
            canvas.drawRect(0.0f, 0.0f, this.f, (int) (this.f4015e * 5.0d), this.q);
            canvas.restore();
        }
        for (int i = 0; i < 69; i++) {
            int i2 = this.u;
            double d = i - 34.5d;
            int iAbs = (int) (((double) ((i2 >> 24) & 255)) + (((double) (((this.v >> 24) & 255) - ((i2 >> 24) & 255))) * (Math.abs(d) / 34.5d)));
            int i3 = this.u;
            int iAbs2 = (int) (((double) ((i3 >> 16) & 255)) + (((double) (((this.v >> 16) & 255) - ((i3 >> 16) & 255))) * (Math.abs(d) / 34.5d)));
            int i4 = this.u;
            int iAbs3 = (int) (((double) ((i4 >> 8) & 255)) + (((double) (((this.v >> 8) & 255) - ((i4 >> 8) & 255))) * (Math.abs(d) / 34.5d)));
            int i5 = this.u;
            this.p.setColor((iAbs << 24) | ((iAbs2 & 255) << 16) | ((iAbs3 & 255) << 8) | (((int) (((double) (i5 & 255)) + (((double) ((this.v & 255) - (i5 & 255))) * (Math.abs(d) / 34.5d)))) & 255));
            double d2 = this.f4015e;
            int i6 = this.l;
            canvas.drawRect((int) (r8 * d2), (int) (((double) (21 - i6)) * d2), (int) (((double) r8) * d2), (int) (d2 * ((double) ((21 - i6) + 1))), this.p);
        }
    }

    public final void b(Canvas canvas, int i) {
        this.q.setShader(new LinearGradient(0.0f, 1.0f, 0.0f, (getHeight() * 2) / 3, this.w, this.x, Shader.TileMode.CLAMP));
        this.q.setAlpha(i);
        int i2 = 0;
        while (i2 < 69) {
            int i3 = (int) (this.j - this.i);
            if (i3 > 50) {
                i3 = 50;
            }
            byte[] bArr = this.c;
            int i4 = (int) (((double) bArr[i2]) + ((((double) (this.d[i2] - bArr[i2])) * ((double) i3)) / 50.0d));
            canvas.save();
            double d = this.f4015e;
            double d2 = 21 - i4;
            canvas.translate((int) (d * r6), (int) (d * d2));
            canvas.drawRect(0.0f, 0.0f, (int) this.f4015e, getHeight() - ((int) (this.f4015e * d2)), this.q);
            canvas.restore();
            int i5 = this.u;
            double d3 = i2 - 34.5d;
            int iAbs = (int) (((double) ((i5 >> 24) & 255)) + (((double) (((this.v >> 24) & 255) - ((i5 >> 24) & 255))) * (Math.abs(d3) / 34.5d)));
            int i6 = this.u;
            int iAbs2 = (int) (((double) ((i6 >> 16) & 255)) + (((double) (((this.v >> 16) & 255) - ((i6 >> 16) & 255))) * (Math.abs(d3) / 34.5d)));
            int i7 = this.u;
            double d4 = (i7 >> 8) & 255;
            int i8 = ((this.v >> 8) & 255) - ((i7 >> 8) & 255);
            int i9 = i2;
            int iAbs3 = (int) (d4 + (((double) i8) * (Math.abs(d3) / 34.5d)));
            int i10 = this.u;
            int i11 = (iAbs3 & 255) << 8;
            this.p.setColor(i11 | ((iAbs2 & 255) << 16) | (iAbs << 24) | (((int) (((double) (i10 & 255)) + (((double) ((this.v & 255) - (i10 & 255))) * (Math.abs(d3) / 34.5d)))) & 255));
            this.p.setAlpha((int) ((((double) i) * ((double) iAbs)) / 255.0d));
            double d5 = this.f4015e;
            canvas.drawRect((int) (r6 * d5), (int) (d5 * d2), (int) (((double) r4) * d5), (int) (d5 * ((double) (r3 + 1))), this.p);
            i2 = i9 + 1;
        }
    }

    public final void a(Canvas canvas) {
        for (int i = 0; i <= 21; i++) {
            double d = this.f4015e;
            double d2 = i;
            canvas.drawLine(0.0f, (int) (d * d2), this.f, (int) (d * d2), this.n);
        }
        for (int i2 = 0; i2 <= 69; i2++) {
            double d3 = this.f4015e;
            double d4 = i2;
            canvas.drawLine((int) (d3 * d4), 0.0f, (int) (d3 * d4), getHeight(), this.n);
        }
    }
}
