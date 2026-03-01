package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: RopeByteString.java */
/* JADX INFO: loaded from: classes.dex */
public final class kr0 extends ByteString {
    public static final int[] f = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8193a;
    public final ByteString b;
    public final ByteString c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8194e;

    /* JADX INFO: compiled from: RopeByteString.java */
    public class a extends ByteString.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c f8195a;
        public ByteString.g b = a();

        public a() {
            this.f8195a = new c(kr0.this, null);
        }

        /* JADX WARN: Type inference failed for: r0v5, types: [com.google.crypto.tink.shaded.protobuf.ByteString$g] */
        public final ByteString.g a() {
            if (this.f8195a.hasNext()) {
                return this.f8195a.next().iterator2();
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b != null;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.g
        public byte nextByte() {
            ByteString.g gVar = this.b;
            if (gVar == null) {
                throw new NoSuchElementException();
            }
            byte bNextByte = gVar.nextByte();
            if (!this.b.hasNext()) {
                this.b = a();
            }
            return bNextByte;
        }
    }

    /* JADX INFO: compiled from: RopeByteString.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayDeque<ByteString> f8196a;

        public b() {
            this.f8196a = new ArrayDeque<>();
        }

        public final void b(ByteString byteString) {
            a aVar;
            int iA = a(byteString.size());
            int iC = kr0.c(iA + 1);
            if (this.f8196a.isEmpty() || this.f8196a.peek().size() >= iC) {
                this.f8196a.push(byteString);
                return;
            }
            int iC2 = kr0.c(iA);
            ByteString byteStringPop = this.f8196a.pop();
            while (true) {
                aVar = null;
                if (this.f8196a.isEmpty() || this.f8196a.peek().size() >= iC2) {
                    break;
                } else {
                    byteStringPop = new kr0(this.f8196a.pop(), byteStringPop, aVar);
                }
            }
            kr0 kr0Var = new kr0(byteStringPop, byteString, aVar);
            while (!this.f8196a.isEmpty()) {
                if (this.f8196a.peek().size() >= kr0.c(a(kr0Var.size()) + 1)) {
                    break;
                } else {
                    kr0Var = new kr0(this.f8196a.pop(), kr0Var, aVar);
                }
            }
            this.f8196a.push(kr0Var);
        }

        public final ByteString a(ByteString byteString, ByteString byteString2) {
            a(byteString);
            a(byteString2);
            ByteString byteStringPop = this.f8196a.pop();
            while (!this.f8196a.isEmpty()) {
                byteStringPop = new kr0(this.f8196a.pop(), byteStringPop, null);
            }
            return byteStringPop;
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        public final void a(ByteString byteString) {
            if (byteString.isBalanced()) {
                b(byteString);
                return;
            }
            if (byteString instanceof kr0) {
                kr0 kr0Var = (kr0) byteString;
                a(kr0Var.b);
                a(kr0Var.c);
            } else {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString.getClass());
            }
        }

        public final int a(int i) {
            int iBinarySearch = Arrays.binarySearch(kr0.f, i);
            return iBinarySearch < 0 ? (-(iBinarySearch + 1)) - 1 : iBinarySearch;
        }
    }

    /* JADX INFO: compiled from: RopeByteString.java */
    public static final class c implements Iterator<ByteString.i> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayDeque<kr0> f8197a;
        public ByteString.i b;

        public /* synthetic */ c(ByteString byteString, a aVar) {
            this(byteString);
        }

        public final ByteString.i a(ByteString byteString) {
            while (byteString instanceof kr0) {
                kr0 kr0Var = (kr0) byteString;
                this.f8197a.push(kr0Var);
                byteString = kr0Var.b;
            }
            return (ByteString.i) byteString;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b != null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public c(ByteString byteString) {
            if (!(byteString instanceof kr0)) {
                this.f8197a = null;
                this.b = (ByteString.i) byteString;
                return;
            }
            kr0 kr0Var = (kr0) byteString;
            ArrayDeque<kr0> arrayDeque = new ArrayDeque<>(kr0Var.getTreeDepth());
            this.f8197a = arrayDeque;
            arrayDeque.push(kr0Var);
            this.b = a(kr0Var.b);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ByteString.i next() {
            ByteString.i iVar = this.b;
            if (iVar == null) {
                throw new NoSuchElementException();
            }
            this.b = a();
            return iVar;
        }

        public final ByteString.i a() {
            ByteString.i iVarA;
            do {
                ArrayDeque<kr0> arrayDeque = this.f8197a;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    return null;
                }
                iVarA = a(this.f8197a.pop().c);
            } while (iVarA.isEmpty());
            return iVarA;
        }
    }

    public /* synthetic */ kr0(ByteString byteString, ByteString byteString2, a aVar) {
        this(byteString, byteString2);
    }

    public static int c(int i) {
        int[] iArr = f;
        if (i >= iArr.length) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        c cVar = new c(this, null);
        while (cVar.hasNext()) {
            arrayList.add(cVar.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte byteAt(int i) {
        ByteString.checkIndex(i, this.f8193a);
        return internalByteAt(i);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        this.b.copyTo(byteBuffer);
        this.c.copyTo(byteBuffer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.d;
        if (i4 <= i5) {
            this.b.copyToInternal(bArr, i, i2, i3);
        } else {
            if (i >= i5) {
                this.c.copyToInternal(bArr, i - i5, i2, i3);
                return;
            }
            int i6 = i5 - i;
            this.b.copyToInternal(bArr, i, i2, i6);
            this.c.copyToInternal(bArr, 0, i2 + i6, i3 - i6);
        }
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
        if (this.f8193a != byteString.size()) {
            return false;
        }
        if (this.f8193a == 0) {
            return true;
        }
        int iPeekCachedHashCode = peekCachedHashCode();
        int iPeekCachedHashCode2 = byteString.peekCachedHashCode();
        if (iPeekCachedHashCode == 0 || iPeekCachedHashCode2 == 0 || iPeekCachedHashCode == iPeekCachedHashCode2) {
            return b(byteString);
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int getTreeDepth() {
        return this.f8194e;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte internalByteAt(int i) {
        int i2 = this.d;
        return i < i2 ? this.b.internalByteAt(i) : this.c.internalByteAt(i - i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isBalanced() {
        return this.f8193a >= c(this.f8194e);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isValidUtf8() {
        int iPartialIsValidUtf8 = this.b.partialIsValidUtf8(0, 0, this.d);
        ByteString byteString = this.c;
        return byteString.partialIsValidUtf8(iPartialIsValidUtf8, 0, byteString.size()) == 0;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public rp0 newCodedInput() {
        return rp0.a(new d());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public InputStream newInput() {
        return new d();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialHash(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.d;
        if (i4 <= i5) {
            return this.b.partialHash(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.c.partialHash(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.c.partialHash(this.b.partialHash(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialIsValidUtf8(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.d;
        if (i4 <= i5) {
            return this.b.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.c.partialIsValidUtf8(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.c.partialIsValidUtf8(this.b.partialIsValidUtf8(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int size() {
        return this.f8193a;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteString substring(int i, int i2) {
        int iCheckRange = ByteString.checkRange(i, i2, this.f8193a);
        if (iCheckRange == 0) {
            return ByteString.EMPTY;
        }
        if (iCheckRange == this.f8193a) {
            return this;
        }
        int i3 = this.d;
        return i2 <= i3 ? this.b.substring(i, i2) : i >= i3 ? this.c.substring(i - i3, i2 - i3) : new kr0(this.b.substring(i), this.c.substring(0, i2 - this.d));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public String toStringInternal(Charset charset) {
        return new String(toByteArray(), charset);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(OutputStream outputStream) throws IOException {
        this.b.writeTo(outputStream);
        this.c.writeTo(outputStream);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException {
        int i3 = i + i2;
        int i4 = this.d;
        if (i3 <= i4) {
            this.b.writeToInternal(outputStream, i, i2);
        } else {
            if (i >= i4) {
                this.c.writeToInternal(outputStream, i - i4, i2);
                return;
            }
            int i5 = i4 - i;
            this.b.writeToInternal(outputStream, i, i5);
            this.c.writeToInternal(outputStream, 0, i2 - i5);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToReverse(qp0 qp0Var) throws IOException {
        this.c.writeToReverse(qp0Var);
        this.b.writeToReverse(qp0Var);
    }

    public kr0(ByteString byteString, ByteString byteString2) {
        this.b = byteString;
        this.c = byteString2;
        int size = byteString.size();
        this.d = size;
        this.f8193a = size + byteString2.size();
        this.f8194e = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    public static ByteString a(ByteString byteString, ByteString byteString2) {
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString.size() + byteString2.size();
        if (size < 128) {
            return b(byteString, byteString2);
        }
        if (byteString instanceof kr0) {
            kr0 kr0Var = (kr0) byteString;
            if (kr0Var.c.size() + byteString2.size() < 128) {
                return new kr0(kr0Var.b, b(kr0Var.c, byteString2));
            }
            if (kr0Var.b.getTreeDepth() > kr0Var.c.getTreeDepth() && kr0Var.getTreeDepth() > byteString2.getTreeDepth()) {
                return new kr0(kr0Var.b, new kr0(kr0Var.c, byteString2));
            }
        }
        return size >= c(Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1) ? new kr0(byteString, byteString2) : new b(null).a(byteString, byteString2);
    }

    public static ByteString b(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return ByteString.wrap(bArr);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString, java.lang.Iterable
    /* JADX INFO: renamed from: iterator */
    public Iterator<Byte> iterator2() {
        return new a();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(qp0 qp0Var) throws IOException {
        this.b.writeTo(qp0Var);
        this.c.writeTo(qp0Var);
    }

    /* JADX INFO: compiled from: RopeByteString.java */
    public class d extends InputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f8198a;
        public ByteString.i b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8199e;
        public int f;

        public d() {
            b();
        }

        public final int a(byte[] bArr, int i, int i2) {
            int i3 = i2;
            while (i3 > 0) {
                a();
                if (this.b == null) {
                    break;
                }
                int iMin = Math.min(this.c - this.d, i3);
                if (bArr != null) {
                    this.b.copyTo(bArr, this.d, i, iMin);
                    i += iMin;
                }
                this.d += iMin;
                i3 -= iMin;
            }
            return i2 - i3;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return kr0.this.size() - (this.f8199e + this.d);
        }

        public final void b() {
            c cVar = new c(kr0.this, null);
            this.f8198a = cVar;
            ByteString.i next = cVar.next();
            this.b = next;
            this.c = next.size();
            this.d = 0;
            this.f8199e = 0;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.f = this.f8199e + this.d;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw null;
            }
            if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
                throw new IndexOutOfBoundsException();
            }
            int iA = a(bArr, i, i2);
            if (iA == 0) {
                return -1;
            }
            return iA;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            b();
            a(null, 0, this.f);
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (j > 2147483647L) {
                j = 2147483647L;
            }
            return a(null, 0, (int) j);
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            a();
            ByteString.i iVar = this.b;
            if (iVar == null) {
                return -1;
            }
            int i = this.d;
            this.d = i + 1;
            return iVar.byteAt(i) & 255;
        }

        public final void a() {
            if (this.b != null) {
                int i = this.d;
                int i2 = this.c;
                if (i == i2) {
                    this.f8199e += i2;
                    this.d = 0;
                    if (this.f8198a.hasNext()) {
                        ByteString.i next = this.f8198a.next();
                        this.b = next;
                        this.c = next.size();
                    } else {
                        this.b = null;
                        this.c = 0;
                    }
                }
            }
        }
    }

    public final boolean b(ByteString byteString) {
        boolean zA;
        a aVar = null;
        c cVar = new c(this, aVar);
        ByteString.i next = cVar.next();
        c cVar2 = new c(byteString, aVar);
        ByteString.i next2 = cVar2.next();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int size = next.size() - i;
            int size2 = next2.size() - i2;
            int iMin = Math.min(size, size2);
            if (i == 0) {
                zA = next.a(next2, i2, iMin);
            } else {
                zA = next2.a(next, i, iMin);
            }
            if (!zA) {
                return false;
            }
            i3 += iMin;
            int i4 = this.f8193a;
            if (i3 >= i4) {
                if (i3 == i4) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (iMin == size) {
                i = 0;
                next = cVar.next();
            } else {
                i += iMin;
                next = next;
            }
            if (iMin == size2) {
                next2 = cVar2.next();
                i2 = 0;
            } else {
                i2 += iMin;
            }
        }
    }
}
