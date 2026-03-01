package com.igexin.push.config;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
public class a implements com.igexin.push.core.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3274a = "com.igexin.push.config.a";
    public static final int b = 63;
    public static final int c = 65;
    public static final int d = 67;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3275e = 68;
    public static final int f = 79;
    public static final int g = 82;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 15;
    public static final int l = 16;
    public static final int m = 24;
    public static final int n = 26;
    public static final int o = 28;
    public static final int p = 46;
    public static final int q = 47;
    public static final int r = 48;
    public static final int s = 49;
    public static final int t = 60;
    public static final int u = 61;
    public static final int v = 62;
    public static final int w = 69;
    public static final int x = 70;
    public static final int y = 74;
    public static volatile a z;

    /* JADX INFO: renamed from: com.igexin.push.config.a$1, reason: invalid class name */
    public class AnonymousClass1 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3276a;

        public AnonymousClass1(String str) {
            this.f3276a = str;
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.b(this.d, 63, this.f3276a);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$3, reason: invalid class name */
    public class AnonymousClass3 extends com.igexin.push.b.d {
        public AnonymousClass3() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.b(this.d, 15, String.valueOf(d.f3289e));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$4, reason: invalid class name */
    public class AnonymousClass4 extends com.igexin.push.b.d {
        public AnonymousClass4() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.b(this.d, 16, String.valueOf(d.f));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$5, reason: invalid class name */
    public class AnonymousClass5 extends com.igexin.push.b.d {
        public AnonymousClass5() {
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.b(this.d, 3, String.valueOf(d.d));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$6, reason: invalid class name */
    public class AnonymousClass6 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3281a;

        public AnonymousClass6(String str) {
            this.f3281a = str;
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.a(this.d, 26, com.igexin.c.a.a.a.b(this.f3281a.getBytes(), com.igexin.push.core.e.M));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$7, reason: invalid class name */
    public class AnonymousClass7 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3282a;

        public AnonymousClass7(String str) {
            this.f3282a = str;
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.a(this.d, 24, com.igexin.c.a.a.a.b(this.f3282a.getBytes(), com.igexin.push.core.e.M));
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.config.a$9, reason: invalid class name */
    public class AnonymousClass9 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3284a;

        public AnonymousClass9(boolean z) {
            this.f3284a = z;
        }

        @Override // com.igexin.push.b.d
        public final void a_() {
            a.b(this.d, 79, String.valueOf(this.f3284a));
        }
    }

    public static a a() {
        if (z == null) {
            synchronized (a.class) {
                if (z == null) {
                    z = new a();
                }
            }
        }
        return z;
    }

    public static void a(SQLiteDatabase sQLiteDatabase, int i2) {
        sQLiteDatabase.delete(com.igexin.push.core.b.Y, "id = ?", new String[]{String.valueOf(i2)});
    }

    public static /* synthetic */ void a(SQLiteDatabase sQLiteDatabase, int i2, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace(com.igexin.push.core.b.Y, null, contentValues);
    }

    private void a(boolean z2) {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass9(z2), true, false);
    }

    private boolean a(String str) {
        return com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass1(str), false, true);
    }

    public static void b(SQLiteDatabase sQLiteDatabase, int i2, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", str);
        sQLiteDatabase.replace(com.igexin.push.core.b.Y, null, contentValues);
    }

    public static void b(SQLiteDatabase sQLiteDatabase, int i2, byte[] bArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(i2));
        contentValues.put("value", bArr);
        sQLiteDatabase.replace(com.igexin.push.core.b.Y, null, contentValues);
    }

    private void b(String str) {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass6(str), true, false);
    }

    private void c() {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass3(), false, true);
    }

    private void c(String str) {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass7(str), true, false);
    }

    private void d() {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass4(), false, true);
    }

    private void e() {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass5(), false, true);
    }

    public final void a(final long j2) {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.config.a.8
            @Override // com.igexin.push.b.d
            public final void a_() {
                long j3 = j2;
                com.igexin.push.core.e.aH = j3;
                a.b(this.d, 65, String.valueOf(j3));
            }
        }, true, false);
    }

    @Override // com.igexin.push.core.e.a
    public final void a(SQLiteDatabase sQLiteDatabase) {
    }

    public final void b() {
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new com.igexin.push.b.d() { // from class: com.igexin.push.config.a.2
            @Override // com.igexin.push.b.d
            public final void a_() {
                a.b(this.d, 1, String.valueOf(d.b));
                a.b(this.d, 2, String.valueOf(d.c));
            }
        }, false, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01e0 A[DONT_GENERATE, PHI: r13
  0x01e0: PHI (r13v4 android.database.Cursor) = (r13v3 android.database.Cursor), (r13v11 android.database.Cursor) binds: [B:111:0x01de, B:106:0x01d4] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.igexin.push.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.database.sqlite.SQLiteDatabase r13) {
        /*
            Method dump skipped, instruction units count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.a.b(android.database.sqlite.SQLiteDatabase):void");
    }

    @Override // com.igexin.push.core.e.a
    public final void c(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase, 1, String.valueOf(d.b));
        b(sQLiteDatabase, 2, String.valueOf(d.c));
        b(sQLiteDatabase, 3, String.valueOf(d.d));
        b(sQLiteDatabase, 15, String.valueOf(d.f3289e));
        b(sQLiteDatabase, 3, String.valueOf(d.d));
    }
}
