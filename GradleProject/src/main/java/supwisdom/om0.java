package supwisdom;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import java.util.Map;

/* JADX INFO: compiled from: TextScale.java */
/* JADX INFO: loaded from: classes.dex */
public class om0 extends Transition {

    /* JADX INFO: compiled from: TextScale.java */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextView f8687a;

        public a(TextView textView) {
            this.f8687a = textView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f8687a.setScaleX(fFloatValue);
            this.f8687a.setScaleY(fFloatValue);
        }
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
        if (view instanceof TextView) {
            tgVar.f9283a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        if (tgVar == null || tgVar2 == null || !(tgVar.b instanceof TextView)) {
            return null;
        }
        View view = tgVar2.b;
        if (!(view instanceof TextView)) {
            return null;
        }
        TextView textView = (TextView) view;
        Map<String, Object> map = tgVar.f9283a;
        Map<String, Object> map2 = tgVar2.f9283a;
        float fFloatValue = map.get("android:textscale:scale") != null ? ((Float) map.get("android:textscale:scale")).floatValue() : 1.0f;
        float fFloatValue2 = map2.get("android:textscale:scale") != null ? ((Float) map2.get("android:textscale:scale")).floatValue() : 1.0f;
        if (fFloatValue == fFloatValue2) {
            return null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fFloatValue, fFloatValue2);
        valueAnimatorOfFloat.addUpdateListener(new a(textView));
        return valueAnimatorOfFloat;
    }
}
