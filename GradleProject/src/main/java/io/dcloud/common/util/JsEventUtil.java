package io.dcloud.common.util;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class JsEventUtil {
    public static String broadcastEvents_format(String str, String str2, boolean z, String... strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("{evt:'%s',args:");
        sb.append(z ? "'%s'" : "%s");
        sb.append(",callbackId:'%s'}");
        return StringUtil.format(sb.toString(), str, str2, strArr);
    }

    public static String eventListener_format(String str, String str2, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("{evt:'%s',args:");
        sb.append(z ? "'%s'" : "%s");
        sb.append(Operators.BLOCK_END_STR);
        return StringUtil.format(sb.toString(), str, str2);
    }
}
