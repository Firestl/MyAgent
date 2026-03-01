package supwisdom;

/* JADX INFO: compiled from: Hex.java */
/* JADX INFO: loaded from: classes.dex */
public final class cs0 {
    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            sb.append("0123456789abcdef".charAt(i / 16));
            sb.append("0123456789abcdef".charAt(i % 16));
        }
        return sb.toString();
    }

    public static byte[] a(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                int iDigit = Character.digit(str.charAt(i2), 16);
                int iDigit2 = Character.digit(str.charAt(i2 + 1), 16);
                if (iDigit != -1 && iDigit2 != -1) {
                    bArr[i] = (byte) ((iDigit * 16) + iDigit2);
                } else {
                    throw new IllegalArgumentException("input is not hexadecimal");
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Expected a string of even length");
    }
}
