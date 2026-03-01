package io.dcloud.media.video.ijkplayer;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IVideoPlayer;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.media.video.VideoPlayerMgr;
import io.dcloud.media.video.ijkplayer.media.IjkPlayerView;
import io.dcloud.media.video.ijkplayer.option.EnumPlayStrategy;
import java.util.HashMap;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class VideoPlayerView extends FrameLayout implements IVideoPlayer {
    public JSONObject fullScreenOptions;
    public boolean isAutoPlay;
    public boolean isLoopPlay;
    public HashMap<String, HashMap<String, String>> mCallbacks;
    public String mHeaderInfo;
    public IWebview mIWebview;
    public JSONObject mOptions;
    public IjkPlayerView mPlayerView;
    public String mPosterUrl;
    public String mUrl;
    public int[] rect;
    public boolean videoHandleTouch;

    public VideoPlayerView(Activity activity, IWebview iWebview, JSONObject jSONObject) {
        super(activity);
        this.isAutoPlay = false;
        this.isLoopPlay = false;
        this.fullScreenOptions = null;
        this.videoHandleTouch = false;
        this.mIWebview = iWebview;
        IjkPlayerView ijkPlayerView = new IjkPlayerView(activity, null, this);
        this.mPlayerView = ijkPlayerView;
        addView(ijkPlayerView, new FrameLayout.LayoutParams(-1, -1));
        this.mCallbacks = new HashMap<>();
        this.mPlayerView.init().setPlayerRootView(this);
        this.mPlayerView.setOnInfoListener(new IMediaPlayer.OnInfoListener() { // from class: io.dcloud.media.video.ijkplayer.VideoPlayerView.1
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (VideoPlayerView.this.mIWebview.obtainApp() == null) {
                    return false;
                }
                if (i == 336) {
                    VideoPlayerView.this.statusChanged("ended", "");
                    if (VideoPlayerView.this.isLoopPlay) {
                        int iOptInt = VideoPlayerView.this.mOptions.optInt("initial-time");
                        if (iOptInt > 0) {
                            VideoPlayerView.this.mPlayerView.seekTo(iOptInt * 1000);
                        }
                        VideoPlayerView.this.play();
                    }
                } else if (i == 334) {
                    VideoPlayerView.this.statusChanged(Constants.Value.PLAY, "");
                } else if (i == 335) {
                    VideoPlayerView.this.statusChanged("pause", "");
                } else if (i == 331) {
                    VideoPlayerView.this.statusChanged("error", "");
                } else if (i == 332 || i == 701) {
                    VideoPlayerView.this.statusChanged(IApp.ConfigProperty.CONFIG_WAITING, "");
                } else if (i == 337) {
                    VideoPlayerView videoPlayerView = VideoPlayerView.this;
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(videoPlayerView.mPlayerView != null ? VideoPlayerView.this.mPlayerView.getCurPosition() : 0);
                    videoPlayerView.statusChanged("seekcomplete", StringUtil.format("{'position':%d}", objArr));
                }
                return false;
            }
        });
        this.mPlayerView.setOnPlayerChangedListener(new OnPlayerChangedListener() { // from class: io.dcloud.media.video.ijkplayer.VideoPlayerView.2
            @Override // io.dcloud.media.video.ijkplayer.OnPlayerChangedListener
            public void onChanged(String str, String str2) {
                VideoPlayerView.this.statusChanged(str, str2);
            }
        });
        initOptionsPlayerView(this.mPlayerView, jSONObject);
    }

    private void resetSeek(IjkPlayerView ijkPlayerView) {
        ijkPlayerView.seekTo(this.mOptions.optInt("initial-time") * 1000);
        this.mPlayerView.clearDanma();
        ijkPlayerView.enableDanmaku(this.mOptions.optBoolean("enable-danmu", false));
        ijkPlayerView.enableDanmuBtn(this.mOptions.optBoolean("danmu-btn", false));
        if (this.isAutoPlay) {
            play();
        }
    }

    private void setPoster(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(this.mPosterUrl) || !this.mPosterUrl.equalsIgnoreCase(str)) {
            Glide.with(this).load(str).into(this.mPlayerView.mPlayerThumb);
        }
        this.mPosterUrl = str;
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void addEventListener(String str, String str2, String str3) {
        HashMap<String, String> map = this.mCallbacks.get(str);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(str2, str3);
        this.mCallbacks.put(str, map);
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void close() {
        statusChanged(IApp.ConfigProperty.CONFIG_WAITING, "");
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.stop();
            this.mPlayerView.onDestroy();
            this.mPlayerView = null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void exitFullScreen() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.exitFullScreen();
        }
    }

    public int getDirection() {
        JSONObject jSONObject = this.mOptions;
        if (jSONObject != null && jSONObject.has("direction")) {
            try {
                return Integer.parseInt(this.mOptions.optString("direction"));
            } catch (Exception unused) {
            }
        }
        return Integer.MIN_VALUE;
    }

    public void initOptionsPlayerView(IjkPlayerView ijkPlayerView, JSONObject jSONObject) {
        boolean z;
        Uri videoFileUri;
        this.mOptions = jSONObject;
        String strOptString = jSONObject.optString("src");
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        if (!PdrUtil.isNetPath(strOptString)) {
            strOptString = this.mIWebview.obtainApp().convert2AbsFullPath(this.mIWebview.obtainFullUrl(), strOptString);
            if (strOptString.startsWith("/android_asset")) {
                strOptString = strOptString.replace("/android_asset", "");
            } else if (strOptString.startsWith(AssetUriLoader.ASSET_PATH_SEGMENT)) {
                strOptString = strOptString.replace(AssetUriLoader.ASSET_PATH_SEGMENT, "");
            }
            if (!PdrUtil.isDeviceRootDir(strOptString) && !TextUtils.isEmpty(strOptString)) {
                strOptString = this.mIWebview.obtainApp().convert2AbsFullPath(this.mIWebview.obtainFullUrl(), this.mIWebview.obtainApp().checkPrivateDirAndCopy2Temp(strOptString));
            }
            if (!FileUtil.checkPrivatePath(getContext(), strOptString) && (videoFileUri = FileUtil.getVideoFileUri(getContext(), strOptString)) != null) {
                strOptString = videoFileUri.toString();
            }
        }
        this.isAutoPlay = this.mOptions.optBoolean(Constants.Name.AUTOPLAY, this.isAutoPlay);
        this.isLoopPlay = this.mOptions.optBoolean("loop", this.isLoopPlay);
        setPoster(this.mOptions.optString("poster"));
        if (!this.mOptions.isNull("muted")) {
            ijkPlayerView.setMutePlayer(this.mOptions.optBoolean("muted", false));
        }
        if (this.mOptions.optBoolean("page-gesture", false) || this.mOptions.optBoolean("vslide-gesture", false)) {
            this.videoHandleTouch = true;
            z = true;
        } else {
            z = false;
        }
        ijkPlayerView.setPageGesture(z);
        ijkPlayerView.setIsFullScreenPageGesture(this.mOptions.optBoolean("vslide-gesture-in-fullscreen", true));
        ijkPlayerView.setIsShowScreenLockButton(this.mOptions.optBoolean("show-screen-lock-button", false));
        ijkPlayerView.setProgressVisibility(this.mOptions.optBoolean("show-progress", true));
        ijkPlayerView.setFullscreenBntVisibility(this.mOptions.optBoolean("show-fullscreen-btn", true));
        ijkPlayerView.setPlayBntVisibility(this.mOptions.optBoolean("show-play-btn", true));
        ijkPlayerView.setIsEnableProgressGesture(this.mOptions.optBoolean("enable-progress-gesture", true));
        ijkPlayerView.setmIsDoubleTapEnable(this.mOptions.optBoolean("enable-play-gesture", false));
        ijkPlayerView.setLoadingVisibility(this.mOptions.optBoolean(Constants.Name.SHOW_LOADING, true));
        ijkPlayerView.setDirection(this.mOptions.optInt("direction", -90));
        ijkPlayerView.setmDanmuList(this.mOptions.optString("danmu-list"));
        ijkPlayerView.setScaleType(this.mOptions.optString("objectFit", "contain"));
        ijkPlayerView.setScaleType(this.mOptions.optString("object-fit", "contain"));
        ijkPlayerView.isMuteBtnShow(this.mOptions.optBoolean("show-mute-btn", false));
        if (this.mOptions.has("codec")) {
            ijkPlayerView.isUseMediaCodec(this.mOptions.optString("codec").equals("hardware"));
        }
        if (this.mOptions.has("advanced")) {
            ijkPlayerView.setCustomAdvanced(this.mOptions.optString("advanced"));
        }
        if (this.mOptions.has("httpCache")) {
            ijkPlayerView.setViewHttpCacheOpen(this.mOptions.optBoolean("httpCache", false));
        }
        if (this.mOptions.has("playStrategy")) {
            int iOptInt = this.mOptions.optInt("playStrategy", EnumPlayStrategy.DEFAULT.getFlagVal());
            EnumPlayStrategy enumPlayStrategy = EnumPlayStrategy.PLAY_SMOOTH;
            if (iOptInt == enumPlayStrategy.getFlagVal()) {
                enumPlayStrategy = EnumPlayStrategy.PLAY_SMOOTH;
            } else if (iOptInt == EnumPlayStrategy.START_QUICK.getFlagVal()) {
                enumPlayStrategy = EnumPlayStrategy.START_QUICK;
            } else if (iOptInt == EnumPlayStrategy.M3U8_SMOOTH.getFlagVal()) {
                enumPlayStrategy = EnumPlayStrategy.M3U8_SMOOTH;
            } else if (iOptInt == EnumPlayStrategy.DEFAULT.getFlagVal()) {
                enumPlayStrategy = EnumPlayStrategy.DEFAULT;
            }
            ijkPlayerView.setFlowStrategy(enumPlayStrategy);
        }
        String strOptString2 = jSONObject.optString("header");
        if (TextUtils.isEmpty(this.mUrl)) {
            this.mPlayerView.setVideoPath(strOptString, strOptString2);
            resetSeek(ijkPlayerView);
        } else if (!this.mUrl.equalsIgnoreCase(strOptString) || !this.mHeaderInfo.equalsIgnoreCase(strOptString2)) {
            this.mPlayerView.switchVideoPath(strOptString, strOptString2);
            resetSeek(ijkPlayerView);
        }
        ijkPlayerView.setCenterPlayBntVisibility(this.mOptions.optBoolean("show-center-play-btn", true));
        ijkPlayerView.setControls(this.mOptions.optBoolean("controls", true));
        ijkPlayerView.setDuration(this.mOptions.optInt("duration", -1) * 1000);
        this.mUrl = strOptString;
        this.mHeaderInfo = strOptString2;
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public boolean isFullScreen() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            return ijkPlayerView.isFullscreen();
        }
        return false;
    }

    public boolean isPlaying() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            return ijkPlayerView.isPlaying();
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public boolean isPointInRect(float f, float f2) {
        int[] iArr = this.rect;
        return iArr != null && f > ((float) iArr[0]) && f < ((float) iArr[2]) && f2 > ((float) iArr[1]) && f2 < ((float) iArr[3]);
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public boolean isVideoHandleTouch() {
        return this.videoHandleTouch;
    }

    public boolean onBackPressed() {
        return this.mPlayerView.onBackPressed();
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void pause() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.pause();
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void play() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.start();
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void playbackRate(String str) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.playbackRate(str);
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void release() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.onDestroy();
            this.mPlayerView = null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void requestFullScreen(String str) {
        int i = Integer.parseInt(str);
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.fullScreen(i);
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void resume() {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.onResume();
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void seek(String str) {
        int i = Integer.parseInt(str);
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.seekTo(i * 1000);
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void sendDanmu(JSONObject jSONObject) {
        IjkPlayerView ijkPlayerView = this.mPlayerView;
        if (ijkPlayerView != null) {
            ijkPlayerView.sendDanmaku(jSONObject, true);
        }
    }

    public void setFullScreenOptions(JSONObject jSONObject) {
        this.fullScreenOptions = jSONObject;
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void setOptions(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mPlayerView.hiddenLoaded(true);
            initOptionsPlayerView(this.mPlayerView, jSONObject);
        }
    }

    public void setRect(int[] iArr) {
        this.rect = iArr;
    }

    public void statusChanged(String str, String str2) {
        if (this.mCallbacks.containsKey(str)) {
            HashMap<String, String> map = this.mCallbacks.get(str);
            for (String str3 : map.keySet()) {
                Deprecated_JSUtil.execCallback(PdrUtil.isEmpty(map.get(str3)) ? this.mIWebview : VideoPlayerMgr.getInstance().findWebview(this.mIWebview, map.get(str3)), str3, str2, JSUtil.OK, !TextUtils.isEmpty(str2), true);
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IVideoPlayer
    public void stop() {
        statusChanged(IApp.ConfigProperty.CONFIG_WAITING, "");
        if (this.mPlayerView == null || TextUtils.isEmpty(this.mUrl)) {
            return;
        }
        this.mPlayerView.clearDanma();
        this.mPlayerView.switchVideoPath(this.mUrl, this.mHeaderInfo);
    }
}
