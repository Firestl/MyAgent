package com.umeng.analytics.pro;

import com.taobao.weex.wson.Wson;
import com.umeng.analytics.pro.co;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: TDeserializer.java */
/* JADX INFO: loaded from: classes2.dex */
public class by {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cu f5219a;
    public final dh b;

    public by() {
        this(new co.a());
    }

    private cp j(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        this.b.a(bArr);
        int length = ccVarArr.length + 1;
        cc[] ccVarArr2 = new cc[length];
        int i = 0;
        ccVarArr2[0] = ccVar;
        int i2 = 0;
        while (i2 < ccVarArr.length) {
            int i3 = i2 + 1;
            ccVarArr2[i3] = ccVarArr[i2];
            i2 = i3;
        }
        this.f5219a.j();
        cp cpVarL = null;
        while (i < length) {
            cpVarL = this.f5219a.l();
            if (cpVarL.b == 0 || cpVarL.c > ccVarArr2[i].a()) {
                return null;
            }
            if (cpVarL.c != ccVarArr2[i].a()) {
                cx.a(this.f5219a, cpVarL.b);
                this.f5219a.m();
            } else {
                i++;
                if (i < length) {
                    this.f5219a.j();
                }
            }
        }
        return cpVarL;
    }

    public void a(bv bvVar, byte[] bArr) throws cb {
        try {
            this.b.a(bArr);
            bvVar.read(this.f5219a);
        } finally {
            this.b.e();
            this.f5219a.B();
        }
    }

    public Byte b(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Byte) a((byte) 3, bArr, ccVar, ccVarArr);
    }

    public Double c(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Double) a((byte) 4, bArr, ccVar, ccVarArr);
    }

    public Short d(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Short) a((byte) 6, bArr, ccVar, ccVarArr);
    }

    public Integer e(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Integer) a((byte) 8, bArr, ccVar, ccVarArr);
    }

    public Long f(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Long) a((byte) 10, bArr, ccVar, ccVarArr);
    }

    public String g(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (String) a((byte) 11, bArr, ccVar, ccVarArr);
    }

    public ByteBuffer h(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (ByteBuffer) a(Wson.NUMBER_DOUBLE_TYPE, bArr, ccVar, ccVarArr);
    }

    public Short i(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        Short shValueOf;
        try {
            try {
                if (j(bArr, ccVar, ccVarArr) != null) {
                    this.f5219a.j();
                    shValueOf = Short.valueOf(this.f5219a.l().c);
                } else {
                    shValueOf = null;
                }
                return shValueOf;
            } catch (Exception e2) {
                throw new cb(e2);
            }
        } finally {
            this.b.e();
            this.f5219a.B();
        }
    }

    public by(cw cwVar) {
        dh dhVar = new dh();
        this.b = dhVar;
        this.f5219a = cwVar.a(dhVar);
    }

    public void a(bv bvVar, String str, String str2) throws cb {
        try {
            try {
                a(bvVar, str.getBytes(str2));
            } catch (UnsupportedEncodingException unused) {
                throw new cb("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.f5219a.B();
        }
    }

    public void a(bv bvVar, byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        try {
            try {
                if (j(bArr, ccVar, ccVarArr) != null) {
                    bvVar.read(this.f5219a);
                }
            } catch (Exception e2) {
                throw new cb(e2);
            }
        } finally {
            this.b.e();
            this.f5219a.B();
        }
    }

    public Boolean a(byte[] bArr, cc ccVar, cc... ccVarArr) throws cb {
        return (Boolean) a((byte) 2, bArr, ccVar, ccVarArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object a(byte r1, byte[] r2, com.umeng.analytics.pro.cc r3, com.umeng.analytics.pro.cc... r4) throws com.umeng.analytics.pro.cb {
        /*
            r0 = this;
            com.umeng.analytics.pro.cp r2 = r0.j(r2, r3, r4)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r2 == 0) goto L9e
            r3 = 2
            if (r1 == r3) goto L8f
            r3 = 3
            if (r1 == r3) goto L80
            r3 = 4
            if (r1 == r3) goto L71
            r3 = 6
            if (r1 == r3) goto L62
            r3 = 8
            if (r1 == r3) goto L53
            r3 = 100
            r4 = 11
            if (r1 == r3) goto L48
            r3 = 10
            if (r1 == r3) goto L39
            if (r1 == r4) goto L24
            goto L9e
        L24:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r4) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.String r1 = r1.z()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
        L2e:
            com.umeng.analytics.pro.dh r2 = r0.b
            r2.e()
            com.umeng.analytics.pro.cu r2 = r0.f5219a
            r2.B()
            return r1
        L39:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r3) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            long r1 = r1.x()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L48:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r4) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.nio.ByteBuffer r1 = r1.A()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L53:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r3) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            int r1 = r1.w()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L62:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r3) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            short r1 = r1.v()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.Short r1 = java.lang.Short.valueOf(r1)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L71:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r3) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            double r1 = r1.y()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L80:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r3) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            byte r1 = r1.u()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L8f:
            byte r1 = r2.b     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            if (r1 != r3) goto L9e
            com.umeng.analytics.pro.cu r1 = r0.f5219a     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            boolean r1 = r1.t()     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> La0 java.lang.Exception -> La2
            goto L2e
        L9e:
            r1 = 0
            goto L2e
        La0:
            r1 = move-exception
            goto La9
        La2:
            r1 = move-exception
            com.umeng.analytics.pro.cb r2 = new com.umeng.analytics.pro.cb     // Catch: java.lang.Throwable -> La0
            r2.<init>(r1)     // Catch: java.lang.Throwable -> La0
            throw r2     // Catch: java.lang.Throwable -> La0
        La9:
            com.umeng.analytics.pro.dh r2 = r0.b
            r2.e()
            com.umeng.analytics.pro.cu r2 = r0.f5219a
            r2.B()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.by.a(byte, byte[], com.umeng.analytics.pro.cc, com.umeng.analytics.pro.cc[]):java.lang.Object");
    }

    public void a(bv bvVar, String str) throws cb {
        a(bvVar, str.getBytes());
    }
}
