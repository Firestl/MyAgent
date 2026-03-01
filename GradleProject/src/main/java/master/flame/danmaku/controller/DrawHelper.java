package master.flame.danmaku.controller;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes3.dex */
public class DrawHelper {
    public static Paint PAINT = null;
    public static Paint PAINT_FPS = null;
    public static RectF RECT = null;
    public static boolean USE_DRAWCOLOR_MODE_CLEAR = true;
    public static boolean USE_DRAWCOLOR_TO_CLEAR_CANVAS = true;

    static {
        Paint paint = new Paint();
        PAINT = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        PAINT.setColor(0);
        RECT = new RectF();
    }

    public static void clearCanvas(Canvas canvas) {
        if (!USE_DRAWCOLOR_TO_CLEAR_CANVAS) {
            RECT.set(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
            clearCanvas(canvas, RECT);
        } else if (USE_DRAWCOLOR_MODE_CLEAR) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        } else {
            canvas.drawColor(0);
        }
    }

    public static void drawFPS(Canvas canvas, String str) {
        if (PAINT_FPS == null) {
            Paint paint = new Paint();
            PAINT_FPS = paint;
            paint.setColor(-65536);
            PAINT_FPS.setTextSize(30.0f);
        }
        int height = canvas.getHeight() - 50;
        clearCanvas(canvas, 10.0f, height - 50, (int) (PAINT_FPS.measureText(str) + 20.0f), canvas.getHeight());
        canvas.drawText(str, 10.0f, height, PAINT_FPS);
    }

    public static void fillTransparent(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
    }

    public static void useDrawColorToClearCanvas(boolean z, boolean z2) {
        USE_DRAWCOLOR_TO_CLEAR_CANVAS = z;
        USE_DRAWCOLOR_MODE_CLEAR = z2;
    }

    public static void clearCanvas(Canvas canvas, float f, float f2, float f3, float f4) {
        RECT.set(f, f2, f3, f4);
        clearCanvas(canvas, RECT);
    }

    public static void clearCanvas(Canvas canvas, RectF rectF) {
        if (rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
            return;
        }
        canvas.drawRect(rectF, PAINT);
    }
}
