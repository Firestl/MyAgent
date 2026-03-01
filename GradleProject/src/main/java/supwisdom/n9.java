package supwisdom;

import android.os.Build;
import android.os.LocaleList;
import com.taobao.weex.el.parse.Operators;
import java.util.Locale;

/* JADX INFO: compiled from: LocaleListCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class n9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p9 f8484a;

    static {
        a(new Locale[0]);
    }

    public n9(p9 p9Var) {
        this.f8484a = p9Var;
    }

    public static n9 a(LocaleList localeList) {
        return new n9(new q9(localeList));
    }

    public boolean equals(Object obj) {
        return (obj instanceof n9) && this.f8484a.equals(((n9) obj).f8484a);
    }

    public int hashCode() {
        return this.f8484a.hashCode();
    }

    public String toString() {
        return this.f8484a.toString();
    }

    public static n9 a(Locale... localeArr) {
        return Build.VERSION.SDK_INT >= 24 ? a(new LocaleList(localeArr)) : new n9(new o9(localeArr));
    }

    public Locale a(int i) {
        return this.f8484a.get(i);
    }

    public static Locale a(String str) {
        if (str.contains("-")) {
            String[] strArrSplit = str.split("-", -1);
            if (strArrSplit.length > 2) {
                return new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
            }
            if (strArrSplit.length > 1) {
                return new Locale(strArrSplit[0], strArrSplit[1]);
            }
            if (strArrSplit.length == 1) {
                return new Locale(strArrSplit[0]);
            }
        } else if (str.contains("_")) {
            String[] strArrSplit2 = str.split("_", -1);
            if (strArrSplit2.length > 2) {
                return new Locale(strArrSplit2[0], strArrSplit2[1], strArrSplit2[2]);
            }
            if (strArrSplit2.length > 1) {
                return new Locale(strArrSplit2[0], strArrSplit2[1]);
            }
            if (strArrSplit2.length == 1) {
                return new Locale(strArrSplit2[0]);
            }
        } else {
            return new Locale(str);
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + Operators.ARRAY_END_STR);
    }
}
