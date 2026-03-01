package supwisdom;

import com.taobao.weex.ui.component.list.template.TemplateDom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class tz0 extends oz0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u41[] f9341e;
    public final int[] f;
    public int g;

    public tz0(mz0 mz0Var, r41 r41Var, m41 m41Var, u41[] u41VarArr, int[] iArr, int i) {
        super(mz0Var, r41Var, m41Var);
        this.f9341e = u41VarArr;
        this.f = iArr;
        this.g = i;
    }

    @Override // supwisdom.kz0
    public String a() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.f9341e.length; i++) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(this.f9341e[i].toHuman());
        }
        return sb.toString();
    }

    @Override // supwisdom.kz0
    public String c() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.f9341e.length; i++) {
            if (!e(i)) {
                return "";
            }
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(c(i).c());
            sb.append(TemplateDom.SEPARATOR);
            int iD = d(i);
            if (iD < 65536) {
                sb.append(m61.d(iD));
            } else {
                sb.append(m61.g(iD));
            }
        }
        return sb.toString();
    }

    @Override // supwisdom.kz0
    public String d() {
        return a();
    }

    public boolean e(int i) {
        return this.f[i] != -1;
    }

    public void f(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index < 0");
        }
        if (o()) {
            throw new IllegalStateException("class index already set");
        }
        this.g = i;
    }

    public int n() {
        return this.f9341e.length;
    }

    public boolean o() {
        return this.g != -1;
    }

    public int d(int i) {
        if (e(i)) {
            return this.f[i];
        }
        throw new IllegalStateException("index not yet set for constant " + i + " value = " + this.f9341e[i]);
    }

    public void a(int i, int i2) {
        if (i2 >= 0) {
            if (!e(i)) {
                this.f[i] = i2;
                return;
            }
            throw new IllegalStateException("index already set");
        }
        throw new IllegalArgumentException("index < 0");
    }

    @Override // supwisdom.kz0
    public kz0 a(mz0 mz0Var) {
        return new tz0(mz0Var, i(), j(), this.f9341e, this.f, this.g);
    }

    @Override // supwisdom.kz0
    public kz0 a(m41 m41Var) {
        return new tz0(h(), i(), m41Var, this.f9341e, this.f, this.g);
    }

    public u41 c(int i) {
        return this.f9341e[i];
    }
}
