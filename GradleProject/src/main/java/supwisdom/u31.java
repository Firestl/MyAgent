package supwisdom;

import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class u31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9363e;
    public final int f;

    public u31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j, int i4, int i5) {
        super(instructionCodec, i, i2, indexType, i3, j);
        this.f9363e = i4;
        this.f = i5;
    }

    @Override // supwisdom.j31
    public int a() {
        return this.f9363e;
    }

    @Override // supwisdom.j31
    public int c() {
        return this.f;
    }

    @Override // supwisdom.j31
    public int s() {
        return 2;
    }
}
