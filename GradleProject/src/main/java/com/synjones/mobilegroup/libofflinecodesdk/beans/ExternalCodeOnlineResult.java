package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalCodeOnlineResult {
    public String code;
    public DataBean data;
    public String msg;

    public static class DataBean {
        public String barcode;
        public String expires;
        public String timeStamp;

        public DataBean(String str, String str2, String str3) {
            this.barcode = str;
            this.expires = str2;
            this.timeStamp = str3;
        }

        public String toString() {
            return "DataBean{barcode='" + this.barcode + Operators.SINGLE_QUOTE + ", expires='" + this.expires + Operators.SINGLE_QUOTE + ", timeStamp='" + this.timeStamp + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public ExternalCodeOnlineResult(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }

    public String toString() {
        return "ExternalCodeOnlineResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }

    public ExternalCodeOnlineResult(String str, String str2, DataBean dataBean) {
        this.code = str;
        this.msg = str2;
        this.data = dataBean;
    }
}
