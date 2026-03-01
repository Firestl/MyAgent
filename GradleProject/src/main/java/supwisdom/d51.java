package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class d51 extends m51 {
    public static final d51 b = new d51(Double.doubleToLongBits(0.0d));

    static {
        new d51(Double.doubleToLongBits(1.0d));
    }

    public d51(long j) {
        super(j);
    }

    public static d51 a(long j) {
        return new d51(j);
    }

    @Override // supwisdom.u41
    public String c() {
        return "double";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.j;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Double.toString(Double.longBitsToDouble(f()));
    }

    public String toString() {
        long jF = f();
        return "double{0x" + m61.a(jF) + " / " + Double.longBitsToDouble(jF) + Operators.BLOCK_END;
    }
}
