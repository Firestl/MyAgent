package io.dcloud.feature.weex;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import com.taobao.weex.WXSDKInstance;

/* JADX INFO: loaded from: classes3.dex */
public final class WXAnalyzerDelegate {
    public static boolean ENABLE = false;
    public Object mWXAnalyzer;

    public WXAnalyzerDelegate(Context context) {
        if (ENABLE && context != null) {
            try {
                this.mWXAnalyzer = Class.forName("com.taobao.weex.analyzer.WeexDevOptions").getDeclaredConstructor(Context.class).newInstance(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onCreate() {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onCreate", new Class[0]).invoke(this.mWXAnalyzer, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onDestroy() {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onDestroy", new Class[0]).invoke(this.mWXAnalyzer, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onException(WXSDKInstance wXSDKInstance, String str, String str2) {
        if (this.mWXAnalyzer == null) {
            return;
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            this.mWXAnalyzer.getClass().getDeclaredMethod("onException", WXSDKInstance.class, String.class, String.class).invoke(this.mWXAnalyzer, wXSDKInstance, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return false;
        }
        try {
            return ((Boolean) obj.getClass().getDeclaredMethod("onKeyUp", Integer.TYPE, KeyEvent.class).invoke(this.mWXAnalyzer, Integer.valueOf(i), keyEvent)).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void onPause() {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onPause", new Class[0]).invoke(this.mWXAnalyzer, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onResume() {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onResume", new Class[0]).invoke(this.mWXAnalyzer, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onStart() {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onStart", new Class[0]).invoke(this.mWXAnalyzer, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onStop() {
        Object obj = this.mWXAnalyzer;
        if (obj == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onStop", new Class[0]).invoke(this.mWXAnalyzer, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onWeexRenderSuccess(WXSDKInstance wXSDKInstance) {
        Object obj = this.mWXAnalyzer;
        if (obj == null || wXSDKInstance == null) {
            return;
        }
        try {
            obj.getClass().getDeclaredMethod("onWeexRenderSuccess", WXSDKInstance.class).invoke(this.mWXAnalyzer, wXSDKInstance);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public View onWeexViewCreated(WXSDKInstance wXSDKInstance, View view) {
        Object obj = this.mWXAnalyzer;
        if (obj == null || wXSDKInstance == null || view == null) {
            return null;
        }
        try {
            return (View) obj.getClass().getDeclaredMethod("onWeexViewCreated", WXSDKInstance.class, View.class).invoke(this.mWXAnalyzer, wXSDKInstance, view);
        } catch (Exception e2) {
            e2.printStackTrace();
            return view;
        }
    }
}
