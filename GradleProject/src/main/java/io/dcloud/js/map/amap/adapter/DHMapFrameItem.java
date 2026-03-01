package io.dcloud.js.map.amap.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.amap.api.maps.model.LatLng;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.pro.f;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.ITypeofAble;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.adapter.util.ViewRect;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.js.map.amap.IFMapDispose;
import io.dcloud.js.map.amap.JsMapManager;
import io.dcloud.js.map.amap.JsMapObject;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DHMapFrameItem extends AdaFrameItem implements IFMapDispose, ISysEventListener, ITypeofAble {
    public static final int MSG_ADD_OVERLAY = 3;
    public static final int MSG_APPEND = 12;
    public static final int MSG_CENTER_ZOOM = 11;
    public static final int MSG_CLEAR_OVERLAY = 5;
    public static final int MSG_CREATE = 0;
    public static final int MSG_REMOVE_OVERLAY = 4;
    public static final int MSG_RESET = 6;
    public static final int MSG_SCALE = 1;
    public static final int MSG_SET_MAPTYPE = 8;
    public static final int MSG_SHOWLOCATION = 7;
    public static final int MSG_SHOWZOOMCONTROLS = 10;
    public static final int MSG_UPDATE_CENTER = 2;
    public static final int MSG_VISIBLE = 9;
    public String PERCENTAGE;
    public MapPoint mCenter;
    public IWebview mContainerWebview;
    public JsMapObject mJsMapView;
    public MapHandler mMapHandler;
    public DHMapView mMapView;
    public JSONArray mOptions;
    public ArrayList<Object> mOverlaysId;
    public String mPosition;
    public LinearLayout mRootView;
    public boolean mShowUserLocation;
    public boolean mShowZoomControls;
    public boolean mTraffic;
    public String mUUID;
    public IWebview mWebview;
    public int mZoom;
    public String mapType;

    public class MapHandler extends Handler {
        public MapHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    DHMapFrameItem.this.createMapFrameItem((JSONArray) message.obj);
                    break;
                case 1:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.setZoom(((Integer) message.obj).intValue());
                    }
                    break;
                case 2:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.setCenter((MapPoint) message.obj);
                    }
                    break;
                case 3:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.addOverlay(message.obj);
                    }
                    break;
                case 4:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.removeOverlay(message.obj);
                    }
                    break;
                case 5:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.clearOverlays();
                    }
                    break;
                case 6:
                case 11:
                    if (DHMapFrameItem.this.mMapView != null) {
                        Object[] objArr = (Object[]) message.obj;
                        DHMapFrameItem.this.mMapView.setCenterAndZoom((MapPoint) objArr[1], ((Integer) objArr[0]).intValue());
                    }
                    break;
                case 7:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.showUserLocation(((Boolean) message.obj).booleanValue());
                    }
                    break;
                case 8:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.setMapType(((Integer) message.obj).intValue());
                    }
                    break;
                case 9:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.setVisible(((Boolean) message.obj).booleanValue());
                    }
                    break;
                case 10:
                    if (DHMapFrameItem.this.mMapView != null) {
                        DHMapFrameItem.this.mMapView.showZoomControls(((Boolean) message.obj).booleanValue());
                    }
                    break;
                case 12:
                    DHMapFrameItem.this.addToFrameItem(message.obj);
                    break;
            }
            if (DHMapFrameItem.this.mMapView != null) {
                DHMapFrameItem.this.mMapView.refreshDrawableState();
            }
        }
    }

    public DHMapFrameItem(Context context, IWebview iWebview, JsMapObject jsMapObject) {
        super(context);
        this.mZoom = 12;
        this.mPosition = AbsoluteConst.JSON_VALUE_POSITION_STATIC;
        this.mJsMapView = null;
        this.PERCENTAGE = "%";
        this.mWebview = iWebview;
        this.mContainerWebview = iWebview;
        this.mJsMapView = jsMapObject;
        LinearLayout linearLayout = new LinearLayout(context);
        this.mRootView = linearLayout;
        setMainView(linearLayout);
        this.mMapHandler = new MapHandler(Looper.getMainLooper());
        this.mOverlaysId = new ArrayList<>();
        IApp iAppObtainApp = this.mWebview.obtainFrameView().obtainApp();
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onPause);
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onResume);
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onStop);
        iAppObtainApp.registerSysEventListener(this, ISysEventListener.SysEventType.onSaveInstanceState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addToFrameItem(Object obj) {
        JSONArray jSONArray = (JSONArray) obj;
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        AdaFrameItem adaFrameItem = (AdaFrameItem) this.mContainerWebview.obtainFrameView();
        ViewGroup.LayoutParams mapLayoutParams = getMapLayoutParams(adaFrameItem.obtainFrameOptions(), jSONObjectOptJSONObject, jSONArray);
        this.mContainerWebview.obtainFrameView().setNeedRender(true);
        if (!AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(this.mPosition)) {
            if (DeviceInfo.sDeviceSdkVer >= 11) {
                this.mContainerWebview.obtainWindowView().setLayerType(0, null);
            }
            this.mContainerWebview.addFrameItem(this, mapLayoutParams);
            return;
        }
        if (adaFrameItem instanceof AdaFrameView) {
            ((AdaFrameView) adaFrameItem).addFrameItem(this, ((ViewGroup) adaFrameItem.obtainMainView()).getChildCount() <= 0 ? -1 : 1, mapLayoutParams);
        } else {
            this.mContainerWebview.obtainFrameView().addFrameItem(this, mapLayoutParams);
        }
        Logger.d(Logger.MAP_TAG, "addMapView webview_name=" + this.mWebview.obtainFrameId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMapFrameItem(JSONArray jSONArray) {
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        LatLng latLng = jSONObjectOptJSONObject.optJSONObject("center") != null ? new LatLng(jSONObjectOptJSONObject.optJSONObject("center").optDouble(Constant.JSONKEY.LATITUDE), jSONObjectOptJSONObject.optJSONObject("center").optDouble(Constant.JSONKEY.LONGITUDE)) : null;
        this.mPosition = jSONObjectOptJSONObject.optString("position", this.mPosition);
        DHMapView dHMapView = new DHMapView(getActivity(), this.mWebview, latLng, jSONObjectOptJSONObject.optInt("zoom"), jSONObjectOptJSONObject.optString("type").equals("MAPTYPE_SATELLITE") ? 2 : 1, jSONObjectOptJSONObject.optBoolean(f.F), jSONObjectOptJSONObject.optBoolean("zoomControls"), this.mRootView);
        dHMapView.mUUID = this.mUUID;
        setMapView(dHMapView);
    }

    private ViewGroup.LayoutParams getMapLayoutParams(ViewRect viewRect, JSONObject jSONObject, JSONArray jSONArray) {
        float scale = this.mContainerWebview.getScale();
        if (jSONArray == null || jSONArray.length() <= 4) {
            int iConvertToScreenInt = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "left"), viewRect.width, 0, scale);
            int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "top"), viewRect.height, 0, scale);
            String string = JSONUtil.getString(jSONObject, "width");
            int i = viewRect.width;
            int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(string, i, i, scale);
            String string2 = JSONUtil.getString(jSONObject, "height");
            int i2 = viewRect.height;
            return AdaFrameItem.LayoutParamsUtil.createLayoutParams(iConvertToScreenInt, iConvertToScreenInt2, iConvertToScreenInt3, PdrUtil.convertToScreenInt(string2, i2, i2, scale));
        }
        int iOptInt = (int) (jSONArray.optInt(1) * scale);
        int iOptInt2 = (int) (jSONArray.optInt(2) * scale);
        int iMin = Math.min((int) (jSONArray.optInt(3) * scale), viewRect.width);
        int iMin2 = Math.min((int) (jSONArray.optInt(4) * scale), viewRect.height);
        updateViewRect((AdaFrameItem) this.mWebview.obtainFrameView(), new int[]{iOptInt, iOptInt2, iMin, iMin2}, new int[]{viewRect.width, viewRect.height});
        Logger.d("mapview", "_l=" + iOptInt + ";_t=" + iOptInt2 + ";_w=" + iMin + ";_h=" + iMin2);
        return AdaFrameItem.LayoutParamsUtil.createLayoutParams(iOptInt, iOptInt2, iMin, iMin2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setMapView(DHMapView dHMapView) {
        this.mMapView = dHMapView;
        this.mRootView.addView(dHMapView);
        this.mJsMapView.onAddToMapView(this.mMapView);
        this.mMapView.onCreate(null);
    }

    public void addOverlay(Object obj) {
        if (obj != null) {
            this.mOverlaysId.add(obj);
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.obj = obj;
            this.mMapHandler.sendMessage(messageObtain);
        }
    }

    public void appendToFrameView(AdaFrameView adaFrameView) {
        if (getMapView() != null && getMapView().getParent() != null) {
            removeMapFrameItem(this.mContainerWebview);
        }
        this.mContainerWebview = adaFrameView.obtainWebView();
        addToFrameItem(this.mOptions);
    }

    public void centerAndZoom(MapPoint mapPoint, String str) {
        if (str == null) {
            str = Constants.VIA_REPORT_TYPE_SET_AVATAR;
        }
        try {
            int i = Integer.parseInt(str);
            if (mapPoint != null) {
                this.mCenter = mapPoint;
            }
            this.mZoom = i;
            Message messageObtain = Message.obtain();
            messageObtain.what = 11;
            messageObtain.obj = new Object[]{Integer.valueOf(this.mZoom), this.mCenter};
            this.mMapHandler.sendMessage(messageObtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void clearOverlays() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 5;
        this.mMapHandler.sendMessage(messageObtain);
    }

    @Override // io.dcloud.js.map.amap.IFMapDispose
    public void close() {
        if (PdrUtil.isEmpty(this.mMapView)) {
            return;
        }
        this.mMapView.dispose();
        this.mRootView.setVisibility(8);
        this.mRootView.post(new Runnable() { // from class: io.dcloud.js.map.amap.adapter.DHMapFrameItem.1
            @Override // java.lang.Runnable
            public void run() {
                DHMapFrameItem.this.mMapView.onDestroy();
                JsMapManager.getJsMapManager().removeJsMapView(DHMapFrameItem.this.mContainerWebview.obtainApp().obtainAppId(), DHMapFrameItem.this.mUUID);
                if (DHMapFrameItem.this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_STATIC)) {
                    DHMapFrameItem.this.mContainerWebview.removeFrameItem(DHMapFrameItem.this);
                } else {
                    DHMapFrameItem.this.mContainerWebview.obtainFrameView().removeFrameItem(DHMapFrameItem.this);
                }
                DHMapFrameItem.this.mMapView.mAutoPopFromStack = false;
                DHMapFrameItem.this.mMapView = null;
                DHMapFrameItem.this.mContainerWebview = null;
            }
        });
    }

    public void createMap(JSONArray jSONArray) {
        this.mOptions = jSONArray;
        createMapFrameItem(jSONArray);
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        if (PdrUtil.isEmpty(this.mMapView)) {
            return;
        }
        this.mMapView.dispose();
        this.mRootView.setVisibility(8);
        this.mMapView.onDestroy();
        JsMapManager.getJsMapManager().removeJsMapView(this.mContainerWebview.obtainApp().obtainAppId(), this.mUUID);
        if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_STATIC)) {
            this.mContainerWebview.removeFrameItem(this);
        } else {
            this.mContainerWebview.obtainFrameView().removeFrameItem(this);
        }
        this.mMapView.mAutoPopFromStack = false;
        this.mMapView = null;
        this.mContainerWebview = null;
    }

    public String getBounds() {
        return this.mMapView.getBounds();
    }

    public void getCurrentCenter(IWebview iWebview, String str) {
        this.mMapView.getCurrentCenter(iWebview, str);
    }

    public JSONObject getMapOptions() {
        JSONArray jSONArray = this.mOptions;
        if (jSONArray == null) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        try {
            jSONObjectOptJSONObject.put("zoom", this.mZoom);
            JSONObject jSONObject = new JSONObject();
            if (this.mCenter != null) {
                jSONObject.put(Constant.JSONKEY.LONGITUDE, this.mCenter.getLongitude());
                jSONObject.put(Constant.JSONKEY.LATITUDE, this.mCenter.getLatitude());
                jSONObjectOptJSONObject.put("center", jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObjectOptJSONObject;
    }

    public DHMapView getMapView() {
        return this.mMapView;
    }

    public void getUserLocation(IWebview iWebview, String str) {
        this.mMapView.getUserLocation(iWebview, str);
    }

    public void hide() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 9;
        messageObtain.obj = false;
        this.mMapHandler.sendMessage(messageObtain);
    }

    public boolean isShowUserLocation() {
        return this.mShowUserLocation;
    }

    public boolean isShowZoomControls() {
        return this.mShowZoomControls;
    }

    public boolean isTraffic() {
        return this.mTraffic;
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (PdrUtil.isEmpty(this.mMapView)) {
            return false;
        }
        if (sysEventType == ISysEventListener.SysEventType.onPause) {
            this.mMapView.onPause();
            this.mMapView.locationStop();
            return true;
        }
        if (sysEventType == ISysEventListener.SysEventType.onResume) {
            this.mMapView.onResume();
            this.mMapView.locationReStart();
            return true;
        }
        if (sysEventType == ISysEventListener.SysEventType.onStop) {
            this.mMapView.onDestroy();
            return true;
        }
        if (sysEventType != ISysEventListener.SysEventType.onSaveInstanceState) {
            return false;
        }
        if (obj instanceof Bundle) {
            this.mMapView.onSaveInstanceState((Bundle) obj);
        }
        return true;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPopFromStack(boolean z) {
        super.onPopFromStack(z);
        if (z) {
            this.mMapView.mAutoPopFromStack = true;
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPushToStack(boolean z) {
        super.onPushToStack(z);
        if (z) {
            DHMapView dHMapView = this.mMapView;
            dHMapView.mAutoPopFromStack = false;
            dHMapView.onResume();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onResize() {
        super.onResize();
        if (this.mOptions == null || getMapView().getParent() == null) {
            return;
        }
        boolean z = false;
        JSONObject jSONObjectOptJSONObject = this.mOptions.optJSONObject(0);
        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.has("width") || jSONObjectOptJSONObject.has("height") || jSONObjectOptJSONObject.has("top") || jSONObjectOptJSONObject.has("left")) {
            String string = JSONUtil.getString(jSONObjectOptJSONObject, "width");
            String string2 = JSONUtil.getString(jSONObjectOptJSONObject, "height");
            String string3 = JSONUtil.getString(jSONObjectOptJSONObject, "top");
            String string4 = JSONUtil.getString(jSONObjectOptJSONObject, "left");
            if (!PdrUtil.isEmpty(string) && string.endsWith(this.PERCENTAGE)) {
                z = true;
            }
            if (!PdrUtil.isEmpty(string2) && string2.endsWith(this.PERCENTAGE)) {
                z = true;
            }
            if (!PdrUtil.isEmpty(string3) && string3.endsWith(this.PERCENTAGE)) {
                z = true;
            }
            if ((PdrUtil.isEmpty(string4) || !string4.endsWith(this.PERCENTAGE)) ? z : true) {
                obtainMainView().setLayoutParams(getMapLayoutParams(((AdaFrameItem) this.mContainerWebview.obtainFrameView()).obtainFrameOptions(), jSONObjectOptJSONObject, this.mOptions));
            }
        }
    }

    public void removeMapFrameItem(IWebview iWebview) {
        if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
            iWebview.obtainFrameView().removeFrameItem(this);
        } else {
            iWebview.removeFrameItem(this);
        }
    }

    public void removeOverlay(Object obj) {
        if (obj != null) {
            this.mOverlaysId.remove(obj);
            Message messageObtain = Message.obtain();
            messageObtain.what = 4;
            messageObtain.obj = obj;
            this.mMapHandler.sendMessage(messageObtain);
        }
    }

    public void reset() {
        if (this.mCenter != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 6;
            messageObtain.obj = new Object[]{Integer.valueOf(this.mZoom), this.mCenter};
            this.mMapHandler.sendMessage(messageObtain);
        }
    }

    public void resize(JSONArray jSONArray) {
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) this.mContainerWebview.obtainFrameView()).obtainFrameOptions();
        JSONObject jSONObjectOptJSONObject = this.mOptions.optJSONObject(0);
        if (this.mOptions.length() > 4) {
            try {
                this.mOptions.put(1, jSONArray.optInt(0));
                this.mOptions.put(2, jSONArray.optInt(1));
                this.mOptions.put(3, jSONArray.optInt(2));
                this.mOptions.put(4, jSONArray.optInt(3));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        obtainMainView().setLayoutParams(getMapLayoutParams(viewOptionsObtainFrameOptions, jSONObjectOptJSONObject, this.mOptions));
    }

    public void setCenter(MapPoint mapPoint) {
        if (mapPoint != null) {
            this.mCenter = mapPoint;
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            messageObtain.obj = this.mCenter;
            this.mMapHandler.sendMessage(messageObtain);
        }
    }

    public void setMapType(String str) {
        this.mapType = str;
        Message messageObtain = Message.obtain();
        messageObtain.what = 8;
        if ("MAPTYPE_SATELLITE".equals(str)) {
            messageObtain.obj = 1;
        } else {
            messageObtain.obj = 0;
        }
        this.mMapHandler.sendMessage(messageObtain);
    }

    public void setShowUserLocation(String str) {
        this.mShowUserLocation = Boolean.parseBoolean(str);
        Message messageObtain = Message.obtain();
        messageObtain.what = 7;
        messageObtain.obj = Boolean.valueOf(this.mShowUserLocation);
        this.mMapHandler.sendMessage(messageObtain);
    }

    public void setShowZoomControls(String str) {
        this.mShowZoomControls = Boolean.parseBoolean(str);
        Message messageObtain = Message.obtain();
        messageObtain.what = 10;
        messageObtain.obj = Boolean.valueOf(this.mShowZoomControls);
        this.mMapHandler.sendMessage(messageObtain);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setStyles(JSONObject jSONObject) {
        JSONUtil.combinJSONObject(JSONUtil.getJSONObject(this.mOptions, 0), jSONObject);
        if (this.mMapView != null) {
            if (jSONObject.has("center")) {
                this.mMapView.setCenter(new MapPoint(jSONObject.optJSONObject("center").optString(Constant.JSONKEY.LATITUDE), jSONObject.optJSONObject("center").optString(Constant.JSONKEY.LONGITUDE)));
            }
            if (jSONObject.has("zoom")) {
                this.mMapView.setZoom(jSONObject.optInt("zoom"));
            }
            if (jSONObject.has("type")) {
                this.mMapView.setMapType(jSONObject.optString("type").equals("MAPTYPE_SATELLITE") ? 1 : 0);
            }
            if (jSONObject.has(f.F)) {
                setTraffic(jSONObject.optBoolean(f.F));
            }
            if (jSONObject.has("zoomControls")) {
                this.mMapView.showZoomControls(jSONObject.optBoolean("zoomControls"));
            }
            if (jSONObject.has("top") || jSONObject.has("left") || jSONObject.has("width") || jSONObject.has("height") || jSONObject.has("position")) {
                ViewGroup.LayoutParams mapLayoutParams = getMapLayoutParams(((AdaFrameItem) this.mContainerWebview.obtainFrameView()).obtainFrameOptions(), JSONUtil.getJSONObject(this.mOptions, 0), this.mOptions);
                if (!jSONObject.has("position")) {
                    obtainMainView().setLayoutParams(mapLayoutParams);
                    return;
                }
                String strOptString = jSONObject.optString("position", this.mPosition);
                if (strOptString.equals(this.mPosition)) {
                    obtainMainView().setLayoutParams(mapLayoutParams);
                    return;
                }
                if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
                    this.mContainerWebview.obtainFrameView().removeFrameItem(this);
                    this.mContainerWebview.addFrameItem(this, mapLayoutParams);
                } else {
                    this.mContainerWebview.removeFrameItem(this);
                    this.mContainerWebview.obtainFrameView().addFrameItem(this, mapLayoutParams);
                }
                this.mPosition = strOptString;
            }
        }
    }

    public void setTraffic(boolean z) {
        this.mTraffic = z;
        int i = z ? 1001 : 1002;
        Message messageObtain = Message.obtain();
        messageObtain.what = 8;
        messageObtain.obj = Integer.valueOf(i);
        this.mMapHandler.sendMessage(messageObtain);
    }

    public void setZoom(String str) {
        if (str == null) {
            str = Constants.VIA_REPORT_TYPE_SET_AVATAR;
        }
        try {
            int i = Integer.parseInt(str);
            this.mZoom = i;
            Message messageObtain = Message.obtain();
            messageObtain.what = 1;
            messageObtain.obj = Integer.valueOf(i);
            this.mMapHandler.sendMessage(messageObtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void show() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 9;
        messageObtain.obj = true;
        this.mMapHandler.sendMessage(messageObtain);
    }
}
