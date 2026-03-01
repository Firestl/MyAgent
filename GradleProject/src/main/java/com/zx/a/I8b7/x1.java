package com.zx.a.I8b7;

import com.zx.sdk.api.ZXIDChangedListener;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class x1 extends o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, LinkedList<ZXIDChangedListener>> f6303a = new ConcurrentHashMap<>();

    @Override // com.zx.a.I8b7.o0
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("code");
            for (String str2 : this.f6303a.keySet()) {
                for (ZXIDChangedListener zXIDChangedListener : this.f6303a.get(str2)) {
                    if (i == 0) {
                        zXIDChangedListener.onChange(a(str2, jSONObject.getString("data")));
                    }
                }
                this.f6303a.remove(str2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
