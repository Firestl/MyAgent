package com.efs.sdk.net;

import android.content.Context;
import com.efs.sdk.base.EfsReporter;
import com.efs.sdk.base.core.util.Log;
import com.huawei.hms.framework.common.ContainerUtils;
import io.dcloud.common.util.net.NetWork;
import java.util.Map;
import supwisdom.bt1;
import supwisdom.ct1;
import supwisdom.hs1;
import supwisdom.xs1;
import supwisdom.zs1;

/* JADX INFO: loaded from: classes.dex */
public class NetManager {
    public static final String TAG = "OkHttpManager";
    public static NetConfigManager mNetConfigManager;
    public static EfsReporter mReporter;

    public static void get(String str, hs1 hs1Var) {
        zs1.b bVar = new zs1.b();
        bVar.a(OkHttpListener.get());
        bVar.b(new OkHttpInterceptor());
        zs1 zs1VarA = bVar.a();
        bt1.a aVar = new bt1.a();
        aVar.b(str);
        zs1VarA.a(aVar.a()).a(hs1Var);
    }

    public static NetConfigManager getNetConfigManager() {
        return mNetConfigManager;
    }

    public static EfsReporter getReporter() {
        return mReporter;
    }

    public static void init(Context context, EfsReporter efsReporter) {
        if (context == null || efsReporter == null) {
            Log.e(TAG, "init net manager error! parameter is null!");
        } else {
            mReporter = efsReporter;
            mNetConfigManager = new NetConfigManager(context, efsReporter);
        }
    }

    public static void post(String str, Map<String, Object> map, hs1 hs1Var) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : map.keySet()) {
            sb.append(str2);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(map.get(str2));
            sb.append("&");
        }
        zs1.b bVar = new zs1.b();
        bVar.a(OkHttpListener.get());
        bVar.b(new OkHttpInterceptor());
        zs1 zs1VarA = bVar.a();
        ct1 ct1VarCreate = ct1.create(xs1.b(NetWork.CONTENT_TYPE_UPLOAD), sb.toString());
        bt1.a aVar = new bt1.a();
        aVar.b(str);
        aVar.c(ct1VarCreate);
        zs1VarA.a(aVar.a()).a(hs1Var);
    }

    public static void postJson(String str, String str2, hs1 hs1Var) {
        zs1.b bVar = new zs1.b();
        bVar.a(OkHttpListener.get());
        bVar.b(new OkHttpInterceptor());
        zs1 zs1VarA = bVar.a();
        ct1 ct1VarCreate = ct1.create(str2, xs1.b("application/json;charset=utf-8"));
        bt1.a aVar = new bt1.a();
        aVar.b(str);
        aVar.c(ct1VarCreate);
        zs1VarA.a(aVar.a()).a(hs1Var);
    }
}
