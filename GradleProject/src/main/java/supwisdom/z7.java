package supwisdom;

import android.content.LocusId;
import android.os.Build;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: LocusIdCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class z7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9972a;
    public final LocusId b;

    /* JADX INFO: compiled from: LocusIdCompat.java */
    public static class a {
        public static LocusId a(String str) {
            return new LocusId(str);
        }
    }

    public z7(String str) {
        na.a(str, (Object) "id cannot be empty");
        this.f9972a = str;
        if (Build.VERSION.SDK_INT >= 29) {
            this.b = a.a(str);
        } else {
            this.b = null;
        }
    }

    public String a() {
        return this.f9972a;
    }

    public final String b() {
        return this.f9972a.length() + "_chars";
    }

    public LocusId c() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || z7.class != obj.getClass()) {
            return false;
        }
        z7 z7Var = (z7) obj;
        String str = this.f9972a;
        return str == null ? z7Var.f9972a == null : str.equals(z7Var.f9972a);
    }

    public int hashCode() {
        String str = this.f9972a;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "LocusIdCompat[" + b() + Operators.ARRAY_END_STR;
    }
}
