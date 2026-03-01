package supwisdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: CutoutDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class in0 extends GradientDrawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f7967a = new Paint(1);
    public final RectF b;
    public int c;

    public in0() {
        c();
        this.b = new RectF();
    }

    public boolean a() {
        return !this.b.isEmpty();
    }

    public void b() {
        a(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public final void c() {
        this.f7967a.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f7967a.setColor(-1);
        this.f7967a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b(canvas);
        super.draw(canvas);
        canvas.drawRect(this.b, this.f7967a);
        a(canvas);
    }

    public void a(float f, float f2, float f3, float f4) {
        RectF rectF = this.b;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        this.b.set(f, f2, f3, f4);
        invalidateSelf();
    }

    public final void b(Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (a(callback)) {
            ((View) callback).setLayerType(2, null);
        } else {
            c(canvas);
        }
    }

    public final void c(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.c = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null);
        } else {
            this.c = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
        }
    }

    public void a(RectF rectF) {
        a(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public final void a(Canvas canvas) {
        if (a(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.c);
    }

    public final boolean a(Drawable.Callback callback) {
        return callback instanceof View;
    }
}
