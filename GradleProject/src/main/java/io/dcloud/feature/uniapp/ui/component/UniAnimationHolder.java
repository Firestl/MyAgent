package io.dcloud.feature.uniapp.ui.component;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.action.GraphicActionAnimation;
import com.taobao.weex.ui.animation.WXAnimationBean;
import io.dcloud.feature.uniapp.AbsSDKInstance;
import io.dcloud.feature.uniapp.ui.AbsAnimationHolder;
import io.dcloud.feature.uniapp.ui.animation.UniAnimationBean;

/* JADX INFO: loaded from: classes3.dex */
public class UniAnimationHolder implements AbsAnimationHolder {
    public String callback;
    public WXAnimationBean wxAnimationBean;

    public UniAnimationHolder(UniAnimationBean uniAnimationBean, String str) {
        this.wxAnimationBean = uniAnimationBean;
        this.callback = str;
    }

    @Override // io.dcloud.feature.uniapp.ui.AbsAnimationHolder
    public void execute(AbsSDKInstance absSDKInstance, AbsBasicComponent absBasicComponent) {
        if (absSDKInstance == null || absBasicComponent == null) {
            return;
        }
        GraphicActionAnimation graphicActionAnimation = new GraphicActionAnimation((WXSDKInstance) absSDKInstance, absBasicComponent.getRef(), this.wxAnimationBean, this.callback);
        WXSDKManager.getInstance().getWXRenderManager().postGraphicAction(graphicActionAnimation.getPageId(), graphicActionAnimation);
    }
}
