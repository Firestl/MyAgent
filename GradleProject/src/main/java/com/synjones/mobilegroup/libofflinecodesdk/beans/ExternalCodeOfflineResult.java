package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalCodeOfflineResult {
    public String code;
    public DataBean data;
    public String msg;

    public static class DataBean {
        public String maxCount;
        public String qrcode;
        public String useCount;

        public DataBean(String str, String str2, String str3) {
            this.maxCount = str;
            this.useCount = str2;
            this.qrcode = str3;
        }

        public String toString() {
            return "DataBean{maxCount='" + this.maxCount + Operators.SINGLE_QUOTE + ", useCount='" + this.useCount + Operators.SINGLE_QUOTE + ", qrcode='" + this.qrcode + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public ExternalCodeOfflineResult(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }

    public String toString() {
        return "ExternalCodeOfflineResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }

    public ExternalCodeOfflineResult(String str, String str2, DataBean dataBean) {
        this.code = str;
        this.msg = str2;
        this.data = dataBean;
    }
}
