package supwisdom;

import android.util.Log;
import android.util.Pair;
import androidtranscoder.format.MediaFormatExtraConstants;
import com.google.android.exoplayer2.f.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import supwisdom.b30;
import supwisdom.y20;

/* JADX INFO: compiled from: AtomParsers.java */
/* JADX INFO: loaded from: classes.dex */
public final class z20 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f9956a = x80.g("vide");
    public static final int b = x80.g("soun");
    public static final int c = x80.g("text");
    public static final int d = x80.g("sbtl");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f9957e = x80.g("subt");
    public static final int f = x80.g("clcp");
    public static final int g = x80.g("cenc");
    public static final int h = x80.g("meta");

    /* JADX INFO: compiled from: AtomParsers.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9958a;
        public int b;
        public int c;
        public long d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final boolean f9959e;
        public final o80 f;
        public final o80 g;
        public int h;
        public int i;

        public a(o80 o80Var, o80 o80Var2, boolean z) {
            this.g = o80Var;
            this.f = o80Var2;
            this.f9959e = z;
            o80Var2.c(12);
            this.f9958a = o80Var2.t();
            o80Var.c(12);
            this.i = o80Var.t();
            e80.b(o80Var.n() == 1, "first_chunk must be 1");
            this.b = -1;
        }

        public boolean a() {
            int i = this.b + 1;
            this.b = i;
            if (i == this.f9958a) {
                return false;
            }
            this.d = this.f9959e ? this.f.v() : this.f.l();
            if (this.b == this.h) {
                this.c = this.g.t();
                this.g.d(4);
                int i2 = this.i - 1;
                this.i = i2;
                this.h = i2 > 0 ? this.g.t() - 1 : -1;
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: AtomParsers.java */
    public interface b {
        int a();

        int b();

        boolean c();
    }

    /* JADX INFO: compiled from: AtomParsers.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final i30[] f9960a;
        public com.google.android.exoplayer2.j b;
        public int c;
        public int d = 0;

        public c(int i) {
            this.f9960a = new i30[i];
        }
    }

    /* JADX INFO: compiled from: AtomParsers.java */
    public static final class d implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9961a;
        public final int b;
        public final o80 c;

        public d(y20.b bVar) {
            o80 o80Var = bVar.P0;
            this.c = o80Var;
            o80Var.c(12);
            this.f9961a = this.c.t();
            this.b = this.c.t();
        }

        @Override // supwisdom.z20.b
        public int a() {
            return this.b;
        }

        @Override // supwisdom.z20.b
        public int b() {
            int i = this.f9961a;
            return i == 0 ? this.c.t() : i;
        }

        @Override // supwisdom.z20.b
        public boolean c() {
            return this.f9961a != 0;
        }
    }

    /* JADX INFO: compiled from: AtomParsers.java */
    public static final class e implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o80 f9962a;
        public final int b;
        public final int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9963e;

        public e(y20.b bVar) {
            o80 o80Var = bVar.P0;
            this.f9962a = o80Var;
            o80Var.c(12);
            this.c = this.f9962a.t() & 255;
            this.b = this.f9962a.t();
        }

        @Override // supwisdom.z20.b
        public int a() {
            return this.b;
        }

        @Override // supwisdom.z20.b
        public int b() {
            int i = this.c;
            if (i == 8) {
                return this.f9962a.g();
            }
            if (i == 16) {
                return this.f9962a.h();
            }
            int i2 = this.d;
            this.d = i2 + 1;
            if (i2 % 2 != 0) {
                return this.f9963e & 15;
            }
            int iG = this.f9962a.g();
            this.f9963e = iG;
            return (iG & 240) >> 4;
        }

        @Override // supwisdom.z20.b
        public boolean c() {
            return false;
        }
    }

    /* JADX INFO: compiled from: AtomParsers.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9964a;
        public final long b;
        public final int c;

        public f(int i, long j, int i2) {
            this.f9964a = i;
            this.b = j;
            this.c = i2;
        }
    }

    public static h30 a(y20.a aVar, y20.b bVar, long j, com.google.android.exoplayer2.c.a aVar2, boolean z) throws com.google.android.exoplayer2.n {
        y20.b bVar2;
        long j2;
        y20.a aVarE = aVar.e(y20.F);
        int iC = c(aVarE.d(y20.T).P0);
        if (iC == -1) {
            return null;
        }
        f fVarB = b(aVar.d(y20.P).P0);
        if (j == -9223372036854775807L) {
            bVar2 = bVar;
            j2 = fVarB.b;
        } else {
            bVar2 = bVar;
            j2 = j;
        }
        long jA = a(bVar2.P0);
        long jA2 = j2 != -9223372036854775807L ? x80.a(j2, 1000000L, jA) : -9223372036854775807L;
        y20.a aVarE2 = aVarE.e(y20.G).e(y20.H);
        Pair<Long, String> pairD = d(aVarE.d(y20.S).P0);
        c cVarA = a(aVarE2.d(y20.U).P0, fVarB.f9964a, fVarB.c, (String) pairD.second, aVar2, z);
        Pair<long[], long[]> pairA = a(aVar.e(y20.Q));
        if (cVarA.b == null) {
            return null;
        }
        return new h30(fVarB.f9964a, iC, ((Long) pairD.first).longValue(), jA, jA2, cVarA.b, cVarA.d, cVarA.f9960a, cVarA.c, (long[]) pairA.first, (long[]) pairA.second);
    }

    public static com.google.android.exoplayer2.f.a b(o80 o80Var, int i) {
        o80Var.d(8);
        ArrayList arrayList = new ArrayList();
        while (o80Var.d() < i) {
            a.InterfaceC0053a interfaceC0053aA = d30.a(o80Var);
            if (interfaceC0053aA != null) {
                arrayList.add(interfaceC0053aA);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new com.google.android.exoplayer2.f.a(arrayList);
    }

    public static int c(o80 o80Var) {
        o80Var.c(16);
        int iN = o80Var.n();
        if (iN == b) {
            return 1;
        }
        if (iN == f9956a) {
            return 2;
        }
        if (iN == c || iN == d || iN == f9957e || iN == f) {
            return 3;
        }
        return iN == h ? 4 : -1;
    }

    public static Pair<Long, String> d(o80 o80Var) {
        o80Var.c(8);
        int iA = y20.a(o80Var.n());
        o80Var.d(iA == 0 ? 8 : 16);
        long jL = o80Var.l();
        o80Var.d(iA == 0 ? 4 : 8);
        int iH = o80Var.h();
        return Pair.create(Long.valueOf(jL), "" + ((char) (((iH >> 10) & 31) + 96)) + ((char) (((iH >> 5) & 31) + 96)) + ((char) ((iH & 31) + 96)));
    }

    public static int e(o80 o80Var) {
        int iG = o80Var.g();
        int i = iG & 127;
        while ((iG & 128) == 128) {
            iG = o80Var.g();
            i = (i << 7) | (iG & 127);
        }
        return i;
    }

    public static f b(o80 o80Var) {
        boolean z;
        o80Var.c(8);
        int iA = y20.a(o80Var.n());
        o80Var.d(iA == 0 ? 8 : 16);
        int iN = o80Var.n();
        o80Var.d(4);
        int iD = o80Var.d();
        int i = iA == 0 ? 4 : 8;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            }
            if (o80Var.f8644a[iD + i3] != -1) {
                z = false;
                break;
            }
            i3++;
        }
        long j = -9223372036854775807L;
        if (z) {
            o80Var.d(i);
        } else {
            long jL = iA == 0 ? o80Var.l() : o80Var.v();
            if (jL != 0) {
                j = jL;
            }
        }
        o80Var.d(16);
        int iN2 = o80Var.n();
        int iN3 = o80Var.n();
        o80Var.d(4);
        int iN4 = o80Var.n();
        int iN5 = o80Var.n();
        if (iN2 == 0 && iN3 == 65536 && iN4 == -65536 && iN5 == 0) {
            i2 = 90;
        } else if (iN2 == 0 && iN3 == -65536 && iN4 == 65536 && iN5 == 0) {
            i2 = 270;
        } else if (iN2 == -65536 && iN3 == 0 && iN4 == 0 && iN5 == -65536) {
            i2 = 180;
        }
        return new f(iN, j, i2);
    }

    public static float c(o80 o80Var, int i) {
        o80Var.c(i + 8);
        return o80Var.t() / o80Var.t();
    }

    public static i30 c(o80 o80Var, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            o80Var.c(i3);
            int iN = o80Var.n();
            if (o80Var.n() == y20.Z) {
                o80Var.d(6);
                boolean z = o80Var.g() == 1;
                int iG = o80Var.g();
                byte[] bArr = new byte[16];
                o80Var.a(bArr, 0, 16);
                return new i30(z, iG, bArr);
            }
            i3 += iN;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair<java.lang.String, byte[]> d(supwisdom.o80 r3, int r4) {
        /*
            int r4 = r4 + 8
            int r4 = r4 + 4
            r3.c(r4)
            r4 = 1
            r3.d(r4)
            e(r3)
            r0 = 2
            r3.d(r0)
            int r1 = r3.g()
            r2 = r1 & 128(0x80, float:1.8E-43)
            if (r2 == 0) goto L1d
            r3.d(r0)
        L1d:
            r2 = r1 & 64
            if (r2 == 0) goto L28
            int r2 = r3.h()
            r3.d(r2)
        L28:
            r2 = 32
            r1 = r1 & r2
            if (r1 == 0) goto L30
            r3.d(r0)
        L30:
            r3.d(r4)
            e(r3)
            int r0 = r3.g()
            r1 = 0
            if (r0 == r2) goto L80
            r2 = 33
            if (r0 == r2) goto L7d
            r2 = 35
            if (r0 == r2) goto L7a
            r2 = 64
            if (r0 == r2) goto L77
            r2 = 107(0x6b, float:1.5E-43)
            if (r0 == r2) goto L70
            r2 = 165(0xa5, float:2.31E-43)
            if (r0 == r2) goto L6d
            r2 = 166(0xa6, float:2.33E-43)
            if (r0 == r2) goto L6a
            switch(r0) {
                case 102: goto L77;
                case 103: goto L77;
                case 104: goto L77;
                default: goto L58;
            }
        L58:
            switch(r0) {
                case 169: goto L63;
                case 170: goto L5c;
                case 171: goto L5c;
                case 172: goto L63;
                default: goto L5b;
            }
        L5b:
            goto L82
        L5c:
            java.lang.String r3 = "audio/vnd.dts.hd"
            android.util.Pair r3 = android.util.Pair.create(r3, r1)
            return r3
        L63:
            java.lang.String r3 = "audio/vnd.dts"
            android.util.Pair r3 = android.util.Pair.create(r3, r1)
            return r3
        L6a:
            java.lang.String r1 = "audio/eac3"
            goto L82
        L6d:
            java.lang.String r1 = "audio/ac3"
            goto L82
        L70:
            java.lang.String r3 = "audio/mpeg"
            android.util.Pair r3 = android.util.Pair.create(r3, r1)
            return r3
        L77:
            java.lang.String r1 = "audio/mp4a-latm"
            goto L82
        L7a:
            java.lang.String r1 = "video/hevc"
            goto L82
        L7d:
            java.lang.String r1 = "video/avc"
            goto L82
        L80:
            java.lang.String r1 = "video/mp4v-es"
        L82:
            r0 = 12
            r3.d(r0)
            r3.d(r4)
            int r4 = e(r3)
            byte[] r0 = new byte[r4]
            r2 = 0
            r3.a(r0, r2, r4)
            android.util.Pair r3 = android.util.Pair.create(r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.z20.d(supwisdom.o80, int):android.util.Pair");
    }

    public static k30 a(h30 h30Var, y20.a aVar, b50 b50Var) throws com.google.android.exoplayer2.n {
        b eVar;
        boolean z;
        int iT;
        int iT2;
        h30 h30Var2;
        int i;
        long[] jArr;
        int[] iArr;
        int i2;
        long[] jArr2;
        int[] iArr2;
        long j;
        long j2;
        long[] jArr3;
        long[] jArr4;
        boolean z2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        int i3;
        int i4;
        int iT3;
        int iT4;
        y20.b bVarD = aVar.d(y20.q0);
        if (bVarD != null) {
            eVar = new d(bVarD);
        } else {
            y20.b bVarD2 = aVar.d(y20.r0);
            if (bVarD2 != null) {
                eVar = new e(bVarD2);
            } else {
                throw new com.google.android.exoplayer2.n("Track has no sample table size information");
            }
        }
        int iA = eVar.a();
        if (iA == 0) {
            return new k30(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        y20.b bVarD3 = aVar.d(y20.s0);
        if (bVarD3 == null) {
            bVarD3 = aVar.d(y20.t0);
            z = true;
        } else {
            z = false;
        }
        o80 o80Var = bVarD3.P0;
        o80 o80Var2 = aVar.d(y20.p0).P0;
        o80 o80Var3 = aVar.d(y20.m0).P0;
        y20.b bVarD4 = aVar.d(y20.n0);
        o80 o80Var4 = null;
        o80 o80Var5 = bVarD4 != null ? bVarD4.P0 : null;
        y20.b bVarD5 = aVar.d(y20.o0);
        o80 o80Var6 = bVarD5 != null ? bVarD5.P0 : null;
        a aVar2 = new a(o80Var2, o80Var, z);
        o80Var3.c(12);
        int iT5 = o80Var3.t() - 1;
        int iT6 = o80Var3.t();
        int iT7 = o80Var3.t();
        if (o80Var6 != null) {
            o80Var6.c(12);
            iT = o80Var6.t();
        } else {
            iT = 0;
        }
        int iT8 = -1;
        if (o80Var5 != null) {
            o80Var5.c(12);
            iT2 = o80Var5.t();
            if (iT2 > 0) {
                iT8 = o80Var5.t() - 1;
                o80Var4 = o80Var5;
            }
        } else {
            o80Var4 = o80Var5;
            iT2 = 0;
        }
        long j3 = 0;
        if (!(eVar.c() && "audio/raw".equals(h30Var.f.f) && iT5 == 0 && iT == 0 && iT2 == 0)) {
            long[] jArr5 = new long[iA];
            iArr = new int[iA];
            jArr2 = new long[iA];
            int i5 = iT2;
            iArr2 = new int[iA];
            int iT9 = iT8;
            long j4 = 0;
            j = 0;
            int i6 = 0;
            i2 = 0;
            int iT10 = 0;
            int i7 = 0;
            int iN = 0;
            int i8 = iT;
            int i9 = iT7;
            int i10 = iT6;
            int i11 = iT5;
            int i12 = i5;
            while (i6 < iA) {
                while (i7 == 0) {
                    e80.b(aVar2.a());
                    j4 = aVar2.d;
                    i7 = aVar2.c;
                    i9 = i9;
                    i10 = i10;
                }
                int i13 = i10;
                int i14 = i9;
                if (o80Var6 != null) {
                    while (iT10 == 0 && i8 > 0) {
                        iT10 = o80Var6.t();
                        iN = o80Var6.n();
                        i8--;
                    }
                    iT10--;
                }
                int i15 = iN;
                jArr5[i6] = j4;
                iArr[i6] = eVar.b();
                if (iArr[i6] > i2) {
                    i2 = iArr[i6];
                }
                int i16 = iA;
                b bVar = eVar;
                jArr2[i6] = j + ((long) i15);
                iArr2[i6] = o80Var4 == null ? 1 : 0;
                if (i6 == iT9) {
                    iArr2[i6] = 1;
                    i12--;
                    if (i12 > 0) {
                        iT9 = o80Var4.t() - 1;
                    }
                }
                long[] jArr6 = jArr5;
                j += (long) i14;
                int i17 = i13 - 1;
                if (i17 != 0 || i11 <= 0) {
                    iT3 = i14;
                    iT4 = i17;
                } else {
                    iT4 = o80Var3.t();
                    iT3 = o80Var3.t();
                    i11--;
                }
                int i18 = iT4;
                j4 += (long) iArr[i6];
                i7--;
                i6++;
                iA = i16;
                jArr5 = jArr6;
                iT9 = iT9;
                iN = i15;
                i10 = i18;
                i9 = iT3;
                eVar = bVar;
            }
            i = iA;
            long[] jArr7 = jArr5;
            int i19 = i10;
            e80.a(iT10 == 0);
            while (i8 > 0) {
                e80.a(o80Var6.t() == 0);
                o80Var6.n();
                i8--;
            }
            if (i12 == 0 && i19 == 0) {
                i4 = i7;
                if (i4 == 0 && i11 == 0) {
                    h30Var2 = h30Var;
                }
                jArr = jArr7;
            } else {
                i4 = i7;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Inconsistent stbl box for track ");
            h30Var2 = h30Var;
            sb.append(h30Var2.f7794a);
            sb.append(": remainingSynchronizationSamples ");
            sb.append(i12);
            sb.append(", remainingSamplesAtTimestampDelta ");
            sb.append(i19);
            sb.append(", remainingSamplesInChunk ");
            sb.append(i4);
            sb.append(", remainingTimestampDeltaChanges ");
            sb.append(i11);
            Log.w("AtomParsers", sb.toString());
            jArr = jArr7;
        } else {
            h30Var2 = h30Var;
            i = iA;
            b bVar2 = eVar;
            int i20 = aVar2.f9958a;
            long[] jArr8 = new long[i20];
            int[] iArr6 = new int[i20];
            while (aVar2.a()) {
                int i21 = aVar2.b;
                jArr8[i21] = aVar2.d;
                iArr6[i21] = aVar2.c;
            }
            b30.b bVarA = b30.a(bVar2.b(), jArr8, iArr6, iT7);
            jArr = bVarA.f7013a;
            iArr = bVarA.b;
            i2 = bVarA.c;
            jArr2 = bVarA.d;
            iArr2 = bVarA.f7014e;
            j = 0;
        }
        if (h30Var2.i != null && !b50Var.a()) {
            long[] jArr9 = h30Var2.i;
            if (jArr9.length == 1 && h30Var2.b == 1 && jArr2.length >= 2) {
                long j5 = h30Var2.j[0];
                long jA = x80.a(jArr9[0], h30Var2.c, h30Var2.d) + j5;
                if (jArr2[0] <= j5 && j5 < jArr2[1] && jArr2[jArr2.length - 1] < jA && jA <= j) {
                    long j6 = j - jA;
                    long jA2 = x80.a(j5 - jArr2[0], h30Var2.f.s, h30Var2.c);
                    long jA3 = x80.a(j6, h30Var2.f.s, h30Var2.c);
                    if ((jA2 != 0 || jA3 != 0) && jA2 <= 2147483647L && jA3 <= 2147483647L) {
                        b50Var.f7024a = (int) jA2;
                        b50Var.b = (int) jA3;
                        x80.a(jArr2, 1000000L, h30Var2.c);
                        return new k30(jArr, iArr, i2, jArr2, iArr2);
                    }
                }
            }
            long[] jArr10 = h30Var2.i;
            if (jArr10.length == 1) {
                char c2 = 0;
                if (jArr10[0] == 0) {
                    int i22 = 0;
                    while (i22 < jArr2.length) {
                        jArr2[i22] = x80.a(jArr2[i22] - h30Var2.j[c2], 1000000L, h30Var2.c);
                        i22++;
                        c2 = 0;
                    }
                    return new k30(jArr, iArr, i2, jArr2, iArr2);
                }
            }
            boolean z3 = h30Var2.b == 1;
            boolean z4 = false;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            while (true) {
                long[] jArr11 = h30Var2.i;
                j2 = -1;
                if (i25 >= jArr11.length) {
                    break;
                }
                int i26 = i2;
                int[] iArr7 = iArr;
                long j7 = h30Var2.j[i25];
                if (j7 != -1) {
                    i3 = i26;
                    long jA4 = x80.a(jArr11[i25], h30Var2.c, h30Var2.d);
                    int iB = x80.b(jArr2, j7, true, true);
                    int iB2 = x80.b(jArr2, j7 + jA4, z3, false);
                    i23 += iB2 - iB;
                    z4 |= i24 != iB;
                    i24 = iB2;
                } else {
                    i3 = i26;
                }
                i25++;
                iArr = iArr7;
                i2 = i3;
            }
            int i27 = i2;
            int[] iArr8 = iArr;
            boolean z5 = z4 | (i23 != i);
            long[] jArr12 = z5 ? new long[i23] : jArr;
            int[] iArr9 = z5 ? new int[i23] : iArr8;
            int i28 = z5 ? 0 : i27;
            int[] iArr10 = z5 ? new int[i23] : iArr2;
            long[] jArr13 = new long[i23];
            int i29 = i28;
            int i30 = 0;
            int i31 = 0;
            while (true) {
                long[] jArr14 = h30Var2.i;
                if (i30 >= jArr14.length) {
                    break;
                }
                int[] iArr11 = iArr9;
                int[] iArr12 = iArr10;
                long j8 = h30Var2.j[i30];
                long j9 = jArr14[i30];
                if (j8 != j2) {
                    jArr3 = jArr12;
                    long[] jArr15 = jArr;
                    long jA5 = x80.a(j9, h30Var2.c, h30Var2.d) + j8;
                    int iB3 = x80.b(jArr2, j8, true, true);
                    int iB4 = x80.b(jArr2, jA5, z3, false);
                    if (z5) {
                        int i32 = iB4 - iB3;
                        System.arraycopy(jArr15, iB3, jArr3, i31, i32);
                        iArr4 = iArr8;
                        z2 = z3;
                        iArr3 = iArr11;
                        System.arraycopy(iArr4, iB3, iArr3, i31, i32);
                        jArr4 = jArr15;
                        iArr5 = iArr12;
                        System.arraycopy(iArr2, iB3, iArr5, i31, i32);
                    } else {
                        iArr4 = iArr8;
                        jArr4 = jArr15;
                        iArr5 = iArr12;
                        z2 = z3;
                        iArr3 = iArr11;
                    }
                    int i33 = i29;
                    while (iB3 < iB4) {
                        int[] iArr13 = iArr5;
                        int[] iArr14 = iArr4;
                        long j10 = j8;
                        jArr13[i31] = x80.a(j3, 1000000L, h30Var2.d) + x80.a(jArr2[iB3] - j8, 1000000L, h30Var2.c);
                        if (z5 && iArr3[i31] > i33) {
                            i33 = iArr14[iB3];
                        }
                        i31++;
                        iB3++;
                        iArr4 = iArr14;
                        j8 = j10;
                        iArr5 = iArr13;
                    }
                    iArr12 = iArr5;
                    iArr8 = iArr4;
                    i29 = i33;
                } else {
                    jArr3 = jArr12;
                    jArr4 = jArr;
                    z2 = z3;
                    iArr3 = iArr11;
                }
                j3 += j9;
                i30++;
                iArr9 = iArr3;
                jArr12 = jArr3;
                z3 = z2;
                iArr10 = iArr12;
                jArr = jArr4;
                j2 = -1;
            }
            long[] jArr16 = jArr12;
            int[] iArr15 = iArr9;
            int[] iArr16 = iArr10;
            boolean z6 = false;
            for (int i34 = 0; i34 < iArr16.length && !z6; i34++) {
                z6 |= (iArr16[i34] & 1) != 0;
            }
            if (z6) {
                return new k30(jArr16, iArr15, i29, jArr13, iArr16);
            }
            throw new com.google.android.exoplayer2.n("The edited sample sequence does not contain a sync sample.");
        }
        int[] iArr17 = iArr;
        x80.a(jArr2, 1000000L, h30Var2.c);
        return new k30(jArr, iArr17, i2, jArr2, iArr2);
    }

    public static Pair<Integer, i30> b(o80 o80Var, int i, int i2) {
        int i3 = i + 8;
        Integer numValueOf = null;
        i30 i30VarC = null;
        boolean z = false;
        while (i3 - i < i2) {
            o80Var.c(i3);
            int iN = o80Var.n();
            int iN2 = o80Var.n();
            if (iN2 == y20.c0) {
                numValueOf = Integer.valueOf(o80Var.n());
            } else if (iN2 == y20.X) {
                o80Var.d(4);
                z = o80Var.n() == g;
            } else if (iN2 == y20.Y) {
                i30VarC = c(o80Var, i3, iN);
            }
            i3 += iN;
        }
        if (!z) {
            return null;
        }
        e80.a(numValueOf != null, "frma atom is mandatory");
        e80.a(i30VarC != null, "schi->tenc atom is mandatory");
        return Pair.create(numValueOf, i30VarC);
    }

    public static byte[] d(o80 o80Var, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            o80Var.c(i3);
            int iN = o80Var.n();
            if (o80Var.n() == y20.J0) {
                return Arrays.copyOfRange(o80Var.f8644a, i3, iN + i3);
            }
            i3 += iN;
        }
        return null;
    }

    public static com.google.android.exoplayer2.f.a a(y20.b bVar, boolean z) {
        if (z) {
            return null;
        }
        o80 o80Var = bVar.P0;
        o80Var.c(8);
        while (o80Var.b() >= 8) {
            int iD = o80Var.d();
            int iN = o80Var.n();
            if (o80Var.n() == y20.B0) {
                o80Var.c(iD);
                return a(o80Var, iD + iN);
            }
            o80Var.d(iN - 8);
        }
        return null;
    }

    public static com.google.android.exoplayer2.f.a a(o80 o80Var, int i) {
        o80Var.d(12);
        while (o80Var.d() < i) {
            int iD = o80Var.d();
            int iN = o80Var.n();
            if (o80Var.n() == y20.C0) {
                o80Var.c(iD);
                return b(o80Var, iD + iN);
            }
            o80Var.d(iN - 8);
        }
        return null;
    }

    public static long a(o80 o80Var) {
        o80Var.c(8);
        o80Var.d(y20.a(o80Var.n()) != 0 ? 16 : 8);
        return o80Var.l();
    }

    public static c a(o80 o80Var, int i, int i2, String str, com.google.android.exoplayer2.c.a aVar, boolean z) throws com.google.android.exoplayer2.n {
        o80Var.c(12);
        int iN = o80Var.n();
        c cVar = new c(iN);
        for (int i3 = 0; i3 < iN; i3++) {
            int iD = o80Var.d();
            int iN2 = o80Var.n();
            e80.a(iN2 > 0, "childAtomSize should be positive");
            int iN3 = o80Var.n();
            if (iN3 != y20.c && iN3 != y20.d && iN3 != y20.a0 && iN3 != y20.l0 && iN3 != y20.f9839e && iN3 != y20.f && iN3 != y20.g && iN3 != y20.K0 && iN3 != y20.L0) {
                if (iN3 != y20.j && iN3 != y20.b0 && iN3 != y20.o && iN3 != y20.q && iN3 != y20.s && iN3 != y20.v && iN3 != y20.t && iN3 != y20.u && iN3 != y20.y0 && iN3 != y20.z0 && iN3 != y20.m && iN3 != y20.n && iN3 != y20.k && iN3 != y20.O0) {
                    if (iN3 != y20.k0 && iN3 != y20.u0 && iN3 != y20.v0 && iN3 != y20.w0 && iN3 != y20.x0) {
                        if (iN3 == y20.N0) {
                            cVar.b = com.google.android.exoplayer2.j.a(Integer.toString(i), "application/x-camera-motion", null, -1, aVar);
                        }
                    } else {
                        a(o80Var, iN3, iD, iN2, i, str, aVar, cVar);
                    }
                } else {
                    a(o80Var, iN3, iD, iN2, i, str, z, aVar, cVar, i3);
                }
            } else {
                a(o80Var, iN3, iD, iN2, i, i2, aVar, cVar, i3);
            }
            o80Var.c(iD + iN2);
        }
        return cVar;
    }

    public static void a(o80 o80Var, int i, int i2, int i3, int i4, String str, com.google.android.exoplayer2.c.a aVar, c cVar) throws com.google.android.exoplayer2.n {
        o80Var.c(i2 + 8 + 8);
        String str2 = "application/ttml+xml";
        List listSingletonList = null;
        long j = Long.MAX_VALUE;
        if (i != y20.k0) {
            if (i == y20.u0) {
                int i5 = (i3 - 8) - 8;
                byte[] bArr = new byte[i5];
                o80Var.a(bArr, 0, i5);
                listSingletonList = Collections.singletonList(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i == y20.v0) {
                str2 = "application/x-mp4-vtt";
            } else if (i == y20.w0) {
                j = 0;
            } else if (i == y20.x0) {
                cVar.d = 1;
                str2 = "application/x-mp4-cea-608";
            } else {
                throw new IllegalStateException();
            }
        }
        cVar.b = com.google.android.exoplayer2.j.a(Integer.toString(i4), str2, (String) null, -1, 0, str, -1, aVar, j, (List<byte[]>) listSingletonList);
    }

    public static void a(o80 o80Var, int i, int i2, int i3, int i4, int i5, com.google.android.exoplayer2.c.a aVar, c cVar, int i6) throws com.google.android.exoplayer2.n {
        int iA;
        int i7 = i2;
        o80Var.c(i7 + 8 + 8);
        o80Var.d(16);
        int iH = o80Var.h();
        int iH2 = o80Var.h();
        o80Var.d(50);
        int iD = o80Var.d();
        if (i == y20.a0) {
            iA = a(o80Var, i7, i3, cVar, i6);
            o80Var.c(iD);
        } else {
            iA = i;
        }
        String str = null;
        List<byte[]> listSingletonList = null;
        byte[] bArrD = null;
        boolean z = false;
        float fC = 1.0f;
        int i8 = -1;
        while (iD - i7 < i3) {
            o80Var.c(iD);
            int iD2 = o80Var.d();
            int iN = o80Var.n();
            if (iN == 0 && o80Var.d() - i7 == i3) {
                break;
            }
            e80.a(iN > 0, "childAtomSize should be positive");
            int iN2 = o80Var.n();
            if (iN2 == y20.I) {
                e80.b(str == null);
                o80Var.c(iD2 + 8);
                z80 z80VarA = z80.a(o80Var);
                listSingletonList = z80VarA.f9975a;
                cVar.c = z80VarA.b;
                if (!z) {
                    fC = z80VarA.f9976e;
                }
                str = "video/avc";
            } else if (iN2 == y20.J) {
                e80.b(str == null);
                o80Var.c(iD2 + 8);
                a90 a90VarA = a90.a(o80Var);
                listSingletonList = a90VarA.f6875a;
                cVar.c = a90VarA.b;
                str = "video/hevc";
            } else if (iN2 == y20.M0) {
                e80.b(str == null);
                str = iA == y20.K0 ? MediaFormatExtraConstants.MIMETYPE_VIDEO_VP8 : "video/x-vnd.on2.vp9";
            } else if (iN2 == y20.h) {
                e80.b(str == null);
                str = MediaFormatExtraConstants.MIMETYPE_VIDEO_H263;
            } else if (iN2 == y20.K) {
                e80.b(str == null);
                Pair<String, byte[]> pairD = d(o80Var, iD2);
                str = (String) pairD.first;
                listSingletonList = Collections.singletonList(pairD.second);
            } else if (iN2 == y20.j0) {
                fC = c(o80Var, iD2);
                z = true;
            } else if (iN2 == y20.I0) {
                bArrD = d(o80Var, iD2, iN);
            } else if (iN2 == y20.H0) {
                int iG = o80Var.g();
                o80Var.d(3);
                if (iG == 0) {
                    int iG2 = o80Var.g();
                    if (iG2 == 0) {
                        i8 = 0;
                    } else if (iG2 == 1) {
                        i8 = 1;
                    } else if (iG2 == 2) {
                        i8 = 2;
                    } else if (iG2 == 3) {
                        i8 = 3;
                    }
                }
            }
            iD += iN;
            i7 = i2;
        }
        if (str == null) {
            return;
        }
        cVar.b = com.google.android.exoplayer2.j.a(Integer.toString(i4), str, (String) null, -1, -1, iH, iH2, -1.0f, listSingletonList, i5, fC, bArrD, i8, (com.google.android.exoplayer2.k.b) null, aVar);
    }

    public static Pair<long[], long[]> a(y20.a aVar) {
        y20.b bVarD;
        if (aVar != null && (bVarD = aVar.d(y20.R)) != null) {
            o80 o80Var = bVarD.P0;
            o80Var.c(8);
            int iA = y20.a(o80Var.n());
            int iT = o80Var.t();
            long[] jArr = new long[iT];
            long[] jArr2 = new long[iT];
            for (int i = 0; i < iT; i++) {
                jArr[i] = iA == 1 ? o80Var.v() : o80Var.l();
                jArr2[i] = iA == 1 ? o80Var.p() : o80Var.n();
                if (o80Var.j() == 1) {
                    o80Var.d(2);
                } else {
                    throw new IllegalArgumentException("Unsupported media rate.");
                }
            }
            return Pair.create(jArr, jArr2);
        }
        return Pair.create(null, null);
    }

    public static void a(o80 o80Var, int i, int i2, int i3, int i4, String str, boolean z, com.google.android.exoplayer2.c.a aVar, c cVar, int i5) {
        int iH;
        int iRound;
        int iT;
        int iA;
        String str2;
        int i6;
        int i7;
        String str3;
        String str4;
        c cVar2;
        int i8;
        int i9 = i3;
        c cVar3 = cVar;
        o80Var.c(i2 + 8 + 8);
        if (z) {
            iH = o80Var.h();
            o80Var.d(6);
        } else {
            o80Var.d(8);
            iH = 0;
        }
        if (iH == 0 || iH == 1) {
            int iH2 = o80Var.h();
            o80Var.d(6);
            int iR = o80Var.r();
            if (iH == 1) {
                o80Var.d(16);
            }
            iRound = iR;
            iT = iH2;
        } else {
            if (iH != 2) {
                return;
            }
            o80Var.d(16);
            iRound = (int) Math.round(o80Var.w());
            iT = o80Var.t();
            o80Var.d(20);
        }
        int iD = o80Var.d();
        if (i == y20.b0) {
            iA = a(o80Var, i2, i9, cVar3, i5);
            o80Var.c(iD);
        } else {
            iA = i;
        }
        String str5 = "audio/raw";
        if (iA == y20.o) {
            str2 = "audio/ac3";
        } else if (iA == y20.q) {
            str2 = "audio/eac3";
        } else if (iA == y20.s) {
            str2 = "audio/vnd.dts";
        } else if (iA == y20.t || iA == y20.u) {
            str2 = "audio/vnd.dts.hd";
        } else if (iA == y20.v) {
            str2 = "audio/vnd.dts.hd;profile=lbr";
        } else if (iA == y20.y0) {
            str2 = "audio/3gpp";
        } else if (iA == y20.z0) {
            str2 = "audio/amr-wb";
        } else if (iA == y20.m || iA == y20.n) {
            str2 = "audio/raw";
        } else if (iA == y20.k) {
            str2 = "audio/mpeg";
        } else {
            str2 = iA == y20.O0 ? "audio/alac" : null;
        }
        int iIntValue = iT;
        int iIntValue2 = iRound;
        int i10 = iD;
        String str6 = str2;
        byte[] bArr = null;
        while (i10 - i2 < i9) {
            o80Var.c(i10);
            int iN = o80Var.n();
            e80.a(iN > 0, "childAtomSize should be positive");
            int iN2 = o80Var.n();
            if (iN2 != y20.K && (!z || iN2 != y20.l)) {
                if (iN2 == y20.p) {
                    o80Var.c(i10 + 8);
                    cVar3.b = k10.a(o80Var, Integer.toString(i4), str, aVar);
                } else if (iN2 == y20.r) {
                    o80Var.c(i10 + 8);
                    cVar3.b = k10.b(o80Var, Integer.toString(i4), str, aVar);
                } else {
                    if (iN2 == y20.w) {
                        i8 = i10;
                        str3 = str6;
                        str4 = str5;
                        cVar2 = cVar3;
                        cVar2.b = com.google.android.exoplayer2.j.a(Integer.toString(i4), str6, null, -1, -1, iIntValue, iIntValue2, null, aVar, 0, str);
                        i6 = iN;
                    } else {
                        i8 = i10;
                        str3 = str6;
                        str4 = str5;
                        cVar2 = cVar3;
                        i6 = iN;
                        if (iN2 == y20.O0) {
                            byte[] bArr2 = new byte[i6];
                            i7 = i8;
                            o80Var.c(i7);
                            o80Var.a(bArr2, 0, i6);
                            bArr = bArr2;
                        }
                    }
                    i7 = i8;
                }
                i6 = iN;
                i7 = i10;
                str3 = str6;
                str4 = str5;
                cVar2 = cVar3;
            } else {
                i6 = iN;
                i7 = i10;
                str3 = str6;
                str4 = str5;
                cVar2 = cVar3;
                int iA2 = iN2 == y20.K ? i7 : a(o80Var, i7, i6);
                if (iA2 != -1) {
                    Pair<String, byte[]> pairD = d(o80Var, iA2);
                    str6 = (String) pairD.first;
                    bArr = (byte[]) pairD.second;
                    if ("audio/mp4a-latm".equals(str6)) {
                        Pair<Integer, Integer> pairA = f80.a(bArr);
                        iIntValue2 = ((Integer) pairA.first).intValue();
                        iIntValue = ((Integer) pairA.second).intValue();
                    }
                }
                cVar3 = cVar2;
                i10 = i7 + i6;
                str5 = str4;
                i9 = i3;
            }
            str6 = str3;
            cVar3 = cVar2;
            i10 = i7 + i6;
            str5 = str4;
            i9 = i3;
        }
        String str7 = str6;
        String str8 = str5;
        c cVar4 = cVar3;
        if (cVar4.b != null || str7 == null) {
            return;
        }
        cVar4.b = com.google.android.exoplayer2.j.a(Integer.toString(i4), str7, (String) null, -1, -1, iIntValue, iIntValue2, str8.equals(str7) ? 2 : -1, (List<byte[]>) (bArr == null ? null : Collections.singletonList(bArr)), aVar, 0, str);
    }

    public static int a(o80 o80Var, int i, int i2) {
        int iD = o80Var.d();
        while (iD - i < i2) {
            o80Var.c(iD);
            int iN = o80Var.n();
            e80.a(iN > 0, "childAtomSize should be positive");
            if (o80Var.n() == y20.K) {
                return iD;
            }
            iD += iN;
        }
        return -1;
    }

    public static int a(o80 o80Var, int i, int i2, c cVar, int i3) {
        Pair<Integer, i30> pairB;
        int iD = o80Var.d();
        while (true) {
            if (iD - i >= i2) {
                return 0;
            }
            o80Var.c(iD);
            int iN = o80Var.n();
            e80.a(iN > 0, "childAtomSize should be positive");
            if (o80Var.n() == y20.W && (pairB = b(o80Var, iD, iN)) != null) {
                cVar.f9960a[i3] = (i30) pairB.second;
                return ((Integer) pairB.first).intValue();
            }
            iD += iN;
        }
    }
}
