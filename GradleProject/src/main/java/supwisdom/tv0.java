package supwisdom;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: BaseDao.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class tv0<T> {
    public static String d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Lock f9323a;
    public SQLiteOpenHelper b;
    public SQLiteDatabase c;

    public tv0(SQLiteOpenHelper sQLiteOpenHelper) {
        d = getClass().getSimpleName();
        this.f9323a = wv0.f9669e;
        this.b = sQLiteOpenHelper;
        this.c = b();
    }

    public abstract ContentValues a(T t);

    public abstract T a(Cursor cursor);

    public abstract String a();

    public final void a(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        sQLiteDatabase.close();
    }

    public SQLiteDatabase b() {
        return this.b.getWritableDatabase();
    }

    public boolean b(T t) {
        if (t == null) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f9323a.lock();
        try {
            this.c.beginTransaction();
            this.c.replace(a(), null, a(t));
            this.c.setTransactionSuccessful();
            return true;
        } catch (Exception e2) {
            fw0.a(e2);
            return false;
        } finally {
            this.c.endTransaction();
            this.f9323a.unlock();
            fw0.a(d, (System.currentTimeMillis() - jCurrentTimeMillis) + " replaceT");
        }
    }

    public boolean a(String str, String[] strArr) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f9323a.lock();
        try {
            try {
                this.c.beginTransaction();
                this.c.delete(a(), str, strArr);
                this.c.setTransactionSuccessful();
                return true;
            } catch (Exception e2) {
                fw0.a(e2);
                this.c.endTransaction();
                this.f9323a.unlock();
                fw0.a(d, (System.currentTimeMillis() - jCurrentTimeMillis) + " delete");
                return false;
            }
        } finally {
            this.c.endTransaction();
            this.f9323a.unlock();
            fw0.a(d, (System.currentTimeMillis() - jCurrentTimeMillis) + " delete");
        }
    }

    public List<T> b(String str, String[] strArr) {
        return a(null, str, strArr, null, null, null, null);
    }

    public List<T> a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) throws Throwable {
        Cursor cursorQuery;
        String str6;
        StringBuilder sb;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f9323a.lock();
        ArrayList arrayList = new ArrayList();
        try {
            this.c.beginTransaction();
            cursorQuery = this.c.query(a(), strArr, str, strArr2, str2, str3, str4, str5);
            while (!cursorQuery.isClosed() && cursorQuery.moveToNext()) {
                try {
                    try {
                        arrayList.add(a(cursorQuery));
                    } catch (Throwable th) {
                        th = th;
                        a((SQLiteDatabase) null, cursorQuery);
                        this.c.endTransaction();
                        this.f9323a.unlock();
                        fw0.a(d, (System.currentTimeMillis() - jCurrentTimeMillis) + " query");
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fw0.a(e);
                    a((SQLiteDatabase) null, cursorQuery);
                    this.c.endTransaction();
                    this.f9323a.unlock();
                    str6 = d;
                    sb = new StringBuilder();
                }
            }
            this.c.setTransactionSuccessful();
            a((SQLiteDatabase) null, cursorQuery);
            this.c.endTransaction();
            this.f9323a.unlock();
            str6 = d;
            sb = new StringBuilder();
        } catch (Exception e3) {
            e = e3;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
            a((SQLiteDatabase) null, cursorQuery);
            this.c.endTransaction();
            this.f9323a.unlock();
            fw0.a(d, (System.currentTimeMillis() - jCurrentTimeMillis) + " query");
            throw th;
        }
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        sb.append(" query");
        fw0.a(str6, sb.toString());
        return arrayList;
    }
}
