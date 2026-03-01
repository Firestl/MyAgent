package com.loc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: compiled from: DB.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ah extends SQLiteOpenHelper {
    public static boolean b = true;
    public static boolean c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ad f3632a;

    public ah(Context context, String str, ad adVar) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.f3632a = adVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f3632a.a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
