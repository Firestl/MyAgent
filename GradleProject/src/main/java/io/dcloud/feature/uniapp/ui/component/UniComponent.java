package io.dcloud.feature.uniapp.ui.component;

import android.view.View;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.uniapp.ui.AbsAnimationHolder;
import io.dcloud.feature.uniapp.ui.AbsIComponentHolder;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;

/* JADX INFO: loaded from: classes3.dex */
public class UniComponent<T extends View> extends WXComponent<T> {
    public UniSDKInstance mUniSDKInstance;

    public interface OnClickListener extends WXComponent.OnClickListener {
    }

    public interface OnFocusChangeListener extends WXComponent.OnFocusChangeListener {
    }

    public UniComponent(UniSDKInstance uniSDKInstance, AbsVContainer absVContainer, int i, AbsComponentData absComponentData) {
        super(uniSDKInstance, (WXVContainer) absVContainer, i, (BasicComponentData) absComponentData);
        this.mUniSDKInstance = uniSDKInstance;
    }

    public final void addClickListener(OnClickListener onClickListener) {
        super.addClickListener((WXComponent.OnClickListener) onClickListener);
    }

    public final void addFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        super.addFocusChangeListener((WXComponent.OnFocusChangeListener) onFocusChangeListener);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public final void bindData(WXComponent wXComponent) {
        super.bindData(wXComponent);
    }

    public void bindHolder(AbsIComponentHolder absIComponentHolder) {
        super.bindHolder((IFComponentHolder) absIComponentHolder);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public void destroy() {
        super.destroy();
        this.mUniSDKInstance = null;
    }

    public final AbsBasicComponent findUniComponent(String str) {
        if (getInstance() == null || str == null) {
            return null;
        }
        return WXSDKManager.getInstance().getWXRenderManager().getWXComponent(getInstanceId(), str);
    }

    public UniSDKInstance getUniInstance() {
        return this.mUniSDKInstance;
    }

    public void postAnimation(UniAnimationHolder uniAnimationHolder) {
        super.postAnimation((AbsAnimationHolder) uniAnimationHolder);
    }

    public void updateStyles(UniComponent uniComponent) {
        updateStyles(uniComponent);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public final void bindHolder(IFComponentHolder iFComponentHolder) {
        super.bindHolder(iFComponentHolder);
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public final void updateStyles(WXComponent wXComponent) {
        super.updateStyles(wXComponent);
    }

    public UniComponent(UniSDKInstance uniSDKInstance, AbsVContainer absVContainer, AbsComponentData absComponentData) {
        super(uniSDKInstance, (WXVContainer) absVContainer, (BasicComponentData) absComponentData);
        this.mUniSDKInstance = uniSDKInstance;
    }
}
