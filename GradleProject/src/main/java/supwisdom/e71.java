package supwisdom;

import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.SFErrorCode;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class e71 implements j81 {
    public static ExecutorService b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k81 f7445a;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f7446a;
        public final /* synthetic */ c b;

        public a(int i, c cVar) {
            this.f7446a = i;
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(this.f7446a * 1000);
                c cVar = this.b;
                if (cVar != null) {
                    cVar.call();
                }
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements c {
        public b() {
        }

        @Override // supwisdom.e71.c
        public void call() {
            e71.a(true);
            e71.this.f7445a.a(new l81(SFErrorCode.SF_ERROR_CODE_NONE.value(), "AUTH_TYPE_NONE", ""));
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public interface c {
        void call();
    }

    /* JADX INFO: compiled from: Proguard */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e71 f7448a = new e71();
    }

    public e71() {
        new HashSet();
    }

    public static /* synthetic */ boolean a(boolean z) {
        return z;
    }

    @Override // supwisdom.j81
    public void a(SFAuthType sFAuthType, Map<String, String> map) {
        SFLogN.c("SFEmptySecuritySDKWrapper", "doSecondaryAuth called");
        if (this.f7445a != null) {
            a(new b(), 1);
        }
    }

    @Override // supwisdom.j81
    public void a(n81 n81Var) {
        SFLogN.c("SFEmptySecuritySDKWrapper", "setTunnelStatusListener called");
        if (n81Var == null) {
            SFLogN.b("SFEmptySecuritySDKWrapper", "setTunnelStatusListener is null", "");
        }
    }

    public static e71 a() {
        return d.f7448a;
    }

    public static void a(c cVar, int i) {
        if (b == null) {
            b = Executors.newSingleThreadExecutor();
        }
        b.execute(new a(i, cVar));
    }
}
