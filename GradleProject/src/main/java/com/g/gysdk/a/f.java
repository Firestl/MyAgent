package com.g.gysdk.a;

import android.content.ContentValues;
import android.database.Cursor;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.db.AbstractTable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends AbstractTable {
    private synchronized void a() {
        try {
            Cursor cursorQuery = getReadableDatabase().query(getTableName(), new String[]{"COUNT(*)"}, null, null, null, null, null);
            cursorQuery.moveToNext();
            long j = cursorQuery.getLong(0);
            cursorQuery.close();
            long jB = j - j.b();
            if (jB > 0) {
                getWritableDatabase().delete(getTableName(), "a IN(SELECT a FROM " + getTableName() + " ORDER BY c ASC LIMIT " + jB + ")", null);
                ak.a("delete old expired data");
            }
        } finally {
        }
    }

    public long a(a aVar) {
        try {
            a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("b", String.valueOf(aVar.c()));
            contentValues.put("c", String.valueOf(aVar.b()));
            contentValues.put(cn.com.chinatelecom.account.api.a.d.f1473a, SecureCryptTools.getInstance().encrypt(aVar.d().getBytes()));
            return insert(contentValues);
        } catch (Throwable th) {
            ak.e("insert data error", th);
            return -1L;
        }
    }

    public List<a> a(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = getReadableDatabase().query(getTableName(), null, null, null, null, null, "c", String.valueOf(i));
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                a aVar = new a();
                try {
                    aVar.a(cursorQuery.getInt(cursorQuery.getColumnIndex("a")));
                    aVar.a(Integer.parseInt(cursorQuery.getString(cursorQuery.getColumnIndex("b"))));
                    aVar.b(Long.parseLong(cursorQuery.getString(cursorQuery.getColumnIndex("c"))));
                    aVar.a(new String(SecureCryptTools.getInstance().decrypt(cursorQuery.getBlob(cursorQuery.getColumnIndex(cn.com.chinatelecom.account.api.a.d.f1473a)))));
                    arrayList.add(aVar);
                } catch (Throwable th) {
                    ak.e("invalid data: id = " + aVar.a() + ", type = " + aVar.c(), th);
                    a(aVar.a());
                }
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    public boolean a(long j) {
        return delete("a=?", new String[]{String.valueOf(j)}) > 0;
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS b (a INTEGER PRIMARY KEY AUTOINCREMENT, b TEXT, c TEXT, d BLOB)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "b";
    }
}
