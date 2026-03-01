package io.dcloud.media.weex.weex_video.ijkplayer.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.MediaController;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidMediaController extends MediaController implements IMediaController {
    public ArrayList<View> mShowOnceArray;

    public AndroidMediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mShowOnceArray = new ArrayList<>();
        initView(context);
    }

    private void initView(Context context) {
    }

    @Override // android.widget.MediaController, io.dcloud.media.weex.weex_video.ijkplayer.media.IMediaController
    public void hide() {
        super.hide();
        Iterator<View> it = this.mShowOnceArray.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(8);
        }
        this.mShowOnceArray.clear();
    }

    @Override // android.widget.MediaController, io.dcloud.media.weex.weex_video.ijkplayer.media.IMediaController
    public void show() {
        super.show();
    }

    @Override // io.dcloud.media.weex.weex_video.ijkplayer.media.IMediaController
    public void showOnce(View view) {
        this.mShowOnceArray.add(view);
        view.setVisibility(0);
        show();
    }

    public AndroidMediaController(Context context, boolean z) {
        super(context, z);
        this.mShowOnceArray = new ArrayList<>();
        initView(context);
    }

    public AndroidMediaController(Context context) {
        super(context);
        this.mShowOnceArray = new ArrayList<>();
        initView(context);
    }
}
