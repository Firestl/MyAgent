package com.loc;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

/* JADX INFO: compiled from: FlatBufferBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public class er {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ByteBuffer f3792a;
    public int b;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f3793e;
    public int f;
    public boolean g;
    public boolean h;
    public int i;
    public int[] j;
    public int k;
    public int l;
    public boolean m;
    public CharsetEncoder n;
    public ByteBuffer o;
    public static final /* synthetic */ boolean p = !er.class.desiredAssertionStatus();
    public static final Charset c = Charset.forName("UTF-8");

    public er() {
        this.d = 1;
        this.f3793e = null;
        this.f = 0;
        this.g = false;
        this.h = false;
        this.j = new int[16];
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = c.newEncoder();
        this.b = 1024;
        this.f3792a = d(1024);
    }

    public er(ByteBuffer byteBuffer) {
        this.d = 1;
        this.f3793e = null;
        this.f = 0;
        this.g = false;
        this.h = false;
        this.j = new int[16];
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.n = c.newEncoder();
        a(byteBuffer);
    }

    private void a(short s) {
        c(2, 0);
        ByteBuffer byteBuffer = this.f3792a;
        int i = this.b - 2;
        this.b = i;
        byteBuffer.putShort(i, s);
    }

    private void c(int i, int i2) {
        if (i > this.d) {
            this.d = i;
        }
        int i3 = ((~((this.f3792a.capacity() - this.b) + i2)) + 1) & (i - 1);
        while (this.b < i3 + i + i2) {
            int iCapacity = this.f3792a.capacity();
            ByteBuffer byteBuffer = this.f3792a;
            int iCapacity2 = byteBuffer.capacity();
            if (((-1073741824) & iCapacity2) != 0) {
                throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
            }
            int i4 = iCapacity2 << 1;
            byteBuffer.position(0);
            ByteBuffer byteBufferD = d(i4);
            byteBufferD.position(i4 - iCapacity2);
            byteBufferD.put(byteBuffer);
            this.f3792a = byteBufferD;
            this.b += byteBufferD.capacity() - iCapacity;
        }
        e(i3);
    }

    private int d() {
        return this.f3792a.capacity() - this.b;
    }

    public static ByteBuffer d(int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferAllocate;
    }

    private void e() {
        if (this.g) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
    }

    private void e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer byteBuffer = this.f3792a;
            int i3 = this.b - 1;
            this.b = i3;
            byteBuffer.put(i3, (byte) 0);
        }
    }

    private void f(int i) {
        ByteBuffer byteBuffer = this.f3792a;
        int i2 = this.b - 4;
        this.b = i2;
        byteBuffer.putInt(i2, i);
    }

    private void g(int i) {
        c(4, 0);
        f(i);
    }

    private void h(int i) {
        this.f3793e[i] = d();
    }

    public final int a() {
        if (!this.g) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.g = false;
        f(this.l);
        return d();
    }

    public int a(CharSequence charSequence) {
        int length = (int) (charSequence.length() * this.n.maxBytesPerChar());
        ByteBuffer byteBuffer = this.o;
        if (byteBuffer == null || byteBuffer.capacity() < length) {
            this.o = ByteBuffer.allocate(Math.max(128, length));
        }
        this.o.clear();
        CoderResult coderResultEncode = this.n.encode(charSequence instanceof CharBuffer ? (CharBuffer) charSequence : CharBuffer.wrap(charSequence), this.o, true);
        if (coderResultEncode.isError()) {
            try {
                coderResultEncode.throwException();
            } catch (CharacterCodingException e2) {
                throw new Error(e2);
            }
        }
        this.o.flip();
        ByteBuffer byteBuffer2 = this.o;
        int iRemaining = byteBuffer2.remaining();
        a((byte) 0);
        a(1, iRemaining, 1);
        ByteBuffer byteBuffer3 = this.f3792a;
        int i = this.b - iRemaining;
        this.b = i;
        byteBuffer3.position(i);
        this.f3792a.put(byteBuffer2);
        return a();
    }

    public final er a(ByteBuffer byteBuffer) {
        this.f3792a = byteBuffer;
        byteBuffer.clear();
        this.f3792a.order(ByteOrder.LITTLE_ENDIAN);
        this.d = 1;
        this.b = this.f3792a.capacity();
        this.f = 0;
        this.g = false;
        this.h = false;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        return this;
    }

    public final void a(byte b) {
        c(1, 0);
        ByteBuffer byteBuffer = this.f3792a;
        int i = this.b - 1;
        this.b = i;
        byteBuffer.put(i, b);
    }

    public final void a(int i) {
        c(4, 0);
        if (!p && i > d()) {
            throw new AssertionError();
        }
        f((d() - i) + 4);
    }

    public final void a(int i, byte b) {
        if (this.m || b != 0) {
            a(b);
            h(i);
        }
    }

    public final void a(int i, int i2) {
        if (this.m || i2 != 0) {
            g(i2);
            h(i);
        }
    }

    public final void a(int i, int i2, int i3) {
        e();
        this.l = i2;
        int i4 = i * i2;
        c(4, i4);
        c(i3, i4);
        this.g = true;
    }

    public final void a(int i, long j) {
        if (this.m || j != 0) {
            c(8, 0);
            ByteBuffer byteBuffer = this.f3792a;
            int i2 = this.b - 8;
            this.b = i2;
            byteBuffer.putLong(i2, j);
            h(i);
        }
    }

    public final void a(int i, short s) {
        if (this.m || s != 0) {
            a(s);
            h(i);
        }
    }

    public final void a(boolean z) {
        if (this.m || z) {
            c(1, 0);
            ByteBuffer byteBuffer = this.f3792a;
            int i = this.b - 1;
            this.b = i;
            byteBuffer.put(i, z ? (byte) 1 : (byte) 0);
            h(0);
        }
    }

    public final int b() {
        int i;
        int i2;
        if (this.f3793e == null || !this.g) {
            throw new AssertionError("FlatBuffers: endObject called without startObject");
        }
        g(0);
        int iD = d();
        for (int i3 = this.f - 1; i3 >= 0; i3--) {
            int[] iArr = this.f3793e;
            a((short) (iArr[i3] != 0 ? iD - iArr[i3] : 0));
        }
        a((short) (iD - this.i));
        a((short) ((this.f + 2) * 2));
        int i4 = 0;
        loop1: while (true) {
            if (i4 >= this.k) {
                i = 0;
                break;
            }
            int iCapacity = this.f3792a.capacity() - this.j[i4];
            int i5 = this.b;
            short s = this.f3792a.getShort(iCapacity);
            if (s == this.f3792a.getShort(i5)) {
                while (i2 < s) {
                    i2 = this.f3792a.getShort(iCapacity + i2) == this.f3792a.getShort(i5 + i2) ? i2 + 2 : 2;
                }
                i = this.j[i4];
                break loop1;
            }
            i4++;
        }
        if (i != 0) {
            int iCapacity2 = this.f3792a.capacity() - iD;
            this.b = iCapacity2;
            this.f3792a.putInt(iCapacity2, i - iD);
        } else {
            int i6 = this.k;
            int[] iArr2 = this.j;
            if (i6 == iArr2.length) {
                this.j = Arrays.copyOf(iArr2, i6 * 2);
            }
            int[] iArr3 = this.j;
            int i7 = this.k;
            this.k = i7 + 1;
            iArr3[i7] = d();
            ByteBuffer byteBuffer = this.f3792a;
            byteBuffer.putInt(byteBuffer.capacity() - iD, d() - iD);
        }
        this.g = false;
        return iD;
    }

    public final void b(int i) {
        e();
        int[] iArr = this.f3793e;
        if (iArr == null || iArr.length < i) {
            this.f3793e = new int[i];
        }
        this.f = i;
        Arrays.fill(this.f3793e, 0, i, 0);
        this.g = true;
        this.i = d();
    }

    public final void b(int i, int i2) {
        if (this.m || i2 != 0) {
            a(i2);
            h(i);
        }
    }

    public final void c(int i) {
        c(this.d, 4);
        a(i);
        this.f3792a.position(this.b);
        this.h = true;
    }

    public final byte[] c() {
        int i = this.b;
        int iCapacity = this.f3792a.capacity() - this.b;
        if (!this.h) {
            throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
        }
        byte[] bArr = new byte[iCapacity];
        this.f3792a.position(i);
        this.f3792a.get(bArr);
        return bArr;
    }
}
