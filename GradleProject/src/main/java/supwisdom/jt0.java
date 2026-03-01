package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: SLConfigDescriptor.java */
/* JADX INFO: loaded from: classes.dex */
public class jt0 extends et0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8086a;

    public void a(int i) {
        this.f8086a = i;
    }

    public int b() {
        return 3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && jt0.class == obj.getClass() && this.f8086a == ((jt0) obj).f8086a;
    }

    public int hashCode() {
        return this.f8086a;
    }

    public String toString() {
        return "SLConfigDescriptor{predefined=" + this.f8086a + Operators.BLOCK_END;
    }

    public ByteBuffer a() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(3);
        ks.c(byteBufferAllocate, 6);
        ks.c(byteBufferAllocate, 1);
        ks.c(byteBufferAllocate, this.f8086a);
        return byteBufferAllocate;
    }
}
