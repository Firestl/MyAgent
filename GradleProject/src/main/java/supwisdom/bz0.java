package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class bz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final az0<?>[] f7128a;
    public final a61 b;

    public bz0(az0<?>[] az0VarArr) {
        this.f7128a = (az0[]) az0VarArr.clone();
        this.b = new a61(az0VarArr.length);
        for (int i = 0; i < az0VarArr.length; i++) {
            this.b.a(i, az0VarArr[i].b);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof bz0) && Arrays.equals(((bz0) obj).f7128a, this.f7128a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f7128a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.f7128a.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.f7128a[i]);
        }
        return sb.toString();
    }
}
