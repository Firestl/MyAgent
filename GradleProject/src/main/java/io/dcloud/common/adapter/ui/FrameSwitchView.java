package io.dcloud.common.adapter.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.FrameBitmapView;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FrameSwitchView implements IReflectAble {
    public static final String POP_IN = "pop-in";
    public static final String POP_OUT = "pop-out";
    public static final String SLIDE_IN_RIGHT = "slide-in-right";
    public static final String SLIDE_OUT_RIGHT = "slide-out-right";
    public static FrameSwitchView mInstance;
    public Activity mActivity;
    public int mAppScreenHeight;
    public int mAppScreenWidth;
    public FrameBitmapView mLeftFrameBpView;
    public View mLeftView;
    public FrameBitmapView mRightFrameBpView;
    public View mRightView;
    public SwitchLayout mSwitchLayout;
    public boolean isInit = false;
    public int mDuration = 300;
    public boolean isVisibility = false;
    public String mAniType = "pop-in";
    public IWebview mWebViewImpl = null;
    public String mCallbackId = null;
    public boolean isRuning = false;
    public FrameBitmapView.ClearAnimationListener clearAnimationListener = new FrameBitmapView.ClearAnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.7
        @Override // io.dcloud.common.adapter.ui.FrameBitmapView.ClearAnimationListener
        public void onAnimationEnd() {
            FrameSwitchView.this.endRefreshView();
        }
    };

    public class SwitchLayout extends RelativeLayout implements IWaiter {
        public SwitchLayout(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (!TextUtils.isEmpty(FrameSwitchView.this.mAniType)) {
                if (FrameSwitchView.this.mAniType.equals("pop-in") && FrameSwitchView.this.mRightView != null && (FrameSwitchView.this.mRightView instanceof IWaiter)) {
                    return ((Boolean) ((IWaiter) FrameSwitchView.this.mRightView).doForFeature("checkTouch", motionEvent)).booleanValue();
                }
                if ((FrameSwitchView.this.mAniType.equals("slide-in-right") || FrameSwitchView.this.mAniType.equals("slide-out-right") || FrameSwitchView.this.mAniType.equals("pop-out")) && FrameSwitchView.this.mLeftView != null && (FrameSwitchView.this.mLeftView instanceof IWaiter)) {
                    return ((Boolean) ((IWaiter) FrameSwitchView.this.mLeftView).doForFeature("checkTouch", motionEvent)).booleanValue();
                }
                if ((FrameSwitchView.this.mLeftFrameBpView != null && FrameSwitchView.this.mLeftFrameBpView.isInit()) || (FrameSwitchView.this.mRightFrameBpView != null && FrameSwitchView.this.mRightFrameBpView.isInit())) {
                    return super.dispatchTouchEvent(motionEvent);
                }
            }
            return FrameSwitchView.this.isRuning;
        }

        @Override // io.dcloud.common.DHInterface.IWaiter
        public Object doForFeature(String str, Object obj) {
            return null;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            if (FrameSwitchView.this.mWebViewImpl == null || !FrameSwitchView.this.isVisibility) {
                return;
            }
            FrameSwitchView.this.stopAnimation();
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (getVisibility() != 0) {
                return super.onTouchEvent(motionEvent);
            }
            if (FrameSwitchView.this.mLeftFrameBpView != null && FrameSwitchView.this.mLeftFrameBpView.isInit()) {
                return true;
            }
            if (FrameSwitchView.this.mRightFrameBpView == null || !FrameSwitchView.this.mRightFrameBpView.isInit()) {
                return super.onTouchEvent(motionEvent);
            }
            return true;
        }
    }

    public FrameSwitchView() {
    }

    private void addView(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup instanceof SwitchLayout) {
            return;
        }
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        this.mSwitchLayout.addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endAnimationLayout(View view, int i) {
        int left = view.getLeft() + i;
        int top = view.getTop();
        int width = view.getWidth();
        int height = view.getHeight();
        view.clearAnimation();
        view.layout(left, top, width + left, height + top);
    }

    private TranslateAnimation getAnimation(int i, int i2, int i3, Animation.AnimationListener animationListener) {
        TranslateAnimation translateAnimation = new TranslateAnimation(i, i2, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setDuration(i3);
        translateAnimation.setAnimationListener(animationListener);
        return translateAnimation;
    }

    public static FrameSwitchView getInstance(Activity activity) {
        if (mInstance == null) {
            synchronized (FrameSwitchView.class) {
                if (mInstance == null) {
                    mInstance = new FrameSwitchView(activity);
                }
            }
        }
        return mInstance;
    }

    private void initScreenData() {
        int navigationBarHeight;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            this.mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            navigationBarHeight = displayMetrics.heightPixels - ((!PdrUtil.isNavigationBarShow(this.mActivity) || PdrUtil.navigationGestureEnabled(this.mActivity)) ? 0 : PdrUtil.getNavigationBarHeight(this.mActivity));
        } else {
            this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            navigationBarHeight = displayMetrics.heightPixels;
        }
        int i = displayMetrics.widthPixels;
        int[] iArr = new int[2];
        this.mWebViewImpl.obtainFrameView().obtainWebAppRootView().obtainMainView().getLocationOnScreen(iArr);
        this.mAppScreenWidth = i - iArr[0];
        this.mAppScreenHeight = navigationBarHeight - iArr[1];
        try {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mSwitchLayout.getLayoutParams();
            layoutParams.height = this.mAppScreenHeight;
            layoutParams.topMargin = iArr[1];
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void runingAnimation(final IWebview iWebview, final int i, int i2, String str, final String str2) {
        initScreenData();
        this.isRuning = true;
        if (this.mSwitchLayout.getVisibility() != 0) {
            this.mSwitchLayout.setVisibility(0);
        }
        final View view = this.mLeftView;
        if (view == null) {
            view = this.mLeftFrameBpView;
        }
        final View view2 = this.mRightView;
        if (view2 == null) {
            view2 = this.mRightFrameBpView;
        }
        view.setVisibility(0);
        if (str.equals("pop-in")) {
            view2.setVisibility(0);
            this.isVisibility = true;
            view.startAnimation(getAnimation(0, -(i / 6), this.mDuration, new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(8);
                    FrameSwitchView.this.isRuning = false;
                    BaseInfo.sDoingAnimation = false;
                    Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    BaseInfo.sDoingAnimation = true;
                }
            }));
            view2.startAnimation(getAnimation(i, 0, this.mDuration, new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    FrameSwitchView.this.endAnimationLayout(view2, 0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            }));
            return;
        }
        if (str.equals("pop-out")) {
            view2.setVisibility(0);
            this.isVisibility = true;
            view.startAnimation(getAnimation(-(i / 2), 0, this.mDuration, new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    FrameSwitchView.this.endAnimationLayout(view, 0);
                    FrameSwitchView.this.isRuning = false;
                    BaseInfo.sDoingAnimation = false;
                    Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    BaseInfo.sDoingAnimation = true;
                }
            }));
            view2.startAnimation(getAnimation(0, i, this.mDuration, new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view2.setVisibility(8);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            }));
            return;
        }
        if (str.equals("slide-in-right")) {
            if (view2 != null) {
                view2.setVisibility(8);
            }
            this.isVisibility = true;
            view.startAnimation(getAnimation(i, 0, this.mDuration, new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.5
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    FrameSwitchView.this.endAnimationLayout(view, 0);
                    FrameSwitchView.this.isRuning = false;
                    BaseInfo.sDoingAnimation = false;
                    Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    BaseInfo.sDoingAnimation = true;
                }
            }));
            return;
        }
        if (!str.equals("slide-out-right")) {
            this.isRuning = false;
            Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
            return;
        }
        if (view2 != null) {
            view2.setVisibility(8);
        }
        this.isVisibility = true;
        final View view3 = view;
        view.startAnimation(getAnimation(0, i, this.mDuration, new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.FrameSwitchView.6
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                FrameSwitchView.this.endAnimationLayout(view3, i);
                FrameSwitchView.this.isRuning = false;
                BaseInfo.sDoingAnimation = false;
                Deprecated_JSUtil.execCallback(iWebview, str2, null, JSUtil.OK, false, false);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                BaseInfo.sDoingAnimation = true;
            }
        }));
    }

    public void clearData() {
        mInstance = null;
        this.mSwitchLayout = null;
        this.isInit = false;
        this.mWebViewImpl = null;
        this.mLeftFrameBpView = null;
        this.mRightFrameBpView = null;
        this.mLeftView = null;
        this.mRightView = null;
        this.mActivity = null;
    }

    public void clearSwitchAnimation(String str) {
        endRefreshView();
    }

    public void endRefreshView() {
        if (this.isVisibility) {
            this.mLeftFrameBpView.clearAnimation();
            this.mRightFrameBpView.clearAnimation();
            this.isVisibility = false;
        }
        if (this.mSwitchLayout.getVisibility() == 0) {
            this.mSwitchLayout.setVisibility(8);
            KeyEvent.Callback callback = this.mLeftView;
            if (callback != null) {
                ((IWaiter) callback).doForFeature("clearAnimate", null);
                this.mSwitchLayout.removeView(this.mLeftView);
                this.mLeftView = null;
            }
            KeyEvent.Callback callback2 = this.mRightView;
            if (callback2 != null) {
                ((IWaiter) callback2).doForFeature("clearAnimate", null);
                this.mSwitchLayout.removeView(this.mRightView);
                this.mRightView = null;
            }
            this.mLeftFrameBpView.clearData();
            this.mRightFrameBpView.clearData();
            this.mLeftFrameBpView.requestLayout();
            this.mRightFrameBpView.requestLayout();
        }
    }

    public void initView() {
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        ViewGroup viewGroup = (ViewGroup) this.mActivity.getWindow().getDecorView();
        this.mSwitchLayout = new SwitchLayout(this.mActivity);
        this.mLeftFrameBpView = new FrameBitmapView(this.mActivity);
        this.mRightFrameBpView = new FrameBitmapView(this.mActivity);
        this.mSwitchLayout.addView(this.mLeftFrameBpView);
        this.mSwitchLayout.addView(this.mRightFrameBpView);
        this.mSwitchLayout.setVisibility(8);
        viewGroup.addView(this.mSwitchLayout);
    }

    public boolean isInit() {
        return this.isInit;
    }

    public void startAnimation(IWebview iWebview, String str, Object obj, String str2, Object obj2, String str3, String str4) {
        try {
            this.mWebViewImpl = iWebview;
            JSONObject jSONObject = new JSONObject(str);
            this.mAniType = jSONObject.optString("type", "pop-in");
            this.mDuration = jSONObject.optInt("duration", this.mDuration);
            initScreenData();
            if (obj != null) {
                if (obj instanceof View) {
                    View view = (View) obj;
                    this.mLeftView = view;
                    addView(view);
                } else {
                    this.mLeftFrameBpView.injectionData(obj, str2, this.mAppScreenWidth, this.mAppScreenHeight, iWebview.getScale());
                }
                if (obj2 != null) {
                    if (obj2 instanceof View) {
                        this.mRightView = (View) obj2;
                        addView((View) obj2);
                    } else {
                        this.mRightFrameBpView.injectionData(obj2, str3, this.mAppScreenWidth, this.mAppScreenHeight, iWebview.getScale());
                    }
                } else if (this.mAniType.equals("pop-in")) {
                    this.mAniType = "slide-in-right";
                } else if (this.mAniType.equals("pop-out")) {
                    this.mAniType = "slide-out-right";
                }
                runingAnimation(iWebview, this.mAppScreenWidth, this.mAppScreenHeight, this.mAniType, str4);
            }
        } catch (Exception unused) {
        }
    }

    public void stopAnimation() {
        initScreenData();
        FrameBitmapView frameBitmapView = this.mLeftFrameBpView;
        if (frameBitmapView != null && frameBitmapView.isInit()) {
            this.mLeftFrameBpView.setStopAnimation(true);
            this.mLeftFrameBpView.configurationChanged(this.mAppScreenWidth, this.mAppScreenHeight);
        }
        FrameBitmapView frameBitmapView2 = this.mRightFrameBpView;
        if (frameBitmapView2 != null && frameBitmapView2.isInit()) {
            this.mRightFrameBpView.setStopAnimation(true);
            this.mRightFrameBpView.configurationChanged(this.mAppScreenWidth, this.mAppScreenHeight);
        }
        if ("pop-in".equals(this.mAniType)) {
            View view = this.mRightView;
            if (view == null) {
                view = this.mRightFrameBpView;
            }
            endAnimationLayout(view, 0);
            View view2 = this.mLeftView;
            if (view2 == null) {
                view2 = this.mLeftFrameBpView;
            }
            view2.setVisibility(8);
            return;
        }
        if (!"pop-out".equals(this.mAniType)) {
            endRefreshView();
            return;
        }
        View view3 = this.mRightView;
        if (view3 == null) {
            view3 = this.mRightFrameBpView;
        }
        view3.setVisibility(8);
        View view4 = this.mLeftView;
        if (view4 == null) {
            view4 = this.mLeftFrameBpView;
        }
        endAnimationLayout(view4, 0);
    }

    public static FrameSwitchView getInstance() {
        return mInstance;
    }

    public FrameSwitchView(Activity activity) {
        this.mActivity = activity;
    }
}
