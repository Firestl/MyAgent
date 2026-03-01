package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import supwisdom.zs1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class gr1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public gt1 f7766a;
    public zs1 b;
    public bt1 c;
    public OnReceivePaySocketMessageListener d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f7767e;
    public String f;

    public class b implements HostnameVerifier {
        public b(gr1 gr1Var) {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    public gr1(String str, String str2) {
        a();
        this.f7767e = str;
        this.f = str2;
        this.c = a(str);
    }

    public abstract bt1 a(String str);

    public final void a() {
        zs1.b bVar = new zs1.b();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        bVar.b(10L, timeUnit);
        bVar.a(90000L, timeUnit);
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, new TrustManager[]{new or1()}, new SecureRandom());
            bVar.a(sSLContext.getSocketFactory());
            bVar.a(new b(this));
            bVar.a(new a(this));
            this.b = bVar.a();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public class a implements os1 {
        public final HashMap<vs1, List<ns1>> b = new HashMap<>();

        public a(gr1 gr1Var) {
        }

        @Override // supwisdom.os1
        public List<ns1> a(vs1 vs1Var) {
            List<ns1> list = this.b.get(vs1Var);
            return list != null ? list : new ArrayList();
        }

        @Override // supwisdom.os1
        public void a(vs1 vs1Var, List<ns1> list) {
            this.b.put(vs1Var, list);
        }
    }
}
