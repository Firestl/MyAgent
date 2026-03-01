package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class dz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public fw f7398a;
    public yv b;
    public yv c;
    public yv d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public yv f7399e;
    public int f;
    public int g;
    public int h;
    public int i;

    public dz(fw fwVar, yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4) throws NotFoundException {
        if ((yvVar == null && yvVar3 == null) || ((yvVar2 == null && yvVar4 == null) || ((yvVar != null && yvVar2 == null) || (yvVar3 != null && yvVar4 == null)))) {
            throw NotFoundException.getNotFoundInstance();
        }
        a(fwVar, yvVar, yvVar2, yvVar3, yvVar4);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.dz a(int r13, int r14, boolean r15) throws com.dcloud.zxing2.NotFoundException {
        /*
            r12 = this;
            supwisdom.yv r0 = r12.b
            supwisdom.yv r1 = r12.c
            supwisdom.yv r2 = r12.d
            supwisdom.yv r3 = r12.f7399e
            if (r13 <= 0) goto L29
            if (r15 == 0) goto Le
            r4 = r0
            goto Lf
        Le:
            r4 = r2
        Lf:
            float r5 = r4.b()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L18
            r5 = 0
        L18:
            supwisdom.yv r13 = new supwisdom.yv
            float r4 = r4.a()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L26
            r8 = r13
            goto L2a
        L26:
            r10 = r13
            r8 = r0
            goto L2b
        L29:
            r8 = r0
        L2a:
            r10 = r2
        L2b:
            if (r14 <= 0) goto L5b
            if (r15 == 0) goto L32
            supwisdom.yv r13 = r12.c
            goto L34
        L32:
            supwisdom.yv r13 = r12.f7399e
        L34:
            float r0 = r13.b()
            int r0 = (int) r0
            int r0 = r0 + r14
            supwisdom.fw r14 = r12.f7398a
            int r14 = r14.c()
            if (r0 < r14) goto L4a
            supwisdom.fw r14 = r12.f7398a
            int r14 = r14.c()
            int r0 = r14 + (-1)
        L4a:
            supwisdom.yv r14 = new supwisdom.yv
            float r13 = r13.a()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L58
            r9 = r14
            goto L5c
        L58:
            r11 = r14
            r9 = r1
            goto L5d
        L5b:
            r9 = r1
        L5c:
            r11 = r3
        L5d:
            r12.a()
            supwisdom.dz r13 = new supwisdom.dz
            supwisdom.fw r7 = r12.f7398a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.dz.a(int, int, boolean):supwisdom.dz");
    }

    public yv b() {
        return this.c;
    }

    public yv c() {
        return this.f7399e;
    }

    public int d() {
        return this.g;
    }

    public int e() {
        return this.i;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.h;
    }

    public yv h() {
        return this.b;
    }

    public yv i() {
        return this.d;
    }

    public dz(dz dzVar) {
        a(dzVar.f7398a, dzVar.b, dzVar.c, dzVar.d, dzVar.f7399e);
    }

    public final void a() {
        if (this.b == null) {
            this.b = new yv(0.0f, this.d.b());
            this.c = new yv(0.0f, this.f7399e.b());
        } else if (this.d == null) {
            this.d = new yv(this.f7398a.e() - 1, this.b.b());
            this.f7399e = new yv(this.f7398a.e() - 1, this.c.b());
        }
        this.f = (int) Math.min(this.b.a(), this.c.a());
        this.g = (int) Math.max(this.d.a(), this.f7399e.a());
        this.h = (int) Math.min(this.b.b(), this.d.b());
        this.i = (int) Math.max(this.c.b(), this.f7399e.b());
    }

    public final void a(fw fwVar, yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4) {
        this.f7398a = fwVar;
        this.b = yvVar;
        this.c = yvVar2;
        this.d = yvVar3;
        this.f7399e = yvVar4;
        a();
    }

    public static dz a(dz dzVar, dz dzVar2) throws NotFoundException {
        return dzVar == null ? dzVar2 : dzVar2 == null ? dzVar : new dz(dzVar.f7398a, dzVar.b, dzVar.c, dzVar2.d, dzVar2.f7399e);
    }
}
