package io.dcloud.js.map.amap.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.Doorway;
import com.amap.api.services.route.RailwayStationItem;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteRailwayItem;
import com.amap.api.services.route.TaxiItem;
import com.amap.api.services.route.WalkStep;
import io.dcloud.js.map.amap.util.AMapUtil;
import io.dcloud.js.map.amap.util.ChString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BusRouteOverlay extends RouteOverlay {
    public BusPath busPath;
    public LatLng latLng;

    public BusRouteOverlay(Context context, AMap aMap, BusPath busPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.busPath = busPath;
        this.startPoint = AMapUtil.convertToLatLng(latLonPoint);
        this.endPoint = AMapUtil.convertToLatLng(latLonPoint2);
        this.mAMap = aMap;
    }

    private void addBusLineSteps(RouteBusLineItem routeBusLineItem) {
        addBusLineSteps(routeBusLineItem.getPolyline());
    }

    private void addBusStationMarkers(RouteBusLineItem routeBusLineItem) {
        LatLng latLngConvertToLatLng = AMapUtil.convertToLatLng(routeBusLineItem.getDepartureBusStation().getLatLonPoint());
        String busLineName = routeBusLineItem.getBusLineName();
        addStationMarker(new MarkerOptions().position(latLngConvertToLatLng).title(busLineName).snippet(getBusSnippet(routeBusLineItem)).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getBusBitmapDescriptor()));
    }

    private void addRailwayMarkers(RouteRailwayItem routeRailwayItem) {
        LatLng latLngConvertToLatLng = AMapUtil.convertToLatLng(routeRailwayItem.getDeparturestop().getLocation());
        addStationMarker(new MarkerOptions().position(latLngConvertToLatLng).title(routeRailwayItem.getDeparturestop().getName() + ChString.GetOn).snippet(routeRailwayItem.getName()).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getBusBitmapDescriptor()));
        LatLng latLngConvertToLatLng2 = AMapUtil.convertToLatLng(routeRailwayItem.getArrivalstop().getLocation());
        addStationMarker(new MarkerOptions().position(latLngConvertToLatLng2).title(routeRailwayItem.getArrivalstop().getName() + ChString.GetOff).snippet(routeRailwayItem.getName()).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getBusBitmapDescriptor()));
    }

    private void addRailwayPolyline(List<LatLng> list) {
        addPolyLine(new PolylineOptions().addAll(list).color(getDriveColor()).width(getRouteWidth()));
    }

    private void addRailwayStep(RouteRailwayItem routeRailwayItem) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(routeRailwayItem.getDeparturestop());
        arrayList2.addAll(routeRailwayItem.getViastops());
        arrayList2.add(routeRailwayItem.getArrivalstop());
        for (int i = 0; i < arrayList2.size(); i++) {
            arrayList.add(AMapUtil.convertToLatLng(((RailwayStationItem) arrayList2.get(i)).getLocation()));
        }
        addRailwayPolyline(arrayList);
    }

    private void addTaxiMarkers(TaxiItem taxiItem) {
        addStationMarker(new MarkerOptions().position(AMapUtil.convertToLatLng(taxiItem.getOrigin())).title(taxiItem.getmSname() + "打车").snippet("到终点").anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getDriveBitmapDescriptor()));
    }

    private void addTaxiStep(TaxiItem taxiItem) {
        addPolyLine(new PolylineOptions().width(getRouteWidth()).color(getBusColor()).add(AMapUtil.convertToLatLng(taxiItem.getOrigin())).add(AMapUtil.convertToLatLng(taxiItem.getDestination())));
    }

    private void addWalkPolyLineByLatLonPoints(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        addWalkPolyline(AMapUtil.convertToLatLng(latLonPoint), AMapUtil.convertToLatLng(latLonPoint2));
    }

    private void addWalkPolyline(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(new LatLng[]{latLng, latLng2}).width(getRouteWidth()).color(getWalkColor()).setDottedLine(true));
    }

    private void addWalkStationMarkers(LatLng latLng, String str, String str2) {
        addStationMarker(new MarkerOptions().position(latLng).title(str).snippet(str2).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getWalkBitmapDescriptor()));
    }

    private void addWalkSteps(BusStep busStep) {
        List<WalkStep> steps = busStep.getWalk().getSteps();
        for (int i = 0; i < steps.size(); i++) {
            WalkStep walkStep = steps.get(i);
            if (i == 0) {
                addWalkStationMarkers(AMapUtil.convertToLatLng((LatLonPoint) walkStep.getPolyline().get(0)), walkStep.getRoad(), getWalkSnippet(steps));
            }
            ArrayList<LatLng> arrayListConvertArrList = AMapUtil.convertArrList(walkStep.getPolyline());
            this.latLng = arrayListConvertArrList.get(arrayListConvertArrList.size() - 1);
            addWalkPolyline(arrayListConvertArrList);
            if (i < steps.size() - 1) {
                LatLng latLng = arrayListConvertArrList.get(arrayListConvertArrList.size() - 1);
                LatLng latLngConvertToLatLng = AMapUtil.convertToLatLng((LatLonPoint) steps.get(i + 1).getPolyline().get(0));
                if (!latLng.equals(latLngConvertToLatLng)) {
                    addWalkPolyline(latLng, latLngConvertToLatLng);
                }
            }
        }
    }

    private void checkBusEndToNextBusStart(BusStep busStep, BusStep busStep2) {
        LatLng latLngConvertToLatLng = AMapUtil.convertToLatLng(getLastBuslinePoint(busStep));
        LatLng latLngConvertToLatLng2 = AMapUtil.convertToLatLng(getFirstBuslinePoint(busStep2));
        if (latLngConvertToLatLng.equals(latLngConvertToLatLng2)) {
            return;
        }
        drawLineArrow(latLngConvertToLatLng, latLngConvertToLatLng2);
    }

    private void checkBusLineToNextRailway(BusStep busStep, BusStep busStep2) {
        LatLonPoint lastBuslinePoint = getLastBuslinePoint(busStep);
        LatLonPoint location = busStep2.getRailway().getDeparturestop().getLocation();
        if (lastBuslinePoint.equals(location)) {
            return;
        }
        addWalkPolyLineByLatLonPoints(lastBuslinePoint, location);
    }

    private void checkBusLineToNextWalk(BusStep busStep, BusStep busStep2) {
        LatLonPoint lastBuslinePoint = getLastBuslinePoint(busStep);
        LatLonPoint firstWalkPoint = getFirstWalkPoint(busStep2);
        if (lastBuslinePoint.equals(firstWalkPoint)) {
            return;
        }
        addWalkPolyLineByLatLonPoints(lastBuslinePoint, firstWalkPoint);
    }

    private void checkBusToNextBusNoWalk(BusStep busStep, BusStep busStep2) {
        LatLng latLngConvertToLatLng = AMapUtil.convertToLatLng(getLastBuslinePoint(busStep));
        LatLng latLngConvertToLatLng2 = AMapUtil.convertToLatLng(getFirstBuslinePoint(busStep2));
        if (latLngConvertToLatLng2.latitude - latLngConvertToLatLng.latitude > 1.0E-4d || latLngConvertToLatLng2.longitude - latLngConvertToLatLng.longitude > 1.0E-4d) {
            drawLineArrow(latLngConvertToLatLng, latLngConvertToLatLng2);
        }
    }

    private void checkRailwayToNextRailway(BusStep busStep, BusStep busStep2) {
        LatLonPoint location = busStep.getRailway().getArrivalstop().getLocation();
        LatLonPoint location2 = busStep2.getRailway().getDeparturestop().getLocation();
        if (location.equals(location2)) {
            return;
        }
        addWalkPolyLineByLatLonPoints(location, location2);
    }

    private void checkRailwayToNextTaxi(BusStep busStep, BusStep busStep2) {
        LatLonPoint location = busStep.getRailway().getArrivalstop().getLocation();
        LatLonPoint origin = busStep2.getTaxi().getOrigin();
        if (location.equals(origin)) {
            return;
        }
        addWalkPolyLineByLatLonPoints(location, origin);
    }

    private void checkRailwayToNextWalk(BusStep busStep, BusStep busStep2) {
        LatLonPoint location = busStep.getRailway().getArrivalstop().getLocation();
        LatLonPoint firstWalkPoint = getFirstWalkPoint(busStep2);
        if (location.equals(firstWalkPoint)) {
            return;
        }
        addWalkPolyLineByLatLonPoints(location, firstWalkPoint);
    }

    private void checkWalkToBusline(BusStep busStep) {
        LatLonPoint lastWalkPoint = getLastWalkPoint(busStep);
        LatLonPoint firstBuslinePoint = getFirstBuslinePoint(busStep);
        if (lastWalkPoint.equals(firstBuslinePoint)) {
            return;
        }
        addWalkPolyLineByLatLonPoints(lastWalkPoint, firstBuslinePoint);
    }

    private String getBusSnippet(RouteBusLineItem routeBusLineItem) {
        return "(" + routeBusLineItem.getDepartureBusStation().getBusStationName() + "-->" + routeBusLineItem.getArrivalBusStation().getBusStationName() + ") 经过" + (routeBusLineItem.getPassStationNum() + 1) + ChString.Zhan;
    }

    private LatLonPoint getEntrancePoint(BusStep busStep) {
        Doorway entrance = busStep.getEntrance();
        if (entrance == null) {
            return null;
        }
        return entrance.getLatLonPoint();
    }

    private LatLonPoint getExitPoint(BusStep busStep) {
        Doorway exit = busStep.getExit();
        if (exit == null) {
            return null;
        }
        return exit.getLatLonPoint();
    }

    private LatLonPoint getFirstBuslinePoint(BusStep busStep) {
        return (LatLonPoint) busStep.getBusLine().getPolyline().get(0);
    }

    private LatLonPoint getFirstWalkPoint(BusStep busStep) {
        return (LatLonPoint) ((WalkStep) busStep.getWalk().getSteps().get(0)).getPolyline().get(0);
    }

    private LatLonPoint getLastBuslinePoint(BusStep busStep) {
        return (LatLonPoint) busStep.getBusLine().getPolyline().get(r2.size() - 1);
    }

    private LatLonPoint getLastWalkPoint(BusStep busStep) {
        return (LatLonPoint) ((WalkStep) busStep.getWalk().getSteps().get(r2.size() - 1)).getPolyline().get(r2.size() - 1);
    }

    private String getWalkSnippet(List<WalkStep> list) {
        Iterator<WalkStep> it = list.iterator();
        float distance = 0.0f;
        while (it.hasNext()) {
            distance += it.next().getDistance();
        }
        return ChString.ByFoot + distance + ChString.Meter;
    }

    public void addToMap() {
        try {
            List steps = this.busPath.getSteps();
            for (int i = 0; i < steps.size(); i++) {
                BusStep busStep = (BusStep) steps.get(i);
                if (i < steps.size() - 1) {
                    BusStep busStep2 = (BusStep) steps.get(i + 1);
                    if (busStep.getWalk() != null && busStep.getBusLine() != null) {
                        checkWalkToBusline(busStep);
                    }
                    if (busStep.getBusLine() != null && busStep2.getWalk() != null && busStep2.getWalk().getSteps().size() > 0) {
                        checkBusLineToNextWalk(busStep, busStep2);
                    }
                    if (busStep.getBusLine() != null && busStep2.getWalk() == null && busStep2.getBusLine() != null) {
                        checkBusEndToNextBusStart(busStep, busStep2);
                    }
                    if (busStep.getBusLine() != null && busStep2.getWalk() == null && busStep2.getBusLine() != null) {
                        checkBusToNextBusNoWalk(busStep, busStep2);
                    }
                    if (busStep.getBusLine() != null && busStep2.getRailway() != null) {
                        checkBusLineToNextRailway(busStep, busStep2);
                    }
                    if (busStep2.getWalk() != null && busStep2.getWalk().getSteps().size() > 0 && busStep.getRailway() != null) {
                        checkRailwayToNextWalk(busStep, busStep2);
                    }
                    if (busStep2.getRailway() != null && busStep.getRailway() != null) {
                        checkRailwayToNextRailway(busStep, busStep2);
                    }
                    if (busStep.getRailway() != null && busStep2.getTaxi() != null) {
                        checkRailwayToNextTaxi(busStep, busStep2);
                    }
                }
                if (busStep.getWalk() != null && busStep.getWalk().getSteps().size() > 0) {
                    addWalkSteps(busStep);
                } else if (busStep.getBusLine() == null && busStep.getRailway() == null && busStep.getTaxi() == null) {
                    addWalkPolyline(this.latLng, this.endPoint);
                }
                if (busStep.getBusLine() != null) {
                    RouteBusLineItem busLine = busStep.getBusLine();
                    addBusLineSteps(busLine);
                    addBusStationMarkers(busLine);
                    if (i == steps.size() - 1) {
                        addWalkPolyline(AMapUtil.convertToLatLng(getLastBuslinePoint(busStep)), this.endPoint);
                    }
                }
                if (busStep.getRailway() != null) {
                    addRailwayStep(busStep.getRailway());
                    addRailwayMarkers(busStep.getRailway());
                    if (i == steps.size() - 1) {
                        addWalkPolyline(AMapUtil.convertToLatLng(busStep.getRailway().getArrivalstop().getLocation()), this.endPoint);
                    }
                }
                if (busStep.getTaxi() != null) {
                    addTaxiStep(busStep.getTaxi());
                    addTaxiMarkers(busStep.getTaxi());
                }
            }
            addStartAndEndMarker();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void drawLineArrow(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(new LatLng[]{latLng, latLng2}).width(3.0f).color(getBusColor()).width(getRouteWidth()));
    }

    private void addBusLineSteps(List<LatLonPoint> list) {
        if (list.size() < 1) {
            return;
        }
        addPolyLine(new PolylineOptions().width(getRouteWidth()).color(getBusColor()).addAll(AMapUtil.convertArrList(list)));
    }

    private void addWalkPolyline(List<LatLng> list) {
        addPolyLine(new PolylineOptions().addAll(list).color(getWalkColor()).width(getRouteWidth()).setDottedLine(true));
    }
}
