package com.amap.api.location;

import android.content.Context;
import android.os.Handler;
import com.igexin.push.config.c;
import com.loc.ej;
import com.loc.n;

/* JADX INFO: loaded from: classes.dex */
public class UmidtokenInfo {
    public static AMapLocationClient d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Handler f1652a = new Handler();
    public static String b = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f1653e = c.k;
    public static boolean c = true;

    public static class a implements AMapLocationListener {
        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (UmidtokenInfo.d != null) {
                    UmidtokenInfo.f1652a.removeCallbacksAndMessages(null);
                    UmidtokenInfo.d.onDestroy();
                }
            } catch (Throwable th) {
                ej.a(th, "UmidListener", "onLocationChanged");
            }
        }
    }

    public static String getUmidtoken() {
        return b;
    }

    public static void setLocAble(boolean z) {
        c = z;
    }

    public static synchronized void setUmidtoken(Context context, String str) {
        try {
            b = str;
            n.a(str);
            if (d == null && c) {
                a aVar = new a();
                d = new AMapLocationClient(context);
                AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
                aMapLocationClientOption.setOnceLocation(true);
                aMapLocationClientOption.setNeedAddress(false);
                d.setLocationOption(aMapLocationClientOption);
                d.setLocationListener(aVar);
                d.startLocation();
                f1652a.postDelayed(new Runnable() { // from class: com.amap.api.location.UmidtokenInfo.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (UmidtokenInfo.d != null) {
                                UmidtokenInfo.d.onDestroy();
                            }
                        } catch (Throwable th) {
                            ej.a(th, "UmidListener", "postDelayed");
                        }
                    }
                }, c.k);
            }
        } catch (Throwable th) {
            ej.a(th, "UmidListener", "setUmidtoken");
        }
    }
}
