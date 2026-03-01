package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: compiled from: DecoderConfigDescriptor.java */
/* JADX INFO: loaded from: classes.dex */
public class gt0 extends et0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7769a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f7770e;
    public long f;
    public ht0 g;
    public dt0 h;
    public List<Object> i = new ArrayList();
    public byte[] j;

    static {
        Logger.getLogger(gt0.class.getName());
    }

    public ByteBuffer a() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(b());
        ks.c(byteBufferAllocate, 4);
        ks.c(byteBufferAllocate, b() - 2);
        ks.c(byteBufferAllocate, this.f7769a);
        ks.c(byteBufferAllocate, (this.b << 2) | (this.c << 1) | 1);
        ks.b(byteBufferAllocate, this.d);
        ks.a(byteBufferAllocate, this.f7770e);
        ks.a(byteBufferAllocate, this.f);
        byteBufferAllocate.put(this.h.b().array());
        return byteBufferAllocate;
    }

    public int b() {
        return this.h.c() + 15;
    }

    public void c(int i) {
        this.b = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecoderConfigDescriptor");
        sb.append("{objectTypeIndication=");
        sb.append(this.f7769a);
        sb.append(", streamType=");
        sb.append(this.b);
        sb.append(", upStream=");
        sb.append(this.c);
        sb.append(", bufferSizeDB=");
        sb.append(this.d);
        sb.append(", maxBitRate=");
        sb.append(this.f7770e);
        sb.append(", avgBitRate=");
        sb.append(this.f);
        sb.append(", decoderSpecificInfo=");
        sb.append(this.g);
        sb.append(", audioSpecificInfo=");
        sb.append(this.h);
        sb.append(", configDescriptorDeadBytes=");
        byte[] bArr = this.j;
        if (bArr == null) {
            bArr = new byte[0];
        }
        sb.append(is.a(bArr));
        sb.append(", profileLevelIndicationDescriptors=");
        List<Object> list = this.i;
        sb.append(list == null ? com.igexin.push.core.b.m : Arrays.asList(list).toString());
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public void b(int i) {
        this.f7769a = i;
    }

    public void b(long j) {
        this.f7770e = j;
    }

    public void a(dt0 dt0Var) {
        this.h = dt0Var;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(long j) {
        this.f = j;
    }
}
