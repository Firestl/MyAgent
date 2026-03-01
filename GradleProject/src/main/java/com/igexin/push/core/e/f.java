package com.igexin.push.core.e;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.text.TextUtils;
import com.igexin.push.g.g;
import com.igexin.push.g.j;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class f implements a {
    public static final int A = 48;
    public static final int B = 49;
    public static final int C = 50;
    public static final int D = 51;
    public static final int E = 53;
    public static final int F = 54;
    public static final int G = 60;
    public static final int H = 61;
    public static final int I = 63;
    public static final int J = 64;
    public static final int K = 65;
    public static final int L = 66;
    public static final int M = 67;
    public static volatile f N = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3417a = "com.igexin.push.core.e.f";
    public static final int c = 1;
    public static final int d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3418e = 3;
    public static final int f = 4;
    public static final int g = 6;
    public static final int h = 8;
    public static final int i = 12;
    public static final int j = 13;
    public static final int k = 14;
    public static final int l = 15;
    public static final int m = 16;
    public static final int n = 17;
    public static final int o = 18;
    public static final int p = 20;
    public static final int q = 21;
    public static final int r = 22;
    public static final int s = 23;
    public static final int t = 25;
    public static final int u = 30;
    public static final int v = 31;
    public static final int w = 32;
    public static final int x = 40;
    public static final int y = 46;
    public static final int z = 47;
    public boolean b;

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$1, reason: invalid class name */
    public class AnonymousClass1 extends com.igexin.push.b.d {
        public AnonymousClass1() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Throwable {
            f.this.c(this.d);
            j.b();
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$12, reason: invalid class name */
    public class AnonymousClass12 extends com.igexin.push.b.d {
        public AnonymousClass12() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            f.a();
            f.b(this.d, 8, String.valueOf(com.igexin.push.core.e.R));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$13, reason: invalid class name */
    public class AnonymousClass13 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3423a;

        public AnonymousClass13(String str) {
            this.f3423a = str;
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            f.a();
            f.b(this.d, 3, this.f3423a);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$17, reason: invalid class name */
    public class AnonymousClass17 extends com.igexin.push.b.d {
        public AnonymousClass17() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            f.a();
            f.b(this.d, 67, String.valueOf(com.igexin.push.core.e.J));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$20, reason: invalid class name */
    public class AnonymousClass20 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3431a;
        public final /* synthetic */ String b;

        public AnonymousClass20(String str, String str2) {
            this.f3431a = str;
            this.b = str2;
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            if (!TextUtils.isEmpty(this.f3431a)) {
                f.a();
                f.b(this.d, 53, this.f3431a);
            }
            if (TextUtils.isEmpty(this.b)) {
                return;
            }
            f.a();
            f.b(this.d, 54, this.b);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$21, reason: invalid class name */
    public class AnonymousClass21 extends com.igexin.push.b.d {
        public AnonymousClass21() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            f.a();
            f.b(this.d, 60, String.valueOf(com.igexin.push.core.e.c));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$25, reason: invalid class name */
    public class AnonymousClass25 extends com.igexin.push.b.d {
        public AnonymousClass25() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            f.a();
            f.b(this.d, 64, String.valueOf(com.igexin.push.core.e.ax));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$27, reason: invalid class name */
    public class AnonymousClass27 extends com.igexin.push.b.d {
        public AnonymousClass27() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            f.a();
            f.a(this.d, 66, com.igexin.c.a.a.a.b(com.igexin.push.a.j.getBytes(), com.igexin.push.core.e.M));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$28, reason: invalid class name */
    public class AnonymousClass28 extends com.igexin.push.b.d {
        public AnonymousClass28() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            f.a();
            f.e(this.d);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$3, reason: invalid class name */
    public class AnonymousClass3 extends com.igexin.push.b.d {
        public AnonymousClass3() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            f.a();
            f.b(this.d, 13, com.igexin.push.core.e.V);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$31, reason: invalid class name */
    public class AnonymousClass31 extends com.igexin.push.b.d {
        public AnonymousClass31() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            f.a();
            f.b(this.d, 51, com.igexin.push.core.e.C);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.core.e.f$5, reason: invalid class name */
    public class AnonymousClass5 extends com.igexin.push.b.d {
        public AnonymousClass5() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            f.a();
            f.b(this.d, 16, String.valueOf(com.igexin.push.core.e.X));
        }
    }

    public static f a() {
        if (N == null) {
            synchronized (f.class) {
                if (N == null) {
                    N = new f();
                }
            }
        }
        return N;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, int i2, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace(com.igexin.push.core.b.Z, null, contentValues);
    }

    private boolean a(String str, String str2) {
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass20(str, str2), false, true);
    }

    private boolean a(String str, String str2, long j2) {
        com.igexin.push.core.e.z = j2;
        if (TextUtils.isEmpty(com.igexin.push.core.e.H)) {
            com.igexin.push.core.e.H = str2;
        }
        com.igexin.push.core.e.A = str;
        return c();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047 A[PHI: r10
  0x0047: PHI (r10v4 android.database.Cursor) = (r10v3 android.database.Cursor), (r10v5 android.database.Cursor) binds: [B:20:0x0045, B:13:0x003b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] a(android.database.sqlite.SQLiteDatabase r10, int r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r3 = "runtime"
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r2 = "id="
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            java.lang.String r5 = r2.concat(r11)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r10
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            if (r10 == 0) goto L3b
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L4b
            if (r11 == 0) goto L3b
            int r11 = r10.getColumnIndex(r0)     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L4b
            byte[] r11 = r10.getBlob(r11)     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L4b
            java.lang.String r0 = com.igexin.push.core.e.M     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L4b
            byte[] r11 = com.igexin.c.a.a.a.a(r11, r0)     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L4b
            if (r10 == 0) goto L38
            r10.close()
        L38:
            return r11
        L39:
            r11 = move-exception
            goto L42
        L3b:
            if (r10 == 0) goto L4a
            goto L47
        L3e:
            r11 = move-exception
            goto L4d
        L40:
            r11 = move-exception
            r10 = r1
        L42:
            com.igexin.c.a.c.a.a(r11)     // Catch: java.lang.Throwable -> L4b
            if (r10 == 0) goto L4a
        L47:
            r10.close()
        L4a:
            return r1
        L4b:
            r11 = move-exception
            r1 = r10
        L4d:
            if (r1 == 0) goto L52
            r1.close()
        L52:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.f.a(android.database.sqlite.SQLiteDatabase, int):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041 A[PHI: r10
  0x0041: PHI (r10v3 android.database.Cursor) = (r10v2 android.database.Cursor), (r10v4 android.database.Cursor) binds: [B:20:0x003f, B:13:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.database.sqlite.SQLiteDatabase r10, int r11) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r3 = "runtime"
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            java.lang.String r2 = "id="
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            java.lang.String r5 = r2.concat(r11)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r10
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            if (r10 == 0) goto L35
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L45
            if (r11 == 0) goto L35
            int r11 = r10.getColumnIndex(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L45
            java.lang.String r11 = r10.getString(r11)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L45
            if (r10 == 0) goto L32
            r10.close()
        L32:
            return r11
        L33:
            r11 = move-exception
            goto L3c
        L35:
            if (r10 == 0) goto L44
            goto L41
        L38:
            r11 = move-exception
            goto L47
        L3a:
            r11 = move-exception
            r10 = r1
        L3c:
            com.igexin.c.a.c.a.a(r11)     // Catch: java.lang.Throwable -> L45
            if (r10 == 0) goto L44
        L41:
            r10.close()
        L44:
            return r1
        L45:
            r11 = move-exception
            r1 = r10
        L47:
            if (r1 == 0) goto L4c
            r1.close()
        L4c:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.f.b(android.database.sqlite.SQLiteDatabase, int):java.lang.String");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", str);
        sQLiteDatabase.replace(com.igexin.push.core.b.Z, null, contentValues);
    }

    private boolean c(int i2) {
        if (com.igexin.push.core.e.J == i2) {
            return false;
        }
        com.igexin.push.core.e.J = i2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass17(), false, true);
    }

    public static void d() {
        try {
            String string = com.igexin.push.core.e.D;
            UUID uuidRandomUUID = UUID.randomUUID();
            if (TextUtils.isEmpty(string) || string.length() <= 8) {
                StringBuilder sb = new StringBuilder("V");
                sb.append(com.igexin.c.b.a.b(com.igexin.push.core.e.g + uuidRandomUUID));
                string = sb.toString();
            }
            StringBuilder sb2 = new StringBuilder("A-");
            sb2.append(string);
            sb2.append("-");
            sb2.append(com.igexin.c.b.a.b(System.currentTimeMillis() + uuidRandomUUID + com.igexin.push.core.e.g));
            String string2 = sb2.toString();
            com.igexin.push.core.e.L = string2;
            if (string2.length() >= 64) {
                com.igexin.push.core.e.L = com.igexin.push.core.e.L.substring(0, 62);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            StringBuilder sb3 = new StringBuilder("A-V");
            sb3.append(com.igexin.push.core.e.g);
            sb3.append("-");
            sb3.append(com.igexin.c.b.a.b(System.currentTimeMillis() + com.igexin.push.core.e.g));
            com.igexin.push.core.e.L = sb3.toString();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004a A[PHI: r1
  0x004a: PHI (r1v3 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v4 android.database.Cursor) binds: [B:14:0x0048, B:8:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(android.database.sqlite.SQLiteDatabase r10) {
        /*
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r3 = "runtime"
            java.lang.String[] r4 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r5 = "id=?"
            java.lang.String r2 = "25"
            java.lang.String[] r6 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r10
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            if (r1 == 0) goto L3f
            boolean r10 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            if (r10 == 0) goto L3f
            java.lang.String r10 = new java.lang.String     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            byte[] r0 = r1.getBlob(r0)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            android.content.Context r2 = com.igexin.push.core.ServiceManager.b     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r2 = r2.getPackageName()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            java.lang.String r2 = com.igexin.c.b.a.b(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            byte[] r0 = com.igexin.c.a.a.a.a(r0, r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r10.<init>(r0)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            com.igexin.push.core.e.M = r10     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
        L3f:
            if (r1 == 0) goto L4d
            goto L4a
        L42:
            r10 = move-exception
            goto L7d
        L44:
            r10 = move-exception
            com.igexin.c.a.c.a.a(r10)     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L4d
        L4a:
            r1.close()
        L4d:
            java.lang.String r10 = com.igexin.push.core.e.M
            if (r10 != 0) goto L5d
            java.lang.String r10 = com.igexin.push.core.e.D
            if (r10 != 0) goto L57
            java.lang.String r10 = "cantgetimei"
        L57:
            java.lang.String r10 = com.igexin.c.b.a.b(r10)
            com.igexin.push.core.e.M = r10
        L5d:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = com.igexin.push.core.e.f.f3417a
            r10.append(r0)
            java.lang.String r0 = "|storageKey = "
            r10.append(r0)
            java.lang.String r0 = com.igexin.push.core.e.M
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r0 = 0
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.igexin.c.a.c.a.a(r10, r0)
            return
        L7d:
            if (r1 == 0) goto L82
            r1.close()
        L82:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.f.d(android.database.sqlite.SQLiteDatabase):void");
    }

    public static /* synthetic */ void e() throws Throwable {
        j.b();
        String strD = j.d();
        if (strD == null || strD.length() <= 5) {
            j.f();
        }
    }

    public static /* synthetic */ void e(SQLiteDatabase sQLiteDatabase) throws Throwable {
        byte[] bArrA = a(sQLiteDatabase, 66);
        if (bArrA != null) {
            String str = new String(bArrA);
            if (TextUtils.isEmpty(str)) {
                com.igexin.c.a.c.a.a(f3417a, "readRedirectAes null");
                return;
            }
            g.c = str;
            com.igexin.c.a.c.a.b(f3417a, " readRedirectAes set success " + g.c);
        }
    }

    private void f() {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass1(), false, true);
    }

    private void f(SQLiteDatabase sQLiteDatabase) throws Throwable {
        this.b = true;
        d(sQLiteDatabase);
        byte[] bArrA = a(sQLiteDatabase, 1);
        if (bArrA != null) {
            try {
                String str = new String(bArrA);
                com.igexin.push.core.e.z = str.equals(com.igexin.push.core.b.m) ? 0L : Long.parseLong(str);
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            com.igexin.c.a.c.a.a(f3417a + "|db version changed, save session = " + com.igexin.push.core.e.z, new Object[0]);
        }
        byte[] bArrA2 = a(sQLiteDatabase, 20);
        if (bArrA2 != null) {
            String str2 = new String(bArrA2);
            if (str2.equals(com.igexin.push.core.b.m)) {
                str2 = null;
            }
            com.igexin.push.core.e.B = str2;
            com.igexin.push.core.e.A = str2;
            com.igexin.c.a.c.a.a(f3417a + "|db version changed, save cid = " + str2, new Object[0]);
        }
        String strB = b(sQLiteDatabase, 3);
        if (!TextUtils.isEmpty(strB)) {
            if (strB.equals(com.igexin.push.core.b.m)) {
                strB = null;
            }
            com.igexin.push.core.e.L = strB;
        }
        String str3 = com.igexin.push.core.e.L;
        String strB2 = b(sQLiteDatabase, 2);
        if (!TextUtils.isEmpty(strB2)) {
            if (strB2.equals(com.igexin.push.core.b.m)) {
                strB2 = null;
            }
            com.igexin.push.core.e.H = strB2;
        }
        String strB3 = b(sQLiteDatabase, 46);
        if (!TextUtils.isEmpty(strB3)) {
            if (strB3.equals(com.igexin.push.core.b.m)) {
                strB3 = null;
            }
            com.igexin.push.core.e.I = strB3;
        }
        String strB4 = b(sQLiteDatabase, 48);
        if (!TextUtils.isEmpty(strB4)) {
            if (strB4.equals(com.igexin.push.core.b.m)) {
                strB4 = null;
            }
            com.igexin.push.core.e.K = strB4;
        }
        String strB5 = b(sQLiteDatabase, 51);
        if (TextUtils.isEmpty(strB5) || strB5.length() == 13) {
            return;
        }
        com.igexin.push.core.e.C = strB5.equals(com.igexin.push.core.b.m) ? null : strB5;
    }

    private boolean f(long j2) {
        if (j2 == com.igexin.push.core.e.R) {
            return false;
        }
        com.igexin.push.core.e.R = j2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass12(), false, true);
    }

    public static /* synthetic */ byte[] f(String str) {
        return g.a(str.getBytes());
    }

    public static void g() throws Throwable {
        j.b();
        String strD = j.d();
        if (strD == null || strD.length() <= 5) {
            j.f();
        }
    }

    public static void g(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 2);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals(com.igexin.push.core.b.m)) {
            strB = null;
        }
        com.igexin.push.core.e.H = strB;
    }

    private boolean g(long j2) {
        if (com.igexin.push.core.e.T == j2) {
            return false;
        }
        com.igexin.push.core.e.T = j2;
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.34
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 12, String.valueOf(com.igexin.push.core.e.T));
            }
        }, false, true);
        return true;
    }

    public static byte[] g(String str) {
        return g.a(str.getBytes());
    }

    public static void h() {
        String str = com.igexin.push.core.e.A;
        com.igexin.c.a.c.a.a(f3417a + "| found a duplicate cid " + com.igexin.push.core.e.A, new Object[0]);
        com.igexin.push.core.e.L = null;
        d();
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) a().new AnonymousClass13(com.igexin.push.core.e.L), false, true);
        a().b();
        com.igexin.push.core.e.r = 0;
        com.igexin.push.f.b.e.g().f3563a = SystemClock.elapsedRealtime();
    }

    public static void h(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 51);
        if (TextUtils.isEmpty(strB) || strB.length() == 13) {
            return;
        }
        if (strB.equals(com.igexin.push.core.b.m)) {
            strB = null;
        }
        com.igexin.push.core.e.C = strB;
    }

    private boolean h(long j2) {
        if (com.igexin.push.core.e.X == j2) {
            return false;
        }
        com.igexin.push.core.e.X = j2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass5(), false, true);
    }

    private boolean h(String str) {
        com.igexin.push.core.e.C = str;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass31(), false, true);
    }

    private void i() {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass25(), false, true);
    }

    public static void i(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 46);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals(com.igexin.push.core.b.m)) {
            strB = null;
        }
        com.igexin.push.core.e.I = strB;
    }

    private boolean i(long j2) {
        if (com.igexin.push.core.e.U == j2) {
            return false;
        }
        com.igexin.push.core.e.U = j2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.14
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 32, String.valueOf(com.igexin.push.core.e.U));
            }
        }, false, true);
    }

    private boolean i(String str) {
        if (str.equals(com.igexin.push.core.e.V)) {
            return false;
        }
        com.igexin.push.core.e.V = str;
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass3(), false, true);
        return true;
    }

    private void j() {
        if (TextUtils.isEmpty(g.c)) {
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass27(), true, false);
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass28(), true, false);
        }
    }

    public static void j(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 48);
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        if (strB.equals(com.igexin.push.core.b.m)) {
            strB = null;
        }
        com.igexin.push.core.e.K = strB;
    }

    private boolean j(long j2) {
        if (com.igexin.push.core.e.c == j2) {
            return false;
        }
        com.igexin.push.core.e.c = j2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass21(), false, true);
    }

    private boolean j(String str) {
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass13(str), false, true);
    }

    public static void k(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String strB = b(sQLiteDatabase, 3);
        if (!TextUtils.isEmpty(strB)) {
            if (strB.equals(com.igexin.push.core.b.m)) {
                strB = null;
            }
            com.igexin.push.core.e.L = strB;
        }
        String str = com.igexin.push.core.e.L;
    }

    public static void l(SQLiteDatabase sQLiteDatabase) throws Throwable {
        byte[] bArrA = a(sQLiteDatabase, 1);
        if (bArrA != null) {
            try {
                String str = new String(bArrA);
                com.igexin.push.core.e.z = str.equals(com.igexin.push.core.b.m) ? 0L : Long.parseLong(str);
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
            com.igexin.c.a.c.a.a(f3417a + "|db version changed, save session = " + com.igexin.push.core.e.z, new Object[0]);
        }
    }

    public static void m(SQLiteDatabase sQLiteDatabase) throws Throwable {
        byte[] bArrA = a(sQLiteDatabase, 20);
        if (bArrA != null) {
            String str = new String(bArrA);
            if (str.equals(com.igexin.push.core.b.m)) {
                str = null;
            }
            com.igexin.push.core.e.B = str;
            com.igexin.push.core.e.A = str;
            com.igexin.c.a.c.a.a(f3417a + "|db version changed, save cid = " + str, new Object[0]);
        }
    }

    public static void n(SQLiteDatabase sQLiteDatabase) throws Throwable {
        byte[] bArrA = a(sQLiteDatabase, 66);
        if (bArrA != null) {
            String str = new String(bArrA);
            if (TextUtils.isEmpty(str)) {
                com.igexin.c.a.c.a.a(f3417a, "readRedirectAes null");
                return;
            }
            g.c = str;
            com.igexin.c.a.c.a.b(f3417a, " readRedirectAes set success " + g.c);
        }
    }

    @Override // com.igexin.push.core.e.a
    public final void a(SQLiteDatabase sQLiteDatabase) {
    }

    public final void a(boolean z2) {
        com.igexin.push.core.e.W = z2;
        com.igexin.c.a.c.a.a(z2);
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.4
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 15, String.valueOf(com.igexin.push.core.e.W));
            }
        }, false, true);
    }

    public final boolean a(int i2) {
        com.igexin.push.core.e.ab = i2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.7
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 18, String.valueOf(com.igexin.push.core.e.ab));
            }
        }, false, true);
    }

    public final boolean a(long j2) {
        com.igexin.push.core.e.a(j2);
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.29
            @Override // com.igexin.push.b.d
            public final void a_() throws Throwable {
                f.a();
                f.a(this.d, 1, g.a(String.valueOf(com.igexin.push.core.e.z).getBytes()));
                f.a();
                f.a(this.d, 20, f.f(com.igexin.push.core.e.A));
                j.b();
            }
        }, false, true);
    }

    public final boolean a(String str) {
        com.igexin.push.core.e.H = str;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.30
            @Override // com.igexin.push.b.d
            public final void a_() throws Throwable {
                f.a();
                f.b(this.d, 2, com.igexin.push.core.e.H);
                String strD = j.d();
                if (strD == null || strD.length() <= 5) {
                    j.f();
                }
            }
        }, false, true);
    }

    public final boolean a(final String str, boolean z2) {
        com.igexin.c.a.b.e eVarA;
        com.igexin.push.b.d dVar;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (z2) {
            if (!str.equals(com.igexin.push.core.e.ar)) {
                com.igexin.push.core.e.ar = str.equals(com.igexin.push.core.b.m) ? null : str;
                eVarA = com.igexin.c.a.b.e.a();
                dVar = new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.8
                    @Override // com.igexin.push.b.d
                    public final void a_() throws Exception {
                        f.a();
                        f.a(this.d, 31, f.f(str));
                    }
                };
                return eVarA.a((com.igexin.c.a.d.f) dVar, false, true);
            }
            return false;
        }
        if (!str.equals(com.igexin.push.core.e.as)) {
            com.igexin.push.core.e.as = str.equals(com.igexin.push.core.b.m) ? null : str;
            eVarA = com.igexin.c.a.b.e.a();
            dVar = new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.9
                @Override // com.igexin.push.b.d
                public final void a_() throws Exception {
                    f.a();
                    f.a(this.d, 30, f.f(str));
                }
            };
            return eVarA.a((com.igexin.c.a.d.f) dVar, false, true);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:256:0x041a A[PHI: r3 r5 r7 r13 r15
  0x041a: PHI (r3v3 int) = (r3v2 int), (r3v12 int) binds: [B:255:0x0418, B:245:0x03f9] A[DONT_GENERATE, DONT_INLINE]
  0x041a: PHI (r5v5 android.database.Cursor) = (r5v4 android.database.Cursor), (r5v8 android.database.Cursor) binds: [B:255:0x0418, B:245:0x03f9] A[DONT_GENERATE, DONT_INLINE]
  0x041a: PHI (r7v4 int) = (r7v3 int), (r7v14 int) binds: [B:255:0x0418, B:245:0x03f9] A[DONT_GENERATE, DONT_INLINE]
  0x041a: PHI (r13v3 int) = (r13v2 int), (r13v5 int) binds: [B:255:0x0418, B:245:0x03f9] A[DONT_GENERATE, DONT_INLINE]
  0x041a: PHI (r15v3 boolean) = (r15v2 boolean), (r15v5 boolean) binds: [B:255:0x0418, B:245:0x03f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0494  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x05d3  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x008e A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:356:0x03c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0082 A[Catch: all -> 0x03e8, TRY_LEAVE, TryCatch #4 {all -> 0x03e8, blocks: (B:33:0x0073, B:35:0x007c, B:37:0x0082), top: B:346:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:463:? A[RETURN, SYNTHETIC] */
    @Override // com.igexin.push.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.database.sqlite.SQLiteDatabase r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.f.b(android.database.sqlite.SQLiteDatabase):void");
    }

    public final boolean b() {
        com.igexin.push.core.e.z = 0L;
        com.igexin.push.core.e.A = com.igexin.push.core.b.m;
        d();
        return c();
    }

    public final boolean b(int i2) {
        if (com.igexin.push.core.e.aA == i2) {
            return false;
        }
        com.igexin.push.core.e.aA = i2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.16
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 47, String.valueOf(com.igexin.push.core.e.aA));
            }
        }, false, true);
    }

    public final boolean b(final long j2) {
        com.igexin.push.core.e.ao = j2;
        com.igexin.c.a.c.a.a(f3417a + "|save idc config failed time : " + j2, new Object[0]);
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.2
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 21, String.valueOf(j2));
            }
        }, false, true);
    }

    public final boolean b(String str) {
        com.igexin.push.core.e.I = str;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.32
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 46, com.igexin.push.core.e.I);
            }
        }, false, true);
    }

    public final boolean b(final String str, boolean z2) {
        com.igexin.c.a.b.e eVarA;
        com.igexin.push.b.d dVar;
        if (str == null) {
            return false;
        }
        if (z2) {
            if (!str.equals(com.igexin.push.core.e.ap)) {
                com.igexin.push.core.e.ap = str.equals(com.igexin.push.core.b.m) ? null : str;
                eVarA = com.igexin.c.a.b.e.a();
                dVar = new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.10
                    @Override // com.igexin.push.b.d
                    public final void a_() throws Exception {
                        f.a();
                        f.a(this.d, 23, f.f(str));
                    }
                };
                return eVarA.a((com.igexin.c.a.d.f) dVar, false, true);
            }
            return false;
        }
        if (!str.equals(com.igexin.push.core.e.aq)) {
            com.igexin.push.core.e.aq = str.equals(com.igexin.push.core.b.m) ? null : str;
            eVarA = com.igexin.c.a.b.e.a();
            dVar = new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.11
                @Override // com.igexin.push.b.d
                public final void a_() throws Exception {
                    f.a();
                    f.a(this.d, 22, f.f(str));
                }
            };
            return eVarA.a((com.igexin.c.a.d.f) dVar, false, true);
        }
        return false;
    }

    public final boolean b(final boolean z2) {
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.18
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 40, String.valueOf(z2));
            }
        }, false, true);
    }

    @Override // com.igexin.push.core.e.a
    public final void c(SQLiteDatabase sQLiteDatabase) {
        byte[] bArrB = com.igexin.c.a.a.a.b(String.valueOf(com.igexin.push.core.e.z).getBytes(), com.igexin.push.core.e.M);
        long j2 = com.igexin.push.core.e.z;
        a(sQLiteDatabase, 1, bArrB);
        b(sQLiteDatabase, 4, String.valueOf(com.igexin.push.core.e.t));
        b(sQLiteDatabase, 8, String.valueOf(com.igexin.push.core.e.R));
        b(sQLiteDatabase, 32, String.valueOf(com.igexin.push.core.e.U));
        b(sQLiteDatabase, 3, com.igexin.push.core.e.L);
        b(sQLiteDatabase, 12, String.valueOf(com.igexin.push.core.e.T));
        a(sQLiteDatabase, 20, com.igexin.c.a.a.a.b(com.igexin.push.core.e.A.getBytes(), com.igexin.push.core.e.M));
        b(sQLiteDatabase, 2, com.igexin.push.core.e.H);
        a(sQLiteDatabase, 25, com.igexin.c.a.a.a.b(com.igexin.push.core.e.M.getBytes(), com.igexin.c.b.a.b(com.igexin.push.core.e.l.getPackageName())));
    }

    public final boolean c() {
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.23
            @Override // com.igexin.push.b.d
            public final void a_() throws Throwable {
                f.a();
                f.b(this.d, 2, com.igexin.push.core.e.H);
                f.a(this.d, 1, f.f(String.valueOf(com.igexin.push.core.e.z)));
                f.a(this.d, 20, f.f(com.igexin.push.core.e.A));
                f.b(this.d, 3, com.igexin.push.core.e.L);
                f.e();
            }
        }, false, true);
    }

    public final boolean c(long j2) {
        if (com.igexin.push.core.e.Q == j2) {
            return false;
        }
        com.igexin.push.core.e.Q = j2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.15
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 6, String.valueOf(com.igexin.push.core.e.Q));
            }
        }, false, true);
    }

    public final boolean c(String str) {
        com.igexin.push.core.e.K = str;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.33
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 48, com.igexin.push.core.e.K);
            }
        }, false, true);
    }

    public final boolean c(final String str, final boolean z2) {
        if (str == null) {
            return false;
        }
        String str2 = str.equals(com.igexin.push.core.b.m) ? null : str;
        if (z2 && !TextUtils.equals(com.igexin.push.core.e.au, str)) {
            com.igexin.push.core.e.au = str2;
        } else {
            if (z2 || TextUtils.equals(com.igexin.push.core.e.at, str)) {
                return false;
            }
            com.igexin.push.core.e.at = str2;
        }
        com.igexin.c.a.c.a.a(f3417a + "|saveLastRedirectCmList isMobile = " + z2 + ", lastRedirectCmList = " + str, new Object[0]);
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.19
            @Override // com.igexin.push.b.d
            public final void a_() throws Exception {
                f.a();
                f.a(this.d, z2 ? 50 : 49, f.f(str));
            }
        }, false, true);
    }

    public final boolean d(long j2) {
        if (com.igexin.push.core.e.aw == j2) {
            return false;
        }
        com.igexin.push.core.e.aw = j2;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.24
            @Override // com.igexin.push.b.d
            public final void a_() throws Exception {
                f.a();
                f.b(this.d, 63, String.valueOf(com.igexin.push.core.e.aw));
            }
        }, false, true);
    }

    public final boolean d(String str) {
        if (str.equals(com.igexin.push.core.e.Z)) {
            return false;
        }
        com.igexin.push.core.e.Z = str;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.6
            @Override // com.igexin.push.b.d
            public final void a_() {
                f.a();
                f.b(this.d, 17, String.valueOf(com.igexin.push.core.e.Z));
            }
        }, false, true);
    }

    public final void e(long j2) {
        if (com.igexin.push.core.e.ay != j2) {
            com.igexin.push.core.e.ay = j2;
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.26
                @Override // com.igexin.push.b.d
                public final void a_() throws Exception {
                    f.a();
                    f.b(this.d, 65, String.valueOf(com.igexin.push.core.e.ay));
                }
            }, false, true);
        }
    }

    public final boolean e(final String str) {
        com.igexin.push.core.e.d = str.equals(com.igexin.push.core.b.m) ? null : str;
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.core.e.f.22
            @Override // com.igexin.push.b.d
            public final void a_() throws Exception {
                f.a();
                f.a(this.d, 61, g.a(str.getBytes()));
            }
        }, false, true);
    }
}
