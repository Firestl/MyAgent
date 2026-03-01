package io.dcloud.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import io.dcloud.PdrR;
import io.dcloud.common.DHInterface.IReflectAble;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes2.dex */
public class GifImageView extends View implements IReflectAble {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f6436a;
    public Movie b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f6437e;
    public float f;
    public int g;
    public volatile boolean h;
    public volatile boolean i;
    public volatile boolean j;
    public boolean k;
    public a l;
    public int m;
    public int n;
    public int o;
    public float p;
    public float q;

    public interface a {
        void a();

        void a(float f);

        void a(boolean z);

        void b();

        void c();
    }

    public GifImageView(Context context) {
        this(context, null);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, PdrR.STYLE_GIFVIEW, i, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(PdrR.STYLE_GIFVIEW_gifSrc, 0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(PdrR.STYLE_GIFVIEW_authPlay, true);
        this.g = typedArrayObtainStyledAttributes.getInt(PdrR.STYLE_GIFVIEW_playCount, -1);
        if (resourceId > 0) {
            setGifResource(resourceId, (a) null);
            if (z) {
                play(this.g);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        setLayerType(1, null);
    }

    private void b() {
        if (this.k) {
            postInvalidateOnAnimation();
        }
    }

    private void c() {
        this.h = false;
        this.c = SystemClock.uptimeMillis();
        this.i = false;
        this.j = true;
        this.d = 0L;
        this.f6437e = 0L;
    }

    private int getCurrentFrameTime() {
        if (this.m == 0) {
            return 0;
        }
        long jUptimeMillis = SystemClock.uptimeMillis() - this.f6437e;
        int i = (int) ((jUptimeMillis - this.c) / ((long) this.m));
        int i2 = this.g;
        if (i2 != -1 && i >= i2) {
            this.j = false;
            a aVar = this.l;
            if (aVar != null) {
                aVar.c();
            }
        }
        long j = jUptimeMillis - this.c;
        int i3 = this.m;
        float f = j % ((long) i3);
        this.f = f / i3;
        if (this.l != null && this.j) {
            double dDoubleValue = new BigDecimal(this.f).setScale(2, 4).doubleValue();
            if (dDoubleValue == 0.99d) {
                dDoubleValue = 1.0d;
            }
            this.l.a((float) dDoubleValue);
        }
        return (int) f;
    }

    public int getDuration() {
        Movie movie = this.b;
        if (movie != null) {
            return movie.duration();
        }
        return 0;
    }

    public boolean isPaused() {
        return this.i;
    }

    public boolean isPlaying() {
        return !this.i && this.j;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b != null) {
            if (this.i || !this.j) {
                a(canvas);
                return;
            }
            if (this.h) {
                this.b.setTime(this.m - getCurrentFrameTime());
            } else {
                this.b.setTime(getCurrentFrameTime());
            }
            a(canvas);
            b();
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.p = (getWidth() - this.n) / 2.0f;
        this.q = (getHeight() - this.o) / 2.0f;
        this.k = getVisibility() == 0;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        Movie movie = this.b;
        if (movie == null) {
            setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
            return;
        }
        int iWidth = movie.width();
        int iHeight = this.b.height();
        int size = View.MeasureSpec.getSize(i);
        float f = 1.0f / (iWidth / size);
        this.f6436a = f;
        this.n = size;
        int i3 = (int) (iHeight * f);
        this.o = i3;
        setMeasuredDimension(size, i3);
    }

    @Override // android.view.View
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        this.k = i == 1;
        b();
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.k = i == 0;
        b();
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.k = i == 0;
        b();
    }

    public void pause() {
        if (this.b == null || this.i || !this.j) {
            a aVar = this.l;
            if (aVar != null) {
                aVar.a(false);
                return;
            }
            return;
        }
        this.i = true;
        invalidate();
        this.d = SystemClock.uptimeMillis();
        a aVar2 = this.l;
        if (aVar2 != null) {
            aVar2.a(true);
        }
    }

    public void play(int i) {
        this.g = i;
        c();
        a aVar = this.l;
        if (aVar != null) {
            aVar.a();
        }
        invalidate();
    }

    public void playOver() {
        if (this.b != null) {
            play(-1);
        }
    }

    public void playReverse() {
        if (this.b != null) {
            c();
            this.h = true;
            a aVar = this.l;
            if (aVar != null) {
                aVar.a();
            }
            invalidate();
        }
    }

    public void setGifResource(int i, a aVar) {
        if (aVar != null) {
            this.l = aVar;
        }
        c();
        Movie movieDecodeStream = Movie.decodeStream(getResources().openRawResource(i));
        this.b = movieDecodeStream;
        this.m = movieDecodeStream.duration() == 0 ? 1000 : this.b.duration();
        requestLayout();
    }

    public void setOnPlayListener(a aVar) {
        this.l = aVar;
    }

    public void setPercent(float f) {
        int i;
        Movie movie = this.b;
        if (movie == null || (i = this.m) <= 0) {
            return;
        }
        this.f = f;
        movie.setTime((int) (i * f));
        b();
        a aVar = this.l;
        if (aVar != null) {
            aVar.a(f);
        }
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public GifImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6436a = 1.0f;
        this.g = -1;
        this.h = false;
        this.k = true;
        a(context, attributeSet, i);
    }

    public void play() {
        if (this.b == null) {
            return;
        }
        if (this.j) {
            if (!this.i || this.d <= 0) {
                return;
            }
            this.i = false;
            this.f6437e = (this.f6437e + SystemClock.uptimeMillis()) - this.d;
            invalidate();
            a aVar = this.l;
            if (aVar != null) {
                aVar.b();
                return;
            }
            return;
        }
        play(this.g);
    }

    public void setGifResource(int i) {
        setGifResource(i, (a) null);
    }

    public void setGifResource(String str, a aVar) {
        this.b = Movie.decodeFile(str);
        this.l = aVar;
        c();
        this.m = this.b.duration() == 0 ? 1000 : this.b.duration();
        requestLayout();
        a aVar2 = this.l;
        if (aVar2 != null) {
            aVar2.a();
        }
    }

    private void a(Canvas canvas) {
        canvas.save();
        float f = this.f6436a;
        canvas.scale(f, f);
        Movie movie = this.b;
        float f2 = this.p;
        float f3 = this.f6436a;
        movie.draw(canvas, f2 / f3, this.q / f3);
        canvas.restore();
    }
}
