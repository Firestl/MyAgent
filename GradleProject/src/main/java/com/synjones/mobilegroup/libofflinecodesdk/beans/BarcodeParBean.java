package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.rp1;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeParBean extends CommonBaseResponse<DataBean> {

    public static class DataBean {
        public String errmsg;
        public ObjBean obj;
        public int retcode;

        public static class ObjBean {
            public String ACCOUNT;
            public String ALGORITHMFLAG;
            public String CARDLOSTTRANSFLAG;
            public String FREEZEFLAG;
            public String IDNO;
            public String KEYID;
            public String LOSTFLAG;
            public String OFFLINEEFFECTIVETIME;
            public String OFFLINEINVALIDDAYS;
            public String OFFLINENUMBER;
            public String OFFLINEPROHIBITPID;
            public String OFFLINESWITCH;
            public String OFFLINEUSERDATA;
            public String OFFLINE_AUTHCODEFLAG;
            public String PID;
            public String SYSTEMTIME;
            public String USERHASHKEY;
            public String VERSION;

            public String toString() {
                return "ObjBean{FREEZEFLAG='" + this.FREEZEFLAG + Operators.SINGLE_QUOTE + ", OFFLINEEFFECTIVETIME='" + this.OFFLINEEFFECTIVETIME + Operators.SINGLE_QUOTE + ", OFFLINESWITCH='" + this.OFFLINESWITCH + Operators.SINGLE_QUOTE + ", OFFLINEPROHIBITPID='" + this.OFFLINEPROHIBITPID + Operators.SINGLE_QUOTE + ", PID='" + this.PID + Operators.SINGLE_QUOTE + ", ACCOUNT='" + this.ACCOUNT + Operators.SINGLE_QUOTE + ", SYSTEMTIME='" + this.SYSTEMTIME + Operators.SINGLE_QUOTE + ", USERHASHKEY='" + this.USERHASHKEY + Operators.SINGLE_QUOTE + ", OFFLINEUSERDATA='" + this.OFFLINEUSERDATA + Operators.SINGLE_QUOTE + ", VERSION='" + this.VERSION + Operators.SINGLE_QUOTE + ", LOSTFLAG='" + this.LOSTFLAG + Operators.SINGLE_QUOTE + ", OFFLINE_AUTHCODEFLAG='" + this.OFFLINE_AUTHCODEFLAG + Operators.SINGLE_QUOTE + ", KEYID='" + this.KEYID + Operators.SINGLE_QUOTE + ", ALGORITHMFLAG='" + this.ALGORITHMFLAG + Operators.SINGLE_QUOTE + ", OFFLINENUMBER='" + this.OFFLINENUMBER + Operators.SINGLE_QUOTE + ", CARDLOSTTRANSFLAG='" + this.CARDLOSTTRANSFLAG + Operators.SINGLE_QUOTE + ", IDNO='" + this.IDNO + Operators.SINGLE_QUOTE + ", OFFLINEINVALIDDAYS='" + this.OFFLINEINVALIDDAYS + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
            }
        }

        public JSONObject toJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(WeiXinOAuthService.KEY_ERRMSG, rp1.a(this.errmsg));
                jSONObject.put(WeiXinPay.PayInfoResult.KEY_RETCODE, this.retcode);
                JSONObject jSONObject2 = new JSONObject();
                if (this.obj == null) {
                    this.obj = new ObjBean();
                }
                jSONObject2.put("FREEZEFLAG", rp1.a(this.obj.FREEZEFLAG));
                jSONObject2.put("OFFLINEEFFECTIVETIME", rp1.a(this.obj.OFFLINEEFFECTIVETIME));
                jSONObject2.put("OFFLINESWITCH", rp1.a(this.obj.OFFLINESWITCH));
                jSONObject2.put("OFFLINEPROHIBITPID", rp1.a(this.obj.OFFLINEPROHIBITPID));
                jSONObject2.put("PID", rp1.a(this.obj.PID));
                jSONObject2.put("ACCOUNT", rp1.a(this.obj.ACCOUNT));
                jSONObject2.put("SYSTEMTIME", rp1.a(this.obj.SYSTEMTIME));
                jSONObject2.put("USERHASHKEY", rp1.a(this.obj.USERHASHKEY));
                jSONObject2.put("OFFLINEUSERDATA", rp1.a(this.obj.OFFLINEUSERDATA));
                jSONObject2.put("VERSION", rp1.a(this.obj.VERSION));
                jSONObject2.put("LOSTFLAG", rp1.a(this.obj.LOSTFLAG));
                jSONObject2.put("OFFLINE_AUTHCODEFLAG", rp1.a(this.obj.OFFLINE_AUTHCODEFLAG));
                jSONObject2.put("KEYID", rp1.a(this.obj.KEYID));
                jSONObject2.put("ALGORITHMFLAG", rp1.a(this.obj.ALGORITHMFLAG));
                jSONObject2.put("OFFLINENUMBER", rp1.a(this.obj.OFFLINENUMBER));
                jSONObject2.put("CARDLOSTTRANSFLAG", rp1.a(this.obj.CARDLOSTTRANSFLAG));
                jSONObject2.put("IDNO", rp1.a(this.obj.IDNO));
                jSONObject2.put("OFFLINEINVALIDDAYS", rp1.a(this.obj.OFFLINEINVALIDDAYS));
                jSONObject.put("obj", jSONObject2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        public String toString() {
            return "DataBean{obj=" + this.obj + ", errmsg='" + this.errmsg + Operators.SINGLE_QUOTE + ", retcode='" + this.retcode + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean$DataBean] */
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("code", Integer.valueOf(this.code)).putOpt("msg", rp1.a(this.msg));
        if (this.data == 0) {
            this.data = new DataBean();
        }
        jSONObject.put("data", ((DataBean) this.data).toJSONObject());
        return jSONObject;
    }
}
