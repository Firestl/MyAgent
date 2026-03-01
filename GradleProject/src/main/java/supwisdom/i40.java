package supwisdom;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import supwisdom.m80;
import supwisdom.u40;

/* JADX INFO: compiled from: H264Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class i40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r40 f7909a;
    public final boolean b;
    public final boolean c;
    public long g;
    public String i;
    public f50 j;
    public b k;
    public boolean l;
    public long m;
    public final boolean[] h = new boolean[3];
    public final m40 d = new m40(7, 128);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m40 f7910e = new m40(8, 128);
    public final m40 f = new m40(6, 128);
    public final o80 n = new o80();

    /* JADX INFO: compiled from: H264Reader.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f50 f7911a;
        public final boolean b;
        public final boolean c;
        public final SparseArray<m80.b> d = new SparseArray<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final SparseArray<m80.a> f7912e = new SparseArray<>();
        public final p80 f;
        public byte[] g;
        public int h;
        public int i;
        public long j;
        public boolean k;
        public long l;
        public a m;
        public a n;
        public boolean o;
        public long p;
        public long q;
        public boolean r;

        /* JADX INFO: compiled from: H264Reader.java */
        public static final class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f7913a;
            public boolean b;
            public m80.b c;
            public int d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public int f7914e;
            public int f;
            public int g;
            public boolean h;
            public boolean i;
            public boolean j;
            public boolean k;
            public int l;
            public int m;
            public int n;
            public int o;
            public int p;

            public a() {
            }

            public boolean b() {
                int i;
                return this.b && ((i = this.f7914e) == 7 || i == 2);
            }

            public void a() {
                this.b = false;
                this.f7913a = false;
            }

            public void a(int i) {
                this.f7914e = i;
                this.b = true;
            }

            public void a(m80.b bVar, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, int i6, int i7, int i8, int i9) {
                this.c = bVar;
                this.d = i;
                this.f7914e = i2;
                this.f = i3;
                this.g = i4;
                this.h = z;
                this.i = z2;
                this.j = z3;
                this.k = z4;
                this.l = i5;
                this.m = i6;
                this.n = i7;
                this.o = i8;
                this.p = i9;
                this.f7913a = true;
                this.b = true;
            }

            public final boolean a(a aVar) {
                boolean z;
                boolean z2;
                if (this.f7913a) {
                    if (!aVar.f7913a || this.f != aVar.f || this.g != aVar.g || this.h != aVar.h) {
                        return true;
                    }
                    if (this.i && aVar.i && this.j != aVar.j) {
                        return true;
                    }
                    int i = this.d;
                    int i2 = aVar.d;
                    if (i != i2 && (i == 0 || i2 == 0)) {
                        return true;
                    }
                    if (this.c.h == 0 && aVar.c.h == 0 && (this.m != aVar.m || this.n != aVar.n)) {
                        return true;
                    }
                    if ((this.c.h == 1 && aVar.c.h == 1 && (this.o != aVar.o || this.p != aVar.p)) || (z = this.k) != (z2 = aVar.k)) {
                        return true;
                    }
                    if (z && z2 && this.l != aVar.l) {
                        return true;
                    }
                }
                return false;
            }
        }

        public b(f50 f50Var, boolean z, boolean z2) {
            this.f7911a = f50Var;
            this.b = z;
            this.c = z2;
            this.m = new a();
            this.n = new a();
            byte[] bArr = new byte[128];
            this.g = bArr;
            this.f = new p80(bArr, 0, 0);
            b();
        }

        public boolean a() {
            return this.c;
        }

        public void b() {
            this.k = false;
            this.o = false;
            this.n.a();
        }

        public void a(m80.b bVar) {
            this.d.append(bVar.f8364a, bVar);
        }

        public void a(m80.a aVar) {
            this.f7912e.append(aVar.f8363a, aVar);
        }

        public void a(long j, int i, long j2) {
            this.i = i;
            this.l = j2;
            this.j = j;
            if (!this.b || i != 1) {
                if (!this.c) {
                    return;
                }
                int i2 = this.i;
                if (i2 != 5 && i2 != 1 && i2 != 2) {
                    return;
                }
            }
            a aVar = this.m;
            this.m = this.n;
            this.n = aVar;
            aVar.a();
            this.h = 0;
            this.k = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0102  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x011e  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x014e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(byte[] r24, int r25, int r26) {
            /*
                Method dump skipped, instruction units count: 408
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.i40.b.a(byte[], int, int):void");
        }

        public void a(long j, int i) {
            boolean z = false;
            if (this.i == 9 || (this.c && this.n.a(this.m))) {
                if (this.o) {
                    a(i + ((int) (j - this.j)));
                }
                this.p = this.j;
                this.q = this.l;
                this.r = false;
                this.o = true;
            }
            boolean z2 = this.r;
            int i2 = this.i;
            if (i2 == 5 || (this.b && i2 == 1 && this.n.b())) {
                z = true;
            }
            this.r = z2 | z;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void a(int i) {
            boolean z = this.r;
            this.f7911a.a(this.q, z ? 1 : 0, (int) (this.j - this.p), i, null);
        }
    }

    public i40(r40 r40Var, boolean z, boolean z2) {
        this.f7909a = r40Var;
        this.b = z;
        this.c = z2;
    }

    @Override // supwisdom.g40
    public void a() {
        m80.a(this.h);
        this.d.a();
        this.f7910e.a();
        this.f.a();
        this.k.b();
        this.g = 0L;
    }

    @Override // supwisdom.g40
    public void b() {
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.i = dVar.c();
        f50 f50VarA = z40Var.a(dVar.b(), 2);
        this.j = f50VarA;
        this.k = new b(f50VarA, this.b, this.c);
        this.f7909a.a(z40Var, dVar);
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        this.m = j;
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        int iD = o80Var.d();
        int iC = o80Var.c();
        byte[] bArr = o80Var.f8644a;
        this.g += (long) o80Var.b();
        this.j.a(o80Var, o80Var.b());
        while (true) {
            int iA = m80.a(bArr, iD, iC, this.h);
            if (iA == iC) {
                a(bArr, iD, iC);
                return;
            }
            int iB = m80.b(bArr, iA);
            int i = iA - iD;
            if (i > 0) {
                a(bArr, iD, iA);
            }
            int i2 = iC - iA;
            long j = this.g - ((long) i2);
            a(j, i2, i < 0 ? -i : 0, this.m);
            a(j, iB, this.m);
            iD = iA + 3;
        }
    }

    public final void a(long j, int i, long j2) {
        if (!this.l || this.k.a()) {
            this.d.a(i);
            this.f7910e.a(i);
        }
        this.f.a(i);
        this.k.a(j, i, j2);
    }

    public final void a(byte[] bArr, int i, int i2) {
        if (!this.l || this.k.a()) {
            this.d.a(bArr, i, i2);
            this.f7910e.a(bArr, i, i2);
        }
        this.f.a(bArr, i, i2);
        this.k.a(bArr, i, i2);
    }

    public final void a(long j, int i, int i2, long j2) {
        if (!this.l || this.k.a()) {
            this.d.b(i2);
            this.f7910e.b(i2);
            if (!this.l) {
                if (this.d.b() && this.f7910e.b()) {
                    ArrayList arrayList = new ArrayList();
                    m40 m40Var = this.d;
                    arrayList.add(Arrays.copyOf(m40Var.d, m40Var.f8356e));
                    m40 m40Var2 = this.f7910e;
                    arrayList.add(Arrays.copyOf(m40Var2.d, m40Var2.f8356e));
                    m40 m40Var3 = this.d;
                    m80.b bVarA = m80.a(m40Var3.d, 3, m40Var3.f8356e);
                    m40 m40Var4 = this.f7910e;
                    m80.a aVarB = m80.b(m40Var4.d, 3, m40Var4.f8356e);
                    this.j.a(com.google.android.exoplayer2.j.a(this.i, "video/avc", (String) null, -1, -1, bVarA.b, bVarA.c, -1.0f, arrayList, -1, bVarA.d, (com.google.android.exoplayer2.c.a) null));
                    this.l = true;
                    this.k.a(bVarA);
                    this.k.a(aVarB);
                    this.d.a();
                    this.f7910e.a();
                }
            } else if (this.d.b()) {
                m40 m40Var5 = this.d;
                this.k.a(m80.a(m40Var5.d, 3, m40Var5.f8356e));
                this.d.a();
            } else if (this.f7910e.b()) {
                m40 m40Var6 = this.f7910e;
                this.k.a(m80.b(m40Var6.d, 3, m40Var6.f8356e));
                this.f7910e.a();
            }
        }
        if (this.f.b(i2)) {
            m40 m40Var7 = this.f;
            this.n.a(this.f.d, m80.a(m40Var7.d, m40Var7.f8356e));
            this.n.c(4);
            this.f7909a.a(j2, this.n);
        }
        this.k.a(j, i);
    }
}
