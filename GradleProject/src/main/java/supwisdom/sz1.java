package supwisdom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: CompositeSubscription.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sz1 implements yw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<yw1> f9222a;
    public volatile boolean b;

    public void a(yw1 yw1Var) {
        if (yw1Var.isUnsubscribed()) {
            return;
        }
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    if (this.f9222a == null) {
                        this.f9222a = new HashSet(4);
                    }
                    this.f9222a.add(yw1Var);
                    return;
                }
            }
        }
        yw1Var.unsubscribe();
    }

    public void b(yw1 yw1Var) {
        if (this.b) {
            return;
        }
        synchronized (this) {
            if (!this.b && this.f9222a != null) {
                boolean zRemove = this.f9222a.remove(yw1Var);
                if (zRemove) {
                    yw1Var.unsubscribe();
                }
            }
        }
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        return this.b;
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        if (this.b) {
            return;
        }
        synchronized (this) {
            if (this.b) {
                return;
            }
            this.b = true;
            Set<yw1> set = this.f9222a;
            this.f9222a = null;
            a(set);
        }
    }

    public static void a(Collection<yw1> collection) {
        if (collection == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<yw1> it = collection.iterator();
        while (it.hasNext()) {
            try {
                it.next().unsubscribe();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        zw1.a(arrayList);
    }
}
