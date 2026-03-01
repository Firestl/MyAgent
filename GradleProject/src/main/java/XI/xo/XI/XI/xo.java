package XI.xo.XI.XI;

import android.content.BroadcastReceiver;

/* JADX INFO: loaded from: classes.dex */
public class xo extends BroadcastReceiver {
    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L8c
            if (r6 != 0) goto L6
            goto L8c
        L6:
            r0 = 0
            java.lang.String r1 = "openIdNotifyFlag"
            int r1 = r6.getIntExtra(r1, r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "shouldUpdateId, notifyFlag : "
            r2.append(r3)
            r2.append(r1)
            r2.toString()
            r2 = 1
            if (r1 != r2) goto L31
            java.lang.String r1 = "openIdPackage"
            java.lang.String r1 = r6.getStringExtra(r1)
            java.lang.String r5 = r5.getPackageName()
            boolean r5 = android.text.TextUtils.equals(r1, r5)
            if (r5 == 0) goto L48
            goto L47
        L31:
            r3 = 2
            if (r1 != r3) goto L45
            java.lang.String r1 = "openIdPackageList"
            java.util.ArrayList r1 = r6.getStringArrayListExtra(r1)
            if (r1 == 0) goto L48
            java.lang.String r5 = r5.getPackageName()
            boolean r0 = r1.contains(r5)
            goto L48
        L45:
            if (r1 != 0) goto L48
        L47:
            r0 = 1
        L48:
            if (r0 != 0) goto L4b
            return
        L4b:
            java.lang.String r5 = "openIdType"
            java.lang.String r5 = r6.getStringExtra(r5)
            XI.xo.XI.XI.CA r6 = XI.xo.XI.XI.CA.XI()
            r6.getClass()
            java.lang.String r0 = "oaid"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L63
            XI.xo.XI.XI.XI r5 = r6.K0
            goto L85
        L63:
            java.lang.String r0 = "vaid"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L6e
            XI.xo.XI.XI.XI r5 = r6.xo
            goto L85
        L6e:
            java.lang.String r0 = "aaid"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L79
            XI.xo.XI.XI.XI r5 = r6.kM
            goto L85
        L79:
            java.lang.String r0 = "udid"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L84
            XI.xo.XI.XI.XI r5 = r6.f1064XI
            goto L85
        L84:
            r5 = 0
        L85:
            if (r5 != 0) goto L88
            return
        L88:
            r0 = 0
            r5.f1066XI = r0
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: XI.xo.XI.XI.xo.onReceive(android.content.Context, android.content.Intent):void");
    }
}
