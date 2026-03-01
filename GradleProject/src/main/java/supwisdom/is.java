package supwisdom;

import com.taobao.weex.utils.FunctionParser;

/* JADX INFO: compiled from: Hex.java */
/* JADX INFO: loaded from: classes.dex */
public class is {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f7978a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        return a(bArr, 0);
    }

    public static String a(byte[] bArr, int i) {
        int length = bArr.length;
        char[] cArr = new char[(length << 1) + (i > 0 ? length / i : 0)];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (i > 0 && i3 % i == 0 && i2 > 0) {
                cArr[i2] = FunctionParser.Lexer.MINUS;
                i2++;
            }
            int i4 = i2 + 1;
            char[] cArr2 = f7978a;
            cArr[i2] = cArr2[(bArr[i3] & 240) >>> 4];
            i2 = i4 + 1;
            cArr[i4] = cArr2[bArr[i3] & 15];
        }
        return new String(cArr);
    }
}
