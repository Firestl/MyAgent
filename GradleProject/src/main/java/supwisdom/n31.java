package supwisdom;

import com.sangfor.dx.io.IndexType;
import com.sangfor.dx.io.instructions.InstructionCodec;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class n31 extends j31 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8472e;
    public final int[] f;

    public n31(InstructionCodec instructionCodec, int i, int i2, IndexType indexType, int i3, int[] iArr) {
        super(instructionCodec, i, i2, indexType, 0, 0L);
        if (i3 == ((short) i3)) {
            this.f8472e = i3;
            this.f = iArr;
        } else {
            throw new IllegalArgumentException("protoIndex doesn't fit in a short: " + i3);
        }
    }

    @Override // supwisdom.j31
    public int e() {
        int[] iArr = this.f;
        if (iArr.length > 0) {
            return iArr[0];
        }
        return 0;
    }

    @Override // supwisdom.j31
    public int g() {
        int[] iArr = this.f;
        if (iArr.length > 1) {
            return iArr[1];
        }
        return 0;
    }

    @Override // supwisdom.j31
    public int h() {
        int[] iArr = this.f;
        if (iArr.length > 2) {
            return iArr[2];
        }
        return 0;
    }

    @Override // supwisdom.j31
    public short r() {
        return (short) this.f8472e;
    }

    @Override // supwisdom.j31
    public int s() {
        return this.f.length;
    }

    public int t() {
        int[] iArr = this.f;
        if (iArr.length > 3) {
            return iArr[3];
        }
        return 0;
    }

    public int u() {
        int[] iArr = this.f;
        if (iArr.length > 4) {
            return iArr[4];
        }
        return 0;
    }
}
