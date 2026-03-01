package supwisdom;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: compiled from: DDMessageAct.java */
/* JADX INFO: loaded from: classes.dex */
public class ur {
    public static boolean a(Context context, String str, Bundle bundle, Intent intent) {
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        String packageName = context.getPackageName();
        String strA = as.a(context, packageName);
        intent.putExtra("android.intent.ding.EXTRA_MESSAGE_SDK_VERSION", 20160101);
        intent.putExtra("android.intent.ding.EXTRA_MESSAGE_APP_PACKAGE_NAME", packageName);
        intent.putExtra("android.intent.ding.EXTRA_ACTION_TYPE", "outShare");
        intent.putExtra("android.intent.ding.EXTRA_MESSAGE_APP_SIGNATURE", strA);
        intent.putExtra("android.intent.ding.EXTRA_MESSAGE_APP_ID", str);
        try {
            context.startActivity(intent);
            Log.d("DDMessageAct", "send dd message, intent=" + intent);
            return true;
        } catch (ActivityNotFoundException unused) {
            Log.e("DDMessageAct", "send v2 fail, target ActivityNotFound");
            return false;
        } catch (Exception e2) {
            Log.e("DDMessageAct", "send v2 fail " + e2.getMessage());
            return false;
        }
    }

    public static boolean b(Context context, String str, Bundle bundle) {
        if (context != null) {
            return a(context, str, bundle, b());
        }
        Log.e("DDMessageAct", "send fail, invalid arguments");
        return false;
    }

    public static Intent b() {
        return a("dingtalk://qr.dingtalk.com/im/forward.html");
    }

    public static Intent a() {
        return a("dingtalk://qr.dingtalk.com/im/send_auth.html");
    }

    public static Intent a(String str) {
        return new Intent("android.intent.action.VIEW", Uri.parse(str));
    }

    public static boolean a(Context context, String str, Bundle bundle) {
        if (context != null) {
            return a(context, str, bundle, a());
        }
        Log.e("DDMessageAct", "send fail, invalid arguments");
        return false;
    }
}
