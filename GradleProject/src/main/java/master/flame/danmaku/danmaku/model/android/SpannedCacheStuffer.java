package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.lang.ref.SoftReference;
import master.flame.danmaku.danmaku.model.BaseDanmaku;

/* JADX INFO: loaded from: classes3.dex */
public class SpannedCacheStuffer extends SimpleTextCacheStuffer {
    @Override // master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void clearCache(BaseDanmaku baseDanmaku) {
        super.clearCache(baseDanmaku);
        Object obj = baseDanmaku.obj;
        if (obj instanceof SoftReference) {
            ((SoftReference) obj).clear();
        }
    }

    @Override // master.flame.danmaku.danmaku.model.android.SimpleTextCacheStuffer, master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void clearCaches() {
        super.clearCaches();
        System.gc();
    }

    @Override // master.flame.danmaku.danmaku.model.android.SimpleTextCacheStuffer
    public void drawStroke(BaseDanmaku baseDanmaku, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (baseDanmaku.obj == null) {
            super.drawStroke(baseDanmaku, str, canvas, f, f2, paint);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.android.SimpleTextCacheStuffer
    public void drawText(BaseDanmaku baseDanmaku, String str, Canvas canvas, float f, float f2, TextPaint textPaint, boolean z) {
        Object obj = baseDanmaku.obj;
        if (obj == null) {
            super.drawText(baseDanmaku, str, canvas, f, f2, textPaint, z);
            return;
        }
        StaticLayout staticLayout = (StaticLayout) ((SoftReference) obj).get();
        boolean z2 = true;
        boolean z3 = (baseDanmaku.requestFlags & 1) != 0;
        boolean z4 = (baseDanmaku.requestFlags & 2) != 0;
        if (z4 || staticLayout == null) {
            if (z4) {
                baseDanmaku.requestFlags &= -3;
            }
            CharSequence charSequence = baseDanmaku.text;
            if (charSequence == null) {
                return;
            }
            if (z3) {
                staticLayout = new StaticLayout(charSequence, textPaint, (int) Math.ceil(StaticLayout.getDesiredWidth(baseDanmaku.text, textPaint)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
                baseDanmaku.paintWidth = staticLayout.getWidth();
                baseDanmaku.paintHeight = staticLayout.getHeight();
                baseDanmaku.requestFlags &= -2;
            } else {
                staticLayout = new StaticLayout(charSequence, textPaint, (int) baseDanmaku.paintWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
            baseDanmaku.obj = new SoftReference(staticLayout);
        }
        if (f == 0.0f || f2 == 0.0f) {
            z2 = false;
        } else {
            canvas.save();
            canvas.translate(f, f2 + textPaint.ascent());
        }
        staticLayout.draw(canvas);
        if (z2) {
            canvas.restore();
        }
    }

    @Override // master.flame.danmaku.danmaku.model.android.SimpleTextCacheStuffer, master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void measure(BaseDanmaku baseDanmaku, TextPaint textPaint, boolean z) {
        CharSequence charSequence = baseDanmaku.text;
        if (!(charSequence instanceof Spanned) || charSequence == null) {
            super.measure(baseDanmaku, textPaint, z);
            return;
        }
        StaticLayout staticLayout = new StaticLayout(charSequence, textPaint, (int) Math.ceil(StaticLayout.getDesiredWidth(baseDanmaku.text, textPaint)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        baseDanmaku.paintWidth = staticLayout.getWidth();
        baseDanmaku.paintHeight = staticLayout.getHeight();
        baseDanmaku.obj = new SoftReference(staticLayout);
    }

    @Override // master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void releaseResource(BaseDanmaku baseDanmaku) {
        clearCache(baseDanmaku);
        super.releaseResource(baseDanmaku);
    }
}
