package com.zx.a.I8b7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Base64;
import com.zx.a.I8b7.t1;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b3 extends c {
    public SQLiteDatabase b = null;

    @Override // com.zx.a.I8b7.c
    public String a() {
        return "CREATE TABLE IF NOT EXISTS zx_table (key integer primary key, value text)";
    }

    @Override // com.zx.a.I8b7.c
    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        y1.a("ZXID数据库升级, drop zx_table表");
        try {
            sQLiteDatabase.execSQL("drop table if exists zx_table");
        } catch (Exception e2) {
            y1.a(e2);
        }
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS zx_table (key integer primary key, value text)");
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void b(int i) {
        if (i != t2.q) {
            t2.q = i;
            t2.s = -1;
            a(14, t2.q + "", false);
            a(7, t2.s + "", false);
        }
    }

    @Override // com.zx.a.I8b7.c
    public String c() {
        return "zx_table";
    }

    public void c(int i) {
        if (i != t2.s) {
            t2.s = i;
            a(7, t2.s + "", false);
        }
    }

    public void d(int i) {
        if (i != t2.m) {
            t2.m = i;
            t1.a.f6285a.f6284a.a(3, t2.m + "", false);
            y1.a("syncId had changed refresh:" + i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003b A[DONT_GENERATE, PHI: r1
  0x003b: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v3 android.database.Cursor) binds: [B:12:0x0039, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int e() {
        /*
            r11 = this;
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r2 = "key"
            java.lang.String[] r5 = new java.lang.String[]{r2, r0}     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "key=20"
            r7 = 0
            android.database.sqlite.SQLiteDatabase r3 = r11.b()     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = r11.c()     // Catch: java.lang.Throwable -> L35
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L32
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L32
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L35
            java.lang.String r0 = r1.getString(r0)     // Catch: java.lang.Throwable -> L35
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L35
            com.zx.a.I8b7.t2.r = r0     // Catch: java.lang.Throwable -> L35
        L32:
            if (r1 == 0) goto L3e
            goto L3b
        L35:
            r0 = move-exception
            com.zx.a.I8b7.y1.a(r0)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L3e
        L3b:
            r1.close()
        L3e:
            int r0 = com.zx.a.I8b7.t2.r
            return r0
        L41:
            r0 = move-exception
            if (r1 == 0) goto L47
            r1.close()
        L47:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.e():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003b A[DONT_GENERATE, PHI: r1
  0x003b: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v3 android.database.Cursor) binds: [B:12:0x0039, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int f() {
        /*
            r11 = this;
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r2 = "key"
            java.lang.String[] r5 = new java.lang.String[]{r2, r0}     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "key=14"
            r7 = 0
            android.database.sqlite.SQLiteDatabase r3 = r11.b()     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = r11.c()     // Catch: java.lang.Throwable -> L35
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L32
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L32
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L35
            java.lang.String r0 = r1.getString(r0)     // Catch: java.lang.Throwable -> L35
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L35
            com.zx.a.I8b7.t2.q = r0     // Catch: java.lang.Throwable -> L35
        L32:
            if (r1 == 0) goto L3e
            goto L3b
        L35:
            r0 = move-exception
            com.zx.a.I8b7.y1.a(r0)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L3e
        L3b:
            r1.close()
        L3e:
            int r0 = com.zx.a.I8b7.t2.q
            return r0
        L41:
            r0 = move-exception
            if (r1 == 0) goto L47
            r1.close()
        L47:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.f():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0044 A[DONT_GENERATE, PHI: r1 r2
  0x0044: PHI (r1v1 javax.crypto.spec.IvParameterSpec) = (r1v0 javax.crypto.spec.IvParameterSpec), (r1v4 javax.crypto.spec.IvParameterSpec) binds: [B:16:0x0042, B:11:0x003a] A[DONT_GENERATE, DONT_INLINE]
  0x0044: PHI (r2v2 android.database.Cursor) = (r2v1 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:16:0x0042, B:11:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public javax.crypto.spec.IvParameterSpec g() {
        /*
            r11 = this;
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r2 = "key"
            java.lang.String[] r5 = new java.lang.String[]{r2, r0}     // Catch: java.lang.Throwable -> L3d
            java.lang.String r6 = "key=10"
            r7 = 0
            android.database.sqlite.SQLiteDatabase r3 = r11.b()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r11.c()     // Catch: java.lang.Throwable -> L3d
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L3d
            if (r2 == 0) goto L3a
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L38
            if (r3 == 0) goto L3a
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L38
            java.lang.String r0 = r2.getString(r0)     // Catch: java.lang.Throwable -> L38
            r3 = 0
            byte[] r0 = android.util.Base64.decode(r0, r3)     // Catch: java.lang.Throwable -> L38
            javax.crypto.spec.IvParameterSpec r3 = new javax.crypto.spec.IvParameterSpec     // Catch: java.lang.Throwable -> L38
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L38
            r1 = r3
            goto L3a
        L38:
            r0 = move-exception
            goto L3f
        L3a:
            if (r2 == 0) goto L47
            goto L44
        L3d:
            r0 = move-exception
            r2 = r1
        L3f:
            com.zx.a.I8b7.y1.a(r0)     // Catch: java.lang.Throwable -> L48
            if (r2 == 0) goto L47
        L44:
            r2.close()
        L47:
            return r1
        L48:
            r0 = move-exception
            if (r2 == 0) goto L4e
            r2.close()
        L4e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.g():javax.crypto.spec.IvParameterSpec");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003b A[DONT_GENERATE, PHI: r1
  0x003b: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v3 android.database.Cursor) binds: [B:12:0x0039, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int h() {
        /*
            r11 = this;
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r2 = "key"
            java.lang.String[] r5 = new java.lang.String[]{r2, r0}     // Catch: java.lang.Throwable -> L35
            java.lang.String r6 = "key=7"
            r7 = 0
            android.database.sqlite.SQLiteDatabase r3 = r11.b()     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = r11.c()     // Catch: java.lang.Throwable -> L35
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L32
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L32
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L35
            java.lang.String r0 = r1.getString(r0)     // Catch: java.lang.Throwable -> L35
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L35
            com.zx.a.I8b7.t2.s = r0     // Catch: java.lang.Throwable -> L35
        L32:
            if (r1 == 0) goto L3e
            goto L3b
        L35:
            r0 = move-exception
            com.zx.a.I8b7.y1.a(r0)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L3e
        L3b:
            r1.close()
        L3e:
            int r0 = com.zx.a.I8b7.t2.s
            return r0
        L41:
            r0 = move-exception
            if (r1 == 0) goto L47
            r1.close()
        L47:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.h():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048 A[DONT_GENERATE, PHI: r1 r2
  0x0048: PHI (r1v1 javax.crypto.spec.SecretKeySpec) = (r1v0 javax.crypto.spec.SecretKeySpec), (r1v4 javax.crypto.spec.SecretKeySpec) binds: [B:16:0x0046, B:11:0x003e] A[DONT_GENERATE, DONT_INLINE]
  0x0048: PHI (r2v2 android.database.Cursor) = (r2v1 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:16:0x0046, B:11:0x003e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public javax.crypto.SecretKey i() {
        /*
            r11 = this;
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r2 = "key"
            java.lang.String[] r5 = new java.lang.String[]{r2, r0}     // Catch: java.lang.Throwable -> L41
            java.lang.String r6 = "key=9"
            r7 = 0
            android.database.sqlite.SQLiteDatabase r3 = r11.b()     // Catch: java.lang.Throwable -> L41
            java.lang.String r4 = r11.c()     // Catch: java.lang.Throwable -> L41
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L3e
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L3c
            if (r3 == 0) goto L3e
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L3c
            java.lang.String r0 = r2.getString(r0)     // Catch: java.lang.Throwable -> L3c
            r3 = 0
            byte[] r0 = android.util.Base64.decode(r0, r3)     // Catch: java.lang.Throwable -> L3c
            java.lang.String r3 = "AES"
            java.security.SecureRandom r4 = com.zx.a.I8b7.j.f6232a     // Catch: java.lang.Throwable -> L3c
            javax.crypto.spec.SecretKeySpec r4 = new javax.crypto.spec.SecretKeySpec     // Catch: java.lang.Throwable -> L3c
            r4.<init>(r0, r3)     // Catch: java.lang.Throwable -> L3c
            r1 = r4
            goto L3e
        L3c:
            r0 = move-exception
            goto L43
        L3e:
            if (r2 == 0) goto L4b
            goto L48
        L41:
            r0 = move-exception
            r2 = r1
        L43:
            com.zx.a.I8b7.y1.a(r0)     // Catch: java.lang.Throwable -> L4c
            if (r2 == 0) goto L4b
        L48:
            r2.close()
        L4b:
            return r1
        L4c:
            r0 = move-exception
            if (r2 == 0) goto L52
            r2.close()
        L52:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.i():javax.crypto.SecretKey");
    }

    public void a(byte[] bArr) {
        String str = new String(Base64.encode(bArr, 0), StandardCharsets.UTF_8);
        a(9, str + "", false);
        y1.a("ZXID saveSecretKey secretStr:" + str);
    }

    public final void a(int i, String str, boolean z) {
        String str2;
        if (this.b == null) {
            this.b = d();
        }
        if (z) {
            try {
                str2 = new String(Base64.encode(j.b("AES/CBC/PKCS5Padding", t2.u, t2.v, str.getBytes()), 0), StandardCharsets.UTF_8);
            } catch (Exception e2) {
                y1.b("ZXID updateDBValue valueID:" + i + ",value:" + str + ",error:" + e2.toString());
                return;
            }
        } else {
            str2 = str;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", Integer.valueOf(i));
        contentValues.put("value", str2);
        y1.a("replace resultId = " + this.b.replace("zx_table", null, contentValues));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d A[DONT_GENERATE, PHI: r1 r2
  0x005d: PHI (r1v2 java.lang.String) = (r1v1 java.lang.String), (r1v4 java.lang.String) binds: [B:13:0x005b, B:9:0x0054] A[DONT_GENERATE, DONT_INLINE]
  0x005d: PHI (r2v2 android.database.Cursor) = (r2v1 android.database.Cursor), (r2v3 android.database.Cursor) binds: [B:13:0x005b, B:9:0x0054] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(int r13) {
        /*
            r12 = this;
            java.lang.String r0 = "value"
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "key"
            java.lang.String[] r6 = new java.lang.String[]{r3, r0}     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57
            r3.<init>()     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = "key="
            r3.append(r4)     // Catch: java.lang.Throwable -> L57
            r3.append(r13)     // Catch: java.lang.Throwable -> L57
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L57
            r8 = 0
            android.database.sqlite.SQLiteDatabase r4 = r12.b()     // Catch: java.lang.Throwable -> L57
            java.lang.String r5 = r12.c()     // Catch: java.lang.Throwable -> L57
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L57
            if (r2 == 0) goto L54
            boolean r13 = r2.moveToNext()     // Catch: java.lang.Throwable -> L57
            if (r13 == 0) goto L54
            int r13 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L57
            java.lang.String r1 = r2.getString(r13)     // Catch: java.lang.Throwable -> L57
            r13 = 0
            byte[] r13 = android.util.Base64.decode(r1, r13)     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = "AES/CBC/PKCS5Padding"
            javax.crypto.SecretKey r3 = com.zx.a.I8b7.t2.u     // Catch: java.lang.Throwable -> L57
            javax.crypto.spec.IvParameterSpec r4 = com.zx.a.I8b7.t2.v     // Catch: java.lang.Throwable -> L57
            byte[] r13 = com.zx.a.I8b7.j.a(r0, r3, r4, r13)     // Catch: java.lang.Throwable -> L57
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> L57
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L57
            r0.<init>(r13, r3)     // Catch: java.lang.Throwable -> L57
            r1 = r0
        L54:
            if (r2 == 0) goto L60
            goto L5d
        L57:
            r13 = move-exception
            com.zx.a.I8b7.y1.a(r13)     // Catch: java.lang.Throwable -> L61
            if (r2 == 0) goto L60
        L5d:
            r2.close()
        L60:
            return r1
        L61:
            r13 = move-exception
            if (r2 == 0) goto L67
            r2.close()
        L67:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.a(int):java.lang.String");
    }

    public final void a(String str, byte[] bArr) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        File file = new File(t2.f6286a.getFilesDir().getAbsolutePath() + File.separator + "zx-core-" + str + ".zip");
        if (file.createNewFile()) {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            byte[] bArr2 = new byte[2048];
            while (true) {
                int i = byteArrayInputStream.read(bArr2);
                if (i != -1) {
                    fileOutputStream.write(bArr2, 0, i);
                } else {
                    fileOutputStream.close();
                    byteArrayInputStream.close();
                    return;
                }
            }
        } else {
            throw new IOException("zx createNewFile exception");
        }
    }

    public void a(String str) {
        IvParameterSpec ivParameterSpecG;
        SecretKey secretKeyI;
        JSONObject jSONObjectA;
        Cursor cursorQuery = null;
        try {
            ivParameterSpecG = g();
            secretKeyI = i();
            jSONObjectA = a(ivParameterSpecG, secretKeyI);
        } catch (Throwable th) {
            try {
                y1.a(th);
                if (cursorQuery == null) {
                    return;
                }
            } finally {
                if (0 != 0) {
                    cursorQuery.close();
                }
            }
        }
        if (jSONObjectA == null) {
            return;
        }
        String string = jSONObjectA.getString("mainVersion");
        String string2 = jSONObjectA.getString("checksum");
        if (TextUtils.equals(string, str)) {
            cursorQuery = b().query("zx_table", new String[]{"key", "value"}, "key=17", null, null, null, null);
            if (cursorQuery != null && cursorQuery.moveToNext()) {
                byte[] bArrA = j.a("AES/CBC/PKCS5Padding", secretKeyI, ivParameterSpecG, Base64.decode(cursorQuery.getString(cursorQuery.getColumnIndex("value")), 0));
                if (TextUtils.equals(string2, j.a("SHA256", bArrA))) {
                    a(str, bArrA);
                } else {
                    throw new IOException("zx checksum1 exception");
                }
            }
            if (cursorQuery == null) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0052 A[DONT_GENERATE, PHI: r2
  0x0052: PHI (r2v2 android.database.Cursor) = (r2v1 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:17:0x0050, B:12:0x0048] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONObject a(javax.crypto.spec.IvParameterSpec r12, javax.crypto.SecretKey r13) {
        /*
            r11 = this;
            java.lang.String r0 = "value"
            r1 = 0
            java.lang.String r2 = "key"
            java.lang.String[] r5 = new java.lang.String[]{r2, r0}     // Catch: java.lang.Throwable -> L4b
            java.lang.String r6 = "key=18"
            r7 = 0
            android.database.sqlite.SQLiteDatabase r3 = r11.b()     // Catch: java.lang.Throwable -> L4b
            java.lang.String r4 = "zx_table"
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L4b
            if (r2 == 0) goto L48
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L46
            if (r3 == 0) goto L48
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L46
            java.lang.String r0 = r2.getString(r0)     // Catch: java.lang.Throwable -> L46
            r3 = 0
            byte[] r0 = android.util.Base64.decode(r0, r3)     // Catch: java.lang.Throwable -> L46
            java.lang.String r3 = "AES/CBC/PKCS5Padding"
            byte[] r12 = com.zx.a.I8b7.j.a(r3, r13, r12, r0)     // Catch: java.lang.Throwable -> L46
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L46
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> L46
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L46
            r0.<init>(r12, r3)     // Catch: java.lang.Throwable -> L46
            r13.<init>(r0)     // Catch: java.lang.Throwable -> L46
            r2.close()
            return r13
        L46:
            r12 = move-exception
            goto L4d
        L48:
            if (r2 == 0) goto L55
            goto L52
        L4b:
            r12 = move-exception
            r2 = r1
        L4d:
            com.zx.a.I8b7.y1.a(r12)     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto L55
        L52:
            r2.close()
        L55:
            return r1
        L56:
            r12 = move-exception
            if (r2 == 0) goto L5c
            r2.close()
        L5c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zx.a.I8b7.b3.a(javax.crypto.spec.IvParameterSpec, javax.crypto.SecretKey):org.json.JSONObject");
    }
}
