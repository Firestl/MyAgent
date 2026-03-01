package supwisdom;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DependencyNode.java */
/* JADX INFO: loaded from: classes.dex */
public class s6 implements q6 {
    public c7 d;
    public int f;
    public int g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public q6 f9126a = null;
    public boolean b = false;
    public boolean c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f9127e = a.UNKNOWN;
    public int h = 1;
    public t6 i = null;
    public boolean j = false;
    public List<q6> k = new ArrayList();
    public List<s6> l = new ArrayList();

    /* JADX INFO: compiled from: DependencyNode.java */
    public enum a {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public s6(c7 c7Var) {
        this.d = c7Var;
    }

    public void a(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (q6 q6Var : this.k) {
            q6Var.a(q6Var);
        }
    }

    public void b(q6 q6Var) {
        this.k.add(q6Var);
        if (this.j) {
            q6Var.a(q6Var);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.i());
        sb.append(Constants.COLON_SEPARATOR);
        sb.append(this.f9127e);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(Operators.G);
        return sb.toString();
    }

    @Override // supwisdom.q6
    public void a(q6 q6Var) {
        Iterator<s6> it = this.l.iterator();
        while (it.hasNext()) {
            if (!it.next().j) {
                return;
            }
        }
        this.c = true;
        q6 q6Var2 = this.f9126a;
        if (q6Var2 != null) {
            q6Var2.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        s6 s6Var = null;
        int i = 0;
        for (s6 s6Var2 : this.l) {
            if (!(s6Var2 instanceof t6)) {
                i++;
                s6Var = s6Var2;
            }
        }
        if (s6Var != null && i == 1 && s6Var.j) {
            t6 t6Var = this.i;
            if (t6Var != null) {
                if (!t6Var.j) {
                    return;
                } else {
                    this.f = this.h * t6Var.g;
                }
            }
            a(s6Var.g + this.f);
        }
        q6 q6Var3 = this.f9126a;
        if (q6Var3 != null) {
            q6Var3.a(this);
        }
    }

    public void a() {
        this.l.clear();
        this.k.clear();
        this.j = false;
        this.g = 0;
        this.c = false;
        this.b = false;
    }
}
