package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetBarCodeBean extends CommonBaseResponse<DataBean> {

    public static class DataBean {
        public String account;
        public List<String> barcode;
        public String errmsg;
        public int expires;
        public String retcode;

        public String toString() {
            return "DataBean{retcode='" + this.retcode + Operators.SINGLE_QUOTE + ", errmsg=" + this.errmsg + ", account='" + this.account + Operators.SINGLE_QUOTE + ", expires=" + this.expires + ", barcode=" + this.barcode + Operators.BLOCK_END;
        }
    }
}
