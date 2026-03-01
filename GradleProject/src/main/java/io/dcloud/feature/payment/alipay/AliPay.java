package io.dcloud.feature.payment.alipay;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.app.PayTask;
import com.huawei.hms.framework.common.ContainerUtils;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.constant.DOMException;
import io.dcloud.feature.payment.AbsPaymentChannel;
import io.dcloud.feature.payment.IPaymentListener;
import io.dcloud.feature.payment.PaymentResult;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AliPay extends AbsPaymentChannel {
    public static final int SDK_PAY_FLAG = 1;
    public static String TAG = "AliPay";
    public boolean DEBUG = false;
    public Handler mHandler = new Handler() { // from class: io.dcloud.feature.payment.alipay.AliPay.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String string;
            String strSubstring;
            String strSubstring2;
            String strSubstring3;
            try {
                String str = (String) message.obj;
                if (message.what == 1) {
                    try {
                        String strSubstring4 = str.substring(str.indexOf("resultStatus=") + 14, str.indexOf("};memo="));
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("resultStatus", strSubstring4);
                        if (strSubstring4.equals("9000")) {
                            int i = 6;
                            String strSubstring5 = str.substring(str.indexOf("memo={") + 6, str.indexOf("};result"));
                            jSONObject.put("memo", strSubstring5);
                            String strSubstring6 = str.substring(str.indexOf("result={") + 8, str.length() - 1);
                            jSONObject.put("result", strSubstring6);
                            String[] strArrSplit = strSubstring6.split("\\&");
                            if (strArrSplit == null || strArrSplit.length <= 0) {
                                strSubstring = null;
                                strSubstring2 = null;
                                strSubstring3 = null;
                            } else {
                                int length = strArrSplit.length;
                                strSubstring = null;
                                strSubstring2 = null;
                                strSubstring3 = null;
                                int i2 = 0;
                                while (i2 < length) {
                                    String str2 = strArrSplit[i2];
                                    if (str2.indexOf(ContainerUtils.KEY_VALUE_DELIMITER) > 0) {
                                        if (str2.indexOf("sign=\"") >= 0) {
                                            strSubstring3 = str2.substring(i, str2.length() - 1);
                                        } else {
                                            String[] strArrSplit2 = str2.split("\\=");
                                            if (strArrSplit2.length >= 2) {
                                                if ("notify_url".equals(strArrSplit2[0])) {
                                                    strSubstring = strArrSplit2[1].substring(1, strArrSplit2[1].length() - 1);
                                                } else if ("out_trade_no".equals(strArrSplit2[0])) {
                                                    strSubstring2 = strArrSplit2[1].substring(1, strArrSplit2[1].length() - 1);
                                                }
                                            }
                                        }
                                    }
                                    i2++;
                                    i = 6;
                                }
                            }
                            PaymentResult paymentResult = new PaymentResult(AliPay.this);
                            paymentResult.description = strSubstring5;
                            paymentResult.url = strSubstring;
                            paymentResult.tradeno = strSubstring2;
                            paymentResult.signature = strSubstring3;
                            paymentResult.rawDataJson = jSONObject.toString();
                            AliPay.this.mListener.onSuccess(paymentResult);
                        } else {
                            boolean zEquals = strSubstring4.equals("4000");
                            int i3 = IPaymentListener.CODE_ACCOUNT_STATUS_ERROR;
                            if (zEquals) {
                                string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_unknown);
                            } else {
                                if (strSubstring4.equals("4001")) {
                                    i3 = IPaymentListener.CODE_DATA_ERROR;
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_data_format_error);
                                } else if (strSubstring4.equals("4003")) {
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_account_locked);
                                } else if (strSubstring4.equals("4004")) {
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_user_unbind_account);
                                } else if (strSubstring4.equals("4005")) {
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_bind_fail);
                                } else if (strSubstring4.equals("4006")) {
                                    i3 = IPaymentListener.CODE_PAY_OPTION_ERROR;
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_pay_order_fail);
                                } else if (strSubstring4.equals("4010")) {
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_bind_account_again);
                                } else if (strSubstring4.equals("6000")) {
                                    i3 = IPaymentListener.CODE_PAY_SERVER_ERROR;
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_alipay_service_upgrading);
                                } else if (strSubstring4.equals("6001")) {
                                    i3 = IPaymentListener.CODE_USER_CANCEL;
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_user_cancel_pay);
                                } else if (strSubstring4.equals("6002")) {
                                    i3 = IPaymentListener.CODE_NETWORK_ERROR;
                                    string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_error_msg_network_error);
                                } else {
                                    string = null;
                                }
                                AliPay.this.mListener.onError(-100, DOMException.toString(i3, AliPay.this.getFullDescription(), string, (String) null));
                            }
                            i3 = IPaymentListener.CODE_UNKNOWN;
                            AliPay.this.mListener.onError(-100, DOMException.toString(i3, AliPay.this.getFullDescription(), string, (String) null));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        AliPay.this.mListener.onError(-100, DOMException.toString(IPaymentListener.CODE_UNKNOWN, AliPay.this.getFullDescription(), e2.getMessage(), (String) null));
                    }
                }
                super.handleMessage(message);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    };

    @Override // io.dcloud.feature.payment.AbsPaymentChannel
    public void init(Context context) {
        super.init(context);
        this.description = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_alipay_plugin_name);
        this.serviceReady = true;
    }

    @Override // io.dcloud.feature.payment.AbsPaymentChannel
    public void installService() {
    }

    @Override // io.dcloud.feature.payment.AbsPaymentChannel
    public void request(final String str) {
        new Thread(new Runnable() { // from class: io.dcloud.feature.payment.alipay.AliPay.1
            @Override // java.lang.Runnable
            public void run() {
                String strPay = new PayTask(AliPay.this.mWebview.getActivity()).pay(str, true);
                Message message = new Message();
                message.what = 1;
                message.obj = strPay;
                AliPay.this.mHandler.sendMessage(message);
            }
        }).start();
    }
}
