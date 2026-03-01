package io.dcloud.feature.barcode2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import io.dcloud.feature.barcode2.decoding.IBarHandler;
import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import supwisdom.yv;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewfinderView extends View {
    public static final long ANIMATION_DELAY = 100;
    public static final int OPAQUE = 255;
    public IBarHandler barHandler;
    public ShapeDrawable laserBitmap;
    public int laserY;
    public Collection<yv> lastPossibleResultPoints;
    public Rect lastRect;
    public Timer mUpdateProgressBar;
    public final Paint paint;
    public Collection<yv> possibleResultPoints;
    public boolean running;

    public ViewfinderView(Context context, IBarHandler iBarHandler) {
        super(context);
        this.barHandler = null;
        this.lastRect = null;
        this.laserBitmap = null;
        this.laserY = -DetectorViewConfig.LASER_WIDTH;
        this.mUpdateProgressBar = null;
        this.running = false;
        this.barHandler = iBarHandler;
        this.paint = new Paint();
        getResources();
        this.possibleResultPoints = new HashSet(5);
    }

    private void drawDetectorCorner(Canvas canvas, Rect rect) {
        this.paint.setColor(DetectorViewConfig.cornerColor);
        int i = DetectorViewConfig.CORNER_WIDTH / 2;
        int i2 = DetectorViewConfig.CORNER_HEIGHT;
        int i3 = rect.left;
        int i4 = rect.top;
        canvas.drawRect(i3 - i, i4 - i, i3 + i2, i4 + i, this.paint);
        int i5 = rect.left;
        canvas.drawRect(i5 - i, rect.top, i5 + i, r3 + i2, this.paint);
        int i6 = rect.right;
        int i7 = rect.top;
        canvas.drawRect(i6 - i2, i7 - i, i6 + i, i7 + i, this.paint);
        int i8 = rect.right;
        canvas.drawRect(i8 - i, rect.top, i8 + i, r3 + i2, this.paint);
        int i9 = rect.left;
        canvas.drawRect(i9 - i, r3 - i2, i9 + i, rect.bottom, this.paint);
        int i10 = rect.left;
        int i11 = rect.bottom;
        canvas.drawRect(i10 - i, i11 - i, i10 + i2, i11 + i, this.paint);
        int i12 = rect.right;
        int i13 = rect.bottom;
        canvas.drawRect(i12 - i2, i13 - i, i12 + i, i13 + i, this.paint);
        int i14 = rect.right;
        int i15 = rect.bottom;
        canvas.drawRect(i14 - i, i15 - i2, i14 + i, i15 + i, this.paint);
    }

    private void drawLaserLine(Canvas canvas, Rect rect) {
        if (this.laserBitmap == null) {
            this.laserBitmap = new ShapeDrawable(new OvalShape());
            float fWidth = rect.width() / 2;
            float f = DetectorViewConfig.LASER_WIDTH / 2;
            int i = DetectorViewConfig.laserColor;
            this.laserBitmap.getPaint().setShader(new RadialGradient(fWidth, f, 240.0f, i, i & X25519Field.M24, Shader.TileMode.CLAMP));
        }
        this.paint.setAntiAlias(true);
        ShapeDrawable shapeDrawable = this.laserBitmap;
        int i2 = rect.left;
        shapeDrawable.setBounds(i2, this.laserY, rect.width() + i2, this.laserY + DetectorViewConfig.LASER_WIDTH);
        this.laserBitmap.draw(canvas);
        this.paint.setShader(null);
    }

    private void drawNonDetectorArea(Canvas canvas, Rect rect, Rect rect2) {
        this.paint.setColor(DetectorViewConfig.maskColor);
        canvas.drawRect(0.0f, 0.0f, rect2.right, rect.top, this.paint);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom, this.paint);
        canvas.drawRect(rect.right, rect.top, rect2.right, rect.bottom, this.paint);
        canvas.drawRect(0.0f, rect.bottom, rect2.right, rect2.bottom, this.paint);
    }

    private void drawResultPoint(Canvas canvas, Rect rect) {
        Collection<yv> collection = this.possibleResultPoints;
        Collection<yv> collection2 = this.lastPossibleResultPoints;
        if (collection.isEmpty()) {
            this.lastPossibleResultPoints = null;
        } else {
            this.possibleResultPoints = new HashSet(5);
            this.lastPossibleResultPoints = collection;
            this.paint.setAlpha(255);
            this.paint.setColor(DetectorViewConfig.resultPointColor);
            for (yv yvVar : collection) {
                canvas.drawCircle(rect.left + yvVar.a(), rect.top + yvVar.b(), 6.0f, this.paint);
            }
        }
        if (collection2 != null) {
            this.paint.setAlpha(127);
            this.paint.setColor(DetectorViewConfig.resultPointColor);
            for (yv yvVar2 : collection2) {
                canvas.drawCircle(rect.left + yvVar2.a(), rect.top + yvVar2.b(), 3.0f, this.paint);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScreen() {
        Rect detectorRect = DetectorViewConfig.getInstance().getDetectorRect();
        int i = this.laserY;
        if (i > detectorRect.bottom) {
            this.laserY = detectorRect.top;
        } else {
            this.laserY = i + 1;
        }
        postInvalidate();
    }

    public void addPossibleResultPoint(yv yvVar) {
        this.possibleResultPoints.add(yvVar);
    }

    public void drawViewfinder() {
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect detectorRect = DetectorViewConfig.getInstance().getDetectorRect();
        Rect rect = DetectorViewConfig.getInstance().gatherRect;
        if (detectorRect == null) {
            return;
        }
        drawNonDetectorArea(canvas, detectorRect, rect);
        drawDetectorCorner(canvas, detectorRect);
        if (this.running) {
            drawLaserLine(canvas, detectorRect);
            return;
        }
        this.lastRect = detectorRect;
        if (detectorRect != null) {
            drawLaserLine(canvas, detectorRect);
        }
    }

    public void startUpdateScreenTimer() {
        if (this.running) {
            return;
        }
        stopUpdateScreenTimer();
        this.laserY = DetectorViewConfig.getInstance().getDetectorRect().top;
        Timer timer = new Timer();
        this.mUpdateProgressBar = timer;
        timer.schedule(new TimerTask() { // from class: io.dcloud.feature.barcode2.view.ViewfinderView.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                ViewfinderView.this.updateScreen();
            }
        }, 0L, 10L);
        this.running = true;
    }

    public void stopUpdateScreenTimer() {
        if (this.running) {
            Timer timer = this.mUpdateProgressBar;
            if (timer != null) {
                timer.cancel();
                this.mUpdateProgressBar = null;
            }
            this.running = false;
            updateScreen();
        }
    }
}
