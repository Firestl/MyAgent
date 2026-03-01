package io.dcloud.feature.weex_media;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.dom.WXAttr;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXBaseRefreshLayout;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.weex.WeexInstanceMgr;
import io.dcloud.feature.weex_media.option.EnumPlayStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class VideoComponent extends WXVContainer<VideoPlayerView> implements ISysEventListener {
    public WXAttr attrs;
    public AtomicBoolean isLoad;
    public IApp mApp;
    public Map<String, Object> params;

    public VideoComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.isLoad = new AtomicBoolean(false);
        this.attrs = basicComponentData.getAttrs();
        if (basicComponentData.getStyles().containsKey(Constants.Name.FLEX)) {
            return;
        }
        setContentBoxMeasurement(new ContentBoxMeasurement() { // from class: io.dcloud.feature.weex_media.VideoComponent.1
            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutAfter(float f, float f2) {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutBefore() {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void measureInternal(float f, float f2, int i, int i2) {
                if (CSSConstants.isUndefined(f)) {
                    this.mMeasureWidth = WXViewUtils.getRealPxByWidth(300.0f, VideoComponent.this.getInstance().getInstanceViewPortWidthWithFloat());
                }
                if (CSSConstants.isUndefined(f2)) {
                    this.mMeasureHeight = WXViewUtils.getRealPxByWidth(225.0f, VideoComponent.this.getInstance().getInstanceViewPortWidthWithFloat());
                }
            }
        });
    }

    private Map<String, Object> combinMap(Map<String, Object> map, Map<String, Object> map2) {
        if (map == null && map2 == null) {
            return new HashMap();
        }
        if (map == null) {
            return map2;
        }
        if (map2 == null) {
            return map;
        }
        map.putAll(map2);
        return map;
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void addChild(WXComponent wXComponent, int i) {
        if (wXComponent instanceof VideoInnerViewComponent) {
            super.addChild(wXComponent, i);
        }
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void addEvent(String str) {
        if (str.equals(Constants.Event.CLICK)) {
            return;
        }
        super.addEvent(str);
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void addSubView(View view, int i) {
        if (view == null || getRealView() == null || (view instanceof WXBaseRefreshLayout)) {
            return;
        }
        if (i >= getRealView().getChildCount()) {
            i = -1;
        }
        if (getRealView().indexOfChild(view) == -1) {
            if (i == -1) {
                getRealView().addView(view);
            } else {
                getRealView().addView(view, i);
            }
        }
        view.bringToFront();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent
    public void destroy() {
        super.destroy();
        if (getHostView() != 0) {
            ((VideoPlayerView) getHostView()).destory();
        }
        IApp iApp = this.mApp;
        if (iApp != null) {
            iApp.unregisterSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void exitFullScreen() {
        ((VideoPlayerView) getHostView()).exitFullScreen();
    }

    public IApp getIApp() {
        IWebview iWebviewFindWebview;
        if (this.mApp == null && (iWebviewFindWebview = WeexInstanceMgr.self().findWebview(getInstance())) != null) {
            this.mApp = iWebviewFindWebview.obtainApp();
        }
        return this.mApp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "httpCache")
    public void httpCache(boolean z) {
        ((VideoPlayerView) getHostView()).setHttpCacheEnable(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "showMuteBtn")
    public void isShowMuteBtn(boolean z) {
        ((VideoPlayerView) getHostView()).setMuteBtn(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityPause() {
        super.onActivityPause();
        if (getHostView() != 0) {
            ((VideoPlayerView) getHostView()).pause();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityResume() {
        super.onActivityResume();
        if (getHostView() != 0) {
            ((VideoPlayerView) getHostView()).onActivityResume();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType == ISysEventListener.SysEventType.onKeyUp && ((Integer) ((Object[]) obj)[0]).intValue() == 4 && ((VideoPlayerView) getHostView()).isFullScreen() && getHostView() != 0) {
            return ((VideoPlayerView) getHostView()).onBackPress();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void pause() {
        ((VideoPlayerView) getHostView()).pause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void play() {
        ((VideoPlayerView) getHostView()).play();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void playbackRate(float f) {
        if (PdrUtil.isEmpty(Float.valueOf(f))) {
            return;
        }
        ((VideoPlayerView) getHostView()).sendPlayBackRate(String.valueOf(f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void requestFullScreen(JSONObject jSONObject) {
        int iIntValue;
        try {
            iIntValue = jSONObject.getInteger("direction").intValue();
        } catch (Exception unused) {
            iIntValue = -90;
        }
        ((VideoPlayerView) getHostView()).requestFullScreen(iIntValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void seek(int i) {
        if (PdrUtil.isEmpty(Integer.valueOf(i))) {
            return;
        }
        ((VideoPlayerView) getHostView()).seek(i * 1000);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void sendDanmu(JSONObject jSONObject) {
        if (jSONObject != null) {
            ((VideoPlayerView) getHostView()).sendDanmu(jSONObject);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = Constants.Name.AUTOPLAY)
    public void setAutoPlay(boolean z) {
        if (PdrUtil.isEmpty(Boolean.valueOf(z))) {
            return;
        }
        ((VideoPlayerView) getHostView()).setAutoplay(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "codec")
    public void setCodeMode(String str) {
        ((VideoPlayerView) getHostView()).setCodec(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "danmuList")
    public void setDanmuList(JSONArray jSONArray) {
        ((VideoPlayerView) getHostView()).setDanmuList(jSONArray);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "direction")
    public void setDirection(int i) {
        ((VideoPlayerView) getHostView()).setDirection(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "duration")
    public void setDuration(float f) {
        ((VideoPlayerView) getHostView()).setDuration(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "objectFit")
    public void setFit(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        ((VideoPlayerView) getHostView()).setObjectFit(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "header")
    public void setHeader(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        ((VideoPlayerView) getHostView()).setHeader(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "initialTime")
    public void setInitTime(float f) {
        ((VideoPlayerView) getHostView()).setInitialTime(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "loop")
    public void setLoop(boolean z) {
        ((VideoPlayerView) getHostView()).setLoop(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "muted")
    public void setMute(boolean z) {
        ((VideoPlayerView) getHostView()).setMuted(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "playBtnPosition")
    public void setPlayBtnPosition(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        ((VideoPlayerView) getHostView()).setPlayBtnPosition(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "playStrategy")
    public void setPlayStrategy(int i) {
        EnumPlayStrategy enumPlayStrategy = EnumPlayStrategy.DEFAULT;
        if (i == EnumPlayStrategy.PLAY_SMOOTH.getFlagVal()) {
            enumPlayStrategy = EnumPlayStrategy.PLAY_SMOOTH;
        } else if (i == EnumPlayStrategy.START_QUICK.getFlagVal()) {
            enumPlayStrategy = EnumPlayStrategy.START_QUICK;
        } else if (i == EnumPlayStrategy.M3U8_SMOOTH.getFlagVal()) {
            enumPlayStrategy = EnumPlayStrategy.M3U8_SMOOTH;
        } else if (i == EnumPlayStrategy.DEFAULT.getFlagVal()) {
            enumPlayStrategy = EnumPlayStrategy.DEFAULT;
        }
        ((VideoPlayerView) getHostView()).setFlowStrategy(enumPlayStrategy);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "poster")
    public void setPoster(String str) {
        ((VideoPlayerView) getHostView()).setPoster(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "src")
    public void setSrc(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        ((VideoPlayerView) getHostView()).setSrc(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "title")
    public void setTitle(String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        ((VideoPlayerView) getHostView()).setTitle(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WXComponentProp(name = "showLoading")
    public void showLoading(boolean z) {
        ((VideoPlayerView) getHostView()).setShowLoading(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JSMethod
    public void stop() {
        ((VideoPlayerView) getHostView()).stop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.taobao.weex.ui.component.WXComponent
    public void updateProperties(Map<String, Object> map) {
        if (map.size() > 0 && getHostView() != 0) {
            this.params = combinMap(this.params, map);
            ((VideoPlayerView) getHostView()).setProgress(!this.params.containsKey("showProgress") || Boolean.parseBoolean(this.params.get("showProgress").toString()));
            ((VideoPlayerView) getHostView()).setShowFullScreenBtn(!this.params.containsKey("showFullscreenBtn") || Boolean.parseBoolean(this.params.get("showFullscreenBtn").toString()));
            ((VideoPlayerView) getHostView()).setPlayBtnVisibility(!this.params.containsKey("showPlayBtn") || Boolean.parseBoolean(this.params.get("showPlayBtn").toString()));
            ((VideoPlayerView) getHostView()).setEnableProgressGesture(!this.params.containsKey("enableProgressGesture") || Boolean.parseBoolean(this.params.get("enableProgressGesture").toString()));
            if (map.containsKey("src")) {
                ((VideoPlayerView) getHostView()).setSrc((String) map.get("src"));
            }
            ((VideoPlayerView) getHostView()).setShowCenterPlayBtn(!this.params.containsKey("showCenterPlayBtn") || Boolean.parseBoolean(this.params.get("showCenterPlayBtn").toString()));
            ((VideoPlayerView) getHostView()).setControls(!this.params.containsKey("controls") || Boolean.parseBoolean(this.params.get("controls").toString()));
            if (this.params.containsKey("vslideGestureInFullscreen")) {
                ((VideoPlayerView) getHostView()).setFullScreenPageGesture(Boolean.parseBoolean(this.params.get("vslideGestureInFullscreen").toString()));
            }
            if (this.params.containsKey("vslideGesture")) {
                ((VideoPlayerView) getHostView()).setPageGesture(Boolean.parseBoolean(this.params.get("vslideGesture").toString()));
            }
            if (this.params.containsKey("showScreenLockButton")) {
                ((VideoPlayerView) getHostView()).setLockScreen(Boolean.parseBoolean(this.params.get("showScreenLockButton").toString()));
            }
            if (this.params.containsKey("enablePlayGesture")) {
                ((VideoPlayerView) getHostView()).setEnablePlayGesture(Boolean.parseBoolean(this.params.get("enablePlayGesture").toString()));
            }
        }
        super.updateProperties(map);
        if (map.size() <= 0 || !map.containsKey("src")) {
            return;
        }
        ((VideoPlayerView) getHostView()).onLayoutFinished();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent
    public ViewGroup getRealView() {
        if (getHostView() != 0) {
            return ((VideoPlayerView) getHostView()).getPlayerView();
        }
        return null;
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public VideoPlayerView initComponentHostView(Context context) {
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(getInstance());
        if (iWebviewFindWebview != null) {
            IApp iAppObtainApp = iWebviewFindWebview.obtainApp();
            this.mApp = iAppObtainApp;
            iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
        }
        return new VideoPlayerView(getContext(), this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.taobao.weex.ui.component.WXComponent
    public void onHostViewInitialized(VideoPlayerView videoPlayerView) {
        super.onHostViewInitialized(videoPlayerView);
        WXAttr wXAttr = this.attrs;
        if (wXAttr == null || wXAttr.size() <= 0) {
            return;
        }
        ((VideoPlayerView) getHostView()).setEnableDanmu(this.attrs.containsKey("enableDanmu") && Boolean.parseBoolean(this.attrs.get("enableDanmu").toString()));
        ((VideoPlayerView) getHostView()).setDanmuBtn(this.attrs.containsKey("danmuBtn") && Boolean.parseBoolean(this.attrs.get("danmuBtn").toString()));
    }
}
