package io.dcloud.feature.sdk;

import android.content.Context;
import android.content.Intent;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleCloseButtontCallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleMenuButtontCallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPPreInitCallback;
import io.dcloud.feature.sdk.Interface.IMenuButtonClickCallBack;
import io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack;
import io.dcloud.feature.unimp.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPSDK {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static DCUniMPSDK f6595a;
    public static c b;

    public static DCUniMPSDK getInstance() {
        if (f6595a == null) {
            f6595a = new DCUniMPSDK();
            b = c.a();
        }
        return f6595a;
    }

    public String getAppBasePath(Context context) {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context);
        }
        return null;
    }

    public JSONObject getAppVersionInfo(String str) {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(str);
        }
        return null;
    }

    public void initialize(Context context, DCSDKInitConfig dCSDKInitConfig) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(context, dCSDKInitConfig, (Class) null, (IDCUniMPPreInitCallback) null);
        }
    }

    public boolean isExistsApp(String str) {
        c cVar = b;
        if (cVar != null) {
            return cVar.b(str);
        }
        return false;
    }

    public boolean isInitialize() {
        c cVar = b;
        if (cVar != null) {
            return cVar.b();
        }
        return false;
    }

    public IUniMP openUniMP(Context context, String str) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str);
        }
        throw new Exception("Not initialized");
    }

    public void recovery() {
        c cVar = b;
        if (cVar != null) {
            cVar.c();
            b = null;
        }
    }

    public void releaseWgtToRunPathFromePath(String str, String str2, ICallBack iCallBack) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(str, str2, iCallBack);
        }
    }

    public void setCapsuleCloseButtonClickCallBack(IDCUniMPOnCapsuleCloseButtontCallBack iDCUniMPOnCapsuleCloseButtontCallBack) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(iDCUniMPOnCapsuleCloseButtontCallBack);
        }
    }

    public void setCapsuleMenuButtonClickCallBack(IDCUniMPOnCapsuleMenuButtontCallBack iDCUniMPOnCapsuleMenuButtontCallBack) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(iDCUniMPOnCapsuleMenuButtontCallBack);
        }
    }

    public void setDefMenuButtonClickCallBack(IMenuButtonClickCallBack iMenuButtonClickCallBack) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(iMenuButtonClickCallBack);
        }
    }

    public void setOnUniMPEventCallBack(IOnUniMPEventCallBack iOnUniMPEventCallBack) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(iOnUniMPEventCallBack);
        }
    }

    public void setUniMPOnCloseCallBack(IUniMPOnCloseCallBack iUniMPOnCloseCallBack) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(iUniMPOnCloseCallBack);
        }
    }

    public boolean startActivityForUniMPTask(String str, Intent intent, int i, int i2) {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(str, intent, i, i2);
        }
        return false;
    }

    public void uniMPCallBackTo(String str, String str2, String str3, Object obj, boolean z) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(str, str2, str3, obj, z);
        }
    }

    public <T extends DCUniMPIntentService> void initialize(Context context, Class<T> cls, DCSDKInitConfig dCSDKInitConfig) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(context, dCSDKInitConfig, cls, (IDCUniMPPreInitCallback) null);
        }
    }

    public boolean startActivityForUniMPTask(String str, Intent intent) {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(str, intent, -1, -1);
        }
        return false;
    }

    public IUniMP openUniMP(Context context, String str, JSONObject jSONObject) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str, jSONObject);
        }
        throw new Exception("Not initialized");
    }

    public void initialize(Context context, DCSDKInitConfig dCSDKInitConfig, IDCUniMPPreInitCallback iDCUniMPPreInitCallback) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(context, dCSDKInitConfig, (Class) null, iDCUniMPPreInitCallback);
        }
    }

    public <T extends DCUniMPIntentService> void initialize(Context context, DCSDKInitConfig dCSDKInitConfig, Class<T> cls, IDCUniMPPreInitCallback iDCUniMPPreInitCallback) {
        c cVar = b;
        if (cVar != null) {
            cVar.a(context, dCSDKInitConfig, cls, iDCUniMPPreInitCallback);
        }
    }

    public IUniMP openUniMP(Context context, String str, Class cls, JSONObject jSONObject) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str, cls, jSONObject);
        }
        throw new Exception("Not initialized");
    }

    public IUniMP openUniMP(Context context, String str, Class cls) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str, cls);
        }
        throw new Exception("Not initialized");
    }

    public IUniMP openUniMP(Context context, String str, String str2) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str, str2);
        }
        throw new Exception("Not initialized");
    }

    public IUniMP openUniMP(Context context, String str, Class cls, String str2) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str, cls, str2);
        }
        throw new Exception("Not initialized");
    }

    public IUniMP openUniMP(Context context, String str, Class cls, String str2, JSONObject jSONObject) throws Exception {
        c cVar = b;
        if (cVar != null) {
            return cVar.a(context, str, cls, str2, jSONObject);
        }
        throw new Exception("Not initialized");
    }
}
