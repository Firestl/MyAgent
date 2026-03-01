package supwisdom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: SubscriptionList.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fy1 implements yw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<yw1> f7666a;
    public volatile boolean b;

    public fy1() {
    }

    public void a(yw1 yw1Var) {
        if (yw1Var.isUnsubscribed()) {
            return;
        }
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    List linkedList = this.f7666a;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f7666a = linkedList;
                    }
                    linkedList.add(yw1Var);
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
            List<yw1> list = this.f7666a;
            if (!this.b && list != null) {
                boolean zRemove = list.remove(yw1Var);
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
            List<yw1> list = this.f7666a;
            this.f7666a = null;
            a(list);
        }
    }

    public fy1(yw1... yw1VarArr) {
        this.f7666a = new LinkedList(Arrays.asList(yw1VarArr));
    }

    public fy1(yw1 yw1Var) {
        LinkedList linkedList = new LinkedList();
        this.f7666a = linkedList;
        linkedList.add(yw1Var);
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
