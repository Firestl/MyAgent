package com.unicom.xiaowo.account.shield.c;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.tencent.connect.common.Constants;
import com.unicom.xiaowo.account.shield.e.c;
import com.unicom.xiaowo.account.shield.e.e;
import com.unicom.xiaowo.account.shield.e.g;
import com.unicom.xiaowo.account.shield.e.i;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public b c;
    public String d;
    public ExecutorService b = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ScheduledExecutorService f5534a = Executors.newScheduledThreadPool(1);

    /* JADX INFO: renamed from: com.unicom.xiaowo.account.shield.c.a$a, reason: collision with other inner class name */
    public interface InterfaceC0136a {
        void a(String str);
    }

    private String a(Context context, int i, String str) {
        try {
            String packageName = context.getPackageName();
            String strB = i.b(context, context.getPackageName());
            if (packageName == null) {
                packageName = "";
            }
            if (strB == null) {
                strB = "";
            }
            String strA = g.a();
            String str2 = i != 2 ? "1" : "";
            String str3 = "" + System.currentTimeMillis();
            String strA2 = com.unicom.xiaowo.account.shield.a.b.a(i.c(context).getBytes());
            String strD = i.d(str);
            String strA3 = i.a(str2 + strA + "30100jsonp" + strA2 + strD + "1" + packageName + strB + str3 + "5.2.3AR002B0427" + g.b());
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.PARAM_CLIENT_ID, strA);
            jSONObject.put("client_type", "30100");
            jSONObject.put("format", com.unicom.xiaowo.account.shield.a.b.a("jsonp"));
            jSONObject.put("version", com.unicom.xiaowo.account.shield.a.b.a("5.2.3AR002B0427"));
            if (i != 2) {
                jSONObject.put("business_type", com.unicom.xiaowo.account.shield.a.b.a(str2));
            }
            jSONObject.put("packname", com.unicom.xiaowo.account.shield.a.b.a(packageName));
            jSONObject.put("packsign", com.unicom.xiaowo.account.shield.a.b.a(strB));
            jSONObject.put("timeStamp", com.unicom.xiaowo.account.shield.a.b.a(str3));
            jSONObject.put("key", com.unicom.xiaowo.account.shield.a.b.a(strD));
            jSONObject.put("fp", com.unicom.xiaowo.account.shield.a.b.a(strA2));
            jSONObject.put("marking", "1");
            jSONObject.put(WeiXinPay.PayInfoResult.KEY_SIGN, com.unicom.xiaowo.account.shield.a.b.a(strA3));
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            if (this.f5534a != null) {
                this.f5534a.shutdownNow();
                this.f5534a = null;
            }
        } catch (Exception unused) {
        }
    }

    private void a(final Context context, final int i) {
        this.d = com.unicom.xiaowo.account.shield.a.a.a();
        a(context, i, new com.unicom.xiaowo.account.shield.d.a() { // from class: com.unicom.xiaowo.account.shield.c.a.2
            @Override // com.unicom.xiaowo.account.shield.d.a
            public void a(int i2, String str) {
                synchronized (a.this) {
                    if (a.this.c == null) {
                        if (i == 1 && g.e() && i2 == 0) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                if (jSONObject.optInt("code", 1) == 0) {
                                    String strDecode = URLDecoder.decode(com.unicom.xiaowo.account.shield.a.a.a(jSONObject.optString("data"), a.this.d), "UTF-8");
                                    if (!TextUtils.isEmpty(strDecode)) {
                                        g.a(context, strDecode);
                                    }
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        return;
                    }
                    if (i2 == 0) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str);
                            int iOptInt = jSONObject2.optInt("code", 1);
                            String strOptString = jSONObject2.optString("msg", "未知错误");
                            String strOptString2 = jSONObject2.optString("data");
                            if (iOptInt == 0) {
                                String strDecode2 = URLDecoder.decode(com.unicom.xiaowo.account.shield.a.a.a(strOptString2, a.this.d), "UTF-8");
                                if (a.this.c != null) {
                                    a.this.c.a(strOptString, strDecode2);
                                }
                            } else if (a.this.c != null) {
                                a.this.c.a(iOptInt, strOptString, strOptString2);
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            if (a.this.c != null) {
                                a.this.c.a(10002, "异常" + e3.getMessage(), str);
                            }
                        }
                    } else if (a.this.c != null) {
                        a.this.c.a(i2, str);
                    }
                    a.this.c = null;
                    a.this.a();
                    return;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, int i, String str, final Network network, final com.unicom.xiaowo.account.shield.d.a aVar) {
        synchronized (this) {
            if (this.b == null || this.c == null) {
                return;
            }
            try {
                final String str2 = str + e.a(a(context, i, this.d), "&");
                this.b.submit(new Runnable() { // from class: com.unicom.xiaowo.account.shield.c.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            String strA = new com.unicom.xiaowo.account.shield.d.b().a(str2, a.this.b(), network);
                            if (TextUtils.isEmpty(strA)) {
                                aVar.a(10022, "网络请求响应为空");
                            } else {
                                aVar.a(0, strA);
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            } catch (Exception e2) {
                aVar.a(10009, "10009" + e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> b() {
        HashMap<String, String> map = new HashMap<>();
        map.put("model", Build.MODEL);
        map.put(ConstantHelper.LOG_OS, Build.VERSION.RELEASE);
        map.put("woodcock", g.d());
        return map;
    }

    public void a(Context context, int i, int i2, InterfaceC0136a interfaceC0136a) {
        this.c = new b(interfaceC0136a);
        try {
            a();
            ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1);
            this.f5534a = scheduledExecutorServiceNewScheduledThreadPool;
            scheduledExecutorServiceNewScheduledThreadPool.schedule(new Runnable() { // from class: com.unicom.xiaowo.account.shield.c.a.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (a.this) {
                        if (a.this.c != null) {
                            a.this.c.a(10000, "请求超时");
                            a.this.c = null;
                            a.this.a();
                        }
                    }
                }
            }, i, TimeUnit.MILLISECONDS);
            a(context, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(final Context context, final int i, final com.unicom.xiaowo.account.shield.d.a aVar) {
        try {
            int iA = i.a(context.getApplicationContext());
            g.b(iA);
            if (iA == 1) {
                com.unicom.xiaowo.account.shield.e.c.a().a(context, "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?", new c.a() { // from class: com.unicom.xiaowo.account.shield.c.a.4
                    @Override // com.unicom.xiaowo.account.shield.e.c.a
                    public void a(boolean z, Network network) {
                        if (a.this.c == null) {
                            return;
                        }
                        if (z) {
                            a.this.a(context, i, "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?", network, aVar);
                        } else {
                            aVar.a(10003, "无法切换至数据网络");
                        }
                    }
                });
            } else if (iA == 0) {
                a(context, i, "https://opencloud.wostore.cn/openapi/netauth/precheck/wp?", null, aVar);
            } else {
                aVar.a(10004, "数据网络未开启");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            aVar.a(10005, "网络判断异常" + e2.getMessage());
        }
    }

    public void finalize() {
        ScheduledExecutorService scheduledExecutorService = this.f5534a;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.f5534a = null;
        }
        ExecutorService executorService = this.b;
        if (executorService != null) {
            executorService.shutdownNow();
            this.b = null;
        }
        this.c = null;
        this.d = null;
    }
}
