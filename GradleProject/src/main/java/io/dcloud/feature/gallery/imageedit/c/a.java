package io.dcloud.feature.gallery.imageedit.c;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import io.dcloud.feature.gallery.imageedit.c.g.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static final Bitmap E = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
    public Paint A;
    public Paint B;
    public Matrix C;
    public List<c> D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Bitmap f6528a;
    public Bitmap b;
    public b c;
    public a.EnumC0172a m;
    public io.dcloud.feature.gallery.imageedit.c.b q;
    public boolean r;
    public RectF s;
    public boolean t;
    public boolean u;
    public io.dcloud.feature.gallery.imageedit.c.j.a v;
    public List<io.dcloud.feature.gallery.imageedit.c.j.a> w;
    public List<io.dcloud.feature.gallery.imageedit.c.c> x;
    public List<io.dcloud.feature.gallery.imageedit.c.c> y;
    public Paint z;
    public RectF d = new RectF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RectF f6529e = new RectF();
    public RectF f = new RectF();
    public RectF g = new RectF();
    public float h = 0.0f;
    public float i = 0.0f;
    public float j = 0.0f;
    public boolean k = false;
    public boolean l = false;
    public boolean n = true;
    public Path o = new Path();
    public io.dcloud.feature.gallery.imageedit.c.g.b p = new io.dcloud.feature.gallery.imageedit.c.g.b();

    /* JADX INFO: renamed from: io.dcloud.feature.gallery.imageedit.c.a$a, reason: collision with other inner class name */
    public static /* synthetic */ class C0171a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6530a;

        static {
            int[] iArr = new int[io.dcloud.feature.gallery.imageedit.c.b.values().length];
            f6530a = iArr;
            try {
                iArr[io.dcloud.feature.gallery.imageedit.c.b.DOODLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6530a[io.dcloud.feature.gallery.imageedit.c.b.MOSAIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public interface b {
        void a();

        void b();
    }

    public interface c {
        void a();
    }

    public a() {
        io.dcloud.feature.gallery.imageedit.c.b bVar = io.dcloud.feature.gallery.imageedit.c.b.NONE;
        this.q = bVar;
        io.dcloud.feature.gallery.imageedit.c.b bVar2 = io.dcloud.feature.gallery.imageedit.c.b.CLIP;
        this.r = bVar == bVar2;
        this.s = new RectF();
        this.t = false;
        this.u = false;
        this.w = new ArrayList();
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.C = new Matrix();
        this.o.setFillType(Path.FillType.WINDING);
        Paint paint = new Paint(1);
        this.z = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.z.setStrokeWidth(14.0f);
        this.z.setColor(-65536);
        this.z.setPathEffect(new CornerPathEffect(14.0f));
        this.z.setStrokeCap(Paint.Cap.ROUND);
        this.z.setStrokeJoin(Paint.Join.ROUND);
        this.D = new ArrayList();
        this.f6528a = E;
        if (this.q == bVar2) {
            g();
        }
    }

    private void b(float f) {
        this.C.setRotate(f, this.f6529e.centerX(), this.f6529e.centerY());
        for (io.dcloud.feature.gallery.imageedit.c.j.a aVar : this.w) {
            this.C.mapRect(aVar.getFrame());
            aVar.setRotation(aVar.getRotation() + f);
            aVar.setX(aVar.getFrame().centerX() - aVar.getPivotX());
            aVar.setY(aVar.getFrame().centerY() - aVar.getPivotY());
        }
    }

    private void g() {
        if (this.B == null) {
            Paint paint = new Paint(1);
            this.B = paint;
            paint.setColor(-872415232);
            this.B.setStyle(Paint.Style.FILL);
        }
    }

    private void l() {
        Bitmap bitmap;
        if (this.b == null && (bitmap = this.f6528a) != null && this.q == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC) {
            int iRound = Math.round(bitmap.getWidth() / 64.0f);
            int iRound2 = Math.round(this.f6528a.getHeight() / 64.0f);
            int iMax = Math.max(iRound, 8);
            int iMax2 = Math.max(iRound2, 8);
            if (this.A == null) {
                Paint paint = new Paint(1);
                this.A = paint;
                paint.setFilterBitmap(false);
                this.A.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            }
            this.b = Bitmap.createScaledBitmap(this.f6528a, iMax, iMax2, false);
        }
    }

    private void n() {
        this.t = false;
        h(this.s.width(), this.s.height());
        if (this.q == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            this.p.a(this.f6529e, f());
        }
    }

    private void o() {
        if (this.q == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            this.p.a(this.f6529e, f());
        }
    }

    private void v() {
        if (this.f6529e.isEmpty()) {
            return;
        }
        float fMin = Math.min(this.s.width() / this.f6529e.width(), this.s.height() / this.f6529e.height());
        this.C.setScale(fMin, fMin, this.f6529e.centerX(), this.f6529e.centerY());
        this.C.postTranslate(this.s.centerX() - this.f6529e.centerX(), this.s.centerY() - this.f6529e.centerY());
        this.C.mapRect(this.d);
        this.C.mapRect(this.f6529e);
    }

    public void a(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f6528a = bitmap;
        Bitmap bitmap2 = this.b;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.b = null;
        l();
        n();
    }

    public io.dcloud.feature.gallery.imageedit.c.b c() {
        return this.q;
    }

    public void d(io.dcloud.feature.gallery.imageedit.c.j.a aVar) {
        b(aVar);
    }

    public void e(io.dcloud.feature.gallery.imageedit.c.j.a aVar) {
        if (this.v == aVar) {
            this.v = null;
        } else {
            this.w.remove(aVar);
        }
    }

    public void f(io.dcloud.feature.gallery.imageedit.c.j.a aVar) {
        if (this.v != aVar) {
            c(aVar);
        }
    }

    public void finalize() throws Throwable {
        super.finalize();
        Bitmap bitmap = E;
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    public boolean h() {
        return this.x.isEmpty();
    }

    public boolean i() {
        return this.r;
    }

    public boolean j() {
        return this.u;
    }

    public boolean k() {
        return this.y.isEmpty();
    }

    public boolean m() {
        return this.p.b();
    }

    public void p() {
    }

    public void q() {
    }

    public void r() {
        Bitmap bitmap = this.f6528a;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f6528a.recycle();
    }

    public void s() {
        e(d() - (d() % 360.0f));
        this.f6529e.set(this.d);
        this.p.a(this.f6529e, f());
    }

    public void t() {
        b(this.v);
    }

    public void u() {
        this.C.setScale(e(), e());
        Matrix matrix = this.C;
        RectF rectF = this.d;
        matrix.postTranslate(rectF.left, rectF.top);
        this.C.mapRect(this.f6529e, this.g);
        float fD = d() % 360.0f;
        if (Math.abs(fD) >= 180.0f) {
            fD = 360.0f - Math.abs(fD);
        }
        c(fD);
        e(this.h);
        this.k = true;
    }

    public void w() {
        if (this.x.isEmpty()) {
            return;
        }
        this.x.remove(r0.size() - 1);
    }

    public void x() {
        if (this.y.isEmpty()) {
            return;
        }
        this.y.remove(r0.size() - 1);
    }

    private void d(float f, float f2) {
        this.d.set(0.0f, 0.0f, this.f6528a.getWidth(), this.f6528a.getHeight());
        this.f6529e.set(this.d);
        this.p.d(f, f2);
        if (this.f6529e.isEmpty()) {
            return;
        }
        v();
        this.t = true;
        o();
    }

    public io.dcloud.feature.gallery.imageedit.c.i.a c(float f, float f2) {
        return new io.dcloud.feature.gallery.imageedit.c.i.a(f, f2, e(), d());
    }

    public void h(float f, float f2) {
        if (f == 0.0f || f2 == 0.0f) {
            return;
        }
        this.s.set(0.0f, 0.0f, f, f2);
        if (this.t) {
            this.C.setTranslate(this.s.centerX() - this.f6529e.centerX(), this.s.centerY() - this.f6529e.centerY());
            this.C.mapRect(this.d);
            this.C.mapRect(this.f6529e);
        } else {
            d(f, f2);
        }
        this.p.d(f, f2);
        this.u = true;
        a();
    }

    private void c(io.dcloud.feature.gallery.imageedit.c.j.a aVar) {
        if (aVar == null) {
            return;
        }
        b(this.v);
        if (aVar.b()) {
            this.v = aVar;
            this.w.remove(aVar);
        } else {
            aVar.a();
        }
    }

    public void f(Canvas canvas) {
        if (this.w.isEmpty()) {
            return;
        }
        canvas.save();
        for (io.dcloud.feature.gallery.imageedit.c.j.a aVar : this.w) {
            if (!aVar.b()) {
                float x = aVar.getX() + aVar.getPivotX();
                float y = aVar.getY() + aVar.getPivotY();
                canvas.save();
                this.C.setTranslate(aVar.getX(), aVar.getY());
                this.C.postScale(aVar.getScale(), aVar.getScale(), x, y);
                this.C.postRotate(aVar.getRotation(), x, y);
                canvas.concat(this.C);
                aVar.a(canvas);
                canvas.restore();
            }
        }
        canvas.restore();
    }

    public void e(Canvas canvas) {
        this.C.setRotate(d(), this.f6529e.centerX(), this.f6529e.centerY());
        this.C.mapRect(this.f, this.p.c() ? this.d : this.f6529e);
        canvas.clipRect(this.f);
    }

    public void g(float f, float f2) {
        b bVar;
        io.dcloud.feature.gallery.imageedit.c.b bVar2 = this.q;
        if ((bVar2 == io.dcloud.feature.gallery.imageedit.c.b.DOODLE || bVar2 == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC) && (bVar = this.c) != null) {
            bVar.a();
        }
        if (this.m != null) {
            this.m = null;
        }
    }

    public RectF b() {
        return this.f6529e;
    }

    public void e(float f, float f2) {
        this.n = true;
        m();
        this.p.d(true);
    }

    public void a(io.dcloud.feature.gallery.imageedit.c.b bVar) {
        if (this.q == bVar) {
            return;
        }
        b(this.v);
        io.dcloud.feature.gallery.imageedit.c.b bVar2 = io.dcloud.feature.gallery.imageedit.c.b.CLIP;
        if (bVar == bVar2) {
            c(true);
        }
        this.q = bVar;
        if (bVar == bVar2) {
            g();
            this.h = d();
            this.g.set(this.f6529e);
            float fE = 1.0f / e();
            Matrix matrix = this.C;
            RectF rectF = this.d;
            matrix.setTranslate(-rectF.left, -rectF.top);
            this.C.postScale(fE, fE);
            this.C.mapRect(this.g);
            this.p.a(this.f6529e, f());
            return;
        }
        if (bVar == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC) {
            l();
        }
        this.p.a(false);
    }

    public io.dcloud.feature.gallery.imageedit.c.i.a b(float f, float f2) {
        io.dcloud.feature.gallery.imageedit.c.i.a aVar = new io.dcloud.feature.gallery.imageedit.c.i.a(f, f2, e(), f());
        if (this.q == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            RectF rectF = new RectF(this.p.a());
            rectF.offset(f, f2);
            if (this.p.f()) {
                RectF rectF2 = new RectF();
                this.C.setRotate(f(), this.f6529e.centerX(), this.f6529e.centerY());
                this.C.mapRect(rectF2, this.f6529e);
                aVar.a(io.dcloud.feature.gallery.imageedit.c.k.a.b(rectF, rectF2));
            } else {
                RectF rectF3 = new RectF();
                if (this.p.d()) {
                    this.C.setRotate(f() - d(), this.f6529e.centerX(), this.f6529e.centerY());
                    this.C.mapRect(rectF3, this.p.b(f, f2));
                    aVar.a(io.dcloud.feature.gallery.imageedit.c.k.a.b(rectF, rectF3, this.f6529e.centerX(), this.f6529e.centerY()));
                } else {
                    this.C.setRotate(f(), this.f6529e.centerX(), this.f6529e.centerY());
                    this.C.mapRect(rectF3, this.d);
                    aVar.a(io.dcloud.feature.gallery.imageedit.c.k.a.a(rectF, rectF3, this.f6529e.centerX(), this.f6529e.centerY()));
                }
            }
        } else {
            RectF rectF4 = new RectF();
            this.C.setRotate(f(), this.f6529e.centerX(), this.f6529e.centerY());
            this.C.mapRect(rectF4, this.f6529e);
            RectF rectF5 = new RectF(this.s);
            rectF5.offset(f, f2);
            aVar.a(io.dcloud.feature.gallery.imageedit.c.k.a.a(rectF5, rectF4, this.k));
            this.k = false;
        }
        return aVar;
    }

    public int c(Canvas canvas) {
        int iSaveLayer = canvas.saveLayer(this.d, null, 31);
        if (!k()) {
            canvas.save();
            float fE = e();
            RectF rectF = this.d;
            canvas.translate(rectF.left, rectF.top);
            canvas.scale(fE, fE);
            Iterator<io.dcloud.feature.gallery.imageedit.c.c> it = this.y.iterator();
            while (it.hasNext()) {
                it.next().b(canvas, this.z);
            }
            canvas.restore();
        }
        return iSaveLayer;
    }

    public void d(Canvas canvas) {
        if (this.q == io.dcloud.feature.gallery.imageedit.c.b.CLIP && this.n) {
            this.o.reset();
            Path path = this.o;
            RectF rectF = this.d;
            path.addRect(rectF.left - 2.0f, rectF.top - 2.0f, rectF.right + 2.0f, rectF.bottom + 2.0f, Path.Direction.CW);
            this.o.addRect(this.f6529e, Path.Direction.CCW);
            canvas.drawPath(this.o, this.B);
        }
    }

    public void e(float f) {
        this.j = f;
    }

    public float e() {
        return (this.d.width() * 1.0f) / this.f6528a.getWidth();
    }

    public float d() {
        return this.i;
    }

    public void d(float f) {
        b(f, this.f6529e.centerX(), this.f6529e.centerY());
    }

    public void c(float f) {
        this.i = f;
    }

    public void f(float f, float f2) {
        b bVar;
        this.n = false;
        b(this.v);
        io.dcloud.feature.gallery.imageedit.c.b bVar2 = this.q;
        if (bVar2 == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            this.m = this.p.a(f, f2);
            this.p.d(false);
        } else if ((bVar2 == io.dcloud.feature.gallery.imageedit.c.b.DOODLE || bVar2 == io.dcloud.feature.gallery.imageedit.c.b.MOSAIC) && (bVar = this.c) != null) {
            bVar.b();
        }
    }

    private void c(boolean z) {
        if (z != this.r) {
            b(z ? -d() : f());
            this.r = z;
        }
    }

    public io.dcloud.feature.gallery.imageedit.c.i.a a(float f, float f2) {
        RectF rectFB = this.p.b(f, f2);
        this.C.setRotate(-d(), this.f6529e.centerX(), this.f6529e.centerY());
        this.C.mapRect(this.f6529e, rectFB);
        return new io.dcloud.feature.gallery.imageedit.c.i.a(f + (this.f6529e.centerX() - rectFB.centerX()), f2 + (this.f6529e.centerY() - rectFB.centerY()), e(), d());
    }

    public float f() {
        return this.j;
    }

    public <S extends io.dcloud.feature.gallery.imageedit.c.j.a> void a(S s) {
        if (s != null) {
            c(s);
        }
    }

    private void b(io.dcloud.feature.gallery.imageedit.c.j.a aVar) {
        if (aVar == null) {
            return;
        }
        if (!aVar.b()) {
            if (!this.w.contains(aVar)) {
                this.w.add(aVar);
            }
            if (this.v == aVar) {
                this.v = null;
                return;
            }
            return;
        }
        aVar.dismiss();
    }

    public void a(io.dcloud.feature.gallery.imageedit.c.c cVar, float f, float f2) {
        if (cVar == null) {
            return;
        }
        float fE = 1.0f / e();
        this.C.setTranslate(f, f2);
        this.C.postRotate(-d(), this.f6529e.centerX(), this.f6529e.centerY());
        Matrix matrix = this.C;
        RectF rectF = this.d;
        matrix.postTranslate(-rectF.left, -rectF.top);
        this.C.postScale(fE, fE);
        cVar.a(this.C);
        int i = C0171a.f6530a[cVar.b().ordinal()];
        if (i == 1) {
            cVar.a(cVar.d() * fE);
            this.x.add(cVar);
        } else {
            if (i != 2) {
                return;
            }
            cVar.a(cVar.d() * fE);
            this.y.add(cVar);
        }
    }

    public void b(c cVar) {
        List<c> list = this.D;
        if (list != null) {
            list.remove(cVar);
        }
    }

    public void b(Canvas canvas) {
        canvas.clipRect(this.p.c() ? this.d : this.f6529e);
        canvas.drawBitmap(this.f6528a, (Rect) null, this.d, (Paint) null);
    }

    public void b(float f, float f2, float f3) {
        a(f / e(), f2, f3);
    }

    public void a(c cVar) {
        if (this.D == null) {
            this.D = new ArrayList();
        }
        this.D.add(cVar);
    }

    public void b(boolean z) {
        this.l = false;
    }

    public void b(int i) {
        this.p.a(i);
    }

    private void a() {
        List<c> list = this.D;
        if (list != null) {
            for (c cVar : list) {
                if (cVar != null) {
                    cVar.a();
                }
            }
        }
    }

    public void a(Canvas canvas, int i) {
        canvas.drawBitmap(this.b, (Rect) null, this.d, this.A);
        canvas.restoreToCount(i);
    }

    public void a(Canvas canvas) {
        if (h()) {
            return;
        }
        canvas.save();
        float fE = e();
        RectF rectF = this.d;
        canvas.translate(rectF.left, rectF.top);
        canvas.scale(fE, fE);
        Iterator<io.dcloud.feature.gallery.imageedit.c.c> it = this.x.iterator();
        while (it.hasNext()) {
            it.next().a(canvas, this.z);
        }
        canvas.restore();
    }

    public void a(Canvas canvas, float f, float f2) {
        if (this.q == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            this.p.a(canvas);
        }
    }

    public io.dcloud.feature.gallery.imageedit.c.i.a a(float f, float f2, float f3, float f4) {
        if (this.q != io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            return null;
        }
        this.p.d(false);
        a.EnumC0172a enumC0172a = this.m;
        if (enumC0172a == null) {
            return null;
        }
        this.p.a(enumC0172a, f3, f4);
        RectF rectF = new RectF();
        this.C.setRotate(d(), this.f6529e.centerX(), this.f6529e.centerY());
        this.C.mapRect(rectF, this.d);
        RectF rectFB = this.p.b(f, f2);
        io.dcloud.feature.gallery.imageedit.c.i.a aVar = new io.dcloud.feature.gallery.imageedit.c.i.a(f, f2, e(), f());
        aVar.a(io.dcloud.feature.gallery.imageedit.c.k.a.a(rectFB, rectF, this.f6529e.centerX(), this.f6529e.centerY()));
        return aVar;
    }

    public void a(int i) {
        this.j = Math.round((this.i + i) / 90.0f) * 90;
        if (this.p.e()) {
            this.f6529e.set(this.d);
        }
        this.p.a(this.f6529e, f());
    }

    public void a(float f, float f2, float f3) {
        if (f == 1.0f) {
            return;
        }
        if (Math.max(this.f6529e.width(), this.f6529e.height()) >= 10000.0f || Math.min(this.f6529e.width(), this.f6529e.height()) <= 500.0f) {
            f += (1.0f - f) / 2.0f;
        }
        this.C.setScale(f, f, f2, f3);
        this.C.mapRect(this.d);
        this.C.mapRect(this.f6529e);
        this.d.contains(this.f6529e);
        for (io.dcloud.feature.gallery.imageedit.c.j.a aVar : this.w) {
            this.C.mapRect(aVar.getFrame());
            float x = aVar.getX() + aVar.getPivotX();
            float y = aVar.getY() + aVar.getPivotY();
            aVar.a(f);
            aVar.setX((aVar.getX() + aVar.getFrame().centerX()) - x);
            aVar.setY((aVar.getY() + aVar.getFrame().centerY()) - y);
        }
    }

    public void a(float f) {
        this.p.a(f);
    }

    public boolean a(float f, float f2, boolean z) {
        if (this.q == io.dcloud.feature.gallery.imageedit.c.b.CLIP) {
            boolean z2 = !this.l;
            this.p.b(false);
            this.p.a(true);
            this.p.c(false);
            return z2;
        }
        if (this.r && !this.l) {
            c(false);
        }
        return false;
    }

    public void a(boolean z) {
        this.l = true;
        Log.d("IMGImage", "Homing cancel");
    }

    public void a(int i, int i2) {
        io.dcloud.feature.gallery.imageedit.c.g.b bVar = this.p;
        if (bVar != null) {
            bVar.e(i, i2);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
