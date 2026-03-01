package supwisdom;

import java.util.ArrayList;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class uz0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vz0 f9457a;
    public ArrayList<kz0> b;

    public uz0(cz0 cz0Var, int i, int i2, int i3, int i4) {
        this.f9457a = new vz0(cz0Var, i, i3, i4);
        this.b = new ArrayList<>(i2);
    }

    public void a(kz0 kz0Var) {
        this.f9457a.a(kz0Var);
    }

    public vz0 b() {
        if (this.b == null) {
            throw new UnsupportedOperationException("already processed");
        }
        a();
        return this.f9457a;
    }

    public final void a() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.f9457a.a(this.b.get(i));
        }
        this.b = null;
    }

    public void a(int i, hz0 hz0Var) {
        this.f9457a.a(i, hz0Var);
    }
}
