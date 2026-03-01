package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;
import supwisdom.ag;
import supwisdom.eg;
import supwisdom.fg;
import supwisdom.fh;
import supwisdom.hg;
import supwisdom.kg;
import supwisdom.l8;
import supwisdom.lb;
import supwisdom.og;
import supwisdom.pg;
import supwisdom.tg;
import supwisdom.yf;

/* JADX INFO: loaded from: classes.dex */
public class ChangeTransform extends Transition {
    public static final String[] M = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    public static final Property<e, float[]> N = new a(float[].class, "nonTranslations");
    public static final Property<e, PointF> O = new b(PointF.class, "translations");
    public static final boolean P;
    public boolean J;
    public boolean K;
    public Matrix L;

    public static class a extends Property<e, float[]> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(e eVar, float[] fArr) {
            eVar.a(fArr);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public float[] get(e eVar) {
            return null;
        }
    }

    public static class b extends Property<e, PointF> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(e eVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(e eVar, PointF pointF) {
            eVar.a(pointF);
        }
    }

    public class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1423a;
        public Matrix b = new Matrix();
        public final /* synthetic */ boolean c;
        public final /* synthetic */ Matrix d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f1424e;
        public final /* synthetic */ f f;
        public final /* synthetic */ e g;

        public c(boolean z, Matrix matrix, View view, f fVar, e eVar) {
            this.c = z;
            this.d = matrix;
            this.f1424e = view;
            this.f = fVar;
            this.g = eVar;
        }

        public final void a(Matrix matrix) {
            this.b.set(matrix);
            this.f1424e.setTag(R.id.transition_transform, this.b);
            this.f.a(this.f1424e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1423a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.f1423a) {
                if (this.c && ChangeTransform.this.J) {
                    a(this.d);
                } else {
                    this.f1424e.setTag(R.id.transition_transform, null);
                    this.f1424e.setTag(R.id.parent_matrix, null);
                }
            }
            fh.a(this.f1424e, (Matrix) null);
            this.f.a(this.f1424e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            a(this.g.a());
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            ChangeTransform.f(this.f1424e);
        }
    }

    public static class d extends pg {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f1425a;
        public eg b;

        public d(View view, eg egVar) {
            this.f1425a = view;
            this.b = egVar;
        }

        @Override // supwisdom.pg, androidx.transition.Transition.f
        public void b(Transition transition) {
            this.b.setVisibility(4);
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            transition.b(this);
            fg.a(this.f1425a);
            this.f1425a.setTag(R.id.transition_transform, null);
            this.f1425a.setTag(R.id.parent_matrix, null);
        }

        @Override // supwisdom.pg, androidx.transition.Transition.f
        public void d(Transition transition) {
            this.b.setVisibility(0);
        }
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f1428a;
        public final float b;
        public final float c;
        public final float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f1429e;
        public final float f;
        public final float g;
        public final float h;

        public f(View view) {
            this.f1428a = view.getTranslationX();
            this.b = view.getTranslationY();
            this.c = lb.C(view);
            this.d = view.getScaleX();
            this.f1429e = view.getScaleY();
            this.f = view.getRotationX();
            this.g = view.getRotationY();
            this.h = view.getRotation();
        }

        public void a(View view) {
            ChangeTransform.a(view, this.f1428a, this.b, this.c, this.d, this.f1429e, this.f, this.g, this.h);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return fVar.f1428a == this.f1428a && fVar.b == this.b && fVar.c == this.c && fVar.d == this.d && fVar.f1429e == this.f1429e && fVar.f == this.f && fVar.g == this.g && fVar.h == this.h;
        }

        public int hashCode() {
            float f = this.f1428a;
            int iFloatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
            float f2 = this.b;
            int iFloatToIntBits2 = (iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
            float f3 = this.c;
            int iFloatToIntBits3 = (iFloatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
            float f4 = this.d;
            int iFloatToIntBits4 = (iFloatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31;
            float f5 = this.f1429e;
            int iFloatToIntBits5 = (iFloatToIntBits4 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0)) * 31;
            float f6 = this.f;
            int iFloatToIntBits6 = (iFloatToIntBits5 + (f6 != 0.0f ? Float.floatToIntBits(f6) : 0)) * 31;
            float f7 = this.g;
            int iFloatToIntBits7 = (iFloatToIntBits6 + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31;
            float f8 = this.h;
            return iFloatToIntBits7 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0);
        }
    }

    static {
        P = Build.VERSION.SDK_INT >= 21;
    }

    public ChangeTransform() {
        this.J = true;
        this.K = true;
        this.L = new Matrix();
    }

    public static void f(View view) {
        a(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // androidx.transition.Transition
    public void a(tg tgVar) {
        d(tgVar);
    }

    public final void b(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        View view = tgVar2.b;
        Matrix matrix = new Matrix((Matrix) tgVar2.f9283a.get("android:changeTransform:parentMatrix"));
        fh.c(viewGroup, matrix);
        eg egVarA = fg.a(view, viewGroup, matrix);
        if (egVarA == null) {
            return;
        }
        egVarA.a((ViewGroup) tgVar.f9283a.get("android:changeTransform:parent"), tgVar.b);
        Transition transition = this;
        while (true) {
            TransitionSet transitionSet = transition.r;
            if (transitionSet == null) {
                break;
            } else {
                transition = transitionSet;
            }
        }
        transition.a(new d(view, egVarA));
        if (P) {
            View view2 = tgVar.b;
            if (view2 != tgVar2.b) {
                fh.a(view2, 0.0f);
            }
            fh.a(view, 1.0f);
        }
    }

    @Override // androidx.transition.Transition
    public void c(tg tgVar) {
        d(tgVar);
        if (P) {
            return;
        }
        ((ViewGroup) tgVar.b.getParent()).startViewTransition(tgVar.b);
    }

    public final void d(tg tgVar) {
        View view = tgVar.b;
        if (view.getVisibility() == 8) {
            return;
        }
        tgVar.f9283a.put("android:changeTransform:parent", view.getParent());
        tgVar.f9283a.put("android:changeTransform:transforms", new f(view));
        Matrix matrix = view.getMatrix();
        tgVar.f9283a.put("android:changeTransform:matrix", (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
        if (this.K) {
            Matrix matrix2 = new Matrix();
            fh.b((ViewGroup) view.getParent(), matrix2);
            matrix2.preTranslate(-r2.getScrollX(), -r2.getScrollY());
            tgVar.f9283a.put("android:changeTransform:parentMatrix", matrix2);
            tgVar.f9283a.put("android:changeTransform:intermediateMatrix", view.getTag(R.id.transition_transform));
            tgVar.f9283a.put("android:changeTransform:intermediateParentMatrix", view.getTag(R.id.parent_matrix));
        }
    }

    @Override // androidx.transition.Transition
    public String[] n() {
        return M;
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f1426a = new Matrix();
        public final View b;
        public final float[] c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f1427e;

        public e(View view, float[] fArr) {
            this.b = view;
            float[] fArr2 = (float[]) fArr.clone();
            this.c = fArr2;
            this.d = fArr2[2];
            this.f1427e = fArr2[5];
            b();
        }

        public void a(float[] fArr) {
            System.arraycopy(fArr, 0, this.c, 0, fArr.length);
            b();
        }

        public final void b() {
            float[] fArr = this.c;
            fArr[2] = this.d;
            fArr[5] = this.f1427e;
            this.f1426a.setValues(fArr);
            fh.a(this.b, this.f1426a);
        }

        public void a(PointF pointF) {
            this.d = pointF.x;
            this.f1427e = pointF.y;
            b();
        }

        public Matrix a() {
            return this.f1426a;
        }
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        if (tgVar == null || tgVar2 == null || !tgVar.f9283a.containsKey("android:changeTransform:parent") || !tgVar2.f9283a.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup viewGroup2 = (ViewGroup) tgVar.f9283a.get("android:changeTransform:parent");
        boolean z = this.K && !a(viewGroup2, (ViewGroup) tgVar2.f9283a.get("android:changeTransform:parent"));
        Matrix matrix = (Matrix) tgVar.f9283a.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            tgVar.f9283a.put("android:changeTransform:matrix", matrix);
        }
        Matrix matrix2 = (Matrix) tgVar.f9283a.get("android:changeTransform:intermediateParentMatrix");
        if (matrix2 != null) {
            tgVar.f9283a.put("android:changeTransform:parentMatrix", matrix2);
        }
        if (z) {
            b(tgVar, tgVar2);
        }
        ObjectAnimator objectAnimatorA = a(tgVar, tgVar2, z);
        if (z && objectAnimatorA != null && this.J) {
            b(viewGroup, tgVar, tgVar2);
        } else if (!P) {
            viewGroup2.endViewTransition(tgVar.b);
        }
        return objectAnimatorA;
    }

    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = true;
        this.K = true;
        this.L = new Matrix();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.f8670e);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        this.J = l8.a(typedArrayObtainStyledAttributes, xmlPullParser, "reparentWithOverlay", 1, true);
        this.K = l8.a(typedArrayObtainStyledAttributes, xmlPullParser, "reparent", 0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(tg tgVar, tg tgVar2) {
        Matrix matrix = (Matrix) tgVar2.f9283a.get("android:changeTransform:parentMatrix");
        tgVar2.b.setTag(R.id.parent_matrix, matrix);
        Matrix matrix2 = this.L;
        matrix2.reset();
        matrix.invert(matrix2);
        Matrix matrix3 = (Matrix) tgVar.f9283a.get("android:changeTransform:matrix");
        if (matrix3 == null) {
            matrix3 = new Matrix();
            tgVar.f9283a.put("android:changeTransform:matrix", matrix3);
        }
        matrix3.postConcat((Matrix) tgVar.f9283a.get("android:changeTransform:parentMatrix"));
        matrix3.postConcat(matrix2);
    }

    public final ObjectAnimator a(tg tgVar, tg tgVar2, boolean z) {
        Matrix matrix = (Matrix) tgVar.f9283a.get("android:changeTransform:matrix");
        Matrix matrix2 = (Matrix) tgVar2.f9283a.get("android:changeTransform:matrix");
        if (matrix == null) {
            matrix = hg.f7835a;
        }
        if (matrix2 == null) {
            matrix2 = hg.f7835a;
        }
        Matrix matrix3 = matrix2;
        if (matrix.equals(matrix3)) {
            return null;
        }
        f fVar = (f) tgVar2.f9283a.get("android:changeTransform:transforms");
        View view = tgVar2.b;
        f(view);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] fArr2 = new float[9];
        matrix3.getValues(fArr2);
        e eVar = new e(view, fArr);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(eVar, PropertyValuesHolder.ofObject(N, new ag(new float[9]), fArr, fArr2), kg.a(O, g().a(fArr[2], fArr[5], fArr2[2], fArr2[5])));
        c cVar = new c(z, matrix3, view, fVar, eVar);
        objectAnimatorOfPropertyValuesHolder.addListener(cVar);
        yf.a(objectAnimatorOfPropertyValuesHolder, cVar);
        return objectAnimatorOfPropertyValuesHolder;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.view.ViewGroup r4, android.view.ViewGroup r5) {
        /*
            r3 = this;
            boolean r0 = r3.b(r4)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1a
            boolean r0 = r3.b(r5)
            if (r0 != 0) goto Lf
            goto L1a
        Lf:
            supwisdom.tg r4 = r3.b(r4, r1)
            if (r4 == 0) goto L1f
            android.view.View r4 = r4.b
            if (r5 != r4) goto L1d
            goto L1e
        L1a:
            if (r4 != r5) goto L1d
            goto L1e
        L1d:
            r1 = 0
        L1e:
            r2 = r1
        L1f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeTransform.a(android.view.ViewGroup, android.view.ViewGroup):boolean");
    }

    public static void a(View view, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        view.setTranslationX(f2);
        view.setTranslationY(f3);
        lb.h(view, f4);
        view.setScaleX(f5);
        view.setScaleY(f6);
        view.setRotationX(f7);
        view.setRotationY(f8);
        view.setRotation(f9);
    }
}
