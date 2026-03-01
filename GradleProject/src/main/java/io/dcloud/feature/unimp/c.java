package io.dcloud.feature.unimp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.ReflectUtils;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.common.util.ZipUtils;
import io.dcloud.feature.sdk.DCSDKInitConfig;
import io.dcloud.feature.sdk.DCUniMPIntentService;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleCloseButtontCallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleMenuButtontCallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPPreInitCallback;
import io.dcloud.feature.sdk.Interface.IMenuButtonClickCallBack;
import io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c {
    public static c c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6695a;
    public io.dcloud.feature.unimp.f.a b;

    public c() {
        new ArrayList();
    }

    public static c a() {
        if (c == null) {
            c = new c();
        }
        return c;
    }

    public boolean b() {
        return this.b != null;
    }

    public void c() {
        this.b.a();
        this.b = null;
    }

    public boolean b(String str) {
        if (this.f6695a == null) {
            return false;
        }
        return new File((a(this.f6695a) + str) + DeviceInfo.sSeparatorChar + BaseInfo.APP_WWW_FS_DIR).exists();
    }

    public <T extends DCUniMPIntentService> void a(Context context, DCSDKInitConfig dCSDKInitConfig, Class<T> cls, IDCUniMPPreInitCallback iDCUniMPPreInitCallback) {
        a(context);
        new Handler(context.getMainLooper());
        String appName = RuningAcitvityUtil.getAppName(ReflectUtils.getApplicationContext());
        if (TextUtils.isEmpty(appName) || appName.contains(":unimp")) {
            if (iDCUniMPPreInitCallback != null) {
                iDCUniMPPreInitCallback.onInitFinished(false);
                return;
            }
            return;
        }
        this.f6695a = context;
        io.dcloud.feature.unimp.f.a aVarE = a.e();
        this.b = aVarE;
        aVarE.a(this.f6695a, dCSDKInitConfig);
        if (cls != null) {
            this.b.c(appName, cls.getCanonicalName());
        }
        iDCUniMPPreInitCallback.onInitFinished(true);
    }

    public IUniMP a(Context context, String str) throws Exception {
        return a(context, str, (Class) null, (String) null, (JSONObject) null);
    }

    public IUniMP a(Context context, String str, JSONObject jSONObject) throws Exception {
        return a(context, str, (Class) null, (String) null, jSONObject);
    }

    public IUniMP a(Context context, String str, Class cls, JSONObject jSONObject) throws Exception {
        return a(context, str, cls, (String) null, jSONObject);
    }

    public IUniMP a(Context context, String str, Class cls) throws Exception {
        return a(context, str, cls, (String) null, (JSONObject) null);
    }

    public IUniMP a(Context context, String str, String str2) throws Exception {
        return a(context, str, (Class) null, str2, (JSONObject) null);
    }

    public IUniMP a(Context context, String str, Class cls, String str2) throws Exception {
        return a(context, str, cls, str2, (JSONObject) null);
    }

    public IUniMP a(Context context, String str, Class cls, String str2, JSONObject jSONObject) throws Exception {
        if (b()) {
            return this.b.a(str, cls != null ? cls.getName() : null, str2, jSONObject);
        }
        throw new Exception("Not initialized");
    }

    public String a(Context context) {
        AndroidResources.initAndroidResources(context);
        BaseInfo.parseControl();
        DeviceInfo.initPath(context);
        return BaseInfo.sCacheFsAppsPath;
    }

    public JSONObject a(String str) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.c(str);
        }
        return null;
    }

    public void a(String str, String str2, ICallBack iCallBack) {
        String string;
        int i = -1;
        if (!b()) {
            iCallBack.onCallBack(-1, "Not initialized");
            return;
        }
        File file = new File(str2);
        if (file.exists() && file.isFile() && str2.endsWith(".wgt")) {
            string = "";
        } else {
            string = this.f6695a.getString(io.dcloud.feature.sdk.b.b);
            i = -4;
        }
        if (TextUtils.isEmpty(string)) {
            String str3 = a(this.f6695a) + str;
            String str4 = str3 + DeviceInfo.sSeparatorChar + BaseInfo.APP_WWW_FS_DIR;
            File file2 = new File(str3);
            if (file2.exists()) {
                file2.delete();
            }
            try {
                ZipUtils.upZipFile(file, str4);
            } catch (IOException e2) {
                e2.printStackTrace();
                i = -5;
                string = e2.getMessage();
            }
            if (new File(str4 + BaseInfo.sConfigXML).exists()) {
                i = 1;
            } else {
                string = this.f6695a.getString(io.dcloud.feature.sdk.b.d);
            }
        }
        if (iCallBack != null) {
            iCallBack.onCallBack(i, string);
        }
    }

    public void a(String str, String str2, String str3, Object obj, boolean z) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            aVar.a(str, str2, str3, obj, z);
        }
    }

    public void a(IMenuButtonClickCallBack iMenuButtonClickCallBack) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            aVar.a(iMenuButtonClickCallBack);
        }
    }

    public void a(IUniMPOnCloseCallBack iUniMPOnCloseCallBack) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            aVar.a(iUniMPOnCloseCallBack);
        }
    }

    public void a(IOnUniMPEventCallBack iOnUniMPEventCallBack) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            aVar.a(iOnUniMPEventCallBack);
        }
    }

    public void a(IDCUniMPOnCapsuleCloseButtontCallBack iDCUniMPOnCapsuleCloseButtontCallBack) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            aVar.a(iDCUniMPOnCapsuleCloseButtontCallBack);
        }
    }

    public void a(IDCUniMPOnCapsuleMenuButtontCallBack iDCUniMPOnCapsuleMenuButtontCallBack) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            aVar.a(iDCUniMPOnCapsuleMenuButtontCallBack);
        }
    }

    public boolean a(String str, Intent intent, int i, int i2) {
        io.dcloud.feature.unimp.f.a aVar = this.b;
        if (aVar != null) {
            return aVar.a(str, intent, i, i2);
        }
        return false;
    }
}
