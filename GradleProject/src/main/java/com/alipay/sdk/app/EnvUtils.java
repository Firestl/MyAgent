package com.alipay.sdk.app;

/* JADX INFO: loaded from: classes.dex */
public class EnvUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static EnvEnum f1602a = EnvEnum.ONLINE;

    public enum EnvEnum {
        ONLINE,
        SANDBOX
    }

    public static boolean a() {
        return f1602a == EnvEnum.SANDBOX;
    }
}
