package supwisdom;

import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class o31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8613e;
    public final int f;
    public final int g;

    public o31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, int i4, int i5) {
        super(instructionCodec, i, i2, indexType, 0, 0L);
        if (i5 == ((short) i5)) {
            this.f8613e = i3;
            this.f = i4;
            this.g = i5;
        } else {
            throw new IllegalArgumentException("protoIndex doesn't fit in a short: " + i5);
        }
    }

    @Override // supwisdom.j31
    public int e() {
        return this.f8613e;
    }

    @Override // supwisdom.j31
    public short r() {
        return (short) this.g;
    }

    @Override // supwisdom.j31
    public int s() {
        return this.f;
    }
}
