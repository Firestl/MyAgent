package supwisdom;

import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.common.reedsolomon.ReedSolomonException;
import com.taobao.weex.el.parse.Operators;
import com.tencent.rtmp.TXLiveConstants;

/* JADX INFO: loaded from: classes.dex */
public final class dw {
    public static final int[] g = {3808, 476, TXLiveConstants.PLAY_WARNING_VIDEO_DISCONTINUITY, 1799};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f7387a;
    public boolean b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7388e;
    public int f;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f7389a;
        public final int b;

        public a(int i, int i2) {
            this.f7389a = i;
            this.b = i2;
        }

        public int a() {
            return this.f7389a;
        }

        public int b() {
            return this.b;
        }

        public yv c() {
            return new yv(a(), b());
        }

        public String toString() {
            return Operators.L + this.f7389a + ' ' + this.b + '>';
        }
    }

    public dw(fw fwVar) {
        this.f7387a = fwVar;
    }

    public static yv[] a(yv[] yvVarArr, float f, float f2) {
        float f3 = f2 / (f * 2.0f);
        float fA = yvVarArr[0].a() - yvVarArr[2].a();
        float fB = yvVarArr[0].b() - yvVarArr[2].b();
        float fA2 = (yvVarArr[0].a() + yvVarArr[2].a()) / 2.0f;
        float fB2 = (yvVarArr[0].b() + yvVarArr[2].b()) / 2.0f;
        float f4 = fA * f3;
        float f5 = fB * f3;
        yv yvVar = new yv(fA2 + f4, fB2 + f5);
        yv yvVar2 = new yv(fA2 - f4, fB2 - f5);
        float fA3 = yvVarArr[1].a() - yvVarArr[3].a();
        float fB3 = yvVarArr[1].b() - yvVarArr[3].b();
        float fA4 = (yvVarArr[1].a() + yvVarArr[3].a()) / 2.0f;
        float fB4 = (yvVarArr[1].b() + yvVarArr[3].b()) / 2.0f;
        float f6 = fA3 * f3;
        float f7 = f3 * fB3;
        return new yv[]{yvVar, new yv(fA4 + f6, fB4 + f7), yvVar2, new yv(fA4 - f6, fB4 - f7)};
    }

    public static float b(a aVar, a aVar2) {
        return ow.a(aVar.a(), aVar.b(), aVar2.a(), aVar2.b());
    }

    public final a b() {
        yv yvVarC;
        yv yvVar;
        yv yvVar2;
        yv yvVar3;
        yv yvVarC2;
        yv yvVarC3;
        yv yvVarC4;
        yv yvVarC5;
        try {
            yv[] yvVarArrA = new pw(this.f7387a).a();
            yvVar2 = yvVarArrA[0];
            yvVar3 = yvVarArrA[1];
            yvVar = yvVarArrA[2];
            yvVarC = yvVarArrA[3];
        } catch (NotFoundException unused) {
            int iE = this.f7387a.e() / 2;
            int iC = this.f7387a.c() / 2;
            int i = iE + 7;
            int i2 = iC - 7;
            yv yvVarC6 = a(new a(i, i2), false, 1, -1).c();
            int i3 = iC + 7;
            yv yvVarC7 = a(new a(i, i3), false, 1, 1).c();
            int i4 = iE - 7;
            yv yvVarC8 = a(new a(i4, i3), false, -1, 1).c();
            yvVarC = a(new a(i4, i2), false, -1, -1).c();
            yvVar = yvVarC8;
            yvVar2 = yvVarC6;
            yvVar3 = yvVarC7;
        }
        int iA = ow.a((((yvVar2.a() + yvVarC.a()) + yvVar3.a()) + yvVar.a()) / 4.0f);
        int iA2 = ow.a((((yvVar2.b() + yvVarC.b()) + yvVar3.b()) + yvVar.b()) / 4.0f);
        try {
            yv[] yvVarArrA2 = new pw(this.f7387a, 15, iA, iA2).a();
            yvVarC2 = yvVarArrA2[0];
            yvVarC3 = yvVarArrA2[1];
            yvVarC4 = yvVarArrA2[2];
            yvVarC5 = yvVarArrA2[3];
        } catch (NotFoundException unused2) {
            int i5 = iA + 7;
            int i6 = iA2 - 7;
            yvVarC2 = a(new a(i5, i6), false, 1, -1).c();
            int i7 = iA2 + 7;
            yvVarC3 = a(new a(i5, i7), false, 1, 1).c();
            int i8 = iA - 7;
            yvVarC4 = a(new a(i8, i7), false, -1, 1).c();
            yvVarC5 = a(new a(i8, i6), false, -1, -1).c();
        }
        return new a(ow.a((((yvVarC2.a() + yvVarC5.a()) + yvVarC3.a()) + yvVarC4.a()) / 4.0f), ow.a((((yvVarC2.b() + yvVarC5.b()) + yvVarC3.b()) + yvVarC4.b()) / 4.0f));
    }

    public final void a(yv[] yvVarArr) throws NotFoundException {
        long j;
        long j2;
        if (a(yvVarArr[0]) && a(yvVarArr[1]) && a(yvVarArr[2]) && a(yvVarArr[3])) {
            int i = this.f7388e * 2;
            int[] iArr = {a(yvVarArr[0], yvVarArr[1], i), a(yvVarArr[1], yvVarArr[2], i), a(yvVarArr[2], yvVarArr[3], i), a(yvVarArr[3], yvVarArr[0], i)};
            this.f = a(iArr, i);
            long j3 = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                int i3 = iArr[(this.f + i2) % 4];
                if (this.b) {
                    j = j3 << 7;
                    j2 = (i3 >> 1) & 127;
                } else {
                    j = j3 << 10;
                    j2 = ((i3 >> 2) & 992) + ((i3 >> 1) & 31);
                }
                j3 = j + j2;
            }
            int iA = a(j3, this.b);
            if (this.b) {
                this.c = (iA >> 6) + 1;
                this.d = (iA & 63) + 1;
                return;
            } else {
                this.c = (iA >> 11) + 1;
                this.d = (iA & 2047) + 1;
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final yv[] b(yv[] yvVarArr) {
        return a(yvVarArr, this.f7388e * 2, a());
    }

    public final yv[] a(a aVar) throws NotFoundException {
        this.f7388e = 1;
        a aVar2 = aVar;
        a aVar3 = aVar2;
        a aVar4 = aVar3;
        a aVar5 = aVar4;
        boolean z = true;
        while (this.f7388e < 9) {
            a aVarA = a(aVar5, z, 1, -1);
            a aVarA2 = a(aVar4, z, 1, 1);
            a aVarA3 = a(aVar3, z, -1, 1);
            a aVarA4 = a(aVar2, z, -1, -1);
            if (this.f7388e > 2) {
                double dB = (b(aVarA4, aVarA) * this.f7388e) / (b(aVar2, aVar5) * (this.f7388e + 2));
                if (dB < 0.75d || dB > 1.25d || !a(aVarA, aVarA2, aVarA3, aVarA4)) {
                    break;
                }
            }
            z = !z;
            this.f7388e++;
            aVar2 = aVarA4;
            aVar5 = aVarA;
            aVar4 = aVarA2;
            aVar3 = aVarA3;
        }
        int i = this.f7388e;
        if (i != 5 && i != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.b = i == 5;
        return a(new yv[]{new yv(aVar5.a() + 0.5f, aVar5.b() - 0.5f), new yv(aVar4.a() + 0.5f, aVar4.b() + 0.5f), new yv(aVar3.a() - 0.5f, aVar3.b() + 0.5f), new yv(aVar2.a() - 0.5f, aVar2.b() - 0.5f)}, r1 - 3, this.f7388e * 2);
    }

    public final int a(a aVar, a aVar2) {
        float fB = b(aVar, aVar2);
        float fA = (aVar2.a() - aVar.a()) / fB;
        float fB2 = (aVar2.b() - aVar.b()) / fB;
        float fA2 = aVar.a();
        float fB3 = aVar.b();
        boolean zB = this.f7387a.b(aVar.a(), aVar.b());
        int i = 0;
        for (int i2 = 0; i2 < fB; i2++) {
            fA2 += fA;
            fB3 += fB2;
            if (this.f7387a.b(ow.a(fA2), ow.a(fB3)) != zB) {
                i++;
            }
        }
        float f = i / fB;
        if (f <= 0.1f || f >= 0.9f) {
            return (f <= 0.1f) == zB ? 1 : -1;
        }
        return 0;
    }

    public static int a(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new sw(qw.k).a(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public final int a() {
        if (this.b) {
            return (this.c * 4) + 11;
        }
        int i = this.c;
        return i <= 4 ? (i * 4) + 15 : (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }

    public final a a(a aVar, boolean z, int i, int i2) {
        int iA = aVar.a() + i;
        int iB = aVar.b();
        while (true) {
            iB += i2;
            if (!a(iA, iB) || this.f7387a.b(iA, iB) != z) {
                break;
            }
            iA += i;
        }
        int i3 = iA - i;
        int i4 = iB - i2;
        while (a(i3, i4) && this.f7387a.b(i3, i4) == z) {
            i3 += i;
        }
        int i5 = i3 - i;
        while (a(i5, i4) && this.f7387a.b(i5, i4) == z) {
            i4 += i2;
        }
        return new a(i5, i4 - i2);
    }

    public static int a(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + ((i3 >> (i - 2)) << 1) + (i3 & 1);
        }
        int i4 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i5 = 0; i5 < 4; i5++) {
            if (Integer.bitCount(g[i5] ^ i4) <= 2) {
                return i5;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final boolean a(int i, int i2) {
        return i >= 0 && i < this.f7387a.e() && i2 > 0 && i2 < this.f7387a.c();
    }

    public final boolean a(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a(aVar.a() - 3, aVar.b() + 3);
        a aVar6 = new a(aVar2.a() - 3, aVar2.b() - 3);
        a aVar7 = new a(aVar3.a() + 3, aVar3.b() - 3);
        a aVar8 = new a(aVar4.a() + 3, aVar4.b() + 3);
        int iA = a(aVar8, aVar5);
        return iA != 0 && a(aVar5, aVar6) == iA && a(aVar6, aVar7) == iA && a(aVar7, aVar8) == iA;
    }

    public final fw a(fw fwVar, yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4) throws NotFoundException {
        lw lwVarA = lw.a();
        int iA = a();
        float f = iA / 2.0f;
        float f2 = this.f7388e;
        float f3 = f - f2;
        float f4 = f + f2;
        return lwVarA.a(fwVar, iA, iA, f3, f3, f4, f3, f4, f4, f3, f4, yvVar.a(), yvVar.b(), yvVar2.a(), yvVar2.b(), yvVar3.a(), yvVar3.b(), yvVar4.a(), yvVar4.b());
    }

    public final int a(yv yvVar, yv yvVar2, int i) {
        float fA = a(yvVar, yvVar2);
        float f = fA / i;
        float fA2 = yvVar.a();
        float fB = yvVar.b();
        float fA3 = ((yvVar2.a() - yvVar.a()) * f) / fA;
        float fB2 = (f * (yvVar2.b() - yvVar.b())) / fA;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f2 = i3;
            if (this.f7387a.b(ow.a((f2 * fA3) + fA2), ow.a((f2 * fB2) + fB))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    public aw a(boolean z) throws NotFoundException {
        yv[] yvVarArrA = a(b());
        if (z) {
            yv yvVar = yvVarArrA[0];
            yvVarArrA[0] = yvVarArrA[2];
            yvVarArrA[2] = yvVar;
        }
        a(yvVarArrA);
        fw fwVar = this.f7387a;
        int i = this.f;
        return new aw(a(fwVar, yvVarArrA[i % 4], yvVarArrA[(i + 1) % 4], yvVarArrA[(i + 2) % 4], yvVarArrA[(i + 3) % 4]), b(yvVarArrA), this.b, this.d, this.c);
    }

    public static float a(yv yvVar, yv yvVar2) {
        return ow.a(yvVar.a(), yvVar.b(), yvVar2.a(), yvVar2.b());
    }

    public final boolean a(yv yvVar) {
        return a(ow.a(yvVar.a()), ow.a(yvVar.b()));
    }
}
