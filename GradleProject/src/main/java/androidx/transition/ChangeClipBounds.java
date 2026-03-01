package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import supwisdom.fh;
import supwisdom.lb;
import supwisdom.lg;
import supwisdom.tg;

/* JADX INFO: loaded from: classes.dex */
public class ChangeClipBounds extends Transition {
    public static final String[] J = {"android:clipBounds:clip"};

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1421a;

        public a(View view) {
            this.f1421a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            lb.a(this.f1421a, (Rect) null);
        }
    }

    public ChangeClipBounds() {
    }

    @Override // androidx.transition.Transition
    public void a(tg tgVar) {
        d(tgVar);
    }

    @Override // androidx.transition.Transition
    public void c(tg tgVar) {
        d(tgVar);
    }

    public final void d(tg tgVar) {
        View view = tgVar.b;
        if (view.getVisibility() == 8) {
            return;
        }
        Rect rectH = lb.h(view);
        tgVar.f9283a.put("android:clipBounds:clip", rectH);
        if (rectH == null) {
            tgVar.f9283a.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    @Override // androidx.transition.Transition
    public String[] n() {
        return J;
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        ObjectAnimator objectAnimatorOfObject = null;
        if (tgVar != null && tgVar2 != null && tgVar.f9283a.containsKey("android:clipBounds:clip") && tgVar2.f9283a.containsKey("android:clipBounds:clip")) {
            Rect rect = (Rect) tgVar.f9283a.get("android:clipBounds:clip");
            Rect rect2 = (Rect) tgVar2.f9283a.get("android:clipBounds:clip");
            boolean z = rect2 == null;
            if (rect == null && rect2 == null) {
                return null;
            }
            if (rect == null) {
                rect = (Rect) tgVar.f9283a.get("android:clipBounds:bounds");
            } else if (rect2 == null) {
                rect2 = (Rect) tgVar2.f9283a.get("android:clipBounds:bounds");
            }
            if (rect.equals(rect2)) {
                return null;
            }
            lb.a(tgVar2.b, rect);
            objectAnimatorOfObject = ObjectAnimator.ofObject(tgVar2.b, (Property<View, V>) fh.f7606e, (TypeEvaluator) new lg(new Rect()), (Object[]) new Rect[]{rect, rect2});
            if (z) {
                objectAnimatorOfObject.addListener(new a(tgVar2.b));
            }
        }
        return objectAnimatorOfObject;
    }
}
