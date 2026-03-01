package supwisdom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: compiled from: DBUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class xv0 {
    public static boolean a(SQLiteDatabase sQLiteDatabase, yv0 yv0Var) {
        if (!a(sQLiteDatabase, yv0Var.f9921a)) {
            return true;
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + yv0Var.f9921a, null);
        if (cursorRawQuery == null) {
            return false;
        }
        try {
            int iB = yv0Var.b();
            if (iB != cursorRawQuery.getColumnCount()) {
                return true;
            }
            for (int i = 0; i < iB; i++) {
                if (yv0Var.a(cursorRawQuery.getColumnName(i)) == -1) {
                    return true;
                }
            }
            return false;
        } finally {
            cursorRawQuery.close();
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        int i;
        if (str == null || sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return false;
        }
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", str});
            } catch (Exception e2) {
                fw0.a(e2);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                i = 0;
            }
            if (!cursorRawQuery.moveToFirst()) {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return false;
            }
            i = cursorRawQuery.getInt(0);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return i > 0;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }
}
