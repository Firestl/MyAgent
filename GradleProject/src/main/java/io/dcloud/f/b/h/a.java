package io.dcloud.f.b.h;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.igexin.push.g.o;
import com.taobao.weex.el.parse.Operators;
import com.umeng.commonsdk.statistics.idtracking.g;
import com.umeng.commonsdk.statistics.idtracking.i;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DataInterface;
import io.dcloud.common.util.ADUtils;
import io.dcloud.common.util.AESUtil;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.LauncherUtil;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.NetworkTypeUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.TelephonyUtil;
import io.dcloud.common.util.ZipUtils;
import io.dcloud.common.util.hostpicker.HostPicker;
import io.dcloud.feature.internal.sdk.SDK;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: io.dcloud.f.b.h.a$a, reason: collision with other inner class name */
    public static class C0165a implements HostPicker.HostPickCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6504a;
        public final /* synthetic */ HashMap b;

        public C0165a(String str, HashMap map) {
            this.f6504a = str;
            this.b = map;
        }

        @Override // io.dcloud.common.util.hostpicker.HostPicker.HostPickCallback
        public boolean doRequest(HostPicker.Host host) {
            byte[] bArrHttpPost = NetTool.httpPost(host.getRealHost(), this.f6504a, (HashMap<String, String>) this.b, false);
            if (bArrHttpPost == null) {
                return false;
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(new String(bArrHttpPost, "utf-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            Logger.d("commitTid", jSONObject);
            return true;
        }

        @Override // io.dcloud.common.util.hostpicker.HostPicker.HostPickCallback
        public void onNoOnePicked() {
        }

        @Override // io.dcloud.common.util.hostpicker.HostPicker.HostPickCallback
        public void onOneSelected(HostPicker.Host host) {
        }
    }

    public static class b implements HostPicker.HostPickCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6505a;
        public final /* synthetic */ HashMap b;

        public b(String str, HashMap map) {
            this.f6505a = str;
            this.b = map;
        }

        @Override // io.dcloud.common.util.hostpicker.HostPicker.HostPickCallback
        public boolean doRequest(HostPicker.Host host) {
            byte[] bArrHttpPost = NetTool.httpPost(host.getRealHost(), this.f6505a, (HashMap<String, String>) this.b, false);
            if (bArrHttpPost == null) {
                return false;
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(new String(bArrHttpPost, StandardCharsets.UTF_8));
            } catch (JSONException unused) {
            }
            Logger.d("commitTid", jSONObject);
            return true;
        }

        @Override // io.dcloud.common.util.hostpicker.HostPicker.HostPickCallback
        public void onNoOnePicked() {
        }

        @Override // io.dcloud.common.util.hostpicker.HostPicker.HostPickCallback
        public void onOneSelected(HostPicker.Host host) {
        }
    }

    public static HashMap<Object, Object> a(IApp iApp, SharedPreferences sharedPreferences) {
        HashMap<Object, Object> map = new HashMap<>();
        try {
            Activity activity = iApp.getActivity();
            String metaValue = null;
            map.putAll(DataInterface.getStartupUrlBaseData(activity, iApp.obtainAppId(), BaseInfo.getLaunchType(activity.getIntent()), null));
            map.put("st", Long.valueOf(BaseInfo.run5appEndTime));
            map.put("pn", activity.getPackageName());
            map.put("v", iApp.obtainAppVersionName());
            map.put("pv", AndroidResources.versionName);
            map.put("uat", Integer.valueOf(BaseInfo.isUniAppAppid(iApp) ? 1 : 0));
            if (SDK.isUniMPSDK()) {
                try {
                    map.put("name", activity.getApplicationInfo().loadLabel(activity.getPackageManager()));
                } catch (Exception unused) {
                    map.put("name", iApp.obtainAppName());
                }
            } else {
                map.put("name", iApp.obtainAppName());
            }
            try {
                map.put("pname", activity.getApplicationInfo().loadLabel(activity.getPackageManager()));
            } catch (Exception unused2) {
            }
            map.put(o.f, Integer.valueOf(SDK.isUniMPSDK() ? 1 : 0));
            if (SDK.isUniMPSDK()) {
                boolean zCheckClass = PlatformUtil.checkClass(iApp.getConfusionMgr().getGDTClassName());
                boolean zCheckClass2 = PlatformUtil.checkClass(iApp.getConfusionMgr().getCSJClassName());
                boolean zCheckClass3 = PlatformUtil.checkClass(iApp.getConfusionMgr().getKSClassName());
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                String str = "1";
                jSONObject2.put("r", zCheckClass2 ? "1" : "0");
                jSONObject.put("csj", jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("r", zCheckClass ? "1" : "0");
                jSONObject.put("gdt", jSONObject3);
                JSONObject jSONObject4 = new JSONObject();
                if (!zCheckClass3) {
                    str = "0";
                }
                jSONObject4.put("r", str);
                jSONObject.put("ks", jSONObject4);
                map.put("cad", jSONObject.toString());
            } else {
                map.put("psdk", 1);
            }
            String bundleData = SP.getBundleData(activity, "pdr", SP.REPORT_UNI_VERIFY_GYUID);
            if (!TextUtils.isEmpty(bundleData)) {
                map.put("uvs", bundleData);
            }
            if (PdrUtil.isSupportOaid()) {
                Object obj = DeviceInfo.oaids;
                if (obj == null) {
                    obj = Operators.OR;
                }
                map.put(i.d, obj);
            }
            if (BaseInfo.isUniAppAppid(iApp)) {
                a(iApp, map);
            }
            if (TextUtils.isEmpty(BaseInfo.sChannel)) {
                try {
                    metaValue = AndroidResources.getMetaValue("DCLOUD_STREAMAPP_CHANNEL");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (TextUtils.isEmpty(metaValue)) {
                    map.put("mc", "");
                } else {
                    map.put("mc", metaValue);
                }
            } else {
                map.put("mc", BaseInfo.sChannel);
            }
            if ((System.currentTimeMillis() - sharedPreferences.getLong(AbsoluteConst.COMMIT_APP_LIST_TIME, 0L)) / 100000 >= 26000) {
                if (!BaseInfo.isChannelGooglePlay()) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("pn", LauncherUtil.getLauncherPackageName(activity));
                    map.put("launcher", jSONObject5.toString());
                }
                map.put("imsi", TelephonyUtil.getIMSI(activity));
                if (!TextUtils.isEmpty(sharedPreferences.getString(AbsoluteConst.GEO_DATA, ""))) {
                    map.put("pos", sharedPreferences.getString(AbsoluteConst.GEO_DATA, ""));
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return map;
    }

    public static Map<String, Object> b(Context context, String str, String str2, int i, String str3, HashMap<String, Object> map) {
        String strEncode;
        String str4;
        try {
            strEncode = URLEncoder.encode(Build.MODEL, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            strEncode = "";
        }
        try {
            str4 = DeviceInfo.sApplicationContext.getPackageManager().getPackageInfo(DeviceInfo.sApplicationContext.getPackageName(), 0).versionName;
        } catch (Exception e3) {
            e3.printStackTrace();
            str4 = "";
        }
        String imei = TelephonyUtil.getIMEI(context, true, true);
        HashMap map2 = new HashMap();
        map2.put("p", "a");
        map2.put("appid", str);
        map2.put("v", str4);
        map2.put("at", Integer.valueOf(i));
        map2.put("os", Integer.valueOf(Build.VERSION.SDK_INT));
        map2.put("adpid", str3);
        if (imei.endsWith("&ie=1")) {
            imei = imei.replace("&ie=1", "");
            map2.put("ie", 1);
        } else if (imei.endsWith("&ie=0")) {
            imei = imei.replace("&ie=0", "");
            map2.put("ie", 0);
        }
        map2.put(g.f5442a, imei);
        map2.put("md", strEncode);
        map2.put("vd", Build.MANUFACTURER);
        map2.put("net", Integer.valueOf(NetworkTypeUtil.getNetworkType(DeviceInfo.sApplicationContext)));
        map2.put("vb", "1.9.9.81096");
        map2.put("t", Long.valueOf(System.currentTimeMillis()));
        if (TextUtils.isEmpty(BaseInfo.sChannel)) {
            String metaValue = null;
            try {
                metaValue = AndroidResources.getMetaValue("DCLOUD_STREAMAPP_CHANNEL");
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            if (TextUtils.isEmpty(metaValue)) {
                map2.put("mc", "");
            } else {
                map2.put("mc", metaValue);
            }
        } else {
            map2.put("mc", BaseInfo.sChannel);
        }
        map2.put("paid", str2);
        if (map != null) {
            map2.putAll(map);
        }
        return map2;
    }

    public static void a(IApp iApp, HashMap<Object, Object> map) {
        String string;
        JSONArray jSONArray;
        StringBuilder sb = new StringBuilder();
        try {
            string = IOUtil.toString(iApp.getActivity().getAssets().open("dcloud_uniplugins.json"));
        } catch (Exception unused) {
            string = null;
        }
        if (!PdrUtil.isEmpty(string)) {
            try {
                JSONArray jSONArray2 = JSON.parseObject(string).getJSONArray("nativePlugins");
                if (jSONArray2 != null && jSONArray2.size() > 0) {
                    for (int i = 0; i < jSONArray2.size(); i++) {
                        com.alibaba.fastjson.JSONObject jSONObject = jSONArray2.getJSONObject(i);
                        if (jSONObject != null && (jSONArray = jSONObject.getJSONArray("plugins")) != null && jSONArray.size() > 0) {
                            for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                                com.alibaba.fastjson.JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                if (jSONObject2 != null && jSONObject2.containsKey("name")) {
                                    sb.append(jSONObject2.getString("name"));
                                    sb.append(",");
                                }
                            }
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            map.put(o.b, sb.toString());
        }
    }

    public static void a(Context context, String str, String str2, String str3, int i, String str4, String str5, JSONObject jSONObject, String str6, String str7, String str8, String str9, HashMap<String, Object> map) {
        try {
            URLEncoder.encode(Build.MODEL, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            String str10 = DeviceInfo.sApplicationContext.getPackageManager().getPackageInfo(DeviceInfo.sApplicationContext.getPackageName(), 0).versionName;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        TelephonyUtil.getIMEI(context, true, true);
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            arrayList.add(new HostPicker.Host("YHx8eHsyJyd8OSZsa2RnfWwmZm18JmtmJ2tnZGRta3wneGR9e2l4eCdpa3xhZ2Y=", HostPicker.Host.PriorityEnum.FIRST));
            arrayList.add(new HostPicker.Host("YHx8eHsyJyd8OiZsa2RnfWwmZm18JmtmJ2tnZGRta3wneGR9e2l4eCdpa3xhZ2Y=", HostPicker.Host.PriorityEnum.NORMAL));
            arrayList.add(new HostPicker.Host("YHx8eHsyJyc6OzswaTw6aiU7bWs7JTxtaz0lMDlrMCU6bm1rMTE/MDw/aTAmant4aXh4JmtnZSdgfHx4J2tpaQ==", HostPicker.Host.PriorityEnum.BACKUP));
        } else {
            arrayList.add(new HostPicker.Host("YHx8eHsyJydpezkmbGtkZ31sJmZtfCZrZidrZ2RkbWt8J3hkfXtpeHgnaWt8YWdm", HostPicker.Host.PriorityEnum.FIRST));
            arrayList.add(new HostPicker.Host("YHx8eHsyJydpezombGtkZ31sJmZtfCZrZidrZ2RkbWt8J3hkfXtpeHgnaWt8YWdm", HostPicker.Host.PriorityEnum.NORMAL));
            arrayList.add(new HostPicker.Host("YHx8eHsyJyc8bTFqOGowaSU4bDw8JTw6bmwlMTA7bCU6P2wxMTlsbGw6Ozomant4aXh4JmtnZSdgfHx4J2tpaQ==", HostPicker.Host.PriorityEnum.BACKUP));
        }
        Map<String, Object> mapB = b(context, str, str3, i, str8, map);
        if (str6 != null) {
            mapB.put("mediaId", str6);
        }
        if (str7 != null) {
            mapB.put("slotId", str7);
        }
        mapB.put("tid", str2);
        if (i == 32) {
            mapB.put("dec", str4);
            mapB.put("dem", str5);
        }
        if (i == 41 && jSONObject != null && str6 == null) {
            if (jSONObject.has("img")) {
                mapB.put("img", Md5Utils.md5(jSONObject.optString("img")).toLowerCase());
            }
            if (jSONObject.has("dw")) {
                mapB.put("dw", jSONObject.optString("dw"));
            }
            if (jSONObject.has("dh")) {
                mapB.put("dh", jSONObject.optString("dh"));
            }
            if (jSONObject.has("click_coord")) {
                mapB.put("click_coord", jSONObject.optJSONObject("click_coord").toString());
            }
        }
        String strEncode = null;
        try {
            strEncode = URLEncoder.encode(Base64.encodeToString(AESUtil.encrypt(io.dcloud.g.a.b(), io.dcloud.g.a.a(), ZipUtils.zipString(new JSONObject(mapB).toString())), 2), "utf-8");
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        String str11 = "edata=" + strEncode;
        try {
            HashMap map2 = new HashMap();
            try {
                if (!PdrUtil.isEmpty(str9) && str9.equalsIgnoreCase("webview")) {
                    Object objADHandlerMethod = ADUtils.ADHandlerMethod("get", "ua-webview");
                    if (objADHandlerMethod instanceof String) {
                        map2.put("User-Agent", (String) objADHandlerMethod);
                    }
                }
            } catch (Exception unused) {
            }
            HostPicker.getInstance().pickSuitHost(context, arrayList, "CAA_" + i, new C0165a(str11, map2));
        } catch (Exception e5) {
            Logger.p("CommitDataUtil", e5.getMessage());
        }
    }

    public static void a(Context context, String str, String str2, int i, String str3, HashMap<String, Object> map) {
        String strEncode;
        Map<String, Object> mapB = b(context, str, str2, i, str3, map);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HostPicker.Host("YHx8eHsyJydpejkmbGtkZ31sJmZtfCZrZidrZ2RkbWt8J3hkfXtpeHgnent4", HostPicker.Host.PriorityEnum.FIRST));
        arrayList.add(new HostPicker.Host("YHx8eHsyJydpejombGtkZ31sJmZtfCZrZidrZ2RkbWt8J3hkfXtpeHgnent4", HostPicker.Host.PriorityEnum.NORMAL));
        arrayList.add(new HostPicker.Host("YHx8eHsyJyc8bTFqOGowaSU4bDw8JTw6bmwlMTA7bCU6P2wxMTlsbGw6Ozomant4aXh4JmtnZSdgfHx4J2tpeg==", HostPicker.Host.PriorityEnum.BACKUP));
        try {
            strEncode = URLEncoder.encode(Base64.encodeToString(AESUtil.encrypt(io.dcloud.g.a.b(), io.dcloud.g.a.a(), ZipUtils.zipString(new JSONObject(mapB).toString())), 2), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            strEncode = null;
        }
        HostPicker.getInstance().pickSuitHost(context, arrayList, "RSP", new b("edata=" + strEncode, new HashMap()));
    }

    public static void a(String str, String str2, String str3, String str4) {
        NetTool.httpGet("https://96f0e031-f37a-48ef-84c7-2023f6360c0a.bspapp.com/http/rewarded-video/report?p=a&t=" + str4 + "&a=" + str + "&c=" + str2 + "&m=" + PdrUtil.encodeURL(str3));
    }
}
