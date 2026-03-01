package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.google.zxing.maxicode.decoder.DecodedBitStreamParser;
import com.google.zxing.oned.Code93Reader;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class hx extends ox {
    public static final char[] c = Code93Reader.ALPHABET_STRING.toCharArray();
    public static final int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f7885e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f7886a = new StringBuilder(20);
    public final int[] b = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, com.umeng.ccg.c.o, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        d = iArr;
        f7885e = iArr[47];
    }

    public static void a(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        a(charSequence, length - 2, 20);
        a(charSequence, length - 1, 15);
    }

    public static String b(CharSequence charSequence) throws FormatException {
        int i;
        char c2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt >= 'a' && cCharAt <= 'd') {
                if (i2 >= length - 1) {
                    throw FormatException.getFormatInstance();
                }
                i2++;
                char cCharAt2 = charSequence.charAt(i2);
                switch (cCharAt) {
                    case 'a':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        i = cCharAt2 - '@';
                        c2 = (char) i;
                        sb.append(c2);
                        break;
                    case 'b':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                            i = cCharAt2 - '&';
                        } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                            i = cCharAt2 + DecodedBitStreamParser.TWOSHIFTA;
                        } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                            i = cCharAt2 + 16;
                        } else if (cCharAt2 >= 'P' && cCharAt2 <= 'S') {
                            i = cCharAt2 + FunctionParser.Lexer.PLUS;
                        } else {
                            if (cCharAt2 < 'T' || cCharAt2 > 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c2 = 127;
                            sb.append(c2);
                        }
                        c2 = (char) i;
                        sb.append(c2);
                        break;
                    case 'c':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                            i = cCharAt2 - ' ';
                            c2 = (char) i;
                            sb.append(c2);
                        } else {
                            if (cCharAt2 != 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c2 = Operators.CONDITION_IF_MIDDLE;
                            sb.append(c2);
                        }
                        break;
                    case 'd':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        i = cCharAt2 + ' ';
                        c2 = (char) i;
                        sb.append(c2);
                        break;
                    default:
                        c2 = 0;
                        sb.append(c2);
                        break;
                }
            } else {
                sb.append(cCharAt);
            }
            i2++;
        }
        return sb.toString();
    }

    public static void a(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int iIndexOf = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iIndexOf += Code93Reader.ALPHABET_STRING.indexOf(charSequence.charAt(i4)) * i3;
            i3++;
            if (i3 > i2) {
                i3 = 1;
            }
        }
        if (charSequence.charAt(i) != c[iIndexOf % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    @Override // supwisdom.ox
    public xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        int iB = ewVar.b(a(ewVar)[1]);
        int iC = ewVar.c();
        int[] iArr = this.b;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f7886a;
        sb.setLength(0);
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
                    sb.deleteCharAt(sb.length() - 1);
                    int i4 = 0;
                    for (int i5 : iArr) {
                        i4 += i5;
                    }
                    if (iB2 != iC && ewVar.a(iB2)) {
                        if (sb.length() >= 2) {
                            a(sb);
                            sb.setLength(sb.length() - 2);
                            float f = i;
                            return new xv(b(sb), null, new yv[]{new yv((r14[1] + r14[0]) / 2.0f, f), new yv(iB + (i4 / 2.0f), f)}, BarcodeFormat.CODE_93);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                iB = iB2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public final int[] a(ew ewVar) throws NotFoundException {
        int iC = ewVar.c();
        int iB = ewVar.b(0);
        Arrays.fill(this.b, 0);
        int[] iArr = this.b;
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
                    if (a(iArr) == f7885e) {
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
            int[] iArr = d;
            if (i2 < iArr.length) {
                if (iArr[i2] == i) {
                    return c[i2];
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
        for (int i2 : iArr) {
            i += i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int iRound = Math.round((iArr[i4] * 9.0f) / i);
            if (iRound < 1 || iRound > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                for (int i5 = 0; i5 < iRound; i5++) {
                    i3 = (i3 << 1) | 1;
                }
            } else {
                i3 <<= iRound;
            }
        }
        return i3;
    }
}
