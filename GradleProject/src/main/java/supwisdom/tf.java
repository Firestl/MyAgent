package supwisdom;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewInfoStore.java */
/* JADX INFO: loaded from: classes.dex */
public class tf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j4<RecyclerView.b0, a> f9281a = new j4<>();
    public final m4<RecyclerView.b0> b = new m4<>();

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public interface b {
        void a(RecyclerView.b0 b0Var);

        void a(RecyclerView.b0 b0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void b(RecyclerView.b0 b0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);

        void c(RecyclerView.b0 b0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2);
    }

    public void a() {
        this.f9281a.clear();
        this.b.a();
    }

    public boolean b(RecyclerView.b0 b0Var) {
        a aVar = this.f9281a.get(b0Var);
        return (aVar == null || (aVar.f9282a & 1) == 0) ? false : true;
    }

    public void c(RecyclerView.b0 b0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f9281a.get(b0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f9281a.put(b0Var, aVarB);
        }
        aVarB.b = cVar;
        aVarB.f9282a |= 4;
    }

    public void d(RecyclerView.b0 b0Var) {
        g(b0Var);
    }

    public RecyclerView.l.c e(RecyclerView.b0 b0Var) {
        return a(b0Var, 8);
    }

    public RecyclerView.l.c f(RecyclerView.b0 b0Var) {
        return a(b0Var, 4);
    }

    public void g(RecyclerView.b0 b0Var) {
        a aVar = this.f9281a.get(b0Var);
        if (aVar == null) {
            return;
        }
        aVar.f9282a &= -2;
    }

    public void h(RecyclerView.b0 b0Var) {
        int iC = this.b.c() - 1;
        while (true) {
            if (iC < 0) {
                break;
            }
            if (b0Var == this.b.c(iC)) {
                this.b.b(iC);
                break;
            }
            iC--;
        }
        a aVarRemove = this.f9281a.remove(b0Var);
        if (aVarRemove != null) {
            a.a(aVarRemove);
        }
    }

    public final RecyclerView.l.c a(RecyclerView.b0 b0Var, int i) {
        a aVarValueAt;
        RecyclerView.l.c cVar;
        int iIndexOfKey = this.f9281a.indexOfKey(b0Var);
        if (iIndexOfKey >= 0 && (aVarValueAt = this.f9281a.valueAt(iIndexOfKey)) != null) {
            int i2 = aVarValueAt.f9282a;
            if ((i2 & i) != 0) {
                aVarValueAt.f9282a = (~i) & i2;
                if (i == 4) {
                    cVar = aVarValueAt.b;
                } else if (i == 8) {
                    cVar = aVarValueAt.c;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((aVarValueAt.f9282a & 12) == 0) {
                    this.f9281a.removeAt(iIndexOfKey);
                    a.a(aVarValueAt);
                }
                return cVar;
            }
        }
        return null;
    }

    public void b(RecyclerView.b0 b0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f9281a.get(b0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f9281a.put(b0Var, aVarB);
        }
        aVarB.c = cVar;
        aVarB.f9282a |= 8;
    }

    /* JADX INFO: compiled from: ViewInfoStore.java */
    public static class a {
        public static ka<a> d = new la(20);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9282a;
        public RecyclerView.l.c b;
        public RecyclerView.l.c c;

        public static void a(a aVar) {
            aVar.f9282a = 0;
            aVar.b = null;
            aVar.c = null;
            d.release(aVar);
        }

        public static a b() {
            a aVarAcquire = d.acquire();
            return aVarAcquire == null ? new a() : aVarAcquire;
        }

        public static void a() {
            while (d.acquire() != null) {
            }
        }
    }

    public boolean c(RecyclerView.b0 b0Var) {
        a aVar = this.f9281a.get(b0Var);
        return (aVar == null || (aVar.f9282a & 4) == 0) ? false : true;
    }

    public void b() {
        a.a();
    }

    public void a(long j, RecyclerView.b0 b0Var) {
        this.b.c(j, b0Var);
    }

    public void a(RecyclerView.b0 b0Var, RecyclerView.l.c cVar) {
        a aVarB = this.f9281a.get(b0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f9281a.put(b0Var, aVarB);
        }
        aVarB.f9282a |= 2;
        aVarB.b = cVar;
    }

    public RecyclerView.b0 a(long j) {
        return this.b.a(j);
    }

    public void a(RecyclerView.b0 b0Var) {
        a aVarB = this.f9281a.get(b0Var);
        if (aVarB == null) {
            aVarB = a.b();
            this.f9281a.put(b0Var, aVarB);
        }
        aVarB.f9282a |= 1;
    }

    public void a(b bVar) {
        for (int size = this.f9281a.size() - 1; size >= 0; size--) {
            RecyclerView.b0 b0VarKeyAt = this.f9281a.keyAt(size);
            a aVarRemoveAt = this.f9281a.removeAt(size);
            int i = aVarRemoveAt.f9282a;
            if ((i & 3) == 3) {
                bVar.a(b0VarKeyAt);
            } else if ((i & 1) != 0) {
                RecyclerView.l.c cVar = aVarRemoveAt.b;
                if (cVar == null) {
                    bVar.a(b0VarKeyAt);
                } else {
                    bVar.b(b0VarKeyAt, cVar, aVarRemoveAt.c);
                }
            } else if ((i & 14) == 14) {
                bVar.a(b0VarKeyAt, aVarRemoveAt.b, aVarRemoveAt.c);
            } else if ((i & 12) == 12) {
                bVar.c(b0VarKeyAt, aVarRemoveAt.b, aVarRemoveAt.c);
            } else if ((i & 4) != 0) {
                bVar.b(b0VarKeyAt, aVarRemoveAt.b, null);
            } else if ((i & 8) != 0) {
                bVar.a(b0VarKeyAt, aVarRemoveAt.b, aVarRemoveAt.c);
            }
            a.a(aVarRemoveAt);
        }
    }
}
