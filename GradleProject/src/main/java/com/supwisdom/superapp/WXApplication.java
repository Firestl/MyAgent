package com.supwisdom.superapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.alibaba.fastjson.JSON;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.google.gson.Gson;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.igexin.sdk.PushManager;
import com.lzy.okgo.model.Progress;
import com.supwisdom.superapp.extend.module.SuperAppModule;
import com.supwisdom.superapp.service.GetuiIntentService;
import com.supwisdom.superapp.service.LiveService;
import com.supwisdom.superapp.service.model.AppVersionInfo;
import com.supwisdom.superapp.ui.activity.AuthorDialogActivity;
import com.supwisdom.superapp.ui.activity.H5Activity;
import com.supwisdom.superapp.ui.activity.LoginActivity;
import com.supwisdom.superapp.ui.activity.MiniLoadingActivity;
import com.supwisdom.superapp.ui.activity.OfflineCodeActivity;
import com.supwisdom.superapp.ui.activity.PayCodeAty;
import com.supwisdom.superapp.ui.activity.UpdateActivity;
import com.supwisdom.superapp.wxapi.WXEntryActivity;
import com.ta.utdid2.device.UTDevice;
import com.taobao.weex.WXSDKEngine;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import com.tencent.wework.api.IWWAPI;
import com.tencent.wework.api.WWAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import io.dcloud.feature.sdk.DCSDKInitConfig;
import io.dcloud.feature.sdk.DCUniMPActivity;
import io.dcloud.feature.sdk.DCUniMPSDK;
import io.dcloud.feature.sdk.Interface.IDCUniMPPreInitCallback;
import io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack;
import io.dcloud.feature.unimp.DCUniMPJSCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.en1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.gn1;
import supwisdom.mj1;
import supwisdom.ne;
import supwisdom.pm1;
import supwisdom.qm1;
import supwisdom.sh1;
import supwisdom.sm1;
import supwisdom.th1;
import supwisdom.um1;
import supwisdom.wm1;
import supwisdom.xe;
import supwisdom.y7;
import supwisdom.ym1;

/* JADX INFO: loaded from: classes2.dex */
public class WXApplication extends Application implements Application.ActivityLifecycleCallbacks {
    public static final int ALIPAY_INFORMATION_COMPLETE = 11;
    public static IWXAPI api;
    public static k callback;
    public static Handler federatedCodeHandler;
    public static IUniMP homeUniMP;
    public static WXApplication instance;
    public static IWWAPI iwwapi;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3973a;
    public DCUniMPJSCallback authorizeStateCallback;
    public ServiceConnection b;
    public String c;
    public String d;
    public DCUniMPJSCallback dcUniMPJSCallback;
    public DCUniMPJSCallback federatedCodeCallback;
    public DCUniMPJSCallback logoutCallback;
    public Object logoutData;
    public static List<LivenessTypeEnum> livenessList = new ArrayList();
    public static boolean isLivenessRandom = false;
    public static Handler CIDHandler = new j();
    public String TAG = "WXApplication";
    public String EventGetAllMessage = "getAllMessage";
    public String EventRemove = AbsoluteConst.XML_REMOVE;
    public String EventSysNavigateToMiniProgram = "sysNavigateToMiniProgram";
    public String EventSysCleanCache = "sysCleanCache";
    public String EventSysGetCacheSize = "sysGetCacheSize";
    public String EventSysVersionUpdate = "sysVersionUpdate";
    public String EventSysLogout = "sysLogout";
    public String EventSysGetUserToken = "sysGetUserToken";
    public String EventSysGetChatToken = "sysGetChatToken";
    public String EventGetHosts = "getHosts";
    public String EventSetHost = "setHost";
    public String EventGetCurrentHost = "getCurrentHost";
    public String EventSysSetBadge = "sysSetBadge";
    public String EventSysGetAppVersion = "sysGetAppVersion";
    public String EventSysVpnState = "sysVpnState";
    public String EventSysVpnConnectOrDisconnect = "sysVpnConnectOrDisconnect";
    public String EventSysGetSchoolCode = "sysGetSchoolCode";
    public String EventSysOpenFile = "sysOpenFile";
    public String EventSysRevokeExitApp = "sysRevokeExitApp";
    public String EventAddCalendar = "addCalendar";
    public String EventGetFederatedCode = "getFederatedCode";
    public String EventSysInfoAuthorizeGetState = "sysInfoAuthorizeGetState";
    public String EventSysInfoAuthorize = "sysInfoAuthorize";
    public String EventSysMournMode = "sysMournMode";
    public String EventSysGetChartConfig = "sysGetChartConfig";

    public class a implements Callback<et1> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            Log.e(WXApplication.this.TAG, "onFailure: ======= ");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            et1 et1VarBody = response.body();
            if (et1VarBody != null) {
                try {
                    JSONObject jSONObject = new JSONObject(et1VarBody.string());
                    int iOptInt = jSONObject.optInt("code");
                    Log.e(WXApplication.this.TAG, "onResponse:  ==== code   ===== " + iOptInt);
                    Log.e(WXApplication.this.TAG, "onResponse:  ==== message   ===== " + jSONObject.optString("message"));
                    if (iOptInt == -1 || !"已登录".equals(jSONObject.optString("message"))) {
                        WXApplication.this.sysLogout(WXApplication.this.logoutData, WXApplication.this.logoutCallback);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    GetuiIntentService.b(WXApplication.this.c, sh1.c.c(fn1.q));
                }
            }
        }
    }

    public class b implements ServiceConnection {
        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(WXApplication.this.TAG, "服务连接成功");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(WXApplication.this.TAG, "服务连接失败");
        }
    }

    public class c implements IDCUniMPPreInitCallback {
        public c() {
        }

        @Override // io.dcloud.feature.sdk.Interface.IDCUniMPPreInitCallback
        public void onInitFinished(boolean z) {
            k kVar = WXApplication.callback;
            if (kVar != null) {
                kVar.a(z);
            }
            if (WXApplication.this.f3973a && z) {
                WXApplication.this.receiveMessageToUni();
            }
        }
    }

    public class d extends ConnectivityManager.NetworkCallback {
        public d() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            boolean unused = WXApplication.this.f3973a;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            boolean unused = WXApplication.this.f3973a;
        }
    }

    public class e implements Callback<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> call, Throwable th) {
            Toast.makeText(WXApplication.this.getApplicationContext(), "网络出错", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> call, Response<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> response) {
            com.supwisdom.superapp.service.model.Response<AppVersionInfo> responseBody = response.body();
            if (responseBody == null || responseBody.code != 0) {
                Toast.makeText(WXApplication.this.getApplicationContext(), "服务出错", 0).show();
                return;
            }
            int i = responseBody.data.updateStatus;
            if (i != AppVersionInfo.STATUS_TYPE_FORCE_UPDATE && i != AppVersionInfo.STATUS_TYPE_UPDATE) {
                Toast.makeText(WXApplication.this.getApplicationContext(), "已经是最新版本了", 0).show();
                return;
            }
            Intent intent = new Intent(WXApplication.this.getApplicationContext(), (Class<?>) UpdateActivity.class);
            intent.putExtra(UpdateActivity.t, responseBody.data.updateStatus);
            intent.putExtra(UpdateActivity.u, responseBody.data.appUpdateDescriptionDTO.downloadUrl);
            intent.putExtra(UpdateActivity.v, responseBody.data.appUpdateDescriptionDTO.description);
            intent.setFlags(268435456);
            WXApplication.this.startActivity(intent);
        }
    }

    public class f implements Application.ActivityLifecycleCallbacks {
        public f(WXApplication wXApplication) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            wm1.b().a((ViewGroup) activity.getWindow().getDecorView());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public class g extends Handler {
        public g() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                WXApplication.this.federatedCodeCallback.invoke(String.valueOf(message.obj));
                return;
            }
            if (i == 11) {
                pm1 pm1Var = new pm1((Map) message.obj, true);
                String strC = pm1Var.c();
                if (TextUtils.equals(strC, "9000") && TextUtils.equals(pm1Var.b(), "200")) {
                    WXApplication.this.federatedCodeCallback.invoke(pm1Var.a());
                } else if (TextUtils.equals(strC, "6001")) {
                    WXApplication wXApplication = WXApplication.this;
                    Toast.makeText(wXApplication, wXApplication.getResources().getString(com.supwisdom.zueb.R.string.cancel_authorize), 0).show();
                } else {
                    WXApplication wXApplication2 = WXApplication.this;
                    Toast.makeText(wXApplication2, wXApplication2.getResources().getString(com.supwisdom.zueb.R.string.authorize_error), 0).show();
                }
            }
        }
    }

    public class h implements IUniMPOnCloseCallBack {
        public h(WXApplication wXApplication) {
        }

        @Override // io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack
        public void onClose(String str) {
            Log.d("close_wgt_appid:", str);
        }
    }

    public class i implements IOnUniMPEventCallBack {
        public i() {
        }

        @Override // io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack
        public void onUniMPEventReceive(String str, String str2, Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
            Log.d(WXApplication.this.TAG, str2);
            WXApplication wXApplication = WXApplication.this;
            wXApplication.logoutData = obj;
            wXApplication.logoutCallback = dCUniMPJSCallback;
            if (str2.equals(wXApplication.EventGetAllMessage)) {
                WXApplication.this.getAllMessage(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventRemove)) {
                WXApplication.this.remove(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysNavigateToMiniProgram)) {
                WXApplication.this.sysNavigateToMiniProgram(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysCleanCache)) {
                WXApplication.this.sysCleanCache(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysGetCacheSize)) {
                WXApplication.this.sysGetCacheSize(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysVersionUpdate)) {
                WXApplication.this.sysVersionUpdate(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysLogout)) {
                WXApplication.this.sysLogout(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysGetUserToken)) {
                WXApplication.this.sysGetUserToken(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSetHost)) {
                WXApplication.this.setHost(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventGetHosts)) {
                WXApplication.this.getHosts(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventGetCurrentHost)) {
                WXApplication.this.getCurrentHost(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysGetChatToken)) {
                WXApplication.this.sysGetChatToken(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysSetBadge)) {
                WXApplication.this.sysSetBadge(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.equals(WXApplication.this.EventSysGetAppVersion)) {
                WXApplication.this.sysGetAppVersion(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysVpnState)) {
                WXApplication.this.sysVpnState(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysVpnConnectOrDisconnect)) {
                WXApplication.this.sysVpnConnectOrDisconnect(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysGetSchoolCode)) {
                WXApplication.this.sysGetSchoolCode(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysOpenFile)) {
                WXApplication.this.sysOpenFile(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysRevokeExitApp)) {
                WXApplication.this.sysRevokeExitApp(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventAddCalendar)) {
                WXApplication.this.addCalendar(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventGetFederatedCode)) {
                WXApplication.this.getFederatedCode(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysInfoAuthorizeGetState)) {
                WXApplication.this.sysInfoAuthorizeGetState(obj, dCUniMPJSCallback);
                return;
            }
            if (str2.endsWith(WXApplication.this.EventSysInfoAuthorize)) {
                WXApplication.this.sysInfoAuthorize(obj, dCUniMPJSCallback);
            } else if (str2.equals(WXApplication.this.EventSysMournMode)) {
                WXApplication.this.sysMournMode(obj, dCUniMPJSCallback);
            } else if (str2.equals(WXApplication.this.EventSysGetChartConfig)) {
                WXApplication.this.sysGetChartConfig(obj, dCUniMPJSCallback);
            }
        }
    }

    public static class j extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                GetuiIntentService.a((String) message.obj, sh1.c.c(fn1.q));
            } else {
                if (i != 2) {
                    return;
                }
                GetuiIntentService.a((String) message.obj, sh1.c.c(fn1.q));
            }
        }
    }

    public interface k {
        void a(int i);

        void a(boolean z);

        void j();
    }

    public WXApplication() {
        new Handler(Looper.getMainLooper());
        this.b = new b();
    }

    public static void setInitDCUniMPSDKCallback(k kVar) {
        callback = kVar;
    }

    public void addCalendar(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        JSONArray jSONArray;
        String string = ((com.alibaba.fastjson.JSONObject) obj).getString("calendarMsg");
        List listAsList = Arrays.asList("MO", "TU", "WE", "TH", "FR", "SA", "SU");
        try {
            JSONArray jSONArray2 = new JSONArray(string);
            StringBuilder sb = null;
            long j2 = 0;
            long j3 = 0;
            String string2 = "";
            String string3 = string2;
            String string4 = string3;
            int i2 = 0;
            while (i2 < jSONArray2.length()) {
                JSONObject jSONObject = (JSONObject) jSONArray2.get(i2);
                string2 = jSONObject.getString("calendarId");
                string3 = jSONObject.getString("title");
                string4 = jSONObject.getString("notes");
                j2 = jSONObject.getLong("startDate");
                j3 = jSONObject.getLong("endDate");
                int i3 = jSONObject.getInt("repeatType");
                sb = new StringBuilder();
                if (i3 == 2) {
                    jSONArray = jSONArray2;
                    sb.append("FREQ=DAILY;INTERVAL=1");
                } else if (i3 != 3) {
                    jSONArray = jSONArray2;
                    if (i3 == 4) {
                        sb.append("FREQ=WEEKLY;INTERVAL=1");
                        i2 = 0;
                        while (i2 <= 5) {
                            if (i2 <= listAsList.size()) {
                                if (i2 == 0) {
                                    sb.append(";BYDAY=${byDayArray[i]}");
                                } else {
                                    sb.append(",${byDayArray[i]}");
                                }
                            }
                            i2++;
                        }
                    } else if (i3 == 5) {
                        sb.append("FREQ=WEEKLY;INTERVAL=1;BYDAY=${byDayArray[5]},${byDayArray[6]}");
                    } else if (i3 == 6) {
                        int i4 = jSONObject.getInt("repeatCycleType");
                        if (i4 == 1) {
                            sb.append("FREQ=DAILY;INTERVAL=$customrepeatInterval");
                        } else if (i4 == 2) {
                            sb.append("FREQ=WEEKLY;INTERVAL=$customrepeatInterval");
                        }
                    }
                } else {
                    jSONArray = jSONArray2;
                    sb.append("FREQ=WEEKLY;INTERVAL=1");
                }
                i2++;
                jSONArray2 = jSONArray;
            }
            if (Build.VERSION.SDK_INT < 29 || y7.a(this, "android.permission.READ_CALENDAR") != 0 || y7.a(this, "android.permission.WRITE_CALENDAR") != 0 || Build.VERSION.SDK_INT < 24) {
                return;
            }
            sm1.a(getApplicationContext(), string2, string3, string4, j2, j3, 1655778609, String.valueOf(sb));
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        xe.c(context);
    }

    public void getAllMessage(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        try {
            if (!TextUtils.isEmpty(fn1.B)) {
                Log.d("addDeepLinkListener", "applicaion---isSend" + homeUniMP.sendUniMPEvent("addDeepLinkListener", JSON.parseObject(fn1.B)) + fn1.B);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        th1.c.a();
        List list = (List) th1.c.a("", th1.b);
        com.alibaba.fastjson.JSONArray jSONArray = new com.alibaba.fastjson.JSONArray();
        if (list == null || list.isEmpty()) {
            dCUniMPJSCallback.invoke("");
            return;
        }
        for (int i2 = 0; list != null && i2 < list.size(); i2++) {
            com.alibaba.fastjson.JSONObject object = JSON.parseObject((String) th1.c.a("", (String) list.get(i2)));
            object.put(RemoteMessageConst.MSGID, list.get(i2));
            jSONArray.add(object);
        }
        String json = jSONArray.size() != 0 ? new Gson().toJson(jSONArray) : "";
        Log.e(this.TAG, json + "aaa");
        dCUniMPJSCallback.invoke(json);
    }

    public void getCurrentHost(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        String strA = gn1.a();
        if (!TextUtils.isEmpty(strA)) {
            dCUniMPJSCallback.invoke(strA);
        }
        Log.e(this.TAG, "获取当前域名" + strA);
    }

    public void getFederatedCode(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        this.federatedCodeCallback = dCUniMPJSCallback;
        String string = ((com.alibaba.fastjson.JSONObject) obj).getString("type");
        if (!ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE.equals(string)) {
            if ("alipay".equals(string)) {
                callback.j();
            }
        } else {
            if (!api.isWXAppInstalled()) {
                Toast.makeText(this, "您还未安装微信客户端", 0).show();
                return;
            }
            SendAuth.Req req = new SendAuth.Req();
            req.scope = WeiXinOAuthService.DEFAULT_SCOPE;
            req.state = "superapp_wx_login";
            WXEntryActivity.b = "getFederatedCode";
            WXEntryActivity.c = federatedCodeHandler;
            api.sendReq(req);
        }
    }

    public void getHosts(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        String strB = gn1.b();
        if (!TextUtils.isEmpty(strB)) {
            dCUniMPJSCallback.invoke(strB);
        }
        Log.e(this.TAG, "获取全部域名" + strB);
    }

    public void initDCUniMPSDK() {
        DCUniMPSDK.getInstance().initialize(this, new DCSDKInitConfig.Builder().setCapsule(false).setMenuDefFontSize("16px").setMenuDefFontColor("#ff00ff").setMenuDefFontWeight("normal").setEnableBackground(false).setUniMPFromRecents(false).build(), new c());
        Log.d(this.TAG, this.f3973a + "");
    }

    public void initFaceSDK() {
        FaceSDKManager.getInstance().initialize(this, fn1.b, fn1.c);
        FaceConfig faceConfig = FaceSDKManager.getInstance().getFaceConfig();
        livenessList.add(LivenessTypeEnum.Eye);
        faceConfig.setLivenessTypeList(livenessList);
        faceConfig.setLivenessRandom(isLivenessRandom);
        faceConfig.setBlurnessValue(0.5f);
        faceConfig.setBrightnessValue(40.0f);
        faceConfig.setCropFaceValue(400);
        faceConfig.setHeadPitchValue(10);
        faceConfig.setHeadRollValue(10);
        faceConfig.setHeadYawValue(10);
        faceConfig.setMinFaceSize(200);
        faceConfig.setNotFaceValue(0.6f);
        faceConfig.setOcclusionValue(0.5f);
        faceConfig.setCheckFaceQuality(true);
        faceConfig.setFaceDecodeNumberOfThreads(2);
        FaceSDKManager.getInstance().setFaceConfig(faceConfig);
    }

    public void initGetuiSDK() {
        Log.d(this.TAG, "initializing sdk...");
        if (sh1.c.a("isAgreePrivacy").booleanValue()) {
            PushManager.getInstance().initialize(this);
        }
    }

    public void initMainProcess() {
        this.f3973a = getApplicationContext().getPackageName().equals(a());
    }

    public void initU_App() {
        UMConfigure.setLogEnabled(false);
        UMConfigure.preInit(this, fn1.K, "umeng");
        if (sh1.c.a("isAgreePrivacy").booleanValue()) {
            UMConfigure.init(this, fn1.K, "umeng", 1, "");
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        }
    }

    public void initVirtualCard() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Log.d("WXApplication", "后台回到前台");
        if (activity instanceof DCUniMPActivity) {
            Log.d("WXApplication", "ProcessMediator");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("style", "BaseResp");
            intent.putExtra("result", bundle);
            ne.a(this).a(intent);
            activity.finishActivity(1000);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application
    @SuppressLint({"HandlerLeak"})
    public void onCreate() {
        super.onCreate();
        instance = this;
        th1.c.a(this);
        sh1.c.a(this);
        if (RuningAcitvityUtil.getAppName(getBaseContext()).contains("unimp")) {
            Log.d(this.TAG, " uni进程 ");
            bindService(new Intent(this, (Class<?>) LiveService.class), this.b, 1);
            try {
                WXSDKEngine.registerModule("superAppModule", SuperAppModule.class);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            registerActivityLifecycleCallbacks(this);
        } else {
            Log.d(this.TAG, " 其他进程 ");
            if (Build.VERSION.SDK_INT >= 28) {
                WebView.setDataDirectorySuffix(Application.getProcessName());
            }
            WebView.setWebContentsDebuggingEnabled(true);
            if (sh1.c.a("isAgreePrivacy").booleanValue()) {
                initMainProcess();
                initDCUniMPSDK();
                regToWx();
                regToWWx();
                initU_App();
                initGetuiSDK();
                registerNetwork();
                initVirtualCard();
            }
            gn1.a(sh1.c.c(fn1.s));
        }
        federatedCodeHandler = new g();
    }

    public void receiveMessageToUni() {
        DCUniMPSDK.getInstance().setUniMPOnCloseCallBack(new h(this));
        DCUniMPSDK.getInstance().setOnUniMPEventCallBack(new i());
    }

    public void regToWWx() {
        IWWAPI iwwapiCreateWWAPI = WWAPIFactory.createWWAPI(this);
        iwwapi = iwwapiCreateWWAPI;
        iwwapiCreateWWAPI.registerApp(fn1.E);
    }

    public void regToWx() {
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this, fn1.D, true);
        api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(fn1.D);
    }

    public void registerNetwork() {
        if (Build.VERSION.SDK_INT >= 24) {
            ((ConnectivityManager) getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest.Builder().build(), new d());
        }
    }

    public void remove(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        String string = ((com.alibaba.fastjson.JSONObject) obj).getString(RemoteMessageConst.MSGID);
        List list = (List) th1.c.a("", th1.b);
        if (list != null && list.contains(string)) {
            list.remove(string);
            th1.c.a("", th1.b, list);
        }
        File file = new File(th1.c.a() + File.separator + string);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        Log.e(this.TAG, "删除");
    }

    public void setHost(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        com.alibaba.fastjson.JSONObject jSONObject = (com.alibaba.fastjson.JSONObject) obj;
        gn1.a(jSONObject.getString("groupId"));
        sysLogout(jSONObject, dCUniMPJSCallback);
    }

    public void sysCleanCache(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        um1.a(this);
        um1.a();
        dCUniMPJSCallback.invoke(um1.b(this));
    }

    public void sysGetAppVersion(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        dCUniMPJSCallback.invoke("1.1.5(5841)");
    }

    public void sysGetCacheSize(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        dCUniMPJSCallback.invoke(um1.b(this));
    }

    public void sysGetChartConfig(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        this.d = ((com.alibaba.fastjson.JSONObject) obj).getString("shareConfig");
    }

    public void sysGetChatToken(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        String strA = sh1.c.a(fn1.r, "");
        Log.d(this.TAG, "huanxin-----" + strA);
        dCUniMPJSCallback.invoke(strA);
    }

    public void sysGetSchoolCode(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        dCUniMPJSCallback.invoke("13508");
    }

    public void sysGetUserToken(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        String strA = sh1.c.a(fn1.o, fn1.w);
        Log.d(this.TAG, "aa-----" + strA);
        dCUniMPJSCallback.invoke(strA);
    }

    public void sysInfoAuthorize(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        com.alibaba.fastjson.JSONObject jSONObject = (com.alibaba.fastjson.JSONObject) obj;
        String string = jSONObject.getString("title");
        if (!sh1.c.a("isAgreeInfo").booleanValue()) {
            Intent intent = new Intent(this, (Class<?>) AuthorDialogActivity.class);
            intent.putExtra("shareTitle", string);
            startActivity(intent);
        } else {
            sh1.c.a("isAgreeInfo", (Boolean) false);
            jSONObject.put("sysInfoAuthorizeState", (Object) "0");
            this.authorizeStateCallback.invoke(jSONObject.toJSONString());
            Toast.makeText(this, "您已取消授权", 0).show();
        }
    }

    public void sysInfoAuthorizeGetState(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        this.authorizeStateCallback = dCUniMPJSCallback;
        com.alibaba.fastjson.JSONObject jSONObject = (com.alibaba.fastjson.JSONObject) obj;
        jSONObject.getString("serviceId");
        jSONObject.put("sysInfoAuthorizeState", (Object) (sh1.c.a("isAgreeInfo").booleanValue() ? "1" : "0"));
        this.authorizeStateCallback.invoke(jSONObject.toJSONString());
    }

    public void sysLogout(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        um1.a(this);
        um1.a();
        this.c = sh1.c.c(fn1.o);
        fn1.w = "";
        sh1.c.b(fn1.o, "");
        sh1.c.b(fn1.n, "");
        sh1.c.b(fn1.m, "");
        sh1.c.b(fn1.l, "");
        en1.a(this, "PAY_TYPE");
        en1.a(this, "PAY_ID");
        en1.a(this, "USER_TOKEN");
        en1.a(this, "V8_TOKEN");
        en1.a(this, "CURRENT_TIME");
        PushManager.getInstance().unBindAlias(this, sh1.c.c(fn1.t), false);
        MobclickAgent.onProfileSignOff();
        qm1.a(this, 0);
        dCUniMPJSCallback.invoke("");
        sh1.c.b(fn1.t, "");
        Intent intent = new Intent();
        intent.addFlags(268468224);
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        GetuiIntentService.b(this.c, sh1.c.c(fn1.q));
    }

    public void sysMournMode(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        if (((com.alibaba.fastjson.JSONObject) obj).getString("type").equals("add")) {
            callback.a(0);
        }
        registerActivityLifecycleCallbacks(new f(this));
    }

    public void sysNavigateToMiniProgram(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        com.alibaba.fastjson.JSONObject jSONObject = (com.alibaba.fastjson.JSONObject) obj;
        jSONObject.getString("engineType");
        String string = jSONObject.getString("loadPath");
        String string2 = jSONObject.getString("tokenTransType");
        String string3 = jSONObject.getString("collect");
        String string4 = jSONObject.getString("iconUrl");
        String string5 = jSONObject.getString("appId");
        String string6 = jSONObject.getString("title");
        String string7 = jSONObject.getString("attachmentUrl");
        String string8 = jSONObject.getString("appType");
        String string9 = jSONObject.getString("columnId");
        String string10 = jSONObject.getString(SocialConstants.PARAM_APP_DESC);
        String string11 = jSONObject.getString("serviceInfo");
        String string12 = jSONObject.getString("shareUrl");
        Uri uri = Uri.parse(string);
        if (TextUtils.isEmpty(string) || string.equals("#")) {
            return;
        }
        if (string.contains(".wgt")) {
            Log.d("小程序", "当前进程名称:" + a());
            Intent intent = new Intent(getApplicationContext(), (Class<?>) MiniLoadingActivity.class);
            intent.putExtra("loadPath", string);
            DCUniMPSDK.getInstance().startActivityForUniMPTask(fn1.i, intent);
            return;
        }
        if (TextUtils.isEmpty(uri.getScheme()) || ((uri.getScheme().equals("nwpu") && TextUtils.isEmpty(uri.getHost())) || uri.getHost().equals("pay"))) {
            try {
                Intent intent2 = new Intent();
                intent2.setData(uri);
                DCUniMPSDK.getInstance().startActivityForUniMPTask(fn1.i, intent2);
                return;
            } catch (Exception e2) {
                Log.d("", e2.getMessage());
                return;
            }
        }
        if (uri.getScheme().equals("nwpuPayment")) {
            Intent intent3 = new Intent();
            intent3.setClass(this, PayCodeAty.class);
            intent3.setFlags(268435456);
            startActivity(intent3);
            return;
        }
        if (uri.getScheme().equals("zzuOffline") || uri.getScheme().equals("zzuitOffline") || uri.getScheme().equals("hutOffline") || uri.getScheme().contains("Offline")) {
            Intent intent4 = new Intent();
            intent4.setClass(this, OfflineCodeActivity.class);
            intent4.setFlags(268435456);
            startActivity(intent4);
            return;
        }
        Intent intent5 = new Intent();
        intent5.setClass(this, H5Activity.class);
        intent5.setFlags(268435456);
        intent5.setData(Uri.parse(string));
        intent5.putExtra(fn1.u, string2);
        intent5.putExtra("collect", string3);
        intent5.putExtra("iconUrl", string4);
        intent5.putExtra("appId", string5);
        intent5.putExtra("attachmentUrl", string7);
        intent5.putExtra("title", string6);
        intent5.putExtra("appType", string8);
        intent5.putExtra(SocialConstants.PARAM_APP_DESC, string10);
        intent5.putExtra("columnId", string9);
        intent5.putExtra("serviceInfo", string11);
        intent5.putExtra("shareConfig", this.d);
        intent5.putExtra("shareUrl", string12);
        startActivity(intent5);
    }

    public void sysOpenFile(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        com.alibaba.fastjson.JSONObject jSONObject = (com.alibaba.fastjson.JSONObject) obj;
        File file = Environment.getExternalStorageState().equals("mounted") ? Build.VERSION.SDK_INT >= 29 ? new File(jSONObject.getString(Progress.FILE_PATH), jSONObject.getString(Progress.FILE_NAME)) : Environment.getExternalStorageDirectory() : Environment.getRootDirectory();
        Uri uriForFile = Build.VERSION.SDK_INT >= 24 ? FileProvider.getUriForFile(this, "com.supwisdom.zueb.UploadFileProvider", file) : Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setFlags(1);
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(uriForFile, ym1.a(file));
        startActivity(intent);
    }

    public void sysRevokeExitApp(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        um1.a(this);
        um1.a();
        String str = fn1.w;
        fn1.w = "";
        sh1.c.b(fn1.o, "");
        sh1.c.b(fn1.n, "");
        sh1.c.b(fn1.m, "");
        sh1.c.b(fn1.l, "");
        sh1.c.b(fn1.p, "");
        PushManager.getInstance().unBindAlias(this, sh1.c.c(fn1.t), false);
        MobclickAgent.onProfileSignOff();
        qm1.a(this, 0);
        dCUniMPJSCallback.invoke("");
        sh1.c.b(fn1.t, "");
        sh1.c.a("isAgreePrivacy", (Boolean) false);
        sh1.c.a("isAgreeFacePrivacy", (Boolean) false);
        GetuiIntentService.b(str, sh1.c.c(fn1.q));
        homeUniMP.closeUniMP();
        homeUniMP = null;
    }

    public void sysSetBadge(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        String string = ((com.alibaba.fastjson.JSONObject) obj).getString("badgeCount");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        qm1.a(this, Integer.parseInt(string));
    }

    public void sysVersionUpdate(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        mj1.b().c("mobile", "mobile", fn1.f7620a, packageInfo.versionName).enqueue(new e());
    }

    public void sysVpnConnectOrDisconnect(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
        ((com.alibaba.fastjson.JSONObject) obj).getInteger("vpnOperateType").intValue();
        this.dcUniMPJSCallback = dCUniMPJSCallback;
    }

    public void sysVpnState(Object obj, DCUniMPJSCallback dCUniMPJSCallback) {
    }

    public void userOnlineDetect() {
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
        }
        String strC2 = sh1.c.c(fn1.t);
        mj1.b().f(sh1.c.c(fn1.o), getPackageName(), strC, strC2).enqueue(new a());
    }

    public final String a() {
        int iMyPid = Process.myPid();
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == iMyPid) {
                str = runningAppProcessInfo.processName;
            }
        }
        return str;
    }
}
