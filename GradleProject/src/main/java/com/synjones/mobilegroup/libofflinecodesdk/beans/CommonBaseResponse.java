package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class CommonBaseResponse<T> {
    public int code;
    public T data;
    public boolean isForceUpdate;
    public String msg;
    public boolean success;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CommonBaseResponse commonBaseResponse = (CommonBaseResponse) obj;
        if (this.success != commonBaseResponse.success || this.code != commonBaseResponse.code) {
            return false;
        }
        String str = this.msg;
        if (str == null ? commonBaseResponse.msg != null : !str.equals(commonBaseResponse.msg)) {
            return false;
        }
        T t = this.data;
        T t2 = commonBaseResponse.data;
        return t != null ? t.equals(t2) : t2 == null;
    }

    public String toString() {
        return "CommonBaseResponse{code=" + this.code + ", success=" + this.success + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
