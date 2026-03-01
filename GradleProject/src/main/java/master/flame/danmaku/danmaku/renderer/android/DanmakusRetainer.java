package master.flame.danmaku.danmaku.renderer.android;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakusRetainer {
    public IDanmakusRetainer rldrInstance = null;
    public IDanmakusRetainer lrdrInstance = null;
    public IDanmakusRetainer ftdrInstance = null;
    public IDanmakusRetainer fbdrInstance = null;

    public static class AlignBottomRetainer extends FTDanmakusRetainer {
        public Danmakus mVisibleDanmakus;

        public AlignBottomRetainer() {
            super();
            this.mVisibleDanmakus = new Danmakus(2);
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void clear() {
            this.mCancelFixingFlag = true;
            this.mVisibleDanmakus.clear();
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
            int i;
            BaseDanmaku next;
            BaseDanmaku baseDanmaku2;
            boolean z;
            BaseDanmaku baseDanmaku3;
            if (baseDanmaku.isOutside()) {
                return;
            }
            boolean zIsShown = baseDanmaku.isShown();
            float top = zIsShown ? baseDanmaku.getTop() : -1.0f;
            int i2 = 1;
            boolean z2 = false;
            boolean zWillHitInDuration = (zIsShown || this.mVisibleDanmakus.isEmpty()) ? false : true;
            if (top < 0.0f) {
                top = iDisplayer.getHeight() - baseDanmaku.paintHeight;
            }
            BaseDanmaku baseDanmaku4 = null;
            if (zIsShown) {
                i2 = 0;
            } else {
                this.mCancelFixingFlag = false;
                IDanmakuIterator it = this.mVisibleDanmakus.iterator();
                float top2 = top;
                BaseDanmaku baseDanmaku5 = null;
                int i3 = 0;
                while (!this.mCancelFixingFlag && it.hasNext()) {
                    i = i3 + 1;
                    next = it.next();
                    if (next == baseDanmaku) {
                        next = baseDanmaku5;
                        baseDanmaku2 = null;
                        z = false;
                        break;
                    }
                    if (baseDanmaku5 != null) {
                        baseDanmaku3 = baseDanmaku5;
                    } else if (next.getBottom() != iDisplayer.getHeight()) {
                        break;
                    } else {
                        baseDanmaku3 = next;
                    }
                    if (top2 < 0.0f) {
                        baseDanmaku2 = null;
                        next = baseDanmaku3;
                        break;
                    }
                    baseDanmaku2 = next;
                    zWillHitInDuration = DanmakuUtils.willHitInDuration(iDisplayer, next, baseDanmaku, baseDanmaku.getDuration(), baseDanmaku.getTimer().currMillisecond);
                    if (!zWillHitInDuration) {
                        z = zWillHitInDuration;
                        next = baseDanmaku3;
                        break;
                    } else {
                        top2 = baseDanmaku2.getTop() - baseDanmaku.paintHeight;
                        i3 = i;
                        baseDanmaku5 = baseDanmaku3;
                    }
                }
                i = i3;
                next = baseDanmaku5;
                baseDanmaku2 = null;
                z = zWillHitInDuration;
                boolean zIsOutVerticalEdge = isOutVerticalEdge(false, baseDanmaku, iDisplayer, top2, next, null);
                if (zIsOutVerticalEdge) {
                    z2 = zIsOutVerticalEdge;
                    top = iDisplayer.getHeight() - baseDanmaku.paintHeight;
                    baseDanmaku4 = baseDanmaku2;
                    zWillHitInDuration = true;
                } else {
                    boolean z3 = top2 >= 0.0f ? false : z;
                    if (baseDanmaku2 != null) {
                        z2 = zIsOutVerticalEdge;
                        top = top2;
                        baseDanmaku4 = baseDanmaku2;
                        boolean z4 = z3;
                        i2 = i - 1;
                        zWillHitInDuration = z4;
                    } else {
                        z2 = zIsOutVerticalEdge;
                        zWillHitInDuration = z3;
                        top = top2;
                        i2 = i;
                        baseDanmaku4 = baseDanmaku2;
                    }
                }
            }
            if (verifier == null || !verifier.skipLayout(baseDanmaku, top, i2, zWillHitInDuration)) {
                if (z2) {
                    clear();
                }
                baseDanmaku.layout(iDisplayer, baseDanmaku.getLeft(), top);
                if (zIsShown) {
                    return;
                }
                this.mVisibleDanmakus.removeItem(baseDanmaku4);
                this.mVisibleDanmakus.addItem(baseDanmaku);
            }
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.FTDanmakusRetainer, master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer
        public boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            if (f >= 0.0f) {
                return (baseDanmaku2 == null || baseDanmaku2.getBottom() == ((float) iDisplayer.getHeight())) ? false : true;
            }
            return true;
        }
    }

    public static class AlignTopRetainer implements IDanmakusRetainer {
        public boolean mCancelFixingFlag;
        public Danmakus mVisibleDanmakus;

        public AlignTopRetainer() {
            this.mVisibleDanmakus = new Danmakus(1);
            this.mCancelFixingFlag = false;
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void clear() {
            this.mCancelFixingFlag = true;
            this.mVisibleDanmakus.clear();
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.IDanmakusRetainer
        public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
            boolean z;
            int i;
            BaseDanmaku baseDanmaku2;
            BaseDanmaku next;
            BaseDanmaku baseDanmaku3;
            boolean z2;
            boolean z3;
            BaseDanmaku baseDanmaku4;
            float top;
            boolean z4;
            float bottom;
            boolean z5;
            if (baseDanmaku.isOutside()) {
                return;
            }
            boolean zIsShown = baseDanmaku.isShown();
            int i2 = 1;
            int i3 = 0;
            boolean zWillHitInDuration = (zIsShown || this.mVisibleDanmakus.isEmpty()) ? false : true;
            float f = 0.0f;
            BaseDanmaku baseDanmaku5 = null;
            if (zIsShown) {
                z = false;
            } else {
                this.mCancelFixingFlag = false;
                IDanmakuIterator it = this.mVisibleDanmakus.iterator();
                BaseDanmaku baseDanmaku6 = null;
                BaseDanmaku baseDanmaku7 = null;
                BaseDanmaku baseDanmaku8 = null;
                int i4 = 0;
                while (!this.mCancelFixingFlag && it.hasNext()) {
                    i = i4 + 1;
                    next = it.next();
                    if (next == baseDanmaku) {
                        baseDanmaku2 = baseDanmaku6;
                        baseDanmaku3 = null;
                        zWillHitInDuration = false;
                        z2 = false;
                        zIsShown = true;
                        break;
                    }
                    baseDanmaku2 = baseDanmaku6 == null ? next : baseDanmaku6;
                    if (baseDanmaku.paintHeight + next.getTop() > iDisplayer.getHeight()) {
                        next = null;
                        baseDanmaku3 = baseDanmaku8;
                        z2 = true;
                        break;
                    }
                    BaseDanmaku baseDanmaku9 = (baseDanmaku7 != null && baseDanmaku7.getRight() < next.getRight()) ? baseDanmaku7 : next;
                    zWillHitInDuration = DanmakuUtils.willHitInDuration(iDisplayer, next, baseDanmaku, baseDanmaku.getDuration(), baseDanmaku.getTimer().currMillisecond);
                    if (!zWillHitInDuration) {
                        baseDanmaku3 = baseDanmaku8;
                        baseDanmaku7 = baseDanmaku9;
                        next = next;
                        break;
                    } else {
                        i4 = i;
                        baseDanmaku6 = baseDanmaku2;
                        baseDanmaku7 = baseDanmaku9;
                        baseDanmaku8 = next;
                    }
                }
                i = i4;
                baseDanmaku2 = baseDanmaku6;
                next = null;
                baseDanmaku3 = baseDanmaku8;
                z2 = false;
                if (next != null) {
                    float bottom2 = baseDanmaku3 != null ? baseDanmaku3.getBottom() : next.getTop();
                    if (next != baseDanmaku) {
                        z4 = zWillHitInDuration;
                        top = bottom2;
                        baseDanmaku4 = next;
                        z5 = true;
                        z3 = false;
                    } else {
                        z3 = zIsShown;
                        baseDanmaku4 = null;
                        z4 = zWillHitInDuration;
                        top = bottom2;
                        z5 = true;
                    }
                } else {
                    if (z2 && baseDanmaku7 != null) {
                        bottom = baseDanmaku7.getTop();
                        z4 = zWillHitInDuration;
                        baseDanmaku4 = null;
                        z5 = false;
                        z3 = false;
                    } else if (baseDanmaku3 != null) {
                        bottom = baseDanmaku3.getBottom();
                        z3 = zIsShown;
                        baseDanmaku4 = null;
                        z5 = true;
                        z4 = false;
                    } else if (baseDanmaku2 != null) {
                        z4 = zWillHitInDuration;
                        top = baseDanmaku2.getTop();
                        baseDanmaku4 = baseDanmaku2;
                        z5 = true;
                        z3 = false;
                    } else {
                        z3 = zIsShown;
                        baseDanmaku4 = null;
                        top = 0.0f;
                        z4 = zWillHitInDuration;
                        z5 = true;
                    }
                    top = bottom;
                }
                boolean zIsOutVerticalEdge = z5 ? isOutVerticalEdge(z2, baseDanmaku, iDisplayer, top, baseDanmaku2, baseDanmaku3) : false;
                if (zIsOutVerticalEdge) {
                    z4 = true;
                    top = 0.0f;
                } else {
                    i2 = baseDanmaku4 != null ? i - 1 : i;
                }
                if (top == 0.0f) {
                    z = zIsOutVerticalEdge;
                    zWillHitInDuration = z4;
                    i3 = i2;
                    f = top;
                    baseDanmaku5 = baseDanmaku4;
                    zIsShown = false;
                } else {
                    z = zIsOutVerticalEdge;
                    zWillHitInDuration = z4;
                    i3 = i2;
                    f = top;
                    zIsShown = z3;
                    baseDanmaku5 = baseDanmaku4;
                }
            }
            if (verifier == null || !verifier.skipLayout(baseDanmaku, f, i3, zWillHitInDuration)) {
                if (z) {
                    clear();
                }
                baseDanmaku.layout(iDisplayer, baseDanmaku.getLeft(), f);
                if (zIsShown) {
                    return;
                }
                this.mVisibleDanmakus.removeItem(baseDanmaku5);
                this.mVisibleDanmakus.addItem(baseDanmaku);
            }
        }

        public boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            if (f >= 0.0f) {
                return (baseDanmaku2 != null && baseDanmaku2.getTop() > 0.0f) || f + baseDanmaku.paintHeight > ((float) iDisplayer.getHeight());
            }
            return true;
        }
    }

    public static class FTDanmakusRetainer extends AlignTopRetainer {
        public FTDanmakusRetainer() {
            super();
        }

        @Override // master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer.AlignTopRetainer
        public boolean isOutVerticalEdge(boolean z, BaseDanmaku baseDanmaku, IDisplayer iDisplayer, float f, BaseDanmaku baseDanmaku2, BaseDanmaku baseDanmaku3) {
            return f + baseDanmaku.paintHeight > ((float) iDisplayer.getHeight());
        }
    }

    public interface IDanmakusRetainer {
        void clear();

        void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier);
    }

    public interface Verifier {
        boolean skipLayout(BaseDanmaku baseDanmaku, float f, int i, boolean z);
    }

    public DanmakusRetainer(boolean z) {
        alignBottom(z);
    }

    public void alignBottom(boolean z) {
        this.rldrInstance = z ? new AlignBottomRetainer() : new AlignTopRetainer();
        this.lrdrInstance = z ? new AlignBottomRetainer() : new AlignTopRetainer();
        if (this.ftdrInstance == null) {
            this.ftdrInstance = new FTDanmakusRetainer();
        }
        if (this.fbdrInstance == null) {
            this.fbdrInstance = new AlignBottomRetainer();
        }
    }

    public void clear() {
        IDanmakusRetainer iDanmakusRetainer = this.rldrInstance;
        if (iDanmakusRetainer != null) {
            iDanmakusRetainer.clear();
        }
        IDanmakusRetainer iDanmakusRetainer2 = this.lrdrInstance;
        if (iDanmakusRetainer2 != null) {
            iDanmakusRetainer2.clear();
        }
        IDanmakusRetainer iDanmakusRetainer3 = this.ftdrInstance;
        if (iDanmakusRetainer3 != null) {
            iDanmakusRetainer3.clear();
        }
        IDanmakusRetainer iDanmakusRetainer4 = this.fbdrInstance;
        if (iDanmakusRetainer4 != null) {
            iDanmakusRetainer4.clear();
        }
    }

    public void fix(BaseDanmaku baseDanmaku, IDisplayer iDisplayer, Verifier verifier) {
        int type = baseDanmaku.getType();
        if (type == 1) {
            this.rldrInstance.fix(baseDanmaku, iDisplayer, verifier);
            return;
        }
        if (type == 4) {
            this.fbdrInstance.fix(baseDanmaku, iDisplayer, verifier);
            return;
        }
        if (type == 5) {
            this.ftdrInstance.fix(baseDanmaku, iDisplayer, verifier);
        } else if (type == 6) {
            this.lrdrInstance.fix(baseDanmaku, iDisplayer, verifier);
        } else {
            if (type != 7) {
                return;
            }
            baseDanmaku.layout(iDisplayer, 0.0f, 0.0f);
        }
    }

    public void release() {
        clear();
    }
}
