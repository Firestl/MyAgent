package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.facebook.imageutils.JfifUtil;
import com.google.zxing.maxicode.decoder.DecodedBitStreamParser;
import com.google.zxing.oned.Code39Reader;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import java.util.Map;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;

/* JADX INFO: loaded from: classes.dex */
public final class gx extends ox {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f7780e;
    public static final int f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7781a;
    public final boolean b;
    public final StringBuilder c;
    public final int[] d;

    static {
        int[] iArr = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, com.igexin.push.config.c.G, 400, JfifUtil.MARKER_RST0, 133, 388, 196, Code39Reader.ASTERISK_ENCODING, HashUtils.SECURE_HASH_ALGORITHM_KECCAK_128_RATE, 162, 138, 42};
        f7780e = iArr;
        f = iArr[39];
    }

    public gx() {
        this(false);
    }

    public static String a(CharSequence charSequence) throws FormatException {
        int i;
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt == '+' || cCharAt == '$' || cCharAt == '%' || cCharAt == '/') {
                i2++;
                char cCharAt2 = charSequence.charAt(i2);
                if (cCharAt != '$') {
                    if (cCharAt != '%') {
                        if (cCharAt != '+') {
                            if (cCharAt != '/') {
                                c = 0;
                            } else if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                                i = cCharAt2 - ' ';
                            } else {
                                if (cCharAt2 != 'Z') {
                                    throw FormatException.getFormatInstance();
                                }
                                c = Operators.CONDITION_IF_MIDDLE;
                            }
                            sb.append(c);
                        } else {
                            if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            i = cCharAt2 + ' ';
                        }
                    } else if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                        i = cCharAt2 - '&';
                    } else {
                        if (cCharAt2 < 'F' || cCharAt2 > 'W') {
                            throw FormatException.getFormatInstance();
                        }
                        i = cCharAt2 + DecodedBitStreamParser.TWOSHIFTA;
                    }
                } else {
                    if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                        throw FormatException.getFormatInstance();
                    }
                    i = cCharAt2 - '@';
                }
                c = (char) i;
                sb.append(c);
            } else {
                sb.append(cCharAt);
            }
            i2++;
        }
        return sb.toString();
    }

    public gx(boolean z) {
        this(z, false);
    }

    public gx(boolean z, boolean z2) {
        this.f7781a = z;
        this.b = z2;
        this.c = new StringBuilder(20);
        this.d = new int[9];
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        String string;
        int[] iArr = this.d;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.c;
        sb.setLength(0);
        int iB = ewVar.b(a(ewVar, iArr)[1]);
        int iC = ewVar.c();
        while (true) {
            ox.a(ewVar, iB, iArr);
            int iA = a(iArr);
            if (iA >= 0) {
                char cA = a(iA);
                sb.append(cA);
                int i2 = iB;
                for (int i3 : iArr) {
                    i2 += i3;
                }
                int iB2 = ewVar.b(i2);
                if (cA == '*') {
                    sb.setLength(sb.length() - 1);
                    int i4 = 0;
                    for (int i5 : iArr) {
                        i4 += i5;
                    }
                    int i6 = (iB2 - iB) - i4;
                    if (iB2 != iC && i6 * 2 < i4) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    if (this.f7781a) {
                        int length = sb.length() - 1;
                        int iIndexOf = 0;
                        for (int i7 = 0; i7 < length; i7++) {
                            iIndexOf += Code39Reader.ALPHABET_STRING.indexOf(this.c.charAt(i7));
                        }
                        if (sb.charAt(length) == Code39Reader.ALPHABET_STRING.charAt(iIndexOf % 43)) {
                            sb.setLength(length);
                        } else {
                            throw ChecksumException.getChecksumInstance();
                        }
                    }
                    if (sb.length() != 0) {
                        if (this.b) {
                            string = a(sb);
                        } else {
                            string = sb.toString();
                        }
                        float f2 = i;
                        return new xv(string, null, new yv[]{new yv((r2[1] + r2[0]) / 2.0f, f2), new yv(iB + (i4 / 2.0f), f2)}, BarcodeFormat.CODE_39);
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                iB = iB2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public static int[] a(ew ewVar, int[] iArr) throws NotFoundException {
        int iC = ewVar.c();
        int iB = ewVar.b(0);
        int length = iArr.length;
        int i = iB;
        boolean z = false;
        int i2 = 0;
        while (iB < iC) {
            if (ewVar.a(iB) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                int i3 = length - 1;
                if (i2 != i3) {
                    i2++;
                } else {
                    if (a(iArr) == f && ewVar.a(Math.max(0, i - ((iB - i) / 2)), i, false)) {
                        return new int[]{i, iB};
                    }
                    i += iArr[0] + iArr[1];
                    int i4 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i4);
                    iArr[i4] = 0;
                    iArr[i3] = 0;
                    i2--;
                }
                iArr[i2] = 1;
                z = !z;
            }
            iB++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static char a(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int[] iArr = f7780e;
            if (i2 < iArr.length) {
                if (iArr[i2] == i) {
                    return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".charAt(i2);
                }
                i2++;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public static int a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            for (int i3 : iArr) {
                if (i3 < i2 && i3 > i) {
                    i2 = i3;
                }
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i6 |= 1 << ((length - 1) - i7);
                    i4++;
                    i5 += i8;
                }
            }
            if (i4 == 3) {
                for (int i9 = 0; i9 < length && i4 > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i4--;
                        if (i10 * 2 >= i5) {
                            return -1;
                        }
                    }
                }
                return i6;
            }
            if (i4 <= 3) {
                return -1;
            }
            i = i2;
        }
    }
}
