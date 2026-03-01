package io.dcloud.feature.oauth.sina;

import android.content.Context;
import android.content.SharedPreferences;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/* JADX INFO: loaded from: classes3.dex */
public class AccessTokenKeeper {
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_EXPIRES_IN = "expires_in";
    public static final String KEY_UID = "uid";
    public static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    public static class SinaOAuthTokenWrapper {
        public String localAccessToken;
        public long localExpiresTime;
        public String localUid;
        public Oauth2AccessToken sourceToken;

        public SinaOAuthTokenWrapper(Oauth2AccessToken oauth2AccessToken) {
            this.sourceToken = oauth2AccessToken;
        }

        public String getAccessToken() {
            Oauth2AccessToken oauth2AccessToken = this.sourceToken;
            return oauth2AccessToken != null ? oauth2AccessToken.getAccessToken() : this.localAccessToken;
        }

        public long getExpiresTime() {
            Oauth2AccessToken oauth2AccessToken = this.sourceToken;
            return oauth2AccessToken != null ? oauth2AccessToken.getExpiresTime() : this.localExpiresTime;
        }

        public String getRefreshToken() {
            Oauth2AccessToken oauth2AccessToken = this.sourceToken;
            return oauth2AccessToken != null ? oauth2AccessToken.getRefreshToken() : "";
        }

        public String getUid() {
            Oauth2AccessToken oauth2AccessToken = this.sourceToken;
            return oauth2AccessToken != null ? oauth2AccessToken.getUid() : this.localUid;
        }

        public boolean isSessionValid() {
            Oauth2AccessToken oauth2AccessToken = this.sourceToken;
            if (oauth2AccessToken == null) {
                return false;
            }
            return oauth2AccessToken.isSessionValid();
        }

        public void setAccessToken(String str) {
            this.localAccessToken = str;
        }

        public void setExpiresTime(long j) {
            this.localExpiresTime = j;
        }

        public void setUid(String str) {
            this.localUid = str;
        }
    }

    public static void clear(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES_NAME, 0).edit();
        editorEdit.clear();
        editorEdit.commit();
    }

    public static SinaOAuthTokenWrapper readAccessToken(Context context) {
        if (context == null) {
            return null;
        }
        SinaOAuthTokenWrapper sinaOAuthTokenWrapper = new SinaOAuthTokenWrapper(null);
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
        sinaOAuthTokenWrapper.setUid(sharedPreferences.getString("uid", ""));
        sinaOAuthTokenWrapper.setAccessToken(sharedPreferences.getString("access_token", ""));
        sinaOAuthTokenWrapper.setExpiresTime(sharedPreferences.getLong("expires_in", 0L));
        return sinaOAuthTokenWrapper;
    }

    public static void writeAccessToken(Context context, SinaOAuthTokenWrapper sinaOAuthTokenWrapper) {
        if (context == null || sinaOAuthTokenWrapper == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES_NAME, 0).edit();
        editorEdit.putString("uid", sinaOAuthTokenWrapper.getUid());
        editorEdit.putString("access_token", sinaOAuthTokenWrapper.getAccessToken());
        editorEdit.putLong("expires_in", sinaOAuthTokenWrapper.getExpiresTime());
        editorEdit.commit();
    }
}
