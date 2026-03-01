package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: NioByteString.java */
/* JADX INFO: loaded from: classes.dex */
public final class cr0 extends ByteString.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteBuffer f7248a;

    public cr0(ByteBuffer byteBuffer) {
        gq0.a(byteBuffer, "buffer");
        this.f7248a = byteBuffer.slice().order(ByteOrder.nativeOrder());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return this.f7248a.asReadOnlyBuffer();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        return Collections.singletonList(asReadOnlyByteBuffer());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte byteAt(int i) {
        try {
            return this.f7248a.get(i);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw e2;
        } catch (IndexOutOfBoundsException e3) {
            throw new ArrayIndexOutOfBoundsException(e3.getMessage());
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        byteBuffer.put(this.f7248a.slice());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        ByteBuffer byteBufferSlice = this.f7248a.slice();
        byteBufferSlice.position(i);
        byteBufferSlice.get(bArr, i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (size() != byteString.size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        return obj instanceof cr0 ? this.f7248a.equals(((cr0) obj).f7248a) : obj instanceof kr0 ? obj.equals(this) : this.f7248a.equals(byteString.asReadOnlyByteBuffer());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte internalByteAt(int i) {
        return byteAt(i);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isValidUtf8() {
        return wr0.a(this.f7248a);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public rp0 newCodedInput() {
        return rp0.a(this.f7248a, true);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public InputStream newInput() {
        return new a();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialHash(int i, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + this.f7248a.get(i4);
        }
        return i;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialIsValidUtf8(int i, int i2, int i3) {
        return wr0.a(i, this.f7248a, i2, i3 + i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int size() {
        return this.f7248a.remaining();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteString substring(int i, int i2) {
        try {
            return new cr0(a(i, i2));
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw e2;
        } catch (IndexOutOfBoundsException e3) {
            throw new ArrayIndexOutOfBoundsException(e3.getMessage());
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public String toStringInternal(Charset charset) {
        byte[] byteArray;
        int iArrayOffset;
        int length;
        if (this.f7248a.hasArray()) {
            byteArray = this.f7248a.array();
            iArrayOffset = this.f7248a.arrayOffset() + this.f7248a.position();
            length = this.f7248a.remaining();
        } else {
            byteArray = toByteArray();
            iArrayOffset = 0;
            length = byteArray.length;
        }
        return new String(byteArray, iArrayOffset, length, charset);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
        if (!this.f7248a.hasArray()) {
            pp0.a(a(i, i2 + i), outputStream);
        } else {
            outputStream.write(this.f7248a.array(), this.f7248a.arrayOffset() + this.f7248a.position() + i, i2);
        }
    }

    /* JADX INFO: compiled from: NioByteString.java */
    public class a extends InputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ByteBuffer f7249a;

        public a() {
            this.f7249a = cr0.this.f7248a.slice();
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.f7249a.remaining();
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.f7249a.mark();
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.f7249a.hasRemaining()) {
                return this.f7249a.get() & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public void reset() throws IOException {
            try {
                this.f7249a.reset();
            } catch (InvalidMarkException e2) {
                throw new IOException(e2);
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!this.f7249a.hasRemaining()) {
                return -1;
            }
            int iMin = Math.min(i2, this.f7249a.remaining());
            this.f7249a.get(bArr, i, iMin);
            return iMin;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString.i
    public boolean a(ByteString byteString, int i, int i2) {
        return substring(0, i2).equals(byteString.substring(i, i2 + i));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(qp0 qp0Var) throws IOException {
        qp0Var.a(this.f7248a.slice());
    }

    public final ByteBuffer a(int i, int i2) {
        if (i >= this.f7248a.position() && i2 <= this.f7248a.limit() && i <= i2) {
            ByteBuffer byteBufferSlice = this.f7248a.slice();
            byteBufferSlice.position(i - this.f7248a.position());
            byteBufferSlice.limit(i2 - this.f7248a.position());
            return byteBufferSlice;
        }
        throw new IllegalArgumentException(String.format("Invalid indices [%d, %d]", Integer.valueOf(i), Integer.valueOf(i2)));
    }
}
