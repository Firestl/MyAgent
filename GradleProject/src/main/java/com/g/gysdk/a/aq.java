package com.g.gysdk.a;

import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class aq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Bundle f1978a;

    public aq(Context context) {
        this.f1978a = null;
        try {
            this.f1978a = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable th) {
            ak.c("get meta data error", th);
        }
    }

    public String a(String str, String str2) {
        try {
            return this.f1978a.getString(str, str2);
        } catch (Throwable th) {
            ak.c("get meta data value error", th);
            return str2;
        }
    }
}
