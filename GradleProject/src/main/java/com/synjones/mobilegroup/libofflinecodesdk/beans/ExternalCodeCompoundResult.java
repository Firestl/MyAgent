package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalCodeCompoundResult {
    public String code;
    public DataBean data;
    public String msg;

    public static class DataBean {
        public String barcode;
        public String expires;
        public String maxCount;
        public String qrcode;
        public String useCount;

        public DataBean(String str, String str2, String str3, String str4, String str5) {
            this.maxCount = str;
            this.useCount = str2;
            this.qrcode = str5;
            this.barcode = str4;
            this.expires = str3;
        }

        public String toString() {
            return "DataBean{maxCount='" + this.maxCount + Operators.SINGLE_QUOTE + ", useCount='" + this.useCount + Operators.SINGLE_QUOTE + ", qrcode='" + this.qrcode + Operators.SINGLE_QUOTE + ", barcode='" + this.barcode + Operators.SINGLE_QUOTE + ", expires='" + this.expires + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public ExternalCodeCompoundResult(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }

    public String toString() {
        return "ExternalCodeCompoundResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }

    public ExternalCodeCompoundResult(String str, String str2, DataBean dataBean) {
        this.code = str;
        this.msg = str2;
        this.data = dataBean;
    }
}
