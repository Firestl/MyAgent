package supwisdom;

import supwisdom.e41;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class f41 extends l61 {
    public f41(int i) {
        super(i);
    }

    public void a(e41.b bVar) {
        int size = size();
        for (int i = 0; i < size; i++) {
            d(i).a(bVar);
        }
    }

    public e41 d(int i) {
        return (e41) a(i);
    }

    public e41 h() {
        return d(size() - 1);
    }

    public void a(int i, e41 e41Var) {
        a(i, (Object) e41Var);
    }
}
