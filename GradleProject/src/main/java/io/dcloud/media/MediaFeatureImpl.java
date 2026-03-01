package io.dcloud.media;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.media.video.VideoPlayerMgr;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class MediaFeatureImpl extends StandardFeature implements IWaiter {
    public void VideoPlayer(IWebview iWebview, JSONArray jSONArray) {
        JSONArray jSONArray2;
        boolean z;
        String string = JSONUtil.getString(jSONArray, 0);
        JSONArray jSONArray3 = JSONUtil.getJSONArray(jSONArray, 1);
        JSONObject jSONObject = JSONUtil.getJSONObject(jSONArray, 2);
        if (jSONArray3 == null || jSONArray3.length() <= 0) {
            JSONArray jSONArray4 = new JSONArray();
            try {
                jSONArray4.put(0, JSONUtil.getString(jSONObject, "left"));
                jSONArray4.put(1, JSONUtil.getString(jSONObject, "top"));
                jSONArray4.put(2, JSONUtil.getString(jSONObject, "width"));
                jSONArray4.put(3, JSONUtil.getString(jSONObject, "height"));
            } catch (JSONException unused) {
            }
            jSONArray2 = jSONArray4;
            z = true;
        } else {
            jSONArray2 = jSONArray3;
            z = false;
        }
        VideoPlayerMgr.getInstance().createVideoPlayer(iWebview, string, jSONArray2, jSONObject, JSONUtil.getString(jSONArray, 3), z);
    }

    public void VideoPlayer_addEventListener(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().addEventListener(iWebview, JSONUtil.getString(jSONArray, 0), JSONUtil.getString(jSONArray, 1), JSONUtil.getString(jSONArray, 2), JSONUtil.getString(jSONArray, 3));
    }

    public void VideoPlayer_close(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().close(iWebview, JSONUtil.getString(jSONArray, 0));
    }

    public void VideoPlayer_exitFullScreen(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().exitFullScreen(JSONUtil.getString(jSONArray, 0));
    }

    public void VideoPlayer_hide(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().hidden(JSONUtil.getString(jSONArray, 0));
    }

    public void VideoPlayer_pause(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().pause(JSONUtil.getString(jSONArray, 0));
    }

    public void VideoPlayer_play(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().play(JSONUtil.getString(jSONArray, 0));
    }

    public void VideoPlayer_playbackRate(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().setPlayBackRate(JSONUtil.getString(jSONArray, 0), JSONUtil.getString(jSONArray, 1));
    }

    public void VideoPlayer_requestFullScreen(IWebview iWebview, JSONArray jSONArray) {
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        if (PdrUtil.isEmpty(string2)) {
            string2 = "-90";
        }
        VideoPlayerMgr.getInstance().requestFullScreen(string, string2);
    }

    public void VideoPlayer_seek(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().seekTo(JSONUtil.getString(jSONArray, 0), JSONUtil.getString(jSONArray, 1));
    }

    public void VideoPlayer_sendDanmu(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().sendDanmu(JSONUtil.getString(jSONArray, 0), JSONUtil.getJSONObject(jSONArray, 1));
    }

    public void VideoPlayer_setOptions(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().setOptions(JSONUtil.getString(jSONArray, 0), JSONUtil.getJSONObject(jSONArray, 1));
    }

    public void VideoPlayer_show(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().show(JSONUtil.getString(jSONArray, 0));
    }

    public void VideoPlayer_stop(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().stop(JSONUtil.getString(jSONArray, 0));
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        super.dispose(str);
        VideoPlayerMgr.getInstance().recovery();
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        if (!"appendToFrameView".equals(str)) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        return VideoPlayerMgr.getInstance().appendVideoPlayer((String) objArr[1], (AdaFrameView) objArr[0]);
    }

    public String getVideoPlayerById(IWebview iWebview, JSONArray jSONArray) {
        return JSUtil.wrapJsVar(VideoPlayerMgr.getInstance().findVideoPlayer(JSONUtil.getString(jSONArray, 0)));
    }

    @Override // io.dcloud.common.DHInterface.StandardFeature, io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        super.init(absMgr, str);
        VideoPlayerMgr.getInstance().initFeature(absMgr);
    }

    public void resize(IWebview iWebview, JSONArray jSONArray) {
        VideoPlayerMgr.getInstance().resize(iWebview, JSONUtil.getString(jSONArray, 0), JSONUtil.getJSONArray(jSONArray, 1));
    }
}
