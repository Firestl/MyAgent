package io.dcloud.weex;

import android.app.Application;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.utils.WXFileUtils;
import io.dcloud.feature.uniapp.UniAppHookProxy;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class MoudlesLoader {
    public static MoudlesLoader sLoader;

    private void createAppMoudle(String str, Application application, boolean z) {
        try {
            Class<?> cls = Class.forName(str);
            Object objNewInstance = cls.newInstance();
            if (!(objNewInstance instanceof AppHookProxy)) {
                Method method = cls.getMethod("onCreate", Application.class);
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(objNewInstance, application);
                }
            } else if (z) {
                ((AppHookProxy) objNewInstance).onCreate(application);
            } else if (objNewInstance instanceof UniAppHookProxy) {
                ((UniAppHookProxy) objNewInstance).onSubProcessCreate(application);
            }
        } catch (Throwable unused) {
        }
    }

    public static MoudlesLoader getInstance() {
        if (sLoader == null) {
            synchronized (MoudlesLoader.class) {
                if (sLoader == null) {
                    sLoader = new MoudlesLoader();
                }
            }
        }
        return sLoader;
    }

    private JSONObject getPluginsValue(Application application) {
        String strLoadAsset = WXFileUtils.loadAsset("dcloud_uniplugins.json", application);
        if (TextUtils.isEmpty(strLoadAsset)) {
            return null;
        }
        return JSON.parseObject(strLoadAsset);
    }

    private void initMoudle(JSONObject jSONObject, Application application) {
        if (jSONObject == null) {
            return;
        }
        String string = jSONObject.getString("hooksClass");
        if (!TextUtils.isEmpty(string)) {
            createAppMoudle(string, application, true);
        }
        JSONArray jSONArray = jSONObject.getJSONArray("plugins");
        if (jSONArray == null || jSONArray == null || jSONArray.size() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 != null) {
                registerMoudle(jSONObject2.getString("name"), jSONObject2.getString("class"), jSONObject2.getString("type"));
            }
        }
    }

    private void registerMoudle(String str, String str2, String str3) {
        try {
            Class<?> cls = Class.forName(str2);
            if (TextUtils.isEmpty(str3) || str3.equalsIgnoreCase("module")) {
                WXSDKEngine.registerModule(str, cls);
            } else if (str3.equalsIgnoreCase(WXBridgeManager.COMPONENT)) {
                WXSDKEngine.registerComponent(str, (Class<? extends WXComponent>) cls);
            }
        } catch (Throwable unused) {
        }
    }

    public void onCreate(Application application) {
        JSONArray jSONArray;
        JSONObject pluginsValue = getPluginsValue(application);
        if (pluginsValue == null || (jSONArray = pluginsValue.getJSONArray("nativePlugins")) == null || jSONArray.size() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.size(); i++) {
            initMoudle(jSONArray.getJSONObject(i), application);
        }
    }

    public void onSubProcess(Application application) {
        JSONArray jSONArray;
        JSONObject pluginsValue = getPluginsValue(application);
        if (pluginsValue == null || (jSONArray = pluginsValue.getJSONArray("nativePlugins")) == null || jSONArray.size() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.size(); i++) {
            createAppMoudle(jSONArray.getJSONObject(i).getString("hooksClass"), application, false);
        }
    }
}
