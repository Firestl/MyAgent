package com.igexin.push.f;

import android.database.Cursor;
import com.igexin.push.core.d;

/* JADX INFO: loaded from: classes2.dex */
public class e implements com.igexin.push.f.b.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile e f3566a;
    public String b = "ReDisplayTask";
    public volatile long c = 0;

    public static e a() {
        if (f3566a == null) {
            synchronized (e.class) {
                if (f3566a == null) {
                    f3566a = new e();
                }
            }
        }
        return f3566a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0082 A[DONT_GENERATE, PHI: r1
  0x0082: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v3 android.database.Cursor) binds: [B:13:0x0080, B:9:0x0062] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.PriorityQueue<com.igexin.push.core.b.k> e() {
        /*
            r10 = this;
            java.util.PriorityQueue r0 = new java.util.PriorityQueue
            r0.<init>()
            r1 = 0
            com.igexin.push.core.d r2 = com.igexin.push.core.d.a.a()     // Catch: java.lang.Throwable -> L65
            com.igexin.push.b.b r2 = r2.i     // Catch: java.lang.Throwable -> L65
            java.lang.String r3 = "message"
            r4 = 0
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L65
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L65
            java.lang.String r6 = "status = '1' and notify_status = '1' and redisplay_freq != '0' and redisplay_num <= redisplay_freq and expect_redisplay_time <= "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L65
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L65
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            r5.append(r6)     // Catch: java.lang.Throwable -> L65
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L65
            android.database.Cursor r1 = r2.a(r3, r4, r5)     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L62
        L2c:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L65
            if (r2 == 0) goto L62
            java.lang.String r2 = "msgextra"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L65
            byte[] r2 = r1.getBlob(r2)     // Catch: java.lang.Throwable -> L65
            java.lang.String r3 = "expect_redisplay_time"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L65
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> L65
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L65
            java.lang.String r6 = "info"
            int r6 = r1.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L65
            byte[] r6 = r1.getBlob(r6)     // Catch: java.lang.Throwable -> L65
            byte[] r6 = com.igexin.c.b.a.c(r6)     // Catch: java.lang.Throwable -> L65
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L65
            com.igexin.push.core.b.k r6 = new com.igexin.push.core.b.k     // Catch: java.lang.Throwable -> L65
            r6.<init>(r2, r5, r3)     // Catch: java.lang.Throwable -> L65
            r0.offer(r6)     // Catch: java.lang.Throwable -> L65
            goto L2c
        L62:
            if (r1 == 0) goto L85
            goto L82
        L65:
            r2 = move-exception
            com.igexin.c.a.c.a.a(r2)     // Catch: java.lang.Throwable -> L86
            java.lang.String r3 = r10.b     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = "get redisplay message"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L86
            r4.append(r2)     // Catch: java.lang.Throwable -> L86
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L86
            com.igexin.c.a.c.a.b(r3, r2)     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L85
        L82:
            r1.close()
        L85:
            return r0
        L86:
            r0 = move-exception
            if (r1 == 0) goto L8c
            r1.close()
        L8c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.e.e():java.util.PriorityQueue");
    }

    @Override // com.igexin.push.f.b.c
    public final void a(long j) {
    }

    @Override // com.igexin.push.f.b.c
    public final void b() {
        if (com.igexin.push.g.c.a(System.currentTimeMillis())) {
            com.igexin.c.a.c.a.b(this.b, "message in silent time period, ignored...");
        } else {
            com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) new d() { // from class: com.igexin.push.f.e.1
                /* JADX WARN: Removed duplicated region for block: B:17:0x0087 A[Catch: all -> 0x00ab, PHI: r7
  0x0087: PHI (r7v3 com.igexin.push.extension.mod.PushTaskBean) = (r7v2 com.igexin.push.extension.mod.PushTaskBean), (r7v6 com.igexin.push.extension.mod.PushTaskBean) binds: [B:6:0x0059, B:14:0x007f] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x00ab, blocks: (B:5:0x003a, B:7:0x005b, B:9:0x006f, B:15:0x0081, B:16:0x0083, B:17:0x0087, B:19:0x0095, B:20:0x0098, B:22:0x00a0, B:23:0x00a3), top: B:28:0x003a }] */
                @Override // com.igexin.push.f.d
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void b() {
                    /*
                        r8 = this;
                        com.igexin.push.f.e r0 = com.igexin.push.f.e.this
                        java.util.PriorityQueue r0 = com.igexin.push.f.e.a(r0)
                        com.igexin.push.f.e r1 = com.igexin.push.f.e.this
                        com.igexin.push.f.e.b(r1)
                        r0.size()
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        r1.<init>()
                        com.igexin.push.f.e r2 = com.igexin.push.f.e.this
                        java.lang.String r2 = com.igexin.push.f.e.b(r2)
                        r1.append(r2)
                        java.lang.String r2 = " | priorityQueue size = "
                        r1.append(r2)
                        int r2 = r0.size()
                        r1.append(r2)
                        java.lang.String r1 = r1.toString()
                        r2 = 0
                        java.lang.Object[] r3 = new java.lang.Object[r2]
                        com.igexin.c.a.c.a.a(r1, r3)
                    L32:
                        java.lang.Object r1 = r0.poll()
                        com.igexin.push.core.b.k r1 = (com.igexin.push.core.b.k) r1
                        if (r1 == 0) goto Lb6
                        org.json.JSONObject r3 = r1.a()     // Catch: java.lang.Throwable -> Lab
                        java.lang.String r4 = "taskid"
                        java.lang.String r4 = r3.getString(r4)     // Catch: java.lang.Throwable -> Lab
                        java.lang.String r5 = "messageid"
                        java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> Lab
                        com.igexin.push.core.a.b.d()     // Catch: java.lang.Throwable -> Lab
                        java.lang.String r6 = com.igexin.push.core.a.b.a(r4, r5)     // Catch: java.lang.Throwable -> Lab
                        java.util.Map<java.lang.String, com.igexin.push.extension.mod.PushTaskBean> r7 = com.igexin.push.core.e.ah     // Catch: java.lang.Throwable -> Lab
                        java.lang.Object r7 = r7.get(r6)     // Catch: java.lang.Throwable -> Lab
                        com.igexin.push.extension.mod.PushTaskBean r7 = (com.igexin.push.extension.mod.PushTaskBean) r7     // Catch: java.lang.Throwable -> Lab
                        if (r7 != 0) goto L87
                        com.igexin.push.core.n r7 = com.igexin.push.core.n.a()     // Catch: java.lang.Throwable -> Lab
                        byte[] r1 = r1.f     // Catch: java.lang.Throwable -> Lab
                        r7.a(r3, r1, r2)     // Catch: java.lang.Throwable -> Lab
                        java.util.Map<java.lang.String, com.igexin.push.extension.mod.PushTaskBean> r1 = com.igexin.push.core.e.ah     // Catch: java.lang.Throwable -> Lab
                        java.lang.Object r1 = r1.get(r6)     // Catch: java.lang.Throwable -> Lab
                        r7 = r1
                        com.igexin.push.extension.mod.PushTaskBean r7 = (com.igexin.push.extension.mod.PushTaskBean) r7     // Catch: java.lang.Throwable -> Lab
                        if (r7 == 0) goto L7e
                        com.igexin.push.core.n r1 = com.igexin.push.core.n.a()     // Catch: java.lang.Throwable -> Lab
                        com.igexin.push.extension.mod.PushMessageInterface$ActionPrepareState r1 = r1.a(r4, r5)     // Catch: java.lang.Throwable -> Lab
                        com.igexin.push.extension.mod.PushMessageInterface$ActionPrepareState r3 = com.igexin.push.extension.mod.PushMessageInterface.ActionPrepareState.success     // Catch: java.lang.Throwable -> Lab
                        if (r1 == r3) goto L7c
                        goto L7e
                    L7c:
                        r1 = 0
                        goto L7f
                    L7e:
                        r1 = 1
                    L7f:
                        if (r1 == 0) goto L87
                        com.igexin.push.f.e r1 = com.igexin.push.f.e.this     // Catch: java.lang.Throwable -> Lab
                    L83:
                        com.igexin.push.f.e.b(r1)     // Catch: java.lang.Throwable -> Lab
                        goto L32
                    L87:
                        com.igexin.push.core.n r1 = com.igexin.push.core.n.a()     // Catch: java.lang.Throwable -> Lab
                        java.util.Map r3 = r7.getConditionMap()     // Catch: java.lang.Throwable -> Lab
                        boolean r1 = r1.a(r3, r4, r7)     // Catch: java.lang.Throwable -> Lab
                        if (r1 != 0) goto L98
                        com.igexin.push.f.e r1 = com.igexin.push.f.e.this     // Catch: java.lang.Throwable -> Lab
                        goto L83
                    L98:
                        java.lang.String r1 = "notification"
                        java.lang.String r1 = r7.getActionIdByType(r1)     // Catch: java.lang.Throwable -> Lab
                        if (r1 != 0) goto La3
                        com.igexin.push.f.e r1 = com.igexin.push.f.e.this     // Catch: java.lang.Throwable -> Lab
                        goto L83
                    La3:
                        com.igexin.push.core.n r3 = com.igexin.push.core.n.a()     // Catch: java.lang.Throwable -> Lab
                        r3.a(r4, r5, r1)     // Catch: java.lang.Throwable -> Lab
                        goto L32
                    Lab:
                        r1 = move-exception
                        com.igexin.push.f.e r3 = com.igexin.push.f.e.this
                        com.igexin.push.f.e.b(r3)
                        com.igexin.c.a.c.a.a(r1)
                        goto L32
                    Lb6:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.e.AnonymousClass1.b():void");
                }
            }, true);
        }
    }

    @Override // com.igexin.push.f.b.c
    public final boolean c() {
        boolean z = System.currentTimeMillis() / 1000 >= this.c;
        com.igexin.c.a.c.a.a(this.b + " | ReDisplayTask isMatch =" + z + "， nextReDisplayTime =" + this.c, new Object[0]);
        return z;
    }

    public final void d() {
        Cursor cursorA = null;
        try {
            cursorA = d.a.f3384a.i.a("message", new String[0], "status = '1' and notify_status = '1' and redisplay_freq != '0' and redisplay_num <= redisplay_freq  order by expect_redisplay_time asc limit 1");
            if (cursorA != null && cursorA.getCount() == 1 && cursorA.moveToFirst()) {
                this.c = cursorA.getLong(cursorA.getColumnIndex("expect_redisplay_time"));
            } else {
                this.c = Long.MAX_VALUE;
            }
            System.currentTimeMillis();
        } catch (Throwable th) {
            try {
                com.igexin.c.a.c.a.a(th);
                com.igexin.c.a.c.a.b(this.b, " get next redisplay message fail" + th.toString());
                if (cursorA != null) {
                    cursorA.close();
                }
            } finally {
                if (cursorA != null) {
                    cursorA.close();
                }
            }
        }
    }
}
