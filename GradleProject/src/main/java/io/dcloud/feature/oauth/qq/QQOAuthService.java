package io.dcloud.feature.oauth.qq;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tencent.connect.UnionInfo;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.feature.oauth.BaseOAuthService;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class QQOAuthService extends BaseOAuthService {
    public static final String TAG = "QQOAuthService";
    public static String appId;
    public static String appKEY;
    public static String appSecret;
    public static String redirectUri;
    public Context mContext;
    public IUiListener mIUiListener = new IUiListener() { // from class: io.dcloud.feature.oauth.qq.QQOAuthService.1
        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            QQOAuthService qQOAuthService = QQOAuthService.this;
            qQOAuthService.onLoginFinished(qQOAuthService.getErrorJsonbject(-2, DOMException.MSG_USER_CANCEL), false);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            if (obj == null) {
                QQOAuthService qQOAuthService = QQOAuthService.this;
                qQOAuthService.onLoginFinished(qQOAuthService.getErrorJsonbject(-1002, DOMException.MSG_GET_TOKEN_ERROR), false);
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() == 0) {
                QQOAuthService qQOAuthService2 = QQOAuthService.this;
                qQOAuthService2.onLoginFinished(qQOAuthService2.getErrorJsonbject(-1002, DOMException.MSG_GET_TOKEN_ERROR), false);
                return;
            }
            QQOAuthService.this.authResult = jSONObject;
            QQOAuthService qQOAuthService3 = QQOAuthService.this;
            qQOAuthService3.initOpenidAndToken(qQOAuthService3.authResult);
            QQOAuthService.this.updateUserInfo(new UserInfo(QQOAuthService.this.mContext, QQOAuthService.this.mTencent.getQQToken()));
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            QQOAuthService qQOAuthService = QQOAuthService.this;
            qQOAuthService.onLoginFinished(qQOAuthService.getErrorJsonbject(-100, uiError.errorMessage, uiError.errorCode), false);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
        }
    };
    public Tencent mTencent;

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUnionId() {
        Tencent tencent = this.mTencent;
        if (tencent == null || !tencent.isSessionValid()) {
            Logger.d(TAG, "please login frist!");
        } else {
            new UnionInfo(this.mContext, this.mTencent.getQQToken()).getUnionId(new IUiListener() { // from class: io.dcloud.feature.oauth.qq.QQOAuthService.4
                @Override // com.tencent.tauth.IUiListener
                public void onCancel() {
                    QQOAuthService qQOAuthService = QQOAuthService.this;
                    qQOAuthService.onLoginFinished(qQOAuthService.makeResultJson(), true);
                }

                @Override // com.tencent.tauth.IUiListener
                public void onComplete(Object obj) {
                    if (obj != null) {
                        JSONObject jSONObject = (JSONObject) obj;
                        try {
                            if (jSONObject.has("unionid")) {
                                QQOAuthService.this.userInfo.put("unionid", jSONObject.getString("unionid"));
                                QQOAuthService.this.saveValue(BaseOAuthService.KEY_USERINFO, QQOAuthService.this.userInfo.toString());
                            }
                        } catch (Exception e2) {
                            Logger.d(QQOAuthService.TAG, "no unionid" + e2.getMessage());
                        }
                    }
                    QQOAuthService qQOAuthService = QQOAuthService.this;
                    qQOAuthService.onLoginFinished(qQOAuthService.makeResultJson(), true);
                }

                @Override // com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                    QQOAuthService qQOAuthService = QQOAuthService.this;
                    qQOAuthService.onLoginFinished(qQOAuthService.makeResultJson(), true);
                }

                @Override // com.tencent.tauth.IUiListener
                public void onWarning(int i) {
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUserInfo(UserInfo userInfo) {
        Tencent tencent = this.mTencent;
        if (tencent == null || !tencent.isSessionValid()) {
            return;
        }
        userInfo.getUserInfo(new IUiListener() { // from class: io.dcloud.feature.oauth.qq.QQOAuthService.3
            @Override // com.tencent.tauth.IUiListener
            public void onCancel() {
                QQOAuthService qQOAuthService = QQOAuthService.this;
                qQOAuthService.onLoginFinished(qQOAuthService.getErrorJsonbject(-2, DOMException.MSG_USER_CANCEL), false);
            }

            @Override // com.tencent.tauth.IUiListener
            public void onComplete(Object obj) {
                if (obj == null) {
                    QQOAuthService qQOAuthService = QQOAuthService.this;
                    qQOAuthService.onLoginFinished(qQOAuthService.getErrorJsonbject(-1002, DOMException.MSG_GET_TOKEN_ERROR), false);
                    return;
                }
                JSONObject jSONObject = (JSONObject) obj;
                QQOAuthService.this.userInfo = jSONObject;
                try {
                    QQOAuthService.this.userInfo.put(BaseOAuthService.KEY_HEADIMGURL, jSONObject.optString("figureurl"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                QQOAuthService qQOAuthService2 = QQOAuthService.this;
                qQOAuthService2.saveValue(BaseOAuthService.KEY_USERINFO, qQOAuthService2.userInfo.toString());
                QQOAuthService.this.updateUnionId();
            }

            @Override // com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
                QQOAuthService qQOAuthService = QQOAuthService.this;
                qQOAuthService.onLoginFinished(qQOAuthService.getErrorJsonbject(-100, uiError.errorMessage, uiError.errorCode), false);
            }

            @Override // com.tencent.tauth.IUiListener
            public void onWarning(int i) {
            }
        });
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void getUserInfo(IWebview iWebview, JSONArray jSONArray) {
        super.getUserInfo(iWebview, jSONArray);
        if (hasGeneralError(this.mGetUserInfoWebViewImpl, this.mGetUserInfoCallbackId)) {
            return;
        }
        String value = getValue(BaseOAuthService.KEY_AUTHRESULT);
        if (!TextUtils.isEmpty(value)) {
            initOpenidAndToken(value);
        }
        Tencent tencent = this.mTencent;
        if (tencent == null || !tencent.isSessionValid()) {
            onGetUserInfoFinished(getErrorJsonbject(-1001, DOMException.MSG_OAUTH_FAIL), false);
            return;
        }
        try {
            this.userInfo = new JSONObject(getValue(BaseOAuthService.KEY_USERINFO));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        onGetUserInfoFinished(makeResultJson(), true);
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public boolean hasFullConfigData() {
        return !TextUtils.isEmpty(appId);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature.BaseModule
    public void init(Context context) {
        super.init(context);
        this.mContext = context;
        this.id = "qq";
        this.description = Constants.SOURCE_QQ;
        this.nativeClient = PlatformUtil.isAppInstalled(context, "com.tencent.mobileqq");
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
        appId = AndroidResources.getMetaValue("QQ_APPID");
    }

    public void initOpenidAndToken(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("access_token");
            String string2 = jSONObject.getString("expires_in");
            String string3 = jSONObject.getString("openid");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                return;
            }
            this.mTencent.setAccessToken(string, string2);
            this.mTencent.setOpenId(string3);
            saveValue(BaseOAuthService.KEY_AUTHRESULT, jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void login(final IWebview iWebview, JSONArray jSONArray) {
        super.login(iWebview, jSONArray);
        if (hasGeneralError(this.mLoginWebViewImpl, this.mLoginCallbackId)) {
            return;
        }
        if (this.mTencent == null) {
            this.mTencent = Tencent.createInstance(appId, this.mContext);
        }
        iWebview.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.feature.oauth.qq.QQOAuthService.2
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                int iIntValue2 = ((Integer) objArr[1]).intValue();
                Intent intent = (Intent) objArr[2];
                if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == 11101 && iIntValue2 == -1) {
                    QQOAuthService.this.mTencent.handleLoginData(intent, QQOAuthService.this.mIUiListener);
                }
                IWebview iWebview2 = iWebview;
                if (iWebview2 != null) {
                    iWebview2.obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onActivityResult);
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
        String value = getValue(BaseOAuthService.KEY_AUTHRESULT);
        if (!TextUtils.isEmpty(value)) {
            initOpenidAndToken(value);
        }
        if (this.mTencent.isSessionValid()) {
            updateUserInfo(new UserInfo(this.mContext, this.mTencent.getQQToken()));
        } else {
            this.mTencent.login(iWebview.getActivity(), ProviderConfigurationPermission.ALL_STR, this.mIUiListener);
        }
    }

    @Override // io.dcloud.feature.oauth.BaseOAuthService
    public void logout(IWebview iWebview, JSONArray jSONArray) {
        super.logout(iWebview, jSONArray);
        if (hasGeneralError(this.mLogoutWebViewImpl, this.mLogoutCallbackId)) {
            return;
        }
        this.userInfo = null;
        this.authResult = null;
        removeToken();
        onLogoutFinished(makeResultJson(), true);
        this.mTencent.logout(this.mContext);
        this.mTencent = null;
    }

    public void initOpenidAndToken(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("access_token");
            String string2 = jSONObject.getString("expires_in");
            String string3 = jSONObject.getString("openid");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                return;
            }
            if (this.mTencent == null) {
                this.mTencent = Tencent.createInstance(appId, this.mContext);
            }
            this.mTencent.setAccessToken(string, string2);
            this.mTencent.setOpenId(string3);
            this.authResult = jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
