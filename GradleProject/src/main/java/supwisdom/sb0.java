package supwisdom;

import android.net.Uri;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: SsManifest.java */
/* JADX INFO: loaded from: classes.dex */
public class sb0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9144a;
    public final a b;
    public final b[] c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f9145e;

    /* JADX INFO: compiled from: SsManifest.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final UUID f9146a;
        public final byte[] b;

        public a(UUID uuid, byte[] bArr) {
            this.f9146a = uuid;
            this.b = bArr;
        }
    }

    /* JADX INFO: compiled from: SsManifest.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9147a;
        public final long b;
        public final com.google.android.exoplayer2.j[] c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f9148e;
        public final String f;
        public final List<Long> g;
        public final long[] h;
        public final long i;

        public b(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, com.google.android.exoplayer2.j[] jVarArr, List<Long> list, long j2) {
            this.f9148e = str;
            this.f = str2;
            this.f9147a = i;
            this.b = j;
            this.c = jVarArr;
            this.d = list.size();
            this.g = list;
            this.i = x80.a(j2, 1000000L, j);
            this.h = x80.a(list, 1000000L, j);
        }

        public int a(long j) {
            return x80.a(this.h, j, true, true);
        }

        public long b(int i) {
            if (i == this.d - 1) {
                return this.i;
            }
            long[] jArr = this.h;
            return jArr[i + 1] - jArr[i];
        }

        public long a(int i) {
            return this.h[i];
        }

        public Uri a(int i, int i2) {
            e80.b(this.c != null);
            e80.b(this.g != null);
            e80.b(i2 < this.g.size());
            String string = Integer.toString(this.c[i].b);
            String string2 = this.g.get(i2).toString();
            return w80.a(this.f9148e, this.f.replace("{bitrate}", string).replace("{Bitrate}", string).replace("{start time}", string2).replace("{start_time}", string2));
        }
    }

    public sb0(int i, int i2, long j, long j2, long j3, int i3, boolean z, a aVar, b[] bVarArr) {
        this.f9144a = z;
        this.b = aVar;
        this.c = bVarArr;
        this.f9145e = j3 == 0 ? -9223372036854775807L : x80.a(j3, 1000000L, j);
        this.d = j2 != 0 ? x80.a(j2, 1000000L, j) : -9223372036854775807L;
    }
}
