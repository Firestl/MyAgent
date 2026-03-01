package supwisdom;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import supwisdom.yl0;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class xl0 {
    public static final int j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f9786a;
    public final View b;
    public final Path c;
    public final Paint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f9787e;
    public yl0.e f;
    public Drawable g;
    public boolean h;
    public boolean i;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void a(Canvas canvas);

        boolean c();
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            j = 2;
        } else if (i >= 18) {
            j = 1;
        } else {
            j = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public xl0(a aVar) {
        this.f9786a = aVar;
        View view = (View) aVar;
        this.b = view;
        view.setWillNotDraw(false);
        this.c = new Path();
        this.d = new Paint(7);
        Paint paint = new Paint(1);
        this.f9787e = paint;
        paint.setColor(0);
    }

    public void a() {
        if (j == 0) {
            this.h = true;
            this.i = false;
            this.b.buildDrawingCache();
            Bitmap drawingCache = this.b.getDrawingCache();
            if (drawingCache == null && this.b.getWidth() != 0 && this.b.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.b.getWidth(), this.b.getHeight(), Bitmap.Config.ARGB_8888);
                this.b.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.d;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.h = false;
            this.i = true;
        }
    }

    public void b() {
        if (j == 0) {
            this.i = false;
            this.b.destroyDrawingCache();
            this.d.setShader(null);
            this.b.invalidate();
        }
    }

    public Drawable c() {
        return this.g;
    }

    public int d() {
        return this.f9787e.getColor();
    }

    public yl0.e e() {
        yl0.e eVar = this.f;
        if (eVar == null) {
            return null;
        }
        yl0.e eVar2 = new yl0.e(eVar);
        if (eVar2.a()) {
            eVar2.c = a(eVar2);
        }
        return eVar2;
    }

    public final void f() {
        if (j == 1) {
            this.c.rewind();
            yl0.e eVar = this.f;
            if (eVar != null) {
                this.c.addCircle(eVar.f9898a, eVar.b, eVar.c, Path.Direction.CW);
            }
        }
        this.b.invalidate();
    }

    public boolean g() {
        return this.f9786a.c() && !h();
    }

    public final boolean h() {
        yl0.e eVar = this.f;
        boolean z = eVar == null || eVar.a();
        return j == 0 ? !z && this.i : !z;
    }

    public final boolean i() {
        return (this.h || this.g == null || this.f == null) ? false : true;
    }

    public final boolean j() {
        return (this.h || Color.alpha(this.f9787e.getColor()) == 0) ? false : true;
    }

    public void b(yl0.e eVar) {
        if (eVar == null) {
            this.f = null;
        } else {
            yl0.e eVar2 = this.f;
            if (eVar2 == null) {
                this.f = new yl0.e(eVar);
            } else {
                eVar2.a(eVar);
            }
            if (rm0.a(eVar.c, a(eVar), 1.0E-4f)) {
                this.f.c = Float.MAX_VALUE;
            }
        }
        f();
    }

    public void a(int i) {
        this.f9787e.setColor(i);
        this.b.invalidate();
    }

    public void a(Drawable drawable) {
        this.g = drawable;
        this.b.invalidate();
    }

    public final void b(Canvas canvas) {
        if (i()) {
            Rect bounds = this.g.getBounds();
            float fWidth = this.f.f9898a - (bounds.width() / 2.0f);
            float fHeight = this.f.b - (bounds.height() / 2.0f);
            canvas.translate(fWidth, fHeight);
            this.g.draw(canvas);
            canvas.translate(-fWidth, -fHeight);
        }
    }

    public final float a(yl0.e eVar) {
        return rm0.a(eVar.f9898a, eVar.b, 0.0f, 0.0f, this.b.getWidth(), this.b.getHeight());
    }

    public void a(Canvas canvas) {
        if (h()) {
            int i = j;
            if (i == 0) {
                yl0.e eVar = this.f;
                canvas.drawCircle(eVar.f9898a, eVar.b, eVar.c, this.d);
                if (j()) {
                    yl0.e eVar2 = this.f;
                    canvas.drawCircle(eVar2.f9898a, eVar2.b, eVar2.c, this.f9787e);
                }
            } else if (i == 1) {
                int iSave = canvas.save();
                canvas.clipPath(this.c);
                this.f9786a.a(canvas);
                if (j()) {
                    canvas.drawRect(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight(), this.f9787e);
                }
                canvas.restoreToCount(iSave);
            } else if (i == 2) {
                this.f9786a.a(canvas);
                if (j()) {
                    canvas.drawRect(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight(), this.f9787e);
                }
            } else {
                throw new IllegalStateException("Unsupported strategy " + j);
            }
        } else {
            this.f9786a.a(canvas);
            if (j()) {
                canvas.drawRect(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight(), this.f9787e);
            }
        }
        b(canvas);
    }
}
