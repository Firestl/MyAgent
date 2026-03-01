package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.APayEntranceActivity;
import com.alipay.sdk.app.AlipayResultActivity;
import com.igexin.push.g.o;
import com.taobao.weex.el.parse.Operators;
import com.vivo.push.PushClientConstants;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONObject;
import supwisdom.bq;
import supwisdom.po;
import supwisdom.pp;
import supwisdom.qo;
import supwisdom.so;
import supwisdom.vo;
import supwisdom.vp;
import supwisdom.zp;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f1621a;
    public volatile IAlixPay b;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f1622e;
    public final pp f;
    public final Object c = IAlixPay.class;
    public boolean g = false;
    public String h = null;
    public String i = null;

    public class a extends IRemoteServiceCallback.Stub {
        public a() {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public int getVersion() throws RemoteException {
            return 3;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void payEnd(boolean z, String str) throws RemoteException {
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void r03(String str, String str2, Map map) throws RemoteException {
            so.b(f.this.f, "wlt", str, str2);
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                bundle.putInt("CallingPid", i);
                intent.putExtras(bundle);
            } catch (Exception e2) {
                so.a(f.this.f, "biz", "ErrIntentEx", e2);
            }
            intent.setClassName(str, str2);
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                    ActivityManager.getMyMemoryState(runningAppProcessInfo);
                    so.b(f.this.f, "biz", "isFg", runningAppProcessInfo.processName + "|" + runningAppProcessInfo.importance + "|");
                }
            } catch (Throwable unused) {
            }
            try {
                if (f.this.f1621a != null) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    f.this.f1621a.startActivity(intent);
                    so.b(f.this.f, "biz", "stAct2", "" + (SystemClock.elapsedRealtime() - jElapsedRealtime));
                } else {
                    so.a(f.this.f, "biz", "ErrActNull", "");
                    Context contextC = f.this.f.c();
                    if (contextC != null) {
                        contextC.startActivity(intent);
                    }
                }
                f.this.f1622e.b();
            } catch (Throwable th) {
                so.a(f.this.f, "biz", "ErrActNull", th);
                throw th;
            }
        }

        public /* synthetic */ a(f fVar, b bVar) {
            this();
        }
    }

    public class b implements AlipayResultActivity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f1624a;

        public b(CountDownLatch countDownLatch) {
            this.f1624a = countDownLatch;
        }

        @Override // com.alipay.sdk.app.AlipayResultActivity.a
        public void a(int i, String str, String str2) {
            f.this.h = qo.a(i, str, str2);
            this.f1624a.countDown();
        }
    }

    public class c implements APayEntranceActivity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CountDownLatch f1625a;

        public c(CountDownLatch countDownLatch) {
            this.f1625a = countDownLatch;
        }

        @Override // com.alipay.sdk.app.APayEntranceActivity.a
        public void a(String str) {
            f.this.i = str;
            this.f1625a.countDown();
        }
    }

    public class d implements ServiceConnection {
        public d() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            so.a(f.this.f, "biz", "srvCon");
            synchronized (f.this.c) {
                f.this.b = IAlixPay.Stub.asInterface(iBinder);
                f.this.c.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            so.a(f.this.f, "biz", "srvDis");
            f.this.b = null;
        }

        public /* synthetic */ d(f fVar, b bVar) {
            this();
        }
    }

    public interface e {
        void a();

        void b();
    }

    public f(Activity activity, pp ppVar, e eVar) {
        this.f1621a = activity;
        this.f = ppVar;
        this.f1622e = eVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(java.lang.String r10, java.lang.String r11, android.content.pm.PackageInfo r12) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.f.b(java.lang.String, java.lang.String, android.content.pm.PackageInfo):java.lang.String");
    }

    public String a(String str) {
        String strA = "";
        try {
            List<vo.b> listR = vo.v().r();
            if (!vo.v().g || listR == null) {
                listR = po.d;
            }
            bq.b bVarA = bq.a(this.f, this.f1621a, listR);
            if (bVarA == null || bVarA.a(this.f) || bVarA.a() || bq.a(bVarA.f7091a)) {
                return AbsoluteConst.EVENTS_FAILED;
            }
            if (bVarA.f7091a != null && !"com.eg.android.AlipayGphone".equals(bVarA.f7091a.packageName)) {
                strA = bVarA.f7091a.packageName;
            } else {
                strA = bq.a();
            }
            packageInfo = bVarA.f7091a != null ? bVarA.f7091a : null;
            String strQ = vo.v().q();
            if (strQ != null && strQ.length() > 0) {
                try {
                    JSONObject jSONObjectOptJSONObject = new JSONObject(strQ).optJSONObject(strA);
                    if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() > 0) {
                        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            int i = Integer.parseInt(next);
                            if (packageInfo != null && packageInfo.versionCode >= i) {
                                try {
                                    boolean zA = vo.v().a(this.f1621a, Integer.parseInt(jSONObjectOptJSONObject.getString(next)));
                                    this.g = zA;
                                    if (zA) {
                                        break;
                                    }
                                } catch (Exception unused) {
                                    continue;
                                }
                            }
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
            if (!this.g && !vo.v().m()) {
                a(bVarA);
            }
        } catch (Throwable th) {
            so.a(this.f, "biz", "CheckClientSignEx", th);
        }
        if (this.g) {
            return a(str, strA, packageInfo);
        }
        return b(str, strA, packageInfo);
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public final String b(String str, String str2) {
        JSONObject jSONObject;
        String str3;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String strA = bq.a(32);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        so.b(this.f, "biz", "BSAStart", strA + "|" + jElapsedRealtime);
        pp.a.a(this.f, strA);
        APayEntranceActivity.c.put(strA, new c(countDownLatch));
        try {
            HashMap<String, String> mapA = pp.a(this.f);
            mapA.put("ts_intent", String.valueOf(jElapsedRealtime));
            jSONObject = new JSONObject(mapA);
        } catch (Throwable th) {
            try {
                so.a(this.f, "biz", "BSALocEx", th);
                jSONObject = null;
            } catch (InterruptedException e2) {
                so.a(this.f, "biz", "BSAWaiting", e2);
                return qo.a(com.alipay.sdk.app.c.PAY_WAITTING.a(), com.alipay.sdk.app.c.PAY_WAITTING.b(), "");
            } catch (Throwable th2) {
                so.a(this.f, "biz", "BSAEx", th2);
                return "scheme_failed";
            }
        }
        Intent intent = new Intent(this.f1621a, (Class<?>) APayEntranceActivity.class);
        intent.putExtra("ap_order_info", str);
        intent.putExtra("ap_target_packagename", str2);
        intent.putExtra("ap_session", strA);
        if (jSONObject != null) {
            intent.putExtra("ap_local_info", jSONObject.toString());
        }
        so.a(this.f1621a, this.f, str, this.f.d);
        this.f1621a.startActivity(intent);
        vo.v().a(this.f, this.f1621a.getApplicationContext());
        countDownLatch.await();
        String str4 = this.i;
        try {
            str3 = zp.a(this.f, str4).get("resultStatus");
            if (str3 == null) {
                str3 = com.igexin.push.core.b.m;
            }
        } catch (Throwable th3) {
            str3 = "unknown";
            so.a(this.f, "biz", "BSAStatEx", th3);
        }
        so.a(this.f, "biz", "BSADone-" + str3);
        if (!TextUtils.isEmpty(str4)) {
            return str4;
        }
        so.a(this.f, "biz", "BSAEmpty");
        return "scheme_failed";
    }

    public final void a(bq.b bVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (bVar == null || (packageInfo = bVar.f7091a) == null) {
            return;
        }
        String str = packageInfo.packageName;
        Intent intent = new Intent();
        intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.f1621a.startActivity(intent);
        } catch (Throwable th) {
            so.a(this.f, "biz", "StartLaunchAppTransEx", th);
        }
        Thread.sleep(200L);
    }

    public final String a(String str, String str2, PackageInfo packageInfo) {
        if (packageInfo != null) {
            int i = packageInfo.versionCode;
        }
        String str3 = packageInfo != null ? packageInfo.versionName : "";
        vp.b("mspl", "pay bind or scheme");
        so.b(this.f, "biz", "PgWltVer", str2 + "|" + str3);
        Activity activity = this.f1621a;
        pp ppVar = this.f;
        so.a(activity, ppVar, str, ppVar.d);
        return b(str, str2);
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    public final String a(String str, String str2) {
        String str3;
        JSONObject jSONObject;
        String strSubstring;
        String strSubstring2;
        String str4;
        String strReplace = str;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String strA = bq.a(32);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        so.b(this.f, "biz", "BSPStart", strA + "|" + jElapsedRealtime);
        pp.a.a(this.f, strA);
        AlipayResultActivity.f1599a.put(strA, new b(countDownLatch));
        try {
            try {
                String[] strArrSplit = strReplace.split("&", -1);
                int length = strArrSplit.length;
                int i = 0;
                while (true) {
                    jSONObject = null;
                    if (i >= length) {
                        strSubstring = "";
                        strSubstring2 = strSubstring;
                        str4 = null;
                        break;
                    }
                    str4 = strArrSplit[i];
                    if (str4.startsWith("bizcontext=")) {
                        String strSubstring3 = str4.substring(str4.indexOf(Operators.BLOCK_START_STR), str4.lastIndexOf(Operators.BLOCK_END_STR) + 1);
                        int iIndexOf = str4.indexOf(strSubstring3);
                        strSubstring2 = str4.substring(0, iIndexOf);
                        strSubstring = str4.substring(iIndexOf + strSubstring3.length());
                        JSONObject jSONObject2 = new JSONObject(strSubstring3);
                        if (jSONObject2.optString(o.f3603e).equals("h5tonative")) {
                            jSONObject2.put(o.f3603e, "h5tonative_scheme");
                        } else {
                            jSONObject2.put(o.f3603e, "h5tonative_sdkscheme");
                        }
                        jSONObject = jSONObject2;
                    } else {
                        i++;
                    }
                }
            } catch (Exception e2) {
                try {
                    so.a(this.f, "biz", "BSPSCReplaceEx", e2, Base64.encodeToString(str.getBytes(), 2));
                } catch (InterruptedException e3) {
                    so.a(this.f, "biz", "BSPWaiting", e3);
                    return qo.a(com.alipay.sdk.app.c.PAY_WAITTING.a(), com.alipay.sdk.app.c.PAY_WAITTING.b(), "");
                }
            }
            if (!TextUtils.isEmpty(str4)) {
                if (strReplace.indexOf(str4) == strReplace.lastIndexOf(str4)) {
                    strReplace = strReplace.replace(str4, strSubstring2 + jSONObject.toString() + strSubstring);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("sourcePid", Binder.getCallingPid());
                    jSONObject3.put("external_info", strReplace);
                    jSONObject3.put(PushClientConstants.TAG_PKG_NAME, this.f1621a.getPackageName());
                    jSONObject3.put(com.umeng.analytics.pro.f.aC, strA);
                    String strEncodeToString = Base64.encodeToString(jSONObject3.toString().getBytes("UTF-8"), 2);
                    Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path(com.igexin.push.core.b.q).appendQueryParameter("appId", "20000125");
                    builderAppendQueryParameter.appendQueryParameter("mqpSchemePay", strEncodeToString);
                    try {
                        HashMap<String, String> mapA = pp.a(this.f);
                        mapA.put("ts_scheme", String.valueOf(jElapsedRealtime));
                        builderAppendQueryParameter.appendQueryParameter("mqpLoc", new JSONObject(mapA).toString());
                    } catch (Throwable th) {
                        so.a(this.f, "biz", "BSPLocEx", th);
                    }
                    String string = builderAppendQueryParameter.build().toString();
                    Intent intent = new Intent();
                    intent.setPackage(str2);
                    intent.addFlags(268435456);
                    intent.setData(Uri.parse(string));
                    so.a(this.f1621a, this.f, strReplace, this.f.d);
                    this.f1621a.startActivity(intent);
                    vo.v().a(this.f, this.f1621a.getApplicationContext());
                    vp.b("mspl", "pay scheme waiting " + string);
                    countDownLatch.await();
                    String str5 = this.h;
                    try {
                        str3 = zp.a(this.f, str5).get("resultStatus");
                        if (str3 == null) {
                            str3 = com.igexin.push.core.b.m;
                        }
                    } catch (Throwable th2) {
                        so.a(this.f, "biz", "BSPStatEx", th2);
                        str3 = "unknown";
                    }
                    so.a(this.f, "biz", "BSPDone-" + str3);
                    if (!TextUtils.isEmpty(str5)) {
                        return str5;
                    }
                    so.a(this.f, "biz", "BSPEmpty");
                    return "scheme_failed";
                }
                throw new RuntimeException("multi ctx_args");
            }
            throw new RuntimeException("empty ctx_args");
        } catch (Throwable th3) {
            so.a(this.f, "biz", "BSPEx", th3);
            return "scheme_failed";
        }
    }

    public static boolean a(String str, Context context, pp ppVar) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            so.a(ppVar, "biz", "BSPDetectFail");
            return false;
        } catch (Throwable th) {
            so.a(ppVar, "biz", "BSPDetectFail", th);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:165:0x0272 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<java.lang.String, java.lang.Boolean> a(java.lang.String r18, java.lang.String r19, supwisdom.pp r20) {
        /*
            Method dump skipped, instruction units count: 814
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.f.a(java.lang.String, java.lang.String, supwisdom.pp):android.util.Pair");
    }

    public void a() {
        this.f1621a = null;
        this.f1622e = null;
    }
}
