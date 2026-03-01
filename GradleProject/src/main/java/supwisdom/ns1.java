package supwisdom;

import com.taobao.weex.appfram.pickers.DatePickerImpl;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.internal.http.HttpDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ns1 {
    public static final Pattern j = Pattern.compile("(\\d{2,4})[^\\d]*");
    public static final Pattern k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    public static final Pattern l = Pattern.compile("(\\d{1,2})[^\\d]*");
    public static final Pattern m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8558a;
    public final String b;
    public final long c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f8559e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;

    /* JADX INFO: compiled from: Cookie.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public String f8560a;

        @Nullable
        public String b;

        @Nullable
        public String d;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public long c = HttpDate.MAX_DATE;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8561e = "/";

        public a a(long j) {
            if (j <= 0) {
                j = Long.MIN_VALUE;
            }
            if (j > HttpDate.MAX_DATE) {
                j = 253402300799999L;
            }
            this.c = j;
            this.h = true;
            return this;
        }

        public a b(String str) {
            a(str, true);
            return this;
        }

        public a c(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("name is not trimmed");
            }
            this.f8560a = str;
            return this;
        }

        public a d(String str) {
            if (!str.startsWith("/")) {
                throw new IllegalArgumentException("path must start with '/'");
            }
            this.f8561e = str;
            return this;
        }

        public a e(String str) {
            if (str == null) {
                throw new NullPointerException("value == null");
            }
            if (!str.trim().equals(str)) {
                throw new IllegalArgumentException("value is not trimmed");
            }
            this.b = str;
            return this;
        }

        public a b() {
            this.g = true;
            return this;
        }

        public a a(String str) {
            a(str, false);
            return this;
        }

        public final a a(String str, boolean z) {
            if (str != null) {
                String strA = kt1.a(str);
                if (strA != null) {
                    this.d = strA;
                    this.i = z;
                    return this;
                }
                throw new IllegalArgumentException("unexpected domain: " + str);
            }
            throw new NullPointerException("domain == null");
        }

        public a c() {
            this.f = true;
            return this;
        }

        public ns1 a() {
            return new ns1(this);
        }
    }

    public ns1(String str, String str2, long j2, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f8558a = str;
        this.b = str2;
        this.c = j2;
        this.d = str3;
        this.f8559e = str4;
        this.f = z;
        this.g = z2;
        this.i = z3;
        this.h = z4;
    }

    public String a() {
        return this.d;
    }

    public long b() {
        return this.c;
    }

    public boolean c() {
        return this.i;
    }

    public boolean d() {
        return this.g;
    }

    public String e() {
        return this.f8558a;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof ns1)) {
            return false;
        }
        ns1 ns1Var = (ns1) obj;
        return ns1Var.f8558a.equals(this.f8558a) && ns1Var.b.equals(this.b) && ns1Var.d.equals(this.d) && ns1Var.f8559e.equals(this.f8559e) && ns1Var.c == this.c && ns1Var.f == this.f && ns1Var.g == this.g && ns1Var.h == this.h && ns1Var.i == this.i;
    }

    public String f() {
        return this.f8559e;
    }

    public boolean g() {
        return this.h;
    }

    public boolean h() {
        return this.f;
    }

    public int hashCode() {
        int iHashCode = (((((((527 + this.f8558a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.f8559e.hashCode()) * 31;
        long j2 = this.c;
        return ((((((((iHashCode + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (!this.f ? 1 : 0)) * 31) + (!this.g ? 1 : 0)) * 31) + (!this.h ? 1 : 0)) * 31) + (!this.i ? 1 : 0);
    }

    public String i() {
        return this.b;
    }

    public String toString() {
        return a(false);
    }

    public static boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        return str.endsWith(str2) && str.charAt((str.length() - str2.length()) - 1) == '.' && !kt1.d(str);
    }

    public static long b(String str) {
        try {
            long j2 = Long.parseLong(str);
            if (j2 <= 0) {
                return Long.MIN_VALUE;
            }
            return j2;
        } catch (NumberFormatException e2) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e2;
        }
    }

    @Nullable
    public static ns1 a(vs1 vs1Var, String str) {
        return a(System.currentTimeMillis(), vs1Var, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c0 A[PHI: r0
  0x00c0: PHI (r0v15 long) = (r0v2 long), (r0v5 long) binds: [B:42:0x00be, B:53:0x00e1] A[DONT_GENERATE, DONT_INLINE]] */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.ns1 a(long r23, supwisdom.vs1 r25, java.lang.String r26) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.ns1.a(long, supwisdom.vs1, java.lang.String):supwisdom.ns1");
    }

    public ns1(a aVar) {
        String str = aVar.f8560a;
        if (str != null) {
            String str2 = aVar.b;
            if (str2 != null) {
                String str3 = aVar.d;
                if (str3 != null) {
                    this.f8558a = str;
                    this.b = str2;
                    this.c = aVar.c;
                    this.d = str3;
                    this.f8559e = aVar.f8561e;
                    this.f = aVar.f;
                    this.g = aVar.g;
                    this.h = aVar.h;
                    this.i = aVar.i;
                    return;
                }
                throw new NullPointerException("builder.domain == null");
            }
            throw new NullPointerException("builder.value == null");
        }
        throw new NullPointerException("builder.name == null");
    }

    public static long a(String str, int i, int i2) {
        int iA = a(str, i, i2, false);
        Matcher matcher = m.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int iIndexOf = -1;
        int i6 = -1;
        int i7 = -1;
        while (iA < i2) {
            int iA2 = a(str, iA + 1, i2, true);
            matcher.region(iA, iA2);
            if (i4 == -1 && matcher.usePattern(m).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
                i6 = Integer.parseInt(matcher.group(2));
                i7 = Integer.parseInt(matcher.group(3));
            } else if (i5 == -1 && matcher.usePattern(l).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else if (iIndexOf == -1 && matcher.usePattern(k).matches()) {
                iIndexOf = k.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i3 == -1 && matcher.usePattern(j).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
            }
            iA = a(str, iA2 + 1, i2, false);
        }
        if (i3 >= 70 && i3 <= 99) {
            i3 += DatePickerImpl.DEFAULT_START_YEAR;
        }
        if (i3 >= 0 && i3 <= 69) {
            i3 += 2000;
        }
        if (i3 < 1601) {
            throw new IllegalArgumentException();
        }
        if (iIndexOf == -1) {
            throw new IllegalArgumentException();
        }
        if (i5 < 1 || i5 > 31) {
            throw new IllegalArgumentException();
        }
        if (i4 < 0 || i4 > 23) {
            throw new IllegalArgumentException();
        }
        if (i6 < 0 || i6 > 59) {
            throw new IllegalArgumentException();
        }
        if (i7 >= 0 && i7 <= 59) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(kt1.n);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i3);
            gregorianCalendar.set(2, iIndexOf - 1);
            gregorianCalendar.set(5, i5);
            gregorianCalendar.set(11, i4);
            gregorianCalendar.set(12, i6);
            gregorianCalendar.set(13, i7);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
        throw new IllegalArgumentException();
    }

    public static int a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static String a(String str) {
        if (!str.endsWith(Operators.DOT_STR)) {
            if (str.startsWith(Operators.DOT_STR)) {
                str = str.substring(1);
            }
            String strA = kt1.a(str);
            if (strA != null) {
                return strA;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    public static List<ns1> a(vs1 vs1Var, us1 us1Var) {
        List<String> listB = us1Var.b("Set-Cookie");
        int size = listB.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ns1 ns1VarA = a(vs1Var, listB.get(i));
            if (ns1VarA != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(ns1VarA);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public String a(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8558a);
        sb.append('=');
        sb.append(this.b);
        if (this.h) {
            if (this.c == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(zt1.a(new Date(this.c)));
            }
        }
        if (!this.i) {
            sb.append("; domain=");
            if (z) {
                sb.append(Operators.DOT_STR);
            }
            sb.append(this.d);
        }
        sb.append("; path=");
        sb.append(this.f8559e);
        if (this.f) {
            sb.append("; secure");
        }
        if (this.g) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
}
