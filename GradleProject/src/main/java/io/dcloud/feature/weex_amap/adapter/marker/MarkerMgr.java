package io.dcloud.feature.weex_amap.adapter.marker;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.Marker;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import io.dcloud.feature.weex_amap.adapter.MapAbsMgr;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class MarkerMgr extends MapAbsMgr {
    public ConcurrentHashMap<String, WXMarker> mMarkerCaches;
    public ConcurrentHashMap<String, WXMarker> mMarkerTemporary;

    public MarkerMgr(WXSDKInstance wXSDKInstance, WXMapView wXMapView) {
        super(wXSDKInstance, wXMapView);
        this.mMarkerCaches = new ConcurrentHashMap<>();
        this.mMarkerTemporary = new ConcurrentHashMap<>();
    }

    private void destroyMarkers(Map<String, WXMarker> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            WXMarker wXMarker = map.get(it.next());
            if (wXMarker != null) {
                wXMarker.destroy();
            }
        }
        map.clear();
    }

    private WXMarker getMarker(String str) {
        if (this.mMarkerTemporary.containsKey(str)) {
            return this.mMarkerTemporary.get(str);
        }
        if (this.mMarkerCaches.containsKey(str)) {
            return this.mMarkerCaches.get(str);
        }
        return null;
    }

    public void destroy() {
        ConcurrentHashMap<String, WXMarker> concurrentHashMap = this.mMarkerCaches;
        if (concurrentHashMap != null) {
            Iterator<String> it = concurrentHashMap.keySet().iterator();
            while (it.hasNext()) {
                WXMarker wXMarker = this.mMarkerCaches.get(it.next());
                if (wXMarker != null) {
                    wXMarker.destroy();
                }
            }
            this.mMarkerCaches.clear();
        }
    }

    public WXMarker getCalloutToWXMarker(Marker marker) {
        if (marker == null) {
            return null;
        }
        Iterator<String> it = this.mMarkerCaches.keySet().iterator();
        while (it.hasNext()) {
            WXMarker wXMarker = this.mMarkerCaches.get(it.next());
            if (wXMarker.getCallout() != null && marker.equals(wXMarker.getCallout().getInstance())) {
                return wXMarker;
            }
        }
        return null;
    }

    public WXMarker getLabelToWXMarker(Marker marker) {
        if (marker == null) {
            return null;
        }
        Iterator<String> it = this.mMarkerCaches.keySet().iterator();
        while (it.hasNext()) {
            WXMarker wXMarker = this.mMarkerCaches.get(it.next());
            if (wXMarker.getLabel() != null && marker.equals(wXMarker.getLabel().getInstance())) {
                return wXMarker;
            }
        }
        return null;
    }

    public WXMarker getWXMarker(Marker marker) {
        if (marker == null) {
            return null;
        }
        Iterator<String> it = this.mMarkerCaches.keySet().iterator();
        while (it.hasNext()) {
            WXMarker wXMarker = this.mMarkerCaches.get(it.next());
            if (marker.equals(wXMarker.getInstance())) {
                return wXMarker;
            }
        }
        WXMapView wXMapView = this.mMap;
        if (wXMapView == null || wXMapView.getClusterAddMarkers() == null) {
            return null;
        }
        for (IUniMarker iUniMarker : this.mMap.getClusterAddMarkers()) {
            if (iUniMarker instanceof WXMarker) {
                WXMarker wXMarker2 = (WXMarker) iUniMarker;
                if (marker.equals(wXMarker2.getInstance())) {
                    return wXMarker2;
                }
            }
        }
        return null;
    }

    public void hideMarkerCallout() {
        WXMarker wXMarker;
        try {
            if (this.mMarkerCaches != null) {
                Iterator<String> it = this.mMarkerCaches.keySet().iterator();
                while (it.hasNext()) {
                    WXMarker wXMarker2 = this.mMarkerCaches.get(it.next());
                    if (wXMarker2 != null) {
                        if (wXMarker2.getCallout() != null) {
                            if (!wXMarker2.getCallout().isAlwaysDisPlay()) {
                                wXMarker2.getCallout().setVisible(false);
                            }
                        } else if (wXMarker2.getInstance().isInfoWindowShown()) {
                            wXMarker2.getInstance().hideInfoWindow();
                        }
                    }
                }
            }
            if (this.mMap == null || this.mMap.getClusterAddMarkers() == null) {
                return;
            }
            for (IUniMarker iUniMarker : this.mMap.getClusterAddMarkers()) {
                if ((iUniMarker instanceof WXMarker) && (wXMarker = (WXMarker) iUniMarker) != null) {
                    if (wXMarker.getCallout() != null && !wXMarker.getCallout().isAlwaysDisPlay()) {
                        wXMarker.getCallout().setVisible(false);
                    } else if (wXMarker.getInstance().isInfoWindowShown()) {
                        wXMarker.getInstance().hideInfoWindow();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public void moveAlongMarker(JSONObject jSONObject, JSCallback jSCallback) {
        if (jSONObject == null) {
            if (jSCallback != null) {
                HashMap map = new HashMap();
                map.put("type", Constants.Event.FAIL);
                jSCallback.invokeAndKeepAlive(map);
                return;
            }
            return;
        }
        WXMarker marker = getMarker(jSONObject.getString("markerId"));
        HashMap map2 = new HashMap();
        if (marker != null) {
            marker.moveAlongMarker(this.mMap, jSONObject, jSCallback);
            return;
        }
        map2.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map2);
        }
    }

    public void removeMarkers(JSONArray jSONArray) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.size(); i++) {
                this.mMap.removeCluster(jSONArray);
                WXMarker marker = getMarker(jSONArray.getString(i));
                if (marker != null) {
                    marker.destroy();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void setMarkers(com.alibaba.fastjson.JSONArray r7, boolean r8) throws java.lang.Exception {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto Lb0
            int r0 = r7.size()     // Catch: java.lang.Throwable -> Lae
            if (r0 <= 0) goto Lb0
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r0 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            r0.clear()     // Catch: java.lang.Throwable -> Lae
            io.dcloud.feature.weex_amap.adapter.WXMapView r0 = r6.mMap     // Catch: java.lang.Throwable -> Lae
            r0.addCluster(r7)     // Catch: java.lang.Throwable -> Lae
            r0 = 0
        L14:
            int r1 = r7.size()     // Catch: java.lang.Throwable -> Lae
            if (r0 >= r1) goto L9f
            com.alibaba.fastjson.JSONObject r1 = r7.getJSONObject(r0)     // Catch: java.lang.Throwable -> Lae
            java.lang.String r2 = "joinCluster"
            boolean r2 = r1.getBooleanValue(r2)     // Catch: java.lang.Throwable -> Lae
            if (r2 != 0) goto L9b
            java.lang.String r2 = "clusterId"
            boolean r2 = r1.containsKey(r2)     // Catch: java.lang.Throwable -> Lae
            if (r2 == 0) goto L2f
            goto L9b
        L2f:
            int r2 = r1.hashCode()     // Catch: java.lang.Throwable -> Lae
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> Lae
            java.lang.String r3 = "id"
            boolean r3 = r1.containsKey(r3)     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L45
            java.lang.String r2 = "id"
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> Lae
        L45:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r3 = r6.mMarkerCaches     // Catch: java.lang.Throwable -> Lae
            boolean r3 = r3.containsKey(r2)     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L69
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r3 = r6.mMarkerCaches     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r3 = r3.remove(r2)     // Catch: java.lang.Throwable -> Lae
            io.dcloud.feature.weex_amap.adapter.marker.WXMarker r3 = (io.dcloud.feature.weex_amap.adapter.marker.WXMarker) r3     // Catch: java.lang.Throwable -> Lae
            r3.updateMarkerOptions(r1)     // Catch: java.lang.Throwable -> Lae
            com.taobao.weex.WXSDKInstance r4 = r6.mInstance     // Catch: java.lang.Throwable -> Lae
            android.content.Context r4 = r4.getContext()     // Catch: java.lang.Throwable -> Lae
            io.dcloud.feature.weex_amap.adapter.WXMapView r5 = r6.mMap     // Catch: java.lang.Throwable -> Lae
            r3.initCalloutAndLabel(r4, r5, r1)     // Catch: java.lang.Throwable -> Lae
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r1 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            r1.put(r2, r3)     // Catch: java.lang.Throwable -> Lae
            goto L9b
        L69:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r3 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            boolean r3 = r3.containsKey(r2)     // Catch: java.lang.Throwable -> Lae
            if (r3 == 0) goto L8d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r3 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r3 = r3.remove(r2)     // Catch: java.lang.Throwable -> Lae
            io.dcloud.feature.weex_amap.adapter.marker.WXMarker r3 = (io.dcloud.feature.weex_amap.adapter.marker.WXMarker) r3     // Catch: java.lang.Throwable -> Lae
            r3.updateMarkerOptions(r1)     // Catch: java.lang.Throwable -> Lae
            com.taobao.weex.WXSDKInstance r4 = r6.mInstance     // Catch: java.lang.Throwable -> Lae
            android.content.Context r4 = r4.getContext()     // Catch: java.lang.Throwable -> Lae
            io.dcloud.feature.weex_amap.adapter.WXMapView r5 = r6.mMap     // Catch: java.lang.Throwable -> Lae
            r3.initCalloutAndLabel(r4, r5, r1)     // Catch: java.lang.Throwable -> Lae
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r1 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            r1.put(r2, r3)     // Catch: java.lang.Throwable -> Lae
            goto L9b
        L8d:
            io.dcloud.feature.weex_amap.adapter.marker.WXMarker r3 = new io.dcloud.feature.weex_amap.adapter.marker.WXMarker     // Catch: java.lang.Throwable -> Lae
            com.taobao.weex.WXSDKInstance r4 = r6.mInstance     // Catch: java.lang.Throwable -> Lae
            io.dcloud.feature.weex_amap.adapter.WXMapView r5 = r6.mMap     // Catch: java.lang.Throwable -> Lae
            r3.<init>(r4, r5, r1, r2)     // Catch: java.lang.Throwable -> Lae
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r1 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            r1.put(r2, r3)     // Catch: java.lang.Throwable -> Lae
        L9b:
            int r0 = r0 + 1
            goto L14
        L9f:
            if (r8 == 0) goto La6
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r7 = r6.mMarkerCaches     // Catch: java.lang.Throwable -> Lae
            r6.destroyMarkers(r7)     // Catch: java.lang.Throwable -> Lae
        La6:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r7 = r6.mMarkerCaches     // Catch: java.lang.Throwable -> Lae
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r8 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            r7.putAll(r8)     // Catch: java.lang.Throwable -> Lae
            goto Lbf
        Lae:
            r7 = move-exception
            goto Lbd
        Lb0:
            if (r8 == 0) goto Lbf
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r7 = r6.mMarkerCaches     // Catch: java.lang.Throwable -> Lae
            r6.destroyMarkers(r7)     // Catch: java.lang.Throwable -> Lae
            java.util.concurrent.ConcurrentHashMap<java.lang.String, io.dcloud.feature.weex_amap.adapter.marker.WXMarker> r7 = r6.mMarkerTemporary     // Catch: java.lang.Throwable -> Lae
            r7.clear()     // Catch: java.lang.Throwable -> Lae
            goto Lbf
        Lbd:
            monitor-exit(r6)
            throw r7
        Lbf:
            monitor-exit(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex_amap.adapter.marker.MarkerMgr.setMarkers(com.alibaba.fastjson.JSONArray, boolean):void");
    }

    public void translateMarker(JSONObject jSONObject, JSCallback jSCallback) {
        if (jSONObject != null) {
            WXMarker marker = getMarker(jSONObject.getString("markerId"));
            if (marker != null) {
                marker.translateMarker(jSONObject, jSCallback);
                return;
            }
            return;
        }
        if (jSCallback != null) {
            HashMap map = new HashMap();
            map.put("type", Constants.Event.FAIL);
            jSCallback.invokeAndKeepAlive(map);
        }
    }
}
