package com.umeng.analytics.pro;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import com.igexin.assist.util.AssistUtils;
import io.dcloud.common.DHInterface.IFeature;

/* JADX INFO: compiled from: DeviceIdSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public class ba {
    @SuppressLint({"SuspiciousIndentation"})
    public static ax a() {
        String str = Build.BRAND;
        bl.a(IFeature.F_DEVICE, "Brand", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (bk.b()) {
            return new bb();
        }
        if (bk.d()) {
            return new bc();
        }
        if (str.equalsIgnoreCase(AssistUtils.BRAND_XIAOMI) || str.equalsIgnoreCase("redmi") || str.equalsIgnoreCase("meitu") || str.equalsIgnoreCase("小米") || str.equalsIgnoreCase("blackshark")) {
            return new bj();
        }
        if (str.equalsIgnoreCase(AssistUtils.BRAND_VIVO)) {
            return new bi();
        }
        if (str.equalsIgnoreCase(AssistUtils.BRAND_OPPO) || str.equalsIgnoreCase("oneplus") || str.equalsIgnoreCase("realme")) {
            return new bg();
        }
        if (str.equalsIgnoreCase("lenovo") || str.equalsIgnoreCase("zuk")) {
            return new bd();
        }
        if (str.equalsIgnoreCase("nubia")) {
            return new bf();
        }
        if (str.equalsIgnoreCase("samsung")) {
            return new bh();
        }
        if (str.equalsIgnoreCase(AssistUtils.BRAND_MZ) || str.equalsIgnoreCase("mblu") || bk.a()) {
            return new be();
        }
        if (bk.e()) {
            return new az();
        }
        return null;
    }

    public static ax b() {
        return new bb();
    }
}
