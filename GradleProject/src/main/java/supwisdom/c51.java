package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class c51 extends l51 {
    public static final c51 b = a((char) 0);

    public c51(char c) {
        super(c);
    }

    public static c51 a(char c) {
        return new c51(c);
    }

    @Override // supwisdom.u41
    public String c() {
        return "char";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.i;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Integer.toString(e());
    }

    public String toString() {
        int iE = e();
        return "char{0x" + m61.d(iE) + " / " + iE + Operators.BLOCK_END;
    }
}
