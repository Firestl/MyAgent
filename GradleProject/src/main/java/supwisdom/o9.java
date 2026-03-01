package supwisdom;

import com.google.zxing.client.android.LocaleManager;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

/* JADX INFO: compiled from: LocaleListCompatWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class o9 implements p9 {
    public static final Locale[] b = new Locale[0];
    public static final Locale c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Locale[] f8645a;

    static {
        new Locale(LocaleManager.DEFAULT_LANGUAGE, "XA");
        new Locale("ar", "XB");
        c = n9.a("en-Latn");
    }

    public o9(Locale... localeArr) {
        if (localeArr.length == 0) {
            this.f8645a = b;
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < localeArr.length; i++) {
            Locale locale = localeArr[i];
            if (locale == null) {
                throw new NullPointerException("list[" + i + "] is null");
            }
            if (!hashSet.contains(locale)) {
                Locale locale2 = (Locale) locale.clone();
                arrayList.add(locale2);
                a(sb, locale2);
                if (i < localeArr.length - 1) {
                    sb.append(',');
                }
                hashSet.add(locale2);
            }
        }
        this.f8645a = (Locale[]) arrayList.toArray(new Locale[arrayList.size()]);
        sb.toString();
    }

    public static void a(StringBuilder sb, Locale locale) {
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (country == null || country.isEmpty()) {
            return;
        }
        sb.append(FunctionParser.Lexer.MINUS);
        sb.append(locale.getCountry());
    }

    @Override // supwisdom.p9
    public Object a() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof o9)) {
            return false;
        }
        Locale[] localeArr = ((o9) obj).f8645a;
        if (this.f8645a.length != localeArr.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Locale[] localeArr2 = this.f8645a;
            if (i >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i].equals(localeArr[i])) {
                return false;
            }
            i++;
        }
    }

    @Override // supwisdom.p9
    public Locale get(int i) {
        if (i >= 0) {
            Locale[] localeArr = this.f8645a;
            if (i < localeArr.length) {
                return localeArr[i];
            }
        }
        return null;
    }

    public int hashCode() {
        int iHashCode = 1;
        int i = 0;
        while (true) {
            Locale[] localeArr = this.f8645a;
            if (i >= localeArr.length) {
                return iHashCode;
            }
            iHashCode = (iHashCode * 31) + localeArr[i].hashCode();
            i++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR);
        int i = 0;
        while (true) {
            Locale[] localeArr = this.f8645a;
            if (i >= localeArr.length) {
                sb.append(Operators.ARRAY_END_STR);
                return sb.toString();
            }
            sb.append(localeArr[i]);
            if (i < this.f8645a.length - 1) {
                sb.append(',');
            }
            i++;
        }
    }
}
