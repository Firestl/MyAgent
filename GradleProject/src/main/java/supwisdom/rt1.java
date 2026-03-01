package supwisdom;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpecSelector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rt1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<ms1> f9091a;
    public int b = 0;
    public boolean c;
    public boolean d;

    public rt1(List<ms1> list) {
        this.f9091a = list;
    }

    public ms1 a(SSLSocket sSLSocket) throws IOException {
        ms1 ms1Var;
        int i = this.b;
        int size = this.f9091a.size();
        while (true) {
            if (i >= size) {
                ms1Var = null;
                break;
            }
            ms1Var = this.f9091a.get(i);
            if (ms1Var.a(sSLSocket)) {
                this.b = i + 1;
                break;
            }
            i++;
        }
        if (ms1Var != null) {
            this.c = b(sSLSocket);
            it1.f7984a.a(ms1Var, sSLSocket, this.d);
            return ms1Var;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.d + ", modes=" + this.f9091a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    public final boolean b(SSLSocket sSLSocket) {
        for (int i = this.b; i < this.f9091a.size(); i++) {
            if (this.f9091a.get(i).a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(IOException iOException) {
        this.d = true;
        if (!this.c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException) || (iOException instanceof SSLException);
    }
}
