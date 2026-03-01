package io.dcloud.feature.weex_switch;

import android.content.Context;
import android.graphics.Color;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.CSSConstants;
import com.taobao.weex.layout.ContentBoxMeasurement;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_switch.SwitchButton;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class DCWXSwitch extends WXComponent<SwitchButton> {
    public DCWXSwitch(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        setContentBoxMeasurement(new ContentBoxMeasurement() { // from class: io.dcloud.feature.weex_switch.DCWXSwitch.1
            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutAfter(float f, float f2) {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void layoutBefore() {
            }

            @Override // com.taobao.weex.layout.ContentBoxMeasurement
            public void measureInternal(float f, float f2, int i, int i2) {
                int realPxByWidth = (int) WXViewUtils.getRealPxByWidth(31.0f, DCWXSwitch.this.getInstance().getInstanceViewPortWidthWithFloat());
                int realPxByWidth2 = (int) WXViewUtils.getRealPxByWidth(51.0f, DCWXSwitch.this.getInstance().getInstanceViewPortWidthWithFloat());
                if (CSSConstants.isUndefined(f2)) {
                    f = realPxByWidth2;
                    f2 = realPxByWidth;
                }
                this.mMeasureWidth = f;
                this.mMeasureHeight = f2;
            }
        });
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void addEvent(String str) {
        super.addEvent(str);
        if (str == null || !str.equals(Constants.Event.CHANGE) || getHostView() == null) {
            return;
        }
        getHostView().setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() { // from class: io.dcloud.feature.weex_switch.DCWXSwitch.2
            @Override // io.dcloud.feature.weex_switch.SwitchButton.OnCheckedChangeListener
            public void onCheckedChanged(SwitchButton switchButton, boolean z) {
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                map2.put("value", Boolean.valueOf(z));
                map.put("detail", map2);
                DCWXSwitch.this.fireEvent(Constants.Event.CHANGE, map);
            }
        });
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void removeEventFromView(String str) {
        super.removeEventFromView(str);
        if (getHostView() == null || !Constants.Event.CHANGE.equals(str)) {
            return;
        }
        getHostView().setOnCheckedChangeListener(null);
    }

    @WXComponentProp(name = Constants.Name.CHECKED)
    public void setChecked(boolean z) {
        getHostView().setChecked(z);
    }

    @WXComponentProp(name = "color")
    public void setColor(String str) {
        getHostView().setCheckedColor(Color.parseColor(str));
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    @WXComponentProp(name = Constants.Name.DISABLED)
    public void setDisabled(boolean z) {
        getHostView().setEnabled(!z);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public SwitchButton initComponentHostView(Context context) {
        SwitchButton switchButton = new SwitchButton(context);
        switchButton.setShadowEffect(true);
        switchButton.setEnableEffect(true);
        return switchButton;
    }
}
