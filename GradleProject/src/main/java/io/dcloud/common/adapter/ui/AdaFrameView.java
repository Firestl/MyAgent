package io.dcloud.common.adapter.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.adapter.util.ViewRect;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.core.ui.k;
import io.dcloud.common.core.ui.l;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.SubNViewsUtil;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import supwisdom.dv;
import supwisdom.vu;
import supwisdom.zu;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AdaFrameView extends AdaContainerFrameItem implements IFrameView {
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public boolean inStack;
    public boolean interceptTouchEvent;
    public boolean isChildOfFrameView;
    public boolean isTabItem;
    public boolean isTouchEvent;
    public String mAccelerationType;
    public boolean mAnimationCapture;
    public Animation.AnimationListener mAnimationListener;
    public boolean mAnimationStarted;
    public BounceView mBounceView;
    public Handler mCaptureHandler;
    public vu mCircleRefreshView;
    public Context mContext;
    public ICallBack mErrCallBack;
    public byte mFrameStatus;
    public int mFrameType;
    public int mLastScreenHeight;
    public ArrayList<IEventCallback> mListeners;
    public Bitmap mLoadingSnapshot;
    public String mNativeViewAction;
    public DHImageView mPageCImageView;
    public RefreshView mRefreshView;
    public Bitmap mSnapshot;
    public ICallBack mSucCallBack;
    public l mWindowMgr;

    public interface OnAnimationEnd {
        void onDone();
    }

    public AdaFrameView(Context context, int i, Object obj) {
        super(context);
        this.isChildOfFrameView = false;
        this.mLoadingSnapshot = null;
        this.mSnapshot = null;
        this.mNativeViewAction = "none";
        this.mAnimationStarted = false;
        this.mRefreshView = null;
        this.mBounceView = null;
        this.mCircleRefreshView = null;
        this.mListeners = null;
        this.mFrameType = 0;
        this.mAccelerationType = "auto";
        this.inStack = true;
        this.interceptTouchEvent = true;
        this.isTouchEvent = true;
        this.mPageCImageView = null;
        this.mLastScreenHeight = 0;
        this.mAnimationCapture = false;
        this.mSucCallBack = null;
        this.mErrCallBack = null;
        this.mCaptureHandler = new Handler() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    if (AdaFrameView.this.mErrCallBack != null) {
                        AdaFrameView.this.mErrCallBack.onCallBack(message.arg1, message.obj);
                    }
                } else if (AdaFrameView.this.mSucCallBack != null) {
                    AdaFrameView.this.mSucCallBack.onCallBack(0, null);
                }
                super.handleMessage(message);
            }
        };
        this.mAnimationListener = new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.7
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(null);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                if (animatorListener != null) {
                    animatorListener.onAnimationRepeat(null);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                if (animatorListener != null) {
                    animatorListener.onAnimationStart(null);
                }
            }
        };
        this.isTabItem = false;
        this.mFrameType = i;
        initMainView(context, i, obj);
        this.mContext = context;
        this.mNeedOrientationUpdate = true;
        this.mLastScreenHeight = PlatformUtil.SCREEN_HEIGHT(context);
    }

    private void addCaptureImageView(ViewGroup viewGroup, DHImageView dHImageView, Bitmap bitmap) {
        if (dHImageView.getParent() != viewGroup) {
            if (dHImageView.getParent() != null) {
                ((ViewGroup) dHImageView.getParent()).removeView(dHImageView);
            }
            viewGroup.addView(dHImageView);
        }
        dHImageView.bringToFront();
        dHImageView.setImageBitmap(bitmap);
        dHImageView.removeNativeView();
        dHImageView.setVisibility(AdaFrameItem.VISIBLE);
    }

    private boolean captureAnimation(final Animator animator, final int i) {
        boolean z;
        if (obtainFrameOptions().isAnimationOptimization && SubNViewsUtil.startAnimation(this, animator, i)) {
            return true;
        }
        if (!this.mAnimationCapture || !BaseInfo.sAnimationCaptureC) {
            return false;
        }
        final ViewGroup viewGroup = (ViewGroup) obtainMainView();
        if (checkITypeofAble()) {
            return false;
        }
        final k kVar = (k) obtainWebAppRootView().obtainMainView();
        Bitmap bitmapCaptureView = null;
        if (this.mPageCImageView != null && kVar.getHeight() != this.mPageCImageView.getHeight()) {
            this.mPageCImageView.clear();
            this.mPageCImageView = null;
            return false;
        }
        if (this.mPageCImageView == null) {
            DHImageView rightImageView = kVar.getRightImageView();
            this.mPageCImageView = rightImageView;
            rightImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if (this.mPageCImageView.isSlipping()) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("C页面是否启用截图动画方案:");
        sb.append(this.mAnimationCapture && BaseInfo.sAnimationCaptureC);
        sb.append(" | ");
        sb.append(this.mAnimOptions.mAnimType);
        Logger.e("mabo", sb.toString());
        long jCurrentTimeMillis = System.currentTimeMillis();
        INativeView iNativeView = this.mNativeView;
        if (iNativeView != null) {
            if (iNativeView.isAnimate()) {
                this.mPageCImageView.removeNativeView();
                this.mNativeView = null;
            } else {
                this.mPageCImageView.setImageBitmap(null);
                this.mPageCImageView.addNativeView(this, this.mNativeView);
            }
        }
        if (this.mNativeView != null) {
            z = false;
        } else if (i == 0) {
            bitmapCaptureView = this.mLoadingSnapshot;
            if (bitmapCaptureView == null && (bitmapCaptureView = this.mSnapshot) == null) {
                bitmapCaptureView = (Build.VERSION.SDK_INT >= 22 || !this.isChildOfFrameView || viewGroup.getHeight() <= obtainFrameOptions().height) ? PlatformUtil.captureView(viewGroup) : PlatformUtil.captureView(viewGroup, true, true, new Rect(0, 0, viewGroup.getWidth(), obtainFrameOptions().height), "ARGB");
                z = true;
            }
            z = false;
        } else {
            bitmapCaptureView = this.mSnapshot;
            if (bitmapCaptureView == null) {
                bitmapCaptureView = PlatformUtil.captureView(viewGroup);
                z = true;
            }
            z = false;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("==============C截图耗时=");
        long j = jCurrentTimeMillis2 - jCurrentTimeMillis;
        sb2.append(j);
        Logger.i("mabo", sb2.toString());
        if (j >= BaseInfo.sTimeoutCapture) {
            int i2 = BaseInfo.sTimeOutCount + 1;
            BaseInfo.sTimeOutCount = i2;
            if (i2 > BaseInfo.sTimeOutMax) {
                BaseInfo.sAnimationCaptureC = false;
            }
        } else if (z) {
            BaseInfo.sTimeOutCount = 0;
        }
        this.mPageCImageView.refreshImagerView();
        if (this.mNativeView == null && (bitmapCaptureView == null || PlatformUtil.isWhiteBitmap(bitmapCaptureView))) {
            return false;
        }
        if (viewGroup.getMeasuredWidth() != 0 && viewGroup.getMeasuredHeight() != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(viewGroup.getMeasuredWidth(), viewGroup.getMeasuredHeight());
            if (Build.VERSION.SDK_INT < 22 && this.isChildOfFrameView) {
                layoutParams.height = (viewGroup.getMeasuredHeight() + obtainFrameOptions().top) - (getParentFrameItem().obtainFrameOptions().isStatusbar ? DeviceInfo.sStatusBarHeight : 0);
                this.mPageCImageView.setPadding(0, obtainFrameOptions().top, 0, 0);
            }
            this.mPageCImageView.setLayoutParams(layoutParams);
        }
        if (this.mNativeView == null) {
            addCaptureImageView(kVar, this.mPageCImageView, bitmapCaptureView);
        } else {
            this.mPageCImageView.bringToFront();
            this.mPageCImageView.setVisibility(0);
        }
        viewGroup.setVisibility(4);
        ViewHelper.setX(this.mPageCImageView, this.mViewOptions.left);
        int i3 = this.mViewOptions.top;
        if (this.isChildOfFrameView) {
            i3 += getParentFrameItem().obtainFrameOptions().isStatusbar ? DeviceInfo.sStatusBarHeight : 0;
        }
        ViewHelper.setY(this.mPageCImageView, i3);
        Animation animation = this.mAnimOptions.mAnimator;
        if (animation == null) {
            animator.setTarget(this.mPageCImageView);
            animator.addListener(new Animator.AnimatorListener() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.1
                @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator2) {
                    BaseInfo.sDoingAnimation = false;
                    if (BaseInfo.sOpenedCount == 0) {
                        BaseInfo.sFullScreenChanged = false;
                    }
                    Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                    if (animatorListener != null) {
                        animatorListener.onAnimationCancel(animator2);
                    }
                }

                @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    if (AdaFrameView.this.mPageCImageView != null) {
                        AdaFrameView.this.mPageCImageView.setIntercept(false);
                        if (i != 0) {
                            AdaFrameView.this.mPageCImageView.setNativeAnimationRuning(false);
                        }
                    }
                    Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                    if (animatorListener != null) {
                        animatorListener.onAnimationEnd(animator2);
                    }
                    byte b = AdaFrameView.this.mAnimOptions.mOption;
                    if (b == 3 || b == 1) {
                        viewGroup.setVisibility(4);
                    } else {
                        viewGroup.setVisibility(0);
                    }
                    kVar.postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (AdaFrameView.this.mPageCImageView != null) {
                                if (AdaFrameView.this.mPageCImageView.isNativeView()) {
                                    AdaFrameView adaFrameView = AdaFrameView.this;
                                    adaFrameView.handleNativeViewByAction(adaFrameView.mPageCImageView, i);
                                } else {
                                    AdaFrameView.this.mPageCImageView.setVisibility(4);
                                    AdaFrameView.this.mPageCImageView.setImageBitmap(null);
                                    AdaFrameView.this.mPageCImageView.removeNativeView();
                                }
                            }
                            BaseInfo.sDoingAnimation = false;
                            if (BaseInfo.sOpenedCount == 0) {
                                BaseInfo.sFullScreenChanged = false;
                            }
                        }
                    }, (AdaFrameView.this.mPageCImageView == null || !AdaFrameView.this.mPageCImageView.isNativeView()) ? 240 : 0);
                    animator.removeListener(this);
                }

                @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator2) {
                    Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                    if (animatorListener != null) {
                        animatorListener.onAnimationRepeat(animator2);
                    }
                }

                @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator2) {
                    if (AdaFrameView.this.mPageCImageView != null) {
                        AdaFrameView.this.mPageCImageView.setIntercept(true);
                        if (i != 0) {
                            AdaFrameView.this.mPageCImageView.setNativeAnimationRuning(true);
                        }
                    }
                    BaseInfo.sDoingAnimation = true;
                    Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                    if (animatorListener != null) {
                        animatorListener.onAnimationStart(animator2);
                    }
                }
            });
            animator.start();
        } else {
            animation.setAnimationListener(new Animation.AnimationListener() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.2
                /* JADX WARN: Removed duplicated region for block: B:20:0x0057  */
                @Override // android.view.animation.Animation.AnimationListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onAnimationEnd(android.view.animation.Animation r5) {
                    /*
                        r4 = this;
                        io.dcloud.common.adapter.ui.AdaFrameView r5 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.common.adapter.ui.DHImageView r5 = io.dcloud.common.adapter.ui.AdaFrameView.access$000(r5)
                        r0 = 0
                        if (r5 == 0) goto L12
                        io.dcloud.common.adapter.ui.AdaFrameView r5 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.common.adapter.ui.DHImageView r5 = io.dcloud.common.adapter.ui.AdaFrameView.access$000(r5)
                        r5.setIntercept(r0)
                    L12:
                        io.dcloud.common.util.BaseInfo.sDoingAnimation = r0
                        io.dcloud.common.adapter.ui.AdaFrameView r5 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.nineoldandroids.animation.Animator$AnimatorListener r5 = r5.mAnimatorListener
                        if (r5 == 0) goto L1e
                        r1 = 0
                        r5.onAnimationEnd(r1)
                    L1e:
                        io.dcloud.common.adapter.ui.AdaFrameView r5 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.common.adapter.util.AnimOptions r5 = r5.mAnimOptions
                        byte r5 = r5.mOption
                        r1 = 3
                        r2 = 4
                        if (r5 == r1) goto L32
                        r1 = 1
                        if (r5 != r1) goto L2c
                        goto L32
                    L2c:
                        android.view.ViewGroup r5 = r2
                        r5.setVisibility(r0)
                        goto L37
                    L32:
                        android.view.ViewGroup r5 = r2
                        r5.setVisibility(r2)
                    L37:
                        r5 = 240(0xf0, float:3.36E-43)
                        io.dcloud.common.adapter.ui.AdaFrameView r1 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.common.adapter.ui.DHImageView r1 = io.dcloud.common.adapter.ui.AdaFrameView.access$000(r1)
                        if (r1 == 0) goto L57
                        io.dcloud.common.adapter.ui.AdaFrameView r1 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.common.adapter.ui.DHImageView r1 = io.dcloud.common.adapter.ui.AdaFrameView.access$000(r1)
                        r1.setVisibility(r2)
                        io.dcloud.common.adapter.ui.AdaFrameView r1 = io.dcloud.common.adapter.ui.AdaFrameView.this
                        io.dcloud.common.adapter.ui.DHImageView r1 = io.dcloud.common.adapter.ui.AdaFrameView.access$000(r1)
                        boolean r1 = r1.isNativeView()
                        if (r1 == 0) goto L57
                        goto L59
                    L57:
                        r0 = 240(0xf0, float:3.36E-43)
                    L59:
                        android.view.ViewGroup r5 = r2
                        io.dcloud.common.adapter.ui.AdaFrameView$2$1 r1 = new io.dcloud.common.adapter.ui.AdaFrameView$2$1
                        r1.<init>()
                        long r2 = (long) r0
                        r5.postDelayed(r1, r2)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.ui.AdaFrameView.AnonymousClass2.onAnimationEnd(android.view.animation.Animation):void");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                    Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                    if (animatorListener != null) {
                        animatorListener.onAnimationRepeat(null);
                    }
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                    if (AdaFrameView.this.mPageCImageView != null) {
                        AdaFrameView.this.mPageCImageView.setIntercept(true);
                    }
                    BaseInfo.sDoingAnimation = true;
                    Animator.AnimatorListener animatorListener = AdaFrameView.this.mAnimatorListener;
                    if (animatorListener != null) {
                        animatorListener.onAnimationStart(null);
                    }
                }
            });
            DHImageView dHImageView = this.mPageCImageView;
            if (dHImageView != null) {
                dHImageView.startAnimation(this.mAnimOptions.mAnimator);
            }
        }
        return true;
    }

    private int getFrameHeight() {
        return obtainMainView().getParent() != null ? obtainFrameOptions().height == -1 ? obtainWebAppRootView().obtainMainView().getHeight() : obtainFrameOptions().isStatusbarDodifyHeight ? obtainFrameOptions().height + DeviceInfo.sStatusBarHeight : obtainFrameOptions().height : obtainMainView().getHeight();
    }

    private int indexOfViewInParent(View view, ViewGroup viewGroup) {
        int i = 0;
        while (i < viewGroup.getChildCount() && viewGroup.getChildAt(i) != view) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorMessage(int i, String str) {
        Message message = new Message();
        message.what = 0;
        message.arg1 = i;
        message.obj = str;
        this.mCaptureHandler.sendMessage(message);
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public final void addFrameViewListener(IEventCallback iEventCallback) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        if (iEventCallback == null || this.mListeners.contains(iEventCallback)) {
            return;
        }
        this.mListeners.add(iEventCallback);
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void animate(IWebview iWebview, String str, String str2) {
        if (obtainMainView() instanceof zu) {
            ((zu) obtainMainView()).a(iWebview, str, str2);
        }
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void captureSnapshot(final String str, final ICallBack iCallBack, final ICallBack iCallBack2) {
        new Thread(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.5
            /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:20:0x00a6
                	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
                	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
                	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
                */
            @Override // java.lang.Runnable
            public void run() {
                /*
                    r4 = this;
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this
                    android.content.Context r0 = r0.getContext()
                    if (r0 != 0) goto L9
                    return
                L9:
                    java.lang.String r0 = r2
                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                    r1 = 1
                    r2 = -100
                    if (r0 != 0) goto L70
                    java.lang.String r0 = r2
                    java.lang.String r0 = r0.toLowerCase()
                    java.lang.String r3 = "loading"
                    boolean r0 = r3.equals(r0)
                    if (r0 == 0) goto L70
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> L59
                    android.view.View r3 = r0.obtainMainView()     // Catch: java.lang.Exception -> L59
                    android.graphics.Bitmap r3 = io.dcloud.common.adapter.util.PlatformUtil.captureView(r3)     // Catch: java.lang.Exception -> L59
                    r0.mLoadingSnapshot = r3     // Catch: java.lang.Exception -> L59
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> L59
                    android.graphics.Bitmap r3 = r0.mLoadingSnapshot     // Catch: java.lang.Exception -> L59
                    if (r3 == 0) goto L44
                    io.dcloud.common.DHInterface.ICallBack r3 = r3     // Catch: java.lang.Exception -> L59
                    io.dcloud.common.adapter.ui.AdaFrameView.access$102(r0, r3)     // Catch: java.lang.Exception -> L59
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> L59
                    android.os.Handler r0 = io.dcloud.common.adapter.ui.AdaFrameView.access$300(r0)     // Catch: java.lang.Exception -> L59
                    r0.sendEmptyMessage(r1)     // Catch: java.lang.Exception -> L59
                    goto Lbc
                L44:
                    io.dcloud.common.DHInterface.ICallBack r1 = r4     // Catch: java.lang.Exception -> L59
                    io.dcloud.common.adapter.ui.AdaFrameView.access$202(r0, r1)     // Catch: java.lang.Exception -> L59
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> L59
                    android.content.Context r1 = r0.getContext()     // Catch: java.lang.Exception -> L59
                    int r3 = io.dcloud.base.R.string.dcloud_common_screenshot_fail     // Catch: java.lang.Exception -> L59
                    java.lang.String r1 = r1.getString(r3)     // Catch: java.lang.Exception -> L59
                    io.dcloud.common.adapter.ui.AdaFrameView.access$400(r0, r2, r1)     // Catch: java.lang.Exception -> L59
                    goto Lbc
                L59:
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this
                    io.dcloud.common.DHInterface.ICallBack r1 = r4
                    io.dcloud.common.adapter.ui.AdaFrameView.access$202(r0, r1)
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this
                    android.content.Context r1 = r0.getContext()
                    int r3 = io.dcloud.base.R.string.dcloud_common_screenshot_fail
                    java.lang.String r1 = r1.getString(r3)
                    io.dcloud.common.adapter.ui.AdaFrameView.access$400(r0, r2, r1)
                    goto Lbc
                L70:
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> La6
                    android.view.View r3 = r0.obtainMainView()     // Catch: java.lang.Exception -> La6
                    android.graphics.Bitmap r3 = io.dcloud.common.adapter.util.PlatformUtil.captureView(r3)     // Catch: java.lang.Exception -> La6
                    r0.mSnapshot = r3     // Catch: java.lang.Exception -> La6
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> La6
                    android.graphics.Bitmap r3 = r0.mSnapshot     // Catch: java.lang.Exception -> La6
                    if (r3 == 0) goto L91
                    io.dcloud.common.DHInterface.ICallBack r3 = r3     // Catch: java.lang.Exception -> La6
                    io.dcloud.common.adapter.ui.AdaFrameView.access$102(r0, r3)     // Catch: java.lang.Exception -> La6
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> La6
                    android.os.Handler r0 = io.dcloud.common.adapter.ui.AdaFrameView.access$300(r0)     // Catch: java.lang.Exception -> La6
                    r0.sendEmptyMessage(r1)     // Catch: java.lang.Exception -> La6
                    goto Lbc
                L91:
                    io.dcloud.common.DHInterface.ICallBack r1 = r4     // Catch: java.lang.Exception -> La6
                    io.dcloud.common.adapter.ui.AdaFrameView.access$202(r0, r1)     // Catch: java.lang.Exception -> La6
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this     // Catch: java.lang.Exception -> La6
                    android.content.Context r1 = r0.getContext()     // Catch: java.lang.Exception -> La6
                    int r3 = io.dcloud.base.R.string.dcloud_common_screenshot_fail     // Catch: java.lang.Exception -> La6
                    java.lang.String r1 = r1.getString(r3)     // Catch: java.lang.Exception -> La6
                    io.dcloud.common.adapter.ui.AdaFrameView.access$400(r0, r2, r1)     // Catch: java.lang.Exception -> La6
                    goto Lbc
                La6:
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this
                    io.dcloud.common.DHInterface.ICallBack r1 = r4
                    io.dcloud.common.adapter.ui.AdaFrameView.access$202(r0, r1)
                    io.dcloud.common.adapter.ui.AdaFrameView r0 = io.dcloud.common.adapter.ui.AdaFrameView.this
                    android.content.Context r1 = r0.getContext()
                    int r3 = io.dcloud.base.R.string.dcloud_common_screenshot_fail
                    java.lang.String r1 = r1.getString(r3)
                    io.dcloud.common.adapter.ui.AdaFrameView.access$400(r0, r2, r1)
                Lbc:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.ui.AdaFrameView.AnonymousClass5.run():void");
            }
        }).start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void changeWebParentViewRect() {
        int i;
        ViewGroup viewGroup = (ViewGroup) obtainMainView();
        if (obtainWebviewParent() == null) {
            return;
        }
        View viewObtainMainView = obtainWebviewParent().obtainMainView();
        if (viewGroup == null || viewObtainMainView == null) {
            return;
        }
        try {
            if (viewGroup.getHeight() == viewObtainMainView.getHeight()) {
                if (viewGroup.getHeight() != viewObtainMainView.getHeight()) {
                    return;
                }
                ViewOptions viewOptions = this.mViewOptions;
                if (!viewOptions.isStatusbar && (viewOptions.titleNView == null || !obtainApp().obtainStatusBarMgr().isImmersive)) {
                    return;
                }
            }
            if (obtainFrameOptions().hasBackground() && viewObtainMainView.getHeight() == obtainWebviewParent().obtainFrameOptions().height && !this.mViewOptions.isStatusbar && obtainFrameOptions().titleNView == null) {
                return;
            }
            int frameHeight = getFrameHeight();
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                View childAt = viewGroup.getChildAt(i3);
                if (childAt != viewObtainMainView) {
                    int height = childAt.getHeight();
                    if (childAt instanceof zu) {
                        if (((zu) childAt).getFrameView().mPosition != ViewRect.DOCK_TOP) {
                        }
                    } else if (!(childAt instanceof INativeView)) {
                        if (!(childAt instanceof dv)) {
                        }
                    } else if (((INativeView) childAt).isDock()) {
                        height = (childAt.getTag() == null || !((childAt.getTag().equals("NavigationBar") || childAt.getTag().equals("titleNView")) && ((INativeView) childAt).isStatusBar())) ? ((INativeView) childAt).getInnerHeight() : ((INativeView) childAt).getInnerHeight() + DeviceInfo.sStatusBarHeight;
                        i = ((INativeView) childAt).isDockTop() ? height : 0;
                    }
                    frameHeight -= height;
                    i2 += i;
                }
            }
            viewObtainMainView.getLayoutParams().height = frameHeight;
            ViewHelper.setY(viewObtainMainView, i2);
            viewObtainMainView.requestLayout();
            viewObtainMainView.invalidate();
            if (this.inStack) {
                return;
            }
            changeWebviewRect();
        } catch (Exception e2) {
            Log.e(AdaFrameItem.TAG, e2.getMessage());
        }
    }

    public void changeWebviewRect() {
        ViewGroup viewGroupObtainWindowView = obtainWebView().obtainWindowView();
        if (viewGroupObtainWindowView.getParent() instanceof FrameLayout) {
            viewGroupObtainWindowView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        } else if (viewGroupObtainWindowView.getParent() instanceof LinearLayout) {
            viewGroupObtainWindowView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        }
        viewGroupObtainWindowView.requestLayout();
    }

    public void chkUseCaptureAnimation(boolean z, int i, boolean z2) {
        StringBuilder sb;
        String str;
        boolean z3;
        if (z) {
            sb = new StringBuilder();
            str = "B页面";
        } else {
            sb = new StringBuilder();
            str = "C页面";
        }
        sb.append(str);
        sb.append(i);
        String string = sb.toString();
        if (this.mAccelerationType.equals("none") && !z2) {
            this.mAnimationCapture = false;
            return;
        }
        if (!this.mAccelerationType.equals(AbsoluteConst.ACCELERATION) && this.mSnapshot == null && this.mNativeView == null && Build.VERSION.SDK_INT >= 23) {
            this.mAnimationCapture = false;
            Logger.e("mabo", string + "1是否启用截图动画方案:" + this.mAnimationCapture);
            return;
        }
        if (this.mViewOptions.mUniNViewJson != null) {
            this.mAnimationCapture = false;
            return;
        }
        byte b = this.mAnimOptions.mOption;
        boolean z4 = true;
        boolean z5 = b == 3 || b == 1;
        if (this.mSnapshot == null && this.mNativeView == null && (!obtainWebView().isLoaded() || DeviceInfo.sDeviceSdkVer < 11)) {
            this.mAnimationCapture = false;
            Logger.e("mabo", string + "1是否启用截图动画方案:" + this.mAnimationCapture);
            return;
        }
        if (this.isChildOfFrameView) {
            this.mAnimationCapture = false;
            Logger.e("mabo", string + "2是否启用截图动画方案:" + this.mAnimationCapture);
            return;
        }
        PlatformUtil.MESURE_SCREEN_STATUSBAR_HEIGHT(obtainWebView().getActivity());
        if (this.mLastScreenHeight != PlatformUtil.SCREEN_HEIGHT(this.mContext)) {
            this.mLastScreenHeight = PlatformUtil.SCREEN_HEIGHT(this.mContext);
            this.mAnimationCapture = false;
            Logger.e("mabo", string + "3是否启用截图动画方案:" + this.mAnimationCapture);
            return;
        }
        boolean z6 = (this.mAccelerationType.equals("auto") && !PdrUtil.isEquals(this.mAnimOptions.mAnimType, AnimOptions.ANIM_FADE_IN) && z5 && !PdrUtil.isContains(this.mAnimOptions.mAnimType, "slide")) || this.mAccelerationType.equals(AbsoluteConst.CAPTURE);
        if (z5) {
            z3 = true & (z6 || PdrUtil.isEquals(this.mAnimOptions.mAnimType_close, "pop-out") || PdrUtil.isEquals(this.mAnimOptions.mAnimType, AnimOptions.ANIM_ZOOM_FADE_IN));
        } else {
            AnimOptions animOptions = this.mAnimOptions;
            byte b2 = animOptions.mOption;
            z3 = (b2 == 4 || b2 == 0) & (z6 || PdrUtil.isEquals(animOptions.mAnimType, "pop-in") || PdrUtil.isEquals(this.mAnimOptions.mAnimType, AnimOptions.ANIM_ZOOM_FADE_OUT));
        }
        if (this.isChildOfFrameView && PdrUtil.isEquals(this.mAnimOptions.mAnimType, AnimOptions.ANIM_FADE_IN)) {
            z3 = true;
        }
        if (this.mSnapshot == null && this.mNativeView == null) {
            z4 = z3;
        }
        this.mAnimationCapture = z4;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void clearSnapshot(String str) {
        DHImageView dHImageView = this.mPageCImageView;
        if (dHImageView != null) {
            dHImageView.setImageBitmap(null);
        }
        if (TextUtils.isEmpty(str) || !"loading".equals(str.toLowerCase())) {
            Bitmap bitmap = this.mSnapshot;
            if (bitmap != null) {
                try {
                    if (!bitmap.isRecycled()) {
                        this.mSnapshot.recycle();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.mSnapshot = null;
            return;
        }
        Bitmap bitmap2 = this.mLoadingSnapshot;
        if (bitmap2 != null) {
            try {
                if (!bitmap2.isRecycled()) {
                    this.mLoadingSnapshot.recycle();
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        this.mLoadingSnapshot = null;
    }

    public final void dispatchFrameViewEvents(String str, Object obj) {
        if (this.mListeners != null) {
            Logger.d("AdaFrameView.dispatchFrameViewEvents type=" + str + ";args=" + obj);
            for (int size = this.mListeners.size() + (-1); size >= 0; size--) {
                IEventCallback iEventCallback = this.mListeners.get(size);
                if (PdrUtil.isEquals(str, "close")) {
                    this.mListeners.remove(size);
                }
                iEventCallback.onCallBack(str, obj);
            }
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        if (this.mRefreshView != null) {
            this.mRefreshView = null;
        }
        if (this.mBounceView != null) {
            this.mBounceView = null;
        }
        if (this.mCircleRefreshView != null) {
            this.mCircleRefreshView = null;
        }
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public synchronized void draw(final View view, final INativeBitmap iNativeBitmap, final boolean z, final boolean z2, final Rect rect, final String str, final ICallBack iCallBack, final ICallBack iCallBack2) {
        this.mCaptureHandler.post(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (AdaFrameView.this.getContext() == null) {
                        return;
                    }
                    Bitmap bitmapCaptureView = PlatformUtil.captureView(view, z, z2, rect, str);
                    if (bitmapCaptureView != null) {
                        iNativeBitmap.setBitmap(bitmapCaptureView);
                        AdaFrameView.this.mSucCallBack = iCallBack;
                        AdaFrameView.this.mCaptureHandler.sendEmptyMessage(1);
                    } else {
                        AdaFrameView.this.mErrCallBack = iCallBack2;
                        AdaFrameView adaFrameView = AdaFrameView.this;
                        adaFrameView.sendErrorMessage(-101, adaFrameView.getContext().getString(R.string.dcloud_common_screenshot_blank));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    AdaFrameView.this.mErrCallBack = iCallBack2;
                    AdaFrameView adaFrameView2 = AdaFrameView.this;
                    adaFrameView2.sendErrorMessage(-100, adaFrameView2.getContext().getString(R.string.dcloud_common_screenshot_fail));
                }
            }
        });
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public IFrameView findPageB() {
        return obtainWebAppRootView().findFrameViewB(this);
    }

    public vu getCircleRefreshView() {
        return this.mCircleRefreshView;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public int getFrameType() {
        return this.mFrameType;
    }

    public void handleNativeViewByAction(DHImageView dHImageView, int i) {
        if ("none".equals(this.mNativeViewAction) || !("hide".equals(this.mNativeViewAction) || "close".equals(this.mNativeViewAction))) {
            if (i != 1 || dHImageView == null) {
                return;
            }
            dHImageView.clearAnimation();
            dHImageView.setVisibility(4);
            dHImageView.setImageBitmap(null);
            dHImageView.removeNativeView();
            return;
        }
        String viewUUId = this.mNativeView.getViewUUId();
        String str = this.mNativeViewAction;
        if (str.equalsIgnoreCase("hide")) {
            if (dHImageView != null) {
                dHImageView.setVisibility(4);
            }
        } else if (this.mNativeViewAction.equalsIgnoreCase("close")) {
            if (dHImageView != null) {
                dHImageView.setVisibility(4);
                dHImageView.setImageBitmap(null);
                dHImageView.removeNativeView();
            }
            str = "view_close";
        }
        this.mNativeView = null;
        this.mNativeViewAction = "none";
        if (obtainWindowMgr() != null) {
            obtainWindowMgr().processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{obtainWebView(), "nativeobj", str, JSONUtil.createJSONArray("['" + viewUUId + "','" + viewUUId + "']")});
        }
    }

    public abstract void initMainView(Context context, int i, Object obj);

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void interceptTouchEvent(boolean z) {
        this.interceptTouchEvent = z;
    }

    public boolean isSupportLongTouch() {
        return false;
    }

    public boolean isTabItem() {
        return this.isTabItem || obtainFrameOptions().isTabItem.booleanValue();
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public boolean isWebviewCovered() {
        ViewGroup viewGroupObtainWindowView;
        IWebview iWebviewObtainWebView = obtainWebView();
        if (iWebviewObtainWebView == null || (viewGroupObtainWindowView = iWebviewObtainWebView.obtainWindowView()) == null || viewGroupObtainWindowView.getVisibility() != 0 || viewGroupObtainWindowView.getParent() == null) {
            return true;
        }
        Rect rect = new Rect(0, 0, PlatformUtil.SCREEN_WIDTH(viewGroupObtainWindowView.getContext()), PlatformUtil.SCREEN_HEIGHT(viewGroupObtainWindowView.getContext()));
        Rect rect2 = new Rect();
        viewGroupObtainWindowView.getGlobalVisibleRect(rect2);
        if (!rect.contains(rect2)) {
            return true;
        }
        while (viewGroupObtainWindowView.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewGroupObtainWindowView.getParent();
            if (viewGroup.getVisibility() != 0) {
                return true;
            }
            for (int iIndexOfViewInParent = indexOfViewInParent(viewGroupObtainWindowView, viewGroup) + 1; iIndexOfViewInParent < viewGroup.getChildCount(); iIndexOfViewInParent++) {
                View childAt = viewGroup.getChildAt(iIndexOfViewInParent);
                if (childAt.getVisibility() == 0 && !(childAt instanceof IWaiter)) {
                    Rect rect3 = new Rect();
                    childAt.getGlobalVisibleRect(rect3);
                    if (rect3.contains(rect2)) {
                        return true;
                    }
                }
            }
            viewGroupObtainWindowView = viewGroup;
        }
        return viewGroupObtainWindowView.getParent() == null;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public abstract IApp obtainApp();

    public abstract String obtainPrePlusreadyJs();

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public byte obtainStatus() {
        return this.mFrameStatus;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public abstract IWebview obtainWebView();

    @Override // io.dcloud.common.DHInterface.IFrameView
    public abstract AbsMgr obtainWindowMgr();

    public void onConfigurationChanged() {
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public void onDestroy() {
        this.mFrameStatus = (byte) 4;
        transition((byte) 4);
        dispose();
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public boolean onDispose() {
        boolean zOnDispose = super.onDispose();
        dispatchFrameViewEvents("close", obtainWebView());
        DHImageView dHImageView = this.mPageCImageView;
        if (dHImageView != null) {
            dHImageView.setImageBitmap(null);
        }
        return zOnDispose;
    }

    public void onDrawAfter(Canvas canvas) {
        vu vuVar;
        if (obtainMainView() == null || obtainMainView().getVisibility() != 0 || (vuVar = this.mCircleRefreshView) == null || !vuVar.d()) {
            return;
        }
        canvas.save();
        int left = obtainWebviewParent().obtainMainView().getLeft();
        int y = (int) ViewHelper.getY(obtainWebviewParent().obtainMainView());
        ViewOptions viewOptions = this.mViewOptions;
        if (viewOptions != null && viewOptions.isStatusbar && viewOptions.titleNView == null) {
            y += DeviceInfo.sStatusBarHeight;
        }
        canvas.translate(left, y);
        this.mCircleRefreshView.a(canvas);
        canvas.restore();
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public void onInit() {
        this.mFrameStatus = (byte) 0;
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public void onLoading() {
        this.mFrameStatus = (byte) 2;
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public void onPreLoading() {
        this.mFrameStatus = (byte) 1;
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public void onPreShow(IFrameView iFrameView) {
        this.mFrameStatus = (byte) 3;
        transition((byte) 3);
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void onResize() {
        if (obtainApp() == null || !obtainApp().manifestBeParsed()) {
            return;
        }
        super.onResize();
        RefreshView refreshView = this.mRefreshView;
        if (refreshView != null) {
            refreshView.onResize();
        }
        BounceView bounceView = this.mBounceView;
        if (bounceView != null) {
            bounceView.onResize();
        }
        dispatchFrameViewEvents(AbsoluteConst.EVENTS_FRAME_ONRESIZE, null);
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void paint(Canvas canvas) {
        super.paint(canvas);
        if (obtainMainView() == null || obtainMainView().getVisibility() != 0 || this.mRefreshView == null || this.isSlipping) {
            return;
        }
        Logger.d(Logger.VIEW_VISIBLE_TAG, "AdaFrameView.paint mRefreshView paint" + this);
        ViewOptions viewOptions = obtainWebviewParent().mViewOptions_birth;
        ViewOptions viewOptions2 = obtainWebviewParent().mViewOptions;
        if (viewOptions == null) {
            viewOptions = this.mViewOptions_birth;
        }
        if (viewOptions2 == null) {
            viewOptions2 = this.mViewOptions;
        }
        int i = viewOptions.left;
        int i2 = viewOptions2.left;
        if (i == i2) {
            i2 = 0;
        }
        this.mRefreshView.paint(canvas, i2 + obtainWebviewParent().obtainMainView().getLeft(), (int) ((viewOptions.top != viewOptions2.top ? r1 : 0) + ViewHelper.getY(obtainWebviewParent().obtainMainView())));
    }

    @Override // io.dcloud.common.DHInterface.IFrameViewStatus
    public final void removeFrameViewListener(IEventCallback iEventCallback) {
        ArrayList<IEventCallback> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.remove(iEventCallback);
        }
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void restore() {
        if (obtainMainView() instanceof zu) {
            ((zu) obtainMainView()).d();
        }
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void setAccelerationType(String str) {
        this.mAccelerationType = str;
    }

    public void setCircleRefreshView(vu vuVar) {
        this.mCircleRefreshView = vuVar;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void setSnapshot(Bitmap bitmap) {
        Bitmap bitmap2 = this.mSnapshot;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.mSnapshot.recycle();
        }
        this.mSnapshot = bitmap;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void setSnapshotView(INativeView iNativeView, String str) {
        this.mNativeView = iNativeView;
        this.mNativeViewAction = str;
    }

    public void setTabItem(boolean z) {
        this.isTabItem = z;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void setVisible(boolean z, boolean z2) {
        Logger.d(Logger.VIEW_VISIBLE_TAG, "AdaFrameView.setVisible pVisible" + z + "       " + this);
        setVisibility(z ? AdaFrameItem.VISIBLE : AdaFrameItem.INVISIBLE);
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void startAnimator(int i) {
        startAnimator(null, i);
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void transition(byte b) {
    }

    public void updateFrameRelViewRect(ViewRect viewRect) {
        ViewRect parentViewRect;
        ViewGroup viewGroup = (ViewGroup) obtainMainView();
        if (!this.mViewOptions.hasBackground()) {
            this.mViewOptions.updateViewData(viewRect);
            if (viewGroup != null && viewGroup.getVisibility() == 0 && this.mRefreshView != null) {
                obtainWebviewParent().reInit();
            }
        } else if (obtainWebviewParent().obtainFrameOptions().allowUpdate) {
            obtainWebviewParent().obtainFrameOptions().updateViewData(viewRect);
        }
        ViewOptions viewOptions = this.mViewOptions;
        int i = viewOptions.top;
        int i2 = viewOptions.height;
        if (this.isChildOfFrameView && (parentViewRect = viewOptions.getParentViewRect()) != null && parentViewRect.isStatusbar && !this.mViewOptions.isStatusbar) {
            if (obtainFrameOptions().isBottomAbsolute()) {
                i2 -= DeviceInfo.sStatusBarHeight;
            }
            i += DeviceInfo.sStatusBarHeight;
        }
        View view = this.mViewImpl;
        ViewOptions viewOptions2 = this.mViewOptions;
        AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(view, viewOptions2.left, i, viewOptions2.width, i2);
        changeWebParentViewRect();
        if (viewGroup != null) {
            obtainMainView().invalidate();
        }
    }

    public void startAnimator(final OnAnimationEnd onAnimationEnd, int i) {
        AnimOptions animOptions = this.mAnimOptions;
        if (animOptions != null) {
            animOptions.mUserFrameItem = this;
            animOptions.sScreenWidth = obtainApp().getInt(0);
            this.mAnimOptions.sScreenHeight = obtainApp().getInt(1);
            Animator animatorCreateAnimation = this.mAnimOptions.createAnimation();
            if (obtainFrameOptions().hasBackground() && this.mAnimOptions.mOption == 2) {
                AdaWebViewParent adaWebViewParentObtainWebviewParent = obtainWebviewParent();
                this.mAnimOptions.mUserFrameItem = adaWebViewParentObtainWebviewParent;
                animatorCreateAnimation.setTarget(adaWebViewParentObtainWebviewParent.obtainMainView());
                animatorCreateAnimation.addListener(this.mAnimatorListener);
                animatorCreateAnimation.start();
            } else if (!captureAnimation(animatorCreateAnimation, i)) {
                DHImageView dHImageView = this.mPageCImageView;
                if (dHImageView != null && !dHImageView.isSlipping()) {
                    this.mPageCImageView.clearAnimation();
                    this.mPageCImageView.setVisibility(4);
                    this.mPageCImageView.setImageBitmap(null);
                }
                this.mViewImpl.bringToFront();
                Animation animation = this.mAnimOptions.mAnimator;
                if (animation == null) {
                    animatorCreateAnimation.setTarget(this.mViewImpl);
                    animatorCreateAnimation.addListener(this.mAnimatorListener);
                    animatorCreateAnimation.start();
                } else {
                    animation.setAnimationListener(this.mAnimationListener);
                    this.mViewImpl.startAnimation(this.mAnimOptions.mAnimator);
                }
            }
            animatorCreateAnimation.setInterpolator(new DecelerateInterpolator());
            AnimOptions animOptions2 = this.mAnimOptions;
            MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.common.adapter.ui.AdaFrameView.3
                @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                public void execute(Object obj) {
                    Animator.AnimatorListener animatorListener;
                    AdaFrameView adaFrameView = AdaFrameView.this;
                    if (!adaFrameView.mAnimationStarted && (animatorListener = adaFrameView.mAnimatorListener) != null) {
                        animatorListener.onAnimationEnd(null);
                    }
                    OnAnimationEnd onAnimationEnd2 = onAnimationEnd;
                    if (onAnimationEnd2 != null) {
                        onAnimationEnd2.onDone();
                    }
                    if (BaseInfo.sOpenedCount == 0) {
                        BaseInfo.sFullScreenChanged = false;
                    }
                }
            }, Math.max(animOptions2.duration_show, Math.max(animOptions2.duration_close, animOptions2.duration)), null);
        }
    }
}
