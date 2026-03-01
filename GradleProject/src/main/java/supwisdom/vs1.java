package supwisdom;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.utils.FunctionParser;
import dc.squareup.okhttp3.HttpUrl;
import io.dcloud.common.util.PdrUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okio.Buffer;

/* JADX INFO: compiled from: HttpUrl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vs1 {
    public static final char[] j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9543a;
    public final String b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9544e;
    public final List<String> f;

    @Nullable
    public final List<String> g;

    @Nullable
    public final String h;
    public final String i;

    /* JADX INFO: compiled from: HttpUrl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public String f9545a;

        @Nullable
        public String d;
        public final List<String> f;

        @Nullable
        public List<String> g;

        @Nullable
        public String h;
        public String b = "";
        public String c = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f9546e = -1;

        public a() {
            ArrayList arrayList = new ArrayList();
            this.f = arrayList;
            arrayList.add("");
        }

        public a a(int i) {
            if (i > 0 && i <= 65535) {
                this.f9546e = i;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i);
        }

        public int b() {
            int i = this.f9546e;
            return i != -1 ? i : vs1.c(this.f9545a);
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            String strB = b(str, 0, str.length());
            if (strB != null) {
                this.d = strB;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        public a d() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, vs1.a(this.f.get(i), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, true, false, true));
            }
            List<String> list = this.g;
            if (list != null) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, vs1.a(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true, true));
                    }
                }
            }
            String str2 = this.h;
            if (str2 != null) {
                this.h = vs1.a(str2, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, true, false, false);
            }
            return this;
        }

        public final boolean e(String str) {
            return str.equals(PdrUtil.FILE_PATH_ENTRY_BACK) || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        public a f(String str) {
            if (str == null) {
                throw new NullPointerException("password == null");
            }
            this.c = vs1.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a g(String str) {
            if (str == null) {
                throw new NullPointerException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.f9545a = "http";
            } else {
                if (!str.equalsIgnoreCase("https")) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                this.f9545a = "https";
            }
            return this;
        }

        public a h(String str) {
            if (str == null) {
                throw new NullPointerException("username == null");
            }
            this.b = vs1.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = this.f9545a;
            if (str != null) {
                sb.append(str);
                sb.append("://");
            } else {
                sb.append("//");
            }
            if (!this.b.isEmpty() || !this.c.isEmpty()) {
                sb.append(this.b);
                if (!this.c.isEmpty()) {
                    sb.append(Operators.CONDITION_IF_MIDDLE);
                    sb.append(this.c);
                }
                sb.append(TemplateDom.SEPARATOR);
            }
            String str2 = this.d;
            if (str2 != null) {
                if (str2.indexOf(58) != -1) {
                    sb.append(Operators.ARRAY_START);
                    sb.append(this.d);
                    sb.append(Operators.ARRAY_END);
                } else {
                    sb.append(this.d);
                }
            }
            if (this.f9546e != -1 || this.f9545a != null) {
                int iB = b();
                String str3 = this.f9545a;
                if (str3 == null || iB != vs1.c(str3)) {
                    sb.append(Operators.CONDITION_IF_MIDDLE);
                    sb.append(iB);
                }
            }
            vs1.b(sb, this.f);
            if (this.g != null) {
                sb.append(Operators.CONDITION_IF);
                vs1.a(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }

        public a b(@Nullable String str) {
            this.g = str != null ? vs1.f(vs1.a(str, HttpUrl.QUERY_ENCODE_SET, true, false, true, true)) : null;
            return this;
        }

        public static int f(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt != '\\' && cCharAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        public a a(String str) {
            if (str != null) {
                if (str.startsWith("/")) {
                    a(str, 0, str.length());
                    return this;
                }
                throw new IllegalArgumentException("unexpected encodedPath: " + str);
            }
            throw new NullPointerException("encodedPath == null");
        }

        public static int e(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char cCharAt = str.charAt(i);
            if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                while (true) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                    char cCharAt2 = str.charAt(i);
                    if (cCharAt2 < 'a' || cCharAt2 > 'z') {
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                    if (cCharAt2 == ':') {
                                        return i;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        public a b(String str, @Nullable String str2) {
            if (str != null) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                this.g.add(vs1.a(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true));
                this.g.add(str2 != null ? vs1.a(str2, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, false, true, true) : null);
                return this;
            }
            throw new NullPointerException("name == null");
        }

        public final void c() {
            if (this.f.remove(r0.size() - 1).isEmpty() && !this.f.isEmpty()) {
                this.f.set(r0.size() - 1, "");
            } else {
                this.f.add("");
            }
        }

        public a a(String str, @Nullable String str2) {
            if (str != null) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                this.g.add(vs1.a(str, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, true));
                this.g.add(str2 != null ? vs1.a(str2, HttpUrl.QUERY_COMPONENT_REENCODE_SET, true, false, true, true) : null);
                return this;
            }
            throw new NullPointerException("encodedName == null");
        }

        public static int c(String str, int i, int i2) {
            int i3;
            try {
                i3 = Integer.parseInt(vs1.a(str, i, i2, "", false, false, false, true, null));
            } catch (NumberFormatException unused) {
            }
            if (i3 <= 0 || i3 > 65535) {
                return -1;
            }
            return i3;
        }

        public static String b(String str, int i, int i2) {
            return kt1.a(vs1.a(str, i, i2, false));
        }

        public final boolean d(String str) {
            return str.equals(Operators.DOT_STR) || str.equalsIgnoreCase("%2e");
        }

        public static int d(String str, int i, int i2) {
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt == ':') {
                    return i;
                }
                if (cCharAt == '[') {
                    do {
                        i++;
                        if (i < i2) {
                        }
                    } while (str.charAt(i) != ']');
                }
                i++;
            }
            return i2;
        }

        public vs1 a() {
            if (this.f9545a != null) {
                if (this.d != null) {
                    return new vs1(this);
                }
                throw new IllegalStateException("host == null");
            }
            throw new IllegalStateException("scheme == null");
        }

        public a a(@Nullable vs1 vs1Var, String str) {
            int iA;
            int i;
            int iB = kt1.b(str, 0, str.length());
            int iC = kt1.c(str, iB, str.length());
            int iE = e(str, iB, iC);
            if (iE != -1) {
                if (str.regionMatches(true, iB, "https:", 0, 6)) {
                    this.f9545a = "https";
                    iB += 6;
                } else if (str.regionMatches(true, iB, "http:", 0, 5)) {
                    this.f9545a = "http";
                    iB += 5;
                } else {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, iE) + "'");
                }
            } else if (vs1Var != null) {
                this.f9545a = vs1Var.f9543a;
            } else {
                throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
            }
            int iF = f(str, iB, iC);
            char c = Operators.CONDITION_IF;
            char c2 = '#';
            if (iF < 2 && vs1Var != null && vs1Var.f9543a.equals(this.f9545a)) {
                this.b = vs1Var.f();
                this.c = vs1Var.b();
                this.d = vs1Var.d;
                this.f9546e = vs1Var.f9544e;
                this.f.clear();
                this.f.addAll(vs1Var.d());
                if (iB == iC || str.charAt(iB) == '#') {
                    b(vs1Var.e());
                }
            } else {
                int i2 = iB + iF;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    iA = kt1.a(str, i2, iC, "@/\\?#");
                    byte bCharAt = iA != iC ? str.charAt(iA) : (byte) -1;
                    if (bCharAt == -1 || bCharAt == c2 || bCharAt == 47 || bCharAt == 92 || bCharAt == c) {
                        break;
                    }
                    if (bCharAt == 64) {
                        if (!z) {
                            int iA2 = kt1.a(str, i2, iA, Operators.CONDITION_IF_MIDDLE);
                            i = iA;
                            String strA = vs1.a(str, i2, iA2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if (z2) {
                                strA = this.b + "%40" + strA;
                            }
                            this.b = strA;
                            if (iA2 != i) {
                                this.c = vs1.a(str, iA2 + 1, i, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                z = true;
                            }
                            z2 = true;
                        } else {
                            i = iA;
                            this.c += "%40" + vs1.a(str, i2, i, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        }
                        i2 = i + 1;
                    }
                    c = Operators.CONDITION_IF;
                    c2 = '#';
                }
                int iD = d(str, i2, iA);
                int i3 = iD + 1;
                if (i3 < iA) {
                    this.d = b(str, i2, iD);
                    int iC2 = c(str, i3, iA);
                    this.f9546e = iC2;
                    if (iC2 == -1) {
                        throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i3, iA) + Operators.QUOTE);
                    }
                } else {
                    this.d = b(str, i2, iD);
                    this.f9546e = vs1.c(this.f9545a);
                }
                if (this.d == null) {
                    throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i2, iD) + Operators.QUOTE);
                }
                iB = iA;
            }
            int iA3 = kt1.a(str, iB, iC, "?#");
            a(str, iB, iA3);
            if (iA3 < iC && str.charAt(iA3) == '?') {
                int iA4 = kt1.a(str, iA3, iC, '#');
                this.g = vs1.f(vs1.a(str, iA3 + 1, iA4, HttpUrl.QUERY_ENCODE_SET, true, false, true, true, null));
                iA3 = iA4;
            }
            if (iA3 < iC && str.charAt(iA3) == '#') {
                this.h = vs1.a(str, 1 + iA3, iC, "", true, false, false, false, null);
            }
            return this;
        }

        public final void a(String str, int i, int i2) {
            if (i == i2) {
                return;
            }
            char cCharAt = str.charAt(i);
            if (cCharAt != '/' && cCharAt != '\\') {
                List<String> list = this.f;
                list.set(list.size() - 1, "");
            } else {
                this.f.clear();
                this.f.add("");
                i++;
            }
            while (true) {
                int i3 = i;
                if (i3 >= i2) {
                    return;
                }
                i = kt1.a(str, i3, i2, "/\\");
                boolean z = i < i2;
                a(str, i3, i, z, true);
                if (z) {
                    i++;
                }
            }
        }

        public final void a(String str, int i, int i2, boolean z, boolean z2) {
            String strA = vs1.a(str, i, i2, HttpUrl.PATH_SEGMENT_ENCODE_SET, z2, false, false, true, null);
            if (d(strA)) {
                return;
            }
            if (e(strA)) {
                c();
                return;
            }
            if (this.f.get(r11.size() - 1).isEmpty()) {
                this.f.set(r11.size() - 1, strA);
            } else {
                this.f.add(strA);
            }
            if (z) {
                this.f.add("");
            }
        }
    }

    public vs1(a aVar) {
        this.f9543a = aVar.f9545a;
        this.b = a(aVar.b, false);
        this.c = a(aVar.c, false);
        this.d = aVar.d;
        this.f9544e = aVar.b();
        this.f = a(aVar.f, false);
        List<String> list = aVar.g;
        this.g = list != null ? a(list, true) : null;
        String str = aVar.h;
        this.h = str != null ? a(str, false) : null;
        this.i = aVar.toString();
    }

    public static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static int c(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public String b() {
        if (this.c.isEmpty()) {
            return "";
        }
        return this.i.substring(this.i.indexOf(58, this.f9543a.length() + 3) + 1, this.i.indexOf(64));
    }

    public List<String> d() {
        int iIndexOf = this.i.indexOf(47, this.f9543a.length() + 3);
        String str = this.i;
        int iA = kt1.a(str, iIndexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iA) {
            int i = iIndexOf + 1;
            int iA2 = kt1.a(this.i, i, iA, '/');
            arrayList.add(this.i.substring(i, iA2));
            iIndexOf = iA2;
        }
        return arrayList;
    }

    @Nullable
    public String e() {
        if (this.g == null) {
            return null;
        }
        int iIndexOf = this.i.indexOf(63) + 1;
        String str = this.i;
        return this.i.substring(iIndexOf, kt1.a(str, iIndexOf, str.length(), '#'));
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof vs1) && ((vs1) obj).i.equals(this.i);
    }

    public String f() {
        if (this.b.isEmpty()) {
            return "";
        }
        int length = this.f9543a.length() + 3;
        String str = this.i;
        return this.i.substring(length, kt1.a(str, length, str.length(), ":@"));
    }

    public String g() {
        return this.d;
    }

    public boolean h() {
        return this.f9543a.equals("https");
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public a i() {
        a aVar = new a();
        aVar.f9545a = this.f9543a;
        aVar.b = f();
        aVar.c = b();
        aVar.d = this.d;
        aVar.f9546e = this.f9544e != c(this.f9543a) ? this.f9544e : -1;
        aVar.f.clear();
        aVar.f.addAll(d());
        aVar.b(e());
        aVar.h = a();
        return aVar;
    }

    public List<String> j() {
        return this.f;
    }

    public int k() {
        return this.f9544e;
    }

    @Nullable
    public String l() {
        if (this.g == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        a(sb, this.g);
        return sb.toString();
    }

    public String m() {
        a aVarA = a("/...");
        aVarA.h("");
        aVarA.f("");
        return aVarA.a().toString();
    }

    public String n() {
        return this.f9543a;
    }

    public URI o() {
        a aVarI = i();
        aVarI.d();
        String string = aVarI.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    public String toString() {
        return this.i;
    }

    public String c() {
        int iIndexOf = this.i.indexOf(47, this.f9543a.length() + 3);
        String str = this.i;
        return this.i.substring(iIndexOf, kt1.a(str, iIndexOf, str.length(), "?#"));
    }

    public static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    @Nullable
    public static vs1 e(String str) {
        try {
            return d(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static List<String> f(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int iIndexOf = str.indexOf(38, i);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i);
            if (iIndexOf2 != -1 && iIndexOf2 <= iIndexOf) {
                arrayList.add(str.substring(i, iIndexOf2));
                arrayList.add(str.substring(iIndexOf2 + 1, iIndexOf));
            } else {
                arrayList.add(str.substring(i, iIndexOf));
                arrayList.add(null);
            }
            i = iIndexOf + 1;
        }
        return arrayList;
    }

    public static vs1 d(String str) {
        a aVar = new a();
        aVar.a((vs1) null, str);
        return aVar.a();
    }

    @Nullable
    public String a() {
        if (this.h == null) {
            return null;
        }
        return this.i.substring(this.i.indexOf(35) + 1);
    }

    @Nullable
    public vs1 b(String str) {
        a aVarA = a(str);
        if (aVarA != null) {
            return aVarA.a();
        }
        return null;
    }

    @Nullable
    public a a(String str) {
        try {
            a aVar = new a();
            aVar.a(this, str);
            return aVar;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    public final List<String> a(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static String a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, i, i3);
                a(buffer, str, i3, i2, z);
                return buffer.readUtf8();
            }
        }
        return str.substring(i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(okio.Buffer r5, java.lang.String r6, int r7, int r8, boolean r9) {
        /*
        L0:
            if (r7 >= r8) goto L42
            int r0 = r6.codePointAt(r7)
            r1 = 37
            if (r0 != r1) goto L2d
            int r1 = r7 + 2
            if (r1 >= r8) goto L2d
            int r2 = r7 + 1
            char r2 = r6.charAt(r2)
            int r2 = supwisdom.kt1.a(r2)
            char r3 = r6.charAt(r1)
            int r3 = supwisdom.kt1.a(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.writeByte(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.writeByte(r1)
            goto L3c
        L39:
            r5.writeUtf8CodePoint(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.vs1.a(okio.Buffer, java.lang.String, int, int, boolean):void");
    }

    public static boolean a(String str, int i, int i2) {
        int i3 = i + 2;
        return i3 < i2 && str.charAt(i) == '%' && kt1.a(str.charAt(i + 1)) != -1 && kt1.a(str.charAt(i3)) != -1;
    }

    public static String a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        int iCharCount = i;
        while (iCharCount < i2) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt >= 32 && iCodePointAt != 127 && (iCodePointAt < 128 || !z4)) {
                if (str2.indexOf(iCodePointAt) == -1 && ((iCodePointAt != 37 || (z && (!z2 || a(str, iCharCount, i2)))) && (iCodePointAt != 43 || !z3))) {
                    iCharCount += Character.charCount(iCodePointAt);
                } else {
                    Buffer buffer = new Buffer();
                    buffer.writeUtf8(str, i, iCharCount);
                    a(buffer, str, iCharCount, i2, str2, z, z2, z3, z4, charset);
                    return buffer.readUtf8();
                }
            } else {
                Buffer buffer2 = new Buffer();
                buffer2.writeUtf8(str, i, iCharCount);
                a(buffer2, str, iCharCount, i2, str2, z, z2, z3, z4, charset);
                return buffer2.readUtf8();
            }
        }
        return str.substring(i, i2);
    }

    public static void a(Buffer buffer, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        Buffer buffer2 = null;
        while (i < i2) {
            int iCodePointAt = str.codePointAt(i);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    buffer.writeUtf8(z ? Operators.PLUS : "%2B");
                } else if (iCodePointAt >= 32 && iCodePointAt != 127 && ((iCodePointAt < 128 || !z4) && str2.indexOf(iCodePointAt) == -1 && (iCodePointAt != 37 || (z && (!z2 || a(str, i, i2)))))) {
                    buffer.writeUtf8CodePoint(iCodePointAt);
                } else {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    if (charset != null && !charset.equals(kt1.i)) {
                        buffer2.writeString(str, i, Character.charCount(iCodePointAt) + i, charset);
                    } else {
                        buffer2.writeUtf8CodePoint(iCodePointAt);
                    }
                    while (!buffer2.exhausted()) {
                        int i3 = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        buffer.writeByte((int) j[(i3 >> 4) & 15]);
                        buffer.writeByte((int) j[i3 & 15]);
                    }
                }
            }
            i += Character.charCount(iCodePointAt);
        }
    }

    public static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    public static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }
}
