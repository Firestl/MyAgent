package com.amap.api.location;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import com.loc.d;
import com.loc.ej;
import com.loc.en;
import com.loc.n;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AMapLocationClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1635a;
    public d b;

    public AMapLocationClient(Context context) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1635a = context.getApplicationContext();
            this.b = new d(context, null, null);
        } catch (Throwable th) {
            ej.a(th, "AMClt", "ne1");
        }
    }

    public AMapLocationClient(Context context, Intent intent) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1635a = context.getApplicationContext();
            this.b = new d(this.f1635a, intent, null);
        } catch (Throwable th) {
            ej.a(th, "AMClt", "ne2");
        }
    }

    public AMapLocationClient(Looper looper, Context context) {
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f1635a = context.getApplicationContext();
            this.b = new d(this.f1635a, null, looper);
        } catch (Throwable th) {
            ej.a(th, "AMClt", "ne3");
        }
    }

    public static String getDeviceId(Context context) {
        return n.y(context);
    }

    public static void setApiKey(String str) {
        try {
            AMapLocationClientOption.f1636a = str;
        } catch (Throwable th) {
            ej.a(th, "AMClt", "sKey");
        }
    }

    public void disableBackgroundLocation(boolean z) {
        try {
            if (this.b != null) {
                this.b.a(z);
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "dBackL");
        }
    }

    public void enableBackgroundLocation(int i, Notification notification) {
        try {
            if (this.b != null) {
                this.b.a(i, notification);
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "eBackL");
        }
    }

    public AMapLocation getLastKnownLocation() {
        try {
            if (this.b != null) {
                return this.b.e();
            }
            return null;
        } catch (Throwable th) {
            ej.a(th, "AMClt", "gLastL");
            return null;
        }
    }

    public String getVersion() {
        return "5.2.0";
    }

    public boolean isStarted() {
        try {
            if (this.b != null) {
                return this.b.a();
            }
            return false;
        } catch (Throwable th) {
            ej.a(th, "AMClt", "isS");
            return false;
        }
    }

    public void onDestroy() {
        try {
            if (this.b != null) {
                this.b.d();
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "onDy");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            if (aMapLocationListener == null) {
                throw new IllegalArgumentException("listener参数不能为null");
            }
            if (this.b != null) {
                this.b.a(aMapLocationListener);
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "sLocL");
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (aMapLocationClientOption == null) {
                throw new IllegalArgumentException("LocationManagerOption参数不能为null");
            }
            if (this.b != null) {
                this.b.a(aMapLocationClientOption);
            }
            if (aMapLocationClientOption.b) {
                aMapLocationClientOption.b = false;
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(aMapLocationClientOption.c)) {
                    jSONObject.put("amap_loc_scenes_type", aMapLocationClientOption.c);
                }
                en.a(this.f1635a, "O019", jSONObject);
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "sLocnO");
        }
    }

    public void startAssistantLocation() {
        try {
            if (this.b != null) {
                this.b.f();
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "stAssLo");
        }
    }

    public void startAssistantLocation(WebView webView) {
        try {
            if (this.b != null) {
                this.b.a(webView);
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "sttAssL1");
        }
    }

    public void startLocation() {
        try {
            if (this.b != null) {
                this.b.b();
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "stl");
        }
    }

    public void stopAssistantLocation() {
        try {
            if (this.b != null) {
                this.b.g();
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "stAssL");
        }
    }

    public void stopLocation() {
        try {
            if (this.b != null) {
                this.b.c();
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "stl");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            if (this.b != null) {
                this.b.b(aMapLocationListener);
            }
        } catch (Throwable th) {
            ej.a(th, "AMClt", "unRL");
        }
    }
}
