package io.dcloud.feature.sensor;

import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class f implements IEventCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<IWebview, e> f6612a = new HashMap<>();

    public String a(IWebview iWebview, String str, String[] strArr) {
        e eVar;
        if (str.equals("getCurrentProximity")) {
            String str2 = strArr[0];
            e eVar2 = this.f6612a.get(iWebview);
            if (eVar2 == null) {
                eVar2 = new e(iWebview);
                this.f6612a.put(iWebview, eVar2);
            }
            eVar2.d = str2;
            eVar2.a();
            return null;
        }
        if (!str.equals("start")) {
            if (!str.equals(Constants.Value.STOP) || (eVar = this.f6612a.get(iWebview)) == null) {
                return null;
            }
            eVar.b();
            return null;
        }
        String str3 = strArr[0];
        ((AdaFrameView) iWebview.obtainFrameView()).addFrameViewListener(this);
        e eVar3 = this.f6612a.get(iWebview);
        if (eVar3 == null) {
            eVar3 = new e(iWebview);
            this.f6612a.put(iWebview, eVar3);
        }
        eVar3.f6611e = str3;
        eVar3.a();
        return null;
    }

    @Override // io.dcloud.common.DHInterface.IEventCallback
    public Object onCallBack(String str, Object obj) {
        if ((!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, "close")) || !(obj instanceof IWebview)) {
            return null;
        }
        IWebview iWebview = (IWebview) obj;
        e eVarRemove = this.f6612a.remove(iWebview);
        if (eVarRemove != null) {
            eVarRemove.b();
        }
        ((AdaFrameView) iWebview.obtainFrameView()).removeFrameViewListener(this);
        return null;
    }
}
