package io.dcloud.js.map.amap.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideStep;
import io.dcloud.js.map.amap.adapter.AMapR;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RideRouteOverlay extends RouteOverlay {
    public PolylineOptions mPolylineOptions;
    public RidePath ridePath;
    public BitmapDescriptor walkStationDescriptor;

    public RideRouteOverlay(Context context, AMap aMap, RidePath ridePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.walkStationDescriptor = null;
        this.mAMap = aMap;
        this.ridePath = ridePath;
        this.startPoint = AMapUtil.convertToLatLng(latLonPoint);
        this.endPoint = AMapUtil.convertToLatLng(latLonPoint2);
    }

    private void addRidePolyLines(RideStep rideStep) {
        this.mPolylineOptions.addAll(AMapUtil.convertArrList(rideStep.getPolyline()));
    }

    private void addRideStationMarkers(RideStep rideStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("方向:" + rideStep.getAction() + "\n道路:" + rideStep.getRoad()).snippet(rideStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(this.walkStationDescriptor));
    }

    private void initPolylineOptions() {
        if (this.walkStationDescriptor == null) {
            this.walkStationDescriptor = BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_RIDE);
        }
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
            List steps = this.ridePath.getSteps();
            this.mPolylineOptions.add(this.startPoint);
            for (int i = 0; i < steps.size(); i++) {
                RideStep rideStep = (RideStep) steps.get(i);
                addRideStationMarkers(rideStep, AMapUtil.convertToLatLng((LatLonPoint) rideStep.getPolyline().get(0)));
                addRidePolyLines(rideStep);
            }
            this.mPolylineOptions.add(this.endPoint);
            addStartAndEndMarker();
            showPolyline();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
