package supwisdom;

import java.util.LinkedList;
import java.util.TreeSet;

/* JADX INFO: compiled from: CeaDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u50 implements p60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedList<a70> f9371a = new LinkedList<>();
    public final LinkedList<b70> b;
    public final TreeSet<a70> c;
    public a70 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f9372e;

    public u50() {
        for (int i = 0; i < 10; i++) {
            this.f9371a.add(new a70());
        }
        this.b = new LinkedList<>();
        for (int i2 = 0; i2 < 2; i2++) {
            this.b.add(new v50(this));
        }
        this.c = new TreeSet<>();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    public abstract void a(a70 a70Var);

    public final void c(a70 a70Var) {
        a70Var.a();
        this.f9371a.add(a70Var);
    }

    @Override // supwisdom.w10
    public void d() {
    }

    public abstract boolean e();

    public abstract m60 f();

    @Override // supwisdom.w10
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public b70 b() throws com.google.android.exoplayer2.g.f {
        if (this.b.isEmpty()) {
            return null;
        }
        while (!this.c.isEmpty() && this.c.first().d <= this.f9372e) {
            a70 a70VarPollFirst = this.c.pollFirst();
            if (a70VarPollFirst.d()) {
                b70 b70VarPollFirst = this.b.pollFirst();
                b70VarPollFirst.c(4);
                c(a70VarPollFirst);
                return b70VarPollFirst;
            }
            a(a70VarPollFirst);
            if (e()) {
                m60 m60VarF = f();
                if (!a70VarPollFirst.c()) {
                    b70 b70VarPollFirst2 = this.b.pollFirst();
                    b70VarPollFirst2.a(a70VarPollFirst.d, m60VarF, Long.MAX_VALUE);
                    c(a70VarPollFirst);
                    return b70VarPollFirst2;
                }
            }
            c(a70VarPollFirst);
        }
        return null;
    }

    @Override // supwisdom.w10
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public a70 a() throws com.google.android.exoplayer2.g.f {
        e80.b(this.d == null);
        if (this.f9371a.isEmpty()) {
            return null;
        }
        a70 a70VarPollFirst = this.f9371a.pollFirst();
        this.d = a70VarPollFirst;
        return a70VarPollFirst;
    }

    @Override // supwisdom.w10
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(a70 a70Var) throws com.google.android.exoplayer2.g.f {
        e80.a(a70Var != null);
        e80.a(a70Var == this.d);
        if (a70Var.c()) {
            c(a70Var);
        } else {
            this.c.add(a70Var);
        }
        this.d = null;
    }

    @Override // supwisdom.p60
    public void a(long j) {
        this.f9372e = j;
    }

    @Override // supwisdom.w10
    public void c() {
        this.f9372e = 0L;
        while (!this.c.isEmpty()) {
            c(this.c.pollFirst());
        }
        a70 a70Var = this.d;
        if (a70Var != null) {
            c(a70Var);
            this.d = null;
        }
    }

    public void a(b70 b70Var) {
        b70Var.a();
        this.b.add(b70Var);
    }
}
