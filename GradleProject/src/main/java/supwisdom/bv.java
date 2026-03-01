package supwisdom;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter;
import io.dcloud.nineoldandroids.animation.ObjectAnimator;

/* JADX INFO: loaded from: classes.dex */
public class bv extends ProgressBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7103a;
    public int b;
    public ObjectAnimator c;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Interpolator f7104a;

        /* JADX INFO: renamed from: supwisdom.bv$a$a, reason: collision with other inner class name */
        public class C0211a extends AnimatorListenerAdapter {
            public C0211a() {
            }

            @Override // io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter, io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                a aVar = a.this;
                bv bvVar = bv.this;
                if (bvVar.f7103a) {
                    return;
                }
                bvVar.c = bvVar.a(95, 50000, aVar.f7104a, null);
                bv.this.c.start();
            }
        }

        public a(Interpolator interpolator) {
            this.f7104a = interpolator;
        }

        @Override // io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter, io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            bv bvVar = bv.this;
            if (bvVar.f7103a) {
                return;
            }
            bvVar.c = bvVar.a(70, 2000, this.f7104a, new C0211a());
            bv.this.c.start();
        }
    }

    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter, io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            bv bvVar = bv.this;
            if (bvVar.f7103a) {
                bvVar.b();
            }
        }
    }

    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter, io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            bv.this.setProgress(0);
        }
    }

    public bv(Context context) {
        super(context, null, R.attr.progressBarStyleHorizontal);
        this.f7103a = false;
        this.b = 255;
        setMax(10000);
    }

    public final void b() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, Constant.JSONKEY.ALPHE, 1.0f, 0.0f);
        objectAnimatorOfFloat.setDuration(1000L);
        objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        objectAnimatorOfFloat.addListener(new c());
        objectAnimatorOfFloat.start();
    }

    public void c() {
        setProgress(0);
        setAlpha(1.0f);
        this.f7103a = false;
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        ObjectAnimator objectAnimatorA = a(30, 2000, decelerateInterpolator, new a(decelerateInterpolator));
        this.c = objectAnimatorA;
        objectAnimatorA.start();
    }

    public void setAlphaInt(int i) {
        this.b = i;
    }

    public void setColorInt(int i) {
        int iArgb = Color.argb(this.b, Color.red(i), Color.green(i), Color.blue(i));
        ClipDrawable clipDrawable = new ClipDrawable(new ColorDrawable(0), 3, 1);
        clipDrawable.setLevel(10000);
        ClipDrawable clipDrawable2 = new ClipDrawable(new ColorDrawable(iArgb), 3, 1);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{clipDrawable, clipDrawable2, clipDrawable2});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.secondaryProgress);
        layerDrawable.setId(2, R.id.progress);
        setProgressDrawable(layerDrawable);
    }

    public void a() {
        if (this.f7103a) {
            return;
        }
        this.f7103a = true;
        ObjectAnimator objectAnimator = this.c;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator objectAnimatorA = a(100, 400, new AccelerateInterpolator(), new b());
        this.c = objectAnimatorA;
        objectAnimatorA.start();
    }

    public final ObjectAnimator a(int i, int i2, Interpolator interpolator, AnimatorListenerAdapter animatorListenerAdapter) {
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, AbsoluteConst.JSON_KEY_PROGRESS, getProgress(), i * 100);
        objectAnimatorOfInt.setDuration(i2);
        objectAnimatorOfInt.setInterpolator(interpolator);
        if (animatorListenerAdapter != null) {
            objectAnimatorOfInt.addListener(animatorListenerAdapter);
        }
        return objectAnimatorOfInt;
    }
}
