package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.BitSet;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class kz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8222a;
    public final mz0 b;
    public final r41 c;
    public final m41 d;

    public kz0(mz0 mz0Var, r41 r41Var, m41 m41Var) {
        if (mz0Var == null) {
            throw new NullPointerException("opcode == null");
        }
        if (r41Var == null) {
            throw new NullPointerException("position == null");
        }
        if (m41Var == null) {
            throw new NullPointerException("registers == null");
        }
        this.f8222a = -1;
        this.b = mz0Var;
        this.c = r41Var;
        this.d = m41Var;
    }

    public abstract String a();

    public abstract String a(boolean z);

    public kz0 a(BitSet bitSet) {
        m41 m41Var = this.d;
        boolean z = bitSet.get(0);
        if (l()) {
            bitSet.set(0);
        }
        m41 m41VarA = m41Var.a(bitSet);
        if (l()) {
            bitSet.set(0, z);
        }
        if (m41VarA.size() == 0) {
            return null;
        }
        return new pz0(this.c, m41VarA);
    }

    public abstract kz0 a(m41 m41Var);

    public abstract kz0 a(mz0 mz0Var);

    public abstract void a(h61 h61Var);

    public abstract int b();

    public abstract kz0 b(int i);

    public kz0 b(BitSet bitSet) {
        if (!l() || bitSet.get(0)) {
            return null;
        }
        l41 l41VarD = this.d.d(0);
        return a(this.c, l41VarD, l41VarD.b(0));
    }

    public String c() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public String d() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public final int e() {
        int i = this.f8222a;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("address not yet known");
    }

    public kz0 f() {
        return a(this.d.a(0, l(), (BitSet) null));
    }

    public final int g() {
        return e() + b();
    }

    public final mz0 h() {
        return this.b;
    }

    public final r41 i() {
        return this.c;
    }

    public final m41 j() {
        return this.d;
    }

    public final boolean k() {
        return this.f8222a >= 0;
    }

    public final boolean l() {
        return this.b.g();
    }

    public final String m() {
        int i = this.f8222a;
        return i != -1 ? String.format("%04x", Integer.valueOf(i)) : m61.g(System.identityHashCode(this));
    }

    public final String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder(100);
        sb.append(m());
        sb.append(' ');
        sb.append(this.c);
        sb.append(": ");
        sb.append(this.b.c());
        if (this.d.size() != 0) {
            sb.append(this.d.a(Operators.SPACE_STR, ", ", (String) null));
            z = true;
        } else {
            z = false;
        }
        String strA = a();
        if (strA != null) {
            if (z) {
                sb.append(',');
            }
            sb.append(' ');
            sb.append(strA);
        }
        return sb.toString();
    }

    public kz0 c(BitSet bitSet) {
        return a(this.d.a(0, l(), bitSet));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1, types: [supwisdom.m41] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.BitSet] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final int d(BitSet bitSet) {
        ?? L = l();
        int size = this.d.size();
        int iC = 0;
        int iC2 = (L == 0 || bitSet.get(0)) ? 0 : this.d.d(0).c();
        while (L < size) {
            if (!bitSet.get(L)) {
                iC += this.d.d(L).c();
            }
            L++;
        }
        return Math.max(iC, iC2);
    }

    public final String a(String str, int i, boolean z) {
        String strA = a(z);
        if (strA == null) {
            return null;
        }
        String str2 = str + m() + ": ";
        int length = str2.length();
        return u61.a(str2, length, "", strA, i == 0 ? strA.length() : i - length);
    }

    public static zz0 a(r41 r41Var, l41 l41Var, l41 l41Var2) {
        mz0 mz0Var;
        boolean z = l41Var.c() == 1;
        boolean zK = l41Var.getType().k();
        int iF = l41Var.f();
        if ((l41Var2.f() | iF) < 16) {
            if (zK) {
                mz0Var = nz0.j;
            } else {
                mz0Var = z ? nz0.d : nz0.g;
            }
        } else if (iF < 256) {
            if (zK) {
                mz0Var = nz0.k;
            } else {
                mz0Var = z ? nz0.f8592e : nz0.h;
            }
        } else if (zK) {
            mz0Var = nz0.l;
        } else {
            mz0Var = z ? nz0.f : nz0.i;
        }
        return new zz0(mz0Var, r41Var, m41.a(l41Var, l41Var2));
    }

    public final void a(int i) {
        if (i >= 0) {
            this.f8222a = i;
            return;
        }
        throw new IllegalArgumentException("address < 0");
    }

    public kz0 a(f61 f61Var) {
        return a(f61Var.a(j()));
    }
}
