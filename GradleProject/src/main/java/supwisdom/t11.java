package supwisdom;

import com.sangfor.dex.util.ExceptionWithContext;
import com.sangfor.dx.dex.file.ItemType;
import java.io.Writer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import supwisdom.o21;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class t11 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cz0 f9234a;
    public final o21 b;
    public final o21 c;
    public final o21 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o21 f9235e;
    public final x21 f;
    public final z21 g;
    public final s21 h;
    public final a21 i;
    public final n21 j;
    public final p11 k;
    public final o21 l;
    public final k11 m;
    public final l21 n;
    public final o21 o;
    public final c21 p;
    public final t21[] q;
    public int r;
    public int s;

    /* JADX INFO: compiled from: Proguard */
    public static final class a {
        public byte[] a(int i) {
            throw null;
        }
    }

    public t11(cz0 cz0Var) {
        this.f9234a = cz0Var;
        c21 c21Var = new c21(this);
        this.p = c21Var;
        o21.c cVar = o21.c.NONE;
        o21 o21Var = new o21(null, this, 4, cVar);
        this.c = o21Var;
        o21.c cVar2 = o21.c.TYPE;
        o21 o21Var2 = new o21("word_data", this, 4, cVar2);
        this.b = o21Var2;
        o21 o21Var3 = new o21("string_data", this, 1, o21.c.INSTANCE);
        this.f9235e = o21Var3;
        o21 o21Var4 = new o21(null, this, 1, cVar);
        this.l = o21Var4;
        o21 o21Var5 = new o21("byte_data", this, 1, cVar2);
        this.o = o21Var5;
        x21 x21Var = new x21(this);
        this.f = x21Var;
        z21 z21Var = new z21(this);
        this.g = z21Var;
        s21 s21Var = new s21(this);
        this.h = s21Var;
        a21 a21Var = new a21(this);
        this.i = a21Var;
        n21 n21Var = new n21(this);
        this.j = n21Var;
        p11 p11Var = new p11(this);
        this.k = p11Var;
        o21 o21Var6 = new o21("map", this, 4, cVar);
        this.d = o21Var6;
        if (cz0Var.a(26)) {
            k11 k11Var = new k11(this);
            this.m = k11Var;
            l21 l21Var = new l21(this);
            this.n = l21Var;
            this.q = new t21[]{c21Var, x21Var, z21Var, s21Var, a21Var, n21Var, p11Var, k11Var, l21Var, o21Var2, o21Var, o21Var3, o21Var5, o21Var4, o21Var6};
        } else {
            this.m = null;
            this.n = null;
            this.q = new t21[]{c21Var, x21Var, z21Var, s21Var, a21Var, n21Var, p11Var, o21Var2, o21Var, o21Var3, o21Var5, o21Var4, o21Var6};
        }
        this.r = -1;
        this.s = 79;
    }

    public static void b(byte[] bArr, int i) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            messageDigest.update(bArr, 32, i - 32);
            try {
                int iDigest = messageDigest.digest(bArr, 12, 20);
                if (iDigest == 20) {
                    return;
                }
                throw new RuntimeException("unexpected digest write: " + iDigest + " bytes");
            } catch (DigestException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    public void a(o11 o11Var) {
        this.k.a(o11Var);
    }

    public p11 c() {
        return this.k;
    }

    public cz0 d() {
        return this.f9234a;
    }

    public a21 e() {
        return this.i;
    }

    public int f() {
        int i = this.r;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("file size not yet known");
    }

    public t21 g() {
        return this.b;
    }

    public t21 h() {
        return this.d;
    }

    public o21 i() {
        return this.d;
    }

    public l21 j() {
        return this.n;
    }

    public n21 k() {
        return this.j;
    }

    public s21 l() {
        return this.h;
    }

    public u21 m() {
        u21 u21Var = new u21();
        for (t21 t21Var : this.q) {
            u21Var.a(t21Var);
        }
        return u21Var;
    }

    public o21 n() {
        return this.f9235e;
    }

    public x21 o() {
        return this.f;
    }

    public z21 p() {
        return this.g;
    }

    public o21 q() {
        return this.c;
    }

    public o21 r() {
        return this.b;
    }

    public static void a(byte[] bArr, int i) {
        Adler32 adler32 = new Adler32();
        adler32.update(bArr, 12, i - 12);
        int value = (int) adler32.getValue();
        bArr[8] = (byte) value;
        bArr[9] = (byte) (value >> 8);
        bArr[10] = (byte) (value >> 16);
        bArr[11] = (byte) (value >> 24);
    }

    public o21 b() {
        return this.l;
    }

    public void b(u41 u41Var) {
        if (u41Var != null) {
            if (u41Var instanceof v51) {
                this.f.b((v51) u41Var);
                return;
            }
            if (u41Var instanceof w51) {
                this.g.b((w51) u41Var);
                return;
            }
            if (u41Var instanceof x41) {
                this.j.b((x41) u41Var);
                return;
            }
            if (u41Var instanceof f51) {
                this.i.b((f51) u41Var);
                return;
            }
            if (u41Var instanceof e51) {
                this.i.b(((e51) u41Var).f());
                return;
            } else if (u41Var instanceof t51) {
                this.h.b(((t51) u41Var).d());
                return;
            } else {
                if (u41Var instanceof q51) {
                    this.n.b((q51) u41Var);
                    return;
                }
                return;
            }
        }
        throw new NullPointerException("cst == null");
    }

    public e21 a(u41 u41Var) {
        if (u41Var instanceof v51) {
            return this.f.a(u41Var);
        }
        if (u41Var instanceof w51) {
            return this.g.a(u41Var);
        }
        if (u41Var instanceof x41) {
            return this.j.a(u41Var);
        }
        if (u41Var instanceof f51) {
            return this.i.a(u41Var);
        }
        if (u41Var instanceof e51) {
            return this.i.b(((e51) u41Var).f());
        }
        if (u41Var instanceof t51) {
            return this.h.a(u41Var);
        }
        if (u41Var instanceof q51) {
            return this.n.a(u41Var);
        }
        if (u41Var instanceof b51) {
            return this.m.a(u41Var);
        }
        return null;
    }

    public o21 a() {
        return this.o;
    }

    public byte[] a(Writer writer, boolean z) {
        boolean z2 = writer != null;
        k61 k61VarA = a(z2, z, null);
        if (z2) {
            k61VarA.a(writer);
        }
        return k61VarA.g();
    }

    public final k61 a(boolean z, boolean z2, a aVar) {
        ExceptionWithContext exceptionWithContext;
        this.k.e();
        this.l.e();
        this.b.e();
        if (this.f9234a.a(26)) {
            this.m.e();
        }
        this.o.e();
        if (this.f9234a.a(26)) {
            this.n.e();
        }
        this.j.e();
        this.i.e();
        this.h.e();
        this.c.e();
        this.g.e();
        this.f.e();
        this.f9235e.e();
        this.p.e();
        int length = this.q.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            t21 t21Var = this.q[i2];
            if ((t21Var != this.m && t21Var != this.n) || !t21Var.d().isEmpty()) {
                int iB = t21Var.b(i);
                if (iB >= i) {
                    try {
                        o21 o21Var = this.d;
                        if (t21Var == o21Var) {
                            g21.a(this.q, o21Var);
                            this.d.e();
                        }
                        if (t21Var instanceof o21) {
                            ((o21) t21Var).j();
                        }
                        i = t21Var.i() + iB;
                    } catch (RuntimeException e2) {
                        throw ExceptionWithContext.withContext(e2, "...while writing section " + i2);
                    }
                } else {
                    throw new RuntimeException("bogus placement for section " + i2);
                }
            }
        }
        this.r = i;
        if (aVar == null) {
            byte[] bArr = new byte[i];
            k61 k61Var = new k61(bArr);
            if (z) {
                k61Var.a(this.s, z2);
            }
            for (int i3 = 0; i3 < length; i3++) {
                try {
                    t21 t21Var2 = this.q[i3];
                    if ((t21Var2 != this.m && t21Var2 != this.n) || !t21Var2.d().isEmpty()) {
                        int iC = t21Var2.c() - k61Var.a();
                        if (iC >= 0) {
                            k61Var.a(iC);
                            t21Var2.b(k61Var);
                        } else {
                            throw new ExceptionWithContext("excess write of " + (-iC));
                        }
                    }
                } catch (RuntimeException e3) {
                    if (e3 instanceof ExceptionWithContext) {
                        exceptionWithContext = (ExceptionWithContext) e3;
                    } else {
                        exceptionWithContext = new ExceptionWithContext(e3);
                    }
                    exceptionWithContext.addContext("...while writing section " + i3);
                    throw exceptionWithContext;
                }
            }
            if (k61Var.a() == this.r) {
                b(bArr, k61Var.a());
                a(bArr, k61Var.a());
                if (z) {
                    this.b.a(k61Var, ItemType.TYPE_CODE_ITEM, "\nmethod code index:\n\n");
                    m().a(k61Var);
                    k61Var.f();
                }
                return k61Var;
            }
            throw new RuntimeException("foreshortened write");
        }
        aVar.a(i);
        throw null;
    }
}
