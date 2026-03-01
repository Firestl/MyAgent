package supwisdom;

import com.taobao.weex.ui.component.list.template.TemplateDom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class iz0 extends oz0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u41 f8001e;
    public int f;
    public int g;

    public iz0(mz0 mz0Var, r41 r41Var, m41 m41Var, u41 u41Var) {
        super(mz0Var, r41Var, m41Var);
        if (u41Var == null) {
            throw new NullPointerException("constant == null");
        }
        this.f8001e = u41Var;
        this.f = -1;
        this.g = -1;
    }

    @Override // supwisdom.kz0
    public String a() {
        return this.f8001e.toHuman();
    }

    @Override // supwisdom.kz0
    public String c() {
        if (!p()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append(n().c());
        sb.append(TemplateDom.SEPARATOR);
        int i = this.f;
        if (i < 65536) {
            sb.append(m61.d(i));
        } else {
            sb.append(m61.g(i));
        }
        return sb.toString();
    }

    @Override // supwisdom.kz0
    public String d() {
        u41 u41Var = this.f8001e;
        return u41Var instanceof v51 ? ((v51) u41Var).h() : u41Var.toHuman();
    }

    public u41 n() {
        return this.f8001e;
    }

    public int o() {
        int i = this.f;
        if (i >= 0) {
            return i;
        }
        throw new IllegalStateException("index not yet set for " + this.f8001e);
    }

    public boolean p() {
        return this.f >= 0;
    }

    @Override // supwisdom.kz0
    public kz0 a(mz0 mz0Var) {
        iz0 iz0Var = new iz0(mz0Var, i(), j(), this.f8001e);
        int i = this.f;
        if (i >= 0) {
            iz0Var.d(i);
        }
        int i2 = this.g;
        if (i2 >= 0) {
            iz0Var.c(i2);
        }
        return iz0Var;
    }

    public void d(int i) {
        if (i >= 0) {
            if (this.f < 0) {
                this.f = i;
                return;
            }
            throw new IllegalStateException("index already set");
        }
        throw new IllegalArgumentException("index < 0");
    }

    @Override // supwisdom.kz0
    public kz0 a(m41 m41Var) {
        iz0 iz0Var = new iz0(h(), i(), m41Var, this.f8001e);
        int i = this.f;
        if (i >= 0) {
            iz0Var.d(i);
        }
        int i2 = this.g;
        if (i2 >= 0) {
            iz0Var.c(i2);
        }
        return iz0Var;
    }

    public void c(int i) {
        if (i >= 0) {
            if (this.g < 0) {
                this.g = i;
                return;
            }
            throw new IllegalStateException("class index already set");
        }
        throw new IllegalArgumentException("index < 0");
    }
}
