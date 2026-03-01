package io.dcloud.nineoldandroids.animation;

/* JADX INFO: loaded from: classes3.dex */
public class TimeAnimator extends ValueAnimator {
    public TimeListener mListener;
    public long mPreviousTime = -1;

    public interface TimeListener {
        void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2);
    }

    @Override // io.dcloud.nineoldandroids.animation.ValueAnimator
    public void animateValue(float f) {
    }

    @Override // io.dcloud.nineoldandroids.animation.ValueAnimator
    public boolean animationFrame(long j) {
        if (this.mPlayingState == 0) {
            this.mPlayingState = 1;
            long j2 = this.mSeekTime;
            if (j2 < 0) {
                this.mStartTime = j;
            } else {
                this.mStartTime = j - j2;
                this.mSeekTime = -1L;
            }
        }
        TimeListener timeListener = this.mListener;
        if (timeListener == null) {
            return false;
        }
        long j3 = j - this.mStartTime;
        long j4 = this.mPreviousTime;
        long j5 = j4 >= 0 ? j - j4 : 0L;
        this.mPreviousTime = j;
        timeListener.onTimeUpdate(this, j3, j5);
        return false;
    }

    @Override // io.dcloud.nineoldandroids.animation.ValueAnimator
    public void initAnimation() {
    }

    public void setTimeListener(TimeListener timeListener) {
        this.mListener = timeListener;
    }
}
