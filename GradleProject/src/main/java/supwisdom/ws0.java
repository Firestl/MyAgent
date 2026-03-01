package supwisdom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Logger;

/* JADX INFO: compiled from: AbstractBox.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ws0 implements os {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9654a;
    public byte[] b;
    public ByteBuffer c;
    public ByteBuffer d = null;

    static {
        Logger.getLogger(ws0.class.getName());
    }

    public ws0(String str) {
        this.f9654a = str;
    }

    public abstract long a();

    public abstract void a(ByteBuffer byteBuffer);

    public String b() {
        return this.f9654a;
    }

    public byte[] c() {
        return this.b;
    }

    public final boolean d() {
        long jLimit;
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer == null) {
            long jA = a();
            ByteBuffer byteBuffer2 = this.d;
            jLimit = jA + ((long) (byteBuffer2 != null ? byteBuffer2.limit() : 0)) + 8;
        } else {
            jLimit = byteBuffer.limit();
        }
        return jLimit < 4294967296L;
    }

    @Override // supwisdom.os
    public void getBox(WritableByteChannel writableByteChannel) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(lt0.a(getSize()));
        b(byteBufferAllocate);
        ByteBuffer byteBuffer = this.c;
        if (byteBuffer == null) {
            a(byteBufferAllocate);
            ByteBuffer byteBuffer2 = this.d;
            if (byteBuffer2 != null) {
                byteBuffer2.rewind();
                while (this.d.remaining() > 0) {
                    byteBufferAllocate.put(this.d);
                }
            }
        } else {
            byteBuffer.rewind();
            byteBufferAllocate.put(this.c);
        }
        byteBufferAllocate.rewind();
        writableByteChannel.write(byteBufferAllocate);
    }

    @Override // supwisdom.os
    public long getSize() {
        long jA = this.c == null ? a() : r0.limit();
        long j = jA + ((long) ((jA >= 4294967288L ? 8 : 0) + 8 + ("uuid".equals(b()) ? 16 : 0)));
        ByteBuffer byteBuffer = this.d;
        return j + ((long) (byteBuffer != null ? byteBuffer.limit() : 0));
    }

    @Override // supwisdom.os
    public void setParent(qs qsVar) {
    }

    public final void b(ByteBuffer byteBuffer) {
        if (d()) {
            ks.a(byteBuffer, getSize());
            byteBuffer.put(js.a(b()));
        } else {
            ks.a(byteBuffer, 1L);
            byteBuffer.put(js.a(b()));
            ks.b(byteBuffer, getSize());
        }
        if ("uuid".equals(b())) {
            byteBuffer.put(c());
        }
    }
}
