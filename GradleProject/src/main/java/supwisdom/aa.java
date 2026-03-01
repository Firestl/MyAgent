package supwisdom;

import android.icu.util.ULocale;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* JADX INFO: compiled from: ICUCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f6876a;
    public static Method b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (i < 24) {
                try {
                    b = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
                    return;
                } catch (Exception e2) {
                    throw new IllegalStateException(e2);
                }
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("libcore.icu.ICU");
            if (cls != null) {
                f6876a = cls.getMethod("getScript", String.class);
                b = cls.getMethod("addLikelySubtags", String.class);
            }
        } catch (Exception e3) {
            f6876a = null;
            b = null;
            Log.w("ICUCompat", e3);
        }
    }

    public static String a(String str) {
        try {
            if (f6876a != null) {
                return (String) f6876a.invoke(null, str);
            }
        } catch (IllegalAccessException e2) {
            Log.w("ICUCompat", e2);
        } catch (InvocationTargetException e3) {
            Log.w("ICUCompat", e3);
        }
        return null;
    }

    public static String b(Locale locale) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return ULocale.addLikelySubtags(ULocale.forLocale(locale)).getScript();
        }
        if (i < 21) {
            String strA = a(locale);
            if (strA != null) {
                return a(strA);
            }
            return null;
        }
        try {
            return ((Locale) b.invoke(null, locale)).getScript();
        } catch (IllegalAccessException e2) {
            Log.w("ICUCompat", e2);
            return locale.getScript();
        } catch (InvocationTargetException e3) {
            Log.w("ICUCompat", e3);
            return locale.getScript();
        }
    }

    public static String a(Locale locale) {
        String string = locale.toString();
        try {
            if (b != null) {
                return (String) b.invoke(null, string);
            }
        } catch (IllegalAccessException e2) {
            Log.w("ICUCompat", e2);
        } catch (InvocationTargetException e3) {
            Log.w("ICUCompat", e3);
        }
        return string;
    }
}
