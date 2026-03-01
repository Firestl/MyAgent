package com.uc.crashsdk.a;

import com.huawei.agconnect.exception.AGCServerException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ boolean f5110a = !e.class.desiredAssertionStatus();
    public final int b;
    public final Object[] c;

    public e(int i) {
        this.b = i;
        this.c = null;
    }

    public final boolean a() {
        int i = this.b;
        if (i == 451 || i == 452) {
            return com.uc.crashsdk.e.b(this.b, this.c);
        }
        switch (i) {
            case 351:
            case 352:
            case 353:
            case 354:
                return h.b(i, this.c);
            default:
                switch (i) {
                    case 751:
                    case 752:
                    case 753:
                    case 754:
                    case 755:
                    case 756:
                        return com.uc.crashsdk.f.a(i, this.c);
                    default:
                        a.d("crashsdk", "Unknown sync runnable: " + toString());
                        if (f5110a) {
                            return false;
                        }
                        throw new AssertionError();
                }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = this.b;
        if (i == 10) {
            f.a(i, this.c);
            return;
        }
        if (i == 500) {
            d.a(i);
            return;
        }
        if (i == 700) {
            com.uc.crashsdk.f.b(i);
            return;
        }
        if (i == 800) {
            g.a(i);
            return;
        }
        if (i == 201 || i == 202) {
            com.uc.crashsdk.a.a(this.b);
            return;
        }
        switch (i) {
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
                com.uc.crashsdk.b.a(i);
                return;
            default:
                switch (i) {
                    case 301:
                    case com.umeng.ccg.c.o /* 302 */:
                    case com.umeng.ccg.c.p /* 303 */:
                        h.a(i, this.c);
                        return;
                    default:
                        switch (i) {
                            case AGCServerException.TOKEN_INVALID /* 401 */:
                            case 402:
                            case AGCServerException.AUTHENTICATION_FAILED /* 403 */:
                                break;
                            default:
                                switch (i) {
                                    case 405:
                                    case 406:
                                    case 407:
                                    case 408:
                                    case 409:
                                    case 410:
                                    case 411:
                                    case 412:
                                    case 413:
                                    case 414:
                                    case 415:
                                    case 416:
                                        break;
                                    default:
                                        a.d("crashsdk", "Unknown async runnable: " + toString());
                                        if (!f5110a) {
                                            throw new AssertionError();
                                        }
                                        return;
                                }
                                break;
                        }
                        com.uc.crashsdk.e.a(this.b, this.c);
                        return;
                }
        }
    }

    public String toString() {
        return super.toString() + "@action_" + this.b;
    }

    public e(int i, Object[] objArr) {
        this.b = i;
        this.c = objArr;
    }
}
