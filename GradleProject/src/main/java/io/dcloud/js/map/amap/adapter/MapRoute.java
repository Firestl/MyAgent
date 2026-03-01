package io.dcloud.js.map.amap.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.WalkPath;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.js.map.amap.overlay.BusRouteOverlay;
import io.dcloud.js.map.amap.overlay.DrivingRouteOverlay;
import io.dcloud.js.map.amap.overlay.WalkRouteOverlay;

/* JADX INFO: loaded from: classes3.dex */
public class MapRoute {
    public MapPoint mEnd;
    public DHMapView mMapview;
    public Paint mPaint;
    public Object mRoute;
    public MapPoint mStart;
    public IWebview mWebview;
    public Object overlay;

    public class MapLine {
        public PolylineOptions options;

        public MapLine(MapView mapView, MapPoint mapPoint, MapPoint mapPoint2) {
            PolylineOptions polylineOptions = new PolylineOptions();
            this.options = polylineOptions;
            polylineOptions.add(new LatLng[]{mapPoint.getLatLng(), mapPoint2.getLatLng()});
            this.options.color(-65536);
            this.options.width(10.0f);
        }
    }

    public MapRoute() {
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(Color.rgb(54, 114, 227));
        this.mPaint.setAlpha(180);
        this.mPaint.setStrokeWidth(5.5f);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setAntiAlias(true);
    }

    public Object getRoute() {
        return this.mRoute;
    }

    public void initMapRoute(IWebview iWebview, DHMapView dHMapView) {
        this.mWebview = iWebview;
        this.mMapview = dHMapView;
        Object obj = this.mRoute;
        if (obj instanceof WalkPath) {
            WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(iWebview.getContext(), this.mMapview.getMap(), (WalkPath) obj, this.mStart.getLatLngPoint(), this.mEnd.getLatLngPoint());
            this.overlay = walkRouteOverlay;
            walkRouteOverlay.zoomToSpan();
            walkRouteOverlay.addToMap();
            return;
        }
        if (obj instanceof DrivePath) {
            DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(iWebview.getContext(), this.mMapview.getMap(), (DrivePath) obj, this.mStart.getLatLngPoint(), this.mEnd.getLatLngPoint(), null);
            this.overlay = drivingRouteOverlay;
            drivingRouteOverlay.zoomToSpan();
            drivingRouteOverlay.addToMap();
            return;
        }
        if (obj instanceof BusPath) {
            BusRouteOverlay busRouteOverlay = new BusRouteOverlay(iWebview.getContext(), this.mMapview.getMap(), (BusPath) obj, this.mStart.getLatLngPoint(), this.mEnd.getLatLngPoint());
            this.overlay = busRouteOverlay;
            busRouteOverlay.zoomToSpan();
            busRouteOverlay.addToMap();
        }
    }

    public void removeFromMap() {
        Object obj = this.mRoute;
        if (obj instanceof WalkPath) {
            ((WalkRouteOverlay) this.overlay).removeFromMap();
        } else if (obj instanceof DrivePath) {
            ((DrivingRouteOverlay) this.overlay).removeFromMap();
        } else if (obj instanceof BusPath) {
            ((BusRouteOverlay) this.overlay).removeFromMap();
        }
    }

    public void setRoute(MapPoint mapPoint, MapPoint mapPoint2) {
        this.mStart = mapPoint;
        this.mEnd = mapPoint2;
    }

    public void setRoute(Object obj) {
        this.mRoute = obj;
    }
}
