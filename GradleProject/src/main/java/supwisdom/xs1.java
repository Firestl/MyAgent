package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: MediaType.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xs1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f9805e = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public static final Pattern f = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9806a;
    public final String b;
    public final String c;

    @Nullable
    public final String d;

    public xs1(String str, String str2, String str3, @Nullable String str4) {
        this.f9806a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public static xs1 a(String str) {
        Matcher matcher = f9805e.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException("No subtype found for: \"" + str + Operators.QUOTE);
        }
        String lowerCase = matcher.group(1).toLowerCase(Locale.US);
        String lowerCase2 = matcher.group(2).toLowerCase(Locale.US);
        String str2 = null;
        Matcher matcher2 = f.matcher(str);
        for (int iEnd = matcher.end(); iEnd < str.length(); iEnd = matcher2.end()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                throw new IllegalArgumentException("Parameter is not formatted correctly: \"" + str.substring(iEnd) + "\" for: \"" + str + Operators.QUOTE);
            }
            String strGroup = matcher2.group(1);
            if (strGroup != null && strGroup.equalsIgnoreCase("charset")) {
                String strGroup2 = matcher2.group(2);
                if (strGroup2 == null) {
                    strGroup2 = matcher2.group(3);
                } else if (strGroup2.startsWith("'") && strGroup2.endsWith("'") && strGroup2.length() > 2) {
                    strGroup2 = strGroup2.substring(1, strGroup2.length() - 1);
                }
                if (str2 != null && !strGroup2.equalsIgnoreCase(str2)) {
                    throw new IllegalArgumentException("Multiple charsets defined: \"" + str2 + "\" and: \"" + strGroup2 + "\" for: \"" + str + Operators.QUOTE);
                }
                str2 = strGroup2;
            }
        }
        return new xs1(str, lowerCase, lowerCase2, str2);
    }

    @Nullable
    public static xs1 b(String str) {
        try {
            return a(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public String c() {
        return this.b;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof xs1) && ((xs1) obj).f9806a.equals(this.f9806a);
    }

    public int hashCode() {
        return this.f9806a.hashCode();
    }

    public String toString() {
        return this.f9806a;
    }

    public String b() {
        return this.c;
    }

    @Nullable
    public Charset a() {
        return a((Charset) null);
    }

    @Nullable
    public Charset a(@Nullable Charset charset) {
        try {
            return this.d != null ? Charset.forName(this.d) : charset;
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }
}
