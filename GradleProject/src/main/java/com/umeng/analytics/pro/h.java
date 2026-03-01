package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.umeng.analytics.pro.g;

/* JADX INFO: compiled from: UMDBCreater.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends SQLiteOpenHelper {
    public static Context b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5284a;

    /* JADX INFO: compiled from: UMDBCreater.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f5285a = new h(h.b, j.b(h.b), g.b, null, 2);
    }

    public static h a(Context context) {
        if (b == null) {
            b = context.getApplicationContext();
        }
        return a.f5285a;
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f5284a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)");
        } catch (SQLException unused) {
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f5284a = "create table if not exists __is(id INTEGER primary key autoincrement, __ii TEXT unique, __e TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __is(id INTEGER primary key autoincrement, __ii TEXT unique, __e TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)");
        } catch (SQLException unused) {
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        if (!j.a(sQLiteDatabase, g.d.f5279a, "__av")) {
            j.a(sQLiteDatabase, g.d.f5279a, "__sp", "TEXT");
            j.a(sQLiteDatabase, g.d.f5279a, "__pp", "TEXT");
            j.a(sQLiteDatabase, g.d.f5279a, "__av", "TEXT");
            j.a(sQLiteDatabase, g.d.f5279a, "__vc", "TEXT");
        }
        if (!j.a(sQLiteDatabase, g.b.f5269a, "__av")) {
            j.a(sQLiteDatabase, g.b.f5269a, "__av", "TEXT");
            j.a(sQLiteDatabase, g.b.f5269a, "__vc", "TEXT");
        }
        if (j.a(sQLiteDatabase, g.a.f5264a, "__av")) {
            return;
        }
        j.a(sQLiteDatabase, g.a.f5264a, "__av", "TEXT");
        j.a(sQLiteDatabase, g.a.f5264a, "__vc", "TEXT");
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase, g.d.f5279a);
        a(sQLiteDatabase, g.b.f5269a);
        a(sQLiteDatabase, g.a.f5264a);
        a();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            try {
                sQLiteDatabase.beginTransaction();
                c(sQLiteDatabase);
                d(sQLiteDatabase);
                b(sQLiteDatabase);
                a(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (SQLiteDatabaseCorruptException unused) {
                j.a(b);
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Throwable unused2) {
                if (sQLiteDatabase == null) {
                    return;
                }
            }
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable unused3) {
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable unused4) {
                }
            }
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 <= i || i != 1) {
            return;
        }
        try {
            try {
                e(sQLiteDatabase);
            } catch (Exception unused) {
                e(sQLiteDatabase);
            }
        } catch (Exception unused2) {
            f(sQLiteDatabase);
        }
    }

    public h(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new e(context, str), str2, cursorFactory, i);
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f5284a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __av TEXT, __vc TEXT)");
        } catch (SQLException unused) {
        }
    }

    public h(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, TextUtils.isEmpty(str) ? g.b : str, cursorFactory, i);
        this.f5284a = null;
        a();
    }

    public void a() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!j.a(g.d.f5279a, writableDatabase)) {
                c(writableDatabase);
            }
            if (!j.a(g.c.f5274a, writableDatabase)) {
                d(writableDatabase);
            }
            if (!j.a(g.b.f5269a, writableDatabase)) {
                b(writableDatabase);
            }
            if (j.a(g.a.f5264a, writableDatabase)) {
                return;
            }
            a(writableDatabase);
        } catch (Exception unused) {
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f5284a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER, __av TEXT, __vc TEXT)");
        } catch (SQLException unused) {
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        } catch (SQLException unused) {
        }
    }
}
