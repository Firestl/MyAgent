package com.zx.a.I8b7;

import com.zx.sdk.api.ZXIDListener;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class w1 extends o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, LinkedList<ZXIDListener>> f6301a = new ConcurrentHashMap<>();

    @Override // com.zx.a.I8b7.o0
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("code");
            String strOptString = jSONObject.optString("message");
            for (String str2 : this.f6301a.keySet()) {
                for (ZXIDListener zXIDListener : this.f6301a.get(str2)) {
                    if (i == 0) {
                        zXIDListener.onSuccess(a(str2, jSONObject.getString("data")));
                    } else {
                        zXIDListener.onFailed(i, strOptString);
                    }
                }
                this.f6301a.remove(str2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
