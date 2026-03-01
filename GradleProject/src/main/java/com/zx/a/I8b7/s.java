package com.zx.a.I8b7;

import android.text.TextUtils;
import com.zx.a.I8b7.c3;
import com.zx.a.I8b7.t1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSONArray f6276a = new JSONArray();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6277a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public a(String str, String str2, String str3) {
            this.f6277a = str;
            this.b = str2;
            this.c = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (s.this.f6276a.length() >= 100) {
                    y1.a("events length > MAX_COUNT " + s.this.f6276a.length());
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", jCurrentTimeMillis);
                jSONObject.put("callId", this.f6277a);
                jSONObject.put("action", this.b);
                jSONObject.put("params", this.c);
                s.this.f6276a.put(jSONObject);
                y1.a("events add:" + jSONObject.toString());
                if (t2.F) {
                    y1.a("events save:" + s.this.f6276a.toString());
                    t1 t1Var = t1.a.f6285a;
                    b3 b3Var = t1Var.f6284a;
                    String string = s.this.f6276a.toString();
                    b3Var.getClass();
                    t1Var.f6284a.a(23, string, true);
                }
            } catch (Throwable th) {
                y1.a(th);
            }
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final s f6278a = new s();
    }

    public void a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str);
            JSONArray jSONArray2 = this.f6276a;
            JSONArray jSONArray3 = new JSONArray();
            int i = 0;
            while (true) {
                if (i >= jSONArray.length()) {
                    for (int i2 = 0; i2 < jSONArray2.length() && jSONArray3.length() < 100; i2++) {
                        jSONArray3.put(jSONArray2.get(i2));
                    }
                } else {
                    if (jSONArray3.length() >= 100) {
                        break;
                    }
                    jSONArray3.put(jSONArray.get(i));
                    i++;
                }
            }
            this.f6276a = jSONArray3;
        } catch (JSONException e2) {
            y1.a(e2);
        }
    }

    public void a(String str, String str2, String str3) {
        try {
            try {
                c3.e.f6204a.b.execute(new a(str, str2, str3));
            } catch (Throwable th) {
                y1.a(th);
            }
        } catch (Throwable th2) {
            y1.a(th2);
        }
    }
}
