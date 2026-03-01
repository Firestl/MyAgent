package io.dcloud.feature.barcode2.view;

import android.graphics.Rect;
import io.dcloud.common.DHInterface.IReflectAble;

/* JADX INFO: loaded from: classes2.dex */
public class DetectorViewConfig implements IReflectAble {
    public static int CORNER_HEIGHT = 40;
    public static int CORNER_WIDTH = 8;
    public static final int F_CORNER_COLOR = -65536;
    public static final int F_LASER_COLOR = -65536;
    public static int LASER_WIDTH = 8;
    public static final int MAX_FRAME_HEIGHT = 360;
    public static final int MAX_FRAME_WIDTH = 640;
    public static final int MIN_FRAME_HEIGHT = 240;
    public static final int MIN_FRAME_WIDTH = 240;
    public static int cornerColor = -65536;
    public static int detectorRectOffestLeft = 0;
    public static int detectorRectOffestTop = 0;
    public static DetectorViewConfig instance = null;
    public static int laserColor = -65536;
    public static int maskColor = 1610612736;
    public static int resultPointColor = -1056964864;
    public Rect surfaceViewRect = null;
    public Rect gatherRect = new Rect();
    public Rect detectorRect = null;
    public boolean retry = false;

    public static void clearData() {
        instance = null;
    }

    public static DetectorViewConfig getInstance() {
        if (instance == null) {
            instance = new DetectorViewConfig();
        }
        return instance;
    }

    public Rect getDetectorRect() {
        if (this.retry || this.detectorRect == null) {
            int iWidth = this.gatherRect.width();
            int iHeight = this.gatherRect.height();
            int i = ((iWidth < iHeight ? iWidth : iHeight) * 6) / 10;
            this.retry = i < 0;
            if (i < 240) {
                i = 240;
            } else if (i > 640) {
                i = 640;
            }
            CORNER_HEIGHT = (i * 10) / 100;
            int i2 = (iWidth - i) / 2;
            int i3 = (iHeight - i) / 2;
            this.detectorRect = new Rect(i2, i3, i2 + i, i + i3);
        }
        return this.detectorRect;
    }

    public void initSurfaceViewRect(int i, int i2, int i3, int i4) {
        this.surfaceViewRect = new Rect(i, i2, i3 + i, i4 + i2);
    }
}
