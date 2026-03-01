package io.dcloud.feature.weex_livepusher;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.common.util.DialogUtil;
import io.dcloud.common.util.PdrUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PusherComponent extends WXComponent<TCPusherView> {
    public WXAttr attr;
    public boolean isInit;

    public PusherComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.isInit = false;
        this.attr = basicComponentData.getAttrs();
        if (basicComponentData.getStyles().containsKey(Constants.Name.FLEX)) {
            return;
        }
        setContentBoxMeasurement(new ContentBoxMeasurement() { // from class: io.dcloud.feature.weex_livepusher.PusherComponent.1
            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutAfter(float f, float f2) {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutBefore() {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void measureInternal(float f, float f2, int i, int i2) {
                if (CSSConstants.isUndefined(f)) {
                    this.mMeasureWidth = WXViewUtils.getRealPxByWidth(300.0f, PusherComponent.this.getInstance().getInstanceViewPortWidthWithFloat());
                }
                if (CSSConstants.isUndefined(f2)) {
                    this.mMeasureHeight = WXViewUtils.getRealPxByWidth(225.0f, PusherComponent.this.getInstance().getInstanceViewPortWidthWithFloat());
                }
            }
        });
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void destroy() {
        super.destroy();
        getHostView().stopPusher(null);
        getHostView().destory();
    }

    @WXComponentProp(name = "muted")
    public void isMute(boolean z) {
        getHostView().setMute(z);
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityPause() {
        super.onActivityPause();
        if (getHostView() != null) {
            getHostView().pause(null);
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityResume() {
        super.onActivityResume();
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void onFinishLayout() {
        super.onFinishLayout();
    }

    @JSMethod
    public void pause(JSCallback jSCallback) {
        getHostView().pause(jSCallback);
    }

    @JSMethod
    public void pauseBGM(JSCallback jSCallback) {
        getHostView().pauseBGM(jSCallback);
    }

    @JSMethod
    public void playBGM(JSONObject jSONObject, JSCallback jSCallback) {
        getHostView().playBGM(jSONObject.getString("url"), jSCallback);
    }

    @JSMethod
    public void resume(JSCallback jSCallback) {
        getHostView().resume(jSCallback);
    }

    @JSMethod
    public void resumeBGM(JSCallback jSCallback) {
        getHostView().resumeBGM(jSCallback);
    }

    @WXComponentProp(name = "aspect")
    public void setAspect(String str) {
    }

    @WXComponentProp(name = "autoFocus")
    public void setAutoFocus(boolean z) {
        getHostView().autoFocus(z);
    }

    @WXComponentProp(name = "autopush")
    public void setAutoPusher(boolean z) {
        getHostView().setAutoPush(z);
    }

    @JSMethod
    public void setBGMVolume(JSONObject jSONObject, JSCallback jSCallback) {
        int iIntValue = jSONObject.getInteger("volume").intValue();
        if (PdrUtil.isEmpty(Integer.valueOf(iIntValue))) {
            return;
        }
        getHostView().setBGNVolume(iIntValue, jSCallback);
    }

    @WXComponentProp(name = "backgroundMute")
    public void setBackgroundMute(boolean z) {
        getHostView().setBGMute(z);
    }

    @WXComponentProp(name = "beauty")
    public void setBeauty(int i) {
        getHostView().setBeauty(i);
    }

    @WXComponentProp(name = "enableCamera")
    public void setEnableCamera(boolean z) {
        getHostView().enableCamera(z);
    }

    @WXComponentProp(name = "maxBitrate")
    public void setMaxBitrate(int i) {
        getHostView().setMaxBitrate(i);
    }

    @WXComponentProp(name = "minBitrate")
    public void setMinBitrate(int i) {
        getHostView().setMinBitrate(i);
    }

    @WXComponentProp(name = "mode")
    public void setMode(String str) {
        getHostView().setMode(str);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public boolean setProperty(String str, Object obj) {
        if (getHostView() == null) {
            return true;
        }
        return super.setProperty(str, obj);
    }

    @WXComponentProp(name = "url")
    public void setSrc(String str) {
        getHostView().setSrc(str);
    }

    @WXComponentProp(name = "waitingImage")
    public void setWaitingImage(String str) {
        getHostView().setWaintImage(str);
    }

    @WXComponentProp(name = "whiteness")
    public void setWhiteness(int i) {
        getHostView().setWhite(i);
    }

    @WXComponentProp(name = "zoom")
    public void setZoom(boolean z) {
        getHostView().setZoom(z);
    }

    @WXComponentProp(name = Constants.Name.ORIENTATION)
    public void setorientation(String str) {
        getHostView().setOritation(str);
    }

    @JSMethod
    public void snapshot(JSCallback jSCallback) {
        getHostView().snapShot(jSCallback);
    }

    @JSMethod
    public void start(JSCallback jSCallback) throws Throwable {
        getHostView().start(jSCallback);
    }

    @JSMethod
    public void startPreview(JSCallback jSCallback) throws Throwable {
        getHostView().preview(jSCallback);
    }

    @JSMethod
    public void stop(JSCallback jSCallback) {
        getHostView().stopPusher(jSCallback);
    }

    @JSMethod
    public void stopBGM(JSCallback jSCallback) {
        getHostView().stopBGM(jSCallback);
    }

    @JSMethod
    public void stopPreview(JSCallback jSCallback) {
        getHostView().stopPreview(jSCallback);
    }

    @JSMethod
    public void switchCamera(JSCallback jSCallback) {
        getHostView().sCamera(jSCallback);
    }

    @JSMethod
    public void toggleTorch(JSCallback jSCallback) {
        getHostView().toggleTorch(jSCallback);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public TCPusherView initComponentHostView(Context context) {
        boolean z = true;
        if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
            if (!this.isInit) {
                this.isInit = true;
                DialogUtil.showDialog((Activity) context, null, context.getString(R.string.dcloud_feature_weex_livepusher_error_tips), new String[]{null});
            }
            return null;
        }
        Object obj = this.attr.get("devicePosition");
        if (obj != null && !obj.equals("front")) {
            z = false;
        }
        return new TCPusherView(context, this, z);
    }

    public PusherComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, int i, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, i, basicComponentData);
        this.isInit = false;
    }
}
