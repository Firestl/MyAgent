package io.dcloud.common.core.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebAppRootView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.AdaWebViewParent;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import supwisdom.zu;

/* JADX INFO: loaded from: classes2.dex */
public class b extends AdaFrameView {
    public static int t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6389a;
    public ArrayList<b> b;
    public ArrayList<b> c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6390e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public IApp j;
    public io.dcloud.common.core.ui.a k;
    public AdaWebview l;
    public AdaWebViewParent m;
    public byte n;
    public Boolean o;
    public Animator.AnimatorListener p;
    public boolean q;
    public boolean r;
    public boolean s;

    public class a implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: io.dcloud.common.core.ui.b$a$a, reason: collision with other inner class name */
        public class RunnableC0152a implements Runnable {
            public RunnableC0152a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                if (b.this.mWindowMgr != null) {
                    aVar.a();
                }
            }
        }

        public a() {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            BaseInfo.sDoingAnimation = false;
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Logger.e("DHFrameView", "---------------------onAnimationEnd");
            AnimOptions animOptions = b.this.getAnimOptions();
            b.this.o = Boolean.FALSE;
            byte b = animOptions.mOption;
            if (b == 1) {
                b.this.setVisibility(AdaFrameItem.GONE);
            } else if (b == 3) {
                b.this.l();
            }
            BaseInfo.sDoingAnimation = false;
            if (b.this.obtainMainView() == null) {
                return;
            }
            b.this.obtainMainView().post(new RunnableC0152a());
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            Logger.e("DHFrameView", "---------------------onAnimationStart");
            BaseInfo.sDoingAnimation = true;
            b.this.mAnimationStarted = true;
            if (b.this.getAnimOptions().mOption == 2) {
                b.a(b.this.obtainMainView(), b.this.mViewOptions.left, b.this.mViewOptions.top, "onAnimationStart");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            b bVar = b.this;
            if (bVar.mWindowMgr == null) {
                return;
            }
            byte b = bVar.getAnimOptions().mOption;
            if (b == 0) {
                View view = i.f6406a;
                if (view != null) {
                    view.clearAnimation();
                }
                b.this.n();
            } else if (b == 1) {
                b.this.i();
            } else if (b == 2) {
                b.this.o();
            } else if (b == 3) {
                b.this.k();
            } else if (b == 4) {
                View view2 = i.f6406a;
                if (view2 != null) {
                    view2.clearAnimation();
                }
                b.this.m();
            }
            b.this.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 70, null);
        }
    }

    public b(Context context, l lVar, IApp iApp, io.dcloud.common.core.ui.a aVar, int i, Object obj) {
        super(context, i, obj);
        this.f6389a = false;
        this.d = false;
        this.f6390e = true;
        this.f = false;
        this.g = false;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = (byte) 2;
        this.p = new a();
        this.q = false;
        this.r = true;
        this.s = false;
        this.lastShowTime = System.currentTimeMillis();
        t++;
        Logger.i("dhframeview", "construction Count=" + t);
        this.mWindowMgr = lVar;
        this.j = iApp;
        this.k = aVar;
        aVar.d().add(this);
        this.f6390e = iApp.isVerticalScreen();
        this.f = iApp.isFullScreen();
    }

    public void c(boolean z) {
        this.k.l();
        if (!this.isChildOfFrameView) {
            this.k.b(this);
            onPushToStack(isAutoPop());
            io.dcloud.common.core.ui.a aVar = this.k;
            if (aVar != null) {
                if (aVar.e().contains(this)) {
                    aVar.m();
                } else {
                    aVar.e(this);
                }
            }
        } else if (getParentFrameItem() != null) {
            this.k.h(this);
        }
        a(z);
    }

    public boolean d() {
        ArrayList<b> arrayList = this.c;
        return arrayList != null && arrayList.size() > 0;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        Logger.e("DHFrameView", "dispose");
        super.dispose();
        if (this.k != null) {
            int frameType = getFrameType();
            if (frameType == 2) {
                this.k.f = null;
            } else if (frameType == 4) {
                this.k.g = null;
            } else if (frameType == 5) {
                this.k.f6373e = null;
            }
            this.k.e().remove(this);
            r();
        }
        this.mWindowMgr = null;
        this.j = null;
        this.mParentFrameItem = null;
        this.k = null;
        this.l = null;
        this.p = null;
    }

    public boolean e() {
        ArrayList<b> arrayList = this.b;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean f() {
        return this.s;
    }

    public boolean g() {
        AnimOptions animOptions = getAnimOptions();
        return (animOptions == null || animOptions.mOption == 1) ? false : true;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public AdaFrameItem getParent() {
        return this.k;
    }

    public void h() {
        if (this.f6390e == this.j.isVerticalScreen() && this.f == this.j.isFullScreen()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("onPushToStack frame ");
        sb.append(this.f6390e ? "调整为横屏状态" : "调整为竖屏状态");
        sb.append(this);
        Logger.d(Logger.ANIMATION_TAG, sb.toString());
        resize();
        this.f6390e = this.j.isVerticalScreen();
        this.f = this.j.isFullScreen();
    }

    public void i() {
        setVisibility(AdaFrameItem.GONE);
        if (BaseInfo.isUniAppAppid(obtainApp())) {
            obtainWebView().setIWebViewFocusable(false);
        }
        j();
        q();
        b();
        clearAnimInfo();
        Logger.d(Logger.ANIMATION_TAG, "onCloseAnimationEnd;" + this);
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView
    public void initMainView(Context context, int i, Object obj) {
        if (i != 1) {
            setMainView(new zu(context, this, this.j));
        }
    }

    public void j() {
        b(true);
    }

    public void k() {
        if (BaseInfo.isUniAppAppid(obtainApp())) {
            obtainWebView().setIWebViewFocusable(false);
        }
        dispatchFrameViewEvents("hide", null);
        if (!this.mViewOptions.hasBackground() && !this.isChildOfFrameView && obtainMainView() != null) {
            ViewHelper.setX(obtainMainView(), this.mViewOptions.left);
            ViewHelper.setY(obtainMainView(), this.mViewOptions.top);
            ViewHelper.setScaleX(obtainMainView(), 1.0f);
            ViewHelper.setScaleY(obtainMainView(), 1.0f);
            if (!this.mViewOptions.hasTransparentValue()) {
                ViewHelper.setAlpha(obtainMainView(), 1.0f);
            }
        }
        ViewOptions viewOptions = this.mViewOptions_animate;
        if (viewOptions != null) {
            updateFrameRelViewRect(viewOptions);
            this.mViewOptions_animate = null;
        }
        j();
        b();
        clearAnimInfo();
        Logger.d(Logger.ANIMATION_TAG, "onHideAnimationEnd;" + toString());
    }

    public void l() {
        setVisible(false, true);
    }

    public void m() {
        if (BaseInfo.isUniAppAppid(obtainApp())) {
            obtainWebView().setIWebViewFocusable(true);
        }
        if (BaseInfo.isUniAppAppid(obtainApp()) && !obtainWebView().isUniWebView()) {
            obtainWebView().obtainWindowView().requestFocus();
        }
        dispatchFrameViewEvents(AbsoluteConst.EVENTS_SHOW_ANIMATION_END, null);
        ViewOptions viewOptions = this.mViewOptions_animate;
        if (viewOptions != null) {
            updateFrameRelViewRect(viewOptions);
            this.mViewOptions_animate = null;
        }
        View viewObtainMainView = obtainMainView();
        ViewOptions viewOptions2 = this.mViewOptions;
        a(viewObtainMainView, viewOptions2.left, viewOptions2.top, "onHideShowAnimationEnd");
        j();
        this.inStack = true;
        b();
        clearAnimInfo();
        Logger.d(Logger.ANIMATION_TAG, "onHideShowAnimationEnd;" + toString());
    }

    public void n() {
        if (BaseInfo.isUniAppAppid(obtainApp())) {
            obtainWebView().setIWebViewFocusable(true);
        }
        if (BaseInfo.isUniAppAppid(obtainApp()) && !obtainWebView().isUniWebView()) {
            obtainWebView().obtainWindowView().requestFocus();
        }
        dispatchFrameViewEvents(AbsoluteConst.EVENTS_SHOW_ANIMATION_END, null);
        ViewOptions viewOptions = this.mViewOptions_animate;
        if (viewOptions != null) {
            updateFrameRelViewRect(viewOptions);
            this.mViewOptions_animate = null;
        }
        j();
        this.inStack = true;
        b();
        clearAnimInfo();
        Logger.d(Logger.ANIMATION_TAG, "onShowAnimationEnd;" + this);
        if (SDK.isUniMPSDK() || BaseInfo.isUniAppAppid(this.j)) {
            return;
        }
        io.dcloud.f.b.a.f().a(this.j.getActivity());
    }

    public void o() {
        AdaWebViewParent adaWebViewParentObtainWebviewParent = obtainFrameOptions().hasBackground() ? obtainWebviewParent() : this;
        ViewOptions viewOptionsObtainFrameOptions_Animate = adaWebViewParentObtainWebviewParent.obtainFrameOptions_Animate();
        if (viewOptionsObtainFrameOptions_Animate != null) {
            updateFrameRelViewRect(viewOptionsObtainFrameOptions_Animate);
            adaWebViewParentObtainWebviewParent.setFrameOptions_Animate(null);
        }
        View viewObtainMainView = obtainMainView();
        ViewOptions viewOptions = this.mViewOptions;
        a(viewObtainMainView, viewOptions.left, viewOptions.top, "onStyleChangedAnimationEnd");
        j();
        b();
        clearAnimInfo();
        Logger.d(Logger.ANIMATION_TAG, "onStyleChangedAnimationEnd;" + adaWebViewParentObtainWebviewParent.toString());
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameView
    public IApp obtainApp() {
        return this.j;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.dcloud.common.adapter.ui.AdaFrameView
    public String obtainPrePlusreadyJs() {
        l lVar = this.mWindowMgr;
        return lVar != null ? (String) lVar.processEvent(IMgr.MgrType.FeatureMgr, 2, new Object[]{this.j, this}) : "";
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public IWebAppRootView obtainWebAppRootView() {
        return this.k;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameView
    public IWebview obtainWebView() {
        return this.l;
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public AdaWebViewParent obtainWebviewParent() {
        return this.m;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameView
    public AbsMgr obtainWindowMgr() {
        return this.mWindowMgr;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView
    public void onConfigurationChanged() {
        super.onConfigurationChanged();
        resize();
        this.f6390e = this.j.isVerticalScreen();
        this.f = this.j.isFullScreen();
        Logger.d(Logger.Android_System_TAG, "onConfigurationChanged", this);
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameViewStatus
    public void onDestroy() {
        super.onDestroy();
        t--;
        Logger.i("dhframeview", "onDestroy Count=" + t);
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public boolean onDispose() {
        if (getParentFrameItem() != null && (getParentFrameItem() instanceof c)) {
            ((c) getParentFrameItem()).removeFrameView(this);
        }
        return super.onDispose();
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameViewStatus
    public void onInit() {
        super.onInit();
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameViewStatus
    public void onLoading() {
        super.onLoading();
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPopFromStack(boolean z) {
        super.onPopFromStack(z);
        IApp iApp = this.j;
        if (iApp == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("已经提前出栈了 ");
            sb.append(this.f6390e ? "竖屏出栈" : "横屏出栈");
            sb.append(this);
            Logger.d(Logger.ANIMATION_TAG, sb.toString());
            return;
        }
        this.f6390e = iApp.isVerticalScreen();
        this.f = this.j.isFullScreen();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("onPopFromStack ");
        sb2.append(this.f6390e ? "竖屏出栈" : "横屏出栈");
        sb2.append(this);
        Logger.d(Logger.ANIMATION_TAG, sb2.toString());
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameViewStatus
    public void onPreLoading() {
        super.onPreLoading();
        if (this.n == 0) {
            t();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameViewStatus
    public void onPreShow(IFrameView iFrameView) {
        super.onPreShow(iFrameView);
    }

    public void p() {
        this.q = true;
        Logger.d(Logger.ANIMATION_TAG, "onWillDoAnimation " + this);
        io.dcloud.common.core.ui.a aVar = this.k;
        if (aVar != null) {
            aVar.l.a(this);
        }
        if (this.d) {
            ViewOptions viewOptionsObtainFrameOptions_Animate = obtainFrameOptions_Animate();
            ViewOptions viewOptions = this.mViewOptions;
            viewOptions.opacity = viewOptionsObtainFrameOptions_Animate.opacity;
            viewOptions.background = viewOptionsObtainFrameOptions_Animate.background;
            viewOptions.strBackground = viewOptionsObtainFrameOptions_Animate.strBackground;
            b(viewOptions, this, obtainWebviewParent(), (AdaFrameItem) obtainWebView());
        }
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void popFromViewStack() {
        if (this.isChildOfFrameView || !this.inStack) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        this.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 27, arrayList);
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void pushToViewStack() {
        if (this.isChildOfFrameView || this.inStack) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        this.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 28, arrayList);
    }

    public void q() {
        io.dcloud.common.core.ui.a aVar = this.k;
        if (aVar != null) {
            aVar.g(this);
        }
    }

    public void r() {
        io.dcloud.common.core.ui.a aVar = this.k;
        if (aVar != null) {
            aVar.d().remove(this);
        }
    }

    public void s() {
        setAnimatorLinstener(this.p);
    }

    @Override // io.dcloud.common.DHInterface.IFrameView
    public void setNeedRender(boolean z) {
        this.f6389a = z;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameView
    public void setVisible(boolean z, boolean z2) {
        super.setVisible(z, z2);
        if (z && (getParentFrameItem() instanceof c)) {
            ((c) getParentFrameItem()).d(this);
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.adapter.ui.AdaFrameItem
    public void startAnimator(int i) {
        chkUseCaptureAnimation(false, hashCode(), this.mSnapshot != null);
        super.startAnimator(i);
    }

    public void t() {
        s();
    }

    public String toString() {
        AdaWebview adaWebview = this.l;
        return adaWebview != null ? adaWebview.toString() : super.toString();
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameView, io.dcloud.common.DHInterface.IFrameView
    public void transition(byte b) {
        if (this.n == b && b == 2) {
            t();
        }
    }

    public void b(ViewOptions viewOptions, AdaFrameItem adaFrameItem, AdaFrameItem adaFrameItem2, AdaFrameItem adaFrameItem3) {
        if (DeviceInfo.sDeviceSdkVer >= 11 && viewOptions.opacity != -1.0f) {
            adaFrameItem.obtainMainView().setAlpha(viewOptions.opacity);
        }
        if (viewOptions.webviewBGTransparent) {
            adaFrameItem3.setBgcolor(0);
        }
        if (viewOptions.isTabItem.booleanValue() && !PdrUtil.isEmpty(viewOptions.strTabBG)) {
            adaFrameItem.setBgcolor(PdrUtil.stringToColor(viewOptions.strTabBG));
            return;
        }
        if (viewOptions.hasBackground()) {
            adaFrameItem.setBgcolor(viewOptions.background);
        } else if (viewOptions.isTransparent()) {
            adaFrameItem2.setBgcolor(0);
            adaFrameItem3.setBgcolor(0);
            adaFrameItem.setBgcolor(0);
        }
    }

    public void d(boolean z) {
        this.s = z;
    }

    public void a(boolean z) {
        boolean z2 = true;
        boolean z3 = obtainMainView().getVisibility() == 0;
        setVisible(true, false);
        p();
        int i = obtainApp().getInt(0);
        int i2 = obtainApp().getInt(1);
        if ((i != obtainFrameOptions().width || obtainFrameOptions().height + 1 < i2) && (obtainFrameOptions().width != -1 || obtainFrameOptions().height != -1)) {
            z2 = false;
        }
        if ((z || BaseInfo.isDefaultAim) && !this.isChildOfFrameView && !z3) {
            if (z2 && PdrUtil.isEquals(getAnimOptions().mAnimType, "pop-in")) {
                i.a(this, 0);
            }
            if (z) {
                s();
                startAnimator(0);
            } else {
                n();
            }
        } else if (z && PdrUtil.isEquals(getAnimOptions().mAnimType, AnimOptions.ANIM_FADE_IN)) {
            s();
            startAnimator(0);
        } else {
            n();
        }
        this.k.i(this);
    }

    public void c() {
        View viewA;
        try {
            viewA = a(obtainMainView());
        } catch (Exception e2) {
            e2.printStackTrace();
            viewA = null;
        }
        if (viewA != null) {
            if (viewA instanceof RecyclerView) {
                ((RecyclerView) viewA).scrollToPosition(0);
            } else {
                viewA.scrollTo(0, 0);
            }
        }
    }

    public void b(boolean z) {
        this.q = false;
        this.mAnimationStarted = true;
        Logger.d(Logger.ANIMATION_TAG, "onDoneAnimation " + this);
        io.dcloud.common.core.ui.a aVar = this.k;
        if (aVar != null) {
            this.b = null;
            if (aVar.l.a() >= 1) {
                if (this.r) {
                    this.k.b(this);
                }
                this.r = true;
                if (!this.isChildOfFrameView) {
                    if (e()) {
                        Logger.d(Logger.ANIMATION_TAG, "on_Done_Animation 动画完后存在窗口入栈；" + this);
                        l lVar = this.mWindowMgr;
                        if (lVar != null) {
                            lVar.processEvent(IMgr.MgrType.WindowMgr, 28, this.b);
                        }
                    }
                    if (d()) {
                        Logger.d(Logger.ANIMATION_TAG, "on_Done_Animation 动画完后存在窗口出栈；" + this);
                        a(this.c);
                    } else {
                        ICallBack iCallBack = this.k.f6372a;
                        if (iCallBack != null) {
                            iCallBack.onCallBack(-1, null);
                        }
                    }
                }
            }
            this.k.l.b(this);
            this.c = null;
            if (z) {
                this.k.j();
            }
        }
        if (obtainApp() == null || !obtainApp().needRefreshApp()) {
            return;
        }
        obtainApp().obtainMgrData(IMgr.MgrType.FeatureMgr, 1, new Object[]{obtainWebView(), IFeature.F_UI, "updateAppFrameViews", null});
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a(ViewOptions viewOptions, AdaFrameItem adaFrameItem, AdaFrameItem adaFrameItem2, AdaFrameItem adaFrameItem3) {
        ((IWebview) adaFrameItem3).setScrollIndicator(viewOptions.getScrollIndicator());
        b(viewOptions, adaFrameItem, adaFrameItem2, adaFrameItem3);
    }

    public static void a(View view, int i, int i2, String str) {
        if (DeviceInfo.sDeviceSdkVer <= 10) {
            view.layout(i, i2, view.getRight() + i, view.getBottom() + i2);
        }
    }

    private void a(ArrayList<b> arrayList) {
        Logger.d(Logger.ANIMATION_TAG, "removeFrameViewFromViewStack DoAnimation Frame=" + this + ";Will PopFrames=" + arrayList);
        this.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 27, arrayList);
        ICallBack iCallBack = this.k.f6372a;
        if (iCallBack != null) {
            iCallBack.onCallBack(-1, null);
        }
    }

    public void a(int i, int i2) {
        ViewGroup.LayoutParams layoutParams = obtainMainView().getLayoutParams();
        if (layoutParams == null) {
            obtainMainView().setLayoutParams(new ViewGroup.LayoutParams(i, i2));
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
    }

    private View a(View view) throws Exception {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view.canScrollVertically(-1)) {
            return view;
        }
        int i = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i >= viewGroup.getChildCount()) {
                return null;
            }
            View viewA = a(viewGroup.getChildAt(i));
            if (viewA != null) {
                return viewA;
            }
            i++;
        }
    }

    private void b() {
        this.b = null;
        this.c = null;
        this.d = false;
    }
}
