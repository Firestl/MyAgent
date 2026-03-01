package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class g51 extends l51 {
    public static final g51 b = a(Float.floatToIntBits(0.0f));

    static {
        a(Float.floatToIntBits(1.0f));
        a(Float.floatToIntBits(2.0f));
    }

    public g51(int i) {
        super(i);
    }

    public static g51 a(int i) {
        return new g51(i);
    }

    @Override // supwisdom.u41
    public String c() {
        return "float";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.k;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Float.toString(Float.intBitsToFloat(e()));
    }

    public String toString() {
        int iE = e();
        return "float{0x" + m61.g(iE) + " / " + Float.intBitsToFloat(iE) + Operators.BLOCK_END;
    }
}
