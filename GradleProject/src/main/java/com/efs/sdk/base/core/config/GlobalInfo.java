package com.efs.sdk.base.core.config;

import android.content.Context;
import android.text.TextUtils;
import com.efs.sdk.base.core.a.a;
import com.efs.sdk.base.core.util.d;
import com.efs.sdk.base.protocol.file.section.AbsSection;
import com.efs.sdk.base.protocol.file.section.KVSection;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.tencent.stat.DeviceInfo;
import com.umeng.umcrash.UMCrash;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class GlobalInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, Object> f1823a = new ConcurrentHashMap();

    public final void a(String str, Object obj) {
        if (obj != null) {
            this.f1823a.put(str, obj);
        }
    }

    public final Object b(String str, Object obj) {
        Object obj2 = this.f1823a.get(str);
        return (obj2 != null || this.f1823a.containsKey(str)) ? obj2 : obj;
    }

    public Map<String, Object> getGlobalInfoMap() {
        HashMap map = new HashMap(this.f1823a);
        a.a();
        map.put("ctime", Long.valueOf(a.b() / 1000));
        a.a();
        map.put("w_tm", Long.valueOf(a.b() / 1000));
        return map;
    }

    public List<AbsSection> getGlobalSectionList(String str) {
        ArrayList arrayList = new ArrayList();
        KVSection kVSection = new KVSection("global_head");
        KVSection kVSectionPut = kVSection.put("type", str).put("appid", this.f1823a.get("appid")).put("wid", this.f1823a.get("wid")).put("pid", this.f1823a.get("pid")).put("pkg", this.f1823a.get("pkg")).put(DeviceInfo.TAG_VERSION, this.f1823a.get(DeviceInfo.TAG_VERSION)).put("vcode", this.f1823a.get("vcode")).put("ps", this.f1823a.get("ps")).put("stime", this.f1823a.get("stime"));
        a.a();
        KVSection kVSectionPut2 = kVSectionPut.put("ctime", Long.valueOf(a.b() / 1000));
        a.a();
        kVSectionPut2.put("w_tm", Long.valueOf(a.b() / 1000)).put(HiAnalyticsConstant.BI_KEY_SDK_VER, this.f1823a.get(HiAnalyticsConstant.BI_KEY_SDK_VER));
        String strValueOf = String.valueOf(b("uid", ""));
        if (!TextUtils.isEmpty(strValueOf)) {
            kVSection.put("uid", strValueOf);
        }
        arrayList.add(kVSection);
        KVSection kVSection2 = new KVSection("device_info");
        kVSection2.put(AbsoluteConst.JSON_KEY_LANG, this.f1823a.get(AbsoluteConst.JSON_KEY_LANG)).put(Constants.PHONE_BRAND, this.f1823a.get(Constants.PHONE_BRAND)).put("model", this.f1823a.get("model")).put("build_model", this.f1823a.get("build_model")).put("rom", this.f1823a.get("rom")).put(com.umeng.ccg.a.r, this.f1823a.get(com.umeng.ccg.a.r)).put("dsp_h", this.f1823a.get("dsp_h")).put("dsp_w", this.f1823a.get("dsp_w")).put("tzone", this.f1823a.get("tzone")).put("net", this.f1823a.get("net")).put("fr", this.f1823a.get("fr"));
        try {
            if (this.f1823a.containsKey(UMCrash.KEY_HEADER_ACCESS)) {
                kVSection2.put(UMCrash.KEY_HEADER_ACCESS, this.f1823a.get(UMCrash.KEY_HEADER_ACCESS));
            }
            if (this.f1823a.containsKey(UMCrash.KEY_HEADER_ACCESS_SUBTYPE)) {
                kVSection2.put(UMCrash.KEY_HEADER_ACCESS_SUBTYPE, this.f1823a.get(UMCrash.KEY_HEADER_ACCESS_SUBTYPE));
            }
            if (this.f1823a.containsKey(UMCrash.KEY_HEADER_NETWORK_TYPE)) {
                kVSection2.put(UMCrash.KEY_HEADER_NETWORK_TYPE, this.f1823a.get(UMCrash.KEY_HEADER_NETWORK_TYPE));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        arrayList.add(kVSection2);
        return arrayList;
    }

    public String getUUID(Context context) {
        String strValueOf = String.valueOf(b("wid", ""));
        if (!TextUtils.isEmpty(strValueOf)) {
            return strValueOf;
        }
        String strA = d.a(context);
        a("wid", strA);
        return strA;
    }
}
