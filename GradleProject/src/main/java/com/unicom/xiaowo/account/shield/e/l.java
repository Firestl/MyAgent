package com.unicom.xiaowo.account.shield.e;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.taobao.weex.utils.FunctionParser;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static char[] f5555a = {FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', 'Z', FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', FunctionParser.Lexer.Z_LOWER, '0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.PLUS, '/'};
    public static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, db.l, 15, 16, 17, SharedPreferencesNewImpl.FINISH_MARK, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, JSONLexer.EOI, 27, 28, 29, 30, co.j, 32, PublicSuffixDatabase.EXCEPTION_MARKER, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static byte[] a(byte[] bArr) {
        return a(bArr, a(new String(b("MkYxNEQwRjU1MEQyNEYxOENCQTU1MTlGNEZBMjI2QUU="))));
    }

    public static byte[] a(byte[] bArr, int[] iArr) {
        return bArr.length == 0 ? bArr : a(a(a(bArr, false), iArr), false);
    }

    public static byte[] a(int[] iArr) {
        return a(a(iArr, a(new String(b("MkYxNEQwRjU1MEQyNEYxOENCQTU1MTlGNEZBMjI2QUU=")))), false);
    }

    public static byte[] a(int[] iArr, boolean z) {
        int length = iArr.length << 2;
        if (z) {
            int i = iArr[iArr.length - 1];
            if (i > length) {
                return null;
            }
            length = i;
        }
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) ((iArr[i2 >>> 2] >>> ((i2 & 3) << 3)) & 255);
        }
        return bArr;
    }

    public static int[] a(String str) {
        return a(c(str), false);
    }

    public static int[] a(byte[] bArr, boolean z) {
        int[] iArr;
        int length = (bArr.length & 3) == 0 ? bArr.length >>> 2 : (bArr.length >>> 2) + 1;
        if (z) {
            iArr = new int[length + 1];
            iArr[length] = bArr.length;
        } else {
            iArr = new int[length];
        }
        int length2 = bArr.length;
        for (int i = 0; i < length2; i++) {
            int i2 = i >>> 2;
            iArr[i2] = iArr[i2] | ((bArr[i] & 255) << ((i & 3) << 3));
        }
        return iArr;
    }

    public static int[] a(int[] iArr, int[] iArr2) {
        int length = iArr.length - 1;
        if (length < 1) {
            return iArr;
        }
        if (iArr2.length < 4) {
            int[] iArr3 = new int[4];
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
            iArr2 = iArr3;
        }
        int i = iArr[length];
        int i2 = iArr[0];
        for (int i3 = ((52 / (length + 1)) + 6) * (-1640531527); i3 != 0; i3 -= -1640531527) {
            int i4 = (i3 >>> 2) & 3;
            int i5 = length;
            while (i5 > 0) {
                int i6 = iArr[i5 - 1];
                i2 = iArr[i5] - (((i2 ^ i3) + (i6 ^ iArr2[(i5 & 3) ^ i4])) ^ (((i6 >>> 5) ^ (i2 << 2)) + ((i2 >>> 3) ^ (i6 << 4))));
                iArr[i5] = i2;
                i5--;
            }
            int i7 = iArr[length];
            i2 = iArr[0] - (((i2 ^ i3) + (iArr2[i4 ^ (i5 & 3)] ^ i7)) ^ (((i7 >>> 5) ^ (i2 << 2)) + ((i2 >>> 3) ^ (i7 << 4))));
            iArr[0] = i2;
        }
        return iArr;
    }

    public static byte[] b(String str) {
        int i;
        byte b2;
        int i2;
        byte b3;
        int i3;
        byte b4;
        int i4;
        byte b5;
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        int i5 = 0;
        while (i5 < length) {
            while (true) {
                i = i5 + 1;
                b2 = b[bytes[i5]];
                if (i >= length || b2 != -1) {
                    break;
                }
                i5 = i;
            }
            if (b2 == -1) {
                break;
            }
            while (true) {
                i2 = i + 1;
                b3 = b[bytes[i]];
                if (i2 >= length || b3 != -1) {
                    break;
                }
                i = i2;
            }
            if (b3 == -1) {
                break;
            }
            byteArrayOutputStream.write((b2 << 2) | ((b3 & 48) >>> 4));
            while (true) {
                i3 = i2 + 1;
                byte b6 = bytes[i2];
                if (b6 == 61) {
                    return byteArrayOutputStream.toByteArray();
                }
                b4 = b[b6];
                if (i3 >= length || b4 != -1) {
                    break;
                }
                i2 = i3;
            }
            if (b4 == -1) {
                break;
            }
            byteArrayOutputStream.write(((b3 & 15) << 4) | ((b4 & 60) >>> 2));
            while (true) {
                i4 = i3 + 1;
                byte b7 = bytes[i3];
                if (b7 == 61) {
                    return byteArrayOutputStream.toByteArray();
                }
                b5 = b[b7];
                if (i4 >= length || b5 != -1) {
                    break;
                }
                i3 = i4;
            }
            if (b5 == -1) {
                break;
            }
            byteArrayOutputStream.write(b5 | ((b4 & 3) << 6));
            i5 = i4;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] c(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            if (bytes[i2] >= 48 && bytes[i2] <= 57) {
                bArr[i] = (byte) ((bytes[i2] - 48) << 4);
            } else if (bytes[i2] >= 65 && bytes[i2] <= 90) {
                bArr[i] = (byte) (((bytes[i2] - 65) + 10) << 4);
            }
            int i3 = i2 + 1;
            if (bytes[i3] >= 48 && bytes[i3] <= 57) {
                bArr[i] = (byte) (bArr[i] + ((byte) (bytes[i3] - 48)));
            } else if (bytes[i3] >= 65 && bytes[i3] <= 90) {
                bArr[i] = (byte) (bArr[i] + ((byte) ((bytes[i3] - 65) + 10)));
            }
        }
        return bArr;
    }
}
