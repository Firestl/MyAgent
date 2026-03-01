package supwisdom;

import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class s31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f9119e;
    public final int[] f;

    public s31(InstructionCodec instructionCodec, int i, int[] iArr, int[] iArr2) {
        super(instructionCodec, i, 0, null, 0, 0L);
        if (iArr.length != iArr2.length) {
            throw new IllegalArgumentException("keys/targets length mismatch");
        }
        this.f9119e = iArr;
        this.f = iArr2;
    }

    @Override // supwisdom.j31
    public int s() {
        return 0;
    }

    public int[] t() {
        return this.f9119e;
    }

    public int[] u() {
        return this.f;
    }
}
