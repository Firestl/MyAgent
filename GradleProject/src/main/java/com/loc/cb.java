package com.loc;

import android.util.Base64;
import com.loopj.android.http.RequestParams;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.adapter.util.DeviceInfo;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: CollectionUploader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cb {
    public static boolean a(byte[] bArr) {
        String str;
        if (bArr == null) {
            return false;
        }
        byte[] bArr2 = null;
        try {
            di diVar = new di();
            diVar.b.put("Content-Type", RequestParams.APPLICATION_OCTET_STREAM);
            diVar.b.put("aps_c_src", Base64.encodeToString(("lc_" + ((int) de.a())).getBytes(), 2));
            diVar.b.put("aps_c_key", Base64.encodeToString((de.c() + Operators.MUL + de.f()).getBytes(), 2));
            diVar.d = bArr;
            if (bs.f3691a) {
                str = "http://cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&";
            } else {
                str = (bs.b ? DeviceInfo.HTTPS_PROTOCOL : DeviceInfo.HTTP_PROTOCOL) + "cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&";
            }
            diVar.f3727a = str;
            dj djVarA = cv.a().a(diVar);
            if (djVarA != null && djVarA.f3729a == 200) {
                bArr2 = djVarA.c;
            }
            if (bArr2 != null) {
                return "true".equals(new String(bArr2, StandardCharsets.UTF_8));
            }
            return false;
        } catch (Exception e2) {
            dg.a(e2);
            return false;
        }
    }
}
