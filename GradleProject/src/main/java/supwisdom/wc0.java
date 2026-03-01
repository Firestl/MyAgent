package supwisdom;

import androidx.annotation.RecentlyNonNull;
import supwisdom.mc0;
import supwisdom.mc0.d;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class wc0<O extends mc0.d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9612a;
    public final mc0<O> b;
    public final O c;
    public final String d;

    public wc0(mc0<O> mc0Var, O o, String str) {
        this.b = mc0Var;
        this.c = o;
        this.d = str;
        this.f9612a = of0.a(mc0Var, o, str);
    }

    @RecentlyNonNull
    public static <O extends mc0.d> wc0<O> a(@RecentlyNonNull mc0<O> mc0Var, O o, String str) {
        return new wc0<>(mc0Var, o, str);
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof wc0)) {
            return false;
        }
        wc0 wc0Var = (wc0) obj;
        return of0.a(this.b, wc0Var.b) && of0.a(this.c, wc0Var.c) && of0.a(this.d, wc0Var.d);
    }

    public final int hashCode() {
        return this.f9612a;
    }

    @RecentlyNonNull
    public final String a() {
        return this.b.b();
    }
}
