package com.loc;

import dc.squareup.okhttp3.internal.ws.WebSocketProtocol;
import java.util.HashMap;

/* JADX INFO: compiled from: CellAgeEstimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class du {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<Long, dv> f3751a = new HashMap<>();
    public long b = 0;

    public static long a(int i, int i2) {
        return (((long) i2) & WebSocketProtocol.PAYLOAD_SHORT_MAX) | ((((long) i) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32);
    }

    public final long a(dv dvVar) {
        int i;
        int i2;
        long jA;
        if (dvVar == null || !dvVar.p) {
            return 0L;
        }
        HashMap<Long, dv> map = this.f3751a;
        int i3 = dvVar.k;
        if (i3 == 1) {
            i = dvVar.c;
            i2 = dvVar.d;
            jA = a(i, i2);
        } else if (i3 != 2) {
            if (i3 != 3 && i3 != 4) {
                jA = 0;
            }
            i = dvVar.c;
            i2 = dvVar.d;
            jA = a(i, i2);
        } else {
            i = dvVar.h;
            i2 = dvVar.i;
            jA = a(i, i2);
        }
        dv dvVar2 = map.get(Long.valueOf(jA));
        if (dvVar2 == null) {
            dvVar.m = ep.b();
            map.put(Long.valueOf(jA), dvVar);
            return 0L;
        }
        if (dvVar2.j != dvVar.j) {
            dvVar.m = ep.b();
            map.put(Long.valueOf(jA), dvVar);
            return 0L;
        }
        dvVar.m = dvVar2.m;
        map.put(Long.valueOf(jA), dvVar);
        return (ep.b() - dvVar2.m) / 1000;
    }

    public final void a() {
        this.f3751a.clear();
        this.b = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
    
        if (r13 != 4) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007f, code lost:
    
        if (r12 != 4) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.util.ArrayList<? extends com.loc.dv> r15) {
        /*
            r14 = this;
            if (r15 == 0) goto L9b
            long r0 = com.loc.ep.b()
            long r2 = r14.b
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L18
            long r2 = r0 - r2
            r6 = 60000(0xea60, double:2.9644E-319)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 >= 0) goto L18
            return
        L18:
            java.util.HashMap<java.lang.Long, com.loc.dv> r2 = r14.f3751a
            int r3 = r15.size()
            r6 = 0
            r7 = 0
        L20:
            r8 = 4
            r9 = 3
            r10 = 2
            r11 = 1
            if (r7 >= r3) goto L64
            java.lang.Object r12 = r15.get(r7)
            com.loc.dv r12 = (com.loc.dv) r12
            boolean r13 = r12.p
            if (r13 == 0) goto L61
            int r13 = r12.k
            if (r13 == r11) goto L40
            if (r13 == r10) goto L3b
            if (r13 == r9) goto L40
            if (r13 == r8) goto L40
            goto L48
        L3b:
            int r4 = r12.h
            int r5 = r12.i
            goto L44
        L40:
            int r4 = r12.c
            int r5 = r12.d
        L44:
            long r4 = a(r4, r5)
        L48:
            java.lang.Long r8 = java.lang.Long.valueOf(r4)
            java.lang.Object r8 = r2.get(r8)
            com.loc.dv r8 = (com.loc.dv) r8
            if (r8 == 0) goto L61
            int r9 = r8.j
            int r10 = r12.j
            if (r9 != r10) goto L5f
            long r8 = r8.m
            r12.m = r8
            goto L61
        L5f:
            r12.m = r0
        L61:
            int r7 = r7 + 1
            goto L20
        L64:
            r2.clear()
            int r3 = r15.size()
        L6b:
            if (r6 >= r3) goto L99
            java.lang.Object r7 = r15.get(r6)
            com.loc.dv r7 = (com.loc.dv) r7
            boolean r12 = r7.p
            if (r12 == 0) goto L96
            int r12 = r7.k
            if (r12 == r11) goto L87
            if (r12 == r10) goto L82
            if (r12 == r9) goto L87
            if (r12 == r8) goto L87
            goto L8f
        L82:
            int r4 = r7.h
            int r5 = r7.i
            goto L8b
        L87:
            int r4 = r7.c
            int r5 = r7.d
        L8b:
            long r4 = a(r4, r5)
        L8f:
            java.lang.Long r12 = java.lang.Long.valueOf(r4)
            r2.put(r12, r7)
        L96:
            int r6 = r6 + 1
            goto L6b
        L99:
            r14.b = r0
        L9b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.du.a(java.util.ArrayList):void");
    }
}
