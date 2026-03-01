package io.dcloud.feature.weex_amap.Module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.heytap.mcssdk.f.e;
import com.taobao.weex.common.Constants;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;
import io.dcloud.feature.weex_amap.R;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WXMapSearchModule extends UniModule {
    private void getCityKey(RegeocodeQuery regeocodeQuery, final ICallBack iCallBack) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(this.mWXSDKInstance.getContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() { // from class: io.dcloud.feature.weex_amap.Module.WXMapSearchModule.5
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            }

            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if (regeocodeResult == null || regeocodeResult.getRegeocodeAddress() == null) {
                    iCallBack.onCallBack(-1, null);
                } else {
                    iCallBack.onCallBack(1, regeocodeResult.getRegeocodeAddress().getCity());
                }
            }
        });
        geocodeSearch.getFromLocationAsyn(regeocodeQuery);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray getTips(List<Tip> list) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Tip tip = list.get(i);
            if (tip.getPoint() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", (Object) tip.getName());
                jSONObject.put("address", (Object) tip.getAddress());
                jSONObject.put("district", (Object) tip.getDistrict());
                jSONObject.put("adcode", (Object) tip.getAdcode());
                jSONObject.put("typecode", (Object) tip.getTypeCode());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Constant.JSONKEY.LATITUDE, (Object) Double.valueOf(tip.getPoint().getLatitude()));
                jSONObject2.put(Constant.JSONKEY.LONGITUDE, (Object) Double.valueOf(tip.getPoint().getLatitude()));
                jSONObject.put("location", (Object) jSONObject2);
                jSONArray.add(jSONObject);
            }
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchPOI(JSONObject jSONObject, boolean z, String str, LatLonPoint latLonPoint, final UniJSCallback uniJSCallback) {
        final HashMap map = new HashMap();
        boolean z2 = true;
        int intValue = jSONObject.containsKey("index") ? jSONObject.getIntValue("index") : 1;
        String string = jSONObject.containsKey("types") ? jSONObject.getString("types") : "";
        if (jSONObject.containsKey("sortrule") && Integer.valueOf(jSONObject.getString("sortrule")).intValue() != 0) {
            z2 = false;
        }
        if (str == null) {
            str = "";
        }
        PoiSearch.Query query = new PoiSearch.Query(jSONObject.getString("key"), string, str);
        query.setPageSize(10);
        query.setDistanceSort(z2);
        query.setPageNum(intValue);
        PoiSearch poiSearch = new PoiSearch(this.mWXSDKInstance.getContext(), query);
        if (!z) {
            poiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, jSONObject.containsKey(Constant.Name.RADIUS) ? jSONObject.getIntValue(Constant.Name.RADIUS) : 3000, z2));
        }
        if (latLonPoint != null) {
            query.setLocation(latLonPoint);
        }
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() { // from class: io.dcloud.feature.weex_amap.Module.WXMapSearchModule.3
            public void onPoiItemSearched(PoiItem poiItem, int i) {
            }

            public void onPoiSearched(PoiResult poiResult, int i) {
                if (i != 1000) {
                    if (i == 1001) {
                        map.put("type", Constants.Event.FAIL);
                        map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_key_invalid));
                    } else if (i != 1804) {
                        map.put("type", Constants.Event.FAIL);
                        map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_unknown_reason));
                    } else {
                        map.put("type", Constants.Event.FAIL);
                        map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_network_error));
                    }
                } else if (poiResult != null && poiResult.getQuery() != null) {
                    int size = poiResult.getPois().size();
                    int pageCount = poiResult.getPageCount();
                    int pageNum = poiResult.getQuery().getPageNum();
                    ArrayList pois = poiResult.getPois();
                    map.put("currentNumber", Integer.valueOf(size));
                    map.put("pageNumber", Integer.valueOf(pageCount));
                    map.put("pageIndex", Integer.valueOf(pageNum));
                    map.put("poiList", WXMapSearchModule.this.toPositionArray(pois));
                }
                UniJSCallback uniJSCallback2 = uniJSCallback;
                if (uniJSCallback2 != null) {
                    uniJSCallback2.invoke(map);
                }
            }
        });
        poiSearch.searchPOIAsyn();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray toPositionArray(ArrayList<PoiItem> arrayList) {
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null) {
            for (PoiItem poiItem : arrayList) {
                JSONObject jSONObject = new JSONObject();
                if (poiItem.getLatLonPoint() != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(Constant.JSONKEY.LATITUDE, (Object) Double.valueOf(poiItem.getLatLonPoint().getLatitude()));
                    jSONObject2.put(Constant.JSONKEY.LONGITUDE, (Object) Double.valueOf(poiItem.getLatLonPoint().getLongitude()));
                    jSONObject.put("location", (Object) jSONObject2);
                }
                jSONObject.put("distance", (Object) Integer.valueOf(poiItem.getDistance()));
                jSONObject.put("address", (Object) PdrUtil.makeQueryStringAllRegExp(poiItem.getSnippet()));
                jSONObject.put("city", (Object) PdrUtil.makeQueryStringAllRegExp(poiItem.getCityName()));
                jSONObject.put("name", (Object) PdrUtil.makeQueryStringAllRegExp(poiItem.getTitle()));
                jSONObject.put("phone", (Object) poiItem.getTel());
                jSONObject.put("postcode", (Object) poiItem.getPostcode());
                jSONObject.put("province", (Object) poiItem.getProvinceName());
                jSONObject.put("district", (Object) poiItem.getAdName());
                jSONObject.put("type", (Object) poiItem.getTypeDes());
                jSONObject.put("typecode", (Object) poiItem.getTypeCode());
                jSONObject.put("adcode", (Object) poiItem.getAdCode());
                jSONArray.add(jSONObject);
            }
        }
        return jSONArray;
    }

    @UniJSMethod(uiThread = true)
    public void inputTipsSearch(JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        if (jSONObject != null) {
            InputtipsQuery inputtipsQuery = new InputtipsQuery(jSONObject.getString("key"), jSONObject.getString("city"));
            if (jSONObject.containsKey("types")) {
                inputtipsQuery.setType(jSONObject.getString("types"));
            }
            if (jSONObject.containsKey("point")) {
                inputtipsQuery.setLocation(MapResourceUtils.createLatLonPoint(jSONObject.getJSONObject("point")));
            }
            final HashMap map = new HashMap();
            Inputtips inputtips = new Inputtips(this.mWXSDKInstance.getContext(), inputtipsQuery);
            inputtips.setInputtipsListener(new Inputtips.InputtipsListener() { // from class: io.dcloud.feature.weex_amap.Module.WXMapSearchModule.4
                public void onGetInputtips(List<Tip> list, int i) {
                    if (i == 1000) {
                        map.put("type", "success");
                        map.put("code", 0);
                        JSONArray tips = WXMapSearchModule.this.getTips(list);
                        map.put(e.b, Integer.valueOf(tips.size()));
                        map.put("tips", tips);
                    } else {
                        map.put("type", Constants.Event.FAIL);
                        map.put("code", Integer.valueOf(i));
                        map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_search_error));
                    }
                    UniJSCallback uniJSCallback2 = uniJSCallback;
                    if (uniJSCallback2 != null) {
                        uniJSCallback2.invoke(map);
                    }
                }
            });
            inputtips.requestInputtipsAsyn();
        }
    }

    @UniJSMethod(uiThread = true)
    public void poiKeywordsSearch(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        if (jSONObject != null) {
            searchPOI(jSONObject, true, jSONObject.getString("city"), MapResourceUtils.createLatLonPoint(jSONObject.getJSONObject("point")), uniJSCallback);
        }
    }

    @UniJSMethod(uiThread = true)
    public void poiSearchNearBy(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        final LatLonPoint latLonPointCreateLatLonPoint = MapResourceUtils.createLatLonPoint(jSONObject.getJSONObject("point"));
        if (jSONObject == null || latLonPointCreateLatLonPoint == null) {
            return;
        }
        if (jSONObject.containsKey("city")) {
            searchPOI(jSONObject, false, jSONObject.getString("city"), latLonPointCreateLatLonPoint, uniJSCallback);
        }
        getCityKey(new RegeocodeQuery(latLonPointCreateLatLonPoint, 200.0f, "autonavi"), new ICallBack() { // from class: io.dcloud.feature.weex_amap.Module.WXMapSearchModule.2
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                if (obj == null) {
                    return null;
                }
                WXMapSearchModule.this.searchPOI(jSONObject, false, obj.toString(), latLonPointCreateLatLonPoint, uniJSCallback);
                return null;
            }
        });
    }

    @UniJSMethod(uiThread = true)
    public void reverseGeocode(JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        final HashMap map = new HashMap();
        map.put(AbsoluteConst.JSONKEY_MAP_COORD_TYPE, "gcj02");
        LatLonPoint latLonPointCreateLatLonPoint = MapResourceUtils.createLatLonPoint(jSONObject.getJSONObject("point"));
        if (jSONObject != null && latLonPointCreateLatLonPoint != null) {
            GeocodeSearch geocodeSearch = new GeocodeSearch(this.mWXSDKInstance.getContext());
            geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() { // from class: io.dcloud.feature.weex_amap.Module.WXMapSearchModule.1
                public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                }

                public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                    if (i != 1000) {
                        if (i == 1001) {
                            map.put("type", Constants.Event.FAIL);
                            map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_key_invalid));
                        } else if (i != 1804) {
                            map.put("type", Constants.Event.FAIL);
                            map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_unknown_reason));
                        } else {
                            map.put("type", Constants.Event.FAIL);
                            map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_network_error));
                        }
                    } else if (regeocodeResult == null || regeocodeResult.getRegeocodeAddress() == null || regeocodeResult.getRegeocodeAddress().getFormatAddress() == null) {
                        map.put("type", Constants.Event.FAIL);
                        map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_no_search_result));
                    } else {
                        String formatAddress = regeocodeResult.getRegeocodeAddress().getFormatAddress();
                        map.put("type", "success");
                        map.put("address", formatAddress);
                    }
                    UniJSCallback uniJSCallback2 = uniJSCallback;
                    if (uniJSCallback2 != null) {
                        uniJSCallback2.invoke(map);
                    }
                }
            });
            geocodeSearch.getFromLocationAsyn(new RegeocodeQuery(latLonPointCreateLatLonPoint, 3000, "autonavi"));
        } else {
            map.put("type", Constants.Event.FAIL);
            map.put("message", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_param_error));
            if (uniJSCallback != null) {
                uniJSCallback.invoke(map);
            }
        }
    }
}
