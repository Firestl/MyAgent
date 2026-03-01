package supwisdom;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import supwisdom.xl0;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes.dex */
public interface yl0 extends xl0.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b implements TypeEvaluator<e> {
        public static final TypeEvaluator<e> b = new b();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final e f9895a = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f, e eVar, e eVar2) {
            this.f9895a.a(rm0.b(eVar.f9898a, eVar2.f9898a, f), rm0.b(eVar.b, eVar2.b, f), rm0.b(eVar.c, eVar2.c, f));
            return this.f9895a;
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class c extends Property<yl0, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<yl0, e> f9896a = new c("circularReveal");

        public c(String str) {
            super(e.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e get(yl0 yl0Var) {
            return yl0Var.getRevealInfo();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(yl0 yl0Var, e eVar) {
            yl0Var.setRevealInfo(eVar);
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class d extends Property<yl0, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<yl0, Integer> f9897a = new d("circularRevealScrimColor");

        public d(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(yl0 yl0Var) {
            return Integer.valueOf(yl0Var.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(yl0 yl0Var, Integer num) {
            yl0Var.setCircularRevealScrimColor(num.intValue());
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f9898a;
        public float b;
        public float c;

        public void a(float f, float f2, float f3) {
            this.f9898a = f;
            this.b = f2;
            this.c = f3;
        }

        public e() {
        }

        public e(float f, float f2, float f3) {
            this.f9898a = f;
            this.b = f2;
            this.c = f3;
        }

        public void a(e eVar) {
            a(eVar.f9898a, eVar.b, eVar.c);
        }

        public boolean a() {
            return this.c == Float.MAX_VALUE;
        }

        public e(e eVar) {
            this(eVar.f9898a, eVar.b, eVar.c);
        }
    }

    void a();

    void b();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(e eVar);
}
