package supwisdom;

import android.content.BroadcastReceiver;

/* JADX INFO: loaded from: classes3.dex */
public final class hw1 extends BroadcastReceiver {
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L86
            if (r6 != 0) goto L6
            goto L86
        L6:
            r0 = 0
            java.lang.String r1 = "openIdNotifyFlag"
            int r1 = r6.getIntExtra(r1, r0)
            java.lang.String r2 = java.lang.String.valueOf(r1)
            java.lang.String r3 = "shouldUpdateId, notifyFlag : "
            java.lang.String r2 = r3.concat(r2)
            supwisdom.iw1.a(r2)
            r2 = 1
            if (r1 != r2) goto L2f
            java.lang.String r1 = "openIdPackage"
            java.lang.String r1 = r6.getStringExtra(r1)
            java.lang.String r5 = r5.getPackageName()
            boolean r5 = android.text.TextUtils.equals(r1, r5)
            if (r5 == 0) goto L46
        L2d:
            r0 = 1
            goto L46
        L2f:
            r3 = 2
            if (r1 != r3) goto L43
            java.lang.String r1 = "openIdPackageList"
            java.util.ArrayList r1 = r6.getStringArrayListExtra(r1)
            if (r1 == 0) goto L46
            java.lang.String r5 = r5.getPackageName()
            boolean r0 = r1.contains(r5)
            goto L46
        L43:
            if (r1 != 0) goto L46
            goto L2d
        L46:
            if (r0 != 0) goto L49
            return
        L49:
            java.lang.String r5 = "openIdType"
            java.lang.String r5 = r6.getStringExtra(r5)
            supwisdom.iw1 r6 = supwisdom.iw1.a()
            java.lang.String r0 = "oaid"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L5e
            supwisdom.dw1 r5 = r6.b
            goto L80
        L5e:
            java.lang.String r0 = "vaid"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L69
            supwisdom.dw1 r5 = r6.d
            goto L80
        L69:
            java.lang.String r0 = "aaid"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L74
            supwisdom.dw1 r5 = r6.c
            goto L80
        L74:
            java.lang.String r0 = "udid"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L7f
            supwisdom.dw1 r5 = r6.f7994a
            goto L80
        L7f:
            r5 = 0
        L80:
            if (r5 != 0) goto L83
            return
        L83:
            r5.b()
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.hw1.onReceive(android.content.Context, android.content.Intent):void");
    }
}
