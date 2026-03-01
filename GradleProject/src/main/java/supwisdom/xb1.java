package supwisdom;

import android.text.TextUtils;
import com.sangfor.sdk.SFSecuritySDK;
import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class xb1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9737a;

    /* JADX INFO: compiled from: Proguard */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final xb1 f9738a = new xb1();
    }

    public static xb1 c() {
        return b.f9738a;
    }

    public boolean a() {
        SFSecuritySDK sFSecuritySDKA = b71.b().a();
        if (sFSecuritySDKA == null) {
            SFLogN.a("PrivacyUtil", "isAtrustApp called failed", "inner sdk is uninit");
            return false;
        }
        int iE = sFSecuritySDKA.e();
        if (!(((32768 & iE) != 0) || ((iE & 16) != 0))) {
            return !b();
        }
        SFLogN.c("PrivacyUtil", "current flag disallow intercept");
        return false;
    }

    public boolean b() {
        if (!this.f9737a) {
            SFSecuritySDK sFSecuritySDKA = b71.b().a();
            if (sFSecuritySDKA == null) {
                SFLogN.a("PrivacyUtil", "isAgreePrivacyPolicy called failed", "inner sdk is uninit");
                return false;
            }
            String strA = sFSecuritySDKA.a("consentAgreement");
            SFLogN.c("PrivacyUtil", "status:" + strA);
            this.f9737a = TextUtils.equals("true", strA);
        }
        return this.f9737a;
    }

    public xb1() {
    }
}
