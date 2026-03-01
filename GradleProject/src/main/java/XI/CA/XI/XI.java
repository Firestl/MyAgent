package XI.CA.XI;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class XI {

    /* JADX INFO: renamed from: XI, reason: collision with root package name */
    public Context f1049XI;

    public XI(Context context) {
        this.f1049XI = context;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(3:4|(2:6|(2:8|(7:10|39|17|(4:41|19|(1:21)|22)|(1:43)|36|44)(1:11))(1:12))(1:13)|14)(1:15)|16|39|17|(0)|(0)|36|44|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0061, code lost:
    
        r10 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String XI(int r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L2b
            r1 = 1
            if (r9 == r1) goto L19
            r1 = 2
            if (r9 == r1) goto L11
            r10 = 4
            if (r9 == r10) goto Le
            r2 = r0
            goto L32
        Le:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS"
            goto L2d
        L11:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            goto L20
        L19:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
        L20:
            r9.append(r1)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto L2d
        L2b:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
        L2d:
            android.net.Uri r9 = android.net.Uri.parse(r9)
            r2 = r9
        L32:
            android.content.Context r9 = r8.f1049XI     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L68
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L68
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L68
            if (r9 == 0) goto L5e
            boolean r10 = r9.moveToNext()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            if (r10 == 0) goto L53
            java.lang.String r10 = "value"
            int r10 = r9.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            r0 = r10
        L53:
            r9.close()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            goto L5e
        L57:
            r10 = move-exception
            r0 = r9
            goto L62
        L5a:
            r7 = r0
            r0 = r9
            r9 = r7
            goto L69
        L5e:
            if (r9 == 0) goto L73
            goto L6e
        L61:
            r10 = move-exception
        L62:
            if (r0 == 0) goto L67
            r0.close()
        L67:
            throw r10
        L68:
            r9 = r0
        L69:
            if (r0 == 0) goto L72
            r7 = r0
            r0 = r9
            r9 = r7
        L6e:
            r9.close()
            goto L73
        L72:
            r0 = r9
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: XI.CA.XI.XI.XI(int, java.lang.String):java.lang.String");
    }
}
