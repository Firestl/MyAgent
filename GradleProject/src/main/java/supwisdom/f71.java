package supwisdom;

import android.java.com.sangfor.sdk.utils.SPUtil;
import android.os.Handler;
import android.os.Looper;
import com.sangfor.sdk.SFSecuritySDK;
import com.sangfor.sdk.base.SFAuthMode;
import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.SFSDKType;
import com.sangfor.sdk.entry.SFServerSelector;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class f71 implements j81 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SFSecuritySDK f7573a;
    public k81 b;
    public l81 c;
    public z71 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d81 f7574e;
    public final Handler f;

    /* JADX INFO: compiled from: Proguard */
    public class a {
        public a(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b {
        public b(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class c {
        public c(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class d {
        public d(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class e {
        public e(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ l81 f7575a;

        public f(l81 l81Var) {
            this.f7575a = l81Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            f71.this.b.b(this.f7575a);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class g {
        public g(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class h {
        public h(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class i implements k81 {
        public i() {
        }

        @Override // supwisdom.k81
        public void a(l81 l81Var) {
            SFLogN.c("SFMobileSecuritySDK", "onAuthSuccess:" + l81Var.toString());
            f71.this.c = l81Var;
        }

        @Override // supwisdom.k81
        public void b(l81 l81Var) {
            SFLogN.c("SFMobileSecuritySDK", "onAuthFailed:" + l81Var.toString());
            f71.this.a(l81Var);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class j {
        public j(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class k {
        public k(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class l {
        public l(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class m {
        public m(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class n {
        public n(f71 f71Var) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static final class o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f71 f7577a = new f71(null);
    }

    public /* synthetic */ f71(a aVar) {
        this();
    }

    public static f71 c() {
        return o.f7577a;
    }

    public final boolean b() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public f71() {
        this.f7573a = null;
        SFSDKType sFSDKType = SFSDKType.SDK_TYPE_UNKNOWN;
        SFAuthMode sFAuthMode = SFAuthMode.Unknown;
        SFAuthType sFAuthType = SFAuthType.AUTH_TYPE_UNKNOWN;
        new HashMap();
        this.b = null;
        new HashSet();
        this.d = null;
        this.f7574e = null;
        this.f = new Handler(Looper.getMainLooper());
        new a(this);
        new g(this);
        new h(this);
        new i();
        new j(this);
        new k(this);
        new l(this);
        new m(this);
        new n(this);
        new b(this);
        new c(this);
        new d(this);
        new e(this);
    }

    @Override // supwisdom.j81
    public synchronized void a(SFAuthType sFAuthType, Map<String, String> map) {
        SFLogN.c("SFMobileSecuritySDK", "doSecondaryAuth called, authType: " + sFAuthType);
        SFSecuritySDK sFSecuritySDK = this.f7573a;
        if (sFSecuritySDK == null) {
            SFLogN.b("SFMobileSecuritySDK", "doSecondaryAuth called failed", "internal sdk is not initialized");
        } else {
            sFSecuritySDK.c().a(sFAuthType, map);
        }
    }

    @Override // supwisdom.j81
    public synchronized void a(n81 n81Var) {
        SFLogN.c("SFMobileSecuritySDK", "setTunnelStatusListener called");
        if (n81Var == null) {
            SFLogN.b("SFMobileSecuritySDK", "setTunnelStatusListener is null", "");
        }
    }

    public final void a(String str) {
        SPUtil.put("SP_LAST_SERVER_INPUT_URL", str);
    }

    public final synchronized void a(l81 l81Var) {
        SFLogN.a("SFMobileSecuritySDK", "dispatchAuthFailed called", "message: " + l81Var);
        z71 z71Var = this.d;
        if (z71Var == null) {
            d81 d81Var = this.f7574e;
            if (d81Var == null) {
                if (this.b != null) {
                    if (b()) {
                        this.b.b(l81Var);
                    } else {
                        this.f.post(new f(l81Var));
                    }
                } else {
                    SFLogN.a("AUTH_LOGOUT_FLOW", "can not callback onAuthFailed", "mAuthResultListener is null", "app will not receive auth failed callback", "check setAuthResultListener has been called and param is not null");
                }
                a();
            } else {
                d81Var.a(l81Var);
                throw null;
            }
        } else {
            z71Var.a(l81Var);
            throw null;
        }
    }

    public final void a() {
        SFServerSelector.a(SFSDKType.SDK_TYPE_UNKNOWN);
        a("");
    }
}
