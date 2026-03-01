package io.dcloud.feature.weex_scroller.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.ui.component.DCWXScroller;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.IWXScroller;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXReflectionUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import supwisdom.ab;
import supwisdom.bb;

/* JADX INFO: loaded from: classes3.dex */
public class DCWXScrollView extends ScrollView implements Handler.Callback, IWXScroller, WXGestureObservable, ab {
    public bb childHelper;
    public int[] consumed;
    public float decelerationRate;
    public boolean isExecScrollerTask;
    public boolean isTouch;
    public float lowwer;
    public int mCheckTime;
    public View mCurrentStickyView;
    public boolean mHasNotDoneActionDown;
    public int mInitialPosition;
    public boolean mRedirectTouchToStickyView;
    public Rect mScrollRect;
    public List<WXScrollViewListener> mScrollViewListeners;
    public int mScrollX;
    public int mScrollY;

    @SuppressLint({"HandlerLeak"})
    public Handler mScrollerTask;
    public int mStickyOffset;
    public int[] mStickyP;
    public DCWXScroller mWAScroller;
    public int[] offsetInWindow;
    public float ox;
    public float oy;
    public boolean scrollable;
    public Field scroller;
    public boolean shouldBeTriggerLower;
    public boolean shouldBeTriggerUpper;
    public int[] stickyScrollerP;
    public int[] stickyViewP;
    public float upper;
    public WXGesture wxGesture;

    public interface WXScrollViewListener {
        void onScroll(DCWXScrollView dCWXScrollView, int i, int i2);

        void onScrollChanged(DCWXScrollView dCWXScrollView, int i, int i2, int i3, int i4);

        void onScrollStopped(DCWXScrollView dCWXScrollView, int i, int i2);

        void onScrollToBottom(DCWXScrollView dCWXScrollView, int i, int i2);

        void onScrollToTop(DCWXScrollView dCWXScrollView, int i, int i2);
    }

    public DCWXScrollView(Context context) {
        super(context);
        this.consumed = new int[2];
        this.offsetInWindow = new int[2];
        this.mHasNotDoneActionDown = true;
        this.mCheckTime = 100;
        this.mStickyP = new int[2];
        this.stickyScrollerP = new int[2];
        this.stickyViewP = new int[2];
        this.scrollable = true;
        this.scroller = null;
        this.isTouch = false;
        this.isExecScrollerTask = false;
        this.decelerationRate = 1.0f;
        this.shouldBeTriggerUpper = true;
        this.shouldBeTriggerLower = true;
        this.upper = 50.0f;
        this.lowwer = 50.0f;
        this.mScrollViewListeners = new ArrayList();
        init();
        try {
            WXReflectionUtils.setValue(this, "mMinimumVelocity", 5);
        } catch (Exception e2) {
            WXLogUtils.e("[WXScrollView] WXScrollView: ", e2);
        }
    }

    private void init() {
        setWillNotDraw(false);
        setOverScrollMode(2);
        bb bbVar = new bb(this);
        this.childHelper = bbVar;
        bbVar.a(true);
        try {
            Field declaredField = ScrollView.class.getDeclaredField("mScroller");
            this.scroller = declaredField;
            declaredField.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private View procSticky(Map<String, Map<String, WXComponent>> map) {
        Map<String, WXComponent> map2;
        if (map == null || (map2 = map.get(this.mWAScroller.getRef())) == null) {
            return null;
        }
        Iterator<Map.Entry<String, WXComponent>> it = map2.entrySet().iterator();
        while (it.hasNext()) {
            WXComponent value = it.next().getValue();
            getLocationOnScreen(this.stickyScrollerP);
            value.getHostView().getLocationOnScreen(this.stickyViewP);
            int height = (value.getParent() == null || value.getParent().getRealView() == null) ? 0 : value.getParent().getRealView().getHeight();
            int height2 = value.getHostView().getHeight();
            int[] iArr = this.stickyScrollerP;
            int i = iArr[1];
            int i2 = (-height) + iArr[1] + height2;
            int[] iArr2 = this.stickyViewP;
            if (iArr2[1] <= i && iArr2[1] >= i2 - height2) {
                this.mStickyOffset = iArr2[1] - i2;
                value.setStickyOffset(iArr2[1] - iArr[1]);
                return value.getHostView();
            }
            value.setStickyOffset(0);
        }
        return null;
    }

    private void showStickyView() {
        DCWXScroller dCWXScroller = this.mWAScroller;
        if (dCWXScroller == null) {
            return;
        }
        View viewProcSticky = procSticky(dCWXScroller.getStickMap());
        if (viewProcSticky != null) {
            this.mCurrentStickyView = viewProcSticky;
        } else {
            this.mCurrentStickyView = null;
        }
    }

    public void addScrollViewListener(WXScrollViewListener wXScrollViewListener) {
        if (this.mScrollViewListeners.contains(wXScrollViewListener)) {
            return;
        }
        this.mScrollViewListeners.add(wXScrollViewListener);
    }

    @Override // com.taobao.weex.ui.view.IWXScroller
    public void destroy() {
        this.mScrollViewListeners.clear();
        Handler handler = this.mScrollerTask;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mCurrentStickyView != null) {
            canvas.save();
            this.mCurrentStickyView.getLocationOnScreen(this.mStickyP);
            int i = this.mStickyOffset;
            if (i > 0) {
                i = 0;
            }
            canvas.translate(this.mStickyP[0], getScrollY() + i);
            canvas.clipRect(0, i, this.mCurrentStickyView.getWidth(), this.mCurrentStickyView.getHeight());
            this.mCurrentStickyView.draw(canvas);
            canvas.restore();
        }
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.childHelper.a(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.childHelper.a(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.childHelper.a(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.childHelper.a(i, i2, i3, i4, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mRedirectTouchToStickyView = true;
        }
        if (this.mRedirectTouchToStickyView) {
            boolean z = false;
            boolean z2 = this.mCurrentStickyView != null;
            this.mRedirectTouchToStickyView = z2;
            if (z2) {
                if (motionEvent.getY() <= this.mCurrentStickyView.getHeight() && motionEvent.getX() >= this.mCurrentStickyView.getLeft() && motionEvent.getX() <= this.mCurrentStickyView.getRight()) {
                    z = true;
                }
                this.mRedirectTouchToStickyView = z;
            }
        }
        if (this.mRedirectTouchToStickyView) {
            if (this.mScrollRect == null) {
                Rect rect = new Rect();
                this.mScrollRect = rect;
                getGlobalVisibleRect(rect);
            }
            this.mCurrentStickyView.getLocationOnScreen(this.stickyViewP);
            motionEvent.offsetLocation(0.0f, this.stickyViewP[1] - this.mScrollRect.top);
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        WXGesture wXGesture = this.wxGesture;
        return wXGesture != null ? zDispatchTouchEvent | wXGesture.onTouch(this, motionEvent) : zDispatchTouchEvent;
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        super.fling((int) (i * this.decelerationRate));
        Handler handler = this.mScrollerTask;
        if (handler != null) {
            handler.removeMessages(0);
        }
        startScrollerTask();
    }

    public Rect getContentFrame() {
        return new Rect(0, 0, computeHorizontalScrollRange(), computeVerticalScrollRange());
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public WXGesture getGestureListener() {
        return this.wxGesture;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return true;
        }
        Handler handler = this.mScrollerTask;
        if (handler != null) {
            handler.removeMessages(0);
        }
        if (this.mInitialPosition - getScrollY() == 0) {
            this.isExecScrollerTask = false;
            onScrollStopped(this, getScrollX(), getScrollY());
            return true;
        }
        onScroll(this, getScrollX(), getScrollY());
        this.mInitialPosition = getScrollY();
        Handler handler2 = this.mScrollerTask;
        if (handler2 == null) {
            return true;
        }
        handler2.sendEmptyMessageDelayed(0, this.mCheckTime);
        return true;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.childHelper.b();
    }

    @Override // android.view.View, supwisdom.ab
    public boolean isNestedScrollingEnabled() {
        return this.childHelper.c();
    }

    public boolean isScrollable() {
        return this.scrollable;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isScrollable()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public void onScroll(DCWXScrollView dCWXScrollView, int i, int i2) {
        List<WXScrollViewListener> list = this.mScrollViewListeners;
        int size = list == null ? 0 : list.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mScrollViewListeners.get(i3).onScroll(this, i, i2);
        }
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        startScrollerTask();
        this.mScrollX = getScrollX();
        int scrollY = getScrollY();
        this.mScrollY = scrollY;
        onScroll(this, this.mScrollX, scrollY);
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == null) {
            return;
        }
        int bottom = childAt.getBottom();
        int height = getHeight();
        int i5 = this.mScrollY;
        float f = bottom - (height + i5);
        if (f <= this.lowwer && !this.shouldBeTriggerLower) {
            this.shouldBeTriggerLower = true;
            onScrollToBottom(this.mScrollX, i5);
        } else if (f > this.lowwer) {
            this.shouldBeTriggerLower = false;
        }
        if (getScrollY() <= this.upper && !this.shouldBeTriggerUpper) {
            this.shouldBeTriggerUpper = true;
            onScrollToTop(this.mScrollX, this.mScrollY);
        } else if (getScrollY() > this.upper) {
            this.shouldBeTriggerUpper = false;
        }
        List<WXScrollViewListener> list = this.mScrollViewListeners;
        int size = list == null ? 0 : list.size();
        for (int i6 = 0; i6 < size; i6++) {
            this.mScrollViewListeners.get(i6).onScrollChanged(this, i, i2, i3 - i, i4 - i2);
        }
        showStickyView();
    }

    public void onScrollStopped(DCWXScrollView dCWXScrollView, int i, int i2) {
        List<WXScrollViewListener> list = this.mScrollViewListeners;
        int size = list == null ? 0 : list.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mScrollViewListeners.get(i3).onScrollStopped(this, i, i2);
        }
    }

    public void onScrollToBottom(int i, int i2) {
        List<WXScrollViewListener> list = this.mScrollViewListeners;
        int size = list == null ? 0 : list.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mScrollViewListeners.get(i3).onScrollToBottom(this, i, i2);
        }
    }

    public void onScrollToTop(int i, int i2) {
        List<WXScrollViewListener> list = this.mScrollViewListeners;
        int size = list == null ? 0 : list.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mScrollViewListeners.get(i3).onScrollToTop(this, i, i2);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.scrollable) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isTouch = true;
        } else if (action == 1 || action == 3) {
            this.isTouch = false;
        }
        if (this.mRedirectTouchToStickyView) {
            if (this.mScrollRect == null) {
                Rect rect = new Rect();
                this.mScrollRect = rect;
                getGlobalVisibleRect(rect);
            }
            this.mCurrentStickyView.getLocationOnScreen(this.stickyViewP);
            motionEvent.offsetLocation(0.0f, -(this.stickyViewP[1] - this.mScrollRect.top));
        }
        if (motionEvent.getAction() == 0) {
            this.mHasNotDoneActionDown = false;
        }
        if (this.mHasNotDoneActionDown) {
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setAction(0);
            this.mHasNotDoneActionDown = false;
            motionEventObtain.recycle();
        }
        if (motionEvent.getAction() == 0) {
            this.ox = motionEvent.getX();
            this.oy = motionEvent.getY();
            startNestedScroll(3);
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.isExecScrollerTask = false;
            this.mHasNotDoneActionDown = true;
            stopNestedScroll();
        }
        if (motionEvent.getAction() == 2 && 19 >= Build.VERSION.SDK_INT) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (dispatchNestedPreScroll((int) (this.ox - x), (int) (this.oy - y), this.consumed, this.offsetInWindow)) {
                int[] iArr = this.consumed;
                motionEvent.setLocation(x + iArr[0], y + iArr[1]);
            }
            this.ox = motionEvent.getX();
            this.oy = motionEvent.getY();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public void registerGestureListener(WXGesture wXGesture) {
        this.wxGesture = wXGesture;
    }

    public void removeScrollViewListener(WXScrollViewListener wXScrollViewListener) {
        this.mScrollViewListeners.remove(wXScrollViewListener);
    }

    public void setLowwerLength(float f) {
        this.lowwer = f;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.childHelper.a(z);
    }

    public void setRate(float f) {
        this.decelerationRate = f;
    }

    public void setScrollable(boolean z) {
        this.scrollable = z;
    }

    public void setUpperLength(float f) {
        this.upper = f;
    }

    public void setWAScroller(DCWXScroller dCWXScroller) {
        this.mWAScroller = dCWXScroller;
        this.upper = WXViewUtils.getRealPxByWidth(50.0f, dCWXScroller.getViewPortWidth());
        this.lowwer = WXViewUtils.getRealPxByWidth(50.0f, this.mWAScroller.getViewPortWidth());
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return this.childHelper.c(i);
    }

    public void startScrollerTask() {
        if (this.isExecScrollerTask) {
            return;
        }
        this.isExecScrollerTask = true;
        if (this.mScrollerTask == null) {
            this.mScrollerTask = new Handler(WXThread.secure(this));
        }
        this.mInitialPosition = getScrollY();
        this.mScrollerTask.sendEmptyMessageDelayed(0, this.mCheckTime);
    }

    @Override // android.view.View, supwisdom.ab
    public void stopNestedScroll() {
        this.childHelper.d();
    }

    public void stopScroll() {
        Field field = this.scroller;
        if (field == null || this.isTouch) {
            return;
        }
        try {
            Method method = field.getType().getMethod("abortAnimation", new Class[0]);
            if (method != null) {
                method.setAccessible(true);
                method.invoke(this.scroller.get(this), new Object[0]);
            }
        } catch (Exception unused) {
        }
    }

    public DCWXScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.consumed = new int[2];
        this.offsetInWindow = new int[2];
        this.mHasNotDoneActionDown = true;
        this.mCheckTime = 100;
        this.mStickyP = new int[2];
        this.stickyScrollerP = new int[2];
        this.stickyViewP = new int[2];
        this.scrollable = true;
        this.scroller = null;
        this.isTouch = false;
        this.isExecScrollerTask = false;
        this.decelerationRate = 1.0f;
        this.shouldBeTriggerUpper = true;
        this.shouldBeTriggerLower = true;
        this.upper = 50.0f;
        this.lowwer = 50.0f;
        init();
    }

    public DCWXScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.consumed = new int[2];
        this.offsetInWindow = new int[2];
        this.mHasNotDoneActionDown = true;
        this.mCheckTime = 100;
        this.mStickyP = new int[2];
        this.stickyScrollerP = new int[2];
        this.stickyViewP = new int[2];
        this.scrollable = true;
        this.scroller = null;
        this.isTouch = false;
        this.isExecScrollerTask = false;
        this.decelerationRate = 1.0f;
        this.shouldBeTriggerUpper = true;
        this.shouldBeTriggerLower = true;
        this.upper = 50.0f;
        this.lowwer = 50.0f;
        setOverScrollMode(2);
    }
}
