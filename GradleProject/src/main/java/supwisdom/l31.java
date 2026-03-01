package supwisdom;

import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class l31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8241e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;

    public l31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j, int i4, int i5, int i6, int i7, int i8) {
        super(instructionCodec, i, i2, indexType, i3, j);
        this.f8241e = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
    }

    @Override // supwisdom.j31
    public int a() {
        return this.f8241e;
    }

    @Override // supwisdom.j31
    public int c() {
        return this.f;
    }

    @Override // supwisdom.j31
    public int e() {
        return this.g;
    }

    @Override // supwisdom.j31
    public int g() {
        return this.h;
    }

    @Override // supwisdom.j31
    public int h() {
        return this.i;
    }

    @Override // supwisdom.j31
    public int s() {
        return 5;
    }
}
