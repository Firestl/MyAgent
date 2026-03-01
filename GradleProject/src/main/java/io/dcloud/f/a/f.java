package io.dcloud.f.a;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import io.dcloud.common.DHInterface.IAppInfo;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.IWebAppRootView;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.adapter.util.ViewRect;
import io.dcloud.common.util.AppStatusBarManager;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;

/* JADX INFO: loaded from: classes2.dex */
public class f implements IAppInfo {
    public AppStatusBarManager k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f6487a = null;
    public IWebAppRootView b = null;
    public IOnCreateSplashView c = null;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6488e = 0;
    public int f = 0;
    public int g = 0;
    public boolean h = false;
    public int i = 0;
    public ViewRect j = new ViewRect();
    public String l = "none";
    public String m = null;
    public boolean n = false;

    public class a implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6489a;

        public a(String str) {
            this.f6489a = str;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            if ("landscape".equals(this.f6489a)) {
                f.this.f6487a.setRequestedOrientation(6);
                return;
            }
            if ("landscape-primary".equals(this.f6489a)) {
                f.this.f6487a.setRequestedOrientation(0);
                return;
            }
            if ("landscape-secondary".equals(this.f6489a)) {
                f.this.f6487a.setRequestedOrientation(8);
                return;
            }
            if ("portrait".equals(this.f6489a)) {
                f.this.f6487a.setRequestedOrientation(7);
                return;
            }
            if ("portrait-primary".equals(this.f6489a)) {
                f.this.f6487a.setRequestedOrientation(1);
            } else if ("portrait-secondary".equals(this.f6489a)) {
                f.this.f6487a.setRequestedOrientation(9);
            } else {
                f.this.f6487a.setRequestedOrientation(4);
            }
        }
    }

    public void a(Activity activity) {
        if (this.k == null) {
            this.k = new AppStatusBarManager(activity, this);
        }
        this.f6487a = activity;
    }

    public int checkSelfPermission(String str, String str2) {
        return PermissionUtil.checkSelfPermission(this.f6487a, str, str2);
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void clearMaskLayerCount() {
        this.i = 0;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public Activity getActivity() {
        return this.f6487a;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public ViewRect getAppViewRect() {
        return this.j;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo, io.dcloud.common.DHInterface.IType_IntValue
    public int getInt(int i) {
        if (i == 0) {
            return this.d;
        }
        if (i == 1) {
            return this.g;
        }
        if (i != 2) {
            return -1;
        }
        return this.f6488e;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public int getMaskLayerCount() {
        return this.i;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public IOnCreateSplashView getOnCreateSplashView() {
        return this.c;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public int getRequestedOrientation() {
        return this.f6487a.getRequestedOrientation();
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public boolean isFullScreen() {
        return this.h;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public boolean isVerticalScreen() {
        return this.f6487a.getResources().getConfiguration().orientation == 1;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public IWebAppRootView obtainWebAppRootView() {
        return this.b;
    }

    public void requestPermissions(String[] strArr, int i) {
        PermissionUtil.requestPermissions(this.f6487a, strArr, i);
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void setFullScreen(boolean z) {
        if (BaseInfo.sGlobalFullScreen != z) {
            this.h = z;
            AppStatusBarManager appStatusBarManager = this.k;
            if (appStatusBarManager != null) {
                appStatusBarManager.setFullScreen(getActivity(), z);
            }
            updateScreenInfo(this.h ? 2 : 3);
        }
        BaseInfo.sGlobalFullScreen = z;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void setMaskLayer(boolean z) {
        if (z) {
            this.i++;
            return;
        }
        int i = this.i - 1;
        this.i = i;
        if (i < 0) {
            this.i = 0;
        }
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void setOnCreateSplashView(IOnCreateSplashView iOnCreateSplashView) {
        this.c = iOnCreateSplashView;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void setRequestedOrientation(String str) {
        try {
            MessageHandler.sendMessage(new a(str), 48L, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void setWebAppRootView(IWebAppRootView iWebAppRootView) {
        this.b = iWebAppRootView;
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void updateScreenInfo(int i) {
        if (!this.h && this.f == 0) {
            Rect rect = new Rect();
            this.f6487a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int i2 = rect.top;
            this.f = i2;
            if (i2 > 0) {
                SP.setBundleData(getActivity(), BaseInfo.PDR, "StatusBarHeight", String.valueOf(this.f));
            }
        }
        DisplayMetrics displayMetrics = this.f6487a.getResources().getDisplayMetrics();
        this.f6487a.getResources().getDisplayMetrics();
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        boolean zIsAllScreenDevice = PdrUtil.isAllScreenDevice(this.f6487a);
        if (zIsAllScreenDevice) {
            this.f6487a.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            int i5 = displayMetrics.heightPixels;
            int i6 = displayMetrics.widthPixels;
            AppStatusBarManager appStatusBarManager = this.k;
            if (appStatusBarManager != null && !appStatusBarManager.isFullScreenOrImmersive()) {
                i5 -= this.f;
            }
            i4 = i5;
            boolean z = PdrUtil.isNavigationBarExist(this.f6487a) && !this.n;
            int navigationBarHeight = PdrUtil.getNavigationBarHeight(this.f6487a);
            if (this.f6487a.getResources().getConfiguration().orientation == 1) {
                if (z) {
                    i4 -= navigationBarHeight;
                }
            } else if (z) {
                i3 = i6 - navigationBarHeight;
            }
            i3 = i6;
        }
        this.f6488e = i4;
        if (i == 2) {
            this.d = i3;
            this.g = i4;
        } else if (i == 1) {
            this.d = i3;
            if (zIsAllScreenDevice) {
                this.g = i4;
            } else {
                this.g = i4 - (this.k.isFullScreenOrImmersive() ? 0 : this.f);
            }
        } else {
            IWebAppRootView iWebAppRootView = this.b;
            if (iWebAppRootView != null) {
                this.d = iWebAppRootView.obtainMainView().getWidth();
                this.g = this.b.obtainMainView().getHeight();
            } else {
                this.d = i3;
                this.g = i4;
            }
        }
        int i7 = this.f6488e;
        int i8 = this.g;
        if (i7 < i8) {
            this.f6488e = i8;
        }
        this.j.onScreenChanged(this.d, i8);
    }

    @Override // io.dcloud.common.DHInterface.IAppInfo
    public void setRequestedOrientation(int i) {
        this.f6487a.setRequestedOrientation(i);
    }
}
