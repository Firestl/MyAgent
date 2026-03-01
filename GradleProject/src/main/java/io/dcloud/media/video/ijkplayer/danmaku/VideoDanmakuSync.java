package io.dcloud.media.video.ijkplayer.danmaku;

import android.util.Log;
import io.dcloud.media.video.ijkplayer.media.IjkPlayerView;
import master.flame.danmaku.danmaku.model.AbsDanmakuSync;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class VideoDanmakuSync extends AbsDanmakuSync {
    public final IjkPlayerView mPlayerView;

    public VideoDanmakuSync(IjkPlayerView ijkPlayerView) {
        this.mPlayerView = ijkPlayerView;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDanmakuSync
    public int getSyncState() {
        if (this.mPlayerView.isPlaying()) {
            Log.e("VideoDanmakuSync", "SYNC_STATE_PLAYING");
            return 2;
        }
        Log.e("VideoDanmakuSync", "SYNC_STATE_HALT");
        return 1;
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDanmakuSync
    public long getUptimeMillis() {
        if (this.mPlayerView == null) {
            return -1L;
        }
        Log.i("VideoDanmakuSync", "" + this.mPlayerView.getCurPosition());
        return this.mPlayerView.getCurPosition();
    }

    @Override // master.flame.danmaku.danmaku.model.AbsDanmakuSync
    public boolean isSyncPlayingState() {
        return true;
    }
}
