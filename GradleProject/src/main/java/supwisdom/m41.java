package supwisdom;

import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class m41 extends l61 implements d61 {
    public static final m41 c = new m41(0);

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BitSet f8357a;
        public final m41 b;
        public int c;
        public final m41 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8358e;

        public b(m41 m41Var, BitSet bitSet, int i, boolean z) {
            this.b = m41Var;
            this.f8357a = bitSet;
            this.c = i;
            this.d = new m41(m41Var.size());
            this.f8358e = z;
        }

        public final void a(int i) {
            a(i, (l41) this.b.a(i));
        }

        public final m41 a() {
            if (this.b.c()) {
                this.d.e();
            }
            return this.d;
        }

        public final void a(int i, l41 l41Var) {
            BitSet bitSet = this.f8357a;
            if (bitSet == null || !bitSet.get(i)) {
                l41Var = l41Var.b(this.c);
                if (!this.f8358e) {
                    this.c += l41Var.c();
                }
            }
            this.f8358e = false;
            this.d.a(i, (Object) l41Var);
        }
    }

    public m41(int i) {
        super(i);
    }

    public static m41 b(l41 l41Var) {
        m41 m41Var = new m41(1);
        m41Var.a(0, l41Var);
        return m41Var;
    }

    public l41 d(int i) {
        return (l41) a(i);
    }

    public m41 e(int i) {
        int size = size();
        if (size == 0) {
            return this;
        }
        m41 m41Var = new m41(size);
        for (int i2 = 0; i2 < size; i2++) {
            l41 l41Var = (l41) a(i2);
            if (l41Var != null) {
                m41Var.a(i2, (Object) l41Var.a(i));
            }
        }
        if (c()) {
            m41Var.e();
        }
        return m41Var;
    }

    @Override // supwisdom.d61
    public b61 getType(int i) {
        b61 type = d(i).getType();
        type.getType();
        return type;
    }

    public int h() {
        int size = size();
        int iD = 0;
        for (int i = 0; i < size; i++) {
            iD += getType(i).d();
        }
        return iD;
    }

    public void a(int i, l41 l41Var) {
        a(i, (Object) l41Var);
    }

    public m41 a(BitSet bitSet) {
        int size = size() - bitSet.cardinality();
        if (size == 0) {
            return c;
        }
        m41 m41Var = new m41(size);
        int i = 0;
        for (int i2 = 0; i2 < size(); i2++) {
            if (!bitSet.get(i2)) {
                m41Var.a(i, a(i2));
                i++;
            }
        }
        if (c()) {
            m41Var.e();
        }
        return m41Var;
    }

    public m41 a(int i, boolean z, BitSet bitSet) {
        int size = size();
        if (size == 0) {
            return this;
        }
        b bVar = new b(bitSet, i, z);
        for (int i2 = 0; i2 < size; i2++) {
            bVar.a(i2);
        }
        return bVar.a();
    }

    public m41 a(l41 l41Var) {
        int size = size();
        m41 m41Var = new m41(size + 1);
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            m41Var.a(i2, a(i));
            i = i2;
        }
        m41Var.a(0, (Object) l41Var);
        if (c()) {
            m41Var.e();
        }
        return m41Var;
    }

    public static m41 a(l41 l41Var, l41 l41Var2) {
        m41 m41Var = new m41(2);
        m41Var.a(0, l41Var);
        m41Var.a(1, l41Var2);
        return m41Var;
    }

    public static m41 a(l41 l41Var, l41 l41Var2, l41 l41Var3) {
        m41 m41Var = new m41(3);
        m41Var.a(0, l41Var);
        m41Var.a(1, l41Var2);
        m41Var.a(2, l41Var3);
        return m41Var;
    }
}
