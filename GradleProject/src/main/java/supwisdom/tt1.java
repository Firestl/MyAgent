package supwisdom;

import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: RouteDatabase.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tt1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set<ft1> f9315a = new LinkedHashSet();

    public synchronized void a(ft1 ft1Var) {
        this.f9315a.remove(ft1Var);
    }

    public synchronized void b(ft1 ft1Var) {
        this.f9315a.add(ft1Var);
    }

    public synchronized boolean c(ft1 ft1Var) {
        return this.f9315a.contains(ft1Var);
    }
}
