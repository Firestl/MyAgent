package io.dcloud.feature.oauth.sina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.oauth.BaseOAuthService;
import io.dcloud.feature.oauth.sina.AccessTokenKeeper;
import io.dcloud.share.sina.SinaWeiboApiManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class SinaOAuthService extends BaseOAuthService {
    public static final String EXPIRES_TIME = "expires_time";
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
    public static final String TAG = "SinaOAuthService";
    public static final String TOKEN = "token";
    public static final String UID = "uid";
    public static final String URL_GETUID = "https://api.weibo.com/2/account/get_uid.json?access_token=%s";
    public static final String URL_GET_USERINFO = "https://api.weibo.com/2/users/show.json?access_token=%s&uid=%s";
    public static final String URL_REVOKE_OAUTH = "https://api.weibo.com/oauth2/revokeoauth2";
    public static String appKEY;
    public static String redirectUri;
    public AccessTokenKeeper.SinaOAuthTokenWrapper mAccessToken;
    public Context mContext;
    public IWBAPI mWBAPI;

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getSinaAuthResultJB(AccessTokenKeeper.SinaOAuthTokenWrapper sinaOAuthTokenWrapper) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("expires_in", sinaOAuthTokenWrapper.getExpiresTime());
            jSONObject.put("access_token", sinaOAuthTokenWrapper.getAccessToken());
            jSONObject.put("refresh_token", sinaOAuthTokenWrapper.getRefreshToken());
            jSONObject.put("openid", sinaOAuthTokenWrapper.getUid());
            jSONObject.put("uid", sinaOAuthTokenWrapper.getUid());
            jSONObject.put("expires_time", sinaOAuthTokenWrapper.getExpiresTime());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initUserInfo() {
        if (this.mAccessToken == null) {
            this.mAccessToken = AccessTokenKeeper.readAccessToken(this.mContext);
        }
        AccessTokenKeeper.SinaOAuthTokenWrapper sinaOAuthTokenWrapper = this.mAccessToken;
        if (sinaOAuthTokenWrapper != null && sinaOAuthTokenWrapper.isSessionValid()) {
            String userInfo = getUserInfo(StringUtil.format(URL_GET_USERINFO, this.mAccessToken.getAccessToken(), this.mAccessToken.getUid()));
            Logger.e("ian", "inituserinfo  s_userinforesult" + userInfo);
            if (userInfo != null) {
                JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(userInfo);
                this.userInfo = jSONObjectCreateJSONObject;
                try {
                    jSONObjectCreateJSONObject.put(BaseOAuthService.KEY_HEADIMGURL, jSONObjectCreateJSONObject.optString("profile_image_url"));
                    this.userInfo.put(BaseOAuthService.KEY_NICKNAME, jSONObjectCreateJSONObject.optString("screen_name"));
                    this.userInfo.put("openid", jSONObjectCreateJSONObject.optString("idstr"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                saveValue(BaseOAuthService.KEY_USERINFO, this.userInfo.toString());
                return true;
            }
        }
        return false;
    }

    private void initWBAPI(Activity activity) {
        IWBAPI iwbapiCreateWBAPI = WBAPIFactory.createWBAPI(activity);
        this.mWBAPI = iwbapiCreateWBAPI;
        iwbapiCreateWBAPI.registerApp(activity, new AuthInfo(activity, appKEY, redirectUri, SCOPE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginInThread() {
        String token;
        AccessTokenKeeper.SinaOAuthTokenWrapper sinaOAuthTokenWrapper = this.mAccessToken;
        if (sinaOAuthTokenWrapper != null && sinaOAuthTokenWrapper.isSessionValid() && !PdrUtil.isEmpty(this.mAccessToken.getAccessToken()) && (token = getToken(StringUtil.format(URL_GETUID, this.mAccessToken.getAccessToken()))) != null && !PdrUtil.isEmpty(JSONUtil.createJSONObject(token).optString("uid"))) {
            this.authResult = getSinaAuthResultJB(this.mAccessToken);
            onLoginFinished(makeResultJson(), true);
            return;
        }
        this.mWBAPI.authorize(new WbAuthListener() { // from class: io.dcloud.feature.oauth.sina.SinaOAuthService.2
            public void onCancel() {
                SinaOAuthService sinaOAuthService = SinaOAuthService.this;
                sinaOAuthService.onLoginFinished(sinaOAuthService.getErrorJsonbject(-2, DOMException.MSG_USER_CANCEL), false);
            }

            public void onComplete(Oauth2AccessToken oauth2AccessToken) {
                SinaOAuthService.this.mAccessToken = new AccessTokenKeeper.SinaOAuthTokenWrapper(oauth2AccessToken);
                if (SinaOAuthService.this.mAccessToken.isSessionValid()) {
                    AccessTokenKeeper.writeAccessToken(SinaOAuthService.this.mContext, SinaOAuthService.this.mAccessToken);
                    SinaOAuthService sinaOAuthService = SinaOAuthService.this;
                    sinaOAuthService.authResult = sinaOAuthService.getSinaAuthResultJB(sinaOAuthService.mAccessToken);
                    SinaOAuthService.this.onLoginFinished(SinaOAuthService.this.makeResultJSONObject(), true);
                }
            }

            public void onError(UiError uiError) {
                SinaOAuthService sinaOAuthService = SinaOAuthService.this;
                sinaOAuthService.onLoginFinished(sinaOAuthService.getErrorJsonbject(uiError.errorCode, uiError.errorMessage), false);
            }
        });
        IWebview iWebview = this.mLoginWebViewImpl;
        if (iWebview == null || iWebview.obtainApp() == null) {
            return;
        }
        this.mLoginWebViewImpl.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.feature.oauth.sina.SinaOAuthService.3
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                int iIntValue2 = ((Integer) objArr[1]).intValue();
                Intent intent = (Intent) objArr[2];
                if (SinaOAuthService.this.mWBAPI != null) {
                    SinaOAuthService.this.mWBAPI.authorizeCallback(iIntValue, iIntValue2, intent);
                }
                if (SinaOAuthService.this.mLoginWebViewImpl != null) {
                    SinaOAuthService.this.mLoginWebViewImpl.obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onActivityResult);
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void getUserInfo(IWebview iWebview, JSONArray jSONArray) {
        super.getUserInfo(iWebview, jSONArray);
        if (hasGeneralError(this.mGetUserInfoWebViewImpl, this.mGetUserInfoCallbackId)) {
            return;
        }
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.sina.SinaOAuthService.5
            @Override // java.lang.Runnable
            public void run() {
                boolean zInitUserInfo = SinaOAuthService.this.initUserInfo();
                if (zInitUserInfo) {
                    SinaOAuthService.this.onGetUserInfoFinished(SinaOAuthService.this.makeResultJSONObject(), zInitUserInfo);
                } else {
                    SinaOAuthService sinaOAuthService = SinaOAuthService.this;
                    sinaOAuthService.onGetUserInfoFinished(sinaOAuthService.getErrorJsonbject(-1001, DOMException.MSG_OAUTH_FAIL), false);
                }
            }
        });
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public boolean hasFullConfigData() {
        return (TextUtils.isEmpty(redirectUri) || TextUtils.isEmpty(appKEY)) ? false : true;
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature.BaseModule
    public void init(Context context) {
        super.init(context);
        this.mContext = context;
        this.id = SinaWeiboApiManager.SINAWEIBO_ID;
        this.description = DCLoudApplicationImpl.self().getContext().getResources().getString(R.string.dcloud_feature_oauth_sina_plugin_description);
        this.nativeClient = PlatformUtil.isAppInstalled(context, SinaWeiboApiManager.PACKAGENAME);
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void initAuthOptions(JSONObject jSONObject) {
        if (jSONObject != null) {
            redirectUri = jSONObject.optString("redirect_uri", redirectUri);
            appKEY = jSONObject.optString("appkey", appKEY);
        }
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void initMetaData() {
        if (!TextUtils.isEmpty(AndroidResources.getMetaValue("SINA_APPKEY"))) {
            appKEY = AndroidResources.getMetaValue("SINA_APPKEY").substring(1);
        }
        redirectUri = AndroidResources.getMetaValue("SINA_REDIRECT_URI");
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void login(IWebview iWebview, JSONArray jSONArray) {
        super.login(iWebview, jSONArray);
        initWBAPI(iWebview.getActivity());
        if (hasGeneralError(this.mLoginWebViewImpl, this.mLoginCallbackId)) {
            return;
        }
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.sina.SinaOAuthService.1
            @Override // java.lang.Runnable
            public void run() {
                SinaOAuthService.this.loginInThread();
            }
        });
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void logout(IWebview iWebview, JSONArray jSONArray) {
        super.logout(iWebview, jSONArray);
        if (hasGeneralError(this.mLogoutWebViewImpl, this.mLogoutCallbackId)) {
            return;
        }
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.oauth.sina.SinaOAuthService.4
            @Override // java.lang.Runnable
            public void run() {
                if (SinaOAuthService.this.mAccessToken == null) {
                    SinaOAuthService sinaOAuthService = SinaOAuthService.this;
                    sinaOAuthService.mAccessToken = AccessTokenKeeper.readAccessToken(sinaOAuthService.mContext);
                }
                if (PdrUtil.isEmpty(SinaOAuthService.this.mAccessToken.getAccessToken())) {
                    SinaOAuthService sinaOAuthService2 = SinaOAuthService.this;
                    sinaOAuthService2.onLogoutFinished(sinaOAuthService2.getErrorJsonbject(-1001, DOMException.MSG_OAUTH_FAIL), false);
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("access_token=" + SinaOAuthService.this.mAccessToken.getAccessToken());
                byte[] bArrHttpPost = NetTool.httpPost("https://api.weibo.com/oauth2/revokeoauth2", stringBuffer.toString(), null);
                if (bArrHttpPost == null || bArrHttpPost.length <= 0) {
                    SinaOAuthService sinaOAuthService3 = SinaOAuthService.this;
                    sinaOAuthService3.onLogoutFinished(sinaOAuthService3.getErrorJsonbject(-1001, DOMException.MSG_OAUTH_FAIL), false);
                    return;
                }
                String str = new String(bArrHttpPost);
                Logger.e("ian", "logout resultStr==" + str);
                try {
                    if (PdrUtil.isEquals(new JSONObject(str).optString("result"), "true")) {
                        AccessTokenKeeper.clear(SinaOAuthService.this.mContext);
                        SinaOAuthService.this.mAccessToken = null;
                        SinaOAuthService.this.userInfo = null;
                        SinaOAuthService.this.authResult = null;
                        SinaOAuthService.this.removeToken();
                        SinaOAuthService.this.onLogoutFinished(SinaOAuthService.this.makeResultJson(), true);
                    } else {
                        SinaOAuthService.this.onLogoutFinished(SinaOAuthService.this.getErrorJsonbject(-1001, DOMException.MSG_OAUTH_FAIL), false);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
