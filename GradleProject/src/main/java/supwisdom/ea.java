package supwisdom;

import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: compiled from: TextUtilsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ea {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Locale f7458a = new Locale("", "");

    public static int a(Locale locale) {
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        return (directionality == 1 || directionality == 2) ? 1 : 0;
    }

    public static int b(Locale locale) {
        if (Build.VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(locale);
        }
        if (locale == null || locale.equals(f7458a)) {
            return 0;
        }
        String strB = aa.b(locale);
        return strB == null ? a(locale) : (strB.equalsIgnoreCase("Arab") || strB.equalsIgnoreCase("Hebr")) ? 1 : 0;
    }
}
