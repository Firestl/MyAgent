package supwisdom;

import com.dcloud.zxing2.NotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f10038a;
    public final pw b;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final yv f10039a;
        public final yv b;
        public final int c;

        public yv a() {
            return this.f10039a;
        }

        public yv b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public String toString() {
            return this.f10039a + "/" + this.b + '/' + this.c;
        }

        public b(yv yvVar, yv yvVar2, int i) {
            this.f10039a = yvVar;
            this.b = yvVar2;
            this.c = i;
        }
    }

    public static final class c implements Comparator<b>, Serializable {
        public c() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            return bVar.c() - bVar2.c();
        }
    }

    public zw(fw fwVar) throws NotFoundException {
        this.f10038a = fwVar;
        this.b = new pw(fwVar);
    }

    public static int b(yv yvVar, yv yvVar2) {
        return ow.a(yv.a(yvVar, yvVar2));
    }

    public final yv a(yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4, int i) {
        float f = i;
        float fB = b(yvVar, yvVar2) / f;
        float fB2 = b(yvVar3, yvVar4);
        yv yvVar5 = new yv(yvVar4.a() + (((yvVar4.a() - yvVar3.a()) / fB2) * fB), yvVar4.b() + (fB * ((yvVar4.b() - yvVar3.b()) / fB2)));
        float fB3 = b(yvVar, yvVar3) / f;
        float fB4 = b(yvVar2, yvVar4);
        yv yvVar6 = new yv(yvVar4.a() + (((yvVar4.a() - yvVar2.a()) / fB4) * fB3), yvVar4.b() + (fB3 * ((yvVar4.b() - yvVar2.b()) / fB4)));
        if (a(yvVar5)) {
            return (a(yvVar6) && Math.abs(a(yvVar3, yvVar5).c() - a(yvVar2, yvVar5).c()) > Math.abs(a(yvVar3, yvVar6).c() - a(yvVar2, yvVar6).c())) ? yvVar6 : yvVar5;
        }
        if (a(yvVar6)) {
            return yvVar6;
        }
        return null;
    }

    public final yv a(yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4, int i, int i2) {
        float fB = b(yvVar, yvVar2) / i;
        float fB2 = b(yvVar3, yvVar4);
        yv yvVar5 = new yv(yvVar4.a() + (((yvVar4.a() - yvVar3.a()) / fB2) * fB), yvVar4.b() + (fB * ((yvVar4.b() - yvVar3.b()) / fB2)));
        float fB3 = b(yvVar, yvVar3) / i2;
        float fB4 = b(yvVar2, yvVar4);
        yv yvVar6 = new yv(yvVar4.a() + (((yvVar4.a() - yvVar2.a()) / fB4) * fB3), yvVar4.b() + (fB3 * ((yvVar4.b() - yvVar2.b()) / fB4)));
        if (a(yvVar5)) {
            return (a(yvVar6) && Math.abs(i - a(yvVar3, yvVar5).c()) + Math.abs(i2 - a(yvVar2, yvVar5).c()) > Math.abs(i - a(yvVar3, yvVar6).c()) + Math.abs(i2 - a(yvVar2, yvVar6).c())) ? yvVar6 : yvVar5;
        }
        if (a(yvVar6)) {
            return yvVar6;
        }
        return null;
    }

    public jw a() throws NotFoundException {
        yv yvVar;
        yv yvVar2;
        fw fwVarA;
        yv[] yvVarArrA = this.b.a();
        yv yvVar3 = yvVarArrA[0];
        yv yvVar4 = yvVarArrA[1];
        yv yvVar5 = yvVarArrA[2];
        yv yvVar6 = yvVarArrA[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(a(yvVar3, yvVar4));
        arrayList.add(a(yvVar3, yvVar5));
        arrayList.add(a(yvVar4, yvVar6));
        arrayList.add(a(yvVar5, yvVar6));
        yv yvVar7 = null;
        Collections.sort(arrayList, new c());
        b bVar = (b) arrayList.get(0);
        b bVar2 = (b) arrayList.get(1);
        HashMap map = new HashMap();
        a(map, bVar.a());
        a(map, bVar.b());
        a(map, bVar2.a());
        a(map, bVar2.b());
        yv yvVar8 = null;
        yv yvVar9 = null;
        for (Map.Entry entry : map.entrySet()) {
            yv yvVar10 = (yv) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                yvVar8 = yvVar10;
            } else if (yvVar7 == null) {
                yvVar7 = yvVar10;
            } else {
                yvVar9 = yvVar10;
            }
        }
        if (yvVar7 != null && yvVar8 != null && yvVar9 != null) {
            yv[] yvVarArr = {yvVar7, yvVar8, yvVar9};
            yv.a(yvVarArr);
            yv yvVar11 = yvVarArr[0];
            yv yvVar12 = yvVarArr[1];
            yv yvVar13 = yvVarArr[2];
            if (!map.containsKey(yvVar3)) {
                yvVar = yvVar3;
            } else if (map.containsKey(yvVar4)) {
                yvVar = !map.containsKey(yvVar5) ? yvVar5 : yvVar6;
            } else {
                yvVar = yvVar4;
            }
            int iC = a(yvVar13, yvVar).c();
            int iC2 = a(yvVar11, yvVar).c();
            if ((iC & 1) == 1) {
                iC++;
            }
            int i = iC + 2;
            if ((iC2 & 1) == 1) {
                iC2++;
            }
            int i2 = iC2 + 2;
            if (i * 4 < i2 * 7 && i2 * 4 < i * 7) {
                yv yvVarA = a(yvVar12, yvVar11, yvVar13, yvVar, Math.min(i2, i));
                if (yvVarA != null) {
                    yvVar = yvVarA;
                }
                int iMax = Math.max(a(yvVar13, yvVar).c(), a(yvVar11, yvVar).c()) + 1;
                if ((iMax & 1) == 1) {
                    iMax++;
                }
                int i3 = iMax;
                fwVarA = a(this.f10038a, yvVar13, yvVar12, yvVar11, yvVar, i3, i3);
                yvVar2 = yvVar13;
            } else {
                yvVar2 = yvVar13;
                yv yvVarA2 = a(yvVar12, yvVar11, yvVar13, yvVar, i, i2);
                if (yvVarA2 != null) {
                    yvVar = yvVarA2;
                }
                int iC3 = a(yvVar2, yvVar).c();
                int iC4 = a(yvVar11, yvVar).c();
                if ((iC3 & 1) == 1) {
                    iC3++;
                }
                int i4 = iC3;
                if ((iC4 & 1) == 1) {
                    iC4++;
                }
                fwVarA = a(this.f10038a, yvVar2, yvVar12, yvVar11, yvVar, i4, iC4);
            }
            return new jw(fwVarA, new yv[]{yvVar2, yvVar12, yvVar11, yvVar});
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static void a(Map<yv, Integer> map, yv yvVar) {
        Integer num = map.get(yvVar);
        map.put(yvVar, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    public final boolean a(yv yvVar) {
        return yvVar.a() >= 0.0f && yvVar.a() < ((float) this.f10038a.e()) && yvVar.b() > 0.0f && yvVar.b() < ((float) this.f10038a.c());
    }

    public static fw a(fw fwVar, yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4, int i, int i2) throws NotFoundException {
        float f = i - 0.5f;
        float f2 = i2 - 0.5f;
        return lw.a().a(fwVar, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, yvVar.a(), yvVar.b(), yvVar4.a(), yvVar4.b(), yvVar3.a(), yvVar3.b(), yvVar2.a(), yvVar2.b());
    }

    public final b a(yv yvVar, yv yvVar2) {
        int iA = (int) yvVar.a();
        int iB = (int) yvVar.b();
        int iA2 = (int) yvVar2.a();
        int iB2 = (int) yvVar2.b();
        int i = 0;
        boolean z = Math.abs(iB2 - iB) > Math.abs(iA2 - iA);
        if (!z) {
            iB = iA;
            iA = iB;
            iB2 = iA2;
            iA2 = iB2;
        }
        int iAbs = Math.abs(iB2 - iB);
        int iAbs2 = Math.abs(iA2 - iA);
        int i2 = (-iAbs) / 2;
        int i3 = iA < iA2 ? 1 : -1;
        int i4 = iB >= iB2 ? -1 : 1;
        boolean zB = this.f10038a.b(z ? iA : iB, z ? iB : iA);
        while (iB != iB2) {
            boolean zB2 = this.f10038a.b(z ? iA : iB, z ? iB : iA);
            if (zB2 != zB) {
                i++;
                zB = zB2;
            }
            i2 += iAbs2;
            if (i2 > 0) {
                if (iA == iA2) {
                    break;
                }
                iA += i3;
                i2 -= iAbs;
            }
            iB += i4;
        }
        return new b(yvVar, yvVar2, i);
    }
}
