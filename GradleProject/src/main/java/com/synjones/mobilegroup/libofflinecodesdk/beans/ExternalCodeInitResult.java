package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalCodeInitResult {
    public String code;
    public String msg;

    public ExternalCodeInitResult(String str, String str2) {
        this.code = str;
        this.msg = str2;
    }

    public String toString() {
        return "ExternalCodeInitResult{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
