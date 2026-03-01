package com.igexin.push.core.a.b;

import android.text.TextUtils;
import com.baidu.speech.utils.analysis.Analysis;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3314a = com.igexin.push.config.c.f3286a + "_QueryTagResultAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        com.igexin.c.a.c.a.a(f3314a + "|query tag result resp data = " + jSONObject, new Object[0]);
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals("query_tag_result")) {
                return true;
            }
            String string = jSONObject.getString("tags");
            com.igexin.push.core.l.a().a(jSONObject.getString("sn"), jSONObject.getString(Analysis.KEY_RECOGNITION_RESULT_ERROR_CODE), jSONObject.getString("tags"));
            if (TextUtils.isEmpty(string)) {
                string = "none";
            }
            com.igexin.push.core.e.f.a().e(string);
            return true;
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
            return true;
        }
    }
}
