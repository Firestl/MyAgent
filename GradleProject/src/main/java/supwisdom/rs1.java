package supwisdom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Protocol;

/* JADX INFO: compiled from: EventListener.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class rs1 {
    public static final rs1 NONE = new a();

    /* JADX INFO: compiled from: EventListener.java */
    public class a extends rs1 {
    }

    /* JADX INFO: compiled from: EventListener.java */
    public class b implements c {
        public b() {
        }

        @Override // supwisdom.rs1.c
        public rs1 create(gs1 gs1Var) {
            return rs1.this;
        }
    }

    /* JADX INFO: compiled from: EventListener.java */
    public interface c {
        rs1 create(gs1 gs1Var);
    }

    public static c factory(rs1 rs1Var) {
        return rs1Var.new b();
    }

    public void callEnd(gs1 gs1Var) {
    }

    public void callFailed(gs1 gs1Var, IOException iOException) {
    }

    public void callStart(gs1 gs1Var) {
    }

    public void connectEnd(gs1 gs1Var, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
    }

    public void connectFailed(gs1 gs1Var, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException iOException) {
    }

    public void connectStart(gs1 gs1Var, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void connectionAcquired(gs1 gs1Var, ks1 ks1Var) {
    }

    public void connectionReleased(gs1 gs1Var, ks1 ks1Var) {
    }

    public void dnsEnd(gs1 gs1Var, String str, List<InetAddress> list) {
    }

    public void dnsStart(gs1 gs1Var, String str) {
    }

    public void requestBodyEnd(gs1 gs1Var, long j) {
    }

    public void requestBodyStart(gs1 gs1Var) {
    }

    public void requestHeadersEnd(gs1 gs1Var, bt1 bt1Var) {
    }

    public void requestHeadersStart(gs1 gs1Var) {
    }

    public void responseBodyEnd(gs1 gs1Var, long j) {
    }

    public void responseBodyStart(gs1 gs1Var) {
    }

    public void responseHeadersEnd(gs1 gs1Var, dt1 dt1Var) {
    }

    public void responseHeadersStart(gs1 gs1Var) {
    }

    public void secureConnectEnd(gs1 gs1Var, @Nullable ts1 ts1Var) {
    }

    public void secureConnectStart(gs1 gs1Var) {
    }
}
