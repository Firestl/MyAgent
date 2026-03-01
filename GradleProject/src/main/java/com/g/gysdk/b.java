package com.g.gysdk;

import android.text.TextUtils;
import android.util.Base64;
import com.g.gysdk.a.ak;
import com.g.gysdk.a.am;
import com.g.gysdk.a.ar;
import com.g.gysdk.a.d;
import com.g.gysdk.a.e;
import com.g.gysdk.a.s;
import com.getui.gtc.base.crypt.CryptTools;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXImage;
import com.xiaomi.mipush.sdk.MiPushClient;
import io.dcloud.common.util.Md5Utils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final GyCode f2047a;
    public final GyErrorCode b;
    public final GYResponse c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2048e;
    public long f;
    public final Map<String, Object> g = new HashMap();

    public b(GyCode gyCode, GyErrorCode gyErrorCode, String str) {
        boolean z = gyCode == GyCode.SUCCESS;
        if (z && gyErrorCode != GyErrorCode.SUCCESS) {
            ak.e(new IllegalStateException("code success, but errorCode:" + gyErrorCode));
            gyErrorCode = GyErrorCode.SUCCESS;
        }
        JSONObject jSONObjectD = d(str);
        try {
            if (jSONObjectD.length() == 0) {
                jSONObjectD.put("metadata", String.valueOf(str));
                if (!z && !TextUtils.isEmpty(str)) {
                    a(WXImage.ERRORDESC, (Serializable) String.valueOf(str));
                }
            }
            jSONObjectD.put("errorCode", gyErrorCode.value);
            jSONObjectD.put(WXImage.ERRORDESC, gyErrorCode.name);
        } catch (JSONException e2) {
            ak.e(e2);
        }
        GYResponse gYResponse = new GYResponse(d.h, gyCode.value, ar.a(false, -1).f1981e, jSONObjectD.toString());
        this.f2047a = gyCode;
        this.b = gyErrorCode;
        this.c = gYResponse;
        this.f = System.currentTimeMillis();
    }

    public static b a(GyCode gyCode, Throwable th) {
        ak.e("GyResult.of " + gyCode, th);
        return th instanceof TimeoutException ? new b(gyCode, GyErrorCode.OPERATE_TIMEOUT, am.a(th)) : new b(gyCode, GyErrorCode.UNKNOWN_ERROR, am.a(th));
    }

    public static b a(Object obj) {
        try {
            return (b) obj;
        } catch (ClassCastException e2) {
            ak.e("GyResult.cast", e2);
            return null;
        }
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(Operators.BLOCK_START_STR) && str.endsWith(Operators.BLOCK_END_STR);
    }

    public static String b(long j) {
        return "{\"costTime\":" + j + Operators.BLOCK_END_STR;
    }

    public static String c(String str) {
        try {
            return Base64.encodeToString(CryptTools.encrypt("RC4", CryptTools.wrapperKey("RC4", "dj1om0z0za9kwzxrphkqxsu9oc21tez1".getBytes()), (IvParameterSpec) null, CryptTools.digestToHexString(Md5Utils.ALGORITHM, str.getBytes()).getBytes()), 0);
        } catch (Throwable th) {
            ak.e("getRcPm", th);
            return "";
        }
    }

    public static JSONObject d(String str) {
        if (a(str)) {
            try {
                return new JSONObject(str);
            } catch (JSONException e2) {
                ak.e(e2);
            }
        }
        return new JSONObject();
    }

    public b a(String str, Serializable serializable) {
        if (serializable != null) {
            try {
                this.g.put(str, serializable);
            } catch (Throwable th) {
                ak.e("putToExt", th);
            }
        }
        return this;
    }

    public b a(String str, String str2) {
        this.d = str;
        this.f2048e = str2;
        this.f = System.currentTimeMillis();
        if ("preLogin".equals(str) || "eLogin".equals(str)) {
            a(MiPushClient.COMMAND_REGISTER, Integer.valueOf(e.b()));
            String strB = s.a().b();
            if (strB != null) {
                a("operator_state", (Serializable) strB);
            }
        }
        return this;
    }

    public void a(long j) {
        long j2 = this.f - j;
        try {
            JSONObject jSONObject = new JSONObject(this.c.getMsg());
            jSONObject.put("costTime", j2);
            this.c.a(jSONObject.toString());
        } catch (JSONException e2) {
            ak.e(e2);
        }
        a("costTime", ("eLogin".equals(this.d) && this.b == GyErrorCode.LOGIN_PAGE_DISMISSED) ? 0 : Long.valueOf(j2));
    }

    public boolean a() {
        return this.c.isSuccess();
    }

    public b b(String str) {
        try {
            if (!a() && a(str)) {
                this.g.put("operator_result", new JSONObject(str));
            }
        } catch (Throwable th) {
            ak.e("putOperatorExtIfFailed", th);
        }
        return this;
    }

    @Deprecated
    public String b() {
        return this.c.getMsg();
    }

    public GYResponse c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.f2048e;
    }

    public String f() {
        try {
            return new JSONObject(this.g).toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public GyErrorCode g() {
        return this.b;
    }

    public String h() {
        try {
            JSONObject jSONObject = new JSONObject();
            Long l = (Long) this.g.get("costTime");
            if (l != null) {
                jSONObject.put("costTime", l);
            }
            if (a()) {
                jSONObject.put("pm", c(new JSONObject(b()).getString("number")));
            } else {
                Object obj = this.g.get("operator_result");
                if (obj instanceof JSONObject) {
                    jSONObject.put("operator_result", obj);
                }
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            ak.e("getOperatorPreLoginExt", th);
            return "";
        }
    }

    public String toString() {
        return this.c.toString();
    }
}
