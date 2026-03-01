package io.dcloud.feature.sdk;

import android.text.TextUtils;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPCapsuleButtonStyle {
    public String backgroundColor;
    public String borderColor;
    public String highlightColor;
    public String textColor;

    public JSONObject getCapsuleButtonStyle() {
        if (TextUtils.isEmpty(this.backgroundColor) && TextUtils.isEmpty(this.textColor) && TextUtils.isEmpty(this.highlightColor) && TextUtils.isEmpty(this.borderColor)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(this.backgroundColor)) {
                jSONObject.put("backgroundColor", this.backgroundColor);
            }
            if (!TextUtils.isEmpty(this.textColor)) {
                jSONObject.put(WXPickersModule.KEY_TEXT_COLOR, this.textColor);
            }
            if (!TextUtils.isEmpty(this.highlightColor)) {
                jSONObject.put("highlightColor", this.highlightColor);
            }
            if (!TextUtils.isEmpty(this.borderColor)) {
                jSONObject.put(Constants.Name.BORDER_COLOR, this.borderColor);
            }
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public void setBorderColor(String str) {
        this.borderColor = str;
    }

    public void setHighlightColor(String str) {
        this.highlightColor = str;
    }

    public void setTextColor(String str) {
        this.textColor = str;
    }
}
