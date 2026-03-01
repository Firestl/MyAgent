package io.dcloud.feature.weex_amap.adapter.marker;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.ui.ArrowTextView;

/* JADX INFO: loaded from: classes3.dex */
public class AbsMarkerTextView {
    public String borderColor;
    public String content;
    public String display;
    public float mViewPort;
    public String color = "#000";
    public float fontSize = 11.0f;
    public int borderRadius = 0;
    public int borderWidth = 0;
    public String bgColor = "#fff";
    public int padding = 0;
    public int textAlign = 3;

    public AbsMarkerTextView(JSONObject jSONObject, float f) {
        this.mViewPort = f;
        setContent(jSONObject);
        setColor(jSONObject);
        setFontSize(jSONObject);
        setBorderRadius(jSONObject);
        setBorderWidth(jSONObject);
        setBorderColor(jSONObject);
        setBgColor(jSONObject);
        setPadding(jSONObject);
        setDisplay(jSONObject);
        setTextAlign(jSONObject);
    }

    public void destroy() {
    }

    public TextView getTextView(Context context, boolean z) {
        ArrowTextView arrowTextView = new ArrowTextView(context, z);
        arrowTextView.setBgColor(MapResourceUtils.getColor(this.bgColor));
        arrowTextView.setTextPadding(this.padding);
        arrowTextView.setGravity(this.textAlign);
        arrowTextView.setRadius(this.borderRadius);
        arrowTextView.setStrokeWidth(this.borderWidth);
        if (!TextUtils.isEmpty(this.borderColor)) {
            arrowTextView.setStrokeColor(MapResourceUtils.getColor(this.borderColor));
        }
        arrowTextView.setText(this.content);
        arrowTextView.setIncludeFontPadding(false);
        if (!TextUtils.isEmpty(this.color)) {
            arrowTextView.setTextColor(MapResourceUtils.getColor(this.color));
        }
        float f = this.fontSize;
        if (f > 0.0f) {
            arrowTextView.setTextSize(0, WXViewUtils.getRealPxByWidth(f, this.mViewPort));
        }
        return arrowTextView;
    }

    public boolean isAlwaysDisPlay() {
        return !TextUtils.isEmpty(this.display) && this.display.equals("ALWAYS");
    }

    public void setBgColor(JSONObject jSONObject) {
        if (jSONObject.containsKey("bgColor")) {
            this.bgColor = jSONObject.getString("bgColor");
        }
    }

    public void setBorderColor(JSONObject jSONObject) {
        if (jSONObject.containsKey(Constants.Name.BORDER_COLOR)) {
            this.borderColor = jSONObject.getString(Constants.Name.BORDER_COLOR);
        }
    }

    public void setBorderRadius(JSONObject jSONObject) {
        if (jSONObject.containsKey(Constants.Name.BORDER_RADIUS)) {
            this.borderRadius = (int) WXViewUtils.getRealSubPxByWidth(WXUtils.getInt(jSONObject.get(Constants.Name.BORDER_RADIUS)), this.mViewPort);
        }
    }

    public void setBorderWidth(JSONObject jSONObject) {
        if (jSONObject.containsKey(Constants.Name.BORDER_WIDTH)) {
            int i = WXUtils.getInt(jSONObject.get(Constants.Name.BORDER_WIDTH));
            this.borderWidth = i;
            this.padding = (int) WXViewUtils.getRealSubPxByWidth(i, this.mViewPort);
        }
    }

    public void setColor(JSONObject jSONObject) {
        if (jSONObject.containsKey("color")) {
            this.color = jSONObject.getString("color");
        }
    }

    public void setContent(JSONObject jSONObject) {
        if (jSONObject.containsKey("content")) {
            this.content = jSONObject.getString("content");
        }
    }

    public void setDisplay(JSONObject jSONObject) {
        if (jSONObject.containsKey("display")) {
            this.display = jSONObject.getString("display");
        }
    }

    public void setFontSize(JSONObject jSONObject) {
        if (jSONObject.containsKey(Constants.Name.FONT_SIZE)) {
            this.fontSize = WXUtils.getFloat(jSONObject.get(Constants.Name.FONT_SIZE), Float.valueOf(10.0f)).floatValue();
        }
    }

    public void setPadding(JSONObject jSONObject) {
        if (jSONObject.containsKey("padding")) {
            int intValue = jSONObject.getIntValue("padding");
            this.padding = intValue;
            this.padding = (int) WXViewUtils.getRealSubPxByWidth(intValue, this.mViewPort);
        }
    }

    public void setTextAlign(JSONObject jSONObject) {
        if (jSONObject.containsKey(Constants.Name.TEXT_ALIGN)) {
            this.textAlign = 3;
            String string = jSONObject.getString(Constants.Name.TEXT_ALIGN);
            byte b = -1;
            int iHashCode = string.hashCode();
            if (iHashCode != -1364013995) {
                if (iHashCode == 108511772 && string.equals("right")) {
                    b = 0;
                }
            } else if (string.equals("center")) {
                b = 1;
            }
            if (b == 0) {
                this.textAlign = 5;
            } else {
                if (b != 1) {
                    return;
                }
                this.textAlign = 17;
            }
        }
    }

    public void update(JSONObject jSONObject) {
        setContent(jSONObject);
        setColor(jSONObject);
        setFontSize(jSONObject);
        setBorderRadius(jSONObject);
        setBorderWidth(jSONObject);
        setBorderColor(jSONObject);
        setBgColor(jSONObject);
        setPadding(jSONObject);
        setDisplay(jSONObject);
        setTextAlign(jSONObject);
    }
}
