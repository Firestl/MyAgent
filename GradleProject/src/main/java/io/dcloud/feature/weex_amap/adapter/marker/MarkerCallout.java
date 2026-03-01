package io.dcloud.feature.weex_amap.adapter.marker;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MarkerCallout extends AbsMarkerTextView {
    public float anchorX;
    public float anchorY;
    public MovingPointOverlay mCalloutSmoothMarker;
    public Context mContext;
    public Marker mMarker;
    public WXMarker mRootMarker;

    public MarkerCallout(Context context, WXMarker wXMarker, JSONObject jSONObject, WXMapView wXMapView, float f) {
        super(jSONObject, f);
        this.anchorX = 0.0f;
        this.anchorY = 0.0f;
        this.mCalloutSmoothMarker = null;
        this.mContext = context;
        this.mRootMarker = wXMarker;
        createMarker(wXMarker.getInstance().getPosition(), wXMapView);
        if (jSONObject.containsKey("anchorX")) {
            this.anchorX = WXViewUtils.getRealSubPxByWidth(MapResourceUtils.getJSONIntValue(jSONObject.getString("anchorX")), f);
        }
        if (jSONObject.containsKey("anchorY")) {
            this.anchorY = WXViewUtils.getRealSubPxByWidth(MapResourceUtils.getJSONIntValue(jSONObject.getString("anchorY")), f);
        }
    }

    public void createMarker(LatLng latLng, WXMapView wXMapView) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.visible(false);
        this.mMarker = wXMapView.getMap().addMarker(markerOptions);
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.AbsMarkerTextView
    public void destroy() {
        MovingPointOverlay movingPointOverlay = this.mCalloutSmoothMarker;
        if (movingPointOverlay != null) {
            movingPointOverlay.setMoveListener((MovingPointOverlay.MoveListener) null);
            this.mCalloutSmoothMarker.destroy();
        }
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.remove();
        }
    }

    public BitmapDescriptor getIcon() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.addView(getTextView(this.mContext, true), new LinearLayout.LayoutParams(-1, -1));
        linearLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        initCalloutAnchor(linearLayout.getMeasuredHeight());
        return BitmapDescriptorFactory.fromView(linearLayout);
    }

    public Marker getInstance() {
        return this.mMarker;
    }

    public void initCalloutAnchor(float f) {
        int height = 0;
        if (this.mRootMarker.getHeight() > 0) {
            height = this.mRootMarker.getHeight();
        } else if (this.mRootMarker.getHeight() < 0 && this.mRootMarker.getInstance().getIcons().size() > 0) {
            height = ((BitmapDescriptor) this.mRootMarker.getInstance().getIcons().get(0)).getHeight();
        }
        float f2 = 1.0f;
        if (height > 0) {
            f2 = 1.0f + (height / f);
            float f3 = this.anchorX;
            f = f3 != 0.0f ? 0.5f - (f3 / f) : 0.5f;
            float f4 = this.anchorY;
            if (f4 != 0.0f) {
                f2 -= f4 / f;
            }
        }
        this.mMarker.setAnchor(f, f2);
    }

    public boolean isVisible() {
        Marker marker = this.mMarker;
        if (marker != null) {
            return marker.isVisible();
        }
        return false;
    }

    public void moveAlongMarker(WXMapView wXMapView, int i, List<LatLng> list) {
        if (this.mCalloutSmoothMarker == null) {
            this.mCalloutSmoothMarker = new MovingPointOverlay(wXMapView.getMap(), getInstance());
        }
        this.mCalloutSmoothMarker.stopMove();
        this.mCalloutSmoothMarker.setPoints(list);
        this.mCalloutSmoothMarker.setTotalDuration(i / 1000);
        this.mCalloutSmoothMarker.startSmoothMove();
    }

    public void setPosition(LatLng latLng) {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setPosition(latLng);
        }
    }

    public void setRotateAngle(float f) {
    }

    public void setVisible(boolean z) {
        Marker marker = this.mMarker;
        if (marker != null) {
            if (!z) {
                marker.setVisible(false);
                return;
            }
            if (!marker.isVisible()) {
                this.mMarker.setVisible(true);
            }
            this.mMarker.setIcon(getIcon());
        }
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.AbsMarkerTextView
    public void update(JSONObject jSONObject) {
        super.update(jSONObject);
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setIcon(getIcon());
        }
    }
}
