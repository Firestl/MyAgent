package io.dcloud.feature.oauth;

import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.DHInterface.BaseFeature;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseOAuthService extends BaseFeature.BaseModule implements IReflectAble {
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_APPID = "appid";
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_APSECRET = "appsecret";
    public static final String KEY_AUTHRESULT = "authResult";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_EXPIRES_IN = "expires_in";
    public static final String KEY_HEADIMGURL = "headimgurl";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_OPENID = "openid";
    public static final String KEY_REDIRECT_URI = "redirect_uri";
    public static final String KEY_REFRESH_TOKEN = "refresh_token";
    public static final String KEY_SCOPE = "scope";
    public static final String KEY_STATE = "state";
    public static final String KEY_UNIONID = "unionid";
    public static final String KEY_UNIVERIFYINFO = "univerifyInfo";
    public static final String KEY_USERINFO = "userInfo";
    public static final String NULL = "null";
    public JSONObject authResult;
    public JSONObject univerifyInfo;
    public JSONObject userInfo;
    public IWebview mLoginWebViewImpl = null;
    public String mLoginCallbackId = null;
    public JSONObject mLoginOptions = null;
    public boolean nativeClient = false;
    public IWebview mAuthWebview = null;
    public String mAuthCallbackId = null;
    public JSONObject mAuthOptions = null;
    public IWebview mLogoutWebViewImpl = null;
    public String mLogoutCallbackId = null;
    public IWebview mPreLoginWebViewImpl = null;
    public String mPreLoginCallbackId = null;
    public IWebview mOtherClickWebViewImpl = null;
    public String mOtherClickCallbackId = null;
    public IWebview mGetUserInfoWebViewImpl = null;
    public String mGetUserInfoCallbackId = null;
    public IWebview mAddPhoneNumberWebViewImpl = null;
    public String mAddPhoneNumberCallbackId = null;

    public void addPhoneNumber(IWebview iWebview, JSONArray jSONArray) {
        this.mAddPhoneNumberWebViewImpl = iWebview;
        try {
            this.mAddPhoneNumberCallbackId = jSONArray.getString(1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void authorize(IWebview iWebview, JSONArray jSONArray) {
        initMetaData();
        this.mAuthWebview = iWebview;
        this.mAuthCallbackId = jSONArray.optString(1, "");
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(2);
        this.mAuthOptions = jSONObjectOptJSONObject;
        initAuthOptions(jSONObjectOptJSONObject);
    }

    public String checkToken(String str) {
        return new String(NetTool.httpGet(str));
    }

    public void closeAuthView(IWebview iWebview, JSONArray jSONArray) {
    }

    public String decrypt(String str) throws Exception {
        return Base64.decodeString(str, true, 5);
    }

    public String encrypt(String str) throws Exception {
        return Base64.encodeString(str, true, 5);
    }

    public boolean getCheckBoxState() {
        return false;
    }

    public JSONObject getErrorJsonbject(int i, String str) {
        try {
            return new JSONObject(DOMException.toJSON(i, str));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new JSONObject();
        }
    }

    public String getToken(String str) {
        byte[] bArrHttpGet = NetTool.httpGet(str);
        if (bArrHttpGet != null) {
            return new String(bArrHttpGet);
        }
        return null;
    }

    public void getUserInfo(IWebview iWebview, JSONArray jSONArray) {
        this.mGetUserInfoWebViewImpl = iWebview;
        try {
            this.mGetUserInfoCallbackId = jSONArray.getString(1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String getValue(String str) {
        try {
            String strEncrypt = encrypt(str);
            String bundleData = SP.getBundleData(this.mApplicationContext, "oauth_" + this.id, strEncrypt);
            return bundleData == null ? "{}" : decrypt(bundleData);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "{}";
        }
    }

    public boolean hasFullConfigData() {
        return false;
    }

    public boolean hasGeneralError(IWebview iWebview, String str) {
        if (hasFullConfigData()) {
            return false;
        }
        Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -7, DOMException.toString(DOMException.MSG_BUSINESS_PARAMETER_HAS_NOT)), JSUtil.ERROR, true, false);
        return true;
    }

    public void initAuthOptions(JSONObject jSONObject) {
    }

    public void initMetaData() {
    }

    public void login(IWebview iWebview, JSONArray jSONArray) {
        initMetaData();
        this.mLoginWebViewImpl = iWebview;
        try {
            this.mLoginCallbackId = jSONArray.getString(1);
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(2);
            this.mLoginOptions = jSONObjectOptJSONObject;
            initAuthOptions(jSONObjectOptJSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void logout(IWebview iWebview, JSONArray jSONArray) {
        this.mLogoutWebViewImpl = iWebview;
        try {
            this.mLogoutCallbackId = jSONArray.getString(1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject makeResultJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_AUTHRESULT, this.authResult);
            jSONObject.put(KEY_USERINFO, this.userInfo);
            jSONObject.put(KEY_UNIVERIFYINFO, this.univerifyInfo);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject makeResultJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_AUTHRESULT, this.authResult);
            jSONObject.put(KEY_USERINFO, this.userInfo);
            jSONObject.put(KEY_UNIVERIFYINFO, this.univerifyInfo);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void onAddPhoneNumberFinished(JSONObject jSONObject, boolean z) {
        JSUtil.execCallback(this.mAddPhoneNumberWebViewImpl, this.mAddPhoneNumberCallbackId, jSONObject, z ? JSUtil.OK : JSUtil.ERROR, false);
    }

    public void onGetUserInfoFinished(JSONObject jSONObject, boolean z) {
        JSUtil.execCallback(this.mGetUserInfoWebViewImpl, this.mGetUserInfoCallbackId, jSONObject, z ? JSUtil.OK : JSUtil.ERROR, false);
    }

    public void onLoginFinished(JSONObject jSONObject, boolean z) {
        JSUtil.execCallback(this.mLoginWebViewImpl, this.mLoginCallbackId, jSONObject, z ? JSUtil.OK : JSUtil.ERROR, false);
        this.mLoginWebViewImpl = null;
        this.mLoginCallbackId = null;
    }

    public void onLogoutFinished(JSONObject jSONObject, boolean z) {
        JSUtil.execCallback(this.mLogoutWebViewImpl, this.mLogoutCallbackId, jSONObject, z ? JSUtil.OK : JSUtil.ERROR, false);
    }

    public void onPreLoginFinished(JSONObject jSONObject, boolean z) {
        JSUtil.execCallback(this.mPreLoginWebViewImpl, this.mPreLoginCallbackId, jSONObject, z ? JSUtil.OK : JSUtil.ERROR, false);
    }

    public void otherLoginButtonClick(IWebview iWebview, JSONArray jSONArray) {
        this.mOtherClickWebViewImpl = iWebview;
        try {
            this.mOtherClickCallbackId = jSONArray.getString(1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void preLogin(IWebview iWebview, JSONArray jSONArray) {
        initMetaData();
        this.mPreLoginWebViewImpl = iWebview;
        try {
            this.mPreLoginCallbackId = jSONArray.getString(1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String refreshToken(String str) {
        byte[] bArrHttpGet = NetTool.httpGet(str);
        if (bArrHttpGet != null) {
            return new String(bArrHttpGet);
        }
        return null;
    }

    public void removeToken() {
        SP.clearBundle(this.mApplicationContext, "oauth_" + this.id);
    }

    public void removeValue(String str) {
        try {
            String strEncrypt = encrypt(str);
            SP.removeBundleData(this.mApplicationContext, "oauth_" + this.id, strEncrypt);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void saveValue(String str, String str2) {
        try {
            String strEncrypt = encrypt(str);
            String strEncrypt2 = encrypt(str2);
            SP.setBundleData(this.mApplicationContext, "oauth_" + this.id, strEncrypt, strEncrypt2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature.BaseModule
    public JSONObject toJSONObject() throws JSONException {
        Object[] objArr = new Object[5];
        objArr[0] = this.id;
        objArr[1] = this.description;
        objArr[2] = Boolean.valueOf(this.nativeClient);
        Object obj = this.authResult;
        if (obj == null) {
            obj = NULL;
        }
        objArr[3] = obj;
        Object obj2 = this.userInfo;
        if (obj2 == null) {
            obj2 = NULL;
        }
        objArr[4] = obj2;
        return new JSONObject(StringUtil.format("{id:'%s',description:'%s', nativeClient:%b, authResult:%s,userInfo:%s}", objArr));
    }

    public String getUserInfo(String str) {
        byte[] bArrHttpGet = NetTool.httpGet(str);
        if (PdrUtil.isEmpty(bArrHttpGet)) {
            return null;
        }
        return new String(bArrHttpGet);
    }

    public JSONObject getErrorJsonbject(String str, String str2) {
        try {
            return new JSONObject(DOMException.toJSON(str, str2));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new JSONObject();
        }
    }

    public JSONObject getErrorJsonbject(int i, String str, int i2) {
        try {
            return new JSONObject(DOMException.toJSON(i, i + Constants.COLON_SEPARATOR + str, i2));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new JSONObject();
        }
    }
}
