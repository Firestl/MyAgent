package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import java.util.List;
import okio.ByteString;

/* JADX INFO: compiled from: HttpHeaders.java */
/* JADX INFO: loaded from: classes3.dex */
public final class au1 {
    static {
        ByteString.encodeUtf8("\"\\");
        ByteString.encodeUtf8("\t ,=");
    }

    public static long a(dt1 dt1Var) {
        return a(dt1Var.e());
    }

    public static boolean b(dt1 dt1Var) {
        if (dt1Var.k().e().equals("HEAD")) {
            return false;
        }
        int iC = dt1Var.c();
        return (((iC >= 100 && iC < 200) || iC == 204 || iC == 304) && a(dt1Var) == -1 && !"chunked".equalsIgnoreCase(dt1Var.a("Transfer-Encoding"))) ? false : true;
    }

    public static long a(us1 us1Var) {
        return a(us1Var.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH));
    }

    public static long a(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static void a(os1 os1Var, vs1 vs1Var, us1 us1Var) {
        if (os1Var == os1.f8708a) {
            return;
        }
        List<ns1> listA = ns1.a(vs1Var, us1Var);
        if (listA.isEmpty()) {
            return;
        }
        os1Var.a(vs1Var, listA);
    }

    public static int b(String str, int i) {
        char cCharAt;
        while (i < str.length() && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    public static int a(String str, int i) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}
