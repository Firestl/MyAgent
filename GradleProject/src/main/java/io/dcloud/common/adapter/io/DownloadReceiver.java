package io.dcloud.common.adapter.io;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.dcloud.common.util.ADUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        JSONObject jSONObjectRemoveLoadAppData;
        if (!"android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) || (jSONObjectRemoveLoadAppData = ADUtils.removeLoadAppData(context, intent.getData().getSchemeSpecificPart())) == null) {
            return;
        }
        ADUtils.downloadCommit(context, jSONObjectRemoveLoadAppData.optString("appid"), jSONObjectRemoveLoadAppData.optString("tid"), jSONObjectRemoveLoadAppData.optString("adid"), 31, null, null, jSONObjectRemoveLoadAppData.optString("ua"));
    }
}
