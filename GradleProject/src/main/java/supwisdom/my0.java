package supwisdom;

import android.util.Pair;
import com.taobao.weex.el.parse.Operators;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class my0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f8452a = "Sangfor SDP VPN";
    public Set<Pair<String, Integer>> b = new HashSet();
    public Set<Pair<String, Integer>> c = new HashSet();
    public Set<String> d = new HashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Set<String> f8453e = new HashSet();
    public int h = 1500;
    public Set<String> f = new HashSet();
    public Set<String> g = new HashSet();

    public boolean equals(Object obj) {
        ky0.a("TunConfig", "equals called, this:" + this + " obj:" + obj);
        if (!(obj instanceof my0)) {
            return false;
        }
        my0 my0Var = (my0) obj;
        return this.h == my0Var.h && this.d.equals(my0Var.d) && this.c.equals(my0Var.c);
    }

    public String toString() {
        return "TunConfig{session='" + this.f8452a + Operators.SINGLE_QUOTE + ", addresses=" + this.b + ", routes=" + this.c + ", dnsServers=" + this.d + ", systemDefaultDnsServers=" + this.f + ",disAllowedPackages: " + this.g + ", searchDomains=" + this.f8453e + ", mtu=" + this.h + Operators.BLOCK_END;
    }
}
