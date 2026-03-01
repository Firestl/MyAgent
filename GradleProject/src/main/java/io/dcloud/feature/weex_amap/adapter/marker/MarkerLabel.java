package io.dcloud.feature.weex_amap.adapter.marker;

import android.content.Context;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MarkerLabel extends AbsMarkerTextView {
    public Context mContext;
    public JSONObject mData;
    public int mHeight;
    public MovingPointOverlay mLabelSmoothMarker;
    public Marker mMarker;
    public Marker mRootMarker;
    public float mViewProt;
    public int mWidth;
    public float x;
    public float y;

    public MarkerLabel(Context context, Marker marker, JSONObject jSONObject, WXMapView wXMapView, float f) {
        super(jSONObject, f);
        this.mWidth = -2;
        this.mHeight = -2;
        this.mLabelSmoothMarker = null;
        this.mContext = context;
        this.mRootMarker = marker;
        this.mViewProt = f;
        setData(jSONObject);
        createMarker(this.mRootMarker.getPosition(), wXMapView);
    }

    private BitmapDescriptor getIcon() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.addView(getTextView(this.mContext, false), new LinearLayout.LayoutParams(this.mWidth, this.mHeight));
        BitmapDescriptor bitmapDescriptorFromView = BitmapDescriptorFactory.fromView(linearLayout);
        if (bitmapDescriptorFromView != null) {
            this.mWidth = bitmapDescriptorFromView.getWidth();
            this.mHeight = bitmapDescriptorFromView.getHeight();
        }
        return bitmapDescriptorFromView;
    }

    private void setData(JSONObject jSONObject) {
        this.mData = jSONObject;
        if (jSONObject.containsKey("anchorX")) {
            this.x = WXViewUtils.getRealSubPxByWidth(MapResourceUtils.getJSONIntValue(jSONObject.getString("anchorX")), this.mViewProt);
        } else if (jSONObject.containsKey(Constants.Name.X)) {
            this.x = WXViewUtils.getRealSubPxByWidth(MapResourceUtils.getJSONIntValue(jSONObject.getString(Constants.Name.X)), this.mViewProt);
        }
        if (jSONObject.containsKey("anchorY")) {
            this.y = WXViewUtils.getRealSubPxByWidth(MapResourceUtils.getJSONIntValue(jSONObject.getString("anchorY")), this.mViewProt);
        } else if (jSONObject.containsKey(Constants.Name.Y)) {
            this.y = WXViewUtils.getRealSubPxByWidth(MapResourceUtils.getJSONIntValue(jSONObject.getString(Constants.Name.Y)), this.mViewProt);
        }
        if (jSONObject.containsKey("width")) {
            this.mWidth = (int) WXViewUtils.getRealSubPxByWidth(WXUtils.getInt(jSONObject.get("width")), this.mViewProt);
        }
        if (jSONObject.containsKey("height")) {
            this.mHeight = (int) WXViewUtils.getRealSubPxByWidth(WXUtils.getInt(jSONObject.get("height")), this.mViewProt);
        }
    }

    public void createMarker(LatLng latLng, WXMapView wXMapView) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(getIcon());
        if (this.mWidth == 0 || this.mHeight == 0) {
            return;
        }
        markerOptions.position(latLng);
        markerOptions.anchor(0.0f - (this.x / this.mWidth), 0.0f - (this.y / this.mHeight));
        this.mMarker = wXMapView.getMap().addMarker(markerOptions);
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.AbsMarkerTextView
    public void destroy() {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.remove();
        }
    }

    public Marker getInstance() {
        return this.mMarker;
    }

    public void moveAlongMarker(WXMapView wXMapView, int i, List<LatLng> list) {
        if (this.mMarker == null) {
            return;
        }
        if (this.mLabelSmoothMarker == null) {
            this.mLabelSmoothMarker = new MovingPointOverlay(wXMapView.getMap(), getInstance());
        }
        this.mLabelSmoothMarker.stopMove();
        this.mLabelSmoothMarker.setPoints(list);
        this.mLabelSmoothMarker.setTotalDuration(i / 1000);
        this.mLabelSmoothMarker.startSmoothMove();
    }

    public void setPosition(LatLng latLng) {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setPosition(latLng);
        }
    }

    public void setRotateAngle(float f) {
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.AbsMarkerTextView
    public void update(JSONObject jSONObject) {
        setData(jSONObject);
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setIcon(getIcon());
        }
    }
}
