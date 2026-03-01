package com.sangfor.atrust.appStore.installer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sangfor.sdk.utils.SFLogN;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.b71;
import supwisdom.ly0;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class InstallReceiver extends BroadcastReceiver {
    public final void a(String str, String str2) {
        try {
            b71.b().a().b().a(str, str2);
        } catch (Exception e2) {
            SFLogN.b("MyInstallReceiver", "dispatch failed", "appstore is disable", e2);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String dataString = intent.getDataString();
        if (TextUtils.isEmpty(dataString)) {
            SFLogN.b("MyInstallReceiver", "onReceive shutdown", "packageName is empty");
            return;
        }
        if (dataString.startsWith("package:")) {
            dataString = dataString.substring(8);
        }
        SFLogN.a("MyInstallReceiver", "onReceive called, [%s] action:[%s] ", dataString, action);
        action.hashCode();
        byte b = -1;
        int iHashCode = action.hashCode();
        if (iHashCode != -810471698) {
            if (iHashCode != 1544582882) {
                if (iHashCode == 1580442797 && action.equals("android.intent.action.PACKAGE_FULLY_REMOVED")) {
                    b = 2;
                }
            } else if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                b = 1;
            }
        } else if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
            b = 0;
        }
        String strA = "";
        if (b == 0 || b == 1) {
            String str = ly0.b().a().get(dataString);
            if (TextUtils.isEmpty(str)) {
                SFLogN.a("MyInstallReceiver", "onReceive install notify skip: " + intent.getAction(), "install id is empty, maybe already handle");
            } else {
                strA = a(str, dataString, a(context, dataString));
                a("appStoreEvent|installResult", strA);
                ly0.b().a().remove(dataString);
            }
        } else if (b == 2) {
            strA = a("", dataString, 0L);
            a("appStoreEvent|uninstall", strA);
        }
        SFLogN.a("MyInstallReceiver", "onReceive over, [%s] content: %s", action, strA);
    }

    public final String a(String str, String str2, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", str);
            jSONObject2.put("packageID", str2);
            jSONObject2.put("installedTime", j);
            jSONObject.put("code", 0);
            jSONObject.put("data", jSONObject2);
            jSONObject.put("message", "success");
        } catch (JSONException e2) {
            SFLogN.a("MyInstallReceiver", "generate InstallReceiver result failed!", "", e2);
        }
        return jSONObject.toString();
    }

    public final long a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 8320).firstInstallTime;
        } catch (Exception e2) {
            SFLogN.b("MyInstallReceiver", "There is no sdk that packageName is " + str, e2);
            return System.currentTimeMillis();
        }
    }
}
