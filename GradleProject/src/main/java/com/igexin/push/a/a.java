package com.igexin.push.a;

import android.app.Activity;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f3214a;

    public static a a() {
        if (f3214a == null) {
            synchronized (a.class) {
                if (f3214a == null) {
                    f3214a = new a();
                }
            }
        }
        return f3214a;
    }

    public static b a(Activity activity) {
        String stringExtra = activity.getIntent().getStringExtra("action");
        if (TextUtils.isEmpty(stringExtra)) {
            activity.finish();
            return null;
        }
        byte b = -1;
        int iHashCode = stringExtra.hashCode();
        if (iHashCode != 106852524) {
            if (iHashCode == 1845633938 && stringExtra.equals("com.igexin.action.notification.click")) {
                b = 0;
            }
        } else if (stringExtra.equals(com.igexin.push.core.b.r)) {
            b = 1;
        }
        if (b == 0) {
            return new f();
        }
        if (b == 1) {
            return new g();
        }
        activity.finish();
        return null;
    }
}
