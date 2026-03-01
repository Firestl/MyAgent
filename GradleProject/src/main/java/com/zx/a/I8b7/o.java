package com.zx.a.I8b7;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ File f6244a;
    public final /* synthetic */ String b;
    public final /* synthetic */ File c;
    public final /* synthetic */ File d;

    public o(p pVar, File file, String str, File file2, File file3) {
        this.f6244a = file;
        this.b = str;
        this.c = file2;
        this.d = file3;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Thread.sleep(1000L);
            this.f6244a.delete();
            new File(this.b).delete();
            this.c.delete();
            this.d.delete();
            b0.a(this.d);
        } catch (Throwable th) {
            y1.a(th);
        }
    }
}
