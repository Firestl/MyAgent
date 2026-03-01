package io.dcloud.js.geolocation.amap;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.igexin.push.core.b;
import com.umeng.analytics.pro.bm;
import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.js.geolocation.GeoManagerBase;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AMapGeoManager extends GeoManagerBase {
    public static final String TAG = "AMapGeoManager";
    public static AMapGeoManager mInstance;
    public boolean hasAppkey;
    public boolean isGeocode;
    public boolean isStreamApp;
    public AMapLocationClient mClient;
    public HashMap<String, AMapLocationClient> mContinuousMap;
    public AMapLocationClientOption mOption;
    public HashMap<String, AMapLocationClient> mSingleTimeMap;

    public AMapGeoManager(Context context) {
        super(context);
        this.hasAppkey = false;
        this.isGeocode = true;
        this.isStreamApp = false;
        this.mClient = null;
        this.mOption = null;
        this.mContinuousMap = new HashMap<>();
        this.mSingleTimeMap = new HashMap<>();
        this.hasAppkey = !PdrUtil.isEmpty(AndroidResources.getMetaValue("com.amap.api.v2.apikey"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBack2Front(AMapLocation aMapLocation, IWebview iWebview, String str, boolean z, boolean z2) {
        if (!z2 && !PdrUtil.isEmpty(this.mSingleTimeMap.get(str))) {
            this.mSingleTimeMap.get(str).stopLocation();
        }
        if (aMapLocation == null || aMapLocation.getErrorCode() != 0) {
            callback(iWebview, str, aMapLocation == null ? StringUtil.format(DOMException.JSON_ERROR_INFO, 17, DOMException.toString(17, "geolocation", DOMException.MSG_GEOLOCATION_PROVIDER_ERROR, (String) null)) : StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(convertGeolocationErrorCode(aMapLocation.getErrorCode())), DOMException.toString(aMapLocation.getErrorCode(), "geolocation", aMapLocation.getErrorInfo(), (String) null)), JSUtil.ERROR, true, z2, z);
        } else {
            callback(iWebview, str, makeJSON(aMapLocation).toString(), JSUtil.OK, true, z2, z);
        }
    }

    private int convertGeolocationErrorCode(int i) {
        if (i != 2) {
            if (i == 4) {
                return 3;
            }
            switch (i) {
                case 12:
                    return 1;
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    return 4;
            }
        }
        return 2;
    }

    public static AMapGeoManager getInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        AMapGeoManager aMapGeoManager = mInstance;
        if (aMapGeoManager != null) {
            return aMapGeoManager;
        }
        AMapGeoManager aMapGeoManager2 = new AMapGeoManager(applicationContext);
        mInstance = aMapGeoManager2;
        return aMapGeoManager2;
    }

    private JSONObject makeJSON(AMapLocation aMapLocation) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constant.JSONKEY.LATITUDE, aMapLocation.getLatitude());
            jSONObject.put(Constant.JSONKEY.LONGITUDE, aMapLocation.getLongitude());
            jSONObject.put("altitude", aMapLocation.getAltitude());
            jSONObject.put("accuracy", aMapLocation.getAccuracy());
            jSONObject.put("altitudeAccuracy", 0);
            jSONObject.put("heading", aMapLocation.getBearing());
            jSONObject.put("velocity", aMapLocation.getSpeed());
            jSONObject.put("coordsType", "gcj02");
            jSONObject.put("timestamp", aMapLocation.getTime());
            if (this.isGeocode) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject.put("address", jSONObject2);
                jSONObject2.put(bm.O, aMapLocation.getCountry());
                jSONObject2.put("province", aMapLocation.getProvince());
                jSONObject2.put("city", aMapLocation.getCity());
                jSONObject2.put("district", aMapLocation.getDistrict());
                jSONObject2.put("street", aMapLocation.getStreet());
                jSONObject2.put("streetNum", aMapLocation.getStreetNum());
                jSONObject2.put("poiName", aMapLocation.getPoiName());
                jSONObject2.put("postalCode", (Object) null);
                jSONObject2.put("cityCode", aMapLocation.getCityCode());
                jSONObject.put("addresses", aMapLocation.getAddress());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        saveGeoData(aMapLocation, "gcj02");
        return jSONObject;
    }

    private void saveGeoData(AMapLocation aMapLocation, String str) {
        if (this.isStreamApp) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Constant.JSONKEY.LATITUDE, aMapLocation.getLatitude());
            jSONObject2.put(Constant.JSONKEY.LONGITUDE, aMapLocation.getLongitude());
            jSONObject.put("coords", jSONObject2);
            jSONObject.put("coordsType", str);
            if (this.isGeocode) {
                jSONObject.put("addresses", aMapLocation.getAddress());
            }
            SP.setBundleData(SP.getOrCreateBundle(AbsoluteConst.START_STATISTICS_DATA), AbsoluteConst.GEO_DATA, jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopContinuousLocating() {
        for (Map.Entry<String, AMapLocationClient> entry : this.mContinuousMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            if (!PdrUtil.isEmpty(entry.getValue())) {
                entry.getValue().stopLocation();
            }
        }
    }

    public void callback(IWebview iWebview, String str, String str2, int i, boolean z, boolean z2, boolean z3) {
        if (z3) {
            JSUtil.execGEOCallback(iWebview, str, str2, i, z, z2);
        } else {
            Deprecated_JSUtil.execCallback(iWebview, str, str2, i, z, z2);
        }
    }

    @Override // io.dcloud.js.geolocation.GeoManagerBase
    public String execute(IWebview iWebview, String str, String[] strArr) {
        int i;
        try {
            String str2 = strArr.length > 7 ? strArr[6] : b.m;
            int i2 = !b.m.equals(str2) ? Integer.parseInt(str2) : Integer.MAX_VALUE;
            String str3 = strArr.length > 8 ? strArr[7] : "5000";
            if (str3.equals(b.m)) {
                i = 5000;
            } else {
                int i3 = Integer.parseInt(str3);
                i = i3 < 1000 ? 1000 : i3;
            }
            if (str.startsWith("getCurrentPosition")) {
                this.isGeocode = Boolean.parseBoolean(strArr[5]);
                startLocating(iWebview, strArr[0], null, Boolean.parseBoolean(strArr[1]), i2, -1, str.endsWith("DLGEO"), false);
            } else if (str.startsWith("watchPosition")) {
                this.isGeocode = Boolean.parseBoolean(strArr[5]);
                boolean z = Boolean.parseBoolean(strArr[2]);
                iWebview.obtainFrameView().addFrameViewListener(new IEventCallback() { // from class: io.dcloud.js.geolocation.amap.AMapGeoManager.1
                    @Override // io.dcloud.common.DHInterface.IEventCallback
                    public Object onCallBack(String str4, Object obj) {
                        if ((!PdrUtil.isEquals(str4, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str4, "close")) || !(obj instanceof IWebview)) {
                            return null;
                        }
                        AMapGeoManager.this.stopContinuousLocating();
                        ((AdaFrameView) ((IWebview) obj).obtainFrameView()).removeFrameViewListener(this);
                        return null;
                    }
                });
                startLocating(iWebview, strArr[0], strArr[1], z, i2, i, str.endsWith("DLGEO"), true);
            } else if (str.startsWith("clearWatch")) {
                this.keySet.remove(strArr[0]);
                AMapLocationClient aMapLocationClientRemove = this.mContinuousMap.remove(strArr[0]);
                if (aMapLocationClientRemove != null) {
                    aMapLocationClientRemove.setLocationListener(null);
                    aMapLocationClientRemove.stopLocation();
                }
            }
            return "";
        } catch (Exception e2) {
            Logger.e(TAG, e2.getMessage());
            return "";
        }
    }

    @Override // io.dcloud.js.geolocation.GeoManagerBase
    public void onDestroy() {
        for (Map.Entry<String, AMapLocationClient> entry : this.mContinuousMap.entrySet()) {
            if (!PdrUtil.isEmpty(entry.getValue())) {
                entry.getValue().onDestroy();
            }
        }
        this.mContinuousMap.clear();
        for (Map.Entry<String, AMapLocationClient> entry2 : this.mSingleTimeMap.entrySet()) {
            if (!PdrUtil.isEmpty(entry2.getValue())) {
                entry2.getValue().onDestroy();
            }
        }
        this.mSingleTimeMap.clear();
    }

    public void startLocating(final IWebview iWebview, final String str, String str2, boolean z, int i, int i2, final boolean z2, final boolean z3) {
        if (!this.hasAppkey) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, 16, DOMException.MSG_GEOLOCATION_HASNT_AMAP_KEY), JSUtil.ERROR, true, false);
            return;
        }
        this.mClient = new AMapLocationClient(iWebview.getContext());
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        this.mOption = aMapLocationClientOption;
        aMapLocationClientOption.setOnceLocationLatest(false);
        if (PdrUtil.isEmpty(str2)) {
            this.mOption.setOnceLocation(true);
            this.mSingleTimeMap.put(str, this.mClient);
        } else {
            this.mOption.setInterval(i2);
            this.keySet.add(str2);
            this.mContinuousMap.put(str2, this.mClient);
        }
        if (NetTool.isNetworkAvailable(this.mContext)) {
            if (z) {
                this.mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                this.mOption.setOnceLocationLatest(true);
            } else {
                this.mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
                this.mOption.setOnceLocationLatest(false);
            }
            this.mOption.setLocationCacheEnable(false);
            this.mOption.setHttpTimeOut(i);
        } else {
            this.mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
            this.mOption.setOnceLocationLatest(false);
            if (Integer.MAX_VALUE == i) {
                this.mOption.setHttpTimeOut(3000L);
            } else {
                this.mOption.setHttpTimeOut(i);
            }
        }
        this.mClient.setLocationOption(this.mOption);
        this.mClient.setLocationListener(new AMapLocationListener() { // from class: io.dcloud.js.geolocation.amap.AMapGeoManager.2
            @Override // com.amap.api.location.AMapLocationListener
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getAddress() != null) {
                    FeatureMessageDispatcher.dispatchMessage("record_address", aMapLocation.getAddress() != null ? aMapLocation.getAddress() : null);
                }
                AMapGeoManager.this.callBack2Front(aMapLocation, iWebview, str, z2, z3);
            }
        });
        this.mClient.startLocation();
    }
}
