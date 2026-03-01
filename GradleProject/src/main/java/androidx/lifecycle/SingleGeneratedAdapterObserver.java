package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import supwisdom.td;
import supwisdom.vd;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements vd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final td f1317a;

    public SingleGeneratedAdapterObserver(td tdVar) {
        this.f1317a = tdVar;
    }

    @Override // supwisdom.vd
    public void a(xd xdVar, Lifecycle.Event event) {
        this.f1317a.a(xdVar, event, false, null);
        this.f1317a.a(xdVar, event, true, null);
    }
}
