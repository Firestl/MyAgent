package supwisdom;

import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.sangfor.sdk.utils.SFLogN;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class u71 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Class<?> f9384a;
    public static Method b;

    static {
        try {
            Class<?> cls = Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP);
            f9384a = cls;
            b = cls.getDeclaredMethod("get", String.class, String.class);
        } catch (Exception e2) {
            SFLogN.a("SystemProperties", "getDeclaredMethod fail", "reflect error", e2);
        }
    }

    public static String a(String str, String str2) {
        try {
            String str3 = (String) b.invoke(f9384a, str, str2);
            return !TextUtils.isEmpty(str3) ? str3 : str2;
        } catch (Exception e2) {
            SFLogN.a("SystemProperties", "getString fail", "reflect invoke error", e2);
            return "";
        }
    }
}
