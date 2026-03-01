package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalCodePayListResult {
    public String code;
    public List<DataBean> data;
    public String msg;

    public static class DataBean {
        public String account;
        public String expdate;
        public String lostFlag;
        public String name;
        public String payacc;
        public String typeId;

        public DataBean(String str, String str2, String str3, String str4, String str5, String str6) {
            this.account = str;
            this.expdate = str2;
            this.lostFlag = str3;
            this.name = str4;
            this.payacc = str5;
            this.typeId = str6;
        }

        public String toString() {
            return "DataBean{account='" + this.account + Operators.SINGLE_QUOTE + ", expdate='" + this.expdate + Operators.SINGLE_QUOTE + ", lostFlag='" + this.lostFlag + Operators.SINGLE_QUOTE + ", name='" + this.name + Operators.SINGLE_QUOTE + ", payacc='" + this.payacc + Operators.SINGLE_QUOTE + ", typeId='" + this.typeId + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public ExternalCodePayListResult(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }

    public String toString() {
        return "ExternalCodePayListResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }

    public ExternalCodePayListResult(String str, String str2, List<DataBean> list) {
        this.code = str;
        this.msg = str2;
        this.data = list;
    }
}
