package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import supwisdom.i4;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class e4 implements g4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RectF f7419a = new RectF();

    /* JADX INFO: compiled from: CardViewBaseImpl.java */
    public class a implements i4.a {
        public a() {
        }

        @Override // supwisdom.i4.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            float f2 = 2.0f * f;
            float fWidth = (rectF.width() - f2) - 1.0f;
            float fHeight = (rectF.height() - f2) - 1.0f;
            if (f >= 1.0f) {
                float f3 = f + 0.5f;
                float f4 = -f3;
                e4.this.f7419a.set(f4, f4, f3, f3);
                int iSave = canvas.save();
                canvas.translate(rectF.left + f3, rectF.top + f3);
                canvas.drawArc(e4.this.f7419a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(e4.this.f7419a, 180.0f, 90.0f, true, paint);
                canvas.translate(fHeight, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(e4.this.f7419a, 180.0f, 90.0f, true, paint);
                canvas.translate(fWidth, 0.0f);
                canvas.rotate(90.0f);
                canvas.drawArc(e4.this.f7419a, 180.0f, 90.0f, true, paint);
                canvas.restoreToCount(iSave);
                float f5 = (rectF.left + f3) - 1.0f;
                float f6 = rectF.top;
                canvas.drawRect(f5, f6, (rectF.right - f3) + 1.0f, f6 + f3, paint);
                float f7 = (rectF.left + f3) - 1.0f;
                float f8 = rectF.bottom;
                canvas.drawRect(f7, f8 - f3, (rectF.right - f3) + 1.0f, f8, paint);
            }
            canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
        }
    }

    @Override // supwisdom.g4
    public void a() {
        i4.r = new a();
    }

    @Override // supwisdom.g4
    public float b(f4 f4Var) {
        return j(f4Var).c();
    }

    @Override // supwisdom.g4
    public void c(f4 f4Var) {
    }

    @Override // supwisdom.g4
    public void c(f4 f4Var, float f) {
        j(f4Var).b(f);
        f(f4Var);
    }

    @Override // supwisdom.g4
    public float d(f4 f4Var) {
        return j(f4Var).d();
    }

    @Override // supwisdom.g4
    public ColorStateList e(f4 f4Var) {
        return j(f4Var).b();
    }

    @Override // supwisdom.g4
    public void f(f4 f4Var) {
        Rect rect = new Rect();
        j(f4Var).b(rect);
        f4Var.a((int) Math.ceil(h(f4Var)), (int) Math.ceil(g(f4Var)));
        f4Var.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // supwisdom.g4
    public float g(f4 f4Var) {
        return j(f4Var).e();
    }

    @Override // supwisdom.g4
    public float h(f4 f4Var) {
        return j(f4Var).f();
    }

    @Override // supwisdom.g4
    public void i(f4 f4Var) {
        j(f4Var).a(f4Var.a());
        f(f4Var);
    }

    public final i4 j(f4 f4Var) {
        return (i4) f4Var.c();
    }

    @Override // supwisdom.g4
    public void a(f4 f4Var, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        i4 i4VarA = a(context, colorStateList, f, f2, f3);
        i4VarA.a(f4Var.a());
        f4Var.a(i4VarA);
        f(f4Var);
    }

    @Override // supwisdom.g4
    public void b(f4 f4Var, float f) {
        j(f4Var).c(f);
    }

    public final i4 a(Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        return new i4(context.getResources(), colorStateList, f, f2, f3);
    }

    @Override // supwisdom.g4
    public void a(f4 f4Var, ColorStateList colorStateList) {
        j(f4Var).b(colorStateList);
    }

    @Override // supwisdom.g4
    public void a(f4 f4Var, float f) {
        j(f4Var).a(f);
        f(f4Var);
    }

    @Override // supwisdom.g4
    public float a(f4 f4Var) {
        return j(f4Var).g();
    }
}
