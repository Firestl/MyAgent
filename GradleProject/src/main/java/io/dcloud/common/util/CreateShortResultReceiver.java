package io.dcloud.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.constant.StringConst;

/* JADX INFO: loaded from: classes2.dex */
public class CreateShortResultReceiver extends BroadcastReceiver {
    public static final String KEY_APPID = "appid";
    public static final String KEY_NAME = "name";
    public static final String KEY_SF = "sf";
    public static final String KEY_SFD = "sfd";
    public static final String KEY_VERSIONNAME = "v";
    public static final String TAG = CreateShortResultReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        Log.e(TAG, "onReceive: intent==" + intent);
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.CreateShortResultReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, "pdr");
                String stringExtra = intent.getStringExtra("name");
                String stringExtra2 = intent.getStringExtra("appid");
                int iCheckPermission = AppPermissionUtil.checkPermission(context, stringExtra);
                String stringExtra3 = intent.getStringExtra("v");
                String stringExtra4 = intent.getStringExtra(CreateShortResultReceiver.KEY_SFD);
                try {
                    NetTool.httpGet((StringConst.STREAMAPP_KEY_BASESERVICEURL() + "collect/startup?s=11&" + DataInterface.getUrlBaseData(context, stringExtra2, intent.getStringExtra(CreateShortResultReceiver.KEY_SF), stringExtra4) + "&romv=" + DataInterface.getRomVersion() + "&scf=0&scp=" + iCheckPermission + "&v=" + PdrUtil.encodeURL(stringExtra3)) + "&sc=s");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                orCreateBundle.edit().putString(stringExtra2 + SP.K_CREATE_SHORTCUT_NAME, stringExtra).commit();
                orCreateBundle.edit().putBoolean(stringExtra2 + SP.K_CREATED_SHORTCUT, true).commit();
                Log.e(CreateShortResultReceiver.TAG, "run: appid==" + stringExtra2);
            }
        });
    }
}
