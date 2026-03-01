package com.zx.a.I8b7;

import com.zx.a.I8b7.u2;
import com.zx.module.base.Callback;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class y0 implements Runnable {

    public class a implements Callback {
        public a(y0 y0Var) {
        }

        @Override // com.zx.module.base.Callback
        public void callback(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("code") == 0) {
                    f1.b(jSONObject.getJSONObject("data").getString("type"), jSONObject.getJSONObject("data").getString("code"));
                }
            } catch (Throwable th) {
                y1.a(th);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            u2.b.f6294a.b(new JSONObject(), new a(this), 2);
        } catch (Throwable th) {
            y1.a(th);
        }
    }
}
