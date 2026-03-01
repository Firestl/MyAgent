package master.flame.danmaku.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import java.util.LinkedList;
import java.util.Locale;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.DrawHelper;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.controller.IDanmakuViewController;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.util.SystemClock;

/* JADX INFO: loaded from: classes3.dex */
public class DanmakuSurfaceView extends SurfaceView implements IDanmakuView, IDanmakuViewController, SurfaceHolder.Callback {
    public static final int MAX_RECORD_SIZE = 50;
    public static final int ONE_SECOND = 1000;
    public static final String TAG = "DanmakuSurfaceView";
    public DrawHandler handler;
    public boolean isSurfaceCreated;
    public DrawHandler.Callback mCallback;
    public boolean mDanmakuVisible;
    public LinkedList<Long> mDrawTimes;
    public int mDrawingThreadType;
    public boolean mEnableDanmakuDrwaingCache;
    public HandlerThread mHandlerThread;
    public IDanmakuView.OnDanmakuClickListener mOnDanmakuClickListener;
    public boolean mShowFps;
    public SurfaceHolder mSurfaceHolder;
    public DanmakuTouchHelper mTouchHelper;

    public DanmakuSurfaceView(Context context) {
        super(context);
        this.mEnableDanmakuDrwaingCache = true;
        this.mDanmakuVisible = true;
        this.mDrawingThreadType = 0;
        init();
    }

    private float fps() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mDrawTimes.addLast(Long.valueOf(jUptimeMillis));
        Long lPeekFirst = this.mDrawTimes.peekFirst();
        if (lPeekFirst == null) {
            return 0.0f;
        }
        float fLongValue = jUptimeMillis - lPeekFirst.longValue();
        if (this.mDrawTimes.size() > 50) {
            this.mDrawTimes.removeFirst();
        }
        if (fLongValue > 0.0f) {
            return (this.mDrawTimes.size() * 1000) / fLongValue;
        }
        return 0.0f;
    }

    private void init() {
        setZOrderMediaOverlay(true);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        this.mSurfaceHolder.setFormat(-2);
        DrawHelper.useDrawColorToClearCanvas(true, true);
        this.mTouchHelper = DanmakuTouchHelper.instance(this);
    }

    private void prepare() {
        if (this.handler == null) {
            this.handler = new DrawHandler(getLooper(this.mDrawingThreadType), this, this.mDanmakuVisible);
        }
    }

    private void stopDraw() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.quit();
            this.handler = null;
        }
        HandlerThread handlerThread = this.mHandlerThread;
        this.mHandlerThread = null;
        if (handlerThread != null) {
            try {
                handlerThread.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            handlerThread.quit();
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void addDanmaku(BaseDanmaku baseDanmaku) {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.addDanmaku(baseDanmaku);
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuViewController
    public void clear() {
        Canvas canvasLockCanvas;
        if (isViewReady() && (canvasLockCanvas = this.mSurfaceHolder.lockCanvas()) != null) {
            DrawHelper.clearCanvas(canvasLockCanvas);
            this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void clearDanmakusOnScreen() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.clearDanmakusOnScreen();
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuViewController
    public long drawDanmakus() {
        if (!this.isSurfaceCreated) {
            return 0L;
        }
        if (!isShown()) {
            return -1L;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        Canvas canvasLockCanvas = this.mSurfaceHolder.lockCanvas();
        if (canvasLockCanvas != null) {
            DrawHandler drawHandler = this.handler;
            if (drawHandler != null) {
                IRenderer.RenderingState renderingStateDraw = drawHandler.draw(canvasLockCanvas);
                if (this.mShowFps) {
                    if (this.mDrawTimes == null) {
                        this.mDrawTimes = new LinkedList<>();
                    }
                    SystemClock.uptimeMillis();
                    DrawHelper.drawFPS(canvasLockCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", Float.valueOf(fps()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(renderingStateDraw.cacheHitCount), Long.valueOf(renderingStateDraw.cacheMissCount)));
                }
            }
            if (this.isSurfaceCreated) {
                this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
            }
        }
        return SystemClock.uptimeMillis() - jUptimeMillis;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void enableDanmakuDrawingCache(boolean z) {
        this.mEnableDanmakuDrwaingCache = z;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public DanmakuContext getConfig() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler == null) {
            return null;
        }
        return drawHandler.getConfig();
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public long getCurrentTime() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            return drawHandler.getCurrentTime();
        }
        return 0L;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public IDanmakus getCurrentVisibleDanmakus() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            return drawHandler.getCurrentVisibleDanmakus();
        }
        return null;
    }

    public Looper getLooper(int i) {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
        if (i == 1) {
            return Looper.getMainLooper();
        }
        int i2 = i != 2 ? i != 3 ? 0 : 19 : -8;
        HandlerThread handlerThread2 = new HandlerThread("DFM Handler Thread #" + i2, i2);
        this.mHandlerThread = handlerThread2;
        handlerThread2.start();
        return this.mHandlerThread.getLooper();
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public IDanmakuView.OnDanmakuClickListener getOnDanmakuClickListener() {
        return this.mOnDanmakuClickListener;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public View getView() {
        return this;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void hide() {
        this.mDanmakuVisible = false;
        DrawHandler drawHandler = this.handler;
        if (drawHandler == null) {
            return;
        }
        drawHandler.hideDanmakus(false);
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public long hideAndPauseDrawTask() {
        this.mDanmakuVisible = false;
        DrawHandler drawHandler = this.handler;
        if (drawHandler == null) {
            return 0L;
        }
        return drawHandler.hideDanmakus(true);
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.invalidateDanmaku(baseDanmaku, z);
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView, master.flame.danmaku.controller.IDanmakuViewController
    public boolean isDanmakuDrawingCacheEnabled() {
        return this.mEnableDanmakuDrwaingCache;
    }

    @Override // android.view.View, master.flame.danmaku.controller.IDanmakuView, master.flame.danmaku.controller.IDanmakuViewController
    public boolean isHardwareAccelerated() {
        return false;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public boolean isPaused() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            return drawHandler.isStop();
        }
        return false;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public boolean isPrepared() {
        DrawHandler drawHandler = this.handler;
        return drawHandler != null && drawHandler.isPrepared();
    }

    @Override // android.view.View, master.flame.danmaku.controller.IDanmakuView
    public boolean isShown() {
        return this.mDanmakuVisible && super.isShown();
    }

    @Override // master.flame.danmaku.controller.IDanmakuViewController
    public boolean isViewReady() {
        return this.isSurfaceCreated;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = this.mTouchHelper.onTouchEvent(motionEvent);
        return !zOnTouchEvent ? super.onTouchEvent(motionEvent) : zOnTouchEvent;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void pause() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.pause();
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void release() {
        stop();
        LinkedList<Long> linkedList = this.mDrawTimes;
        if (linkedList != null) {
            linkedList.clear();
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void removeAllDanmakus(boolean z) {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.removeAllDanmakus(z);
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void removeAllLiveDanmakus() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.removeAllLiveDanmakus();
        }
    }

    public void restart() {
        stop();
        start();
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void resume() {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null && drawHandler.isPrepared()) {
            this.handler.resume();
        } else if (this.handler == null) {
            restart();
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void seekTo(Long l) {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.seekTo(l);
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void setCallback(DrawHandler.Callback callback) {
        this.mCallback = callback;
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.setCallback(callback);
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void setDrawingThreadType(int i) {
        this.mDrawingThreadType = i;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void setOnDanmakuClickListener(IDanmakuView.OnDanmakuClickListener onDanmakuClickListener) {
        this.mOnDanmakuClickListener = onDanmakuClickListener;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void show() {
        showAndResumeDrawTask(null);
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void showAndResumeDrawTask(Long l) {
        this.mDanmakuVisible = true;
        DrawHandler drawHandler = this.handler;
        if (drawHandler == null) {
            return;
        }
        drawHandler.showDanmakus(l);
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void showFPS(boolean z) {
        this.mShowFps = z;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void start() {
        start(0L);
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void stop() {
        stopDraw();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        DrawHandler drawHandler = this.handler;
        if (drawHandler != null) {
            drawHandler.notifyDispSizeChanged(i2, i3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.isSurfaceCreated = true;
        Canvas canvasLockCanvas = surfaceHolder.lockCanvas();
        if (canvasLockCanvas != null) {
            DrawHelper.clearCanvas(canvasLockCanvas);
            surfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.isSurfaceCreated = false;
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void toggle() {
        if (this.isSurfaceCreated) {
            DrawHandler drawHandler = this.handler;
            if (drawHandler == null) {
                start();
            } else if (drawHandler.isStop()) {
                resume();
            } else {
                pause();
            }
        }
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void start(long j) {
        DrawHandler drawHandler = this.handler;
        if (drawHandler == null) {
            prepare();
        } else {
            drawHandler.removeCallbacksAndMessages(null);
        }
        this.handler.obtainMessage(1, Long.valueOf(j)).sendToTarget();
    }

    @Override // master.flame.danmaku.controller.IDanmakuView
    public void prepare(BaseDanmakuParser baseDanmakuParser, DanmakuContext danmakuContext) {
        prepare();
        this.handler.setConfig(danmakuContext);
        this.handler.setParser(baseDanmakuParser);
        this.handler.setCallback(this.mCallback);
        this.handler.prepare();
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableDanmakuDrwaingCache = true;
        this.mDanmakuVisible = true;
        this.mDrawingThreadType = 0;
        init();
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnableDanmakuDrwaingCache = true;
        this.mDanmakuVisible = true;
        this.mDrawingThreadType = 0;
        init();
    }
}
