package supwisdom;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: compiled from: Id3Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class l50 implements m50 {
    public static final int b = x80.g("ID3");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f8250a;

    /* JADX INFO: compiled from: Id3Decoder.java */
    public interface a {
        boolean a(int i, int i2, int i3, int i4, int i5);
    }

    /* JADX INFO: compiled from: Id3Decoder.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8251a;
        public final boolean b;
        public final int c;

        public b(int i, boolean z, int i2) {
            this.f8251a = i;
            this.b = z;
            this.c = i2;
        }
    }

    public l50() {
        this(null);
    }

    public static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "ISO-8859-1" : "UTF-8" : "UTF-16BE" : "UTF-16";
    }

    public static int b(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    public static com.google.android.exoplayer2.f.b.k b(o80 o80Var, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int iG = o80Var.g();
        String strA = a(iG);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        o80Var.a(bArr, 0, i2);
        int iA = a(bArr, 0, iG);
        String str = new String(bArr, 0, iA, strA);
        int iB = iA + b(iG);
        return new com.google.android.exoplayer2.f.b.k("WXXX", str, iB < i2 ? new String(bArr, iB, b(bArr, iB) - iB, "ISO-8859-1") : "");
    }

    public static com.google.android.exoplayer2.f.b.i c(o80 o80Var, int i) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        o80Var.a(bArr, 0, i);
        int iB = b(bArr, 0);
        String str = new String(bArr, 0, iB, "ISO-8859-1");
        int i2 = iB + 1;
        return new com.google.android.exoplayer2.f.b.i(str, i2 < i ? Arrays.copyOfRange(bArr, i2, i) : new byte[0]);
    }

    public static com.google.android.exoplayer2.f.b.f d(o80 o80Var, int i) throws UnsupportedEncodingException {
        int iG = o80Var.g();
        String strA = a(iG);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        o80Var.a(bArr, 0, i2);
        int iB = b(bArr, 0);
        String str = new String(bArr, 0, iB, "ISO-8859-1");
        int i3 = iB + 1;
        int iA = a(bArr, i3, iG);
        String str2 = new String(bArr, i3, iA - i3, strA);
        int iB2 = iA + b(iG);
        int iA2 = a(bArr, iB2, iG);
        return new com.google.android.exoplayer2.f.b.f(str, str2, new String(bArr, iB2, iA2 - iB2, strA), Arrays.copyOfRange(bArr, iA2 + b(iG), i2));
    }

    public static com.google.android.exoplayer2.f.b.e e(o80 o80Var, int i) throws UnsupportedEncodingException {
        if (i < 4) {
            return null;
        }
        int iG = o80Var.g();
        String strA = a(iG);
        byte[] bArr = new byte[3];
        o80Var.a(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i2 = i - 4;
        byte[] bArr2 = new byte[i2];
        o80Var.a(bArr2, 0, i2);
        int iA = a(bArr2, 0, iG);
        String str2 = new String(bArr2, 0, iA, strA);
        int iB = iA + b(iG);
        return new com.google.android.exoplayer2.f.b.e(str, str2, iB < i2 ? new String(bArr2, iB, a(bArr2, iB, iG) - iB, strA) : "");
    }

    public static int f(o80 o80Var, int i) {
        byte[] bArr = o80Var.f8644a;
        int iD = o80Var.d();
        while (true) {
            int i2 = iD + 1;
            if (i2 >= i) {
                return i;
            }
            if ((bArr[iD] & 255) == 255 && bArr[i2] == 0) {
                System.arraycopy(bArr, iD + 2, bArr, i2, (i - iD) - 2);
                i--;
            }
            iD = i2;
        }
    }

    @Override // supwisdom.m50
    public com.google.android.exoplayer2.f.a a(p50 p50Var) {
        ByteBuffer byteBuffer = p50Var.c;
        return a(byteBuffer.array(), byteBuffer.limit());
    }

    public l50(a aVar) {
        this.f8250a = aVar;
    }

    public com.google.android.exoplayer2.f.a a(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        o80 o80Var = new o80(bArr, i);
        b bVarA = a(o80Var);
        if (bVarA == null) {
            return null;
        }
        int iD = o80Var.d();
        int i2 = bVarA.f8251a == 2 ? 6 : 10;
        int iF = bVarA.c;
        if (bVarA.b) {
            iF = f(o80Var, bVarA.c);
        }
        o80Var.b(iD + iF);
        boolean z = false;
        if (!a(o80Var, bVarA.f8251a, i2, false)) {
            if (bVarA.f8251a != 4 || !a(o80Var, 4, i2, true)) {
                Log.w("Id3Decoder", "Failed to validate ID3 tag with majorVersion=" + bVarA.f8251a);
                return null;
            }
            z = true;
        }
        while (o80Var.b() >= i2) {
            com.google.android.exoplayer2.f.b.h hVarA = a(bVarA.f8251a, o80Var, z, i2, this.f8250a);
            if (hVarA != null) {
                arrayList.add(hVarA);
            }
        }
        return new com.google.android.exoplayer2.f.a(arrayList);
    }

    public static com.google.android.exoplayer2.f.b.b c(o80 o80Var, int i, String str) {
        byte[] bArr = new byte[i];
        o80Var.a(bArr, 0, i);
        return new com.google.android.exoplayer2.f.b.b(str, bArr);
    }

    public static com.google.android.exoplayer2.f.b.k b(o80 o80Var, int i, String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        o80Var.a(bArr, 0, i);
        return new com.google.android.exoplayer2.f.b.k(str, null, new String(bArr, 0, b(bArr, 0), "ISO-8859-1"));
    }

    public static com.google.android.exoplayer2.f.b.d b(o80 o80Var, int i, int i2, boolean z, int i3, a aVar) throws UnsupportedEncodingException {
        int iD = o80Var.d();
        int iB = b(o80Var.f8644a, iD);
        String str = new String(o80Var.f8644a, iD, iB - iD, "ISO-8859-1");
        o80Var.c(iB + 1);
        int iG = o80Var.g();
        boolean z2 = (iG & 2) != 0;
        boolean z3 = (iG & 1) != 0;
        int iG2 = o80Var.g();
        String[] strArr = new String[iG2];
        for (int i4 = 0; i4 < iG2; i4++) {
            int iD2 = o80Var.d();
            int iB2 = b(o80Var.f8644a, iD2);
            strArr[i4] = new String(o80Var.f8644a, iD2, iB2 - iD2, "ISO-8859-1");
            o80Var.c(iB2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i5 = iD + i;
        while (o80Var.d() < i5) {
            com.google.android.exoplayer2.f.b.h hVarA = a(i2, o80Var, z, i3, aVar);
            if (hVarA != null) {
                arrayList.add(hVarA);
            }
        }
        com.google.android.exoplayer2.f.b.h[] hVarArr = new com.google.android.exoplayer2.f.b.h[arrayList.size()];
        arrayList.toArray(hVarArr);
        return new com.google.android.exoplayer2.f.b.d(str, z2, z3, strArr, hVarArr);
    }

    public static b a(o80 o80Var) {
        if (o80Var.b() < 10) {
            Log.w("Id3Decoder", "Data too short to be an ID3 tag");
            return null;
        }
        int iK = o80Var.k();
        if (iK != b) {
            Log.w("Id3Decoder", "Unexpected first three bytes of ID3 tag header: " + iK);
            return null;
        }
        int iG = o80Var.g();
        o80Var.d(1);
        int iG2 = o80Var.g();
        int iS = o80Var.s();
        if (iG == 2) {
            if ((iG2 & 64) != 0) {
                Log.w("Id3Decoder", "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (iG == 3) {
            if ((iG2 & 64) != 0) {
                int iN = o80Var.n();
                o80Var.d(iN);
                iS -= iN + 4;
            }
        } else {
            if (iG != 4) {
                Log.w("Id3Decoder", "Skipped ID3 tag with unsupported majorVersion=" + iG);
                return null;
            }
            if ((iG2 & 64) != 0) {
                int iS2 = o80Var.s();
                o80Var.d(iS2 - 4);
                iS -= iS2;
            }
            if ((iG2 & 16) != 0) {
                iS -= 10;
            }
        }
        return new b(iG, iG < 4 && (iG2 & 128) != 0, iS);
    }

    public static int b(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0078 A[PHI: r3
  0x0078: PHI (r3v13 boolean) = (r3v5 boolean), (r3v16 boolean) binds: [B:39:0x0085, B:31:0x0076] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(supwisdom.o80 r18, int r19, int r20, boolean r21) {
        /*
            r1 = r18
            r0 = r19
            int r2 = r18.d()
        L8:
            int r3 = r18.b()     // Catch: java.lang.Throwable -> Lb2
            r4 = 1
            r5 = r20
            if (r3 < r5) goto Lae
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L22
            int r7 = r18.n()     // Catch: java.lang.Throwable -> Lb2
            long r8 = r18.l()     // Catch: java.lang.Throwable -> Lb2
            int r10 = r18.h()     // Catch: java.lang.Throwable -> Lb2
            goto L2c
        L22:
            int r7 = r18.k()     // Catch: java.lang.Throwable -> Lb2
            int r8 = r18.k()     // Catch: java.lang.Throwable -> Lb2
            long r8 = (long) r8
            r10 = 0
        L2c:
            r11 = 0
            if (r7 != 0) goto L3a
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L3a
            if (r10 != 0) goto L3a
            r1.c(r2)
            return r4
        L3a:
            r7 = 4
            if (r0 != r7) goto L6b
            if (r21 != 0) goto L6b
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r15 == 0) goto L4b
            r1.c(r2)
            return r6
        L4b:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 14
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 24
            long r8 = r8 >> r15
            long r8 = r8 & r11
            r11 = 21
            long r8 = r8 << r11
            long r8 = r8 | r13
        L6b:
            if (r0 != r7) goto L7a
            r3 = r10 & 64
            if (r3 == 0) goto L73
            r3 = 1
            goto L74
        L73:
            r3 = 0
        L74:
            r7 = r10 & 1
            if (r7 == 0) goto L89
        L78:
            r7 = 1
            goto L8a
        L7a:
            if (r0 != r3) goto L88
            r3 = r10 & 32
            if (r3 == 0) goto L82
            r3 = 1
            goto L83
        L82:
            r3 = 0
        L83:
            r7 = r10 & 128(0x80, float:1.8E-43)
            if (r7 == 0) goto L89
            goto L78
        L88:
            r3 = 0
        L89:
            r7 = 0
        L8a:
            if (r3 == 0) goto L8d
            goto L8e
        L8d:
            r4 = 0
        L8e:
            if (r7 == 0) goto L92
            int r4 = r4 + 4
        L92:
            long r3 = (long) r4
            int r7 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r7 >= 0) goto L9b
            r1.c(r2)
            return r6
        L9b:
            int r3 = r18.b()     // Catch: java.lang.Throwable -> Lb2
            long r3 = (long) r3
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 >= 0) goto La8
            r1.c(r2)
            return r6
        La8:
            int r3 = (int) r8
            r1.d(r3)     // Catch: java.lang.Throwable -> Lb2
            goto L8
        Lae:
            r1.c(r2)
            return r4
        Lb2:
            r0 = move-exception
            r1.c(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.l50.a(supwisdom.o80, int, int, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01da A[Catch: all -> 0x012e, UnsupportedEncodingException -> 0x0208, TryCatch #0 {UnsupportedEncodingException -> 0x0208, blocks: (B:91:0x011c, B:153:0x01e4, B:93:0x0124, B:102:0x013d, B:104:0x0145, B:112:0x015f, B:121:0x0177, B:132:0x0192, B:139:0x01a3, B:145:0x01b2, B:150:0x01ca, B:151:0x01da), top: B:163:0x0112, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.f.b.h a(int r19, supwisdom.o80 r20, boolean r21, int r22, supwisdom.l50.a r23) {
        /*
            Method dump skipped, instruction units count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.l50.a(int, supwisdom.o80, boolean, int, supwisdom.l50$a):com.google.android.exoplayer2.f.b.h");
    }

    public static com.google.android.exoplayer2.f.b.j a(o80 o80Var, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int iG = o80Var.g();
        String strA = a(iG);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        o80Var.a(bArr, 0, i2);
        int iA = a(bArr, 0, iG);
        String str = new String(bArr, 0, iA, strA);
        int iB = iA + b(iG);
        return new com.google.android.exoplayer2.f.b.j("TXXX", str, iB < i2 ? new String(bArr, iB, a(bArr, iB, iG) - iB, strA) : "");
    }

    public static com.google.android.exoplayer2.f.b.j a(o80 o80Var, int i, String str) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int iG = o80Var.g();
        String strA = a(iG);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        o80Var.a(bArr, 0, i2);
        return new com.google.android.exoplayer2.f.b.j(str, null, new String(bArr, 0, a(bArr, 0, iG), strA));
    }

    public static com.google.android.exoplayer2.f.b.a a(o80 o80Var, int i, int i2) throws UnsupportedEncodingException {
        int iB;
        String str;
        int iG = o80Var.g();
        String strA = a(iG);
        int i3 = i - 1;
        byte[] bArr = new byte[i3];
        o80Var.a(bArr, 0, i3);
        if (i2 == 2) {
            str = "image/" + x80.d(new String(bArr, 0, 3, "ISO-8859-1"));
            if (str.equals("image/jpg")) {
                str = "image/jpeg";
            }
            iB = 2;
        } else {
            iB = b(bArr, 0);
            String strD = x80.d(new String(bArr, 0, iB, "ISO-8859-1"));
            if (strD.indexOf(47) == -1) {
                str = "image/" + strD;
            } else {
                str = strD;
            }
        }
        int i4 = bArr[iB + 1] & 255;
        int i5 = iB + 2;
        int iA = a(bArr, i5, iG);
        return new com.google.android.exoplayer2.f.b.a(str, new String(bArr, i5, iA - i5, strA), i4, Arrays.copyOfRange(bArr, iA + b(iG), i3));
    }

    public static com.google.android.exoplayer2.f.b.c a(o80 o80Var, int i, int i2, boolean z, int i3, a aVar) throws UnsupportedEncodingException {
        int iD = o80Var.d();
        int iB = b(o80Var.f8644a, iD);
        String str = new String(o80Var.f8644a, iD, iB - iD, "ISO-8859-1");
        o80Var.c(iB + 1);
        int iN = o80Var.n();
        int iN2 = o80Var.n();
        long jL = o80Var.l();
        long j = jL == 4294967295L ? -1L : jL;
        long jL2 = o80Var.l();
        long j2 = jL2 == 4294967295L ? -1L : jL2;
        ArrayList arrayList = new ArrayList();
        int i4 = iD + i;
        while (o80Var.d() < i4) {
            com.google.android.exoplayer2.f.b.h hVarA = a(i2, o80Var, z, i3, aVar);
            if (hVarA != null) {
                arrayList.add(hVarA);
            }
        }
        com.google.android.exoplayer2.f.b.h[] hVarArr = new com.google.android.exoplayer2.f.b.h[arrayList.size()];
        arrayList.toArray(hVarArr);
        return new com.google.android.exoplayer2.f.b.c(str, iN, iN2, j, j2, hVarArr);
    }

    public static String a(int i, int i2, int i3, int i4, int i5) {
        return i == 2 ? String.format(Locale.US, "%c%c%c", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)) : String.format(Locale.US, "%c%c%c%c", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
    }

    public static int a(byte[] bArr, int i, int i2) {
        int iB = b(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return iB;
        }
        while (iB < bArr.length - 1) {
            if (iB % 2 == 0 && bArr[iB + 1] == 0) {
                return iB;
            }
            iB = b(bArr, iB + 1);
        }
        return bArr.length;
    }
}
