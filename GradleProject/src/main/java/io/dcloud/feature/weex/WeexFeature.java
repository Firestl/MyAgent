package io.dcloud.feature.weex;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IWaiter;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.ReceiveSystemEventVoucher;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class WeexFeature extends StandardFeature implements IWaiter, ReceiveSystemEventVoucher {
    public boolean isRegisterAllEvent = false;
    public IApp mApp;

    private void createUniNView(IWebview iWebview, ViewGroup viewGroup, String str, JSONObject jSONObject) {
        createUniNView(iWebview, viewGroup, str, jSONObject, WXBaseWrapper.DE_INDEX);
    }

    private Object createWeexService(Object[] objArr) {
        IApp iApp = (IApp) objArr[0];
        registerAllEvent(iApp);
        JSONObject jSONObject = (JSONObject) objArr[1];
        return WeexInstanceMgr.self().createWeexService(iApp, (ViewGroup) objArr[2], (String) objArr[3], jSONObject);
    }

    private Object createWeexWindow(Object[] objArr) {
        IWebview iWebview = (IWebview) objArr[0];
        registerAllEvent(iWebview.obtainApp());
        return WeexInstanceMgr.self().createWeexView(iWebview, (ViewGroup) objArr[1], (JSONObject) objArr[2], (String) objArr[3], WXBaseWrapper.DE_INDEX);
    }

    public String callNativeModule(String str, com.alibaba.fastjson.JSONObject jSONObject) {
        Object objCallNativeModule = WXBridgeManager.getInstance().callNativeModule(str, jSONObject.getString("plugin"), jSONObject.getString("method"), JSON.parseArray(jSONObject.getString("args")), (com.alibaba.fastjson.JSONObject) null);
        if (objCallNativeModule != null) {
            if (objCallNativeModule instanceof com.alibaba.fastjson.JSONObject) {
                return JSUtil.wrapJsVar(JSONUtil.createJSONObject(((com.alibaba.fastjson.JSONObject) objCallNativeModule).toJSONString()));
            }
            if (objCallNativeModule instanceof HashMap) {
                return JSUtil.wrapJsVar(JSONUtil.createJSONObject(new com.alibaba.fastjson.JSONObject((HashMap) objCallNativeModule).toJSONString()));
            }
            if (objCallNativeModule instanceof JSONArray) {
                return JSUtil.wrapJsVar(JSONUtil.createJSONArray(((JSONArray) objCallNativeModule).toJSONString()));
            }
            if (objCallNativeModule instanceof String) {
                return JSUtil.wrapJsVar(String.valueOf(objCallNativeModule));
            }
            if (objCallNativeModule instanceof JSONObject) {
                return JSUtil.wrapJsVar((JSONObject) objCallNativeModule);
            }
            if (objCallNativeModule instanceof org.json.JSONArray) {
                return JSUtil.wrapJsVar((org.json.JSONArray) objCallNativeModule);
            }
        }
        return JSUtil.wrapJsVar("");
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        super.dispose(str);
        if (this.mApp != null) {
            onDestroy();
            if (this.isRegisterAllEvent) {
                unregisterSysEvent(this.mApp);
                this.isRegisterAllEvent = false;
                this.mApp = null;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
    @Override // io.dcloud.common.DHInterface.IWaiter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object doForFeature(java.lang.String r8, java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 618
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex.WeexFeature.doForFeature(java.lang.String, java.lang.Object):java.lang.Object");
    }

    @Override // io.dcloud.common.DHInterface.StandardFeature, io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        super.init(absMgr, str);
        WeexInstanceMgr.self().init(absMgr);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        WeexInstanceMgr.self().onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
        IApp iApp = this.mApp;
        if (iApp == null || iApp.getQuitModel() != 2) {
            WeexInstanceMgr.self().onActivityDestroy();
        } else {
            WeexInstanceMgr.self().onActivityDestroy(false);
        }
        WeexInstanceMgr.self().setUniServiceCreated(false, null);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onPause() {
        super.onPause();
        WeexInstanceMgr.self().onActivityPause();
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        WeexInstanceMgr.self().onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onResume() {
        super.onResume();
        WeexInstanceMgr.self().onActivityResume();
    }

    @Override // io.dcloud.common.DHInterface.BaseFeature, io.dcloud.common.DHInterface.IBoot
    public void onStart(Context context, Bundle bundle, String[] strArr) {
        super.onStart(context, bundle, strArr);
        if (context instanceof Application) {
            WeexInstanceMgr.self().initWeexEnv((Application) context);
        }
    }

    public void registerAllEvent(IApp iApp) {
        this.mApp = iApp;
        if (this.isRegisterAllEvent) {
            return;
        }
        this.isRegisterAllEvent = true;
        unregisterSysEvent(iApp);
        registerSysEvent(this.mApp);
    }

    private void createUniNView(IWebview iWebview, ViewGroup viewGroup, String str, JSONObject jSONObject, int i) {
        WeexInstanceMgr.self().createWeexView(iWebview, viewGroup, jSONObject, str, i);
    }
}
