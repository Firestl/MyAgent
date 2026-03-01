package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class e21 extends f21 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7408a = -1;

    public final void a(int i) {
        if (this.f7408a != -1) {
            throw new RuntimeException("index already set");
        }
        this.f7408a = i;
    }

    public final int d() {
        int i = this.f7408a;
        if (i >= 0) {
            return i;
        }
        throw new RuntimeException("index not yet set");
    }

    public final boolean e() {
        return this.f7408a >= 0;
    }

    public final String f() {
        return Operators.ARRAY_START + Integer.toHexString(this.f7408a) + Operators.ARRAY_END;
    }
}
