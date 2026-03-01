package io.dcloud.feature.weex_amap.adapter;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.taobao.weex.utils.WXResourceUtils;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class MapResourceUtils {
    public static LatLng crateLatLng(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.containsKey(Constant.JSONKEY.LATITUDE) || !jSONObject.containsKey(Constant.JSONKEY.LONGITUDE)) {
            return null;
        }
        try {
            return new LatLng(jSONObject.getDouble(Constant.JSONKEY.LATITUDE).doubleValue(), jSONObject.getDouble(Constant.JSONKEY.LONGITUDE).doubleValue());
        } catch (Exception unused) {
            return null;
        }
    }

    public static ArrayList<LatLng> crateLatLngs(JSONArray jSONArray) {
        LatLng latLngCrateLatLng;
        ArrayList<LatLng> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.size(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj != null && (obj instanceof JSONObject) && (latLngCrateLatLng = crateLatLng((JSONObject) obj)) != null) {
                    arrayList.add(latLngCrateLatLng);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public static JSONObject createJsLatLng(LatLng latLng) {
        JSONObject jSONObject = new JSONObject();
        if (latLng != null) {
            jSONObject.put(Constant.JSONKEY.LATITUDE, (Object) Double.valueOf(latLng.latitude));
            jSONObject.put(Constant.JSONKEY.LONGITUDE, (Object) Double.valueOf(latLng.longitude));
        }
        return jSONObject;
    }

    public static LatLonPoint createLatLonPoint(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.containsKey(Constant.JSONKEY.LATITUDE) || !jSONObject.containsKey(Constant.JSONKEY.LONGITUDE)) {
            return null;
        }
        try {
            return new LatLonPoint(jSONObject.getDouble(Constant.JSONKEY.LATITUDE).doubleValue(), jSONObject.getDouble(Constant.JSONKEY.LONGITUDE).doubleValue());
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean equalsLatLng(LatLng latLng, LatLng latLng2) {
        return latLng != null && latLng2 != null && latLng.latitude == latLng2.latitude && latLng2.longitude == latLng.longitude;
    }

    public static int getColor(String str) {
        if (!TextUtils.isEmpty(str) && str.length() == 9 && str.startsWith("#")) {
            str = "#" + str.substring(7, 9) + str.substring(1, 7);
        }
        return WXResourceUtils.getColor(str);
    }

    public static Object getId(String str) {
        try {
            try {
                return Integer.valueOf(Integer.parseInt(str));
            } catch (Exception unused) {
                return str;
            }
        } catch (Exception unused2) {
            return Integer.valueOf((int) Float.parseFloat(str));
        }
    }

    public static int getJSONIntValue(String str) {
        try {
            try {
                try {
                    return Integer.parseInt(str);
                } catch (Exception unused) {
                    return 0;
                }
            } catch (Exception unused2) {
                return (int) Float.parseFloat(str);
            }
        } catch (Exception unused3) {
            return (int) Double.parseDouble(str);
        }
    }

    public static LatLng crateLatLng(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return null;
        }
        try {
            return new LatLng(Double.valueOf(obj.toString()).doubleValue(), Double.valueOf(obj2.toString()).doubleValue());
        } catch (Exception unused) {
            return null;
        }
    }
}
