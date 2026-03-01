package io.dcloud.js.map.amap.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.amap.api.maps.AMapUtils;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class DHMapUtil {
    public static void calculateArea(IWebview iWebview, MapPoint mapPoint, MapPoint mapPoint2, String str) {
        Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(-100, DOMException.toString("高德地图暂不支持面积计算")), JSUtil.ERROR, true, false);
    }

    public static void calculateDistance(IWebview iWebview, MapPoint mapPoint, MapPoint mapPoint2, String str) {
        try {
            float fCalculateLineDistance = AMapUtils.calculateLineDistance(mapPoint.getLatLng(), mapPoint2.getLatLng());
            if (fCalculateLineDistance == 0.0f) {
                Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(-100, DOMException.toString("计算结果为0，请查看坐标是否正确")), JSUtil.ERROR, true, false);
            } else {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSUtil.execCallback(iWebview, str, fCalculateLineDistance, JSUtil.OK, true);
            }
        } catch (Exception unused) {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(-100, DOMException.toString("数据信息异常")), JSUtil.ERROR, true, false);
        }
    }

    public static void geocode(final IWebview iWebview, String str, final String str2, String str3, final String str4) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(iWebview.getContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() { // from class: io.dcloud.js.map.amap.adapter.DHMapUtil.1
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                if (i == 1000) {
                    i = 0;
                }
                if (i == 0) {
                    if (geocodeResult == null || geocodeResult.getGeocodeAddressList() == null || geocodeResult.getGeocodeAddressList().size() <= 0) {
                        Deprecated_JSUtil.execCallback(iWebview, str4, DOMException.toJSON(-100, DOMException.toString("对不起，没有搜索到相关数据！")), JSUtil.ERROR, true, false);
                        return;
                    }
                    GeocodeAddress geocodeAddress = (GeocodeAddress) geocodeResult.getGeocodeAddressList().get(0);
                    LatLonPoint latLonPoint = geocodeAddress.getLatLonPoint();
                    Deprecated_JSUtil.execCallback(iWebview, str4, String.format(Locale.ENGLISH, "{long:%f,lat:%f,addr:'%s',type:'%s'}", Double.valueOf(latLonPoint.getLongitude()), Double.valueOf(latLonPoint.getLatitude()), geocodeAddress.getFormatAddress(), str2), JSUtil.OK, true, false);
                    return;
                }
                if (i == 27 || i == 1804) {
                    Deprecated_JSUtil.execCallback(iWebview, str4, DOMException.toJSON(-6, DOMException.toString(i, "Maps高德地图", "网络错误", AMapLink.AMapErrorLink)), JSUtil.ERROR, true, false);
                } else if (i == 32 || i == 1001) {
                    Deprecated_JSUtil.execCallback(iWebview, str4, DOMException.toJSON(-6, DOMException.toString(i, "Maps高德地图", "key验证无效！", AMapLink.AMapErrorLink)), JSUtil.ERROR, true, false);
                } else {
                    Deprecated_JSUtil.execCallback(iWebview, str4, DOMException.toJSON(-100, DOMException.toString(i, "Maps高德地图", "未知错误", AMapLink.AMapErrorLink)), JSUtil.ERROR, true, false);
                }
            }

            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            }
        });
        geocodeSearch.getFromLocationNameAsyn(new GeocodeQuery(str, str3));
    }

    public static void openSysMap(IWebview iWebview, String str, String[][] strArr, String str2) {
        Uri uri;
        try {
            if (str2 != null) {
                uri = Uri.parse("geo:" + strArr[0][0] + "," + strArr[0][1] + "?q=" + str2);
            } else {
                uri = Uri.parse("geo:" + strArr[0][0] + "," + strArr[0][1]);
            }
            iWebview.getActivity().startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void reverseGeocode(final IWebview iWebview, final MapPoint mapPoint, final String str, String str2, final String str3) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(iWebview.getContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() { // from class: io.dcloud.js.map.amap.adapter.DHMapUtil.2
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            }

            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if (i == 1000) {
                    i = 0;
                }
                if (i == 0) {
                    if (regeocodeResult == null || regeocodeResult.getRegeocodeAddress() == null || regeocodeResult.getRegeocodeAddress().getFormatAddress() == null) {
                        Deprecated_JSUtil.execCallback(iWebview, str3, DOMException.toJSON(-100, DOMException.toString("对不起，没有搜索到相关数据！")), JSUtil.ERROR, true, false);
                        return;
                    } else {
                        Deprecated_JSUtil.execCallback(iWebview, str3, String.format(Locale.ENGLISH, "{long:%f,lat:%f,addr:'%s',type:'%s'}", Double.valueOf(mapPoint.getLongitude()), Double.valueOf(mapPoint.getLatitude()), regeocodeResult.getRegeocodeAddress().getFormatAddress(), str), JSUtil.OK, true, false);
                        return;
                    }
                }
                if (i == 27 || i == 1804) {
                    Deprecated_JSUtil.execCallback(iWebview, str3, DOMException.toJSON(-6, DOMException.toString(i, "Maps高德地图", "网络错误", AMapLink.AMapErrorLink)), JSUtil.ERROR, true, false);
                } else if (i == 32 || i == 1001) {
                    Deprecated_JSUtil.execCallback(iWebview, str3, DOMException.toJSON(-6, DOMException.toString(i, "Maps高德地图", "key验证无效！", AMapLink.AMapErrorLink)), JSUtil.ERROR, true, false);
                } else {
                    Deprecated_JSUtil.execCallback(iWebview, str3, DOMException.toJSON(-100, DOMException.toString(i, "Maps高德地图", "未知错误", AMapLink.AMapErrorLink)), JSUtil.ERROR, true, false);
                }
            }
        });
        geocodeSearch.getFromLocationAsyn(new RegeocodeQuery(mapPoint.getLatLngPoint(), 3000.0f, (TextUtils.isEmpty(str) || !"wgs84".equals(str)) ? "autonavi" : "gps"));
    }
}
