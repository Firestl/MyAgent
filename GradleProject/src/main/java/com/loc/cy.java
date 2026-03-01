package com.loc;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: AmapCellGsm.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cy extends cw {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;

    public cy(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
        this.o = Integer.MAX_VALUE;
    }

    @Override // com.loc.cw
    /* JADX INFO: renamed from: a */
    public final cw clone() {
        cy cyVar = new cy(this.h, this.i);
        cyVar.a(this);
        cyVar.j = this.j;
        cyVar.k = this.k;
        cyVar.l = this.l;
        cyVar.m = this.m;
        cyVar.n = this.n;
        cyVar.o = this.o;
        return cyVar;
    }

    @Override // com.loc.cw
    public final String toString() {
        return "AmapCellGsm{lac=" + this.j + ", cid=" + this.k + ", psc=" + this.l + ", arfcn=" + this.m + ", bsic=" + this.n + ", timingAdvance=" + this.o + Operators.BLOCK_END + super.toString();
    }
}
