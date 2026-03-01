package com.loc;

/* JADX INFO: compiled from: UpdateStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class bq {
    public bq c;

    public bq() {
    }

    public bq(bq bqVar) {
        this.c = bqVar;
    }

    public void a(int i) {
        bq bqVar = this.c;
        if (bqVar != null) {
            bqVar.a(i);
        }
    }

    public void a(boolean z) {
        bq bqVar = this.c;
        if (bqVar != null) {
            bqVar.a(z);
        }
    }

    public abstract boolean a();

    public int b() {
        bq bqVar = this.c;
        return Math.min(Integer.MAX_VALUE, bqVar != null ? bqVar.b() : Integer.MAX_VALUE);
    }

    public final boolean c() {
        bq bqVar = this.c;
        if (bqVar != null ? bqVar.c() : true) {
            return a();
        }
        return false;
    }
}
