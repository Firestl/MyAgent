package supwisdom;

import android.content.Context;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class qp {
    public static qp b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8944a;

    public static qp d() {
        if (b == null) {
            b = new qp();
        }
        return b;
    }

    public static boolean e() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    public Context a() {
        return this.f8944a;
    }

    public wo b() {
        return wo.a();
    }

    public String c() {
        try {
            return UTDevice.getUtdid(this.f8944a);
        } catch (Throwable th) {
            vp.a(th);
            return "getUtdidEx";
        }
    }

    public void a(Context context) {
        wo.a();
        this.f8944a = context.getApplicationContext();
    }
}
