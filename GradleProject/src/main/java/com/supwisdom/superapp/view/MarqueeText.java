package com.supwisdom.superapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public class MarqueeText extends TextView {
    public MarqueeText(Context context) {
        super(context);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public MarqueeText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MarqueeText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MarqueeText(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
