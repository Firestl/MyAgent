package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes3.dex */
public final class us1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f9437a;

    public us1(a aVar) {
        List<String> list = aVar.f9438a;
        this.f9437a = (String[]) list.toArray(new String[list.size()]);
    }

    @Nullable
    public String a(String str) {
        return a(this.f9437a, str);
    }

    public String b(int i) {
        return this.f9437a[(i * 2) + 1];
    }

    public int c() {
        return this.f9437a.length / 2;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof us1) && Arrays.equals(((us1) obj).f9437a, this.f9437a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f9437a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iC = c();
        for (int i = 0; i < iC; i++) {
            sb.append(a(i));
            sb.append(": ");
            sb.append(b(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    /* JADX INFO: compiled from: Headers.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f9438a = new ArrayList(20);

        public a a(String str) {
            int iIndexOf = str.indexOf(Constants.COLON_SEPARATOR, 1);
            if (iIndexOf != -1) {
                b(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
                return this;
            }
            if (str.startsWith(Constants.COLON_SEPARATOR)) {
                b("", str.substring(1));
                return this;
            }
            b("", str);
            return this;
        }

        public a b(String str, String str2) {
            this.f9438a.add(str);
            this.f9438a.add(str2.trim());
            return this;
        }

        public a c(String str, String str2) {
            us1.c(str);
            us1.a(str2, str);
            b(str);
            b(str, str2);
            return this;
        }

        public a b(String str) {
            int i = 0;
            while (i < this.f9438a.size()) {
                if (str.equalsIgnoreCase(this.f9438a.get(i))) {
                    this.f9438a.remove(i);
                    this.f9438a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public a a(String str, String str2) {
            us1.c(str);
            us1.a(str2, str);
            b(str, str2);
            return this;
        }

        public a a(us1 us1Var) {
            int iC = us1Var.c();
            for (int i = 0; i < iC; i++) {
                b(us1Var.a(i), us1Var.b(i));
            }
            return this;
        }

        public us1 a() {
            return new us1(this);
        }
    }

    public static void c(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= ' ' || cCharAt >= 127) {
                throw new IllegalArgumentException(kt1.a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
            }
        }
    }

    public String a(int i) {
        return this.f9437a[i * 2];
    }

    public List<String> b(String str) {
        int iC = c();
        ArrayList arrayList = null;
        for (int i = 0; i < iC; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(b(i));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public us1(String[] strArr) {
        this.f9437a = strArr;
    }

    public Set<String> a() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int iC = c();
        for (int i = 0; i < iC; i++) {
            treeSet.add(a(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public a b() {
        a aVar = new a();
        Collections.addAll(aVar.f9438a, this.f9437a);
        return aVar;
    }

    public static us1 a(String... strArr) {
        if (strArr != null) {
            if (strArr.length % 2 == 0) {
                String[] strArr2 = (String[]) strArr.clone();
                for (int i = 0; i < strArr2.length; i++) {
                    if (strArr2[i] != null) {
                        strArr2[i] = strArr2[i].trim();
                    } else {
                        throw new IllegalArgumentException("Headers cannot be null");
                    }
                }
                for (int i2 = 0; i2 < strArr2.length; i2 += 2) {
                    String str = strArr2[i2];
                    String str2 = strArr2[i2 + 1];
                    c(str);
                    a(str2, str);
                }
                return new us1(strArr2);
            }
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        throw new NullPointerException("namesAndValues == null");
    }

    public static void a(String str, String str2) {
        if (str != null) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if ((cCharAt <= 31 && cCharAt != '\t') || cCharAt >= 127) {
                    throw new IllegalArgumentException(kt1.a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str2, str));
                }
            }
            return;
        }
        throw new NullPointerException("value for name " + str2 + " == null");
    }
}
