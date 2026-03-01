package com.cmic.gen.sdk.c.b;

import com.taobao.weex.el.parse.Operators;
import com.tencent.stat.DeviceInfo;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h extends a {
    public String x = "";
    public String y = "";

    @Override // com.cmic.gen.sdk.c.b.g
    public String a(String str) {
        return this.b + this.c + this.d + this.f1703e + this.f + this.g + this.h + this.i + this.j + this.m + this.n + str + this.o + this.q + this.r + this.s + this.t + this.u + this.v + this.x + this.y + this.w;
    }

    @Override // com.cmic.gen.sdk.c.b.a
    public void a_(String str) {
        this.v = t(str);
    }

    @Override // com.cmic.gen.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceInfo.TAG_VERSION, this.f1702a);
            jSONObject.put("sdkver", this.b);
            jSONObject.put("appid", this.c);
            jSONObject.put("imsi", this.d);
            jSONObject.put("operatortype", this.f1703e);
            jSONObject.put("networktype", this.f);
            jSONObject.put("mobilebrand", this.g);
            jSONObject.put("mobilemodel", this.h);
            jSONObject.put("mobilesystem", this.i);
            jSONObject.put("clienttype", this.j);
            jSONObject.put("interfacever", this.k);
            jSONObject.put("expandparams", this.l);
            jSONObject.put("msgid", this.m);
            jSONObject.put("timestamp", this.n);
            jSONObject.put("subimsi", this.o);
            jSONObject.put(WeiXinPay.PayInfoResult.KEY_SIGN, this.p);
            jSONObject.put("apppackage", this.q);
            jSONObject.put("appsign", this.r);
            jSONObject.put("ipv4_list", this.s);
            jSONObject.put("ipv6_list", this.t);
            jSONObject.put("sdkType", this.u);
            jSONObject.put("tempPDR", this.v);
            jSONObject.put("scrip", this.x);
            jSONObject.put("userCapaid", this.y);
            jSONObject.put("funcType", this.w);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return this.f1702a + "&" + this.b + "&" + this.c + "&" + this.d + "&" + this.f1703e + "&" + this.f + "&" + this.g + "&" + this.h + "&" + this.i + "&" + this.j + "&" + this.k + "&" + this.l + "&" + this.m + "&" + this.n + "&" + this.o + "&" + this.p + "&" + this.q + "&" + this.r + Operators.AND + this.s + "&" + this.t + "&" + this.u + "&" + this.v + "&" + this.x + "&" + this.y + "&" + this.w;
    }

    public void v(String str) {
        this.x = t(str);
    }

    public void w(String str) {
        this.y = t(str);
    }
}
