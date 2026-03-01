package supwisdom;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: DashManifest.java */
/* JADX INFO: loaded from: classes.dex */
public class ca0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f7168a;
    public final long b;
    public final boolean c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f7169e;
    public final long f;
    public final la0 g;
    public final Uri h;
    public final List<ea0> i;

    public ca0(long j, long j2, long j3, boolean z, long j4, long j5, long j6, la0 la0Var, Uri uri, List<ea0> list) {
        this.f7168a = j;
        this.b = j2;
        this.c = z;
        this.d = j4;
        this.f7169e = j5;
        this.f = j6;
        this.g = la0Var;
        this.h = uri;
        this.i = list == null ? Collections.emptyList() : list;
    }

    public final int a() {
        return this.i.size();
    }

    public final long b(int i) {
        if (i != this.i.size() - 1) {
            return this.i.get(i + 1).b - this.i.get(i).b;
        }
        long j = this.b;
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return j - this.i.get(i).b;
    }

    public final long c(int i) {
        return b20.b(b(i));
    }

    public final ea0 a(int i) {
        return this.i.get(i);
    }
}
