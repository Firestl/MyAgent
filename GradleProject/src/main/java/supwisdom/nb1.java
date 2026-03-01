package supwisdom;

import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import supwisdom.aa1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class nb1 {
    public static String a(Object obj) {
        String[] packagesForUid;
        String strA = aa1.a.c.a(obj);
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        IBinder iBinderA = aa1.a.b.a(obj);
        if (iBinderA == null) {
            return null;
        }
        try {
            String str = (String) da1.c.a(n91.a(), iBinderA);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        } catch (Exception unused) {
        }
        try {
            Integer num = (Integer) da1.b.a(n91.a(), iBinderA);
            if (num != null && (packagesForUid = n91.b().getPackageManager().getPackagesForUid(num.intValue())) != null && packagesForUid.length > 0) {
                strA = packagesForUid[0];
            }
        } catch (Exception unused2) {
        }
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return strA;
    }

    public static void b(Object obj) {
        Intent intentA = aa1.a.f6878a.a(obj);
        if (intentA != null) {
            intentA.setExtrasClassLoader(n91.b().getClassLoader());
            intentA.putExtra("com.sangfor.extra_calling_package", a(obj));
        }
    }
}
