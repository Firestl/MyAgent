package supwisdom;

import com.dcloud.zxing2.FormatException;

/* JADX INFO: loaded from: classes.dex */
public final class rz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f9100a;
    public yz b;
    public wz c;
    public boolean d;

    public rz(fw fwVar) throws FormatException {
        int iC = fwVar.c();
        if (iC < 21 || (iC & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.f9100a = fwVar;
    }

    public final int a(int i, int i2, int i3) {
        return this.d ? this.f9100a.b(i2, i) : this.f9100a.b(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    public byte[] b() throws FormatException {
        wz wzVarC = c();
        yz yzVarD = d();
        tz tzVarA = tz.a(wzVarC.a());
        int iC = this.f9100a.c();
        tzVarA.a(this.f9100a, iC);
        fw fwVarA = yzVarD.a();
        byte[] bArr = new byte[yzVarD.d()];
        int i = iC - 1;
        boolean z = true;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i2 == 6) {
                i2--;
            }
            for (int i6 = 0; i6 < iC; i6++) {
                int i7 = z ? i - i6 : i6;
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i2 - i8;
                    if (!fwVarA.b(i9, i7)) {
                        i5++;
                        i4 <<= 1;
                        if (this.f9100a.b(i9, i7)) {
                            i4 |= 1;
                        }
                        if (i5 == 8) {
                            bArr[i3] = (byte) i4;
                            i3++;
                            i4 = 0;
                            i5 = 0;
                        }
                    }
                }
            }
            z = !z;
            i2 -= 2;
        }
        if (i3 == yzVarD.d()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    public wz c() throws FormatException {
        wz wzVar = this.c;
        if (wzVar != null) {
            return wzVar;
        }
        int iA = 0;
        int iA2 = 0;
        for (int i = 0; i < 6; i++) {
            iA2 = a(i, 8, iA2);
        }
        int iA3 = a(8, 7, a(8, 8, a(7, 8, iA2)));
        for (int i2 = 5; i2 >= 0; i2--) {
            iA3 = a(8, i2, iA3);
        }
        int iC = this.f9100a.c();
        int i3 = iC - 7;
        for (int i4 = iC - 1; i4 >= i3; i4--) {
            iA = a(8, i4, iA);
        }
        for (int i5 = iC - 8; i5 < iC; i5++) {
            iA = a(i5, 8, iA);
        }
        wz wzVarA = wz.a(iA3, iA);
        this.c = wzVarA;
        if (wzVarA != null) {
            return wzVarA;
        }
        throw FormatException.getFormatInstance();
    }

    public yz d() throws FormatException {
        yz yzVar = this.b;
        if (yzVar != null) {
            return yzVar;
        }
        int iC = this.f9100a.c();
        int i = (iC - 17) / 4;
        if (i <= 6) {
            return yz.c(i);
        }
        int i2 = iC - 11;
        int iA = 0;
        int iA2 = 0;
        for (int i3 = 5; i3 >= 0; i3--) {
            for (int i4 = iC - 9; i4 >= i2; i4--) {
                iA2 = a(i4, i3, iA2);
            }
        }
        yz yzVarA = yz.a(iA2);
        if (yzVarA != null && yzVarA.c() == iC) {
            this.b = yzVarA;
            return yzVarA;
        }
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = iC - 9; i6 >= i2; i6--) {
                iA = a(i5, i6, iA);
            }
        }
        yz yzVarA2 = yz.a(iA);
        if (yzVarA2 == null || yzVarA2.c() != iC) {
            throw FormatException.getFormatInstance();
        }
        this.b = yzVarA2;
        return yzVarA2;
    }

    public void e() {
        wz wzVar = this.c;
        if (wzVar == null) {
            return;
        }
        tz.a(wzVar.a()).a(this.f9100a, this.f9100a.c());
    }

    public void a() {
        int i = 0;
        while (i < this.f9100a.e()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f9100a.c(); i3++) {
                if (this.f9100a.b(i, i3) != this.f9100a.b(i3, i)) {
                    this.f9100a.a(i3, i);
                    this.f9100a.a(i, i3);
                }
            }
            i = i2;
        }
    }

    public void a(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }
}
