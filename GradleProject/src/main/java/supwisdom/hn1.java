package supwisdom;

import android.util.Log;
import com.auth0.android.jwt.JWT;
import java.util.Date;

/* JADX INFO: compiled from: TokenUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class hn1 {
    public static boolean a(String str, long j) {
        try {
            Date dateB = new JWT(str).b();
            Log.d("exp:", dateB.toString());
            Date date = new Date();
            date.setTime(date.getTime() + j);
            return dateB.before(date);
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public static String b(String str) {
        try {
            return new JWT(str).c();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String c(String str) {
        try {
            return new JWT(str).a().get("ATTR_userName").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String[] a(String str) {
        String[] strArr = new String[3];
        try {
            JWT jwt = new JWT(str);
            strArr[0] = jwt.a().get("ATTR_identityTypeName").a();
            strArr[1] = jwt.a().get("ATTR_organizationName").a();
            strArr[2] = "ATTR_userName";
            return strArr;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
