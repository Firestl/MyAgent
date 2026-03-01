package io.dcloud.common.core.a;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import com.google.zxing.client.android.ScanFromWebPageManager;
import com.nostra13.dcloudimageloader.core.assist.FailReason;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.tencent.open.SocialConstants;
import io.dcloud.EntryProxy;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.NotificationUtil;
import io.dcloud.common.util.TestUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.hx0;
import supwisdom.zw0;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: io.dcloud.common.core.a.a$a, reason: collision with other inner class name */
    public static class RunnableC0148a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f6356a;

        public RunnableC0148a(JSONObject jSONObject) {
            this.f6356a = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.d(this.f6356a);
        }
    }

    public static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f6357a;

        public b(JSONObject jSONObject) {
            this.f6357a = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.e(this.f6357a);
        }
    }

    public static class c implements hx0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONObject f6358a;
        public final /* synthetic */ IApp b;

        public c(JSONObject jSONObject, IApp iApp) {
            this.f6358a = jSONObject;
            this.b = iApp;
        }

        @Override // supwisdom.hx0
        public void onLoadingCancelled(String str, View view) {
            System.out.print(str);
        }

        @Override // supwisdom.hx0
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            if (bitmap != null) {
                String strOptString = this.f6358a.optString("title");
                String strOptString2 = this.f6358a.optString("content");
                Intent intent = new Intent();
                intent.putExtra("dcloud.push.broswer", "true");
                intent.setClassName(this.b.getActivity(), "io.dcloud.PandoraEntry");
                intent.putExtra("__json__", this.f6358a.toString());
                intent.putExtra("appid", this.b.obtainAppId());
                intent.putExtra("adid", this.b.obtainConfigProperty("adid"));
                NotificationUtil.createCustomNotification(this.b.getActivity(), strOptString, bitmap, strOptString, strOptString2, this.f6358a.hashCode(), PendingIntent.getActivity(this.b.getActivity(), intent.hashCode(), intent, 1073741824));
            }
        }

        @Override // supwisdom.hx0
        public void onLoadingFailed(String str, View view, FailReason failReason) {
            System.out.print(str);
        }

        @Override // supwisdom.hx0
        public void onLoadingStarted(String str, View view) {
            System.out.print(str);
        }
    }

    public static void bc(String str) {
        SP.setBundleData("pdr", SP.REPORT_UNI_VERIFY_GYUID, "");
        SharedPreferences orCreateBundle = SP.getOrCreateBundle(AbsoluteConst.START_STATISTICS_DATA);
        if ((System.currentTimeMillis() - orCreateBundle.getLong(AbsoluteConst.COMMIT_APP_LIST_TIME, 0L)) / 100000 >= 26000 && !BaseInfo.isChannelGooglePlay() && ((!Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI) || Build.VERSION.SDK_INT < 23 || PlatformUtil.checkGTAndYoumeng()) && (TextUtils.isEmpty(BaseInfo.sChannel) || !BaseInfo.sChannel.endsWith("|xiaomi")))) {
            orCreateBundle.edit().putLong(AbsoluteConst.COMMIT_APP_LIST_TIME, System.currentTimeMillis()).commit();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(ScanFromWebPageManager.RETURN_URL_PARAM) && jSONObject.optInt(ScanFromWebPageManager.RETURN_URL_PARAM) == 0 && WXModalUIModule.OK.equals(jSONObject.opt(SocialConstants.PARAM_APP_DESC)) && !jSONObject.isNull("did")) {
                SP.setBundleData(SP.getOrCreateBundle("pdr"), SP.STARTUP_DEVICE_ID, jSONObject.optString("did"));
            }
            if (BaseInfo.ISDEBUG) {
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                String strOptString = jSONObjectOptJSONObject.optString("action");
                if ("webview".equals(strOptString)) {
                    MessageHandler.postDelayed(new RunnableC0148a(jSONObjectOptJSONObject), jSONObjectOptJSONObject.optInt(IApp.ConfigProperty.CONFIG_DELAY, 0));
                } else if ("push".equals(strOptString)) {
                    MessageHandler.postDelayed(new b(jSONObjectOptJSONObject), jSONObjectOptJSONObject.optInt(IApp.ConfigProperty.CONFIG_DELAY, 0));
                } else if ("apk".equals(strOptString)) {
                    c(jSONObjectOptJSONObject);
                }
            }
            if (jSONObject.has("urd")) {
                String strOptString2 = jSONObject.optString("urd");
                if (URLUtil.isNetworkUrl(strOptString2)) {
                    DHFile.writeFile(NetTool.httpGet(strOptString2, false), 0, BaseInfo.sURDFilePath);
                }
            }
        } catch (JSONException e2) {
            Logger.p("IDBridge", e2.getMessage());
        }
    }

    public static void c(JSONObject jSONObject) {
        IApp iAppA = a();
        if (iAppA == null) {
            return;
        }
        try {
            String strOptString = jSONObject.optString("url");
            String strOptString2 = jSONObject.optString("tid");
            String strOptString3 = jSONObject.optString("name");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strOptString).openConnection();
            long contentLength = httpURLConnection.getContentLength();
            if (contentLength < 1) {
                return;
            }
            File file = new File(DeviceInfo.sDeviceRootDir + "/.system/apks/" + strOptString3);
            InputStream inputStream = httpURLConnection.getInputStream();
            if (file.exists() && file.length() < contentLength) {
                file.delete();
            } else if (file.exists() && file.length() == contentLength) {
                return;
            }
            TestUtil.PointTime.commitTid(iAppA.getActivity(), iAppA.obtainAppId(), strOptString2, iAppA.obtainConfigProperty("adid"), 29);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[(int) Math.min(contentLength, 4096L)];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    fileOutputStream.flush();
                    TestUtil.PointTime.commitTid(iAppA.getActivity(), iAppA.obtainAppId(), strOptString2, iAppA.obtainConfigProperty("adid"), 30);
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void d(JSONObject jSONObject) {
        IApp iAppA = a();
        if (iAppA == null) {
            return;
        }
        iAppA.obtainMgrData(IMgr.MgrType.WindowMgr, 52, new Object[]{iAppA, jSONObject});
    }

    public static void e(JSONObject jSONObject) {
        IApp iAppA = a();
        if (iAppA == null) {
            return;
        }
        String strOptString = jSONObject.optString("icon");
        if (TextUtils.isEmpty(strOptString) || !URLUtil.isNetworkUrl(strOptString)) {
            return;
        }
        zw0.f().a(strOptString, new c(jSONObject, iAppA));
    }

    public static String gd() {
        IApp iAppA = a();
        if (iAppA == null) {
            return "";
        }
        HashMap map = new HashMap(io.dcloud.f.b.h.a.a(iAppA, SP.getOrCreateBundle(iAppA.getActivity(), AbsoluteConst.START_STATISTICS_DATA)));
        try {
            map.put("ps", Integer.valueOf(BaseInfo.existsStreamEnv() ? 1 : 0));
            map.put("psd", Integer.valueOf(BaseInfo.ISDEBUG ? 1 : 0));
            map.put("paid", iAppA.obtainConfigProperty("adid"));
            JSONObject jSONObjectObtainThridInfo = iAppA.obtainThridInfo(IApp.ConfigProperty.ThridInfo.URDJsonData);
            map.put("urv", jSONObjectObtainThridInfo != null ? jSONObjectObtainThridInfo.optString("version") : "0.1");
        } catch (Exception unused) {
        }
        while (map.values().remove(null)) {
        }
        while (map.values().remove(com.igexin.push.core.b.m)) {
        }
        return new JSONObject(map).toString();
    }

    public static IApp a() {
        return (IApp) EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 28, BaseInfo.sDefaultBootApp);
    }
}
