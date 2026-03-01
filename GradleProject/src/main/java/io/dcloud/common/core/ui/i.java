package io.dcloud.common.core.ui;

import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.ITypeofAble;
import io.dcloud.common.adapter.ui.DHImageView;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.nineoldandroids.view.ViewHelper;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static View f6406a = null;
    public static DHImageView b = null;
    public static boolean c = false;

    public static class a implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6407a;

        public a(io.dcloud.common.core.ui.b bVar) {
            this.f6407a = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            BaseInfo.sDoingAnimation = false;
            DHImageView dHImageView = i.b;
            if (dHImageView == null || dHImageView.isNativeView()) {
                DHImageView dHImageView2 = i.b;
                if (dHImageView2 == null || !dHImageView2.isNativeView()) {
                    return;
                }
                this.f6407a.handleNativeViewByAction(i.b, 0);
                i.b = null;
                return;
            }
            DHImageView dHImageView3 = i.b;
            if (dHImageView3 != null) {
                dHImageView3.setIntercept(false);
                i.b.clearAnimation();
                i.b.setVisibility(4);
                i.b.setImageBitmap(null);
                i.b = null;
            }
            View view = i.f6406a;
            if (view != null) {
                view.clearAnimation();
                i.f6406a = null;
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            DHImageView dHImageView = i.b;
            if (dHImageView != null) {
                dHImageView.setIntercept(true);
            }
            BaseInfo.sDoingAnimation = true;
        }
    }

    public static class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6408a;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DHImageView dHImageView = i.b;
                if (dHImageView != null) {
                    dHImageView.clearAnimation();
                    i.b.setVisibility(4);
                    if (i.b.isNativeView()) {
                        b.this.f6408a.handleNativeViewByAction(i.b, 1);
                    }
                    i.b.removeNativeView();
                    i.b.setImageBitmap(null);
                    i.b.setTag(0);
                    i.b = null;
                }
                View view = i.f6406a;
                if (view != null) {
                    view.clearAnimation();
                    i.f6406a = null;
                }
            }
        }

        public b(io.dcloud.common.core.ui.b bVar) {
            this.f6408a = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            DHImageView dHImageView = i.b;
            if (dHImageView != null) {
                dHImageView.setIntercept(false);
                i.b.setNativeAnimationRuning(false);
            }
            BaseInfo.sDoingAnimation = false;
            DHImageView dHImageView2 = i.b;
            int i = (dHImageView2 == null || !dHImageView2.isNativeView()) ? MediaSessionCompat.MAX_BITMAP_SIZE_IN_DP : 0;
            View viewObtainMainView = i.f6406a;
            if (viewObtainMainView == null) {
                viewObtainMainView = this.f6408a.obtainMainView();
            }
            viewObtainMainView.postDelayed(new a(), i);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            DHImageView dHImageView = i.b;
            if (dHImageView != null) {
                dHImageView.setIntercept(true);
                i.b.setNativeAnimationRuning(true);
            }
            BaseInfo.sDoingAnimation = true;
        }
    }

    public static class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6410a;

        public c(io.dcloud.common.core.ui.b bVar) {
            this.f6410a = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            i.c(this.f6410a);
            View view = i.f6406a;
            if (view != null) {
                ViewHelper.setX(view, this.f6410a.obtainFrameOptions().left);
                ViewHelper.setY(i.f6406a, this.f6410a.obtainFrameOptions().top);
                i.f6406a.clearAnimation();
                i.f6406a = null;
            }
            BaseInfo.sDoingAnimation = false;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            BaseInfo.sDoingAnimation = true;
        }
    }

    public static class d implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6411a;

        public d(io.dcloud.common.core.ui.b bVar) {
            this.f6411a = bVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            i.c(this.f6411a);
            View view = i.f6406a;
            if (view != null) {
                ViewHelper.setX(view, this.f6411a.obtainFrameOptions().left);
                ViewHelper.setY(i.f6406a, this.f6411a.obtainFrameOptions().top);
                i.f6406a.clearAnimation();
                i.f6406a = null;
            }
            BaseInfo.sDoingAnimation = false;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            BaseInfo.sDoingAnimation = true;
        }
    }

    public static boolean b(io.dcloud.common.core.ui.b bVar) {
        ViewGroup viewGroup = (ViewGroup) bVar.obtainMainView();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i) instanceof ITypeofAble) {
                return true;
            }
        }
        return false;
    }

    public static void c(io.dcloud.common.core.ui.b bVar) {
        io.dcloud.common.core.ui.a aVar;
        if (bVar == null || (aVar = bVar.k) == null) {
            return;
        }
        aVar.obtainMainView().setBackgroundColor(-1);
        for (io.dcloud.common.core.ui.b bVar2 : bVar.k.e()) {
            if (bVar2.f()) {
                bVar2.d(false);
                bVar2.obtainMainView().setVisibility(0);
            }
        }
    }

    public static void a(io.dcloud.common.core.ui.b bVar, int i) {
        String str = bVar.getAnimOptions().mAnimType;
        String str2 = bVar.getAnimOptions().mAnimType_close;
        io.dcloud.common.core.ui.b bVar2 = (io.dcloud.common.core.ui.b) bVar.k.findFrameViewB(bVar);
        if (bVar2 == null) {
            return;
        }
        if (i == 1) {
            String closeAnimType = AnimOptions.getCloseAnimType(str2);
            if (bVar.mAccelerationType.equals("auto") && PdrUtil.isContains(closeAnimType, "slide")) {
                return;
            }
            if (bVar.mAccelerationType.equals("auto") && !PdrUtil.isEquals(closeAnimType, "pop-out") && !BaseInfo.isDefaultAim && bVar2.mSnapshot == null) {
                return;
            }
            if (bVar.mAccelerationType.equals("none") && !PdrUtil.isEquals(closeAnimType, "pop-out") && bVar2.mSnapshot == null) {
                return;
            }
            if (!bVar.mAccelerationType.equals("none") && bVar2.mSnapshot == null) {
                BaseInfo.sOpenedCount--;
            }
        } else {
            if (bVar.mAccelerationType.equals("auto") && PdrUtil.isContains(str2, "slide")) {
                return;
            }
            if (bVar.mAccelerationType.equals("auto") && !PdrUtil.isEquals(str, "pop-in") && !BaseInfo.isDefaultAim && bVar2.mSnapshot == null) {
                return;
            }
            if (bVar.mAccelerationType.equals("none") && !PdrUtil.isEquals(str2, "pop-in") && bVar2.mSnapshot == null) {
                return;
            }
            if (!bVar.mAccelerationType.equals("none") && bVar2.mSnapshot == null) {
                int i2 = BaseInfo.sOpenedCount + 1;
                BaseInfo.sOpenedCount = i2;
                c = i2 > 1;
            }
        }
        if (bVar2 != null) {
            f6406a = bVar2.obtainMainView();
            bVar2.k.a(bVar2, bVar);
            bVar2.chkUseCaptureAnimation(true, bVar2.hashCode(), bVar2.mSnapshot != null);
            if (bVar2.mAnimationCapture && BaseInfo.sAnimationCaptureB && !b(bVar2)) {
                Logger.e("mabo", "B页面是否启用截图动画方案:true | " + bVar2.getAnimOptions().mAnimType);
                a(i, bVar2, bVar);
            } else {
                Logger.e("mabo", "B页面是否启用截图动画方案:false | " + bVar2.getAnimOptions().mAnimType);
                b(i, bVar2, bVar);
            }
        }
        if (BaseInfo.sOpenedCount == 0) {
            c = false;
        }
    }

    public static void b(int i, io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.b bVar2) {
        Animation translateAnimation;
        int i2 = bVar2.obtainApp().getInt(0);
        if (i == 0) {
            if (PdrUtil.isEquals(bVar2.getAnimOptions().mAnimType, "pop-in")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    translateAnimation = AnimationUtils.loadAnimation(bVar.getContext(), R.anim.dcloud_page_open_exit);
                    a(bVar, bVar2);
                } else {
                    translateAnimation = new TranslateAnimation(bVar.obtainFrameOptions().left, (-i2) / 4, 0.0f, 0.0f);
                    translateAnimation.setDuration(bVar2.getAnimOptions().duration_show);
                    translateAnimation.setInterpolator(new DecelerateInterpolator());
                }
            } else {
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                translateAnimation.setDuration(bVar2.getAnimOptions().duration_show);
            }
            translateAnimation.setAnimationListener(new c(bVar));
        } else {
            if (PdrUtil.isEquals(AnimOptions.getCloseAnimType(bVar2.getAnimOptions().mAnimType_close), "pop-out")) {
                if (Build.VERSION.SDK_INT >= 23) {
                    translateAnimation = AnimationUtils.loadAnimation(bVar.getContext(), R.anim.dcloud_page_close_enter);
                    a(bVar, bVar2);
                } else {
                    translateAnimation = new TranslateAnimation((-i2) / 4, bVar2.obtainFrameOptions().left, 0.0f, 0.0f);
                    translateAnimation.setDuration(bVar2.getAnimOptions().duration_close);
                    translateAnimation.setInterpolator(new DecelerateInterpolator());
                }
            } else {
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                translateAnimation.setDuration(bVar2.getAnimOptions().duration_close);
            }
            translateAnimation.setAnimationListener(new d(bVar));
        }
        View view = b;
        if (view == null) {
            view = f6406a;
        }
        view.startAnimation(translateAnimation);
        bVar.k.f(bVar);
    }

    public static void a(int i, io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.b bVar2) {
        TranslateAnimation translateAnimation;
        int i2 = bVar2.obtainApp().getInt(0);
        DHImageView dHImageViewA = bVar2.k.a(bVar, i, c);
        b = dHImageViewA;
        if (dHImageViewA == null) {
            b(i, bVar, bVar2);
            return;
        }
        if (i == 0) {
            dHImageViewA.setTag(Integer.valueOf(bVar.hashCode()));
            if (PdrUtil.isEquals(bVar2.getAnimOptions().mAnimType, "pop-in")) {
                translateAnimation = new TranslateAnimation(bVar.obtainFrameOptions().left, (-i2) / 4, 0.0f, 0.0f);
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(300L);
            } else {
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                translateAnimation.setDuration(bVar2.getAnimOptions().duration_show);
            }
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setAnimationListener(new a(bVar));
        } else {
            if (PdrUtil.isEquals(AnimOptions.getCloseAnimType(bVar2.getAnimOptions().mAnimType_close), "pop-out")) {
                translateAnimation = new TranslateAnimation((-i2) / 4, bVar.obtainFrameOptions().left, 0.0f, 0.0f);
                translateAnimation.setFillAfter(true);
                translateAnimation.setDuration(360L);
            } else {
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                translateAnimation.setDuration(bVar2.getAnimOptions().duration_close);
            }
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setAnimationListener(new b(bVar));
        }
        View view = b;
        if (view == null) {
            view = f6406a;
        }
        view.startAnimation(translateAnimation);
        bVar.k.f(bVar);
    }

    public static void a(io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.b bVar2) {
        if (bVar == null || bVar.k == null) {
            return;
        }
        if (bVar.obtainFrameOptions() != null && !PdrUtil.isEmpty(bVar.obtainFrameOptions().animationAlphaBackground)) {
            bVar.k.obtainMainView().setBackgroundColor(PdrUtil.stringToColor(bVar.obtainFrameOptions().animationAlphaBackground));
        }
        for (io.dcloud.common.core.ui.b bVar3 : bVar.k.e()) {
            if (bVar3 != bVar && bVar2 != bVar3 && bVar3.obtainMainView().getVisibility() == 0) {
                bVar3.d(true);
                bVar3.obtainMainView().setVisibility(4);
            }
        }
    }
}
