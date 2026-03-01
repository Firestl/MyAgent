package supwisdom;

import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class q31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8871e;
    public final int[] f;

    public q31(InstructionCodec instructionCodec, int i, int i2, int[] iArr) {
        super(instructionCodec, i, 0, null, 0, 0L);
        this.f8871e = i2;
        this.f = iArr;
    }

    @Override // supwisdom.j31
    public int s() {
        return 0;
    }

    public int t() {
        return this.f8871e;
    }

    public int[] u() {
        return this.f;
    }
}
