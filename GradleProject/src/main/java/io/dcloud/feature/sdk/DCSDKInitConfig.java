package io.dcloud.feature.sdk;

import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DCSDKInitConfig {
    public static final String CAPSULE = "CAPSULE";
    public static final String DEFAULT_MB = "DefaultMenuButton";
    public boolean isCapsule;
    public boolean isDisplayedUnimpTaskList;
    public boolean isEnableBackground;
    public boolean isUniMPFromRecents;
    public String mDefaultMenuButton;

    public static class Builder {
        public DCUniMPCapsuleButtonStyle mCapsuleButtonStyle;
        public List<MenuActionSheetItem> mSheetItems;
        public boolean isCapsule = true;
        public String mMenuDefFontColor = AMapUtil.HtmlBlack;
        public String mMenuDefFontSize = "22px";
        public String mMenuDefFontWeight = "normal";
        public boolean isEnableBackground = true;
        public boolean isUniMPFromRecents = true;
        public boolean isDisplayedUnimpTaskList = true;

        private String getDefaultMenuButton() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AbsoluteConst.EVENTS_WEBVIEW_SHOW, this.isCapsule);
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                List<MenuActionSheetItem> list = this.mSheetItems;
                if (list != null && list.size() > 0) {
                    for (MenuActionSheetItem menuActionSheetItem : this.mSheetItems) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("title", menuActionSheetItem.f6598a);
                        jSONObject3.put("id", menuActionSheetItem.b);
                        jSONArray.put(jSONObject3);
                    }
                    jSONObject2.put("buttons", jSONArray);
                }
                jSONObject2.put(WXPickersModule.KEY_TEXT_COLOR, this.mMenuDefFontColor);
                jSONObject2.put(Constants.Name.FONT_SIZE, this.mMenuDefFontSize);
                jSONObject2.put(Constants.Name.FONT_WEIGHT, this.mMenuDefFontWeight);
                jSONObject.put(AbsoluteConst.EVENTS_MENU, jSONObject2);
                jSONObject.put("enableBackground", this.isEnableBackground);
                jSONObject.put("isUniMPFromRecents", this.isUniMPFromRecents);
                DCUniMPCapsuleButtonStyle dCUniMPCapsuleButtonStyle = this.mCapsuleButtonStyle;
                if (dCUniMPCapsuleButtonStyle != null) {
                    jSONObject.put("capsuleButtonStyle", dCUniMPCapsuleButtonStyle.getCapsuleButtonStyle());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject.toString();
        }

        public DCSDKInitConfig build() {
            DCSDKInitConfig dCSDKInitConfig = new DCSDKInitConfig();
            dCSDKInitConfig.isCapsule = this.isCapsule;
            dCSDKInitConfig.isEnableBackground = this.isEnableBackground;
            dCSDKInitConfig.isUniMPFromRecents = this.isUniMPFromRecents;
            dCSDKInitConfig.mDefaultMenuButton = getDefaultMenuButton();
            dCSDKInitConfig.isDisplayedUnimpTaskList = this.isDisplayedUnimpTaskList;
            return dCSDKInitConfig;
        }

        public Builder setCapsule(boolean z) {
            this.isCapsule = z;
            return this;
        }

        public Builder setCapsuleButtonStyle(DCUniMPCapsuleButtonStyle dCUniMPCapsuleButtonStyle) {
            this.mCapsuleButtonStyle = dCUniMPCapsuleButtonStyle;
            return this;
        }

        public Builder setEnableBackground(boolean z) {
            this.isEnableBackground = z;
            return this;
        }

        public Builder setMenuActionSheetItems(List<MenuActionSheetItem> list) {
            this.mSheetItems = list;
            return this;
        }

        public Builder setMenuDefFontColor(String str) {
            this.mMenuDefFontColor = str;
            return this;
        }

        public Builder setMenuDefFontSize(String str) {
            this.mMenuDefFontSize = str;
            return this;
        }

        public Builder setMenuDefFontWeight(String str) {
            this.mMenuDefFontWeight = str;
            return this;
        }

        public Builder setUniMPFromRecents(boolean z) {
            this.isUniMPFromRecents = z;
            return this;
        }
    }

    public String getDefaultMenuButton() {
        return this.mDefaultMenuButton;
    }

    public boolean isCapsule() {
        return this.isCapsule;
    }

    public boolean isEnableBackground() {
        return this.isEnableBackground;
    }

    public boolean isUniMPFromRecents() {
        return this.isUniMPFromRecents;
    }

    public DCSDKInitConfig() {
        this.isCapsule = true;
        this.isEnableBackground = true;
        this.isDisplayedUnimpTaskList = true;
        this.isUniMPFromRecents = true;
    }
}
