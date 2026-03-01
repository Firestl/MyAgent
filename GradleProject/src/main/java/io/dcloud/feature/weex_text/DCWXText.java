package io.dcloud.feature.weex_text;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.component.WXVContainer;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes3.dex */
@Component(lazyload = false)
public class DCWXText extends WXText {

    public static class Creator implements ComponentCreator {
        @Override // com.taobao.weex.ui.ComponentCreator
        public WXComponent createInstance(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) throws IllegalAccessException, InstantiationException, InvocationTargetException {
            return new DCWXText(wXSDKInstance, wXVContainer, basicComponentData);
        }
    }

    public DCWXText(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        setContentBoxMeasurement(new DCTextContentBoxMeasurement(this));
    }

    @WXComponentProp(name = "selectable")
    public void setSelectable(boolean z) {
        getHostView().enableCopy(true);
    }
}
