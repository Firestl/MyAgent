package com.sangfor.sdk.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.w61;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class StoreInfoManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3921a;

    /* JADX INFO: compiled from: Proguard */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final StoreInfoManager f3922a = new StoreInfoManager();
    }

    public static final StoreInfoManager b() {
        return a.f3922a;
    }

    public String a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            SFLogN.d("StoreInfoManager", "getMetaData arg invalid");
            return null;
        }
        String strA = a(context, context.getPackageName(), str);
        if (TextUtils.isEmpty(strA)) {
            SFLogN.d("StoreInfoManager", "getMetaData ret null, If it is a sub-application, the shared path should be set in <meta-data key = \"host_data\" value=\"packageName(main application packageName)\">");
        }
        return strA;
    }

    public native String[] getSangforConfigPaths();

    public native String getSharedMobileId();

    public String a() {
        String str = this.f3921a;
        if (str != null) {
            return str;
        }
        Context contextB = w61.b();
        if (contextB != null) {
            String strA = a(contextB, "host_data");
            this.f3921a = strA;
            return strA;
        }
        SFLogN.b("StoreInfoManager", "you have to call SangforCore init func");
        throw new RuntimeException("you have to call SangforCore init func");
    }

    public String a(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str2)) {
            try {
                Bundle bundle = context.getPackageManager().getApplicationInfo(str, 128).metaData;
                if (bundle == null) {
                    SFLogN.b("StoreInfoManager", "getMetaData failed", "getApplicationInfo ret null, pkgname:" + str);
                    return null;
                }
                String string = bundle.getString(str2, "");
                this.f3921a = string;
                return string;
            } catch (Exception e2) {
                SFLogN.a("StoreInfoManager", "getMetaData failed", "exception occured,msg:" + e2.getMessage());
                return null;
            }
        }
        SFLogN.d("StoreInfoManager", "getMetaData arg invalid");
        return null;
    }
}
