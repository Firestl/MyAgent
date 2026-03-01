package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Date;

/* JADX INFO: compiled from: MovieHeaderBox.java */
/* JADX INFO: loaded from: classes.dex */
public class bt extends ys0 {
    public Date g;
    public Date h;
    public long i;
    public long j;
    public double k;
    public float l;
    public nt0 m;
    public long n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;

    public bt() {
        super("mvhd");
        this.k = 1.0d;
        this.l = 1.0f;
        this.m = nt0.j;
    }

    @Override // supwisdom.ws0
    public long a() {
        return (f() == 1 ? 32L : 20L) + 80;
    }

    public void b(Date date) {
        this.h = date;
    }

    public void c(long j) {
        this.i = j;
    }

    public Date g() {
        return this.g;
    }

    public long h() {
        return this.j;
    }

    public Date i() {
        return this.h;
    }

    public long j() {
        return this.n;
    }

    public double k() {
        return this.k;
    }

    public long l() {
        return this.i;
    }

    public float n() {
        return this.l;
    }

    public String toString() {
        return "MovieHeaderBox[creationTime=" + g() + ";modificationTime=" + i() + ";timescale=" + l() + ";duration=" + h() + ";rate=" + k() + ";volume=" + n() + ";matrix=" + this.m + ";nextTrackId=" + j() + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        if (f() == 1) {
            ks.b(byteBuffer, at0.a(this.g));
            ks.b(byteBuffer, at0.a(this.h));
            ks.a(byteBuffer, this.i);
            ks.b(byteBuffer, this.j);
        } else {
            ks.a(byteBuffer, at0.a(this.g));
            ks.a(byteBuffer, at0.a(this.h));
            ks.a(byteBuffer, this.i);
            ks.a(byteBuffer, this.j);
        }
        ks.b(byteBuffer, this.k);
        ks.c(byteBuffer, this.l);
        ks.a(byteBuffer, 0);
        ks.a(byteBuffer, 0L);
        ks.a(byteBuffer, 0L);
        this.m.a(byteBuffer);
        byteBuffer.putInt(this.o);
        byteBuffer.putInt(this.p);
        byteBuffer.putInt(this.q);
        byteBuffer.putInt(this.r);
        byteBuffer.putInt(this.s);
        byteBuffer.putInt(this.t);
        ks.a(byteBuffer, this.n);
    }

    public void b(long j) {
        this.n = j;
    }

    public void a(Date date) {
        this.g = date;
    }

    public void a(long j) {
        this.j = j;
    }

    public void a(nt0 nt0Var) {
        this.m = nt0Var;
    }
}
