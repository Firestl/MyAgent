package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class GetSdkListBean extends CommonBaseResponse<DataBean> {

    public static class DataBean {
        public String errmsg;
        public String retcode;
        public String schoolcode;
        public String sdknamelist;

        public String toString() {
            return "DataBean{retcode='" + this.retcode + Operators.SINGLE_QUOTE + ", errmsg='" + this.errmsg + Operators.SINGLE_QUOTE + ", schoolcode='" + this.schoolcode + Operators.SINGLE_QUOTE + ", sdknamelist='" + this.sdknamelist + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }
}
