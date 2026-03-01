package io.dcloud.feature.weex_scroller.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.taobao.weex.ui.component.DCWXScroller;
import com.taobao.weex.ui.view.IWXScroller;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXViewUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DCWXHorizontalScrollView extends HorizontalScrollView implements IWXScroller, WXGestureObservable {
    public boolean isTouch;
    public float lowwer;
    public ScrollViewListener mScrollViewListener;
    public List<ScrollViewListener> mScrollViewListeners;
    public int mScrollX;
    public int mScrollY;
    public boolean scrollable;
    public Field scroller;
    public boolean shouldBeTriggerLower;
    public boolean shouldBeTriggerUpper;
    public float upper;
    public WXGesture wxGesture;

    public interface ScrollViewListener {
        void onScrollChanged(DCWXHorizontalScrollView dCWXHorizontalScrollView, int i, int i2, int i3, int i4);

        void onScrollToBottom();

        void onScrolltoTop();
    }

    public DCWXHorizontalScrollView(Context context) {
        super(context);
        this.scrollable = true;
        this.scroller = null;
        this.isTouch = false;
        this.shouldBeTriggerUpper = true;
        this.shouldBeTriggerLower = true;
        this.upper = 50.0f;
        this.lowwer = 50.0f;
        init();
    }

    private void init() {
        setWillNotDraw(false);
        setOverScrollMode(2);
        try {
            Field declaredField = HorizontalScrollView.class.getDeclaredField("mScroller");
            this.scroller = declaredField;
            declaredField.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private void scrollToBottom() {
        ScrollViewListener scrollViewListener = this.mScrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollToBottom();
        }
        List<ScrollViewListener> list = this.mScrollViewListeners;
        if (list != null) {
            Iterator<ScrollViewListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onScrollToBottom();
            }
        }
    }

    private void scrollToTop() {
        ScrollViewListener scrollViewListener = this.mScrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrolltoTop();
        }
        List<ScrollViewListener> list = this.mScrollViewListeners;
        if (list != null) {
            Iterator<ScrollViewListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onScrolltoTop();
            }
        }
    }

    public void addScrollViewListener(ScrollViewListener scrollViewListener) {
        if (this.mScrollViewListeners == null) {
            this.mScrollViewListeners = new ArrayList();
        }
        if (this.mScrollViewListeners.contains(scrollViewListener)) {
            return;
        }
        this.mScrollViewListeners.add(scrollViewListener);
    }

    @Override // com.taobao.weex.ui.view.IWXScroller
    public void destroy() {
        List<ScrollViewListener> list = this.mScrollViewListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        WXGesture wXGesture = this.wxGesture;
        return wXGesture != null ? zDispatchTouchEvent | wXGesture.onTouch(this, motionEvent) : zDispatchTouchEvent;
    }

    public Rect getContentFrame() {
        return new Rect(0, 0, computeHorizontalScrollRange(), computeVerticalScrollRange());
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public WXGesture getGestureListener() {
        return this.wxGesture;
    }

    public boolean isScrollable() {
        return this.scrollable;
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.mScrollX = getScrollX();
        this.mScrollY = getScrollY();
        if (getScrollX() <= this.upper && !this.shouldBeTriggerUpper) {
            scrollToTop();
            this.shouldBeTriggerUpper = true;
        } else if (getScrollX() > this.upper) {
            this.shouldBeTriggerUpper = false;
        }
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == null) {
            return;
        }
        float right = childAt.getRight() - (getWidth() + this.mScrollX);
        if (right <= this.lowwer && !this.shouldBeTriggerLower) {
            scrollToBottom();
            this.shouldBeTriggerLower = true;
        } else if (right > this.lowwer) {
            this.shouldBeTriggerLower = false;
        }
        ScrollViewListener scrollViewListener = this.mScrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, i, i2, i3 - i, i4 - i2);
        }
        List<ScrollViewListener> list = this.mScrollViewListeners;
        if (list != null) {
            Iterator<ScrollViewListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onScrollChanged(this, i, i2, i3 - i, i4 - i2);
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
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
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.taobao.weex.ui.view.gesture.WXGestureObservable
    public void registerGestureListener(WXGesture wXGesture) {
        this.wxGesture = wXGesture;
    }

    public void removeScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListeners.remove(scrollViewListener);
    }

    public void setLowwerLength(float f) {
        this.lowwer = f;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListener = scrollViewListener;
    }

    public void setScrollable(boolean z) {
        this.scrollable = z;
    }

    public void setUpperLength(float f) {
        this.upper = f;
    }

    public void setWAScroller(DCWXScroller dCWXScroller) {
        this.upper = WXViewUtils.getRealPxByWidth(50.0f, dCWXScroller.getViewPortWidth());
        this.lowwer = WXViewUtils.getRealPxByWidth(50.0f, dCWXScroller.getViewPortWidth());
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

    public DCWXHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scrollable = true;
        this.scroller = null;
        this.isTouch = false;
        this.shouldBeTriggerUpper = true;
        this.shouldBeTriggerLower = true;
        this.upper = 50.0f;
        this.lowwer = 50.0f;
        init();
    }
}
