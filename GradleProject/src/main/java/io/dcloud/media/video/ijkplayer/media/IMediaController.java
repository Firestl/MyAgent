package io.dcloud.media.video.ijkplayer.media;

import android.view.View;
import android.widget.MediaController;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaController {
    void hide();

    boolean isShowing();

    void setAnchorView(View view);

    void setEnabled(boolean z);

    void setMediaPlayer(MediaController.MediaPlayerControl mediaPlayerControl);

    void show();

    void show(int i);

    void showOnce(View view);
}
