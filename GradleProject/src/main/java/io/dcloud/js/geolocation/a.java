package io.dcloud.js.geolocation;

import android.content.Context;
import android.util.Log;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.speech.utils.cuid.util.DeviceId;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsMgr f6757a;
    public GeoManagerBase b = null;
    public GeoManagerBase c = null;
    public GeoManagerBase d = null;

    public a(AbsMgr absMgr) {
        this.f6757a = null;
        this.f6757a = absMgr;
    }

    private GeoManagerBase b(String str) {
        GeoManagerBase geoManagerBase;
        try {
            return (!str.equals("io.dcloud.js.geolocation.amap.AMapGeoManager") || (geoManagerBase = (GeoManagerBase) PlatformUtil.invokeMethod(str, "getInstance", null, new Class[]{Context.class}, new Object[]{this.f6757a.getContext()})) == null) ? (GeoManagerBase) Class.forName(str).getConstructor(Context.class).newInstance(this.f6757a.getContext()) : geoManagerBase;
        } catch (Exception unused) {
            Log.w("geoLoaction", str + " exception");
            return null;
        }
    }

    public String a(IWebview iWebview, String str, String[] strArr) {
        GeoManagerBase geoManagerBaseA;
        boolean zEquals = "clearWatch".equals(str);
        if (zEquals) {
            GeoManagerBase geoManagerBase = this.c;
            if (geoManagerBase == null || !geoManagerBase.hasKey(strArr[0])) {
                GeoManagerBase geoManagerBase2 = this.b;
                if (geoManagerBase2 == null || !geoManagerBase2.hasKey(strArr[0])) {
                    GeoManagerBase geoManagerBase3 = this.d;
                    geoManagerBaseA = (geoManagerBase3 == null || !geoManagerBase3.hasKey(strArr[0])) ? null : this.d;
                } else {
                    geoManagerBaseA = this.b;
                }
            } else {
                geoManagerBaseA = this.c;
            }
        } else {
            geoManagerBaseA = a(strArr[4]);
        }
        if (geoManagerBaseA != null) {
            geoManagerBaseA.execute(iWebview, str, strArr);
        } else if (!zEquals) {
            Deprecated_JSUtil.execCallback(iWebview, strArr[0], DOMException.toJSON(17, DOMException.MSG_GEOLOCATION_PROVIDER_ERROR), JSUtil.ERROR, true, false);
        }
        return null;
    }

    private GeoManagerBase a(String str) {
        GeoManagerBase geoManagerBaseB;
        if (!ConstantHelper.LOG_OS.equals(str) && !"sytem".equals(str) && !DeviceId.OLD_EXT_DIR.equals(str)) {
            geoManagerBaseB = this.c;
            if (geoManagerBaseB == null) {
                geoManagerBaseB = b("io.dcloud.js.geolocation.amap.AMapGeoManager");
            }
            this.c = geoManagerBaseB;
            if (geoManagerBaseB == null) {
                geoManagerBaseB = this.b;
                if (geoManagerBaseB == null) {
                    geoManagerBaseB = b("io.dcloud.js.geolocation.baidu.BaiduGeoManager");
                }
                this.b = geoManagerBaseB;
            }
        } else if (DeviceId.OLD_EXT_DIR.equals(str)) {
            geoManagerBaseB = this.b;
            if (geoManagerBaseB == null) {
                geoManagerBaseB = b("io.dcloud.js.geolocation.baidu.BaiduGeoManager");
            }
            this.b = geoManagerBaseB;
            if (geoManagerBaseB == null) {
                geoManagerBaseB = this.c;
                if (geoManagerBaseB == null) {
                    geoManagerBaseB = b("io.dcloud.js.geolocation.amap.AMapGeoManager");
                }
                this.c = geoManagerBaseB;
            }
        } else {
            geoManagerBaseB = null;
        }
        if (geoManagerBaseB == null) {
            geoManagerBaseB = this.d;
            if (geoManagerBaseB == null) {
                geoManagerBaseB = b("io.dcloud.js.geolocation.system.LocalGeoManager");
            }
            this.d = geoManagerBaseB;
        }
        return geoManagerBaseB;
    }

    public void a() {
        GeoManagerBase geoManagerBase = this.b;
        if (geoManagerBase != null) {
            geoManagerBase.onDestroy();
        }
        GeoManagerBase geoManagerBase2 = this.d;
        if (geoManagerBase2 != null) {
            geoManagerBase2.onDestroy();
        }
        GeoManagerBase geoManagerBase3 = this.c;
        if (geoManagerBase3 != null) {
            geoManagerBase3.onDestroy();
        }
    }
}
