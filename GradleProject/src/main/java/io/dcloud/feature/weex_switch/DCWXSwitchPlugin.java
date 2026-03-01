package io.dcloud.feature.weex_switch;

import android.content.Context;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.feature.weex.WeexInstanceMgr;

/* JADX INFO: loaded from: classes3.dex */
public class DCWXSwitchPlugin {
    public static void initPlugin(Context context) {
        try {
            WXSDKEngine.registerComponent("dc-switch", (Class<? extends WXComponent>) DCWXSwitch.class);
            WeexInstanceMgr.self().addComponentByName(Constants.Name.Recycler.LIST_DATA_TEMPLATE_SWITCH_KEY, DCWXSwitch.class);
        } catch (WXException e2) {
            e2.printStackTrace();
        }
    }
}
