package io.dcloud.js.map.amap.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.VisibleRegion;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.js.map.amap.IFMapDispose;
import io.dcloud.js.map.amap.JsMapManager;
import io.dcloud.js.map.amap.MapJsUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class DHMapView extends TextureMapView implements IFMapDispose, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.OnCameraChangeListener, AMap.OnMarkerDragListener, AMap.InfoWindowAdapter, AMap.OnMapClickListener, AMap.OnMapLongClickListener, AMapLocationListener {
    public static final String GET_USER_LOCATION_TEMPLATE = "{state:%s,point:%s}";
    public static final int MAPTYPE_NORMAL = 0;
    public static final int MAPTYPE_SATELLITE = 1;
    public static final int MAPTYPE_TRAFFIC = 1001;
    public static final int MAPTYPE_UNTRAFFIC = 1002;
    public static final String MAP_STATUS_CHANGE = "{ callbackType:'%s',center:{long:%f,lat:%f},northease:{long:%f,lat:%f},southwest:{long:%f,lat:%f},zoom:%f}";
    public static final String PLUS_MAPS_POINT_TEMPLATE = "new plus.maps.Point(%s,%s)";
    public static final String POINT_CLICK_TEMPLATE = "{callbackType:'%s',payload:new plus.maps.Point(%f, %f)}";
    public static final int SCAN_SPAN_TIME = 10000;
    public static final String T_GETBOUNDS = "{ northease:{longitude:%f,latitude:%f},southwest:{longitude:%f,latitude:%f}}";
    public static int aaaaaaaaaaa;
    public String flag;
    public boolean isLoctionReduction;
    public AMap mAMap;
    public boolean mAutoPopFromStack;
    public LocationSource.OnLocationChangedListener mChangedListener;
    public MapMarker mClickMapMarker;
    public AMapLocationClient mGULClient;
    public AMapLocationClientOption mLocationOption;
    public LocationSource mLocationSource;
    public ArrayList<String> mMapCallBackWebUuids;
    public ArrayList<MapCircleProxy> mMapCircleProxyList;
    public ArrayList<MapRoute> mMapRoutes;
    public ArrayList<MapMarker> mMarkersOverlay;
    public ArrayList<MapPolygonProxy> mPolygonProxiesList;
    public ArrayList<MapPolylineProxy> mPolylineOptionsList;
    public LinearLayout mRootView;
    public AMapLocationClient mSULClient;
    public String mUUID;
    public IWebview mWebView;
    public boolean show;
    public String tGetUserLocCallbackId;
    public IWebview tGetUserLocWebview;

    public DHMapView(Context context, IWebview iWebview, LatLng latLng, int i, int i2, boolean z, boolean z2, LinearLayout linearLayout) {
        super(context);
        this.mAutoPopFromStack = false;
        this.mAMap = null;
        this.mUUID = null;
        this.flag = "";
        this.show = false;
        this.mLocationOption = null;
        this.mLocationSource = new LocationSource() { // from class: io.dcloud.js.map.amap.adapter.DHMapView.3
            public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
                DHMapView dHMapView = DHMapView.this;
                dHMapView.mChangedListener = onLocationChangedListener;
                dHMapView.mSULClient = new AMapLocationClient(dHMapView.getContext().getApplicationContext());
                DHMapView.this.mLocationOption = new AMapLocationClientOption();
                DHMapView.this.mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                DHMapView dHMapView2 = DHMapView.this;
                dHMapView2.mSULClient.setLocationOption(dHMapView2.mLocationOption);
                DHMapView.this.mSULClient.setLocationListener(new AMapLocationListener() { // from class: io.dcloud.js.map.amap.adapter.DHMapView.3.1
                    @Override // com.amap.api.location.AMapLocationListener
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        if (PdrUtil.isEmpty(aMapLocation) || aMapLocation.getErrorCode() != 0) {
                            DHMapView dHMapView3 = DHMapView.this;
                            dHMapView3.disposeClientResource(dHMapView3.mSULClient);
                        } else {
                            LocationSource.OnLocationChangedListener onLocationChangedListener2 = DHMapView.this.mChangedListener;
                            if (onLocationChangedListener2 != null) {
                                onLocationChangedListener2.onLocationChanged(aMapLocation);
                            }
                        }
                    }
                });
                DHMapView.this.mSULClient.startLocation();
            }

            public void deactivate() {
                DHMapView dHMapView = DHMapView.this;
                if (dHMapView.mChangedListener != null) {
                    dHMapView.mChangedListener = null;
                }
                DHMapView dHMapView2 = DHMapView.this;
                dHMapView2.disposeClientResource(dHMapView2.mSULClient);
            }
        };
        this.tGetUserLocWebview = null;
        this.tGetUserLocCallbackId = null;
        this.isLoctionReduction = false;
        StringBuilder sb = new StringBuilder();
        sb.append("我是编号：");
        int i3 = aaaaaaaaaaa;
        aaaaaaaaaaa = i3 + 1;
        sb.append(i3);
        this.flag = sb.toString();
        this.mWebView = iWebview;
        this.mMapCallBackWebUuids = new ArrayList<>();
        this.mRootView = linearLayout;
        addMapCallBackWebUuid(iWebview.getWebviewUUID());
        onResume();
        initMap();
        if (latLng == null) {
            setZoom(i);
        } else {
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, i));
        }
        this.mAMap.setTrafficEnabled(z);
        this.mAMap.setMapType(i2);
        showZoomControls(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disposeClientResource(AMapLocationClient aMapLocationClient) {
        aMapLocationClient.stopLocation();
        aMapLocationClient.onDestroy();
    }

    private void execCallBack(String str) {
        ArrayList<String> arrayList = this.mMapCallBackWebUuids;
        if (arrayList != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                IWebview iWebviewFindWebviewByUuid = JsMapManager.getJsMapManager().findWebviewByUuid(this.mWebView, it.next());
                if (iWebviewFindWebviewByUuid != null) {
                    MapJsUtil.execCallback(iWebviewFindWebviewByUuid, this.mUUID, str);
                }
            }
        }
    }

    private MapMarker getMapMarker(Marker marker) {
        for (MapMarker mapMarker : this.mMarkersOverlay) {
            if (marker.equals(mapMarker.getMarker())) {
                return mapMarker;
            }
        }
        return null;
    }

    public static boolean isRightLocation(double d, double d2) {
        return (d == Double.MIN_VALUE || d2 == Double.MIN_VALUE) ? false : true;
    }

    public void addMapCallBackWebUuid(String str) {
        if (this.mMapCallBackWebUuids.contains(str)) {
            return;
        }
        this.mMapCallBackWebUuids.add(str);
    }

    public void addOverlay(Object obj) {
        if (obj instanceof MapMarker) {
            MapMarker mapMarker = (MapMarker) obj;
            this.mMarkersOverlay.add(mapMarker);
            mapMarker.checkPop();
            return;
        }
        if (obj instanceof MapPolylineProxy) {
            this.mPolylineOptionsList.add((MapPolylineProxy) obj);
            return;
        }
        if (obj instanceof MapPolygonProxy) {
            this.mPolygonProxiesList.add((MapPolygonProxy) obj);
        } else if (obj instanceof MapRoute) {
            this.mMapRoutes.add((MapRoute) obj);
        } else if (obj instanceof MapCircleProxy) {
            this.mMapCircleProxyList.add((MapCircleProxy) obj);
        }
    }

    public void clearMapCallBack() {
        ArrayList<String> arrayList = this.mMapCallBackWebUuids;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void clearOverlays() {
        this.mAMap.clear();
        ArrayList<MapPolygonProxy> arrayList = this.mPolygonProxiesList;
        if (arrayList != null) {
            arrayList.clear();
            this.mPolylineOptionsList.clear();
            this.mMarkersOverlay.clear();
            this.mMapRoutes.clear();
        }
        refreshDrawableState();
    }

    @Override // io.dcloud.js.map.amap.IFMapDispose
    public void close() {
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.show) {
            super.dispatchDraw(canvas);
        } else {
            this.show = true;
            postDelayed(new Runnable() { // from class: io.dcloud.js.map.amap.adapter.DHMapView.1
                @Override // java.lang.Runnable
                public void run() {
                    DHMapView.this.invalidate();
                }
            }, 1L);
        }
    }

    @Override // io.dcloud.js.map.amap.IFMapDispose
    public void dispose() {
        this.mAMap.clear();
        clearMapCallBack();
    }

    public String getBounds() {
        VisibleRegion visibleRegion = this.mAMap.getProjection().getVisibleRegion();
        return String.format(Locale.ENGLISH, T_GETBOUNDS, Double.valueOf(visibleRegion.farRight.longitude), Double.valueOf(visibleRegion.farRight.latitude), Double.valueOf(visibleRegion.nearLeft.longitude), Double.valueOf(visibleRegion.nearLeft.latitude));
    }

    public void getCurrentCenter(IWebview iWebview, String str) {
        LatLng latLng = this.mAMap.getCameraPosition().target;
        MapJsUtil.execCallback(iWebview, str, latLng != null ? StringUtil.format(GET_USER_LOCATION_TEMPLATE, 0, String.format(Locale.ENGLISH, PLUS_MAPS_POINT_TEMPLATE, Double.valueOf(latLng.longitude), Double.valueOf(latLng.latitude))) : StringUtil.format(GET_USER_LOCATION_TEMPLATE, -1, String.format(Locale.ENGLISH, PLUS_MAPS_POINT_TEMPLATE, 0, 0)));
    }

    public View getInfoContents(Marker marker) {
        return null;
    }

    public View getInfoWindow(Marker marker) {
        MapMarker mapMarker = getMapMarker(marker);
        this.mClickMapMarker = mapMarker;
        if (mapMarker == null) {
            return null;
        }
        if (!TextUtils.isEmpty(mapMarker.getLoadImageDataURL())) {
            ImageView imageView = new ImageView(getContext());
            this.mClickMapMarker.base64ToBitmap(imageView);
            return imageView;
        }
        if (TextUtils.isEmpty(this.mClickMapMarker.getLoadImage())) {
            return new PopViewLayout(getContext(), this.mClickMapMarker.getBubbleLabel(), this.mClickMapMarker.getPopIcon());
        }
        ImageView imageView2 = new ImageView(getContext());
        this.mClickMapMarker.loadImageBitmap(imageView2);
        return imageView2;
    }

    public void getUserLocation(IWebview iWebview, String str) {
        this.tGetUserLocWebview = iWebview;
        this.tGetUserLocCallbackId = str;
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        this.mLocationOption = aMapLocationClientOption;
        aMapLocationClientOption.setOnceLocation(true);
        this.mLocationOption.setOnceLocationLatest(true);
        AMapLocationClient aMapLocationClient = new AMapLocationClient(getContext().getApplicationContext());
        this.mGULClient = aMapLocationClient;
        aMapLocationClient.setLocationListener(this);
        this.mGULClient.startLocation();
    }

    public void initMap() {
        AMap map = getMap();
        this.mAMap = map;
        map.setOnMarkerClickListener(this);
        this.mAMap.setOnInfoWindowClickListener(this);
        this.mAMap.setOnCameraChangeListener(this);
        this.mAMap.setOnMarkerDragListener(this);
        this.mAMap.setInfoWindowAdapter(this);
        this.mAMap.setOnMapClickListener(this);
        this.mAMap.setOnMapLongClickListener(this);
        initMarkerOverlays();
        initUserLocationOverlay();
    }

    public void initMarkerOverlays() {
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(PlatformUtil.getResInputStream("res/point.png"));
        new BitmapDrawable(bitmapDecodeStream).setBounds(0, 0, bitmapDecodeStream.getWidth(), bitmapDecodeStream.getHeight());
        this.mMarkersOverlay = new ArrayList<>();
        this.mPolylineOptionsList = new ArrayList<>();
        this.mPolygonProxiesList = new ArrayList<>();
        this.mMapCircleProxyList = new ArrayList<>();
        this.mMapRoutes = new ArrayList<>();
    }

    public void initUserLocationOverlay() {
    }

    public void locationReStart() {
        if (this.isLoctionReduction) {
            showUserLocation(true);
            this.isLoctionReduction = false;
        }
    }

    public void locationStop() {
        if (this.mChangedListener != null) {
            showUserLocation(false);
            this.isLoctionReduction = true;
        }
    }

    public void onCameraChange(CameraPosition cameraPosition) {
    }

    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        try {
            VisibleRegion visibleRegion = this.mAMap.getProjection().getVisibleRegion();
            execCallBack(String.format(Locale.ENGLISH, MAP_STATUS_CHANGE, Constants.Event.CHANGE, Double.valueOf(cameraPosition.target.longitude), Double.valueOf(cameraPosition.target.latitude), Double.valueOf(visibleRegion.farRight.longitude), Double.valueOf(visibleRegion.farRight.latitude), Double.valueOf(visibleRegion.nearLeft.longitude), Double.valueOf(visibleRegion.nearLeft.latitude), Float.valueOf(cameraPosition.zoom)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onDetachedFromWindow() {
        if (this.mAutoPopFromStack) {
            return;
        }
        super.onDetachedFromWindow();
    }

    public void onInfoWindowClick(Marker marker) {
        marker.hideInfoWindow();
        MapMarker mapMarker = getMapMarker(marker);
        if (mapMarker != null) {
            MapJsUtil.execCallback(mapMarker.getWebview(), mapMarker.getUuid(), "{type:'bubbleclick'}");
        }
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        MapJsUtil.execCallback(this.tGetUserLocWebview, this.tGetUserLocCallbackId, PdrUtil.isEmpty(aMapLocation) ? StringUtil.format(DOMException.JSON_ERROR_INFO, 17, DOMException.toString(17, "geolocation", DOMException.MSG_GEOLOCATION_PROVIDER_ERROR, (String) null)) : String.format(GET_USER_LOCATION_TEMPLATE, Integer.valueOf(aMapLocation.getErrorCode()), String.format(Locale.ENGLISH, PLUS_MAPS_POINT_TEMPLATE, Double.valueOf(aMapLocation.getLongitude()), Double.valueOf(aMapLocation.getLatitude()))));
        disposeClientResource(this.mGULClient);
    }

    public void onMapClick(LatLng latLng) {
        MapMarker mapMarker = this.mClickMapMarker;
        if (mapMarker != null && mapMarker.getMarker().isInfoWindowShown()) {
            this.mClickMapMarker.getMarker().hideInfoWindow();
        }
        execCallBack(String.format(Locale.ENGLISH, POINT_CLICK_TEMPLATE, Constants.Event.CLICK, Double.valueOf(latLng.longitude), Double.valueOf(latLng.latitude)));
    }

    public void onMapLongClick(LatLng latLng) {
        execCallBack(String.format(Locale.ENGLISH, POINT_CLICK_TEMPLATE, Constants.Event.CLICK, Double.valueOf(latLng.longitude), Double.valueOf(latLng.latitude)));
    }

    public boolean onMarkerClick(Marker marker) {
        MapMarker mapMarker = getMapMarker(marker);
        this.mClickMapMarker = mapMarker;
        if (mapMarker == null) {
            return true;
        }
        if (!TextUtils.isEmpty(mapMarker.getBubbleLabel())) {
            if (this.mClickMapMarker.getMarker().isInfoWindowShown()) {
                this.mClickMapMarker.getMarker().hideInfoWindow();
            } else {
                this.mClickMapMarker.getMarker().showInfoWindow();
            }
        }
        MapJsUtil.execCallback(this.mClickMapMarker.getWebview(), this.mClickMapMarker.getUuid(), "{type:'markerclick'}");
        return true;
    }

    public void onMarkerDrag(Marker marker) {
    }

    public void onMarkerDragEnd(Marker marker) {
        MapMarker mapMarker = getMapMarker(marker);
        if (mapMarker != null) {
            LatLng position = marker.getPosition();
            MapJsUtil.execCallback(mapMarker.getWebview(), mapMarker.getUuid(), String.format(Locale.ENGLISH, "{type:'onDrag',pt:new plus.maps.Point(%f, %f)}", Double.valueOf(position.longitude), Double.valueOf(position.latitude)));
        }
    }

    public void onMarkerDragStart(Marker marker) {
    }

    public void removeOverlay(Object obj) {
        if (obj instanceof MapMarker) {
            MapMarker mapMarker = (MapMarker) obj;
            if (!this.mMarkersOverlay.contains(mapMarker) || mapMarker.getMarker() == null) {
                return;
            }
            mapMarker.getMarker().remove();
            this.mMarkersOverlay.remove(mapMarker);
            return;
        }
        if (obj instanceof MapPolylineProxy) {
            MapPolylineProxy mapPolylineProxy = (MapPolylineProxy) obj;
            if (mapPolylineProxy.getPolyline() != null) {
                mapPolylineProxy.getPolyline().remove();
                this.mPolylineOptionsList.remove(mapPolylineProxy);
                return;
            }
            return;
        }
        if (obj instanceof MapPolygonProxy) {
            MapPolygonProxy mapPolygonProxy = (MapPolygonProxy) obj;
            if (mapPolygonProxy.getPolygon() != null) {
                mapPolygonProxy.getPolygon().remove();
                this.mPolygonProxiesList.remove(mapPolygonProxy);
                return;
            }
            return;
        }
        if (obj instanceof MapRoute) {
            MapRoute mapRoute = (MapRoute) obj;
            mapRoute.removeFromMap();
            this.mMapRoutes.remove(mapRoute);
        } else if (obj instanceof MapCircleProxy) {
            MapCircleProxy mapCircleProxy = (MapCircleProxy) obj;
            if (mapCircleProxy.getCircle() != null) {
                mapCircleProxy.getCircle().remove();
                this.mMapCircleProxyList.remove(mapCircleProxy);
            }
        }
    }

    public void setCenter(MapPoint mapPoint) {
        this.mAMap.animateCamera(CameraUpdateFactory.changeLatLng(mapPoint.getLatLng()));
    }

    public void setCenterAndZoom(MapPoint mapPoint, int i) {
        this.mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mapPoint.getLatLng(), i));
    }

    public void setMapType(int i) {
        if (i == 0) {
            this.mAMap.setMapType(1);
            return;
        }
        if (i == 1) {
            this.mAMap.setMapType(2);
        } else if (i == 1001) {
            this.mAMap.setTrafficEnabled(true);
        } else {
            if (i != 1002) {
                return;
            }
            this.mAMap.setTrafficEnabled(false);
        }
    }

    public void setVisible(boolean z) {
        if (z) {
            this.mRootView.setVisibility(0);
            setVisibility(0);
        } else {
            this.mRootView.setVisibility(8);
            this.mRootView.post(new Runnable() { // from class: io.dcloud.js.map.amap.adapter.DHMapView.2
                @Override // java.lang.Runnable
                public void run() {
                    DHMapView.this.setVisibility(8);
                }
            });
        }
    }

    public void setZoom(int i) {
        this.mAMap.moveCamera(CameraUpdateFactory.zoomTo(i));
    }

    public void showUserLocation(boolean z) {
        if (!z) {
            this.mAMap.setMyLocationEnabled(false);
            return;
        }
        this.mAMap.setLocationSource(this.mLocationSource);
        this.mAMap.setMyLocationEnabled(true);
        this.mAMap.setMyLocationType(1);
    }

    public void showZoomControls(boolean z) {
        this.mAMap.getUiSettings().setZoomControlsEnabled(z);
    }
}
