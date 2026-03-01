package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.feature.internal.sdk.SDK;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class BaseFeature implements IFeature, IBoot, IDPlugin, ISysEventListener, IReflectAble {
    public String mFeatureName = null;
    public AbsMgr mFeatureMgr = null;
    public Context mApplicationContext = null;
    public Context mDPluginContext = null;
    public Activity mDPluginActivity = null;
    public ArrayList<BaseModule> mModules = null;

    public static abstract class BaseModule {
        public Context mApplicationContext;
        public String featureName = null;
        public String name = null;
        public String id = null;
        public String description = null;

        public String getFullDescription() {
            return this.featureName + this.description;
        }

        public void init(Context context) {
            this.mApplicationContext = context;
        }

        public abstract JSONObject toJSONObject() throws JSONException;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
    }

    public boolean doHandleAction(String str) {
        return false;
    }

    public String execute(IWebview iWebview, String str, JSONArray jSONArray) {
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public String execute(IWebview iWebview, String str, String[] strArr) {
        return null;
    }

    public BaseModule getBaseModuleById(String str) {
        ArrayList<BaseModule> arrayList = this.mModules;
        if (arrayList == null) {
            return null;
        }
        for (BaseModule baseModule : arrayList) {
            if (baseModule.id.equals(str)) {
                return baseModule;
            }
        }
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IDPlugin
    public Activity getDPluginActivity() {
        return this.mDPluginActivity;
    }

    @Override // io.dcloud.common.DHInterface.IDPlugin
    public Context getDPluginContext() {
        Context context = this.mDPluginContext;
        return context == null ? this.mApplicationContext : context;
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.mFeatureMgr = absMgr;
        this.mApplicationContext = absMgr.getContext();
        this.mFeatureName = str;
    }

    @Override // io.dcloud.common.DHInterface.IDPlugin
    public void initDPlugin(Context context, Activity activity) {
        this.mDPluginContext = context;
        this.mDPluginActivity = activity;
    }

    public boolean isOldMode() {
        return false;
    }

    public ArrayList<BaseModule> loadModules() {
        ArrayList<BaseModule> arrayList = this.mModules;
        if (arrayList != null) {
            return arrayList;
        }
        this.mModules = new ArrayList<>();
        HashMap map = (HashMap) this.mFeatureMgr.processEvent(IMgr.MgrType.FeatureMgr, 4, this.mFeatureName);
        if (SDK.isUniMPSDK() && map != null && map.containsKey("oauth-igetui")) {
            map.remove("oauth-igetui");
        }
        if (map != null && !map.isEmpty()) {
            for (String str : map.keySet()) {
                try {
                    Object objNewInstance = Class.forName((String) map.get(str)).newInstance();
                    if (objNewInstance instanceof BaseModule) {
                        BaseModule baseModule = (BaseModule) objNewInstance;
                        baseModule.init(this.mApplicationContext);
                        baseModule.name = str;
                        baseModule.featureName = this.mFeatureName;
                        if (baseModule.id == null) {
                            baseModule.id = str;
                        }
                        this.mModules.add(baseModule);
                    }
                } catch (ClassNotFoundException e2) {
                    Logger.e(e2.getLocalizedMessage());
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                } catch (InstantiationException e4) {
                    e4.printStackTrace();
                }
            }
        }
        return this.mModules;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onEventExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        return false;
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public final boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        if (sysEventType == ISysEventListener.SysEventType.onActivityResult) {
            Object[] objArr = (Object[]) obj;
            onActivityResult(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (Intent) objArr[2]);
        } else if (sysEventType == ISysEventListener.SysEventType.onStart) {
            Object[] objArr2 = (Object[]) obj;
            onStart((Context) objArr2[0], (Bundle) objArr2[1], (String[]) objArr2[3]);
        } else if (sysEventType == ISysEventListener.SysEventType.onPause) {
            onPause();
        } else if (sysEventType == ISysEventListener.SysEventType.onStop) {
            onStop();
        } else if (sysEventType == ISysEventListener.SysEventType.onResume) {
            onResume();
        } else if (sysEventType == ISysEventListener.SysEventType.onNewIntent) {
            onNewIntent();
        } else if (sysEventType == ISysEventListener.SysEventType.onSaveInstanceState) {
            if (obj instanceof Bundle) {
                onSaveInstanceState((Bundle) obj);
            }
        } else {
            if (sysEventType != ISysEventListener.SysEventType.onRequestPermissionsResult) {
                return onEventExecute(sysEventType, obj);
            }
            Object[] objArr3 = (Object[]) obj;
            onRequestPermissionsResult(((Integer) objArr3[0]).intValue(), (String[]) objArr3[1], (int[]) objArr3[2]);
        }
        return false;
    }

    public void onLowMemory() {
    }

    public void onNewIntent() {
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onPause() {
    }

    public void onReceiver(Intent intent) {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onResume() {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onStart(Context context, Bundle bundle, String[] strArr) {
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onStop() {
    }

    public final void registerSysEvent(IWebview iWebview, ISysEventListener.SysEventType sysEventType) {
        registerSysEvent(iWebview.obtainApp(), sysEventType);
    }

    public JSONArray toModuleJSONArray() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        ArrayList<BaseModule> arrayList = this.mModules;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                jSONArray.put(this.mModules.get(i).toJSONObject());
            }
        }
        return jSONArray;
    }

    public final void unregisterSysEvent(IWebview iWebview, ISysEventListener.SysEventType sysEventType) {
        unregisterSysEvent(iWebview.obtainApp(), sysEventType);
    }

    public final void registerSysEvent(IApp iApp, ISysEventListener.SysEventType sysEventType) {
        iApp.registerSysEventListener(this, sysEventType);
    }

    public final void unregisterSysEvent(IApp iApp, ISysEventListener.SysEventType sysEventType) {
        iApp.unregisterSysEventListener(this, sysEventType);
    }

    public final void registerSysEvent(IWebview iWebview) {
        registerSysEvent(iWebview, ISysEventListener.SysEventType.AllSystemEvent);
    }

    public final void unregisterSysEvent(IWebview iWebview) {
        unregisterSysEvent(iWebview, ISysEventListener.SysEventType.AllSystemEvent);
    }

    public final void registerSysEvent(IApp iApp) {
        registerSysEvent(iApp, ISysEventListener.SysEventType.AllSystemEvent);
    }

    public final void unregisterSysEvent(IApp iApp) {
        unregisterSysEvent(iApp, ISysEventListener.SysEventType.AllSystemEvent);
    }
}
