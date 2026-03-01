package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TableEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public class yv0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9921a;
    public List<vv0> b = new ArrayList();

    public yv0(String str) {
        this.f9921a = str;
    }

    public yv0 a(vv0 vv0Var) {
        this.b.add(vv0Var);
        return this;
    }

    public int b() {
        return this.b.size();
    }

    public String a() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(this.f9921a);
        sb.append(Operators.BRACKET_START);
        for (vv0 vv0Var : this.b) {
            if (vv0Var.c != null) {
                sb.append("PRIMARY KEY (");
                for (String str : vv0Var.c) {
                    sb.append(str);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            } else {
                sb.append(vv0Var.f9552a);
                sb.append(Operators.SPACE_STR);
                sb.append(vv0Var.b);
                if (vv0Var.f9553e) {
                    sb.append(" NOT NULL");
                }
                if (vv0Var.d) {
                    sb.append(" PRIMARY KEY");
                }
                if (vv0Var.f) {
                    sb.append(" AUTOINCREMENT");
                }
                sb.append(",");
            }
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(Operators.BRACKET_END);
        return sb.toString();
    }

    public int a(String str) {
        int iB = b();
        for (int i = 0; i < iB; i++) {
            if (this.b.get(i).f9552a.equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
