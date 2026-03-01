package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class q61 extends l61 {
    public final o61 c;

    public q61(int i) {
        super(i);
        this.c = new o61(i);
    }

    public final void a(int i, int i2) {
        int size = this.c.size();
        for (int i3 = 0; i3 <= i - size; i3++) {
            this.c.a(-1);
        }
        this.c.a(i, i2);
    }

    public final int d(int i) {
        if (i >= this.c.size()) {
            return -1;
        }
        return this.c.d(i);
    }

    public final void e(int i) {
        this.c.a(i, -1);
    }

    public final int h() {
        int size = this.c.size() - 1;
        while (size >= 0 && this.c.d(size) < 0) {
            size--;
        }
        int i = size + 1;
        this.c.f(i);
        return i;
    }

    public void a(int i, p61 p61Var) {
        p61 p61Var2 = (p61) b(i);
        a(i, (Object) p61Var);
        if (p61Var2 != null) {
            e(p61Var2.a());
        }
        if (p61Var != null) {
            a(p61Var.a(), i);
        }
    }
}
