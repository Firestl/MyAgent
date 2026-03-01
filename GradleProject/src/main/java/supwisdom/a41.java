package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class a41 implements p61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6855a;
    public final f41 b;
    public final o61 c;
    public final int d;

    public a41(int i, f41 f41Var, o61 o61Var, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("label < 0");
        }
        try {
            f41Var.g();
            int size = f41Var.size();
            if (size == 0) {
                throw new IllegalArgumentException("insns.size() == 0");
            }
            for (int i3 = size - 2; i3 >= 0; i3--) {
                if (f41Var.d(i3).f().b() != 1) {
                    throw new IllegalArgumentException("insns[" + i3 + "] is a branch or can throw");
                }
            }
            if (f41Var.d(size - 1).f().b() == 1) {
                throw new IllegalArgumentException("insns does not end with a branch or throwing instruction");
            }
            try {
                o61Var.g();
                if (i2 < -1) {
                    throw new IllegalArgumentException("primarySuccessor < -1");
                }
                if (i2 < 0 || o61Var.c(i2)) {
                    this.f6855a = i;
                    this.b = f41Var;
                    this.c = o61Var;
                    this.d = i2;
                    return;
                }
                throw new IllegalArgumentException("primarySuccessor " + i2 + " not in successors " + o61Var);
            } catch (NullPointerException unused) {
                throw new NullPointerException("successors == null");
            }
        } catch (NullPointerException unused2) {
            throw new NullPointerException("insns == null");
        }
    }

    @Override // supwisdom.p61
    public int a() {
        return this.f6855a;
    }

    public boolean b() {
        return this.b.h().c();
    }

    public f41 c() {
        return this.b;
    }

    public e41 d() {
        return this.b.h();
    }

    public int e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int f() {
        if (this.c.size() != 2) {
            throw new UnsupportedOperationException("block doesn't have exactly two successors");
        }
        int iD = this.c.d(0);
        return iD == this.d ? this.c.d(1) : iD;
    }

    public o61 g() {
        return this.c;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return Operators.BLOCK_START + m61.d(this.f6855a) + Operators.BLOCK_END;
    }
}
