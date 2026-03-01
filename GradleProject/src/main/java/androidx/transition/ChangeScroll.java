package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import supwisdom.sg;
import supwisdom.tg;

/* JADX INFO: loaded from: classes.dex */
public class ChangeScroll extends Transition {
    public static final String[] J = {"android:changeScroll:x", "android:changeScroll:y"};

    public ChangeScroll() {
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
        tgVar.f9283a.put("android:changeScroll:x", Integer.valueOf(tgVar.b.getScrollX()));
        tgVar.f9283a.put("android:changeScroll:y", Integer.valueOf(tgVar.b.getScrollY()));
    }

    @Override // androidx.transition.Transition
    public String[] n() {
        return J;
    }

    public ChangeScroll(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        ObjectAnimator objectAnimatorOfInt;
        ObjectAnimator objectAnimatorOfInt2 = null;
        if (tgVar == null || tgVar2 == null) {
            return null;
        }
        View view = tgVar2.b;
        int iIntValue = ((Integer) tgVar.f9283a.get("android:changeScroll:x")).intValue();
        int iIntValue2 = ((Integer) tgVar2.f9283a.get("android:changeScroll:x")).intValue();
        int iIntValue3 = ((Integer) tgVar.f9283a.get("android:changeScroll:y")).intValue();
        int iIntValue4 = ((Integer) tgVar2.f9283a.get("android:changeScroll:y")).intValue();
        if (iIntValue != iIntValue2) {
            view.setScrollX(iIntValue);
            objectAnimatorOfInt = ObjectAnimator.ofInt(view, "scrollX", iIntValue, iIntValue2);
        } else {
            objectAnimatorOfInt = null;
        }
        if (iIntValue3 != iIntValue4) {
            view.setScrollY(iIntValue3);
            objectAnimatorOfInt2 = ObjectAnimator.ofInt(view, "scrollY", iIntValue3, iIntValue4);
        }
        return sg.a(objectAnimatorOfInt, objectAnimatorOfInt2);
    }
}
