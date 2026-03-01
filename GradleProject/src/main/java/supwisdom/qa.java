package supwisdom;

import android.os.Build;
import android.view.DisplayCutout;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: DisplayCutoutCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class qa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f8900a;

    public qa(Object obj) {
        this.f8900a = obj;
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.f8900a).getSafeInsetBottom();
        }
        return 0;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.f8900a).getSafeInsetLeft();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.f8900a).getSafeInsetRight();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 28) {
            return ((DisplayCutout) this.f8900a).getSafeInsetTop();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || qa.class != obj.getClass()) {
            return false;
        }
        return ia.a(this.f8900a, ((qa) obj).f8900a);
    }

    public int hashCode() {
        Object obj = this.f8900a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f8900a + Operators.BLOCK_END_STR;
    }

    public static qa a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new qa(obj);
    }
}
