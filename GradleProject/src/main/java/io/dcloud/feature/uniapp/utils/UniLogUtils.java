package io.dcloud.feature.uniapp.utils;

import com.taobao.weex.utils.WXLogUtils;

/* JADX INFO: loaded from: classes3.dex */
public class UniLogUtils extends WXLogUtils {
    public static final String UNI_PERF_TAG = "uni_perf";
    public static final String UNI_TAG = "uni";

    public static void d(String str) {
        WXLogUtils.d(UNI_TAG, str);
    }

    public static void e(String str) {
        WXLogUtils.e(UNI_TAG, str);
    }

    public static void i(String str) {
        WXLogUtils.i(UNI_TAG, str);
    }

    public static void info(String str) {
        WXLogUtils.i(UNI_TAG, str);
    }

    public static void v(String str) {
        WXLogUtils.v(UNI_TAG, str);
    }

    public static void w(String str) {
        WXLogUtils.w(UNI_TAG, str);
    }

    public static void d(String str, byte[] bArr) {
        WXLogUtils.d(str, new String(bArr));
    }

    public static void e(String str, byte[] bArr) {
        WXLogUtils.e(str, new String(bArr));
    }

    public static void i(String str, byte[] bArr) {
        WXLogUtils.i(str, new String(bArr));
    }

    public static void w(String str, byte[] bArr) {
        WXLogUtils.w(str, new String(bArr));
    }
}
