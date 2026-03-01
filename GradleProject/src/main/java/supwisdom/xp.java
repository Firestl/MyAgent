package supwisdom;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class xp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f9795a;

    public static synchronized void a(pp ppVar, Context context, String str, String str2) {
        try {
            String strA = bp.a(a(context), str2, str);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(strA)) {
                so.a(ppVar, com.umeng.analytics.c.f5149e, "TriDesDecryptError", String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, strA).apply();
        } catch (Throwable th) {
            vp.a(th);
        }
    }

    public static synchronized String b(pp ppVar, Context context, String str, String str2) {
        String strB;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            strB = TextUtils.isEmpty(string) ? null : bp.b(a(context), string, str);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(strB)) {
                so.a(ppVar, com.umeng.analytics.c.f5149e, "TriDesEncryptError", String.format("%s,%s", str, string));
            }
        } catch (Exception e2) {
            vp.a(e2);
        }
        return strB;
    }

    public static String a(Context context) {
        String packageName;
        if (TextUtils.isEmpty(f9795a)) {
            try {
                packageName = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                vp.a(th);
                packageName = "";
            }
            f9795a = (packageName + "0000000000000000000000000000").substring(0, 24);
        }
        return f9795a;
    }
}
