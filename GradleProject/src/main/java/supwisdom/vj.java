package supwisdom;

import com.alibaba.android.bindingx.plugin.weex.WXBindingXModule;
import com.alibaba.android.bindingx.plugin.weex.WXExpressionBindingModule;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/* JADX INFO: compiled from: BindingX.java */
/* JADX INFO: loaded from: classes.dex */
public class vj {
    public static void a() throws WXException {
        WXSDKEngine.registerModule("expressionBinding", WXExpressionBindingModule.class);
        WXSDKEngine.registerModule("binding", WXBindingXModule.class);
        WXSDKEngine.registerModule("bindingx", WXBindingXModule.class);
    }
}
