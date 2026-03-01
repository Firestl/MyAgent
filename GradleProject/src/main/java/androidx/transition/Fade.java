package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import supwisdom.fh;
import supwisdom.l8;
import supwisdom.lb;
import supwisdom.og;
import supwisdom.pg;
import supwisdom.tg;

/* JADX INFO: loaded from: classes.dex */
public class Fade extends Visibility {

    public class a extends pg {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1430a;

        public a(View view) {
            this.f1430a = view;
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            fh.a(this.f1430a, 1.0f);
            fh.a(this.f1430a);
            transition.b(this);
        }
    }

    public static class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1431a;
        public boolean b = false;

        public b(View view) {
            this.f1431a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            fh.a(this.f1431a, 1.0f);
            if (this.b) {
                this.f1431a.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (lb.H(this.f1431a) && this.f1431a.getLayerType() == 0) {
                this.b = true;
                this.f1431a.setLayerType(2, null);
            }
        }
    }

    public Fade(int i) {
        b(i);
    }

    public final Animator a(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        fh.a(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, fh.d, f2);
        objectAnimatorOfFloat.addListener(new b(view));
        a(new a(view));
        return objectAnimatorOfFloat;
    }

    @Override // androidx.transition.Visibility
    public Animator b(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        fh.e(view);
        return a(view, a(tgVar, 1.0f), 0.0f);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void c(tg tgVar) {
        super.c(tgVar);
        tgVar.f9283a.put("android:fade:transitionAlpha", Float.valueOf(fh.c(tgVar.b)));
    }

    public Fade() {
    }

    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.d);
        b(l8.b(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, r()));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.transition.Visibility
    public Animator a(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        float fA = a(tgVar, 0.0f);
        return a(view, fA != 1.0f ? fA : 0.0f, 1.0f);
    }

    public static float a(tg tgVar, float f) {
        Float f2;
        return (tgVar == null || (f2 = (Float) tgVar.f9283a.get("android:fade:transitionAlpha")) == null) ? f : f2.floatValue();
    }
}
