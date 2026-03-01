package io.dcloud.weex;

import com.alibaba.fastjson.JSONObject;
import io.dcloud.common.util.BaseInfo;

/* JADX INFO: loaded from: classes3.dex */
public class WXDotDataUtil {
    public static JSONObject DEVICEINFO = new JSONObject();

    public static JSONObject getDeviceInfo() {
        return DEVICEINFO;
    }

    public static void setValue(String str, Object obj) {
        if (BaseInfo.SyncDebug) {
            DEVICEINFO.put(str, obj);
        }
    }
}
