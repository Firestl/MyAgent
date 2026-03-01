package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigSDKBean extends CommonBaseResponse<DataBean> {

    public static class ApiInfo {
        public String code;
        public String des;
        public boolean empower;
        public String name;

        public String toString() {
            return "ApiInfo{des='" + this.des + Operators.SINGLE_QUOTE + ", name='" + this.name + Operators.SINGLE_QUOTE + ", code='" + this.code + Operators.SINGLE_QUOTE + ", empower=" + this.empower + Operators.BLOCK_END;
        }
    }

    public static class DataBean {
        public List<ApiInfo> apiInfo;
        public String apiVersion;
        public boolean checkSno;
        public String publicKey;

        public String toString() {
            return "DataBean{apiInfo=" + this.apiInfo + ",publicKey=" + this.publicKey + ",checkSno=" + this.checkSno + ", apiVersion='" + this.apiVersion + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }
}
