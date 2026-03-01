package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class o51 extends m51 {
    public static final o51 b = a(0);

    static {
        a(1L);
    }

    public o51(long j) {
        super(j);
    }

    public static o51 a(long j) {
        return new o51(j);
    }

    @Override // supwisdom.u41
    public String c() {
        return "long";
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.m;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return Long.toString(f());
    }

    public String toString() {
        long jF = f();
        return "long{0x" + m61.a(jF) + " / " + jF + Operators.BLOCK_END;
    }
}
