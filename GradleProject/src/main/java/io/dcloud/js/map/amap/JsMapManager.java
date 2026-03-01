package io.dcloud.js.map.amap;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapManager {
    public static JsMapManager mJsMapManager;
    public AbsMgr mFeatureMgr;
    public HashMap<String, JsMapObject> mJsMapObjects = new HashMap<>();
    public HashMap<String, JsMapRoute> mMapRoutes = new HashMap<>();
    public HashMap<String, LinkedHashMap<String, JsMapView>> mJsMapViews = new HashMap<>();

    public static JsMapManager getJsMapManager() {
        if (mJsMapManager == null) {
            mJsMapManager = new JsMapManager();
        }
        return mJsMapManager;
    }

    public static ArrayList<String> getStrToStrArry(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.get(i) instanceof String ? (String) jSONArray.get(i) : jSONArray.get(i).toString());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public static int hexString2Int(String str) {
        if (str == null) {
            return 0;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("#")) {
            strTrim = strTrim.substring(1);
        }
        if (strTrim.length() == 8) {
            strTrim = strTrim.substring(2);
        }
        try {
            return Integer.valueOf(strTrim, 16).intValue();
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public void dispose(String str) {
        LinkedHashMap<String, JsMapView> linkedHashMapRemove = this.mJsMapViews.remove(str);
        if (linkedHashMapRemove != null) {
            Iterator<String> it = linkedHashMapRemove.keySet().iterator();
            while (it.hasNext()) {
                linkedHashMapRemove.get(it.next()).dispose();
            }
            linkedHashMapRemove.clear();
        }
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
    public IWebview findWebviewByUuid(IWebview iWebview, String str) {
        Object objProcessEvent;
        AbsMgr absMgr = this.mFeatureMgr;
        if (absMgr == null || (objProcessEvent = absMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "ui", "findWebview", new String[]{iWebview.obtainApp().obtainAppId(), str}})) == null) {
            return null;
        }
        return (IWebview) objProcessEvent;
    }

    public JsMapView getJsMapViewById(String str, String str2) {
        if (!this.mJsMapViews.containsKey(str)) {
            return null;
        }
        LinkedHashMap<String, JsMapView> linkedHashMap = this.mJsMapViews.get(str);
        for (String str3 : linkedHashMap.keySet()) {
            String str4 = linkedHashMap.get(str3).mJsId;
            if (!TextUtils.isEmpty(str4) && str2.equals(str4)) {
                return linkedHashMap.get(str3);
            }
        }
        return null;
    }

    public JsMapView getJsMapViewByUuid(String str, String str2) {
        if (!this.mJsMapViews.containsKey(str)) {
            return null;
        }
        LinkedHashMap<String, JsMapView> linkedHashMap = this.mJsMapViews.get(str);
        if (linkedHashMap.containsKey(str2)) {
            return linkedHashMap.get(str2);
        }
        return null;
    }

    public JsMapObject getJsObject(String str) {
        return this.mJsMapObjects.get(str);
    }

    public ArrayList<JsMapPoint> getJsToPointArry(IWebview iWebview, String str) {
        ArrayList<JsMapPoint> arrayList = new ArrayList<>();
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    jSONArray.get(i);
                    arrayList.add(getMapPoint(iWebview, JSONUtil.getJSONObject(jSONArray, i)));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public JsMapPoint getMapPoint(IWebview iWebview, JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return new JsMapPoint(iWebview, jSONObject.getString(Constant.JSONKEY.LATITUDE), jSONObject.getString(Constant.JSONKEY.LONGITUDE));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void putJsMapView(String str, String str2, JsMapView jsMapView) {
        if (!this.mJsMapViews.containsKey(str)) {
            this.mJsMapViews.put(str, new LinkedHashMap<>());
        }
        if (this.mJsMapViews.get(str).containsKey(str2)) {
            return;
        }
        this.mJsMapViews.get(str).put(str2, jsMapView);
    }

    public void putJsObject(String str, JsMapObject jsMapObject) {
        this.mJsMapObjects.put(str, jsMapObject);
    }

    public void removeJsMapView(String str, String str2) {
        if (this.mJsMapViews.containsKey(str)) {
            LinkedHashMap<String, JsMapView> linkedHashMap = this.mJsMapViews.get(str);
            if (linkedHashMap.containsKey(str2)) {
                linkedHashMap.remove(str2);
            }
        }
    }

    public JsMapObject removeJsObject(String str, String str2) {
        JsMapObject jsMapObjectRemove = this.mJsMapObjects.remove(str2);
        if (jsMapObjectRemove instanceof JsMapView) {
            removeJsMapView(str, str2);
        }
        return jsMapObjectRemove;
    }

    public void setFeatureMgr(AbsMgr absMgr) {
        this.mFeatureMgr = absMgr;
    }
}
