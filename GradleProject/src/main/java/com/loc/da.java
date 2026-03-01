package com.loc;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: AmapCellWcdma.java */
/* JADX INFO: loaded from: classes2.dex */
public final class da extends cw {
    public int j;
    public int k;
    public int l;
    public int m;

    public da(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
    }

    @Override // com.loc.cw
    /* JADX INFO: renamed from: a */
    public final cw clone() {
        da daVar = new da(this.h, this.i);
        daVar.a(this);
        daVar.j = this.j;
        daVar.k = this.k;
        daVar.l = this.l;
        daVar.m = this.m;
        return daVar;
    }

    @Override // com.loc.cw
    public final String toString() {
        return "AmapCellWcdma{lac=" + this.j + ", cid=" + this.k + ", psc=" + this.l + ", uarfcn=" + this.m + Operators.BLOCK_END + super.toString();
    }
}
