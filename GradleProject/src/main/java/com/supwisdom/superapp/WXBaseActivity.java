package com.supwisdom.superapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.igexin.assist.sdk.AssistPushConsts;
import com.supwisdom.superapp.ui.activity.LoginActivity;
import com.taobao.weex.common.Constants;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.mipush.sdk.MiPushMessage;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import supwisdom.cm1;
import supwisdom.cn1;
import supwisdom.en1;
import supwisdom.fn1;
import supwisdom.lm1;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WXBaseActivity extends AppCompatActivity {
    public static final String TAG = "syncTag";
    public Handler handler = new Handler();
    public String trackPageName;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            WXBaseActivity.this.finish();
            if (WXBaseActivity.this.isExitActivity(LoginActivity.class)) {
                LoginActivity.loginFinish();
            }
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Runnable f3982a;

        public b(Runnable runnable) {
            this.f3982a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Intent intent = WXBaseActivity.this.getIntent();
                if ("android.intent.action.VIEW".equals(intent.getAction())) {
                    WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(WXBaseActivity.this.getBaseContext(), fn1.i, cm1.class);
                    Uri data = intent.getData();
                    String strA = en1.a(WXBaseActivity.this.getApplicationContext(), "INTENT_URL", "");
                    if ("".equals(strA)) {
                        WXApplication.homeUniMP.sendUniMPEvent("addRouterPathListener", data);
                    } else {
                        WXApplication.homeUniMP.sendUniMPEvent("addRouterPathListener", strA);
                        en1.a(WXBaseActivity.this.getApplicationContext(), "INTENT_URL");
                    }
                    WXBaseActivity.this.handler.post(this.f3982a);
                    return;
                }
                String strA2 = en1.a(WXBaseActivity.this.getApplicationContext(), "INTENT_URL", "");
                if (strA2 == null || "".equals(strA2)) {
                    String stringExtra = intent.getStringExtra(AssistPushConsts.MSG_TYPE_PAYLOAD);
                    if (stringExtra != null) {
                        JSONObject object = JSON.parseObject(stringExtra);
                        object.getString("messageType");
                        String string = object.getString(MiPushMessage.KEY_MESSAGE_ID);
                        object.put("type", (Object) Constants.Event.CLICK);
                        object.put(RemoteMessageConst.MSGID, (Object) string);
                        try {
                            WXApplication.homeUniMP.sendUniMPEvent("addEventListener", object);
                        } catch (Exception e2) {
                            lm1.a(e2);
                            e2.printStackTrace();
                        }
                        WXBaseActivity.this.handler.post(this.f3982a);
                        return;
                    }
                    WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(WXBaseActivity.this.getBaseContext(), fn1.i, cm1.class);
                } else {
                    org.json.JSONObject jSONObject = new org.json.JSONObject();
                    jSONObject.put("uri", strA2);
                    WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(WXBaseActivity.this.getBaseContext(), fn1.i, cm1.class, jSONObject);
                    en1.a(WXBaseActivity.this.getApplicationContext(), "INTENT_URL");
                }
                WXBaseActivity.this.handler.post(this.f3982a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window != null) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
                if (z) {
                    method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    method.invoke(window, 0, Integer.valueOf(i));
                }
                try {
                    if (Build.VERSION.SDK_INT >= 23 && cn1.e()) {
                        if (z) {
                            activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                        } else {
                            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
                        }
                    }
                    return true;
                } catch (Exception unused) {
                    return true;
                }
            } catch (Exception unused2) {
            }
        }
        return false;
    }

    public static synchronized String getAppName(Context context) {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
        return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isExitActivity(Class<?> cls) {
        ComponentName componentNameResolveActivity = new Intent(this, cls).resolveActivity(getPackageManager());
        if (componentNameResolveActivity != null) {
            Iterator<ActivityManager.RunningTaskInfo> it = ((ActivityManager) getSystemService("activity")).getRunningTasks(10).iterator();
            while (it.hasNext()) {
                if (it.next().baseActivity.equals(componentNameResolveActivity)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void setAndroidNativeLightStatusBar(Activity activity, boolean z) {
        View decorView = activity.getWindow().getDecorView();
        if (z) {
            decorView.setSystemUiVisibility(9216);
        } else {
            decorView.setSystemUiVisibility(1280);
        }
    }

    public static boolean setFlymeLightStatusBar(Activity activity, boolean z) {
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
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static void setLightStatusBar(Activity activity, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            int iA = cn1.a();
            if (iA == 1) {
                MIUISetStatusBarLightMode(activity, z);
            } else if (iA == 2) {
                setFlymeLightStatusBar(activity, z);
            } else {
                if (iA != 3) {
                    return;
                }
                setAndroidNativeLightStatusBar(activity, z);
            }
        }
    }

    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            if (i >= 19) {
                activity.getWindow().setFlags(67108864, 67108864);
            }
        } else {
            Window window = activity.getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    public void logout() {
        if (this instanceof LoginActivity) {
            return;
        }
        Toast.makeText(this, "账号在其他设备登录，请重新登录", 1).show();
        fn1.w = "";
        sh1.c.b(fn1.o, "");
        Intent intent = new Intent();
        intent.addFlags(268468224);
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(-1);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        MobclickAgent.onPageEnd(this.trackPageName);
        MobclickAgent.onPause(this);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        MobclickAgent.onPageStart(this.trackPageName);
        MobclickAgent.onResume(this);
        super.onResume();
    }

    public void openMini() {
        try {
            a aVar = new a();
            if (WXApplication.homeUniMP != null && fn1.i.equals(WXApplication.homeUniMP.getAppid())) {
                if (WXApplication.homeUniMP.isRuning()) {
                    this.handler.postDelayed(new b(aVar), 500L);
                    return;
                }
                return;
            }
            Intent intent = getIntent();
            if (!"android.intent.action.VIEW".equals(intent.getAction())) {
                String strA = en1.a(getApplicationContext(), "INTENT_URL", "");
                if (strA == null || "".equals(strA)) {
                    WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(getBaseContext(), fn1.i, cm1.class);
                } else {
                    org.json.JSONObject jSONObject = new org.json.JSONObject();
                    jSONObject.put("uri", strA);
                    WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(getBaseContext(), fn1.i, cm1.class, jSONObject);
                    en1.a(getApplicationContext(), "INTENT_URL");
                }
                this.handler.postDelayed(aVar, 2000L);
                return;
            }
            Uri data = intent.getData();
            org.json.JSONObject jSONObject2 = new org.json.JSONObject();
            jSONObject2.put("uri", data.toString());
            Log.d(TAG, "run: ===== " + data.toString());
            WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(getBaseContext(), fn1.i, cm1.class, jSONObject2);
            en1.a(getApplicationContext(), "INTENT_URL");
            Log.d(TAG, "end: ===== " + data.toString());
            this.handler.postDelayed(aVar, 2000L);
        } catch (Exception e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
    }

    public void setStatusBarVisibility(boolean z) {
        getWindow().setFlags(z ? 0 : 1024, 1024);
    }
}
