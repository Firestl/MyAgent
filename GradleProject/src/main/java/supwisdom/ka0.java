package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.Locale;

/* JADX INFO: compiled from: UrlTemplate.java */
/* JADX INFO: loaded from: classes.dex */
public final class ka0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f8155a;
    public final int[] b;
    public final String[] c;
    public final int d;

    public ka0(String[] strArr, int[] iArr, String[] strArr2, int i) {
        this.f8155a = strArr;
        this.b = iArr;
        this.c = strArr2;
        this.d = i;
    }

    public static ka0 a(String str) {
        String[] strArr = new String[5];
        int[] iArr = new int[4];
        String[] strArr2 = new String[4];
        return new ka0(strArr, iArr, strArr2, a(str, strArr, iArr, strArr2));
    }

    public String a(String str, int i, int i2, long j) {
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (true) {
            int i4 = this.d;
            if (i3 < i4) {
                sb.append(this.f8155a[i3]);
                int[] iArr = this.b;
                if (iArr[i3] == 1) {
                    sb.append(str);
                } else if (iArr[i3] == 2) {
                    sb.append(String.format(Locale.US, this.c[i3], Integer.valueOf(i)));
                } else if (iArr[i3] == 3) {
                    sb.append(String.format(Locale.US, this.c[i3], Integer.valueOf(i2)));
                } else if (iArr[i3] == 4) {
                    sb.append(String.format(Locale.US, this.c[i3], Long.valueOf(j)));
                }
                i3++;
            } else {
                sb.append(this.f8155a[i4]);
                return sb.toString();
            }
        }
    }

    public static int a(String str, String[] strArr, int[] iArr, String[] strArr2) {
        String strSubstring;
        strArr[0] = "";
        int length = 0;
        int i = 0;
        while (length < str.length()) {
            int iIndexOf = str.indexOf(Operators.DOLLAR_STR, length);
            byte b = -1;
            if (iIndexOf == -1) {
                strArr[i] = strArr[i] + str.substring(length);
                length = str.length();
            } else if (iIndexOf != length) {
                strArr[i] = strArr[i] + str.substring(length, iIndexOf);
                length = iIndexOf;
            } else if (str.startsWith("$$", length)) {
                strArr[i] = strArr[i] + Operators.DOLLAR_STR;
                length += 2;
            } else {
                int i2 = length + 1;
                int iIndexOf2 = str.indexOf(Operators.DOLLAR_STR, i2);
                String strSubstring2 = str.substring(i2, iIndexOf2);
                if (strSubstring2.equals("RepresentationID")) {
                    iArr[i] = 1;
                } else {
                    int iIndexOf3 = strSubstring2.indexOf("%0");
                    if (iIndexOf3 != -1) {
                        strSubstring = strSubstring2.substring(iIndexOf3);
                        if (!strSubstring.endsWith(cn.com.chinatelecom.account.api.a.d.f1473a)) {
                            strSubstring = strSubstring + cn.com.chinatelecom.account.api.a.d.f1473a;
                        }
                        strSubstring2 = strSubstring2.substring(0, iIndexOf3);
                    } else {
                        strSubstring = "%01d";
                    }
                    int iHashCode = strSubstring2.hashCode();
                    if (iHashCode != -1950496919) {
                        if (iHashCode != 2606829) {
                            if (iHashCode == 38199441 && strSubstring2.equals("Bandwidth")) {
                                b = 1;
                            }
                        } else if (strSubstring2.equals("Time")) {
                            b = 2;
                        }
                    } else if (strSubstring2.equals("Number")) {
                        b = 0;
                    }
                    if (b == 0) {
                        iArr[i] = 2;
                    } else if (b == 1) {
                        iArr[i] = 3;
                    } else if (b == 2) {
                        iArr[i] = 4;
                    } else {
                        throw new IllegalArgumentException("Invalid template: " + str);
                    }
                    strArr2[i] = strSubstring;
                }
                i++;
                strArr[i] = "";
                length = iIndexOf2 + 1;
            }
        }
        return i;
    }
}
