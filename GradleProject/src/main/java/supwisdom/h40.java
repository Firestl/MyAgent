package supwisdom;

import android.util.Pair;
import java.util.Arrays;
import supwisdom.u40;

/* JADX INFO: compiled from: H262Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class h40 implements g40 {
    public static final double[] n = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7798a;
    public f50 b;
    public boolean c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean[] f7799e = new boolean[4];
    public final a f = new a(128);
    public boolean g;
    public long h;
    public long i;
    public boolean j;
    public boolean k;
    public long l;
    public long m;

    @Override // supwisdom.g40
    public void a() {
        m80.a(this.f7799e);
        this.f.a();
        this.j = false;
        this.g = false;
        this.h = 0L;
    }

    @Override // supwisdom.g40
    public void b() {
    }

    /* JADX INFO: compiled from: H262Reader.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7800a;
        public int b;
        public int c;
        public byte[] d;

        public a(int i) {
            this.d = new byte[i];
        }

        public void a() {
            this.f7800a = false;
            this.b = 0;
            this.c = 0;
        }

        public boolean a(int i, int i2) {
            if (this.f7800a) {
                if (this.c == 0 && i == 181) {
                    this.c = this.b;
                } else {
                    this.b -= i2;
                    this.f7800a = false;
                    return true;
                }
            } else if (i == 179) {
                this.f7800a = true;
            }
            return false;
        }

        public void a(byte[] bArr, int i, int i2) {
            if (this.f7800a) {
                int i3 = i2 - i;
                byte[] bArr2 = this.d;
                int length = bArr2.length;
                int i4 = this.b;
                if (length < i4 + i3) {
                    this.d = Arrays.copyOf(bArr2, (i4 + i3) * 2);
                }
                System.arraycopy(bArr, i, this.d, this.b, i3);
                this.b += i3;
            }
        }
    }

    @Override // supwisdom.g40
    public void a(z40 z40Var, u40.d dVar) {
        dVar.a();
        this.f7798a = dVar.c();
        this.b = z40Var.a(dVar.b(), 2);
    }

    @Override // supwisdom.g40
    public void a(long j, boolean z) {
        boolean z2 = j != -9223372036854775807L;
        this.j = z2;
        if (z2) {
            this.i = j;
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
    @Override // supwisdom.g40
    public void a(o80 o80Var) {
        int i;
        int iD = o80Var.d();
        int iC = o80Var.c();
        byte[] bArr = o80Var.f8644a;
        this.h += (long) o80Var.b();
        this.b.a(o80Var, o80Var.b());
        int i2 = iD;
        while (true) {
            int iA = m80.a(bArr, iD, iC, this.f7799e);
            if (iA == iC) {
                break;
            }
            int i3 = iA + 3;
            int i4 = o80Var.f8644a[i3] & 255;
            if (!this.c) {
                int i5 = iA - i2;
                if (i5 > 0) {
                    this.f.a(bArr, i2, iA);
                }
                if (this.f.a(i4, i5 < 0 ? -i5 : 0)) {
                    Pair<com.google.android.exoplayer2.j, Long> pairA = a(this.f, this.f7798a);
                    this.b.a((com.google.android.exoplayer2.j) pairA.first);
                    this.d = ((Long) pairA.second).longValue();
                    this.c = true;
                }
            }
            if (this.c && (i4 == 184 || i4 == 0)) {
                int i6 = iC - iA;
                if (this.g) {
                    this.b.a(this.m, this.k ? 1 : 0, ((int) (this.h - this.l)) - i6, i6, null);
                    this.k = false;
                    i = i4;
                } else {
                    i = i4;
                }
                if (i == 184) {
                    this.g = false;
                    this.k = true;
                } else {
                    this.m = this.j ? this.i : this.m + this.d;
                    this.l = this.h - ((long) i6);
                    this.j = false;
                    this.g = true;
                }
            }
            i2 = iA;
            iD = i3;
        }
        if (this.c) {
            return;
        }
        this.f.a(bArr, i2, iC);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair<com.google.android.exoplayer2.j, java.lang.Long> a(supwisdom.h40.a r20, java.lang.String r21) {
        /*
            r0 = r20
            byte[] r1 = r0.d
            int r2 = r0.b
            byte[] r1 = java.util.Arrays.copyOf(r1, r2)
            r2 = 4
            r3 = r1[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r4 = 5
            r5 = r1[r4]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r6 = 6
            r6 = r1[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r2
            int r7 = r5 >> 4
            r13 = r3 | r7
            r3 = r5 & 15
            int r3 = r3 << 8
            r14 = r3 | r6
            r3 = 7
            r5 = r1[r3]
            r5 = r5 & 240(0xf0, float:3.36E-43)
            int r5 = r5 >> r2
            r6 = 2
            if (r5 == r6) goto L43
            r6 = 3
            if (r5 == r6) goto L3d
            if (r5 == r2) goto L37
            r2 = 1065353216(0x3f800000, float:1.0)
            r18 = 1065353216(0x3f800000, float:1.0)
            goto L4c
        L37:
            int r2 = r14 * 121
            float r2 = (float) r2
            int r5 = r13 * 100
            goto L48
        L3d:
            int r2 = r14 * 16
            float r2 = (float) r2
            int r5 = r13 * 9
            goto L48
        L43:
            int r2 = r14 * 4
            float r2 = (float) r2
            int r5 = r13 * 3
        L48:
            float r5 = (float) r5
            float r2 = r2 / r5
            r18 = r2
        L4c:
            r10 = 0
            r11 = -1
            r12 = -1
            r15 = -1082130432(0xffffffffbf800000, float:-1.0)
            java.util.List r16 = java.util.Collections.singletonList(r1)
            r17 = -1
            r19 = 0
            java.lang.String r9 = "video/mpeg2"
            r8 = r21
            com.google.android.exoplayer2.j r2 = com.google.android.exoplayer2.j.a(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            r5 = 0
            r3 = r1[r3]
            r3 = r3 & 15
            int r3 = r3 + (-1)
            if (r3 < 0) goto L92
            double[] r7 = supwisdom.h40.n
            int r8 = r7.length
            if (r3 >= r8) goto L92
            r5 = r7[r3]
            int r0 = r0.c
            int r0 = r0 + 9
            r3 = r1[r0]
            r3 = r3 & 96
            int r3 = r3 >> r4
            r0 = r1[r0]
            r0 = r0 & 31
            if (r3 == r0) goto L8b
            double r3 = (double) r3
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r3 = r3 + r7
            int r0 = r0 + 1
            double r0 = (double) r0
            double r3 = r3 / r0
            double r5 = r5 * r3
        L8b:
            r0 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r0 = r0 / r5
            long r5 = (long) r0
        L92:
            java.lang.Long r0 = java.lang.Long.valueOf(r5)
            android.util.Pair r0 = android.util.Pair.create(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.h40.a(supwisdom.h40$a, java.lang.String):android.util.Pair");
    }
}
