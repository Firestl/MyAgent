package io.dcloud.common.adapter.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.gesture.GestureOverlayView;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import io.dcloud.common.adapter.util.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class AdaRootView extends AdaContainerFrameItem {
    public FrameLayout mMyRootView;

    public static class GestureListenerImpl implements GestureOverlayView.OnGestureListener {
        @Override // android.gesture.GestureOverlayView.OnGestureListener
        public void onGesture(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        }

        @Override // android.gesture.GestureOverlayView.OnGestureListener
        public void onGestureCancelled(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        }

        @Override // android.gesture.GestureOverlayView.OnGestureListener
        public void onGestureEnded(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
            gestureOverlayView.getGesture().getStrokes();
        }

        @Override // android.gesture.GestureOverlayView.OnGestureListener
        public void onGestureStarted(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        }
    }

    public class MyRootView extends FrameLayout {
        public AdaRootView mProxy;

        public MyRootView(Context context, AdaRootView adaRootView) {
            super(context);
            this.mProxy = null;
            this.mProxy = adaRootView;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            AdaRootView.this.mViewOptions.onScreenChanged();
        }

        @Override // android.view.View
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            AdaRootView.this.mViewOptions.onScreenChanged(i, i2);
            Logger.d(Logger.LAYOUT_TAG, "AdaRootView onSizeChanged", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        }
    }

    public AdaRootView(Context context, FrameLayout frameLayout) {
        super(context);
        this.mMyRootView = null;
        if (frameLayout != null) {
            this.mMyRootView = frameLayout;
        } else {
            this.mMyRootView = new MyRootView(context, this);
        }
        setMainView(this.mMyRootView);
    }
}
