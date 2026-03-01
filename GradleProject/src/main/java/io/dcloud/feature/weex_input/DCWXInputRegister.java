package io.dcloud.feature.weex_input;

import android.content.Context;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.component.DCTextArea;
import com.taobao.weex.ui.component.DCWXInput;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.feature.weex.WeexInstanceMgr;

/* JADX INFO: loaded from: classes3.dex */
public class DCWXInputRegister {
    public static void initPlugin(Context context) {
        try {
            WXSDKEngine.registerComponent("u-input", (Class<? extends WXComponent>) DCWXInput.class);
            WXSDKEngine.registerComponent("u-textarea", (Class<? extends WXComponent>) DCTextArea.class, false);
            WeexInstanceMgr.self().addComponentByName("input", DCWXInput.class);
            WeexInstanceMgr.self().addComponentByName(WXBasicComponentType.TEXTAREA, DCTextArea.class);
        } catch (WXException e2) {
            e2.printStackTrace();
        }
    }
}
