package io.dcloud.media.live;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.media.live.push.LivePusher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class LiveMediaFeatureImpl extends StandardFeature implements IWaiter, ISysEventListener {
    public AbsMgr featureMgr;
    public HashMap<String, LivePusher> pusherList = null;
    public LivePusher activePusher = null;
    public JSONArray initOptions = null;
    public Map<String, Map<String, Object>> startMap = new HashMap();
    public Map<String, IWebview> previewMap = new HashMap();
    public Map<String, Map<IWebview, JSONObject>> pusherOptions = new HashMap();
    public Map<String, Map<JSONArray, IWebview>> listeners = new HashMap();
    public Map<String, IFrameView> appendView = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    public AdaFrameItem appendLivePusher(String str, IFrameView iFrameView) {
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || str == null) ? null : map.get(str);
        if (livePusher != null) {
            if (!livePusher.isInited) {
                livePusher.initLivePusher(iFrameView.obtainWebView(), this.initOptions);
            }
            livePusher.appendLivePusher("", iFrameView);
        } else if (str != null) {
            this.appendView.put(str, iFrameView);
        }
        return livePusher;
    }

    public static JSONObject deepMerge(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object string = jSONObject2.getString(next);
            if (!jSONObject.has(next)) {
                jSONObject.put(next, string);
            } else if (string instanceof JSONObject) {
                deepMerge((JSONObject) string, jSONObject.getJSONObject(next));
            } else {
                jSONObject.put(next, string);
            }
        }
        return jSONObject;
    }

    public static JSONArray joinOptions(JSONArray jSONArray, JSONArray jSONArray2) {
        JSONArray jSONArray3 = new JSONArray();
        try {
            JSONObject jSONObjectDeepMerge = deepMerge(jSONArray.optJSONObject(2), jSONArray2.optJSONObject(1));
            jSONArray3.put(jSONArray.opt(0));
            jSONArray3.put(jSONArray.opt(1));
            jSONArray3.put(jSONObjectDeepMerge);
            return jSONArray3;
        } catch (Exception unused) {
            return jSONArray;
        }
    }

    public void LivePusher(final IWebview iWebview, final JSONArray jSONArray) {
        final LivePusher[] livePusherArr = {null};
        jSONArray.optJSONArray(1);
        final String strOptString = jSONArray != null ? jSONArray.optString(0) : null;
        HashMap<String, LivePusher> map = this.pusherList;
        if (map != null && strOptString != null) {
            livePusherArr[0] = map.get(strOptString);
        }
        if (livePusherArr[0] == null) {
            PermissionUtil.StreamPermissionRequest streamPermissionRequest = new PermissionUtil.StreamPermissionRequest(iWebview.obtainApp()) { // from class: io.dcloud.media.live.LiveMediaFeatureImpl.1
                public List<String> gramtPermission = new ArrayList();

                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onDenied(String str) {
                }

                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onGranted(String str) {
                    this.gramtPermission.add(str);
                    if (this.gramtPermission.size() < getSystemRequestPermission().length) {
                        return;
                    }
                    livePusherArr[0] = new LivePusher(iWebview, jSONArray);
                    if (livePusherArr[0] != null) {
                        LiveMediaFeatureImpl liveMediaFeatureImpl = LiveMediaFeatureImpl.this;
                        if (liveMediaFeatureImpl.pusherList == null) {
                            liveMediaFeatureImpl.pusherList = new HashMap<>();
                        }
                        LiveMediaFeatureImpl liveMediaFeatureImpl2 = LiveMediaFeatureImpl.this;
                        liveMediaFeatureImpl2.initOptions = jSONArray;
                        liveMediaFeatureImpl2.pusherList.put(strOptString, livePusherArr[0]);
                        livePusherArr[0].setStatusListener(new LivePusherStateListener() { // from class: io.dcloud.media.live.LiveMediaFeatureImpl.1.1
                            @Override // io.dcloud.media.live.LivePusherStateListener
                            public void onRtmpStopped(String str2) {
                                LiveMediaFeatureImpl.this.pusherList.remove(str2);
                            }
                        });
                    }
                    if (LiveMediaFeatureImpl.this.listeners.containsKey(strOptString)) {
                        Map map2 = (Map) LiveMediaFeatureImpl.this.listeners.get(strOptString);
                        for (JSONArray jSONArray2 : map2.keySet()) {
                            livePusherArr[0].addEventListener((IWebview) map2.get(jSONArray2), jSONArray2);
                        }
                        LiveMediaFeatureImpl.this.listeners.remove(strOptString);
                    }
                    if (LiveMediaFeatureImpl.this.appendView.containsKey(strOptString)) {
                        LiveMediaFeatureImpl liveMediaFeatureImpl3 = LiveMediaFeatureImpl.this;
                        liveMediaFeatureImpl3.appendLivePusher(strOptString, (IFrameView) liveMediaFeatureImpl3.appendView.get(strOptString));
                        LiveMediaFeatureImpl.this.appendView.remove(strOptString);
                    }
                    if (LiveMediaFeatureImpl.this.previewMap.containsKey(strOptString)) {
                        livePusherArr[0].preview((IWebview) LiveMediaFeatureImpl.this.previewMap.get(strOptString));
                        LiveMediaFeatureImpl.this.previewMap.remove(strOptString);
                    }
                    if (LiveMediaFeatureImpl.this.pusherOptions.containsKey(strOptString)) {
                        Map map3 = (Map) LiveMediaFeatureImpl.this.pusherOptions.get(strOptString);
                        for (IWebview iWebview2 : map3.keySet()) {
                            livePusherArr[0].setOptions(iWebview2, (JSONObject) map3.get(iWebview2));
                        }
                        LiveMediaFeatureImpl.this.pusherOptions.remove(strOptString);
                    }
                    if (LiveMediaFeatureImpl.this.startMap.containsKey(strOptString)) {
                        LivePusher[] livePusherArr2 = livePusherArr;
                        if (!livePusherArr2[0].isInited) {
                            livePusherArr2[0].initLivePusher(iWebview, LiveMediaFeatureImpl.this.initOptions);
                        }
                        Map map4 = (Map) LiveMediaFeatureImpl.this.startMap.get(strOptString);
                        livePusherArr[0].start((IWebview) map4.get("webView"), (JSONArray) map4.get("array"));
                        LiveMediaFeatureImpl liveMediaFeatureImpl4 = LiveMediaFeatureImpl.this;
                        liveMediaFeatureImpl4.activePusher = livePusherArr[0];
                        liveMediaFeatureImpl4.startMap.remove(strOptString);
                    }
                }
            };
            streamPermissionRequest.setRequestPermission("android.permission.CAMERA", "android.permission.RECORD_AUDIO");
            PermissionUtil.useSystemPermissions(iWebview.getActivity(), new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}, streamPermissionRequest);
        }
    }

    public void addEventListener(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.addEventListener(iWebview, jSONArray);
            return;
        }
        Map<JSONArray, IWebview> map2 = new HashMap<>();
        if (this.listeners.containsKey(jSONArray.optString(0))) {
            map2 = this.listeners.get(jSONArray.optString(0));
        }
        map2.put(jSONArray, iWebview);
        this.listeners.put(jSONArray.optString(0), map2);
    }

    public void close(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.destory(strOptString);
            livePusher.removeFromFrame();
        }
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        super.dispose(str);
        for (Map.Entry<String, LivePusher> entry : this.pusherList.entrySet()) {
            entry.getValue().destory(entry.getKey());
        }
    }

    @Override // io.dcloud.common.DHInterface.IWaiter
    public Object doForFeature(String str, Object obj) {
        if (!"appendToFrameView".equals(str)) {
            return null;
        }
        Object[] objArr = (Object[]) obj;
        return appendLivePusher((String) objArr[1], (AdaFrameView) objArr[0]);
    }

    public String getLivePusherById(IWebview iWebview, JSONArray jSONArray) {
        HashMap<String, LivePusher> map = this.pusherList;
        if (map == null) {
            return null;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (this.pusherList.get(next).getUserId().equals(JSONUtil.getString(jSONArray, 0))) {
                try {
                    return JSUtil.wrapJsVar(new JSONObject("{uuid:'" + next + "'}"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.StandardFeature, io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        super.init(absMgr, str);
        this.featureMgr = absMgr;
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onPause() {
        super.onPause();
        LivePusher livePusher = this.activePusher;
        if (livePusher != null) {
            livePusher.pause(null, null);
        }
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onResume() {
        super.onResume();
        LivePusher livePusher = this.activePusher;
        if (livePusher != null) {
            livePusher.resume(null, null);
        }
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onStart(Context context, Bundle bundle, String[] strArr) {
        super.onStart(context, bundle, strArr);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onStop() {
        super.onStop();
        Log.d("dqqdo", " onStop  ------  " + this.activePusher);
        LivePusher livePusher = this.activePusher;
        if (livePusher != null) {
            livePusher.stop(null, null);
        }
    }

    public void pause(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.pause(iWebview, jSONArray);
            this.activePusher = null;
        }
    }

    public void preview(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.preview(iWebview);
        } else {
            this.previewMap.put(strOptString, iWebview);
        }
    }

    public void resize(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.resize(iWebview, jSONArray);
        }
    }

    public void resume(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.resume(iWebview, jSONArray);
            this.activePusher = livePusher;
        }
    }

    public void setOptions(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.setOptions(iWebview, jSONArray.optJSONObject(1));
            return;
        }
        Map<IWebview, JSONObject> map2 = new HashMap<>();
        if (this.pusherOptions.containsKey(strOptString)) {
            map2 = this.pusherOptions.get(strOptString);
        }
        map2.put(iWebview, jSONArray.optJSONObject(1));
        this.pusherOptions.put(strOptString, map2);
    }

    public void snapshot(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.snapshot(iWebview, jSONArray);
        }
    }

    public void start(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            if (!livePusher.isInited) {
                livePusher.initLivePusher(iWebview, this.initOptions);
            }
            livePusher.start(iWebview, jSONArray);
            this.activePusher = livePusher;
            return;
        }
        Map<String, Object> map2 = new HashMap<>();
        if (this.startMap.containsKey(strOptString)) {
            map2 = this.startMap.get(strOptString);
        }
        map2.put("webView", iWebview);
        map2.put("array", jSONArray);
        this.startMap.put(strOptString, map2);
    }

    public void stop(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        if (livePusher != null) {
            livePusher.stop(iWebview, jSONObjectOptJSONObject);
            this.activePusher = null;
        }
    }

    public void switchCamera(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        HashMap<String, LivePusher> map = this.pusherList;
        LivePusher livePusher = (map == null || strOptString == null) ? null : map.get(strOptString);
        if (livePusher != null) {
            livePusher.switchCamera(iWebview, jSONArray);
        }
    }
}
