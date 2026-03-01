package com.alibaba.android.bindingx.plugin.weex;

import android.content.Context;
import android.view.View;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.BasicListComponent;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import supwisdom.ak;
import supwisdom.si;
import supwisdom.vi;
import supwisdom.wj;
import supwisdom.xi;
import supwisdom.xj;
import supwisdom.yi;
import supwisdom.zj;

/* JADX INFO: loaded from: classes.dex */
public class WXBindingXModule extends WXSDKEngine.DestroyableModule {
    public si mBindingXCore;
    public yi mPlatformManager;

    public class a implements si.e<vi, Context, yi> {
        public a(WXBindingXModule wXBindingXModule) {
        }

        @Override // supwisdom.si.e
        public vi a(Context context, yi yiVar, Object... objArr) {
            return new xj(context, yiVar, objArr);
        }
    }

    public class b implements si.e<vi, Context, yi> {
        public b(WXBindingXModule wXBindingXModule) {
        }

        @Override // supwisdom.si.e
        public vi a(Context context, yi yiVar, Object... objArr) {
            return new wj(context, yiVar, objArr);
        }
    }

    public class c implements si.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSCallback f1525a;

        public c(WXBindingXModule wXBindingXModule, JSCallback jSCallback) {
            this.f1525a = jSCallback;
        }

        @Override // supwisdom.si.d
        public void a(Object obj) {
            JSCallback jSCallback = this.f1525a;
            if (jSCallback != null) {
                jSCallback.invokeAndKeepAlive(obj);
            }
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WXBindingXModule.this.mBindingXCore != null) {
                WXBindingXModule.this.mBindingXCore.a();
                WXBindingXModule.this.mBindingXCore = null;
            }
            ak.a();
        }
    }

    public static class e implements yi.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1527a;

        public e(int i) {
            this.f1527a = i;
        }

        @Override // supwisdom.yi.c
        public double a(double d, Object... objArr) {
            return WXViewUtils.getWebPxByWidth((float) d, this.f1527a);
        }

        @Override // supwisdom.yi.c
        public double b(double d, Object... objArr) {
            return WXViewUtils.getRealPxByWidth((float) d, this.f1527a);
        }
    }

    public static class f implements yi.e {
        @Override // supwisdom.yi.e
        public void a(View view, String str, Object obj, yi.c cVar, Map<String, Object> map, Object... objArr) {
            if (objArr == null || objArr.length < 2 || !(objArr[0] instanceof String) || !(objArr[1] instanceof String)) {
                return;
            }
            String str2 = (String) objArr[0];
            String str3 = (String) objArr[1];
            WXComponent wXComponentA = zj.a(str3, str2);
            if (wXComponentA != null) {
                ak.a(str).a(wXComponentA, view, obj, cVar, map);
                return;
            }
            xi.b("unexpected error. component not found [ref:" + str2 + ",instanceId:" + str3 + Operators.ARRAY_END_STR);
        }
    }

    public static class g implements yi.d {
        @Override // supwisdom.yi.d
        public View a(String str, Object... objArr) {
            if (objArr.length <= 0 || !(objArr[0] instanceof String)) {
                return null;
            }
            return zj.b((String) objArr[0], str);
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WXBindingXModule.this.mBindingXCore != null) {
                WXBindingXModule.this.mBindingXCore.c();
            }
        }
    }

    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WXBindingXModule.this.mBindingXCore != null) {
                WXBindingXModule.this.mBindingXCore.d();
            }
        }
    }

    public WXBindingXModule() {
    }

    public static yi createPlatformManager(WXSDKInstance wXSDKInstance) {
        int instanceViewPortWidth = wXSDKInstance == null ? WXSDKManager.DEFAULT_VIEWPORT_WIDTH : wXSDKInstance.getInstanceViewPortWidth();
        yi.b bVar = new yi.b();
        bVar.a(new g());
        bVar.a(new f());
        bVar.a(new e(instanceViewPortWidth));
        return bVar.a();
    }

    private void prepareInternal() {
        if (this.mPlatformManager == null) {
            this.mPlatformManager = createPlatformManager(this.mWXSDKInstance);
        }
        if (this.mBindingXCore == null) {
            si siVar = new si(this.mPlatformManager);
            this.mBindingXCore = siVar;
            siVar.a(Constants.Event.SCROLL, new a(this));
            this.mBindingXCore.a(BasicListComponent.DragTriggerType.PAN, new b(this));
        }
    }

    @JSMethod(uiThread = false)
    public Map<String, String> bind(Map<String, Object> map, JSCallback jSCallback) {
        prepareInternal();
        si siVar = this.mBindingXCore;
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        Context context = wXSDKInstance == null ? null : wXSDKInstance.getContext();
        WXSDKInstance wXSDKInstance2 = this.mWXSDKInstance;
        String instanceId = wXSDKInstance2 != null ? wXSDKInstance2.getInstanceId() : null;
        if (map == null) {
            map = Collections.emptyMap();
        }
        String strA = siVar.a(context, instanceId, map, new c(this, jSCallback), new Object[0]);
        HashMap map2 = new HashMap(2);
        map2.put("token", strA);
        return map2;
    }

    @JSMethod(uiThread = false)
    public void bindAsync(Map<String, Object> map, JSCallback jSCallback, JSCallback jSCallback2) {
        Map<String, String> mapBind = bind(map, jSCallback);
        if (jSCallback2 == null || mapBind == null) {
            return;
        }
        jSCallback2.invoke(mapBind);
    }

    @Override // com.taobao.weex.common.Destroyable
    public void destroy() {
        WXBridgeManager.getInstance().post(new d(), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x01e3  */
    @com.taobao.weex.annotation.JSMethod(uiThread = false)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<java.lang.String, java.lang.Object> getComputedStyle(java.lang.String r18) {
        /*
            Method dump skipped, instruction units count: 776
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.plugin.weex.WXBindingXModule.getComputedStyle(java.lang.String):java.util.Map");
    }

    @JSMethod(uiThread = false)
    public void getComputedStyleAsync(String str, JSCallback jSCallback) {
        Map<String, Object> computedStyle = getComputedStyle(str);
        if (jSCallback != null) {
            jSCallback.invoke(computedStyle);
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityPause() {
        WXBridgeManager.getInstance().post(new h(), null);
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResume() {
        WXBridgeManager.getInstance().post(new i(), null);
    }

    @JSMethod(uiThread = false)
    public void prepare(Map<String, Object> map) {
        prepareInternal();
    }

    @JSMethod(uiThread = false)
    public List<String> supportFeatures() {
        return Arrays.asList(BasicListComponent.DragTriggerType.PAN, Constants.Name.ORIENTATION, "timing", Constants.Event.SCROLL, "experimentalGestureFeatures");
    }

    @JSMethod(uiThread = false)
    public void unbind(Map<String, Object> map) {
        si siVar = this.mBindingXCore;
        if (siVar != null) {
            siVar.a(map);
        }
    }

    @JSMethod(uiThread = false)
    public void unbindAll() {
        si siVar = this.mBindingXCore;
        if (siVar != null) {
            siVar.a();
        }
    }

    public WXBindingXModule(si siVar) {
        this.mBindingXCore = siVar;
    }
}
