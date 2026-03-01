package io.dcloud.feature.payment;

import io.dcloud.common.DHInterface.IReflectAble;

/* JADX INFO: loaded from: classes3.dex */
public interface IPaymentListener extends IReflectAble {
    public static final int CODE_ACCOUNT_STATUS_ERROR = 62004;
    public static final int CODE_DATA_ERROR = 62003;
    public static final int CODE_DEVICE_NO_SUPPORT = 62002;
    public static final int CODE_NETWORK_ERROR = 62008;
    public static final int CODE_NO_INSTALL_MOBILE_SP = 62000;
    public static final int CODE_ORDER_INFO_ERROR = 62005;
    public static final int CODE_PAY_OPTION_ERROR = 62006;
    public static final int CODE_PAY_SERVER_ERROR = 62007;
    public static final int CODE_SUCCESS = 1;
    public static final int CODE_UNKNOWN = 62009;
    public static final int CODE_USER_CANCEL = 62001;

    void onError(int i, String str);

    void onSuccess(PaymentResult paymentResult);
}
