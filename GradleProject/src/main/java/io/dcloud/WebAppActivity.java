package io.dcloud;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.umeng.analytics.pro.bm;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.application.DCloudApplication;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.SplashView;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.io.PushReceiver;
import io.dcloud.common.adapter.ui.FrameSwitchView;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.ui.DCKeyboardManager;
import io.dcloud.common.core.ui.TabBarWebviewMgr;
import io.dcloud.common.util.AppRuntime;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.ImageLoaderUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ShortCutUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.language.LanguageUtil;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.internal.splash.ISplash;
import io.dcloud.feature.internal.splash.SplashViewDBackground;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class WebAppActivity extends io.dcloud.b {
    public static final long ONE_SECOND = 1000;
    public static final long SPLASH_SECOND = 5000;
    public boolean mSplashShowing;
    public AlertDialog o;
    public boolean p;
    public BroadcastReceiver r;
    public f s;
    public ArrayList<ICallBack> u;
    public Handler q = new Handler();
    public Bitmap t = null;
    public View mSplashView = null;
    public boolean v = false;
    public View w = null;
    public boolean x = false;
    public boolean y = true;
    public FrameLayout z = null;
    public LinearLayout A = null;
    public FrameLayout B = null;
    public ICallBack C = null;

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("android.intent.action.LOCALE_CHANGED")) {
                LanguageUtil.updateDeviceDefLocalLanguage(Locale.getDefault());
                return;
            }
            if (action.equals("com.huawei.intent.action.CLICK_STATUSBAR")) {
                WebAppActivity webAppActivity = WebAppActivity.this;
                if (webAppActivity.z == null || webAppActivity.u == null) {
                    return;
                }
                Iterator it = WebAppActivity.this.u.iterator();
                while (it.hasNext()) {
                    ((ICallBack) it.next()).onCallBack(1, action);
                }
            }
        }
    }

    public class b implements ICallBack {
        public b() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i != 1) {
                return null;
            }
            WebAppActivity webAppActivity = WebAppActivity.this;
            if (webAppActivity.C == null) {
                webAppActivity.x = true;
                return null;
            }
            webAppActivity.closeAppStreamSplash((String) obj);
            WebAppActivity.this.x = false;
            return null;
        }
    }

    public class c implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6321a;

        public c(String str) {
            this.f6321a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            WebAppActivity.this.b(this.f6321a, 1);
        }
    }

    public class d implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6322a;

        public d(String str) {
            this.f6322a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            WebAppActivity webAppActivity = WebAppActivity.this;
            webAppActivity.updateParam("closewebapp", webAppActivity.that);
            WebAppActivity.this.b(this.f6322a, 0);
        }
    }

    public class e implements DialogInterface.OnKeyListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6323a;

        public e(String str) {
            this.f6323a = str;
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 1 || i != 4) {
                return false;
            }
            WebAppActivity webAppActivity = WebAppActivity.this;
            webAppActivity.updateParam("closewebapp", webAppActivity.that);
            WebAppActivity.this.b(this.f6323a, 0);
            return true;
        }
    }

    public class g extends RelativeLayout {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6326a;
        public float b;
        public int c;
        public Paint d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6327e;
        public int f;
        public int g;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g gVar = g.this;
                int i = gVar.g - 5;
                gVar.g = i;
                if (i > 0) {
                    gVar.postDelayed(this, 5L);
                } else {
                    ViewGroup viewGroup = (ViewGroup) gVar.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(g.this);
                    }
                }
                g.this.invalidate();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g gVar = g.this;
                int i = gVar.f;
                int i2 = gVar.f6327e;
                int i3 = (i - i2) / 10;
                int i4 = i2 + (i3 <= 10 ? i3 < 1 ? 1 : i3 : 10);
                gVar.f6327e = i4;
                if (i > i4) {
                    gVar.postDelayed(this, 5L);
                } else if (i >= gVar.f6326a) {
                    gVar.a();
                }
                g.this.invalidate();
            }
        }

        public g(WebAppActivity webAppActivity, Context context) {
            super(context);
            this.c = 0;
            this.d = new Paint();
            this.f6327e = 0;
            this.f = 0;
            this.g = 255;
            this.f6326a = context.getResources().getDisplayMetrics().widthPixels;
            int i = context.getResources().getDisplayMetrics().heightPixels;
            if (i == 1280) {
                this.b = 6.0f;
            } else if (i != 1920) {
                this.b = context.getResources().getDisplayMetrics().heightPixels * 0.0045f;
            } else {
                this.b = 9.0f;
            }
        }

        public void a() {
            postDelayed(new a(), 50L);
        }

        public void b() {
            a(100);
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.d.setColor(Color.argb(this.g, 26, 173, 25));
            float f = this.c;
            canvas.drawRect(0.0f, f, this.f6327e, f + this.b, this.d);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            setMeasuredDimension(this.f6326a, this.c + ((int) this.b));
        }

        public void a(int i) {
            int i2 = (this.f6326a * i) / 100;
            if (this.f6327e >= this.f) {
                postDelayed(new b(), 5L);
            }
            this.f = i2;
        }
    }

    private Object b(Context context) {
        boolean booleanExtra;
        KeyEvent.Callback callback;
        KeyEvent.Callback callback2;
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("appid");
        Logger.d("splash", "WebAppActivity.onCreateSplash().appid=" + stringExtra + " this.appid=" + this.f + " this=" + this.that);
        StringBuilder sb = new StringBuilder();
        sb.append("WebAppActivity.onCreateSplash().mSplashView is Null =");
        sb.append(this.mSplashView == null);
        Logger.d("splash", sb.toString());
        View view = this.mSplashView;
        if (view != null && view.getTag() != null) {
            if (this.mSplashView.getTag().equals(stringExtra)) {
                return null;
            }
            closeAppStreamSplash(this.mSplashView.getTag().toString());
        }
        this.y = true;
        Logger.d("WebAppActivity", "onCreateSplash;intent=" + intent);
        boolean booleanExtra2 = intent.getBooleanExtra(IntentConst.IS_START_FIRST_WEB, false);
        boolean z = intent.hasExtra(IntentConst.DIRECT_PAGE) && BaseInfo.isWap2AppAppid(stringExtra);
        if ((booleanExtra2 && !z) || !(booleanExtra = intent.getBooleanExtra(IntentConst.SPLASH_VIEW, true))) {
            return null;
        }
        Logger.d("WebAppActivity", "onCreateSplash hasSplash=" + booleanExtra);
        if (intent.getBooleanExtra(IntentConst.PL_AUTO_HIDE, false)) {
            return null;
        }
        String stringExtra2 = intent.getStringExtra(IntentConst.WEBAPP_ACTIVITY_SPLASH_MODE);
        intent.removeExtra(IntentConst.WEBAPP_ACTIVITY_SPLASH_MODE);
        if (stringExtra2 == null || "".equals(stringExtra2.trim()) || (!"auto".equals(stringExtra2) && !"default".equals(stringExtra2))) {
            stringExtra2 = "auto";
        }
        Logger.d("WebAppActivity", "onCreateSplash __splash_mode__=" + stringExtra2);
        if (intent != null && intent.getBooleanExtra(IntentConst.WEBAPP_ACTIVITY_HIDE_STREAM_SPLASH, false)) {
            setViewAsContentView(new View(context), null);
            System.currentTimeMillis();
            this.mSplashShowing = true;
            return null;
        }
        String str = "drawable";
        if (intent != null) {
            if (intent.getBooleanExtra(IntentConst.WEBAPP_ACTIVITY_HAS_STREAM_SPLASH, false)) {
                if (this.mSplashView == null) {
                    if ("auto".equals(stringExtra2)) {
                        if (!z) {
                            StringBuilder sb2 = new StringBuilder();
                            String str2 = AbsoluteConst.STREAMAPP_KEY_ROOTPATH;
                            sb2.append(str2);
                            sb2.append("splash_temp/");
                            sb2.append(stringExtra);
                            sb2.append(".png");
                            this.t = a(sb2.toString(), intent.getStringExtra(IntentConst.APP_SPLASH_PATH), str2 + "splash/" + stringExtra + ".png");
                        }
                        if (this.t != null) {
                            SplashView splashView = new SplashView(this.that, this.t);
                            this.mSplashView = splashView;
                            if (!this.y) {
                                splashView.showWaiting(SplashView.STYLE_BLACK);
                            }
                        }
                    }
                    if (this.mSplashView == null) {
                        String stringExtra3 = intent.getStringExtra(IntentConst.WEBAPP_ACTIVITY_APPICON);
                        if (!TextUtils.isEmpty(stringExtra3) && new File(stringExtra3).exists()) {
                            this.t = BitmapFactory.decodeFile(stringExtra3);
                        }
                        this.mSplashView = a(context, this.t, intent.getStringExtra(IntentConst.NAME), stringExtra);
                        if (this.t == null && PdrUtil.isEmpty(stringExtra) && (callback2 = this.mSplashView) != null && (callback2 instanceof ISplash)) {
                            ((ISplash) callback2).setImageBitmap(BitmapFactory.decodeResource(getResources(), PdrR.getInt(getContext(), "drawable", "icon")));
                        }
                    }
                }
                this.mSplashView.setTag(stringExtra);
                setViewAsContentView(this.mSplashView, null);
                System.currentTimeMillis();
                this.mSplashShowing = true;
                Logger.e("IAN", "onCreateSplash aliyun ended");
                return null;
            }
            str = "drawable";
        }
        if (this.mSplashView != null) {
            return null;
        }
        try {
            if ("auto".equals(stringExtra2)) {
                if (!z) {
                    StringBuilder sb3 = new StringBuilder();
                    String str3 = AbsoluteConst.STREAMAPP_KEY_ROOTPATH;
                    sb3.append(str3);
                    sb3.append("splash_temp/");
                    sb3.append(stringExtra);
                    sb3.append(".png");
                    this.t = a(sb3.toString(), intent.getStringExtra(IntentConst.APP_SPLASH_PATH), str3 + "splash/" + stringExtra + ".png");
                }
                if (this.t == null) {
                    String string = SP.getOrCreateBundle(getContext(), "pdr").getString(SP.UPDATE_SPLASH_IMG_PATH, "");
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            if (PdrUtil.isDeviceRootDir(string)) {
                                this.t = BitmapFactory.decodeFile(string);
                            } else {
                                InputStream inputStreamOpen = getResources().getAssets().open(string);
                                this.t = BitmapFactory.decodeStream(inputStreamOpen);
                                inputStreamOpen.close();
                            }
                        } catch (Exception unused) {
                            this.t = null;
                        }
                    }
                    if (this.t == null && !this.p) {
                        this.t = BitmapFactory.decodeResource(getResources(), PdrR.getInt(context, str, "splash"));
                    }
                }
                if (this.t != null) {
                    SplashView splashView2 = new SplashView(this.that, this.t);
                    this.mSplashView = splashView2;
                    if (!this.y) {
                        splashView2.showWaiting(SplashView.STYLE_BLACK);
                    }
                }
            }
            if (this.mSplashView == null) {
                String stringExtra4 = intent.getStringExtra(IntentConst.WEBAPP_ACTIVITY_APPICON);
                if (!TextUtils.isEmpty(stringExtra4) && new File(stringExtra4).exists()) {
                    this.t = BitmapFactory.decodeFile(stringExtra4);
                }
                Log.d(Logger.MAIN_TAG, "use defaultSplash");
                this.mSplashView = a(context, this.t, intent.getStringExtra(IntentConst.NAME), stringExtra);
                if (this.t == null && PdrUtil.isEmpty(stringExtra) && (callback = this.mSplashView) != null && (callback instanceof ISplash)) {
                    ((ISplash) callback).setImageBitmap(BitmapFactory.decodeResource(getResources(), PdrR.getInt(getContext(), str, "icon")));
                }
            }
            this.mSplashView.setTag(stringExtra);
            setViewAsContentView(this.mSplashView, null);
            System.currentTimeMillis();
            this.mSplashShowing = true;
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void deviceInjectionGeoLocationJs(Context context) {
        BaseInfo.injectionGeolocationJS = !MobilePhoneModel.checkDeviceHtml5Geo();
    }

    private void f() {
        Intent intent = getIntent();
        boolean booleanExtra = intent != null ? intent.getBooleanExtra(IntentConst.PL_AUTO_HIDE, false) : false;
        Log.d("WebAppActivity", "checkAutoHide " + booleanExtra);
        if (booleanExtra) {
            Intent intent2 = new Intent();
            String stringExtra = intent.getStringExtra(IntentConst.PL_AUTO_HIDE_SHOW_PN);
            String stringExtra2 = intent.getStringExtra(IntentConst.PL_AUTO_HIDE_SHOW_ACTIVITY);
            intent2.putExtra(IntentConst.PL_AUTO_HIDE_SHOW_PN, true);
            intent2.setClassName(stringExtra, stringExtra2);
            this.that.startActivity(intent2);
            this.that.overridePendingTransition(0, 0);
            Log.d("WebAppActivity", "checkAutoHide return mini package " + stringExtra2);
        }
    }

    private String g() {
        return this.p ? getIntent().getStringExtra("appid") : BaseInfo.sDefaultBootApp;
    }

    private void h() {
        Intent intent = getIntent();
        if (intent.hasExtra("dcloud.push.broswer")) {
            PushReceiver.onReceive(this, intent);
        }
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra(IntentConst.IS_STREAM_APP, false);
            this.p = booleanExtra;
            if (booleanExtra) {
                return;
            }
            intent.removeExtra("appid");
        }
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void addClickStatusbarCallBack(ICallBack iCallBack) {
        if (this.u == null) {
            this.u = new ArrayList<>();
        }
        if (this.u.contains(iCallBack)) {
            return;
        }
        this.u.add(iCallBack);
    }

    public void addViewToContentView(View view) {
        int iIndexOfChild = this.z.indexOfChild(this.mSplashView);
        int childCount = this.z.getChildCount();
        if (childCount <= 0) {
            this.z.addView(view);
            return;
        }
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = this.z.getChildAt(i);
            if (childAt != view) {
                if ("AppRootView".equals(childAt.getTag())) {
                    this.z.addView(view, i);
                    this.z.removeView(childAt);
                    return;
                } else if (i == 0) {
                    if (childAt == this.mSplashView) {
                        this.z.addView(view, 0);
                    } else if (iIndexOfChild > 0) {
                        this.z.addView(view, iIndexOfChild - 1);
                    } else {
                        this.z.addView(view);
                    }
                }
            }
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            super.attachBaseContext(context);
        } else {
            super.attachBaseContext(LanguageUtil.updateContextLanguageAfterO(context, false));
        }
    }

    public int backPressed() {
        return 0;
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ void callBack(String str, Bundle bundle) {
        super.callBack(str, bundle);
    }

    @Override // io.dcloud.b
    public /* bridge */ /* synthetic */ void checkAndRequestPhoneState() {
        super.checkAndRequestPhoneState();
    }

    @Override // io.dcloud.b
    public /* bridge */ /* synthetic */ void checkAndRequestStoragePermission() {
        super.checkAndRequestStoragePermission();
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void closeAppStreamSplash(String str) {
        Logger.d("webappActivity closeAppStreamSplash");
        DCloudAdapterUtil.Plugin2Host_closeAppStreamSplash(str);
        Bitmap bitmap = this.t;
        if (bitmap != null && !bitmap.isRecycled()) {
            try {
                this.t.recycle();
                this.t = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.mSplashView != null) {
            Logger.d("webappActivity removeView mSplashView");
            View view = this.mSplashView;
            if (view instanceof g) {
                ((g) view).b();
            } else {
                this.z.removeView(view);
            }
            this.mSplashView = null;
            this.w = null;
        }
        this.v = false;
        this.mSplashShowing = false;
        ICallBack iCallBack = this.C;
        if (iCallBack != null) {
            iCallBack.onCallBack(1, null);
            this.C = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = g();
        }
        io.dcloud.a.a(this, str, "onCloseSplashNoAd", null);
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void closeSideBar() {
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        Intent intent = new Intent(getPackageName() + ".WebAppActivityFinish");
        intent.setPackage(getPackageName());
        this.that.sendBroadcast(intent);
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ int getActivityState() {
        return super.getActivityState();
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public String getErrorTipMsg() {
        int i = TestUtil.PointTime.mEc;
        if (i == 4) {
            return "" + getString(R.string.dcloud_common_not_sd_card);
        }
        if (i == 9) {
            return "" + getString(R.string.dcloud_common_sd_not_space);
        }
        int i2 = TestUtil.PointTime.mEt;
        if (i2 == 1) {
            return "" + getString(R.string.dcloud_common_setting_download_failed);
        }
        if (i2 != 3 && i2 != 2) {
            return "";
        }
        return "" + getString(R.string.dcloud_common_app_res_download_failed);
    }

    public View getProgressView() {
        View view = this.mSplashView;
        if (view == null || !(view instanceof g)) {
            return null;
        }
        return view;
    }

    @Override // io.dcloud.b, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public /* bridge */ /* synthetic */ Resources getResources() {
        return super.getResources();
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ String getUrlByFilePath(String str, String str2) {
        return super.getUrlByFilePath(str, str2);
    }

    @Override // io.dcloud.b
    public /* bridge */ /* synthetic */ void handleNewIntent(Intent intent) {
        super.handleNewIntent(intent);
    }

    @Override // io.dcloud.b, io.dcloud.common.DHInterface.IActivityHandler
    public boolean hasAdService() {
        return super.hasAdService() && this.v;
    }

    public void initBackToFrontSplashAd() {
        if (!SDK.isUniMPSDK() && (getApplication() instanceof DCloudApplication) && this.s == null) {
            this.s = new f(this);
            ((DCloudApplication) getApplication()).addActivityStatusListener(this.s);
        }
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ boolean isMultiProcessMode() {
        return super.isMultiProcessMode();
    }

    public boolean isSideBarCanRefresh() {
        if (this.z == null) {
            return false;
        }
        for (int i = 0; i < this.z.getChildCount(); i++) {
            View childAt = this.z.getChildAt(i);
            if (childAt != null && (childAt instanceof SplashView)) {
                return false;
            }
        }
        return true;
    }

    @Override // io.dcloud.common.DHInterface.IActivityDelegate
    public FrameLayout obtainActivityContentView() {
        return this.z;
    }

    @Override // io.dcloud.b, androidx.fragment.app.FragmentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onAppActive(IApp iApp) {
    }

    public void onAppActive(String str) {
    }

    public void onAppStart(IApp iApp) {
    }

    public void onAppStart(String str) {
    }

    public void onAppStop(String str) {
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ void onAsyncStartAppEnd(String str, Object obj) {
        super.onAsyncStartAppEnd(str, obj);
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ Object onAsyncStartAppStart(String str) {
        return super.onAsyncStartAppStart(str);
    }

    @Override // io.dcloud.b, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        int iBackPressed;
        Logger.e("back", "WebAppActivity onBackPressed");
        if (isMultiProcessMode() || !(2 == (iBackPressed = backPressed()) || 1 == iBackPressed)) {
            io.dcloud.a.a(this, TextUtils.isEmpty(this.f) ? g() : this.f, "onBack", this.w);
            super.onBackPressed();
        }
    }

    @Override // io.dcloud.b, io.dcloud.common.DHInterface.IOnCreateSplashView
    public /* bridge */ /* synthetic */ void onCloseSplash() {
        super.onCloseSplash();
    }

    @Override // io.dcloud.b, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // io.dcloud.b, io.dcloud.c, io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        BaseInfo.startTime = System.currentTimeMillis();
        if (!SDK.isUniMPSDK()) {
            AppRuntime.restartWeex(getApplication(), null, getIntent().getStringExtra("appid"));
        }
        Log.e("Html5Plus-onCreate", System.currentTimeMillis() + "");
        ShortCutUtil.activityNameSDK = getIntent().getStringExtra(IntentConst.WEBAPP_ACTIVITY_SHORTCUTACTIVITY);
        ShortCutUtil.mAutoCreateShortcut = getIntent().getBooleanExtra(IntentConst.WEBAPP_ACTIVITY_AUTOCREATESHORTCUT, true);
        Serializable serializableExtra = getIntent().getSerializableExtra(IntentConst.WEBAPP_ACTIVITY_EXTRAPRO);
        if (PdrUtil.isEmpty(serializableExtra)) {
            ShortCutUtil.extraProSDK = (HashMap) serializableExtra;
        }
        if (getIntent().hasExtra(IntentConst.INTENT_ORIENTATION)) {
            setRequestedOrientation(getIntent().getIntExtra(IntentConst.INTENT_ORIENTATION, 2));
        }
        deviceInjectionGeoLocationJs(getApplicationContext());
        Log.d("WebAppActivity", "onCreate");
        h();
        if (!this.p) {
            TestUtil.record(AbsoluteConst.RUN_5AP_TIME_KEY);
        }
        super.onCreate(bundle);
        BaseInfo.isFirstRun = false;
        IntentFilter intentFilter = new IntentFilter("com.huawei.intent.action.CLICK_STATUSBAR");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        a aVar = new a();
        this.r = aVar;
        registerReceiver(aVar, intentFilter, "huawei.permission.CLICK_STATUSBAR_BROADCAST", (Handler) null);
        FrameSwitchView frameSwitchView = FrameSwitchView.getInstance(this.that);
        if (!frameSwitchView.isInit()) {
            frameSwitchView.initView();
        }
        f();
    }

    public void onCreateAdSplash(Context context) {
        if (this.p || this.mSplashView == null || this.w != null) {
            return;
        }
        if (super.hasAdService()) {
            this.w = io.dcloud.a.a(this.that, new b(), BaseInfo.sDefaultBootApp);
        }
        View view = this.w;
        this.v = view != null;
        if (view != null) {
            if (view.getParent() != null) {
                ((ViewGroup) this.w.getParent()).removeView(this.w);
            }
            View view2 = this.mSplashView;
            if (view2 instanceof ViewGroup) {
                ((ViewGroup) view2).addView(this.w, new ViewGroup.LayoutParams(-1, -1));
            }
        }
    }

    @Override // io.dcloud.b, android.app.Activity
    public /* bridge */ /* synthetic */ boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // io.dcloud.b, io.dcloud.common.DHInterface.IOnCreateSplashView
    public Object onCreateSplash(Context context) {
        Window window = getWindow();
        int i = Build.VERSION.SDK_INT;
        if (i >= 19 && i <= 25) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.flags |= 1024;
            window.setAttributes(attributes);
        } else if (i > 25) {
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | 1280);
            window.setStatusBarColor(0);
            if (i >= 28 && window.getDecorView().getRootWindowInsets() != null) {
                WindowManager.LayoutParams attributes2 = window.getAttributes();
                attributes2.flags |= 1024;
                attributes2.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes2);
            }
        }
        if (this.mSplashView != null) {
            return null;
        }
        BaseInfo.splashCreateTime = System.currentTimeMillis();
        b(context);
        return null;
    }

    @Override // io.dcloud.b, io.dcloud.c, io.src.dcloud.adapter.DCloudBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Log.d("WebAppActivity", "onDestroy");
        super.onDestroy();
        try {
            unregisterReceiver(this.r);
            this.u.clear();
            PermissionUtil.clearUseRejectedCache();
            ImageLoaderUtil.clearCache();
        } catch (Exception unused) {
        }
        Handler handler = this.q;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (getApplication() instanceof DCloudApplication) {
            ((DCloudApplication) getApplication()).removeActivityStatusListener(this.s);
        }
        FrameSwitchView.getInstance(this.that).clearData();
        TabBarWebviewMgr.getInstance().setLancheTabBar(null);
        AlertDialog alertDialog = this.o;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.o.dismiss();
        }
        PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexDevtoolImpl", "unregisterReceiver", null, new Class[]{Context.class}, new Object[]{getContext()});
        this.o = null;
        Glide.get(this).clearMemory();
    }

    @Override // io.dcloud.b, android.app.Activity, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // io.dcloud.b, io.dcloud.e
    public /* bridge */ /* synthetic */ boolean onKeyEventExecute(ISysEventListener.SysEventType sysEventType, int i, KeyEvent keyEvent) {
        return super.onKeyEventExecute(sysEventType, i, keyEvent);
    }

    @Override // io.dcloud.b, android.app.Activity, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return super.onKeyLongPress(i, keyEvent);
    }

    @Override // io.dcloud.b, android.app.Activity, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // io.dcloud.b, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public /* bridge */ /* synthetic */ void onLowMemory() {
        super.onLowMemory();
    }

    @Override // io.dcloud.b, io.src.dcloud.adapter.DCloudBaseActivity
    public void onNewIntentImpl(Intent intent) {
        super.onNewIntentImpl(intent);
        if (intent.hasExtra("dcloud.push.broswer")) {
            PushReceiver.onReceive(this, intent);
        }
    }

    @Override // io.dcloud.b, io.dcloud.c, androidx.fragment.app.FragmentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    @Override // io.dcloud.b, androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public /* bridge */ /* synthetic */ void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // io.dcloud.b, io.dcloud.c, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.e("Html5Plus-onResume", System.currentTimeMillis() + "");
    }

    @Override // io.dcloud.b, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        PlatformUtil.SCREEN_WIDTH(this.that);
        PlatformUtil.SCREEN_HEIGHT(this.that);
        PlatformUtil.MESURE_SCREEN_STATUSBAR_HEIGHT(this.that);
    }

    public void recordDialog(Dialog dialog) {
    }

    @Override // io.dcloud.c
    public /* bridge */ /* synthetic */ void registerLocalReceiver(io.dcloud.feature.internal.reflect.BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        super.registerLocalReceiver(broadcastReceiver, intentFilter);
    }

    @Override // io.dcloud.c
    public /* bridge */ /* synthetic */ Intent registerReceiver(io.dcloud.feature.internal.reflect.BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        return super.registerReceiver(broadcastReceiver, intentFilter, str, handler);
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void removeClickStatusbarCallBack(ICallBack iCallBack) {
        ArrayList<ICallBack> arrayList = this.u;
        if (arrayList == null || !arrayList.contains(iCallBack)) {
            return;
        }
        this.u.remove(iCallBack);
    }

    public void removeFromRecord(Dialog dialog) {
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ void sendLocalBroadcast(Intent intent) {
        super.sendLocalBroadcast(intent);
    }

    public void setProgressView() {
        int i = 0;
        while (true) {
            if (i < this.z.getChildCount()) {
                View childAt = this.z.getChildAt(i);
                if (childAt != null && childAt == this.mSplashView) {
                    this.z.removeViewAt(i);
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        g gVar = new g(this, this.that);
        this.mSplashView = gVar;
        this.z.addView(gVar);
    }

    @Override // io.dcloud.b
    public /* bridge */ /* synthetic */ void setSecondPrivacyAlert() {
        super.setSecondPrivacyAlert();
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void setSideBarVisibility(int i) {
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void setSplashCloseListener(String str, ICallBack iCallBack) {
        this.C = iCallBack;
        if (this.mSplashView == null) {
            iCallBack.onCallBack(1, null);
            this.C = null;
        }
        if (this.x) {
            closeAppStreamSplash(str);
        }
        if (TextUtils.isEmpty(str)) {
            str = g();
        }
        io.dcloud.a.a(this, str, "onWillCloseSplash", this.w);
        this.x = false;
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void setViewAsContentView(View view, FrameLayout.LayoutParams layoutParams) {
        if (this.z == null) {
            FrameLayout frameLayout = new FrameLayout(this.that);
            this.z = frameLayout;
            if (this.A != null) {
                this.z.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                this.A.addView(this.z);
                setContentView(this.A);
            } else {
                setContentView(frameLayout);
            }
            DCKeyboardManager.getInstance().setContentView(this);
        }
        PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "checkNeedTitleView", null, new Class[]{Activity.class, String.class}, new Object[]{this.that, getIntent().getStringExtra("appid")});
        addViewToContentView(view);
        if (layoutParams != null) {
            view.setLayoutParams(layoutParams);
        }
        TestUtil.print(TestUtil.START_APP_SET_ROOTVIEW, "start" + view);
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void setWebViewIntoPreloadView(View view) {
        if (this.B == null) {
            FrameLayout frameLayout = new FrameLayout(this.that);
            this.B = frameLayout;
            this.z.addView(frameLayout, 0);
        }
        this.B.addView(view);
    }

    public void showDownloadDialog(String str, String str2) {
        if (this.mSplashShowing) {
            AlertDialog alertDialog = this.o;
            if (alertDialog == null || !alertDialog.isShowing()) {
                if (this.o == null) {
                    this.o = new AlertDialog.Builder(this.that).create();
                }
                this.o.setTitle(getString(R.string.dcloud_common_tips));
                if (TestUtil.PointTime.mEc == 20) {
                    this.o.setMessage(getString(R.string.dcloud_common_no_network_tips));
                } else if (str != null) {
                    this.o.setMessage(getString(R.string.dcloud_common_into) + str + getString(R.string.dcloud_common_fail) + getErrorTipMsg());
                } else {
                    this.o.setMessage(getString(R.string.dcloud_common_run_app_failed) + getErrorTipMsg());
                }
                this.o.setCanceledOnTouchOutside(false);
                this.o.setButton(-1, getString(R.string.dcloud_common_retry), new c(str2));
                this.o.setButton(-2, getString(R.string.dcloud_common_close), new d(str2));
                this.o.setOnKeyListener(new e(str2));
                this.o.show();
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void showSplashWaiting() {
        if (this.y) {
            View view = this.mSplashView;
            if (view instanceof SplashView) {
                ((SplashView) view).showWaiting();
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void sideBarHideMenu() {
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void sideBarShowMenu(String str, String str2, IWebview iWebview, String str3) {
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ void unregisterReceiver(io.dcloud.feature.internal.reflect.BroadcastReceiver broadcastReceiver) {
        super.unregisterReceiver(broadcastReceiver);
    }

    @Override // io.dcloud.b, io.dcloud.common.DHInterface.IActivityHandler
    public void updateParam(String str, Object obj) {
        if (AbsoluteConst.JSON_KEY_PROGRESS.equals(str)) {
            View view = this.mSplashView;
            if (view instanceof g) {
                ((g) view).a(((Integer) obj).intValue());
                return;
            }
            return;
        }
        if ("setProgressView".equals(str)) {
            setProgressView();
        } else {
            super.updateParam(str, obj);
        }
    }

    @Override // io.dcloud.common.DHInterface.IActivityHandler
    public void updateSplash(String str) {
        KeyEvent.Callback callback = this.mSplashView;
        if (callback == null || !(callback instanceof ISplash)) {
            return;
        }
        ((ISplash) callback).setNameText(str);
    }

    @Override // io.dcloud.c, io.dcloud.common.DHInterface.IActivityHandler
    public /* bridge */ /* synthetic */ void registerReceiver(io.dcloud.feature.internal.reflect.BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        super.registerReceiver(broadcastReceiver, intentFilter);
    }

    private Bitmap a(String str, String str2, String str3) {
        Bitmap bitmapDecodeFile = null;
        try {
            if (!TextUtils.isEmpty(str) && new File(str).exists()) {
                Logger.d(Logger.MAIN_TAG, "use splashPath=" + str);
                bitmapDecodeFile = BitmapFactory.decodeFile(str);
                if (bitmapDecodeFile != null) {
                    this.y = false;
                    try {
                        DHFile.deleteFile(str);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    Logger.d(Logger.MAIN_TAG, "use splashPath=" + str);
                }
            }
            if (bitmapDecodeFile == null && !TextUtils.isEmpty(str2) && new File(str2).exists()) {
                Logger.d(Logger.MAIN_TAG, "use splashPath=" + str2);
                bitmapDecodeFile = BitmapFactory.decodeFile(str2);
            }
            if (bitmapDecodeFile != null || TextUtils.isEmpty(str3) || !new File(str3).exists()) {
                return bitmapDecodeFile;
            }
            Logger.d(Logger.MAIN_TAG, "use splashPath=" + str3);
            return BitmapFactory.decodeFile(str3);
        } catch (Exception e3) {
            e3.printStackTrace();
            return bitmapDecodeFile;
        }
    }

    public class f extends Handler implements DCLoudApplicationImpl.ActivityStatusListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public SoftReference<Activity> f6324a;
        public String b;
        public int c;
        public long d = 0;

        public f(Activity activity) {
            this.c = 0;
            this.f6324a = new SoftReference<>(activity);
            String str = SP.getsBundleData(WebAppActivity.this.getContext(), String.valueOf(PlatformUtil.invokeFieldValue("io.dcloud.feature.gg.dcloud.ADHandler", "AdTag", null)), "uniad");
            if (PdrUtil.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("splash")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("splash");
                    this.b = jSONObjectOptJSONObject.getString("_fr_");
                    try {
                        this.c = Integer.parseInt(jSONObjectOptJSONObject.optString("_frt_"));
                    } catch (Exception unused) {
                        this.c = 180;
                    }
                }
            } catch (JSONException unused2) {
            }
        }

        private void a() {
            String str = SP.getsBundleData(WebAppActivity.this.getContext(), String.valueOf(PlatformUtil.invokeFieldValue("io.dcloud.feature.gg.dcloud.ADHandler", "AdTag", null)), "uniad");
            if (PdrUtil.isEmpty(str)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("splash")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("splash");
                    this.b = jSONObjectOptJSONObject.getString("_fr_");
                    try {
                        this.c = Integer.parseInt(jSONObjectOptJSONObject.getString("_frt_"));
                    } catch (Exception unused) {
                        this.c = 180;
                    }
                    if (this.b.equals("1")) {
                        onBack();
                    }
                }
            } catch (JSONException unused2) {
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                a("recreateSplash");
                removeMessages(1);
            } else {
                if (i != 2) {
                    return;
                }
                a("recreateSplash");
            }
        }

        @Override // io.dcloud.application.DCLoudApplicationImpl.ActivityStatusListener
        public void onBack() {
            Activity activity = this.f6324a.get();
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            if (TextUtils.isEmpty(this.b)) {
                this.d = 0L;
                a();
            } else {
                if (!this.b.equals("1")) {
                    this.d = 0L;
                    return;
                }
                int i = this.c > 60 ? 30 : 0;
                this.d = SystemClock.elapsedRealtime();
                sendEmptyMessageDelayed(1, i * 1000);
                sendEmptyMessageDelayed(2, (i + 1800) * 1000);
            }
        }

        @Override // io.dcloud.application.DCLoudApplicationImpl.ActivityStatusListener
        public void onFront() {
            Activity activity = this.f6324a.get();
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            if (!hasMessages(1) && !TextUtils.isEmpty(this.b) && this.b.equals("1")) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - ((long) ((this.c - 5) * 1000));
                long j = this.d;
                if (jElapsedRealtime > j && j > 0) {
                    a("showSplash");
                }
            }
            removeMessages(1);
            removeMessages(2);
        }

        private void a(String str) {
            String str2;
            String str3 = BaseInfo.sDefaultBootApp;
            if ("HBuilder".equals(str3) && (str2 = BaseInfo.sCurrentAppOriginalAppid) != null) {
                str3 = str2;
            }
            try {
                EntryProxy.getInstnace().getCoreHandler().dispatchEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{null, bm.aA, str, new Object[]{str3, this.f6324a.get(), this}});
            } catch (Exception unused) {
            }
        }
    }

    private View a(Context context, Bitmap bitmap, String str, String str2) {
        boolean z;
        String strB = b("DCLOUD_STREAMAPP_CHANNEL");
        if (PdrUtil.isEmpty(str2) && PdrUtil.isEmpty(str)) {
            str = getString(PdrR.getInt(context, "string", "app_name"));
            z = true;
        } else {
            z = false;
        }
        return "_12214060304".equals(strB) ? (View) PlatformUtil.newInstance("io.dcloud.html5pframework.splash.SplashView4Yunos", new Class[]{Context.class, Bitmap.class, String.class}, new Object[]{context, bitmap, str}) : new SplashViewDBackground(context, bitmap, str, z);
    }

    private String b(String str) {
        if (DCloudAdapterUtil.isPlugin()) {
            return null;
        }
        try {
            Bundle bundle = this.that.getPackageManager().getApplicationInfo(getPackageName(), 128) != null ? this.that.getPackageManager().getApplicationInfo(getPackageName(), 128).metaData : null;
            if (bundle == null || PdrUtil.isEmpty(bundle.get(str))) {
                return null;
            }
            return String.valueOf(bundle.get(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i) {
        Intent intent = new Intent(AbsoluteConst.ACTION_APP_DOWNLOAD_ERROR_DIALOG_CLICKED);
        intent.putExtra("type", i);
        intent.putExtra("appid", str);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }
}
