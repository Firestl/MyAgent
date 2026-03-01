package supwisdom;

import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.xiaomi.mipush.sdk.Constants;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class r41 {
    public static final r41 d = new r41(null, -1, -1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v51 f9009a;
    public final int b;
    public final int c;

    public r41(v51 v51Var, int i, int i2) {
        if (i < -1) {
            throw new IllegalArgumentException("address < -1");
        }
        if (i2 < -1) {
            throw new IllegalArgumentException("line < -1");
        }
        this.f9009a = v51Var;
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public boolean b(r41 r41Var) {
        v51 v51Var;
        v51 v51Var2;
        return this.c == r41Var.c && ((v51Var = this.f9009a) == (v51Var2 = r41Var.f9009a) || (v51Var != null && v51Var.equals(v51Var2)));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r41)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        r41 r41Var = (r41) obj;
        return this.b == r41Var.b && b(r41Var);
    }

    public int hashCode() {
        return this.f9009a.hashCode() + this.b + this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        v51 v51Var = this.f9009a;
        if (v51Var != null) {
            sb.append(v51Var.toHuman());
            sb.append(Constants.COLON_SEPARATOR);
        }
        int i = this.c;
        if (i >= 0) {
            sb.append(i);
        }
        sb.append(TemplateDom.SEPARATOR);
        int i2 = this.b;
        if (i2 < 0) {
            sb.append("????");
        } else {
            sb.append(m61.d(i2));
        }
        return sb.toString();
    }

    public boolean a(r41 r41Var) {
        return this.c == r41Var.c;
    }
}
