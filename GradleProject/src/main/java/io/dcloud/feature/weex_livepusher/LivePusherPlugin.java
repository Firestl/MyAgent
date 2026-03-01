package io.dcloud.feature.weex_livepusher;

import android.content.Context;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.feature.weex.WeexInstanceMgr;

/* JADX INFO: loaded from: classes3.dex */
public class LivePusherPlugin {
    public static void initPlugin(Context context) {
        try {
            WXSDKEngine.registerComponent("live-pusher", (Class<? extends WXComponent>) PusherComponent.class);
            WeexInstanceMgr.self().addComponentByName("live-pusher", PusherComponent.class);
        } catch (WXException e2) {
            e2.printStackTrace();
        }
    }
}
