package com.cmic.gen.sdk.view;

import android.content.Context;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public interface GenLoginClickListener {
    void onLoginClickComplete(Context context, JSONObject jSONObject);

    void onLoginClickStart(Context context, JSONObject jSONObject);
}
