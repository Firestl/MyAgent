package io.dcloud.feature.weex.adapter.widget.refresh;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import supwisdom.du;
import supwisdom.fu;
import supwisdom.gu;
import supwisdom.hu;
import supwisdom.iu;
import supwisdom.ku;
import supwisdom.wu;

/* JADX INFO: loaded from: classes3.dex */
public class DCWeexBaseRefreshLayout extends ViewGroup implements hu, fu {
    public static final int ALPHA_ANIMATION_DURATION = 300;
    public static final int ANIMATE_TO_START_DURATION = 200;
    public static final int ANIMATE_TO_TRIGGER_DURATION = 200;
    public static final int CIRCLE_BG_LIGHT = -328966;
    public static final int CIRCLE_DIAMETER = 40;
    public static final int CIRCLE_DIAMETER_LARGE = 56;
    public static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT = 1;
    public static final int DEFAULT_CIRCLE_TARGET = 64;
    public static final float DRAG_RATE = 0.5f;
    public static final int INVALID_POINTER = -1;
    public static final int LARGE = 0;
    public static final int MAX_ALPHA = 255;
    public static final float MAX_PROGRESS_ANGLE = 0.8f;
    public static final int SCALE_DOWN_DURATION = 150;
    public static final int STARTING_PROGRESS_ALPHA = 76;
    public int mActivePointerId;
    public Animation mAlphaMaxAnimation;
    public Animation mAlphaStartAnimation;
    public final Animation mAnimateToCorrectPosition;
    public final Animation mAnimateToStartPosition;
    public boolean mBeginRefresh;
    public OnChildScrollUpCallback mChildScrollUpCallback;
    public int mCircleDiameter;
    public int mCircleLatyouOffsetTop;
    public CircleLayout mCircleLayout;
    public DCWeexCircleImageView mCircleView;
    public int mCircleViewIndex;
    public int mCurrentTargetOffsetTop;
    public final DecelerateInterpolator mDecelerateInterpolator;
    public int mFrom;
    public float mInitialDownY;
    public float mInitialMotionY;
    public boolean mIsBeingDragged;
    public OnRefreshListener mListener;
    public int mMediumAnimationDuration;
    public boolean mNestedScrollInProgress;
    public final gu mNestedScrollingChildHelper;
    public final iu mNestedScrollingParentHelper;
    public boolean mNotify;
    public int mOriginalOffsetTop;
    public final int[] mParentOffsetInWindow;
    public final int[] mParentScrollConsumed;
    public wu mProgress;
    public Animation.AnimationListener mRefreshListener;
    public boolean mRefreshing;
    public boolean mReturningToStart;
    public boolean mScale;
    public Animation mScaleAnimation;
    public Animation mScaleDownAnimation;
    public Animation mScaleDownToStartAnimation;
    public float mSpinnerFinalOffset;
    public float mStartingScale;
    public View mTarget;
    public float mTotalDragDistance;
    public float mTotalUnconsumed;
    public int mTouchSlop;
    public boolean mUsingCustomStart;
    public static final String LOG_TAG = DCWeexBaseRefreshLayout.class.getSimpleName();
    public static final int[] LAYOUT_ATTRS = {R.attr.enabled};

    public interface OnChildScrollUpCallback {
        boolean canChildScrollUp(DCWeexBaseRefreshLayout dCWeexBaseRefreshLayout, View view);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public DCWeexBaseRefreshLayout(Context context) {
        this(context, null);
    }

    private void animateOffsetToCorrectPosition(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        this.mAnimateToCorrectPosition.reset();
        this.mAnimateToCorrectPosition.setDuration(200L);
        this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
    }

    private void animateOffsetToStartPosition(int i, Animation.AnimationListener animationListener) {
        if (this.mScale) {
            startScaleDownReturnToStartAnimation(i, animationListener);
            return;
        }
        this.mFrom = i;
        this.mAnimateToStartPosition.reset();
        this.mAnimateToStartPosition.setDuration(200L);
        this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }

    private void createProgressView() {
        this.mCircleView = new DCWeexCircleImageView(getContext(), -328966, 20.0f);
        wu wuVar = new wu(getContext(), this);
        this.mProgress = wuVar;
        wuVar.a(-328966);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setVisibility(8);
        CircleLayout circleLayout = new CircleLayout(getContext());
        this.mCircleLayout = circleLayout;
        circleLayout.addView(this.mCircleView);
        this.mCircleLayout.setVisibility(0);
        addView(this.mCircleLayout, new ViewGroup.LayoutParams(-1, -1));
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (!childAt.equals(this.mCircleLayout)) {
                    this.mTarget = childAt;
                    return;
                }
            }
        }
    }

    private void finishSpinner(float f) {
        if (f > this.mTotalDragDistance) {
            setRefreshing(true, true);
            return;
        }
        this.mRefreshing = false;
        this.mProgress.a(0.0f, 0.0f);
        animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, this.mScale ? null : new Animation.AnimationListener() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.6
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (DCWeexBaseRefreshLayout.this.mScale) {
                    return;
                }
                DCWeexBaseRefreshLayout.this.startScaleDownAnimation(null);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.mProgress.a(false);
    }

    private boolean isAlphaUsedForScale() {
        return Build.VERSION.SDK_INT < 11;
    }

    private boolean isAnimationRunning(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveSpinner(float f) {
        this.mProgress.a(true);
        float fMin = Math.min(1.0f, Math.abs(f / this.mTotalDragDistance));
        float fMax = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f) - this.mTotalDragDistance;
        float f2 = this.mUsingCustomStart ? this.mSpinnerFinalOffset - this.mOriginalOffsetTop : this.mSpinnerFinalOffset;
        double dMax = Math.max(0.0f, Math.min(fAbs, f2 * 2.0f) / f2) / 4.0f;
        float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
        int i = this.mOriginalOffsetTop + ((int) ((f2 * fMin) + (f2 * fPow * 2.0f)));
        if (this.mCircleView.getVisibility() != 0) {
            this.mCircleView.setVisibility(0);
        }
        if (!this.mScale) {
            ku.b((View) this.mCircleView, 1.0f);
            ku.c(this.mCircleView, 1.0f);
        }
        if (this.mScale) {
            setAnimationProgress(Math.min(1.0f, f / this.mTotalDragDistance));
        }
        if (f < this.mTotalDragDistance) {
            if (this.mProgress.getAlpha() > 76 && !isAnimationRunning(this.mAlphaStartAnimation)) {
                startProgressAlphaStartAnimation();
            }
        } else if (this.mProgress.getAlpha() < 255 && !isAnimationRunning(this.mAlphaMaxAnimation)) {
            startProgressAlphaMaxAnimation();
        }
        this.mProgress.a(0.0f, Math.min(0.8f, fMax * 0.8f));
        this.mProgress.a(Math.min(1.0f, fMax));
        this.mProgress.b((((fMax * 0.4f) - 0.25f) + (fPow * 2.0f)) * 0.5f);
        setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToStart(float f) {
        setTargetOffsetTopAndBottom((this.mFrom + ((int) ((this.mOriginalOffsetTop - r0) * f))) - this.mCircleView.getTop(), false);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int iA = du.a(motionEvent);
        if (motionEvent.getPointerId(iA) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(iA == 0 ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.mCircleView.clearAnimation();
        this.mProgress.stop();
        this.mCircleView.setVisibility(8);
        setColorViewAlpha(255);
        if (this.mScale) {
            setAnimationProgress(0.0f);
        } else {
            setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCurrentTargetOffsetTop, true);
        }
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimationProgress(float f) {
        if (isAlphaUsedForScale()) {
            setColorViewAlpha((int) (f * 255.0f));
        } else {
            ku.b(this.mCircleView, f);
            ku.c(this.mCircleView, f);
        }
    }

    private void setColorViewAlpha(int i) {
        this.mCircleView.getBackground().setAlpha(i);
        this.mProgress.setAlpha(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetOffsetTopAndBottom(int i, boolean z) {
        this.mCircleLayout.bringToFront();
        ku.b((View) this.mCircleView, i);
        this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
        if (!z || Build.VERSION.SDK_INT >= 11) {
            return;
        }
        invalidate();
    }

    private Animation startAlphaAnimation(final int i, final int i2) {
        if (this.mScale && isAlphaUsedForScale()) {
            return null;
        }
        Animation animation = new Animation() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.5
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                DCWeexBaseRefreshLayout.this.mProgress.setAlpha((int) (i + ((i2 - r0) * f)));
            }
        };
        animation.setDuration(300L);
        this.mCircleView.setAnimationListener(null);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(animation);
        return animation;
    }

    private void startDragging(float f) {
        float f2 = this.mInitialDownY;
        float f3 = f - f2;
        int i = this.mTouchSlop;
        if (f3 <= i || this.mIsBeingDragged) {
            return;
        }
        this.mInitialMotionY = f2 + i;
        this.mIsBeingDragged = true;
        this.mProgress.setAlpha(76);
    }

    private void startProgressAlphaMaxAnimation() {
        this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 255);
    }

    private void startProgressAlphaStartAnimation() {
        this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScaleDownAnimation(Animation.AnimationListener animationListener) {
        Animation animation = new Animation() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.4
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                DCWeexBaseRefreshLayout.this.setAnimationProgress(1.0f - f);
            }
        };
        this.mScaleDownAnimation = animation;
        animation.setDuration(150L);
        this.mCircleView.setAnimationListener(animationListener);
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownAnimation);
    }

    private void startScaleDownReturnToStartAnimation(int i, Animation.AnimationListener animationListener) {
        this.mFrom = i;
        if (isAlphaUsedForScale()) {
            this.mStartingScale = this.mProgress.getAlpha();
        } else {
            this.mStartingScale = ku.a(this.mCircleView);
        }
        Animation animation = new Animation() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.9
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                DCWeexBaseRefreshLayout.this.setAnimationProgress(DCWeexBaseRefreshLayout.this.mStartingScale + ((-DCWeexBaseRefreshLayout.this.mStartingScale) * f));
                DCWeexBaseRefreshLayout.this.moveToStart(f);
            }
        };
        this.mScaleDownToStartAnimation = animation;
        animation.setDuration(150L);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
    }

    private void startScaleUpAnimation(Animation.AnimationListener animationListener) {
        this.mCircleView.setVisibility(0);
        if (Build.VERSION.SDK_INT >= 11) {
            this.mProgress.setAlpha(255);
        }
        Animation animation = new Animation() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.3
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                DCWeexBaseRefreshLayout.this.setAnimationProgress(f);
            }
        };
        this.mScaleAnimation = animation;
        animation.setDuration(this.mMediumAnimationDuration);
        if (animationListener != null) {
            this.mCircleView.setAnimationListener(animationListener);
        }
        this.mCircleView.clearAnimation();
        this.mCircleView.startAnimation(this.mScaleAnimation);
    }

    public void beginRefresh() {
        if (this.mBeginRefresh || this.mCircleView.getVisibility() == 0) {
            return;
        }
        post(new Runnable() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.2
            public int offset = 0;

            @Override // java.lang.Runnable
            public void run() {
                int i = this.offset;
                float f = i;
                DCWeexBaseRefreshLayout dCWeexBaseRefreshLayout = DCWeexBaseRefreshLayout.this;
                if (f >= dCWeexBaseRefreshLayout.mTotalDragDistance) {
                    dCWeexBaseRefreshLayout.setRefreshing(true, true);
                    DCWeexBaseRefreshLayout.this.mBeginRefresh = false;
                } else {
                    dCWeexBaseRefreshLayout.moveSpinner(i);
                    DCWeexBaseRefreshLayout.this.postDelayed(this, 1L);
                    this.offset += 15;
                }
            }
        });
        this.mBeginRefresh = true;
    }

    public boolean canChildScrollUp() {
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view == null) {
            return false;
        }
        OnChildScrollUpCallback onChildScrollUpCallback = this.mChildScrollUpCallback;
        if (onChildScrollUpCallback != null) {
            return onChildScrollUpCallback.canChildScrollUp(this, view);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            return ku.a(view, -1);
        }
        if (!(view instanceof AbsListView)) {
            return ku.a(view, -1) || this.mTarget.getScrollY() > 0;
        }
        AbsListView absListView = (AbsListView) view;
        if (absListView.getChildCount() > 0) {
            return absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop();
        }
        return false;
    }

    public void clearTarget() {
        this.mTarget = null;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mNestedScrollingChildHelper.a(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mNestedScrollingChildHelper.a(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.a(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mNestedScrollingChildHelper.a(i, i2, i3, i4, iArr);
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        int i3 = this.mCircleViewIndex;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.a();
    }

    public int getProgressCircleDiameter() {
        return this.mCircleDiameter;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.a();
    }

    @Override // android.view.View, supwisdom.fu
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.b();
    }

    public boolean isRefreshing() {
        return this.mRefreshing;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        reset();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0058  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r4.ensureTarget()
            int r0 = supwisdom.du.b(r5)
            boolean r1 = r4.mReturningToStart
            r2 = 0
            if (r1 == 0) goto L10
            if (r0 != 0) goto L10
            r4.mReturningToStart = r2
        L10:
            boolean r1 = r4.isEnabled()
            if (r1 == 0) goto L81
            boolean r1 = r4.mReturningToStart
            if (r1 != 0) goto L81
            boolean r1 = r4.canChildScrollUp()
            if (r1 != 0) goto L81
            boolean r1 = r4.mRefreshing
            if (r1 != 0) goto L81
            boolean r1 = r4.mNestedScrollInProgress
            if (r1 == 0) goto L29
            goto L81
        L29:
            r1 = 1
            if (r0 == 0) goto L5d
            r3 = -1
            if (r0 == r1) goto L58
            r1 = 2
            if (r0 == r1) goto L3d
            r1 = 3
            if (r0 == r1) goto L58
            r1 = 6
            if (r0 == r1) goto L39
            goto L7e
        L39:
            r4.onSecondaryPointerUp(r5)
            goto L7e
        L3d:
            int r0 = r4.mActivePointerId
            if (r0 != r3) goto L49
            java.lang.String r5 = io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.LOG_TAG
            java.lang.String r0 = "Got ACTION_MOVE event but don't have an active pointer id."
            android.util.Log.e(r5, r0)
            return r2
        L49:
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L50
            return r2
        L50:
            float r5 = r5.getY(r0)
            r4.startDragging(r5)
            goto L7e
        L58:
            r4.mIsBeingDragged = r2
            r4.mActivePointerId = r3
            goto L7e
        L5d:
            int r0 = r4.mOriginalOffsetTop
            io.dcloud.feature.weex.adapter.widget.refresh.DCWeexCircleImageView r3 = r4.mCircleView
            int r3 = r3.getTop()
            int r0 = r0 - r3
            r4.setTargetOffsetTopAndBottom(r0, r1)
            int r0 = r5.getPointerId(r2)
            r4.mActivePointerId = r0
            r4.mIsBeingDragged = r2
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L78
            return r2
        L78:
            float r5 = r5.getY(r0)
            r4.mInitialDownY = r5
        L7e:
            boolean r5 = r4.mIsBeingDragged
            return r5
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingLeft2 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int i5 = paddingLeft2 + paddingLeft;
        int paddingTop2 = ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop;
        this.mCircleLayout.layout(paddingLeft, this.mCircleLatyouOffsetTop + paddingTop, i5, paddingTop2);
        view.layout(paddingLeft, paddingTop, i5, paddingTop2);
        int measuredWidth2 = this.mCircleView.getMeasuredWidth();
        int measuredHeight2 = this.mCircleView.getMeasuredHeight();
        int i6 = measuredWidth / 2;
        int i7 = measuredWidth2 / 2;
        int i8 = this.mCurrentTargetOffsetTop;
        this.mCircleView.layout(i6 - i7, i8, i6 + i7, measuredHeight2 + i8);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mTarget == null) {
            ensureTarget();
        }
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824));
        this.mCircleViewIndex = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.mCircleLayout) {
                this.mCircleViewIndex = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            float f = this.mTotalUnconsumed;
            if (f > 0.0f) {
                float f2 = i2;
                if (f2 > f) {
                    iArr[1] = i2 - ((int) f);
                    this.mTotalUnconsumed = 0.0f;
                } else {
                    this.mTotalUnconsumed = f - f2;
                    iArr[1] = i2;
                }
                moveSpinner(this.mTotalUnconsumed);
            }
        }
        if (this.mUsingCustomStart && i2 > 0 && this.mTotalUnconsumed == 0.0f && Math.abs(i2 - iArr[1]) > 0) {
            this.mCircleView.setVisibility(8);
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        if (i4 + this.mParentOffsetInWindow[1] >= 0 || canChildScrollUp()) {
            return;
        }
        float fAbs = this.mTotalUnconsumed + Math.abs(r11);
        this.mTotalUnconsumed = fAbs;
        moveSpinner(fAbs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mNestedScrollingParentHelper.a(view, view2, i);
        startNestedScroll(i & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (!isEnabled() || this.mReturningToStart || this.mRefreshing || (i & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, supwisdom.hu
    public void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.a(view);
        this.mNestedScrollInProgress = false;
        float f = this.mTotalUnconsumed;
        if (f > 0.0f) {
            finishSpinner(f);
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int iB = du.b(motionEvent);
        Boolean bool = false;
        if (this.mReturningToStart && iB == 0) {
            this.mReturningToStart = false;
        }
        if (!isEnabled() || this.mReturningToStart || canChildScrollUp() || this.mRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        if (iB != 0) {
            if (iB == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                } else {
                    if (this.mIsBeingDragged) {
                        float y = (motionEvent.getY(iFindPointerIndex) - this.mInitialMotionY) * 0.5f;
                        this.mIsBeingDragged = false;
                        finishSpinner(y);
                    }
                    this.mActivePointerId = -1;
                }
            } else if (iB == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex2 < 0) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                } else {
                    float y2 = motionEvent.getY(iFindPointerIndex2);
                    startDragging(y2);
                    if (this.mIsBeingDragged) {
                        float f = (y2 - this.mInitialMotionY) * 0.5f;
                        if (f > 0.0f) {
                            moveSpinner(f);
                        }
                    }
                }
            } else if (iB != 3) {
                if (iB == 5) {
                    int iA = du.a(motionEvent);
                    if (iA < 0) {
                        Log.e(LOG_TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.mActivePointerId = motionEvent.getPointerId(iA);
                } else if (iB == 6) {
                    onSecondaryPointerUp(motionEvent);
                }
            }
            return bool.booleanValue();
        }
        this.mActivePointerId = motionEvent.getPointerId(0);
        this.mIsBeingDragged = false;
        bool = true;
        return bool.booleanValue();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) {
            View view = this.mTarget;
            if (view == null || ku.b(view)) {
                super.requestDisallowInterceptTouchEvent(z);
            }
        }
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        ensureTarget();
        this.mProgress.a(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.mTotalDragDistance = i;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        reset();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedScrollingChildHelper.a(z);
    }

    public void setOnChildScrollUpCallback(OnChildScrollUpCallback onChildScrollUpCallback) {
        this.mChildScrollUpCallback = onChildScrollUpCallback;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mListener = onRefreshListener;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.mCircleView.setBackgroundColor(i);
        this.mProgress.a(i);
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i));
    }

    public void setProgressViewEndTarget(boolean z, int i) {
        this.mSpinnerFinalOffset = i;
        this.mScale = z;
        this.mCircleView.invalidate();
    }

    public void setProgressViewOffset(boolean z, int i, int i2, int i3, int i4) {
        this.mScale = z;
        this.mOriginalOffsetTop = i;
        this.mCircleLatyouOffsetTop = i4;
        this.mSpinnerFinalOffset = i2;
        this.mTotalDragDistance = i3;
        this.mUsingCustomStart = true;
        reset();
        this.mRefreshing = false;
    }

    public void setRefreshing(boolean z) {
        if (!z || this.mRefreshing == z) {
            setRefreshing(z, false);
            return;
        }
        this.mRefreshing = z;
        setTargetOffsetTopAndBottom(((int) (!this.mUsingCustomStart ? this.mSpinnerFinalOffset + this.mOriginalOffsetTop : this.mSpinnerFinalOffset)) - this.mCurrentTargetOffsetTop, true);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.mCircleDiameter = (int) (displayMetrics.density * 56.0f);
            } else {
                this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
            }
            this.mCircleView.setImageDrawable(null);
            this.mProgress.b(i);
            this.mCircleView.setImageDrawable(this.mProgress);
        }
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return this.mNestedScrollingChildHelper.a(i);
    }

    @Override // android.view.View, supwisdom.fu
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.c();
    }

    public DCWeexBaseRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRefreshing = false;
        this.mTotalDragDistance = -1.0f;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mActivePointerId = -1;
        this.mCircleLatyouOffsetTop = 0;
        this.mCircleViewIndex = -1;
        this.mRefreshListener = new Animation.AnimationListener() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (!DCWeexBaseRefreshLayout.this.mRefreshing) {
                    DCWeexBaseRefreshLayout.this.reset();
                    return;
                }
                DCWeexBaseRefreshLayout.this.mProgress.setAlpha(255);
                DCWeexBaseRefreshLayout.this.mProgress.start();
                if (DCWeexBaseRefreshLayout.this.mNotify && DCWeexBaseRefreshLayout.this.mListener != null) {
                    DCWeexBaseRefreshLayout.this.mListener.onRefresh();
                }
                DCWeexBaseRefreshLayout dCWeexBaseRefreshLayout = DCWeexBaseRefreshLayout.this;
                dCWeexBaseRefreshLayout.mCurrentTargetOffsetTop = dCWeexBaseRefreshLayout.mCircleView.getTop();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
        this.mBeginRefresh = false;
        this.mAnimateToCorrectPosition = new Animation() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.7
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                int iAbs;
                if (DCWeexBaseRefreshLayout.this.mUsingCustomStart) {
                    iAbs = (int) DCWeexBaseRefreshLayout.this.mSpinnerFinalOffset;
                } else {
                    iAbs = (int) (DCWeexBaseRefreshLayout.this.mSpinnerFinalOffset - Math.abs(r4.mOriginalOffsetTop));
                }
                DCWeexBaseRefreshLayout dCWeexBaseRefreshLayout = DCWeexBaseRefreshLayout.this;
                DCWeexBaseRefreshLayout.this.setTargetOffsetTopAndBottom((dCWeexBaseRefreshLayout.mFrom + ((int) ((iAbs - r1) * f))) - dCWeexBaseRefreshLayout.mCircleView.getTop(), false);
                DCWeexBaseRefreshLayout.this.mProgress.a(1.0f - f);
            }
        };
        this.mAnimateToStartPosition = new Animation() { // from class: io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout.8
            @Override // android.view.animation.Animation
            public void applyTransformation(float f, Transformation transformation) {
                DCWeexBaseRefreshLayout.this.moveToStart(f);
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMediumAnimationDuration = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
        createProgressView();
        ku.a((ViewGroup) this, true);
        float f = displayMetrics.density * 64.0f;
        this.mSpinnerFinalOffset = f;
        this.mTotalDragDistance = f;
        this.mNestedScrollingParentHelper = new iu(this);
        this.mNestedScrollingChildHelper = new gu(this);
        setNestedScrollingEnabled(true);
        int i = -this.mCircleDiameter;
        this.mCurrentTargetOffsetTop = i;
        this.mOriginalOffsetTop = i;
        moveToStart(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshing(boolean z, boolean z2) {
        if (this.mRefreshing != z) {
            this.mNotify = z2;
            ensureTarget();
            this.mRefreshing = z;
            if (z) {
                animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
            } else {
                startScaleDownAnimation(this.mRefreshListener);
            }
        }
    }
}
