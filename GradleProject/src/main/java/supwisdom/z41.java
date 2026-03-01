package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class z41 extends l51 {
    public static final z41 b = a((byte) 0);

    public z41(byte b2) {
        super(b2);
    }

    public static z41 a(byte b2) {
        return new z41(b2);
    }

    @Override // supwisdom.u41
    public String c() {
        return "byte";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.h;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Integer.toString(e());
    }

    public String toString() {
        int iE = e();
        return "byte{0x" + m61.c(iE) + " / " + iE + Operators.BLOCK_END;
    }
}
