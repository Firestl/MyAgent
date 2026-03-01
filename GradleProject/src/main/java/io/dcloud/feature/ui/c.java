package io.dcloud.feature.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.el.parse.Operators;
import com.umeng.analytics.pro.bm;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IContainerView;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.DHInterface.INativeView;
import io.dcloud.common.DHInterface.ITitleNView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.ReceiveJSValue;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class c extends io.dcloud.feature.ui.b implements IEventCallback {
    public static final HashMap<String, String> Y;
    public int A;
    public Object B;
    public boolean C;
    public boolean D;
    public int E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public ArrayList<io.dcloud.feature.ui.b> L;
    public String M;
    public IWebview N;
    public String O;
    public IWebview P;
    public String Q;
    public IWebview R;
    public c S;
    public ArrayList<c> T;
    public String U;
    public int V;
    public boolean W;
    public Runnable X;
    public long u;
    public JSONArray v;
    public IWebview w;
    public JSONObject x;
    public IFrameView y;
    public String z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.this.y.obtainWebView().checkWhite(c.this.U)) {
                c.this.l();
            } else {
                c.this.a(AbsoluteConst.EVENTS_WEBVIEW_RENDERED, "{}", false);
            }
            c.this.X = null;
        }
    }

    public class b implements ReceiveJSValue.ReceiveJSValueCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6619a;
        public final /* synthetic */ String b;

        public b(c cVar, IWebview iWebview, String str) {
            this.f6619a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.adapter.ui.ReceiveJSValue.ReceiveJSValueCallback
        public String callback(JSONArray jSONArray) {
            Object obj;
            String string = JSONUtil.getString(jSONArray, 0);
            try {
                obj = jSONArray.get(1);
            } catch (JSONException unused) {
                obj = null;
            }
            if ((obj instanceof String) || "string".equals(string)) {
                Deprecated_JSUtil.execCallback(this.f6619a, this.b, String.valueOf(obj), JSUtil.OK, false, false);
            } else if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || "object".equals(string) || !"undefined".equals(string)) {
                Deprecated_JSUtil.execCallback(this.f6619a, this.b, obj.toString(), JSUtil.OK, true, false);
            } else {
                Deprecated_JSUtil.execCallback(this.f6619a, this.b, "undefined", JSUtil.OK, true, false);
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: io.dcloud.feature.ui.c$c, reason: collision with other inner class name */
    public class RunnableC0177c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6620a;
        public final /* synthetic */ String b;

        public RunnableC0177c(c cVar, IWebview iWebview, String str) {
            this.f6620a = iWebview;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6620a.evalJSSync(this.b, null);
        }
    }

    public class d implements MessageHandler.UncheckedCallable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6621a;
        public final /* synthetic */ String b;

        public class a implements ICallBack {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ MessageHandler.WaitableRunnable f6622a;

            public a(d dVar, MessageHandler.WaitableRunnable waitableRunnable) {
                this.f6622a = waitableRunnable;
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                MessageHandler.WaitableRunnable waitableRunnable = this.f6622a;
                if (waitableRunnable == null) {
                    return null;
                }
                waitableRunnable.callBack(obj);
                return null;
            }
        }

        public d(c cVar, IWebview iWebview, String str) {
            this.f6621a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.UncheckedCallable
        public void run(MessageHandler.WaitableRunnable waitableRunnable) {
            try {
                this.f6621a.evalJSSync(this.b, new a(this, waitableRunnable));
            } catch (Exception e2) {
                e2.printStackTrace();
                if (waitableRunnable != null) {
                    waitableRunnable.callBack("");
                }
            }
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6623a;
        public final /* synthetic */ String b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        public e(IWebview iWebview, String str, IWebview iWebview2, String str2) {
            this.f6623a = iWebview;
            this.b = str;
            this.c = iWebview2;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                boolean zCheckWhite = this.f6623a.checkWhite(this.b);
                IWebview iWebview = this.c;
                String str = this.d;
                StringBuilder sb = new StringBuilder();
                sb.append("{\"code\":100,\"rendered\":");
                sb.append(!zCheckWhite);
                sb.append(Operators.BLOCK_END_STR);
                Deprecated_JSUtil.execCallback(iWebview, str, sb.toString(), JSUtil.OK, true, false);
            } catch (Exception unused) {
                if (c.this.a() != null) {
                    Deprecated_JSUtil.execCallback(this.c, this.d, "{\"code\":-100,\"message\":\"" + c.this.a().getString(R.string.dcloud_common_screenshot_fail) + "\"}", JSUtil.ERROR, true, false);
                }
            }
        }
    }

    public class f implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6625a;
        public final /* synthetic */ String b;

        public f(c cVar, IWebview iWebview, String str) {
            this.f6625a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            Deprecated_JSUtil.execCallback(this.f6625a, this.b, null, JSUtil.OK, false, false);
            return null;
        }
    }

    public class g implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6626a;
        public final /* synthetic */ String b;

        public g(c cVar, IWebview iWebview, String str) {
            this.f6626a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            Deprecated_JSUtil.execCallback(this.f6626a, this.b, "{\"code\":-100,\"message\":\"" + this.f6626a.getContext().getString(R.string.dcloud_common_screenshot_fail) + "\"}", JSUtil.ERROR, true, false);
            return null;
        }
    }

    public class h implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6627a;
        public final /* synthetic */ String b;

        public h(c cVar, IWebview iWebview, String str) {
            this.f6627a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            Deprecated_JSUtil.execCallback(this.f6627a, this.b, null, JSUtil.OK, false, false);
            return null;
        }
    }

    public class i implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6628a;
        public final /* synthetic */ String b;

        public i(IWebview iWebview, String str) {
            this.f6628a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            IWebview iWebview = this.f6628a;
            String str = this.b;
            StringBuilder sb = new StringBuilder();
            sb.append("{\"code\":");
            sb.append(i);
            sb.append(",\"message\":\"");
            sb.append(obj != null ? obj.toString() : c.this.a().getString(R.string.dcloud_common_screenshot_fail));
            sb.append("\"}");
            Deprecated_JSUtil.execCallback(iWebview, str, sb.toString(), JSUtil.ERROR, true, false);
            return null;
        }
    }

    public class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6629a;
        public final /* synthetic */ String b;

        public j(String str, String str2) {
            this.f6629a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            TestUtil.PointTime.commitTid(c.this.a(), this.f6629a, c.this.g, this.b, 10);
        }
    }

    static {
        HashMap<String, String> map = new HashMap<>();
        Y = map;
        map.put("close", "onclose");
        map.put("loading", "onloading");
        map.put(AbsoluteConst.EVENTS_FAILED, "onerror");
        map.put(AbsoluteConst.EVENTS_LOADED, "onloaded");
    }

    public c(io.dcloud.feature.ui.a aVar, String str, String str2, String str3, JSONObject jSONObject) {
        this(aVar, null, str, str2, str3, jSONObject);
    }

    private INativeBitmap g(IWebview iWebview, String str) {
        return (INativeBitmap) iWebview.obtainApp().obtainMgrData(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "getNativeBitmap", new String[]{iWebview.obtainApp().obtainAppId(), str}});
    }

    private void i() {
        if (this.y.getFrameType() == 6) {
            ThreadPool.self().addThreadTask(new j(this.c.f.obtainAppId(), this.c.f.obtainConfigProperty("adid")));
        }
    }

    private void j() {
        View viewObtainMainView = this.y.obtainMainView();
        if (viewObtainMainView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewObtainMainView;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt instanceof ITitleNView) {
                    childAt.bringToFront();
                    return;
                }
            }
        }
    }

    private boolean k() {
        c cVar = this.f6616a;
        if (cVar != null) {
            return cVar.F && cVar.k();
        }
        return true;
    }

    private void s() {
        this.c.b(this);
        if (!this.H) {
            d().onDispose();
            d().dispose();
        } else if (!this.I) {
            if (this.K) {
                c cVar = this.f6616a;
                if (cVar != null) {
                    cVar.c(this);
                }
                d().onDispose();
                d().dispose();
            } else {
                ((AdaFrameItem) this.y).getAnimOptions().mOption = (byte) 1;
            }
        }
        e();
    }

    private void t() {
        ((AdaFrameItem) this.y).getAnimOptions().mOption = (byte) 3;
        this.F = false;
        this.G = true;
    }

    @Override // io.dcloud.feature.ui.b
    public void a(int i2, int i3, int i4, int i5, int i6, int i7) {
    }

    public void b(boolean z) {
    }

    public boolean b(io.dcloud.feature.ui.b bVar) {
        ArrayList<io.dcloud.feature.ui.b> arrayList = this.L;
        if (arrayList == null) {
            return false;
        }
        return arrayList.contains(bVar);
    }

    public io.dcloud.feature.ui.b c(String str) {
        ArrayList<io.dcloud.feature.ui.b> arrayList = this.L;
        io.dcloud.feature.ui.b bVar = null;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int size = this.L.size() - 1; size >= 0; size--) {
                bVar = this.L.get(size);
                if (PdrUtil.isEquals(str, bVar.f)) {
                    break;
                }
            }
        }
        return bVar;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void d(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "maps", "appendToFrameView", new Object[]{this.y, str}});
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void e(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "addNativeView", new Object[]{this.y, str}});
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void f(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "videoplayer", "appendToFrameView", new Object[]{this.y, str}});
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void h(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "removeNativeView", new Object[]{this.y, str}});
    }

    public void l() {
        HashMap<String, ArrayList<String[]>> map = this.m;
        if (map == null || !map.containsKey(AbsoluteConst.EVENTS_WEBVIEW_RENDERED)) {
            return;
        }
        Runnable runnable = this.X;
        if (runnable != null) {
            MessageHandler.removeCallbacks(runnable);
        }
        a aVar = new a();
        this.X = aVar;
        MessageHandler.postDelayed(aVar, this.V);
    }

    public String m() {
        IWebview iWebviewObtainWebView = this.y.obtainWebView();
        if (iWebviewObtainWebView != null) {
            return iWebviewObtainWebView.obtainFrameId();
        }
        return null;
    }

    public String n() {
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) this.y).obtainFrameOptions();
        return StringUtil.format("{top:%d,left:%d,width:%d,height:%d}", Integer.valueOf((int) (viewOptionsObtainFrameOptions.top / viewOptionsObtainFrameOptions.mWebviewScale)), Integer.valueOf((int) (viewOptionsObtainFrameOptions.left / viewOptionsObtainFrameOptions.mWebviewScale)), Integer.valueOf((int) (viewOptionsObtainFrameOptions.width / viewOptionsObtainFrameOptions.mWebviewScale)), Integer.valueOf((int) (viewOptionsObtainFrameOptions.height / viewOptionsObtainFrameOptions.mWebviewScale)));
    }

    public boolean o() {
        return !this.y.isWebviewCovered();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0125  */
    @Override // io.dcloud.common.DHInterface.IEventCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object onCallBack(java.lang.String r14, java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 1038
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.onCallBack(java.lang.String, java.lang.Object):java.lang.Object");
    }

    public boolean p() {
        return this.W;
    }

    public void q() {
    }

    public IWebview r() {
        return this.y.obtainWebView();
    }

    public c(io.dcloud.feature.ui.a aVar, IFrameView iFrameView, String str, String str2, String str3, JSONObject jSONObject) {
        super("NWindow");
        this.u = System.currentTimeMillis();
        this.v = null;
        this.w = null;
        this.x = null;
        this.z = null;
        this.C = false;
        this.D = false;
        this.E = 0;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = "auto";
        this.V = 150;
        this.W = false;
        this.X = null;
        this.c = aVar;
        this.z = str;
        this.f6617e = str3;
        this.h = jSONObject;
        a(iFrameView, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0068  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e(io.dcloud.common.DHInterface.IWebview r20, org.json.JSONArray r21, io.dcloud.feature.ui.c r22) {
        /*
            Method dump skipped, instruction units count: 885
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.e(io.dcloud.common.DHInterface.IWebview, org.json.JSONArray, io.dcloud.feature.ui.c):void");
    }

    public void a(boolean z) {
        this.W = z;
    }

    @Override // io.dcloud.feature.ui.b
    public AdaFrameItem d() {
        return (AdaFrameItem) this.y;
    }

    @Override // io.dcloud.feature.ui.b
    public String h() {
        if (PdrUtil.isEmpty(r().obtainFrameId())) {
            Object[] objArr = new Object[4];
            objArr[0] = this.f6617e;
            objArr[1] = "undefined";
            objArr[2] = this.d;
            JSONObject jSONObject = this.x;
            objArr[3] = jSONObject != null ? jSONObject.toString() : "{}";
            return StringUtil.format("(function(){return {'uuid':'%s','id':%s,'identity':'%s','extras':%s}})()", objArr);
        }
        Object[] objArr2 = new Object[4];
        objArr2[0] = this.f6617e;
        objArr2[1] = r().obtainFrameId();
        objArr2[2] = this.d;
        JSONObject jSONObject2 = this.x;
        objArr2[3] = jSONObject2 != null ? jSONObject2.toString() : "{}";
        return StringUtil.format("(function(){return {'uuid':'%s','id':'%s','identity':'%s','extras':%s}})()", objArr2);
    }

    private void d(IWebview iWebview, JSONArray jSONArray, c cVar) {
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        AnimOptions animOptions = ((AdaFrameItem) cVar.y).getAnimOptions();
        if (!PdrUtil.isEmpty(string2)) {
            animOptions.duration_close = PdrUtil.parseInt(string2, animOptions.duration_close);
        } else {
            animOptions.duration_close = animOptions.duration_show;
        }
        animOptions.setCloseAnimType(string);
        animOptions.mOption = (byte) 3;
        Logger.d(Logger.VIEW_VISIBLE_TAG, "NWindow.hide view=" + cVar.d());
        if (cVar.F) {
            if (cVar.k()) {
                a(iWebview, JSONUtil.getJSONObject(jSONArray, 2), cVar, string);
                this.c.d.processEvent(IMgr.MgrType.WindowMgr, 23, cVar.y);
            } else {
                onCallBack("hide", null);
                cVar.y.setVisible(false, true);
            }
            cVar.F = false;
        } else {
            cVar.y.setVisible(false, true);
        }
        cVar.G = true;
    }

    public void a(IFrameView iFrameView, String str) {
        if (iFrameView != null) {
            this.y = iFrameView;
            IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
            if (iWebviewObtainWebView != null) {
                iWebviewObtainWebView.initWebviewUUID(this.f6617e);
                iWebviewObtainWebView.setFrameId(str);
            }
        }
    }

    public void b(c cVar) {
        if (this.T == null) {
            this.T = new ArrayList<>();
        }
        this.T.add(cVar);
        cVar.S = this;
        if (cVar.r() != null) {
            cVar.r().setOpener(r());
        }
    }

    public void c(io.dcloud.feature.ui.b bVar) {
        ArrayList<io.dcloud.feature.ui.b> arrayList = this.L;
        if (arrayList == null || !arrayList.contains(bVar)) {
            return;
        }
        this.L.remove(bVar);
        bVar.f6616a = null;
        byte bC = bVar.c();
        boolean z = bVar instanceof c;
        if (bC == io.dcloud.feature.ui.b.n) {
            this.y.obtainWebView().removeFrameItem(bVar.d());
            return;
        }
        if (bC == io.dcloud.feature.ui.b.o) {
            this.y.obtainWebviewParent().removeFrameItem(bVar.d());
        } else if (bC == io.dcloud.feature.ui.b.p) {
            this.y.removeFrameItem(bVar.d());
            if (z) {
                this.y.obtainWebviewParent().obtainFrameOptions().delRelViewRect(bVar.d().obtainFrameOptions());
            }
            d().resize();
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void a(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, bm.aA, "addNativeView", new Object[]{this.y, str}});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e0  */
    /* JADX WARN: Type inference failed for: r21v0, types: [io.dcloud.common.DHInterface.IContainerView] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(io.dcloud.feature.ui.b r23) {
        /*
            Method dump skipped, instruction units count: 664
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.feature.ui.b):void");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void b(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "barcode", "appendToFrameView", new Object[]{this.y, str}});
    }

    public void b(IWebview iWebview, JSONArray jSONArray, c cVar) {
        if (cVar.H) {
            if (!cVar.I) {
                this.c.b(cVar);
                if (cVar.K) {
                    c cVar2 = cVar.f6616a;
                    if (cVar2 != null) {
                        cVar2.c(cVar);
                    }
                    cVar.d().onDispose();
                    cVar.d().dispose();
                } else {
                    String string = JSONUtil.getString(jSONArray, 0);
                    String string2 = JSONUtil.getString(jSONArray, 1);
                    AnimOptions animOptions = ((AdaFrameItem) cVar.y).getAnimOptions();
                    if (PdrUtil.isEmpty(string)) {
                        string = "auto";
                    }
                    if (!PdrUtil.isEmpty(string2)) {
                        animOptions.duration_close = PdrUtil.parseInt(string2, animOptions.duration_close);
                    } else if (string.equals("pop-out")) {
                        animOptions.duration_close = 360;
                    } else {
                        animOptions.duration_close = animOptions.duration_show;
                    }
                    animOptions.setCloseAnimType(string);
                    animOptions.mOption = (byte) 1;
                    a(iWebview, JSONUtil.getJSONObject(jSONArray, 2), cVar, string);
                    this.c.d.processEvent(IMgr.MgrType.WindowMgr, 2, cVar.y);
                }
            }
        } else {
            this.c.b(cVar);
            cVar.d().onDispose();
            cVar.d().dispose();
        }
        cVar.e();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void c(IWebview iWebview, String str) {
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "livepusher", "appendToFrameView", new Object[]{this.y, str}});
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(io.dcloud.common.DHInterface.IWebview r18, org.json.JSONArray r19, io.dcloud.feature.ui.c r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = 0
            java.lang.String r4 = io.dcloud.common.util.JSONUtil.getString(r2, r3)
            r5 = 1
            java.lang.String r5 = io.dcloud.common.util.JSONUtil.getString(r2, r5)
            io.dcloud.feature.ui.a r6 = r0.c
            r7 = 0
            io.dcloud.feature.ui.c r5 = r6.a(r5, r5, r7)
            io.dcloud.common.adapter.ui.AdaFrameItem r5 = r5.d()
            android.view.View r9 = r5.obtainMainView()
            r5 = 2
            java.lang.String r5 = io.dcloud.common.util.JSONUtil.getString(r2, r5)
            r6 = 3
            org.json.JSONObject r2 = io.dcloud.common.util.JSONUtil.getJSONObject(r2, r6)
            java.lang.String r6 = "RGB565"
            if (r2 == 0) goto L86
            java.lang.String r8 = "check"
            boolean r8 = r2.optBoolean(r8, r3)
            java.lang.String r10 = "checkKeyboard"
            boolean r10 = r2.optBoolean(r10, r3)
            java.lang.String r11 = "bit"
            java.lang.String r6 = r2.optString(r11, r6)
            java.lang.String r11 = "clip"
            org.json.JSONObject r2 = r2.optJSONObject(r11)
            if (r2 == 0) goto L84
            int r11 = r9.getWidth()
            int r12 = r9.getHeight()
            float r13 = r18.getScale()
            java.lang.String r14 = "left"
            java.lang.String r14 = r2.optString(r14)
            int r14 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r14, r11, r3, r13)
            java.lang.String r15 = "top"
            java.lang.String r15 = r2.optString(r15)
            int r3 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r15, r12, r3, r13)
            java.lang.String r15 = "width"
            java.lang.String r15 = r2.optString(r15)
            int r11 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r15, r11, r11, r13)
            java.lang.String r15 = "height"
            java.lang.String r2 = r2.optString(r15)
            int r2 = io.dcloud.common.util.PdrUtil.convertToScreenInt(r2, r12, r12, r13)
            android.graphics.Rect r12 = new android.graphics.Rect
            r12.<init>(r14, r3, r11, r2)
            r14 = r6
            r11 = r8
            r13 = r12
            goto L8a
        L84:
            r3 = r8
            goto L87
        L86:
            r10 = 0
        L87:
            r11 = r3
            r14 = r6
            r13 = r7
        L8a:
            r12 = r10
            io.dcloud.common.DHInterface.IFrameView r8 = r18.obtainFrameView()
            io.dcloud.common.DHInterface.INativeBitmap r10 = r0.g(r1, r4)
            boolean r2 = android.text.TextUtils.isEmpty(r5)
            if (r2 == 0) goto L9b
            r15 = r7
            goto La1
        L9b:
            io.dcloud.feature.ui.c$h r2 = new io.dcloud.feature.ui.c$h
            r2.<init>(r0, r1, r5)
            r15 = r2
        La1:
            boolean r2 = android.text.TextUtils.isEmpty(r5)
            if (r2 == 0) goto La8
            goto Lad
        La8:
            io.dcloud.feature.ui.c$i r7 = new io.dcloud.feature.ui.c$i
            r7.<init>(r1, r5)
        Lad:
            r16 = r7
            r8.draw(r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.c(io.dcloud.common.DHInterface.IWebview, org.json.JSONArray, io.dcloud.feature.ui.c):void");
    }

    public boolean b(String str, String str2, boolean z) {
        ArrayList<io.dcloud.feature.ui.b> arrayList = this.L;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                io.dcloud.feature.ui.b bVar = this.L.get(size);
                if (bVar instanceof c) {
                    c cVar = (c) bVar;
                    if (cVar.F && cVar.b(str, str2, z)) {
                        return true;
                    }
                }
            }
        }
        return a(str) && a(str, str2, z);
    }

    public boolean c(String str, String str2, boolean z) {
        ArrayList<io.dcloud.feature.ui.b> arrayList = this.L;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                io.dcloud.feature.ui.b bVar = this.L.get(size);
                if (bVar instanceof c) {
                    c cVar = (c) bVar;
                    if (cVar.F && cVar.c(str, str2, z)) {
                        return true;
                    }
                }
            }
        }
        if (a(str)) {
            return a(str, str2, z);
        }
        IFrameView iFrameView = this.y;
        if (iFrameView instanceof AdaFrameView) {
            String str3 = ((AdaFrameView) iFrameView).obtainFrameOptions().historyBack;
            if ((str3.equals("backButton") || str3.equals(ProviderConfigurationPermission.ALL_STR)) && this.y.obtainWebView() != null && this.y.obtainWebView().canGoBack()) {
                this.y.obtainWebView().goBackOrForward(-1);
                return true;
            }
        }
        if ("hide".equals(d().obtainFrameOptions().backButtonAutoControl)) {
            d(this.y.obtainWebView(), JSONUtil.createJSONArray("['auto',null]"), this);
            return true;
        }
        if ("quit".equals(d().obtainFrameOptions().backButtonAutoControl)) {
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 20, this.y.obtainApp());
            return false;
        }
        if (!"close".equals(d().obtainFrameOptions().backButtonAutoControl)) {
            return false;
        }
        b(this.y.obtainWebView(), JSONUtil.createJSONArray("['auto',null]"), this);
        return true;
    }

    public boolean b(String str) {
        ArrayList<io.dcloud.feature.ui.b> arrayList = this.L;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                io.dcloud.feature.ui.b bVar = this.L.get(size);
                if ((bVar instanceof c) && ((c) bVar).a(str)) {
                    return true;
                }
            }
        }
        return a(str);
    }

    private void a(io.dcloud.feature.ui.b bVar, c cVar) {
        if (!BaseInfo.isBase(bVar.a()) || this.z.startsWith(DeviceInfo.HTTP_PROTOCOL) || this.z.startsWith(DeviceInfo.HTTPS_PROTOCOL) || cVar.z.startsWith(DeviceInfo.HTTP_PROTOCOL) || cVar.z.startsWith(DeviceInfo.HTTPS_PROTOCOL) || TextUtils.isEmpty(this.z) || TextUtils.isEmpty(cVar.z)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.FILIATIONLOG, io.dcloud.feature.ui.e.c(WebResUtil.getHBuilderPrintUrl(cVar.r().obtainApp().convert2RelPath(r().obtainUrl()))), io.dcloud.feature.ui.e.c(WebResUtil.getHBuilderPrintUrl(cVar.r().obtainUrl()))));
    }

    public static void a(IContainerView iContainerView, AdaFrameItem adaFrameItem, ViewGroup.LayoutParams layoutParams, int i2, int i3, int i4, int i5) {
        ViewOptions viewOptionsObtainFrameOptions = adaFrameItem.obtainFrameOptions();
        viewOptionsObtainFrameOptions.left = i2;
        viewOptionsObtainFrameOptions.top = i3;
        viewOptionsObtainFrameOptions.width = i4;
        viewOptionsObtainFrameOptions.height = i5;
        viewOptionsObtainFrameOptions.commitUpdate2JSONObject();
        AdaFrameView adaFrameView = (AdaFrameView) adaFrameItem;
        adaFrameView.isChildOfFrameView = true;
        View viewObtainMainView = adaFrameItem.obtainMainView();
        if (adaFrameView.obtainWebView().isUniWebView()) {
            viewObtainMainView.layout(0, 0, i4, i5);
        } else {
            viewObtainMainView.setTop(0);
            viewObtainMainView.setLeft(0);
        }
        ViewHelper.setX(viewObtainMainView, 0.0f);
        ViewHelper.setY(viewObtainMainView, 0.0f);
        iContainerView.addFrameItem(adaFrameItem, AdaFrameItem.LayoutParamsUtil.createLayoutParams(i2, i3, i4, i5));
        Logger.d(Logger.VIEW_VISIBLE_TAG, "appendNWindow Y=" + ViewHelper.getY(viewObtainMainView));
    }

    @Override // io.dcloud.feature.ui.b
    public void e() {
        ArrayList<c> arrayList;
        i();
        c cVar = this.S;
        if (cVar != null && (arrayList = cVar.T) != null) {
            arrayList.remove(this);
        }
        this.S = null;
        this.f6616a = null;
        ArrayList<io.dcloud.feature.ui.b> arrayList2 = this.L;
        if (arrayList2 != null) {
            Iterator<io.dcloud.feature.ui.b> it = arrayList2.iterator();
            while (it.hasNext()) {
                it.next().e();
            }
            this.L.clear();
            this.L = null;
        }
        this.N = null;
        this.M = null;
        this.O = null;
        this.P = null;
        this.V = 150;
        HashMap<String, String> map = this.b;
        if (map != null) {
            map.clear();
        }
    }

    public static synchronized void a(String str, Object obj, List<c> list, c cVar) {
        Iterator<c> it = list.iterator();
        while (it.hasNext()) {
            JSUtil.broadcastWebviewEvent(it.next().r(), cVar.f6617e, str, JSONUtil.toJSONableString(String.valueOf(obj)));
        }
        if (!list.contains(cVar)) {
            JSUtil.broadcastWebviewEvent(cVar.r(), cVar.f6617e, str, JSONUtil.toJSONableString(String.valueOf(obj)));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0737  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.dcloud.feature.ui.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(io.dcloud.common.DHInterface.IWebview r25, java.lang.String r26, org.json.JSONArray r27) {
        /*
            Method dump skipped, instruction units count: 3984
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, org.json.JSONArray):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0029, code lost:
    
        if (r0.y.obtainMainView().getVisibility() == 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.DHInterface.IWebview r9, org.json.JSONArray r10) {
        /*
            r8 = this;
            r0 = 0
            java.lang.String r0 = io.dcloud.common.util.JSONUtil.getString(r10, r0)
            r1 = 1
            java.lang.String r1 = io.dcloud.common.util.JSONUtil.getString(r10, r1)
            io.dcloud.feature.ui.a r2 = r8.c
            r3 = 0
            io.dcloud.feature.ui.c r0 = r2.a(r0, r0, r3)
            if (r0 == 0) goto L2b
            io.dcloud.common.DHInterface.IFrameView r2 = r0.y     // Catch: java.lang.Exception -> L55
            android.view.View r2 = r2.obtainMainView()     // Catch: java.lang.Exception -> L55
            android.view.ViewParent r2 = r2.getParent()     // Catch: java.lang.Exception -> L55
            if (r2 == 0) goto L2b
            io.dcloud.common.DHInterface.IFrameView r2 = r0.y     // Catch: java.lang.Exception -> L55
            android.view.View r2 = r2.obtainMainView()     // Catch: java.lang.Exception -> L55
            int r2 = r2.getVisibility()     // Catch: java.lang.Exception -> L55
            if (r2 == 0) goto L64
        L2b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L55
            r2.<init>()     // Catch: java.lang.Exception -> L55
            java.lang.String r3 = "{\"code\":-100,\"message\":\""
            r2.append(r3)     // Catch: java.lang.Exception -> L55
            android.content.Context r3 = r9.getContext()     // Catch: java.lang.Exception -> L55
            int r4 = io.dcloud.base.R.string.dcloud_ui_webview_not_finished     // Catch: java.lang.Exception -> L55
            java.lang.String r3 = r3.getString(r4)     // Catch: java.lang.Exception -> L55
            r2.append(r3)     // Catch: java.lang.Exception -> L55
            java.lang.String r3 = "\"}"
            r2.append(r3)     // Catch: java.lang.Exception -> L55
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Exception -> L55
            int r5 = io.dcloud.common.util.JSUtil.ERROR     // Catch: java.lang.Exception -> L55
            r6 = 1
            r7 = 0
            r2 = r9
            r3 = r1
            io.dcloud.common.util.JSUtil.execCallback(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Exception -> L55
            return
        L55:
            r2 = move-exception
            r2.printStackTrace()
            int r5 = io.dcloud.common.util.JSUtil.ERROR
            r6 = 1
            r7 = 0
            java.lang.String r4 = "{\"code\":-100,\"message\":\"\"+sWeb.getContext().getString(R.string.dcloud_ui_webview_not_finished)+\"\"}"
            r2 = r9
            r3 = r1
            io.dcloud.common.util.JSUtil.execCallback(r2, r3, r4, r5, r6, r7)
        L64:
            io.dcloud.common.DHInterface.IWebview r4 = r0.r()
            r0 = 2
            org.json.JSONObject r10 = io.dcloud.common.util.JSONUtil.getJSONObject(r10, r0)
            java.lang.String r0 = "auto"
            if (r10 == 0) goto L7f
            java.lang.String r2 = "type"
            boolean r3 = r10.has(r2)
            if (r3 == 0) goto L7f
            java.lang.String r10 = r10.optString(r2, r0)
            r5 = r10
            goto L80
        L7f:
            r5 = r0
        L80:
            android.view.ViewGroup r10 = r4.obtainWindowView()
            if (r10 == 0) goto L92
            io.dcloud.feature.ui.c$e r0 = new io.dcloud.feature.ui.c$e
            r2 = r0
            r3 = r8
            r6 = r9
            r7 = r1
            r2.<init>(r4, r5, r6, r7)
            r10.post(r0)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.common.DHInterface.IWebview, org.json.JSONArray):void");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void a(io.dcloud.feature.ui.b bVar, c cVar, String str) {
        c cVar2 = (c) bVar;
        if (cVar2.F) {
            cVar.u = cVar2.u - 1;
            cVar.F = true;
            cVar.H = true;
            cVar.G = false;
            this.c.a(str, cVar, this.c.c(this));
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 45, new Object[]{cVar.y, cVar2.y});
        }
    }

    private void a(c cVar, String str) {
        IApp iAppObtainApp;
        if (cVar == null || PdrUtil.isEmpty(str) || (iAppObtainApp = cVar.r().obtainApp()) == null) {
            return;
        }
        c cVar2 = cVar.S;
        if (cVar2 != null) {
            str = cVar2.r().obtainUrl();
        }
        String strObtainUrl = cVar.r().obtainUrl();
        if (!BaseInfo.isBase(cVar.a()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(strObtainUrl) || str.startsWith(DeviceInfo.HTTP_PROTOCOL) || strObtainUrl.startsWith(DeviceInfo.HTTP_PROTOCOL)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.OPENLOG, WebResUtil.getHBuilderPrintUrl(iAppObtainApp.convert2RelPath(WebResUtil.getOriginalUrl(str))), WebResUtil.getHBuilderPrintUrl(iAppObtainApp.convert2RelPath(WebResUtil.getOriginalUrl(strObtainUrl)))));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void a(IWebview iWebview, JSONArray jSONArray, c cVar, String str) {
        String str2;
        if (this.c.a(cVar)) {
            Logger.d(Logger.StreamApp_TAG, "showWebview url=" + cVar.z);
            cVar.A = 1;
            this.c.f(cVar);
            cVar.B = new Object[]{iWebview, jSONArray, cVar, str};
            return;
        }
        cVar.u = System.currentTimeMillis();
        cVar.F = true;
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        String string3 = JSONUtil.getString(jSONArray, 3);
        this.M = string3;
        if (!PdrUtil.isEmpty(string3)) {
            this.N = iWebview;
        }
        AnimOptions animOptions = ((AdaFrameItem) cVar.y).getAnimOptions();
        if (PdrUtil.isEquals("auto", string)) {
            str2 = animOptions.mAnimType;
        } else {
            str2 = PdrUtil.isEmpty(string) ? "none" : string;
        }
        animOptions.mAnimType = str2;
        boolean z = !PdrUtil.isEquals("none", str2);
        if (PdrUtil.isEmpty(string2)) {
            if (animOptions.mAnimType.equals("pop-in")) {
                animOptions.duration_show = 300;
            }
        } else {
            animOptions.duration_show = PdrUtil.parseInt(string2, animOptions.duration_show);
        }
        if (!cVar.G && cVar.H) {
            z = false;
        }
        this.c.a(str, cVar, this.c.c(this));
        a(iWebview, JSONUtil.getJSONObject(jSONArray, 4), cVar, string);
        if (cVar.G) {
            animOptions.mOption = (byte) 4;
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 24, cVar.y);
        } else {
            animOptions.mOption = (byte) 0;
            cVar.H = true;
            this.c.d.processEvent(IMgr.MgrType.WindowMgr, 1, new Object[]{cVar.y, Boolean.valueOf(z)});
        }
        cVar.G = false;
        Logger.d(Logger.VIEW_VISIBLE_TAG, "show " + cVar.y + ";webview_name=" + r().obtainFrameId());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void a(IWebview iWebview, JSONObject jSONObject, c cVar, String str) {
        Object obj;
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject != null) {
            String strOptString = jSONObject.optString(AbsoluteConst.ACCELERATION);
            String str2 = TextUtils.isEmpty(strOptString) ? "auto" : strOptString;
            String strOptString2 = jSONObject.optString("action", "none");
            cVar.y.setAccelerationType(str2);
            if (jSONObject.has(AbsoluteConst.CAPTURE)) {
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(AbsoluteConst.CAPTURE);
                if (jSONObjectOptJSONObject2 == null) {
                    return;
                }
                String strOptString3 = jSONObjectOptJSONObject2.optString("__id__");
                if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.has("type") && jSONObjectOptJSONObject2.optString("type").equals(AbsoluteConst.NATIVE_NVIEW)) {
                    obj = "getNativeView";
                    Object objProcessEvent = this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", "getNativeView", new Object[]{this.y, strOptString3}});
                    if (objProcessEvent != null && (objProcessEvent instanceof INativeView)) {
                        cVar.y.setSnapshotView((INativeView) objProcessEvent, strOptString2);
                        cVar.y.setSnapshot(null);
                    }
                } else {
                    obj = "getNativeView";
                    INativeBitmap iNativeBitmapG = g(cVar.y.obtainWebView(), strOptString3);
                    cVar.y.setSnapshot(iNativeBitmapG != null ? iNativeBitmapG.getBitmap() : null);
                    cVar.y.setSnapshotView(null, "none");
                }
            } else {
                obj = "getNativeView";
            }
            IFrameView iFrameViewFindPageB = cVar.y.findPageB();
            if (iFrameViewFindPageB != null) {
                iFrameViewFindPageB.setAccelerationType(str2);
                if (!jSONObject.has("otherCapture") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("otherCapture")) == null) {
                    return;
                }
                String strOptString4 = jSONObjectOptJSONObject.optString("__id__");
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("type") && jSONObjectOptJSONObject.optString("type").equals(AbsoluteConst.NATIVE_NVIEW)) {
                    Object objProcessEvent2 = this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iWebview, "nativeobj", obj, new Object[]{iFrameViewFindPageB, strOptString4}});
                    if (objProcessEvent2 == null || !(objProcessEvent2 instanceof INativeView)) {
                        return;
                    }
                    iFrameViewFindPageB.setSnapshotView((INativeView) objProcessEvent2, strOptString2);
                    iFrameViewFindPageB.setSnapshot(null);
                    return;
                }
                INativeBitmap iNativeBitmapG2 = g(iFrameViewFindPageB.obtainWebView(), strOptString4);
                if (iFrameViewFindPageB != null) {
                    iFrameViewFindPageB.setSnapshot(iNativeBitmapG2 != null ? iNativeBitmapG2.getBitmap() : null);
                    iFrameViewFindPageB.setSnapshotView(null, "none");
                    return;
                }
                return;
            }
            return;
        }
        cVar.y.setSnapshot(null);
        cVar.y.setAccelerationType("auto");
        IFrameView iFrameViewFindPageB2 = cVar.y.findPageB();
        if (iFrameViewFindPageB2 != null) {
            iFrameViewFindPageB2.setSnapshot(null);
            iFrameViewFindPageB2.setAccelerationType("auto");
        }
    }

    public void a(IWebview iWebview, JSONArray jSONArray, c cVar) {
        String string = JSONUtil.getString(jSONArray, 0);
        String string2 = JSONUtil.getString(jSONArray, 1);
        cVar.y.captureSnapshot(string, TextUtils.isEmpty(string2) ? null : new f(this, iWebview, string2), TextUtils.isEmpty(string2) ? null : new g(this, iWebview, string2));
    }

    public void a(c cVar, boolean z) {
        cVar.y.obtainWebView().reload(z);
    }

    public boolean a(JSONObject jSONObject, boolean z) {
        boolean z2 = false;
        if (!jSONObject.isNull(AbsoluteConst.JSON_KEY_ZINDEX)) {
            try {
                int i2 = Integer.parseInt(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_ZINDEX));
                if (i2 != this.E) {
                    z2 = true;
                    this.E = i2;
                    ((AdaFrameView) this.y).mZIndex = i2;
                    if (z) {
                        this.c.g(this);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return z2;
    }

    public static String a(ArrayList arrayList) {
        StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                io.dcloud.feature.ui.b bVar = (io.dcloud.feature.ui.b) arrayList.get(i2);
                if (bVar instanceof c) {
                    stringBuffer.append(((c) bVar).h());
                } else {
                    stringBuffer.append("'" + bVar.f6617e + "'");
                }
                if (i2 != size - 1) {
                    stringBuffer.append(",");
                }
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:157:0x0121 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x014b  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x014b -> B:43:0x014d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.DHInterface.IWebview r47, io.dcloud.feature.ui.c r48, org.json.JSONObject r49, org.json.JSONObject r50) {
        /*
            Method dump skipped, instruction units count: 1110
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.c.a(io.dcloud.common.DHInterface.IWebview, io.dcloud.feature.ui.c, org.json.JSONObject, org.json.JSONObject):void");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void a(c cVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.c.d.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{cVar.r().obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "weexViewUpdate", new Object[]{cVar.r(), cVar.y.obtainMainView(), jSONObject, BaseInfo.getUniNViewId(cVar.y)}});
    }
}
