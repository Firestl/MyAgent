package io.dcloud.common.util;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import io.dcloud.common.DHInterface.IActivityDelegate;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.ui.DCKeyboardManager;
import io.dcloud.f.a.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AppStatusBarManager {
    public IApp mIApp;
    public f mWebAppInfo;
    public int mStatusBarDefaultColor = 0;
    public boolean isImmersive = false;
    public boolean isFullScreen = false;
    public boolean isHandledWhiteScreen = false;
    public boolean isTemporaryFullScreen = true;

    /* JADX WARN: Multi-variable type inference failed */
    public AppStatusBarManager(Activity activity, f fVar) {
        initStatusBarDefaultColor(activity);
        this.mWebAppInfo = fVar;
        this.mIApp = (IApp) fVar;
        initDirectImmersive();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void diyContentFullScreenBug(Activity activity) {
        if ((activity instanceof IActivityDelegate ? ((IActivityDelegate) activity).obtainActivityContentView() : null) == null) {
            return;
        }
        if (!this.isFullScreen && !this.isImmersive) {
            this.isTemporaryFullScreen = false;
            DCKeyboardManager.getInstance().setAdjust(false);
            return;
        }
        String metaValue = AndroidResources.getMetaValue("DCLOUD_INPUT_MODE");
        if (TextUtils.isEmpty(metaValue) || !metaValue.contains("adjustPan")) {
            DCKeyboardManager.getInstance().setAdjust(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getRootView(Activity activity) {
        return activity.findViewById(R.id.content);
    }

    private void initDirectImmersive() {
        if (!(this.mWebAppInfo.getActivity().getIntent().hasExtra(IntentConst.DIRECT_PAGE) && BaseInfo.isWap2AppAppid(this.mWebAppInfo.m)) || Build.VERSION.SDK_INT <= 19) {
            return;
        }
        JSONObject directStatusJson = getDirectStatusJson(this.mIApp);
        if (directStatusJson == null || !directStatusJson.has(AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)) {
            this.isImmersive = true;
        } else {
            this.isImmersive = directStatusJson.optBoolean(AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED);
        }
        BaseInfo.isImmersive = this.isImmersive;
    }

    private void initStatusBarDefaultColor(Activity activity) {
        if (activity == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        Window window = activity.getWindow();
        int statusbarColorIndex = getStatusbarColorIndex();
        if (statusbarColorIndex > 0) {
            this.mStatusBarDefaultColor = window.getWindowStyle().getColor(statusbarColorIndex, 0);
        }
        if (this.mStatusBarDefaultColor == 0) {
            this.mStatusBarDefaultColor = Color.parseColor("#D4D4D4");
        }
    }

    private void setMeizuStatusBarDarkIcon(Activity activity, boolean z) {
        if (activity != null) {
            try {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                activity.getWindow().setAttributes(attributes);
            } catch (Exception unused) {
            }
        }
    }

    private void setMiuiStatusBarDarkMode(Activity activity, boolean z) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Class<?> cls3 = Integer.TYPE;
            Method method = cls.getMethod("setExtraFlags", cls3, cls3);
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (Exception unused) {
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(final Activity activity, boolean z) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
        window.getDecorView().post(new Runnable() { // from class: io.dcloud.common.util.AppStatusBarManager.2
            @Override // java.lang.Runnable
            public void run() {
                View rootView = AppStatusBarManager.this.getRootView(activity);
                if (rootView.getParent() instanceof LinearLayout) {
                    rootView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                } else if (rootView.getParent() instanceof FrameLayout) {
                    rootView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                }
            }
        });
    }

    public boolean checkImmersedStatusBar(Context context, boolean z) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        int i = Build.VERSION.SDK_INT;
        boolean z2 = false;
        if (i < 19) {
            return false;
        }
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        boolean z3 = (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? false : bundle.getBoolean("immersed.status.bar", false);
        if (z3) {
            z = z3;
        }
        if (this.mWebAppInfo.l.equals("suggestedDevice") && z) {
            z = ((DeviceInfo.sBrand.equalsIgnoreCase(MobilePhoneModel.XIAOMI) || DeviceInfo.sBrand.contentEquals(MobilePhoneModel.MEIZU)) && i >= 21) || i >= 23;
        }
        this.isImmersive = z;
        if (!z) {
            if (this.mWebAppInfo.getActivity().getIntent().hasExtra(IntentConst.DIRECT_PAGE) && BaseInfo.isWap2AppAppid(this.mWebAppInfo.m)) {
                z2 = true;
            }
            if (z2 && !this.mIApp.manifestBeParsed()) {
                JSONObject directStatusJson = getDirectStatusJson(this.mIApp);
                if (directStatusJson == null || !directStatusJson.has(AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED)) {
                    this.isImmersive = true;
                } else {
                    this.isImmersive = directStatusJson.optBoolean(AbsoluteConst.JSONKEY_STATUSBAR_IMMERSED);
                }
                z = this.isImmersive;
            }
        }
        BaseInfo.isImmersive = this.isImmersive;
        return z;
    }

    public JSONObject getDirectStatusJson(IApp iApp) {
        JSONObject jSONObjectObtainThridInfo = iApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.DirectPageJsonData);
        if (jSONObjectObtainThridInfo != null && jSONObjectObtainThridInfo.has(AbsoluteConst.JSONKEY_STATUSBAR)) {
            JSONObject jSONObjectOptJSONObject = jSONObjectObtainThridInfo.optJSONObject(AbsoluteConst.JSONKEY_STATUSBAR);
            if (jSONObjectOptJSONObject != null) {
                return jSONObjectOptJSONObject;
            }
            return null;
        }
        JSONObject jSONObjectObtainThridInfo2 = iApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.SitemapJsonData);
        if (jSONObjectObtainThridInfo2 == null || !jSONObjectObtainThridInfo2.has(AbsoluteConst.JSONKEY_STATUSBAR)) {
            return null;
        }
        return jSONObjectObtainThridInfo2.optJSONObject(AbsoluteConst.JSONKEY_STATUSBAR);
    }

    public int getStatusBarDefaultColor() {
        return this.mStatusBarDefaultColor;
    }

    public int getStatusbarColorIndex() {
        try {
            return Integer.parseInt(PlatformUtil.invokeFieldValue(null, "Window_statusBarColor", PlatformUtil.newInstance(Base64.decode2String("Y29tLmFuZHJvaWQuaW50ZXJuYWwuUiRzdHlsZWFibGU="), null, null)).toString());
        } catch (Exception unused) {
            return -1;
        }
    }

    public boolean isFullScreenOrImmersive() {
        return this.isImmersive || this.isFullScreen;
    }

    public void setFullScreen(Activity activity, boolean z) {
        Window window = activity.getWindow();
        this.isFullScreen = z;
        if (z) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags |= 1024;
            if (Build.VERSION.SDK_INT >= 28) {
                attributes.layoutInDisplayCutoutMode = 1;
            }
            window.setAttributes(attributes);
        } else {
            WindowManager.LayoutParams attributes2 = window.getAttributes();
            attributes2.flags &= -1025;
            if (Build.VERSION.SDK_INT >= 28) {
                attributes2.layoutInDisplayCutoutMode = 0;
            }
            window.setAttributes(attributes2);
        }
        diyContentFullScreenBug(activity);
    }

    public void setImmersive(final Activity activity, boolean z) {
        if (activity != null) {
            int i = Build.VERSION.SDK_INT;
            if (i == 19 || ((DeviceInfo.sBrand.equalsIgnoreCase(MobilePhoneModel.SONY) && i >= 21) || (DeviceInfo.sBrand.equalsIgnoreCase(MobilePhoneModel.QiKU) && i >= 21))) {
                this.isImmersive = z;
                setTranslucentStatus(activity, z);
                diyContentFullScreenBug(activity);
                return;
            }
            if (i >= 21) {
                this.isImmersive = z;
                Window window = activity.getWindow();
                int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
                if (z) {
                    window.clearFlags(67108864);
                    window.addFlags(Integer.MIN_VALUE);
                    window.getDecorView().setSystemUiVisibility(systemUiVisibility | 1280);
                    window.setStatusBarColor(0);
                } else {
                    window.getDecorView().setSystemUiVisibility(systemUiVisibility & (-1281));
                    window.setStatusBarColor(this.mStatusBarDefaultColor);
                }
                window.getDecorView().post(new Runnable() { // from class: io.dcloud.common.util.AppStatusBarManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        View rootView = AppStatusBarManager.this.getRootView(activity);
                        if (rootView.getParent() instanceof LinearLayout) {
                            rootView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                        } else if (rootView.getParent() instanceof FrameLayout) {
                            rootView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                        }
                    }
                });
                diyContentFullScreenBug(activity);
            }
        }
    }

    public void setStatusBarColor(Activity activity, int i) {
        if (PdrUtil.checkStatusbarColor(i) && Build.VERSION.SDK_INT >= 21 && activity != null && !this.isImmersive) {
            Window window = activity.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
        }
    }

    public void setStatusBarMode(Activity activity, String str) {
        int i = Build.VERSION.SDK_INT;
        if (i < 21 || activity == null) {
            return;
        }
        if (PdrUtil.isEmpty(str)) {
            str = "nono";
        }
        boolean zEqualsIgnoreCase = str.equalsIgnoreCase("dark");
        Window window = activity.getWindow();
        String str2 = Build.BRAND;
        if (str2.equalsIgnoreCase(MobilePhoneModel.GOOGLE)) {
            str2 = Build.MANUFACTURER;
        }
        if (str2.equals(MobilePhoneModel.XIAOMI)) {
            setMiuiStatusBarDarkMode(activity, zEqualsIgnoreCase);
        } else if (str2.equals(MobilePhoneModel.MEIZU)) {
            setMeizuStatusBarDarkIcon(activity, zEqualsIgnoreCase);
        }
        if (i >= 23) {
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            int i2 = 8192;
            try {
                Class<?> cls = Class.forName("android.view.View");
                i2 = cls.getField("SYSTEM_UI_FLAG_LIGHT_STATUS_BAR").getInt(cls);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            window.getDecorView().setSystemUiVisibility(zEqualsIgnoreCase ? systemUiVisibility | i2 : systemUiVisibility & (~i2));
        }
    }
}
