package supwisdom;

import com.huawei.hms.framework.common.ContainerUtils;

/* JADX INFO: compiled from: BasicHeaderElement.java */
/* JADX INFO: loaded from: classes2.dex */
public class oo1 implements yn1, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8690a;
    public final String b;
    public final co1[] c;

    public oo1(String str, String str2, co1[] co1VarArr) {
        yo1.a(str, "Name");
        this.f8690a = str;
        this.b = str2;
        if (co1VarArr != null) {
            this.c = co1VarArr;
        } else {
            this.c = new co1[0];
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof yn1)) {
            return false;
        }
        oo1 oo1Var = (oo1) obj;
        return this.f8690a.equals(oo1Var.f8690a) && ap1.a(this.b, oo1Var.b) && ap1.a((Object[]) this.c, (Object[]) oo1Var.c);
    }

    @Override // supwisdom.yn1
    public String getName() {
        return this.f8690a;
    }

    @Override // supwisdom.yn1
    public co1[] getParameters() {
        return (co1[]) this.c.clone();
    }

    @Override // supwisdom.yn1
    public String getValue() {
        return this.b;
    }

    public int hashCode() {
        int iA = ap1.a(ap1.a(17, this.f8690a), this.b);
        for (co1 co1Var : this.c) {
            iA = ap1.a(iA, co1Var);
        }
        return iA;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8690a);
        if (this.b != null) {
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(this.b);
        }
        for (co1 co1Var : this.c) {
            sb.append("; ");
            sb.append(co1Var);
        }
        return sb.toString();
    }
}
