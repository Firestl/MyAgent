package com.efs.sdk.base.http;

import android.text.TextUtils;
import com.efs.sdk.base.core.d.d;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HttpResponse extends d<Map<String, String>> {
    public static final String KEY_BIZ_CODE = "biz_code";
    public static final String KEY_REQUEST_URL = "req_url";
    public static final int REQUEST_ERROR_DEFAULT = -1;
    public static final int REQUEST_ERROR_NETWORK_DISCONNECT = -2;
    public static final int REQUEST_ERROR_SOCKET_TIMEOUT = -3;

    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.HashMap] */
    public HttpResponse() {
        this.extra = new HashMap();
    }

    public String getBizCode() {
        return !((Map) this.extra).containsKey(KEY_BIZ_CODE) ? "" : (String) ((Map) this.extra).get(KEY_BIZ_CODE);
    }

    public int getHttpCode() {
        return this.code;
    }

    public String getReqUrl() {
        return !((Map) this.extra).containsKey(KEY_REQUEST_URL) ? "" : (String) ((Map) this.extra).get(KEY_REQUEST_URL);
    }

    public void setBizCode(String str) {
        ((Map) this.extra).put(KEY_BIZ_CODE, str);
    }

    public void setHttpCode(int i) {
        this.succ = (i >= 200 && i < 300) || i == 304;
        this.code = i;
    }

    public void setReqUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (str.contains(Operators.CONDITION_IF_STRING)) {
            str = str.substring(0, str.indexOf(Operators.CONDITION_IF_STRING));
        }
        ((Map) this.extra).put(KEY_REQUEST_URL, str);
    }

    public String toString() {
        return "HttpResponse {succ=" + this.succ + ", code=" + this.code + ", data='" + this.data + Operators.SINGLE_QUOTE + ", extra=" + this.extra + Operators.BLOCK_END;
    }
}
