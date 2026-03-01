package supwisdom;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R;

/* JADX INFO: compiled from: AppCompatSeekBarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class s2 extends r2 {
    public final SeekBar d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f9110e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public boolean h;
    public boolean i;

    public s2(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.d = seekBar;
    }

    @Override // supwisdom.r2
    public void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        p3 p3VarA = p3.a(this.d.getContext(), attributeSet, R.styleable.AppCompatSeekBar, i, 0);
        SeekBar seekBar = this.d;
        lb.a(seekBar, seekBar.getContext(), R.styleable.AppCompatSeekBar, attributeSet, p3VarA.a(), i, 0);
        Drawable drawableC = p3VarA.c(R.styleable.AppCompatSeekBar_android_thumb);
        if (drawableC != null) {
            this.d.setThumb(drawableC);
        }
        b(p3VarA.b(R.styleable.AppCompatSeekBar_tickMark));
        if (p3VarA.g(R.styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.g = y2.a(p3VarA.d(R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.g);
            this.i = true;
        }
        if (p3VarA.g(R.styleable.AppCompatSeekBar_tickMarkTint)) {
            this.f = p3VarA.a(R.styleable.AppCompatSeekBar_tickMarkTint);
            this.h = true;
        }
        p3VarA.b();
        c();
    }

    public void b(Drawable drawable) {
        Drawable drawable2 = this.f9110e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f9110e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.d);
            y8.a(drawable, lb.n(this.d));
            if (drawable.isStateful()) {
                drawable.setState(this.d.getDrawableState());
            }
            c();
        }
        this.d.invalidate();
    }

    public final void c() {
        if (this.f9110e != null) {
            if (this.h || this.i) {
                Drawable drawableI = y8.i(this.f9110e.mutate());
                this.f9110e = drawableI;
                if (this.h) {
                    y8.a(drawableI, this.f);
                }
                if (this.i) {
                    y8.a(this.f9110e, this.g);
                }
                if (this.f9110e.isStateful()) {
                    this.f9110e.setState(this.d.getDrawableState());
                }
            }
        }
    }

    public void d() {
        Drawable drawable = this.f9110e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.d.getDrawableState())) {
            this.d.invalidateDrawable(drawable);
        }
    }

    public void e() {
        Drawable drawable = this.f9110e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void a(Canvas canvas) {
        if (this.f9110e != null) {
            int max = this.d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f9110e.getIntrinsicWidth();
                int intrinsicHeight = this.f9110e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f9110e.setBounds(-i, -i2, i, i2);
                float width = ((this.d.getWidth() - this.d.getPaddingLeft()) - this.d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.d.getPaddingLeft(), this.d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f9110e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
