package com.g.gysdk.a;

import com.g.gysdk.GyErrorCode;
import com.getui.gtc.base.crypt.CryptTools;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import io.dcloud.common.util.Md5Utils;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class t {
    public static final AtomicInteger h = new AtomicInteger(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final as f2043a;
    public final String b;
    public String c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public GyErrorCode f2044e = GyErrorCode.UNKNOWN_ERROR;
    public String f;
    public long g;

    public t(as asVar, String str, long j) {
        this.f2043a = asVar;
        this.b = str == null ? com.igexin.push.core.b.m : str;
        this.c = i();
        this.d = j > 0 ? System.currentTimeMillis() - j : 0L;
    }

    public static String i() {
        try {
            return CryptTools.digestToHexString(Md5Utils.ALGORITHM, (d.h + System.currentTimeMillis() + h.addAndGet(1)).getBytes());
        } catch (Throwable th) {
            ak.e(th);
            return "";
        }
    }

    public long a() {
        return this.d;
    }

    public void a(long j) {
        this.g = j;
    }

    public void a(GyErrorCode gyErrorCode) {
        this.f2044e = gyErrorCode;
    }

    public void a(String str) {
        this.f = str;
    }

    public boolean b() {
        return this.f2044e == GyErrorCode.SUCCESS;
    }

    public String c() {
        return this.f;
    }

    public long d() {
        return this.g;
    }

    public as e() {
        return this.f2043a;
    }

    public String f() {
        return this.c;
    }

    public String g() {
        return this.b;
    }

    public JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        if (!b()) {
            try {
                try {
                    jSONObject.put("metadata", new JSONObject(this.b));
                } catch (JSONException e2) {
                    ak.e(e2);
                }
            } catch (JSONException unused) {
                jSONObject.put("metadata", this.b);
            }
        }
        try {
            jSONObject.put(CrashHianalyticsData.PROCESS_ID, this.c);
            jSONObject.put("operatorType", String.valueOf(this.f2043a.g));
            jSONObject.put("clienttype", "1");
        } catch (Throwable th) {
            ak.e(th);
        }
        return jSONObject;
    }
}
