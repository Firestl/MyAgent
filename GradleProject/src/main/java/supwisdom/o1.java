package supwisdom;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class o1 {
    public Interpolator c;
    public qb d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8597e;
    public long b = -1;
    public final rb f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<pb> f8596a = new ArrayList<>();

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    public class a extends rb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f8598a = false;
        public int b = 0;

        public a() {
        }

        public void a() {
            this.b = 0;
            this.f8598a = false;
            o1.this.b();
        }

        @Override // supwisdom.qb
        public void b(View view) {
            int i = this.b + 1;
            this.b = i;
            if (i == o1.this.f8596a.size()) {
                qb qbVar = o1.this.d;
                if (qbVar != null) {
                    qbVar.b(null);
                }
                a();
            }
        }

        @Override // supwisdom.rb, supwisdom.qb
        public void c(View view) {
            if (this.f8598a) {
                return;
            }
            this.f8598a = true;
            qb qbVar = o1.this.d;
            if (qbVar != null) {
                qbVar.c(null);
            }
        }
    }

    public o1 a(pb pbVar) {
        if (!this.f8597e) {
            this.f8596a.add(pbVar);
        }
        return this;
    }

    public void b() {
        this.f8597e = false;
    }

    public void c() {
        if (this.f8597e) {
            return;
        }
        for (pb pbVar : this.f8596a) {
            long j = this.b;
            if (j >= 0) {
                pbVar.a(j);
            }
            Interpolator interpolator = this.c;
            if (interpolator != null) {
                pbVar.a(interpolator);
            }
            if (this.d != null) {
                pbVar.a(this.f);
            }
            pbVar.c();
        }
        this.f8597e = true;
    }

    public o1 a(pb pbVar, pb pbVar2) {
        this.f8596a.add(pbVar);
        pbVar2.b(pbVar.b());
        this.f8596a.add(pbVar2);
        return this;
    }

    public void a() {
        if (this.f8597e) {
            Iterator<pb> it = this.f8596a.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.f8597e = false;
        }
    }

    public o1 a(long j) {
        if (!this.f8597e) {
            this.b = j;
        }
        return this;
    }

    public o1 a(Interpolator interpolator) {
        if (!this.f8597e) {
            this.c = interpolator;
        }
        return this;
    }

    public o1 a(qb qbVar) {
        if (!this.f8597e) {
            this.d = qbVar;
        }
        return this;
    }
}
