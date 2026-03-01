package supwisdom;

import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.SFErrorCode;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class c81 {
    public static SFErrorCode[] b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k81 f7162a;

    /* JADX INFO: compiled from: Proguard */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c81 f7163a = new c81(null);
    }

    static {
        SFErrorCode sFErrorCode = SFErrorCode.SF_ERROR_ADMIN_UNAUTHORIZED;
        SFErrorCode sFErrorCode2 = SFErrorCode.SF_ERROR_INVALID_SESSION;
        SFErrorCode sFErrorCode3 = SFErrorCode.SF_ERROR_SESSION_TIME_OUT;
        SFErrorCode sFErrorCode4 = SFErrorCode.SF_ERROR_AUTH_STATUE;
        SFErrorCode sFErrorCode5 = SFErrorCode.SF_ERROR_UPDATE_PWD_STRATEGY;
        b = new SFErrorCode[]{SFErrorCode.SF_ERROR_CODE_CONNECT_SERVER, SFErrorCode.SF_ERROR_CODE_CONNECT_TIME_OUT, SFErrorCode.SF_ERROR_CODE_CONNECT_ERROR, SFErrorCode.SF_ERROR_CONNECT_VPN_FAILED};
    }

    public /* synthetic */ c81(b81 b81Var) {
        this();
    }

    public static c81 a() {
        return a.f7163a;
    }

    public static boolean b(l81 l81Var) {
        int length = b.length;
        for (int i = 0; i < length; i++) {
            if (l81Var.f8262a == b[i].value()) {
                return true;
            }
        }
        return false;
    }

    public c81() {
        this.f7162a = null;
        SFAuthType sFAuthType = SFAuthType.AUTH_TYPE_AUTH_CHECK;
        SFAuthType sFAuthType2 = SFAuthType.AUTH_TYPE_PURE_BIND_AUTH_DEVICE;
        SFAuthType sFAuthType3 = SFAuthType.AUTH_TYPE_APPLY_BIND_AUTH_DEVICE;
        SFAuthType sFAuthType4 = SFAuthType.AUTH_TYPE_UNBIND_AUTH_DEVICE;
        SFAuthType sFAuthType5 = SFAuthType.AUTH_TYPE_PURE_TRUST_DEVICE;
        SFAuthType sFAuthType6 = SFAuthType.AUTH_TYPE_APPLY_TRUST_DEVICE;
        SFAuthType sFAuthType7 = SFAuthType.AUTH_TYPE_UNBIND_TRUST_DEVICE;
        SFAuthType sFAuthType8 = SFAuthType.AUTH_TYPE_PRIMARY_SMS;
        SFAuthType sFAuthType9 = SFAuthType.AUTH_TYPE_SMS;
        SFAuthType sFAuthType10 = SFAuthType.AUTH_TYPE_RAND;
        SFAuthType sFAuthType11 = SFAuthType.AUTH_TYPE_RENEW_PASSWORD;
    }

    public void a(SFAuthType sFAuthType, Map<String, String> map) {
        SFLogN.c("AuthHelper", "doSecondAuth called");
        if (map == null) {
            map = new HashMap<>();
        }
        d71.a().a(sFAuthType, map);
    }

    public void a(f81 f81Var) {
        SFLogN.c("AuthHelper", "setRouteCallback called");
        synchronized (this) {
        }
    }

    public void a(l81 l81Var) {
        k81 k81Var = this.f7162a;
        if (k81Var == null) {
            SFLogN.a("AuthHelper", "handleAuthBack failed", "mAuthResultListener is null");
        } else {
            k81Var.b(l81Var);
        }
    }
}
