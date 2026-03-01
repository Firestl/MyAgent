package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oe1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String[] f8660a;

    /* JADX INFO: compiled from: Headers.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f8661a = new ArrayList(20);

        public final b b(String str, String str2) {
            this.f8661a.add(str);
            this.f8661a.add(str2.trim());
            return this;
        }

        public b c(String str, String str2) {
            b(str);
            a(str, str2);
            return this;
        }

        public b a(String str) {
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

        public b b(String str) {
            int i = 0;
            while (i < this.f8661a.size()) {
                if (str.equalsIgnoreCase(this.f8661a.get(i))) {
                    this.f8661a.remove(i);
                    this.f8661a.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public b a(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (str2 != null) {
                if (str.length() != 0 && str.indexOf(0) == -1 && str2.indexOf(0) == -1) {
                    b(str, str2);
                    return this;
                }
                throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
            }
            throw new IllegalArgumentException("value == null");
        }

        public oe1 a() {
            return new oe1(this);
        }
    }

    public String a(String str) {
        return a(this.f8660a, str);
    }

    public Date b(String str) {
        String strA = a(str);
        if (strA != null) {
            return nf1.a(strA);
        }
        return null;
    }

    public List<String> c(String str) {
        int iB = b();
        ArrayList arrayList = null;
        for (int i = 0; i < iB; i++) {
            if (str.equalsIgnoreCase(a(i))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(b(i));
            }
        }
        return arrayList != null ? Collections.unmodifiableList(arrayList) : Collections.emptyList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iB = b();
        for (int i = 0; i < iB; i++) {
            sb.append(a(i));
            sb.append(": ");
            sb.append(b(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public oe1(b bVar) {
        this.f8660a = (String[]) bVar.f8661a.toArray(new String[bVar.f8661a.size()]);
    }

    public String a(int i) {
        int i2 = i * 2;
        if (i2 < 0) {
            return null;
        }
        String[] strArr = this.f8660a;
        if (i2 >= strArr.length) {
            return null;
        }
        return strArr[i2];
    }

    public int b() {
        return this.f8660a.length / 2;
    }

    public b a() {
        b bVar = new b();
        Collections.addAll(bVar.f8661a, this.f8660a);
        return bVar;
    }

    public String b(int i) {
        int i2 = (i * 2) + 1;
        if (i2 < 0) {
            return null;
        }
        String[] strArr = this.f8660a;
        if (i2 >= strArr.length) {
            return null;
        }
        return strArr[i2];
    }

    public static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }
}
