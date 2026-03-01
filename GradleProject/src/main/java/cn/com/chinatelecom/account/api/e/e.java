package cn.com.chinatelecom.account.api.e;

import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.ClientUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import io.dcloud.common.constant.AbsoluteConst;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public String l;
    public int o;
    public long q;
    public int t;
    public long w;
    public long r = -1;
    public StringBuffer u = new StringBuffer();
    public String c = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1516e = "";
    public String n = "";
    public String m = "";
    public String p = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1515a = "1.2";
    public long v = SystemClock.uptimeMillis();
    public String b = a(System.currentTimeMillis());
    public String d = CtAuth.mAppId;
    public String f = "";
    public String g = Build.BRAND;
    public String h = Build.MODEL;
    public String i = "Android";
    public String j = Build.VERSION.RELEASE;
    public String k = ClientUtils.getSdkVersion();
    public String s = "0";

    public e(String str) {
        this.l = str;
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public e a(int i) {
        this.o = i;
        return this;
    }

    public e a(String str) {
        this.f1516e = str;
        return this;
    }

    public String a() {
        return this.l;
    }

    public e b(int i) {
        this.t = i;
        return this;
    }

    public e b(long j) {
        if (j > 0) {
            this.q = j;
        }
        return this;
    }

    public e b(String str) {
        this.f = str;
        return this;
    }

    public void b() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.w = jUptimeMillis;
        if (this.r == -1) {
            this.r = jUptimeMillis - this.v;
        }
    }

    public e c(String str) {
        this.m = str;
        return this;
    }

    public e d(String str) {
        this.n = str;
        return this;
    }

    public e e(String str) {
        this.p = str;
        return this;
    }

    public e f(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.s = str;
        }
        return this;
    }

    public e g(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuffer stringBuffer = this.u;
            stringBuffer.append(str);
            stringBuffer.append(";");
        }
        return this;
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("v", this.f1515a);
            jSONObject.put("t", this.b);
            jSONObject.put("tag", this.c);
            jSONObject.put("ai", this.d);
            jSONObject.put("di", this.f1516e);
            jSONObject.put(AbsoluteConst.XML_NS, this.f);
            jSONObject.put("br", this.g);
            jSONObject.put("ml", this.h);
            jSONObject.put("os", this.i);
            jSONObject.put("ov", this.j);
            jSONObject.put("sv", this.k);
            jSONObject.put("ri", this.l);
            jSONObject.put("api", this.m);
            jSONObject.put("p", this.n);
            jSONObject.put("rt", this.o);
            jSONObject.put("msg", this.p);
            jSONObject.put("st", this.q);
            jSONObject.put("tt", this.r);
            jSONObject.put("ot", this.s);
            jSONObject.put("rec", this.t);
            jSONObject.put("ep", this.u.toString());
            return jSONObject.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
