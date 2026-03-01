package master.flame.danmaku.danmaku.model.android;

import java.lang.reflect.Array;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.FBDanmaku;
import master.flame.danmaku.danmaku.model.FTDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.L2RDanmaku;
import master.flame.danmaku.danmaku.model.R2LDanmaku;
import master.flame.danmaku.danmaku.model.SpecialDanmaku;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuFactory {
    public static final float BILI_PLAYER_HEIGHT = 438.0f;
    public static final float BILI_PLAYER_WIDTH = 682.0f;
    public static final long COMMON_DANMAKU_DURATION = 3800;
    public static final int DANMAKU_MEDIUM_TEXTSIZE = 25;
    public static final long MAX_DANMAKU_DURATION_HIGH_DENSITY = 9000;
    public static final long MIN_DANMAKU_DURATION = 4000;
    public static final float OLD_BILI_PLAYER_HEIGHT = 385.0f;
    public static final float OLD_BILI_PLAYER_WIDTH = 539.0f;
    public Duration MAX_Duration_Fix_Danmaku;
    public Duration MAX_Duration_Scroll_Danmaku;
    public Duration MAX_Duration_Special_Danmaku;
    public DanmakuContext sLastConfig;
    public IDisplayer sLastDisp;
    public int CURRENT_DISP_WIDTH = 0;
    public int CURRENT_DISP_HEIGHT = 0;
    public float CURRENT_DISP_SIZE_FACTOR = 1.0f;
    public long REAL_DANMAKU_DURATION = COMMON_DANMAKU_DURATION;
    public long MAX_DANMAKU_DURATION = 4000;
    public IDanmakus sSpecialDanmakus = new Danmakus();

    public static DanmakuFactory create() {
        return new DanmakuFactory();
    }

    public static void fillLinePathData(BaseDanmaku baseDanmaku, float[][] fArr, float f, float f2) {
        if (baseDanmaku.getType() == 7 && fArr.length != 0 && fArr[0].length == 2) {
            for (int i = 0; i < fArr.length; i++) {
                float[] fArr2 = fArr[i];
                fArr2[0] = fArr2[0] * f;
                float[] fArr3 = fArr[i];
                fArr3[1] = fArr3[1] * f2;
            }
            ((SpecialDanmaku) baseDanmaku).setLinePathData(fArr);
        }
    }

    private void updateSpecialDanmakusDate(float f, float f2) {
        IDanmakuIterator it = this.sSpecialDanmakus.iterator();
        while (it.hasNext()) {
            SpecialDanmaku specialDanmaku = (SpecialDanmaku) it.next();
            fillTranslationData(specialDanmaku, specialDanmaku.beginX, specialDanmaku.beginY, specialDanmaku.endX, specialDanmaku.endY, specialDanmaku.translationDuration, specialDanmaku.translationStartDelay, f, f2);
            SpecialDanmaku.LinePath[] linePathArr = specialDanmaku.linePaths;
            if (linePathArr != null && linePathArr.length > 0) {
                int length = linePathArr.length;
                int i = 0;
                float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, length + 1, 2);
                while (i < length) {
                    fArr[i] = linePathArr[i].getBeginPoint();
                    int i2 = i + 1;
                    fArr[i2] = linePathArr[i].getEndPoint();
                    i = i2;
                }
                fillLinePathData(specialDanmaku, fArr, f, f2);
            }
        }
    }

    private void updateSpecicalDanmakuDuration(BaseDanmaku baseDanmaku) {
        Duration duration;
        Duration duration2 = this.MAX_Duration_Special_Danmaku;
        if (duration2 == null || ((duration = baseDanmaku.duration) != null && duration.value > duration2.value)) {
            this.MAX_Duration_Special_Danmaku = baseDanmaku.duration;
            updateMaxDanmakuDuration();
        }
    }

    public BaseDanmaku createDanmaku(int i) {
        return createDanmaku(i, this.sLastConfig);
    }

    public void fillAlphaData(BaseDanmaku baseDanmaku, int i, int i2, long j) {
        if (baseDanmaku.getType() != 7) {
            return;
        }
        ((SpecialDanmaku) baseDanmaku).setAlphaData(i, i2, j);
        updateSpecicalDanmakuDuration(baseDanmaku);
    }

    public void fillTranslationData(BaseDanmaku baseDanmaku, float f, float f2, float f3, float f4, long j, long j2, float f5, float f6) {
        if (baseDanmaku.getType() != 7) {
            return;
        }
        ((SpecialDanmaku) baseDanmaku).setTranslationData(f * f5, f2 * f6, f3 * f5, f4 * f6, j, j2);
        updateSpecicalDanmakuDuration(baseDanmaku);
    }

    public void notifyDispSizeChanged(DanmakuContext danmakuContext) {
        this.sLastConfig = danmakuContext;
        this.sLastDisp = danmakuContext.getDisplayer();
        createDanmaku(1, danmakuContext);
    }

    public void resetDurationsData() {
        this.sLastDisp = null;
        this.CURRENT_DISP_HEIGHT = 0;
        this.CURRENT_DISP_WIDTH = 0;
        this.sSpecialDanmakus.clear();
        this.MAX_Duration_Scroll_Danmaku = null;
        this.MAX_Duration_Fix_Danmaku = null;
        this.MAX_Duration_Special_Danmaku = null;
        this.MAX_DANMAKU_DURATION = 4000L;
    }

    public void updateDurationFactor(float f) {
        Duration duration = this.MAX_Duration_Scroll_Danmaku;
        if (duration == null || this.MAX_Duration_Fix_Danmaku == null) {
            return;
        }
        duration.setFactor(f);
        updateMaxDanmakuDuration();
    }

    public void updateMaxDanmakuDuration() {
        Duration duration = this.MAX_Duration_Scroll_Danmaku;
        long j = duration == null ? 0L : duration.value;
        Duration duration2 = this.MAX_Duration_Fix_Danmaku;
        long j2 = duration2 == null ? 0L : duration2.value;
        Duration duration3 = this.MAX_Duration_Special_Danmaku;
        long j3 = duration3 != null ? duration3.value : 0L;
        long jMax = Math.max(j, j2);
        this.MAX_DANMAKU_DURATION = jMax;
        long jMax2 = Math.max(jMax, j3);
        this.MAX_DANMAKU_DURATION = jMax2;
        long jMax3 = Math.max(COMMON_DANMAKU_DURATION, jMax2);
        this.MAX_DANMAKU_DURATION = jMax3;
        this.MAX_DANMAKU_DURATION = Math.max(this.REAL_DANMAKU_DURATION, jMax3);
    }

    public boolean updateViewportState(float f, float f2, float f3) {
        int i = (int) f;
        if (this.CURRENT_DISP_WIDTH == i && this.CURRENT_DISP_HEIGHT == ((int) f2) && this.CURRENT_DISP_SIZE_FACTOR == f3) {
            return false;
        }
        long j = (long) (((f * f3) / 682.0f) * 3800.0f);
        this.REAL_DANMAKU_DURATION = j;
        long jMin = Math.min(MAX_DANMAKU_DURATION_HIGH_DENSITY, j);
        this.REAL_DANMAKU_DURATION = jMin;
        this.REAL_DANMAKU_DURATION = Math.max(4000L, jMin);
        this.CURRENT_DISP_WIDTH = i;
        this.CURRENT_DISP_HEIGHT = (int) f2;
        this.CURRENT_DISP_SIZE_FACTOR = f3;
        return true;
    }

    public BaseDanmaku createDanmaku(int i, DanmakuContext danmakuContext) {
        if (danmakuContext == null) {
            return null;
        }
        this.sLastConfig = danmakuContext;
        AbsDisplayer displayer = danmakuContext.getDisplayer();
        this.sLastDisp = displayer;
        return createDanmaku(i, displayer.getWidth(), this.sLastDisp.getHeight(), this.CURRENT_DISP_SIZE_FACTOR, danmakuContext.scrollSpeedFactor);
    }

    public BaseDanmaku createDanmaku(int i, IDisplayer iDisplayer, float f, float f2) {
        if (iDisplayer == null) {
            return null;
        }
        this.sLastDisp = iDisplayer;
        return createDanmaku(i, iDisplayer.getWidth(), iDisplayer.getHeight(), f, f2);
    }

    public BaseDanmaku createDanmaku(int i, int i2, int i3, float f, float f2) {
        return createDanmaku(i, i2, i3, f, f2);
    }

    public BaseDanmaku createDanmaku(int i, float f, float f2, float f3, float f4) {
        float f5;
        int i2 = this.CURRENT_DISP_WIDTH;
        int i3 = this.CURRENT_DISP_HEIGHT;
        boolean zUpdateViewportState = updateViewportState(f, f2, f3);
        Duration duration = this.MAX_Duration_Scroll_Danmaku;
        if (duration == null) {
            Duration duration2 = new Duration(this.REAL_DANMAKU_DURATION);
            this.MAX_Duration_Scroll_Danmaku = duration2;
            duration2.setFactor(f4);
        } else if (zUpdateViewportState) {
            duration.setValue(this.REAL_DANMAKU_DURATION);
        }
        if (this.MAX_Duration_Fix_Danmaku == null) {
            this.MAX_Duration_Fix_Danmaku = new Duration(COMMON_DANMAKU_DURATION);
        }
        if (zUpdateViewportState && f > 0.0f) {
            updateMaxDanmakuDuration();
            float f6 = 1.0f;
            if (i2 <= 0 || i3 <= 0) {
                f5 = 1.0f;
            } else {
                f6 = f / i2;
                f5 = f2 / i3;
            }
            if (f2 > 0.0f) {
                updateSpecialDanmakusDate(f6, f5);
            }
        }
        if (i == 1) {
            return new R2LDanmaku(this.MAX_Duration_Scroll_Danmaku);
        }
        if (i == 4) {
            return new FBDanmaku(this.MAX_Duration_Fix_Danmaku);
        }
        if (i == 5) {
            return new FTDanmaku(this.MAX_Duration_Fix_Danmaku);
        }
        if (i == 6) {
            return new L2RDanmaku(this.MAX_Duration_Scroll_Danmaku);
        }
        if (i != 7) {
            return null;
        }
        SpecialDanmaku specialDanmaku = new SpecialDanmaku();
        this.sSpecialDanmakus.addItem(specialDanmaku);
        return specialDanmaku;
    }
}
