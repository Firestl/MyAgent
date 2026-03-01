package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import supwisdom.tg;
import supwisdom.vg;
import supwisdom.zf;

/* JADX INFO: loaded from: classes.dex */
public class Explode extends Visibility {
    public static final TimeInterpolator M = new DecelerateInterpolator();
    public static final TimeInterpolator N = new AccelerateInterpolator();
    public int[] L;

    public Explode() {
        this.L = new int[2];
        a(new zf());
    }

    private void d(tg tgVar) {
        View view = tgVar.b;
        view.getLocationOnScreen(this.L);
        int[] iArr = this.L;
        int i = iArr[0];
        int i2 = iArr[1];
        tgVar.f9283a.put("android:explode:screenBounds", new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2));
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void a(tg tgVar) {
        super.a(tgVar);
        d(tgVar);
    }

    @Override // androidx.transition.Visibility
    public Animator b(ViewGroup viewGroup, View view, tg tgVar, tg tgVar2) {
        float f;
        float f2;
        if (tgVar == null) {
            return null;
        }
        Rect rect = (Rect) tgVar.f9283a.get("android:explode:screenBounds");
        int i = rect.left;
        int i2 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) tgVar.b.getTag(R.id.transition_position);
        if (iArr != null) {
            f = (iArr[0] - rect.left) + translationX;
            f2 = (iArr[1] - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f = translationX;
            f2 = translationY;
        }
        a(viewGroup, rect, this.L);
        int[] iArr2 = this.L;
        return vg.a(view, tgVar, i, i2, translationX, translationY, f + iArr2[0], f2 + iArr2[1], N);
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
        Rect rect = (Rect) tgVar2.f9283a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        a(viewGroup, rect, this.L);
        int[] iArr = this.L;
        return vg.a(view, tgVar2, rect.left, rect.top, translationX + iArr[0], translationY + iArr[1], translationX, translationY, M);
    }

    public Explode(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = new int[2];
        a(new zf());
    }

    public final void a(View view, Rect rect, int[] iArr) {
        int iCenterY;
        int width;
        view.getLocationOnScreen(this.L);
        int[] iArr2 = this.L;
        int i = iArr2[0];
        int i2 = iArr2[1];
        Rect rectC = c();
        if (rectC == null) {
            width = (view.getWidth() / 2) + i + Math.round(view.getTranslationX());
            iCenterY = (view.getHeight() / 2) + i2 + Math.round(view.getTranslationY());
        } else {
            int iCenterX = rectC.centerX();
            iCenterY = rectC.centerY();
            width = iCenterX;
        }
        float fCenterX = rect.centerX() - width;
        float fCenterY = rect.centerY() - iCenterY;
        if (fCenterX == 0.0f && fCenterY == 0.0f) {
            fCenterX = ((float) (Math.random() * 2.0d)) - 1.0f;
            fCenterY = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float fA = a(fCenterX, fCenterY);
        float fA2 = a(view, width - i, iCenterY - i2);
        iArr[0] = Math.round((fCenterX / fA) * fA2);
        iArr[1] = Math.round(fA2 * (fCenterY / fA));
    }

    public static float a(View view, int i, int i2) {
        return a(Math.max(i, view.getWidth() - i), Math.max(i2, view.getHeight() - i2));
    }

    public static float a(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }
}
