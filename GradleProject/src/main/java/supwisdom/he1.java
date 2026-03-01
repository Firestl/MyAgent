package supwisdom;

import io.dcloud.common.util.JSUtil;

/* JADX INFO: compiled from: Challenge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class he1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7833a;
    public final String b;

    public he1(String str, String str2) {
        this.f7833a = str;
        this.b = str2;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.f7833a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof he1) {
            he1 he1Var = (he1) obj;
            if (gf1.a(this.f7833a, he1Var.f7833a) && gf1.a(this.b, he1Var.b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.b;
        int iHashCode = (899 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.f7833a;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return this.f7833a + " realm=\"" + this.b + JSUtil.QUOTE;
    }
}
