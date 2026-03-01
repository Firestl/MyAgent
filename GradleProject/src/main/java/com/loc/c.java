package com.loc;

import android.os.Bundle;
import com.amap.api.fence.DistrictItem;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.PoiItem;
import com.amap.api.location.DPoint;
import com.taobao.weex.common.Constants;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: GeoFenceSearchResultParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f3703a;

    public static int a(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray jSONArrayOptJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("status", 0);
            int iOptInt2 = jSONObject.optInt("infocode", 0);
            if (iOptInt != 1 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("pois")) == null) {
                return iOptInt2;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                GeoFence geoFence = new GeoFence();
                PoiItem poiItem = new PoiItem();
                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                poiItem.setPoiId(jSONObject2.optString("id"));
                poiItem.setPoiName(jSONObject2.optString("name"));
                poiItem.setPoiType(jSONObject2.optString("type"));
                poiItem.setTypeCode(jSONObject2.optString("typecode"));
                poiItem.setAddress(jSONObject2.optString("address"));
                String strOptString = jSONObject2.optString("location");
                if (strOptString != null) {
                    String[] strArrSplit = strOptString.split(",");
                    poiItem.setLongitude(Double.parseDouble(strArrSplit[0]));
                    poiItem.setLatitude(Double.parseDouble(strArrSplit[1]));
                    List<List<DPoint>> arrayList = new ArrayList<>();
                    ArrayList arrayList2 = new ArrayList();
                    DPoint dPoint = new DPoint(poiItem.getLatitude(), poiItem.getLongitude());
                    arrayList2.add(dPoint);
                    arrayList.add(arrayList2);
                    geoFence.setPointList(arrayList);
                    geoFence.setCenter(dPoint);
                }
                poiItem.setTel(jSONObject2.optString(Constants.Value.TEL));
                poiItem.setProvince(jSONObject2.optString("pname"));
                poiItem.setCity(jSONObject2.optString("cityname"));
                poiItem.setAdname(jSONObject2.optString("adname"));
                geoFence.setPoiItem(poiItem);
                StringBuilder sb = new StringBuilder();
                sb.append(a());
                geoFence.setFenceId(sb.toString());
                geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
                geoFence.setPendingIntentAction(bundle.getString("pendingIntentAction"));
                geoFence.setType(2);
                geoFence.setRadius(bundle.getFloat("fenceRadius"));
                geoFence.setExpiration(bundle.getLong("expiration"));
                geoFence.setActivatesAction(bundle.getInt("activatesAction", 1));
                list.add(geoFence);
            }
            return iOptInt2;
        } catch (Throwable unused) {
            return 5;
        }
    }

    public static synchronized long a() {
        long jB = ep.b();
        if (jB > f3703a) {
            f3703a = jB;
        } else {
            f3703a++;
        }
        return f3703a;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ec A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.amap.api.location.DPoint> a(java.util.List<com.amap.api.location.DPoint> r29, float r30) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.c.a(java.util.List, float):java.util.List");
    }

    public final int b(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray jSONArrayOptJSONArray;
        ArrayList arrayList;
        String str2;
        int i;
        String str3;
        String str4;
        float f;
        long j;
        long j2;
        int i2;
        String str5;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("status", 0);
            int iOptInt2 = jSONObject.optInt("infocode", 0);
            String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
            String string2 = bundle.getString("pendingIntentAction");
            float f2 = bundle.getFloat("fenceRadius");
            long j3 = bundle.getLong("expiration");
            int i3 = bundle.getInt("activatesAction", 1);
            if (iOptInt == 1 && (jSONArrayOptJSONArray = jSONObject.optJSONArray("districts")) != null) {
                int i4 = 0;
                while (i4 < jSONArrayOptJSONArray.length()) {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    GeoFence geoFence = new GeoFence();
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i4);
                    String strOptString = jSONObject2.optString("citycode");
                    String strOptString2 = jSONObject2.optString("adcode");
                    String strOptString3 = jSONObject2.optString("name");
                    JSONArray jSONArray = jSONArrayOptJSONArray;
                    String string3 = jSONObject2.getString("center");
                    int i5 = iOptInt2;
                    DPoint dPoint = new DPoint();
                    int i6 = i4;
                    String str6 = ",";
                    if (string3 != null) {
                        String[] strArrSplit = string3.split(",");
                        arrayList = arrayList2;
                        str2 = strOptString3;
                        dPoint.setLatitude(Double.parseDouble(strArrSplit[1]));
                        dPoint.setLongitude(Double.parseDouble(strArrSplit[0]));
                        geoFence.setCenter(dPoint);
                    } else {
                        arrayList = arrayList2;
                        str2 = strOptString3;
                    }
                    geoFence.setCustomId(string);
                    geoFence.setPendingIntentAction(string2);
                    geoFence.setType(3);
                    geoFence.setRadius(f2);
                    geoFence.setExpiration(j3);
                    geoFence.setActivatesAction(i3);
                    StringBuilder sb = new StringBuilder();
                    sb.append(a());
                    geoFence.setFenceId(sb.toString());
                    String strOptString4 = jSONObject2.optString(Constant.Name.POLYLINE);
                    if (strOptString4 != null) {
                        String[] strArrSplit2 = strOptString4.split("\\|");
                        int length = strArrSplit2.length;
                        i = i3;
                        float fMin = Float.MAX_VALUE;
                        int i7 = 0;
                        float fMax = Float.MIN_VALUE;
                        while (i7 < length) {
                            String str7 = string;
                            String str8 = strArrSplit2[i7];
                            String[] strArr = strArrSplit2;
                            DistrictItem districtItem = new DistrictItem();
                            String str9 = string2;
                            List<DPoint> arrayList4 = new ArrayList<>();
                            districtItem.setCitycode(strOptString);
                            districtItem.setAdcode(strOptString2);
                            String str10 = strOptString2;
                            String str11 = str2;
                            districtItem.setDistrictName(str11);
                            str2 = str11;
                            String[] strArrSplit3 = str8.split(";");
                            float f3 = f2;
                            int i8 = 0;
                            while (i8 < strArrSplit3.length) {
                                String[] strArrSplit4 = strArrSplit3[i8].split(str6);
                                String str12 = str6;
                                String[] strArr2 = strArrSplit3;
                                if (strArrSplit4.length > 1) {
                                    String str13 = strArrSplit4[1];
                                    String str14 = strArrSplit4[0];
                                    j2 = j3;
                                    double d = Double.parseDouble(str13);
                                    i2 = length;
                                    str5 = strOptString;
                                    arrayList4.add(new DPoint(d, Double.parseDouble(str14)));
                                } else {
                                    j2 = j3;
                                    i2 = length;
                                    str5 = strOptString;
                                }
                                i8++;
                                strOptString = str5;
                                str6 = str12;
                                strArrSplit3 = strArr2;
                                j3 = j2;
                                length = i2;
                            }
                            String str15 = str6;
                            long j4 = j3;
                            int i9 = length;
                            String str16 = strOptString;
                            if (arrayList4.size() > 100.0f) {
                                try {
                                    arrayList4 = a(arrayList4, 100.0f);
                                } catch (Throwable unused) {
                                    return 5;
                                }
                            }
                            arrayList3.add(arrayList4);
                            districtItem.setPolyline(arrayList4);
                            ArrayList arrayList5 = arrayList;
                            arrayList5.add(districtItem);
                            fMax = Math.max(fMax, a.b(dPoint, arrayList4));
                            fMin = Math.min(fMin, a.a(dPoint, arrayList4));
                            i7++;
                            strOptString = str16;
                            arrayList = arrayList5;
                            string = str7;
                            strArrSplit2 = strArr;
                            string2 = str9;
                            strOptString2 = str10;
                            f2 = f3;
                            str6 = str15;
                            j3 = j4;
                            length = i9;
                        }
                        str3 = string;
                        str4 = string2;
                        f = f2;
                        j = j3;
                        geoFence.setMaxDis2Center(fMax);
                        geoFence.setMinDis2Center(fMin);
                        geoFence.setDistrictItemList(arrayList);
                        geoFence.setPointList(arrayList3);
                        list.add(geoFence);
                    } else {
                        i = i3;
                        str3 = string;
                        str4 = string2;
                        f = f2;
                        j = j3;
                    }
                    i4 = i6 + 1;
                    jSONArrayOptJSONArray = jSONArray;
                    iOptInt2 = i5;
                    i3 = i;
                    string = str3;
                    string2 = str4;
                    f2 = f;
                    j3 = j;
                }
            }
            return iOptInt2;
        } catch (Throwable unused2) {
        }
    }
}
