package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import org.xmlpull.v1.XmlPullParser;
import supwisdom.l8;
import supwisdom.lb;
import supwisdom.ng;
import supwisdom.og;
import supwisdom.tg;
import supwisdom.vg;

/* JADX INFO: loaded from: classes.dex */
public class Slide extends Visibility {
    public static final TimeInterpolator M = new DecelerateInterpolator();
    public static final TimeInterpolator N = new AccelerateInterpolator();
    public static final g O = new a();
    public static final g P = new b();
    public static final g Q = new c();
    public static final g R = new d();
    public static final g S = new e();
    public static final g T = new f();
    public g L;

    public static class a extends h {
        public a() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationX() - viewGroup.getWidth();
        }
    }

    public static class b extends h {
        public b() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return lb.n(viewGroup) == 1 ? view.getTranslationX() + viewGroup.getWidth() : view.getTranslationX() - viewGroup.getWidth();
        }
    }

    public static class c extends i {
        public c() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationY() - viewGroup.getHeight();
        }
    }

    public static class d extends h {
        public d() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationX() + viewGroup.getWidth();
        }
    }

    public static class e extends h {
        public e() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return lb.n(viewGroup) == 1 ? view.getTranslationX() - viewGroup.getWidth() : view.getTranslationX() + viewGroup.getWidth();
        }
    }

    public static class f extends i {
        public f() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationY() + viewGroup.getHeight();
        }
    }

    public interface g {
        float a(ViewGroup viewGroup, View view);

        float b(ViewGroup viewGroup, View view);
    }

    public static abstract class h implements g {
        public h() {
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationY();
        }

        public /* synthetic */ h(a aVar) {
            this();
        }
    }

    public static abstract class i implements g {
        public i() {
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationX();
        }

        public /* synthetic */ i(a aVar) {
            this();
        }
    }

    public Slide() {
        this.L = T;
        c(80);
    }

    private void d(tg tgVar) {
        int[] iArr = new int[2];
        tgVar.b.getLocationOnScreen(iArr);
        tgVar.f9283a.put("android:slide:screenPosition", iArr);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void a(tg tgVar) {
        super.a(tgVar);
        d(tgVar);
    }

    @Override // androidx.transition.Visibility
    public Animator b(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        if (tgVar == null) {
            return null;
        }
        int[] iArr = (int[]) tgVar.f9283a.get("android:slide:screenPosition");
        return vg.a(view, tgVar, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.L.b(viewGroup, view), this.L.a(viewGroup, view), N);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void c(tg tgVar) {
        super.c(tgVar);
        d(tgVar);
    }

    @Override // androidx.transition.Visibility
    public Animator a(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        if (tgVar2 == null) {
            return null;
        }
        int[] iArr = (int[]) tgVar2.f9283a.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return vg.a(view, tgVar2, iArr[0], iArr[1], this.L.b(viewGroup, view), this.L.a(viewGroup, view), translationX, translationY, M);
    }

    public void c(int i2) {
        if (i2 == 3) {
            this.L = O;
        } else if (i2 == 5) {
            this.L = R;
        } else if (i2 == 48) {
            this.L = Q;
        } else if (i2 == 80) {
            this.L = T;
        } else if (i2 == 8388611) {
            this.L = P;
        } else if (i2 == 8388613) {
            this.L = S;
        } else {
            throw new IllegalArgumentException("Invalid slide direction");
        }
        ng ngVar = new ng();
        ngVar.a(i2);
        a(ngVar);
    }

    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = T;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.f);
        int iB = l8.b(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        typedArrayObtainStyledAttributes.recycle();
        c(iB);
    }
}
