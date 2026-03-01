package supwisdom;

import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class t31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9244e;
    public final int f;
    public final int g;

    public t31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j, int i4, int i5, int i6) {
        super(instructionCodec, i, i2, indexType, i3, j);
        this.f9244e = i4;
        this.f = i5;
        this.g = i6;
    }

    @Override // supwisdom.j31
    public int a() {
        return this.f9244e;
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
    public int s() {
        return 3;
    }
}
