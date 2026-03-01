package supwisdom;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.i.r;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import supwisdom.k90;
import supwisdom.va0;
import supwisdom.wa0;

/* JADX INFO: compiled from: HlsPlaylistTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class za0 implements r.a<c80<xa0>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f9984a;
    public final db0 b;
    public final int d;
    public final c g;
    public final k90.a j;
    public va0 k;
    public va0.a l;
    public wa0 m;
    public boolean n;
    public final List<b> h = new ArrayList();
    public final com.google.android.exoplayer2.i.r i = new com.google.android.exoplayer2.i.r("HlsPlaylistTracker:MasterPlaylist");
    public final ya0 c = new ya0();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final IdentityHashMap<va0.a, a> f9985e = new IdentityHashMap<>();
    public final Handler f = new Handler();

    /* JADX INFO: compiled from: HlsPlaylistTracker.java */
    public final class a implements r.a<c80<xa0>>, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final va0.a f9986a;
        public final com.google.android.exoplayer2.i.r b = new com.google.android.exoplayer2.i.r("HlsPlaylistTracker:MediaPlaylist");
        public final c80<xa0> c;
        public wa0 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f9987e;
        public long f;
        public long g;
        public boolean h;

        public a(va0.a aVar, long j) {
            this.f9986a = aVar;
            this.f = j;
            this.c = new c80<>(za0.this.b.a(4), w80.a(za0.this.k.f9726a, aVar.f9497a), 4, za0.this.c);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h = false;
            d();
        }

        public boolean b() {
            int i;
            if (this.d == null) {
                return false;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long jMax = Math.max(com.igexin.push.config.c.k, b20.a(this.d.o));
            wa0 wa0Var = this.d;
            return wa0Var.j || (i = wa0Var.b) == 2 || i == 1 || this.f9987e + jMax > jElapsedRealtime;
        }

        public void c() {
            this.b.c();
        }

        public void d() {
            this.g = 0L;
            if (this.h || this.b.a()) {
                return;
            }
            this.b.a(this.c, this, za0.this.d);
        }

        public wa0 a() {
            this.f = SystemClock.elapsedRealtime();
            return this.d;
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public void a(c80<xa0> c80Var, long j, long j2) {
            xa0 xa0VarD = c80Var.d();
            if (xa0VarD instanceof wa0) {
                a((wa0) xa0VarD);
                za0.this.j.a(c80Var.f7160a, 4, j, j2, c80Var.e());
            } else {
                a(c80Var, j, j2, (IOException) new com.google.android.exoplayer2.n("Loaded playlist has unexpected type."));
            }
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public void a(c80<xa0> c80Var, long j, long j2, boolean z) {
            za0.this.j.b(c80Var.f7160a, 4, j, j2, c80Var.e());
        }

        @Override // com.google.android.exoplayer2.i.r.a
        public int a(c80<xa0> c80Var, long j, long j2, IOException iOException) {
            boolean z = iOException instanceof com.google.android.exoplayer2.n;
            za0.this.j.a(c80Var.f7160a, 4, j, j2, c80Var.e(), iOException, z);
            if (z) {
                return 3;
            }
            boolean z2 = true;
            if (s90.a(iOException)) {
                this.g = SystemClock.elapsedRealtime() + 60000;
                za0.this.a(this.f9986a, 60000L);
                if (za0.this.l != this.f9986a || za0.this.f()) {
                    z2 = false;
                }
            }
            return z2 ? 0 : 2;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a(supwisdom.wa0 r8) {
            /*
                r7 = this;
                supwisdom.wa0 r0 = r7.d
                long r1 = android.os.SystemClock.elapsedRealtime()
                r7.f9987e = r1
                supwisdom.za0 r1 = supwisdom.za0.this
                supwisdom.wa0 r8 = supwisdom.za0.a(r1, r0, r8)
                r7.d = r8
                r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
                if (r8 == r0) goto L26
                supwisdom.za0 r0 = supwisdom.za0.this
                supwisdom.va0$a r3 = r7.f9986a
                boolean r8 = supwisdom.za0.a(r0, r3, r8)
                if (r8 == 0) goto L30
                supwisdom.wa0 r8 = r7.d
                long r3 = r8.i
                goto L31
            L26:
                boolean r0 = r8.j
                if (r0 != 0) goto L30
                long r3 = r8.i
                r5 = 2
                long r3 = r3 / r5
                goto L31
            L30:
                r3 = r1
            L31:
                int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r8 == 0) goto L45
                supwisdom.za0 r8 = supwisdom.za0.this
                android.os.Handler r8 = supwisdom.za0.h(r8)
                long r0 = supwisdom.b20.a(r3)
                boolean r8 = r8.postDelayed(r7, r0)
                r7.h = r8
            L45:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.za0.a.a(supwisdom.wa0):void");
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistTracker.java */
    public interface b {
        void a(va0.a aVar, long j);

        void h();
    }

    /* JADX INFO: compiled from: HlsPlaylistTracker.java */
    public interface c {
        void a(wa0 wa0Var);
    }

    public za0(Uri uri, db0 db0Var, k90.a aVar, int i, c cVar) {
        this.f9984a = uri;
        this.b = db0Var;
        this.j = aVar;
        this.d = i;
        this.g = cVar;
    }

    public void b(b bVar) {
        this.h.remove(bVar);
    }

    public void c() {
        this.i.c();
        Iterator<a> it = this.f9985e.values().iterator();
        while (it.hasNext()) {
            it.next().c();
        }
        this.f.removeCallbacksAndMessages(null);
        this.f9985e.clear();
    }

    public void d() throws IOException {
        this.i.d();
        va0.a aVar = this.l;
        if (aVar != null) {
            c(aVar);
        }
    }

    public boolean e() {
        return this.n;
    }

    public final boolean f() {
        List<va0.a> list = this.k.b;
        int size = list.size();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        for (int i = 0; i < size; i++) {
            a aVar = this.f9985e.get(list.get(i));
            if (jElapsedRealtime > aVar.g) {
                this.l = aVar.f9986a;
                aVar.d();
                return true;
            }
        }
        return false;
    }

    public va0 b() {
        return this.k;
    }

    public final void e(va0.a aVar) {
        if (this.k.b.contains(aVar)) {
            wa0 wa0Var = this.m;
            if ((wa0Var == null || !wa0Var.j) && this.f9985e.get(this.l).f - SystemClock.elapsedRealtime() > 15000) {
                this.l = aVar;
                this.f9985e.get(aVar).d();
            }
        }
    }

    public boolean b(va0.a aVar) {
        return this.f9985e.get(aVar).b();
    }

    public final long b(wa0 wa0Var, wa0 wa0Var2) {
        if (wa0Var2.k) {
            return wa0Var2.d;
        }
        wa0 wa0Var3 = this.m;
        long j = wa0Var3 != null ? wa0Var3.d : 0L;
        if (wa0Var == null) {
            return j;
        }
        int size = wa0Var.m.size();
        wa0.a aVarD = d(wa0Var, wa0Var2);
        if (aVarD != null) {
            return wa0Var.d + aVarD.d;
        }
        return size == wa0Var2.g - wa0Var.g ? wa0Var.a() : j;
    }

    public void d(va0.a aVar) {
        this.f9985e.get(aVar).d();
    }

    public static wa0.a d(wa0 wa0Var, wa0 wa0Var2) {
        int i = wa0Var2.g - wa0Var.g;
        List<wa0.a> list = wa0Var.m;
        if (i < list.size()) {
            return list.get(i);
        }
        return null;
    }

    public void c(va0.a aVar) throws IOException {
        this.f9985e.get(aVar).b.d();
    }

    public void a(b bVar) {
        this.h.add(bVar);
    }

    public final int c(wa0 wa0Var, wa0 wa0Var2) {
        wa0.a aVarD;
        if (wa0Var2.f9604e) {
            return wa0Var2.f;
        }
        wa0 wa0Var3 = this.m;
        int i = wa0Var3 != null ? wa0Var3.f : 0;
        return (wa0Var == null || (aVarD = d(wa0Var, wa0Var2)) == null) ? i : (wa0Var.f + aVarD.c) - wa0Var2.m.get(0).c;
    }

    public void a() {
        this.i.a(new c80(this.b.a(4), this.f9984a, 4, this.c), this, this.d);
    }

    public wa0 a(va0.a aVar) {
        wa0 wa0VarA = this.f9985e.get(aVar).a();
        if (wa0VarA != null) {
            e(aVar);
        }
        return wa0VarA;
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(c80<xa0> c80Var, long j, long j2) {
        va0 va0VarA;
        xa0 xa0VarD = c80Var.d();
        boolean z = xa0VarD instanceof wa0;
        if (z) {
            va0VarA = va0.a(xa0VarD.f9726a);
        } else {
            va0VarA = (va0) xa0VarD;
        }
        this.k = va0VarA;
        this.l = va0VarA.b.get(0);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(va0VarA.b);
        arrayList.addAll(va0VarA.c);
        arrayList.addAll(va0VarA.d);
        a(arrayList);
        a aVar = this.f9985e.get(this.l);
        if (z) {
            aVar.a((wa0) xa0VarD);
        } else {
            aVar.d();
        }
        this.j.a(c80Var.f7160a, 4, j, j2, c80Var.e());
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public void a(c80<xa0> c80Var, long j, long j2, boolean z) {
        this.j.b(c80Var.f7160a, 4, j, j2, c80Var.e());
    }

    @Override // com.google.android.exoplayer2.i.r.a
    public int a(c80<xa0> c80Var, long j, long j2, IOException iOException) {
        boolean z = iOException instanceof com.google.android.exoplayer2.n;
        this.j.a(c80Var.f7160a, 4, j, j2, c80Var.e(), iOException, z);
        return z ? 3 : 0;
    }

    public final void a(List<va0.a> list) {
        int size = list.size();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        for (int i = 0; i < size; i++) {
            va0.a aVar = list.get(i);
            this.f9985e.put(aVar, new a(aVar, jElapsedRealtime));
        }
    }

    public final boolean a(va0.a aVar, wa0 wa0Var) {
        if (aVar == this.l) {
            if (this.m == null) {
                this.n = !wa0Var.j;
            }
            this.m = wa0Var;
            this.g.a(wa0Var);
        }
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            this.h.get(i).h();
        }
        return aVar == this.l && !wa0Var.j;
    }

    public final void a(va0.a aVar, long j) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            this.h.get(i).a(aVar, j);
        }
    }

    public final wa0 a(wa0 wa0Var, wa0 wa0Var2) {
        if (wa0Var2.a(wa0Var)) {
            return wa0Var2.a(b(wa0Var, wa0Var2), c(wa0Var, wa0Var2));
        }
        return wa0Var2.j ? wa0Var.b() : wa0Var;
    }
}
