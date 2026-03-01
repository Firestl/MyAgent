package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.util.JSUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;
import supwisdom.so;

/* JADX INFO: loaded from: classes.dex */
public class to {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9307a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9308e;
    public String f;
    public String g;
    public String h = "";
    public String i = "";
    public String j;

    public to(Context context, boolean z) {
        context = context != null ? context.getApplicationContext() : context;
        this.f9307a = b();
        this.c = a(context);
        this.d = a(z ? 0L : so.c.a(context));
        this.f9308e = d();
        this.f = b(context);
        this.g = "-";
        this.j = "-";
    }

    public void a(String str, String str2, Throwable th) {
        c(str, str2, a(th));
    }

    public void b(String str, String str2, String str3) {
        d("", str, str2 + "|" + str3);
    }

    public final synchronized void c(String str, String str2, String str3) {
        vp.d("mspl", String.format("err %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.i)) {
            str4 = "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = str;
        objArr[1] = str2;
        objArr[2] = TextUtils.isEmpty(str3) ? "-" : b(str3);
        objArr[3] = b(a());
        sb.append(String.format("%s,%s,%s,%s", objArr));
        this.i += sb.toString();
    }

    public final synchronized void d(String str, String str2, String str3) {
        vp.b("mspl", String.format("event %s %s %s", str, str2, str3));
        String str4 = "";
        if (!TextUtils.isEmpty(this.h)) {
            str4 = "^";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str4);
        Object[] objArr = new Object[4];
        objArr[0] = TextUtils.isEmpty(str) ? "-" : b(str);
        objArr[1] = b(str2);
        objArr[2] = b(str3);
        objArr[3] = b(a());
        sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", objArr));
        this.h += sb.toString();
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace(Operators.ARRAY_START_STR, "【").replace(Operators.ARRAY_END_STR, "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("^", Constants.WAVE_SEPARATOR).replace("#", "＃");
    }

    public void a(String str, String str2, Throwable th, String str3) {
        c(str, str2, str3 + ": " + a(th));
    }

    public void a(String str, String str2, String str3) {
        c(str, str2, str3);
    }

    public void a(String str, String str2) {
        d("", str, str2);
    }

    public static String a() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName());
            stringBuffer.append(Constants.COLON_SEPARATOR);
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                int i = 0;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString());
                    stringBuffer.append(" 》 ");
                    i++;
                    if (i > 5) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String b() {
        return String.format("%s,%s", c(), new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "-" : str;
    }

    public static String c() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "12345678uuid";
        }
    }

    public static String d(String str) {
        String str2;
        String string;
        if (str == null) {
            str = "";
        }
        String[] strArrSplit = str.split("&");
        String strReplace = null;
        if (strArrSplit != null) {
            string = null;
            String strReplace2 = null;
            for (String str3 : strArrSplit) {
                String[] strArrSplit2 = str3.split(ContainerUtils.KEY_VALUE_DELIMITER);
                if (strArrSplit2 != null && strArrSplit2.length == 2) {
                    if (strArrSplit2[0].equalsIgnoreCase("partner")) {
                        strReplace = strArrSplit2[1].replace(JSUtil.QUOTE, "");
                    } else if (strArrSplit2[0].equalsIgnoreCase("out_trade_no")) {
                        string = strArrSplit2[1].replace(JSUtil.QUOTE, "");
                    } else if (strArrSplit2[0].equalsIgnoreCase("trade_no")) {
                        strReplace2 = strArrSplit2[1].replace(JSUtil.QUOTE, "");
                    } else if (strArrSplit2[0].equalsIgnoreCase("biz_content")) {
                        try {
                            JSONObject jSONObject = new JSONObject(bq.b(pp.e(), strArrSplit2[1]));
                            if (TextUtils.isEmpty(string)) {
                                string = jSONObject.getString("out_trade_no");
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (strArrSplit2[0].equalsIgnoreCase("app_id") && TextUtils.isEmpty(strReplace)) {
                        strReplace = strArrSplit2[1];
                    }
                }
            }
            str2 = strReplace;
            strReplace = strReplace2;
        } else {
            str2 = null;
            string = null;
        }
        return String.format("%s,%s,-,%s,-,-,-", b(strReplace), b(string), b(str2));
    }

    public static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", b(tp.c(context)), "android", b(Build.VERSION.RELEASE), b(Build.MODEL), "-", b(tp.a(context).a()), b(tp.b(context).b()), "gw", b(tp.a(context).b()));
    }

    public String a(String str) {
        String strD = d(str);
        this.b = strD;
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.f9307a, strD, this.c, this.d, this.f9308e, this.f, this.g, c(this.h), c(this.i), this.j);
    }

    public static String a(Context context) {
        String packageName;
        String str;
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                packageName = applicationContext.getPackageName();
                try {
                    PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 64);
                    str2 = packageInfo.versionName + "|" + a(packageInfo);
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                packageName = "-";
            }
            str = str2;
            str2 = packageName;
        } else {
            str = "-";
        }
        return String.format("%s,%s,-,-,-", b(str2), b(str));
    }

    public static String a(PackageInfo packageInfo) {
        Signature[] signatureArr;
        String strSubstring;
        String strA;
        if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length == 0) {
            return "0";
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                try {
                    strA = bq.a((pp) null, signature.toByteArray());
                } catch (Throwable unused) {
                }
                if (TextUtils.isEmpty(strA)) {
                    strSubstring = Operators.CONDITION_IF_STRING;
                    sb.append("-");
                    sb.append(strSubstring);
                } else {
                    strSubstring = bq.f(strA).substring(0, 8);
                    sb.append("-");
                    sb.append(strSubstring);
                }
            }
            return sb.toString();
        } catch (Throwable unused2) {
            return Operators.CONDITION_IF_STRING;
        }
    }

    public static String d() {
        return String.format("%s,%s,-,-,-", b(rp.b(qp.d().a()).a()), b(qp.d().c()));
    }

    public static String a(long j) {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", b("15.8.00"), b("h.a.3.8.00"), Constants.WAVE_SEPARATOR + j);
    }
}
