package io.dcloud.feature.unimp.f;

import android.content.Context;
import android.content.Intent;
import io.dcloud.feature.sdk.DCSDKInitConfig;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleCloseButtontCallBack;
import io.dcloud.feature.sdk.Interface.IDCUniMPOnCapsuleMenuButtontCallBack;
import io.dcloud.feature.sdk.Interface.IMenuButtonClickCallBack;
import io.dcloud.feature.sdk.Interface.IOnUniMPEventCallBack;
import io.dcloud.feature.sdk.Interface.IUniMP;
import io.dcloud.feature.sdk.Interface.IUniMPOnCloseCallBack;
import io.dcloud.feature.unimp.DCUniMPJSCallback;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface a {
    IUniMP a(String str, String str2, String str3, JSONObject jSONObject);

    IUniMP a(JSONObject jSONObject);

    void a();

    void a(Context context, DCSDKInitConfig dCSDKInitConfig);

    void a(IDCUniMPOnCapsuleCloseButtontCallBack iDCUniMPOnCapsuleCloseButtontCallBack);

    void a(IDCUniMPOnCapsuleMenuButtontCallBack iDCUniMPOnCapsuleMenuButtontCallBack);

    void a(IMenuButtonClickCallBack iMenuButtonClickCallBack);

    void a(IOnUniMPEventCallBack iOnUniMPEventCallBack);

    void a(IUniMPOnCloseCallBack iUniMPOnCloseCallBack);

    void a(c cVar, String str);

    void a(String str, String str2);

    void a(String str, String str2, String str3, Object obj, boolean z);

    boolean a(String str);

    boolean a(String str, Intent intent, int i, int i2);

    boolean a(String str, String str2, Object obj);

    DCSDKInitConfig b();

    void b(String str, String str2);

    boolean b(String str);

    String c();

    JSONObject c(String str);

    void c(String str, String str2);

    String d();

    boolean d(String str);

    boolean e(String str);

    String f(String str);

    void onUniMPEventReceive(String str, String str2, Object obj, DCUniMPJSCallback dCUniMPJSCallback);
}
