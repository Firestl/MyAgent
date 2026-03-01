package com.loc;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: RssiManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ct {

    /* JADX INFO: compiled from: RssiManager.java */
    public static class a implements cr {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3708a;
        public int b;
        public int c;

        public a(int i, int i2, int i3) {
            this.f3708a = i;
            this.b = i2;
            this.c = i3;
        }

        @Override // com.loc.cr
        public final long a() {
            return ct.a(this.f3708a, this.b);
        }

        @Override // com.loc.cr
        public final int b() {
            return this.c;
        }
    }

    /* JADX INFO: compiled from: RssiManager.java */
    public static class b implements cr {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f3709a;
        public int b;

        public b(long j, int i) {
            this.f3709a = j;
            this.b = i;
        }

        @Override // com.loc.cr
        public final long a() {
            return this.f3709a;
        }

        @Override // com.loc.cr
        public final int b() {
            return this.b;
        }
    }

    public static long a(int i, int i2) {
        return (((long) i2) & 4294967295L) | ((((long) i) & 4294967295L) << 32);
    }

    public static synchronized short a(long j) {
        return cs.a().a(j);
    }

    public static synchronized void a(List<cw> list) {
        a aVar;
        if (list != null) {
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list.size());
                for (cw cwVar : list) {
                    if (cwVar instanceof cy) {
                        cy cyVar = (cy) cwVar;
                        aVar = new a(cyVar.j, cyVar.k, cyVar.c);
                    } else if (cwVar instanceof cz) {
                        cz czVar = (cz) cwVar;
                        aVar = new a(czVar.j, czVar.k, czVar.c);
                    } else if (cwVar instanceof da) {
                        da daVar = (da) cwVar;
                        aVar = new a(daVar.j, daVar.k, daVar.c);
                    } else if (cwVar instanceof cx) {
                        cx cxVar = (cx) cwVar;
                        aVar = new a(cxVar.k, cxVar.l, cxVar.c);
                    }
                    arrayList.add(aVar);
                }
                cs.a().a(arrayList);
            }
        }
    }

    public static synchronized short b(long j) {
        return cs.a().b(j);
    }

    public static synchronized void b(List<dd> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList(list.size());
                for (dd ddVar : list) {
                    arrayList.add(new b(ddVar.f3722a, ddVar.c));
                }
                cs.a().b(arrayList);
            }
        }
    }
}
