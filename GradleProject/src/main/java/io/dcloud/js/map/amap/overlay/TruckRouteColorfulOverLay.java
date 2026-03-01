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
import com.amap.api.services.route.TMC;
import com.amap.api.services.route.TruckPath;
import com.amap.api.services.route.TruckStep;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import io.dcloud.js.map.amap.adapter.AMapR;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TruckRouteColorfulOverLay extends RouteOverlay {
    public boolean isColorfulline;
    public Context mContext;
    public List<LatLng> mLatLngsOfPath;
    public PolylineOptions mPolylineOptions;
    public float mWidth;
    public List<LatLonPoint> throughPointList;
    public List<Marker> throughPointMarkerList;
    public boolean throughPointMarkerVisible;
    public List<TMC> tmcs;
    public TruckPath truckPath;

    public TruckRouteColorfulOverLay(Context context, AMap aMap, TruckPath truckPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2, List<LatLonPoint> list) {
        super(context);
        this.throughPointMarkerList = new ArrayList();
        this.throughPointMarkerVisible = true;
        this.isColorfulline = true;
        this.mWidth = 17.0f;
        this.mContext = context;
        this.mAMap = aMap;
        this.truckPath = truckPath;
        this.startPoint = AMapUtil.convertToLatLng(latLonPoint);
        this.endPoint = AMapUtil.convertToLatLng(latLonPoint2);
        this.throughPointList = list;
    }

    private void addLimitMarker(TruckStep truckStep, LatLng latLng) {
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
        List<LatLng> list2;
        if (this.mAMap == null || (list2 = this.mLatLngsOfPath) == null || list2.size() <= 0 || list == null || list.size() <= 0) {
            return;
        }
        LatLng pointForDis = this.mLatLngsOfPath.get(0);
        ArrayList arrayList = new ArrayList();
        addPolyLine(new PolylineOptions().add(new LatLng[]{this.startPoint, pointForDis}).setDottedLine(true));
        PolylineOptions polylineOptions = new PolylineOptions();
        List<LatLng> list3 = this.mLatLngsOfPath;
        addPolyLine(polylineOptions.add(new LatLng[]{list3.get(list3.size() - 1), this.endPoint}).setDottedLine(true));
        int i = 0;
        int i2 = 0;
        double d = 0.0d;
        while (i < this.mLatLngsOfPath.size() && i2 < list.size()) {
            TMC tmc = list.get(i2);
            LatLng latLng = this.mLatLngsOfPath.get(i);
            double dCalculateDistance = calculateDistance(pointForDis, latLng);
            d += dCalculateDistance;
            int i3 = i2;
            if (d > tmc.getDistance() + 1) {
                pointForDis = getPointForDis(pointForDis, latLng, dCalculateDistance - (d - ((double) tmc.getDistance())));
                arrayList.add(pointForDis);
                i--;
            } else {
                arrayList.add(latLng);
                pointForDis = latLng;
            }
            if (d >= tmc.getDistance() || i == this.mLatLngsOfPath.size() - 1) {
                if (i3 == list.size() - 1 && i < this.mLatLngsOfPath.size() - 1) {
                    while (true) {
                        i++;
                        if (i >= this.mLatLngsOfPath.size()) {
                            break;
                        } else {
                            arrayList.add(this.mLatLngsOfPath.get(i));
                        }
                    }
                }
                i2 = i3 + 1;
                if (tmc.getStatus().equals("畅通")) {
                    addPolyLine(new PolylineOptions().addAll(arrayList).width(this.mWidth).color(-16711936));
                } else if (tmc.getStatus().equals("缓行")) {
                    addPolyLine(new PolylineOptions().addAll(arrayList).width(this.mWidth).color(DefaultImageHeaderParser.VP8_HEADER_MASK));
                } else if (tmc.getStatus().equals("拥堵")) {
                    addPolyLine(new PolylineOptions().addAll(arrayList).width(this.mWidth).color(-65536));
                } else if (tmc.getStatus().equals("严重拥堵")) {
                    addPolyLine(new PolylineOptions().addAll(arrayList).width(this.mWidth).color(Color.parseColor("#990033")));
                } else {
                    addPolyLine(new PolylineOptions().addAll(arrayList).width(this.mWidth).color(Color.parseColor("#537edc")));
                }
                arrayList.clear();
                arrayList.add(pointForDis);
                d = 0.0d;
            } else {
                i2 = i3;
            }
            if (i == this.mLatLngsOfPath.size() - 1) {
                addPolyLine(new PolylineOptions().add(new LatLng[]{latLng, this.endPoint}).setDottedLine(true));
            }
            i++;
        }
    }

    public static LatLng getPointForDis(LatLng latLng, LatLng latLng2, double d) {
        double dCalculateDistance = d / ((double) calculateDistance(latLng, latLng2));
        return new LatLng(((latLng2.latitude - latLng.latitude) * dCalculateDistance) + latLng.latitude, ((latLng2.longitude - latLng.longitude) * dCalculateDistance) + latLng.longitude);
    }

    private BitmapDescriptor getThroughPointBitDes() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_THROUGH);
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

    public void addToMap() {
        initPolylineOptions();
        try {
            if (this.mAMap != null && this.mWidth != 0.0f && this.truckPath != null) {
                this.mLatLngsOfPath = new ArrayList();
                this.tmcs = new ArrayList();
                List<TruckStep> steps = this.truckPath.getSteps();
                this.mPolylineOptions.add(this.startPoint);
                for (TruckStep truckStep : steps) {
                    List<LatLonPoint> polyline = truckStep.getPolyline();
                    this.tmcs.addAll(truckStep.getTMCs());
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
