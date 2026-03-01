package com.getui.gtc.e;

import android.content.ContentValues;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.getui.gtc.base.db.AbstractTable;

/* JADX INFO: loaded from: classes.dex */
public class a extends AbstractTable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SparseArray<Long> f2211a = new SparseArray<>();
    public SparseArray<Long> b = new SparseArray<>();
    public SparseIntArray c = new SparseIntArray();

    public final long a(int i) {
        Long l = this.f2211a.get(i);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public final void a(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ei", Integer.valueOf(i));
        contentValues.put("elt", String.valueOf(j));
        if (replace(null, contentValues) != -1) {
            this.f2211a.put(i, Long.valueOf(j));
        }
    }

    public final void a(int i, long j, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ei", Integer.valueOf(i));
        contentValues.put("est", String.valueOf(j));
        contentValues.put("esn", Integer.valueOf(i2));
        if (replace(null, contentValues) != -1) {
            this.b.put(i, Long.valueOf(j));
            this.c.put(i, i2);
        }
    }

    public final long b(int i) {
        Long l = this.b.get(i);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public final int c(int i) {
        return this.c.get(i);
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS e (ei INTEGER PRIMARY KEY, elt TEXT, est TEXT, esn INTEGER)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "e";
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0072  */
    @Override // com.getui.gtc.base.db.AbstractTable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void initCache() {
        /*
            r9 = this;
            java.lang.String r0 = "esn"
            java.lang.String r1 = "est"
            java.lang.String r2 = "elt"
            java.lang.String r3 = "ei"
            r4 = 0
            java.lang.String[] r5 = new java.lang.String[]{r3, r2, r1, r0}     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            android.database.Cursor r4 = r9.query(r5, r4, r4)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            if (r4 != 0) goto L19
            if (r4 == 0) goto L18
            r4.close()
        L18:
            return
        L19:
            boolean r5 = r4.moveToNext()     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            if (r5 == 0) goto L5f
            int r5 = r4.getColumnIndex(r3)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            int r5 = r4.getInt(r5)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            int r6 = r4.getColumnIndex(r2)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6f
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6f
            long r6 = java.lang.Long.parseLong(r6)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6f
            android.util.SparseArray<java.lang.Long> r8 = r9.f2211a     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6f
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6f
            r8.put(r5, r6)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L6f
        L3c:
            int r6 = r4.getColumnIndex(r1)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L6f
            java.lang.String r6 = r4.getString(r6)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L6f
            long r6 = java.lang.Long.parseLong(r6)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L6f
            android.util.SparseArray<java.lang.Long> r8 = r9.b     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L6f
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L6f
            r8.put(r5, r6)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L6f
        L51:
            int r6 = r4.getColumnIndex(r0)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            int r6 = r4.getInt(r6)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            android.util.SparseIntArray r7 = r9.c     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            r7.put(r5, r6)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L6f
            goto L19
        L5f:
            if (r4 == 0) goto L6e
            r4.close()
            return
        L65:
            r0 = move-exception
            com.getui.gtc.i.c.a.a(r0)     // Catch: java.lang.Throwable -> L6f
            if (r4 == 0) goto L6e
            r4.close()
        L6e:
            return
        L6f:
            r0 = move-exception
            if (r4 == 0) goto L75
            r4.close()
        L75:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.e.a.initCache():void");
    }
}
