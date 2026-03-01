package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import supwisdom.oe1;
import supwisdom.se1;

/* JADX INFO: compiled from: OkHeaders.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rf1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Comparator<String> f9057a = new a();
    public static final String b = ef1.c().a();
    public static final String c = b + "-Sent-Millis";
    public static final String d = b + "-Received-Millis";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f9058e = b + "-Selected-Protocol";

    /* JADX INFO: compiled from: OkHeaders.java */
    public static class a implements Comparator<String> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
        }
    }

    public static long a(se1 se1Var) {
        return a(se1Var.c());
    }

    public static long b(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static Set<String> c(ue1 ue1Var) {
        Set<String> setEmptySet = Collections.emptySet();
        oe1 oe1VarG = ue1Var.g();
        int iB = oe1VarG.b();
        for (int i = 0; i < iB; i++) {
            if ("Vary".equalsIgnoreCase(oe1VarG.a(i))) {
                String strB = oe1VarG.b(i);
                if (setEmptySet.isEmpty()) {
                    setEmptySet = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : strB.split(",")) {
                    setEmptySet.add(str.trim());
                }
            }
        }
        return setEmptySet;
    }

    public static oe1 d(ue1 ue1Var) {
        Set<String> setC = c(ue1Var);
        if (setC.isEmpty()) {
            return new oe1.b().a();
        }
        oe1 oe1VarC = ue1Var.i().l().c();
        oe1.b bVar = new oe1.b();
        int iB = oe1VarC.b();
        for (int i = 0; i < iB; i++) {
            String strA = oe1VarC.a(i);
            if (setC.contains(strA)) {
                bVar.a(strA, oe1VarC.b(i));
            }
        }
        return bVar.a();
    }

    public static long a(ue1 ue1Var) {
        return a(ue1Var.g());
    }

    public static Map<String, List<String>> b(oe1 oe1Var, String str) {
        TreeMap treeMap = new TreeMap(f9057a);
        int iB = oe1Var.b();
        for (int i = 0; i < iB; i++) {
            String strA = oe1Var.a(i);
            String strB = oe1Var.b(i);
            ArrayList arrayList = new ArrayList();
            List list = (List) treeMap.get(strA);
            if (list != null) {
                arrayList.addAll(list);
            }
            arrayList.add(strB);
            treeMap.put(strA, Collections.unmodifiableList(arrayList));
        }
        if (str != null) {
            treeMap.put(null, Collections.unmodifiableList(Collections.singletonList(str)));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    public static long a(oe1 oe1Var) {
        return b(oe1Var.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH));
    }

    public static void a(se1.b bVar, Map<String, List<String>> map) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if ("Cookie".equalsIgnoreCase(key) || HttpHeaders.HEAD_KEY_COOKIE2.equalsIgnoreCase(key)) {
                if (!entry.getValue().isEmpty()) {
                    bVar.a(key, a(entry.getValue()));
                }
            }
        }
    }

    public static String a(List<String> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static boolean b(ue1 ue1Var) {
        return c(ue1Var).contains(Operators.MUL);
    }

    public static boolean a(ue1 ue1Var, oe1 oe1Var, se1 se1Var) {
        for (String str : c(ue1Var)) {
            if (!gf1.a(oe1Var.c(str), se1Var.b(str))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(String str) {
        return (HttpHeaders.HEAD_KEY_CONNECTION.equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static List<he1> a(oe1 oe1Var, String str) {
        ArrayList arrayList = new ArrayList();
        int iB = oe1Var.b();
        for (int i = 0; i < iB; i++) {
            if (str.equalsIgnoreCase(oe1Var.a(i))) {
                String strB = oe1Var.b(i);
                int iB2 = 0;
                while (iB2 < strB.length()) {
                    int iA = lf1.a(strB, iB2, Operators.SPACE_STR);
                    String strTrim = strB.substring(iB2, iA).trim();
                    int iB3 = lf1.b(strB, iA);
                    if (!strB.regionMatches(true, iB3, "realm=\"", 0, 7)) {
                        break;
                    }
                    int i2 = iB3 + 7;
                    int iA2 = lf1.a(strB, i2, JSUtil.QUOTE);
                    String strSubstring = strB.substring(i2, iA2);
                    iB2 = lf1.b(strB, lf1.a(strB, iA2 + 1, ",") + 1);
                    arrayList.add(new he1(strTrim, strSubstring));
                }
            }
        }
        return arrayList;
    }

    public static se1 a(be1 be1Var, ue1 ue1Var, Proxy proxy) throws IOException {
        if (ue1Var.e() == 407) {
            return be1Var.a(proxy, ue1Var);
        }
        return be1Var.b(proxy, ue1Var);
    }
}
