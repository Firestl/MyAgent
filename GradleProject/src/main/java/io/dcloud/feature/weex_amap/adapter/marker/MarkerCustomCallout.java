package io.dcloud.feature.weex_amap.adapter.marker;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.feature.weex.extend.DCCoverViewComponent;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class MarkerCustomCallout extends MarkerCallout {
    public WXMapView mMapView;

    public MarkerCustomCallout(Context context, WXMarker wXMarker, JSONObject jSONObject, WXMapView wXMapView, float f) {
        super(context, wXMarker, jSONObject, wXMapView, f);
        this.mMapView = wXMapView;
        this.mRootMarker = wXMarker;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.MarkerCallout
    public BitmapDescriptor getIcon() {
        HashMap<String, String> calloutMarkerIds;
        WXComponent wXComponent;
        WXComponent wXComponent2 = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(this.mMapView.getUniSDKInstance().getInstanceId(), this.mMapView.getCoverViewCalloutComponetRef());
        if (wXComponent2 != null && (wXComponent2 instanceof DCCoverViewComponent) && (calloutMarkerIds = ((DCCoverViewComponent) wXComponent2).getCalloutMarkerIds()) != null && calloutMarkerIds.size() > 0) {
            String str = calloutMarkerIds.get(this.mRootMarker.getId());
            if (!TextUtils.isEmpty(str) && (wXComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(this.mMapView.getUniSDKInstance().getInstanceId(), str)) != null) {
                Bitmap viewToBitmamp = getViewToBitmamp(wXComponent.getHostView());
                LinearLayout linearLayout = new LinearLayout(this.mContext);
                ImageView imageView = new ImageView(this.mContext);
                imageView.setImageBitmap(viewToBitmamp);
                initCalloutAnchor(viewToBitmamp.getHeight());
                linearLayout.addView(imageView, new LinearLayout.LayoutParams(-1, -1));
                return BitmapDescriptorFactory.fromView(linearLayout);
            }
        }
        return super.getIcon();
    }

    public Bitmap getViewToBitmamp(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmapCreateBitmap;
    }
}
