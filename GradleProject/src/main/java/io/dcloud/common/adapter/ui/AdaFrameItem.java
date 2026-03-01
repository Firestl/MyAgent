package io.dcloud.common.adapter.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsoluteLayout;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.adapter.util.ViewRect;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.animation.AnimatorSet;
import io.dcloud.nineoldandroids.animation.ObjectAnimator;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.util.HashMap;
import supwisdom.dv;
import supwisdom.zu;

/* JADX INFO: loaded from: classes2.dex */
public class AdaFrameItem {
    public static int GONE = 8;
    public static int INVISIBLE = 4;
    public static final String TAG = "AdaFrameItem";
    public static int VISIBLE;
    public Context mContextWrapper;
    public boolean mAutoPop = false;
    public boolean mAutoPush = false;
    public boolean mNeedOrientationUpdate = false;
    public boolean mLongPressed = false;
    public boolean mPressed = false;
    public ViewOptions mViewOptions = null;
    public ViewOptions mViewOptions_animate = null;
    public ViewOptions mViewOptions_birth = null;
    public View mViewImpl = null;
    public Animator.AnimatorListener mAnimatorListener = null;
    public AnimOptions mAnimOptions = null;
    public Animation mAnimation = null;
    public boolean mStranslate = false;
    public int mZIndex = 0;
    public AdaContainerFrameItem mParentFrameItem = null;
    public long lastShowTime = 0;
    public int mPosition = ViewRect.POSITION_ABSOLUTE;
    public INativeView mNativeView = null;
    public boolean isSlipping = false;

    public class DefaultView extends View {
        public DefaultView(Context context) {
            super(context);
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            AdaFrameItem.this.onResize();
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            AdaFrameItem.this.paint(canvas);
        }
    }

    public static class LayoutParamsUtil {
        public static ViewGroup.LayoutParams createLayoutParams(int i, int i2, int i3, int i4) {
            return new AbsoluteLayout.LayoutParams(i3, i4, i, i2);
        }

        public static void preAndroid30SetViewLayoutParams(final View view, final int i, final int i2) {
            if (!(i == 0 && i2 == 0) && DeviceInfo.sDeviceSdkVer <= 10) {
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator objectAnimator = new ObjectAnimator();
                objectAnimator.setPropertyName(Constants.Name.X);
                objectAnimator.setFloatValues(i - 1, i);
                animatorSet.playTogether(objectAnimator);
                ObjectAnimator objectAnimator2 = new ObjectAnimator();
                objectAnimator2.setPropertyName(Constants.Name.Y);
                objectAnimator2.setFloatValues(i2 - 1, i2);
                animatorSet.playTogether(objectAnimator2);
                animatorSet.setDuration(5L);
                animatorSet.setTarget(view);
                animatorSet.addListener(new Animator.AnimatorListener() { // from class: io.dcloud.common.adapter.ui.AdaFrameItem.LayoutParamsUtil.1
                    @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                        view.postDelayed(new Runnable() { // from class: io.dcloud.common.adapter.ui.AdaFrameItem.LayoutParamsUtil.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ViewHelper.setX(view, i);
                                ViewHelper.setY(view, i2);
                            }
                        }, 10L);
                    }

                    @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                    }

                    @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                    }

                    @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                    }
                });
                animatorSet.start();
            }
        }

        public static void setViewLayoutParams(View view, int i, int i2, int i3, int i4) {
            if (view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) {
                ((AbsoluteLayout.LayoutParams) view.getLayoutParams()).x = 0;
                ((AbsoluteLayout.LayoutParams) view.getLayoutParams()).y = 0;
            }
            view.setTop(0);
            view.setLeft(0);
            ViewHelper.setX(view, i);
            ViewHelper.setY(view, i2);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(i3, i4);
            } else {
                layoutParams.width = i3;
                layoutParams.height = i4;
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public AdaFrameItem(Context context) {
        this.mContextWrapper = null;
        this.mContextWrapper = context;
        setFrameOptions(new ViewOptions());
        this.mViewOptions.mTag = this;
    }

    private void addStatusBar(AdaFrameItem adaFrameItem) {
        boolean z;
        int statusHeight;
        ViewOptions viewOptionsObtainFrameOptions = adaFrameItem.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions.isStatusbar && ((z = adaFrameItem instanceof AdaFrameView)) && -1 != (statusHeight = DeviceInfo.getStatusHeight(adaFrameItem.getContext()))) {
            int iHashCode = adaFrameItem.hashCode();
            int statusBarDefaultColor = z ? ((AdaFrameView) adaFrameItem).obtainApp().obtainStatusBarMgr().getStatusBarDefaultColor() : 0;
            if (!PdrUtil.isEmpty(viewOptionsObtainFrameOptions.mStatusbarColor)) {
                int iStringToColor = PdrUtil.stringToColor(viewOptionsObtainFrameOptions.mStatusbarColor);
                if (PdrUtil.checkStatusbarColor(iStringToColor)) {
                    statusBarDefaultColor = iStringToColor;
                }
            }
            ViewGroup viewGroup = (ViewGroup) adaFrameItem.obtainMainView();
            if (viewGroup.findViewById(iHashCode) == null && viewOptionsObtainFrameOptions.titleNView == null) {
                dv dvVar = new dv(adaFrameItem.getContext());
                if (adaFrameItem != null && adaFrameItem.obtainFrameOptions() != null && adaFrameItem.obtainFrameOptions().titleNView != null && "transparent".equals(adaFrameItem.obtainFrameOptions().titleNView.optString("type"))) {
                    statusBarDefaultColor = Color.argb(0, Color.red(statusBarDefaultColor), Color.green(statusBarDefaultColor), Color.blue(statusBarDefaultColor));
                }
                dvVar.setStatusBarHeight(statusHeight);
                dvVar.setBackgroundColor(statusBarDefaultColor);
                dvVar.setId(iHashCode);
                viewGroup.addView(dvVar);
            }
        }
    }

    public final void clearAnimInfo() {
        if (this.isSlipping) {
            return;
        }
        this.mAnimation = null;
        this.mAnimatorListener = null;
        View view = this.mViewImpl;
        if (view != null) {
            view.clearAnimation();
        }
    }

    public void dispose() {
        onDispose();
        clearAnimInfo();
        View view = this.mViewImpl;
        if (view != null) {
            view.setVisibility(GONE);
            ViewGroup viewGroup = (ViewGroup) this.mViewImpl.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.mViewImpl);
            }
            this.mViewImpl = null;
        }
    }

    public AdaFrameItem findLastFrameItem(AdaFrameItem adaFrameItem) {
        return null;
    }

    public Activity getActivity() {
        return (Activity) this.mContextWrapper;
    }

    public AnimOptions getAnimOptions() {
        if (this.mAnimOptions == null) {
            this.mAnimOptions = new AnimOptions();
        }
        return this.mAnimOptions;
    }

    public Context getContext() {
        return this.mContextWrapper;
    }

    public AdaFrameItem getParent() {
        return null;
    }

    public AdaContainerFrameItem getParentFrameItem() {
        return this.mParentFrameItem;
    }

    public boolean isAutoPop() {
        return this.mAutoPop;
    }

    public boolean isAutoPush() {
        return this.mAutoPush;
    }

    public boolean isDisposed() {
        return this.mViewImpl == null;
    }

    public void makeViewOptions_animate() {
        obtainFrameOptions_Birth();
        ViewOptions viewOptionsObtainFrameOptions = obtainFrameOptions();
        ViewOptions viewOptionsCreateViewOptionsData = this.mViewOptions_animate;
        if (viewOptionsCreateViewOptionsData == null) {
            ViewOptions viewOptions = this.mViewOptions;
            viewOptionsCreateViewOptionsData = ViewOptions.createViewOptionsData(viewOptions, viewOptions.getParentViewRect());
            this.mViewOptions_animate = viewOptionsCreateViewOptionsData;
        }
        if (viewOptionsCreateViewOptionsData.hasBackground()) {
            viewOptionsCreateViewOptionsData.anim_top = 0;
            viewOptionsCreateViewOptionsData.anim_left = 0;
            viewOptionsObtainFrameOptions.anim_top = 0;
            viewOptionsObtainFrameOptions.anim_left = 0;
        }
        AnimOptions animOptions = this.mAnimOptions;
        byte b = animOptions.mOption;
        if (b != 0 && b != 4) {
            if (b == 1 || 3 == b) {
                String str = animOptions.mAnimType_close;
                HashMap<String, String> map = AnimOptions.mAnimTypes;
                if (!map.containsValue(str)) {
                    str = map.get(this.mAnimOptions.mAnimType);
                }
                if (PdrUtil.isEquals(str, "slide-out-right") || PdrUtil.isEquals(str, "pop-out")) {
                    viewOptionsCreateViewOptionsData.anim_left = this.mAnimOptions.sScreenWidth;
                    return;
                }
                if (PdrUtil.isEquals(str, AnimOptions.ANIM_SLIDE_OUT_LEFT)) {
                    viewOptionsCreateViewOptionsData.anim_left = -(viewOptionsCreateViewOptionsData.hasBackground() ? this.mAnimOptions.sScreenWidth : viewOptionsCreateViewOptionsData.width);
                    return;
                } else if (PdrUtil.isEquals(str, AnimOptions.ANIM_SLIDE_OUT_TOP)) {
                    viewOptionsCreateViewOptionsData.anim_top = -(viewOptionsCreateViewOptionsData.hasBackground() ? this.mAnimOptions.sScreenHeight : viewOptionsCreateViewOptionsData.height);
                    return;
                } else {
                    if (PdrUtil.isEquals(str, AnimOptions.ANIM_SLIDE_OUT_BOTTOM)) {
                        viewOptionsCreateViewOptionsData.anim_top = this.mAnimOptions.sScreenHeight;
                        return;
                    }
                    return;
                }
            }
            return;
        }
        String str2 = animOptions.mAnimType;
        if (PdrUtil.isEmpty(str2)) {
            str2 = "none";
        }
        if (PdrUtil.isEquals(str2, "slide-in-right") || PdrUtil.isEquals(str2, "pop-in")) {
            viewOptionsObtainFrameOptions.anim_left = this.mAnimOptions.sScreenWidth;
            return;
        }
        if (PdrUtil.isEquals(str2, AnimOptions.ANIM_SLIDE_IN_LEFT)) {
            int i = viewOptionsObtainFrameOptions.width;
            if (i == -1) {
                i = this.mAnimOptions.sScreenWidth;
            }
            viewOptionsObtainFrameOptions.anim_left = -i;
            return;
        }
        if (!PdrUtil.isEquals(str2, AnimOptions.ANIM_SLIDE_IN_TOP)) {
            if (PdrUtil.isEquals(str2, AnimOptions.ANIM_SLIDE_IN_BOTTOM)) {
                viewOptionsObtainFrameOptions.anim_top = this.mAnimOptions.sScreenHeight;
            }
        } else {
            int i2 = viewOptionsObtainFrameOptions.height;
            if (i2 == -1) {
                i2 = this.mAnimOptions.sScreenHeight;
            }
            viewOptionsObtainFrameOptions.anim_top = -i2;
        }
    }

    public ViewOptions obtainFrameOptions() {
        return this.mViewOptions;
    }

    public ViewOptions obtainFrameOptions_Animate() {
        return this.mViewOptions_animate;
    }

    public ViewOptions obtainFrameOptions_Birth() {
        return this.mViewOptions_birth;
    }

    public View obtainMainView() {
        return this.mViewImpl;
    }

    public boolean onDispose() {
        return true;
    }

    public void onPopFromStack(boolean z) {
        this.mAutoPop = z;
    }

    public void onPushToStack(boolean z) {
        this.mAutoPush = z;
    }

    public void onResize() {
        if (!this.mNeedOrientationUpdate || isDisposed()) {
            return;
        }
        addStatusBar(this);
        ViewOptions viewOptions = this.mViewOptions;
        boolean z = this instanceof AdaFrameView;
        boolean z2 = z && ((AdaFrameView) this).isChildOfFrameView;
        viewOptions.onScreenChanged();
        View viewObtainMainView = obtainMainView();
        ViewGroup.LayoutParams layoutParams = viewObtainMainView.getLayoutParams();
        if (z && !z2) {
            if (layoutParams == null || !z) {
                return;
            }
            layoutParams.height = layoutParams.height == -1 ? -1 : viewOptions.height;
            if (viewOptions.isStatusbarDodifyHeight && (viewObtainMainView instanceof zu)) {
                layoutParams.height = viewOptions.height + DeviceInfo.sStatusBarHeight;
            }
            layoutParams.width = layoutParams.width != -1 ? viewOptions.width : -1;
            if (!viewOptions.hasBackground()) {
                ViewHelper.setX(viewObtainMainView, viewOptions.left);
                ViewHelper.setY(viewObtainMainView, viewOptions.top);
            }
            if (z) {
                ((AdaFrameView) this).changeWebParentViewRect();
            }
            viewObtainMainView.requestLayout();
            viewObtainMainView.postInvalidate();
            return;
        }
        int i = viewOptions.height;
        if (i > 0 || i == -1) {
            layoutParams.height = i;
        }
        int i2 = viewOptions.width;
        if (i2 > 0 || i2 == -1) {
            layoutParams.width = i2;
        }
        viewObtainMainView.setLayoutParams(layoutParams);
        if (z2 && z) {
            int iConvertToScreenInt = viewOptions.top;
            int i3 = viewOptions.left;
            int height = layoutParams.height;
            int width = layoutParams.width;
            AdaContainerFrameItem parentFrameItem = getParentFrameItem();
            if (parentFrameItem != null) {
                if (this.mPosition == ViewRect.DOCK_TOP && parentFrameItem.obtainFrameOptions().titleNView != null && TitleNViewUtil.isTitleTypeForDef(parentFrameItem.obtainFrameOptions().titleNView)) {
                    iConvertToScreenInt += PdrUtil.convertToScreenInt("44px", 0, 0, ((AdaFrameView) this).obtainWebView().getScale());
                }
                if (!viewOptions.isStatusbar && parentFrameItem.obtainFrameOptions().isStatusbar) {
                    iConvertToScreenInt += DeviceInfo.sStatusBarHeight;
                    if (viewOptions.isBottomAbsolute()) {
                        height -= DeviceInfo.sStatusBarHeight;
                    }
                }
            }
            if (viewOptions.isStatusbar && !viewOptions.isBottomAbsolute()) {
                height += DeviceInfo.sStatusBarHeight;
            }
            if (width <= 0 && width != -1) {
                width = viewObtainMainView.getWidth();
            }
            if (height <= 0 && height != -1) {
                height = viewObtainMainView.getHeight();
            }
            LayoutParamsUtil.setViewLayoutParams(obtainMainView(), i3, iConvertToScreenInt, width, height);
        }
        if (z) {
            ((AdaFrameView) this).changeWebParentViewRect();
        }
    }

    public void paint(Canvas canvas) {
    }

    public void resize() {
        onResize();
    }

    public void scrollBy(int i, int i2) {
        this.mViewImpl.scrollBy(i, i2);
    }

    public void scrollTo(int i, int i2) {
        this.mViewImpl.scrollTo(i, i2);
    }

    public void setAnimOptions(AnimOptions animOptions) {
        this.mAnimOptions = animOptions;
    }

    public void setAnimatorLinstener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorListener = animatorListener;
    }

    public void setBgcolor(int i) {
        this.mViewImpl.setBackgroundColor(i);
    }

    public void setFrameOptions(ViewOptions viewOptions) {
        this.mViewOptions = viewOptions;
    }

    public void setFrameOptions_Animate(ViewOptions viewOptions) {
        this.mViewOptions_animate = viewOptions;
    }

    public void setFrameOptions_Birth(ViewOptions viewOptions) {
        this.mViewOptions_birth = viewOptions;
    }

    public void setMainView(View view) {
        this.mViewImpl = view;
    }

    public void setParentFrameItem(AdaContainerFrameItem adaContainerFrameItem) {
        this.mParentFrameItem = adaContainerFrameItem;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public void setSlipping(boolean z) {
        this.isSlipping = z;
    }

    public void setVisibility(int i) {
        View view = this.mViewImpl;
        if (view == null || view.getVisibility() == i) {
            return;
        }
        this.mViewImpl.setVisibility(i);
    }

    public void startAnimator(int i) {
    }

    public void updateViewRect(AdaFrameItem adaFrameItem, int[] iArr, int[] iArr2) {
        updateViewRect(adaFrameItem, iArr, iArr2, new boolean[]{true, true, true, true}, new boolean[]{true, true, true, false});
    }

    public void useDefaultMainView() {
        setMainView(new DefaultView(this.mContextWrapper));
    }

    public void updateViewRect(AdaFrameItem adaFrameItem, int[] iArr, int[] iArr2, boolean[] zArr, boolean[] zArr2) {
        ViewOptions viewOptions = this.mViewOptions;
        int i = iArr[0];
        viewOptions.left = i;
        viewOptions.checkValueIsPercentage("left", i, iArr2[0], zArr[0], zArr2[0]);
        ViewOptions viewOptions2 = this.mViewOptions;
        int i2 = iArr[1];
        viewOptions2.top = i2;
        viewOptions2.checkValueIsPercentage("top", i2, iArr2[1], zArr[1], zArr2[1]);
        ViewOptions viewOptions3 = this.mViewOptions;
        int i3 = iArr[2];
        viewOptions3.width = i3;
        viewOptions3.checkValueIsPercentage("width", i3, iArr2[0], zArr[2], zArr2[2]);
        ViewOptions viewOptions4 = this.mViewOptions;
        int i4 = iArr[3];
        viewOptions4.height = i4;
        viewOptions4.checkValueIsPercentage("height", i4, iArr2[1], zArr[3], zArr2[3]);
        this.mViewOptions.setParentViewRect(adaFrameItem.mViewOptions);
    }
}
