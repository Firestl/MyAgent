package io.dcloud.media.weex.weex_video.ijkplayer.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class MarqueeTextView extends TextView {
    public boolean isMotion;

    public MarqueeTextView(Context context) {
        super(context);
        this.isMotion = false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.isMotion;
    }

    public void startMotion() {
        this.isMotion = true;
        requestFocus();
    }

    public void stopMotion() {
        this.isMotion = false;
        clearFocus();
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isMotion = false;
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isMotion = false;
    }
}
