package dc.squareup.okhttp3;

import com.huawei.secure.android.common.ssl.SSLUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public enum TlsVersion {
    TLS_1_3(SSLUtil.b),
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
    /* JADX WARN: Removed duplicated region for block: B:26:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static dc.squareup.okhttp3.TlsVersion forJavaName(java.lang.String r6) {
        /*
            r6.hashCode()
            int r0 = r6.hashCode()
            r1 = 79201641(0x4b88569, float:4.338071E-36)
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r0 == r1) goto L45
            r1 = 79923350(0x4c38896, float:4.5969714E-36)
            if (r0 == r1) goto L3a
            switch(r0) {
                case -503070503: goto L2f;
                case -503070502: goto L24;
                case -503070501: goto L19;
                default: goto L18;
            }
        L18:
            goto L4d
        L19:
            java.lang.String r0 = "TLSv1.3"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L22
            goto L4d
        L22:
            r0 = 2
            goto L50
        L24:
            java.lang.String r0 = "TLSv1.2"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L2d
            goto L4d
        L2d:
            r0 = 1
            goto L50
        L2f:
            java.lang.String r0 = "TLSv1.1"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L38
            goto L4d
        L38:
            r0 = 0
            goto L50
        L3a:
            java.lang.String r0 = "TLSv1"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L43
            goto L4d
        L43:
            r0 = 4
            goto L50
        L45:
            java.lang.String r0 = "SSLv3"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L4f
        L4d:
            r0 = -1
            goto L50
        L4f:
            r0 = 3
        L50:
            if (r0 == 0) goto L7d
            if (r0 == r5) goto L7a
            if (r0 == r4) goto L77
            if (r0 == r3) goto L74
            if (r0 != r2) goto L5d
            dc.squareup.okhttp3.TlsVersion r6 = dc.squareup.okhttp3.TlsVersion.TLS_1_0
            return r6
        L5d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected TLS version: "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        L74:
            dc.squareup.okhttp3.TlsVersion r6 = dc.squareup.okhttp3.TlsVersion.SSL_3_0
            return r6
        L77:
            dc.squareup.okhttp3.TlsVersion r6 = dc.squareup.okhttp3.TlsVersion.TLS_1_3
            return r6
        L7a:
            dc.squareup.okhttp3.TlsVersion r6 = dc.squareup.okhttp3.TlsVersion.TLS_1_2
            return r6
        L7d:
            dc.squareup.okhttp3.TlsVersion r6 = dc.squareup.okhttp3.TlsVersion.TLS_1_1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: dc.squareup.okhttp3.TlsVersion.forJavaName(java.lang.String):dc.squareup.okhttp3.TlsVersion");
    }

    public static List<TlsVersion> forJavaNames(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(forJavaName(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String javaName() {
        return this.javaName;
    }
}
