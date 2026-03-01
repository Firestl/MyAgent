package com.squareup.okhttp;

import com.huawei.secure.android.common.ssl.SSLUtil;

/* JADX INFO: loaded from: classes2.dex */
public enum TlsVersion {
    TLS_1_2(SSLUtil.c),
    TLS_1_1("TLSv1.1"),
    TLS_1_0(SSLUtil.f2970e),
    SSL_3_0("SSLv3");

    public final String javaName;

    TlsVersion(String str) {
        this.javaName = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.squareup.okhttp.TlsVersion forJavaName(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -503070503: goto L29;
                case -503070502: goto L1f;
                case 79201641: goto L15;
                case 79923350: goto Lb;
                default: goto La;
            }
        La:
            goto L33
        Lb:
            java.lang.String r0 = "TLSv1"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L33
            r0 = 2
            goto L34
        L15:
            java.lang.String r0 = "SSLv3"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L33
            r0 = 3
            goto L34
        L1f:
            java.lang.String r0 = "TLSv1.2"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L33
            r0 = 0
            goto L34
        L29:
            java.lang.String r0 = "TLSv1.1"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L33
            r0 = 1
            goto L34
        L33:
            r0 = -1
        L34:
            if (r0 == 0) goto L5c
            if (r0 == r3) goto L59
            if (r0 == r2) goto L56
            if (r0 != r1) goto L3f
            com.squareup.okhttp.TlsVersion r4 = com.squareup.okhttp.TlsVersion.SSL_3_0
            return r4
        L3f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected TLS version: "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L56:
            com.squareup.okhttp.TlsVersion r4 = com.squareup.okhttp.TlsVersion.TLS_1_0
            return r4
        L59:
            com.squareup.okhttp.TlsVersion r4 = com.squareup.okhttp.TlsVersion.TLS_1_1
            return r4
        L5c:
            com.squareup.okhttp.TlsVersion r4 = com.squareup.okhttp.TlsVersion.TLS_1_2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.TlsVersion.forJavaName(java.lang.String):com.squareup.okhttp.TlsVersion");
    }
}
