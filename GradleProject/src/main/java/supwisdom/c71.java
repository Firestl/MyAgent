package supwisdom;

import android.os.Handler;
import android.os.Looper;
import com.sangfor.sdk.base.SFTunnelStatus;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class c71 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<n81> f7153a = new HashSet();
    public volatile boolean b;
    public final n81 c;

    /* JADX INFO: compiled from: Proguard */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SFLogN.c("SFTunnel", "StartTunnelTimeout is come, clear Cached event");
            c71.this.b = false;
            if (c71.this.c != null) {
                c71.this.c.a(new l81(10006L, "网络异常，启动隧道超时，请稍后重试", ""));
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements n81 {
        public b() {
        }

        @Override // supwisdom.n81
        public void a(l81 l81Var) {
            synchronized (this) {
                SFLogN.c("SFTunnel", "onTunnelStarted, msg: " + l81Var.b + ",errCode: " + l81Var.f8262a);
                if (c71.this.f7153a.isEmpty()) {
                    SFLogN.b("SFTunnel", "not callback onTunnelStarted", "mTunnelStatusListeners is empty");
                    return;
                }
                int size = c71.this.f7153a.size();
                n81[] n81VarArr = new n81[size];
                c71.this.f7153a.toArray(n81VarArr);
                for (int i = 0; i < size; i++) {
                    n81 n81Var = n81VarArr[i];
                    SFLogN.c("SFTunnel", "callback onTunnelStarted, listener: " + n81Var);
                    n81Var.a(l81Var);
                }
            }
        }
    }

    public c71(j81 j81Var) {
        SFTunnelStatus sFTunnelStatus = SFTunnelStatus.INIT;
        new Handler(Looper.getMainLooper());
        new a();
        b bVar = new b();
        this.c = bVar;
        if (j81Var != null) {
            j81Var.a(bVar);
        } else {
            SFLogN.a("SFTunnel", "SFTunnel init failed, user Tunnel Callback not worked", "mobileSecuritySDK is null");
        }
    }
}
