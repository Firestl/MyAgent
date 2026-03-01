package supwisdom;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* JADX INFO: compiled from: ByteBufferByteChannel.java */
/* JADX INFO: loaded from: classes.dex */
public class kt0 implements ByteChannel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ByteBuffer f8202a;

    public kt0(ByteBuffer byteBuffer) {
        this.f8202a = byteBuffer;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        byte[] bArrArray = byteBuffer.array();
        int iRemaining = byteBuffer.remaining();
        if (this.f8202a.remaining() < iRemaining) {
            throw new EOFException("Reading beyond end of stream");
        }
        this.f8202a.get(bArrArray, byteBuffer.position(), iRemaining);
        return iRemaining;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        int iRemaining = byteBuffer.remaining();
        this.f8202a.put(byteBuffer);
        return iRemaining;
    }
}
