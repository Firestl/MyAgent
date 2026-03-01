package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class qp1 {
    public static byte[] a(String str) {
        String strReplace = str.replace(Operators.SPACE_STR, "");
        char[] charArray = strReplace.toCharArray();
        int length = strReplace.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((byte) (((byte) ("0123456789ABCDEF".indexOf(charArray[i2]) * 16)) + "0123456789ABCDEF".indexOf(charArray[i2 + 1]))) & 255);
        }
        return bArr;
    }
}
