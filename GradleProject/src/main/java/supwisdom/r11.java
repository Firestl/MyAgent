package supwisdom;

import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.sangfor.dex.util.ExceptionWithContext;
import com.sangfor.dx.dex.code.LocalList;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import supwisdom.wz0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class r11 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final wz0 f8999a;
    public final LocalList b;
    public final t11 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9000e;
    public final int f;
    public final z51 g;
    public final boolean h;
    public h61 k;
    public PrintWriter l;
    public String m;
    public boolean n;
    public final LocalList.a[] o;
    public int i = 0;
    public int j = 1;
    public final k61 c = new k61();

    /* JADX INFO: compiled from: Proguard */
    public class a implements Comparator<wz0.a> {
        public a(r11 r11Var) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(wz0.a aVar, wz0.a aVar2) {
            return aVar.a() - aVar2.a();
        }

        @Override // java.util.Comparator
        public boolean equals(Object obj) {
            return obj == this;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements Comparator<LocalList.a> {
        public b(r11 r11Var) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(LocalList.a aVar, LocalList.a aVar2) {
            return aVar.d() - aVar2.d();
        }

        @Override // java.util.Comparator
        public boolean equals(Object obj) {
            return obj == this;
        }
    }

    public r11(wz0 wz0Var, LocalList localList, t11 t11Var, int i, int i2, boolean z, r51 r51Var) {
        this.f8999a = wz0Var;
        this.b = localList;
        this.d = t11Var;
        this.g = r51Var.f();
        this.h = z;
        this.f9000e = i;
        this.f = i2;
        this.o = new LocalList.a[i2];
    }

    public final void a(int i, String str) {
        if (this.m != null) {
            str = this.m + str;
        }
        h61 h61Var = this.k;
        if (h61Var != null) {
            if (!this.n) {
                i = 0;
            }
            h61Var.a(i, str);
        }
        PrintWriter printWriter = this.l;
        if (printWriter != null) {
            printWriter.println(str);
        }
    }

    public byte[] b() {
        try {
            return c();
        } catch (IOException e2) {
            throw ExceptionWithContext.withContext(e2, "...while encoding debug info");
        }
    }

    public final byte[] c() {
        ArrayList<wz0.a> arrayListA = a();
        a(arrayListA, e());
        this.c.writeByte(7);
        int iC = 0;
        if (this.k != null || this.l != null) {
            a(1, String.format("%04x: prologue end", Integer.valueOf(this.i)));
        }
        int size = arrayListA.size();
        int size2 = this.b.size();
        int iA = 0;
        while (true) {
            iC = c(iC);
            iA = a(iA, arrayListA);
            int iA2 = iC < size2 ? this.b.d(iC).a() : Integer.MAX_VALUE;
            int iA3 = iA < size ? arrayListA.get(iA).a() : Integer.MAX_VALUE;
            int iMin = Math.min(iA3, iA2);
            if (iMin == Integer.MAX_VALUE || (iMin == this.f9000e && iA2 == Integer.MAX_VALUE && iA3 == Integer.MAX_VALUE)) {
                break;
            }
            if (iMin == iA3) {
                a(arrayListA.get(iA));
                iA++;
            } else {
                b(iMin - this.i);
            }
        }
        d();
        return this.c.h();
    }

    public final void d() {
        this.c.writeByte(0);
        if (this.k == null && this.l == null) {
            return;
        }
        a(1, "end sequence");
    }

    public final ArrayList<LocalList.a> e() {
        ArrayList<LocalList.a> arrayList = new ArrayList<>(this.g.c().size());
        int iF = f();
        BitSet bitSet = new BitSet(this.f - iF);
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            LocalList.a aVarD = this.b.d(i);
            int iD = aVarD.d();
            if (iD >= iF) {
                int i2 = iD - iF;
                if (!bitSet.get(i2)) {
                    bitSet.set(i2);
                    arrayList.add(aVarD);
                }
            }
        }
        Collections.sort(arrayList, new b(this));
        return arrayList;
    }

    public final int f() {
        return (this.f - this.g.c().h()) - (!this.h ? 1 : 0);
    }

    public final void b(int i) {
        int iA = this.c.a();
        this.c.writeByte(1);
        this.c.c(i);
        this.i += i;
        if (this.k == null && this.l == null) {
            return;
        }
        a(this.c.a() - iA, String.format("%04x: advance pc", Integer.valueOf(this.i)));
    }

    public final void d(int i) {
        if (i >= 0) {
            this.c.c(i);
            return;
        }
        throw new RuntimeException("Signed value where unsigned required: " + i);
    }

    public final String d(LocalList.a aVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("v");
        sb.append(aVar.d());
        sb.append(' ');
        aVar.c();
        throw null;
    }

    public final ArrayList<wz0.a> a() {
        wz0 wz0Var = this.f8999a;
        int size = wz0Var == null ? 0 : wz0Var.size();
        ArrayList<wz0.a> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(this.f8999a.d(i));
        }
        Collections.sort(arrayList, new a(this));
        return arrayList;
    }

    public static int a(int i, int i2) {
        if (i < -4 || i > 10) {
            throw new RuntimeException("Parameter out of range");
        }
        return (i - (-4)) + (i2 * 15) + 10;
    }

    public final void b(LocalList.a aVar) {
        this.c.a();
        this.c.writeByte(6);
        d(aVar.d());
        if (this.k == null && this.l == null) {
            return;
        }
        this.c.a();
        d(aVar);
        throw null;
    }

    public byte[] a(String str, PrintWriter printWriter, h61 h61Var, boolean z) {
        this.m = str;
        this.l = printWriter;
        this.k = h61Var;
        this.n = z;
        return b();
    }

    public final void a(int i) {
        int iA = this.c.a();
        this.c.writeByte(2);
        this.c.f(i);
        this.j += i;
        if (this.k == null && this.l == null) {
            return;
        }
        a(this.c.a() - iA, String.format("line = %d", Integer.valueOf(this.j)));
    }

    public final void c(LocalList.a aVar) {
        aVar.f();
        throw null;
    }

    public final int c(int i) {
        int size = this.b.size();
        while (i < size && this.b.d(i).a() == this.i) {
            int i2 = i + 1;
            LocalList.a aVarD = this.b.d(i);
            int iD = aVarD.d();
            LocalList.a[] aVarArr = this.o;
            LocalList.a aVar = aVarArr[iD];
            if (aVarD != aVar) {
                aVarArr[iD] = aVarD;
                if (aVarD.g()) {
                    if (aVar != null && aVarD.b(aVar)) {
                        if (!aVar.g()) {
                            b(aVarD);
                        } else {
                            throw new RuntimeException("shouldn't happen");
                        }
                    } else {
                        c(aVarD);
                        throw null;
                    }
                } else if (aVarD.b() != LocalList.Disposition.END_REPLACED) {
                    a(aVarD);
                }
            }
            i = i2;
        }
        return i;
    }

    public final void a(ArrayList<wz0.a> arrayList, ArrayList<LocalList.a> arrayList2) {
        boolean z = (this.k == null && this.l == null) ? false : true;
        int iA = this.c.a();
        if (arrayList.size() > 0) {
            this.j = arrayList.get(0).b().a();
        }
        this.c.c(this.j);
        if (z) {
            a(this.c.a() - iA, "line_start: " + this.j);
        }
        int iF = f();
        a61 a61VarC = this.g.c();
        int size = a61VarC.size();
        if (!this.h) {
            Iterator<LocalList.a> it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                LocalList.a next = it.next();
                if (iF == next.d()) {
                    this.o[iF] = next;
                    break;
                }
            }
            iF++;
        }
        int iA2 = this.c.a();
        this.c.c(size);
        if (z) {
            a(this.c.a() - iA2, String.format("parameters_size: %04x", Integer.valueOf(size)));
        }
        for (int i = 0; i < size; i++) {
            b61 b61VarD = a61VarC.d(i);
            int iA3 = this.c.a();
            for (LocalList.a aVar : arrayList2) {
                if (iF == aVar.d()) {
                    aVar.f();
                    throw null;
                }
            }
            a((v51) null);
            if (z) {
                a(this.c.a() - iA3, "parameter <unnamed>" + Operators.SPACE_STR + "v" + iF);
            }
            iF += b61VarD.d();
        }
        for (LocalList.a aVar2 : this.o) {
            if (aVar2 != null) {
                aVar2.f();
                throw null;
            }
        }
    }

    public final void a(LocalList.a aVar) {
        this.c.a();
        this.c.writeByte(5);
        this.c.c(aVar.d());
        if (this.k == null && this.l == null) {
            return;
        }
        this.c.a();
        d(aVar);
        throw null;
    }

    public final void a(wz0.a aVar) {
        int iA = aVar.b().a();
        int iA2 = aVar.a();
        int i = iA - this.j;
        int i2 = iA2 - this.i;
        if (i2 >= 0) {
            if (i < -4 || i > 10) {
                a(i);
                i = 0;
            }
            int iA3 = a(i, i2);
            if ((iA3 & DefaultImageHeaderParser.VP8_HEADER_MASK) > 0) {
                b(i2);
                iA3 = a(i, 0);
                if ((iA3 & DefaultImageHeaderParser.VP8_HEADER_MASK) > 0) {
                    a(i);
                    iA3 = a(0, 0);
                    i2 = 0;
                    i = 0;
                } else {
                    i2 = 0;
                }
            }
            this.c.writeByte(iA3);
            this.j += i;
            int i3 = this.i + i2;
            this.i = i3;
            if (this.k == null && this.l == null) {
                return;
            }
            a(1, String.format("%04x: line %d", Integer.valueOf(i3), Integer.valueOf(this.j)));
            return;
        }
        throw new RuntimeException("Position entries must be in ascending address order");
    }

    public final int a(int i, ArrayList<wz0.a> arrayList) {
        int size = arrayList.size();
        while (i < size && arrayList.get(i).a() == this.i) {
            a(arrayList.get(i));
            i++;
        }
        return i;
    }

    public final void a(v51 v51Var) {
        t11 t11Var;
        if (v51Var != null && (t11Var = this.d) != null) {
            this.c.c(t11Var.o().a(v51Var) + 1);
        } else {
            this.c.c(0);
        }
    }
}
