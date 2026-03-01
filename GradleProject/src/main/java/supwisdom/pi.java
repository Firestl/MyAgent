package supwisdom;

import bolts.UnobservedTaskException;
import supwisdom.ni;

/* JADX INFO: compiled from: UnobservedErrorNotifier.java */
/* JADX INFO: loaded from: classes.dex */
public class pi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ni<?> f8796a;

    public pi(ni<?> niVar) {
        this.f8796a = niVar;
    }

    public void a() {
        this.f8796a = null;
    }

    public void finalize() throws Throwable {
        ni.f fVarH;
        try {
            ni<?> niVar = this.f8796a;
            if (niVar != null && (fVarH = ni.h()) != null) {
                fVarH.a(niVar, new UnobservedTaskException(niVar.a()));
            }
        } finally {
            super.finalize();
        }
    }
}
