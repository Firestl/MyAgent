package com.g.gysdk.a;

import android.content.ContentValues;
import android.util.SparseArray;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.db.AbstractTable;

/* JADX INFO: loaded from: classes.dex */
public class h extends AbstractTable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SparseArray<String> f2006a = new SparseArray<>();

    /* JADX WARN: Removed duplicated region for block: B:16:0x004b A[DONT_GENERATE, PHI: r0
  0x004b: PHI (r0v2 android.database.Cursor) = (r0v1 android.database.Cursor), (r0v4 android.database.Cursor) binds: [B:15:0x0049, B:11:0x0040] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(int r7) {
        /*
            r6 = this;
            r0 = 0
            android.util.SparseArray<java.lang.String> r1 = r6.f2006a     // Catch: java.lang.Throwable -> L43
            java.lang.Object r1 = r1.get(r7)     // Catch: java.lang.Throwable -> L43
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto Lc
            return r1
        Lc:
            java.lang.String r1 = "b"
            java.lang.String[] r1 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L43
            java.lang.String r2 = "a=?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L43
            java.lang.String r4 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L43
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L43
            android.database.Cursor r0 = r6.query(r1, r2, r3)     // Catch: java.lang.Throwable -> L43
            if (r0 == 0) goto L40
            boolean r1 = r0.moveToNext()     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto L40
            com.getui.gtc.base.crypt.SecureCryptTools r1 = com.getui.gtc.base.crypt.SecureCryptTools.getInstance()     // Catch: java.lang.Throwable -> L43
            byte[] r2 = r0.getBlob(r5)     // Catch: java.lang.Throwable -> L43
            byte[] r1 = r1.decrypt(r2)     // Catch: java.lang.Throwable -> L43
            android.util.SparseArray<java.lang.String> r2 = r6.f2006a     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Throwable -> L43
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L43
            r2.put(r7, r3)     // Catch: java.lang.Throwable -> L43
        L40:
            if (r0 == 0) goto L4e
            goto L4b
        L43:
            r1 = move-exception
            java.lang.String r2 = "db get failed"
            com.g.gysdk.a.ak.c(r2, r1)     // Catch: java.lang.Throwable -> L59
            if (r0 == 0) goto L4e
        L4b:
            r0.close()
        L4e:
            android.util.SparseArray<java.lang.String> r0 = r6.f2006a
            java.lang.String r1 = ""
            java.lang.Object r7 = r0.get(r7, r1)
            java.lang.String r7 = (java.lang.String) r7
            return r7
        L59:
            r7 = move-exception
            if (r0 == 0) goto L5f
            r0.close()
        L5f:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.g.gysdk.a.h.a(int):java.lang.String");
    }

    public boolean a(int i, String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("a", Integer.valueOf(i));
            contentValues.put("b", SecureCryptTools.getInstance().encrypt(str.getBytes()));
            boolean z = replace(null, contentValues) != -1;
            if (z) {
                this.f2006a.put(i, str);
            }
            return z;
        } catch (Throwable th) {
            ak.e("db set failed", th);
            return false;
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS r (a INTEGER PRIMARY KEY, b BLOB)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "r";
    }
}
