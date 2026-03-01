package io.dcloud.common.DHInterface;

import com.google.zxing.client.android.ScanFromWebPageManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface ISmartUpdate {

    public interface OnSmartUpdateCallback {
        void onCallback(SmartUpdateCallbackParams smartUpdateCallbackParams);
    }

    public static class SmartUpdateCallbackParams {
        public String icon_url;
        public int mode;
        public boolean needUpdate;
        public String oldVersion;
        public JSONObject sitemap;
        public String splash_url;
        public int type;
        public String updateVersion;
        public String url;
        public String wap2app_url;

        public SmartUpdateCallbackParams(String str) {
            this.needUpdate = false;
            this.type = 1;
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt(ScanFromWebPageManager.RETURN_URL_PARAM, jSONObject.optInt("status", 0) == 1 ? 0 : 1);
                this.url = jSONObject.optString("url");
                this.icon_url = jSONObject.optString("icon_url");
                this.splash_url = jSONObject.optString("splash_url");
                this.wap2app_url = jSONObject.optString("wap2app_url");
                this.sitemap = jSONObject.optJSONObject("sitemap");
                if (iOptInt == 0) {
                    String strOptString = jSONObject.optString("type");
                    this.updateVersion = jSONObject.optString("updateVersion");
                    this.needUpdate = true;
                    if (strOptString.equals("wgt")) {
                        this.type = 0;
                    }
                }
                this.mode = jSONObject.optInt("up_mode");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    void checkUpdate();

    void reload();

    void update(SmartUpdateCallbackParams smartUpdateCallbackParams);
}
