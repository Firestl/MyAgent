package io.dcloud.js.map.amap.overlay;

import android.content.Context;
import android.graphics.Color;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.TMC;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import io.dcloud.js.map.amap.adapter.AMapR;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DrivingRouteOverlay extends RouteOverlay {
    public DrivePath drivePath;
    public boolean isColorfulline;
    public Context mContext;
    public List<LatLng> mLatLngsOfPath;
    public PolylineOptions mPolylineOptions;
    public PolylineOptions mPolylineOptionscolor;
    public float mWidth;
    public List<LatLonPoint> throughPointList;
    public List<Marker> throughPointMarkerList;
    public boolean throughPointMarkerVisible;
    public List<TMC> tmcs;

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2, List<LatLonPoint> list) {
        super(context);
        this.throughPointMarkerList = new ArrayList();
        this.throughPointMarkerVisible = true;
        this.isColorfulline = true;
        this.mWidth = 25.0f;
        this.mContext = context;
        this.mAMap = aMap;
        this.drivePath = drivePath;
        this.startPoint = AMapUtil.convertToLatLng(latLonPoint);
        this.endPoint = AMapUtil.convertToLatLng(latLonPoint2);
        this.throughPointList = list;
    }

    private void addDrivingStationMarkers(DriveStep driveStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("方向:" + driveStep.getAction() + "\n道路:" + driveStep.getRoad()).snippet(driveStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(getDriveBitmapDescriptor()));
    }

    private void addThroughPointMarker() {
        List<LatLonPoint> list = this.throughPointList;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.throughPointList.size(); i++) {
            LatLonPoint latLonPoint = this.throughPointList.get(i);
            if (latLonPoint != null) {
                this.throughPointMarkerList.add(this.mAMap.addMarker(new MarkerOptions().position(new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude())).visible(this.throughPointMarkerVisible).icon(getThroughPointBitDes()).title("途经点")));
            }
        }
    }

    public static int calculateDistance(LatLng latLng, LatLng latLng2) {
        return calculateDistance(latLng.longitude, latLng.latitude, latLng2.longitude, latLng2.latitude);
    }

    private void colorWayUpdate(List<TMC> list) {
        if (this.mAMap == null || list == null || list.size() <= 0) {
            return;
        }
        this.mPolylineOptionscolor = null;
        PolylineOptions polylineOptions = new PolylineOptions();
        this.mPolylineOptionscolor = polylineOptions;
        polylineOptions.width(getRouteWidth());
        ArrayList arrayList = new ArrayList();
        this.mPolylineOptionscolor.add(this.startPoint);
        this.mPolylineOptionscolor.add(AMapUtil.convertToLatLng((LatLonPoint) list.get(0).getPolyline().get(0)));
        arrayList.add(Integer.valueOf(getDriveColor()));
        for (int i = 0; i < list.size(); i++) {
            TMC tmc = list.get(i);
            int i2 = getcolor(tmc.getStatus());
            List polyline = tmc.getPolyline();
            for (int i3 = 1; i3 < polyline.size(); i3++) {
                this.mPolylineOptionscolor.add(AMapUtil.convertToLatLng((LatLonPoint) polyline.get(i3)));
                arrayList.add(Integer.valueOf(i2));
            }
        }
        this.mPolylineOptionscolor.add(this.endPoint);
        arrayList.add(Integer.valueOf(getDriveColor()));
        this.mPolylineOptionscolor.colorValues(arrayList);
    }

    public static LatLng getPointForDis(LatLng latLng, LatLng latLng2, double d) {
        double dCalculateDistance = d / ((double) calculateDistance(latLng, latLng2));
        return new LatLng(((latLng2.latitude - latLng.latitude) * dCalculateDistance) + latLng.latitude, ((latLng2.longitude - latLng.longitude) * dCalculateDistance) + latLng.longitude);
    }

    private BitmapDescriptor getThroughPointBitDes() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_THROUGH);
    }

    private int getcolor(String str) {
        if (str.equals("畅通")) {
            return -16711936;
        }
        if (str.equals("缓行")) {
            return DefaultImageHeaderParser.VP8_HEADER_MASK;
        }
        if (str.equals("拥堵")) {
            return -65536;
        }
        return str.equals("严重拥堵") ? Color.parseColor("#990033") : Color.parseColor("#537edc");
    }

    private void initPolylineOptions() {
        this.mPolylineOptions = null;
        PolylineOptions polylineOptions = new PolylineOptions();
        this.mPolylineOptions = polylineOptions;
        polylineOptions.color(getDriveColor()).width(getRouteWidth());
    }

    private void showPolyline() {
        addPolyLine(this.mPolylineOptions);
    }

    private void showcolorPolyline() {
        addPolyLine(this.mPolylineOptionscolor);
    }

    public void addToMap() {
        initPolylineOptions();
        try {
            if (this.mAMap != null && this.mWidth != 0.0f && this.drivePath != null) {
                this.mLatLngsOfPath = new ArrayList();
                this.tmcs = new ArrayList();
                List<DriveStep> steps = this.drivePath.getSteps();
                this.mPolylineOptions.add(this.startPoint);
                for (DriveStep driveStep : steps) {
                    List<LatLonPoint> polyline = driveStep.getPolyline();
                    this.tmcs.addAll(driveStep.getTMCs());
                    addDrivingStationMarkers(driveStep, convertToLatLng((LatLonPoint) polyline.get(0)));
                    for (LatLonPoint latLonPoint : polyline) {
                        this.mPolylineOptions.add(convertToLatLng(latLonPoint));
                        this.mLatLngsOfPath.add(convertToLatLng(latLonPoint));
                    }
                }
                this.mPolylineOptions.add(this.endPoint);
                if (this.startMarker != null) {
                    this.startMarker.remove();
                    this.startMarker = null;
                }
                if (this.endMarker != null) {
                    this.endMarker.remove();
                    this.endMarker = null;
                }
                addStartAndEndMarker();
                addThroughPointMarker();
                if (!this.isColorfulline || this.tmcs.size() <= 0) {
                    showPolyline();
                } else {
                    colorWayUpdate(this.tmcs);
                    showcolorPolyline();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    @Override // io.dcloud.js.map.amap.overlay.RouteOverlay
    public LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        List<LatLonPoint> list = this.throughPointList;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.throughPointList.size(); i++) {
                builder.include(new LatLng(this.throughPointList.get(i).getLatitude(), this.throughPointList.get(i).getLongitude()));
            }
        }
        return builder.build();
    }

    @Override // io.dcloud.js.map.amap.overlay.RouteOverlay
    public float getRouteWidth() {
        return this.mWidth;
    }

    @Override // io.dcloud.js.map.amap.overlay.RouteOverlay
    public void removeFromMap() {
        try {
            super.removeFromMap();
            if (this.throughPointMarkerList == null || this.throughPointMarkerList.size() <= 0) {
                return;
            }
            for (int i = 0; i < this.throughPointMarkerList.size(); i++) {
                this.throughPointMarkerList.get(i).remove();
            }
            this.throughPointMarkerList.clear();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setIsColorfulline(boolean z) {
        this.isColorfulline = z;
    }

    public void setRouteWidth(float f) {
        this.mWidth = f;
    }

    public void setThroughPointIconVisibility(boolean z) {
        try {
            this.throughPointMarkerVisible = z;
            if (this.throughPointMarkerList == null || this.throughPointMarkerList.size() <= 0) {
                return;
            }
            for (int i = 0; i < this.throughPointMarkerList.size(); i++) {
                this.throughPointMarkerList.get(i).setVisible(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int calculateDistance(double d, double d2, double d3, double d4) {
        double d5 = d * 0.01745329251994329d;
        double d6 = d2 * 0.01745329251994329d;
        double d7 = d3 * 0.01745329251994329d;
        double d8 = 0.01745329251994329d * d4;
        double dSin = Math.sin(d5);
        double dSin2 = Math.sin(d6);
        double dCos = Math.cos(d5);
        double dCos2 = Math.cos(d6);
        double dSin3 = Math.sin(d7);
        double dSin4 = Math.sin(d8);
        double dCos3 = Math.cos(d7);
        double dCos4 = Math.cos(d8);
        double[] dArr = {(dCos * dCos2) - (dCos3 * dCos4), (dCos2 * dSin) - (dCos4 * dSin3), dSin2 - dSin4};
        return (int) (Math.asin(Math.sqrt(((dArr[0] * dArr[0]) + (dArr[1] * dArr[1])) + (dArr[2] * dArr[2])) / 2.0d) * 1.27420015798544E7d);
    }
}
