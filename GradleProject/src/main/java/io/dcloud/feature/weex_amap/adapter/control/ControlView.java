package io.dcloud.feature.weex_amap.adapter.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback;
import io.dcloud.feature.weex.adapter.FrescoLoadUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ControlView {
    public boolean clickable;
    public String iconPath;
    public String id;
    public WXSDKInstance mInstance;
    public ImageView mView;
    public JSONObject position;
    public String ref;
    public FrameLayout rootView;

    public ControlView(WXSDKInstance wXSDKInstance, String str, String str2, JSONObject jSONObject, FrameLayout frameLayout) {
        this.clickable = false;
        if (jSONObject.containsKey(Constant.JSONKEY.ICONPATH) && jSONObject.containsKey("position")) {
            this.mInstance = wXSDKInstance;
            this.ref = str;
            this.rootView = frameLayout;
            this.iconPath = jSONObject.getString(Constant.JSONKEY.ICONPATH);
            this.position = jSONObject.getJSONObject("position");
            this.id = str2;
            if (jSONObject.containsKey("clickable")) {
                this.clickable = WXUtils.getBoolean(jSONObject.get("clickable"), false).booleanValue();
            }
            createView(wXSDKInstance.getContext(), frameLayout);
        }
    }

    private void createView(Context context, FrameLayout frameLayout) {
        this.mView = new ImageView(context);
        loadIcon();
    }

    private void loadIcon() {
        int i;
        int i2;
        int i3;
        JSONObject jSONObject = this.position;
        if (jSONObject != null) {
            int realPxByWidth2 = jSONObject.containsKey("width") ? WXViewUtils.getRealPxByWidth2(WXUtils.parseInt(this.position.get("width")), this.mInstance.getInstanceViewPortWidthWithFloat()) : -2;
            int realPxByWidth22 = this.position.containsKey("height") ? WXViewUtils.getRealPxByWidth2(WXUtils.parseInt(this.position.get("height")), this.mInstance.getInstanceViewPortWidthWithFloat()) : -2;
            i = this.position.containsKey("top") ? WXUtils.parseInt(this.position.get("top")) : 0;
            i = this.position.containsKey("left") ? WXUtils.parseInt(this.position.get("left")) : 0;
            i2 = realPxByWidth2;
            i3 = realPxByWidth22;
        } else {
            i = 0;
            i2 = -2;
            i3 = -2;
        }
        if (this.clickable) {
            this.mView.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.feature.weex_amap.adapter.control.ControlView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HashMap map = new HashMap();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("controlId", MapResourceUtils.getId(ControlView.this.id));
                    map.put("detail", jSONObject2);
                    ControlView.this.mInstance.fireEvent(ControlView.this.ref, Constant.EVENT.BIND_CONTROL_TAP, map);
                }
            });
        } else {
            this.mView.setOnClickListener(null);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i2, i3);
        layoutParams.leftMargin = WXViewUtils.getRealPxByWidth2(i, this.mInstance.getInstanceViewPortWidthWithFloat());
        layoutParams.topMargin = WXViewUtils.getRealPxByWidth2(i, this.mInstance.getInstanceViewPortWidthWithFloat());
        this.rootView.addView(this.mView, layoutParams);
        FrescoLoadUtil.getInstance().loadImageBitmap(this.mInstance.getContext(), this.mInstance.rewriteUri(Uri.parse(this.iconPath), "image").toString(), i2, i3, new BitmapLoadCallback<Bitmap>() { // from class: io.dcloud.feature.weex_amap.adapter.control.ControlView.2
            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onFailure(String str, Throwable th) {
            }

            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onSuccess(String str, Bitmap bitmap) {
                ControlView.this.mView.setImageBitmap(bitmap);
            }
        });
    }

    public void destroy() {
        ImageView imageView = this.mView;
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
    }

    public void update(JSONObject jSONObject) {
        if (jSONObject.containsKey(Constant.JSONKEY.ICONPATH)) {
            this.iconPath = jSONObject.getString(Constant.JSONKEY.ICONPATH);
        }
        if (jSONObject.containsKey("position")) {
            this.position = jSONObject.getJSONObject("position");
        }
        if (jSONObject.containsKey("clickable")) {
            this.clickable = WXUtils.getBoolean(jSONObject.get("clickable"), false).booleanValue();
        }
    }
}
