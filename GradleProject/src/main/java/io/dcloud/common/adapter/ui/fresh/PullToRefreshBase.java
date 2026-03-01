package io.dcloud.common.adapter.ui.fresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import io.dcloud.common.adapter.ui.fresh.ILoadingLayout;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public abstract class PullToRefreshBase<T extends View> extends LinearLayout implements IPullToRefresh<T> {
    public static final float OFFSET_RADIO = 2.5f;
    public static final int SCROLL_DURATION = 150;
    public final int DOWN;
    public final int LEFT;
    public final int RIGHT;
    public final int UP;
    public String mAppId;
    public boolean mBeginPullRefresh;
    public boolean mCanDoPullDownEvent;
    public int mFooterHeight;
    public LoadingLayout mFooterLayout;
    public int mHeaderHeight;
    public LoadingLayout mHeaderLayout;
    public int mHeaderPullDownMaxHeight;
    public boolean mInterceptEventEnable;
    public boolean mIsHandledTouchEvent;
    public float mLastMotionX;
    public float mLastMotionY;
    public float mLastMotionY_pullup;
    public OnPullUpListener mOnPullUpListener;
    public OnStateChangeListener mOnStateChangeListener;
    public ILoadingLayout.State mPullDownState;
    public boolean mPullLoadEnabled;
    public boolean mPullRefreshEnabled;
    public ILoadingLayout.State mPullUpState;
    public OnRefreshListener<T> mRefreshListener;
    public T mRefreshableView;
    public boolean mScrollLoadEnabled;
    public PullToRefreshBase<T>.SmoothScrollRunnable mSmoothScrollRunnable;
    public int mTouchSlop;

    public interface OnPullUpListener {
        void onPlusScrollBottom();
    }

    public interface OnRefreshListener<V extends View> {
        void onPullDownToRefresh(PullToRefreshBase<V> pullToRefreshBase);

        void onPullUpToRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    public interface OnStateChangeListener {
        void onStateChanged(ILoadingLayout.State state, boolean z);
    }

    public final class SmoothScrollRunnable implements Runnable {
        public final long mDuration;
        public final int mScrollFromY;
        public final int mScrollToY;
        public boolean mContinueRunning = true;
        public long mStartTime = -1;
        public int mCurrentY = -1;
        public final Interpolator mInterpolator = new DecelerateInterpolator();

        public SmoothScrollRunnable(int i, int i2, long j) {
            this.mScrollFromY = i;
            this.mScrollToY = i2;
            this.mDuration = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mDuration <= 0) {
                PullToRefreshBase.this.setScrollTo(0, this.mScrollToY);
                return;
            }
            if (this.mStartTime == -1) {
                this.mStartTime = System.currentTimeMillis();
            } else {
                int iRound = this.mScrollFromY - Math.round((this.mScrollFromY - this.mScrollToY) * this.mInterpolator.getInterpolation(Math.max(Math.min(((System.currentTimeMillis() - this.mStartTime) * 1000) / this.mDuration, 1000L), 0L) / 1000.0f));
                this.mCurrentY = iRound;
                PullToRefreshBase.this.setScrollTo(0, iRound);
            }
            if (!this.mContinueRunning || this.mScrollToY == this.mCurrentY) {
                return;
            }
            PullToRefreshBase.this.postDelayed(this, 16L);
        }

        public void stop() {
            this.mContinueRunning = false;
            PullToRefreshBase.this.removeCallbacks(this);
        }
    }

    public PullToRefreshBase(Context context) {
        super(context);
        this.mLastMotionY = -1.0f;
        this.mLastMotionX = -1.0f;
        this.mCanDoPullDownEvent = false;
        this.mPullRefreshEnabled = true;
        this.mPullLoadEnabled = false;
        this.mScrollLoadEnabled = false;
        this.mInterceptEventEnable = true;
        this.mIsHandledTouchEvent = false;
        ILoadingLayout.State state = ILoadingLayout.State.NONE;
        this.mPullDownState = state;
        this.mPullUpState = state;
        this.mLastMotionY_pullup = -1.0f;
        this.UP = 0;
        this.DOWN = 1;
        this.LEFT = 2;
        this.RIGHT = 3;
        this.mBeginPullRefresh = false;
    }

    private boolean canDoPullDownEvent(float f, float f2) {
        float f3 = this.mLastMotionY;
        if (f2 < f3) {
            return true;
        }
        if (!this.mCanDoPullDownEvent) {
            this.mCanDoPullDownEvent = 1 == getDirectionByAngle(getAngle(this.mLastMotionX, f3, f, f2));
        }
        return this.mCanDoPullDownEvent;
    }

    private double getAngle(float f, float f2, float f3, float f4) {
        return (Math.atan2(f4 - f2, f3 - f) * 180.0d) / 3.141592653589793d;
    }

    private int getDirectionByAngle(double d) {
        if (d < -45.0d && d > -135.0d) {
            return 0;
        }
        if (d >= 45.0d && d < 135.0d) {
            return 1;
        }
        if (d >= 135.0d || d <= -135.0d) {
            return 2;
        }
        return (d < -45.0d || d > 45.0d) ? -1 : 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getScrollYValue() {
        return getScrollY();
    }

    private boolean handlePullUpEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1) {
            float y = motionEvent.getY() - this.mLastMotionY_pullup;
            this.mLastMotionY_pullup = y;
            if (y < -3.0f && isReadyForPullUp()) {
                this.mOnPullUpListener.onPlusScrollBottom();
                return false;
            }
        } else if (action == 0) {
            this.mLastMotionY_pullup = motionEvent.getY();
        }
        return false;
    }

    private void setScrollBy(int i, int i2) {
        scrollBy(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollTo(int i, int i2) {
        scrollTo(i, i2);
    }

    public void addHeaderAndFooter(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LoadingLayout loadingLayout = this.mHeaderLayout;
        LoadingLayout loadingLayout2 = this.mFooterLayout;
        if (loadingLayout != null) {
            if (this == loadingLayout.getParent()) {
                removeView(loadingLayout);
            }
            addView(loadingLayout, 0, layoutParams);
        }
        if (loadingLayout2 != null) {
            if (this == loadingLayout2.getParent()) {
                removeView(loadingLayout2);
            }
            addView(loadingLayout2, -1, layoutParams);
        }
    }

    public void addRefreshableView(T t) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        onAddRefreshableView(layoutParams);
        addView(t, layoutParams);
    }

    public void beginPullRefresh() {
        if (this.mBeginPullRefresh) {
            return;
        }
        postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.3
            public int deltaY = 0;

            @Override // java.lang.Runnable
            public void run() {
                int iAbs = Math.abs(PullToRefreshBase.this.getScrollYValue());
                if (PullToRefreshBase.this.isPullRefreshEnabled() && PullToRefreshBase.this.isReadyForPullDown()) {
                    if (iAbs >= PullToRefreshBase.this.mHeaderHeight) {
                        PullToRefreshBase.this.startRefreshing();
                        PullToRefreshBase.this.mBeginPullRefresh = false;
                    } else {
                        PullToRefreshBase.this.pullHeaderLayout(this.deltaY / 2.5f);
                        this.deltaY += 3;
                        PullToRefreshBase.this.postDelayed(this, 5L);
                    }
                }
            }
        }, 5L);
        this.mBeginPullRefresh = true;
    }

    public LoadingLayout createFooterLoadingLayout(Context context) {
        return null;
    }

    public LoadingLayout createHeaderLoadingLayout(Context context) {
        LoadingLayout loadingLayout = this.mHeaderLayout;
        return loadingLayout == null ? new HeaderLoadingLayout(context) : loadingLayout;
    }

    public void doPullRefreshing(final boolean z, long j) {
        postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.6
            @Override // java.lang.Runnable
            public void run() {
                int i = -PullToRefreshBase.this.mHeaderHeight;
                int i2 = z ? 150 : 0;
                PullToRefreshBase.this.startRefreshing();
                PullToRefreshBase.this.smoothScrollTo(i, i2, 0L);
            }
        }, j);
    }

    public String getAppId() {
        return this.mAppId;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public LoadingLayout getFooterLoadingLayout() {
        return this.mFooterLayout;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public LoadingLayout getHeaderLoadingLayout() {
        return this.mHeaderLayout;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public T getRefreshableView() {
        return this.mRefreshableView;
    }

    public long getSmoothScrollDuration() {
        return 150L;
    }

    public void init(Context context) {
        setOrientation(1);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mHeaderLayout = createHeaderLoadingLayout(context);
        this.mFooterLayout = createFooterLoadingLayout(context);
        addHeaderAndFooter(context);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                PullToRefreshBase.this.refreshLoadingViewsSize();
                PullToRefreshBase.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        smoothScrollTo(0);
    }

    public boolean isInterceptTouchEventEnabled() {
        return this.mInterceptEventEnable;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public boolean isPullLoadEnabled() {
        return this.mPullLoadEnabled && this.mFooterLayout != null;
    }

    public boolean isPullLoading() {
        return this.mPullUpState == ILoadingLayout.State.REFRESHING;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public boolean isPullRefreshEnabled() {
        return this.mPullRefreshEnabled && this.mHeaderLayout != null;
    }

    public boolean isPullRefreshing() {
        return this.mPullDownState == ILoadingLayout.State.REFRESHING;
    }

    public abstract boolean isReadyForPullDown();

    public abstract boolean isReadyForPullUp();

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public boolean isScrollLoadEnabled() {
        return this.mScrollLoadEnabled;
    }

    public void onAddRefreshableView(LinearLayout.LayoutParams layoutParams) {
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (!isInterceptTouchEventEnabled()) {
            return false;
        }
        if (!isPullLoadEnabled() && !isPullRefreshEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.mIsHandledTouchEvent = false;
            this.mCanDoPullDownEvent = false;
            return false;
        }
        if (action != 0 && this.mIsHandledTouchEvent) {
            return true;
        }
        if (action == 0) {
            this.mLastMotionY = motionEvent.getY();
            this.mLastMotionX = motionEvent.getX();
            this.mIsHandledTouchEvent = false;
            this.mCanDoPullDownEvent = false;
        } else if (action == 2 && canDoPullDownEvent(motionEvent.getX(), motionEvent.getY())) {
            float y = motionEvent.getY() - this.mLastMotionY;
            if (Math.abs(y) > this.mTouchSlop || isPullRefreshing() || !isPullLoading()) {
                this.mLastMotionY = motionEvent.getY();
                if (isPullRefreshEnabled() && isReadyForPullDown()) {
                    z = Math.abs(getScrollYValue()) > 0 || y > 0.5f;
                    this.mIsHandledTouchEvent = z;
                    if (z) {
                        this.mRefreshableView.onTouchEvent(motionEvent);
                        requestDisallowInterceptTouchEvent(true);
                    }
                } else if (isPullLoadEnabled() && isReadyForPullUp()) {
                    z = Math.abs(getScrollYValue()) > 0 || y < -0.5f;
                    this.mIsHandledTouchEvent = z;
                    if (z) {
                        requestDisallowInterceptTouchEvent(true);
                    }
                }
            }
        }
        boolean z2 = this.mIsHandledTouchEvent;
        return z2 ? z2 : super.onTouchEvent(motionEvent);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void onPullDownRefreshComplete() {
        if (isPullRefreshing()) {
            ILoadingLayout.State state = ILoadingLayout.State.RESET;
            this.mPullDownState = state;
            onStateChanged(state, true);
            postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.4
                @Override // java.lang.Runnable
                public void run() {
                    PullToRefreshBase.this.setInterceptTouchEventEnabled(true);
                    PullToRefreshBase.this.mHeaderLayout.setState(ILoadingLayout.State.RESET);
                }
            }, getSmoothScrollDuration());
            resetHeaderLayout();
            setInterceptTouchEventEnabled(false);
        }
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void onPullUpRefreshComplete() {
        if (isPullLoading()) {
            ILoadingLayout.State state = ILoadingLayout.State.RESET;
            this.mPullUpState = state;
            onStateChanged(state, false);
            postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.5
                @Override // java.lang.Runnable
                public void run() {
                    PullToRefreshBase.this.setInterceptTouchEventEnabled(true);
                    PullToRefreshBase.this.mFooterLayout.setState(ILoadingLayout.State.RESET);
                }
            }, getSmoothScrollDuration());
            resetFooterLayout();
            setInterceptTouchEventEnabled(false);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        refreshLoadingViewsSize();
        refreshRefreshableViewSize(i, i2);
        post(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.2
            @Override // java.lang.Runnable
            public void run() {
                PullToRefreshBase.this.requestLayout();
            }
        });
    }

    public void onStateChanged(ILoadingLayout.State state, boolean z) {
        OnStateChangeListener onStateChangeListener = this.mOnStateChangeListener;
        if (onStateChangeListener != null) {
            onStateChangeListener.onStateChanged(state, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0088  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.isInterceptTouchEventEnabled()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r5.getAction()
            r2 = 1
            if (r0 == 0) goto L8e
            if (r0 == r2) goto L4e
            r3 = 2
            if (r0 == r3) goto L19
            r5 = 3
            if (r0 == r5) goto L4e
            goto L96
        L19:
            float r0 = r5.getY()
            float r3 = r4.mLastMotionY
            float r0 = r0 - r3
            float r5 = r5.getY()
            r4.mLastMotionY = r5
            boolean r5 = r4.isPullRefreshEnabled()
            r3 = 1075838976(0x40200000, float:2.5)
            if (r5 == 0) goto L3a
            boolean r5 = r4.isReadyForPullDown()
            if (r5 == 0) goto L3a
            float r0 = r0 / r3
            r4.pullHeaderLayout(r0)
        L38:
            r1 = 1
            goto L96
        L3a:
            boolean r5 = r4.isPullLoadEnabled()
            if (r5 == 0) goto L4b
            boolean r5 = r4.isReadyForPullUp()
            if (r5 == 0) goto L4b
            float r0 = r0 / r3
            r4.pullFooterLayout(r0)
            goto L38
        L4b:
            r4.mIsHandledTouchEvent = r1
            goto L96
        L4e:
            boolean r5 = r4.mIsHandledTouchEvent
            if (r5 == 0) goto L88
            r4.mIsHandledTouchEvent = r1
            boolean r5 = r4.isReadyForPullDown()
            if (r5 == 0) goto L6d
            boolean r5 = r4.mPullRefreshEnabled
            if (r5 == 0) goto L68
            io.dcloud.common.adapter.ui.fresh.ILoadingLayout$State r5 = r4.mPullDownState
            io.dcloud.common.adapter.ui.fresh.ILoadingLayout$State r0 = io.dcloud.common.adapter.ui.fresh.ILoadingLayout.State.RELEASE_TO_REFRESH
            if (r5 != r0) goto L68
            r4.startRefreshing()
            goto L69
        L68:
            r2 = 0
        L69:
            r4.resetHeaderLayout()
            goto L89
        L6d:
            boolean r5 = r4.isReadyForPullUp()
            if (r5 == 0) goto L88
            boolean r5 = r4.isPullLoadEnabled()
            if (r5 == 0) goto L83
            io.dcloud.common.adapter.ui.fresh.ILoadingLayout$State r5 = r4.mPullUpState
            io.dcloud.common.adapter.ui.fresh.ILoadingLayout$State r0 = io.dcloud.common.adapter.ui.fresh.ILoadingLayout.State.RELEASE_TO_REFRESH
            if (r5 != r0) goto L83
            r4.startLoading()
            goto L84
        L83:
            r2 = 0
        L84:
            r4.resetFooterLayout()
            goto L89
        L88:
            r2 = 0
        L89:
            r4.requestDisallowInterceptTouchEvent(r1)
            r1 = r2
            goto L96
        L8e:
            float r5 = r5.getY()
            r4.mLastMotionY = r5
            r4.mIsHandledTouchEvent = r1
        L96:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void pullFooterLayout(float f) {
        int scrollYValue = getScrollYValue();
        if (f > 0.0f && scrollYValue - f <= 0.0f) {
            setScrollTo(0, 0);
            return;
        }
        setScrollBy(0, -((int) f));
        if (this.mFooterLayout != null && this.mFooterHeight != 0) {
            this.mFooterLayout.onPull(Math.abs(getScrollYValue()) / this.mFooterHeight);
        }
        int iAbs = Math.abs(getScrollYValue());
        if (!isPullLoadEnabled() || isPullLoading()) {
            return;
        }
        if (iAbs >= this.mFooterHeight) {
            this.mPullUpState = ILoadingLayout.State.RELEASE_TO_REFRESH;
        } else {
            this.mPullUpState = ILoadingLayout.State.PULL_TO_REFRESH;
        }
        this.mFooterLayout.setState(this.mPullUpState);
        onStateChanged(this.mPullUpState, false);
    }

    public void pullHeaderLayout(float f) {
        int scrollYValue = getScrollYValue();
        if (f <= 0.0f || Math.abs(scrollYValue) < this.mHeaderPullDownMaxHeight) {
            if (f < 0.0f && scrollYValue - f >= 0.0f) {
                setScrollTo(0, 0);
                return;
            }
            setScrollBy(0, -((int) f));
            if (this.mHeaderLayout != null && this.mHeaderHeight != 0) {
                this.mHeaderLayout.onPull(Math.abs(getScrollYValue()) / this.mHeaderHeight);
            }
            int iAbs = Math.abs(getScrollYValue());
            if (!isPullRefreshEnabled() || isPullRefreshing()) {
                return;
            }
            if (iAbs >= this.mHeaderHeight) {
                this.mPullDownState = ILoadingLayout.State.RELEASE_TO_REFRESH;
            } else {
                this.mPullDownState = ILoadingLayout.State.PULL_TO_REFRESH;
            }
            this.mHeaderLayout.setState(this.mPullDownState);
            onStateChanged(this.mPullDownState, true);
        }
    }

    public void refreshLoadingViewsSize() {
        int i = this.mHeaderHeight;
        LoadingLayout loadingLayout = this.mFooterLayout;
        int contentSize = loadingLayout != null ? loadingLayout.getContentSize() : 0;
        if (i < 0) {
            i = 0;
        }
        if (contentSize < 0) {
            contentSize = 0;
        }
        this.mHeaderHeight = i;
        this.mFooterHeight = contentSize;
        LoadingLayout loadingLayout2 = this.mHeaderLayout;
        int measuredHeight = loadingLayout2 != null ? loadingLayout2.getMeasuredHeight() : 0;
        Logger.d(Logger.VIEW_VISIBLE_TAG, "PullToRefreshBase.refreshLoadingViewsSize mHeaderHeight=" + this.mHeaderHeight + ";headerHeight=" + measuredHeight);
        LoadingLayout loadingLayout3 = this.mFooterLayout;
        int measuredHeight2 = loadingLayout3 != null ? loadingLayout3.getMeasuredHeight() : 0;
        if (measuredHeight2 == 0) {
            measuredHeight2 = this.mFooterHeight;
        }
        int paddingLeft = getPaddingLeft();
        getPaddingTop();
        int paddingRight = getPaddingRight();
        getPaddingBottom();
        setPadding(paddingLeft, -measuredHeight, paddingRight, -measuredHeight2);
    }

    public void refreshRefreshableViewSize(int i, int i2) {
        T t = this.mRefreshableView;
        if (t != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) t.getLayoutParams();
            if (layoutParams.height != i2) {
                layoutParams.height = i2;
                this.mRefreshableView.requestLayout();
            }
        }
    }

    public void resetFooterLayout() {
        int iAbs = Math.abs(getScrollYValue());
        boolean zIsPullLoading = isPullLoading();
        if (zIsPullLoading && iAbs <= this.mFooterHeight) {
            smoothScrollTo(0);
        } else if (zIsPullLoading) {
            smoothScrollTo(this.mFooterHeight);
        } else {
            smoothScrollTo(0);
        }
    }

    public void resetHeaderLayout() {
        int iAbs = Math.abs(getScrollYValue());
        boolean zIsPullRefreshing = isPullRefreshing();
        if (!zIsPullRefreshing || iAbs > this.mHeaderHeight) {
            if (zIsPullRefreshing) {
                smoothScrollTo(-this.mHeaderHeight);
            } else {
                smoothScrollTo(0);
            }
        }
    }

    public void setAppId(String str) {
        this.mAppId = str;
    }

    public void setHeaderHeight(int i) {
        this.mHeaderHeight = i;
    }

    public void setHeaderPullDownMaxHeight(int i) {
        this.mHeaderPullDownMaxHeight = i;
    }

    public void setInterceptTouchEventEnabled(boolean z) {
        this.mInterceptEventEnable = z;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void setLastUpdatedLabel(CharSequence charSequence) {
        LoadingLayout loadingLayout = this.mHeaderLayout;
        if (loadingLayout != null) {
            loadingLayout.setLastUpdatedLabel(charSequence);
        }
        LoadingLayout loadingLayout2 = this.mFooterLayout;
        if (loadingLayout2 != null) {
            loadingLayout2.setLastUpdatedLabel(charSequence);
        }
    }

    public void setOnOnPullUpListener(OnPullUpListener onPullUpListener) {
        this.mOnPullUpListener = onPullUpListener;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void setOnRefreshListener(OnRefreshListener<T> onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.mOnStateChangeListener = onStateChangeListener;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (1 != i) {
            throw new IllegalArgumentException("This class only supports VERTICAL orientation.");
        }
        super.setOrientation(i);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void setPullLoadEnabled(boolean z) {
        this.mPullLoadEnabled = z;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void setPullRefreshEnabled(boolean z) {
        this.mPullRefreshEnabled = z;
    }

    public void setRefreshableView(T t) {
        this.mRefreshableView = t;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.IPullToRefresh
    public void setScrollLoadEnabled(boolean z) {
        this.mScrollLoadEnabled = z;
    }

    public void smoothScrollTo(int i) {
        smoothScrollTo(i, getSmoothScrollDuration(), 0L);
    }

    public void startLoading() {
        if (isPullLoading()) {
            return;
        }
        ILoadingLayout.State state = ILoadingLayout.State.REFRESHING;
        this.mPullUpState = state;
        onStateChanged(state, false);
        LoadingLayout loadingLayout = this.mFooterLayout;
        if (loadingLayout != null) {
            loadingLayout.setState(state);
        }
        if (this.mRefreshListener != null) {
            postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.8
                @Override // java.lang.Runnable
                public void run() {
                    PullToRefreshBase.this.mRefreshListener.onPullUpToRefresh(PullToRefreshBase.this);
                }
            }, getSmoothScrollDuration());
        }
    }

    public void startRefreshing() {
        if (isPullRefreshing()) {
            return;
        }
        ILoadingLayout.State state = ILoadingLayout.State.REFRESHING;
        this.mPullDownState = state;
        onStateChanged(state, true);
        LoadingLayout loadingLayout = this.mHeaderLayout;
        if (loadingLayout != null) {
            loadingLayout.setState(state);
        }
        if (this.mRefreshListener != null) {
            postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.7
                @Override // java.lang.Runnable
                public void run() {
                    PullToRefreshBase.this.mRefreshListener.onPullDownToRefresh(PullToRefreshBase.this);
                }
            }, getSmoothScrollDuration());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void smoothScrollTo(int i, long j, long j2) {
        PullToRefreshBase<T>.SmoothScrollRunnable smoothScrollRunnable = this.mSmoothScrollRunnable;
        if (smoothScrollRunnable != null) {
            smoothScrollRunnable.stop();
        }
        int scrollYValue = getScrollYValue();
        boolean z = scrollYValue != i;
        if (z) {
            this.mSmoothScrollRunnable = new SmoothScrollRunnable(scrollYValue, i, j);
        }
        if (z) {
            if (j2 > 0) {
                postDelayed(this.mSmoothScrollRunnable, j2);
            } else {
                post(this.mSmoothScrollRunnable);
            }
        }
    }
}
