package com.unicom.xiaowo.account.shield;

import android.content.Context;
import android.text.TextUtils;
import com.unicom.xiaowo.account.shield.c.c;
import com.unicom.xiaowo.account.shield.e.a;
import com.unicom.xiaowo.account.shield.e.f;
import com.unicom.xiaowo.account.shield.e.g;
import com.unicom.xiaowo.account.shield.e.i;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class UniAccountHelper {
    public static volatile UniAccountHelper s_instance;
    public Context mContext;

    public static UniAccountHelper getInstance() {
        if (s_instance == null) {
            synchronized (UniAccountHelper.class) {
                if (s_instance == null) {
                    s_instance = new UniAccountHelper();
                }
            }
        }
        return s_instance;
    }

    private void initFail(ResultListener resultListener, String str) {
        f.b(str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", 1);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", "");
            jSONObject.put("traceId", "");
            jSONObject.put("operatorType", "CU");
            if (resultListener != null) {
                resultListener.onResult(jSONObject.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public String getSdkVersion() {
        return c.b();
    }

    public boolean init(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            f.b("初始化参数不能为空");
            return false;
        }
        if (this.mContext != null) {
            f.b("重复初始化");
            return true;
        }
        this.mContext = context.getApplicationContext();
        g.a(str);
        g.b(str2);
        c.a().a(this.mContext, str, str2);
        g.f(i.b(this.mContext));
        g.g(a.a(this.mContext));
        return true;
    }

    public void login(int i, ResultListener resultListener) {
        if (this.mContext == null || TextUtils.isEmpty(g.a()) || TextUtils.isEmpty(g.b())) {
            initFail(resultListener, "sdk未初始化");
        } else {
            g.a(i);
            c.a().a(this.mContext, i, 1, resultListener);
        }
    }

    public void mobileAuth(int i, ResultListener resultListener) {
        if (this.mContext == null || TextUtils.isEmpty(g.a()) || TextUtils.isEmpty(g.b())) {
            initFail(resultListener, "sdk未初始化");
        } else {
            g.a(i);
            c.a().a(this.mContext, i, 2, resultListener);
        }
    }

    public void releaseNetwork() {
        com.unicom.xiaowo.account.shield.e.c.a().b();
    }

    public void setLogEnable(boolean z) {
        c.a().a(z);
    }

    public void setPrecheckCacheEnable(boolean z) {
        g.a(z);
    }
}
