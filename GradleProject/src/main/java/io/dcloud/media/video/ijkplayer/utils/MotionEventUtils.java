package io.dcloud.media.video.ijkplayer.utils;

import android.graphics.PointF;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes3.dex */
public final class MotionEventUtils {
    public static final int FINGER_FLAG_1 = 601;
    public static final int FINGER_FLAG_2 = 602;
    public static final int FINGER_FLAG_3 = 603;

    public MotionEventUtils() {
        throw new AssertionError();
    }

    public static int calcFingerFlag(MotionEvent motionEvent) {
        float fCalcSpacing = calcSpacing(motionEvent, 0, 1);
        float fCalcSpacing2 = calcSpacing(motionEvent, 0, 2);
        float fCalcSpacing3 = calcSpacing(motionEvent, 2, 1);
        float fMin = Math.min(fCalcSpacing, Math.min(fCalcSpacing2, fCalcSpacing3));
        if (fMin == fCalcSpacing) {
            return 601;
        }
        if (fMin == fCalcSpacing2) {
            return 602;
        }
        return fMin == fCalcSpacing3 ? 603 : -1;
    }

    public static float calcSpacing(MotionEvent motionEvent, int i, int i2) {
        float x = motionEvent.getX(i) - motionEvent.getX(i2);
        float y = motionEvent.getY(i) - motionEvent.getY(i2);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public static void midPoint(PointF pointF, MotionEvent motionEvent) {
        pointF.set(((motionEvent.getX(0) + motionEvent.getX(1)) + motionEvent.getX(2)) / 3.0f, ((motionEvent.getY(0) + motionEvent.getY(1)) + motionEvent.getY(2)) / 3.0f);
    }

    public static float rotation(MotionEvent motionEvent, int i) {
        double x;
        float y;
        float y2;
        if (601 == i) {
            x = ((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f) - motionEvent.getX(2);
            y = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
            y2 = motionEvent.getY(2);
        } else if (602 == i) {
            x = ((motionEvent.getX(0) + motionEvent.getX(2)) / 2.0f) - motionEvent.getX(1);
            y = (motionEvent.getY(0) + motionEvent.getY(2)) / 2.0f;
            y2 = motionEvent.getY(1);
        } else if (603 == i) {
            x = ((motionEvent.getX(2) + motionEvent.getX(1)) / 2.0f) - motionEvent.getX(0);
            y = (motionEvent.getY(2) + motionEvent.getY(1)) / 2.0f;
            y2 = motionEvent.getY(0);
        } else {
            x = motionEvent.getX(0) - motionEvent.getX(1);
            y = motionEvent.getY(0);
            y2 = motionEvent.getY(1);
        }
        return (float) Math.toDegrees(Math.atan2(y - y2, x));
    }

    public static float calcSpacing(MotionEvent motionEvent, int i) {
        float x;
        float y;
        float y2;
        float f;
        float y3;
        float y4;
        if (601 == i) {
            x = ((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f) - motionEvent.getX(2);
            y3 = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
            y4 = motionEvent.getY(2);
        } else if (602 == i) {
            x = ((motionEvent.getX(0) + motionEvent.getX(2)) / 2.0f) - motionEvent.getX(1);
            y3 = (motionEvent.getY(0) + motionEvent.getY(2)) / 2.0f;
            y4 = motionEvent.getY(1);
        } else {
            if (603 == i) {
                x = ((motionEvent.getX(2) + motionEvent.getX(1)) / 2.0f) - motionEvent.getX(0);
                y = (motionEvent.getY(2) + motionEvent.getY(1)) / 2.0f;
                y2 = motionEvent.getY(0);
            } else {
                x = motionEvent.getX(0) - motionEvent.getX(1);
                y = motionEvent.getY(0);
                y2 = motionEvent.getY(1);
            }
            f = y - y2;
            return (float) Math.sqrt((x * x) + (f * f));
        }
        f = y3 - y4;
        return (float) Math.sqrt((x * x) + (f * f));
    }
}
