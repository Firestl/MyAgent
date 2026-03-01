package supwisdom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class xy0 {
    public ty0 b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public xy0 f9827e;
    public xy0 f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<e41> f9826a = new ArrayList();
    public boolean c = false;
    public List<xy0> d = Collections.emptyList();
    public int g = -1;

    public void a() {
        for (int i = 0; i < this.d.size(); i++) {
            while (this.d.get(i).b()) {
                List<xy0> list = this.d;
                list.set(i, list.get(i).f9827e);
            }
        }
        while (true) {
            xy0 xy0Var = this.f9827e;
            if (xy0Var == null || !xy0Var.b()) {
                break;
            } else {
                this.f9827e = this.f9827e.f9827e;
            }
        }
        while (true) {
            xy0 xy0Var2 = this.f;
            if (xy0Var2 == null || !xy0Var2.b()) {
                return;
            } else {
                this.f = this.f.f9827e;
            }
        }
    }

    public boolean b() {
        return this.f9826a.isEmpty();
    }

    public a41 c() {
        f41 f41Var = new f41(this.f9826a.size());
        for (int i = 0; i < this.f9826a.size(); i++) {
            f41Var.a(i, this.f9826a.get(i));
        }
        f41Var.e();
        int i2 = -1;
        o61 o61Var = new o61();
        Iterator<xy0> it = this.d.iterator();
        while (it.hasNext()) {
            o61Var.a(it.next().g);
        }
        xy0 xy0Var = this.f9827e;
        if (xy0Var != null) {
            i2 = xy0Var.g;
            o61Var.a(i2);
        }
        xy0 xy0Var2 = this.f;
        if (xy0Var2 != null) {
            o61Var.a(xy0Var2.g);
        }
        o61Var.e();
        return new a41(this.g, f41Var, o61Var, i2);
    }

    public String toString() {
        return super.toString();
    }
}
