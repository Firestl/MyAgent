package io.dcloud.feature.unimp.f;

import android.content.Intent;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.unimp.h.a;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface c {
    void a(String str, String str2, String str3, Object obj, boolean z);

    void a(String str, String str2, String str3, JSONObject jSONObject);

    void a(String str, boolean z);

    boolean a();

    boolean a(String str, Intent intent, int i, int i2);

    boolean b();

    a.EnumC0183a c();

    IUniMP clone();

    boolean d();

    String getAppid();

    String getCurrentPageUrl();

    int getState();

    boolean sendUniMPEvent(String str, Object obj);
}
