package supwisdom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.taobao.weex.common.Constants;
import java.util.ArrayList;

/* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
/* JADX INFO: loaded from: classes.dex */
public class em0 extends dm0 {
    public InsetDrawable I;

    /* JADX INFO: compiled from: FloatingActionButtonImplLollipop.java */
    public static class a extends GradientDrawable {
        @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
        public boolean isStateful() {
            return true;
        }
    }

    public em0(VisibilityAwareImageButton visibilityAwareImageButton, xm0 xm0Var) {
        super(visibilityAwareImageButton, xm0Var);
    }

    @Override // supwisdom.dm0
    public void a(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i) {
        Drawable layerDrawable;
        Drawable drawableI = y8.i(a());
        this.j = drawableI;
        y8.a(drawableI, colorStateList);
        if (mode != null) {
            y8.a(this.j, mode);
        }
        if (i > 0) {
            this.l = a(i, colorStateList);
            layerDrawable = new LayerDrawable(new Drawable[]{this.l, this.j});
        } else {
            this.l = null;
            layerDrawable = this.j;
        }
        RippleDrawable rippleDrawable = new RippleDrawable(vm0.a(colorStateList2), layerDrawable, null);
        this.k = rippleDrawable;
        this.m = rippleDrawable;
        this.v.a(rippleDrawable);
    }

    @Override // supwisdom.dm0
    public void b(ColorStateList colorStateList) {
        Drawable drawable = this.k;
        if (drawable instanceof RippleDrawable) {
            ((RippleDrawable) drawable).setColor(vm0.a(colorStateList));
        } else {
            super.b(colorStateList);
        }
    }

    @Override // supwisdom.dm0
    public float f() {
        return this.u.getElevation();
    }

    @Override // supwisdom.dm0
    public void m() {
    }

    @Override // supwisdom.dm0
    public fm0 n() {
        return new gm0();
    }

    @Override // supwisdom.dm0
    public GradientDrawable o() {
        return new a();
    }

    @Override // supwisdom.dm0
    public void q() {
        x();
    }

    @Override // supwisdom.dm0
    public boolean t() {
        return false;
    }

    @Override // supwisdom.dm0
    public void b(Rect rect) {
        if (this.v.a()) {
            InsetDrawable insetDrawable = new InsetDrawable(this.k, rect.left, rect.top, rect.right, rect.bottom);
            this.I = insetDrawable;
            this.v.a(insetDrawable);
            return;
        }
        this.v.a(this.k);
    }

    @Override // supwisdom.dm0
    public void a(float f, float f2, float f3) {
        if (Build.VERSION.SDK_INT == 21) {
            this.u.refreshDrawableState();
        } else {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(dm0.C, a(f, f3));
            stateListAnimator.addState(dm0.D, a(f, f2));
            stateListAnimator.addState(dm0.E, a(f, f2));
            stateListAnimator.addState(dm0.F, a(f, f2));
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.u, Constants.Name.ELEVATION, f).setDuration(0L));
            int i = Build.VERSION.SDK_INT;
            if (i >= 22 && i <= 24) {
                VisibilityAwareImageButton visibilityAwareImageButton = this.u;
                arrayList.add(ObjectAnimator.ofFloat(visibilityAwareImageButton, (Property<VisibilityAwareImageButton, Float>) View.TRANSLATION_Z, visibilityAwareImageButton.getTranslationZ()).setDuration(100L));
            }
            arrayList.add(ObjectAnimator.ofFloat(this.u, (Property<VisibilityAwareImageButton, Float>) View.TRANSLATION_Z, 0.0f).setDuration(100L));
            animatorSet.playSequentially((Animator[]) arrayList.toArray(new Animator[0]));
            animatorSet.setInterpolator(dm0.B);
            stateListAnimator.addState(dm0.G, animatorSet);
            stateListAnimator.addState(dm0.H, a(0.0f, 0.0f));
            this.u.setStateListAnimator(stateListAnimator);
        }
        if (this.v.a()) {
            x();
        }
    }

    public final Animator a(float f, float f2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this.u, Constants.Name.ELEVATION, f).setDuration(0L)).with(ObjectAnimator.ofFloat(this.u, (Property<VisibilityAwareImageButton, Float>) View.TRANSLATION_Z, f2).setDuration(100L));
        animatorSet.setInterpolator(dm0.B);
        return animatorSet;
    }

    @Override // supwisdom.dm0
    public void a(int[] iArr) {
        if (Build.VERSION.SDK_INT == 21) {
            if (this.u.isEnabled()) {
                this.u.setElevation(this.n);
                if (this.u.isPressed()) {
                    this.u.setTranslationZ(this.p);
                    return;
                } else if (!this.u.isFocused() && !this.u.isHovered()) {
                    this.u.setTranslationZ(0.0f);
                    return;
                } else {
                    this.u.setTranslationZ(this.o);
                    return;
                }
            }
            this.u.setElevation(0.0f);
            this.u.setTranslationZ(0.0f);
        }
    }

    @Override // supwisdom.dm0
    public void a(Rect rect) {
        if (this.v.a()) {
            float fB = this.v.b();
            float f = f() + this.p;
            int iCeil = (int) Math.ceil(wm0.a(f, fB, false));
            int iCeil2 = (int) Math.ceil(wm0.b(f, fB, false));
            rect.set(iCeil, iCeil2, iCeil, iCeil2);
            return;
        }
        rect.set(0, 0, 0, 0);
    }
}
