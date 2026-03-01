package com.umeng.analytics.pro;

import android.text.TextUtils;
import com.umeng.ccg.ActionInfo;
import com.umeng.ccg.CcgAgent;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import io.dcloud.common.DHInterface.IApp;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: compiled from: Action.java */
/* JADX INFO: loaded from: classes2.dex */
public class ab implements ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5159a;
    public ArrayList<ac> b;
    public String c = "";
    public String d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5160e = "";
    public String f = "";

    public ab(String str, ArrayList<ac> arrayList) {
        this.f5159a = null;
        this.b = new ArrayList<>();
        this.f5159a = str;
        this.b = arrayList;
    }

    public String a() {
        return this.f5159a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    private String c(String str) {
        String[] strArrSplit = str.split(",");
        String str2 = "";
        if (strArrSplit.length <= 0) {
            return "";
        }
        ArrayList<String> forbidSdkArray = CcgAgent.getForbidSdkArray(this.f5159a);
        if (forbidSdkArray != null && forbidSdkArray.size() > 0) {
            this.f = forbidSdkArray.toString();
            for (String str3 : strArrSplit) {
                if (CcgAgent.getActionInfo(str3) != null && !forbidSdkArray.contains(str3)) {
                    return str3;
                }
            }
            return "";
        }
        for (String str4 : strArrSplit) {
            ActionInfo actionInfo = CcgAgent.getActionInfo(str4);
            if (actionInfo != null) {
                String[] supportAction = actionInfo.getSupportAction(UMGlobalContext.getAppContext());
                if (supportAction.length > 0) {
                    int i = 0;
                    while (true) {
                        if (i >= supportAction.length) {
                            break;
                        }
                        if (this.f5159a.equals(supportAction[i])) {
                            str2 = str4;
                            break;
                        }
                        i++;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        return str2;
                    }
                } else {
                    continue;
                }
            }
        }
        return str2;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    @Override // com.umeng.analytics.pro.ai
    public JSONObject a(String str, JSONObject jSONObject) {
        try {
            int size = this.b.size();
            if (size == 0) {
                return null;
            }
            for (int i = 0; i < size; i++) {
                if (this.b.get(i).b()) {
                    return null;
                }
            }
            if (CcgAgent.hasRegistedActionInfo() && !TextUtils.isEmpty(this.d)) {
                String strC = c(this.d);
                this.f5160e = strC;
                if (TextUtils.isEmpty(strC)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "采集项：" + this.f5159a + "; 未选中可用Module ; sdk: " + this.d);
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "采集项：" + this.f5159a + "; 选中Module: " + this.f5160e + "; sdk: " + this.d);
                }
            }
            ac acVar = this.b.get(size - 1);
            if (acVar == null || !(acVar instanceof ae)) {
                return null;
            }
            long jC = acVar.c();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("actionName", this.f5159a);
                jSONObject2.put(com.umeng.ccg.a.r, this.d);
                jSONObject2.put(com.umeng.ccg.a.o, this.c);
                jSONObject2.put(IApp.ConfigProperty.CONFIG_DELAY, jC);
                jSONObject2.put(com.umeng.ccg.a.p, this.f5160e);
                jSONObject2.put(com.umeng.ccg.a.q, this.f);
            } catch (Throwable unused) {
            }
            return jSONObject2;
        } catch (Throwable unused2) {
            return null;
        }
    }
}
