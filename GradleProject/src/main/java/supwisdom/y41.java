package supwisdom;

import io.dcloud.common.constant.AbsoluteConst;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class y41 extends l51 {
    public static final y41 b = new y41(false);
    public static final y41 c = new y41(true);

    public y41(boolean z) {
        super(z ? 1 : 0);
    }

    public static y41 a(boolean z) {
        return z ? c : b;
    }

    @Override // supwisdom.u41
    public String c() {
        return "boolean";
    }

    public boolean g() {
        return e() != 0;
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.g;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return g() ? "true" : AbsoluteConst.FALSE;
    }

    public String toString() {
        return g() ? "boolean{true}" : "boolean{false}";
    }
}
