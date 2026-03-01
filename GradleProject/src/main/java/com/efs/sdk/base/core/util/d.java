package com.efs.sdk.base.core.util;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile String f1866a = "";

    public static String a(Context context) {
        if (TextUtils.isEmpty(f1866a)) {
            synchronized (d.class) {
                if (TextUtils.isEmpty(f1866a)) {
                    String strB = b(context);
                    f1866a = strB;
                    if (TextUtils.isEmpty(strB)) {
                        f1866a = c(context);
                    }
                }
            }
        }
        return f1866a;
    }

    public static String b(Context context) {
        try {
            File file = new File(a.a(context), "efsid");
            if (file.exists()) {
                return b.a(file);
            }
            return null;
        } catch (Exception e2) {
            Log.e("efs.base", "get uuid error", e2);
            return null;
        }
    }

    public static String c(Context context) {
        String string = "";
        for (int i = 0; i < 3; i++) {
            try {
                string = UUID.randomUUID().toString();
            } catch (Throwable unused) {
            }
            if (TextUtils.isEmpty(string)) {
            }
        }
        try {
            File fileA = a.a(context);
            File file = new File(fileA, "efsid" + Process.myPid());
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            b.a(file, string);
            if (file.renameTo(new File(fileA, "efsid"))) {
                file.delete();
            }
        } catch (Exception e2) {
            Log.e("efs.base", "save uuid '" + string + "' error", e2);
        }
        return string;
    }
}
