package io.dcloud.js.map.amap.adapter;

import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.route.WalkStep;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.js.map.amap.JsMapManager;
import io.dcloud.js.map.amap.JsMapRoute;
import io.dcloud.js.map.amap.MapJsUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class MapSearch {
    public static final int BUS_MODE_SEARCH = 0;
    public static final int DRIVING_MODE_SEARCH = 1;
    public static final int POISEARCH_TYPE = 0;
    public static final int ROUTESEARCH_TYPE = 1;
    public static final int SEARCH_ACTION = 10000;
    public static final int WALK_MODE_SEARCH = 2;
    public ArrayList<AMapSearchResultData> mCallResultDatas;
    public String mCallbackId;
    public IWebview mIWebview;
    public int mPageCapacity;
    public RouteSearch mSearchHandler;
    public int mIndex = 0;
    public int busMode = 0;
    public int drivingMode = 0;
    public int walkMode = 0;
    public Handler mHandler = new Handler() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 10000) {
                return;
            }
            AMapSearchResultData aMapSearchResultData = (AMapSearchResultData) message.obj;
            Object obj = aMapSearchResultData.pStart;
            if (obj instanceof LatLonPoint) {
                Object obj2 = aMapSearchResultData.pEnd;
                if (obj2 instanceof LatLonPoint) {
                    int i = aMapSearchResultData.type;
                    if (i == 0) {
                        MapSearch.this.transitSearch(obj, obj2, aMapSearchResultData.endCity);
                    } else if (i == 1) {
                        MapSearch.this.drivingSearch(obj, aMapSearchResultData.startCity, obj2, aMapSearchResultData.endCity);
                    } else {
                        if (i != 2) {
                            return;
                        }
                        MapSearch.this.walkingSearch(obj, aMapSearchResultData.startCity, obj2, aMapSearchResultData.endCity);
                    }
                }
            }
        }
    };
    public DHMapView mMapView = null;
    public RouteSearch.OnRouteSearchListener mRouteSearchListener = new RouteSearch.OnRouteSearchListener() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.6
        private void route_callback_js(MapPoint mapPoint, MapPoint mapPoint2, int i, int i2, JSONArray jSONArray) {
            StringBuffer stringBuffer = new StringBuffer();
            MapSearch.this.create_js_SearchRouteResult_Obj("srr", stringBuffer);
            MapJsUtil.assignJsVar(stringBuffer, "srr", "__state__", i2);
            MapJsUtil.assignJsVar(stringBuffer, "srr", "__type__", 1.0d);
            MapJsUtil.assignJsVar(stringBuffer, "srr", "startPosition", MapJsUtil.wrapJsEvalString(MapSearch.this.create_js_Position_Obj(mapPoint, "startPosition"), "startPosition"), false);
            MapJsUtil.assignJsVar(stringBuffer, "srr", "endPosition", MapJsUtil.wrapJsEvalString(MapSearch.this.create_js_Position_Obj(mapPoint2, "endPosition"), "endPosition"), false);
            MapJsUtil.assignJsVar(stringBuffer, "srr", "routeNumber", i);
            MapJsUtil.assignJsVar(stringBuffer, "srr", "routeList", jSONArray);
            MapSearch.this.onSearchComplete(1, MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "srr"));
        }

        public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
            int i2 = i == 1000 ? 0 : i;
            if (i2 == 0) {
                if (busRouteResult == null || busRouteResult.getPaths() == null || busRouteResult.getPaths().size() <= 0) {
                    MapSearch.this.route_error_callback_js(-1, "对不起，没有搜索到相关数据！");
                    return;
                }
                List paths = busRouteResult.getPaths();
                MapPoint mapPoint = MapSearch.this.getMapPoint(busRouteResult.getStartPos());
                MapPoint mapPoint2 = MapSearch.this.getMapPoint(busRouteResult.getTargetPos());
                route_callback_js(mapPoint, mapPoint2, paths.size(), i2, MapSearch.this.toBusRouteArray(paths, mapPoint, mapPoint2));
                return;
            }
            if (i2 == 27 || i2 == 1804) {
                MapSearch.this.route_error_callback_js(i2, DOMException.MSG_NETWORK_ERROR);
            } else if (i2 == 32 || i2 == 1001) {
                MapSearch.this.route_error_callback_js(i2, "签名错误");
            } else {
                MapSearch.this.route_error_callback_js(i2, DOMException.MSG_UNKNOWN_ERROR);
            }
        }

        public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
            int i2 = i == 1000 ? 0 : i;
            if (i2 == 0) {
                if (driveRouteResult == null || driveRouteResult.getPaths() == null || driveRouteResult.getPaths().size() <= 0) {
                    MapSearch.this.route_error_callback_js(-1, "对不起，没有搜索到相关数据！");
                    return;
                }
                List paths = driveRouteResult.getPaths();
                MapPoint mapPoint = MapSearch.this.getMapPoint(driveRouteResult.getStartPos());
                MapPoint mapPoint2 = MapSearch.this.getMapPoint(driveRouteResult.getTargetPos());
                route_callback_js(mapPoint, mapPoint2, paths.size(), i2, MapSearch.this.toDriveRouteArray(paths, mapPoint, mapPoint2));
                return;
            }
            if (i2 == 27 || i2 == 1804) {
                MapSearch.this.route_error_callback_js(i2, DOMException.MSG_NETWORK_ERROR);
            } else if (i2 == 32 || i2 == 1001) {
                MapSearch.this.route_error_callback_js(i2, "签名错误");
            } else {
                MapSearch.this.route_error_callback_js(i2, DOMException.MSG_UNKNOWN_ERROR);
            }
        }

        public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
        }

        public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
            int i2 = i == 1000 ? 0 : i;
            if (i2 == 0) {
                if (walkRouteResult == null || walkRouteResult.getPaths() == null || walkRouteResult.getPaths().size() <= 0) {
                    MapSearch.this.route_error_callback_js(-1, "对不起，没有搜索到相关数据！");
                    return;
                }
                List paths = walkRouteResult.getPaths();
                MapPoint mapPoint = MapSearch.this.getMapPoint(walkRouteResult.getStartPos());
                MapPoint mapPoint2 = MapSearch.this.getMapPoint(walkRouteResult.getTargetPos());
                route_callback_js(mapPoint, mapPoint2, paths.size(), i2, MapSearch.this.toWalkRouteArray(paths, mapPoint, mapPoint2));
                return;
            }
            if (i2 == 27 || i2 == 1804) {
                MapSearch.this.route_error_callback_js(i2, DOMException.MSG_NETWORK_ERROR);
            } else if (i2 == 32 || i2 == 1001) {
                MapSearch.this.route_error_callback_js(i2, "签名错误");
            } else {
                MapSearch.this.route_error_callback_js(i2, DOMException.MSG_UNKNOWN_ERROR);
            }
        }
    };
    public PoiSearch.OnPoiSearchListener mPoiSearchListener = new PoiSearch.OnPoiSearchListener() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.7
        public void onPoiItemSearched(PoiItem poiItem, int i) {
        }

        public void onPoiSearched(PoiResult poiResult, int i) {
            ArrayList arrayList;
            int size;
            int pageCount;
            int pageNum;
            if (i == 1000) {
                i = 0;
            }
            StringBuffer stringBuffer = new StringBuffer();
            MapSearch.this.create_js_SearchPoiResult_Obj("spr", stringBuffer);
            MapJsUtil.assignJsVar(stringBuffer, "spr", "__state__", i);
            MapJsUtil.assignJsVar(stringBuffer, "spr", "__type__", 0.0d);
            if (i != 0) {
                if (i == 27 || i == 1804) {
                    MapJsUtil.assignJsVar(stringBuffer, "spr", "errorMsg", DOMException.MSG_NETWORK_ERROR);
                    MapSearch.this.onSearchComplete(0, MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "spr"));
                    return;
                } else if (i == 32 || i == 1001) {
                    MapJsUtil.assignJsVar(stringBuffer, "spr", "errorMsg", "签名错误");
                    MapSearch.this.onSearchComplete(0, MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "spr"));
                    return;
                } else {
                    MapJsUtil.assignJsVar(stringBuffer, "spr", "errorMsg", DOMException.MSG_UNKNOWN_ERROR);
                    MapSearch.this.onSearchComplete(0, MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "spr"));
                    return;
                }
            }
            if (poiResult != null) {
                size = poiResult.getPois().size();
                pageCount = poiResult.getPageCount();
                pageNum = poiResult.getQuery().getPageNum();
                arrayList = poiResult.getPois();
            } else {
                arrayList = new ArrayList();
                size = 0;
                pageCount = 0;
                pageNum = 0;
            }
            MapJsUtil.assignJsVar(stringBuffer, "spr", "currentNumber", size);
            MapJsUtil.assignJsVar(stringBuffer, "spr", "pageNumber", pageCount);
            MapJsUtil.assignJsVar(stringBuffer, "spr", "pageIndex", pageNum);
            MapJsUtil.assignJsVar(stringBuffer, "spr", "poiList", MapSearch.this.toPositionArray(arrayList));
            MapSearch.this.onSearchComplete(0, MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "spr"));
        }
    };

    public MapSearch(IWebview iWebview) {
        this.mIWebview = iWebview;
        RouteSearch routeSearch = new RouteSearch(iWebview.getActivity());
        this.mSearchHandler = routeSearch;
        routeSearch.setRouteSearchListener(this.mRouteSearchListener);
    }

    private String create_jsBusRouteObject(BusPath busPath, MapPoint mapPoint, MapPoint mapPoint2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(create_js_Point_Obj(mapPoint, "sp"));
        stringBuffer.append(create_js_Point_Obj(mapPoint2, "ep"));
        MapJsUtil.create_Js_Var(stringBuffer, "route", "plus.maps.Route", "sp,ep,false");
        MapJsUtil.assignJsVar(stringBuffer, "route", "distance", busPath.getDistance());
        MapJsUtil.assignJsVar(stringBuffer, "route", "routeTip", "");
        String str = "Route_" + busPath.hashCode();
        MapJsUtil.assignJsVar(stringBuffer, "route", "_UUID_", str);
        JsMapRoute jsMapRoute = new JsMapRoute(this.mIWebview);
        jsMapRoute.setRoute(busPath);
        jsMapRoute.setPoint(mapPoint, mapPoint2);
        JsMapManager.getJsMapManager().putJsObject(str, jsMapRoute);
        return MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "route");
    }

    private String create_jsDriveRouteObject(DrivePath drivePath, MapPoint mapPoint, MapPoint mapPoint2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(create_js_Point_Obj(mapPoint, "sp"));
        stringBuffer.append(create_js_Point_Obj(mapPoint2, "ep"));
        MapJsUtil.create_Js_Var(stringBuffer, "route", "plus.maps.Route", "sp,ep,false");
        MapJsUtil.assignJsVar(stringBuffer, "route", "pointCount", ((DriveStep) drivePath.getSteps().get(0)).getPolyline().size());
        MapJsUtil.assignJsVar(stringBuffer, "route", "distance", ((DriveStep) drivePath.getSteps().get(0)).getDistance());
        MapJsUtil.assignJsVar(stringBuffer, "route", "routeTip", ((DriveStep) drivePath.getSteps().get(0)).getInstruction());
        String str = "Route_" + drivePath.hashCode();
        MapJsUtil.assignJsVar(stringBuffer, "route", "_UUID_", str);
        JsMapRoute jsMapRoute = new JsMapRoute(this.mIWebview);
        jsMapRoute.setRoute(drivePath);
        jsMapRoute.setPoint(mapPoint, mapPoint2);
        JsMapManager.getJsMapManager().putJsObject(str, jsMapRoute);
        JSONArray jSONArray = new JSONArray();
        Iterator it = drivePath.getSteps().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((DriveStep) it.next()).getPolyline().iterator();
            while (it2.hasNext()) {
                jSONArray.put(create_js_Point_Obj(getMapPoint((LatLonPoint) it2.next()), null));
            }
        }
        MapJsUtil.assignJsVar(stringBuffer, "route", "pointList", jSONArray);
        return MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "route");
    }

    private String create_jsWalkRouteObject(WalkPath walkPath, MapPoint mapPoint, MapPoint mapPoint2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(create_js_Point_Obj(mapPoint, "sp"));
        stringBuffer.append(create_js_Point_Obj(mapPoint2, "ep"));
        MapJsUtil.create_Js_Var(stringBuffer, "route", "plus.maps.Route", "sp,ep,false");
        MapJsUtil.assignJsVar(stringBuffer, "route", "pointCount", ((WalkStep) walkPath.getSteps().get(0)).getPolyline().size());
        MapJsUtil.assignJsVar(stringBuffer, "route", "distance", ((WalkStep) walkPath.getSteps().get(0)).getDistance());
        MapJsUtil.assignJsVar(stringBuffer, "route", "routeTip", ((WalkStep) walkPath.getSteps().get(0)).getInstruction());
        String str = "Route_" + walkPath.hashCode();
        MapJsUtil.assignJsVar(stringBuffer, "route", "_UUID_", str);
        JsMapRoute jsMapRoute = new JsMapRoute(this.mIWebview);
        jsMapRoute.setRoute(walkPath);
        jsMapRoute.setPoint(mapPoint, mapPoint2);
        JsMapManager.getJsMapManager().putJsObject(str, jsMapRoute);
        return MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "route");
    }

    private String create_js_Point_Obj(MapPoint mapPoint, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        float latitude = (float) mapPoint.getLatitude();
        MapJsUtil.create_Js_Var(stringBuffer, str, "plus.maps.Point", ((float) mapPoint.getLongitude()) + "," + latitude);
        return stringBuffer.toString();
    }

    private String create_js_Position_Obj(PoiItem poiItem) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(create_js_Point_Obj(getMapPoint(poiItem.getLatLonPoint()), "p"));
        MapJsUtil.create_Js_Var(stringBuffer, "pos", "plus.maps.Position", "p");
        MapJsUtil.assignJsVar(stringBuffer, "pos", "address", PdrUtil.makeQueryStringAllRegExp(poiItem.getSnippet()));
        MapJsUtil.assignJsVar(stringBuffer, "pos", "city", PdrUtil.makeQueryStringAllRegExp(poiItem.getCityName()));
        MapJsUtil.assignJsVar(stringBuffer, "pos", "name", PdrUtil.makeQueryStringAllRegExp(poiItem.getTitle()));
        MapJsUtil.assignJsVar(stringBuffer, "pos", "phone", poiItem.getTel());
        MapJsUtil.assignJsVar(stringBuffer, "pos", "postcode", poiItem.getPostcode());
        return MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "pos");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuffer create_js_SearchPoiResult_Obj(String str, StringBuffer stringBuffer) {
        MapJsUtil.create_Js_Var(stringBuffer, str, "plus.maps.__SearchPoiResult__", null);
        return stringBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StringBuffer create_js_SearchRouteResult_Obj(String str, StringBuffer stringBuffer) {
        MapJsUtil.create_Js_Var(stringBuffer, str, "plus.maps.__SearchRouteResult__", null);
        return stringBuffer;
    }

    private void getCityKey(RegeocodeQuery regeocodeQuery, final ICallBack iCallBack) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(this.mIWebview.getContext());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.5
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

    private void getGeocodeLatLon(GeocodeQuery geocodeQuery, final AMapSearchResultData aMapSearchResultData, final int i) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(this.mIWebview.getContext());
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.4
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i2) {
                if (i2 == 1000) {
                    List geocodeAddressList = geocodeResult.getGeocodeAddressList();
                    if (geocodeAddressList != null) {
                        GeocodeAddress geocodeAddress = (GeocodeAddress) geocodeAddressList.get(0);
                        if (i == 1) {
                            aMapSearchResultData.pStart = geocodeAddress.getLatLonPoint();
                        } else {
                            aMapSearchResultData.pEnd = geocodeAddress.getLatLonPoint();
                        }
                        Message message = new Message();
                        message.what = 10000;
                        message.obj = aMapSearchResultData;
                        MapSearch.this.mHandler.sendMessage(message);
                    }
                } else {
                    MapSearch.this.route_error_callback_js(-1, DOMException.MSG_PARAMETER_ERROR + "(原错误号" + i2 + ", 相关SDK官网查询具体错误原因)");
                }
                Logger.e("shutao", "onGeocodeSearched" + geocodeResult.getGeocodeAddressList().size() + "   code=" + i2);
            }

            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i2) {
            }
        });
    }

    private LatLonPoint getLatLonPoint(Object obj, String str, AMapSearchResultData aMapSearchResultData, int i) {
        if (obj instanceof LatLonPoint) {
            return (LatLonPoint) obj;
        }
        if (!(obj instanceof MapPoint)) {
            getGeocodeLatLon(new GeocodeQuery((String) obj, str), aMapSearchResultData, i);
            return null;
        }
        if (i == 1) {
            aMapSearchResultData.pStart = ((MapPoint) obj).getLatLngPoint();
        } else {
            aMapSearchResultData.pEnd = ((MapPoint) obj).getLatLngPoint();
        }
        return ((MapPoint) obj).getLatLngPoint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapPoint getMapPoint(LatLonPoint latLonPoint) {
        return new MapPoint(latLonPoint.getLatitude() + "", latLonPoint.getLongitude() + "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSearchComplete(int i, String str) {
        if (i == 0) {
            MapJsUtil.execCallback(this.mIWebview, this.mCallbackId, str);
        } else if (i == 1) {
            MapJsUtil.execCallback(this.mIWebview, this.mCallbackId, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void route_error_callback_js(int i, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        create_js_SearchRouteResult_Obj("srr", stringBuffer);
        MapJsUtil.assignJsVar(stringBuffer, "srr", "__state__", i);
        MapJsUtil.assignJsVar(stringBuffer, "srr", "__type__", 1.0d);
        onSearchComplete(1, MapJsUtil.wrapJsEvalString(stringBuffer.toString(), "srr"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray toBusRouteArray(List<BusPath> list, MapPoint mapPoint, MapPoint mapPoint2) {
        JSONArray jSONArray = new JSONArray();
        Iterator<BusPath> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(create_jsBusRouteObject(it.next(), mapPoint, mapPoint2));
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray toDriveRouteArray(List<DrivePath> list, MapPoint mapPoint, MapPoint mapPoint2) {
        JSONArray jSONArray = new JSONArray();
        Iterator<DrivePath> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(create_jsDriveRouteObject(it.next(), mapPoint, mapPoint2));
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray toPositionArray(ArrayList<PoiItem> arrayList) {
        JSONArray jSONArray = new JSONArray();
        Iterator<PoiItem> it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put(create_js_Position_Obj(it.next()));
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray toWalkRouteArray(List<WalkPath> list, MapPoint mapPoint, MapPoint mapPoint2) {
        JSONArray jSONArray = new JSONArray();
        Iterator<WalkPath> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(create_jsWalkRouteObject(it.next(), mapPoint, mapPoint2));
        }
        return jSONArray;
    }

    public void drivingSearch(Object obj, String str, Object obj2, String str2) {
        AMapSearchResultData aMapSearchResultData = new AMapSearchResultData(1, obj, str, obj2, str2);
        LatLonPoint latLonPoint = getLatLonPoint(obj, str, aMapSearchResultData, 1);
        LatLonPoint latLonPoint2 = getLatLonPoint(obj2, str2, aMapSearchResultData, 2);
        if (latLonPoint == null || latLonPoint2 == null) {
            return;
        }
        this.mSearchHandler.calculateDriveRouteAsyn(new RouteSearch.DriveRouteQuery(new RouteSearch.FromAndTo(latLonPoint, latLonPoint2), this.drivingMode, (List) null, (List) null, ""));
    }

    public int getPageCapacity() {
        return this.mPageCapacity;
    }

    public void poiSearchInCity(String str, String str2, String str3) {
        PoiSearch.Query query = new PoiSearch.Query(str2, "", str);
        int i = PdrUtil.parseInt(str3, 0);
        this.mIndex = i;
        query.setPageNum(i + 1);
        query.setPageSize(this.mPageCapacity);
        PoiSearch poiSearch = new PoiSearch(this.mIWebview.getContext(), query);
        poiSearch.setOnPoiSearchListener(this.mPoiSearchListener);
        poiSearch.searchPOIAsyn();
    }

    public void poiSearchInbounds(final String str, final MapPoint mapPoint, final MapPoint mapPoint2, final String str2) {
        getCityKey(new RegeocodeQuery(mapPoint.getLatLngPoint(), 200.0f, "autonavi"), new ICallBack() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.3
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                if (obj == null) {
                    return null;
                }
                PoiSearch.Query query = new PoiSearch.Query(str, "", (String) obj);
                query.setPageSize(MapSearch.this.mPageCapacity);
                MapSearch.this.mIndex = PdrUtil.parseInt(str2, 0);
                query.setPageNum(MapSearch.this.mIndex + 1);
                PoiSearch poiSearch = new PoiSearch(MapSearch.this.mIWebview.getContext(), query);
                poiSearch.setBound(new PoiSearch.SearchBound(mapPoint.getLatLngPoint(), mapPoint2.getLatLngPoint()));
                poiSearch.setOnPoiSearchListener(MapSearch.this.mPoiSearchListener);
                poiSearch.searchPOIAsyn();
                return null;
            }
        });
    }

    public void poiSearchNearBy(final String str, final MapPoint mapPoint, String str2, final String str3) {
        final int i = PdrUtil.parseInt(str2, 0);
        getCityKey(new RegeocodeQuery(mapPoint.getLatLngPoint(), i, "autonavi"), new ICallBack() { // from class: io.dcloud.js.map.amap.adapter.MapSearch.2
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i2, Object obj) {
                if (obj == null) {
                    return null;
                }
                PoiSearch.Query query = new PoiSearch.Query(str, "", (String) obj);
                query.setPageSize(MapSearch.this.mPageCapacity);
                MapSearch.this.mIndex = PdrUtil.parseInt(str3, 0);
                query.setPageNum(MapSearch.this.mIndex + 1);
                PoiSearch poiSearch = new PoiSearch(MapSearch.this.mIWebview.getContext(), query);
                poiSearch.setBound(new PoiSearch.SearchBound(mapPoint.getLatLngPoint(), i));
                poiSearch.setOnPoiSearchListener(MapSearch.this.mPoiSearchListener);
                poiSearch.searchPOIAsyn();
                return null;
            }
        });
    }

    public void setDrivingPolicy(int i) {
        this.drivingMode = i;
    }

    public void setMapView(DHMapView dHMapView) {
        this.mMapView = dHMapView;
    }

    public void setPageCapacity(String str) {
        this.mPageCapacity = PdrUtil.parseInt(str, 10);
    }

    public void setTransitPolicy(int i) {
        this.busMode = i;
    }

    public void setWalkPolicy(int i) {
        this.walkMode = i;
    }

    public void transitSearch(Object obj, Object obj2, String str) {
        AMapSearchResultData aMapSearchResultData = new AMapSearchResultData(0, obj, null, obj2, str);
        LatLonPoint latLonPoint = getLatLonPoint(obj, str, aMapSearchResultData, 1);
        LatLonPoint latLonPoint2 = getLatLonPoint(obj2, str, aMapSearchResultData, 2);
        if (latLonPoint == null || latLonPoint2 == null) {
            return;
        }
        this.mSearchHandler.calculateBusRouteAsyn(new RouteSearch.BusRouteQuery(new RouteSearch.FromAndTo(latLonPoint, latLonPoint2), this.busMode, str, 0));
    }

    public void walkingSearch(Object obj, String str, Object obj2, String str2) {
        AMapSearchResultData aMapSearchResultData = new AMapSearchResultData(2, obj, str, obj2, str2);
        LatLonPoint latLonPoint = getLatLonPoint(obj, str, aMapSearchResultData, 1);
        LatLonPoint latLonPoint2 = getLatLonPoint(obj2, str2, aMapSearchResultData, 2);
        if (latLonPoint == null || latLonPoint2 == null) {
            return;
        }
        this.mSearchHandler.calculateWalkRouteAsyn(new RouteSearch.WalkRouteQuery(new RouteSearch.FromAndTo(latLonPoint, latLonPoint2), this.walkMode));
    }

    public boolean setDrivingPolicy(String str) {
        if ("DRIVING_DIS_FIRST".equals(str)) {
            this.drivingMode = 2;
            return true;
        }
        if ("DRIVING_FEE_FIRST".equals(str)) {
            this.drivingMode = 1;
            return true;
        }
        if (!"DRIVING_NO_EXPRESSWAY".equals(str)) {
            return false;
        }
        this.drivingMode = 6;
        return true;
    }

    public boolean setTransitPolicy(String str) {
        if ("TRANSIT_FEE_FIRST".equals(str)) {
            this.busMode = 1;
        } else if ("TRANSIT_TIME_FIRST".equals(str)) {
            this.busMode = 0;
        } else if ("TRANSIT_TRANSFER_FIRST".equals(str)) {
            this.busMode = 2;
        } else {
            if (!"TRANSIT_WALK_FIRST".equals(str)) {
                return false;
            }
            this.busMode = 3;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String create_js_Position_Obj(MapPoint mapPoint, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(create_js_Point_Obj(mapPoint, "p"));
        MapJsUtil.create_Js_Var(stringBuffer, str, "plus.maps.Position", "p");
        return stringBuffer.toString();
    }
}
