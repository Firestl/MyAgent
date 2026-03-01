package supwisdom;

import android.util.Log;
import java.util.Collections;
import java.util.List;
import supwisdom.u40;

/* JADX INFO: compiled from: H265Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class j40 implements g40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r40 f8018a;
    public String b;
    public f50 c;
    public a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8019e;
    public long l;
    public long m;
    public final boolean[] f = new boolean[3];
    public final m40 g = new m40(32, 128);
    public final m40 h = new m40(33, 128);
    public final m40 i = new m40(34, 128);
    public final m40 j = new m40(39, 128);
    public final m40 k = new m40(40, 128);
    public final o80 n = new o80();

    public j40(r40 r40Var) {
        this.f8018a = r40Var;
    }

    @Override // supwisdom.g40
    public void a() {
        m80.a(this.f);
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        this.d.a();
        this.l = 0L;
    }

    @Override // supwisdom.g40
    public void b() {
    }

    public final void b(long j, int i, int i2, long j2) {
        if (this.f8019e) {
            this.d.a(j, i);
        } else {
            this.g.b(i2);
            this.h.b(i2);
            this.i.b(i2);
            if (this.g.b() && this.h.b() && this.i.b()) {
                this.c.a(a(this.b, this.g, this.h, this.i));
                this.f8019e = true;
            }
        }
        if (this.j.b(i2)) {
            m40 m40Var = this.j;
            this.n.a(this.j.d, m80.a(m40Var.d, m40Var.f8356e));
            this.n.d(5);
            this.f8018a.a(j2, this.n);
        }
        if (this.k.b(i2)) {
            m40 m40Var2 = this.k;
            this.n.a(this.k.d, m80.a(m40Var2.d, m40Var2.f8356e));
            this.n.d(5);
            this.f8018a.a(j2, this.n);
        }
    }

    /* JADX INFO: compiled from: H265Reader.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f50 f8020a;
        public long b;
        public boolean c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f8021e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public long k;
        public long l;
        public boolean m;

        public a(f50 f50Var) {
            this.f8020a = f50Var;
        }

        public void a() {
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = false;
        }

        public void a(long j, int i, int i2, long j2) {
            this.g = false;
            this.h = false;
            this.f8021e = j2;
            this.d = 0;
            this.b = j;
            if (i2 >= 32) {
                if (!this.j && this.i) {
                    a(i);
                    this.i = false;
                }
                if (i2 <= 34) {
                    this.h = !this.j;
                    this.j = true;
                }
            }
            boolean z = i2 >= 16 && i2 <= 21;
            this.c = z;
            this.f = z || i2 <= 9;
        }

        public void a(byte[] bArr, int i, int i2) {
            if (this.f) {
                int i3 = this.d;
                int i4 = (i + 2) - i3;
                if (i4 < i2) {
                    this.g = (bArr[i4] & com.igexin.c.a.d.g.n) != 0;
                    this.f = false;
                } else {
                    this.d = i3 + (i2 - i);
                }
            }
        }

        public void a(long j, int i) {
            if (this.j && this.g) {
                this.m = this.c;
                this.j = false;
            } else if (this.h || this.g) {
                if (this.i) {
                    a(i + ((int) (j - this.b)));
                }
                this.k = this.b;
                this.l = this.f8021e;
                this.i = true;
                this.m = this.c;
            }
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
            boolean z = this.m;
            this.f8020a.a(this.l, z ? 1 : 0, (int) (this.b - this.k), i, null);
        }
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.b = dVar.c();
        f50 f50VarA = z40Var.a(dVar.b(), 2);
        this.c = f50VarA;
        this.d = new a(f50VarA);
        this.f8018a.a(z40Var, dVar);
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        this.m = j;
    }

    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        while (o80Var.b() > 0) {
            int iD = o80Var.d();
            int iC = o80Var.c();
            byte[] bArr = o80Var.f8644a;
            this.l += (long) o80Var.b();
            this.c.a(o80Var, o80Var.b());
            while (iD < iC) {
                int iA = m80.a(bArr, iD, iC, this.f);
                if (iA == iC) {
                    a(bArr, iD, iC);
                    return;
                }
                int iC2 = m80.c(bArr, iA);
                int i = iA - iD;
                if (i > 0) {
                    a(bArr, iD, iA);
                }
                int i2 = iC - iA;
                long j = this.l - ((long) i2);
                b(j, i2, i < 0 ? -i : 0, this.m);
                a(j, i2, iC2, this.m);
                iD = iA + 3;
            }
        }
    }

    public static void b(p80 p80Var) {
        int iC = p80Var.c();
        boolean zA = false;
        int i = 0;
        for (int i2 = 0; i2 < iC; i2++) {
            if (i2 != 0) {
                zA = p80Var.a();
            }
            if (zA) {
                p80Var.a(1);
                p80Var.c();
                for (int i3 = 0; i3 <= i; i3++) {
                    if (p80Var.a()) {
                        p80Var.a(1);
                    }
                }
            } else {
                int iC2 = p80Var.c();
                int iC3 = p80Var.c();
                int i4 = iC2 + iC3;
                for (int i5 = 0; i5 < iC2; i5++) {
                    p80Var.c();
                    p80Var.a(1);
                }
                for (int i6 = 0; i6 < iC3; i6++) {
                    p80Var.c();
                    p80Var.a(1);
                }
                i = i4;
            }
        }
    }

    public final void a(long j, int i, int i2, long j2) {
        if (this.f8019e) {
            this.d.a(j, i, i2, j2);
        } else {
            this.g.a(i2);
            this.h.a(i2);
            this.i.a(i2);
        }
        this.j.a(i2);
        this.k.a(i2);
    }

    public final void a(byte[] bArr, int i, int i2) {
        if (this.f8019e) {
            this.d.a(bArr, i, i2);
        } else {
            this.g.a(bArr, i, i2);
            this.h.a(bArr, i, i2);
            this.i.a(bArr, i, i2);
        }
        this.j.a(bArr, i, i2);
        this.k.a(bArr, i, i2);
    }

    public static com.google.android.exoplayer2.j a(String str, m40 m40Var, m40 m40Var2, m40 m40Var3) {
        float f;
        int i = m40Var.f8356e;
        byte[] bArr = new byte[m40Var2.f8356e + i + m40Var3.f8356e];
        System.arraycopy(m40Var.d, 0, bArr, 0, i);
        System.arraycopy(m40Var2.d, 0, bArr, m40Var.f8356e, m40Var2.f8356e);
        System.arraycopy(m40Var3.d, 0, bArr, m40Var.f8356e + m40Var2.f8356e, m40Var3.f8356e);
        p80 p80Var = new p80(m40Var2.d, 0, m40Var2.f8356e);
        p80Var.a(44);
        int iC = p80Var.c(3);
        p80Var.a(1);
        p80Var.a(88);
        p80Var.a(8);
        int i2 = 0;
        for (int i3 = 0; i3 < iC; i3++) {
            if (p80Var.a()) {
                i2 += 89;
            }
            if (p80Var.a()) {
                i2 += 8;
            }
        }
        p80Var.a(i2);
        if (iC > 0) {
            p80Var.a((8 - iC) * 2);
        }
        p80Var.c();
        int iC2 = p80Var.c();
        if (iC2 == 3) {
            p80Var.a(1);
        }
        int iC3 = p80Var.c();
        int iC4 = p80Var.c();
        if (p80Var.a()) {
            int iC5 = p80Var.c();
            int iC6 = p80Var.c();
            int iC7 = p80Var.c();
            int iC8 = p80Var.c();
            iC3 -= ((iC2 == 1 || iC2 == 2) ? 2 : 1) * (iC5 + iC6);
            iC4 -= (iC2 == 1 ? 2 : 1) * (iC7 + iC8);
        }
        int i4 = iC3;
        int i5 = iC4;
        p80Var.c();
        p80Var.c();
        int iC9 = p80Var.c();
        for (int i6 = p80Var.a() ? 0 : iC; i6 <= iC; i6++) {
            p80Var.c();
            p80Var.c();
            p80Var.c();
        }
        p80Var.c();
        p80Var.c();
        p80Var.c();
        p80Var.c();
        p80Var.c();
        p80Var.c();
        if (p80Var.a() && p80Var.a()) {
            a(p80Var);
        }
        p80Var.a(2);
        if (p80Var.a()) {
            p80Var.a(8);
            p80Var.c();
            p80Var.c();
            p80Var.a(1);
        }
        b(p80Var);
        if (p80Var.a()) {
            for (int i7 = 0; i7 < p80Var.c(); i7++) {
                p80Var.a(iC9 + 4 + 1);
            }
        }
        p80Var.a(2);
        float f2 = 1.0f;
        if (p80Var.a() && p80Var.a()) {
            int iC10 = p80Var.c(8);
            if (iC10 == 255) {
                int iC11 = p80Var.c(16);
                int iC12 = p80Var.c(16);
                if (iC11 != 0 && iC12 != 0) {
                    f2 = iC11 / iC12;
                }
                f = f2;
            } else {
                float[] fArr = m80.b;
                if (iC10 < fArr.length) {
                    f = fArr[iC10];
                } else {
                    Log.w("H265Reader", "Unexpected aspect_ratio_idc value: " + iC10);
                    f = 1.0f;
                }
            }
        } else {
            f = 1.0f;
        }
        return com.google.android.exoplayer2.j.a(str, "video/hevc", (String) null, -1, -1, i4, i5, -1.0f, (List<byte[]>) Collections.singletonList(bArr), -1, f, (com.google.android.exoplayer2.c.a) null);
    }

    public static void a(p80 p80Var) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = 1;
                if (!p80Var.a()) {
                    p80Var.c();
                } else {
                    int iMin = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        p80Var.d();
                    }
                    for (int i4 = 0; i4 < iMin; i4++) {
                        p80Var.d();
                    }
                }
                if (i == 3) {
                    i3 = 3;
                }
                i2 += i3;
            }
        }
    }
}
