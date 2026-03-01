package supwisdom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AvcConfigurationBox.java */
/* JADX INFO: loaded from: classes.dex */
public final class nt extends ws0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f8562e;

    public nt() {
        super("avcC");
        this.f8562e = new a();
    }

    public void a(int i) {
        this.f8562e.d = i;
    }

    public void b(int i) {
        this.f8562e.b = i;
    }

    public void c(int i) {
        this.f8562e.k = i;
    }

    public void d(int i) {
        this.f8562e.j = i;
    }

    public void e(int i) {
        this.f8562e.i = i;
    }

    public void f(int i) {
        this.f8562e.f8563a = i;
    }

    public void g(int i) {
        this.f8562e.f8564e = i;
    }

    public void h(int i) {
        this.f8562e.c = i;
    }

    public void a(List<byte[]> list) {
        this.f8562e.g = list;
    }

    public void b(List<byte[]> list) {
        this.f8562e.f = list;
    }

    public void a(boolean z) {
        this.f8562e.h = z;
    }

    @Override // supwisdom.ws0
    public long a() {
        return this.f8562e.a();
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        this.f8562e.a(byteBuffer);
    }

    /* JADX INFO: compiled from: AvcConfigurationBox.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f8563a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8564e;
        public List<byte[]> f = new ArrayList();
        public List<byte[]> g = new ArrayList();
        public boolean h = true;
        public int i = 1;
        public int j = 0;
        public int k = 0;
        public List<byte[]> l = new ArrayList();
        public int m = 60;
        public int n = 7;
        public int o = 31;
        public int p = 31;
        public int q = 31;

        public void a(ByteBuffer byteBuffer) {
            ks.c(byteBuffer, this.f8563a);
            ks.c(byteBuffer, this.b);
            ks.c(byteBuffer, this.c);
            ks.c(byteBuffer, this.d);
            ft0 ft0Var = new ft0(byteBuffer);
            ft0Var.a(this.m, 6);
            ft0Var.a(this.f8564e, 2);
            ft0Var.a(this.n, 3);
            ft0Var.a(this.g.size(), 5);
            for (byte[] bArr : this.f) {
                ks.a(byteBuffer, bArr.length);
                byteBuffer.put(bArr);
            }
            ks.c(byteBuffer, this.g.size());
            for (byte[] bArr2 : this.g) {
                ks.a(byteBuffer, bArr2.length);
                byteBuffer.put(bArr2);
            }
            if (this.h) {
                int i = this.b;
                if (i == 100 || i == 110 || i == 122 || i == 144) {
                    ft0 ft0Var2 = new ft0(byteBuffer);
                    ft0Var2.a(this.o, 6);
                    ft0Var2.a(this.i, 2);
                    ft0Var2.a(this.p, 5);
                    ft0Var2.a(this.j, 3);
                    ft0Var2.a(this.q, 5);
                    ft0Var2.a(this.k, 3);
                    for (byte[] bArr3 : this.l) {
                        ks.a(byteBuffer, bArr3.length);
                        byteBuffer.put(bArr3);
                    }
                }
            }
        }

        public long a() {
            int i;
            Iterator<byte[]> it = this.f.iterator();
            long length = 6;
            while (it.hasNext()) {
                length = length + 2 + ((long) it.next().length);
            }
            long length2 = length + 1;
            Iterator<byte[]> it2 = this.g.iterator();
            while (it2.hasNext()) {
                length2 = length2 + 2 + ((long) it2.next().length);
            }
            if (this.h && ((i = this.b) == 100 || i == 110 || i == 122 || i == 144)) {
                length2 += 4;
                Iterator<byte[]> it3 = this.l.iterator();
                while (it3.hasNext()) {
                    length2 = length2 + 2 + ((long) it3.next().length);
                }
            }
            return length2;
        }
    }
}
