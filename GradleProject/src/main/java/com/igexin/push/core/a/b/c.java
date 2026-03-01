package com.igexin.push.core.a.b;

import com.igexin.push.config.a.AnonymousClass5;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3311a = "BlockClientAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has("action") && jSONObject.getString("action").equals("block_client") && jSONObject.has("duration")) {
                long j = jSONObject.getLong("duration") * 1000;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (j != 0) {
                    com.igexin.push.config.d.d = jCurrentTimeMillis + j;
                    com.igexin.c.a.b.e.a().a((com.igexin.c.a.d.f) com.igexin.push.config.a.a().new AnonymousClass5(), false, true);
                    com.igexin.push.f.f.a().d();
                }
            }
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        return true;
    }
}
