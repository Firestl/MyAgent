package com.zx.a.I8b7;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<y> f6228a = new ArrayList();

    public void a(int i, String str, String str2, Throwable th) {
        for (y yVar : this.f6228a) {
            try {
                if (yVar.a(i, null)) {
                    yVar.a(i, null, str2, th);
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public void a(y yVar) {
        this.f6228a.add(yVar);
    }
}
