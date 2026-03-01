package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Date;

/* JADX INFO: compiled from: TrackHeaderBox.java */
/* JADX INFO: loaded from: classes.dex */
public class lt extends ys0 {
    public Date g;
    public Date h;
    public long i;
    public long j;
    public int k;
    public int l;
    public float m;
    public nt0 n;
    public double o;
    public double p;

    public lt() {
        super("tkhd");
        this.n = nt0.j;
    }

    @Override // supwisdom.ws0
    public long a() {
        return (f() == 1 ? 36L : 24L) + 60;
    }

    public void b(Date date) {
        this.h = date;
    }

    public void c(int i) {
        this.k = i;
    }

    public int g() {
        return this.l;
    }

    public Date h() {
        return this.g;
    }

    public long i() {
        return this.j;
    }

    public double j() {
        return this.p;
    }

    public int k() {
        return this.k;
    }

    public Date l() {
        return this.h;
    }

    public long n() {
        return this.i;
    }

    public float o() {
        return this.m;
    }

    public double p() {
        return this.o;
    }

    public String toString() {
        return "TrackHeaderBox[creationTime=" + h() + ";modificationTime=" + l() + ";trackId=" + n() + ";duration=" + i() + ";layer=" + k() + ";alternateGroup=" + g() + ";volume=" + o() + ";matrix=" + this.n + ";width=" + p() + ";height=" + j() + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        if (f() == 1) {
            ks.b(byteBuffer, at0.a(this.g));
            ks.b(byteBuffer, at0.a(this.h));
            ks.a(byteBuffer, this.i);
            ks.a(byteBuffer, 0L);
            ks.b(byteBuffer, this.j);
        } else {
            ks.a(byteBuffer, at0.a(this.g));
            ks.a(byteBuffer, at0.a(this.h));
            ks.a(byteBuffer, this.i);
            ks.a(byteBuffer, 0L);
            ks.a(byteBuffer, this.j);
        }
        ks.a(byteBuffer, 0L);
        ks.a(byteBuffer, 0L);
        ks.a(byteBuffer, this.k);
        ks.a(byteBuffer, this.l);
        ks.c(byteBuffer, this.m);
        ks.a(byteBuffer, 0);
        this.n.a(byteBuffer);
        ks.b(byteBuffer, this.o);
        ks.b(byteBuffer, this.p);
    }

    public void b(long j) {
        this.i = j;
    }

    public void c(boolean z) {
        if (z) {
            a(e() | 4);
        } else {
            a(e() & (-5));
        }
    }

    public void b(int i) {
        this.l = i;
    }

    public void b(double d) {
        this.o = d;
    }

    public void b(boolean z) {
        if (z) {
            a(e() | 2);
        } else {
            a(e() & (-3));
        }
    }

    public void a(Date date) {
        this.g = date;
    }

    public void a(long j) {
        this.j = j;
    }

    public void a(float f) {
        this.m = f;
    }

    public void a(nt0 nt0Var) {
        this.n = nt0Var;
    }

    public void a(double d) {
        this.p = d;
    }

    public void a(boolean z) {
        if (z) {
            a(e() | 1);
        } else {
            a(e() & (-2));
        }
    }
}
