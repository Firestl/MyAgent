package com.loc;

import java.io.File;

/* JADX INFO: compiled from: FileNumUpdateStrategy.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bm extends bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3684a;
    public String b;

    public bm(String str, bq bqVar) {
        super(bqVar);
        this.f3684a = 30;
        this.b = str;
    }

    public static int a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            ab.b(th, "fus", "gfn");
            return 0;
        }
    }

    @Override // com.loc.bq
    public final boolean a() {
        return a(this.b) >= this.f3684a;
    }
}
