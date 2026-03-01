package io.dcloud.feature.weex.extend;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.feature.internal.sdk.SDK;
import io.dcloud.feature.uniapp.common.UniModule;
import io.dcloud.feature.weex.WeexInstanceMgr;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPModule extends UniModule {
    @JSMethod
    public void getUniMPVersion(String str, final JSCallback jSCallback) {
        final HashMap map = new HashMap();
        if (SDK.isUniMPSDK()) {
            map.put("type", Constants.Event.FAIL);
            map.put("code", -3);
            map.put("message", "No permission");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str) || !str.startsWith("__UNI__")) {
            map.put("code", -1);
            map.put("type", Constants.Event.FAIL);
            map.put("message", "Parameter error");
            if (jSCallback != null) {
                jSCallback.invoke(map);
            }
        }
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview != null) {
            WeexInstanceMgr.self().doForFeature(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebviewFindWebview.obtainApp(), WeexInstanceMgr.self().getUniMPFeature(), "getUniMPVersion", new Object[]{str, new ICallBack() { // from class: io.dcloud.feature.weex.extend.DCUniMPModule.3
                @Override // io.dcloud.common.DHInterface.ICallBack
                public Object onCallBack(int i, Object obj) {
                    if (obj == null) {
                        map.put("type", Constants.Event.FAIL);
                        map.put("code", -4);
                        map.put("message", "The unimp resource does not exist");
                        JSCallback jSCallback2 = jSCallback;
                        if (jSCallback2 == null) {
                            return null;
                        }
                        jSCallback2.invoke(map);
                        return null;
                    }
                    JSONObject object = JSON.parseObject(obj.toString());
                    map.put("type", "success");
                    map.put("code", 0);
                    map.put("versionInfo", object);
                    JSCallback jSCallback3 = jSCallback;
                    if (jSCallback3 == null) {
                        return null;
                    }
                    jSCallback3.invoke(map);
                    return null;
                }
            }}});
        }
    }

    @JSMethod
    public void installUniMP(String str, final JSCallback jSCallback) {
        JSONObject object;
        final HashMap map = new HashMap();
        try {
            object = JSON.parseObject(str);
        } catch (Exception unused) {
            object = null;
        }
        if (SDK.isUniMPSDK()) {
            map.put("type", Constants.Event.FAIL);
            map.put("code", -10);
            map.put("message", "No permission");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        if (object == null) {
            map.put("code", -1);
            map.put("type", Constants.Event.FAIL);
            map.put("message", "Parameter error");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        String string = object.getString("appid");
        String string2 = object.getString("wgtFile");
        ICallBack iCallBack = new ICallBack() { // from class: io.dcloud.feature.weex.extend.DCUniMPModule.2
            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                if (i == 1) {
                    map.put("code", 0);
                    map.put("type", "success");
                    JSCallback jSCallback2 = jSCallback;
                    if (jSCallback2 == null) {
                        return null;
                    }
                    jSCallback2.invoke(map);
                    return null;
                }
                map.put("code", Integer.valueOf(i));
                map.put("type", Constants.Event.FAIL);
                map.put("message", obj != null ? obj.toString() : "Unknown error");
                JSCallback jSCallback3 = jSCallback;
                if (jSCallback3 == null) {
                    return null;
                }
                jSCallback3.invoke(map);
                return null;
            }
        };
        IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(this.mWXSDKInstance);
        if (iWebviewFindWebview != null) {
            WeexInstanceMgr.self().doForFeature(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebviewFindWebview.obtainApp(), WeexInstanceMgr.self().getUniMPFeature(), "installUniMP", new Object[]{string, string2, iCallBack}});
        }
    }

    @JSMethod
    public void openUniMP(final String str, final JSCallback jSCallback) {
        MessageHandler.post(new Runnable() { // from class: io.dcloud.feature.weex.extend.DCUniMPModule.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject object;
                try {
                    object = JSON.parseObject(str);
                } catch (Exception unused) {
                    object = null;
                }
                try {
                    final HashMap map = new HashMap();
                    if (SDK.isUniMPSdk) {
                        map.put("type", Constants.Event.FAIL);
                        map.put("code", -10);
                        map.put("message", "No permission");
                        if (jSCallback != null) {
                            jSCallback.invoke(map);
                            return;
                        }
                        return;
                    }
                    if (object == null) {
                        map.put("code", -1);
                        map.put("type", Constants.Event.FAIL);
                        map.put("message", "Parameter error");
                        if (jSCallback != null) {
                            jSCallback.invoke(map);
                            return;
                        }
                        return;
                    }
                    IWebview iWebviewFindWebview = WeexInstanceMgr.self().findWebview(DCUniMPModule.this.mWXSDKInstance);
                    if (iWebviewFindWebview != null) {
                        ICallBack iCallBack = new ICallBack() { // from class: io.dcloud.feature.weex.extend.DCUniMPModule.1.1
                            @Override // io.dcloud.common.DHInterface.ICallBack
                            public Object onCallBack(int i, Object obj) {
                                map.put("code", Integer.valueOf(i));
                                if (i == 0) {
                                    map.put("type", "success");
                                    JSCallback jSCallback2 = jSCallback;
                                    if (jSCallback2 == null) {
                                        return null;
                                    }
                                    jSCallback2.invoke(map);
                                    return null;
                                }
                                map.put("type", Constants.Event.FAIL);
                                map.put("message", obj != null ? obj.toString() : "Unknown error");
                                JSCallback jSCallback3 = jSCallback;
                                if (jSCallback3 == null) {
                                    return null;
                                }
                                jSCallback3.invoke(map);
                                return null;
                            }
                        };
                        object.put("appInfo", (Object) iWebviewFindWebview.obtainApp().obtainAppInfo());
                        WeexInstanceMgr.self().doForFeature(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebviewFindWebview.obtainApp(), WeexInstanceMgr.self().getUniMPFeature(), "openUniMP", new Object[]{object.toJSONString(), iCallBack}});
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
