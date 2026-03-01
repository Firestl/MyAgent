package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.js.map.amap.adapter.DHMapUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapPluginImpl implements IWaiter, IFeature {
    public JsMapManager mMapManager;

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        this.mMapManager.dispose(str);
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        if (!str.equals("appendToFrameView")) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        AdaFrameView adaFrameView = (AdaFrameView) objArr[0];
        String str2 = (String) objArr[1];
        JsMapView jsMapViewByUuid = this.mMapManager.getJsMapViewByUuid(adaFrameView.obtainApp().obtainAppId(), str2);
        if (jsMapViewByUuid == null) {
            return null;
        }
        jsMapViewByUuid.appendToFrameView(adaFrameView);
        return null;
    }

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
    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        String string;
        Object jsObject;
        JsMapObject jsMapMarker;
        String strObtainAppId = iWebview.obtainApp().obtainAppId();
        final JSONArray jSONArrayCreateJSONArray = JSONUtil.createJSONArray(strArr[0]);
        if ("createObject".equals(str)) {
            String string2 = JSONUtil.getString(jSONArrayCreateJSONArray, 1);
            if ("mapview".equals(string2)) {
                JsMapView jsMapView = new JsMapView(iWebview);
                if (jSONArrayCreateJSONArray.length() > 3) {
                    jsMapView.setJsId(JSONUtil.getString(jSONArrayCreateJSONArray, 3));
                }
                this.mMapManager.putJsMapView(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0), jsMapView);
                jsMapMarker = jsMapView;
            } else {
                jsMapMarker = Constant.Name.MARKER.equals(string2) ? new JsMapMarker(iWebview) : "search".equals(string2) ? new JsMapSearch(iWebview) : Constant.Name.POLYLINE.equals(string2) ? new JsMapPolyline(iWebview) : "polygon".equals(string2) ? new JsMapPolygon(iWebview) : "circle".equals(string2) ? new JsMapCircle(iWebview) : null;
            }
            jsMapMarker.setUUID(JSONUtil.getString(jSONArrayCreateJSONArray, 0));
            jsMapMarker.createObject(JSONUtil.getJSONArray(jSONArrayCreateJSONArray, 2));
            this.mMapManager.putJsObject(JSONUtil.getString(jSONArrayCreateJSONArray, 0), jsMapMarker);
            return null;
        }
        if ("updateObject".equals(str)) {
            MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.js.map.amap.JsMapPluginImpl.1
                @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                public void execute(Object obj) {
                    JsMapObject jsObject2 = JsMapPluginImpl.this.mMapManager.getJsObject(JSONUtil.getString(jSONArrayCreateJSONArray, 0));
                    if (jsObject2 != null) {
                        JSONArray jSONArray = JSONUtil.getJSONArray(jSONArrayCreateJSONArray, 1);
                        jsObject2.updateObject(JSONUtil.getString(jSONArray, 0), JSONUtil.getJSONArray(jSONArray, 1));
                    }
                }
            }, null);
            return null;
        }
        if ("execMethod".equals(str)) {
            JSONArray jSONArray = JSONUtil.getJSONArray(jSONArrayCreateJSONArray, 1);
            String string3 = JSONUtil.getString(jSONArray, 0);
            if (!"openSysMap".equals(string3)) {
                if (!"close".equals(string3) || (jsObject = this.mMapManager.getJsObject((string = JSONUtil.getString(jSONArray, 1)))) == null) {
                    return null;
                }
                ((IFMapDispose) jsObject).close();
                this.mMapManager.removeJsObject(iWebview.obtainApp().obtainAppId(), string);
                return null;
            }
            JSONArray jSONArray2 = JSONUtil.getJSONArray(jSONArray, 1);
            JSONObject jSONObject = JSONUtil.getJSONObject(jSONArray2, 0);
            String string4 = JSONUtil.getString(jSONObject, Constant.JSONKEY.LONGITUDE);
            String string5 = JSONUtil.getString(jSONObject, Constant.JSONKEY.LATITUDE);
            String string6 = JSONUtil.getString(jSONArray2, 1);
            JSONObject jSONObject2 = JSONUtil.getJSONObject(jSONArray2, 2);
            DHMapUtil.openSysMap(iWebview, "", new String[][]{new String[]{string5, string4}, new String[]{JSONUtil.getString(jSONObject2, Constant.JSONKEY.LATITUDE), JSONUtil.getString(jSONObject2, Constant.JSONKEY.LONGITUDE)}}, string6);
            return null;
        }
        if ("geocode".equals(str)) {
            String str2 = strArr[0];
            JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(strArr[1]);
            DHMapUtil.geocode(iWebview, str2, jSONObjectCreateJSONObject == null ? null : jSONObjectCreateJSONObject.optString(AbsoluteConst.JSONKEY_MAP_COORD_TYPE), jSONObjectCreateJSONObject == null ? null : jSONObjectCreateJSONObject.optString("city"), strArr[2]);
            return null;
        }
        if ("reverseGeocode".equals(str)) {
            JsMapPoint mapPoint = JsMapManager.getJsMapManager().getMapPoint(iWebview, JSONUtil.createJSONObject(strArr[0]));
            JSONObject jSONObjectCreateJSONObject2 = JSONUtil.createJSONObject(strArr[1]);
            DHMapUtil.reverseGeocode(iWebview, mapPoint.getMapPoint(), jSONObjectCreateJSONObject2 == null ? null : jSONObjectCreateJSONObject2.optString(AbsoluteConst.JSONKEY_MAP_COORD_TYPE), jSONObjectCreateJSONObject2 == null ? null : jSONObjectCreateJSONObject2.optString("city"), strArr[2]);
            return null;
        }
        if ("updateObjectSYNC".equals(str)) {
            JsMapObject jsObject2 = this.mMapManager.getJsObject(JSONUtil.getString(jSONArrayCreateJSONArray, 0));
            if (jsObject2 == null) {
                return null;
            }
            JSONArray jSONArray3 = JSONUtil.getJSONArray(jSONArrayCreateJSONArray, 1);
            return jsObject2.updateObjectSYNC(JSONUtil.getString(jSONArray3, 0), JSONUtil.getJSONArray(jSONArray3, 1));
        }
        if ("calculateDistance".equals(str)) {
            DHMapUtil.calculateDistance(iWebview, JsMapManager.getJsMapManager().getMapPoint(iWebview, JSONUtil.createJSONObject(strArr[0])).getMapPoint(), JsMapManager.getJsMapManager().getMapPoint(iWebview, JSONUtil.createJSONObject(strArr[1])).getMapPoint(), strArr[2]);
            return null;
        }
        if ("calculateArea".equals(str)) {
            JSONObject jSONObjectCreateJSONObject3 = JSONUtil.createJSONObject(strArr[0]);
            DHMapUtil.calculateArea(iWebview, JsMapManager.getJsMapManager().getMapPoint(iWebview, jSONObjectCreateJSONObject3.optJSONObject("southwest")).getMapPoint(), JsMapManager.getJsMapManager().getMapPoint(iWebview, jSONObjectCreateJSONObject3.optJSONObject("northease")).getMapPoint(), strArr[1]);
            return null;
        }
        if ("convertCoordinates".equals(str)) {
            Deprecated_JSUtil.execCallback(iWebview, strArr[2], DOMException.toJSON(-100, DOMException.toString("高德地图不支持该功能")), JSUtil.ERROR, true, false);
            return null;
        }
        if ("getMapById".equals(str)) {
            JsMapView jsMapViewById = this.mMapManager.getJsMapViewById(strObtainAppId, JSONUtil.getString(jSONArrayCreateJSONArray, 0));
            if (jsMapViewById == null) {
                return null;
            }
            jsMapViewById.setCallBackWebUuid(iWebview.getWebviewUUID());
            JSONObject jsJsonMap = jsMapViewById.getJsJsonMap();
            if (jsJsonMap != null) {
                return JSUtil.wrapJsVar(jsJsonMap);
            }
            return null;
        }
        if (!"setStyles".equals(str)) {
            return null;
        }
        String string7 = JSONUtil.getString(jSONArrayCreateJSONArray, 0);
        JSONObject jSONObject3 = JSONUtil.getJSONObject(jSONArrayCreateJSONArray, 1);
        JsMapView jsMapViewByUuid = this.mMapManager.getJsMapViewByUuid(strObtainAppId, string7);
        if (jsMapViewByUuid == null) {
            return null;
        }
        jsMapViewByUuid.setStyles(jSONObject3);
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        JsMapManager jsMapManager = JsMapManager.getJsMapManager();
        this.mMapManager = jsMapManager;
        jsMapManager.setFeatureMgr(absMgr);
    }
}
