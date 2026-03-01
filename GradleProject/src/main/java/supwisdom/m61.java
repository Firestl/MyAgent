package supwisdom;

import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class m61 {
    public static String a(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = i + i2;
        if ((i | i2 | i6) < 0 || i6 > bArr.length) {
            throw new IndexOutOfBoundsException("arr.length " + bArr.length + "; " + i + "..!" + i6);
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("outOffset < 0");
        }
        if (i2 == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((i2 * 4) + 6);
        int i7 = 0;
        while (i2 > 0) {
            if (i7 == 0) {
                sb.append(i5 != 2 ? i5 != 4 ? i5 != 6 ? g(i3) : f(i3) : d(i3) : c(i3));
                sb.append(": ");
            } else if ((i7 & 1) == 0) {
                sb.append(' ');
            }
            sb.append(c(bArr[i]));
            i3++;
            i++;
            i7++;
            if (i7 == i4) {
                sb.append('\n');
                i7 = 0;
            }
            i2--;
        }
        if (i7 != 0) {
            sb.append('\n');
        }
        return sb.toString();
    }

    public static String b(int i) {
        char[] cArr = new char[9];
        if (i < 0) {
            cArr[0] = FunctionParser.Lexer.MINUS;
            i = -i;
        } else {
            cArr[0] = FunctionParser.Lexer.PLUS;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            cArr[8 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String c(int i) {
        char[] cArr = new char[2];
        for (int i2 = 0; i2 < 2; i2++) {
            cArr[1 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String d(int i) {
        char[] cArr = new char[4];
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[3 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String e(int i) {
        return i == ((char) i) ? d(i) : g(i);
    }

    public static String f(int i) {
        char[] cArr = new char[6];
        for (int i2 = 0; i2 < 6; i2++) {
            cArr[5 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String g(int i) {
        char[] cArr = new char[8];
        for (int i2 = 0; i2 < 8; i2++) {
            cArr[7 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }

    public static String h(int i) {
        return new String(new char[]{Character.forDigit(i & 15, 16)});
    }

    public static String a(long j) {
        char[] cArr = new char[16];
        for (int i = 0; i < 16; i++) {
            cArr[15 - i] = Character.forDigit(((int) j) & 15, 16);
            j >>= 4;
        }
        return new String(cArr);
    }

    public static String a(int i) {
        char[] cArr = new char[5];
        if (i < 0) {
            cArr[0] = FunctionParser.Lexer.MINUS;
            i = -i;
        } else {
            cArr[0] = FunctionParser.Lexer.PLUS;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[4 - i2] = Character.forDigit(i & 15, 16);
            i >>= 4;
        }
        return new String(cArr);
    }
}
