package io.dcloud.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.dcloud.android.downloader.DownloadService;
import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.exception.DownloadException;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.WebviewActivity;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ILoadCallBack;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.DownloadUtil;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.f.b.h.a;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.qv;
import supwisdom.st;
import supwisdom.tt;

/* JADX INFO: loaded from: classes2.dex */
public class ADUtils {
    public static final tt DOWNLOAD_DC_LISTENER = new tt() { // from class: io.dcloud.common.util.ADUtils.1
        @Override // supwisdom.tt
        public void onDownloadFailed(DownloadInfo downloadInfo, DownloadException downloadException) {
            downloadException.printStackTrace();
            JSONObject loadData = ADUtils.getLoadData(downloadInfo.getId());
            if (loadData != null) {
                ADUtils.downloadCommit(downloadInfo.getContext(), loadData.optString("appid"), loadData.optString("tid"), loadData.optString("adid"), 32, String.valueOf(downloadException.getCode()), downloadException.getMessage(), loadData.optString("ua"));
            }
        }

        @Override // supwisdom.tt
        public void onDownloadSuccess(DownloadInfo downloadInfo) {
            JSONObject jSONObjectRemoveDownlaodData = ADUtils.removeDownlaodData(downloadInfo.getContext(), downloadInfo.getId());
            if (jSONObjectRemoveDownlaodData != null) {
                ADUtils.downloadCommit(downloadInfo.getContext(), jSONObjectRemoveDownlaodData.optString("appid"), jSONObjectRemoveDownlaodData.optString("tid"), jSONObjectRemoveDownlaodData.optString("adid"), 30, null, null, jSONObjectRemoveDownlaodData.optString("ua"));
                ADUtils.saveLoadAppData(downloadInfo.getContext(), jSONObjectRemoveDownlaodData.optString("pname"), jSONObjectRemoveDownlaodData.optString("appid"), jSONObjectRemoveDownlaodData.optString("tid"), jSONObjectRemoveDownlaodData.optString("adid"), downloadInfo.getPath(), jSONObjectRemoveDownlaodData.optString("ua"));
            }
            Intent aPKInstallIntent = DownloadUtil.getAPKInstallIntent(downloadInfo.getContext(), downloadInfo.getPath());
            if (downloadInfo.getTag() == null || !(downloadInfo.getTag() instanceof ILoadCallBack)) {
                downloadInfo.getContext().startActivity(aPKInstallIntent);
            } else {
                ((ILoadCallBack) downloadInfo.getTag()).onCallBack(0, downloadInfo.getContext(), aPKInstallIntent);
            }
            DownloadService.a(downloadInfo.getContext().getApplicationContext()).c(downloadInfo);
        }

        @Override // supwisdom.tt
        public void onDownloading(long j, long j2) {
        }

        @Override // supwisdom.tt
        public void onPaused() {
        }

        @Override // supwisdom.tt
        public void onRemoved() {
        }

        @Override // supwisdom.tt
        public void onStart() {
        }

        @Override // supwisdom.tt
        public void onWaited() {
        }
    };
    public static final String TAG = "ADUtils";

    public static class ADLoadData {
        public String adid;
        public String appid;
        public long expiresTime;
        public long id;
        public String name;
        public String pname;
        public String tid;
        public String type = "default";
        public String ua;
        public String url;
    }

    public static Object ADHandlerMethod(String str, Object... objArr) {
        try {
            Class[] clsArr = new Class[0];
            if (objArr != null && objArr.length > 0) {
                clsArr = new Class[objArr.length];
                for (int i = 0; i < objArr.length; i++) {
                    clsArr[i] = objArr[i].getClass();
                }
            }
            return PlatformUtil.invokeMethod("io.dcloud.feature.gg.dcloud.ADHandler", str, null, clsArr, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void checkADDownload(final Activity activity) throws Exception {
        Map<String, ?> all = SP.getOrCreateBundle(activity, AbsoluteConst.AD_DOWNLOAD_DATA).getAll();
        if (all.size() > 0) {
            Iterator<?> it = all.values().iterator();
            while (it.hasNext()) {
                JSONObject jSONObject = new JSONObject((String) it.next());
                final long jOptLong = jSONObject.optLong("id");
                final long jOptLong2 = jSONObject.optLong("expiresTime");
                final String strOptString = jSONObject.optString("name");
                final String strOptString2 = jSONObject.optString("pname");
                final String strOptString3 = jSONObject.optString("url");
                final String strOptString4 = jSONObject.optString("appid");
                final String strOptString5 = jSONObject.optString("tid");
                final String strOptString6 = jSONObject.optString("adid");
                final String strOptString7 = jSONObject.optString("type");
                final String strOptString8 = jSONObject.optString("ua");
                final st stVarA = DownloadService.a(activity.getApplicationContext());
                final DownloadInfo downloadInfoA = stVarA.a((int) jOptLong);
                activity.runOnUiThread(new Runnable() { // from class: io.dcloud.common.util.ADUtils.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DownloadInfo downloadInfo = downloadInfoA;
                        int status = downloadInfo != null ? downloadInfo.getStatus() : 0;
                        switch (status) {
                            case 0:
                            case 6:
                                ADUtils.removeDownlaodData(activity, jOptLong);
                                if (!TextUtils.isEmpty(strOptString7) && strOptString7.equalsIgnoreCase("wanka")) {
                                    DownloadInfo downloadInfo2 = downloadInfoA;
                                    if (downloadInfo2 != null) {
                                        stVarA.c(downloadInfo2);
                                    }
                                } else {
                                    long j = jOptLong2;
                                    if (j > 0 && j < System.currentTimeMillis()) {
                                        DownloadInfo downloadInfo3 = downloadInfoA;
                                        if (downloadInfo3 != null) {
                                            stVarA.c(downloadInfo3);
                                        }
                                    } else {
                                        DownloadInfo downloadInfo4 = downloadInfoA;
                                        if (downloadInfo4 != null) {
                                            stVarA.c(downloadInfo4);
                                        }
                                        ADUtils.dwApp(activity, strOptString4, strOptString5, strOptString6, strOptString3, strOptString, strOptString2, jOptLong2, false, status != 6, strOptString8);
                                        qv.makeText((Context) activity, R.string.dcloud_common_download_tips2, 0).show();
                                    }
                                }
                                break;
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                if (downloadInfoA != null) {
                                    long j2 = jOptLong2;
                                    if (j2 > 0 && j2 < System.currentTimeMillis()) {
                                        ADUtils.removeDownlaodData(activity, jOptLong);
                                        stVarA.c(downloadInfoA);
                                    } else {
                                        stVarA.a(downloadInfoA);
                                        downloadInfoA.setDownloadListener(ADUtils.DOWNLOAD_DC_LISTENER);
                                        qv.makeText((Context) activity, R.string.dcloud_common_download_tips2, 0).show();
                                    }
                                }
                                break;
                            case 5:
                                if (downloadInfoA != null) {
                                    ADUtils.removeDownlaodData(activity, jOptLong);
                                    String path = downloadInfoA.getPath();
                                    activity.startActivity(DownloadUtil.getAPKInstallIntent(activity, path));
                                    ADUtils.saveLoadAppData(activity, strOptString2, strOptString4, strOptString5, strOptString6, path, strOptString8);
                                    stVarA.c(downloadInfoA);
                                    qv.makeText((Context) activity, R.string.dcloud_common_download_tips3, 0).show();
                                }
                                break;
                        }
                    }
                });
            }
        }
    }

    public static void downloadCommit(final Context context, final String str, final String str2, final String str3, final int i, final String str4, final String str5, final String str6) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.ADUtils.7
            @Override // java.lang.Runnable
            public void run() {
                a.a(context, str, str2, str3, i, str4, str5, null, null, null, ADUtils.getSplashAdpId(), str6, null);
            }
        });
    }

    public static void dwApp(Context context, String str, String str2, String str3, String str4, String str5, String str6, long j, boolean z, boolean z2, String str7) {
        String str8;
        if (z2) {
            try {
                loadAppTip(context);
            } catch (Exception e2) {
                Log.e(TAG, "downloadApk exception: " + e2.getMessage());
                e2.printStackTrace();
                return;
            }
        }
        if (getDdDataForUrl(str4) != null) {
            return;
        }
        String str9 = Operators.DOT_STR + context.getString(R.string.in_package);
        if (TextUtils.isEmpty(str6)) {
            str8 = null;
        } else {
            str8 = str6 + Operators.DOT_STR + context.getString(R.string.in_package);
        }
        String fileNameByUrl = PdrUtil.getFileNameByUrl(str4, str9, str8);
        ADLoadData aDLoadData = new ADLoadData();
        aDLoadData.name = str5;
        aDLoadData.pname = str6;
        aDLoadData.url = str4;
        aDLoadData.expiresTime = j;
        aDLoadData.adid = str3;
        aDLoadData.appid = str;
        aDLoadData.tid = str2;
        aDLoadData.ua = str7;
        aDLoadData.id = loadADFile(context, str, str2, str3, fileNameByUrl, str5, str4, "application/vnd.android.package-archive", new ILoadCallBack() { // from class: io.dcloud.common.util.ADUtils.2
            @Override // io.dcloud.common.DHInterface.ILoadCallBack
            public Object onCallBack(int i, Context context2, Object obj) {
                if (i != 0) {
                    return null;
                }
                context2.startActivity(new Intent((Intent) obj));
                return Boolean.TRUE;
            }
        }, z, str7);
        saveLoadData(aDLoadData);
    }

    public static JSONObject getDdDataForUrl(String str) {
        JSONObject jSONObject;
        Map<String, ?> all = SP.getOrCreateBundle(AbsoluteConst.AD_DOWNLOAD_DATA).getAll();
        if (all == null || all.size() <= 0) {
            return null;
        }
        Iterator<?> it = all.values().iterator();
        while (it.hasNext()) {
            try {
                jSONObject = new JSONObject((String) it.next());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (str.equals(jSONObject.optString("url"))) {
                return jSONObject;
            }
        }
        return null;
    }

    public static String getDownloadDataKey(long j) {
        return AbsoluteConst.AD_DL_DATA_KEY + String.valueOf(j);
    }

    public static String getLoadAppDataKey(String str) {
        return AbsoluteConst.AD_IA_DATA_KEY + str;
    }

    public static JSONObject getLoadData(long j) {
        try {
            return new JSONObject(SP.getOrCreateBundle(AbsoluteConst.AD_DOWNLOAD_DATA).getString(getDownloadDataKey(j), ""));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getSplashAdpId() {
        return String.valueOf(PlatformUtil.invokeMethod("io.dcloud.feature.gg.AdSplashUtil", "getSplashAdpId", null, new Class[]{IApp.class, String.class, String.class}, new Object[]{null, "_adpid_", "UNIAD_SPLASH_ADPID"}));
    }

    public static long loadADFile(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, ILoadCallBack iLoadCallBack, boolean z, String str8) {
        if (z) {
            downloadCommit(context, str, str2, str3, 29, null, null, str8);
        }
        st stVarA = DownloadService.a(context.getApplicationContext());
        String str9 = DeviceInfo.sDeviceRootDir + "/Download/" + str4;
        DownloadInfo.a aVar = new DownloadInfo.a();
        aVar.b(str6);
        aVar.a(str9);
        DownloadInfo downloadInfoA = aVar.a(context);
        downloadInfoA.setTag(iLoadCallBack);
        downloadInfoA.setDownloadListener(DOWNLOAD_DC_LISTENER);
        stVarA.b(downloadInfoA);
        return downloadInfoA.getId();
    }

    public static void loadAppTip(Context context) {
        MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.common.util.ADUtils.6
            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                qv.makeText((Context) obj, R.string.dcloud_common_download_tips1, 0).show();
            }
        }, context);
    }

    public static void openBrowser(Context context, String str) {
        try {
            Intent intent = new Intent();
            if (!Build.BRAND.equalsIgnoreCase(MobilePhoneModel.VIVO) && LoadAppUtils.isAppLoad(context, "com.android.browser")) {
                intent.setPackage("com.android.browser");
            }
            intent.setData(Uri.parse(str));
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e2) {
            Log.e(TAG, "openBrowser exception: " + e2.getMessage());
            e2.printStackTrace();
        }
    }

    public static boolean openDeepLink(Context context, String str) {
        try {
            Intent uri = Intent.parseUri(str, 1);
            if (BaseInfo.isDefense) {
                uri.setSelector(null);
                uri.setComponent(null);
                uri.addCategory("android.intent.category.BROWSABLE");
            }
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(uri, 65536);
            if (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) {
                return false;
            }
            uri.setFlags(268435456);
            context.startActivity(uri);
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "openDeepLink exception: " + e2.getMessage());
            e2.printStackTrace();
            return false;
        }
    }

    public static void openStreamApp(Context context, String str, JSONObject jSONObject, int i, String str2) {
        try {
            Intent intent = new Intent();
            intent.putExtra(IntentConst.START_FROM, i);
            intent.setAction("android.intent.action.MAIN");
            if (TextUtils.isEmpty(str2)) {
                if (BaseInfo.existsStreamEnv()) {
                    intent.setClassName(context.getPackageName(), "io.dcloud.appstream.StreamAppMainActivity");
                }
            } else if ("com.qihoo.appstore".equals(str2)) {
                intent.setClassName(str2, "io.dcloud.appstream.StreamAppListFakeActivity");
            } else if ("com.aspire.mm".equals(str2)) {
                intent.setClassName(str2, "io.dcloud.StreamAppLauncherActivity");
            } else {
                intent.setClassName(str2, "io.dcloud.appstream.StreamAppMainActivity");
            }
            intent.putExtra("appid", str);
            boolean z = true;
            intent.putExtra(IntentConst.IS_STREAM_APP, true);
            if (jSONObject != null) {
                boolean z2 = false;
                if (jSONObject.has("arguments")) {
                    intent.putExtra(IntentConst.EXTRAS, jSONObject.opt("arguments").toString());
                    z2 = true;
                }
                if (jSONObject.has("richurl")) {
                    intent.putExtra(IntentConst.DIRECT_PAGE, jSONObject.optString("richurl"));
                    intent.putExtra(IntentConst.IS_START_FIRST_WEB, true);
                } else {
                    z = z2;
                }
                if (z) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("action", AbsoluteConst.XML_APP);
                    jSONObject2.put("parameters", jSONObject);
                    intent.putExtra("rules_msg", jSONObject2.toString());
                }
            }
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e2) {
            Log.e(TAG, "openStreamApp exception: " + e2.getMessage());
            e2.printStackTrace();
        }
    }

    public static void openUrl(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setClass(context, WebviewActivity.class);
            intent.putExtra("url", str);
            intent.setData(Uri.parse(str));
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e2) {
            Log.e(TAG, "openUrl exception: " + e2.getMessage());
            e2.printStackTrace();
        }
    }

    public static JSONObject removeDownlaodData(Context context, long j) {
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, AbsoluteConst.AD_DOWNLOAD_DATA);
        String downloadDataKey = getDownloadDataKey(j);
        String string = orCreateBundle.getString(downloadDataKey, "");
        orCreateBundle.edit().remove(downloadDataKey).commit();
        try {
            return new JSONObject(string);
        } catch (Exception unused) {
            return null;
        }
    }

    public static JSONObject removeLoadAppData(Context context, String str) {
        JSONObject jSONObject;
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, AbsoluteConst.AD_INSTALL_DATA);
        String loadAppDataKey = getLoadAppDataKey(str);
        String string = orCreateBundle.getString(loadAppDataKey, "");
        if (TextUtils.isEmpty(string)) {
            jSONObject = null;
        } else {
            try {
                jSONObject = new JSONObject(string);
            } catch (JSONException e2) {
                e2.printStackTrace();
                jSONObject = null;
            }
        }
        orCreateBundle.edit().remove(loadAppDataKey).commit();
        return jSONObject;
    }

    public static void runThreadCheckADDownload(final Activity activity) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.ADUtils.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ADUtils.checkADDownload(activity);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static void saveLoadAppData(final Context context, final String str, final String str2, final String str3, final String str4, final String str5, final String str6) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.ADUtils.5
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences orCreateBundle = SP.getOrCreateBundle(context, AbsoluteConst.AD_INSTALL_DATA);
                String loadAppDataKey = ADUtils.getLoadAppDataKey(str);
                JSONObject jSONObject = new JSONObject();
                try {
                    if (PdrUtil.isEmpty(str)) {
                        PackageInfo apkInfo = PlatformUtil.parseApkInfo(context, str5);
                        if (apkInfo == null) {
                            return;
                        }
                        jSONObject.put("packName", apkInfo.packageName);
                        loadAppDataKey = ADUtils.getLoadAppDataKey(apkInfo.packageName);
                    } else {
                        jSONObject.put("packName", str);
                    }
                    jSONObject.put("appid", str2);
                    jSONObject.put("tid", str3);
                    jSONObject.put("adid", str4);
                    jSONObject.put("ua", str6);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                orCreateBundle.edit().putString(loadAppDataKey, jSONObject.toString()).commit();
            }
        });
    }

    public static void saveLoadData(ADLoadData aDLoadData) throws Exception {
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(AbsoluteConst.AD_DOWNLOAD_DATA);
        String downloadDataKey = getDownloadDataKey(aDLoadData.id);
        if (TextUtils.isEmpty(orCreateBundle.getString(downloadDataKey, ""))) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", aDLoadData.name);
            jSONObject.put("url", aDLoadData.url);
            jSONObject.put("pname", aDLoadData.pname);
            jSONObject.put("id", aDLoadData.id);
            jSONObject.put("expiresTime", aDLoadData.expiresTime);
            jSONObject.put("tid", aDLoadData.tid);
            jSONObject.put("appid", aDLoadData.appid);
            jSONObject.put("adid", aDLoadData.adid);
            jSONObject.put("type", aDLoadData.type);
            jSONObject.put("ua", aDLoadData.ua);
            orCreateBundle.edit().putString(downloadDataKey, jSONObject.toString()).commit();
        }
    }
}
