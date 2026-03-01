package com.alipay.sdk.protocol;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public enum a {
    None("none"),
    WapPay("js://wappay"),
    Update("js://update"),
    OpenWeb("loc:openweb"),
    SetResult("loc:setResult"),
    Exit("loc:exit");

    public String g;

    a(String str) {
        this.g = str;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        a aVar = None;
        for (a aVar2 : values()) {
            if (str.startsWith(aVar2.g)) {
                return aVar2;
            }
        }
        return aVar;
    }
}
