package com.efs.sdk.base.core.config.a;

import android.text.TextUtils;
import android.util.Base64;
import com.efs.sdk.base.core.controller.ControllerCenter;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public String b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1827a = -1;
    public String c = DeviceInfo.HTTPS_PROTOCOL;
    public String d = "errnewlog.umeng.com";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1828e = 480;
    public Boolean i = null;
    public Map<String, Double> f = new HashMap();
    public Map<String, String> g = new HashMap();
    public Map<String, Object> h = new HashMap();

    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.b = str;
            String str2 = new String(com.efs.sdk.base.core.util.b.a.a(Base64.decode(str.getBytes(), 11), ControllerCenter.getGlobalEnvStruct().getSecret().getBytes()));
            String[] strArrSplit = str2.split("\\|");
            if (strArrSplit.length <= 1) {
                return;
            }
            String str3 = strArrSplit[1];
            try {
                JSONArray jSONArray = new JSONArray(str2.substring(strArrSplit[0].length() + strArrSplit[1].length() + 2));
                this.h.put("rate", Integer.valueOf(Integer.parseInt(str3)));
                this.h.put("stra", jSONArray);
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static b a() {
        b bVar = new b();
        if (ControllerCenter.getGlobalEnvStruct().isIntl()) {
            bVar.d = "errnewlogos.umeng.com";
        } else {
            bVar.d = "errnewlog.umeng.com";
        }
        return bVar;
    }

    public final void a(Map<String, String> map) {
        if (map.containsKey("gate_way")) {
            String str = map.get("gate_way");
            if (!TextUtils.isEmpty(str)) {
                this.d = str;
            }
        }
        if (map.containsKey("gate_way_https")) {
            String str2 = map.get("gate_way_https");
            if (!TextUtils.isEmpty(str2)) {
                this.c = Boolean.parseBoolean(str2) ? DeviceInfo.HTTPS_PROTOCOL : DeviceInfo.HTTP_PROTOCOL;
            }
        }
        try {
            if (map.containsKey("updateInteval")) {
                String str3 = map.get("updateInteval");
                if (!TextUtils.isEmpty(str3)) {
                    this.f1828e = Long.parseLong(str3);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("data_sampling_rate_") || key.startsWith("file_sampling_rate_")) {
                String strReplace = key.replace("data_sampling_rate_", "").replace("file_sampling_rate_", "");
                double d = 100.0d;
                try {
                    d = Double.parseDouble(entry.getValue());
                } catch (Throwable unused) {
                }
                map2.put(strReplace, Double.valueOf(d));
            }
        }
        this.f = map2;
        this.g = map;
    }
}
