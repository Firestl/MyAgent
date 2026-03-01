package com.zx.a.I8b7;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f6202a;

    public abstract String a();

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final SQLiteDatabase b() {
        b bVar = this.f6202a;
        if (bVar != null) {
            return bVar.b().getReadableDatabase();
        }
        StringBuilder sbA = m2.a("table ");
        sbA.append(c());
        sbA.append(" has not been added to a db");
        throw new RuntimeException(sbA.toString());
    }

    public abstract String c();

    public final SQLiteDatabase d() {
        b bVar = this.f6202a;
        if (bVar != null) {
            return bVar.b().getWritableDatabase();
        }
        throw new RuntimeException("table zx_table has not been added to a db");
    }
}
