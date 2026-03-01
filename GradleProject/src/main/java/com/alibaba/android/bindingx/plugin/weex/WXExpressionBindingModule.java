package com.alibaba.android.bindingx.plugin.weex;

import android.content.Context;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import java.util.List;
import java.util.Map;
import supwisdom.jj;
import supwisdom.si;
import supwisdom.vi;
import supwisdom.xj;
import supwisdom.yi;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class WXExpressionBindingModule extends WXSDKEngine.DestroyableModule {
    public si mExpressionBindingCore;
    public yi mPlatformManager;

    public class a implements si.e<vi, Context, yi> {
        public a(WXExpressionBindingModule wXExpressionBindingModule) {
        }

        @Override // supwisdom.si.e
        public vi a(Context context, yi yiVar, Object... objArr) {
            return new xj(context, yiVar, objArr);
        }
    }

    public class b implements si.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSCallback f1530a;

        public b(WXExpressionBindingModule wXExpressionBindingModule, JSCallback jSCallback) {
            this.f1530a = jSCallback;
        }

        @Override // supwisdom.si.d
        public void a(Object obj) {
            JSCallback jSCallback = this.f1530a;
            if (jSCallback != null) {
                jSCallback.invokeAndKeepAlive(obj);
            }
        }
    }

    @JSMethod
    @Deprecated
    public void createBinding(String str, String str2, String str3, List<Map<String, Object>> list, JSCallback jSCallback) {
        enableBinding(null, null);
        jj jjVarA = jj.a(null, str3);
        si siVar = this.mExpressionBindingCore;
        b bVar = new b(this, jSCallback);
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        Context context = wXSDKInstance == null ? null : wXSDKInstance.getContext();
        WXSDKInstance wXSDKInstance2 = this.mWXSDKInstance;
        siVar.a(str, null, str2, null, jjVarA, list, null, bVar, context, wXSDKInstance2 != null ? wXSDKInstance2.getInstanceId() : null, new Object[0]);
    }

    @Override // com.taobao.weex.common.Destroyable
    public void destroy() {
        si siVar = this.mExpressionBindingCore;
        if (siVar != null) {
            siVar.a();
            this.mExpressionBindingCore = null;
        }
    }

    @JSMethod
    @Deprecated
    public void disableAll() {
        si siVar = this.mExpressionBindingCore;
        if (siVar != null) {
            siVar.a();
        }
    }

    @JSMethod
    @Deprecated
    public void disableBinding(String str, String str2) {
        si siVar = this.mExpressionBindingCore;
        if (siVar != null) {
            siVar.a(str, str2);
        }
    }

    @JSMethod
    @Deprecated
    public void enableBinding(String str, String str2) {
        if (this.mPlatformManager == null) {
            this.mPlatformManager = WXBindingXModule.createPlatformManager(this.mWXSDKInstance);
        }
        if (this.mExpressionBindingCore == null) {
            si siVar = new si(this.mPlatformManager);
            this.mExpressionBindingCore = siVar;
            siVar.a(Constants.Event.SCROLL, new a(this));
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityPause() {
        si siVar = this.mExpressionBindingCore;
        if (siVar != null) {
            siVar.c();
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResume() {
        si siVar = this.mExpressionBindingCore;
        if (siVar != null) {
            siVar.d();
        }
    }
}
