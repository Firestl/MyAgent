package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import com.google.zxing.oned.CodaBarReader;
import com.taobao.weex.utils.FunctionParser;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ex extends ox {
    public static final char[] d = CodaBarReader.ALPHABET_STRING.toCharArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f7538e = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    public static final char[] f = {FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f7539a = new StringBuilder(20);
    public int[] b = new int[80];
    public int c = 0;

    public static boolean a(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int b(int i) {
        int i2 = i + 7;
        if (i2 >= this.c) {
            return -1;
        }
        int[] iArr = this.b;
        int i3 = Integer.MAX_VALUE;
        int i4 = 0;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        for (int i7 = i; i7 < i2; i7 += 2) {
            int i8 = iArr[i7];
            if (i8 < i5) {
                i5 = i8;
            }
            if (i8 > i6) {
                i6 = i8;
            }
        }
        int i9 = (i5 + i6) / 2;
        int i10 = 0;
        for (int i11 = i + 1; i11 < i2; i11 += 2) {
            int i12 = iArr[i11];
            if (i12 < i3) {
                i3 = i12;
            }
            if (i12 > i10) {
                i10 = i12;
            }
        }
        int i13 = (i3 + i10) / 2;
        int i14 = 128;
        int i15 = 0;
        for (int i16 = 0; i16 < 7; i16++) {
            i14 >>= 1;
            if (iArr[i + i16] > ((i16 & 1) == 0 ? i9 : i13)) {
                i15 |= i14;
            }
        }
        while (true) {
            int[] iArr2 = f7538e;
            if (i4 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i4] == i15) {
                return i4;
            }
            i4++;
        }
    }

    public void c(int i) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.f7539a.length() - 1;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = f7538e[this.f7539a.charAt(i4)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) * 2);
                iArr[i7] = iArr[i7] + this.b[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i8 = 0; i8 < 2; i8++) {
            fArr2[i8] = 0.0f;
            int i9 = i8 + 2;
            fArr2[i9] = ((iArr[i8] / iArr2[i8]) + (iArr[i9] / iArr2[i9])) / 2.0f;
            fArr[i8] = fArr2[i9];
            fArr[i9] = ((iArr[i9] * 2.0f) + 1.5f) / iArr2[i9];
        }
        loop3: while (true) {
            int i10 = f7538e[this.f7539a.charAt(i2)];
            for (int i11 = 6; i11 >= 0; i11--) {
                int i12 = (i11 & 1) + ((i10 & 1) * 2);
                float f2 = this.b[i + i11];
                if (f2 < fArr2[i12] || f2 > fArr[i12]) {
                    break loop3;
                }
                i10 >>= 1;
            }
            if (i2 >= length) {
                return;
            }
            i += 8;
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final void a(int i) {
        int[] iArr = this.b;
        int i2 = this.c;
        iArr[i2] = i;
        int i3 = i2 + 1;
        this.c = i3;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.b = iArr2;
        }
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.b, 0);
        a(ewVar);
        int iA = a();
        this.f7539a.setLength(0);
        int i2 = iA;
        do {
            int iB = b(i2);
            if (iB != -1) {
                this.f7539a.append((char) iB);
                i2 += 8;
                if (this.f7539a.length() > 1 && a(f, d[iB])) {
                    break;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } while (i2 < this.c);
        int i3 = i2 - 1;
        int i4 = this.b[i3];
        int i5 = 0;
        for (int i6 = -8; i6 < -1; i6++) {
            i5 += this.b[i2 + i6];
        }
        if (i2 < this.c && i4 < i5 / 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        c(iA);
        for (int i7 = 0; i7 < this.f7539a.length(); i7++) {
            StringBuilder sb = this.f7539a;
            sb.setCharAt(i7, d[sb.charAt(i7)]);
        }
        char cCharAt = this.f7539a.charAt(0);
        char[] cArr = f;
        if (a(cArr, cCharAt)) {
            StringBuilder sb2 = this.f7539a;
            if (a(cArr, sb2.charAt(sb2.length() - 1))) {
                if (this.f7539a.length() > 3) {
                    if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
                        StringBuilder sb3 = this.f7539a;
                        sb3.deleteCharAt(sb3.length() - 1);
                        this.f7539a.deleteCharAt(0);
                    }
                    int i8 = 0;
                    for (int i9 = 0; i9 < iA; i9++) {
                        i8 += this.b[i9];
                    }
                    float f2 = i8;
                    while (iA < i3) {
                        i8 += this.b[iA];
                        iA++;
                    }
                    float f3 = i;
                    return new xv(this.f7539a.toString(), null, new yv[]{new yv(f2, f3), new yv(i8, f3)}, BarcodeFormat.CODABAR);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final int a() throws NotFoundException {
        for (int i = 1; i < this.c; i += 2) {
            int iB = b(i);
            if (iB != -1 && a(f, d[iB])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.b[i3];
                }
                if (i == 1 || this.b[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final void a(ew ewVar) throws NotFoundException {
        int i = 0;
        this.c = 0;
        int iC = ewVar.c(0);
        int iC2 = ewVar.c();
        if (iC < iC2) {
            boolean z = true;
            while (iC < iC2) {
                if (ewVar.a(iC) ^ z) {
                    i++;
                } else {
                    a(i);
                    z = !z;
                    i = 1;
                }
                iC++;
            }
            a(i);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
