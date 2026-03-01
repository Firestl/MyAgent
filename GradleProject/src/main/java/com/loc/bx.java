package com.loc;

import android.os.SystemClock;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CellCollector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public cw f3693a;
    public cw b;
    public dc c;
    public a d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<cw> f3694e = new ArrayList(3);

    /* JADX INFO: compiled from: CellCollector.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte f3695a;
        public String b;
        public cw c;
        public cw d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public cw f3696e;
        public List<cw> f = new ArrayList();
        public List<cw> g = new ArrayList();

        public static boolean a(cw cwVar, cw cwVar2) {
            if (cwVar == null || cwVar2 == null) {
                return (cwVar == null) == (cwVar2 == null);
            }
            if ((cwVar instanceof cy) && (cwVar2 instanceof cy)) {
                cy cyVar = (cy) cwVar;
                cy cyVar2 = (cy) cwVar2;
                return cyVar.j == cyVar2.j && cyVar.k == cyVar2.k;
            }
            if ((cwVar instanceof cx) && (cwVar2 instanceof cx)) {
                cx cxVar = (cx) cwVar;
                cx cxVar2 = (cx) cwVar2;
                return cxVar.l == cxVar2.l && cxVar.k == cxVar2.k && cxVar.j == cxVar2.j;
            }
            if ((cwVar instanceof cz) && (cwVar2 instanceof cz)) {
                cz czVar = (cz) cwVar;
                cz czVar2 = (cz) cwVar2;
                return czVar.j == czVar2.j && czVar.k == czVar2.k;
            }
            if ((cwVar instanceof da) && (cwVar2 instanceof da)) {
                da daVar = (da) cwVar;
                da daVar2 = (da) cwVar2;
                if (daVar.j == daVar2.j && daVar.k == daVar2.k) {
                    return true;
                }
            }
            return false;
        }

        public final void a() {
            this.f3695a = (byte) 0;
            this.b = "";
            this.c = null;
            this.d = null;
            this.f3696e = null;
            this.f.clear();
            this.g.clear();
        }

        public final String toString() {
            return "CellInfo{radio=" + ((int) this.f3695a) + ", operator='" + this.b + Operators.SINGLE_QUOTE + ", mainCell=" + this.c + ", mainOldInterCell=" + this.d + ", mainNewInterCell=" + this.f3696e + ", cells=" + this.f + ", historyMainCellList=" + this.g + Operators.BLOCK_END;
        }
    }

    public final a a(dc dcVar, boolean z, byte b, String str, List<cw> list) {
        List list2;
        if (z) {
            this.d.a();
            return null;
        }
        a aVar = this.d;
        aVar.a();
        aVar.f3695a = b;
        aVar.b = str;
        if (list != null) {
            aVar.f.addAll(list);
            for (cw cwVar : aVar.f) {
                if (!cwVar.i && cwVar.h) {
                    aVar.d = cwVar;
                } else if (cwVar.i && cwVar.h) {
                    aVar.f3696e = cwVar;
                }
            }
        }
        cw cwVar2 = aVar.d;
        if (cwVar2 == null) {
            cwVar2 = aVar.f3696e;
        }
        aVar.c = cwVar2;
        if (this.d.c == null) {
            return null;
        }
        boolean z2 = true;
        if (this.c != null) {
            float f = dcVar.g;
            if (!(dcVar.a(this.c) > ((double) ((f > 10.0f ? 1 : (f == 10.0f ? 0 : -1)) > 0 ? 2000.0f : (f > 2.0f ? 1 : (f == 2.0f ? 0 : -1)) > 0 ? 500.0f : 100.0f))) && a.a(this.d.d, this.f3693a) && a.a(this.d.f3696e, this.b)) {
                z2 = false;
            }
        }
        if (!z2) {
            return null;
        }
        a aVar2 = this.d;
        this.f3693a = aVar2.d;
        this.b = aVar2.f3696e;
        this.c = dcVar;
        ct.a(aVar2.f);
        a aVar3 = this.d;
        synchronized (this.f3694e) {
            for (cw cwVar3 : aVar3.f) {
                if (cwVar3 != null && cwVar3.h) {
                    cw cwVarClone = cwVar3.clone();
                    cwVarClone.f3712e = SystemClock.elapsedRealtime();
                    int size = this.f3694e.size();
                    if (size == 0) {
                        list2 = this.f3694e;
                    } else {
                        long jMin = Long.MAX_VALUE;
                        int i = -1;
                        int i2 = 0;
                        int i3 = -1;
                        while (true) {
                            if (i2 >= size) {
                                i = i3;
                                break;
                            }
                            cw cwVar4 = this.f3694e.get(i2);
                            if (!cwVarClone.equals(cwVar4)) {
                                jMin = Math.min(jMin, cwVar4.f3712e);
                                if (jMin == cwVar4.f3712e) {
                                    i3 = i2;
                                }
                                i2++;
                            } else if (cwVarClone.c != cwVar4.c) {
                                cwVar4.f3712e = cwVarClone.c;
                                cwVar4.c = cwVarClone.c;
                            }
                        }
                        if (i >= 0) {
                            if (size < 3) {
                                list2 = this.f3694e;
                            } else if (cwVarClone.f3712e > jMin && i < size) {
                                this.f3694e.remove(i);
                                list2 = this.f3694e;
                            }
                        }
                    }
                    list2.add(cwVarClone);
                }
            }
            this.d.g.clear();
            this.d.g.addAll(this.f3694e);
        }
        return this.d;
    }
}
