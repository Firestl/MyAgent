package com.getui.gtc.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static C0032a f2119a;

    /* JADX INFO: renamed from: com.getui.gtc.b.a$a, reason: collision with other inner class name */
    public static class C0032a extends SQLiteOpenHelper {
        public C0032a(Context context) {
            super(context, "gtc.db", (SQLiteDatabase.CursorFactory) null, 5);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0086: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:42:0x0086 */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0081 A[PHI: r0
  0x0081: PHI (r0v9 android.database.sqlite.SQLiteDatabase) = (r0v8 android.database.sqlite.SQLiteDatabase), (r0v11 android.database.sqlite.SQLiteDatabase) binds: [B:38:0x007f, B:25:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r10) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "gtc.db"
            java.io.File r0 = r10.getDatabasePath(r0)
            boolean r0 = r0.exists()
            r1 = 0
            if (r0 != 0) goto Le
            return r1
        Le:
            com.getui.gtc.b.a$a r0 = com.getui.gtc.b.a.f2119a
            if (r0 != 0) goto L19
            com.getui.gtc.b.a$a r0 = new com.getui.gtc.b.a$a
            r0.<init>(r10)
            com.getui.gtc.b.a.f2119a = r0
        L19:
            com.getui.gtc.b.a$a r0 = com.getui.gtc.b.a.f2119a     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L74
            android.database.sqlite.SQLiteDatabase r0 = r0.getReadableDatabase()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L74
            java.lang.String r3 = "i"
            java.lang.String r2 = "b"
            java.lang.String[] r4 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r5 = "a=?"
            java.lang.String r2 = "100"
            java.lang.String[] r6 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r2 == 0) goto L64
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            if (r3 == 0) goto L64
            r3 = 0
            byte[] r3 = r2.getBlob(r3)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            if (r3 == 0) goto L64
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            java.lang.String r10 = r10.getPackageName()     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            java.lang.String r10 = com.getui.gtc.i.a.a.a(r10)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            byte[] r10 = com.getui.gtc.i.a.b.a(r3, r10)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            r4.<init>(r10)     // Catch: java.lang.Exception -> L62 java.lang.Throwable -> L85
            if (r2 == 0) goto L5c
            r2.close()
        L5c:
            if (r0 == 0) goto L61
            r0.close()
        L61:
            return r4
        L62:
            r10 = move-exception
            goto L77
        L64:
            if (r2 == 0) goto L69
            r2.close()
        L69:
            if (r0 == 0) goto L84
            goto L81
        L6c:
            r10 = move-exception
            goto L87
        L6e:
            r10 = move-exception
            r2 = r1
            goto L77
        L71:
            r10 = move-exception
            r0 = r1
            goto L87
        L74:
            r10 = move-exception
            r0 = r1
            r2 = r0
        L77:
            com.getui.gtc.i.c.a.a(r10)     // Catch: java.lang.Throwable -> L85
            if (r2 == 0) goto L7f
            r2.close()
        L7f:
            if (r0 == 0) goto L84
        L81:
            r0.close()
        L84:
            return r1
        L85:
            r10 = move-exception
            r1 = r2
        L87:
            if (r1 == 0) goto L8c
            r1.close()
        L8c:
            if (r0 == 0) goto L91
            r0.close()
        L91:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.b.a.a(android.content.Context):java.lang.String");
    }
}
