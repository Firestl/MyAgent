package com.loc;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: AmapCellCdma.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cx extends cw {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public cx(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }

    @Override // com.loc.cw
    /* JADX INFO: renamed from: a */
    public final cw clone() {
        cx cxVar = new cx(this.h, this.i);
        cxVar.a(this);
        this.j = cxVar.j;
        this.k = cxVar.k;
        this.l = cxVar.l;
        this.m = cxVar.m;
        this.n = cxVar.n;
        return cxVar;
    }

    @Override // com.loc.cw
    public final String toString() {
        return "AmapCellCdma{sid=" + this.j + ", nid=" + this.k + ", bid=" + this.l + ", latitude=" + this.m + ", longitude=" + this.n + Operators.BLOCK_END + super.toString();
    }
}
