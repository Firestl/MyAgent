package org.bouncycastle.crypto.params;

import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class DESParameters extends KeyParameter {
    public static final int DES_KEY_LENGTH = 8;
    public static byte[] DES_weak_keys = {1, 1, 1, 1, 1, 1, 1, 1, co.j, co.j, co.j, co.j, db.l, db.l, db.l, db.l, co.k, co.k, co.k, co.k, -15, -15, -15, -15, -2, -2, -2, -2, -2, -2, -2, -2, 1, -2, 1, -2, 1, -2, 1, -2, co.j, co.k, co.j, co.k, db.l, -15, db.l, -15, 1, co.k, 1, co.k, 1, -15, 1, -15, co.j, -2, co.j, -2, db.l, -2, db.l, -2, 1, co.j, 1, co.j, 1, db.l, 1, db.l, co.k, -2, co.k, -2, -15, -2, -15, -2, -2, 1, -2, 1, -2, 1, -2, 1, co.k, co.j, co.k, co.j, -15, db.l, -15, db.l, co.k, 1, co.k, 1, -15, 1, -15, 1, -2, co.j, -2, co.j, -2, db.l, -2, db.l, co.j, 1, co.j, 1, db.l, 1, db.l, 1, -2, co.k, -2, co.k, -2, -15, -2, -15};
    public static final int N_DES_WEAK_KEYS = 16;

    public DESParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0)) {
            throw new IllegalArgumentException("attempt to create weak DES key");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isWeakKey(byte[] r7, int r8) {
        /*
            int r0 = r7.length
            int r0 = r0 - r8
            r1 = 8
            if (r0 < r1) goto L25
            r0 = 0
            r2 = 0
        L8:
            r3 = 16
            if (r2 >= r3) goto L24
            r3 = 0
        Ld:
            if (r3 >= r1) goto L22
            int r4 = r3 + r8
            r4 = r7[r4]
            byte[] r5 = org.bouncycastle.crypto.params.DESParameters.DES_weak_keys
            int r6 = r2 * 8
            int r6 = r6 + r3
            r5 = r5[r6]
            if (r4 == r5) goto L1f
            int r2 = r2 + 1
            goto L8
        L1f:
            int r3 = r3 + 1
            goto Ld
        L22:
            r7 = 1
            return r7
        L24:
            return r0
        L25:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "key material too short."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.params.DESParameters.isWeakKey(byte[], int):boolean");
    }

    public static void setOddParity(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            bArr[i] = (byte) (((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) & 1) | (b & UTF8.S_ERR));
        }
    }
}
