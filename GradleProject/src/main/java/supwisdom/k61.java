package supwisdom;

import com.sangfor.dex.util.ExceptionWithContext;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class k61 implements h61, ry0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8129a;
    public byte[] b;
    public int c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<a> f8130e;
    public int f;
    public int g;

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8131a;
        public int b;
        public final String c;

        public a(int i, int i2, String str) {
            this.f8131a = i;
            this.b = i2;
            this.c = str;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.f8131a;
        }

        public String c() {
            return this.c;
        }

        public void a(int i) {
            this.b = i;
        }

        public void b(int i) {
            if (this.b == Integer.MAX_VALUE) {
                this.b = i;
            }
        }

        public a(int i, String str) {
            this(i, Integer.MAX_VALUE, str);
        }
    }

    public k61(byte[] bArr) {
        this(bArr, false);
    }

    public static void i() {
        throw new IndexOutOfBoundsException("attempt to write past the end");
    }

    @Override // supwisdom.h61
    public void a(String str) {
        if (this.f8130e == null) {
            return;
        }
        d();
        this.f8130e.add(new a(this.c, str));
    }

    @Override // supwisdom.s61
    public void b(int i) {
        if (this.c == i) {
            return;
        }
        throw new ExceptionWithContext("expected cursor " + i + "; actual value: " + this.c);
    }

    @Override // supwisdom.h61
    public boolean c() {
        return this.d;
    }

    @Override // supwisdom.s61
    public void d(int i) {
        int i2 = i - 1;
        if (i < 0 || (i & i2) != 0) {
            throw new IllegalArgumentException("bogus alignment");
        }
        int i3 = (this.c + i2) & (~i2);
        if (this.f8129a) {
            e(i3);
        } else if (i3 > this.b.length) {
            i();
            throw null;
        }
        Arrays.fill(this.b, this.c, i3, (byte) 0);
        this.c = i3;
    }

    @Override // supwisdom.h61
    public boolean e() {
        return this.f8130e != null;
    }

    public void f() {
        d();
        ArrayList<a> arrayList = this.f8130e;
        if (arrayList != null) {
            for (int size = arrayList.size(); size > 0; size--) {
                int i = size - 1;
                a aVar = this.f8130e.get(i);
                if (aVar.b() <= this.c) {
                    int iA = aVar.a();
                    int i2 = this.c;
                    if (iA > i2) {
                        aVar.a(i2);
                        return;
                    }
                    return;
                }
                this.f8130e.remove(i);
            }
        }
    }

    public byte[] g() {
        return this.b;
    }

    public byte[] h() {
        int i = this.c;
        byte[] bArr = new byte[i];
        System.arraycopy(this.b, 0, bArr, 0, i);
        return bArr;
    }

    @Override // supwisdom.s61
    public void write(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    @Override // supwisdom.s61, supwisdom.ry0
    public void writeByte(int i) {
        int i2 = this.c;
        int i3 = i2 + 1;
        if (this.f8129a) {
            e(i3);
        } else if (i3 > this.b.length) {
            i();
            throw null;
        }
        this.b[i2] = (byte) i;
        this.c = i3;
    }

    @Override // supwisdom.s61
    public void writeInt(int i) {
        int i2 = this.c;
        int i3 = i2 + 4;
        if (this.f8129a) {
            e(i3);
        } else if (i3 > this.b.length) {
            i();
            throw null;
        }
        byte[] bArr = this.b;
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
        this.c = i3;
    }

    @Override // supwisdom.s61
    public void writeShort(int i) {
        int i2 = this.c;
        int i3 = i2 + 2;
        if (this.f8129a) {
            e(i3);
        } else if (i3 > this.b.length) {
            i();
            throw null;
        }
        byte[] bArr = this.b;
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        this.c = i3;
    }

    public k61() {
        this(1000);
    }

    @Override // supwisdom.s61
    public int c(int i) {
        if (this.f8129a) {
            e(this.c + 5);
        }
        int i2 = this.c;
        py0.b(this, i);
        return this.c - i2;
    }

    public final void e(int i) {
        byte[] bArr = this.b;
        if (bArr.length < i) {
            byte[] bArr2 = new byte[(i * 2) + 1000];
            System.arraycopy(bArr, 0, bArr2, 0, this.c);
            this.b = bArr2;
        }
    }

    public k61(int i) {
        this(new byte[i], true);
    }

    @Override // supwisdom.h61
    public int b() {
        int i = this.g;
        return this.f - (((i * 2) + 8) + (i / 2));
    }

    public k61(byte[] bArr, boolean z) {
        if (bArr != null) {
            this.f8129a = z;
            this.b = bArr;
            this.c = 0;
            this.d = false;
            this.f8130e = null;
            this.f = 0;
            this.g = 0;
            return;
        }
        throw new NullPointerException("data == null");
    }

    public void a(int i, boolean z) {
        if (this.f8130e != null || this.c != 0) {
            throw new RuntimeException("cannot enable annotations");
        }
        if (i >= 40) {
            int i2 = (((i - 7) / 15) + 1) & (-2);
            if (i2 < 6) {
                i2 = 6;
            } else if (i2 > 10) {
                i2 = 10;
            }
            this.f8130e = new ArrayList<>(1000);
            this.f = i;
            this.g = i2;
            this.d = z;
            return;
        }
        throw new IllegalArgumentException("annotationWidth < 40");
    }

    @Override // supwisdom.h61
    public void d() {
        int size;
        ArrayList<a> arrayList = this.f8130e;
        if (arrayList == null || (size = arrayList.size()) == 0) {
            return;
        }
        this.f8130e.get(size - 1).b(this.c);
    }

    public int f(int i) {
        if (this.f8129a) {
            e(this.c + 5);
        }
        int i2 = this.c;
        py0.a(this, i);
        return this.c - i2;
    }

    @Override // supwisdom.s61
    public int a() {
        return this.c;
    }

    @Override // supwisdom.s61
    public void a(j61 j61Var) {
        int iA = j61Var.a();
        int i = this.c;
        int i2 = iA + i;
        if (this.f8129a) {
            e(i2);
        } else if (i2 > this.b.length) {
            i();
            throw null;
        }
        j61Var.a(this.b, i);
        this.c = i2;
    }

    public void a(Writer writer) {
        int i;
        String strC;
        int i2;
        int i3;
        u61 u61Var = new u61(writer, (this.f - r0) - 1, b(), "|");
        Writer writerD = u61Var.d();
        Writer writerE = u61Var.e();
        int size = this.f8130e.size();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            i = this.c;
            if (i5 >= i || i4 >= size) {
                break;
            }
            a aVar = this.f8130e.get(i4);
            int iB = aVar.b();
            if (i5 < iB) {
                strC = "";
                i3 = iB;
                i2 = i5;
            } else {
                int iA = aVar.a();
                strC = aVar.c();
                i4++;
                i2 = iB;
                i3 = iA;
            }
            writerD.write(m61.a(this.b, i2, i3 - i2, i2, this.g, 6));
            writerE.write(strC);
            u61Var.a();
            i5 = i3;
        }
        if (i5 < i) {
            writerD.write(m61.a(this.b, i5, i - i5, i5, this.g, 6));
        }
        while (i4 < size) {
            writerE.write(this.f8130e.get(i4).c());
            i4++;
        }
        u61Var.a();
    }

    @Override // supwisdom.s61
    public void a(int i) {
        if (i >= 0) {
            int i2 = this.c + i;
            if (this.f8129a) {
                e(i2);
            } else if (i2 > this.b.length) {
                i();
                throw null;
            }
            Arrays.fill(this.b, this.c, i2, (byte) 0);
            this.c = i2;
            return;
        }
        throw new IllegalArgumentException("count < 0");
    }

    @Override // supwisdom.h61
    public void a(int i, String str) {
        if (this.f8130e == null) {
            return;
        }
        d();
        int size = this.f8130e.size();
        int iA = size == 0 ? 0 : this.f8130e.get(size - 1).a();
        int i2 = this.c;
        if (iA <= i2) {
            iA = i2;
        }
        this.f8130e.add(new a(iA, i + iA, str));
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3 = this.c;
        int i4 = i3 + i2;
        int i5 = i + i2;
        if ((i | i2 | i4) >= 0 && i5 <= bArr.length) {
            if (this.f8129a) {
                e(i4);
            } else if (i4 > this.b.length) {
                i();
                throw null;
            }
            System.arraycopy(bArr, i, this.b, i3, i2);
            this.c = i4;
            return;
        }
        throw new IndexOutOfBoundsException("bytes.length " + bArr.length + "; " + i + "..!" + i4);
    }
}
