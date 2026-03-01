package io.dcloud.feature.weex_amap.adapter.marker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.AnimationSet;
import com.amap.api.maps.model.animation.RotateAnimation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.maps.utils.overlay.MovingPointOverlay;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback;
import io.dcloud.feature.weex.adapter.FrescoLoadUtil;
import io.dcloud.feature.weex_amap.R;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WXMarker implements IUniMarker {
    public MarkerCallout mCallout;
    public WXSDKInstance mInstance;
    public MarkerLabel mLabel;
    public Marker mMarker;
    public String mid;
    public int mWidth = -1;
    public int mHeight = -1;
    public boolean mJoinCluster = false;
    public MovingPointOverlay mSmoothMarker = null;

    public WXMarker(WXSDKInstance wXSDKInstance, WXMapView wXMapView, JSONObject jSONObject, String str) {
        this.mInstance = wXSDKInstance;
        this.mid = str;
        this.mMarker = wXMapView.getMap().addMarker(createMarkerOptions(jSONObject));
        initCalloutAndLabel(this.mInstance.getContext(), wXMapView, jSONObject);
    }

    private MarkerCallout createMarkerCallout(Context context, WXMapView wXMapView, JSONObject jSONObject) {
        if (jSONObject.containsKey(Constant.JSONKEY.CUSTOM_CALLOUT)) {
            MarkerCustomCallout markerCustomCallout = new MarkerCustomCallout(context, this, jSONObject.getJSONObject(Constant.JSONKEY.CUSTOM_CALLOUT), wXMapView, this.mInstance.getInstanceViewPortWidthWithFloat());
            markerCustomCallout.getInstance().setZIndex(getInstance().getZIndex() + 1.0f);
            return markerCustomCallout;
        }
        if (!jSONObject.containsKey(Constant.JSONKEY.CALLOUT)) {
            return null;
        }
        MarkerCallout markerCallout = new MarkerCallout(context, this, jSONObject.getJSONObject(Constant.JSONKEY.CALLOUT), wXMapView, this.mInstance.getInstanceViewPortWidthWithFloat());
        markerCallout.getInstance().setZIndex(getInstance().getZIndex() + 1.0f);
        return markerCallout;
    }

    private MarkerOptions createMarkerOptions(JSONObject jSONObject) {
        MarkerOptions markerOptions = new MarkerOptions();
        setPosition(jSONObject, markerOptions);
        setTitle(jSONObject, markerOptions);
        setZIndex(jSONObject, markerOptions);
        setIconInfo(jSONObject, markerOptions);
        setRotate(jSONObject, markerOptions);
        setAlphe(jSONObject, markerOptions);
        setAnchor(jSONObject, markerOptions);
        setSnippet(jSONObject, markerOptions);
        if (jSONObject.containsKey(Constant.JSONKEY.JOIN_CLUSTER)) {
            this.mJoinCluster = jSONObject.getBooleanValue(Constant.JSONKEY.JOIN_CLUSTER);
        }
        return markerOptions;
    }

    private List<LatLng> getMovePoints(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray.size() > 0) {
            for (int i = 0; i < jSONArray.size(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    arrayList.add(new LatLng(Double.valueOf(jSONObject.getDoubleValue(Constant.JSONKEY.LATITUDE)).doubleValue(), Double.valueOf(jSONObject.getDoubleValue(Constant.JSONKEY.LONGITUDE)).doubleValue()));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadIcon(Bitmap bitmap, MarkerOptions markerOptions, JSONObject jSONObject) {
        BitmapDescriptor bitmapDescriptorFromBitmap = BitmapDescriptorFactory.fromBitmap(bitmap);
        if (getInstance() != null) {
            getInstance().setIcon(bitmapDescriptorFromBitmap);
        } else if (markerOptions != null) {
            markerOptions.icon(bitmapDescriptorFromBitmap);
        }
    }

    private void loadImageToIcon(Uri uri, int i, int i2, final MarkerOptions markerOptions, final JSONObject jSONObject) {
        FrescoLoadUtil.getInstance().loadImageBitmap(this.mInstance.getContext(), uri.toString(), i, i2, new BitmapLoadCallback<Bitmap>() { // from class: io.dcloud.feature.weex_amap.adapter.marker.WXMarker.1
            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onFailure(String str, Throwable th) {
            }

            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onSuccess(String str, Bitmap bitmap) {
                WXMarker.this.loadIcon(bitmap, markerOptions, jSONObject);
            }
        });
    }

    private void loadImageViewIcon(Bitmap bitmap, int i, int i2, MarkerOptions markerOptions) {
        ImageView imageView = new ImageView(this.mInstance.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        BitmapDescriptor bitmapDescriptorFromView = BitmapDescriptorFactory.fromView(imageView);
        if (getInstance() != null) {
            getInstance().setIcon(bitmapDescriptorFromView);
        } else if (markerOptions != null) {
            markerOptions.icon(bitmapDescriptorFromView);
        }
    }

    private void rotateToTranslateAnimation(final Marker marker, float f, long j, final LatLng latLng, boolean z, final JSCallback jSCallback) {
        if (!z) {
            long j2 = (long) (j * 0.3d);
            final long j3 = j - j2;
            RotateAnimation rotateAnimation = new RotateAnimation(marker.getRotateAngle(), f);
            rotateAnimation.setDuration(j2);
            rotateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: io.dcloud.feature.weex_amap.adapter.marker.WXMarker.4
                public void onAnimationEnd() {
                    WXMarker.this.translateAnimation(latLng, j3, marker, jSCallback);
                }

                public void onAnimationStart() {
                }
            });
            marker.setAnimation(rotateAnimation);
            marker.startAnimation();
            return;
        }
        RotateAnimation rotateAnimation2 = new RotateAnimation(marker.getRotateAngle(), f);
        rotateAnimation2.setDuration(j);
        TranslateAnimation translateAnimation = new TranslateAnimation(latLng);
        translateAnimation.setDuration(j);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation2);
        animationSet.addAnimation(translateAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: io.dcloud.feature.weex_amap.adapter.marker.WXMarker.3
            public void onAnimationEnd() {
                if (jSCallback != null) {
                    HashMap map = new HashMap();
                    map.put("type", "animationEnd");
                    jSCallback.invokeAndKeepAlive(map);
                }
            }

            public void onAnimationStart() {
            }
        });
        marker.setAnimation(animationSet);
        marker.startAnimation();
    }

    private void setAlphe(JSONObject jSONObject, MarkerOptions markerOptions) {
        if (jSONObject.containsKey(Constant.JSONKEY.ALPHE)) {
            float fFloatValue = jSONObject.getFloat(Constant.JSONKEY.ALPHE).floatValue();
            if (getInstance() != null && getInstance().getAlpha() != fFloatValue) {
                getInstance().setAlpha(fFloatValue);
            } else if (markerOptions != null) {
                markerOptions.alpha(fFloatValue);
            }
        }
    }

    private void setAnchor(JSONObject jSONObject, MarkerOptions markerOptions) {
        JSONObject jSONObject2;
        if (!jSONObject.containsKey(Constant.JSONKEY.ANCHOR) || (jSONObject2 = jSONObject.getJSONObject(Constant.JSONKEY.ANCHOR)) == null) {
            return;
        }
        if (getInstance() != null) {
            getInstance().setAnchor(jSONObject2.getFloat(Constants.Name.X).floatValue(), jSONObject2.getFloat(Constants.Name.Y).floatValue());
        } else {
            markerOptions.anchor(jSONObject2.getFloat(Constants.Name.X).floatValue(), jSONObject2.getFloat(Constants.Name.Y).floatValue());
        }
    }

    private void setIconInfo(JSONObject jSONObject, MarkerOptions markerOptions) {
        if (jSONObject.containsKey("width")) {
            this.mWidth = (int) WXViewUtils.getRealSubPxByWidth(WXUtils.getInt(jSONObject.get("width")), this.mInstance.getInstanceViewPortWidthWithFloat());
        }
        if (jSONObject.containsKey("height")) {
            this.mHeight = (int) WXViewUtils.getRealSubPxByWidth(WXUtils.getInt(jSONObject.get("height")), this.mInstance.getInstanceViewPortWidthWithFloat());
        }
        if (this.mHeight == 0 || this.mWidth == 0) {
            loadImageViewIcon(null, 1, 1, markerOptions);
        } else if (!jSONObject.containsKey(Constant.JSONKEY.ICONPATH)) {
            loadImageViewIcon(BitmapFactory.decodeResource(this.mInstance.getContext().getResources(), R.drawable.dcloud_map_marker_ic), this.mWidth, this.mHeight, markerOptions);
        } else {
            loadImageToIcon(this.mInstance.rewriteUri(Uri.parse(jSONObject.getString(Constant.JSONKEY.ICONPATH)), "image"), this.mWidth, this.mHeight, markerOptions, jSONObject);
        }
    }

    private void setPosition(JSONObject jSONObject, MarkerOptions markerOptions) {
        LatLng latLng = new LatLng(jSONObject.getDouble(Constant.JSONKEY.LATITUDE).doubleValue(), jSONObject.getDouble(Constant.JSONKEY.LONGITUDE).doubleValue());
        if (getInstance() == null) {
            if (markerOptions != null) {
                markerOptions.position(latLng);
                return;
            }
            return;
        }
        try {
            getInstance().setPosition(latLng);
            if (this.mCallout != null) {
                this.mCallout.setPosition(latLng);
            }
            if (this.mLabel != null) {
                this.mLabel.setPosition(latLng);
            }
        } catch (Exception unused) {
        }
    }

    private void setRotate(JSONObject jSONObject, MarkerOptions markerOptions) {
        if (jSONObject.containsKey("rotate")) {
            float fFloatValue = jSONObject.getFloat("rotate").floatValue();
            if (getInstance() == null || getInstance().getRotateAngle() == fFloatValue) {
                if (markerOptions != null) {
                    markerOptions.rotateAngle(fFloatValue);
                    return;
                }
                return;
            }
            getInstance().setRotateAngle(fFloatValue);
            MarkerLabel markerLabel = this.mLabel;
            if (markerLabel != null) {
                markerLabel.setRotateAngle(fFloatValue);
            }
            MarkerCallout markerCallout = this.mCallout;
            if (markerCallout != null) {
                markerCallout.setRotateAngle(fFloatValue);
            }
        }
    }

    private void setSnippet(JSONObject jSONObject, MarkerOptions markerOptions) {
        if (jSONObject.containsKey(Constant.JSONKEY.ARIA_LABEL)) {
            String string = jSONObject.getString(Constant.JSONKEY.ARIA_LABEL);
            if (getInstance() != null) {
                getInstance().setSnippet(string);
            } else {
                markerOptions.snippet(string);
            }
        }
    }

    private void setTitle(JSONObject jSONObject, MarkerOptions markerOptions) {
        if (jSONObject.containsKey("title")) {
            String string = jSONObject.getString("title");
            if (getInstance() != null && !TextUtils.isEmpty(getInstance().getTitle()) && !getInstance().getTitle().equals(string)) {
                getInstance().setTitle(string);
            } else if (markerOptions != null) {
                markerOptions.title(string);
            }
        }
    }

    private void setZIndex(JSONObject jSONObject, MarkerOptions markerOptions) {
        if (jSONObject.containsKey(Constant.JSONKEY.ZINDEX)) {
            float fFloatValue = jSONObject.getFloat(Constant.JSONKEY.ZINDEX).floatValue();
            if (getInstance() != null && fFloatValue != getInstance().getZIndex()) {
                getInstance().setZIndex(fFloatValue);
            } else if (markerOptions != null) {
                markerOptions.zIndex(fFloatValue);
            }
            if (getCallout() != null) {
                getCallout().getInstance().setZIndex(fFloatValue + 1.0f);
            }
            if (getLabel() != null) {
                getLabel().getInstance().setZIndex(fFloatValue + 1.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateAnimation(LatLng latLng, long j, Marker marker, final JSCallback jSCallback) {
        TranslateAnimation translateAnimation = new TranslateAnimation(latLng);
        translateAnimation.setDuration(j);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: io.dcloud.feature.weex_amap.adapter.marker.WXMarker.5
            public void onAnimationEnd() {
                if (jSCallback != null) {
                    HashMap map = new HashMap();
                    map.put("type", "animationEnd");
                    jSCallback.invokeAndKeepAlive(map);
                }
            }

            public void onAnimationStart() {
            }
        });
        marker.setAnimation(translateAnimation);
        marker.startAnimation();
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.IUniMarker
    public void destroy() {
        MovingPointOverlay movingPointOverlay = this.mSmoothMarker;
        if (movingPointOverlay != null) {
            movingPointOverlay.setMoveListener((MovingPointOverlay.MoveListener) null);
            this.mSmoothMarker.destroy();
        }
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.destroy();
        }
        MarkerCallout markerCallout = this.mCallout;
        if (markerCallout != null) {
            markerCallout.destroy();
        }
        MarkerLabel markerLabel = this.mLabel;
        if (markerLabel != null) {
            markerLabel.destroy();
        }
    }

    public MarkerCallout getCallout() {
        return this.mCallout;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public String getId() {
        return this.mid;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.IUniMarker
    public Marker getInstance() {
        return this.mMarker;
    }

    public MarkerLabel getLabel() {
        return this.mLabel;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void hideCallout() {
        if (getCallout() == null || getCallout().isAlwaysDisPlay()) {
            return;
        }
        getCallout().setVisible(false);
    }

    public void initCalloutAndLabel(Context context, WXMapView wXMapView, JSONObject jSONObject) {
        if (getCallout() == null) {
            setCallout(createMarkerCallout(context, wXMapView, jSONObject));
        } else if (jSONObject.containsKey(Constant.JSONKEY.CALLOUT)) {
            getCallout().update(jSONObject.getJSONObject(Constant.JSONKEY.CALLOUT));
        } else {
            getCallout().destroy();
            this.mCallout = null;
        }
        isShowInfoWindow();
        if (jSONObject.containsKey("label")) {
            MarkerLabel markerLabel = this.mLabel;
            if (markerLabel != null) {
                markerLabel.update(jSONObject.getJSONObject("label"));
            } else {
                this.mLabel = new MarkerLabel(context, this.mMarker, jSONObject.getJSONObject("label"), wXMapView, this.mInstance.getInstanceViewPortWidthWithFloat());
            }
            this.mLabel.getInstance().setZIndex(getInstance().getZIndex() + 1.0f);
        }
    }

    public boolean isCalloutShown() {
        if (getCallout() != null) {
            return getCallout().getInstance().isVisible();
        }
        return false;
    }

    public boolean isJoinCluster() {
        return this.mJoinCluster;
    }

    public void isShowInfoWindow() {
        MarkerCallout markerCallout = this.mCallout;
        if (markerCallout == null || !markerCallout.isAlwaysDisPlay()) {
            return;
        }
        this.mCallout.setVisible(true);
    }

    public void moveAlongMarker(WXMapView wXMapView, JSONObject jSONObject, final JSCallback jSCallback) {
        JSONArray jSONArray;
        int iIntValue = WXUtils.getInteger(jSONObject.get("duration"), 0).intValue();
        if (iIntValue < 1000) {
            iIntValue = 1000;
        }
        final HashMap map = new HashMap();
        if (iIntValue > 0) {
            if (this.mSmoothMarker == null) {
                this.mSmoothMarker = new MovingPointOverlay(wXMapView.getMap(), getInstance());
            }
            this.mSmoothMarker.stopMove();
            List<LatLng> movePoints = null;
            if (jSONObject.containsKey("path") && (jSONArray = jSONObject.getJSONArray("path")) != null && (movePoints = getMovePoints(jSONArray)) != null && movePoints.size() > 0) {
                this.mSmoothMarker.setPoints(movePoints);
            }
            if (movePoints != null) {
                this.mSmoothMarker.setTotalDuration(iIntValue / 1000);
                this.mSmoothMarker.setMoveListener(new MovingPointOverlay.MoveListener() { // from class: io.dcloud.feature.weex_amap.adapter.marker.WXMarker.2
                    public void move(double d) {
                        if (d <= 0.0d) {
                            if (jSCallback != null) {
                                map.put("type", "success");
                                jSCallback.invoke(map);
                            }
                            WXMarker.this.mSmoothMarker.setMoveListener((MovingPointOverlay.MoveListener) null);
                        }
                    }
                });
                MarkerLabel markerLabel = this.mLabel;
                if (markerLabel != null) {
                    markerLabel.moveAlongMarker(wXMapView, iIntValue, movePoints);
                }
                MarkerCallout markerCallout = this.mCallout;
                if (markerCallout != null) {
                    markerCallout.moveAlongMarker(wXMapView, iIntValue, movePoints);
                }
                this.mSmoothMarker.startSmoothMove();
                return;
            }
        }
        map.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void setCallout(MarkerCallout markerCallout) {
        this.mCallout = markerCallout;
    }

    public void showCallout() {
        if (getCallout() == null || getCallout().isAlwaysDisPlay()) {
            return;
        }
        getCallout().setVisible(true);
    }

    public void translateMarker(JSONObject jSONObject, JSCallback jSCallback) {
        LatLng latLngCrateLatLng = MapResourceUtils.crateLatLng(jSONObject.getJSONObject("destination"));
        boolean zBooleanValue = jSONObject.containsKey("moveWithRotate") ? jSONObject.getBoolean("moveWithRotate").booleanValue() : false;
        long j = jSONObject.containsKey("duration") ? WXUtils.getLong(jSONObject.get("duration")) : 1000L;
        Marker wXMarker = getInstance();
        if (jSONObject.containsKey("rotate")) {
            float f = -WXUtils.parseFloat(jSONObject.get("rotate"));
            rotateToTranslateAnimation(wXMarker, f, j, latLngCrateLatLng, zBooleanValue, jSCallback);
            MarkerLabel markerLabel = this.mLabel;
            if (markerLabel != null) {
                rotateToTranslateAnimation(markerLabel.getInstance(), f, j, latLngCrateLatLng, zBooleanValue, null);
            }
            MarkerCallout markerCallout = this.mCallout;
            if (markerCallout != null) {
                rotateToTranslateAnimation(markerCallout.getInstance(), this.mCallout.getInstance().getRotateAngle(), j, latLngCrateLatLng, zBooleanValue, null);
            }
        } else {
            translateAnimation(latLngCrateLatLng, j, wXMarker, jSCallback);
            MarkerLabel markerLabel2 = this.mLabel;
            if (markerLabel2 != null) {
                translateAnimation(latLngCrateLatLng, j, markerLabel2.getInstance(), null);
            }
            MarkerCallout markerCallout2 = this.mCallout;
            if (markerCallout2 != null) {
                translateAnimation(latLngCrateLatLng, j, markerCallout2.getInstance(), null);
            }
        }
        if (jSCallback != null) {
            HashMap map = new HashMap();
            map.put("type", "success");
            jSCallback.invokeAndKeepAlive(map);
        }
    }

    public void updateMarkerOptions(JSONObject jSONObject) {
        if (getInstance() != null) {
            setPosition(jSONObject, null);
            setTitle(jSONObject, null);
            setZIndex(jSONObject, null);
            setIconInfo(jSONObject, null);
            setRotate(jSONObject, null);
            setAlphe(jSONObject, null);
            setAnchor(jSONObject, null);
            setSnippet(jSONObject, null);
            if (jSONObject.containsKey(Constant.JSONKEY.JOIN_CLUSTER)) {
                this.mJoinCluster = jSONObject.getBooleanValue(Constant.JSONKEY.JOIN_CLUSTER);
            }
        }
    }
}
