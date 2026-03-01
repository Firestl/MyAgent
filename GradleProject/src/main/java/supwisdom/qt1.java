package supwisdom;

import java.io.IOException;
import supwisdom.ws1;

/* JADX INFO: compiled from: ConnectInterceptor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qt1 implements ws1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zs1 f8952a;

    public qt1(zs1 zs1Var) {
        this.f8952a = zs1Var;
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) throws IOException {
        cu1 cu1Var = (cu1) aVar;
        bt1 bt1VarRequest = cu1Var.request();
        vt1 vt1VarD = cu1Var.d();
        return cu1Var.a(bt1VarRequest, vt1VarD, vt1VarD.a(this.f8952a, aVar, !bt1VarRequest.e().equals("GET")), vt1VarD.c());
    }
}
