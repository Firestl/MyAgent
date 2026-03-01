package io.dcloud.common.adapter.io;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.ADUtils;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.uniapp.adapter.AbsURIAdapter;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PushReceiver {
    public static void handle(final Context context, Intent intent) throws Exception {
        JSONObject jSONObject = new JSONObject(intent.getStringExtra("__json__"));
        final String stringExtra = intent.getStringExtra("appid");
        final String stringExtra2 = intent.getStringExtra("adid");
        final String strOptString = jSONObject.optString("tid");
        if (!TextUtils.isEmpty(strOptString)) {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.adapter.io.PushReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    TestUtil.PointTime.commitTid(context, stringExtra, strOptString, stringExtra2, 20);
                }
            });
        }
        if (jSONObject.has("dplk") && ADUtils.openDeepLink(context, jSONObject.optString("dplk"))) {
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.adapter.io.PushReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    TestUtil.PointTime.commitTid(context, stringExtra, strOptString, stringExtra2, 50);
                }
            });
            return;
        }
        String strOptString2 = jSONObject.optString("click_action");
        if (TextUtils.isEmpty(strOptString2)) {
            strOptString2 = (!jSONObject.has("appid") || TextUtils.isEmpty(jSONObject.optString("appid"))) ? "browser" : AbsoluteConst.XML_STREAMAPP;
        }
        if (TextUtils.equals("url", strOptString2)) {
            ADUtils.openUrl(context, jSONObject.optString("url"));
            return;
        }
        if (TextUtils.equals(AbsoluteConst.SPNAME_DOWNLOAD, strOptString2)) {
            ADUtils.dwApp(context.getApplicationContext(), stringExtra, strOptString, stringExtra2, jSONObject.optString("url"), jSONObject.optString("downloadAppName"), jSONObject.optString(AbsURIAdapter.BUNDLE), 0L, true, true, "");
        } else if (!TextUtils.equals(AbsoluteConst.XML_STREAMAPP, strOptString2) && TextUtils.equals("browser", strOptString2)) {
            ADUtils.openBrowser(context, jSONObject.optString("url"));
        }
    }

    public static void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("dcloud.push.broswer")) {
            try {
                if (Boolean.valueOf(intent.getStringExtra("dcloud.push.broswer")).booleanValue()) {
                    handle(context, intent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
