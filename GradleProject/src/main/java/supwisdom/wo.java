package supwisdom;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ConditionVariable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.huawei.hms.api.ConnectionResult;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class wo {
    public static volatile wo d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9647a;
    public String b = "sdk-and-lite";
    public String c;

    public static class a implements APSecuritySdk.InitResultListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f9648a;
        public final /* synthetic */ ConditionVariable b;

        public a(String[] strArr, ConditionVariable conditionVariable) {
            this.f9648a = strArr;
            this.b = conditionVariable;
        }

        @Override // com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener
        public void onResult(APSecuritySdk.TokenResult tokenResult) {
            if (tokenResult != null) {
                this.f9648a[0] = tokenResult.apdidToken;
            }
            this.b.open();
        }
    }

    public static class b implements Callable<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ pp f9649a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ HashMap c;

        public b(pp ppVar, Context context, HashMap map) {
            this.f9649a = ppVar;
            this.b = context;
            this.c = map;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String call() throws Exception {
            return wo.b(this.f9649a, this.b, this.c);
        }
    }

    public wo() {
        String strA = po.a();
        if (po.b()) {
            return;
        }
        this.b += '_' + strA;
    }

    public static String b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    public static String c() {
        String strA;
        Context contextA = qp.d().a();
        SharedPreferences sharedPreferences = contextA.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imsi", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(rp.b(contextA).a())) {
            String strC = qp.d().c();
            strA = (TextUtils.isEmpty(strC) || strC.length() < 18) ? f() : strC.substring(3, 18);
        } else {
            strA = tp.a(contextA).a();
        }
        String str = strA;
        sharedPreferences.edit().putString("virtual_imsi", str).apply();
        return str;
    }

    public static String d() {
        return "1";
    }

    public static String d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "00";
    }

    public static String e() {
        return "-1;-1";
    }

    public static String f() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(ConnectionResult.NETWORK_ERROR) + 1000);
    }

    public static synchronized wo a() {
        if (d == null) {
            d = new wo();
        }
        return d;
    }

    public static String b() {
        String strB;
        Context contextA = qp.d().a();
        SharedPreferences sharedPreferences = contextA.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imei", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(rp.b(contextA).a())) {
            strB = f();
        } else {
            strB = tp.a(contextA).b();
        }
        String str = strB;
        sharedPreferences.edit().putString("virtual_imei", str).apply();
        return str;
    }

    public static synchronized void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PreferenceManager.getDefaultSharedPreferences(qp.d().a()).edit().putString("trideskey", str).apply();
        uo.b = str;
    }

    public static String b(pp ppVar, Context context, HashMap<String, String> map) {
        String[] strArr = {""};
        try {
            APSecuritySdk aPSecuritySdk = APSecuritySdk.getInstance(context);
            ConditionVariable conditionVariable = new ConditionVariable();
            aPSecuritySdk.initToken(0, map, new a(strArr, conditionVariable));
            conditionVariable.block(3000L);
        } catch (Throwable th) {
            vp.a(th);
            so.a(ppVar, "third", "GetApdidEx", th);
        }
        if (TextUtils.isEmpty(strArr[0])) {
            so.a(ppVar, "third", "GetApdidNull", "missing token");
        }
        vp.a("mspl", "ap:" + strArr[0]);
        return strArr[0];
    }

    public String a(pp ppVar, rp rpVar) {
        Context contextA = qp.d().a();
        tp tpVarA = tp.a(contextA);
        if (TextUtils.isEmpty(this.f9647a)) {
            this.f9647a = "Msp/15.8.00 (" + bq.b() + ";" + bq.c() + ";" + bq.d(contextA) + ";" + bq.f(contextA) + ";" + bq.e(contextA) + ";" + b(contextA);
        }
        String strB = tp.b(contextA).b();
        String strG = bq.g(contextA);
        String strD = d();
        String strA = tpVarA.a();
        String strB2 = tpVarA.b();
        String strC = c();
        String strB3 = b();
        if (rpVar != null) {
            this.c = rpVar.b();
        }
        String strReplace = Build.MANUFACTURER.replace(";", Operators.SPACE_STR);
        String strReplace2 = Build.MODEL.replace(";", Operators.SPACE_STR);
        boolean zE = qp.e();
        String strC2 = tpVarA.c();
        String strC3 = c(contextA);
        String strD2 = d(contextA);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9647a);
        sb.append(";");
        sb.append(strB);
        sb.append(";");
        sb.append(strG);
        sb.append(";");
        sb.append(strD);
        sb.append(";");
        sb.append(strA);
        sb.append(";");
        sb.append(strB2);
        sb.append(";");
        sb.append(this.c);
        sb.append(";");
        sb.append(strReplace);
        sb.append(";");
        sb.append(strReplace2);
        sb.append(";");
        sb.append(zE);
        sb.append(";");
        sb.append(strC2);
        sb.append(";");
        sb.append(e());
        sb.append(";");
        sb.append(this.b);
        sb.append(";");
        sb.append(strC);
        sb.append(";");
        sb.append(strB3);
        sb.append(";");
        sb.append(strC3);
        sb.append(";");
        sb.append(strD2);
        if (rpVar != null) {
            HashMap map = new HashMap();
            map.put("tid", rp.b(contextA).a());
            map.put(com.umeng.commonsdk.statistics.idtracking.k.f5447a, qp.d().c());
            String strC4 = c(ppVar, contextA, map);
            if (!TextUtils.isEmpty(strC4)) {
                sb.append(";;;");
                sb.append(strC4);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : "-1";
    }

    public static String c(pp ppVar, Context context, HashMap<String, String> map) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new b(ppVar, context, map)).get(3000L, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            so.a(ppVar, "third", "GetApdidTimeout", th);
            return "";
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append("(");
            sb.append(packageName);
            sb.append(";");
            sb.append(packageInfo.versionCode);
            sb.append(")");
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
