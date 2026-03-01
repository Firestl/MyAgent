package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.js.map.amap.adapter.DHMapFrameItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapView extends JsMapObject implements IFMapDispose {
    public String id;
    public DHMapFrameItem mMapFrameItem;

    public JsMapView(IWebview iWebview) {
        super(iWebview);
        this.mMapFrameItem = new DHMapFrameItem(iWebview.getContext(), iWebview, this);
        Logger.d(Logger.MAP_TAG, "JsMapView create DHMapFrameItem");
    }

    private void init(String str, String str2) {
        this.id = str;
    }

    public void appendToFrameView(AdaFrameView adaFrameView) {
        this.mMapFrameItem.appendToFrameView(adaFrameView);
    }

    @Override // io.dcloud.js.map.amap.IFMapDispose
    public void close() {
        this.mMapFrameItem.close();
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
        this.mMapFrameItem.createMap(jSONArray);
        if (jSONArray.length() > 4) {
            appendToFrameView((AdaFrameView) this.mWebview.obtainFrameView());
        }
    }

    @Override // io.dcloud.js.map.amap.IFMapDispose
    public void dispose() {
        this.mMapFrameItem.dispose();
    }

    public JSONObject getJsJsonMap() {
        JSONObject jSONObject = new JSONObject();
        DHMapFrameItem dHMapFrameItem = this.mMapFrameItem;
        if (dHMapFrameItem == null) {
            return null;
        }
        try {
            jSONObject.put("uuid", dHMapFrameItem.mUUID);
            jSONObject.put("options", this.mMapFrameItem.getMapOptions());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void setCallBackWebUuid(String str) {
        DHMapFrameItem dHMapFrameItem = this.mMapFrameItem;
        if (dHMapFrameItem == null || dHMapFrameItem.getMapView() == null) {
            return;
        }
        this.mMapFrameItem.getMapView().addMapCallBackWebUuid(str);
    }

    public void setMapView(DHMapFrameItem dHMapFrameItem) {
        this.mMapFrameItem = dHMapFrameItem;
    }

    public void setStyles(JSONObject jSONObject) {
        DHMapFrameItem dHMapFrameItem = this.mMapFrameItem;
        if (dHMapFrameItem != null) {
            dHMapFrameItem.setStyles(jSONObject);
        }
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void setUUID(String str) {
        super.setUUID(str);
        this.mMapFrameItem.mUUID = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c6  */
    @Override // io.dcloud.js.map.amap.JsMapObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateObject(java.lang.String r7, final org.json.JSONArray r8) {
        /*
            Method dump skipped, instruction units count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.map.amap.JsMapView.updateObject(java.lang.String, org.json.JSONArray):void");
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public String updateObjectSYNC(String str, JSONArray jSONArray) {
        return "getBounds".equals(str) ? Deprecated_JSUtil.wrapJsVar(this.mMapFrameItem.getBounds(), false) : super.updateObjectSYNC(str, jSONArray);
    }
}
