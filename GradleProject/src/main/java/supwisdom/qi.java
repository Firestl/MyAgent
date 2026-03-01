package supwisdom;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class qi implements hs1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ gp1 f8926a;

    public qi(ri riVar, gp1 gp1Var) {
        this.f8926a = gp1Var;
    }

    @Override // supwisdom.hs1
    public void onFailure(gs1 gs1Var, IOException iOException) {
        this.f8926a.a(iOException);
    }

    @Override // supwisdom.hs1
    public void onResponse(gs1 gs1Var, dt1 dt1Var) {
        this.f8926a.onResponse(gs1Var, dt1Var);
    }
}
