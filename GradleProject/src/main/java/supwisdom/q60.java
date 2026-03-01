package supwisdom;

import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.TypefaceUtil;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: CssParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class q60 {
    public static final Pattern c = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o80 f8881a = new o80();
    public final StringBuilder b = new StringBuilder();

    public static String b(o80 o80Var, StringBuilder sb) {
        b(o80Var);
        if (o80Var.b() < 5 || !"::cue".equals(o80Var.e(5))) {
            return null;
        }
        int iD = o80Var.d();
        String strA = a(o80Var, sb);
        if (strA == null) {
            return null;
        }
        if (Operators.BLOCK_START_STR.equals(strA)) {
            o80Var.c(iD);
            return "";
        }
        String strD = "(".equals(strA) ? d(o80Var) : null;
        String strA2 = a(o80Var, sb);
        if (!")".equals(strA2) || strA2 == null) {
            return null;
        }
        return strD;
    }

    public static void c(o80 o80Var) {
        while (!TextUtils.isEmpty(o80Var.y())) {
        }
    }

    public static String d(o80 o80Var) {
        int iD = o80Var.d();
        int iC = o80Var.c();
        boolean z = false;
        while (iD < iC && !z) {
            int i = iD + 1;
            z = ((char) o80Var.f8644a[iD]) == ')';
            iD = i;
        }
        return o80Var.e((iD - 1) - o80Var.d()).trim();
    }

    public static boolean e(o80 o80Var) {
        char cA = a(o80Var, o80Var.d());
        if (cA != '\t' && cA != '\n' && cA != '\f' && cA != '\r' && cA != ' ') {
            return false;
        }
        o80Var.d(1);
        return true;
    }

    public static boolean f(o80 o80Var) {
        int iD = o80Var.d();
        int iC = o80Var.c();
        byte[] bArr = o80Var.f8644a;
        if (iD + 2 > iC) {
            return false;
        }
        int i = iD + 1;
        if (bArr[iD] != 47) {
            return false;
        }
        int i2 = i + 1;
        if (bArr[i] != 42) {
            return false;
        }
        while (true) {
            int i3 = i2 + 1;
            if (i3 >= iC) {
                o80Var.d(iC - o80Var.d());
                return true;
            }
            if (((char) bArr[i2]) == '*' && ((char) bArr[i3]) == '/') {
                i2 = i3 + 1;
                iC = i2;
            } else {
                i2 = i3;
            }
        }
    }

    public t60 a(o80 o80Var) {
        this.b.setLength(0);
        int iD = o80Var.d();
        c(o80Var);
        this.f8881a.a(o80Var.f8644a, o80Var.d());
        this.f8881a.c(iD);
        String strB = b(this.f8881a, this.b);
        if (strB == null || !Operators.BLOCK_START_STR.equals(a(this.f8881a, this.b))) {
            return null;
        }
        t60 t60Var = new t60();
        a(t60Var, strB);
        String strA = null;
        boolean z = false;
        while (!z) {
            int iD2 = this.f8881a.d();
            strA = a(this.f8881a, this.b);
            boolean z2 = strA == null || Operators.BLOCK_END_STR.equals(strA);
            if (!z2) {
                this.f8881a.c(iD2);
                a(this.f8881a, t60Var, this.b);
            }
            z = z2;
        }
        if (Operators.BLOCK_END_STR.equals(strA)) {
            return t60Var;
        }
        return null;
    }

    public static String c(o80 o80Var, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        while (!z) {
            int iD = o80Var.d();
            String strA = a(o80Var, sb);
            if (strA == null) {
                return null;
            }
            if (!Operators.BLOCK_END_STR.equals(strA) && !";".equals(strA)) {
                sb2.append(strA);
            } else {
                o80Var.c(iD);
                z = true;
            }
        }
        return sb2.toString();
    }

    public static String d(o80 o80Var, StringBuilder sb) {
        boolean z = false;
        sb.setLength(0);
        int iD = o80Var.d();
        int iC = o80Var.c();
        while (iD < iC && !z) {
            char c2 = (char) o80Var.f8644a[iD];
            if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !((c2 >= '0' && c2 <= '9') || c2 == '#' || c2 == '-' || c2 == '.' || c2 == '_'))) {
                z = true;
            } else {
                iD++;
                sb.append(c2);
            }
        }
        o80Var.d(iD - o80Var.d());
        return sb.toString();
    }

    public static void b(o80 o80Var) {
        while (true) {
            for (boolean z = true; o80Var.b() > 0 && z; z = false) {
                if (e(o80Var) || f(o80Var)) {
                    break;
                }
            }
            return;
        }
    }

    public static void a(o80 o80Var, t60 t60Var, StringBuilder sb) {
        b(o80Var);
        String strD = d(o80Var, sb);
        if (!"".equals(strD) && Constants.COLON_SEPARATOR.equals(a(o80Var, sb))) {
            b(o80Var);
            String strC = c(o80Var, sb);
            if (strC == null || "".equals(strC)) {
                return;
            }
            int iD = o80Var.d();
            String strA = a(o80Var, sb);
            if (!";".equals(strA)) {
                if (!Operators.BLOCK_END_STR.equals(strA)) {
                    return;
                } else {
                    o80Var.c(iD);
                }
            }
            if ("color".equals(strD)) {
                t60Var.a(g80.b(strC));
                return;
            }
            if ("background-color".equals(strD)) {
                t60Var.b(g80.b(strC));
                return;
            }
            if ("text-decoration".equals(strD)) {
                if ("underline".equals(strC)) {
                    t60Var.a(true);
                }
            } else {
                if (TypefaceUtil.FONT_CACHE_DIR_NAME.equals(strD)) {
                    t60Var.d(strC);
                    return;
                }
                if ("font-weight".equals(strD)) {
                    if (Constants.Value.BOLD.equals(strC)) {
                        t60Var.b(true);
                    }
                } else if ("font-style".equals(strD) && Constants.Value.ITALIC.equals(strC)) {
                    t60Var.c(true);
                }
            }
        }
    }

    public static String a(o80 o80Var, StringBuilder sb) {
        b(o80Var);
        if (o80Var.b() == 0) {
            return null;
        }
        String strD = d(o80Var, sb);
        if (!"".equals(strD)) {
            return strD;
        }
        return "" + ((char) o80Var.g());
    }

    public static char a(o80 o80Var, int i) {
        return (char) o80Var.f8644a[i];
    }

    public final void a(t60 t60Var, String str) {
        if ("".equals(str)) {
            return;
        }
        int iIndexOf = str.indexOf(91);
        if (iIndexOf != -1) {
            Matcher matcher = c.matcher(str.substring(iIndexOf));
            if (matcher.matches()) {
                t60Var.c(matcher.group(1));
            }
            str = str.substring(0, iIndexOf);
        }
        String[] strArrSplit = str.split("\\.");
        String str2 = strArrSplit[0];
        int iIndexOf2 = str2.indexOf(35);
        if (iIndexOf2 != -1) {
            t60Var.b(str2.substring(0, iIndexOf2));
            t60Var.a(str2.substring(iIndexOf2 + 1));
        } else {
            t60Var.b(str2);
        }
        if (strArrSplit.length > 1) {
            t60Var.a((String[]) Arrays.copyOfRange(strArrSplit, 1, strArrSplit.length));
        }
    }
}
