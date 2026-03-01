package com.igexin.push.core.e;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.push.core.b.n;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class e implements a {
    public static final String b = "RALDataManager";
    public static final int c = 318;
    public static final int d = 300;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile e f3412e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<n> f3413a = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: com.igexin.push.core.e.e$3, reason: invalid class name */
    public class AnonymousClass3 extends com.igexin.push.b.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f3416a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(ContentValues contentValues, long j) {
            super(contentValues);
            this.f3416a = j;
        }

        @Override // com.igexin.push.b.d
        public final void a_() throws Exception {
            this.d.update(com.igexin.push.core.b.ab, this.h, "id=?", new String[]{String.valueOf(this.f3416a)});
        }
    }

    private int a(byte b2) {
        Iterator<n> it = this.f3413a.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().c == b2) {
                i++;
            }
        }
        return i;
    }

    public static ContentValues a(n nVar) {
        if (nVar == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(nVar.f3360a));
        contentValues.put("data", com.igexin.c.b.a.b(nVar.b.getBytes()));
        contentValues.put("type", Byte.valueOf(nVar.c));
        contentValues.put("time", Long.valueOf(nVar.d));
        contentValues.put("send_times", Integer.valueOf(nVar.f3361e));
        return contentValues;
    }

    public static e a() {
        if (f3412e == null) {
            synchronized (e.class) {
                if (f3412e == null) {
                    f3412e = new e();
                }
            }
        }
        return f3412e;
    }

    private boolean a(long j, long j2) {
        n nVarA = a(j);
        if (nVarA == null) {
            return false;
        }
        nVarA.d = j2;
        nVarA.f3361e++;
        com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new AnonymousClass3(a(nVarA), j), true, true);
        return true;
    }

    private List<n> b() {
        return this.f3413a;
    }

    private void b(byte b2) {
        n nVar = null;
        try {
            Iterator<n> it = this.f3413a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                n next = it.next();
                if (next.c == b2) {
                    nVar = next;
                    break;
                }
            }
            if (nVar != null) {
                a(nVar.f3360a, true);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    public final n a(long j) {
        for (n nVar : this.f3413a) {
            if (nVar.f3360a == j) {
                return nVar;
            }
        }
        return null;
    }

    public final void a(final long j, boolean z) {
        n nVarA = a(j);
        if (nVarA != null) {
            this.f3413a.remove(nVarA);
        }
        com.igexin.c.a.b.e.a().a(new com.igexin.push.b.d(a(nVarA)) { // from class: com.igexin.push.core.e.e.2
            @Override // com.igexin.push.b.d
            public final void a_() throws Exception {
                this.d.delete(com.igexin.push.core.b.ab, "id=?", new String[]{String.valueOf(j)});
            }
        }, z, !z);
    }

    @Override // com.igexin.push.core.e.a
    public final void a(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.igexin.push.core.e.a
    public final void b(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabase.query(com.igexin.push.core.b.ab, new String[]{"id", "data", "type", "time", "send_times"}, null, null, null, null, null);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (cursorQuery != null) {
                    while (cursorQuery.moveToNext()) {
                        long j = cursorQuery.getLong(0);
                        byte b2 = (byte) cursorQuery.getInt(2);
                        long j2 = cursorQuery.getLong(3);
                        int i = cursorQuery.getInt(4);
                        if ((j2 == 0 || jCurrentTimeMillis - j2 <= 259200000) && i < com.igexin.push.config.d.N - 1) {
                            List<n> list = this.f3413a;
                            n nVar = new n(j, new String(com.igexin.c.b.a.c(cursorQuery.getBlob(1))), b2, j2);
                            nVar.f3361e = i;
                            list.add(nVar);
                        } else {
                            a(j, true);
                        }
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(final com.igexin.push.core.b.n r5) {
        /*
            r4 = this;
            java.util.List<com.igexin.push.core.b.n> r0 = r4.f3413a
            int r0 = r0.size()
            r1 = 7
            r2 = 2
            r3 = 318(0x13e, float:4.46E-43)
            if (r0 < r3) goto L13
            byte r0 = r5.c
            if (r0 == r2) goto L13
            if (r0 == r1) goto L13
            return
        L13:
            byte r0 = r5.c
            if (r0 == r2) goto L47
            r2 = 3
            if (r0 == r2) goto L3e
            r3 = 5
            if (r0 == r3) goto L37
            r3 = 6
            if (r0 == r3) goto L2e
            if (r0 == r1) goto L47
            r1 = 8
            if (r0 == r1) goto L27
            goto L4c
        L27:
            int r0 = r4.a(r1)
            if (r0 < r2) goto L4c
            return
        L2e:
            int r0 = r4.a(r3)
            r1 = 10
            if (r0 < r1) goto L4c
            return
        L37:
            int r0 = r4.a(r3)
            if (r0 < r2) goto L4c
            return
        L3e:
            int r0 = r4.a(r2)
            r1 = 300(0x12c, float:4.2E-43)
            if (r0 < r1) goto L4c
            return
        L47:
            byte r0 = r5.c
            r4.b(r0)
        L4c:
            java.util.List<com.igexin.push.core.b.n> r0 = r4.f3413a
            r0.add(r5)
            com.igexin.c.a.b.e r0 = com.igexin.c.a.b.e.a()
            com.igexin.push.core.e.e$1 r1 = new com.igexin.push.core.e.e$1
            android.content.ContentValues r2 = a(r5)
            r1.<init>(r2)
            r5 = 0
            r2 = 1
            r0.a(r1, r5, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.e.e.b(com.igexin.push.core.b.n):void");
    }

    @Override // com.igexin.push.core.e.a
    public final void c(SQLiteDatabase sQLiteDatabase) {
    }
}
