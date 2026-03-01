package supwisdom;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: MotionTiming.java */
/* JADX INFO: loaded from: classes.dex */
public class kl0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f8181a;
    public long b;
    public TimeInterpolator c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8182e;

    public kl0(long j, long j2) {
        this.f8181a = 0L;
        this.b = 300L;
        this.c = null;
        this.d = 0;
        this.f8182e = 1;
        this.f8181a = j;
        this.b = j2;
    }

    public void a(Animator animator) {
        animator.setStartDelay(a());
        animator.setDuration(b());
        animator.setInterpolator(c());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(d());
            valueAnimator.setRepeatMode(e());
        }
    }

    public long b() {
        return this.b;
    }

    public TimeInterpolator c() {
        TimeInterpolator timeInterpolator = this.c;
        return timeInterpolator != null ? timeInterpolator : cl0.b;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.f8182e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || kl0.class != obj.getClass()) {
            return false;
        }
        kl0 kl0Var = (kl0) obj;
        if (a() == kl0Var.a() && b() == kl0Var.b() && d() == kl0Var.d() && e() == kl0Var.e()) {
            return c().getClass().equals(kl0Var.c().getClass());
        }
        return false;
    }

    public int hashCode() {
        return (((((((((int) (a() ^ (a() >>> 32))) * 31) + ((int) (b() ^ (b() >>> 32)))) * 31) + c().getClass().hashCode()) * 31) + d()) * 31) + e();
    }

    public String toString() {
        return '\n' + kl0.class.getName() + Operators.BLOCK_START + Integer.toHexString(System.identityHashCode(this)) + " delay: " + a() + " duration: " + b() + " interpolator: " + c().getClass() + " repeatCount: " + d() + " repeatMode: " + e() + "}\n";
    }

    public static TimeInterpolator b(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? cl0.b : interpolator instanceof AccelerateInterpolator ? cl0.c : interpolator instanceof DecelerateInterpolator ? cl0.d : interpolator;
    }

    public long a() {
        return this.f8181a;
    }

    public static kl0 a(ValueAnimator valueAnimator) {
        kl0 kl0Var = new kl0(valueAnimator.getStartDelay(), valueAnimator.getDuration(), b(valueAnimator));
        kl0Var.d = valueAnimator.getRepeatCount();
        kl0Var.f8182e = valueAnimator.getRepeatMode();
        return kl0Var;
    }

    public kl0(long j, long j2, TimeInterpolator timeInterpolator) {
        this.f8181a = 0L;
        this.b = 300L;
        this.c = null;
        this.d = 0;
        this.f8182e = 1;
        this.f8181a = j;
        this.b = j2;
        this.c = timeInterpolator;
    }
}
