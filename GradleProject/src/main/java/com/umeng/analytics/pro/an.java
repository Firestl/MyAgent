package com.umeng.analytics.pro;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ModelHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class an {
    public static JSONObject a(Context context, String str) {
        JSONObject jSONObject = null;
        try {
            am amVar = new am();
            String uMId = UMUtils.getUMId(context);
            if (TextUtils.isEmpty(uMId)) {
                return null;
            }
            amVar.a(uMId);
            String appkey = UMUtils.getAppkey(context);
            if (TextUtils.isEmpty(appkey)) {
                return null;
            }
            amVar.b(appkey);
            amVar.c(UMUtils.getAppVersionName(context));
            amVar.d("9.6.8");
            amVar.e(UMUtils.getChannel(context));
            amVar.f(Build.VERSION.SDK_INT + "");
            amVar.g(Build.BRAND);
            amVar.h(Build.MODEL);
            String[] localeInfo = DeviceConfig.getLocaleInfo(context);
            amVar.i(localeInfo[1]);
            amVar.j(localeInfo[0]);
            int[] resolutionArray = DeviceConfig.getResolutionArray(context);
            amVar.b(Integer.valueOf(resolutionArray[1]));
            amVar.a(Integer.valueOf(resolutionArray[0]));
            amVar.k(ar.a(context, "install_datetime", ""));
            try {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(am.f5170a, amVar.a());
                    jSONObject2.put(am.c, amVar.c());
                    jSONObject2.put(am.b, amVar.b());
                    jSONObject2.put(am.d, amVar.d());
                    jSONObject2.put(am.f5171e, amVar.e());
                    jSONObject2.put(am.f, amVar.f());
                    jSONObject2.put(am.g, amVar.g());
                    jSONObject2.put(am.h, amVar.h());
                    jSONObject2.put(am.k, amVar.k());
                    jSONObject2.put(am.j, amVar.j());
                    jSONObject2.put(am.l, amVar.l());
                    jSONObject2.put(am.i, amVar.i());
                    jSONObject2.put(am.m, amVar.m());
                    jSONObject2.put(bm.al, UMUtils.getZid(context));
                    jSONObject2.put("platform", "android");
                    jSONObject2.put("optional", new JSONObject(ar.a()));
                    String[] strArrSplit = str.split(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR);
                    if (strArrSplit.length == 4) {
                        try {
                            long j = Long.parseLong(strArrSplit[0]);
                            String str2 = strArrSplit[1];
                            jSONObject2.put("s1", j);
                            jSONObject2.put("s2", str2);
                        } catch (Throwable unused) {
                        }
                    }
                    try {
                        String strA = as.a(Build.BRAND);
                        String strB = as.b(Build.BRAND);
                        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strB)) {
                            jSONObject2.put(am.n, "Android");
                            jSONObject2.put(am.o, Build.VERSION.RELEASE);
                        } else {
                            jSONObject2.put(am.n, strA);
                            jSONObject2.put(am.o, strB);
                        }
                    } catch (Throwable unused2) {
                    }
                    return jSONObject2;
                } catch (JSONException e2) {
                    e = e2;
                    jSONObject = jSONObject2;
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "[getCloudConfigParam] error " + e.getMessage());
                    return jSONObject;
                } catch (Throwable th) {
                    th = th;
                    jSONObject = jSONObject2;
                }
            } catch (JSONException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        UMRTLog.e(UMRTLog.RTLOG_TAG, "[getCloudConfigParam] error " + th.getMessage());
        return jSONObject;
    }

    public static JSONObject a(Context context, int i, JSONArray jSONArray, String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        try {
            jSONObject = new JSONObject();
        } catch (Throwable unused) {
        }
        try {
            String zid = UMUtils.getZid(context);
            if (TextUtils.isEmpty(zid)) {
                return jSONObject;
            }
            jSONObject.put("atoken", zid);
            String deviceToken = UMUtils.getDeviceToken(context);
            if (!TextUtils.isEmpty(deviceToken)) {
                jSONObject.put(RemoteMessageConst.DEVICE_TOKEN, deviceToken);
            }
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("os", "android");
            jSONObject.put(bm.y, Build.VERSION.RELEASE);
            jSONObject.put(com.umeng.ccg.a.o, str);
            jSONObject.put(com.umeng.ccg.a.r, jSONArray);
            jSONObject.put("e", i);
            return jSONObject;
        } catch (Throwable unused2) {
            jSONObject2 = jSONObject;
            return jSONObject2;
        }
    }
}
