package com.igexin.push.core.a.b;

import com.baidu.speech.utils.analysis.Analysis;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class k extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3318a = com.igexin.push.config.c.f3286a + "_SetTagResultAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        com.igexin.c.a.c.a.a(f3318a + "|set tag result resp data = " + jSONObject, new Object[0]);
        try {
            if (jSONObject.has("action") && jSONObject.getString("action").equals("settag_result")) {
                com.igexin.push.core.l.a().a(jSONObject.getString("sn"), jSONObject.getString(Analysis.KEY_RECOGNITION_RESULT_ERROR_CODE));
            }
            if (!jSONObject.getString(Analysis.KEY_RECOGNITION_RESULT_ERROR_CODE).equals("0") || com.igexin.push.core.e.f3404e == null) {
                return true;
            }
            com.igexin.push.core.e.f.a().e(com.igexin.push.core.e.f3404e);
            com.igexin.push.core.e.f3404e = null;
            return true;
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
            com.igexin.c.a.c.a.a(f3318a + "|" + e2.toString(), new Object[0]);
            return true;
        }
    }
}
