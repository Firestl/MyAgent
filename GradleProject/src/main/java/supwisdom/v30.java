package supwisdom;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: compiled from: VorbisUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class v30 {

    /* JADX INFO: compiled from: VorbisUtil.java */
    public static final class a {
        public a(int i, int i2, long[] jArr, int i3, boolean z) {
        }
    }

    /* JADX INFO: compiled from: VorbisUtil.java */
    public static final class b {
        public b(String str, String[] strArr, int i) {
        }
    }

    /* JADX INFO: compiled from: VorbisUtil.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f9475a;

        public c(boolean z, int i, int i2, int i3) {
            this.f9475a = z;
        }
    }

    /* JADX INFO: compiled from: VorbisUtil.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9476a;
        public final long b;
        public final int c;
        public final int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f9477e;
        public final byte[] f;

        public d(long j, int i, long j2, int i2, int i3, int i4, int i5, int i6, boolean z, byte[] bArr) {
            this.f9476a = i;
            this.b = j2;
            this.c = i3;
            this.d = i5;
            this.f9477e = i6;
            this.f = bArr;
        }
    }

    public static int a(int i) {
        int i2 = 0;
        while (i > 0) {
            i2++;
            i >>>= 1;
        }
        return i2;
    }

    public static d a(o80 o80Var) throws com.google.android.exoplayer2.n {
        a(1, o80Var, false);
        long jM = o80Var.m();
        int iG = o80Var.g();
        long jM2 = o80Var.m();
        int iO = o80Var.o();
        int iO2 = o80Var.o();
        int iO3 = o80Var.o();
        int iG2 = o80Var.g();
        return new d(jM, iG, jM2, iO, iO2, iO3, (int) Math.pow(2.0d, iG2 & 15), (int) Math.pow(2.0d, (iG2 & 240) >> 4), (o80Var.g() & 1) > 0, Arrays.copyOf(o80Var.f8644a, o80Var.c()));
    }

    public static b b(o80 o80Var) throws com.google.android.exoplayer2.n {
        a(3, o80Var, false);
        String strE = o80Var.e((int) o80Var.m());
        int length = 11 + strE.length();
        long jM = o80Var.m();
        String[] strArr = new String[(int) jM];
        int length2 = length + 4;
        for (int i = 0; i < jM; i++) {
            strArr[i] = o80Var.e((int) o80Var.m());
            length2 = length2 + 4 + strArr[i].length();
        }
        if ((o80Var.g() & 1) != 0) {
            return new b(strE, strArr, length2 + 1);
        }
        throw new com.google.android.exoplayer2.n("framing bit expected to be set");
    }

    public static void c(t30 t30Var) throws com.google.android.exoplayer2.n {
        int iA = t30Var.a(6) + 1;
        for (int i = 0; i < iA; i++) {
            int iA2 = t30Var.a(16);
            if (iA2 == 0) {
                t30Var.b(8);
                t30Var.b(16);
                t30Var.b(16);
                t30Var.b(6);
                t30Var.b(8);
                int iA3 = t30Var.a(4) + 1;
                for (int i2 = 0; i2 < iA3; i2++) {
                    t30Var.b(8);
                }
            } else {
                if (iA2 != 1) {
                    throw new com.google.android.exoplayer2.n("floor type greater than 1 not decodable: " + iA2);
                }
                int iA4 = t30Var.a(5);
                int i3 = -1;
                int[] iArr = new int[iA4];
                for (int i4 = 0; i4 < iA4; i4++) {
                    iArr[i4] = t30Var.a(4);
                    if (iArr[i4] > i3) {
                        i3 = iArr[i4];
                    }
                }
                int i5 = i3 + 1;
                int[] iArr2 = new int[i5];
                for (int i6 = 0; i6 < i5; i6++) {
                    iArr2[i6] = t30Var.a(3) + 1;
                    int iA5 = t30Var.a(2);
                    if (iA5 > 0) {
                        t30Var.b(8);
                    }
                    for (int i7 = 0; i7 < (1 << iA5); i7++) {
                        t30Var.b(8);
                    }
                }
                t30Var.b(2);
                int iA6 = t30Var.a(4);
                int i8 = 0;
                int i9 = 0;
                for (int i10 = 0; i10 < iA4; i10++) {
                    i8 += iArr2[iArr[i10]];
                    while (i9 < i8) {
                        t30Var.b(iA6);
                        i9++;
                    }
                }
            }
        }
    }

    public static a d(t30 t30Var) throws com.google.android.exoplayer2.n {
        if (t30Var.a(24) != 5653314) {
            throw new com.google.android.exoplayer2.n("expected code book to start with [0x56, 0x43, 0x42] at " + t30Var.b());
        }
        int iA = t30Var.a(16);
        int iA2 = t30Var.a(24);
        long[] jArr = new long[iA2];
        boolean zA = t30Var.a();
        long jA = 0;
        if (zA) {
            int iA3 = t30Var.a(5) + 1;
            int i = 0;
            while (i < iA2) {
                int iA4 = t30Var.a(a(iA2 - i));
                for (int i2 = 0; i2 < iA4 && i < iA2; i2++) {
                    jArr[i] = iA3;
                    i++;
                }
                iA3++;
            }
        } else {
            boolean zA2 = t30Var.a();
            for (int i3 = 0; i3 < iA2; i3++) {
                if (!zA2) {
                    jArr[i3] = t30Var.a(5) + 1;
                } else if (t30Var.a()) {
                    jArr[i3] = t30Var.a(5) + 1;
                } else {
                    jArr[i3] = 0;
                }
            }
        }
        int iA5 = t30Var.a(4);
        if (iA5 > 2) {
            throw new com.google.android.exoplayer2.n("lookup type greater than 2 not decodable: " + iA5);
        }
        if (iA5 == 1 || iA5 == 2) {
            t30Var.b(32);
            t30Var.b(32);
            int iA6 = t30Var.a(4) + 1;
            t30Var.b(1);
            if (iA5 != 1) {
                jA = iA2 * iA;
            } else if (iA != 0) {
                jA = a(iA2, iA);
            }
            t30Var.b((int) (jA * ((long) iA6)));
        }
        return new a(iA, iA2, jArr, iA5, zA);
    }

    public static void b(t30 t30Var) throws com.google.android.exoplayer2.n {
        int iA = t30Var.a(6) + 1;
        for (int i = 0; i < iA; i++) {
            if (t30Var.a(16) <= 2) {
                t30Var.b(24);
                t30Var.b(24);
                t30Var.b(24);
                int iA2 = t30Var.a(6) + 1;
                t30Var.b(8);
                int[] iArr = new int[iA2];
                for (int i2 = 0; i2 < iA2; i2++) {
                    iArr[i2] = ((t30Var.a() ? t30Var.a(5) : 0) * 8) + t30Var.a(3);
                }
                for (int i3 = 0; i3 < iA2; i3++) {
                    for (int i4 = 0; i4 < 8; i4++) {
                        if ((iArr[i3] & (1 << i4)) != 0) {
                            t30Var.b(8);
                        }
                    }
                }
            } else {
                throw new com.google.android.exoplayer2.n("residueType greater than 2 is not decodable");
            }
        }
    }

    public static boolean a(int i, o80 o80Var, boolean z) throws com.google.android.exoplayer2.n {
        if (o80Var.b() < 7) {
            if (z) {
                return false;
            }
            throw new com.google.android.exoplayer2.n("too short header: " + o80Var.b());
        }
        if (o80Var.g() != i) {
            if (z) {
                return false;
            }
            throw new com.google.android.exoplayer2.n("expected header type " + Integer.toHexString(i));
        }
        if (o80Var.g() == 118 && o80Var.g() == 111 && o80Var.g() == 114 && o80Var.g() == 98 && o80Var.g() == 105 && o80Var.g() == 115) {
            return true;
        }
        if (z) {
            return false;
        }
        throw new com.google.android.exoplayer2.n("expected characters 'vorbis'");
    }

    public static c[] a(o80 o80Var, int i) throws com.google.android.exoplayer2.n {
        a(5, o80Var, false);
        int iG = o80Var.g() + 1;
        t30 t30Var = new t30(o80Var.f8644a);
        t30Var.b(o80Var.d() * 8);
        for (int i2 = 0; i2 < iG; i2++) {
            d(t30Var);
        }
        int iA = t30Var.a(6) + 1;
        for (int i3 = 0; i3 < iA; i3++) {
            if (t30Var.a(16) != 0) {
                throw new com.google.android.exoplayer2.n("placeholder of time domain transforms not zeroed out");
            }
        }
        c(t30Var);
        b(t30Var);
        a(i, t30Var);
        c[] cVarArrA = a(t30Var);
        if (t30Var.a()) {
            return cVarArrA;
        }
        throw new com.google.android.exoplayer2.n("framing bit after modes not set as expected");
    }

    public static c[] a(t30 t30Var) {
        int iA = t30Var.a(6) + 1;
        c[] cVarArr = new c[iA];
        for (int i = 0; i < iA; i++) {
            cVarArr[i] = new c(t30Var.a(), t30Var.a(16), t30Var.a(16), t30Var.a(8));
        }
        return cVarArr;
    }

    public static void a(int i, t30 t30Var) throws com.google.android.exoplayer2.n {
        int iA = t30Var.a(6) + 1;
        for (int i2 = 0; i2 < iA; i2++) {
            int iA2 = t30Var.a(16);
            if (iA2 != 0) {
                Log.e("VorbisUtil", "mapping type other than 0 not supported: " + iA2);
            } else {
                int iA3 = t30Var.a() ? t30Var.a(4) + 1 : 1;
                if (t30Var.a()) {
                    int iA4 = t30Var.a(8) + 1;
                    for (int i3 = 0; i3 < iA4; i3++) {
                        int i4 = i - 1;
                        t30Var.b(a(i4));
                        t30Var.b(a(i4));
                    }
                }
                if (t30Var.a(2) != 0) {
                    throw new com.google.android.exoplayer2.n("to reserved bits must be zero after mapping coupling steps");
                }
                if (iA3 > 1) {
                    for (int i5 = 0; i5 < i; i5++) {
                        t30Var.b(4);
                    }
                }
                for (int i6 = 0; i6 < iA3; i6++) {
                    t30Var.b(8);
                    t30Var.b(8);
                    t30Var.b(8);
                }
            }
        }
    }

    public static long a(long j, long j2) {
        return (long) Math.floor(Math.pow(j, 1.0d / j2));
    }
}
