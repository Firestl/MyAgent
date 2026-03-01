package com.g.gysdk.a;

import android.content.Context;
import android.text.TextUtils;
import com.g.gysdk.EloginActivityParam;
import com.g.gysdk.GYResponse;
import com.g.gysdk.GyCallBack;
import com.g.gysdk.GyCode;
import com.g.gysdk.GyErrorCode;
import com.g.gysdk.GyPreloginResult;
import com.g.gysdk.a.al;
import com.g.gysdk.a.an;
import com.getui.gtc.base.util.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.g.gysdk.a[] f1985a = new com.g.gysdk.a[0];
    public static final AtomicInteger b = new AtomicInteger(0);
    public static final List<com.g.gysdk.a> c = new ArrayList();
    public static final AtomicInteger d = new AtomicInteger(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final List<com.g.gysdk.a> f1986e = new ArrayList();
    public static final AtomicInteger f = new AtomicInteger(0);
    public static final AtomicInteger g = new AtomicInteger(0);
    public static final AtomicInteger h = new AtomicInteger(0);

    public static class a extends com.g.gysdk.a {
        public a(GyCallBack gyCallBack, boolean z) {
            super(gyCallBack, z);
        }
    }

    public static GyPreloginResult a() {
        GyPreloginResult gyPreloginResult = new GyPreloginResult();
        gyPreloginResult.setValid(a(false));
        gyPreloginResult.setOperator(ar.a(false, -1).f1981e);
        String[] strArrI = s.a().i();
        gyPreloginResult.setPrivacyName(strArrI[0]);
        gyPreloginResult.setPrivacyUrl(strArrI[1]);
        return gyPreloginResult;
    }

    public static void a(final int i, final com.g.gysdk.a aVar) {
        synchronized (f1986e) {
            if (d.get() == 1) {
                f1986e.add(aVar);
                return;
            }
            d.set(1);
            final long jCurrentTimeMillis = System.currentTimeMillis();
            an.a("preLogin", i).a("gy_init").a(new an.b() { // from class: com.g.gysdk.a.c.3
                @Override // com.g.gysdk.a.an.b
                public void a(final an anVar) {
                    if (c.b.get() != 2) {
                        anVar.a(new com.g.gysdk.b(GyCode.PRELOGIN_ERROR, GyErrorCode.SDK_INIT_ERROR, ""));
                    } else {
                        s.a().a(jCurrentTimeMillis, i, new r() { // from class: com.g.gysdk.a.c.3.1
                            @Override // com.g.gysdk.a.r
                            public void a(com.g.gysdk.b bVar) {
                                anVar.a(bVar);
                            }
                        });
                    }
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    com.g.gysdk.b bVarB = c.b(GyCode.PRELOGIN_ERROR, obj, th);
                    bVarB.a("timeout", Integer.valueOf(i));
                    if (bVarB.a()) {
                        aj.a("preLogin success, response:" + bVarB.toString());
                    } else {
                        aj.b("preLogin failed, response:" + bVarB.toString());
                    }
                    if (bVarB.d() == null) {
                        bVarB.a("preLogin", "");
                    }
                    bVarB.a("isAuto", Boolean.valueOf(aVar instanceof a));
                    com.g.gysdk.a.a(aVar, bVarB);
                    synchronized (c.f1986e) {
                        c.d.set(bVarB.a() ? 2 : 3);
                        if (c.f1986e.isEmpty()) {
                            return;
                        }
                        com.g.gysdk.a[] aVarArr = (com.g.gysdk.a[]) c.f1986e.toArray(c.f1985a);
                        c.f1986e.clear();
                        for (com.g.gysdk.a aVar2 : aVarArr) {
                            bVarB.a("isAuto", Boolean.valueOf(aVar2 instanceof a));
                            com.g.gysdk.a.a(aVar2, bVarB);
                        }
                    }
                }
            });
        }
    }

    public static void a(final Context context, final com.g.gysdk.a aVar) {
        com.g.gysdk.b bVar;
        try {
            if (context == null) {
                throw new IllegalArgumentException("context is null");
            }
            if (!CommonUtil.isMainProcess(context)) {
                throw new IllegalStateException("not main process");
            }
            if (b.compareAndSet(0, 1)) {
                an.b("gy_init").a(new an.b() { // from class: com.g.gysdk.a.c.1
                    @Override // com.g.gysdk.a.an.b
                    public void a(an anVar) {
                        GyErrorCode gyErrorCodeA = d.a(context);
                        if (gyErrorCodeA != GyErrorCode.SUCCESS) {
                            anVar.a(new com.g.gysdk.b(GyCode.UNKNOWN_ERROR, gyErrorCodeA, ""));
                            return;
                        }
                        j.a();
                        com.g.gysdk.view.c.a().b();
                        s.a().d();
                        e.c();
                        al.a(al.b.Queue, new Runnable() { // from class: com.g.gysdk.a.c.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    c.k();
                                    b.a();
                                    com.g.gysdk.view.b.b();
                                } catch (Throwable th) {
                                    ak.e("GyLogic init not important job", th);
                                }
                            }
                        }, true);
                        anVar.a(new com.g.gysdk.b(GyCode.SUCCESS, GyErrorCode.SUCCESS, ""));
                    }

                    @Override // com.g.gysdk.a.an.b
                    public void a(Object obj, Throwable th) {
                        com.g.gysdk.b bVarA = com.g.gysdk.b.a(obj);
                        if (bVarA == null) {
                            bVarA = com.g.gysdk.b.a(GyCode.UNKNOWN_ERROR, th);
                        }
                        if (bVarA.a()) {
                            aj.a("gy init success");
                        } else {
                            aj.b("gy init error, response:" + bVarA.toString());
                        }
                        bVarA.a("init", "");
                        com.g.gysdk.a.a(aVar, bVarA);
                        synchronized (c.c) {
                            c.b.set(bVarA.a() ? 2 : 3);
                            if (c.c.isEmpty()) {
                                return;
                            }
                            com.g.gysdk.a[] aVarArr = (com.g.gysdk.a[]) c.c.toArray(c.f1985a);
                            c.c.clear();
                            for (com.g.gysdk.a aVar2 : aVarArr) {
                                com.g.gysdk.a.a(aVar2, bVarA);
                            }
                        }
                    }
                });
                return;
            }
            aj.b("gysdk already inited, please not init again!");
            aVar.a(false);
            synchronized (c) {
                int i = b.get();
                if (i == 1) {
                    c.add(aVar);
                    return;
                }
                if (i == 2) {
                    bVar = new com.g.gysdk.b(GyCode.SUCCESS, GyErrorCode.SUCCESS, "already inited");
                } else if (i != 3) {
                    return;
                } else {
                    bVar = new com.g.gysdk.b(GyCode.UNKNOWN_ERROR, GyErrorCode.SDK_INIT_ERROR, "already inited");
                }
                com.g.gysdk.a.a(aVar, bVar);
            }
        } catch (Throwable th) {
            aj.a("gysdk init exception:", th);
            com.g.gysdk.b bVar2 = new com.g.gysdk.b(GyCode.UNKNOWN_ERROR, GyErrorCode.SDK_INIT_ERROR, am.a(th));
            bVar2.a("init", "");
            com.g.gysdk.a.a(aVar, bVar2);
        }
    }

    public static void a(final String str, final int i, final com.g.gysdk.a aVar) {
        if (g.get() == 1) {
            aj.b("preVerify doing, pls retry later");
            com.g.gysdk.a.a(aVar, new com.g.gysdk.b(GyCode.PREVERIFY_ERROR, GyErrorCode.OPERATE_DOING, "").a("getAccessCode", ""));
        } else {
            g.set(1);
            final long jCurrentTimeMillis = System.currentTimeMillis();
            an.a("preVerify", i).a("gy_init").a(new an.b() { // from class: com.g.gysdk.a.c.5
                @Override // com.g.gysdk.a.an.b
                public void a(final an anVar) {
                    if (c.b.get() != 2) {
                        anVar.a(new com.g.gysdk.b(GyCode.PREVERIFY_ERROR, GyErrorCode.SDK_INIT_ERROR, ""));
                    } else {
                        s.a().a(str, jCurrentTimeMillis, i, new r() { // from class: com.g.gysdk.a.c.5.1
                            @Override // com.g.gysdk.a.r
                            public void a(com.g.gysdk.b bVar) {
                                anVar.a(bVar);
                            }
                        });
                    }
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    com.g.gysdk.b bVarB = c.b(GyCode.PREVERIFY_ERROR, obj, th);
                    if (bVarB.a()) {
                        c.g.set(2);
                        aj.a("preVerify success, response:" + bVarB.toString());
                    } else {
                        c.g.set(3);
                        aj.b("preVerify failed, response:" + bVarB.toString());
                    }
                    if (bVarB.d() == null) {
                        bVarB.a("getAccessCode", "");
                    }
                    com.g.gysdk.a.a(aVar, bVarB);
                }
            });
        }
    }

    public static void a(final String str, final String str2, final String str3, final int i, final int i2, final com.g.gysdk.a aVar) {
        if (h.get() == 1) {
            aj.b("verify doing, pls retry later");
            com.g.gysdk.a.a(aVar, new com.g.gysdk.b(GyCode.VERIFY_ERROR, GyErrorCode.OPERATE_DOING, ""));
        } else {
            h.set(1);
            final long jCurrentTimeMillis = System.currentTimeMillis();
            an.a("verify", i2).a(1, 2).a(new an.b() { // from class: com.g.gysdk.a.c.6
                @Override // com.g.gysdk.a.an.b
                public void a(final an anVar) {
                    if (c.b.get() != 2) {
                        anVar.a(new com.g.gysdk.b(GyCode.VERIFY_ERROR, GyErrorCode.SDK_INIT_ERROR, ""));
                    } else {
                        s.a().a(str, str2, str3, i, jCurrentTimeMillis, i2, new r() { // from class: com.g.gysdk.a.c.6.1
                            @Override // com.g.gysdk.a.r
                            public void a(com.g.gysdk.b bVar) {
                                anVar.a(bVar);
                            }
                        });
                    }
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    com.g.gysdk.b bVarB = c.b(GyCode.VERIFY_ERROR, obj, th);
                    if (bVarB.a()) {
                        c.h.set(2);
                        aj.a("verify success, response:" + bVarB.toString());
                    } else {
                        c.h.set(3);
                        aj.b("verify failed, response:" + bVarB.toString());
                    }
                    com.g.gysdk.a.a(aVar, bVarB);
                }
            });
        }
    }

    public static void a(final boolean z, final EloginActivityParam eloginActivityParam, final int i, final com.g.gysdk.a aVar) {
        if (f.get() == 1) {
            aj.b("login doing, pls retry later");
            com.g.gysdk.a.a(aVar, new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.OPERATE_DOING, "重复").a("eLogin", s.a().f()));
        } else {
            f.set(1);
            final long jCurrentTimeMillis = System.currentTimeMillis();
            an.a("login", i).a(1, 2).a(new an.b() { // from class: com.g.gysdk.a.c.4
                @Override // com.g.gysdk.a.an.b
                public void a(final an anVar) {
                    com.g.gysdk.b bVar;
                    if (c.b.get() != 2) {
                        bVar = new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.SDK_INIT_ERROR, "");
                    } else if (!s.a().e()) {
                        bVar = new com.g.gysdk.b(GyCode.LOGIN_ERROR, GyErrorCode.INVALID_PRELOGIN, "预登录无效");
                    } else {
                        if (!z) {
                            b.a("clickOnLogin", GyErrorCode.SUCCESS, s.a().f(), d.a());
                            s.a().b(jCurrentTimeMillis, i, new r() { // from class: com.g.gysdk.a.c.4.1
                                @Override // com.g.gysdk.a.r
                                public void a(com.g.gysdk.b bVar2) {
                                    anVar.a(bVar2);
                                }
                            });
                            return;
                        }
                        bVar = new com.g.gysdk.b(GyCode.SUCCESS, GyErrorCode.SUCCESS, "开启页面监听");
                    }
                    anVar.a(bVar);
                }

                @Override // com.g.gysdk.a.an.b
                public void a(Object obj, Throwable th) {
                    com.g.gysdk.b bVarB = c.b(GyCode.LOGIN_ERROR, obj, th);
                    if (bVarB.a()) {
                        c.f.set(2);
                        if (z) {
                            aj.a("login, start UIHelper");
                            com.g.gysdk.view.b.a(eloginActivityParam, i, aVar);
                            return;
                        } else {
                            aj.a("login success, response:" + bVarB.toString());
                        }
                    } else {
                        c.f.set(3);
                        aj.b("login failed, response:" + bVarB.toString());
                    }
                    if (bVarB.d() == null) {
                        bVarB.a("eLogin", s.a().f());
                    }
                    com.g.gysdk.a.a(aVar, bVarB);
                }
            });
        }
    }

    public static boolean a(boolean z) {
        return s.a().b(z);
    }

    public static com.g.gysdk.b b(GyCode gyCode, Object obj, Throwable th) {
        if (b.get() == 0) {
            return new com.g.gysdk.b(gyCode, GyErrorCode.SDK_NOT_INITED, "");
        }
        com.g.gysdk.b bVarA = com.g.gysdk.b.a(obj);
        if (bVarA != null && bVarA.a()) {
            return bVarA;
        }
        if (bVarA == null) {
            bVarA = e.a();
        }
        return bVarA == null ? com.g.gysdk.b.a(gyCode, th) : bVarA;
    }

    public static void k() {
        if (ar.a(d.c()) != GyErrorCode.SUCCESS) {
            ak.a("checkAutoPreLogin skipped, because no mobile data");
            return;
        }
        if (j.d()) {
            String strA = g.a().a(4);
            if (!TextUtils.isEmpty(strA)) {
                try {
                    if (System.currentTimeMillis() - Long.parseLong(strA) <= j.e() * 1000) {
                        return;
                    }
                } catch (Throwable th) {
                    ak.e("lastAutoPreLoginTime convert to long failed", th);
                }
            }
            a(10000, new a(new GyCallBack() { // from class: com.g.gysdk.a.c.2
                @Override // com.g.gysdk.GyCallBack
                public void onFailed(GYResponse gYResponse) {
                    ak.e("auto prelogin failed:" + gYResponse);
                }

                @Override // com.g.gysdk.GyCallBack
                public void onSuccess(GYResponse gYResponse) {
                    g.a().a(4, String.valueOf(System.currentTimeMillis()));
                    ak.a("auto prelogin success:" + gYResponse);
                }
            }, true));
        }
    }
}
