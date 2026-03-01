package com.supwisdom.superapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/* JADX INFO: loaded from: classes2.dex */
public class FullVideoView extends VideoView {
    public FullVideoView(Context context) {
        super(context);
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(VideoView.getDefaultSize(0, i), VideoView.getDefaultSize(0, i2));
    }

    public FullVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FullVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
