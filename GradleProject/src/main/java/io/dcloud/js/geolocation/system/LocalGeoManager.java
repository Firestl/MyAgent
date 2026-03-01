package io.dcloud.js.geolocation.system;

import android.content.Context;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.js.geolocation.GeoManagerBase;

/* JADX INFO: loaded from: classes3.dex */
public class LocalGeoManager extends GeoManagerBase {
    public static final String TAG = "LocalGeoManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public io.dcloud.js.geolocation.system.a f6758a;

    public class a implements IEventCallback {
        public a() {
        }

        @Override // io.dcloud.common.DHInterface.IEventCallback
        public Object onCallBack(String str, Object obj) {
            if ((!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, "close")) || !(obj instanceof IWebview)) {
                return null;
            }
            if (LocalGeoManager.this.f6758a != null) {
                LocalGeoManager.this.f6758a.c(io.dcloud.js.geolocation.system.a.q);
            }
            ((AdaFrameView) ((IWebview) obj).obtainFrameView()).removeFrameViewListener(this);
            return null;
        }
    }

    public LocalGeoManager(Context context) {
        super(context);
    }

    public io.dcloud.js.geolocation.system.a b() {
        if (this.f6758a == null) {
            this.f6758a = new io.dcloud.js.geolocation.system.a(this.mContext, "");
        }
        return this.f6758a;
    }

    @Override // io.dcloud.js.geolocation.GeoManagerBase
    public String execute(IWebview iWebview, String str, String[] strArr) {
        try {
            if (str.equals("getCurrentPosition")) {
                boolean z = Boolean.parseBoolean(strArr[1]);
                int i = Integer.parseInt(strArr[2]);
                if (PdrUtil.isEquals(strArr[3], "wgs84") || PdrUtil.isEmpty(strArr[3])) {
                    getCurrentLocation(iWebview, strArr[0], z, i);
                } else {
                    Deprecated_JSUtil.execCallback(iWebview, strArr[0], StringUtil.format(DOMException.JSON_ERROR_INFO, 17, "only support wgs84"), JSUtil.ERROR, true, false);
                }
            } else if (str.equals("watchPosition")) {
                boolean z2 = Boolean.parseBoolean(strArr[2]);
                iWebview.obtainFrameView().addFrameViewListener(new a());
                boolean z3 = PdrUtil.isEquals(strArr[3], "wgs84") || PdrUtil.isEmpty(strArr[3]);
                String str2 = strArr.length > 7 ? strArr[6] : com.igexin.push.core.b.m;
                int i2 = io.dcloud.js.geolocation.system.a.r;
                if (!com.igexin.push.core.b.m.equals(str2)) {
                    i2 = Integer.parseInt(str2);
                }
                String str3 = strArr.length > 8 ? strArr[7] : "5000";
                int i3 = !str3.equals(com.igexin.push.core.b.m) ? Integer.parseInt(str3) : 5000;
                if (z3) {
                    start(iWebview, strArr[0], strArr[1], z2, i2, i3);
                } else {
                    Deprecated_JSUtil.execCallback(iWebview, strArr[0], StringUtil.format(DOMException.JSON_ERROR_INFO, 17, "only support wgs84"), JSUtil.ERROR, true, false);
                }
            } else if (str.equals("clearWatch")) {
                stop(strArr[0]);
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public void getCurrentLocation(IWebview iWebview, String str, boolean z, int i) {
        b().a(iWebview, i, str);
    }

    @Override // io.dcloud.js.geolocation.GeoManagerBase
    public void onDestroy() {
        io.dcloud.js.geolocation.system.a aVar = this.f6758a;
        if (aVar != null) {
            aVar.a();
        }
        this.f6758a = null;
    }

    public void start(IWebview iWebview, String str, String str2, boolean z, int i, int i2) {
        if (b().a(iWebview, i, str, i2)) {
            this.keySet.add(str2);
        }
    }

    public void stop(String str) {
        if (this.f6758a == null || !this.keySet.contains(str)) {
            return;
        }
        this.keySet.remove(str);
        this.f6758a.c(io.dcloud.js.geolocation.system.a.q);
    }
}
