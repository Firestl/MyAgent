package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalSocketReceiveResult {
    public String code;
    public Map data;
    public String msg;

    public ExternalSocketReceiveResult(String str, String str2, Map map) {
        this.code = str;
        this.msg = str2;
        this.data = map;
    }

    public String toString() {
        return "ExternalSocketReceiveResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
