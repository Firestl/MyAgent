package pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class RenderTask extends SafeRunnable {
    public RenderTask(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @Override // pl.droidsonroids.gif.SafeRunnable
    public void doWork() {
        GifDrawable gifDrawable = this.mGifDrawable;
        long jRenderFrame = gifDrawable.mNativeInfoHandle.renderFrame(gifDrawable.mBuffer);
        if (jRenderFrame >= 0) {
            this.mGifDrawable.mNextFrameRenderTime = SystemClock.uptimeMillis() + jRenderFrame;
            if (this.mGifDrawable.isVisible() && this.mGifDrawable.mIsRunning) {
                GifDrawable gifDrawable2 = this.mGifDrawable;
                if (!gifDrawable2.mIsRenderingTriggeredOnDraw) {
                    gifDrawable2.mExecutor.remove(this);
                    GifDrawable gifDrawable3 = this.mGifDrawable;
                    gifDrawable3.mRenderTaskSchedule = gifDrawable3.mExecutor.schedule(this, jRenderFrame, TimeUnit.MILLISECONDS);
                }
            }
            if (!this.mGifDrawable.mListeners.isEmpty() && this.mGifDrawable.getCurrentFrameIndex() == this.mGifDrawable.mNativeInfoHandle.getNumberOfFrames() - 1) {
                GifDrawable gifDrawable4 = this.mGifDrawable;
                gifDrawable4.mInvalidationHandler.sendEmptyMessageAtTime(gifDrawable4.getCurrentLoop(), this.mGifDrawable.mNextFrameRenderTime);
            }
        } else {
            GifDrawable gifDrawable5 = this.mGifDrawable;
            gifDrawable5.mNextFrameRenderTime = Long.MIN_VALUE;
            gifDrawable5.mIsRunning = false;
        }
        if (!this.mGifDrawable.isVisible() || this.mGifDrawable.mInvalidationHandler.hasMessages(-1)) {
            return;
        }
        this.mGifDrawable.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
    }
}
