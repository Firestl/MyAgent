package com.g.gysdk.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.xiaomi.mipush.sdk.Constants;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class af {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1955a;
    public String b;
    public String c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1956e;

    public af(Context context) {
        this.f1955a = "";
        this.b = "";
        this.c = "";
        this.d = 0L;
        this.f1956e = "";
        try {
            this.f1955a = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.f1955a, 64);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.f1955a, 0);
            this.c = packageInfo.versionName;
            if (Build.VERSION.SDK_INT < 28) {
                this.d = packageInfo.versionCode;
            } else {
                this.d = packageInfo.getLongVersionCode();
            }
            this.b = (String) packageManager.getApplicationLabel(applicationInfo);
            this.f1956e = a(packageInfo);
        } catch (Throwable th) {
            ak.e(th);
        }
    }

    private String a(PackageInfo packageInfo) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(packageInfo.signatures[0].toByteArray());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bArrDigest.length; i++) {
                String string = Integer.toString(bArrDigest[i] & 255, 16);
                if (string.length() == 1) {
                    string = "0" + string;
                }
                if (i != bArrDigest.length - 1) {
                    string = string + Constants.COLON_SEPARATOR;
                }
                sb.append(string);
            }
            return sb.toString().toUpperCase();
        } catch (Throwable th) {
            ak.c(th);
            return "";
        }
    }
}
