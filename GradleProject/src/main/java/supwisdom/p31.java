package supwisdom;

import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class p31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8765e;

    public p31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, long j, int i4) {
        super(instructionCodec, i, i2, indexType, i3, j);
        this.f8765e = i4;
    }

    @Override // supwisdom.j31
    public int a() {
        return this.f8765e;
    }

    @Override // supwisdom.j31
    public int s() {
        return 1;
    }
}
