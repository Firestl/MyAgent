package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: DefaultAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public final class v70 implements q70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9486a;
    public final int b;
    public final byte[] c;
    public final p70[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9487e;
    public int f;
    public int g;
    public p70[] h;

    public v70(boolean z, int i) {
        this(z, i, 0);
    }

    public synchronized void a(int i) {
        boolean z = i < this.f9487e;
        this.f9487e = i;
        if (z) {
            b();
        }
    }

    @Override // supwisdom.q70
    public synchronized void b() {
        int i = 0;
        int iMax = Math.max(0, x80.a(this.f9487e, this.b) - this.f);
        if (iMax >= this.g) {
            return;
        }
        if (this.c != null) {
            int i2 = this.g - 1;
            while (i <= i2) {
                p70 p70Var = this.h[i];
                if (p70Var.f8774a == this.c) {
                    i++;
                } else {
                    p70 p70Var2 = this.h[i2];
                    if (p70Var2.f8774a != this.c) {
                        i2--;
                    } else {
                        this.h[i] = p70Var2;
                        this.h[i2] = p70Var;
                        i2--;
                        i++;
                    }
                }
            }
            iMax = Math.max(iMax, i);
            if (iMax >= this.g) {
                return;
            }
        }
        Arrays.fill(this.h, iMax, this.g, (Object) null);
        this.g = iMax;
    }

    @Override // supwisdom.q70
    public int c() {
        return this.b;
    }

    public synchronized void d() {
        if (this.f9486a) {
            a(0);
        }
    }

    public synchronized int e() {
        return this.f * this.b;
    }

    public v70(boolean z, int i, int i2) {
        e80.a(i > 0);
        e80.a(i2 >= 0);
        this.f9486a = z;
        this.b = i;
        this.g = i2;
        this.h = new p70[i2 + 100];
        if (i2 > 0) {
            this.c = new byte[i2 * i];
            for (int i3 = 0; i3 < i2; i3++) {
                this.h[i3] = new p70(this.c, i3 * i);
            }
        } else {
            this.c = null;
        }
        this.d = new p70[1];
    }

    @Override // supwisdom.q70
    public synchronized p70 a() {
        p70 p70Var;
        this.f++;
        if (this.g > 0) {
            p70[] p70VarArr = this.h;
            int i = this.g - 1;
            this.g = i;
            p70Var = p70VarArr[i];
            this.h[i] = null;
        } else {
            p70Var = new p70(new byte[this.b], 0);
        }
        return p70Var;
    }

    @Override // supwisdom.q70
    public synchronized void a(p70 p70Var) {
        this.d[0] = p70Var;
        a(this.d);
    }

    @Override // supwisdom.q70
    public synchronized void a(p70[] p70VarArr) {
        if (this.g + p70VarArr.length >= this.h.length) {
            this.h = (p70[]) Arrays.copyOf(this.h, Math.max(this.h.length * 2, this.g + p70VarArr.length));
        }
        for (p70 p70Var : p70VarArr) {
            e80.a(p70Var.f8774a == this.c || p70Var.f8774a.length == this.b);
            p70[] p70VarArr2 = this.h;
            int i = this.g;
            this.g = i + 1;
            p70VarArr2[i] = p70Var;
        }
        this.f -= p70VarArr.length;
        notifyAll();
    }
}
