package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.util.f;
import com.igexin.push.g.o;
import com.lzy.okgo.cookie.SerializableCookie;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;
import supwisdom.bq;
import supwisdom.cq;
import supwisdom.np;
import supwisdom.op;
import supwisdom.po;
import supwisdom.pp;
import supwisdom.qo;
import supwisdom.qp;
import supwisdom.rp;
import supwisdom.so;
import supwisdom.sp;
import supwisdom.uo;
import supwisdom.vo;
import supwisdom.vp;
import supwisdom.wp;
import supwisdom.zp;

/* JADX INFO: loaded from: classes.dex */
public class PayTask {
    public static final Object d = f.class;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f1609e = 0;
    public static long f = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f1610a;
    public cq b;
    public Map<String, c> c = new HashMap();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1611a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ H5PayCallback c;

        public a(String str, boolean z, H5PayCallback h5PayCallback) {
            this.f1611a = str;
            this.b = z;
            this.c = h5PayCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            sp spVarH5Pay = PayTask.this.h5Pay(new pp(PayTask.this.f1610a, this.f1611a, "payInterceptorWithUrl"), this.f1611a, this.b);
            vp.b("mspl", "inc finished: " + spVarH5Pay.a());
            this.c.onPayResult(spVarH5Pay);
        }
    }

    public class b implements f.e {
        public b() {
        }

        @Override // com.alipay.sdk.util.f.e
        public void a() {
        }

        @Override // com.alipay.sdk.util.f.e
        public void b() {
            PayTask.this.dismissLoading();
        }
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1613a;
        public String b;
        public String c;
        public String d;

        public c(PayTask payTask) {
            this.f1613a = "";
            this.b = "";
            this.c = "";
            this.d = "";
        }

        public String a() {
            return this.f1613a;
        }

        public String b() {
            return this.c;
        }

        public String c() {
            return this.b;
        }

        public String d() {
            return this.d;
        }

        public void a(String str) {
            this.f1613a = str;
        }

        public void b(String str) {
            this.c = str;
        }

        public void c(String str) {
            this.b = str;
        }

        public void d(String str) {
            this.d = str;
        }

        public /* synthetic */ c(PayTask payTask, a aVar) {
            this(payTask);
        }
    }

    public PayTask(Activity activity) {
        this.f1610a = activity;
        qp.d().a(this.f1610a);
        this.b = new cq(activity, "去支付宝付款");
    }

    public static boolean b() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - f < 3000) {
            return true;
        }
        f = jElapsedRealtime;
        return false;
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        try {
            qp.d().a(context);
            long jElapsedRealtime = SystemClock.elapsedRealtime() / 1000;
            if (jElapsedRealtime - f1609e < vo.v().f()) {
                return false;
            }
            f1609e = jElapsedRealtime;
            vo.v().a(pp.e(), context.getApplicationContext());
            return true;
        } catch (Exception e2) {
            vp.a(e2);
            return false;
        }
    }

    public void dismissLoading() {
        cq cqVar = this.b;
        if (cqVar != null) {
            cqVar.b();
            this.b = null;
        }
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String strTrim = str.trim();
                if (strTrim.startsWith("https://wappaygw.alipay.com/service/rest.htm") || strTrim.startsWith("http://wappaygw.alipay.com/service/rest.htm")) {
                    String strTrim2 = strTrim.replaceFirst("(http|https)://wappaygw.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(strTrim2)) {
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + bq.a("<request_token>", "</request_token>", bq.b(strTrim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new pp(this.f1610a, "", "").a(o.f3603e, "h5tonative") + JSUtil.QUOTE;
                    }
                }
                if (strTrim.startsWith("https://mclient.alipay.com/service/rest.htm") || strTrim.startsWith("http://mclient.alipay.com/service/rest.htm")) {
                    String strTrim3 = strTrim.replaceFirst("(http|https)://mclient.alipay.com/service/rest.htm\\?", "").trim();
                    if (!TextUtils.isEmpty(strTrim3)) {
                        return "_input_charset=\"utf-8\"&ordertoken=\"" + bq.a("<request_token>", "</request_token>", bq.b(strTrim3).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new pp(this.f1610a, "", "").a(o.f3603e, "h5tonative") + JSUtil.QUOTE;
                    }
                }
                if ((strTrim.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm") || strTrim.startsWith("http://mclient.alipay.com/home/exterfaceAssign.htm")) && ((strTrim.contains("alipay.wap.create.direct.pay.by.user") || strTrim.contains("create_forex_trade_wap")) && !TextUtils.isEmpty(strTrim.replaceFirst("(http|https)://mclient.alipay.com/home/exterfaceAssign.htm\\?", "").trim()))) {
                    pp ppVar = new pp(this.f1610a, "", "");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("url", str);
                    jSONObject.put("bizcontext", ppVar.a(o.f3603e, "h5tonative"));
                    return "new_external_info==" + jSONObject.toString();
                }
                a aVar = null;
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com/w/trade_pay\\.do.?|mali\\.alipay\\.com/w/trade_pay\\.do.?|mclient\\.alipay\\.com/w/trade_pay\\.do.?)").matcher(str).find()) {
                    String strA = bq.a(Operators.CONDITION_IF_STRING, "", str);
                    if (!TextUtils.isEmpty(strA)) {
                        Map<String, String> mapB = bq.b(strA);
                        StringBuilder sb = new StringBuilder();
                        if (a(false, true, "trade_no", sb, mapB, "trade_no", "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", sb, mapB, "payPhaseId", "pay_phase_id", "out_relation_id");
                            sb.append("&biz_sub_type=\"TRADE\"");
                            sb.append("&biz_type=\"trade\"");
                            String str2 = mapB.get("app_name");
                            if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(mapB.get("cid"))) {
                                str2 = "ali1688";
                            } else if (TextUtils.isEmpty(str2) && (!TextUtils.isEmpty(mapB.get("sid")) || !TextUtils.isEmpty(mapB.get("s_id")))) {
                                str2 = "tb";
                            }
                            sb.append("&app_name=\"" + str2 + JSUtil.QUOTE);
                            if (!a(true, true, "extern_token", sb, mapB, "extern_token", "cid", "sid", "s_id")) {
                                return "";
                            }
                            a(true, false, "appenv", sb, mapB, "appenv");
                            sb.append("&pay_channel_id=\"alipay_sdk\"");
                            c cVar = new c(this, aVar);
                            cVar.a(mapB.get("return_url"));
                            cVar.c(mapB.get("show_url"));
                            cVar.b(mapB.get("pay_order_id"));
                            String str3 = sb.toString() + "&bizcontext=\"" + new pp(this.f1610a, "", "").a(o.f3603e, "h5tonative") + JSUtil.QUOTE;
                            this.c.put(str3, cVar);
                            return str3;
                        }
                    }
                }
                if (!strTrim.startsWith("https://mclient.alipay.com/cashier/mobilepay.htm") && !strTrim.startsWith("http://mclient.alipay.com/cashier/mobilepay.htm") && (!EnvUtils.a() || !strTrim.contains("mobileclientgw.alipaydev.com/cashier/mobilepay.htm"))) {
                    if (vo.v().d() && Pattern.compile("^https?://(maliprod\\.alipay\\.com|mali\\.alipay\\.com)/batch_payment\\.do\\?").matcher(strTrim).find()) {
                        Uri uri = Uri.parse(strTrim);
                        String queryParameter = uri.getQueryParameter("return_url");
                        String queryParameter2 = uri.getQueryParameter("show_url");
                        String queryParameter3 = uri.getQueryParameter("pay_order_id");
                        String strA2 = a(uri.getQueryParameter("trade_nos"), uri.getQueryParameter("alipay_trade_no"));
                        String strA3 = a(uri.getQueryParameter("payPhaseId"), uri.getQueryParameter("pay_phase_id"), uri.getQueryParameter("out_relation_id"));
                        String[] strArr = new String[4];
                        strArr[0] = uri.getQueryParameter("app_name");
                        strArr[1] = !TextUtils.isEmpty(uri.getQueryParameter("cid")) ? "ali1688" : "";
                        strArr[2] = !TextUtils.isEmpty(uri.getQueryParameter("sid")) ? "tb" : "";
                        strArr[3] = !TextUtils.isEmpty(uri.getQueryParameter("s_id")) ? "tb" : "";
                        String strA4 = a(strArr);
                        String strA5 = a(uri.getQueryParameter("extern_token"), uri.getQueryParameter("cid"), uri.getQueryParameter("sid"), uri.getQueryParameter("s_id"));
                        String strA6 = a(uri.getQueryParameter("appenv"));
                        if (!TextUtils.isEmpty(strA2) && !TextUtils.isEmpty(strA4) && !TextUtils.isEmpty(strA5)) {
                            String str4 = String.format("trade_no=\"%s\"&pay_phase_id=\"%s\"&biz_type=\"trade\"&biz_sub_type=\"TRADE\"&app_name=\"%s\"&extern_token=\"%s\"&appenv=\"%s\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"%s\"", strA2, strA3, strA4, strA5, strA6, new pp(this.f1610a, "", "").a(o.f3603e, "h5tonative"));
                            c cVar2 = new c(this, null);
                            cVar2.a(queryParameter);
                            cVar2.c(queryParameter2);
                            cVar2.b(queryParameter3);
                            cVar2.d(strA2);
                            this.c.put(str4, cVar2);
                            return str4;
                        }
                    }
                }
                String strA7 = new pp(this.f1610a, "", "").a(o.f3603e, "h5tonative");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("url", strTrim);
                jSONObject2.put("bizcontext", strA7);
                return String.format("new_external_info==%s", jSONObject2.toString());
            }
        } catch (Throwable th) {
            vp.a(th);
        }
        return "";
    }

    public synchronized String fetchTradeToken() {
        return wp.a(new pp(this.f1610a, "", "fetchTradeToken"), this.f1610a.getApplicationContext());
    }

    public String getVersion() {
        return "15.8.00";
    }

    public synchronized sp h5Pay(pp ppVar, String str, boolean z) {
        sp spVar;
        spVar = new sp();
        try {
            String[] strArrSplit = a(ppVar, str, z).split(";");
            HashMap map = new HashMap();
            for (String str2 : strArrSplit) {
                int iIndexOf = str2.indexOf("={");
                if (iIndexOf >= 0) {
                    String strSubstring = str2.substring(0, iIndexOf);
                    map.put(strSubstring, a(str2, strSubstring));
                }
            }
            if (map.containsKey("resultStatus")) {
                spVar.a(map.get("resultStatus"));
            }
            spVar.b(a(str, map));
            if (TextUtils.isEmpty(spVar.b())) {
                so.a(ppVar, "biz", "H5CbUrlEmpty", "");
            }
        } catch (Throwable th) {
            so.a(ppVar, "biz", "H5CbEx", th);
            vp.a(th);
        }
        return spVar;
    }

    public synchronized String pay(String str, boolean z) {
        return a(new pp(this.f1610a, str, "pay"), str, z);
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String strFetchOrderInfoFromH5PayUrl;
        strFetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(strFetchOrderInfoFromH5PayUrl)) {
            vp.b("mspl", "intercepted: " + strFetchOrderInfoFromH5PayUrl);
            new Thread(new a(strFetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(strFetchOrderInfoFromH5PayUrl);
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        pp ppVar;
        ppVar = new pp(this.f1610a, str, "payV2");
        return zp.a(ppVar, a(ppVar, str, z));
    }

    public void showLoading() {
        cq cqVar = this.b;
        if (cqVar != null) {
            cqVar.a();
        }
    }

    public final synchronized String a(pp ppVar, String str, boolean z) {
        Context applicationContext;
        String str2;
        if (b()) {
            so.a(ppVar, "biz", "RepPay", "");
            return qo.d();
        }
        if (z) {
            showLoading();
        }
        if (str.contains("payment_inst=")) {
            String strSubstring = str.substring(str.indexOf("payment_inst=") + 13);
            int iIndexOf = strSubstring.indexOf(38);
            if (iIndexOf > 0) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
            po.a(strSubstring.replaceAll(JSUtil.QUOTE, "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
        } else {
            po.a("");
        }
        if (str.contains("service=alipay.acquire.mr.ord.createandpay")) {
            uo.c = true;
        }
        if (uo.c) {
            if (str.startsWith("https://wappaygw.alipay.com/home/exterfaceAssign.htm?")) {
                str = str.substring(str.indexOf("https://wappaygw.alipay.com/home/exterfaceAssign.htm?") + 53);
            } else if (str.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm?")) {
                str = str.substring(str.indexOf("https://mclient.alipay.com/home/exterfaceAssign.htm?") + 52);
            }
        }
        String strC = "";
        try {
            vp.b("mspl", "pay prepared: " + str);
            strC = a(str, ppVar);
            vp.b("mspl", "pay raw result: " + strC);
            wp.a(ppVar, this.f1610a.getApplicationContext(), strC);
            so.b(ppVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
            so.b(ppVar, "biz", "PgReturnV", zp.a(strC, "resultStatus") + "|" + zp.a(strC, "memo"));
            if (!vo.v().n()) {
                vo.v().a(ppVar, this.f1610a.getApplicationContext());
            }
            dismissLoading();
            applicationContext = this.f1610a.getApplicationContext();
            str2 = ppVar.d;
        } catch (Throwable th) {
            try {
                strC = qo.c();
                vp.a(th);
                so.b(ppVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
                so.b(ppVar, "biz", "PgReturnV", zp.a(strC, "resultStatus") + "|" + zp.a(strC, "memo"));
                if (!vo.v().n()) {
                    vo.v().a(ppVar, this.f1610a.getApplicationContext());
                }
                dismissLoading();
                applicationContext = this.f1610a.getApplicationContext();
                str2 = ppVar.d;
            } catch (Throwable th2) {
                so.b(ppVar, "biz", "PgReturn", "" + SystemClock.elapsedRealtime());
                so.b(ppVar, "biz", "PgReturnV", zp.a(strC, "resultStatus") + "|" + zp.a(strC, "memo"));
                if (!vo.v().n()) {
                    vo.v().a(ppVar, this.f1610a.getApplicationContext());
                }
                dismissLoading();
                so.b(this.f1610a.getApplicationContext(), ppVar, str, ppVar.d);
                throw th2;
            }
        }
        so.b(applicationContext, ppVar, str, str2);
        vp.b("mspl", "pay returning: " + strC);
        return strC;
    }

    public static final String a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public final boolean a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = "";
                break;
            }
            String str3 = strArr[i];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        }
        if (z) {
            sb.append("&");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append(JSUtil.QUOTE);
            return true;
        }
        sb.append(str);
        sb.append("=\"");
        sb.append(str2);
        sb.append(JSUtil.QUOTE);
        return true;
    }

    public final String a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean zEquals = "9000".equals(map.get("resultStatus"));
        String str2 = map.get("result");
        c cVarRemove = this.c.remove(str);
        String[] strArr = new String[2];
        strArr[0] = cVarRemove != null ? cVarRemove.b() : "";
        strArr[1] = cVarRemove != null ? cVarRemove.d() : "";
        a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String strA = a(bq.a("&callBackUrl=\"", JSUtil.QUOTE, str2), bq.a("&call_back_url=\"", JSUtil.QUOTE, str2), bq.a("&return_url=\"", JSUtil.QUOTE, str2), URLDecoder.decode(bq.a("&return_url=", "&", str2), "utf-8"), URLDecoder.decode(bq.a("&callBackUrl=", "&", str2), "utf-8"), bq.a("call_back_url=\"", JSUtil.QUOTE, str2));
            if (!TextUtils.isEmpty(strA)) {
                return strA;
            }
        }
        if (cVarRemove != null) {
            String strA2 = zEquals ? cVarRemove.a() : cVarRemove.c();
            if (!TextUtils.isEmpty(strA2)) {
                return strA2;
            }
        }
        return cVarRemove != null ? vo.v().e() : "";
    }

    public final String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf(Operators.BLOCK_END_STR));
    }

    public final f.e a() {
        return new b();
    }

    public final String a(String str, pp ppVar) {
        String strA = ppVar.a(str);
        if (strA.contains("paymethod=\"expressGateway\"")) {
            return a(ppVar, strA);
        }
        List<vo.b> listR = vo.v().r();
        if (!vo.v().g || listR == null) {
            listR = po.d;
        }
        if (bq.b(ppVar, this.f1610a, listR)) {
            f fVar = new f(this.f1610a, ppVar, a());
            vp.b("mspl", "pay inner started: " + strA);
            String strA2 = fVar.a(strA);
            vp.b("mspl", "pay inner raw result: " + strA2);
            fVar.a();
            if (!TextUtils.equals(strA2, AbsoluteConst.EVENTS_FAILED) && !TextUtils.equals(strA2, "scheme_failed")) {
                if (TextUtils.isEmpty(strA2)) {
                    return qo.c();
                }
                if (!strA2.contains("{\"isLogin\":\"false\"}")) {
                    return strA2;
                }
                so.a(ppVar, "biz", "LogHkLoginByIntent");
                return a(ppVar, strA, listR, strA2, this.f1610a);
            }
            so.a(ppVar, "biz", "LogBindCalledH5");
            return a(ppVar, strA);
        }
        so.a(ppVar, "biz", "LogCalledH5");
        return a(ppVar, strA);
    }

    public static String a(pp ppVar, String str, List<vo.b> list, String str2, Activity activity) {
        bq.b bVarA = bq.a(ppVar, activity, list);
        if (bVarA == null || bVarA.a(ppVar) || bVarA.a() || !TextUtils.equals(bVarA.f7091a.packageName, "hk.alipay.wallet")) {
            return str2;
        }
        vp.a("mspl", "PayTask not_login");
        String strValueOf = String.valueOf(str.hashCode());
        PayResultActivity.b.put(strValueOf, new Object());
        Intent intent = new Intent(activity, (Class<?>) PayResultActivity.class);
        intent.putExtra("orderSuffix", str);
        intent.putExtra("externalPkgName", activity.getPackageName());
        intent.putExtra("phonecashier.pay.hash", strValueOf);
        pp.a.a(ppVar, intent);
        activity.startActivity(intent);
        synchronized (PayResultActivity.b.get(strValueOf)) {
            try {
                vp.a("mspl", "PayTask wait");
                PayResultActivity.b.get(strValueOf).wait();
            } catch (InterruptedException unused) {
                vp.a("mspl", "PayTask interrupted");
                return qo.c();
            }
        }
        String str3 = PayResultActivity.b.b;
        vp.a("mspl", "PayTask ret: " + str3);
        return str3;
    }

    public final String a(pp ppVar, String str) {
        String strA;
        showLoading();
        com.alipay.sdk.app.c cVarB = null;
        try {
            try {
                JSONObject jSONObjectC = new np().a(ppVar, this.f1610a.getApplicationContext(), str).c();
                String strOptString = jSONObjectC.optString("end_code", null);
                List<op> listA = op.a(jSONObjectC.optJSONObject("form").optJSONObject("onload"));
                for (int i = 0; i < listA.size(); i++) {
                    if (listA.get(i).a() == com.alipay.sdk.protocol.a.Update) {
                        op.a(listA.get(i));
                    }
                }
                a(ppVar, jSONObjectC);
                dismissLoading();
                so.a(this.f1610a, ppVar, str, ppVar.d);
                for (int i2 = 0; i2 < listA.size(); i2++) {
                    op opVar = listA.get(i2);
                    if (opVar.a() == com.alipay.sdk.protocol.a.WapPay) {
                        strA = a(ppVar, opVar);
                    } else if (opVar.a() == com.alipay.sdk.protocol.a.OpenWeb) {
                        strA = a(ppVar, opVar, strOptString);
                    }
                    return strA;
                }
            } catch (IOException e2) {
                com.alipay.sdk.app.c cVarB2 = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.NETWORK_ERROR.a());
                so.a(ppVar, "net", e2);
                dismissLoading();
                so.a(this.f1610a, ppVar, str, ppVar.d);
                cVarB = cVarB2;
            } catch (Throwable th) {
                vp.a(th);
                so.a(ppVar, "biz", "H5PayDataAnalysisError", th);
            }
            if (cVarB == null) {
                cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.FAILED.a());
            }
            return qo.a(cVarB.a(), cVarB.b(), "");
        } finally {
            dismissLoading();
            so.a(this.f1610a, ppVar, str, ppVar.d);
        }
    }

    public final void a(pp ppVar, JSONObject jSONObject) {
        try {
            String strOptString = jSONObject.optString("tid");
            String strOptString2 = jSONObject.optString("client_key");
            if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2)) {
                return;
            }
            rp.b(qp.d().a()).a(strOptString, strOptString2);
        } catch (Throwable th) {
            so.a(ppVar, "biz", "ParserTidClientKeyEx", th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0091, code lost:
    
        r0 = r6.b();
        r11 = supwisdom.qo.a(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], supwisdom.bq.b(r10, r0[2]));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(supwisdom.pp r10, supwisdom.op r11, java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.a(supwisdom.pp, supwisdom.op, java.lang.String):java.lang.String");
    }

    public final String a(pp ppVar, op opVar) {
        String[] strArrB = opVar.b();
        Intent intent = new Intent(this.f1610a, (Class<?>) H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArrB[0]);
        if (strArrB.length == 2) {
            bundle.putString(SerializableCookie.COOKIE, strArrB[1]);
        }
        intent.putExtras(bundle);
        pp.a.a(ppVar, intent);
        this.f1610a.startActivity(intent);
        synchronized (d) {
            try {
                d.wait();
            } catch (InterruptedException e2) {
                vp.a(e2);
                return qo.c();
            }
        }
        String strA = qo.a();
        return TextUtils.isEmpty(strA) ? qo.c() : strA;
    }
}
