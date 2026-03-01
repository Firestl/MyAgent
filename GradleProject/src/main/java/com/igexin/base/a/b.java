package com.igexin.base.a;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements Runnable {
    public static b b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<c> f3143a = new ArrayList();

    public b() {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(this, 5L, 5L, TimeUnit.SECONDS);
    }

    public static synchronized b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:27|(4:137|28|139|29)|34|35|36|(3:38|(1:40)|41)(4:42|(1:44)(1:45)|46|47)|(3:135|48|49)|(3:129|(7:132|51|52|147|53|54|55)(2:60|(3:62|(5:64|65|66|(1:71)|72)(1:74)|73)(2:76|77))|75)|85|143|86|87|(4:(1:99)|100|(1:102)|103)) */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01e3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x022b A[Catch: all -> 0x0245, TRY_ENTER, TryCatch #5 {all -> 0x0245, blocks: (B:3:0x000b, B:5:0x001c, B:7:0x0026, B:10:0x002d, B:13:0x0034, B:16:0x003b, B:17:0x0042, B:19:0x0048, B:20:0x0057, B:22:0x005d, B:114:0x022b, B:116:0x0231, B:120:0x0239, B:122:0x023f, B:123:0x0242), top: B:134:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:152:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f0  */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v14, types: [java.nio.channels.FileLock] */
    /* JADX WARN: Type inference failed for: r21v15 */
    /* JADX WARN: Type inference failed for: r21v16 */
    /* JADX WARN: Type inference failed for: r21v17 */
    /* JADX WARN: Type inference failed for: r21v18 */
    /* JADX WARN: Type inference failed for: r21v19 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v20 */
    /* JADX WARN: Type inference failed for: r21v21 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r21v6 */
    /* JADX WARN: Type inference failed for: r21v7, types: [int] */
    /* JADX WARN: Type inference failed for: r21v8 */
    /* JADX WARN: Type inference failed for: r21v9 */
    /* JADX WARN: Type inference failed for: r27v0, types: [com.igexin.base.a.c] */
    /* JADX WARN: Type inference failed for: r27v1 */
    /* JADX WARN: Type inference failed for: r27v10 */
    /* JADX WARN: Type inference failed for: r27v11 */
    /* JADX WARN: Type inference failed for: r27v12 */
    /* JADX WARN: Type inference failed for: r27v13 */
    /* JADX WARN: Type inference failed for: r27v14 */
    /* JADX WARN: Type inference failed for: r27v15 */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r27v3 */
    /* JADX WARN: Type inference failed for: r27v4 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r27v6 */
    /* JADX WARN: Type inference failed for: r27v7 */
    /* JADX WARN: Type inference failed for: r27v8 */
    /* JADX WARN: Type inference failed for: r27v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(com.igexin.base.a.c r27) {
        /*
            Method dump skipped, instruction units count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.base.a.b.a(com.igexin.base.a.c):boolean");
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (c cVar : this.f3143a) {
            if (cVar.isEnabled()) {
                if (cVar.f3144a.size() >= cVar.b || SystemClock.elapsedRealtime() - cVar.d >= cVar.c) {
                    a(cVar);
                    cVar.d = SystemClock.elapsedRealtime();
                }
            }
        }
    }
}
