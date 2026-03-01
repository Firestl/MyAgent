package supwisdom;

import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class k31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f8119e;
    public final int f;
    public final int g;

    public k31(InstructionCodec instructionCodec, int i, Object obj, int i2, int i3) {
        super(instructionCodec, i, 0, null, 0, 0L);
        this.f8119e = obj;
        this.f = i2;
        this.g = i3;
    }

    @Override // supwisdom.j31
    public int s() {
        return 0;
    }

    public Object t() {
        return this.f8119e;
    }

    public short u() {
        return (short) this.g;
    }

    public int v() {
        return this.f;
    }

    public k31(InstructionCodec instructionCodec, int i, byte[] bArr) {
        this(instructionCodec, i, bArr, bArr.length, 1);
    }

    public k31(InstructionCodec instructionCodec, int i, short[] sArr) {
        this(instructionCodec, i, sArr, sArr.length, 2);
    }

    public k31(InstructionCodec instructionCodec, int i, int[] iArr) {
        this(instructionCodec, i, iArr, iArr.length, 4);
    }

    public k31(InstructionCodec instructionCodec, int i, long[] jArr) {
        this(instructionCodec, i, jArr, jArr.length, 8);
    }
}
