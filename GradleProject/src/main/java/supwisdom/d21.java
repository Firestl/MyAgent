package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class d21 extends e21 {
    public final w51 b;

    public d21(w51 w51Var) {
        if (w51Var == null) {
            throw new NullPointerException("type == null");
        }
        this.b = w51Var;
    }

    @Override // supwisdom.f21
    public void a(t11 t11Var) {
        t11Var.p().b(this.b);
    }

    public final w51 g() {
        return this.b;
    }
}
