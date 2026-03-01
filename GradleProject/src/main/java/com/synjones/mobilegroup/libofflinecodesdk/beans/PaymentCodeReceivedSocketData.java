package com.synjones.mobilegroup.libofflinecodesdk.beans;

/* JADX INFO: loaded from: classes2.dex */
public class PaymentCodeReceivedSocketData {
    public int code;
    public DataBean data;
    public String msg;
    public boolean success;

    public static class DataBean {
        public String callerRemark;
        public String content;
    }
}
