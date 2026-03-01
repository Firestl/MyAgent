package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class e61 extends f61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o61 f7429a;
    public int b;

    public e61(int i) {
        this.f7429a = new o61(i);
    }

    public void a(int i, int i2, int i3) {
        if (i >= this.f7429a.size()) {
            for (int size = i - this.f7429a.size(); size >= 0; size--) {
                this.f7429a.a(-1);
            }
        }
        this.f7429a.a(i, i2);
        int i4 = i2 + i3;
        if (this.b < i4) {
            this.b = i4;
        }
    }

    @Override // supwisdom.f61
    public int a() {
        return this.b;
    }

    @Override // supwisdom.f61
    public l41 a(l41 l41Var) {
        int iD;
        if (l41Var == null) {
            return null;
        }
        try {
            iD = this.f7429a.d(l41Var.f());
        } catch (IndexOutOfBoundsException unused) {
            iD = -1;
        }
        if (iD >= 0) {
            return l41Var.b(iD);
        }
        throw new RuntimeException("no mapping specified for register");
    }
}
