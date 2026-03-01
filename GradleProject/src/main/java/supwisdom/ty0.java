package supwisdom;

import com.sangfor.dx.Comparison;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import supwisdom.vy0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class ty0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zy0<?, ?> f9338a;
    public final List<xy0> b = new ArrayList();
    public xy0 c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final yy0<?> f9339e;
    public final List<yy0<?>> f;
    public final List<yy0<?>> g;
    public r41 h;
    public final List<xy0> i;
    public a61 j;

    public ty0(vy0.b bVar) {
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        this.g = new ArrayList();
        this.h = r41.d;
        new ArrayList();
        this.i = new ArrayList();
        this.j = a61.c;
        zy0<?, ?> zy0Var = bVar.f9564a;
        this.f9338a = zy0Var;
        if (bVar.b()) {
            this.f9339e = null;
        } else {
            yy0<?> yy0VarA = yy0.a(this, zy0Var.f10047a);
            this.f9339e = yy0VarA;
            arrayList.add(yy0VarA);
        }
        az0<?>[] az0VarArr = zy0Var.d.f7128a;
        for (az0<?> az0Var : az0VarArr) {
            this.f.add(yy0.a(this, az0Var));
        }
        xy0 xy0Var = new xy0();
        this.c = xy0Var;
        a(xy0Var);
        this.c.c = true;
    }

    public final void a(e41 e41Var) {
        a(e41Var, (xy0) null);
    }

    public void b(yy0<?> yy0Var, yy0<Integer> yy0Var2, yy0<?> yy0Var3) {
        a(new t41(q41.b(yy0Var3.b.b), this.h, m41.a(yy0Var3.c(), yy0Var.c(), yy0Var2.c()), this.j));
    }

    public <D, R> void c(zy0<D, R> zy0Var, yy0<? super R> yy0Var, yy0<? extends D> yy0Var2, yy0<?>... yy0VarArr) {
        a(q41.d(zy0Var.b(true)), zy0Var, yy0Var, yy0Var2, yy0VarArr);
    }

    public <D, R> void d(zy0<D, R> zy0Var, yy0<? super R> yy0Var, yy0<? extends D> yy0Var2, yy0<?>... yy0VarArr) {
        a(q41.e(zy0Var.b(true)), zy0Var, yy0Var, yy0Var2, yy0VarArr);
    }

    public b41 e() {
        if (!this.d) {
            b();
        }
        a();
        b41 b41Var = new b41(this.b.size());
        for (int i = 0; i < this.b.size(); i++) {
            b41Var.a(i, this.b.get(i).c());
        }
        return b41Var;
    }

    public final void a(xy0 xy0Var) {
        ty0 ty0Var = xy0Var.b;
        if (ty0Var == this) {
            return;
        }
        if (ty0Var != null) {
            throw new IllegalArgumentException("Cannot adopt label; it belongs to another Code");
        }
        xy0Var.b = this;
        this.b.add(xy0Var);
    }

    public void c(xy0 xy0Var) {
        a(xy0Var);
        if (xy0Var.c) {
            throw new IllegalStateException("already marked");
        }
        xy0Var.c = true;
        if (this.c != null) {
            b(xy0Var);
        }
        this.c = xy0Var;
    }

    public void d() {
        if (this.f9338a.b.equals(az0.l)) {
            a(new j41(q41.v1, this.h, (l41) null, m41.c));
            return;
        }
        throw new IllegalArgumentException("declared " + this.f9338a.b + " but returned void");
    }

    public void b() {
        if (!this.d) {
            this.d = true;
            Iterator<yy0<?>> it = this.g.iterator();
            int iA = 0;
            while (it.hasNext()) {
                iA += it.next().a(iA);
            }
            ArrayList arrayList = new ArrayList();
            int iA2 = iA;
            for (yy0<?> yy0Var : this.f) {
                h51 h51VarA = h51.a(iA2 - iA);
                iA2 += yy0Var.a(iA2);
                arrayList.add(new i41(q41.f(yy0Var.b.b), this.h, yy0Var.c(), m41.c, h51VarA));
            }
            this.b.get(0).f9826a.addAll(0, arrayList);
            return;
        }
        throw new AssertionError();
    }

    public void a(yy0<?> yy0Var, yy0<?> yy0Var2, yy0<Integer> yy0Var3) {
        a(new t41(q41.a(yy0Var.b.b), this.h, m41.a(yy0Var2.c(), yy0Var3.c()), this.j));
        a(yy0Var, true);
    }

    public int c() {
        Iterator<yy0<?>> it = this.f.iterator();
        int iB = 0;
        while (it.hasNext()) {
            iB += it.next().b();
        }
        return iB;
    }

    public void a(yy0<?> yy0Var, yy0<?> yy0Var2) {
        if (yy0Var2.a().b.k()) {
            a(new s41(q41.g2, this.h, m41.b(yy0Var2.c()), this.j, yy0Var.b.c));
            a(yy0Var, true);
        } else {
            a(new j41(a(yy0Var2.b.b, yy0Var.b.b), this.h, yy0Var.c(), yy0Var2.c()));
        }
    }

    public <D, R> void b(zy0<D, R> zy0Var, yy0<? super R> yy0Var, yy0<? extends D> yy0Var2, yy0<?>... yy0VarArr) {
        a(q41.b(zy0Var.b(true)), zy0Var, yy0Var, yy0Var2, yy0VarArr);
    }

    public final void a() {
        Iterator<xy0> it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            xy0 next = it.next();
            if (next.b()) {
                it.remove();
            } else {
                next.a();
                next.g = i;
                i++;
            }
        }
    }

    public void b(xy0 xy0Var) {
        a(xy0Var);
        a(new j41(q41.r, this.h, (l41) null, m41.c), xy0Var);
    }

    public <T> void b(yy0<T> yy0Var, yy0<Integer> yy0Var2) {
        a(new s41(q41.j(yy0Var.b.b), this.h, m41.b(yy0Var2.c()), this.j, yy0Var.b.c));
        a((yy0<?>) yy0Var, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> yy0<T> a(yy0<?> yy0Var, az0<T> az0Var) {
        if (yy0Var.b.equals(az0Var)) {
            return yy0Var;
        }
        throw new IllegalArgumentException("requested " + az0Var + " but was " + yy0Var.b);
    }

    public <T> yy0<T> b(az0<T> az0Var) {
        if (!this.d) {
            yy0<T> yy0VarA = yy0.a(this, az0Var);
            this.g.add(yy0VarA);
            return yy0VarA;
        }
        throw new IllegalStateException("Cannot allocate locals after adding instructions");
    }

    public <T> void a(Comparison comparison, xy0 xy0Var, yy0<T> yy0Var, yy0<T> yy0Var2) {
        a(xy0Var);
        a(new j41(comparison.rop(a61.a(yy0Var.b.b, yy0Var2.b.b)), this.h, (l41) null, m41.a(yy0Var.c(), yy0Var2.c())), xy0Var);
    }

    public void b(yy0<? extends Throwable> yy0Var) {
        a(new t41(q41.C1, this.h, m41.b(yy0Var.c()), this.j));
    }

    public static m41 a(yy0<?> yy0Var, yy0<?>[] yy0VarArr) {
        int i = yy0Var != null ? 1 : 0;
        m41 m41Var = new m41(yy0VarArr.length + i);
        if (yy0Var != null) {
            m41Var.a(0, yy0Var.c());
        }
        for (int i2 = 0; i2 < yy0VarArr.length; i2++) {
            m41Var.a(i2 + i, yy0VarArr[i2].c());
        }
        return m41Var;
    }

    public final o41 a(b61 b61Var, b61 b61Var2) {
        if (b61Var.a() == 6) {
            int iA = b61Var2.a();
            if (iA == 2) {
                return q41.s1;
            }
            if (iA == 3) {
                return q41.t1;
            }
            if (iA == 8) {
                return q41.u1;
            }
        }
        return q41.a(b61Var2, b61Var);
    }

    public <T> yy0<T> a(int i, az0<T> az0Var) {
        if (this.f9339e != null) {
            i++;
        }
        yy0<T> yy0Var = (yy0) this.f.get(i);
        a((yy0<?>) yy0Var, (az0) az0Var);
        return yy0Var;
    }

    public <T> yy0<T> a(az0<T> az0Var) {
        yy0<T> yy0Var = (yy0<T>) this.f9339e;
        if (yy0Var != null) {
            a((yy0<?>) yy0Var, (az0) az0Var);
            return yy0Var;
        }
        throw new IllegalStateException("static methods cannot access 'this'");
    }

    public <D, V> void a(wy0<D, ? extends V> wy0Var, yy0<V> yy0Var, yy0<D> yy0Var2) {
        a(new s41(q41.d(yy0Var.b.b), this.h, m41.b(yy0Var2.c()), this.j, wy0Var.c));
        a((yy0<?>) yy0Var, true);
    }

    public final <D, R> void a(o41 o41Var, zy0<D, R> zy0Var, yy0<? super R> yy0Var, yy0<? extends D> yy0Var2, yy0<?>... yy0VarArr) {
        a(new s41(o41Var, this.h, a((yy0<?>) yy0Var2, yy0VarArr), this.j, zy0Var.f10048e));
        if (yy0Var != null) {
            a((yy0<?>) yy0Var, false);
        }
    }

    public <D, R> void a(zy0<D, R> zy0Var, yy0<? super R> yy0Var, yy0<? extends D> yy0Var2, yy0<?>... yy0VarArr) {
        a(q41.a(zy0Var.b(true)), zy0Var, yy0Var, yy0Var2, yy0VarArr);
    }

    public <R> void a(zy0<?, R> zy0Var, yy0<? super R> yy0Var, yy0<?>... yy0VarArr) {
        a(q41.c(zy0Var.b(true)), zy0Var, yy0Var, null, yy0VarArr);
    }

    public <T> void a(yy0<T> yy0Var, T t) {
        o41 o41VarC;
        if (t == null) {
            o41VarC = q41.q;
        } else {
            o41VarC = q41.c(yy0Var.b.b);
        }
        o41 o41Var = o41VarC;
        if (o41Var.b() == 1) {
            a(new i41(o41Var, this.h, yy0Var.c(), m41.c, uy0.a(t)));
        } else {
            a(new s41(o41Var, this.h, m41.c, this.j, uy0.a(t)));
            a((yy0<?>) yy0Var, true);
        }
    }

    public final void a(yy0<?> yy0Var, boolean z) {
        o41 o41VarG;
        if (z) {
            o41VarG = q41.h(yy0Var.b.b);
        } else {
            o41VarG = q41.g(yy0Var.b.b);
        }
        a(new j41(o41VarG, this.h, yy0Var.c(), m41.c));
    }

    public <T> void a(yy0<T> yy0Var, zy0<T, Void> zy0Var, yy0<?>... yy0VarArr) {
        if (yy0Var != null) {
            a(new s41(q41.X1, this.h, m41.c, this.j, zy0Var.f10047a.c));
            a((yy0<?>) yy0Var, true);
            a(zy0Var, (yy0) null, yy0Var, yy0VarArr);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void a(yy0<?> yy0Var) {
        if (yy0Var.b.equals(this.f9338a.b)) {
            a(new j41(q41.l(yy0Var.b.b), this.h, (l41) null, m41.b(yy0Var.c())));
            return;
        }
        throw new IllegalArgumentException("declared " + this.f9338a.b + " but returned " + yy0Var.b);
    }

    public <V> void a(wy0<?, ? extends V> wy0Var, yy0<V> yy0Var) {
        a(new s41(q41.e(yy0Var.b.b), this.h, m41.c, this.j, wy0Var.c));
        a((yy0<?>) yy0Var, true);
    }

    public final void a(xy0 xy0Var, List<xy0> list) {
        xy0 xy0Var2 = new xy0();
        a(xy0Var2);
        xy0 xy0Var3 = this.c;
        xy0Var3.f9827e = xy0Var2;
        xy0Var3.f = xy0Var;
        xy0Var3.d = list;
        this.c = xy0Var2;
        xy0Var2.c = true;
    }

    public final void a(e41 e41Var, xy0 xy0Var) {
        xy0 xy0Var2 = this.c;
        if (xy0Var2 != null && xy0Var2.c) {
            xy0Var2.f9826a.add(e41Var);
            int iB = e41Var.f().b();
            if (iB == 1) {
                if (xy0Var == null) {
                    return;
                }
                throw new IllegalArgumentException("unexpected branch: " + xy0Var);
            }
            if (iB == 2) {
                if (xy0Var == null) {
                    this.c = null;
                    return;
                }
                throw new IllegalArgumentException("unexpected branch: " + xy0Var);
            }
            if (iB == 3) {
                if (xy0Var != null) {
                    this.c.f9827e = xy0Var;
                    this.c = null;
                    return;
                }
                throw new IllegalArgumentException("branch == null");
            }
            if (iB == 4) {
                if (xy0Var != null) {
                    a(xy0Var, Collections.emptyList());
                    return;
                }
                throw new IllegalArgumentException("branch == null");
            }
            if (iB != 6) {
                throw new IllegalArgumentException();
            }
            if (xy0Var == null) {
                a((xy0) null, new ArrayList(this.i));
                return;
            }
            throw new IllegalArgumentException("unexpected branch: " + xy0Var);
        }
        throw new IllegalStateException("no current label");
    }
}
