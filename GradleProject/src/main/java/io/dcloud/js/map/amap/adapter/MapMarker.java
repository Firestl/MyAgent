package io.dcloud.js.map.amap.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.CanvasHelper;
import io.dcloud.common.adapter.util.PlatformUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class MapMarker {
    public boolean isDraggable = false;
    public boolean isPop = false;
    public boolean isToTop = false;
    public String mBubbleIcon;
    public String mBubbleLabel;
    public IWebview mIWebview;
    public String mIcon;
    public ArrayList<BitmapDescriptor> mIcons;
    public String mLabel;
    public String mLoadImagePath;
    public String mLoadImageUrlData;
    public MapPoint mMapPoint;
    public Marker mMarker;
    public int mPeriod;
    public String uuid;

    public MapMarker(MapPoint mapPoint, IWebview iWebview) {
        this.mMapPoint = mapPoint;
        this.mIWebview = iWebview;
    }

    private BitmapDescriptor getDefaultMarkerIcon() {
        Bitmap bitmapDecodeResourceStream = BitmapFactory.decodeResourceStream(this.mIWebview.getActivity().getResources(), null, PlatformUtil.getResInputStream("res/point.png"), null, null);
        if (bitmapDecodeResourceStream == null) {
            return null;
        }
        if (TextUtils.isEmpty(this.mLabel)) {
            return BitmapDescriptorFactory.fromBitmap(bitmapDecodeResourceStream);
        }
        LinearLayout linearLayout = new LinearLayout(this.mIWebview.getActivity());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        ImageView imageView = new ImageView(this.mIWebview.getActivity());
        imageView.setImageBitmap(bitmapDecodeResourceStream);
        TextView textView = new TextView(this.mIWebview.getActivity());
        textView.setTextColor(-16777216);
        textView.setText(this.mLabel);
        textView.setTextSize(12.0f);
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        return BitmapDescriptorFactory.fromView(linearLayout);
    }

    private MarkerOptions getMapMarkOptions() {
        MarkerOptions markerOptions = new MarkerOptions();
        MapPoint mapPoint = this.mMapPoint;
        if (mapPoint != null) {
            markerOptions.position(mapPoint.getLatLng());
        }
        String str = this.mBubbleLabel;
        if (str != null) {
            markerOptions.snippet(str);
        }
        if (!TextUtils.isEmpty(this.mLabel)) {
            markerOptions.title(this.mLabel);
        }
        ArrayList<BitmapDescriptor> arrayList = this.mIcons;
        if (arrayList != null) {
            markerOptions.icons(arrayList);
            int i = this.mPeriod;
            if (i > 0) {
                markerOptions.period(i);
            }
        } else {
            String str2 = this.mIcon;
            if (str2 != null) {
                markerOptions.icon(getIcon(str2));
            } else {
                markerOptions.icon(getDefaultMarkerIcon());
            }
        }
        boolean z = this.isDraggable;
        if (z) {
            markerOptions.draggable(z);
        }
        return markerOptions;
    }

    public void base64ToBitmap(ImageView imageView) {
        byte[] bArrDecode = Base64.decode(this.mLoadImageUrlData.split(",")[1], 1);
        imageView.setImageBitmap(BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
        imageView.setBackgroundColor(0);
    }

    public void bringToTop() {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setToTop();
        } else {
            this.isToTop = true;
        }
    }

    public void checkPop() {
        if (this.isPop) {
            this.mMarker.showInfoWindow();
        }
    }

    public String getBubbleIcon() {
        return this.mBubbleIcon;
    }

    public String getBubbleLabel() {
        return this.mBubbleLabel;
    }

    public String getIcon() {
        return this.mIcon;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getLoadImage() {
        return this.mLoadImagePath;
    }

    public String getLoadImageDataURL() {
        return this.mLoadImageUrlData;
    }

    public MapPoint getMapPoint() {
        return this.mMapPoint;
    }

    public Marker getMarker() {
        return this.mMarker;
    }

    public Drawable getPopIcon() {
        if (this.mBubbleIcon == null) {
            return null;
        }
        Drawable drawable = CanvasHelper.getDrawable(this.mIWebview.obtainFrameView().obtainApp().convert2AbsFullPath(this.mIWebview.obtainFullUrl(), this.mBubbleIcon));
        if (drawable == null) {
            return drawable;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        drawable.setBounds((-intrinsicWidth) / 2, -drawable.getIntrinsicHeight(), intrinsicWidth / 2, 0);
        return drawable;
    }

    public String getUuid() {
        return this.uuid;
    }

    public IWebview getWebview() {
        return this.mIWebview;
    }

    public void hide() {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setVisible(false);
        }
    }

    public void hideBubble() {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.hideInfoWindow();
        }
    }

    public void initMapMarker(DHMapView dHMapView) {
        this.mMarker = dHMapView.getMap().addMarker(getMapMarkOptions());
        if (this.isToTop) {
            bringToTop();
        }
    }

    public boolean isDraggable() {
        Marker marker = this.mMarker;
        return marker != null ? marker.isDraggable() : this.isDraggable;
    }

    public void loadImage(String str) {
        this.mLoadImagePath = str;
    }

    public void loadImageBitmap(ImageView imageView) {
        imageView.setImageBitmap(CanvasHelper.getBitmap(this.mIWebview.obtainFrameView().obtainApp().convert2AbsFullPath(this.mIWebview.obtainFullUrl(), this.mLoadImagePath)));
        imageView.setBackgroundColor(0);
    }

    public void loadImageDataURL(String str) {
        this.mLoadImageUrlData = str;
    }

    public void setBubble(String str, String str2, boolean z) {
        this.mBubbleLabel = str;
        this.mBubbleIcon = str2;
        this.isPop = z;
    }

    public void setBubbleIcon(String str) {
        this.mBubbleIcon = str;
    }

    public void setBubbleLabel(String str) {
        this.mBubbleLabel = str;
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setSnippet(str);
        }
    }

    public void setDraggable(boolean z) {
        this.isDraggable = z;
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setDraggable(z);
        }
    }

    public void setIcon(String str) {
        this.mIcon = str;
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setIcon(getIcon(str));
        }
    }

    public void setIcons(JSONArray jSONArray, int i) {
        Marker marker;
        if (jSONArray != null) {
            this.mIcons = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    this.mIcons.add(getIcon(jSONArray.getString(i2)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            if (i > 0) {
                this.mPeriod = i;
            }
            if (this.mIcons.size() <= 0 || (marker = this.mMarker) == null) {
                return;
            }
            marker.setIcons(this.mIcons);
            int i3 = this.mPeriod;
            if (i3 > 0) {
                this.mMarker.setPeriod(i3);
            }
        }
    }

    public void setLabel(String str) {
        this.mLabel = str;
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setIcon(getIcon(this.mIcon));
        }
    }

    public void setMapPoint(MapPoint mapPoint) {
        this.mMapPoint = mapPoint;
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setPosition(mapPoint.getLatLng());
        }
    }

    public void setPop(boolean z) {
        this.isPop = z;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void show() {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.setVisible(true);
        }
    }

    public BitmapDescriptor getIcon(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strConvert2AbsFullPath = this.mIWebview.obtainFrameView().obtainApp().convert2AbsFullPath(this.mIWebview.obtainFullUrl(), str);
        if (TextUtils.isEmpty(this.mLabel)) {
            return BitmapDescriptorFactory.fromBitmap(CanvasHelper.getBitmap(strConvert2AbsFullPath));
        }
        LinearLayout linearLayout = new LinearLayout(this.mIWebview.getActivity());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        ImageView imageView = new ImageView(this.mIWebview.getActivity());
        imageView.setImageBitmap(CanvasHelper.getBitmap(strConvert2AbsFullPath));
        TextView textView = new TextView(this.mIWebview.getActivity());
        textView.setTextColor(-16777216);
        textView.setText(this.mLabel);
        textView.setTextSize(12.0f);
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        return BitmapDescriptorFactory.fromView(linearLayout);
    }
}
