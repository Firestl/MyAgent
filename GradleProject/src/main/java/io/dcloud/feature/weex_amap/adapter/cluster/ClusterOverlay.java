package io.dcloud.feature.weex_amap.adapter.cluster;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.animation.AlphaAnimation;
import com.amap.api.maps.model.animation.Animation;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import io.dcloud.feature.weex_amap.adapter.marker.IUniMarker;
import io.dcloud.feature.weex_amap.adapter.marker.WXMarker;
import io.dcloud.feature.weex_amap.ui.ArrowTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ClusterOverlay implements AMap.OnCameraChangeListener, AMap.OnMarkerClickListener {
    public boolean isEnableDefaultStyle;
    public boolean isZoomOnClick;
    public AlphaAnimation mADDAnimation;
    public List<IUniMarker> mAddMarkers;
    public OnClusterClickListener mClusterClickListener;
    public OnClusterCreateListener mClusterCreateListener;
    public double mClusterDistance;
    public LinkedHashMap<String, ClusterItem> mClusterItems;
    public int mClusterSize;
    public LinkedHashMap<String, Cluster> mClusters;
    public Context mContext;
    public int mDynamicID;
    public UniSDKInstance mInstance;
    public boolean mIsCanceled;
    public LruCache<Integer, BitmapDescriptor> mLruCache;
    public WXMapView mMap;
    public HandlerThread mMarkerHandlerThread;
    public Handler mMarkerhandler;
    public float mPXInMeters;
    public Handler mSignClusterHandler;
    public HandlerThread mSignClusterThread;

    public class MarkerHandler extends Handler {
        public static final int ADD_CLUSTER_LIST = 0;
        public static final int ADD_SINGLE_CLUSTER = 1;
        public static final int UPDATE_SINGLE_CLUSTER = 2;
        public static final int UPDATE_SINGLE_CLUSTER_RENDER = 3;

        public MarkerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ClusterOverlay.this.addClusterToMap((LinkedHashMap) message.obj);
                return;
            }
            if (i == 1) {
                ClusterOverlay.this.addSingleClusterToMap((Cluster) message.obj);
                return;
            }
            if (i == 2) {
                Cluster cluster = (Cluster) message.obj;
                ClusterOverlay.this.updateCluster(cluster);
                ClusterOverlay.this.triggerCreateListener(cluster);
            } else {
                if (i != 3) {
                    return;
                }
                Cluster cluster2 = (Cluster) message.obj;
                ClusterOverlay.this.mAddMarkers.remove(cluster2.getMarker());
                cluster2.updateClusterForRender(ClusterOverlay.this.mMap, ClusterOverlay.this.mInstance);
                ClusterOverlay.this.mAddMarkers.add(cluster2.getMarker());
            }
        }
    }

    public class MyAnimationListener implements Animation.AnimationListener {
        public List<IUniMarker> mRemoveMarkers;

        public MyAnimationListener(List<IUniMarker> list) {
            this.mRemoveMarkers = list;
        }

        public void onAnimationEnd() {
            Iterator<IUniMarker> it = this.mRemoveMarkers.iterator();
            while (it.hasNext()) {
                it.next().destroy();
            }
            this.mRemoveMarkers.clear();
        }

        public void onAnimationStart() {
        }
    }

    public class SignClusterHandler extends Handler {
        public static final int CALCULATE_CLUSTER = 0;
        public static final int CALCULATE_REMOVE_SINGLE_CLUSTER = 2;
        public static final int CALCULATE_SINGLE_CLUSTER = 1;

        public SignClusterHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ClusterOverlay.this.calculateClusters();
                return;
            }
            int i2 = 0;
            if (i == 1) {
                ArrayList arrayList = null;
                Object obj = message.obj;
                if (obj instanceof ArrayList) {
                    arrayList = (ArrayList) obj;
                    while (i2 < arrayList.size()) {
                        ClusterItem clusterItem = (ClusterItem) arrayList.get(i2);
                        ClusterOverlay.this.mClusterItems.put(clusterItem.getId(), clusterItem);
                        i2++;
                    }
                } else if (obj instanceof ClusterItem) {
                    ClusterItem clusterItem2 = (ClusterItem) obj;
                    ClusterOverlay.this.mClusterItems.put(clusterItem2.getId(), clusterItem2);
                    arrayList = new ArrayList();
                    arrayList.add(clusterItem2);
                }
                if (arrayList != null) {
                    ClusterOverlay.this.calculateSingleCluster(arrayList);
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            Object obj2 = message.obj;
            if (!(obj2 instanceof JSONArray)) {
                if (obj2 instanceof ClusterItem) {
                    ClusterItem clusterItem3 = (ClusterItem) obj2;
                    ClusterOverlay.this.mClusterItems.remove(clusterItem3);
                    ClusterOverlay.this.calculateRemoveSingleCluster(clusterItem3);
                    return;
                }
                return;
            }
            JSONArray jSONArray = (JSONArray) obj2;
            ArrayList arrayList2 = new ArrayList();
            while (i2 < jSONArray.size()) {
                ClusterItem clusterItem4 = (ClusterItem) ClusterOverlay.this.mClusterItems.remove(jSONArray.getString(i2));
                if (clusterItem4 != null) {
                    arrayList2.add(clusterItem4);
                }
                i2++;
            }
            if (arrayList2.size() > 0) {
                ClusterOverlay.this.calculateRemoveSingleClusters(arrayList2);
            }
        }
    }

    public ClusterOverlay(WXMapView wXMapView, UniSDKInstance uniSDKInstance, int i, boolean z, boolean z2, Context context) {
        this(wXMapView, uniSDKInstance, null, i, z, z2, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addClusterToMap(LinkedHashMap<String, Cluster> linkedHashMap) {
        if (linkedHashMap.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mAddMarkers);
        ArrayList arrayList2 = new ArrayList();
        for (Cluster cluster : linkedHashMap.values()) {
            if (cluster.isMultiplexing()) {
                if (cluster.getClusterCount() > 1 && cluster.isContUpdate()) {
                    arrayList2.add(cluster);
                }
                arrayList.remove(cluster.getMarker());
            } else if (cluster.getClusterCount() > 1) {
                arrayList2.add(cluster);
            }
        }
        if (arrayList2.size() > 0) {
            triggerCreateListener(arrayList2);
        }
        arrayList2.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((IUniMarker) it.next()).destroy();
        }
        for (Cluster cluster2 : linkedHashMap.values()) {
            if (cluster2.isMultiplexing()) {
                int clusterCount = cluster2.getClusterCount();
                if (cluster2.getMarker() instanceof ClusterMarker) {
                    ClusterMarker clusterMarker = (ClusterMarker) cluster2.getMarker();
                    if (clusterCount == 1) {
                        cluster2.setMarker(null);
                        clusterMarker.destroy();
                        addSingleClusterToMap(cluster2);
                    } else if (clusterMarker.getMarkerCount() != cluster2.getClusterCount()) {
                        triggerCreateListener(cluster2);
                        clusterMarker.updateIcon(getBitmapDes(cluster2), cluster2.getClusterCount());
                    }
                } else if (clusterCount > 1) {
                    cluster2.getMarker().destroy();
                    addSingleClusterToMap(cluster2);
                }
            } else {
                addSingleClusterToMap(cluster2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSingleClusterToMap(Cluster cluster) {
        IUniMarker clusterMarker;
        ClusterItem clusterItem;
        LatLng centerLatLng = cluster.getCenterLatLng();
        if (cluster.getClusterCount() != 1 || (clusterItem = cluster.getClusterItem(0)) == null || clusterItem.getData() == null) {
            clusterMarker = null;
        } else {
            clusterMarker = new WXMarker(this.mMap.getUniSDKInstance(), this.mMap, clusterItem.getData(), clusterItem.getData().containsKey("id") ? clusterItem.getData().getString("id") : String.valueOf(clusterItem.hashCode()));
        }
        if (clusterMarker == null) {
            clusterMarker = new ClusterMarker(this.mMap, getBitmapDes(cluster), centerLatLng, cluster.getClusterCount());
        }
        clusterMarker.getInstance().setAnimation(this.mADDAnimation);
        clusterMarker.getInstance().setObject(cluster);
        clusterMarker.getInstance().startAnimation();
        cluster.setMarker(clusterMarker);
        this.mAddMarkers.add(clusterMarker);
    }

    private void assignClusters() {
        this.mIsCanceled = true;
        this.mSignClusterHandler.removeMessages(0);
        this.mSignClusterHandler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateClusters() {
        this.mIsCanceled = false;
        LinkedHashMap<String, Cluster> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(this.mClusters);
        for (Cluster cluster : linkedHashMap.values()) {
            if (cluster != null) {
                cluster.clearClusterItems();
            }
        }
        this.mClusters.clear();
        LinkedHashMap<String, Cluster> linkedHashMap2 = new LinkedHashMap<>();
        LatLngBounds latLngBounds = this.mMap.getMap().getProjection().getVisibleRegion().latLngBounds;
        for (ClusterItem clusterItem : this.mClusterItems.values()) {
            if (this.mIsCanceled) {
                return;
            }
            LatLng position = clusterItem.getPosition();
            if (latLngBounds.contains(position)) {
                Cluster cluster2 = getCluster(position, linkedHashMap);
                if (cluster2 != null) {
                    linkedHashMap2.put(cluster2.getId(), cluster2);
                    cluster2.addClusterItem(clusterItem);
                    cluster2.setMultiplexing(true);
                } else {
                    Cluster cluster3 = getCluster(position, linkedHashMap2);
                    if (cluster3 == null) {
                        cluster3 = new Cluster(getClusterId(clusterItem), position);
                    }
                    linkedHashMap2.put(cluster3.getId(), cluster3);
                    cluster3.addClusterItem(clusterItem);
                }
            }
        }
        this.mClusters.putAll(linkedHashMap2);
        linkedHashMap.clear();
        Message messageObtain = Message.obtain();
        messageObtain.what = 0;
        messageObtain.obj = linkedHashMap2;
        if (this.mIsCanceled) {
            return;
        }
        this.mMarkerhandler.sendMessage(messageObtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateRemoveSingleCluster(ClusterItem clusterItem) {
        Cluster cluster;
        LatLngBounds latLngBounds = this.mMap.getMap().getProjection().getVisibleRegion().latLngBounds;
        LatLng position = clusterItem.getPosition();
        if (latLngBounds.contains(position) && (cluster = getCluster(position, this.mClusters)) != null) {
            if (cluster.getClusterCount() == 1) {
                cluster.getMarker().destroy();
                return;
            }
            cluster.removeClusterItem(clusterItem);
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            messageObtain.obj = cluster;
            this.mMarkerhandler.removeMessages(2);
            this.mMarkerhandler.sendMessageDelayed(messageObtain, 5L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateRemoveSingleClusters(ArrayList<ClusterItem> arrayList) {
        Iterator<ClusterItem> it = arrayList.iterator();
        while (it.hasNext()) {
            calculateRemoveSingleCluster(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateSingleCluster(ArrayList<ClusterItem> arrayList) {
        LatLngBounds latLngBounds = this.mMap.getMap().getProjection().getVisibleRegion().latLngBounds;
        for (ClusterItem clusterItem : arrayList) {
            if (latLngBounds.contains(clusterItem.getPosition())) {
                this.mClusterItems.put(clusterItem.getId(), clusterItem);
            }
        }
        this.mSignClusterHandler.removeMessages(0);
        this.mSignClusterHandler.sendEmptyMessage(0);
    }

    private BitmapDescriptor getBitmapDes(Cluster cluster) {
        int clusterCount = cluster.getClusterCount();
        BitmapDescriptor bitmapDescriptor = this.mLruCache.get(Integer.valueOf(clusterCount));
        if (bitmapDescriptor != null) {
            return bitmapDescriptor;
        }
        ArrowTextView arrowTextView = new ArrowTextView(this.mContext, false);
        if (clusterCount > 1) {
            arrowTextView.setText(String.valueOf(clusterCount));
        }
        int iDip2px = WXViewUtils.dip2px(50.0f);
        arrowTextView.setRadius(iDip2px / 2);
        arrowTextView.setGravity(17);
        arrowTextView.setTextColor(-1);
        arrowTextView.setIncludeFontPadding(false);
        arrowTextView.setTextSize(2, 15.0f);
        arrowTextView.setBgColor(-16776961);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iDip2px, iDip2px);
        layoutParams.setMargins(3, 3, 3, 3);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.addView(arrowTextView, layoutParams);
        BitmapDescriptor bitmapDescriptorFromView = BitmapDescriptorFactory.fromView(linearLayout);
        this.mLruCache.put(Integer.valueOf(clusterCount), bitmapDescriptorFromView);
        return bitmapDescriptorFromView;
    }

    private Cluster getCluster(LatLng latLng, LinkedHashMap<String, Cluster> linkedHashMap) {
        for (Cluster cluster : linkedHashMap.values()) {
            if (AMapUtils.calculateLineDistance(latLng, cluster.getCenterLatLng()) < this.mClusterDistance && this.mMap.getMap().getCameraPosition().zoom < 21.0f) {
                return cluster;
            }
        }
        return null;
    }

    private String getClusterId(ClusterItem clusterItem) {
        if (clusterItem.getData() != null && clusterItem.getData().containsKey("clusterId")) {
            return clusterItem.getData().getString("clusterId");
        }
        int i = this.mDynamicID + 1;
        this.mDynamicID = i;
        return String.valueOf(i);
    }

    private void initThreadHandler() {
        this.mMarkerHandlerThread.start();
        this.mSignClusterThread.start();
        this.mMarkerhandler = new MarkerHandler(this.mMarkerHandlerThread.getLooper());
        this.mSignClusterHandler = new SignClusterHandler(this.mSignClusterThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reycleBitmap(Bitmap bitmap) {
        if (bitmap == null || Build.VERSION.SDK_INT > 10 || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void triggerCreateListener(Object obj) {
        if (!this.isEnableDefaultStyle && this.mClusterCreateListener != null) {
            List<Cluster> arrayList = null;
            if (obj instanceof Cluster) {
                arrayList = new ArrayList<>();
                arrayList.add((Cluster) obj);
            } else if (obj instanceof ArrayList) {
                arrayList = (List) obj;
            }
            if (arrayList != null) {
                this.mClusterCreateListener.onClusterCreate(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCluster(Cluster cluster) {
        IUniMarker marker = cluster.getMarker();
        if (marker != null) {
            if (marker instanceof ClusterMarker) {
                ((ClusterMarker) marker).updateIcon(getBitmapDes(cluster), cluster.getClusterCount());
                return;
            }
            if (marker instanceof WXMarker) {
                if (marker != null) {
                    marker.destroy();
                    this.mAddMarkers.remove(marker);
                }
                ClusterMarker clusterMarker = new ClusterMarker(this.mMap, getBitmapDes(cluster), cluster.getCenterLatLng(), cluster.getClusterCount());
                cluster.setMarker(clusterMarker);
                this.mAddMarkers.add(clusterMarker);
            }
        }
    }

    public void addClusterItem(ClusterItem clusterItem) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = clusterItem;
        this.mSignClusterHandler.sendMessage(messageObtain);
    }

    public void addClusterItems(ArrayList<ClusterItem> arrayList) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = arrayList;
        this.mSignClusterHandler.sendMessage(messageObtain);
    }

    public List<IUniMarker> getClusterAddMarkers() {
        return this.mAddMarkers;
    }

    public JSONObject getClusterInfo(Cluster cluster) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("clusterId", (Object) cluster.getId());
        jSONObject.put("center", (Object) MapResourceUtils.createJsLatLng(cluster.getCenterLatLng()));
        jSONObject.put("markerIds", (Object) cluster.getMarkerIds());
        return jSONObject;
    }

    public void onCameraChange(CameraPosition cameraPosition) {
    }

    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        this.mPXInMeters = this.mMap.getMap().getScalePerPixel();
        this.mClusterDistance = r3 * this.mClusterSize;
        assignClusters();
    }

    public void onDestroy() {
        this.mIsCanceled = true;
        this.mSignClusterHandler.removeCallbacksAndMessages(null);
        this.mMarkerhandler.removeCallbacksAndMessages(null);
        this.mSignClusterThread.quit();
        this.mMarkerHandlerThread.quit();
        Iterator<IUniMarker> it = this.mAddMarkers.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        this.mDynamicID = 100;
        this.mAddMarkers.clear();
        this.mLruCache.evictAll();
    }

    public boolean onMarkerClick(Marker marker) {
        Cluster cluster = null;
        if (marker.getObject() == null) {
            Iterator<Cluster> it = this.mClusters.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Cluster next = it.next();
                if (MapResourceUtils.equalsLatLng(marker.getPosition(), next.getCenterLatLng())) {
                    cluster = next;
                    break;
                }
            }
        } else if (marker.getObject() instanceof Cluster) {
            cluster = (Cluster) marker.getObject();
        }
        if (cluster != null && (cluster.getMarker() instanceof ClusterMarker)) {
            if (this.isZoomOnClick && cluster.getClusterCount() > 1) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                Iterator<ClusterItem> it2 = cluster.getClusterItems().iterator();
                while (it2.hasNext()) {
                    builder.include(it2.next().getPosition());
                }
                this.mMap.getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 50));
            }
            OnClusterClickListener onClusterClickListener = this.mClusterClickListener;
            if (onClusterClickListener != null && cluster != null) {
                onClusterClickListener.onClick(marker, cluster);
                return true;
            }
        }
        return false;
    }

    public void removeClusterItem(ClusterItem clusterItem) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = clusterItem;
        this.mSignClusterHandler.sendMessage(messageObtain);
    }

    public void removeClusterItems(JSONArray jSONArray) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = jSONArray;
        this.mSignClusterHandler.sendMessage(messageObtain);
    }

    public void setClusterCreateListener(OnClusterCreateListener onClusterCreateListener) {
        this.mClusterCreateListener = onClusterCreateListener;
    }

    public void setClusterRenderer(ClusterRender clusterRender) {
        if (this.mClusters.containsKey(clusterRender.getClusterId())) {
            Cluster cluster = this.mClusters.get(clusterRender.getClusterId());
            cluster.setClusterRender(clusterRender);
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.obj = cluster;
            this.mMarkerhandler.sendMessageDelayed(messageObtain, 5L);
        }
    }

    public void setOnClusterClickListener(OnClusterClickListener onClusterClickListener) {
        this.mClusterClickListener = onClusterClickListener;
    }

    public void updateClusterOverlay(int i, boolean z, boolean z2) {
        this.mClusterSize = i;
        this.mPXInMeters = this.mMap.getMap().getScalePerPixel();
        this.mClusterDistance = r3 * this.mClusterSize;
        this.isEnableDefaultStyle = z;
        this.isZoomOnClick = z2;
    }

    public ClusterOverlay(WXMapView wXMapView, UniSDKInstance uniSDKInstance, LinkedHashMap<String, ClusterItem> linkedHashMap, int i, boolean z, boolean z2, Context context) {
        this.mAddMarkers = Collections.synchronizedList(new ArrayList());
        this.mMarkerHandlerThread = new HandlerThread("addMarker");
        this.mSignClusterThread = new HandlerThread("calculateCluster");
        this.mIsCanceled = false;
        this.mDynamicID = 100;
        this.isEnableDefaultStyle = true;
        this.isZoomOnClick = true;
        this.mADDAnimation = new AlphaAnimation(0.0f, 1.0f);
        this.mLruCache = new LruCache<Integer, BitmapDescriptor>(80) { // from class: io.dcloud.feature.weex_amap.adapter.cluster.ClusterOverlay.1
            @Override // android.util.LruCache
            public void entryRemoved(boolean z3, Integer num, BitmapDescriptor bitmapDescriptor, BitmapDescriptor bitmapDescriptor2) {
                ClusterOverlay.this.reycleBitmap(bitmapDescriptor.getBitmap());
            }
        };
        this.mInstance = uniSDKInstance;
        if (linkedHashMap != null) {
            this.mClusterItems = linkedHashMap;
        } else {
            this.mClusterItems = new LinkedHashMap<>();
        }
        this.mContext = context;
        this.mClusters = new LinkedHashMap<>();
        this.mMap = wXMapView;
        this.isEnableDefaultStyle = z;
        this.isZoomOnClick = z2;
        this.mClusterSize = i;
        this.mPXInMeters = wXMapView.getMap().getScalePerPixel();
        this.mClusterDistance = r4 * this.mClusterSize;
        this.mMap.getMap().addOnCameraChangeListener(this);
        this.mMap.getMap().addOnMarkerClickListener(this);
        initThreadHandler();
        assignClusters();
    }
}
