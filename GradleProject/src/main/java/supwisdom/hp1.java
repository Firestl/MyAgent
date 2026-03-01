package supwisdom;

import com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ConfigSDKBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetBarCodeBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetRequestLogBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.GetSdkListBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JniResponseBean;
import com.synjones.mobilegroup.libofflinecodesdk.beans.JniResponseCreateCodeBean;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class hp1 {
    /* JADX WARN: Type inference failed for: r10v13, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r10v14, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.GetSdkListBean$DataBean] */
    /* JADX WARN: Type inference failed for: r10v15, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.ConfigSDKBean$DataBean] */
    /* JADX WARN: Type inference failed for: r10v6, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.GetBarCodeBean$DataBean] */
    /* JADX WARN: Type inference failed for: r10v9, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean$DataBean] */
    /* JADX WARN: Type inference failed for: r9v10, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.BarcodeParBean, com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse] */
    /* JADX WARN: Type inference failed for: r9v11, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.CodeBarPayInfoBean, com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse] */
    /* JADX WARN: Type inference failed for: r9v12, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse, com.synjones.mobilegroup.libofflinecodesdk.beans.GetSdkListBean] */
    /* JADX WARN: Type inference failed for: r9v13, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse, com.synjones.mobilegroup.libofflinecodesdk.beans.ConfigSDKBean] */
    /* JADX WARN: Type inference failed for: r9v8, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse, com.synjones.mobilegroup.libofflinecodesdk.beans.GetRequestLogBean] */
    /* JADX WARN: Type inference failed for: r9v9, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.CommonBaseResponse, com.synjones.mobilegroup.libofflinecodesdk.beans.GetBarCodeBean] */
    public static <T> T a(String str, Class<T> cls) throws JSONException {
        JSONArray jSONArray;
        JSONObject jSONObject = new JSONObject(str);
        if (cls == ConfigSDKBean.class) {
            ?? r9 = (T) new ConfigSDKBean();
            ?? r10 = (T) new ConfigSDKBean.DataBean();
            int i = jSONObject.getInt("code");
            String string = jSONObject.getString("msg");
            r9.data = r10;
            r9.code = i;
            r9.msg = string;
            if (i == 0 || (i == 200 && jSONObject.toString().contains("\"data\":"))) {
                List<ConfigSDKBean.ApiInfo> arrayList = new ArrayList<>();
                String strTrim = jSONObject.toString().trim();
                int iIndexOf = strTrim.indexOf("\"data\":") + 7;
                String strSubstring = strTrim.substring(iIndexOf, iIndexOf + 1);
                if (Operators.ARRAY_START_STR.equals(strSubstring)) {
                    r10.apiVersion = "0";
                    arrayList = a(jSONObject.getJSONArray("data"));
                } else if (Operators.BLOCK_START_STR.equals(strSubstring)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    r10.apiVersion = jSONObject2.getString("apiVersion");
                    r10.publicKey = jSONObject2.getString("publicKey");
                    try {
                        r10.checkSno = jSONObject2.getBoolean("checkSno");
                    } catch (JSONException unused) {
                    }
                    arrayList = a(jSONObject2.getJSONArray("apiInfo"));
                }
                r10.apiInfo = arrayList;
            } else {
                System.out.println("数据异常：" + i + ", msg=" + string);
            }
            return r9;
        }
        if (cls == GetSdkListBean.class) {
            ?? r92 = (T) new GetSdkListBean();
            ?? r102 = (T) new GetSdkListBean.DataBean();
            int i2 = jSONObject.getInt("code");
            String string2 = jSONObject.getString("msg");
            r92.data = r102;
            r92.code = i2;
            r92.msg = string2;
            if (i2 == 0 || i2 == 200) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                r102.retcode = jSONObject3.getString(WeiXinPay.PayInfoResult.KEY_RETCODE);
                r102.errmsg = jSONObject3.getString(WeiXinOAuthService.KEY_ERRMSG);
                r102.schoolcode = jSONObject3.getString("schoolcode");
                r102.sdknamelist = jSONObject3.getString("sdknamelist");
            }
            return r92;
        }
        int i3 = 0;
        if (cls == CodeBarPayInfoBean.class) {
            ?? r93 = (T) new CodeBarPayInfoBean();
            ?? r103 = (T) new ArrayList();
            int i4 = jSONObject.getInt("code");
            String string3 = jSONObject.getString("msg");
            r93.data = r103;
            r93.code = i4;
            r93.msg = string3;
            if ((i4 == 0 || i4 == 200) && (jSONArray = jSONObject.getJSONArray("data")) != null) {
                while (i3 < jSONArray.length()) {
                    CodeBarPayInfoBean.DataBean dataBean = new CodeBarPayInfoBean.DataBean();
                    JSONObject jSONObject4 = jSONArray.getJSONObject(i3);
                    jSONObject4.isNull("elec_accamt");
                    jSONObject4.isNull("unsettle_amount");
                    try {
                        dataBean.id = jSONObject4.getInt("id");
                        dataBean.account = jSONObject4.getString("account");
                        dataBean.paytype = jSONObject4.getString("paytype");
                        dataBean.payacc = jSONObject4.getString("payacc");
                        dataBean.bankRelation = jSONObject4.getBoolean("bankRelation");
                        dataBean.name = jSONObject4.getString("name");
                        dataBean.code = jSONObject4.getString("code");
                        dataBean.payif = jSONObject4.getString("payif");
                        dataBean.icon = jSONObject4.getString("icon");
                        dataBean.status = jSONObject4.getInt("status");
                        dataBean.payid = jSONObject4.getInt("payid");
                        dataBean.voucher = jSONObject4.getString("voucher");
                        dataBean.voucherStatus = jSONObject4.getInt("voucherStatus");
                        dataBean.lostflag = jSONObject4.getInt("lostflag");
                        dataBean.expdate = jSONObject4.getString("expdate");
                        dataBean.bandacc = jSONObject4.getString("bandacc");
                        dataBean.unsettle_amount = jSONObject4.getInt("unsettle_amount");
                        dataBean.db_balance = jSONObject4.getInt("db_balance");
                        dataBean.elec_accamt = jSONObject4.getInt("elec_accamt");
                        dataBean.accinfo_balance = jSONObject4.getInt("accinfo_balance");
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    r103.add(dataBean);
                    i3++;
                }
            }
            return r93;
        }
        if (cls != BarcodeParBean.class) {
            if (cls != GetBarCodeBean.class) {
                if (cls != GetRequestLogBean.class) {
                    return null;
                }
                ?? r94 = (T) new GetRequestLogBean();
                try {
                    r94.code = jSONObject.getInt("code");
                    r94.success = jSONObject.getBoolean("success");
                    r94.msg = jSONObject.getString("msg");
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                return r94;
            }
            ?? r95 = (T) new GetBarCodeBean();
            ?? r104 = (T) new GetBarCodeBean.DataBean();
            int i5 = jSONObject.getInt("code");
            String string4 = jSONObject.getString("msg");
            r95.data = r104;
            r95.code = i5;
            r95.msg = string4;
            if (i5 == 0 || i5 == 200) {
                JSONObject jSONObject5 = jSONObject.getJSONObject("data");
                r104.retcode = jSONObject5.getString(WeiXinPay.PayInfoResult.KEY_RETCODE);
                r104.errmsg = jSONObject5.getString(WeiXinOAuthService.KEY_ERRMSG);
                r104.account = jSONObject5.getString("account");
                r104.expires = jSONObject5.getInt("expires");
                ArrayList arrayList2 = new ArrayList();
                r104.barcode = arrayList2;
                JSONArray jSONArray2 = jSONObject5.getJSONArray("barcode");
                if (jSONArray2 != null) {
                    while (i3 < jSONArray2.length()) {
                        arrayList2.add(jSONArray2.getString(i3));
                        i3++;
                    }
                }
            }
            return r95;
        }
        ?? r96 = (T) new BarcodeParBean();
        ?? r105 = (T) new BarcodeParBean.DataBean();
        int i6 = jSONObject.getInt("code");
        String string5 = jSONObject.getString("msg");
        r96.data = r105;
        r96.code = i6;
        r96.msg = string5;
        if (i6 == 0 || i6 == 200) {
            JSONObject jSONObject6 = jSONObject.getJSONObject("data");
            BarcodeParBean.DataBean.ObjBean objBean = new BarcodeParBean.DataBean.ObjBean();
            r105.obj = objBean;
            r105.errmsg = jSONObject6.getString(WeiXinOAuthService.KEY_ERRMSG);
            r105.retcode = jSONObject6.getInt(WeiXinPay.PayInfoResult.KEY_RETCODE);
            JSONObject jSONObject7 = jSONObject6.getJSONObject("obj");
            objBean.FREEZEFLAG = jSONObject7.getString("FREEZEFLAG");
            objBean.OFFLINEEFFECTIVETIME = jSONObject7.getString("OFFLINEEFFECTIVETIME");
            objBean.OFFLINESWITCH = jSONObject7.getString("OFFLINESWITCH");
            objBean.OFFLINEPROHIBITPID = jSONObject7.getString("OFFLINEPROHIBITPID");
            objBean.PID = jSONObject7.getString("PID");
            objBean.ACCOUNT = jSONObject7.getString("ACCOUNT");
            objBean.SYSTEMTIME = jSONObject7.getString("SYSTEMTIME");
            objBean.USERHASHKEY = jSONObject7.getString("USERHASHKEY");
            objBean.OFFLINEUSERDATA = jSONObject7.getString("OFFLINEUSERDATA");
            objBean.VERSION = jSONObject7.getString("VERSION");
            objBean.LOSTFLAG = jSONObject7.getString("LOSTFLAG");
            objBean.OFFLINE_AUTHCODEFLAG = jSONObject7.getString("OFFLINE_AUTHCODEFLAG");
            objBean.KEYID = jSONObject7.getString("KEYID");
            objBean.ALGORITHMFLAG = jSONObject7.getString("ALGORITHMFLAG");
            objBean.OFFLINENUMBER = jSONObject7.getString("OFFLINENUMBER");
            objBean.CARDLOSTTRANSFLAG = jSONObject7.getString("CARDLOSTTRANSFLAG");
            objBean.IDNO = jSONObject7.getString("IDNO");
            objBean.OFFLINEINVALIDDAYS = jSONObject7.getString("OFFLINEINVALIDDAYS");
        }
        return r96;
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [T, com.synjones.mobilegroup.libofflinecodesdk.beans.JniResponseBean] */
    public static <T> T b(String str, Class<T> cls) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (cls != JniResponseBean.class) {
                if (cls == JniResponseCreateCodeBean.class) {
                    return (T) b(jSONObject);
                }
                return null;
            }
            ?? r2 = (T) new JniResponseBean();
            JniResponseBean.MessageBean messageBean = new JniResponseBean.MessageBean();
            r2.message = messageBean;
            JSONObject jSONObject2 = jSONObject.getJSONObject("message");
            messageBean.retcode = jSONObject2.getInt(WeiXinPay.PayInfoResult.KEY_RETCODE);
            messageBean.retmsg = jSONObject2.getString(WeiXinPay.PayInfoResult.KEY_RETMSG);
            return r2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static JniResponseCreateCodeBean b(JSONObject jSONObject) throws JSONException {
        JniResponseCreateCodeBean jniResponseCreateCodeBean = new JniResponseCreateCodeBean();
        JniResponseCreateCodeBean.MessageBean messageBean = new JniResponseCreateCodeBean.MessageBean();
        jniResponseCreateCodeBean.message = messageBean;
        JSONObject jSONObject2 = jSONObject.getJSONObject("message");
        messageBean.retcode = jSONObject2.getInt(WeiXinPay.PayInfoResult.KEY_RETCODE);
        messageBean.retmsg = jSONObject2.getString(WeiXinPay.PayInfoResult.KEY_RETMSG);
        messageBean.qrcode_data = jSONObject2.getString("qrcode_data");
        return jniResponseCreateCodeBean;
    }

    public static List<ConfigSDKBean.ApiInfo> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    ConfigSDKBean.ApiInfo apiInfo = new ConfigSDKBean.ApiInfo();
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    apiInfo.des = jSONObject.getString("des");
                    apiInfo.name = jSONObject.getString("name");
                    apiInfo.code = jSONObject.getString("code");
                    apiInfo.empower = jSONObject.getBoolean("empower");
                    arrayList.add(apiInfo);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static CodeBarPayInfoBean.DataBean a(JSONObject jSONObject) {
        CodeBarPayInfoBean.DataBean dataBean = new CodeBarPayInfoBean.DataBean();
        dataBean.id = jSONObject.getInt("id");
        dataBean.account = jSONObject.getString("account");
        dataBean.paytype = jSONObject.getString("paytype");
        dataBean.payacc = jSONObject.getString("payacc");
        dataBean.bankRelation = jSONObject.getBoolean("bankRelation");
        dataBean.name = jSONObject.getString("name");
        dataBean.code = jSONObject.getString("code");
        dataBean.payif = jSONObject.getString("payif");
        dataBean.icon = jSONObject.getString("icon");
        dataBean.status = jSONObject.getInt("status");
        dataBean.payid = jSONObject.getInt("payid");
        dataBean.voucher = jSONObject.getString("voucher");
        dataBean.voucherStatus = jSONObject.getInt("voucherStatus");
        dataBean.lostflag = jSONObject.getInt("lostflag");
        dataBean.expdate = jSONObject.getString("expdate");
        dataBean.bandacc = jSONObject.getString("bandacc");
        dataBean.unsettle_amount = jSONObject.getInt("unsettle_amount");
        dataBean.db_balance = jSONObject.getInt("db_balance");
        dataBean.elec_accamt = jSONObject.getInt("elec_accamt");
        dataBean.accinfo_balance = jSONObject.getInt("accinfo_balance");
        return dataBean;
    }
}
