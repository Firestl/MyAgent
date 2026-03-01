package supwisdom;

import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes.dex */
public class h0 {
    public int b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f7785a = new byte[64];
    public byte[] c = (byte[]) g0.f7673a.clone();
    public int d = 0;

    public final void a() {
        byte[] bArr = new byte[64];
        for (int i = 0; i < 64; i += 64) {
            System.arraycopy(this.f7785a, i, bArr, 0, 64);
            a(bArr);
        }
        this.b = 0;
    }

    public final void a(byte[] bArr) {
        int[] iArrB = g0.b(this.c);
        int[] iArrB2 = g0.b(bArr);
        int i = iArrB[0];
        int i2 = iArrB[1];
        int i3 = iArrB[2];
        int i4 = iArrB[3];
        int iA = iArrB[4];
        int i5 = iArrB[5];
        int i6 = iArrB[6];
        int i7 = iArrB[7];
        int[] iArr = new int[68];
        int i8 = 64;
        int[] iArr2 = new int[64];
        for (int i9 = 0; i9 < iArrB2.length; i9++) {
            iArr[i9] = iArrB2[i9];
        }
        int i10 = 16;
        for (int i11 = 68; i10 < i11; i11 = 68) {
            int iA2 = (iArr[i10 - 16] ^ iArr[i10 - 9]) ^ g0.a(iArr[i10 - 3], 15);
            iArr[i10] = (((g0.a(iA2, 15) ^ iA2) ^ g0.a(iA2, 23)) ^ g0.a(iArr[i10 - 13], 7)) ^ iArr[i10 - 6];
            i10++;
        }
        for (int i12 = 0; i12 < 64; i12++) {
            iArr2[i12] = iArr[i12] ^ iArr[i12 + 4];
        }
        int[][] iArr3 = {iArr, iArr2};
        int[] iArr4 = iArr3[0];
        int[] iArr5 = iArr3[1];
        int i13 = 0;
        while (i13 < i8) {
            int iA3 = g0.a(g0.a(g0.b[i13], i13) + g0.a(i, 12) + iA, 7);
            int iA4 = iArr5[i13] + ((i13 < 0 || i13 > 15) ? (i & i2) | (i & i3) | (i2 & i3) : (i ^ i2) ^ i3) + i4 + (g0.a(i, 12) ^ iA3);
            int i14 = ((i13 < 0 || i13 > 15) ? (iA & i5) | ((~iA) & i6) : (iA ^ i5) ^ i6) + i7 + iA3 + iArr4[i13];
            int iA5 = g0.a(i2, 9);
            int iA6 = g0.a(i5, 19);
            i13++;
            i5 = iA;
            i7 = i6;
            iA = (g0.a(i14, 9) ^ i14) ^ g0.a(i14, 17);
            i6 = iA6;
            i8 = 64;
            i2 = i;
            i = iA4;
            i4 = i3;
            i3 = iA5;
        }
        int[] iArr6 = {i ^ iArrB[0], iArrB[1] ^ i2, iArrB[2] ^ i3, iArrB[3] ^ i4, iArrB[4] ^ iA, iArrB[5] ^ i5, i6 ^ iArrB[6], i7 ^ iArrB[7]};
        byte[] bArr2 = new byte[32];
        for (int i15 = 0; i15 < 8; i15++) {
            System.arraycopy(g0.a(iArr6[i15]), 0, bArr2, i15 * 4, 4);
        }
        byte[] bArr3 = this.c;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
        this.d++;
    }

    public static String a(String str) {
        int i;
        byte[] bArr = new byte[32];
        byte[] bytes = str.getBytes();
        h0 h0Var = new h0();
        int length = bytes.length;
        int i2 = h0Var.b;
        int i3 = 64 - i2;
        if (i3 < length) {
            System.arraycopy(bytes, 0, h0Var.f7785a, i2, i3);
            length -= i3;
            i = i3 + 0;
            while (true) {
                h0Var.a();
                if (length <= 64) {
                    break;
                }
                System.arraycopy(bytes, i, h0Var.f7785a, 0, 64);
                length -= 64;
                i += 64;
            }
        } else {
            i = 0;
        }
        System.arraycopy(bytes, i, h0Var.f7785a, h0Var.b, length);
        int i4 = h0Var.b + length;
        h0Var.b = i4;
        byte[] bArr2 = new byte[64];
        byte[] bArr3 = new byte[i4];
        System.arraycopy(h0Var.f7785a, 0, bArr3, 0, i4);
        byte[] bArrA = g0.a(bArr3, h0Var.d);
        for (int i5 = 0; i5 < bArrA.length; i5 += 64) {
            System.arraycopy(bArrA, i5, bArr2, 0, 64);
            h0Var.a(bArr2);
        }
        byte[] bArr4 = h0Var.c;
        System.arraycopy(bArr4, 0, bArr, 0, bArr4.length);
        return new String(Hex.encode(bArr));
    }
}
