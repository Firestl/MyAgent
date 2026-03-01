package supwisdom;

import java.util.Iterator;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public class hq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Stack<gq> f7863a = new Stack<>();

    public gq a() {
        return this.f7863a.pop();
    }

    public boolean b() {
        return this.f7863a.isEmpty();
    }

    public void c() {
        if (b()) {
            return;
        }
        Iterator<gq> it = this.f7863a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f7863a.clear();
    }

    public void a(gq gqVar) {
        this.f7863a.push(gqVar);
    }
}
