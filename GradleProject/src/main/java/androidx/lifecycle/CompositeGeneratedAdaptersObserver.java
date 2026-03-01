package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import supwisdom.be;
import supwisdom.td;
import supwisdom.vd;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements vd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final td[] f1307a;

    public CompositeGeneratedAdaptersObserver(td[] tdVarArr) {
        this.f1307a = tdVarArr;
    }

    @Override // supwisdom.vd
    public void a(xd xdVar, Lifecycle.Event event) {
        be beVar = new be();
        for (td tdVar : this.f1307a) {
            tdVar.a(xdVar, event, false, beVar);
        }
        for (td tdVar2 : this.f1307a) {
            tdVar2.a(xdVar, event, true, beVar);
        }
    }
}
