package tv.danmaku.ijk.media.player;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes3.dex */
public final class IjkTimedText {
    public Rect mTextBounds;
    public String mTextChars;

    public IjkTimedText(Rect rect, String str) {
        this.mTextBounds = null;
        this.mTextChars = null;
        this.mTextBounds = rect;
        this.mTextChars = str;
    }

    public Rect getBounds() {
        return this.mTextBounds;
    }

    public String getText() {
        return this.mTextChars;
    }
}
