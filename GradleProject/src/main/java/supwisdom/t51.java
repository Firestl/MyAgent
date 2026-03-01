package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class t51 extends x51 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z51 f9257a;

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        return this.f9257a.compareTo(((t51) u41Var).d());
    }

    @Override // supwisdom.u41
    public String c() {
        return "proto";
    }

    public z51 d() {
        return this.f9257a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof t51) {
            return d().equals(((t51) obj).d());
        }
        return false;
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return b61.v;
    }

    public int hashCode() {
        return this.f9257a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f9257a.a();
    }

    public final String toString() {
        return c() + Operators.BLOCK_START_STR + toHuman() + Operators.BLOCK_END;
    }
}
