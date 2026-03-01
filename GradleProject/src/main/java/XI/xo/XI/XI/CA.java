package XI.xo.XI.XI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import com.umeng.commonsdk.statistics.idtracking.i;

/* JADX INFO: loaded from: classes.dex */
public class CA {
    public static volatile CA J9;
    public BroadcastReceiver vs;

    /* JADX INFO: renamed from: XI, reason: collision with root package name */
    public XI f1064XI = new XI("udid");
    public XI K0 = new XI(i.d);
    public XI xo = new XI("vaid");
    public XI kM = new XI("aaid");
    public K0 CA = new K0();

    public static final CA XI() {
        if (J9 == null) {
            synchronized (CA.class) {
                if (J9 == null) {
                    J9 = new CA();
                }
            }
        }
        return J9;
    }

    public static kM XI(Cursor cursor) {
        kM kMVar = new kM(null, 0);
        if (cursor.isClosed()) {
            return kMVar;
        }
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("value");
        if (columnIndex >= 0) {
            kMVar.f1067XI = cursor.getString(columnIndex);
        }
        int columnIndex2 = cursor.getColumnIndex("code");
        if (columnIndex2 >= 0) {
            kMVar.K0 = cursor.getInt(columnIndex2);
        }
        int columnIndex3 = cursor.getColumnIndex("expired");
        if (columnIndex3 >= 0) {
            kMVar.kM = cursor.getLong(columnIndex3);
        }
        return kMVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cd A[PHI: r0 r1
  0x00cd: PHI (r0v7 ??) = (r0v5 ??), (r0v11 ??) binds: [B:40:0x00cb, B:28:0x00a7] A[DONT_GENERATE, DONT_INLINE]
  0x00cd: PHI (r1v8 android.database.Cursor) = (r1v7 android.database.Cursor), (r1v10 android.database.Cursor) binds: [B:40:0x00cb, B:28:0x00a7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String XI(android.content.Context r14, XI.xo.XI.XI.XI r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: XI.xo.XI.XI.CA.XI(android.content.Context, XI.xo.XI.XI.XI):java.lang.String");
    }

    public final synchronized void XI(Context context) {
        if (this.vs != null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.meizu.flyme.openid.ACTION_OPEN_ID_CHANGE");
        xo xoVar = new xo();
        this.vs = xoVar;
        context.registerReceiver(xoVar, intentFilter, "com.meizu.flyme.openid.permission.OPEN_ID_CHANGE", null);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x00ea A[PHI: r1
  0x00ea: PHI (r1v5 android.database.Cursor) = (r1v3 android.database.Cursor), (r1v6 android.database.Cursor) binds: [B:64:0x00e8, B:58:0x00ce] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean XI(android.content.Context r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: XI.xo.XI.XI.CA.XI(android.content.Context, boolean):boolean");
    }
}
