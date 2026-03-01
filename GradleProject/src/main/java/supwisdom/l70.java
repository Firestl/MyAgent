package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: TrackSelectionArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class l70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8261a;
    public final k70[] b;
    public int c;

    public l70(k70... k70VarArr) {
        this.b = k70VarArr;
        this.f8261a = k70VarArr.length;
    }

    public k70 a(int i) {
        return this.b[i];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l70.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.b, ((l70) obj).b);
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = 527 + Arrays.hashCode(this.b);
        }
        return this.c;
    }

    public k70[] a() {
        return (k70[]) this.b.clone();
    }
}
