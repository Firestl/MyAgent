package master.flame.danmaku.ui.widget;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.Danmakus;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuTouchHelper {
    public IDanmakuView danmakuView;
    public final GestureDetector mTouchDelegate;
    public final GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: master.flame.danmaku.ui.widget.DanmakuTouchHelper.1
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return (DanmakuTouchHelper.this.danmakuView == null || DanmakuTouchHelper.this.danmakuView.getOnDanmakuClickListener() == null) ? false : true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            IDanmakus iDanmakus = DanmakuTouchHelper.this.touchHitDanmaku(motionEvent.getX(), motionEvent.getY());
            boolean zPerformDanmakuClick = (iDanmakus == null || iDanmakus.isEmpty()) ? false : DanmakuTouchHelper.this.performDanmakuClick(iDanmakus);
            return !zPerformDanmakuClick ? DanmakuTouchHelper.this.performViewClick() : zPerformDanmakuClick;
        }
    };
    public RectF mDanmakuBounds = new RectF();

    /* JADX WARN: Multi-variable type inference failed */
    public DanmakuTouchHelper(IDanmakuView iDanmakuView) {
        this.danmakuView = iDanmakuView;
        this.mTouchDelegate = new GestureDetector(((View) iDanmakuView).getContext(), this.mOnGestureListener);
    }

    public static synchronized DanmakuTouchHelper instance(IDanmakuView iDanmakuView) {
        return new DanmakuTouchHelper(iDanmakuView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean performDanmakuClick(IDanmakus iDanmakus) {
        IDanmakuView.OnDanmakuClickListener onDanmakuClickListener = this.danmakuView.getOnDanmakuClickListener();
        if (onDanmakuClickListener != null) {
            return onDanmakuClickListener.onDanmakuClick(iDanmakus);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean performViewClick() {
        IDanmakuView.OnDanmakuClickListener onDanmakuClickListener = this.danmakuView.getOnDanmakuClickListener();
        if (onDanmakuClickListener != null) {
            return onDanmakuClickListener.onViewClick(this.danmakuView);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IDanmakus touchHitDanmaku(float f, float f2) {
        Danmakus danmakus = new Danmakus();
        this.mDanmakuBounds.setEmpty();
        IDanmakus currentVisibleDanmakus = this.danmakuView.getCurrentVisibleDanmakus();
        if (currentVisibleDanmakus != null && !currentVisibleDanmakus.isEmpty()) {
            IDanmakuIterator it = currentVisibleDanmakus.iterator();
            while (it.hasNext()) {
                BaseDanmaku next = it.next();
                if (next != null) {
                    this.mDanmakuBounds.set(next.getLeft(), next.getTop(), next.getRight(), next.getBottom());
                    if (this.mDanmakuBounds.contains(f, f2)) {
                        danmakus.addItem(next);
                    }
                }
            }
        }
        return danmakus;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mTouchDelegate.onTouchEvent(motionEvent);
    }
}
