package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import com.taobao.weex.el.parse.Operators;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class rx {
    public static final int[] c = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f9097a = new int[4];
    public final StringBuilder b = new StringBuilder();

    public static Map<ResultMetadataType, Object> b(String str) {
        String strA;
        if (str.length() != 5 || (strA = a(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, strA);
        return enumMap;
    }

    public int a(ew ewVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f9097a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iC = ewVar.c();
        int iC2 = iArr[1];
        int i = 0;
        for (int i2 = 0; i2 < 5 && iC2 < iC; i2++) {
            int iA = tx.a(ewVar, iArr2, iC2, tx.g);
            sb.append((char) ((iA % 10) + 48));
            for (int i3 : iArr2) {
                iC2 += i3;
            }
            if (iA >= 10) {
                i |= 1 << (4 - i2);
            }
            if (i2 != 4) {
                iC2 = ewVar.c(ewVar.b(iC2));
            }
        }
        if (sb.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (a((CharSequence) sb.toString()) == a(i)) {
            return iC2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public xv a(int i, ew ewVar, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.b;
        sb.setLength(0);
        int iA = a(ewVar, iArr, sb);
        String string = sb.toString();
        Map<ResultMetadataType, Object> mapB = b(string);
        float f = i;
        xv xvVar = new xv(string, null, new yv[]{new yv((iArr[0] + iArr[1]) / 2.0f, f), new yv(iA, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (mapB != null) {
            xvVar.a(mapB);
        }
        return xvVar;
    }

    public static int a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == c[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int iCharAt = 0;
        for (int i = length - 2; i >= 0; i -= 2) {
            iCharAt += charSequence.charAt(i) - '0';
        }
        int iCharAt2 = iCharAt * 3;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            iCharAt2 += charSequence.charAt(i2) - '0';
        }
        return (iCharAt2 * 3) % 10;
    }

    public static String a(String str) {
        String str2;
        String strValueOf;
        char cCharAt = str.charAt(0);
        if (cCharAt == '0') {
            str2 = "£";
        } else if (cCharAt != '5') {
            if (cCharAt == '9') {
                if ("90000".equals(str)) {
                    return null;
                }
                if ("99991".equals(str)) {
                    return "0.00";
                }
                if ("99990".equals(str)) {
                    return "Used";
                }
            }
            str2 = "";
        } else {
            str2 = Operators.DOLLAR_STR;
        }
        int i = Integer.parseInt(str.substring(1));
        String strValueOf2 = String.valueOf(i / 100);
        int i2 = i % 100;
        if (i2 < 10) {
            strValueOf = "0" + i2;
        } else {
            strValueOf = String.valueOf(i2);
        }
        return str2 + strValueOf2 + '.' + strValueOf;
    }
}
