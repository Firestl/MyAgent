package io.dcloud.common.core.ui;

import android.content.Context;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class d extends AdaUniWebView {
    public d(Context context, b bVar, String str, String str2, JSONObject jSONObject, boolean z) {
        super(context, bVar.obtainApp(), bVar, str, str2, jSONObject, z);
        bVar.l = this;
        bVar.m = getWebviewParent();
    }

    @Override // io.dcloud.common.adapter.ui.AdaUniWebView, io.dcloud.common.DHInterface.IWebview
    public IApp obtainApp() {
        if (obtainFrameView() != null) {
            return obtainFrameView().obtainApp();
        }
        return null;
    }
}
