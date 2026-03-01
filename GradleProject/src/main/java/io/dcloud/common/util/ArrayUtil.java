package io.dcloud.common.util;

/* JADX INFO: loaded from: classes2.dex */
public class ArrayUtil {
    public static String[] riseArray(String[] strArr, String str) {
        String[] strArr2;
        if (strArr == null) {
            strArr2 = new String[1];
        } else {
            int length = strArr.length;
            String[] strArr3 = new String[length + 1];
            System.arraycopy(strArr, 0, strArr3, 0, length);
            strArr2 = strArr3;
        }
        strArr2[strArr2.length] = str;
        return strArr2;
    }
}
