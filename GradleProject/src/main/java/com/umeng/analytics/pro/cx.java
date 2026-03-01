package com.umeng.analytics.pro;

import com.umeng.analytics.pro.co;

/* JADX INFO: compiled from: TProtocolUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class cx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5248a = Integer.MAX_VALUE;

    public static void a(int i) {
        f5248a = i;
    }

    public static void a(cu cuVar, byte b) throws cb {
        a(cuVar, b, f5248a);
    }

    public static void a(cu cuVar, byte b, int i) throws cb {
        if (i > 0) {
            int i2 = 0;
            switch (b) {
                case 2:
                    cuVar.t();
                    return;
                case 3:
                    cuVar.u();
                    return;
                case 4:
                    cuVar.y();
                    return;
                case 5:
                case 7:
                case 9:
                default:
                    return;
                case 6:
                    cuVar.v();
                    return;
                case 8:
                    cuVar.w();
                    return;
                case 10:
                    cuVar.x();
                    return;
                case 11:
                    cuVar.A();
                    return;
                case 12:
                    cuVar.j();
                    while (true) {
                        byte b2 = cuVar.l().b;
                        if (b2 == 0) {
                            cuVar.k();
                            return;
                        } else {
                            a(cuVar, b2, i - 1);
                            cuVar.m();
                        }
                    }
                    break;
                case 13:
                    cr crVarN = cuVar.n();
                    while (i2 < crVarN.c) {
                        int i3 = i - 1;
                        a(cuVar, crVarN.f5243a, i3);
                        a(cuVar, crVarN.b, i3);
                        i2++;
                    }
                    cuVar.o();
                    return;
                case 14:
                    cy cyVarR = cuVar.r();
                    while (i2 < cyVarR.b) {
                        a(cuVar, cyVarR.f5249a, i - 1);
                        i2++;
                    }
                    cuVar.s();
                    return;
                case 15:
                    cq cqVarP = cuVar.p();
                    while (i2 < cqVarP.b) {
                        a(cuVar, cqVarP.f5242a, i - 1);
                        i2++;
                    }
                    cuVar.q();
                    return;
            }
        } else {
            throw new cb("Maximum skip depth exceeded");
        }
    }

    public static cw a(byte[] bArr, cw cwVar) {
        if (bArr[0] > 16) {
            return new co.a();
        }
        return (bArr.length <= 1 || (bArr[1] & com.igexin.c.a.d.g.n) == 0) ? cwVar : new co.a();
    }
}
