package supwisdom;

import android.view.View;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.ui.component.WXComponent;

/* JADX INFO: compiled from: WXModuleUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class zj {
    public static WXComponent a(String str, String str2) {
        return WXSDKManager.getInstance().getWXRenderManager().getWXComponent(str, str2);
    }

    public static View b(String str, String str2) {
        WXComponent wXComponentA = a(str, str2);
        if (wXComponentA == null) {
            return null;
        }
        return wXComponentA.getHostView();
    }
}
