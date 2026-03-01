package io.dcloud.feature.oauth.weixin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.dcloud.ProcessMediator;
import io.dcloud.common.adapter.util.AndroidResources;

/* JADX INFO: loaded from: classes3.dex */
public class WeiXinMediator implements ProcessMediator.Logic {
    public String APPID;
    public IWXAPI api;

    @Override // io.dcloud.ProcessMediator.Logic
    public void exec(Context context, Intent intent) {
        String metaValue = AndroidResources.getMetaValue("WX_APPID");
        this.APPID = metaValue;
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(context, metaValue, true);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(this.APPID);
        Bundle bundleExtra = intent.getBundleExtra("req");
        SendAuth.Req req = new SendAuth.Req();
        req.fromBundle(bundleExtra);
        this.api.sendReq(req);
    }
}
