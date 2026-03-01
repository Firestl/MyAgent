package com.loc;

import java.net.URLConnection;

/* JADX INFO: compiled from: BaseNetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3648a = 0;
    public static String b = "";
    public static aq c;

    /* JADX INFO: compiled from: BaseNetManager.java */
    public interface a {
        URLConnection a();
    }

    public static aq a() {
        if (c == null) {
            c = new aq();
        }
        return c;
    }

    public static aw a(av avVar, boolean z) throws j {
        aw awVarA;
        byte[] bArr;
        int iA = at.a(avVar);
        try {
            awVarA = a(avVar, z, iA);
        } catch (j e2) {
            if (!at.a(iA)) {
                throw e2;
            }
            awVarA = null;
        }
        if ((awVarA != null && (bArr = awVarA.f3662a) != null && bArr.length > 0) || !at.a(iA)) {
            return awVarA;
        }
        try {
            return a(avVar, z, 3);
        } catch (j e3) {
            throw e3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.loc.aw a(com.loc.av r9, boolean r10, int r11) throws com.loc.j {
        /*
            if (r9 == 0) goto L5d
            java.lang.String r0 = r9.c()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            if (r0 == 0) goto L51
            java.lang.String r0 = ""
            java.lang.String r1 = r9.c()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            boolean r0 = r0.equals(r1)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            if (r0 != 0) goto L51
            com.loc.at r1 = new com.loc.at     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            r1.<init>(r9, r10)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.lang.String r2 = r9.m()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.lang.String r3 = r9.n()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            boolean r4 = r9.o()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.lang.String r5 = r9.h()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.util.Map r6 = r9.b()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            byte[] r10 = r9.e()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            if (r10 == 0) goto L36
            int r0 = r10.length     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            if (r0 != 0) goto L4a
        L36:
            java.util.Map r9 = r9.b_()     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.lang.String r9 = com.loc.at.a(r9)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            if (r0 != 0) goto L4a
            byte[] r9 = com.loc.u.a(r9)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            r7 = r9
            goto L4b
        L4a:
            r7 = r10
        L4b:
            r8 = r11
            com.loc.aw r9 = r1.a(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            return r9
        L51:
            com.loc.j r9 = new com.loc.j     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.lang.String r10 = "request url is empty"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            throw r9     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
        L59:
            r9 = move-exception
            goto L65
        L5b:
            r9 = move-exception
            goto L71
        L5d:
            com.loc.j r9 = new com.loc.j     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            java.lang.String r10 = "requeust is null"
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
            throw r9     // Catch: java.lang.Throwable -> L59 com.loc.j -> L5b
        L65:
            r9.printStackTrace()
            com.loc.j r9 = new com.loc.j
            java.lang.String r10 = "未知的错误"
            r9.<init>(r10)
            throw r9
        L71:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.aq.a(com.loc.av, boolean, int):com.loc.aw");
    }

    public static byte[] a(av avVar) throws j {
        try {
            aw awVarA = a(avVar, true);
            if (awVarA != null) {
                return awVarA.f3662a;
            }
            return null;
        } catch (j e2) {
            throw e2;
        }
    }

    public static byte[] b(av avVar) throws j {
        try {
            aw awVarA = a(avVar, false);
            if (awVarA != null) {
                return awVarA.f3662a;
            }
            return null;
        } catch (j e2) {
            throw e2;
        } catch (Throwable th) {
            y.a(th, "bm", "msp");
            throw new j("未知的错误");
        }
    }

    public static aw c(av avVar) throws j {
        try {
            aw awVarA = a(avVar, false);
            if (awVarA != null) {
                return awVarA;
            }
            return null;
        } catch (j e2) {
            throw e2;
        } catch (Throwable th) {
            y.a(th, "bm", "mp");
            throw new j("未知的错误");
        }
    }
}
