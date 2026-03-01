package supwisdom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.legacy.widget.Space;
import com.google.android.material.R;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: IndicatorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class jn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8077a;
    public final TextInputLayout b;
    public LinearLayout c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f8078e;
    public int f;
    public Animator g;
    public final float h;
    public int i;
    public int j;
    public CharSequence k;
    public boolean l;
    public TextView m;
    public int n;
    public CharSequence o;
    public boolean p;
    public TextView q;
    public int r;
    public Typeface s;

    /* JADX INFO: compiled from: IndicatorViewController.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f8079a;
        public final /* synthetic */ TextView b;
        public final /* synthetic */ int c;
        public final /* synthetic */ TextView d;

        public a(int i, TextView textView, int i2, TextView textView2) {
            this.f8079a = i;
            this.b = textView;
            this.c = i2;
            this.d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            jn0.this.i = this.f8079a;
            jn0.this.g = null;
            TextView textView = this.b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.c != 1 || jn0.this.m == null) {
                    return;
                }
                jn0.this.m.setText((CharSequence) null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public jn0(TextInputLayout textInputLayout) {
        this.f8077a = textInputLayout.getContext();
        this.b = textInputLayout;
        this.h = r0.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    public void b(CharSequence charSequence) {
        c();
        this.o = charSequence;
        this.q.setText(charSequence);
        if (this.i != 2) {
            this.j = 2;
        }
        a(this.i, this.j, a(this.q, charSequence));
    }

    public void c() {
        Animator animator = this.g;
        if (animator != null) {
            animator.cancel();
        }
    }

    public boolean c(int i) {
        return i == 0 || i == 1;
    }

    public boolean d() {
        return b(this.j);
    }

    public CharSequence e() {
        return this.k;
    }

    public int f() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList g() {
        TextView textView = this.m;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence h() {
        return this.o;
    }

    public int i() {
        TextView textView = this.q;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public void j() {
        this.k = null;
        c();
        if (this.i == 1) {
            if (!this.p || TextUtils.isEmpty(this.o)) {
                this.j = 0;
            } else {
                this.j = 2;
            }
        }
        a(this.i, this.j, a(this.m, (CharSequence) null));
    }

    public void k() {
        c();
        if (this.i == 2) {
            this.j = 0;
        }
        a(this.i, this.j, a(this.q, (CharSequence) null));
    }

    public boolean l() {
        return this.l;
    }

    public boolean m() {
        return this.p;
    }

    public void d(int i) {
        this.n = i;
        TextView textView = this.m;
        if (textView != null) {
            this.b.a(textView, i);
        }
    }

    public void e(int i) {
        this.r = i;
        TextView textView = this.q;
        if (textView != null) {
            nc.d(textView, i);
        }
    }

    public void a(CharSequence charSequence) {
        c();
        this.k = charSequence;
        this.m.setText(charSequence);
        if (this.i != 1) {
            this.j = 1;
        }
        a(this.i, this.j, a(this.m, charSequence));
    }

    public final boolean b() {
        return (this.c == null || this.b.getEditText() == null) ? false : true;
    }

    public void b(TextView textView, int i) {
        FrameLayout frameLayout;
        if (this.c == null) {
            return;
        }
        if (c(i) && (frameLayout = this.f8078e) != null) {
            int i2 = this.f - 1;
            this.f = i2;
            a(frameLayout, i2);
            this.f8078e.removeView(textView);
        } else {
            this.c.removeView(textView);
        }
        int i3 = this.d - 1;
        this.d = i3;
        a(this.c, i3);
    }

    public final boolean a(TextView textView, CharSequence charSequence) {
        return lb.M(this.b) && this.b.isEnabled() && !(this.j == this.i && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public final void a(int i, int i2, boolean z) {
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.g = animatorSet;
            ArrayList arrayList = new ArrayList();
            a(arrayList, this.p, this.q, 2, i, i2);
            a(arrayList, this.l, this.m, 1, i, i2);
            dl0.a(animatorSet, arrayList);
            animatorSet.addListener(new a(i2, a(i), i, a(i2)));
            animatorSet.start();
        } else {
            a(i, i2);
        }
        this.b.q();
        this.b.d(z);
        this.b.v();
    }

    public void b(boolean z) {
        if (this.p == z) {
            return;
        }
        c();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f8077a);
            this.q = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_helper_text);
            Typeface typeface = this.s;
            if (typeface != null) {
                this.q.setTypeface(typeface);
            }
            this.q.setVisibility(4);
            lb.g((View) this.q, 1);
            e(this.r);
            a(this.q, 1);
        } else {
            k();
            b(this.q, 1);
            this.q = null;
            this.b.q();
            this.b.v();
        }
        this.p = z;
    }

    public final void a(int i, int i2) {
        TextView textViewA;
        TextView textViewA2;
        if (i == i2) {
            return;
        }
        if (i2 != 0 && (textViewA2 = a(i2)) != null) {
            textViewA2.setVisibility(0);
            textViewA2.setAlpha(1.0f);
        }
        if (i != 0 && (textViewA = a(i)) != null) {
            textViewA.setVisibility(4);
            if (i == 1) {
                textViewA.setText((CharSequence) null);
            }
        }
        this.i = i2;
    }

    public final boolean b(int i) {
        return (i != 1 || this.m == null || TextUtils.isEmpty(this.k)) ? false : true;
    }

    public final void a(List<Animator> list, boolean z, TextView textView, int i, int i2, int i3) {
        if (textView == null || !z) {
            return;
        }
        if (i == i3 || i == i2) {
            list.add(a(textView, i3 == i));
            if (i3 == i) {
                list.add(a(textView));
            }
        }
    }

    public void b(ColorStateList colorStateList) {
        TextView textView = this.q;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public final ObjectAnimator a(TextView textView, boolean z) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(167L);
        objectAnimatorOfFloat.setInterpolator(cl0.f7227a);
        return objectAnimatorOfFloat;
    }

    public final ObjectAnimator a(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.h, 0.0f);
        objectAnimatorOfFloat.setDuration(217L);
        objectAnimatorOfFloat.setInterpolator(cl0.d);
        return objectAnimatorOfFloat;
    }

    public final TextView a(int i) {
        if (i == 1) {
            return this.m;
        }
        if (i != 2) {
            return null;
        }
        return this.q;
    }

    public void a() {
        if (b()) {
            lb.b(this.c, lb.r(this.b.getEditText()), 0, lb.q(this.b.getEditText()), 0);
        }
    }

    public void a(TextView textView, int i) {
        if (this.c == null && this.f8078e == null) {
            LinearLayout linearLayout = new LinearLayout(this.f8077a);
            this.c = linearLayout;
            linearLayout.setOrientation(0);
            this.b.addView(this.c, -1, -2);
            FrameLayout frameLayout = new FrameLayout(this.f8077a);
            this.f8078e = frameLayout;
            this.c.addView(frameLayout, -1, new FrameLayout.LayoutParams(-2, -2));
            this.c.addView(new Space(this.f8077a), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.b.getEditText() != null) {
                a();
            }
        }
        if (c(i)) {
            this.f8078e.setVisibility(0);
            this.f8078e.addView(textView);
            this.f++;
        } else {
            this.c.addView(textView, i);
        }
        this.c.setVisibility(0);
        this.d++;
    }

    public final void a(ViewGroup viewGroup, int i) {
        if (i == 0) {
            viewGroup.setVisibility(8);
        }
    }

    public void a(boolean z) {
        if (this.l == z) {
            return;
        }
        c();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.f8077a);
            this.m = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_error);
            Typeface typeface = this.s;
            if (typeface != null) {
                this.m.setTypeface(typeface);
            }
            d(this.n);
            this.m.setVisibility(4);
            lb.g((View) this.m, 1);
            a(this.m, 0);
        } else {
            j();
            b(this.m, 0);
            this.m = null;
            this.b.q();
            this.b.v();
        }
        this.l = z;
    }

    public void a(Typeface typeface) {
        if (typeface != this.s) {
            this.s = typeface;
            a(this.m, typeface);
            a(this.q, typeface);
        }
    }

    public final void a(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    public void a(ColorStateList colorStateList) {
        TextView textView = this.m;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }
}
