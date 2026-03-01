package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.xiaomi.mipush.sdk.Constants;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class ac0 {
    public static final Lock c = new ReentrantLock();

    @GuardedBy("sLk")
    public static ac0 d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Lock f6885a = new ReentrantLock();

    @GuardedBy("mLk")
    public final SharedPreferences b;

    public ac0(Context context) {
        this.b = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    @RecentlyNonNull
    public static ac0 a(@RecentlyNonNull Context context) {
        pf0.a(context);
        c.lock();
        try {
            if (d == null) {
                d = new ac0(context.getApplicationContext());
            }
            return d;
        } finally {
            c.unlock();
        }
    }

    public final String b(String str) {
        this.f6885a.lock();
        try {
            return this.b.getString(str, null);
        } finally {
            this.f6885a.unlock();
        }
    }

    @RecentlyNullable
    public GoogleSignInAccount a() {
        return a(b("defaultGoogleSignInAccount"));
    }

    public final GoogleSignInAccount a(String str) {
        String strB;
        if (!TextUtils.isEmpty(str) && (strB = b(a("googleSignInAccount", str))) != null) {
            try {
                return GoogleSignInAccount.a(strB);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(str2);
        return sb.toString();
    }
}
