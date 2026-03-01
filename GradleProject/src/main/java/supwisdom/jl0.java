package supwisdom;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MotionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public class jl0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p4<String, kl0> f8073a = new p4<>();

    public kl0 a(String str) {
        if (b(str)) {
            return this.f8073a.get(str);
        }
        throw new IllegalArgumentException();
    }

    public boolean b(String str) {
        return this.f8073a.get(str) != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || jl0.class != obj.getClass()) {
            return false;
        }
        return this.f8073a.equals(((jl0) obj).f8073a);
    }

    public int hashCode() {
        return this.f8073a.hashCode();
    }

    public String toString() {
        return '\n' + jl0.class.getName() + Operators.BLOCK_START + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f8073a + "}\n";
    }

    public void a(String str, kl0 kl0Var) {
        this.f8073a.put(str, kl0Var);
    }

    public long a() {
        int size = this.f8073a.size();
        long jMax = 0;
        for (int i = 0; i < size; i++) {
            kl0 kl0VarValueAt = this.f8073a.valueAt(i);
            jMax = Math.max(jMax, kl0VarValueAt.a() + kl0VarValueAt.b());
        }
        return jMax;
    }

    public static jl0 a(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return a(context, resourceId);
    }

    public static jl0 a(Context context, int i) {
        try {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, i);
            if (animatorLoadAnimator instanceof AnimatorSet) {
                return a(((AnimatorSet) animatorLoadAnimator).getChildAnimations());
            }
            if (animatorLoadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(animatorLoadAnimator);
            return a(arrayList);
        } catch (Exception e2) {
            Log.w("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i), e2);
            return null;
        }
    }

    public static jl0 a(List<Animator> list) {
        jl0 jl0Var = new jl0();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(jl0Var, list.get(i));
        }
        return jl0Var;
    }

    public static void a(jl0 jl0Var, Animator animator) {
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            jl0Var.a(objectAnimator.getPropertyName(), kl0.a((ValueAnimator) objectAnimator));
        } else {
            throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
        }
    }
}
