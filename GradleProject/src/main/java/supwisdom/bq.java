package supwisdom;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.app.EnvUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.util.Md5Utils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import supwisdom.vo;

/* JADX INFO: loaded from: classes.dex */
public class bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f7089a = {"10.1.5.1013151", "10.1.5.1013148"};

    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f7090a;

        public a(Activity activity) {
            this.f7090a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f7090a.finish();
        }
    }

    public static String a() {
        if (EnvUtils.a()) {
            return "com.eg.android.AlipayGphoneRC";
        }
        try {
            return po.d.get(0).f9535a;
        } catch (Throwable unused) {
            return "com.eg.android.AlipayGphone";
        }
    }

    public static Map<String, String> b(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("&")) {
            int iIndexOf = str2.indexOf(ContainerUtils.KEY_VALUE_DELIMITER, 1);
            if (-1 != iIndexOf) {
                map.put(str2.substring(0, iIndexOf), URLDecoder.decode(str2.substring(iIndexOf + 1)));
            }
        }
        return map;
    }

    public static JSONObject c(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String e(Context context) {
        DisplayMetrics displayMetricsI = i(context);
        return displayMetricsI.widthPixels + Operators.MUL + displayMetricsI.heightPixels;
    }

    public static String f() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(line);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + Operators.SPACE_STR + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    public static int g(String str) {
        try {
            String strP = vo.v().p();
            if (TextUtils.isEmpty(strP)) {
                return 0;
            }
            return 1 | (a(strP, "").contains(str) ? 2 : 0);
        } catch (Throwable unused) {
            return 61440;
        }
    }

    public static String g(Context context) {
        return "-1;-1";
    }

    public static ActivityInfo h(Context context) {
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities) {
                    if (TextUtils.equals(activityInfo.name, activity.getClass().getName())) {
                        return activityInfo;
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            vp.a(th);
            return null;
        }
    }

    public static DisplayMetrics i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String a(String str) {
        return (EnvUtils.a() && TextUtils.equals(str, "com.eg.android.AlipayGphoneRC")) ? "com.eg.android.AlipayGphoneRC.IAlixPay" : "com.eg.android.AlipayGphone.IAlixPay";
    }

    public static String c(Context context) {
        return " (" + b() + ";" + c() + ";" + d(context) + ";;" + e(context) + ")(sdk android)";
    }

    public static Map<String, String> a(pp ppVar, String str) {
        HashMap map = new HashMap(4);
        int iIndexOf = str.indexOf(63);
        if (iIndexOf != -1 && iIndexOf < str.length() - 1) {
            for (String str2 : str.substring(iIndexOf + 1).split("&")) {
                int iIndexOf2 = str2.indexOf(61, 1);
                if (iIndexOf2 != -1 && iIndexOf2 < str2.length() - 1) {
                    map.put(str2.substring(0, iIndexOf2), b(ppVar, str2.substring(iIndexOf2 + 1)));
                }
            }
        }
        return map;
    }

    public static boolean d(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final PackageInfo f7091a;
        public final int b;
        public final String c;

        public b(PackageInfo packageInfo, int i, String str) {
            this.f7091a = packageInfo;
            this.b = i;
            this.c = str;
        }

        public boolean a(pp ppVar) {
            Signature[] signatureArr = this.f7091a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String strA = bq.a(ppVar, signature.toByteArray());
                if (strA != null && !TextUtils.equals(strA, this.c)) {
                    so.a(ppVar, "biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", strA, this.c));
                    return true;
                }
            }
            return false;
        }

        public boolean a() {
            return this.f7091a.versionCode < this.b;
        }
    }

    public static String e(String str) {
        try {
            Uri uri = Uri.parse(str);
            return String.format("%s%s", uri.getAuthority(), uri.getPath());
        } catch (Throwable th) {
            vp.a(th);
            return "-";
        }
    }

    public static String b(pp ppVar, String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            so.a(ppVar, "biz", "H5PayDataAnalysisError", e2);
            return "";
        }
    }

    public static int d() {
        try {
            return Process.myUid();
        } catch (Throwable th) {
            vp.a(th);
            return -200;
        }
    }

    public static PackageInfo b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    public static String d(pp ppVar, String str) {
        try {
            return (String) Class.forName(CountryCodeBean.ANDRIOD_SYSTEMPROP).getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            so.a(ppVar, "biz", "rflex", e2.getClass().getSimpleName());
            return null;
        }
    }

    public static String c() {
        String strF = f();
        int iIndexOf = strF.indexOf("-");
        if (iIndexOf != -1) {
            strF = strF.substring(0, iIndexOf);
        }
        int iIndexOf2 = strF.indexOf("\n");
        if (iIndexOf2 != -1) {
            strF = strF.substring(0, iIndexOf2);
        }
        return "Linux " + strF;
    }

    public static String f(Context context) {
        String strA = aq.a(context);
        return strA.substring(0, strA.indexOf("://"));
    }

    public static boolean b(pp ppVar, Context context, List<vo.b> list) {
        try {
            for (vo.b bVar : list) {
                if (bVar != null) {
                    String str = bVar.f9535a;
                    if (EnvUtils.a() && "com.eg.android.AlipayGphone".equals(str)) {
                        str = "com.eg.android.AlipayGphoneRC";
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            so.a(ppVar, "biz", "CheckLaunchAppExistEx", th);
            return false;
        }
    }

    public static boolean e() {
        try {
            String[] strArrSplit = vo.v().j().split("\\|");
            String str = Build.MODEL;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            for (String str2 : strArrSplit) {
                if (TextUtils.equals(str, str2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            vp.a(th);
            return false;
        }
    }

    public static String f(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String a(String str, String str2, String str3) {
        try {
            int iIndexOf = str3.indexOf(str) + str.length();
            if (iIndexOf <= str.length()) {
                return "";
            }
            int iIndexOf2 = TextUtils.isEmpty(str2) ? 0 : str3.indexOf(str2, iIndexOf);
            if (iIndexOf2 < 1) {
                return str3.substring(iIndexOf);
            }
            return str3.substring(iIndexOf, iIndexOf2);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean c(pp ppVar, String str) {
        try {
            int iG = g(str);
            so.b(ppVar, "biz", "bindExt", "" + iG);
            return vo.v().o() && (iG & 2) == 2;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode < 99;
        } catch (Throwable th) {
            vp.a(th);
            return false;
        }
    }

    public static boolean e(pp ppVar, String str) {
        try {
            String host = new URL(str).getHost();
            if (host.endsWith("alipay.com")) {
                return true;
            }
            return host.endsWith("alipay.net");
        } catch (Throwable th) {
            so.a(ppVar, "biz", "ckUrlErr", th);
            return false;
        }
    }

    public static String a(pp ppVar, byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e2) {
            so.a(ppVar, "auth", "GetPublicKeyFromSignEx", e2);
            return null;
        }
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static int b(int i) {
        return i / 100000;
    }

    public static b a(pp ppVar, Context context, List<vo.b> list) {
        b bVarA;
        if (list == null) {
            return null;
        }
        for (vo.b bVar : list) {
            if (bVar != null && (bVarA = a(ppVar, context, bVar.f9535a, bVar.b, bVar.c)) != null && !bVarA.a(ppVar) && !bVarA.a()) {
                return bVarA;
            }
        }
        return null;
    }

    public static b a(pp ppVar, Context context, String str, int i, String str2) {
        PackageInfo packageInfoB;
        if (EnvUtils.a() && "com.eg.android.AlipayGphone".equals(str)) {
            str = "com.eg.android.AlipayGphoneRC";
        }
        try {
            packageInfoB = b(context, str);
        } catch (Throwable th) {
            so.a(ppVar, "auth", "GetPackageInfoEx", th.getMessage());
            packageInfoB = null;
        }
        if (a(ppVar, packageInfoB)) {
            return a(packageInfoB, i, str2);
        }
        return null;
    }

    public static boolean a(pp ppVar, PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                str = "info.signatures == null";
            } else if (signatureArr.length <= 0) {
                str = "info.signatures.length <= 0";
            } else {
                z = true;
            }
        }
        if (!z) {
            so.a(ppVar, "auth", "NotIncludeSignatures", str);
        }
        return z;
    }

    public static b a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new b(packageInfo, i, str);
    }

    public static boolean a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (!TextUtils.equals(str, f7089a[0])) {
                if (!TextUtils.equals(str, f7089a[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
            } else if (iNextInt == 1) {
                sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
            } else if (iNextInt == 2) {
                sb.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return sb.toString();
    }

    public static String a(Context context, String str) {
        String strSubstring = "";
        try {
            String string = "";
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    string = string + "#M";
                } else {
                    if (runningAppProcessInfo.processName.startsWith(str + Constants.COLON_SEPARATOR)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(string);
                        sb.append("#");
                        sb.append(runningAppProcessInfo.processName.replace(str + Constants.COLON_SEPARATOR, ""));
                        string = sb.toString();
                    }
                }
            }
            strSubstring = string;
        } catch (Throwable unused) {
        }
        if (strSubstring.length() > 0) {
            strSubstring = strSubstring.substring(1);
        }
        return strSubstring.length() == 0 ? "N" : strSubstring;
    }

    public static boolean a(pp ppVar, String str, Activity activity) {
        String strSubstring;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (!str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) && !str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
            if (!TextUtils.equals(str, "sdklite://h5quit") && !TextUtils.equals(str, "http://m.alipay.com/?action=h5quit")) {
                if (!str.startsWith("sdklite://h5quit?result=")) {
                    return false;
                }
                try {
                    String strSubstring2 = str.substring(str.indexOf("sdklite://h5quit?result=") + 24);
                    int i = Integer.parseInt(strSubstring2.substring(strSubstring2.lastIndexOf("&end_code=") + 10));
                    if (i != com.alipay.sdk.app.c.SUCCEEDED.a() && i != com.alipay.sdk.app.c.PAY_WAITTING.a()) {
                        com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.FAILED.a());
                        qo.a(qo.a(cVarB.a(), cVarB.b(), ""));
                    } else {
                        if (uo.c) {
                            StringBuilder sb = new StringBuilder();
                            String strDecode = URLDecoder.decode(str);
                            String strDecode2 = URLDecoder.decode(strDecode);
                            String str2 = strDecode2.substring(strDecode2.indexOf("sdklite://h5quit?result=") + 24, strDecode2.lastIndexOf("&end_code=")).split("&return_url=")[0];
                            int iIndexOf = strDecode.indexOf("&return_url=") + 12;
                            sb.append(str2);
                            sb.append("&return_url=");
                            sb.append(strDecode.substring(iIndexOf, strDecode.indexOf("&", iIndexOf)));
                            sb.append(strDecode.substring(strDecode.indexOf("&", iIndexOf)));
                            strSubstring = sb.toString();
                        } else {
                            String strDecode3 = URLDecoder.decode(str);
                            strSubstring = strDecode3.substring(strDecode3.indexOf("sdklite://h5quit?result=") + 24, strDecode3.lastIndexOf("&end_code="));
                        }
                        com.alipay.sdk.app.c cVarB2 = com.alipay.sdk.app.c.b(i);
                        qo.a(qo.a(cVarB2.a(), cVarB2.b(), strSubstring));
                    }
                } catch (Exception unused) {
                    qo.a(qo.e());
                }
                activity.runOnUiThread(new a(activity));
                return true;
            }
            qo.a(qo.c());
            activity.finish();
            return true;
        }
        try {
            b bVarA = a(ppVar, activity, po.d);
            if (bVarA != null && !bVarA.a() && !bVarA.a(ppVar)) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            }
        } catch (Throwable unused2) {
        }
        return true;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(Character.forDigit((b2 & 240) >> 4, 16));
            sb.append(Character.forDigit(b2 & 15, 16));
        }
        return sb.toString();
    }

    public static String a(pp ppVar) {
        return d(ppVar, "ro.build.fingerprint");
    }

    public static <T> T a(WeakReference<T> weakReference) {
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static JSONObject a(Intent intent) {
        Bundle extras;
        JSONObject jSONObject = new JSONObject();
        if (intent != null && (extras = intent.getExtras()) != null) {
            for (String str : extras.keySet()) {
                try {
                    jSONObject.put(str, String.valueOf(extras.get(str)));
                } catch (Throwable unused) {
                }
            }
        }
        return jSONObject;
    }

    public static String a(String str, String str2) {
        String string = Settings.Secure.getString(((Application) qp.d().a()).getContentResolver(), str);
        return string != null ? string : str2;
    }
}
