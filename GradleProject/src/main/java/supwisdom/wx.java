package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class wx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9679a;
    public final int b;

    public wx(int i, int i2) {
        this.f9679a = i;
        this.b = i2;
    }

    public final int a() {
        return this.b;
    }

    public final int b() {
        return this.f9679a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof wx)) {
            return false;
        }
        wx wxVar = (wx) obj;
        return this.f9679a == wxVar.f9679a && this.b == wxVar.b;
    }

    public final int hashCode() {
        return this.f9679a ^ this.b;
    }

    public final String toString() {
        return this.f9679a + "(" + this.b + Operators.BRACKET_END;
    }
}
