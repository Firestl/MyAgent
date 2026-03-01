package com.loc;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: AmapCellLte.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cz extends cw {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public cz(boolean z) {
        super(z, true);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
    }

    @Override // com.loc.cw
    /* JADX INFO: renamed from: a */
    public final cw clone() {
        cz czVar = new cz(this.h);
        czVar.a(this);
        czVar.j = this.j;
        czVar.k = this.k;
        czVar.l = this.l;
        czVar.m = this.m;
        czVar.n = this.n;
        return czVar;
    }

    @Override // com.loc.cw
    public final String toString() {
        return "AmapCellLte{lac=" + this.j + ", cid=" + this.k + ", pci=" + this.l + ", earfcn=" + this.m + ", timingAdvance=" + this.n + Operators.BLOCK_END + super.toString();
    }
}
