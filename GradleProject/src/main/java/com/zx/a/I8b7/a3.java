package com.zx.a.I8b7;

import android.text.TextUtils;
import com.zx.module.base.Listener;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a3 implements v2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f6198a = new AtomicBoolean(false);
    public final AtomicBoolean b = new AtomicBoolean(false);
    public Listener c;

    public static void a(a3 a3Var) throws Exception {
        a3Var.getClass();
        String str = t2.i;
        if (!t2.o) {
            h.c();
        }
        z0.h();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", 0);
        jSONObject.put("data", t2.a());
        String string = jSONObject.toString();
        a3Var.c.onMessage("MESSAGE_ON_ZXID_RECEIVED", string);
        if (TextUtils.equals(str, t2.i)) {
            return;
        }
        a3Var.c.onMessage("MESSAGE_ON_ZXID_CHANGED", string);
    }
}
