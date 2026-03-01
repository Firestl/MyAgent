package io.dcloud.feature.oauth.weixin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mm.opensdk.channel.MMessageActV2;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.pro.bm;
import io.dcloud.ProcessMediator;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.oauth.BaseOAuthService;
import io.dcloud.share.mm.WeiXinApiManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class WeiXinOAuthService extends BaseOAuthService {
    public static final String DEFAULT_SCOPE = "snsapi_userinfo";
    public static final String DEFAULT_STATE = "wechat_sdk_dcloud_weixin_oauth";
    public static final String ERR_MSG_AUTH_DENIED = "Authentication failed";
    public static final String ERR_MSG_COMM = "General errors";
    public static final String ERR_MSG_SENT_FAILED = "Unable to send";
    public static final String ERR_MSG_UNSUPPORT = "Unsupport error";
    public static final String ERR_MSG_USER_CANCEL = "User canceled";
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_ERRCODE = "errcode";
    public static final String KEY_ERRMSG = "errmsg";
    public static final String KEY_OPENID = "openid";
    public static final String KEY_REFRESH_TOKEN = "refresh_token";
    public static final String TAG = "WeiXinOAuthService";
    public static final String URL_CHECK_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";
    public static final String URL_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    public static final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    public static final String URL_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
    public static String appId;
    public static String appKEY;
    public static String appSecret;
    public static String redirectUri;
    public IWXAPI api;
    public boolean isLoginReceiver = false;
    public FeatureMessageDispatcher.MessageListener sLoginMessageListener = new FeatureMessageDispatcher.MessageListener() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.1
        @Override // io.dcloud.common.DHInterface.FeatureMessageDispatcher.MessageListener
        public void onReceiver(final Object obj) {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.1.1
                @Override // java.lang.Runnable
                public void run() {
                    String token;
                    Object obj2 = obj;
                    if (obj2 instanceof SendMessageToWX.Resp) {
                        int i = ((SendMessageToWX.Resp) obj2).errCode;
                        WeiXinOAuthService weiXinOAuthService = WeiXinOAuthService.this;
                        weiXinOAuthService.onLoginCallBack(weiXinOAuthService.mLoginWebViewImpl, WeiXinOAuthService.this.mLoginCallbackId, i);
                    }
                    if (obj instanceof SendAuth.Resp) {
                        Logger.d(WeiXinOAuthService.TAG, "isLoginReceiver = true");
                        WeiXinOAuthService.this.isLoginReceiver = true;
                        SendAuth.Resp resp = (SendAuth.Resp) obj;
                        String str = resp.code;
                        String str2 = resp.state;
                        if (WeiXinOAuthService.this.isAuth) {
                            WeiXinOAuthService.this.isAuth = false;
                            if (resp.errCode == 0) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    JSONObject jSONObject2 = WeiXinOAuthService.this.mAuthOptions;
                                    String strOptString = WeiXinOAuthService.DEFAULT_SCOPE;
                                    if (jSONObject2 != null) {
                                        strOptString = WeiXinOAuthService.this.mAuthOptions.optString("scope", WeiXinOAuthService.DEFAULT_SCOPE);
                                    }
                                    jSONObject.put("scope", strOptString);
                                    jSONObject.put("state", str2);
                                    jSONObject.put("code", str);
                                    jSONObject.put(AbsoluteConst.JSON_KEY_LANG, resp.lang);
                                    jSONObject.put(bm.O, resp.country);
                                    Deprecated_JSUtil.execCallback(WeiXinOAuthService.this.mAuthWebview, WeiXinOAuthService.this.mAuthCallbackId, jSONObject.toString(), JSUtil.OK, true, false);
                                } catch (JSONException unused) {
                                }
                            } else {
                                WeiXinOAuthService weiXinOAuthService2 = WeiXinOAuthService.this;
                                weiXinOAuthService2.onLoginCallBack(weiXinOAuthService2.mAuthWebview, WeiXinOAuthService.this.mAuthCallbackId, resp.errCode);
                            }
                            FeatureMessageDispatcher.unregisterListener(WeiXinOAuthService.this.sLoginMessageListener);
                            return;
                        }
                        JSONObject jSONObjectCreateJSONObject = null;
                        if (str != null) {
                            token = WeiXinOAuthService.this.getToken(StringUtil.format(WeiXinOAuthService.URL_GET_ACCESS_TOKEN, WeiXinOAuthService.appId, WeiXinOAuthService.appSecret, str));
                            if (token != null) {
                                jSONObjectCreateJSONObject = JSONUtil.createJSONObject(token);
                            }
                        } else {
                            token = null;
                        }
                        if (jSONObjectCreateJSONObject == null) {
                            WeiXinOAuthService weiXinOAuthService3 = WeiXinOAuthService.this;
                            weiXinOAuthService3.onLoginCallBack(weiXinOAuthService3.mLoginWebViewImpl, WeiXinOAuthService.this.mLoginCallbackId, resp.errCode);
                        } else if (jSONObjectCreateJSONObject.has(WeiXinOAuthService.KEY_ERRCODE)) {
                            Deprecated_JSUtil.execCallback(WeiXinOAuthService.this.mLoginWebViewImpl, WeiXinOAuthService.this.mLoginCallbackId, DOMException.toJSON(-100, JSONUtil.getString(jSONObjectCreateJSONObject, WeiXinOAuthService.KEY_ERRMSG), JSONUtil.getInt(jSONObjectCreateJSONObject, WeiXinOAuthService.KEY_ERRCODE)), JSUtil.ERROR, true, false);
                        } else {
                            WeiXinOAuthService.this.saveValue(BaseOAuthService.KEY_AUTHRESULT, token);
                            WeiXinOAuthService.this.saveValue("state", str2);
                            WeiXinOAuthService.this.initUserInfo();
                            WeiXinOAuthService weiXinOAuthService4 = WeiXinOAuthService.this;
                            weiXinOAuthService4.onLoginCallBack(weiXinOAuthService4.mLoginWebViewImpl, WeiXinOAuthService.this.mLoginCallbackId, 0);
                        }
                        FeatureMessageDispatcher.unregisterListener(WeiXinOAuthService.this.sLoginMessageListener);
                    }
                }
            }, true);
        }
    };
    public boolean isAuth = false;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasWXEntryActivity(Context context) {
        try {
            Class.forName(context.getPackageName() + MMessageActV2.DEFAULT_ENTRY_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginInThread(IWebview iWebview, String str, JSONObject jSONObject) {
        Logger.d(TAG, "isLoginReceiver = false");
        this.isLoginReceiver = false;
        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(getValue(BaseOAuthService.KEY_AUTHRESULT));
        if (!this.isAuth && jSONObjectCreateJSONObject != null && jSONObjectCreateJSONObject.has("access_token")) {
            byte[] bArrHttpGet = NetTool.httpGet(StringUtil.format(URL_CHECK_TOKEN, jSONObjectCreateJSONObject.optString("access_token"), jSONObjectCreateJSONObject.optString("openid")), 15000);
            if (bArrHttpGet != null) {
                JSONObject jSONObjectCreateJSONObject2 = JSONUtil.createJSONObject(new String(bArrHttpGet));
                if (jSONObjectCreateJSONObject2 != null) {
                    if (jSONObjectCreateJSONObject2.optInt(KEY_ERRCODE) == 0) {
                        initUserInfo();
                        onLoginCallBack(iWebview, str, 0);
                        return;
                    }
                    removeToken();
                    String strRefreshToken = refreshToken(StringUtil.format(URL_REFRESH_TOKEN, appId, jSONObjectCreateJSONObject.optString("refresh_token")));
                    JSONObject jSONObjectCreateJSONObject3 = null;
                    if (PdrUtil.isEmpty(strRefreshToken)) {
                        onLoginCallBack(iWebview, str, -1);
                    } else {
                        jSONObjectCreateJSONObject3 = JSONUtil.createJSONObject(strRefreshToken);
                    }
                    if (PdrUtil.isEmpty(jSONObjectCreateJSONObject3)) {
                        onLoginCallBack(iWebview, str, -1);
                    } else if (!jSONObjectCreateJSONObject3.has(KEY_ERRCODE)) {
                        saveValue(BaseOAuthService.KEY_AUTHRESULT, strRefreshToken);
                        initUserInfo();
                        onLoginCallBack(iWebview, str, 0);
                        return;
                    }
                }
            } else {
                onLoginCallBack(iWebview, str, -1);
            }
        }
        loginInUIThread(iWebview, str, jSONObject);
    }

    private void loginInUIThread(final IWebview iWebview, final String str, final JSONObject jSONObject) {
        MessageHandler.post(new Runnable() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.4
            @Override // java.lang.Runnable
            public void run() {
                IWebview iWebview2 = iWebview;
                if (iWebview2 == null || iWebview2.obtainFrameView() == null || iWebview.obtainApp() == null) {
                    return;
                }
                SendAuth.Req req = new SendAuth.Req();
                req.scope = WeiXinOAuthService.DEFAULT_SCOPE;
                req.state = WeiXinOAuthService.DEFAULT_STATE;
                req.transaction = String.valueOf(System.currentTimeMillis());
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 != null) {
                    req.scope = jSONObject2.optString("scope", req.scope);
                    req.state = jSONObject.optString("state", req.state);
                }
                if ((iWebview.getActivity() instanceof IActivityHandler) && ((IActivityHandler) iWebview.getActivity()).isMultiProcessMode()) {
                    WeiXinOAuthService.this.startWeiXinMediator(req, iWebview, str);
                    return;
                }
                if (WeiXinOAuthService.this.api == null) {
                    WeiXinOAuthService.this.api = WXAPIFactory.createWXAPI(iWebview.getActivity(), WeiXinOAuthService.appId, true);
                    WeiXinOAuthService.this.api.registerApp(WeiXinOAuthService.appId);
                }
                boolean zSendReq = WeiXinOAuthService.this.api.sendReq(req);
                final IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
                iAppObtainApp.registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.4.1
                    @Override // io.dcloud.common.DHInterface.ISysEventListener
                    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                        Logger.d(WeiXinOAuthService.TAG, "isLoginReceiver1 " + WeiXinOAuthService.this.isLoginReceiver);
                        if (!WeiXinOAuthService.this.isLoginReceiver) {
                            Logger.d(WeiXinOAuthService.TAG, "isLoginReceiver2 " + WeiXinOAuthService.this.isLoginReceiver);
                            WeiXinOAuthService weiXinOAuthService = WeiXinOAuthService.this;
                            JSONObject errorJsonbject = weiXinOAuthService.getErrorJsonbject(-2, DOMException.MSG_USER_CANCEL);
                            AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                            weiXinOAuthService.onLoginFinished(errorJsonbject, false, iWebview, str);
                        }
                        IApp iApp = iAppObtainApp;
                        if (iApp != null) {
                            iApp.unregisterSysEventListener(this, ISysEventListener.SysEventType.onResume);
                        }
                        return false;
                    }
                }, ISysEventListener.SysEventType.onResume);
                if (!zSendReq || !WeiXinOAuthService.this.hasWXEntryActivity(iWebview.getContext())) {
                    iWebview.obtainWindowView().postDelayed(new Runnable() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                            WeiXinOAuthService.this.onLoginCallBack(iWebview, str, -3);
                        }
                    }, 500L);
                    return;
                }
                WeiXinOAuthService.this.isLoginReceiver = true;
                if (FeatureMessageDispatcher.sFeatureMessage.contains(WeiXinOAuthService.this.sLoginMessageListener)) {
                    return;
                }
                FeatureMessageDispatcher.registerListener(WeiXinOAuthService.this.sLoginMessageListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLoginCallBack(IWebview iWebview, String str, int i) {
        boolean z;
        String str2 = "send";
        if (i == 0) {
            z = true;
        } else {
            if (i == -4) {
                str2 = "Authentication failed";
            } else if (i == -1) {
                str2 = "General errors";
            } else if (i == -3) {
                str2 = "Unable to send";
            } else if (i == -5) {
                str2 = "Unsupport error";
            } else if (i == -2) {
                onLoginFinished(getErrorJsonbject(-2, DOMException.MSG_USER_CANCEL), false, iWebview, str);
                return;
            }
            z = false;
        }
        if (z) {
            JSUtil.execCallback(iWebview, str, makeResultJSONObject(), JSUtil.OK, false);
        } else {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(-100, str2, i), JSUtil.ERROR, true, false);
        }
    }

    private void reFreshTokenAndSave(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWeiXinMediator(SendAuth.Req req, final IWebview iWebview, final String str) {
        Intent intent = new Intent();
        intent.putExtra(ProcessMediator.LOGIC_CLASS, WeiXinMediator.class.getName());
        Bundle bundle = new Bundle();
        req.toBundle(bundle);
        intent.putExtra("req", bundle);
        intent.putExtra("transaction", req.transaction);
        intent.setClassName(iWebview.getActivity(), ProcessMediator.class.getName());
        iWebview.getActivity().startActivityForResult(intent, 1000);
        iWebview.getActivity().overridePendingTransition(0, 0);
        iWebview.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.5
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                ((Integer) objArr[1]).intValue();
                Intent intent2 = (Intent) objArr[2];
                if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == 1000) {
                    Bundle bundleExtra = intent2.getBundleExtra("result");
                    if (bundleExtra == null) {
                        WeiXinOAuthService.this.onLoginCallBack(iWebview, str, -2);
                    } else {
                        String string = bundleExtra.getString("style");
                        if ("BaseResp".equals(string)) {
                            SendAuth.Resp resp = new SendAuth.Resp();
                            resp.fromBundle(bundleExtra);
                            WeiXinOAuthService.this.sLoginMessageListener.onReceiver(resp);
                        } else if ("BaseReq".equals(string)) {
                            SendAuth.Req req2 = new SendAuth.Req();
                            req2.fromBundle(bundleExtra);
                            WeiXinOAuthService.this.sLoginMessageListener.onReceiver(req2);
                        }
                    }
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void authorize(IWebview iWebview, JSONArray jSONArray) {
        super.authorize(iWebview, jSONArray);
        if (TextUtils.isEmpty(appId)) {
            Deprecated_JSUtil.execCallback(iWebview, this.mAuthCallbackId, StringUtil.format(DOMException.JSON_ERROR_INFO, -7, DOMException.toString(DOMException.MSG_BUSINESS_PARAMETER_HAS_NOT)), JSUtil.ERROR, true, false);
        } else if (PlatformUtil.isAppInstalled(iWebview.getContext(), "com.tencent.mm")) {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.3
                @Override // java.lang.Runnable
                public void run() {
                    WeiXinOAuthService.this.isAuth = true;
                    WeiXinOAuthService weiXinOAuthService = WeiXinOAuthService.this;
                    weiXinOAuthService.loginInThread(weiXinOAuthService.mAuthWebview, WeiXinOAuthService.this.mAuthCallbackId, WeiXinOAuthService.this.mAuthOptions);
                }
            });
        } else {
            Deprecated_JSUtil.execCallback(iWebview, this.mAuthCallbackId, StringUtil.format(DOMException.JSON_ERROR_INFO, -8, DOMException.toString(DOMException.MSG_CLIENT_UNINSTALLED)), JSUtil.ERROR, true, false);
        }
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void getUserInfo(IWebview iWebview, JSONArray jSONArray) {
        super.getUserInfo(iWebview, jSONArray);
        if (hasGeneralError(this.mGetUserInfoWebViewImpl, this.mGetUserInfoCallbackId)) {
            return;
        }
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.6
            @Override // java.lang.Runnable
            public void run() {
                boolean zInitUserInfo = WeiXinOAuthService.this.initUserInfo();
                if (zInitUserInfo) {
                    WeiXinOAuthService.this.onGetUserInfoFinished(WeiXinOAuthService.this.makeResultJSONObject(), zInitUserInfo);
                } else {
                    Deprecated_JSUtil.execCallback(WeiXinOAuthService.this.mGetUserInfoWebViewImpl, WeiXinOAuthService.this.mGetUserInfoCallbackId, DOMException.toJSON(-100, DOMException.MSG_UNOAUTH_ERROR, -1003), JSUtil.ERROR, true, false);
                }
            }
        });
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public boolean hasFullConfigData() {
        return (TextUtils.isEmpty(appId) || TextUtils.isEmpty(appSecret)) ? false : true;
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature.BaseModule
    public void init(Context context) {
        super.init(context);
        this.id = WeiXinApiManager.WEIXIN_ID;
        this.description = DCLoudApplicationImpl.self().getContext().getResources().getString(R.string.dcloud_feature_oauth_weixin_plugin_description);
        this.nativeClient = PlatformUtil.isAppInstalled(context, "com.tencent.mm");
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void initAuthOptions(JSONObject jSONObject) {
        if (jSONObject != null) {
            appId = jSONObject.optString("appid", appId);
            Logger.e(TAG, "initAuthOptions: appId" + appId);
            appSecret = jSONObject.optString(BaseOAuthService.KEY_APSECRET, appSecret);
            redirectUri = jSONObject.optString("redirect_uri", redirectUri);
            appKEY = jSONObject.optString("appkey", appKEY);
        }
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void initMetaData() {
        appId = AndroidResources.getMetaValue("WX_APPID");
        appSecret = AndroidResources.getMetaValue("WX_SECRET");
    }

    public boolean initUserInfo() {
        String userInfo;
        JSONObject jSONObjectCreateJSONObject;
        JSONObject jSONObjectCreateJSONObject2 = JSONUtil.createJSONObject(getValue(BaseOAuthService.KEY_AUTHRESULT));
        this.authResult = jSONObjectCreateJSONObject2;
        if (jSONObjectCreateJSONObject2 == null || !jSONObjectCreateJSONObject2.has("access_token") || (jSONObjectCreateJSONObject = JSONUtil.createJSONObject((userInfo = getUserInfo(StringUtil.format(URL_GET_USERINFO, this.authResult.opt("access_token"), this.authResult.opt("openid")))))) == null || jSONObjectCreateJSONObject.has(KEY_ERRCODE)) {
            return false;
        }
        saveValue(BaseOAuthService.KEY_USERINFO, userInfo);
        this.userInfo = jSONObjectCreateJSONObject;
        return true;
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void login(IWebview iWebview, JSONArray jSONArray) {
        super.login(iWebview, jSONArray);
        if (hasGeneralError(this.mLoginWebViewImpl, this.mLoginCallbackId)) {
            return;
        }
        if (PlatformUtil.isAppInstalled(iWebview.getContext(), "com.tencent.mm")) {
            ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.weixin.WeiXinOAuthService.2
                @Override // java.lang.Runnable
                public void run() {
                    WeiXinOAuthService weiXinOAuthService = WeiXinOAuthService.this;
                    weiXinOAuthService.loginInThread(weiXinOAuthService.mLoginWebViewImpl, WeiXinOAuthService.this.mLoginCallbackId, WeiXinOAuthService.this.mLoginOptions);
                }
            });
        } else {
            Deprecated_JSUtil.execCallback(iWebview, this.mLoginCallbackId, StringUtil.format(DOMException.JSON_ERROR_INFO, -8, DOMException.toString(DOMException.MSG_CLIENT_UNINSTALLED)), JSUtil.ERROR, true, false);
        }
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void logout(IWebview iWebview, JSONArray jSONArray) {
        super.logout(iWebview, jSONArray);
        if (hasGeneralError(this.mLogoutWebViewImpl, this.mLogoutCallbackId)) {
            return;
        }
        removeToken();
        this.userInfo = null;
        this.authResult = null;
        onLogoutFinished(makeResultJSONObject(), true);
        this.api = null;
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public JSONObject makeResultJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BaseOAuthService.KEY_AUTHRESULT, this.authResult);
            jSONObject.put(BaseOAuthService.KEY_USERINFO, this.userInfo);
            jSONObject.put("state", getValue("state"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void onLoginFinished(JSONObject jSONObject, boolean z, IWebview iWebview, String str) {
        JSUtil.execCallback(iWebview, str, jSONObject, z ? JSUtil.OK : JSUtil.ERROR, false);
        if (this.isAuth) {
            this.mAuthWebview = null;
            this.mAuthCallbackId = null;
        } else {
            this.mLoginCallbackId = null;
            this.mLoginWebViewImpl = null;
        }
    }
}
