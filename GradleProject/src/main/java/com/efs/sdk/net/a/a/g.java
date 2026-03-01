package com.efs.sdk.net.a.a;

import android.text.TextUtils;
import com.efs.sdk.base.core.util.Log;
import com.efs.sdk.net.NetManager;
import com.efs.sdk.net.a.a.f;
import com.huawei.hms.utils.FileUtil;
import com.loopj.android.http.RequestParams;
import io.dcloud.common.util.net.NetWork;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicInteger f1905a = new AtomicInteger(0);
    public static g c;
    public b b = new b();

    public static g c() {
        if (c == null) {
            c = new g();
        }
        return c;
    }

    @Override // com.efs.sdk.net.a.a.f
    public final void a(f.b bVar) {
        Log.d("NetTrace-Interceptor", "request will be sent");
        b bVar2 = this.b;
        try {
            String strA = bVar.a();
            bVar2.f1901a.put(bVar.a(), Long.valueOf(System.currentTimeMillis()));
            Log.i("NetTrace-Interceptor", "save request");
            com.efs.sdk.net.a.c cVarA = com.efs.sdk.net.a.a.a().a(strA);
            String strB = bVar.b();
            if (!TextUtils.isEmpty(strB)) {
                cVarA.d = strB;
            }
            cVarA.f1910e = bVar.c();
            HashMap map = new HashMap();
            int iE = bVar.e();
            for (int i = 0; i < iE; i++) {
                map.put(bVar.a(i), bVar.b(i));
            }
            cVarA.f = b.a(bVar);
            if (NetManager.getNetConfigManager().getNetRequestBodyCollectState()) {
                if ((cVarA.f1910e == null || !cVarA.f1910e.equalsIgnoreCase("get")) && cVarA.f1910e != null && cVarA.f1910e.equalsIgnoreCase("post") && cVarA.f < FileUtil.LOCAL_REPORT_FILE_MAX_SIZE) {
                    if (map.containsKey("Content-Type") || map.containsKey("content-type")) {
                        String str = (String) map.get("Content-Type");
                        if (TextUtils.isEmpty(str)) {
                            str = (String) map.get("content-type");
                        }
                        if (str != null) {
                            if (str.contains(RequestParams.APPLICATION_JSON) || str.contains(NetWork.CONTENT_TYPE_UPLOAD)) {
                                cVarA.g = new String(bVar.d());
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.efs.sdk.net.a.a.f
    public final String b() {
        Log.d("NetTrace-Interceptor", "next request id");
        return String.valueOf(f1905a.getAndIncrement());
    }

    @Override // com.efs.sdk.net.a.a.f
    public final void a(f.d dVar) {
        Log.d("NetTrace-Interceptor", "response headers received");
        b bVar = this.b;
        Log.i("NetTrace-Interceptor", "save response");
        String strA = dVar.a();
        if (bVar.f1901a != null) {
            com.efs.sdk.net.a.a.a().a(strA).h = dVar.b();
        }
    }

    @Override // com.efs.sdk.net.a.a.f
    @Nullable
    public final InputStream a(String str, @Nullable String str2, @Nullable String str3, @Nullable InputStream inputStream) {
        Log.d("NetTrace-Interceptor", "interpret response stream");
        return b.a(str, str2, str3, inputStream);
    }

    @Override // com.efs.sdk.net.a.a.f
    public final void a() {
        Log.d("NetTrace-Interceptor", "data sent");
    }
}
