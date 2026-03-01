package cn.com.chinatelecom.account.api;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.a.d;
import cn.com.chinatelecom.account.api.b.b;
import cn.com.chinatelecom.account.api.d.c;
import cn.com.chinatelecom.account.api.e.f;
import cn.com.chinatelecom.account.api.e.g;
import cn.com.chinatelecom.account.api.e.j;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CtAuth {
    public static final String TAG = "CtAuth";
    public static volatile CtAuth instance = null;
    public static boolean isInit = false;
    public static String mAppId = "";
    public static String mAppSecret = "";
    public static Context mContext;
    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public static TraceLogger mTraceLogger;

    public static CtAuth getInstance() {
        if (instance == null) {
            synchronized (CtAuth.class) {
                if (instance == null) {
                    instance = new CtAuth();
                }
            }
        }
        return instance;
    }

    public static void info(String str, String str2) {
        if (mTraceLogger != null) {
            mTraceLogger.info("CT_" + str, str2);
        }
    }

    public static void postResultOnMainThread(final String str, final JSONObject jSONObject, final ResultListener resultListener) {
        mHandler.post(new Runnable() { // from class: cn.com.chinatelecom.account.api.CtAuth.1
            @Override // java.lang.Runnable
            public void run() {
                if (resultListener != null) {
                    try {
                        if (str != null) {
                            jSONObject.put("reqId", str);
                        }
                        resultListener.onResult(jSONObject.toString());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    f.c(str);
                }
            }
        });
    }

    public static void warn(String str, String str2, Throwable th) {
        if (mTraceLogger != null) {
            mTraceLogger.warn("CT_" + str, str2, th);
        }
    }

    public Context getContext() {
        return mContext;
    }

    public String getOperatorType() {
        Context context = mContext;
        if (context != null) {
            return g.a(context, false);
        }
        throw new IllegalArgumentException("Please call the init method");
    }

    public void getPreCodeParamsByJs(String str, cn.com.chinatelecom.account.api.b.a aVar) {
        info(TAG, "called getPreCodeParamsByJs()");
        if (aVar == null) {
            return;
        }
        if (mContext == null || TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppSecret)) {
            aVar.callbackPreCodeParams(j.e().toString());
        } else {
            new b().a(str, aVar);
        }
    }

    public void init(Context context, String str, String str2, TraceLogger traceLogger) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null!");
        }
        if (str == null) {
            throw new IllegalArgumentException("appId must not be null!");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("appSecret must not be null!");
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        mContext = context;
        c.a(mContext);
        mAppId = str;
        mAppSecret = str2;
        mTraceLogger = traceLogger;
    }

    public boolean isMobileDataEnabled() {
        Context context = mContext;
        if (context != null) {
            return g.d(context);
        }
        throw new IllegalArgumentException("Please call the init method");
    }

    @Deprecated
    public void requestPreCode(CtSetting ctSetting, ResultListener resultListener) {
        requestPreLogin(ctSetting, resultListener);
    }

    public void requestPreCodeByJs(String str, cn.com.chinatelecom.account.api.b.a aVar) {
        JSONObject jSONObjectE;
        String strOptString;
        String str2;
        info(TAG, "called requestPreCodeByJs()");
        if (aVar == null) {
            return;
        }
        if (mContext == null || TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppSecret)) {
            jSONObjectE = j.e();
        } else {
            String strOptString2 = null;
            if (TextUtils.isEmpty(str)) {
                str2 = null;
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    strOptString = jSONObject.optString("url");
                    try {
                        strOptString2 = jSONObject.optString("taskId");
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                } catch (Exception e3) {
                    e = e3;
                    strOptString = null;
                }
                String str3 = strOptString2;
                strOptString2 = strOptString;
                str2 = str3;
            }
            if (!TextUtils.isEmpty(strOptString2)) {
                if (g.c(mContext)) {
                    new b().a(str2, strOptString2, aVar);
                    return;
                } else if (g.d(mContext)) {
                    new b().b(str2, strOptString2, aVar);
                    return;
                } else {
                    aVar.callbackPreCode(j.d().toString());
                    return;
                }
            }
            jSONObjectE = j.f();
        }
        aVar.callbackPreCode(jSONObjectE.toString());
    }

    public void requestPreLogin(CtSetting ctSetting, int i, ResultListener resultListener) {
        JSONObject jSONObjectE;
        info(TAG, "called requestPreLogin()");
        if (resultListener == null) {
            return;
        }
        if (mContext == null || TextUtils.isEmpty(mAppId) || TextUtils.isEmpty(mAppSecret)) {
            jSONObjectE = j.e();
        } else {
            if (g.b(mContext)) {
                if (g.c(mContext)) {
                    new cn.com.chinatelecom.account.api.c.a(mContext, mAppId, mAppSecret).a(d.a(cn.com.chinatelecom.account.api.e.b.f1513e), ctSetting, i, resultListener);
                    return;
                } else if (g.d(mContext)) {
                    new cn.com.chinatelecom.account.api.c.a(mContext, mAppId, mAppSecret).b(d.a(cn.com.chinatelecom.account.api.e.b.f1513e), ctSetting, i, resultListener);
                    return;
                } else {
                    postResultOnMainThread(null, j.d(), resultListener);
                    return;
                }
            }
            jSONObjectE = j.a();
        }
        postResultOnMainThread(null, jSONObjectE, resultListener);
    }

    public void requestPreLogin(CtSetting ctSetting, ResultListener resultListener) {
        requestPreLogin(ctSetting, a.d, resultListener);
    }

    public void setDomainName(String str, String str2, String str3) {
        g.f1519a = str;
        g.b = str2;
        g.c = str3;
    }
}
