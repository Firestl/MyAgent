package io.dcloud.media.video;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.media.video.ijkplayer.VideoPlayerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DHVideoFrameItem extends AdaFrameItem implements ISysEventListener {
    public ViewGroup.LayoutParams _vlps;
    public boolean isRegisterResize;
    public IWebview mAppendWebview;
    public IWebview mContenterView;
    public IWebview mIWebview;
    public String mId;
    public VideoPlayerView mPlayerView;
    public String position;
    public JSONArray rect;
    public long resumeTime;
    public JSONObject styles;
    public String userId;

    public DHVideoFrameItem(Context context, String str, IWebview iWebview, JSONArray jSONArray, JSONObject jSONObject, String str2) {
        super(context);
        this.resumeTime = 0L;
        this.mAppendWebview = null;
        this.position = AbsoluteConst.JSON_VALUE_POSITION_STATIC;
        this.mContenterView = null;
        this.rect = null;
        this.styles = null;
        this.isRegisterResize = false;
        this.mIWebview = iWebview;
        this.mContenterView = iWebview;
        this.mId = str;
        this.userId = str2;
        this.rect = jSONArray;
        this.styles = jSONObject;
        IApp iAppObtainApp = iWebview.obtainFrameView().obtainApp();
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onPause);
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onResume);
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onStop);
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
        VideoPlayerView videoPlayerView = new VideoPlayerView(this.mIWebview.getActivity(), iWebview, this.styles);
        this.mPlayerView = videoPlayerView;
        setMainView(videoPlayerView);
        initFrame(this.rect);
        this.position = this.styles.optString("position");
    }

    private void initFrame(JSONArray jSONArray) {
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) this.mContenterView.obtainFrameView()).obtainFrameOptions();
        float scale = this.mContenterView.getScale();
        String string = JSONUtil.getString(jSONArray, 2);
        int i = viewOptionsObtainFrameOptions.width;
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(string, i, i, scale);
        String string2 = JSONUtil.getString(jSONArray, 3);
        int i2 = viewOptionsObtainFrameOptions.height;
        int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(string2, i2, i2, scale);
        int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONArray, 0), viewOptionsObtainFrameOptions.width, 0, scale);
        int iConvertToScreenInt4 = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONArray, 1), viewOptionsObtainFrameOptions.height, 0, scale);
        updateViewRect((AdaFrameItem) this.mContenterView.obtainFrameView(), new int[]{iConvertToScreenInt3, iConvertToScreenInt4, iConvertToScreenInt, iConvertToScreenInt2}, new int[]{viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height});
        this._vlps = AdaFrameItem.LayoutParamsUtil.createLayoutParams(iConvertToScreenInt3, iConvertToScreenInt4, iConvertToScreenInt, iConvertToScreenInt2);
        this.mPlayerView.setRect(new int[]{iConvertToScreenInt3, iConvertToScreenInt4, iConvertToScreenInt + iConvertToScreenInt3, iConvertToScreenInt2 + iConvertToScreenInt4});
    }

    public void addEventListener(String str, String str2, String str3) {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.addEventListener(str, str2, str3);
        }
    }

    public void appendToFrame(IFrameView iFrameView) {
        View viewObtainMainView = obtainMainView();
        if (viewObtainMainView != null && viewObtainMainView.getParent() != null) {
            removeFrameItem();
        }
        this.mContenterView = iFrameView.obtainWebView();
        initFrame(this.rect);
        if (this.position.equals(AbsoluteConst.JSON_VALUE_POSITION_STATIC)) {
            this.mContenterView.addFrameItem(this, this._vlps);
        } else if (this.position.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
            this.mContenterView.obtainFrameView().addFrameItem(this, this._vlps);
        } else {
            this.mContenterView.addFrameItem(this, this._vlps);
        }
    }

    public void close() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.close();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        release();
        VideoPlayerMgr.getInstance().rmovePlayer(this.mId);
    }

    public void exitFullScreen() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.exitFullScreen();
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public String getmId() {
        return this.mId;
    }

    public void hidden() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.setVisibility(4);
        }
    }

    public boolean isFullScreen() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            return videoPlayerView.isFullScreen();
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        VideoPlayerView videoPlayerView;
        if (sysEventType == ISysEventListener.SysEventType.onPause) {
            if (System.currentTimeMillis() - this.resumeTime > 100) {
                pause();
                this.resumeTime = 0L;
            }
            return true;
        }
        if (sysEventType == ISysEventListener.SysEventType.onResume) {
            if (this.resumeTime > 0) {
                resume();
            }
            this.resumeTime = System.currentTimeMillis();
            return true;
        }
        if (sysEventType == ISysEventListener.SysEventType.onStop) {
            release();
            return true;
        }
        if (sysEventType == ISysEventListener.SysEventType.onKeyUp && ((Integer) ((Object[]) obj)[0]).intValue() == 4 && isFullScreen() && (videoPlayerView = this.mPlayerView) != null) {
            return videoPlayerView.onBackPressed();
        }
        return false;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onResize() {
        if (this.isRegisterResize) {
            return;
        }
        super.onResize();
        initFrame(this.rect);
        obtainMainView().setLayoutParams(this._vlps);
    }

    public void pause() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.pause();
        }
    }

    public void play() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.play();
        }
    }

    public void playbackRate(String str) {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.playbackRate(str);
        }
    }

    public void release() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.release();
            this.mPlayerView = null;
        }
    }

    public void removeFrameItem() {
        if (this.position.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
            this.mContenterView.obtainFrameView().removeFrameItem(this);
        } else {
            this.mContenterView.removeFrameItem(this);
        }
    }

    public void requestFullScreen(String str) {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.requestFullScreen(str);
        }
    }

    public void resize(JSONArray jSONArray) {
        initFrame(jSONArray);
        obtainMainView().setLayoutParams(this._vlps);
        this.isRegisterResize = true;
    }

    public void resume() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.resume();
        }
    }

    public void seek(String str) {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.seek(str);
        }
    }

    public void sendDanmu(JSONObject jSONObject) {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.sendDanmu(jSONObject);
        }
    }

    public void sendPlayBackRate(String str) {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.playbackRate(str);
        }
    }

    public void setOptions(JSONObject jSONObject) {
        if (this.mPlayerView != null) {
            this.styles = JSONUtil.combinJSONObject(this.styles, jSONObject);
            if (this.mPlayerView.isFullScreen()) {
                try {
                    JSONObject jSONObject2 = new JSONObject(this.styles.toString());
                    jSONObject2.remove("top");
                    jSONObject2.remove("left");
                    jSONObject2.remove("width");
                    jSONObject2.remove("height");
                    jSONObject2.remove("position");
                    this.mPlayerView.setOptions(jSONObject2);
                    return;
                } catch (JSONException unused) {
                    return;
                }
            }
            if (jSONObject.has("top") || jSONObject.has("left") || jSONObject.has("width") || jSONObject.has("height") || jSONObject.has("position")) {
                try {
                    this.rect.put(0, JSONUtil.getString(jSONObject, "left"));
                    this.rect.put(1, JSONUtil.getString(jSONObject, "top"));
                    this.rect.put(2, JSONUtil.getString(jSONObject, "width"));
                    this.rect.put(3, JSONUtil.getString(jSONObject, "height"));
                } catch (JSONException unused2) {
                }
                String string = JSONUtil.getString(jSONObject, "position");
                initFrame(this.rect);
                if (jSONObject.has("position")) {
                    if (string.equals(this.position)) {
                        obtainMainView().setLayoutParams(this._vlps);
                    } else if (AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(this.position)) {
                        this.mContenterView.obtainFrameView().removeFrameItem(this);
                        this.mContenterView.addFrameItem(this, this._vlps);
                    } else if (AbsoluteConst.JSON_VALUE_POSITION_STATIC.equals(this.position)) {
                        this.mContenterView.removeFrameItem(this);
                        this.mContenterView.obtainFrameView().addFrameItem(this, this._vlps);
                    }
                    this.position = string;
                } else {
                    obtainMainView().setLayoutParams(this._vlps);
                }
            }
            this.mPlayerView.setOptions(this.styles);
        }
    }

    public void show() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.setVisibility(0);
        }
    }

    public void stop() {
        VideoPlayerView videoPlayerView = this.mPlayerView;
        if (videoPlayerView != null) {
            videoPlayerView.stop();
        }
    }
}
