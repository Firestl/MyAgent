package supwisdom;

import com.sangfor.dex.DexException;
import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class j31 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8017a;
    public final int b;
    public final int c;
    public final long d;

    public j31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j) {
        if (instructionCodec == null) {
            throw new NullPointerException("format == null");
        }
        if (!f31.a(i)) {
            throw new IllegalArgumentException("invalid opcode");
        }
        this.f8017a = i;
        this.b = i2;
        this.c = i3;
        this.d = j;
    }

    public int a() {
        return 0;
    }

    public final int a(int i) {
        return this.c - i;
    }

    public final short b() {
        int iA = a();
        if (((-65536) & iA) == 0) {
            return (short) iA;
        }
        throw new DexException("Register A out of range: " + m61.a(iA));
    }

    public int c() {
        return 0;
    }

    public final short c(int i) {
        int iA = a(i);
        short s = (short) iA;
        if (iA == s) {
            return s;
        }
        throw new DexException("Target out of range: " + m61.b(iA));
    }

    public final short d() {
        int iC = c();
        if (((-65536) & iC) == 0) {
            return (short) iC;
        }
        throw new DexException("Register B out of range: " + m61.a(iC));
    }

    public int e() {
        return 0;
    }

    public final short f() {
        int iE = e();
        if (((-65536) & iE) == 0) {
            return (short) iE;
        }
        throw new DexException("Register C out of range: " + m61.a(iE));
    }

    public int g() {
        return 0;
    }

    public int h() {
        return 0;
    }

    public final int i() {
        return this.b;
    }

    public final short j() {
        return (short) this.b;
    }

    public final long k() {
        return this.d;
    }

    public final int l() {
        long j = this.d;
        int i = (int) j;
        if (j == ((byte) i)) {
            return i & 255;
        }
        throw new DexException("Literal out of range: " + m61.a(this.d));
    }

    public final int m() {
        long j = this.d;
        int i = (int) j;
        if (j == i) {
            return i;
        }
        throw new DexException("Literal out of range: " + m61.a(this.d));
    }

    public final int n() {
        long j = this.d;
        if (j >= -8 && j <= 7) {
            return ((int) j) & 15;
        }
        throw new DexException("Literal out of range: " + m61.a(this.d));
    }

    public final short o() {
        long j = this.d;
        short s = (short) j;
        if (j == s) {
            return s;
        }
        throw new DexException("Literal out of range: " + m61.a(this.d));
    }

    public final int p() {
        return this.f8017a;
    }

    public final short q() {
        return (short) this.f8017a;
    }

    public short r() {
        throw new IllegalStateException(getClass().toString());
    }

    public abstract int s();

    public final int b(int i) {
        int iA = a(i);
        if (iA == ((byte) iA)) {
            return iA & 255;
        }
        throw new DexException("Target out of range: " + m61.b(iA));
    }
}
