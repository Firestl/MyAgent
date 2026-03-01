package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class pz0 extends c01 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public zz0[] f8853e;

    public pz0(r41 r41Var, m41 m41Var) {
        super(r41Var, m41Var);
        if (m41Var.size() == 0) {
            throw new IllegalArgumentException("registers.size() == 0");
        }
        this.f8853e = null;
    }

    @Override // supwisdom.kz0
    public String a() {
        return null;
    }

    @Override // supwisdom.kz0
    public String a(boolean z) {
        m41 m41VarJ = j();
        int size = m41VarJ.size();
        StringBuilder sb = new StringBuilder(100);
        int iC = 0;
        for (int i = 0; i < size; i++) {
            l41 l41VarD = m41VarJ.d(i);
            zz0 zz0VarA = a(l41VarD, iC);
            if (i != 0) {
                sb.append('\n');
            }
            sb.append(zz0VarA.a(z));
            iC += l41VarD.c();
        }
        return sb.toString();
    }

    @Override // supwisdom.kz0
    public int b() {
        n();
        int iB = 0;
        for (zz0 zz0Var : this.f8853e) {
            iB += zz0Var.b();
        }
        return iB;
    }

    public final void n() {
        if (this.f8853e != null) {
            return;
        }
        m41 m41VarJ = j();
        int size = m41VarJ.size();
        this.f8853e = new zz0[size];
        int iC = 0;
        for (int i = 0; i < size; i++) {
            l41 l41VarD = m41VarJ.d(i);
            this.f8853e[i] = a(l41VarD, iC);
            iC += l41VarD.c();
        }
    }

    public static zz0 a(l41 l41Var, int i) {
        return kz0.a(r41.d, l41.a(i, l41Var.getType()), l41Var);
    }

    @Override // supwisdom.kz0
    public kz0 a(m41 m41Var) {
        return new pz0(i(), m41Var);
    }

    @Override // supwisdom.kz0
    public void a(h61 h61Var) {
        n();
        for (zz0 zz0Var : this.f8853e) {
            zz0Var.a(h61Var);
        }
    }
}
