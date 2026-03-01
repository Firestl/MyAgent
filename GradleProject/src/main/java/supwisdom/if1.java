package supwisdom;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import supwisdom.se1;

/* JADX INFO: compiled from: AuthenticatorAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class if1 implements be1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final be1 f7953a = new if1();

    @Override // supwisdom.be1
    public se1 a(Proxy proxy, ue1 ue1Var) throws IOException {
        List<he1> listD = ue1Var.d();
        se1 se1VarL = ue1Var.l();
        URL urlH = se1VarL.h();
        int size = listD.size();
        for (int i = 0; i < size; i++) {
            he1 he1Var = listD.get(i);
            if ("Basic".equalsIgnoreCase(he1Var.b())) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), a(proxy, urlH), inetSocketAddress.getPort(), urlH.getProtocol(), he1Var.a(), he1Var.b(), urlH, Authenticator.RequestorType.PROXY);
                if (passwordAuthenticationRequestPasswordAuthentication != null) {
                    String strA = le1.a(passwordAuthenticationRequestPasswordAuthentication.getUserName(), new String(passwordAuthenticationRequestPasswordAuthentication.getPassword()));
                    se1.b bVarF = se1VarL.f();
                    bVarF.b("Proxy-Authorization", strA);
                    return bVarF.a();
                }
            }
        }
        return null;
    }

    @Override // supwisdom.be1
    public se1 b(Proxy proxy, ue1 ue1Var) throws IOException {
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        List<he1> listD = ue1Var.d();
        se1 se1VarL = ue1Var.l();
        URL urlH = se1VarL.h();
        int size = listD.size();
        for (int i = 0; i < size; i++) {
            he1 he1Var = listD.get(i);
            if ("Basic".equalsIgnoreCase(he1Var.b()) && (passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(urlH.getHost(), a(proxy, urlH), urlH.getPort(), urlH.getProtocol(), he1Var.a(), he1Var.b(), urlH, Authenticator.RequestorType.SERVER)) != null) {
                String strA = le1.a(passwordAuthenticationRequestPasswordAuthentication.getUserName(), new String(passwordAuthenticationRequestPasswordAuthentication.getPassword()));
                se1.b bVarF = se1VarL.f();
                bVarF.b("Authorization", strA);
                return bVarF.a();
            }
        }
        return null;
    }

    public final InetAddress a(Proxy proxy, URL url) throws IOException {
        if (proxy != null && proxy.type() != Proxy.Type.DIRECT) {
            return ((InetSocketAddress) proxy.address()).getAddress();
        }
        return InetAddress.getByName(url.getHost());
    }
}
