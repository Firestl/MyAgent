package com.zx.a.I8b7;

import android.content.Context;
import android.text.TextUtils;
import com.zx.a.I8b7.p;
import com.zx.module.annotation.Java2C;
import com.zx.module.base.Callback;
import com.zx.module.base.ZXModule;
import com.zx.module.context.ContextHolder;
import com.zx.module.exception.ZXModuleInvokeException;
import com.zx.sdk.api.SAIDCallback;
import com.zx.sdk.api.ZXIDListener;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class o2 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicBoolean f6246e = new AtomicBoolean(false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ZXModule f6247a = null;
    public final n0 b;
    public final x1 c;
    public final w1 d;

    public class a implements ContextHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f6248a;

        public a(o2 o2Var, Context context) {
            this.f6248a = context;
        }

        @Override // com.zx.module.context.ContextHolder
        public Object getContext() {
            return this.f6248a;
        }
    }

    public class b implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SAIDCallback f6249a;

        public b(o2 o2Var, SAIDCallback sAIDCallback) {
            this.f6249a = sAIDCallback;
        }

        @Override // com.zx.module.base.Callback
        public void callback(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt("code");
                if (i == 0) {
                    this.f6249a.onSuccess(jSONObject.getString("data"));
                } else {
                    this.f6249a.onFailed(i, jSONObject.optString("data"));
                }
            } catch (Throwable th) {
                y1.a(th);
                SAIDCallback sAIDCallback = this.f6249a;
                if (sAIDCallback != null) {
                    sAIDCallback.onFailed(10000, th.getMessage());
                }
            }
        }
    }

    public class c implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ com.zx.sdk.api.Callback f6250a;

        public c(o2 o2Var, com.zx.sdk.api.Callback callback) {
            this.f6250a = callback;
        }

        @Override // com.zx.module.base.Callback
        public void callback(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt("code");
                if (i == 0) {
                    this.f6250a.onSuccess(jSONObject.getString("data"));
                } else {
                    this.f6250a.onFailed(i, jSONObject.optString("data"));
                }
            } catch (Throwable th) {
                y1.a(th);
                com.zx.sdk.api.Callback callback = this.f6250a;
                if (callback != null) {
                    callback.onFailed(10000, th.getMessage());
                }
            }
        }
    }

    public class d implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ com.zx.sdk.api.Callback f6251a;

        public d(o2 o2Var, com.zx.sdk.api.Callback callback) {
            this.f6251a = callback;
        }

        @Override // com.zx.module.base.Callback
        public void callback(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int i = jSONObject.getInt("code");
                if (i == 0) {
                    this.f6251a.onSuccess(jSONObject.getString("data"));
                } else {
                    this.f6251a.onFailed(i, jSONObject.optString("data"));
                }
            } catch (Throwable th) {
                y1.a(th);
                com.zx.sdk.api.Callback callback = this.f6251a;
                if (callback != null) {
                    callback.onFailed(10000, th.getMessage());
                }
            }
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final o2 f6252a = new o2();
    }

    public o2() {
        n0 n0Var = new n0();
        this.b = n0Var;
        x1 x1Var = new x1();
        this.c = x1Var;
        w1 w1Var = new w1();
        this.d = w1Var;
        n0Var.a("MESSAGE_ON_ZXID_CHANGED", x1Var);
        n0Var.a("MESSAGE_ON_ZXID_RECEIVED", w1Var);
        try {
            a(t2.f6286a);
        } catch (Throwable th) {
            n2.a(th, m2.a("ZXModule init failed: "));
        }
    }

    @Java2C.Method2C
    public native String a(String str, String str2, String str3, String str4, String str5, String str6, SAIDCallback sAIDCallback) throws JSONException, ZXModuleInvokeException;

    public void a(Context context) throws q1 {
        try {
            if (f6246e.getAndSet(true)) {
                return;
            }
            this.f6247a = p.a.f6253a.a(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("version", "3.3.2.25477");
            this.f6247a.invoke("setSDKVersion", jSONObject.toString());
            this.f6247a.onCreate(new a(this, context));
            this.f6247a.setMessageListener(this.b);
        } catch (Exception e2) {
            f6246e.set(false);
            StringBuilder sbA = m2.a("Raised exception while initializing: ");
            sbA.append(e2.getMessage());
            throw new q1(sbA.toString(), e2);
        }
    }

    @Java2C.Method2C
    public native void a(String str, com.zx.sdk.api.Callback callback) throws JSONException, ZXModuleInvokeException;

    @Java2C.Method2C
    public native void a(boolean z) throws JSONException, ZXModuleInvokeException;

    @Java2C.Method2C
    public native void b(String str, com.zx.sdk.api.Callback callback) throws JSONException, ZXModuleInvokeException;

    public void a() throws q1 {
        try {
            this.f6247a.start();
        } catch (Exception e2) {
            StringBuilder sbA = m2.a("Raised exception in start: ");
            sbA.append(e2.getMessage());
            throw new q1(sbA.toString(), e2);
        }
    }

    public void a(String str, ZXIDListener zXIDListener) throws q1 {
        if (zXIDListener != null) {
            try {
                w1 w1Var = this.d;
                w1Var.getClass();
                if (!TextUtils.isEmpty(str)) {
                    LinkedList<ZXIDListener> linkedList = w1Var.f6301a.get(str);
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(zXIDListener);
                    w1Var.f6301a.put(str, linkedList);
                }
            } catch (Exception e2) {
                y1.a(e2);
                StringBuilder sbA = m2.a("Raised exception while getZXID: nested exception is ");
                sbA.append(e2.getMessage());
                throw new q1(sbA.toString(), e2);
            }
        }
        a();
    }
}
