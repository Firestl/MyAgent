package com.efs.sdk.base.integrationtesting;

/* JADX INFO: loaded from: classes.dex */
public class IntegrationTestingUtil {
    public static boolean sIsInPeriod = false;

    public static boolean isIntegrationTestingInPeriod() {
        return sIsInPeriod;
    }

    public static void setIntegrationTestingInPeriod(boolean z) {
        sIsInPeriod = z;
    }
}
