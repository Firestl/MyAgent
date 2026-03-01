package com.igexin.push.core.e;

import android.database.sqlite.SQLiteDatabase;
import com.igexin.push.g.p;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements a {
    public static b b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, byte[]> f3405a = new HashMap();

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private String a(byte[] bArr) {
        String strA;
        do {
            strA = p.a();
        } while (this.f3405a.containsKey(strA));
        this.f3405a.put(strA, bArr);
        return strA;
    }

    private synchronized byte[] a(String str) {
        byte[] bArr;
        bArr = this.f3405a.get(str);
        if (bArr != null) {
            this.f3405a.remove(str);
        }
        return bArr;
    }

    @Override // com.igexin.push.core.e.a
    public final void a(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.igexin.push.core.e.a
    public final void b(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.igexin.push.core.e.a
    public final void c(SQLiteDatabase sQLiteDatabase) {
    }
}
