package com.loc;

/* JADX INFO: compiled from: LogJsonDataStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bh extends bk {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public StringBuilder f3681a;
    public boolean b;

    public bh() {
        this.f3681a = new StringBuilder();
        this.b = true;
    }

    public bh(bk bkVar) {
        super(bkVar);
        this.f3681a = new StringBuilder();
        this.b = true;
    }

    @Override // com.loc.bk
    public final byte[] a(byte[] bArr) {
        byte[] bArrA = u.a(this.f3681a.toString());
        this.d = bArrA;
        this.b = true;
        StringBuilder sb = this.f3681a;
        sb.delete(0, sb.length());
        return bArrA;
    }

    @Override // com.loc.bk
    public final void b(byte[] bArr) {
        String strA = u.a(bArr);
        if (this.b) {
            this.b = false;
        } else {
            this.f3681a.append(",");
        }
        StringBuilder sb = this.f3681a;
        sb.append("{\"log\":\"");
        sb.append(strA);
        sb.append("\"}");
    }
}
