package supwisdom;

import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: RouteDatabase.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ff1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<we1> f7601a = new LinkedHashSet();

    public synchronized void a(we1 we1Var) {
        this.f7601a.remove(we1Var);
    }

    public synchronized void b(we1 we1Var) {
        this.f7601a.add(we1Var);
    }

    public synchronized boolean c(we1 we1Var) {
        return this.f7601a.contains(we1Var);
    }
}
