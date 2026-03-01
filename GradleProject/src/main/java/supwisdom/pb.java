package supwisdom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class pb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<View> f8784a;
    public Runnable b = null;
    public Runnable c = null;
    public int d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ qb f8785a;
        public final /* synthetic */ View b;

        public a(pb pbVar, qb qbVar, View view) {
            this.f8785a = qbVar;
            this.b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f8785a.a(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f8785a.b(this.b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f8785a.c(this.b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ sb f8786a;
        public final /* synthetic */ View b;

        public b(pb pbVar, sb sbVar, View view) {
            this.f8786a = sbVar;
            this.b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f8786a.a(this.b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    public static class c implements qb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public pb f8787a;
        public boolean b;

        public c(pb pbVar) {
            this.f8787a = pbVar;
        }

        @Override // supwisdom.qb
        public void a(View view) {
            Object tag = view.getTag(2113929216);
            qb qbVar = tag instanceof qb ? (qb) tag : null;
            if (qbVar != null) {
                qbVar.a(view);
            }
        }

        @Override // supwisdom.qb
        @SuppressLint({"WrongConstant"})
        public void b(View view) {
            int i = this.f8787a.d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f8787a.d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.b) {
                pb pbVar = this.f8787a;
                Runnable runnable = pbVar.c;
                if (runnable != null) {
                    pbVar.c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                qb qbVar = tag instanceof qb ? (qb) tag : null;
                if (qbVar != null) {
                    qbVar.b(view);
                }
                this.b = true;
            }
        }

        @Override // supwisdom.qb
        public void c(View view) {
            this.b = false;
            if (this.f8787a.d > -1) {
                view.setLayerType(2, null);
            }
            pb pbVar = this.f8787a;
            Runnable runnable = pbVar.b;
            if (runnable != null) {
                pbVar.b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            qb qbVar = tag instanceof qb ? (qb) tag : null;
            if (qbVar != null) {
                qbVar.c(view);
            }
        }
    }

    public pb(View view) {
        this.f8784a = new WeakReference<>(view);
    }

    public pb a(long j) {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public pb b(float f) {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }

    public void c() {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public pb a(float f) {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public long b() {
        View view = this.f8784a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public pb a(Interpolator interpolator) {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public pb b(long j) {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public void a() {
        View view = this.f8784a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public pb a(qb qbVar) {
        View view = this.f8784a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                a(view, qbVar);
            } else {
                view.setTag(2113929216, qbVar);
                a(view, new c(this));
            }
        }
        return this;
    }

    public final void a(View view, qb qbVar) {
        if (qbVar != null) {
            view.animate().setListener(new a(this, qbVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public pb a(sb sbVar) {
        View view = this.f8784a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(sbVar != null ? new b(this, sbVar, view) : null);
        }
        return this;
    }
}
