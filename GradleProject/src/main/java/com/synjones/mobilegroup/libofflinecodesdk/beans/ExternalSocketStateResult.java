package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalSocketStateResult {
    public String code;
    public DataBean data;
    public String msg;

    public static class DataBean {
        public String barcode;

        public DataBean(String str) {
            this.barcode = str;
        }

        public String toString() {
            return "DataBean{barcode='" + this.barcode + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public ExternalSocketStateResult(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }

    public String toString() {
        return "ExternalSocketResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", dataBean=" + this.data + Operators.BLOCK_END;
    }

    public ExternalSocketStateResult(String str, String str2, DataBean dataBean) {
        this.code = str;
        this.msg = str2;
        this.data = dataBean;
    }
}
