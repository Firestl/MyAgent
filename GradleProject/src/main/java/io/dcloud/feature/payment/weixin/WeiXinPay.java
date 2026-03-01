package io.dcloud.feature.payment.weixin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.dcloud.ProcessMediator;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.payment.AbsPaymentChannel;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class WeiXinPay extends AbsPaymentChannel implements ISysEventListener {
    public static String APPID = null;
    public static final String ERR_MSG_AUTH_DENIED = "Authentication failed";
    public static final String ERR_MSG_COMM = "General errors";
    public static final String ERR_MSG_SENT_FAILED = "Unable to send";
    public static final String ERR_MSG_UNSUPPORT = "Unsupport error";
    public static final String ERR_MSG_USER_CANCEL = "User canceled";
    public static final String PNAME_MM = "com.tencent.mm";
    public static final String URL_MARKET_DETAILS = "market://search?q=pname:%s";
    public IWXAPI api;
    public boolean isInstallingService = false;
    public String statement = null;
    public FeatureMessageDispatcher.MessageListener sPayCallbackMessageListener = new FeatureMessageDispatcher.MessageListener() { // from class: io.dcloud.feature.payment.weixin.WeiXinPay.2
        @Override // io.dcloud.common.DHInterface.FeatureMessageDispatcher.MessageListener
        public void onReceiver(Object obj) {
            if (obj instanceof BaseResp) {
                WeiXinPay.this.executePayCallbackMsg((BaseResp) obj);
                FeatureMessageDispatcher.unregisterListener(WeiXinPay.this.sPayCallbackMessageListener);
            }
        }
    };

    public static class PayInfoResult {
        public static final String KEY_APPID = "appid";
        public static final String KEY_NONCESTR = "noncestr";
        public static final String KEY_PACKAGE = "package";
        public static final String KEY_PARTNERID = "partnerid";
        public static final String KEY_PREPAYID = "prepayid";
        public static final String KEY_RETCODE = "retcode";
        public static final String KEY_RETMSG = "retmsg";
        public static final String KEY_SIGN = "sign";
        public static final String KEY_TIMESTAMP = "timestamp";
        public String appid;
        public String noncestr;
        public String partnerid;
        public String prepayid;
        public String result_package;
        public int retcode;
        public String retmsg;
        public String sign;
        public String timestamp;

        public PayInfoResult() {
        }

        public void parseFrom(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.appid = jSONObject.optString("appid");
                if (jSONObject.has("nonceStr")) {
                    this.noncestr = jSONObject.optString("nonceStr");
                } else {
                    this.noncestr = jSONObject.optString(KEY_NONCESTR);
                }
                this.result_package = jSONObject.optString("package", "Sign=WXPay");
                this.partnerid = jSONObject.optString(KEY_PARTNERID);
                this.prepayid = jSONObject.optString(KEY_PREPAYID);
                this.timestamp = jSONObject.optString("timestamp");
                this.sign = jSONObject.optString(KEY_SIGN);
            } catch (Exception unused) {
                this.retcode = -1000;
            }
        }
    }

    private boolean hasFullConfigData() {
        return !TextUtils.isEmpty(APPID);
    }

    private boolean hasGeneralError() {
        if (!hasFullConfigData()) {
            this.mListener.onError(-7, DOMException.toString(DOMException.MSG_BUSINESS_PARAMETER_HAS_NOT));
            return true;
        }
        if (PlatformUtil.isAppInstalled(this.mContext, "com.tencent.mm")) {
            return false;
        }
        this.mListener.onError(-8, DOMException.toString(DOMException.MSG_CLIENT_UNINSTALLED));
        return true;
    }

    public static boolean hasWXPayEntryActivity(Context context) {
        try {
            Class.forName(context.getPackageName() + ".wxapi.WXPayEntryActivity");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private void startWXPayMediator(PayReq payReq) {
        Intent intent = new Intent();
        intent.putExtra(ProcessMediator.LOGIC_CLASS, WXPayMediator.class.getName());
        Bundle bundle = new Bundle();
        payReq.toBundle(bundle);
        intent.putExtra("req", bundle);
        intent.putExtra("transaction", payReq.prepayId);
        intent.putExtra(ProcessMediator.PROCESS_ACTIVITY_SOURCE, this.mWebview.getActivity().getClass().getName());
        intent.setClassName(this.mWebview.getActivity(), ProcessMediator.class.getName());
        this.mWebview.getActivity().startActivityForResult(intent, 1000);
        this.mWebview.getActivity().overridePendingTransition(0, 0);
        this.mWebview.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.feature.payment.weixin.WeiXinPay.1
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                ((Integer) objArr[1]).intValue();
                Intent intent2 = (Intent) objArr[2];
                if (intent2 == null || sysEventType != ISysEventListener.SysEventType.onActivityResult || iIntValue != 1000 || !intent2.hasExtra("result")) {
                    return false;
                }
                Bundle bundleExtra = intent2.getBundleExtra("result");
                String string = bundleExtra.getString("style");
                if ("BaseResp".equals(string)) {
                    PayResp payResp = new PayResp();
                    payResp.fromBundle(bundleExtra);
                    WeiXinPay.this.sPayCallbackMessageListener.onReceiver(payResp);
                } else if ("BaseReq".equals(string)) {
                    PayReq payReq2 = new PayReq();
                    payReq2.fromBundle(bundleExtra);
                    WeiXinPay.this.sPayCallbackMessageListener.onReceiver(payReq2);
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
    }

    public void executePayCallbackMsg(BaseResp baseResp) {
        if (baseResp != null) {
            int i = baseResp.errCode;
            StringBuffer stringBuffer = new StringBuffer(Operators.BLOCK_START_STR);
            Bundle bundle = new Bundle();
            baseResp.toBundle(bundle);
            Set<String> setKeySet = bundle.keySet();
            if (setKeySet != null) {
                int size = setKeySet.size();
                String[] strArr = new String[size];
                setKeySet.toArray(strArr);
                for (int i2 = 0; i2 < size; i2++) {
                    String str = strArr[i2];
                    stringBuffer.append("'");
                    stringBuffer.append(str);
                    stringBuffer.append("':");
                    stringBuffer.append("'");
                    stringBuffer.append(bundle.get(str));
                    stringBuffer.append("'");
                    if (i2 != size - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
            stringBuffer.append(Operators.BLOCK_END_STR);
            onPayCallback(i, stringBuffer.toString());
        }
    }

    @Override // io.dcloud.feature.payment.AbsPaymentChannel
    public void init(Context context) {
        super.init(context);
        this.id = "wxpay";
        this.description = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_wxpay_plugin_description);
        this.serviceReady = PlatformUtil.isAppInstalled(this.mContext, "com.tencent.mm");
        String metaValue = AndroidResources.getMetaValue("WX_APPID");
        APPID = metaValue;
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(context, metaValue);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(APPID);
    }

    @Override // io.dcloud.feature.payment.AbsPaymentChannel
    public void installService() {
        try {
            PlatformUtil.openURL(this.mContext, StringUtil.format(URL_MARKET_DETAILS, "com.tencent.mm"), null);
            this.isInstallingService = true;
            this.mWebview.obtainApp().registerSysEventListener(this, ISysEventListener.SysEventType.onResume);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mWebview.obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onResume);
        }
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType == ISysEventListener.SysEventType.onResume) {
            this.serviceReady = PlatformUtil.isAppInstalled(this.mContext, "com.tencent.mm");
            this.isInstallingService = false;
            this.mWebview.obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onResume);
            if (!this.serviceReady) {
                this.mListener.onError(-8, DOMException.MSG_CLIENT_UNINSTALLED);
            } else if (!PdrUtil.isEmpty(this.statement)) {
                request(this.statement);
                this.statement = null;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPayCallback(int r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            if (r5 != 0) goto L7
            r1 = 1
        L5:
            r2 = r0
            goto L24
        L7:
            r2 = -4
            if (r5 != r2) goto Ld
            java.lang.String r2 = "Authentication failed"
            goto L24
        Ld:
            r2 = -1
            if (r5 != r2) goto L13
            java.lang.String r2 = "General errors"
            goto L24
        L13:
            r2 = -3
            if (r5 != r2) goto L19
            java.lang.String r2 = "Unable to send"
            goto L24
        L19:
            r2 = -5
            if (r5 != r2) goto L1f
            java.lang.String r2 = "Unsupport error"
            goto L24
        L1f:
            r2 = -2
            if (r5 != r2) goto L5
            java.lang.String r2 = "User canceled"
        L24:
            java.lang.String r3 = "wxpay"
            if (r1 == 0) goto L3a
            java.lang.String r5 = "pay success"
            io.dcloud.common.adapter.util.Logger.d(r3, r5)
            io.dcloud.feature.payment.PaymentResult r5 = new io.dcloud.feature.payment.PaymentResult
            r5.<init>(r4)
            r5.rawDataJson = r6
            io.dcloud.feature.payment.IPaymentListener r6 = r4.mListener
            r6.onSuccess(r5)
            goto L5d
        L3a:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "pay failed code="
            r6.append(r1)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            io.dcloud.common.adapter.util.Logger.d(r3, r6)
            io.dcloud.feature.payment.IPaymentListener r6 = r4.mListener
            r1 = -100
            java.lang.String r3 = r4.getFullDescription()
            java.lang.String r5 = io.dcloud.common.constant.DOMException.toString(r5, r3, r2, r0)
            r6.onError(r1, r5)
        L5d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.payment.weixin.WeiXinPay.onPayCallback(int, java.lang.String):void");
    }

    @Override // io.dcloud.feature.payment.AbsPaymentChannel
    public void request(String str) {
        if (hasGeneralError()) {
            return;
        }
        if (!PlatformUtil.isAppInstalled(this.mContext, "com.tencent.mm")) {
            if (this.isInstallingService) {
                this.statement = str;
                return;
            } else {
                this.mListener.onError(-8, DOMException.MSG_CLIENT_UNINSTALLED);
                return;
            }
        }
        PayInfoResult payInfoResult = new PayInfoResult();
        payInfoResult.parseFrom(str);
        PayReq payReq = new PayReq();
        payReq.appId = payInfoResult.appid;
        payReq.partnerId = payInfoResult.partnerid;
        payReq.prepayId = payInfoResult.prepayid;
        payReq.nonceStr = payInfoResult.noncestr;
        payReq.timeStamp = payInfoResult.timestamp;
        payReq.packageValue = payInfoResult.result_package;
        payReq.sign = payInfoResult.sign;
        if ((this.mWebview.getActivity() instanceof IActivityHandler) && ((IActivityHandler) this.mWebview.getActivity()).isMultiProcessMode()) {
            startWXPayMediator(payReq);
            return;
        }
        boolean zSendReq = this.api.sendReq(payReq);
        if (!zSendReq || !hasWXPayEntryActivity(this.mWebview.getContext())) {
            onPayCallback(zSendReq ? 0 : -1, null);
        } else {
            FeatureMessageDispatcher.registerListener(this.sPayCallbackMessageListener);
            Logger.d("wxpay", "will pay");
        }
    }
}
