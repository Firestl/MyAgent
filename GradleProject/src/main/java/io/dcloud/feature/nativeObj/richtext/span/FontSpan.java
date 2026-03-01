package io.dcloud.feature.nativeObj.richtext.span;

import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;

/* JADX INFO: loaded from: classes3.dex */
public class FontSpan extends AbsoluteSizeSpan {
    public static final int DECORATION_LINE_THROUGH = 2;
    public static final int DECORATION_NONE = 0;
    public static final int DECORATION_UNDERLINE = 1;
    public static float DEF_FONT_SIZE = 12.0f;
    public static final int STYLE_ITALIC = 1;
    public static final int STYLE_NORMAL = 0;
    public static final int WEIGHT_BOLD = 1;
    public static final int WEIGHT_NORMAL = 0;
    public int color;
    public int decoration;
    public int style;
    public int weight;

    public FontSpan(float f, int i, int i2, int i3, int i4) {
        super((int) f, true);
        this.color = -16777216;
        this.weight = 0;
        this.style = 0;
        this.decoration = 0;
        this.color = i;
        this.weight = i2;
        this.style = i3;
        this.decoration = i4;
    }

    @Override // android.text.style.AbsoluteSizeSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.color);
        int i = this.decoration;
        if (i == 2) {
            textPaint.setStrikeThruText(true);
        } else if (i == 1) {
            textPaint.setUnderlineText(true);
        }
        if (this.style == 1) {
            textPaint.setTextSkewX(-0.3f);
        }
        if (this.weight == 1) {
            textPaint.setFakeBoldText(true);
        } else {
            textPaint.setFakeBoldText(false);
        }
        super.updateDrawState(textPaint);
    }
}
