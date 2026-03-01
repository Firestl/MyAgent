package io.dcloud.feature.weex_amap;

import android.content.Context;
import com.taobao.weex.common.WXException;
import io.dcloud.feature.uniapp.UniSDKEngine;
import io.dcloud.feature.weex.WeexInstanceMgr;
import io.dcloud.feature.weex_amap.Module.WXMapSearchModule;
import io.dcloud.feature.weex_amap.component.WXAMapViewComponent;

/* JADX INFO: loaded from: classes3.dex */
public class AMapPluginImpl {
    public static void initPlugin(Context context) {
        try {
            UniSDKEngine.registerUniVContainer("map", WXAMapViewComponent.class);
            UniSDKEngine.registerUniModule("mapSearch", WXMapSearchModule.class);
            WeexInstanceMgr.self().addComponentByName("map", WXAMapViewComponent.class);
        } catch (WXException e2) {
            e2.printStackTrace();
        }
    }
}
