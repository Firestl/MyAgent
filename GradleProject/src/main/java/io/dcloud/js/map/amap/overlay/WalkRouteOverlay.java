package io.dcloud.js.map.amap.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkStep;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WalkRouteOverlay extends RouteOverlay {
    public PolylineOptions mPolylineOptions;
    public WalkPath walkPath;
    public BitmapDescriptor walkStationDescriptor;

    public WalkRouteOverlay(Context context, AMap aMap, WalkPath walkPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.walkStationDescriptor = null;
        this.mAMap = aMap;
        this.walkPath = walkPath;
        this.startPoint = AMapServicesUtil.convertToLatLng(latLonPoint);
        this.endPoint = AMapServicesUtil.convertToLatLng(latLonPoint2);
    }

    private void addWalkPolyLine(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        addWalkPolyLine(AMapServicesUtil.convertToLatLng(latLonPoint), AMapServicesUtil.convertToLatLng(latLonPoint2));
    }

    private void addWalkPolyLines(WalkStep walkStep) {
        this.mPolylineOptions.addAll(AMapServicesUtil.convertArrList(walkStep.getPolyline()));
    }

    private void addWalkStationMarkers(WalkStep walkStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("方向:" + walkStep.getAction() + "\n道路:" + walkStep.getRoad()).snippet(walkStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(this.walkStationDescriptor));
    }

    private void checkDistanceToNextStep(WalkStep walkStep, WalkStep walkStep2) {
        LatLonPoint lastWalkPoint = getLastWalkPoint(walkStep);
        LatLonPoint firstWalkPoint = getFirstWalkPoint(walkStep2);
        if (lastWalkPoint.equals(firstWalkPoint)) {
            return;
        }
        addWalkPolyLine(lastWalkPoint, firstWalkPoint);
    }

    private LatLonPoint getFirstWalkPoint(WalkStep walkStep) {
        return (LatLonPoint) walkStep.getPolyline().get(0);
    }

    private LatLonPoint getLastWalkPoint(WalkStep walkStep) {
        return (LatLonPoint) walkStep.getPolyline().get(walkStep.getPolyline().size() - 1);
    }

    private void initPolylineOptions() {
        if (this.walkStationDescriptor == null) {
            this.walkStationDescriptor = getWalkBitmapDescriptor();
        }
        this.mPolylineOptions = null;
        PolylineOptions polylineOptions = new PolylineOptions();
        this.mPolylineOptions = polylineOptions;
        polylineOptions.color(getWalkColor()).width(getRouteWidth());
    }

    private void showPolyline() {
        addPolyLine(this.mPolylineOptions);
    }

    public void addToMap() {
        initPolylineOptions();
        try {
            List steps = this.walkPath.getSteps();
            this.mPolylineOptions.add(this.startPoint);
            for (int i = 0; i < steps.size(); i++) {
                WalkStep walkStep = (WalkStep) steps.get(i);
                addWalkStationMarkers(walkStep, AMapServicesUtil.convertToLatLng((LatLonPoint) walkStep.getPolyline().get(0)));
                addWalkPolyLines(walkStep);
            }
            this.mPolylineOptions.add(this.endPoint);
            addStartAndEndMarker();
            showPolyline();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void addWalkPolyLine(LatLng latLng, LatLng latLng2) {
        this.mPolylineOptions.add(new LatLng[]{latLng, latLng2});
    }
}
