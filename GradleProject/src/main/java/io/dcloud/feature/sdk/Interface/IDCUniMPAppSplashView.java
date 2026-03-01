package io.dcloud.feature.sdk.Interface;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public interface IDCUniMPAppSplashView {
    View getSplashView(Context context, String str, String str2, String str3);

    void onCloseSplash(ViewGroup viewGroup);
}
