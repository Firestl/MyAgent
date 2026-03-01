package com.efs.sdk.base.core.config.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.ValueCallback;
import com.efs.sdk.base.IConfigRefreshAction;
import com.efs.sdk.base.core.c.f;
import com.efs.sdk.base.core.controller.ControllerCenter;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.base.observer.IConfigCallback;
import com.efs.sdk.base.observer.IEfsReporterObserver;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.feature.payment.weixin.WeiXinPay;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Random f1829a = new Random();
    public IConfigRefreshAction b;
    public boolean c;
    public b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<IConfigCallback, String[]> f1830e;
    public Handler f;
    public e g;
    public long h;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f1832a = new c(0);
    }

    public /* synthetic */ c(byte b) {
        this();
    }

    private void e() {
        if (!f.a.f1820a.a()) {
            Log.i("efs.config", "has no permission to refresh config from remote");
            return;
        }
        if (!this.c) {
            Log.i("efs.config", "disable refresh config from remote");
            return;
        }
        String strRefresh = g().refresh();
        Log.i("efs.config", "from server. efs config is ".concat(String.valueOf(strRefresh)));
        if (TextUtils.isEmpty(strRefresh)) {
            return;
        }
        a(strRefresh);
    }

    private void f() {
        boolean zA;
        try {
            zA = this.g.a(this.d);
        } catch (Throwable unused) {
            zA = false;
        }
        if (zA) {
            return;
        }
        this.f.sendEmptyMessageDelayed(3, 3000L);
    }

    private IConfigRefreshAction g() {
        IConfigRefreshAction iConfigRefreshAction = this.b;
        return iConfigRefreshAction == null ? com.efs.sdk.base.core.config.a.a.a() : iConfigRefreshAction;
    }

    private boolean h() {
        e.b();
        long j = 0;
        try {
            e eVar = this.g;
            eVar.c();
            if (eVar.f1834a != null) {
                j = eVar.f1834a.getLong("last_refresh_time", 0L);
            }
        } catch (Throwable unused) {
        }
        return System.currentTimeMillis() - j >= (this.d.f1828e * 60) * 1000;
    }

    private void i() {
        try {
            for (ValueCallback<Pair<Message, Message>> valueCallback : ControllerCenter.getGlobalEnvStruct().getCallback(1)) {
                Message messageObtain = Message.obtain(null, 1, new JSONObject(this.d.g).toString());
                Message messageObtain2 = Message.obtain();
                valueCallback.onReceiveValue(new Pair<>(messageObtain, messageObtain2));
                messageObtain.recycle();
                messageObtain2.recycle();
            }
            Iterator<IEfsReporterObserver> it = ControllerCenter.getGlobalEnvStruct().getEfsReporterObservers().iterator();
            while (it.hasNext()) {
                it.next().onConfigChange();
            }
        } catch (Throwable th) {
            Log.e("efs.config", th);
        }
    }

    public final Map<String, String> c() {
        return new HashMap(this.d.g);
    }

    public final void d() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.efs.sdk.base.core.config.a.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    for (IConfigCallback iConfigCallback : c.this.f1830e.keySet()) {
                        String[] strArr = (String[]) c.this.f1830e.get(iConfigCallback);
                        HashMap map = new HashMap();
                        if (strArr != null && strArr.length != 0) {
                            for (String str : strArr) {
                                if (c.this.d.g.containsKey(str)) {
                                    map.put(str, c.this.c().get(str));
                                    Log.i("efs.config", "--->>> configCallback key is " + str + " ## value is " + c.this.c().get(str));
                                }
                            }
                        }
                        iConfigCallback.onChange(map);
                    }
                    c.this.f1830e.clear();
                } catch (Throwable unused) {
                }
            }
        });
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        b bVar;
        int i = message.what;
        if (i == 0) {
            boolean zA = e.a();
            Log.i("efs.config", "delete old config is ".concat(String.valueOf(zA)));
            if (zA) {
                this.f.sendEmptyMessage(1);
            } else {
                e eVar = this.g;
                eVar.c();
                if (eVar.f1834a == null) {
                    bVar = null;
                } else {
                    b bVarA = b.a();
                    bVarA.f1827a = eVar.f1834a.getInt("cver", -1);
                    Set<String> setKeySet = eVar.f1834a.getAll().keySet();
                    HashMap map = new HashMap();
                    for (String str : setKeySet) {
                        String string = eVar.f1834a.getString(str, "");
                        if (!TextUtils.isEmpty(string)) {
                            map.put(str, string);
                        }
                    }
                    bVarA.a(map);
                    bVarA.a(eVar.f1834a.getString(WeiXinPay.PayInfoResult.KEY_SIGN, ""));
                    bVar = bVarA;
                }
                if (bVar == null) {
                    Log.i("efs.config", "first load local config false.");
                } else if (a(bVar)) {
                    Log.i("efs.config", "current config to same.");
                } else {
                    this.d = bVar;
                    String str2 = "load config from storage";
                    if (-1 != bVar.f1827a) {
                        i();
                        d();
                        str2 = "load config from storage and notify observer";
                    }
                    Log.i("efs.config", str2);
                }
            }
        } else if (i == 1) {
            int i2 = message.arg1;
            if (i2 <= this.d.f1827a) {
                Log.i("efs.config", "current config version is " + i2 + ", no need to refresh");
                Log.i("efs.config", "current config version(" + this.d.f1827a + ") is " + i2 + ", no need to refresh");
            } else {
                e();
            }
        } else if (i == 2) {
            try {
                if (f.a.f1820a.a()) {
                    if (h()) {
                        e();
                    } else {
                        Log.i("efs.config", "No update is required, less than 8h since the last update");
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if (i == 3) {
            f();
        }
        return true;
    }

    public c() {
        this.c = true;
        this.f1830e = new HashMap();
        this.f = new Handler(com.efs.sdk.base.core.util.concurrent.a.f1864a.getLooper(), this);
        this.g = new e();
        this.d = b.a();
        this.h = ControllerCenter.getGlobalEnvStruct().configRefreshDelayMills;
    }

    public static c a() {
        return a.f1832a;
    }

    public final void b() {
        this.f.sendEmptyMessage(0);
        this.f.sendEmptyMessageDelayed(2, this.h);
    }

    public final void a(int i) {
        if (i <= this.d.f1827a) {
            Log.i("efs.config", "current config version is " + i + ", no need to refresh");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.arg1 = i;
        messageObtain.what = 1;
        this.f.sendMessage(messageObtain);
    }

    public final String a(boolean z) {
        if (z) {
            return DeviceInfo.HTTPS_PROTOCOL + this.d.d;
        }
        return this.d.c + this.d.d;
    }

    public final void a(String str) {
        b bVarA = b.a();
        if (d.a(str, bVarA)) {
            if (a(bVarA)) {
                return;
            }
            this.d = bVarA;
            f();
            i();
            d();
            return;
        }
        this.f.sendEmptyMessageDelayed(1, 3000L);
    }

    private boolean a(b bVar) {
        if (this.d.f1827a >= bVar.f1827a) {
            return true;
        }
        Log.i("efs.config", "current config version (" + this.d.f1827a + ") is older than another (" + bVar.f1827a + ")");
        return false;
    }
}
