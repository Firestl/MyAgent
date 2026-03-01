package supwisdom;

import com.taobao.weex.utils.FunctionParser;
import java.util.Arrays;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: classes2.dex */
public class pp1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f8819a = {FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', 'Z', FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', FunctionParser.Lexer.Z_LOWER, '0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.PLUS, '/'};
    public static final pp1 b = new pp1(false, null, -1, true);

    public pp1(boolean z, byte[] bArr, int i, boolean z2) {
    }

    public String a(byte[] bArr) {
        int i;
        int length = ((bArr.length + 2) / 3) * 4;
        byte[] bArrCopyOf = new byte[length];
        int length2 = bArr.length;
        char[] cArr = f8819a;
        int i2 = ((length2 + 0) / 3) * 3;
        int i3 = i2 + 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            int iMin = Math.min(i4 + i2, i3);
            int i6 = i4;
            int i7 = i5;
            while (i6 < iMin) {
                int i8 = i6 + 1;
                int i9 = i8 + 1;
                int i10 = ((bArr[i6] & 255) << 16) | ((bArr[i8] & 255) << 8);
                int i11 = i9 + 1;
                int i12 = i10 | (bArr[i9] & 255);
                int i13 = i7 + 1;
                bArrCopyOf[i7] = (byte) cArr[(i12 >>> 18) & 63];
                int i14 = i13 + 1;
                bArrCopyOf[i13] = (byte) cArr[(i12 >>> 12) & 63];
                int i15 = i14 + 1;
                bArrCopyOf[i14] = (byte) cArr[(i12 >>> 6) & 63];
                i7 = i15 + 1;
                bArrCopyOf[i15] = (byte) cArr[i12 & 63];
                i6 = i11;
            }
            int i16 = ((iMin - i4) / 3) * 4;
            i5 += i16;
            if (i16 == -1 && iMin < length2) {
                throw null;
            }
            i4 = iMin;
        }
        if (i4 < length2) {
            int i17 = i4 + 1;
            int i18 = bArr[i4] & 255;
            int i19 = i5 + 1;
            bArrCopyOf[i5] = (byte) cArr[i18 >> 2];
            if (i17 == length2) {
                int i20 = i19 + 1;
                bArrCopyOf[i19] = (byte) cArr[(i18 << 4) & 63];
                int i21 = i20 + 1;
                bArrCopyOf[i20] = 61;
                i = i21 + 1;
                bArrCopyOf[i21] = 61;
            } else {
                int i22 = bArr[i17] & 255;
                int i23 = i19 + 1;
                bArrCopyOf[i19] = (byte) cArr[((i18 << 4) & 63) | (i22 >> 4)];
                int i24 = i23 + 1;
                bArrCopyOf[i23] = (byte) cArr[(i22 << 2) & 63];
                i = i24 + 1;
                bArrCopyOf[i24] = 61;
            }
            i5 = i;
        }
        if (i5 != length) {
            bArrCopyOf = Arrays.copyOf(bArrCopyOf, i5);
        }
        return new String(bArrCopyOf, 0, 0, bArrCopyOf.length);
    }
}
