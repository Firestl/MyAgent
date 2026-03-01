package io.dcloud.feature.weex_media;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.GraphicSize;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.weex_media.option.EnumPlayStrategy;
import io.dcloud.media.weex.weex_video.ijkplayer.OnPlayerChangedListener;
import io.dcloud.media.weex.weex_video.ijkplayer.media.AssetsDataSourceProvider;
import io.dcloud.media.weex.weex_video.ijkplayer.media.IjkPlayerView;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class VideoPlayerView extends FrameLayout implements IMediaPlayer.OnInfoListener, OnPlayerChangedListener, IMediaPlayer.OnBufferingUpdateListener {
    public boolean autoplay;
    public float duration;
    public GraphicSize fullScreenSize;
    public float initialTime;
    public boolean isCreate;
    public boolean isEnableDanmu;
    public boolean isEnableDanmuBtn;
    public boolean isFinishLayout;
    public boolean loop;
    public VideoComponent mComponent;
    public Context mContext;
    public String mHeader;
    public IjkPlayerView mPlayerView;
    public String mSrc;
    public GraphicSize originalSize;
    public String poster;
    public int seek;
    public FrameLayout subViewContainer;
    public String willBeSetSrc;

    public VideoPlayerView(Context context, VideoComponent videoComponent) {
        super(context);
        this.mSrc = "";
        this.mHeader = "";
        this.willBeSetSrc = "";
        this.autoplay = false;
        this.loop = false;
        this.poster = "";
        this.duration = -1.0f;
        this.initialTime = 0.0f;
        this.isFinishLayout = false;
        this.seek = 0;
        this.isCreate = false;
        this.isEnableDanmu = false;
        this.isEnableDanmuBtn = false;
        this.originalSize = null;
        this.fullScreenSize = null;
        this.mContext = context;
        this.mComponent = videoComponent;
        FrameLayout frameLayout = new FrameLayout(context);
        this.subViewContainer = frameLayout;
        addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        createVideoView();
        this.subViewContainer.bringToFront();
        if (videoComponent.getInstance().isFrameViewShow()) {
            this.mPlayerView.setVideoVisibility();
        } else {
            videoComponent.getInstance().addFrameViewEventListener(new WXSDKInstance.FrameViewEventListener() { // from class: io.dcloud.feature.weex_media.VideoPlayerView.1
                @Override // com.taobao.weex.WXSDKInstance.FrameViewEventListener
                public void onShowAnimationEnd() {
                    if (VideoPlayerView.this.mPlayerView != null) {
                        VideoPlayerView.this.mPlayerView.setVideoVisibility();
                    }
                    if (VideoPlayerView.this.mComponent == null || VideoPlayerView.this.mComponent.getInstance() == null) {
                        return;
                    }
                    VideoPlayerView.this.mComponent.getInstance().removeFrameViewEventListener(this);
                }
            });
        }
    }

    private void execCallBack(String str, Map<String, Object> map) {
        if (this.mComponent.getEvents().contains(str)) {
            this.mComponent.fireEvent(str, map);
        }
    }

    public void createVideoView() {
        if (this.isCreate) {
            return;
        }
        this.isCreate = true;
        IjkPlayerView ijkPlayerView = new IjkPlayerView(this.mContext, null, this);
        this.mPlayerView = ijkPlayerView;
        ijkPlayerView.setComponent(this.mComponent);
        addView(this.mPlayerView, new FrameLayout.LayoutParams(-1, -1));
        this.mPlayerView.init().setPlayerRootView(this);
        this.mPlayerView.setOnInfoListener(this);
        this.mPlayerView.setOnPlayerChangedListener(this);
        this.mPlayerView.setOnBufferingUpdateListener(this);
        if (this.fullScreenSize == null) {
            View viewFindViewById = ((Activity) this.mComponent.getInstance().getContext()).getWindow().getDecorView().findViewById(android.R.id.content);
            this.fullScreenSize = new GraphicSize(viewFindViewById.getWidth(), viewFindViewById.getHeight());
        }
    }

    public void destory() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.onDestroy();
            this.mPlayerView = null;
        }
    }

    public void exitFullScreen() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.exitFullScreen();
        }
    }

    public int getDirection() {
        VideoComponent videoComponent = this.mComponent;
        if (videoComponent != null && videoComponent.getAttrs().containsKey("direction")) {
            try {
                return Integer.parseInt(String.valueOf(this.mComponent.getAttrs().get("direction")));
            } catch (Exception unused) {
            }
        }
        return Integer.MIN_VALUE;
    }

    public ViewGroup getPlayerView() {
        return this.subViewContainer;
    }

    public boolean isFullScreen() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        return ijkPlayerView != null && ijkPlayerView.isFullscreen();
    }

    public void onActivityResume() {
        if (TextUtils.isEmpty(this.poster)) {
            return;
        }
        Glide.with(this).load(this.poster).into(this.mPlayerView.mPlayerThumb);
    }

    public boolean onBackPress() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        return ijkPlayerView != null && ijkPlayerView.onBackPressed();
    }

    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        Map<String, Object> map = new HashMap<>();
        HashMap map2 = new HashMap();
        map2.put("buffered", Integer.valueOf(i));
        map.put("detail", map2);
        execCallBack(AbsoluteConst.JSON_KEY_PROGRESS, map);
    }

    @Override // io.dcloud.media.weex.weex_video.ijkplayer.OnPlayerChangedListener
    public void onChanged(String str, String str2) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            try {
                map.put("detail", JSON.parse(str2));
            } catch (Exception unused) {
                map.put("detail", str2);
            }
        }
        execCallBack(str, map);
        if (this.mPlayerView == null) {
            return;
        }
        WXComponent child = this.mComponent.getChild(0);
        if (child instanceof VideoInnerViewComponent) {
            if (this.originalSize == null) {
                this.originalSize = child.getLayoutSize();
            }
            if (str.equals("fullscreenchange")) {
                if (!isFullScreen()) {
                    WXBridgeManager.getInstance().setStyleHeight(child.getInstanceId(), child.getRef(), this.originalSize.getHeight());
                    WXBridgeManager.getInstance().setStyleWidth(child.getInstanceId(), child.getRef(), this.originalSize.getWidth());
                    this.mPlayerView.removeView(this.subViewContainer);
                    removeView(this.subViewContainer);
                    addView(this.subViewContainer);
                    this.subViewContainer.bringToFront();
                    return;
                }
                View viewFindViewById = ((Activity) this.mComponent.getInstance().getContext()).getWindow().getDecorView().findViewById(android.R.id.content);
                GraphicSize graphicSize = new GraphicSize(viewFindViewById.getMeasuredWidth(), viewFindViewById.getMeasuredHeight());
                if (!graphicSize.equals(this.fullScreenSize)) {
                    this.fullScreenSize = graphicSize;
                }
                float width = this.fullScreenSize.getWidth();
                float height = this.fullScreenSize.getHeight();
                int i = this.mPlayerView.orientation;
                if (i != 0 ? Math.abs(i) != 90 || width >= height : width <= height) {
                    height = width;
                    width = height;
                }
                WXBridgeManager.getInstance().setStyleHeight(child.getInstanceId(), child.getRef(), width);
                WXBridgeManager.getInstance().setStyleWidth(child.getInstanceId(), child.getRef(), height);
                removeView(this.subViewContainer);
                this.mPlayerView.addView(this.subViewContainer);
                this.subViewContainer.bringToFront();
            }
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (PdrUtil.isEmpty(this.mComponent.getStyles().getBackgroundColor())) {
            setBackgroundColor(-16777216);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
        if (i == 331) {
            execCallBack("error", new HashMap<>());
        } else if (i != 332 && i != 701) {
            switch (i) {
                case 334:
                    execCallBack(Constants.Value.PLAY, new HashMap<>());
                    break;
                case 335:
                    execCallBack("pause", new HashMap<>());
                    break;
                case 336:
                    if (this.loop) {
                        float f = this.initialTime;
                        if (f > 0.0f) {
                            this.mPlayerView.seekTo((int) f);
                        }
                        play();
                    }
                    execCallBack("ended", new HashMap<>());
                    break;
                case 337:
                    Object[] objArr = new Object[1];
                    IjkPlayerView ijkPlayerView = this.mPlayerView;
                    objArr[0] = Integer.valueOf(ijkPlayerView != null ? ijkPlayerView.getCurPosition() : 0);
                    String str = StringUtil.format("{'position':%d}", objArr);
                    HashMap map = new HashMap();
                    map.put("detail", str);
                    execCallBack("seekcomplete", map);
                    break;
            }
        } else {
            execCallBack(IApp.ConfigProperty.CONFIG_WAITING, new HashMap<>());
        }
        return false;
    }

    public void onLayoutFinished() {
        Uri videoFileUri;
        if (this.mPlayerView == null) {
            return;
        }
        AssetsDataSourceProvider assetsDataSourceProvider = null;
        if (!PdrUtil.isNetPath(this.willBeSetSrc)) {
            String path = this.mComponent.getInstance().rewriteUri(Uri.parse(this.willBeSetSrc), "video").getPath();
            this.willBeSetSrc = path;
            if (path != null && !PdrUtil.isDeviceRootDir(path)) {
                try {
                    if (this.mComponent.getIApp() != null) {
                        if (!this.willBeSetSrc.startsWith("/")) {
                            this.willBeSetSrc = "/" + this.willBeSetSrc;
                        }
                        this.willBeSetSrc = this.mComponent.getIApp().checkPrivateDirAndCopy2Temp(this.willBeSetSrc);
                        this.willBeSetSrc = this.mComponent.getInstance().rewriteUri(Uri.parse(this.willBeSetSrc), "video").getPath();
                    } else {
                        if (this.willBeSetSrc.startsWith("/")) {
                            this.willBeSetSrc = this.willBeSetSrc.replaceFirst("/", "");
                        }
                        assetsDataSourceProvider = new AssetsDataSourceProvider(this.mComponent.getContext().getAssets().openFd(this.willBeSetSrc));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (!FileUtil.checkPrivatePath(getContext(), this.willBeSetSrc) && (videoFileUri = FileUtil.getVideoFileUri(getContext(), this.willBeSetSrc)) != null) {
                this.willBeSetSrc = videoFileUri.toString();
            }
        }
        if (TextUtils.isEmpty(this.mSrc)) {
            if (assetsDataSourceProvider == null) {
                this.mPlayerView.setVideoPath(this.willBeSetSrc);
            } else {
                this.mPlayerView.setVideoFileDescriptor(assetsDataSourceProvider);
            }
            this.mPlayerView.clearDanma();
        } else if (!this.mSrc.equalsIgnoreCase(this.willBeSetSrc)) {
            if (assetsDataSourceProvider == null) {
                this.mPlayerView.switchVideoPath(this.willBeSetSrc);
            } else {
                this.mPlayerView.switchVideoFileDescriptor(assetsDataSourceProvider);
            }
            this.mPlayerView.clearDanma();
        }
        this.mSrc = this.willBeSetSrc;
        this.mPlayerView.setDuration((int) this.duration);
        float f = this.initialTime;
        if (f > 0.0f) {
            this.mPlayerView.seekTo((int) f);
        }
        this.mPlayerView.enableDanmaku(this.isEnableDanmu);
        this.mPlayerView.enableDanmuBtn(this.isEnableDanmuBtn);
        this.isFinishLayout = true;
        if (this.autoplay) {
            play();
        }
    }

    public void pause() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.pause();
        }
    }

    public void play() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.start();
        }
    }

    public void requestFullScreen(int i) {
        if (PdrUtil.isEmpty(Integer.valueOf(i))) {
            i = 90;
        }
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.fullScreen(i);
        }
    }

    public void resume() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.onResume();
        }
    }

    public void seek(int i) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            this.seek = i;
            ijkPlayerView.seekTo(i);
        }
    }

    public void sendDanmu(JSONObject jSONObject) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.sendDanmaku(new org.json.JSONObject(jSONObject), true);
        }
    }

    public void sendPlayBackRate(String str) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.playbackRate(str);
        }
    }

    public void setAutoplay(boolean z) {
        this.autoplay = z;
    }

    public void setCodec(String str) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.isUseMediaCodec(str.equals("hardware"));
        }
    }

    public void setControls(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setControls(z);
        }
    }

    public void setDanmuBtn(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            this.isEnableDanmuBtn = z;
            ijkPlayerView.enableDanmuBtn(z);
        }
    }

    public void setDanmuList(JSONArray jSONArray) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setmDanmuList(jSONArray.toString());
        }
    }

    public void setDirection(int i) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setDirection(i);
        }
    }

    public void setDuration(float f) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            float f2 = f * 1000.0f;
            this.duration = f2;
            if (this.isFinishLayout) {
                ijkPlayerView.setDuration((int) f2);
            }
        }
    }

    public void setEnableDanmu(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            this.isEnableDanmu = z;
            ijkPlayerView.enableDanmaku(z);
        }
    }

    public void setEnablePlayGesture(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setmIsDoubleTapEnable(z);
        }
    }

    public void setEnableProgressGesture(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setIsEnableProgressGesture(z);
        }
    }

    public void setFlowStrategy(EnumPlayStrategy enumPlayStrategy) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setFlowStrategy(enumPlayStrategy);
        }
    }

    public void setFullScreenPageGesture(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setFullScreenPageGesture(z);
        }
    }

    public void setHeader(String str) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setHeader(str);
            if (this.isFinishLayout) {
                onLayoutFinished();
            }
        }
    }

    public void setHttpCacheEnable(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setHttpCacheEnable(z);
        }
    }

    public void setInitialTime(float f) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView == null || f <= 0.0f) {
            return;
        }
        float f2 = f * 1000.0f;
        this.initialTime = f2;
        if (this.isFinishLayout) {
            ijkPlayerView.seekTo((int) f2);
        }
    }

    public void setLockScreen(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setIsShowScreenLockButton(z);
        }
    }

    public void setLoop(boolean z) {
        this.loop = z;
    }

    public void setMuteBtn(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.isMuteBtnShow(z);
        }
    }

    public void setMuted(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setMutePlayer(z);
        }
    }

    public void setObjectFit(String str) {
        IjkPlayerView ijkPlayerView;
        if (TextUtils.isEmpty(str) || (ijkPlayerView = this.mPlayerView) == null) {
            return;
        }
        ijkPlayerView.setScaleType(str);
    }

    public void setPageGesture(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setPageGesture(z);
        }
    }

    public void setPlayBtnPosition(String str) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setPlayBtnPosition(str);
        }
    }

    public void setPlayBtnVisibility(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setPlayBntVisibility(z);
        }
    }

    public void setPoster(String str) {
        if (this.mPlayerView == null || TextUtils.isEmpty(str) || this.poster.equalsIgnoreCase(str)) {
            return;
        }
        Glide.with(this).load(str).into(this.mPlayerView.mPlayerThumb);
        this.poster = str;
    }

    public void setProgress(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setProgressVisibility(z);
        }
    }

    public void setShowCenterPlayBtn(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setCenterPlayBtnVisibility(z);
        }
    }

    public void setShowFullScreenBtn(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setFullscreenBntVisibility(z);
        }
    }

    public void setShowLoading(boolean z) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.setLoadingVisibility(z);
        }
    }

    public void setSrc(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.willBeSetSrc = str;
        if (this.isFinishLayout) {
            onLayoutFinished();
        }
    }

    public void setTitle(String str) {
        IjkPlayerView ijkPlayerView;
        if (PdrUtil.isEmpty(str) || (ijkPlayerView = this.mPlayerView) == null) {
            return;
        }
        ijkPlayerView.setTitle(str);
    }

    public void stop() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.stop();
        }
    }
}
