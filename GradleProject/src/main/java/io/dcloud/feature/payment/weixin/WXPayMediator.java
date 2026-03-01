package io.dcloud.feature.payment.weixin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.dcloud.ProcessMediator;
import io.dcloud.common.adapter.util.AndroidResources;

/* JADX INFO: loaded from: classes3.dex */
public class WXPayMediator implements ProcessMediator.Logic {
    public static String APPID;
    public IWXAPI api;

    @Override // io.dcloud.ProcessMediator.Logic
    public void exec(Context context, Intent intent) {
        String metaValue = AndroidResources.getMetaValue("WX_APPID");
        APPID = metaValue;
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(context, metaValue);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(APPID);
        Bundle bundleExtra = intent.getBundleExtra("req");
        PayReq payReq = new PayReq();
        payReq.fromBundle(bundleExtra);
        this.api.sendReq(payReq);
    }
}
