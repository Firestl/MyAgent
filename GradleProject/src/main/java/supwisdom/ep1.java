package supwisdom;

import supwisdom.bt1;
import supwisdom.ws1;

/* JADX INFO: loaded from: classes2.dex */
public class ep1 implements ws1 {
    public ep1(fp1 fp1Var) {
    }

    @Override // supwisdom.ws1
    public dt1 intercept(ws1.a aVar) {
        bt1.a aVarF = aVar.request().f();
        aVarF.a("synjones-auth", "bearer " + dq1.c().b());
        return aVar.a(aVarF.a());
    }
}
