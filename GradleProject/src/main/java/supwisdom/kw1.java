package supwisdom;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: compiled from: DataBaseOperation.java */
/* JADX INFO: loaded from: classes3.dex */
public class kw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8216a;

    public kw1(Context context) {
        this.f8216a = context;
    }

    public boolean a(int i, String str, ContentValues[] contentValuesArr) {
        Uri uri;
        int iBulkInsert;
        if (i == 6) {
            uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAIDBLACK_" + str);
        } else if (i != 7) {
            uri = null;
        } else {
            uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/STATISTICS_" + str);
        }
        if (uri == null) {
            return false;
        }
        try {
            iBulkInsert = this.f8216a.getContentResolver().bulkInsert(uri, contentValuesArr);
            Log.d("VMS_SDK_DB", "insert:" + iBulkInsert);
        } catch (Exception unused) {
            Log.e("VMS_SDK_DB", "return insert is error");
        }
        return iBulkInsert != 0;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(2:4|(2:6|(2:8|(2:10|(2:12|(5:14|47|(1:23)(6:49|24|45|(2:26|(1:28))(1:29)|(1:31)|38)|(1:42)|43)(1:15))(1:16))(1:17))(1:18))(1:19))(1:20)|21|47|(0)(0)|(0)|43|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a9, code lost:
    
        r10 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00aa, code lost:
    
        r1 = r9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v10, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(int r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.String r0 = "VMS_SDK_DB"
            r1 = 0
            if (r9 == 0) goto L66
            r2 = 1
            if (r9 == r2) goto L50
            r2 = 2
            if (r9 == r2) goto L3a
            r2 = 3
            if (r9 == r2) goto L33
            r2 = 4
            if (r9 == r2) goto L1d
            r10 = 5
            if (r9 == r10) goto L16
            r3 = r1
            goto L6d
        L16:
            java.lang.String r9 = "content://com.vivo.abe.exidentifier/guid"
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L6c
        L1d:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS_"
            r9.append(r2)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L6c
        L33:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/UDID"
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L6c
        L3a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            r9.append(r2)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L6c
        L50:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
            r9.append(r2)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.net.Uri r9 = android.net.Uri.parse(r9)
            goto L6c
        L66:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
            android.net.Uri r9 = android.net.Uri.parse(r9)
        L6c:
            r3 = r9
        L6d:
            if (r3 != 0) goto L70
            return r1
        L70:
            android.content.Context r9 = r8.f8216a     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> L9f
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> L9f
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> L9f
            if (r9 == 0) goto L92
            boolean r10 = r9.moveToNext()     // Catch: java.lang.Exception -> La0 java.lang.Throwable -> La9
            if (r10 == 0) goto L97
            java.lang.String r10 = "value"
            int r10 = r9.getColumnIndex(r10)     // Catch: java.lang.Exception -> La0 java.lang.Throwable -> La9
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Exception -> La0 java.lang.Throwable -> La9
            r1 = r10
            goto L97
        L92:
            java.lang.String r10 = "return cursor is null,return"
            android.util.Log.d(r0, r10)     // Catch: java.lang.Exception -> La0 java.lang.Throwable -> La9
        L97:
            if (r9 == 0) goto La8
        L99:
            r9.close()
            goto La8
        L9d:
            r10 = move-exception
            goto Lab
        L9f:
            r9 = r1
        La0:
            java.lang.String r10 = "return cursor is error"
            android.util.Log.e(r0, r10)     // Catch: java.lang.Throwable -> La9
            if (r9 == 0) goto La8
            goto L99
        La8:
            return r1
        La9:
            r10 = move-exception
            r1 = r9
        Lab:
            if (r1 == 0) goto Lb0
            r1.close()
        Lb0:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.kw1.a(int, java.lang.String):java.lang.String");
    }
}
