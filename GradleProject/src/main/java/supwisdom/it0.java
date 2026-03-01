package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: compiled from: ESDescriptor.java */
/* JADX INFO: loaded from: classes.dex */
public class it0 extends et0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7982a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7983e;
    public String g;
    public int h;
    public int i;
    public int j;
    public gt0 k;
    public jt0 l;
    public int f = 0;
    public List<et0> m = new ArrayList();

    static {
        Logger.getLogger(it0.class.getName());
    }

    public ByteBuffer a() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(b());
        ks.c(byteBufferAllocate, 3);
        ks.c(byteBufferAllocate, b() - 2);
        ks.a(byteBufferAllocate, this.f7982a);
        ks.c(byteBufferAllocate, (this.b << 7) | (this.c << 6) | (this.d << 5) | (this.f7983e & 31));
        if (this.b > 0) {
            ks.a(byteBufferAllocate, this.i);
        }
        if (this.c > 0) {
            ks.c(byteBufferAllocate, this.f);
            ks.b(byteBufferAllocate, this.g);
        }
        if (this.d > 0) {
            ks.a(byteBufferAllocate, this.j);
        }
        ByteBuffer byteBufferA = this.k.a();
        ByteBuffer byteBufferA2 = this.l.a();
        byteBufferAllocate.put(byteBufferA.array());
        byteBufferAllocate.put(byteBufferA2.array());
        return byteBufferAllocate;
    }

    public int b() {
        int i = this.b > 0 ? 7 : 5;
        if (this.c > 0) {
            i += this.f + 1;
        }
        if (this.d > 0) {
            i += 2;
        }
        return i + this.k.b() + this.l.b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || it0.class != obj.getClass()) {
            return false;
        }
        it0 it0Var = (it0) obj;
        if (this.c != it0Var.c || this.f != it0Var.f || this.i != it0Var.i || this.f7982a != it0Var.f7982a || this.j != it0Var.j || this.d != it0Var.d || this.h != it0Var.h || this.b != it0Var.b || this.f7983e != it0Var.f7983e) {
            return false;
        }
        String str = this.g;
        if (str == null ? it0Var.g != null : !str.equals(it0Var.g)) {
            return false;
        }
        gt0 gt0Var = this.k;
        if (gt0Var == null ? it0Var.k != null : !gt0Var.equals(it0Var.k)) {
            return false;
        }
        List<et0> list = this.m;
        if (list == null ? it0Var.m != null : !list.equals(it0Var.m)) {
            return false;
        }
        jt0 jt0Var = this.l;
        jt0 jt0Var2 = it0Var.l;
        return jt0Var == null ? jt0Var2 == null : jt0Var.equals(jt0Var2);
    }

    public int hashCode() {
        int i = ((((((((((this.f7982a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.f7983e) * 31) + this.f) * 31;
        String str = this.g;
        int iHashCode = (((((((i + (str != null ? str.hashCode() : 0)) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31;
        gt0 gt0Var = this.k;
        int iHashCode2 = (iHashCode + (gt0Var != null ? gt0Var.hashCode() : 0)) * 31;
        jt0 jt0Var = this.l;
        int iHashCode3 = (iHashCode2 + (jt0Var != null ? jt0Var.hashCode() : 0)) * 31;
        List<et0> list = this.m;
        return iHashCode3 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "ESDescriptor{esId=" + this.f7982a + ", streamDependenceFlag=" + this.b + ", URLFlag=" + this.c + ", oCRstreamFlag=" + this.d + ", streamPriority=" + this.f7983e + ", URLLength=" + this.f + ", URLString='" + this.g + Operators.SINGLE_QUOTE + ", remoteODFlag=" + this.h + ", dependsOnEsId=" + this.i + ", oCREsId=" + this.j + ", decoderConfigDescriptor=" + this.k + ", slConfigDescriptor=" + this.l + Operators.BLOCK_END;
    }

    public void a(gt0 gt0Var) {
        this.k = gt0Var;
    }

    public void a(jt0 jt0Var) {
        this.l = jt0Var;
    }

    public void a(int i) {
        this.f7982a = i;
    }
}
