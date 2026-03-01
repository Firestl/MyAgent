package io.dcloud.feature.weex_barcode;

import android.content.Context;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXResourceUtils;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class BarcodeComponent extends WXComponent<BarcodeView> {
    public boolean isAnimationEnd;
    public AtomicBoolean isLoad;

    public BarcodeComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.isLoad = new AtomicBoolean(false);
        this.isAnimationEnd = false;
    }

    @JSMethod
    public void cancel() {
        getHostView().cancelScan();
    }

    @JSMethod
    public void close() {
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void destroy() {
        super.destroy();
        getHostView().closeScan();
        getHostView().onDestory();
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityPause() {
        super.onActivityPause();
        getHostView().onPause();
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityResume() {
        super.onActivityResume();
        getHostView().onResume(true);
    }

    @WXComponentProp(name = "autoDecodeCharset")
    public void setAutoDecodeCharset(boolean z) {
        getHostView().setAutoDecodeCharset(z);
    }

    @WXComponentProp(name = "background")
    public void setBackground(String str) {
        getHostView().setBackground(WXResourceUtils.getColor(str));
    }

    @WXComponentProp(name = "filters")
    public void setFilters(JSONArray jSONArray) {
        getHostView().initDecodeFormats(jSONArray);
    }

    @JSMethod
    public void setFlash(boolean z) {
        if (PdrUtil.isEmpty(Boolean.valueOf(z))) {
            return;
        }
        getHostView().setFlash(z);
    }

    @WXComponentProp(name = "frameColor")
    public void setFrameColor(String str) {
        getHostView().setFrameColor(WXResourceUtils.getColor(str));
    }

    @WXComponentProp(name = "scanbarColor")
    public void setScanbarColor(String str) {
        getHostView().setScanBarColor(WXResourceUtils.getColor(str));
    }

    @WXComponentProp(name = "autostart")
    public void setSutoStart(final boolean z) {
        if (!this.isAnimationEnd) {
            getInstance().addFrameViewEventListener(new WXSDKInstance.FrameViewEventListener() { // from class: io.dcloud.feature.weex_barcode.BarcodeComponent.1
                @Override // com.taobao.weex.WXSDKInstance.FrameViewEventListener
                public void onShowAnimationEnd() {
                    BarcodeComponent.this.isAnimationEnd = true;
                    if (z) {
                        BarcodeComponent.this.start(null);
                    }
                    BarcodeComponent.this.getInstance().removeFrameViewEventListener(this);
                }
            });
        } else if (z) {
            start(null);
        }
    }

    @JSMethod
    public void start(JSONObject jSONObject) {
        if (jSONObject != null) {
            getHostView().setConserve(jSONObject.containsKey("conserve") ? jSONObject.getBoolean("conserve").booleanValue() : false);
            getHostView().setFilename(PdrUtil.getDefaultPrivateDocPath(jSONObject.getString(AbsoluteConst.JSON_KEY_FILENAME), "png"));
            getHostView().setVibrate(jSONObject.containsKey("vibrate") ? jSONObject.getBoolean("vibrate").booleanValue() : true);
            getHostView().setPlayBeep(!jSONObject.containsKey(RemoteMessageConst.Notification.SOUND) || jSONObject.getString(RemoteMessageConst.Notification.SOUND).equals("default"));
        }
        getHostView().start();
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void updateProperties(Map<String, Object> map) {
        super.updateProperties(map);
        if (getAttrs().containsKey("background")) {
            return;
        }
        getHostView().setBackgroundColor(-16777216);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public BarcodeView initComponentHostView(Context context) {
        return new BarcodeView(context, this, getInstance());
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void setHostLayoutParams(BarcodeView barcodeView, int i, int i2, int i3, int i4, int i5, int i6) {
        super.setHostLayoutParams(barcodeView, i, i2, i3, i4, i5, i6);
        if (this.isLoad.get()) {
            getHostView().updateStyles(i, i2);
        } else {
            this.isLoad.set(true);
            getHostView().initBarcodeView(i, i2);
        }
    }

    public BarcodeComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, int i, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, i, basicComponentData);
        this.isLoad = new AtomicBoolean(false);
        this.isAnimationEnd = false;
    }
}
