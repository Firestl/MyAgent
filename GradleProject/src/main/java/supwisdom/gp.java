package supwisdom;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class gp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7754a;
    public String b = bq.a(24);

    public gp(boolean z) {
        this.f7754a = z;
    }

    public static byte[] b(String str, byte[] bArr, String str2) {
        return bp.b(str, bArr, str2);
    }

    public hp a(fp fpVar, boolean z, String str) {
        if (fpVar == null) {
            return null;
        }
        byte[] bytes = fpVar.a().getBytes();
        byte[] bytes2 = fpVar.b().getBytes();
        if (z) {
            try {
                bytes2 = yo.a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        return new hp(z, this.f7754a ? a(bytes, a(this.b, uo.b), a(this.b, bytes2, str)) : a(bytes, bytes2));
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.fp a(supwisdom.hp r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            byte[] r2 = r6.b()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L60
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            r1.read(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            r4.<init>(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            int r3 = a(r4)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            r1.read(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            r4.<init>(r3)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L77
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            r1.read(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            r3.<init>(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            int r2 = a(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            if (r2 <= 0) goto L52
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            r1.read(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            boolean r3 = r5.f7754a     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            if (r3 == 0) goto L42
            java.lang.String r3 = r5.b     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            byte[] r2 = b(r3, r2, r7)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
        L42:
            boolean r6 = r6.a()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            if (r6 == 0) goto L4c
            byte[] r2 = supwisdom.yo.b(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
        L4c:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            r6.<init>(r2)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L77
            goto L53
        L52:
            r6 = r0
        L53:
            r1.close()     // Catch: java.lang.Exception -> L57
            goto L6c
        L57:
            goto L6c
        L59:
            r6 = move-exception
            goto L63
        L5b:
            r6 = move-exception
            r4 = r0
            goto L63
        L5e:
            r6 = move-exception
            goto L79
        L60:
            r6 = move-exception
            r1 = r0
            r4 = r1
        L63:
            supwisdom.vp.a(r6)     // Catch: java.lang.Throwable -> L77
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.lang.Exception -> L6b
        L6b:
            r6 = r0
        L6c:
            if (r4 != 0) goto L71
            if (r6 != 0) goto L71
            return r0
        L71:
            supwisdom.fp r7 = new supwisdom.fp
            r7.<init>(r4, r6)
            return r7
        L77:
            r6 = move-exception
            r0 = r1
        L79:
            if (r0 == 0) goto L7e
            r0.close()     // Catch: java.lang.Exception -> L7e
        L7e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.gp.a(supwisdom.hp, java.lang.String):supwisdom.fp");
    }

    public static byte[] a(String str, String str2) {
        return ap.a(str, str2);
    }

    public static byte[] a(String str, byte[] bArr, String str2) {
        return bp.a(str, bArr, str2);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:60|7|62|8|(7:52|9|(1:11)|64|12|54|13)|56|14|33) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(byte[]... r7) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r7 == 0) goto L63
            int r1 = r7.length
            if (r1 != 0) goto L8
            goto L63
        L8:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L43
            r1.<init>()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L43
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3d
            int r3 = r7.length     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            r4 = 0
        L14:
            if (r4 >= r3) goto L2a
            r5 = r7[r4]     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            int r6 = r5.length     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            java.lang.String r6 = a(r6)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            byte[] r6 = r6.getBytes()     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            r2.write(r6)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            r2.write(r5)     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            int r4 = r4 + 1
            goto L14
        L2a:
            r2.flush()     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            byte[] r0 = r1.toByteArray()     // Catch: java.lang.Exception -> L38 java.lang.Throwable -> L54
            r1.close()     // Catch: java.lang.Exception -> L34
        L34:
            r2.close()     // Catch: java.lang.Exception -> L53
            goto L53
        L38:
            r7 = move-exception
            goto L46
        L3a:
            r7 = move-exception
            r2 = r0
            goto L55
        L3d:
            r7 = move-exception
            r2 = r0
            goto L46
        L40:
            r7 = move-exception
            r2 = r0
            goto L56
        L43:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L46:
            supwisdom.vp.a(r7)     // Catch: java.lang.Throwable -> L54
            if (r1 == 0) goto L50
            r1.close()     // Catch: java.lang.Exception -> L4f
            goto L50
        L4f:
        L50:
            if (r2 == 0) goto L53
            goto L34
        L53:
            return r0
        L54:
            r7 = move-exception
        L55:
            r0 = r1
        L56:
            if (r0 == 0) goto L5d
            r0.close()     // Catch: java.lang.Exception -> L5c
            goto L5d
        L5c:
        L5d:
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.lang.Exception -> L62
        L62:
            throw r7
        L63:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.gp.a(byte[][]):byte[]");
    }

    public static String a(int i) {
        return String.format(Locale.getDefault(), "%05d", Integer.valueOf(i));
    }

    public static int a(String str) {
        return Integer.parseInt(str);
    }
}
