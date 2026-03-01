package io.dcloud.feature.weex_amap.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.layout.UniContentBoxMeasurement;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;
import io.dcloud.feature.uniapp.ui.component.AbsVContainer;
import io.dcloud.feature.uniapp.ui.component.UniComponentProp;
import io.dcloud.feature.uniapp.ui.component.UniVContainer;
import io.dcloud.feature.weex.extend.DCCoverViewComponent;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapInterface;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import io.dcloud.feature.weex_amap.adapter.circle.CircleMgr;
import io.dcloud.feature.weex_amap.adapter.control.ControlMgr;
import io.dcloud.feature.weex_amap.adapter.ground.GroundMgr;
import io.dcloud.feature.weex_amap.adapter.marker.MarkerMgr;
import io.dcloud.feature.weex_amap.adapter.marker.WXMarker;
import io.dcloud.feature.weex_amap.adapter.polygon.PolygonMgr;
import io.dcloud.feature.weex_amap.adapter.polyline.PolylineMgr;
import io.dcloud.feature.weex_amap.ui.MapLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import supwisdom.j7;
import supwisdom.y7;

/* JADX INFO: loaded from: classes3.dex */
public class WXAMapViewComponent extends UniVContainer<FrameLayout> implements MapInterface {
    public static final int REQUEST_CODE_MAPVIEW = 11224;
    public static final String TAG = "WXAMapViewComponent";
    public static String[] permissions = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public String defBackgroundColor;
    public boolean isBuilding3D;
    public boolean isChangeStart;
    public boolean isCompassEnable;
    public boolean isEnableIndoorMap;
    public boolean isEnablePoi;
    public boolean isEnableSatellite;
    public boolean isEnableTraffic;
    public AtomicBoolean isInited;
    public AtomicBoolean isMapLoaded;
    public boolean isMyLocationEnable;
    public boolean isOverLookingEnable;
    public boolean isRotateEnable;
    public boolean isScrollEnable;
    public boolean isSetUpdate;
    public boolean isShowAnimationEnd;
    public boolean isShowScale;
    public boolean isZoomEnable;
    public AMap mAMap;
    public Activity mActivity;
    public String mCameraType;
    public CircleMgr mCircleMgr;
    public ControlMgr mControlMgr;
    public float mDefHeight;
    public float mDefWidth;
    public boolean mDragged;
    public GroundMgr mGroundMgr;
    public long mLoadTime;
    public AMapLocationClient mLocationClient;
    public AMapLocationClientOption mLocationOption;
    public Point mMapCenterPoint;
    public FrameLayout mMapContainer;
    public WXMapView mMapView;
    public MarkerMgr mMarkerMgr;
    public Location mMyLocation;
    public AMap.OnMyLocationChangeListener mMyLocationCallBack;
    public PolygonMgr mPolygonMgr;
    public PolylineMgr mPolylineMgr;
    public UiSettings mUiSettings;
    public float mZoomLevel;
    public MyLocationStyle myLocationStyle;
    public Queue<MapOperationTask> paddingTasks;

    public interface MapOperationTask {
        void execute(WXMapView wXMapView);
    }

    public class Point {
        public double latitude;
        public double longitude;

        public Point() {
        }

        public void update(double d, double d2) {
            this.latitude = d;
            this.longitude = d2;
        }
    }

    public WXAMapViewComponent(UniSDKInstance uniSDKInstance, AbsVContainer absVContainer, AbsComponentData absComponentData) {
        super(uniSDKInstance, absVContainer, absComponentData);
        this.isZoomEnable = true;
        this.isBuilding3D = true;
        this.isCompassEnable = false;
        this.isScrollEnable = true;
        this.isRotateEnable = false;
        this.isMyLocationEnable = false;
        this.isOverLookingEnable = false;
        this.isEnableSatellite = false;
        this.isEnableTraffic = false;
        this.isShowScale = false;
        this.isEnablePoi = true;
        this.isEnableIndoorMap = false;
        this.mZoomLevel = 16.0f;
        this.isMapLoaded = new AtomicBoolean(false);
        this.isInited = new AtomicBoolean(false);
        this.paddingTasks = new LinkedList();
        this.defBackgroundColor = "#f1f1f1";
        this.mLoadTime = 0L;
        this.isChangeStart = false;
        this.mDragged = false;
        this.mCameraType = "drag";
        this.isSetUpdate = false;
        this.mDefHeight = 0.0f;
        this.mDefWidth = 0.0f;
        this.isShowAnimationEnd = false;
        this.mLoadTime = System.currentTimeMillis();
        this.mDefHeight = WXViewUtils.getRealPxByWidth(150.0f, getInstance().getInstanceViewPortWidthWithFloat());
        this.mDefWidth = WXViewUtils.getRealPxByWidth(300.0f, getInstance().getInstanceViewPortWidthWithFloat());
        if (!absComponentData.getStyles().containsKey("backgroundColor")) {
            absComponentData.getStyles().put("backgroundColor", (Object) this.defBackgroundColor);
        }
        if (!absComponentData.getStyles().containsKey(Constants.Name.FLEX)) {
            setContentBoxMeasurement(new UniContentBoxMeasurement() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.1
                @Override // com.taobao.weex.layout.ContentBoxMeasurement
                public void layoutAfter(float f, float f2) {
                }

                @Override // com.taobao.weex.layout.ContentBoxMeasurement
                public void layoutBefore() {
                }

                @Override // com.taobao.weex.layout.ContentBoxMeasurement
                public void measureInternal(float f, float f2, int i, int i2) {
                    if (CSSConstants.isUndefined(f2)) {
                        f2 = WXAMapViewComponent.this.mDefHeight;
                    }
                    this.mMeasureHeight = f2;
                }
            });
        }
        getInstance().addFrameViewEventListener(new WXSDKInstance.FrameViewEventListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.2
            @Override // com.taobao.weex.WXSDKInstance.FrameViewEventListener
            public void onShowAnimationEnd() {
                WXAMapViewComponent.this.isShowAnimationEnd = true;
                if (WXAMapViewComponent.this.mMapView != null) {
                    WXAMapViewComponent.this.mMapView.setVisibility(0);
                    WXLogUtils.e(WXAMapViewComponent.TAG, "Map VISIBLE");
                }
                WXAMapViewComponent.this.getInstance().removeFrameViewEventListener(this);
            }
        });
    }

    private void createMap() {
        FrameLayout frameLayout = this.mMapContainer;
        if (frameLayout != null) {
            int i = frameLayout.getChildCount() > 0 ? 0 : -1;
            this.mMapView = new WXMapView(getContext(), getInstance());
            if (this.mMarkerMgr == null) {
                this.mMarkerMgr = new MarkerMgr(getInstance(), this.mMapView);
            }
            this.mMapContainer.addView((View) this.mMapView, i, new FrameLayout.LayoutParams(-1, -1));
            this.mMapView.onCreate(null);
            this.mMapView.setVisibility(4);
            WXLogUtils.e(TAG, "Create MapView " + this.mMapView.toString());
            initMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean equalsLocation(Location location, Location location2) {
        return location != null && location2 != null && location.getLatitude() == location2.getLatitude() && location.getLongitude() == location2.getLongitude() && location.getBearing() == location2.getBearing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean equalsMyLocationMarker(LatLng latLng) {
        AMap aMap;
        if (latLng != null && (aMap = this.mAMap) != null && aMap.isMyLocationEnabled()) {
            Location myLocation = this.mAMap.getMyLocation();
            if (latLng.latitude == myLocation.getLatitude() && latLng.longitude == myLocation.getLongitude()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execPaddingTasks() {
        WXMapView wXMapView;
        if (getInstance() == null || getHostView() == 0) {
            return;
        }
        while (!this.paddingTasks.isEmpty()) {
            MapOperationTask mapOperationTaskPoll = this.paddingTasks.poll();
            if (mapOperationTaskPoll != null && (wXMapView = this.mMapView) != null && wXMapView.getMap() != null) {
                WXLogUtils.d(TAG, "Exec padding task " + mapOperationTaskPoll.toString());
                mapOperationTaskPoll.execute(this.mMapView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void fireEventMapEvent(java.lang.String r13, java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 404
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.fireEventMapEvent(java.lang.String, java.lang.Object):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean requestPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            if (checkPermissions(activity, permissions)) {
                return true;
            }
            PermissionControler.requestPermissions(this.mActivity, permissions, REQUEST_CODE_MAPVIEW);
        }
        return false;
    }

    private void setUpMap() {
        this.mAMap.showBuildings(this.isBuilding3D);
        this.mAMap.moveCamera(CameraUpdateFactory.zoomTo(this.mZoomLevel));
        UiSettings uiSettings = this.mAMap.getUiSettings();
        this.mUiSettings = uiSettings;
        uiSettings.setZoomGesturesEnabled(this.isZoomEnable);
        this.mUiSettings.setCompassEnabled(this.isCompassEnable);
        this.mUiSettings.setRotateGesturesEnabled(this.isRotateEnable);
        this.mUiSettings.setScrollGesturesEnabled(this.isScrollEnable);
        this.mUiSettings.setZoomControlsEnabled(false);
        this.mUiSettings.setTiltGesturesEnabled(this.isOverLookingEnable);
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void addChild(WXComponent wXComponent, int i) {
        Object obj;
        super.addChild(wXComponent, i);
        if (!(wXComponent instanceof DCCoverViewComponent) || wXComponent == null || wXComponent.getAttrs() == null || !wXComponent.getAttrs().containsKey("slot") || this.mMapView == null || (obj = wXComponent.getAttrs().get("slot")) == null || !obj.equals(Constant.JSONKEY.CALLOUT)) {
            return;
        }
        this.mMapView.setCoverViewCalloutComponetRef(wXComponent.getRef());
    }

    @UniJSMethod
    public void addCustomLayer(String str, UniJSCallback uniJSCallback) {
    }

    @UniJSMethod
    public void addGroundOverlay(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.44
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mGroundMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mGroundMgr = new GroundMgr(wXAMapViewComponent.getInstance(), wXMapView);
                }
                WXAMapViewComponent.this.mGroundMgr.addGroundOverlay(jSONObject, uniJSCallback);
            }
        });
    }

    @UniJSMethod
    public void addMarkers(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.39
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mMarkerMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mMarkerMgr = new MarkerMgr(wXAMapViewComponent.getInstance(), wXMapView);
                }
                HashMap map = new HashMap();
                if (WXAMapViewComponent.this.mMarkerMgr != null) {
                    if (jSONObject.containsKey("clear") && jSONObject.getBoolean("clear").booleanValue()) {
                        WXAMapViewComponent.this.mMarkerMgr.destroy();
                    }
                    if (jSONObject.containsKey(Constant.Name.MARKERS)) {
                        try {
                            WXAMapViewComponent.this.mMarkerMgr.setMarkers(jSONObject.getJSONArray(Constant.Name.MARKERS), false);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    map.put("type", "success");
                    UniJSCallback uniJSCallback2 = uniJSCallback;
                    if (uniJSCallback2 != null) {
                        uniJSCallback2.invoke(map);
                    }
                }
            }
        });
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void addSubView(View view, int i) {
        if (view == null || getRealView() == null) {
            return;
        }
        int childCount = getRealView().getChildCount();
        if (childCount >= 1 && (getRealView().getChildAt(0) instanceof WXMapView)) {
            i = i >= childCount ? 1 : i + 1;
        } else if (i >= childCount) {
            i = -1;
        }
        if (i == -1) {
            getRealView().addView(view);
        } else {
            getRealView().addView(view, i);
        }
        UniSDKInstance uniVContainer = getInstance();
        if (uniVContainer != null) {
            uniVContainer.getApmForInstance().hasAddView = true;
        }
    }

    public boolean checkPermissions(Activity activity, String[] strArr) {
        boolean z = true;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (y7.a(activity, str) != 0) {
                    j7.a(activity, str);
                    z = false;
                }
            }
        }
        return z;
    }

    @UniJSMethod
    public void getCenterLocation(UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        if (this.isMapLoaded.get()) {
            map.put("type", "success");
            map.put(Constant.JSONKEY.LATITUDE, Double.valueOf(this.mMapCenterPoint.latitude));
            map.put(Constant.JSONKEY.LONGITUDE, Double.valueOf(this.mMapCenterPoint.longitude));
        } else {
            map.put("type", Constants.Event.FAIL);
        }
        if (uniJSCallback != null) {
            uniJSCallback.invoke(map);
        }
    }

    @UniJSMethod
    public void getRegion(UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        if (this.isMapLoaded.get()) {
            LatLngBounds latLngBounds = this.mAMap.getProjection().getVisibleRegion().latLngBounds;
            map.put("type", "success");
            map.put("northeast", latLngBounds.northeast);
            map.put("southwest", latLngBounds.southwest);
        } else {
            map.put("type", Constants.Event.FAIL);
        }
        if (uniJSCallback != null) {
            uniJSCallback.invoke(map);
        }
    }

    @UniJSMethod
    public void getRotate(UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        if (this.isMapLoaded.get()) {
            map.put("type", "success");
            map.put("rotate", Float.valueOf(this.mAMap.getCameraPosition().bearing));
        } else {
            map.put("type", Constants.Event.FAIL);
        }
        if (uniJSCallback != null) {
            uniJSCallback.invoke(map);
        }
    }

    @UniJSMethod
    public void getScale(UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        if (this.isMapLoaded.get()) {
            map.put("type", "success");
            map.put("scale", Float.valueOf(this.mAMap.getCameraPosition().zoom));
        } else {
            map.put("type", Constants.Event.FAIL);
        }
        if (uniJSCallback != null) {
            uniJSCallback.invoke(map);
        }
    }

    @UniJSMethod
    public void getSkew(UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        if (this.isMapLoaded.get()) {
            map.put("type", "success");
            map.put("rotate", Float.valueOf(this.mAMap.getCameraPosition().tilt));
        } else {
            map.put("type", Constants.Event.FAIL);
        }
        if (uniJSCallback != null) {
            uniJSCallback.invoke(map);
        }
    }

    @UniJSMethod
    public void getUserLocation(final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.43
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                Location myLocation = wXMapView.getMap().getMyLocation();
                HashMap map = new HashMap();
                if (myLocation == null) {
                    map.put("type", Constants.Event.FAIL);
                    uniJSCallback.invoke(map);
                } else {
                    map.put("type", "success");
                    map.put(Constant.JSONKEY.LATITUDE, Double.valueOf(myLocation.getLatitude()));
                    map.put(Constant.JSONKEY.LONGITUDE, Double.valueOf(myLocation.getLongitude()));
                    uniJSCallback.invoke(map);
                }
            }
        });
    }

    @UniJSMethod
    public void includePoints(JSONObject jSONObject, UniJSCallback uniJSCallback) throws Exception {
        HashMap map = new HashMap();
        if (!this.isMapLoaded.get() || jSONObject == null) {
            map.put("type", Constants.Event.FAIL);
        } else {
            JSONArray jSONArray = jSONObject.getJSONArray("points");
            JSONArray jSONArray2 = jSONObject.getJSONArray("padding");
            setincludePoints(jSONArray, jSONArray2 != null ? jSONArray2.getIntValue(0) : 20);
            map.put("type", "success");
            map.put("scale", Float.valueOf(this.mZoomLevel));
        }
        if (uniJSCallback != null) {
            uniJSCallback.invoke(map);
        }
    }

    @Override // io.dcloud.feature.weex_amap.adapter.MapInterface
    public void initMap() {
        this.isMapLoaded.set(false);
        if (this.mAMap == null) {
            this.mMapCenterPoint = new Point();
            AMap map = this.mMapView.getMap();
            this.mAMap = map;
            map.setMinZoomLevel(3.0f);
            this.mAMap.setMaxZoomLevel(20.0f);
            setCenter(getAttrs().get(Constant.JSONKEY.LATITUDE), getAttrs().get(Constant.JSONKEY.LONGITUDE));
            this.mAMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.4
                public void onMapLoaded() {
                    WXLogUtils.e(WXAMapViewComponent.TAG, "Map loaded");
                    WXAMapViewComponent.this.isMapLoaded.set(true);
                    WXAMapViewComponent.this.mMapView.postDelayed(new Runnable() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            WXAMapViewComponent.this.execPaddingTasks();
                        }
                    }, 16L);
                    WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.UPDATED, null);
                }
            });
            this.mAMap.setOnMapClickListener(new AMap.OnMapClickListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.5
                public void onMapClick(LatLng latLng) {
                    WXAMapViewComponent.this.mMarkerMgr.hideMarkerCallout();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(Constant.JSONKEY.LONGITUDE, (Object) Double.valueOf(latLng.longitude));
                    jSONObject.put(Constant.JSONKEY.LATITUDE, (Object) Double.valueOf(latLng.latitude));
                    WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BINDTAP, jSONObject);
                }
            });
            this.mAMap.addOnMarkerClickListener(new AMap.OnMarkerClickListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.6
                public boolean onMarkerClick(Marker marker) {
                    if (marker == null) {
                        return false;
                    }
                    JSONObject jSONObject = new JSONObject();
                    WXMarker wXMarker = WXAMapViewComponent.this.mMarkerMgr.getWXMarker(marker);
                    if (wXMarker != null) {
                        jSONObject.put("markerId", MapResourceUtils.getId(wXMarker.getId()));
                        if (wXMarker.getCallout() != null) {
                            WXAMapViewComponent.this.mMarkerMgr.hideMarkerCallout();
                            if (!wXMarker.getCallout().isAlwaysDisPlay()) {
                                if (wXMarker.isCalloutShown()) {
                                    wXMarker.hideCallout();
                                } else {
                                    wXMarker.showCallout();
                                }
                            }
                        } else if (marker.isInfoWindowShown()) {
                            marker.hideInfoWindow();
                        } else {
                            marker.showInfoWindow();
                        }
                        WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BIND_MARKER_TAP, jSONObject);
                        return true;
                    }
                    WXMarker calloutToWXMarker = WXAMapViewComponent.this.mMarkerMgr.getCalloutToWXMarker(marker);
                    if (calloutToWXMarker != null) {
                        jSONObject.put("markerId", MapResourceUtils.getId(calloutToWXMarker.getId()));
                        WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BIND_CALLOUT_TAP, jSONObject);
                        return true;
                    }
                    WXMarker labelToWXMarker = WXAMapViewComponent.this.mMarkerMgr.getLabelToWXMarker(marker);
                    if (labelToWXMarker != null) {
                        jSONObject.put("markerId", MapResourceUtils.getId(labelToWXMarker.getId()));
                        WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BIND_LABEL_TAP, jSONObject);
                        return true;
                    }
                    if (labelToWXMarker != null || !WXAMapViewComponent.this.equalsMyLocationMarker(marker.getPosition())) {
                        return false;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(Constant.JSONKEY.LONGITUDE, (Object) Double.valueOf(marker.getPosition().longitude));
                    jSONObject2.put(Constant.JSONKEY.LATITUDE, (Object) Double.valueOf(marker.getPosition().latitude));
                    WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BIND_ANCHOR_POINT_TAP, jSONObject2);
                    return true;
                }
            });
            this.mAMap.addOnCameraChangeListener(new AMap.OnCameraChangeListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.7
                public void onCameraChange(CameraPosition cameraPosition) {
                    WXAMapViewComponent.this.mMapCenterPoint.update(cameraPosition.target.latitude, cameraPosition.target.longitude);
                    if (WXAMapViewComponent.this.isChangeStart || !WXAMapViewComponent.this.isMapLoaded.get()) {
                        return;
                    }
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    if (wXAMapViewComponent.mDragged) {
                        wXAMapViewComponent.mCameraType = Constant.Name.GESTURE;
                    } else {
                        wXAMapViewComponent.mCameraType = "update";
                    }
                    WXAMapViewComponent.this.mZoomLevel = cameraPosition.zoom;
                    WXAMapViewComponent.this.isChangeStart = true;
                    WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BINDREGION_CHANGE, "begin");
                }

                public void onCameraChangeFinish(CameraPosition cameraPosition) {
                    if (WXAMapViewComponent.this.isChangeStart && WXAMapViewComponent.this.isMapLoaded.get()) {
                        WXAMapViewComponent.this.isChangeStart = false;
                        WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                        if (wXAMapViewComponent.isSetUpdate) {
                            wXAMapViewComponent.mCameraType = "update";
                        } else if (cameraPosition.zoom != WXAMapViewComponent.this.mZoomLevel) {
                            WXAMapViewComponent.this.mCameraType = "scale";
                        } else {
                            WXAMapViewComponent.this.mCameraType = "drag";
                        }
                        WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BINDREGION_CHANGE, WXGesture.END);
                    }
                    WXAMapViewComponent.this.isSetUpdate = false;
                }
            });
            this.mAMap.setOnMapTouchListener(new AMap.OnMapTouchListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.8
                public void onTouch(MotionEvent motionEvent) {
                    int action = motionEvent.getAction();
                    if (action == 1) {
                        WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                        if (wXAMapViewComponent.mDragged) {
                            wXAMapViewComponent.getInstance().fireEvent(WXAMapViewComponent.this.getRef(), "dragend");
                        }
                        WXAMapViewComponent.this.mDragged = false;
                        return;
                    }
                    if (action != 2) {
                        return;
                    }
                    WXAMapViewComponent wXAMapViewComponent2 = WXAMapViewComponent.this;
                    wXAMapViewComponent2.mDragged = true;
                    wXAMapViewComponent2.isSetUpdate = false;
                }
            });
            this.mAMap.setOnPOIClickListener(new AMap.OnPOIClickListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.9
                public void onPOIClick(Poi poi) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("name", (Object) poi.getName());
                    jSONObject.put(Constant.JSONKEY.LONGITUDE, (Object) Double.valueOf(poi.getCoordinate().longitude));
                    jSONObject.put(Constant.JSONKEY.LATITUDE, (Object) Double.valueOf(poi.getCoordinate().latitude));
                    WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BIND_POI_TAP, jSONObject);
                }
            });
            setUpMap();
        }
    }

    @UniJSMethod
    public void initMarkerCluster(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.38
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                HashMap map = new HashMap();
                if (wXMapView.getClusterOverlay() != null) {
                    map.put("type", "success");
                    UniJSCallback uniJSCallback2 = uniJSCallback;
                    if (uniJSCallback2 != null) {
                        uniJSCallback2.invoke(map);
                        return;
                    }
                    return;
                }
                wXMapView.createClusterOverlay(jSONObject);
                map.put("type", "success");
                UniJSCallback uniJSCallback3 = uniJSCallback;
                if (uniJSCallback3 != null) {
                    uniJSCallback3.invoke(map);
                }
            }
        });
    }

    @UniJSMethod
    public void moveAlong(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.42
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mMarkerMgr != null) {
                    HashMap map = new HashMap();
                    if (jSONObject.containsKey("markerId")) {
                        WXAMapViewComponent.this.mMarkerMgr.moveAlongMarker(jSONObject, uniJSCallback);
                        return;
                    }
                    map.put("type", Constants.Event.FAIL);
                    UniJSCallback uniJSCallback2 = uniJSCallback;
                    if (uniJSCallback2 != null) {
                        uniJSCallback2.invoke(map);
                    }
                }
            }
        });
    }

    @UniJSMethod
    public void moveToLocation(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.36
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                HashMap map = new HashMap();
                Location myLocation = wXMapView.getMap().getMyLocation();
                JSONObject jSONObject2 = jSONObject;
                LatLng latLngCrateLatLng = jSONObject2 != null ? MapResourceUtils.crateLatLng(jSONObject2) : myLocation != null ? new LatLng(myLocation.getLatitude(), myLocation.getLongitude()) : null;
                if (latLngCrateLatLng != null) {
                    wXMapView.getMap().animateCamera(CameraUpdateFactory.changeLatLng(latLngCrateLatLng));
                    map.put("type", "success");
                } else {
                    map.put("type", Constants.Event.FAIL);
                }
                UniJSCallback uniJSCallback2 = uniJSCallback;
                if (uniJSCallback2 != null) {
                    uniJSCallback2.invoke(map);
                }
            }
        });
    }

    @UniJSMethod
    public void on(final String str, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.41
            /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void execute(final io.dcloud.feature.weex_amap.adapter.WXMapView r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = r2
                    int r1 = r0.hashCode()
                    r2 = -273840772(0xffffffffefad857c, float:-1.0740456E29)
                    r3 = 1
                    if (r1 == r2) goto L1c
                    r2 = 1653559560(0x628f5108, float:1.3218617E21)
                    if (r1 == r2) goto L12
                    goto L26
                L12:
                    java.lang.String r1 = "markerClusterClick"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L26
                    r0 = 1
                    goto L27
                L1c:
                    java.lang.String r1 = "markerClusterCreate"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L26
                    r0 = 0
                    goto L27
                L26:
                    r0 = -1
                L27:
                    if (r0 == 0) goto L35
                    if (r0 == r3) goto L2c
                    goto L3d
                L2c:
                    io.dcloud.feature.weex_amap.component.WXAMapViewComponent$41$2 r0 = new io.dcloud.feature.weex_amap.component.WXAMapViewComponent$41$2
                    r0.<init>()
                    r5.setClusterClickListener(r0)
                    goto L3d
                L35:
                    io.dcloud.feature.weex_amap.component.WXAMapViewComponent$41$1 r0 = new io.dcloud.feature.weex_amap.component.WXAMapViewComponent$41$1
                    r0.<init>()
                    r5.setClusterCreateListener(r0)
                L3d:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.AnonymousClass41.execute(io.dcloud.feature.weex_amap.adapter.WXMapView):void");
            }
        });
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityCreate() {
        super.onActivityCreate();
        WXLogUtils.e(TAG, "onActivityCreate");
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityDestroy() {
        onActivityPause();
        WXMapView wXMapView = this.mMapView;
        if (wXMapView != null) {
            wXMapView.setVisibility(8);
            MarkerMgr markerMgr = this.mMarkerMgr;
            if (markerMgr != null) {
                markerMgr.destroy();
            }
            GroundMgr groundMgr = this.mGroundMgr;
            if (groundMgr != null) {
                groundMgr.destroy();
            }
            this.mMapView.destroy();
            this.mMapContainer.removeView(this.mMapView);
            this.mMyLocation = null;
            this.mAMap.clear();
        }
        AMapLocationClient aMapLocationClient = this.mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.onDestroy();
        }
        WXLogUtils.e(TAG, "onActivityDestroy");
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityPause() {
        WXMapView wXMapView = this.mMapView;
        if (wXMapView != null) {
            wXMapView.onPause();
        }
        WXLogUtils.e(TAG, "onActivityPause");
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.IWXActivityStateListener
    public void onActivityResume() {
        WXMapView wXMapView = this.mMapView;
        if (wXMapView != null) {
            wXMapView.onResume();
        }
        WXLogUtils.e(TAG, "onActivityResume");
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 11224) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                String str = strArr[i2];
                int i3 = iArr[i2];
                if ("android.permission.ACCESS_FINE_LOCATION".equals(str) && i3 == 0) {
                    showMyLocation(true);
                }
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @UniJSMethod
    public void openMapApp(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("geo:");
            stringBuffer.append(jSONObject.getDoubleValue(Constant.JSONKEY.LATITUDE));
            stringBuffer.append(",");
            stringBuffer.append(jSONObject.getDoubleValue(Constant.JSONKEY.LATITUDE));
            stringBuffer.append(Operators.CONDITION_IF_STRING);
            stringBuffer.append("q=");
            stringBuffer.append(jSONObject.getString("destination"));
            getInstance().getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString())));
            map.put("type", "success");
            if (uniJSCallback != null) {
                uniJSCallback.invoke(map);
            }
        } catch (Exception unused) {
            map.put("type", Constants.Event.FAIL);
            if (uniJSCallback != null) {
                uniJSCallback.invoke(map);
            }
        }
    }

    public void postTask(MapOperationTask mapOperationTask) {
        if (this.mMapView == null || !this.isMapLoaded.get()) {
            WXLogUtils.d(TAG, "Padding task " + mapOperationTask.toString());
            this.paddingTasks.offer(mapOperationTask);
            return;
        }
        WXLogUtils.d(TAG, "Exec task " + mapOperationTask.toString());
        mapOperationTask.execute(this.mMapView);
    }

    @UniJSMethod
    public void removeCustomLayer(JSONObject jSONObject, UniJSCallback uniJSCallback) {
    }

    @UniJSMethod
    public void removeGroundOverlay(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.46
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 != null && jSONObject2.containsKey("id")) {
                    if (WXAMapViewComponent.this.mGroundMgr == null) {
                        WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                        wXAMapViewComponent.mGroundMgr = new GroundMgr(wXAMapViewComponent.getInstance(), wXMapView);
                    }
                    WXAMapViewComponent.this.mGroundMgr.removeGroundOverlay(jSONObject.getString("id"), uniJSCallback);
                    return;
                }
                if (uniJSCallback != null) {
                    HashMap map = new HashMap();
                    map.put("type", Constants.Event.FAIL);
                    map.put("msg", "Parameter exception");
                    uniJSCallback.invoke(map);
                }
            }
        });
    }

    @UniJSMethod
    public void removeMarkers(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.40
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mMarkerMgr != null) {
                    HashMap map = new HashMap();
                    if (!jSONObject.containsKey("markerIds")) {
                        map.put("type", Constants.Event.FAIL);
                        UniJSCallback uniJSCallback2 = uniJSCallback;
                        if (uniJSCallback2 != null) {
                            uniJSCallback2.invoke(map);
                            return;
                        }
                        return;
                    }
                    try {
                        WXAMapViewComponent.this.mMarkerMgr.removeMarkers(jSONObject.getJSONArray("markerIds"));
                        map.put("type", "success");
                        if (uniJSCallback != null) {
                            uniJSCallback.invoke(map);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        map.put("type", Constants.Event.FAIL);
                        UniJSCallback uniJSCallback3 = uniJSCallback;
                        if (uniJSCallback3 != null) {
                            uniJSCallback3.invoke(map);
                        }
                    }
                }
            }
        });
    }

    @UniComponentProp(name = Constant.Name.KEYS)
    public void setApiKey(String str) throws Exception {
        String string = JSON.parseObject(str).getString("android");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        MapsInitializer.setApiKey(string);
        AMapLocationClient.setApiKey(string);
        WXLogUtils.d(TAG, "Set API key success");
    }

    @Override // io.dcloud.feature.weex_amap.adapter.MapInterface
    public void setCenter(Object obj, Object obj2) {
        LatLng latLngCrateLatLng = MapResourceUtils.crateLatLng(obj, obj2);
        if (latLngCrateLatLng == null) {
            return;
        }
        this.mMapCenterPoint.update(latLngCrateLatLng.latitude, latLngCrateLatLng.longitude);
        this.mAMap.moveCamera(CameraUpdateFactory.changeLatLng(latLngCrateLatLng));
    }

    @UniComponentProp(name = Constant.Name.CIRCLES)
    public void setCircles(final JSONArray jSONArray) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.28
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mCircleMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mCircleMgr = new CircleMgr(wXAMapViewComponent.getInstance(), WXAMapViewComponent.this.mMapView);
                }
                WXAMapViewComponent.this.mCircleMgr.setCircles(jSONArray);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.SHOW_COMPASS)
    public void setCompass(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.12
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isCompassEnable = z;
                WXAMapViewComponent.this.mUiSettings.setCompassEnabled(z);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_BUILDING)
    public void setEnableBuilding(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.23
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isBuilding3D = z;
                wXMapView.getMap().showBuildings(z);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_INDOOR_MAP)
    public void setEnableIndoorMap(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.24
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isEnableIndoorMap = z;
                wXMapView.getMap().showIndoorMap(z);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_POI)
    public void setEnablePoi(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.22
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isEnablePoi = z;
                wXMapView.getMap().showMapText(z);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_SATELLITE)
    public void setEnableSatellite(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.13
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isEnableSatellite = z;
                if (!WXAMapViewComponent.this.isEnableSatellite) {
                    WXAMapViewComponent.this.mAMap.setMapType(1);
                    return;
                }
                AMap aMap = WXAMapViewComponent.this.mAMap;
                AMap unused = WXAMapViewComponent.this.mAMap;
                aMap.setMapType(2);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_TRAFFIC)
    public void setEnableTraffic(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.14
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isEnableTraffic = z;
                WXAMapViewComponent.this.mAMap.setTrafficEnabled(WXAMapViewComponent.this.isEnableTraffic);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.LAYER_STYLE)
    public void setLayerStyle(final String str) {
        if (PdrUtil.isEmpty(str)) {
            return;
        }
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.33
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                CustomMapStyleOptions customMapStyleOptions = new CustomMapStyleOptions();
                customMapStyleOptions.setEnable(true);
                customMapStyleOptions.setStyleId(str);
                wXMapView.getMap().setCustomMapStyle(customMapStyleOptions);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.MARKERS)
    public void setMarkers(final JSONArray jSONArray) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.25
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mMarkerMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mMarkerMgr = new MarkerMgr(wXAMapViewComponent.getInstance(), WXAMapViewComponent.this.mMapView);
                }
                try {
                    WXAMapViewComponent.this.mMarkerMgr.setMarkers(jSONArray, true);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @UniComponentProp(name = Constant.Name.MAX_SCALE)
    public void setMaxScale(final String str) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.32
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                int i = WXUtils.getInt(str);
                if (i <= 3 || i > 20) {
                    return;
                }
                wXMapView.getMap().setMaxZoomLevel(i);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.MIN_SCALE)
    public void setMinScale(final String str) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.31
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                int i = WXUtils.getInt(str);
                if (i < 3 || i >= 20) {
                    return;
                }
                wXMapView.getMap().setMinZoomLevel(i);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_OVERLOOKING)
    public void setOverLookingEnable(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.21
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isOverLookingEnable = z;
                WXAMapViewComponent.this.mUiSettings.setTiltGesturesEnabled(z);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.POLYGONS)
    public void setPolygon(final JSONArray jSONArray) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.27
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mPolygonMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mPolygonMgr = new PolygonMgr(wXAMapViewComponent.getInstance(), WXAMapViewComponent.this.mMapView);
                }
                WXAMapViewComponent.this.mPolygonMgr.setPolygon(jSONArray);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.POLYLINE)
    public void setPolyline(final JSONArray jSONArray) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.26
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mPolylineMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mPolylineMgr = new PolylineMgr(wXAMapViewComponent.getInstance(), WXAMapViewComponent.this.mMapView);
                }
                WXAMapViewComponent.this.mPolylineMgr.setPolyline(jSONArray);
            }
        });
    }

    @UniComponentProp(name = "rotate")
    public void setRotate(final float f) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.19
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                wXAMapViewComponent.isSetUpdate = true;
                wXAMapViewComponent.mAMap.moveCamera(CameraUpdateFactory.changeBearing(f));
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_ROTATE)
    public void setRotateEnable(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.18
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isRotateEnable = z;
                WXAMapViewComponent.this.mUiSettings.setRotateGesturesEnabled(z);
            }
        });
    }

    @UniComponentProp(name = "scale")
    public void setScale(final float f) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.11
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.mZoomLevel = f;
                WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                wXAMapViewComponent.isSetUpdate = true;
                wXAMapViewComponent.mAMap.moveCamera(CameraUpdateFactory.zoomTo(f));
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_SCROLL)
    public void setScrollEnable(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.17
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isScrollEnable = z;
                WXAMapViewComponent.this.mUiSettings.setScrollGesturesEnabled(z);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.SETTING)
    public void setSetting(final JSONObject jSONObject) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.30
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:52:0x00b8  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x00bc  */
            /* JADX WARN: Removed duplicated region for block: B:55:0x00be A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:56:0x00cb A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:57:0x00d8 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:58:0x00e5 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:59:0x00f2 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:60:0x00ff A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:61:0x010b A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:62:0x0117 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:63:0x0123 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:64:0x0131 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:65:0x013d A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:66:0x0149 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:67:0x0155 A[Catch: Exception -> 0x016f, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /* JADX WARN: Removed duplicated region for block: B:68:0x0161 A[Catch: Exception -> 0x016f, TRY_LEAVE, TryCatch #0 {Exception -> 0x016f, blocks: (B:10:0x003d, B:53:0x00b9, B:55:0x00be, B:56:0x00cb, B:57:0x00d8, B:58:0x00e5, B:59:0x00f2, B:60:0x00ff, B:61:0x010b, B:62:0x0117, B:63:0x0123, B:64:0x0131, B:65:0x013d, B:66:0x0149, B:67:0x0155, B:68:0x0161, B:13:0x0046, B:16:0x0050, B:19:0x005a, B:22:0x0062, B:25:0x006b, B:28:0x0074, B:31:0x007c, B:34:0x0086, B:37:0x008e, B:40:0x0096, B:43:0x009f, B:46:0x00a7, B:49:0x00b0), top: B:75:0x003d }] */
            /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
                java.lang.UnsupportedOperationException
                	at java.base/java.util.Collections$UnmodifiableCollection.add(Unknown Source)
                	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
                	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
                	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
                	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
                	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
                	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
                */
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void execute(io.dcloud.feature.weex_amap.adapter.WXMapView r18) {
                /*
                    Method dump skipped, instruction units count: 468
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.AnonymousClass30.execute(io.dcloud.feature.weex_amap.adapter.WXMapView):void");
            }
        });
    }

    @UniComponentProp(name = Constant.Name.SKEW)
    public void setSkew(final float f) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.20
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                wXAMapViewComponent.isSetUpdate = true;
                wXAMapViewComponent.mAMap.moveCamera(CameraUpdateFactory.changeTilt(f));
            }
        });
    }

    @UniComponentProp(name = Constant.Name.ENABLE_ZOOM)
    public void setZoomEnable(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.16
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isZoomEnable = z;
                WXAMapViewComponent.this.mUiSettings.setZoomGesturesEnabled(z);
            }
        });
    }

    @UniComponentProp(name = "controls")
    public void setcontrols(final JSONArray jSONArray) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.29
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mControlMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mControlMgr = new ControlMgr(wXAMapViewComponent.getInstance(), WXAMapViewComponent.this.getRef(), WXAMapViewComponent.this.mMapView, WXAMapViewComponent.this.mMapContainer);
                }
                WXAMapViewComponent.this.mControlMgr.setControls(jSONArray);
            }
        });
    }

    @UniComponentProp(name = Constant.Name.INCLUDE_POINTS)
    public void setincludePoints(JSONArray jSONArray) throws Exception {
        setincludePoints(jSONArray, 0);
    }

    @UniComponentProp(name = Constant.Name.SHOW_LOCATION)
    public void showMyLocation(final boolean z) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.34
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (!z) {
                    wXMapView.getMap().setMyLocationEnabled(false);
                    wXMapView.getMap().setOnMyLocationChangeListener((AMap.OnMyLocationChangeListener) null);
                    return;
                }
                WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                if (wXAMapViewComponent.mMyLocationCallBack == null && wXAMapViewComponent.requestPermissions()) {
                    WXAMapViewComponent.this.mMyLocationCallBack = new AMap.OnMyLocationChangeListener() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.34.1
                        public void onMyLocationChange(Location location) {
                            WXAMapViewComponent wXAMapViewComponent2 = WXAMapViewComponent.this;
                            if (wXAMapViewComponent2.equalsLocation(wXAMapViewComponent2.mMyLocation, location)) {
                                return;
                            }
                            WXAMapViewComponent.this.mMyLocation = location;
                            WXAMapViewComponent.this.fireEventMapEvent(Constant.EVENT.BIND_USER_LOCATION_CHANGE, location);
                        }
                    };
                    wXMapView.getMap().setMyLocationEnabled(true);
                    wXMapView.getMap().setOnMyLocationChangeListener(WXAMapViewComponent.this.mMyLocationCallBack);
                    WXAMapViewComponent.this.myLocationStyle = new MyLocationStyle();
                    wXMapView.getMap().setMyLocationStyle(WXAMapViewComponent.this.myLocationStyle.myLocationType(5));
                }
            }
        });
    }

    @UniComponentProp(name = Constant.Name.SHOW_SCALE)
    public void showScale(final boolean z) throws Exception {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.15
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent.this.isShowScale = z;
                WXAMapViewComponent.this.mUiSettings.setScaleControlsEnabled(WXAMapViewComponent.this.isShowScale);
            }
        });
    }

    @UniJSMethod
    public void translateMarker(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.37
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mMarkerMgr != null) {
                    WXAMapViewComponent.this.mMarkerMgr.translateMarker(jSONObject, uniJSCallback);
                }
            }
        });
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void updateAttrs(Map<String, Object> map) {
        super.updateAttrs(map);
        if (map.containsKey(Constant.JSONKEY.LONGITUDE) || map.containsKey(Constant.JSONKEY.LATITUDE)) {
            updateCenter(getAttrs().get(Constant.JSONKEY.LATITUDE), getAttrs().get(Constant.JSONKEY.LONGITUDE));
        }
    }

    public void updateCenter(final Object obj, final Object obj2) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.10
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                wXAMapViewComponent.isSetUpdate = true;
                wXAMapViewComponent.setCenter(obj, obj2);
            }
        });
    }

    @UniJSMethod
    public void updateGroundOverlay(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.45
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                if (WXAMapViewComponent.this.mGroundMgr == null) {
                    WXAMapViewComponent wXAMapViewComponent = WXAMapViewComponent.this;
                    wXAMapViewComponent.mGroundMgr = new GroundMgr(wXAMapViewComponent.getInstance(), wXMapView);
                }
                WXAMapViewComponent.this.mGroundMgr.updateGroundOverlay(jSONObject, uniJSCallback);
            }
        });
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public FrameLayout initComponentHostView(Context context) {
        this.mMapContainer = new MapLayout(context);
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
        createMap();
        return this.mMapContainer;
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void setHostLayoutParams(FrameLayout frameLayout, int i, int i2, int i3, int i4, int i5, int i6) {
        super.setHostLayoutParams(frameLayout, i, i2, i3, i4, i5, i6);
        if (this.isMapLoaded.get() || this.isInited.get()) {
            return;
        }
        this.isInited.set(true);
        this.mMapContainer.postDelayed(new Runnable() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.3
            @Override // java.lang.Runnable
            public void run() {
                if (WXAMapViewComponent.this.getInstance() != null) {
                    if (WXAMapViewComponent.this.isShowAnimationEnd || WXAMapViewComponent.this.getInstance().isFrameViewShow()) {
                        WXAMapViewComponent.this.mMapView.setVisibility(0);
                    }
                }
            }
        }, 0L);
    }

    public void setincludePoints(final JSONArray jSONArray, final int i) {
        postTask(new MapOperationTask() { // from class: io.dcloud.feature.weex_amap.component.WXAMapViewComponent.35
            @Override // io.dcloud.feature.weex_amap.component.WXAMapViewComponent.MapOperationTask
            public void execute(WXMapView wXMapView) {
                ArrayList<LatLng> arrayListCrateLatLngs = MapResourceUtils.crateLatLngs(jSONArray);
                if (arrayListCrateLatLngs.size() > 0) {
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    Iterator<LatLng> it = arrayListCrateLatLngs.iterator();
                    while (it.hasNext()) {
                        builder.include(it.next());
                    }
                    wXMapView.getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), i));
                }
                WXAMapViewComponent.this.isSetUpdate = true;
            }
        });
    }
}
