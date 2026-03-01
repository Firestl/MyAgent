package io.dcloud.g;

import android.content.Context;
import io.dcloud.common.DHInterface.IConfusionMgr;
import io.dcloud.common.DHInterface.INativeAppInfo;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static IConfusionMgr f6701a;

    public static void a(INativeAppInfo iNativeAppInfo) {
        if (iNativeAppInfo != null) {
            f6701a = iNativeAppInfo.getCofusionMgr();
        }
    }

    public static String b(String str, boolean z, int i) {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.encodeString(str, z, i);
        }
        return null;
    }

    public static String c() {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.getSQK();
        }
        return null;
    }

    public static String a(String str, boolean z, int i) {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.decodeString(str, z, i);
        }
        return null;
    }

    public static String b(String str) {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.decryptStr(str);
        }
        return null;
    }

    public static String a(Context context, byte[] bArr) {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.handleEncryption(context, bArr);
        }
        return null;
    }

    public static String b() {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.getSK();
        }
        return null;
    }

    public static String a(String str) {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.decodeString(str);
        }
        return null;
    }

    public static String a() {
        IConfusionMgr iConfusionMgr = f6701a;
        if (iConfusionMgr != null) {
            return iConfusionMgr.getSIV();
        }
        return null;
    }
}
