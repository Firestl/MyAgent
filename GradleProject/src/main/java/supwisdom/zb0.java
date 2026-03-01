package supwisdom;

import androidx.annotation.RecentlyNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class zb0 {
    public static int b = 31;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9989a = 1;

    @RecentlyNonNull
    public zb0 a(Object obj) {
        this.f9989a = (b * this.f9989a) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    @RecentlyNonNull
    public final zb0 a(boolean z) {
        this.f9989a = (b * this.f9989a) + (z ? 1 : 0);
        return this;
    }

    public int a() {
        return this.f9989a;
    }
}
