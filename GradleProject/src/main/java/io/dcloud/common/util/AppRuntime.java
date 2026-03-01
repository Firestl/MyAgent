package io.dcloud.common.util;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.INativeAppInfo;
import io.dcloud.common.DHInterface.IUniInstanceMgr;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.ui.b;
import io.dcloud.common.util.BaseInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AppRuntime {
    public static IUniInstanceMgr getInstanceMgr() {
        return (IUniInstanceMgr) PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexInstanceMgr", "self");
    }

    public static boolean hasPrivacyForNotShown(Context context) {
        return b.a().b(context);
    }

    public static void initUniappPlugin(Application application) {
        IUniInstanceMgr instanceMgr = getInstanceMgr();
        if (instanceMgr != null) {
            instanceMgr.initUniappPlugin(application);
        }
    }

    public static void initWeex(INativeAppInfo iNativeAppInfo) {
        IUniInstanceMgr iUniInstanceMgr = (IUniInstanceMgr) PlatformUtil.invokeMethod("io.dcloud.feature.weex.WeexInstanceMgr", "self");
        if (iUniInstanceMgr != null) {
            iUniInstanceMgr.initWeexEnv(iNativeAppInfo);
        }
    }

    public static void initX5(Application application) {
        PlatformUtil.invokeMethod("io.dcloud.feature.x5.X5InitImpl", "init", null, new Class[]{Application.class}, new Object[]{application});
    }

    public static boolean isAppResourcesInAssetsPath(Context context, String str) {
        String path;
        JSONObject jSONObjectCreateJSONObject;
        if (PdrUtil.isEmpty(str)) {
            if (PdrUtil.isEmpty(BaseInfo.sDefaultBootApp)) {
                BaseInfo.parseControl();
            }
            str = BaseInfo.sDefaultBootApp;
            if (PdrUtil.isEmpty(str)) {
                return false;
            }
        }
        if (BaseInfo.isBase(context)) {
            return false;
        }
        boolean z = BaseInfo.ISDEBUG || DHFile.hasFile();
        String str2 = "apps/" + str + "/www/" + BaseInfo.sConfigXML;
        if (z) {
            path = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName();
        } else {
            path = context.getFilesDir().getPath();
        }
        String str3 = path + "/" + str2;
        try {
            JSONObject configData = PdrUtil.getConfigData(context, str, str2, true);
            String string = (configData == null || (jSONObjectCreateJSONObject = JSONUtil.createJSONObject(configData.getString("version"))) == null) ? "" : jSONObjectCreateJSONObject.getString("name");
            JSONObject configData2 = PdrUtil.getConfigData(context, str, str3, false);
            return BaseInfo.BaseAppInfo.compareVersion(string, configData2 != null ? JSONUtil.createJSONObject(configData2.getString("version")).getString("name") : "");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isUniApp(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("__UNI__");
    }

    public static InputStream loadWeexAsset(String str, Context context) {
        if ((str.startsWith("weex-main-jsfm") || str.startsWith("uni-jsframework")) && str.endsWith(".js") && context.getPackageName().equals("io.dcloud.HBuilder") && context.getExternalCacheDir() != null) {
            File file = new File(context.getExternalCacheDir().getPath() + File.separator + str);
            if (file.exists()) {
                try {
                    return new FileInputStream(file);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void onSubProcess(Application application) {
        IUniInstanceMgr instanceMgr = getInstanceMgr();
        if (instanceMgr != null) {
            instanceMgr.onSubProcess(application);
        }
    }

    public static void restartWeex(Application application, ICallBack iCallBack, String str) {
        IUniInstanceMgr instanceMgr = getInstanceMgr();
        if (instanceMgr != null) {
            instanceMgr.restartWeex(application, iCallBack, str);
        }
    }
}
