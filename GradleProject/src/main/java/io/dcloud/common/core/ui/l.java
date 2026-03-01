package io.dcloud.common.core.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.internal.bind.TypeAdapters;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.AdaWebViewParent;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.webview.WebLoadEvent;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.dcloud.common.util.TitleNViewUtil;
import io.dcloud.nineoldandroids.view.ViewHelper;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.dv;

/* JADX INFO: loaded from: classes2.dex */
public class l extends AbsMgr implements IMgr.WindowEvent {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, io.dcloud.common.core.ui.a> f6418a;
    public List<m> b;
    public String c;
    public Runnable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f6419e;
    public boolean f;

    public class a implements IEventCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6420a;
        public final /* synthetic */ IApp b;
        public final /* synthetic */ io.dcloud.common.core.ui.a c;

        public a(io.dcloud.common.core.ui.b bVar, IApp iApp, io.dcloud.common.core.ui.a aVar) {
            this.f6420a = bVar;
            this.b = iApp;
            this.c = aVar;
        }

        @Override // io.dcloud.common.DHInterface.IEventCallback
        public Object onCallBack(String str, Object obj) {
            if (!PdrUtil.isEquals(str, "close")) {
                return null;
            }
            this.f6420a.removeFrameViewListener(this);
            l.this.a(this.b, this.c);
            return null;
        }
    }

    public class b implements IWebviewStateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6421a = false;
        public final /* synthetic */ IApp b;
        public final /* synthetic */ io.dcloud.common.core.ui.b c;
        public final /* synthetic */ boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f6422e;
        public final /* synthetic */ String f;
        public final /* synthetic */ AdaWebview g;
        public final /* synthetic */ io.dcloud.common.core.ui.a h;
        public final /* synthetic */ int i;

        public class a implements MessageHandler.IMessages {
            public a() {
            }

            @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
            public void execute(Object obj) {
                if (((io.dcloud.common.core.ui.a) b.this.b.obtainWebAppRootView()).a(5) == null) {
                    b.this.b.checkOrLoadlaunchWebview();
                }
            }
        }

        public b(IApp iApp, io.dcloud.common.core.ui.b bVar, boolean z, boolean z2, String str, AdaWebview adaWebview, io.dcloud.common.core.ui.a aVar, int i) {
            this.b = iApp;
            this.c = bVar;
            this.d = z;
            this.f6422e = z2;
            this.f = str;
            this.g = adaWebview;
            this.h = aVar;
            this.i = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x00ed  */
        @Override // io.dcloud.common.DHInterface.ICallBack
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onCallBack(int r23, java.lang.Object r24) {
            /*
                Method dump skipped, instruction units count: 268
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.b.onCallBack(int, java.lang.Object):java.lang.Object");
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AdaFrameItem f6424a;

        public c(l lVar, AdaFrameItem adaFrameItem) {
            this.f6424a = adaFrameItem;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((AdaFrameView) this.f6424a).changeWebParentViewRect();
        }
    }

    public class d implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6425a;
        public final /* synthetic */ Object[] b;

        public d(l lVar, io.dcloud.common.core.ui.b bVar, Object[] objArr) {
            this.f6425a = bVar;
            this.b = objArr;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (this.f6425a.q) {
                return null;
            }
            this.f6425a.c(((Boolean) this.b[1]).booleanValue());
            return null;
        }
    }

    public class e implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6426a;

        public e(l lVar, io.dcloud.common.core.ui.b bVar) {
            this.f6426a = bVar;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            boolean z = true;
            this.f6426a.setVisible(true, false);
            this.f6426a.p();
            this.f6426a.lastShowTime = System.currentTimeMillis();
            this.f6426a.k.l();
            io.dcloud.common.core.ui.b bVar = this.f6426a;
            if (!bVar.isChildOfFrameView) {
                TestUtil.record("computeStackArray");
                io.dcloud.common.core.ui.b bVar2 = this.f6426a;
                bVar2.k.b(bVar2);
                io.dcloud.common.core.ui.b bVar3 = this.f6426a;
                bVar3.onPushToStack(bVar3.isAutoPop());
                TestUtil.print("computeStackArray", "计算满屏幕时间");
                if (this.f6426a.k.e().contains(this.f6426a)) {
                    this.f6426a.k.m();
                } else {
                    io.dcloud.common.core.ui.b bVar4 = this.f6426a;
                    bVar4.k.e(bVar4);
                }
            } else if (bVar.getParentFrameItem() != null) {
                io.dcloud.common.core.ui.b bVar5 = this.f6426a;
                bVar5.k.h(bVar5);
            }
            io.dcloud.common.core.ui.b bVar6 = this.f6426a;
            if (!bVar6.isChildOfFrameView) {
                int i2 = bVar6.obtainApp().getInt(0);
                int i3 = this.f6426a.obtainApp().getInt(1);
                if ((i2 != this.f6426a.obtainFrameOptions().width || this.f6426a.obtainFrameOptions().height + 1 < i3) && (this.f6426a.obtainFrameOptions().width != -1 || this.f6426a.obtainFrameOptions().height != -1)) {
                    z = false;
                }
                if (z) {
                    io.dcloud.common.core.ui.i.a(this.f6426a, 0);
                }
                if (PdrUtil.isEquals(this.f6426a.getAnimOptions().mAnimType, "none")) {
                    this.f6426a.makeViewOptions_animate();
                    this.f6426a.m();
                } else {
                    this.f6426a.s();
                    this.f6426a.startAnimator(0);
                }
            } else if (PdrUtil.isEquals(bVar6.getAnimOptions().mAnimType, AnimOptions.ANIM_FADE_IN)) {
                this.f6426a.s();
                this.f6426a.startAnimator(0);
            } else {
                this.f6426a.makeViewOptions_animate();
                this.f6426a.m();
            }
            io.dcloud.common.core.ui.b bVar7 = this.f6426a;
            bVar7.k.i(bVar7);
            return null;
        }
    }

    public class f implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6427a;

        public f(io.dcloud.common.core.ui.b bVar) {
            this.f6427a = bVar;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            io.dcloud.common.core.ui.b bVar = this.f6427a;
            int iC = bVar.k.c(bVar);
            this.f6427a.p();
            boolean z = false;
            boolean z2 = this.f6427a.obtainMainView().getVisibility() == AdaFrameItem.VISIBLE;
            io.dcloud.common.core.ui.b bVar2 = this.f6427a;
            if (bVar2.inStack && z2 && !bVar2.isChildOfFrameView) {
                bVar2.k.b(bVar2);
                if (this.f6427a.e()) {
                    l.this.processEvent(IMgr.MgrType.WindowMgr, 28, this.f6427a.b);
                    this.f6427a.b = null;
                }
                int i2 = this.f6427a.obtainApp().getInt(0);
                int i3 = this.f6427a.obtainApp().getInt(1);
                if ((i2 == this.f6427a.obtainFrameOptions().width && this.f6427a.obtainFrameOptions().height + 1 >= i3) || (this.f6427a.obtainFrameOptions().width == -1 && this.f6427a.obtainFrameOptions().height == -1)) {
                    z = true;
                }
                if ((!PdrUtil.isEquals(this.f6427a.getAnimOptions().mAnimType_close, "none") || (BaseInfo.isDefaultAim && z)) && iC >= 0) {
                    this.f6427a.s();
                    if (z && !PdrUtil.isEquals(this.f6427a.getAnimOptions().mAnimType_close, "none")) {
                        io.dcloud.common.core.ui.i.a(this.f6427a, 1);
                    }
                    this.f6427a.startAnimator(1);
                } else {
                    this.f6427a.makeViewOptions_animate();
                    this.f6427a.l();
                    this.f6427a.k();
                }
            } else {
                bVar2.makeViewOptions_animate();
                this.f6427a.l();
                this.f6427a.k();
            }
            return null;
        }
    }

    public class g implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6428a;
        public final /* synthetic */ int b;

        public g(io.dcloud.common.core.ui.b bVar, int i) {
            this.f6428a = bVar;
            this.b = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
        @Override // io.dcloud.common.DHInterface.ICallBack
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object onCallBack(int r9, java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 324
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.g.onCallBack(int, java.lang.Object):java.lang.Object");
        }
    }

    public class h implements ICallBack {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6429a;

        public h(l lVar, io.dcloud.common.core.ui.b bVar) {
            this.f6429a = bVar;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            io.dcloud.common.core.ui.b bVar = this.f6429a;
            bVar.k.e(bVar);
            this.f6429a.setVisible(true, false);
            this.f6429a.k.j();
            return Boolean.FALSE;
        }
    }

    public class i implements IWebviewStateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6430a = false;
        public final /* synthetic */ String b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ IApp d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.a f6431e;
        public final /* synthetic */ String f;
        public final /* synthetic */ IWebview g;
        public final /* synthetic */ int h;
        public final /* synthetic */ io.dcloud.common.core.ui.b i;
        public final /* synthetic */ int j;
        public final /* synthetic */ long k;

        public i(String str, boolean z, IApp iApp, io.dcloud.common.core.ui.a aVar, String str2, IWebview iWebview, int i, io.dcloud.common.core.ui.b bVar, int i2, long j) {
            this.b = str;
            this.c = z;
            this.d = iApp;
            this.f6431e = aVar;
            this.f = str2;
            this.g = iWebview;
            this.h = i;
            this.i = bVar;
            this.j = i2;
            this.k = j;
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            int i2 = AbsoluteConst.EVENTS_TITLE_UPDATE.equals(l.this.c) ? 4 : AbsoluteConst.EVENTS_RENDERING.equals(l.this.c) ? 6 : 1;
            Logger.d(Logger.MAIN_TAG, "autoCloseSplash4LaunchWebview  IWebviewStateListener pType= " + i + ";pArgs=" + obj);
            if (i != i2) {
                if (i != 3) {
                    return null;
                }
                IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(this.d.getActivity());
                if (iActivityHandler != null) {
                    iActivityHandler.updateParam(AbsoluteConst.JSON_KEY_PROGRESS, obj);
                }
                if (!l.this.a(this.d) || this.f6430a) {
                    return null;
                }
                Integer num = (Integer) obj;
                if (num.intValue() < 50) {
                    return null;
                }
                this.f6430a = true;
                Intent intent = new Intent();
                intent.setAction(this.d.getActivity().getPackageName() + ".streamdownload.downloadfinish." + this.d.obtainAppId());
                intent.putExtra("appid", this.d.obtainAppId());
                intent.putExtra(AbsoluteConst.JSON_KEY_PROGRESS, num.intValue());
                intent.putExtra("flag", AbsoluteConst.STREAMAPP_KEY_DIRECT_PAGE_PROGRESSED);
                this.d.getActivity().sendBroadcast(intent);
                return null;
            }
            if (this.b.equals("id:*") && this.c) {
                l.this.a(this.d, this.f6431e);
            } else if (this.b.equals("default") && this.c) {
                if (PdrUtil.isNetPath(this.f) && (i == 4 || i == 6)) {
                    int i3 = i == 4 ? 1200 : i == 6 ? TestUtil.PointTime.AC_TYPE_1_3 : TestUtil.PointTime.AC_TYPE_1_1;
                    l lVar = l.this;
                    lVar.f = false;
                    lVar.a(this.g, this.d, false, this.f6431e, this.h, this.i, this.j, i3);
                } else {
                    this.d.setConfigProperty("timeout", "-1");
                    io.dcloud.common.core.ui.a aVar = this.f6431e;
                    aVar.a(aVar, this.i, this.j, true, TestUtil.PointTime.AC_TYPE_1_1);
                }
            }
            BaseInfo.setLoadingLaunchePage(false, "f_need_auto_close_splash");
            long jCurrentTimeMillis = System.currentTimeMillis() - this.k;
            this.d.setConfigProperty(IApp.ConfigProperty.CONFIG_LOADED_TIME, String.valueOf(jCurrentTimeMillis));
            this.g.evalJS(AbsoluteConst.PROTOCOL_JAVASCRIPT + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, StringUtil.format(AbsoluteConst.JS_RUNTIME_LOADEDTIME, String.valueOf(jCurrentTimeMillis))));
            Logger.d("shutao", "首页面loadtime=" + jCurrentTimeMillis + "type=" + i);
            return null;
        }
    }

    public class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.a f6432a;
        public final /* synthetic */ io.dcloud.common.core.ui.b b;
        public final /* synthetic */ int c;

        public j(io.dcloud.common.core.ui.a aVar, io.dcloud.common.core.ui.b bVar, int i) {
            this.f6432a = aVar;
            this.b = bVar;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            io.dcloud.common.core.ui.a aVar = this.f6432a;
            if (aVar != null) {
                aVar.a(aVar, this.b, this.c, true, 1000);
            }
            l.this.d = null;
        }
    }

    public class k implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6433a;
        public final /* synthetic */ io.dcloud.common.core.ui.a b;
        public final /* synthetic */ IApp c;

        public k(io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.a aVar, IApp iApp) {
            this.f6433a = bVar;
            this.b = aVar;
            this.c = iApp;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!this.f6433a.obtainWebView().isLoaded() || this.f6433a.obtainWebView().obtainUrl().endsWith("__uniappservice.html") || this.f6433a.obtainWebView().checkWhite("auto")) {
                    l.this.a(this.c, this.b);
                } else {
                    io.dcloud.common.core.ui.a aVar = this.b;
                    aVar.a(aVar, this.f6433a, 0, true, 1);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: renamed from: io.dcloud.common.core.ui.l$l, reason: collision with other inner class name */
    public class RunnableC0153l implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.a f6434a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ io.dcloud.common.core.ui.b c;
        public final /* synthetic */ IWebview d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ IApp f6435e;
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;
        public final /* synthetic */ int h;

        public RunnableC0153l(io.dcloud.common.core.ui.a aVar, boolean z, io.dcloud.common.core.ui.b bVar, IWebview iWebview, IApp iApp, int i, int i2, int i3) {
            this.f6434a = aVar;
            this.b = z;
            this.c = bVar;
            this.d = iWebview;
            this.f6435e = iApp;
            this.f = i;
            this.g = i2;
            this.h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                io.dcloud.common.core.ui.a aVar = this.f6434a;
                if (aVar == null || aVar.o || l.this.f) {
                    return;
                }
                if ((this.b || this.c.obtainFrameOptions().titleNView == null) && this.d.checkWhite("auto")) {
                    l.this.a(this.d, this.f6435e, this.b, this.f6434a, this.h, this.c, this.f, this.g);
                    return;
                }
                System.currentTimeMillis();
                long j = BaseInfo.startTime;
                this.f6435e.setConfigProperty("timeout", "-1");
                io.dcloud.common.core.ui.a aVar2 = this.f6434a;
                aVar2.a(aVar2, this.c, this.f, true, this.g);
            } catch (Exception unused) {
            }
        }
    }

    public interface m {
        void onAnimationEnd();
    }

    public l(ICore iCore) {
        super(iCore, "windowmgr", IMgr.MgrType.WindowMgr);
        this.f6418a = new HashMap<>(0);
        this.b = Collections.synchronizedList(new ArrayList());
        this.c = null;
        this.d = null;
        this.f = false;
    }

    private boolean a(int i2, int i3, int i4, int i5, int i6, int i7) {
        return i2 == 0 && i3 == 0 && i4 == i6 && i5 == i7;
    }

    private void b(int i2, Object obj) {
        JSONObject jSONObjectCreateJSONObject;
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            IApp iApp = (IApp) objArr[0];
            boolean zBooleanValue = objArr.length >= 3 ? ((Boolean) objArr[2]).booleanValue() : false;
            String strObtainAppId = iApp.obtainAppId();
            io.dcloud.common.core.ui.a aVar = this.f6418a.get(strObtainAppId);
            io.dcloud.common.core.ui.b bVar = aVar.d;
            boolean z = bVar == null;
            if (bVar == null) {
                String stringExtra = iApp.obtainWebAppIntent().getStringExtra(IntentConst.FROM_STREAM_OPEN_STYLE);
                try {
                    if (TextUtils.isEmpty(stringExtra)) {
                        jSONObjectCreateJSONObject = JSONUtil.createJSONObject("{}");
                    } else {
                        jSONObjectCreateJSONObject = new JSONObject(stringExtra);
                        try {
                            iApp.obtainWebAppIntent().removeExtra(IntentConst.FROM_STREAM_OPEN_STYLE);
                        } catch (JSONException e2) {
                            e = e2;
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e3) {
                    e = e3;
                    jSONObjectCreateJSONObject = null;
                }
                bVar = (io.dcloud.common.core.ui.b) processEvent(IMgr.MgrType.WindowMgr, 3, new Object[]{3, iApp, new Object[]{objArr[1], jSONObjectCreateJSONObject}, aVar});
                aVar.d = bVar;
            }
            IWebview iWebviewObtainWebView = bVar.obtainWebView();
            if (Build.VERSION.SDK_INT > 10) {
                if (zBooleanValue) {
                    iWebviewObtainWebView.obtainWindowView().setLayerType(0, null);
                } else {
                    iWebviewObtainWebView.obtainWindowView().setLayerType(1, null);
                }
            }
            Logger.d(Logger.MAIN_TAG, "load " + strObtainAppId + " launchPage =" + objArr[1]);
            iWebviewObtainWebView.loadUrl(String.valueOf(objArr[1]));
            if (z) {
                aVar.e(bVar);
            }
        }
    }

    public synchronized void c() {
        if (this.b == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (m mVar : this.b) {
                mVar.onAnimationEnd();
                arrayList.add(mVar);
            }
            if (arrayList.size() > 0) {
                this.b.removeAll(arrayList);
            }
            arrayList.clear();
        } catch (Exception unused) {
        }
    }

    public void d(io.dcloud.common.core.ui.b bVar) {
        IApp iAppObtainApp = bVar.obtainApp();
        iAppObtainApp.setMaskLayer(true);
        iAppObtainApp.obtainWebAppRootView().obtainMainView().invalidate();
    }

    @Override // io.dcloud.common.DHInterface.AbsMgr
    public void dispose() {
        try {
            List<m> list = this.b;
            if (list != null) {
                list.clear();
            }
            Iterator<String> it = this.f6418a.keySet().iterator();
            while (it.hasNext()) {
                this.f6418a.get(it.next()).dispose();
            }
            this.f6418a.clear();
            if (BaseInfo.ISDEBUG) {
                io.dcloud.common.core.ui.f.b();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:102:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0252 A[Catch: all -> 0x0a22, TryCatch #0 {all -> 0x0a22, blocks: (B:3:0x000f, B:5:0x0015, B:308:0x081a, B:310:0x082d, B:312:0x083c, B:314:0x084d, B:316:0x0853, B:318:0x087a, B:320:0x087e, B:323:0x0893, B:326:0x08a8, B:324:0x089f, B:328:0x08c1, B:330:0x08d2, B:332:0x08d6, B:334:0x08de, B:336:0x08e6, B:338:0x08ee, B:340:0x08fa, B:342:0x0904, B:344:0x091c, B:346:0x092f, B:347:0x093c, B:349:0x0951, B:351:0x0955, B:354:0x095f, B:357:0x0969, B:358:0x097f, B:359:0x0988, B:361:0x099d, B:364:0x09ab, B:363:0x09a7, B:365:0x09ba, B:366:0x09c5, B:367:0x09d4, B:368:0x09f2, B:369:0x09f9, B:257:0x067d, B:259:0x0681, B:261:0x068c, B:263:0x0692, B:265:0x069a, B:267:0x06a0, B:268:0x06a9, B:270:0x06b0, B:272:0x06b6, B:273:0x06c1, B:275:0x06c5, B:277:0x06d4, B:279:0x06da, B:280:0x06e3, B:282:0x06e9, B:284:0x06ef, B:285:0x06fa, B:287:0x06fe, B:289:0x0710, B:291:0x071a, B:290:0x0715, B:292:0x0767, B:294:0x0775, B:295:0x077b, B:297:0x0789, B:298:0x078f, B:300:0x07a4, B:301:0x07b2, B:302:0x07e6, B:303:0x07fb, B:304:0x0808, B:306:0x0811, B:307:0x0815, B:143:0x035c, B:145:0x0360, B:150:0x037e, B:151:0x038a, B:153:0x0390, B:155:0x039c, B:157:0x03aa, B:146:0x036c, B:148:0x0370, B:158:0x03ae, B:160:0x03c3, B:161:0x03c8, B:162:0x03d0, B:164:0x03da, B:166:0x03df, B:168:0x03e5, B:170:0x03ed, B:172:0x03f5, B:174:0x0401, B:175:0x0432, B:176:0x044c, B:178:0x0451, B:179:0x045e, B:181:0x0463, B:183:0x046a, B:185:0x0472, B:187:0x047e, B:189:0x0484, B:191:0x0488, B:194:0x0493, B:195:0x04e6, B:196:0x04ea, B:198:0x04ef, B:199:0x04f7, B:200:0x050c, B:202:0x0510, B:206:0x0518, B:207:0x0540, B:209:0x0544, B:213:0x054c, B:215:0x0576, B:217:0x057b, B:219:0x0587, B:221:0x0591, B:222:0x05ba, B:230:0x05f4, B:232:0x05fb, B:248:0x0658, B:252:0x0662, B:255:0x066c, B:256:0x0674, B:233:0x0605, B:235:0x0609, B:236:0x0615, B:238:0x0619, B:241:0x0629, B:244:0x0645, B:131:0x02dc, B:132:0x02e2, B:134:0x02f4, B:136:0x02fc, B:138:0x0302, B:140:0x0338, B:141:0x033f, B:142:0x0344, B:19:0x0046, B:20:0x0053, B:21:0x005c, B:22:0x0065, B:23:0x006f, B:25:0x007d, B:27:0x0087, B:28:0x0091, B:30:0x0096, B:32:0x00a2, B:34:0x00c2, B:35:0x00c7, B:36:0x00cd, B:44:0x010d, B:47:0x0133, B:49:0x0137, B:51:0x0142, B:52:0x014b, B:53:0x0153, B:55:0x015b, B:57:0x016c, B:58:0x0173, B:64:0x0182, B:67:0x018a, B:68:0x0198, B:70:0x01a0, B:72:0x01b1, B:73:0x01ba, B:75:0x01be, B:76:0x01ce, B:78:0x01d9, B:79:0x01de, B:80:0x01f4, B:82:0x020d, B:84:0x0215, B:86:0x021b, B:88:0x0222, B:90:0x0225, B:92:0x022b, B:97:0x0240, B:99:0x0244, B:101:0x024a, B:104:0x0252, B:108:0x025e, B:109:0x0262, B:111:0x0266, B:115:0x026f, B:119:0x0277, B:121:0x027d, B:93:0x0231, B:95:0x0237, B:122:0x0293, B:124:0x029c, B:129:0x02ae, B:130:0x02b4, B:127:0x02a4, B:223:0x05c0, B:225:0x05c4, B:229:0x05cb, B:370:0x09fd, B:371:0x0a16), top: B:376:0x000f, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0266 A[Catch: all -> 0x0a22, TRY_LEAVE, TryCatch #0 {all -> 0x0a22, blocks: (B:3:0x000f, B:5:0x0015, B:308:0x081a, B:310:0x082d, B:312:0x083c, B:314:0x084d, B:316:0x0853, B:318:0x087a, B:320:0x087e, B:323:0x0893, B:326:0x08a8, B:324:0x089f, B:328:0x08c1, B:330:0x08d2, B:332:0x08d6, B:334:0x08de, B:336:0x08e6, B:338:0x08ee, B:340:0x08fa, B:342:0x0904, B:344:0x091c, B:346:0x092f, B:347:0x093c, B:349:0x0951, B:351:0x0955, B:354:0x095f, B:357:0x0969, B:358:0x097f, B:359:0x0988, B:361:0x099d, B:364:0x09ab, B:363:0x09a7, B:365:0x09ba, B:366:0x09c5, B:367:0x09d4, B:368:0x09f2, B:369:0x09f9, B:257:0x067d, B:259:0x0681, B:261:0x068c, B:263:0x0692, B:265:0x069a, B:267:0x06a0, B:268:0x06a9, B:270:0x06b0, B:272:0x06b6, B:273:0x06c1, B:275:0x06c5, B:277:0x06d4, B:279:0x06da, B:280:0x06e3, B:282:0x06e9, B:284:0x06ef, B:285:0x06fa, B:287:0x06fe, B:289:0x0710, B:291:0x071a, B:290:0x0715, B:292:0x0767, B:294:0x0775, B:295:0x077b, B:297:0x0789, B:298:0x078f, B:300:0x07a4, B:301:0x07b2, B:302:0x07e6, B:303:0x07fb, B:304:0x0808, B:306:0x0811, B:307:0x0815, B:143:0x035c, B:145:0x0360, B:150:0x037e, B:151:0x038a, B:153:0x0390, B:155:0x039c, B:157:0x03aa, B:146:0x036c, B:148:0x0370, B:158:0x03ae, B:160:0x03c3, B:161:0x03c8, B:162:0x03d0, B:164:0x03da, B:166:0x03df, B:168:0x03e5, B:170:0x03ed, B:172:0x03f5, B:174:0x0401, B:175:0x0432, B:176:0x044c, B:178:0x0451, B:179:0x045e, B:181:0x0463, B:183:0x046a, B:185:0x0472, B:187:0x047e, B:189:0x0484, B:191:0x0488, B:194:0x0493, B:195:0x04e6, B:196:0x04ea, B:198:0x04ef, B:199:0x04f7, B:200:0x050c, B:202:0x0510, B:206:0x0518, B:207:0x0540, B:209:0x0544, B:213:0x054c, B:215:0x0576, B:217:0x057b, B:219:0x0587, B:221:0x0591, B:222:0x05ba, B:230:0x05f4, B:232:0x05fb, B:248:0x0658, B:252:0x0662, B:255:0x066c, B:256:0x0674, B:233:0x0605, B:235:0x0609, B:236:0x0615, B:238:0x0619, B:241:0x0629, B:244:0x0645, B:131:0x02dc, B:132:0x02e2, B:134:0x02f4, B:136:0x02fc, B:138:0x0302, B:140:0x0338, B:141:0x033f, B:142:0x0344, B:19:0x0046, B:20:0x0053, B:21:0x005c, B:22:0x0065, B:23:0x006f, B:25:0x007d, B:27:0x0087, B:28:0x0091, B:30:0x0096, B:32:0x00a2, B:34:0x00c2, B:35:0x00c7, B:36:0x00cd, B:44:0x010d, B:47:0x0133, B:49:0x0137, B:51:0x0142, B:52:0x014b, B:53:0x0153, B:55:0x015b, B:57:0x016c, B:58:0x0173, B:64:0x0182, B:67:0x018a, B:68:0x0198, B:70:0x01a0, B:72:0x01b1, B:73:0x01ba, B:75:0x01be, B:76:0x01ce, B:78:0x01d9, B:79:0x01de, B:80:0x01f4, B:82:0x020d, B:84:0x0215, B:86:0x021b, B:88:0x0222, B:90:0x0225, B:92:0x022b, B:97:0x0240, B:99:0x0244, B:101:0x024a, B:104:0x0252, B:108:0x025e, B:109:0x0262, B:111:0x0266, B:115:0x026f, B:119:0x0277, B:121:0x027d, B:93:0x0231, B:95:0x0237, B:122:0x0293, B:124:0x029c, B:129:0x02ae, B:130:0x02b4, B:127:0x02a4, B:223:0x05c0, B:225:0x05c4, B:229:0x05cb, B:370:0x09fd, B:371:0x0a16), top: B:376:0x000f, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x05c0 A[Catch: all -> 0x0a22, TryCatch #0 {all -> 0x0a22, blocks: (B:3:0x000f, B:5:0x0015, B:308:0x081a, B:310:0x082d, B:312:0x083c, B:314:0x084d, B:316:0x0853, B:318:0x087a, B:320:0x087e, B:323:0x0893, B:326:0x08a8, B:324:0x089f, B:328:0x08c1, B:330:0x08d2, B:332:0x08d6, B:334:0x08de, B:336:0x08e6, B:338:0x08ee, B:340:0x08fa, B:342:0x0904, B:344:0x091c, B:346:0x092f, B:347:0x093c, B:349:0x0951, B:351:0x0955, B:354:0x095f, B:357:0x0969, B:358:0x097f, B:359:0x0988, B:361:0x099d, B:364:0x09ab, B:363:0x09a7, B:365:0x09ba, B:366:0x09c5, B:367:0x09d4, B:368:0x09f2, B:369:0x09f9, B:257:0x067d, B:259:0x0681, B:261:0x068c, B:263:0x0692, B:265:0x069a, B:267:0x06a0, B:268:0x06a9, B:270:0x06b0, B:272:0x06b6, B:273:0x06c1, B:275:0x06c5, B:277:0x06d4, B:279:0x06da, B:280:0x06e3, B:282:0x06e9, B:284:0x06ef, B:285:0x06fa, B:287:0x06fe, B:289:0x0710, B:291:0x071a, B:290:0x0715, B:292:0x0767, B:294:0x0775, B:295:0x077b, B:297:0x0789, B:298:0x078f, B:300:0x07a4, B:301:0x07b2, B:302:0x07e6, B:303:0x07fb, B:304:0x0808, B:306:0x0811, B:307:0x0815, B:143:0x035c, B:145:0x0360, B:150:0x037e, B:151:0x038a, B:153:0x0390, B:155:0x039c, B:157:0x03aa, B:146:0x036c, B:148:0x0370, B:158:0x03ae, B:160:0x03c3, B:161:0x03c8, B:162:0x03d0, B:164:0x03da, B:166:0x03df, B:168:0x03e5, B:170:0x03ed, B:172:0x03f5, B:174:0x0401, B:175:0x0432, B:176:0x044c, B:178:0x0451, B:179:0x045e, B:181:0x0463, B:183:0x046a, B:185:0x0472, B:187:0x047e, B:189:0x0484, B:191:0x0488, B:194:0x0493, B:195:0x04e6, B:196:0x04ea, B:198:0x04ef, B:199:0x04f7, B:200:0x050c, B:202:0x0510, B:206:0x0518, B:207:0x0540, B:209:0x0544, B:213:0x054c, B:215:0x0576, B:217:0x057b, B:219:0x0587, B:221:0x0591, B:222:0x05ba, B:230:0x05f4, B:232:0x05fb, B:248:0x0658, B:252:0x0662, B:255:0x066c, B:256:0x0674, B:233:0x0605, B:235:0x0609, B:236:0x0615, B:238:0x0619, B:241:0x0629, B:244:0x0645, B:131:0x02dc, B:132:0x02e2, B:134:0x02f4, B:136:0x02fc, B:138:0x0302, B:140:0x0338, B:141:0x033f, B:142:0x0344, B:19:0x0046, B:20:0x0053, B:21:0x005c, B:22:0x0065, B:23:0x006f, B:25:0x007d, B:27:0x0087, B:28:0x0091, B:30:0x0096, B:32:0x00a2, B:34:0x00c2, B:35:0x00c7, B:36:0x00cd, B:44:0x010d, B:47:0x0133, B:49:0x0137, B:51:0x0142, B:52:0x014b, B:53:0x0153, B:55:0x015b, B:57:0x016c, B:58:0x0173, B:64:0x0182, B:67:0x018a, B:68:0x0198, B:70:0x01a0, B:72:0x01b1, B:73:0x01ba, B:75:0x01be, B:76:0x01ce, B:78:0x01d9, B:79:0x01de, B:80:0x01f4, B:82:0x020d, B:84:0x0215, B:86:0x021b, B:88:0x0222, B:90:0x0225, B:92:0x022b, B:97:0x0240, B:99:0x0244, B:101:0x024a, B:104:0x0252, B:108:0x025e, B:109:0x0262, B:111:0x0266, B:115:0x026f, B:119:0x0277, B:121:0x027d, B:93:0x0231, B:95:0x0237, B:122:0x0293, B:124:0x029c, B:129:0x02ae, B:130:0x02b4, B:127:0x02a4, B:223:0x05c0, B:225:0x05c4, B:229:0x05cb, B:370:0x09fd, B:371:0x0a16), top: B:376:0x000f, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x066c A[Catch: all -> 0x0a22, TryCatch #0 {all -> 0x0a22, blocks: (B:3:0x000f, B:5:0x0015, B:308:0x081a, B:310:0x082d, B:312:0x083c, B:314:0x084d, B:316:0x0853, B:318:0x087a, B:320:0x087e, B:323:0x0893, B:326:0x08a8, B:324:0x089f, B:328:0x08c1, B:330:0x08d2, B:332:0x08d6, B:334:0x08de, B:336:0x08e6, B:338:0x08ee, B:340:0x08fa, B:342:0x0904, B:344:0x091c, B:346:0x092f, B:347:0x093c, B:349:0x0951, B:351:0x0955, B:354:0x095f, B:357:0x0969, B:358:0x097f, B:359:0x0988, B:361:0x099d, B:364:0x09ab, B:363:0x09a7, B:365:0x09ba, B:366:0x09c5, B:367:0x09d4, B:368:0x09f2, B:369:0x09f9, B:257:0x067d, B:259:0x0681, B:261:0x068c, B:263:0x0692, B:265:0x069a, B:267:0x06a0, B:268:0x06a9, B:270:0x06b0, B:272:0x06b6, B:273:0x06c1, B:275:0x06c5, B:277:0x06d4, B:279:0x06da, B:280:0x06e3, B:282:0x06e9, B:284:0x06ef, B:285:0x06fa, B:287:0x06fe, B:289:0x0710, B:291:0x071a, B:290:0x0715, B:292:0x0767, B:294:0x0775, B:295:0x077b, B:297:0x0789, B:298:0x078f, B:300:0x07a4, B:301:0x07b2, B:302:0x07e6, B:303:0x07fb, B:304:0x0808, B:306:0x0811, B:307:0x0815, B:143:0x035c, B:145:0x0360, B:150:0x037e, B:151:0x038a, B:153:0x0390, B:155:0x039c, B:157:0x03aa, B:146:0x036c, B:148:0x0370, B:158:0x03ae, B:160:0x03c3, B:161:0x03c8, B:162:0x03d0, B:164:0x03da, B:166:0x03df, B:168:0x03e5, B:170:0x03ed, B:172:0x03f5, B:174:0x0401, B:175:0x0432, B:176:0x044c, B:178:0x0451, B:179:0x045e, B:181:0x0463, B:183:0x046a, B:185:0x0472, B:187:0x047e, B:189:0x0484, B:191:0x0488, B:194:0x0493, B:195:0x04e6, B:196:0x04ea, B:198:0x04ef, B:199:0x04f7, B:200:0x050c, B:202:0x0510, B:206:0x0518, B:207:0x0540, B:209:0x0544, B:213:0x054c, B:215:0x0576, B:217:0x057b, B:219:0x0587, B:221:0x0591, B:222:0x05ba, B:230:0x05f4, B:232:0x05fb, B:248:0x0658, B:252:0x0662, B:255:0x066c, B:256:0x0674, B:233:0x0605, B:235:0x0609, B:236:0x0615, B:238:0x0619, B:241:0x0629, B:244:0x0645, B:131:0x02dc, B:132:0x02e2, B:134:0x02f4, B:136:0x02fc, B:138:0x0302, B:140:0x0338, B:141:0x033f, B:142:0x0344, B:19:0x0046, B:20:0x0053, B:21:0x005c, B:22:0x0065, B:23:0x006f, B:25:0x007d, B:27:0x0087, B:28:0x0091, B:30:0x0096, B:32:0x00a2, B:34:0x00c2, B:35:0x00c7, B:36:0x00cd, B:44:0x010d, B:47:0x0133, B:49:0x0137, B:51:0x0142, B:52:0x014b, B:53:0x0153, B:55:0x015b, B:57:0x016c, B:58:0x0173, B:64:0x0182, B:67:0x018a, B:68:0x0198, B:70:0x01a0, B:72:0x01b1, B:73:0x01ba, B:75:0x01be, B:76:0x01ce, B:78:0x01d9, B:79:0x01de, B:80:0x01f4, B:82:0x020d, B:84:0x0215, B:86:0x021b, B:88:0x0222, B:90:0x0225, B:92:0x022b, B:97:0x0240, B:99:0x0244, B:101:0x024a, B:104:0x0252, B:108:0x025e, B:109:0x0262, B:111:0x0266, B:115:0x026f, B:119:0x0277, B:121:0x027d, B:93:0x0231, B:95:0x0237, B:122:0x0293, B:124:0x029c, B:129:0x02ae, B:130:0x02b4, B:127:0x02a4, B:223:0x05c0, B:225:0x05c4, B:229:0x05cb, B:370:0x09fd, B:371:0x0a16), top: B:376:0x000f, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x023e  */
    @Override // io.dcloud.common.DHInterface.IMgr
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object processEvent(io.dcloud.common.DHInterface.IMgr.MgrType r17, int r18, java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 2724
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.processEvent(io.dcloud.common.DHInterface.IMgr$MgrType, int, java.lang.Object):java.lang.Object");
    }

    public synchronized void a(m mVar) {
        if (!this.b.contains(mVar)) {
            this.b.add(mVar);
        }
    }

    public void a(ViewGroup viewGroup, IApp iApp, IWebview iWebview, ViewGroup.LayoutParams layoutParams) {
        a(iApp, iApp.obtainAppId());
        io.dcloud.common.core.ui.a aVar = this.f6418a.get(iApp.obtainAppId());
        io.dcloud.common.core.ui.b bVar = (io.dcloud.common.core.ui.b) iWebview.obtainFrameView();
        bVar.k = aVar;
        View viewObtainMainView = bVar.obtainMainView();
        if (viewObtainMainView.getParent() != null) {
            ((ViewGroup) viewObtainMainView.getParent()).removeView(viewObtainMainView);
        }
        viewGroup.addView(viewObtainMainView, layoutParams);
    }

    public void c(io.dcloud.common.core.ui.b bVar) {
        bVar.p();
        bVar.k.b(bVar);
        if (bVar.e()) {
            processEvent(IMgr.MgrType.WindowMgr, 28, bVar.b);
            bVar.b = null;
        }
        bVar.makeViewOptions_animate();
        bVar.l();
        bVar.k();
    }

    public synchronized boolean a(IApp iApp, String str) {
        boolean z;
        Logger.e("streamsdk", "come into createAppRootView pAppid===" + str);
        io.dcloud.common.core.ui.a aVar = this.f6418a.get(str);
        z = true;
        if (aVar == null || !aVar.h) {
            if (aVar != null && !aVar.h) {
                this.f6418a.remove(str);
            }
            Logger.e("streamsdk", "come into createAppRootView and new le rootview  pAppid===" + str);
            Logger.d(Logger.MAIN_TAG, "create " + str + " AppRootView");
            io.dcloud.common.core.ui.a aVar2 = new io.dcloud.common.core.ui.a(iApp.getActivity(), iApp, null);
            aVar2.onAppStart(iApp);
            aVar2.obtainFrameOptions().setParentViewRect(iApp.getAppViewRect());
            aVar2.obtainFrameOptions().updateViewData(JSONUtil.createJSONObject("{}"), iApp.getInt(0), iApp.getInt(1));
            this.f6418a.put(str, aVar2);
            iApp.obtainAppId();
        } else {
            z = false;
        }
        return z;
    }

    public void b(IApp iApp, IWebview iWebview) {
        if (iApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData) != null || (BaseInfo.isWap2AppAppid(iApp.obtainAppId()) && !TextUtils.isEmpty(iApp.getOriginalDirectPage()))) {
            processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{iWebview, IFeature.F_UI, "n_createSecondWebview", null});
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:199:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01fb  */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r24v0, types: [io.dcloud.common.core.ui.l] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(int r25, java.lang.Object r26) {
        /*
            Method dump skipped, instruction units count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(int, java.lang.Object):void");
    }

    private io.dcloud.common.core.ui.b b(IApp iApp) {
        io.dcloud.common.core.ui.a aVar = (io.dcloud.common.core.ui.a) iApp.obtainWebAppRootView();
        if (aVar != null) {
            return aVar.i();
        }
        return null;
    }

    private io.dcloud.common.core.ui.a b() {
        return this.f6418a.get(String.valueOf(processEvent(IMgr.MgrType.AppMgr, 11, null)));
    }

    public void b(io.dcloud.common.core.ui.b bVar) {
        IApp iAppObtainApp = bVar.obtainApp();
        iAppObtainApp.setMaskLayer(false);
        iAppObtainApp.obtainWebAppRootView().obtainMainView().invalidate();
    }

    private io.dcloud.common.core.ui.c b(IApp iApp, io.dcloud.common.core.ui.a aVar) {
        JSONObject jSONObjectObtainThridInfo = iApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.Tabbar);
        if (jSONObjectObtainThridInfo == null) {
            return null;
        }
        io.dcloud.common.core.ui.c cVar = new io.dcloud.common.core.ui.c(iApp.getActivity(), this, iApp, aVar, 8, jSONObjectObtainThridInfo);
        int i2 = iApp.getInt(0);
        int i3 = iApp.getInt(1);
        ViewOptions viewOptionsObtainFrameOptions = cVar.obtainFrameOptions();
        ViewOptions viewOptionsObtainFrameOptions2 = aVar.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions2.height > i3) {
            viewOptionsObtainFrameOptions2.updateViewData(viewOptionsObtainFrameOptions2.mJsonViewOption, i2, i3);
        }
        viewOptionsObtainFrameOptions.setParentViewRect(viewOptionsObtainFrameOptions2);
        viewOptionsObtainFrameOptions.popGesture = iApp.getPopGesture();
        View viewObtainMainView = cVar.obtainMainView();
        viewOptionsObtainFrameOptions.width = -1;
        viewOptionsObtainFrameOptions.height = -1;
        AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(viewObtainMainView, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, -1, -1);
        aVar.addFrameItem(cVar, new ViewGroup.LayoutParams(-1, -1));
        cVar.k.e(cVar);
        processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{cVar.obtainWebView(), IFeature.F_UI, "", null});
        return cVar;
    }

    public void a(IApp iApp, io.dcloud.common.core.ui.a aVar, String str, String str2, JSONObject jSONObject) {
        String strOptString = (jSONObject == null || !jSONObject.has("path")) ? null : jSONObject.optString("path");
        if (PdrUtil.isEmpty(strOptString)) {
            return;
        }
        iApp.setConfigProperty(AbsoluteConst.UNIAPP_WEEX_JS_SERVICE, String.valueOf(true));
        int i2 = iApp.getInt(0);
        int i3 = iApp.getInt(1);
        io.dcloud.common.core.ui.b bVar = new io.dcloud.common.core.ui.b(iApp.getActivity(), this, iApp, aVar, 7, null);
        io.dcloud.common.core.ui.d dVar = new io.dcloud.common.core.ui.d(iApp.getActivity(), bVar, strOptString, str, jSONObject, true);
        dVar.initWebviewUUID(str);
        ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
        ViewOptions viewOptionsObtainFrameOptions2 = aVar.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions2.height > i3) {
            viewOptionsObtainFrameOptions2.updateViewData(viewOptionsObtainFrameOptions2.mJsonViewOption, i2, i3);
        }
        viewOptionsObtainFrameOptions.setParentViewRect(viewOptionsObtainFrameOptions2);
        viewOptionsObtainFrameOptions.popGesture = iApp.getPopGesture();
        View viewObtainMainView = bVar.obtainMainView();
        int i4 = viewOptionsObtainFrameOptions.width;
        if (i4 == i2) {
            i4 = -1;
        }
        int i5 = viewOptionsObtainFrameOptions.height;
        if (i5 == i3) {
            i5 = -1;
        }
        AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(viewObtainMainView, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, i4, i5);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        bVar.addFrameItem(bVar.obtainWebviewParent(), layoutParams);
        bVar.setVisible(false, false);
        aVar.addFrameItem(bVar, layoutParams);
        dVar.setFrameId(str2);
        bVar.k.e(bVar);
        processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{dVar, IFeature.F_UI, "", null});
    }

    private void a(IApp iApp, ViewGroup viewGroup) {
        if (!BaseInfo.isUniNViewBackgroud() || BaseInfo.isWeexUniJs(iApp)) {
            return;
        }
        Object objProcessEvent = processEvent(IMgr.MgrType.AppMgr, 24, null);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("template", String.valueOf(objProcessEvent));
            jSONObject.put("path", iApp.obtainAppDataPath() + "nvue_service.js");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{iApp, "weex,io.dcloud.feature.weex.WeexFeature", "createServiceUniNView", new Object[]{iApp, jSONObject, viewGroup, "__uniapp__nvue"}});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(IApp iApp) {
        return (TextUtils.isEmpty(iApp.getOriginalDirectPage()) || iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) ? false : true;
    }

    private void a(int i2, io.dcloud.common.core.ui.a aVar, String str, io.dcloud.common.core.ui.b bVar, IApp iApp, String str2, IWebview iWebview) {
        boolean z;
        io.dcloud.common.core.ui.a aVar2;
        io.dcloud.common.core.ui.b bVar2;
        IWebviewStateListener iWebviewStateListenerObtainLaunchPageStateListener = iApp.obtainLaunchPageStateListener();
        if (iWebviewStateListenerObtainLaunchPageStateListener != null) {
            boolean z2 = PdrUtil.parseBoolean(String.valueOf(iWebviewStateListenerObtainLaunchPageStateListener.onCallBack(-1, iWebview)), true, false);
            iWebview.addStateListener(iApp.obtainLaunchPageStateListener());
            z = z2;
        } else {
            z = true;
        }
        int i3 = Integer.parseInt(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_DELAY));
        boolean z3 = Boolean.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_AUTOCLOSE));
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z4 = BaseInfo.isWap2AppAppid(str) && Boolean.parseBoolean(iApp.obtainConfigProperty("w2a_autoclose"));
        Intent intentObtainWebAppIntent = iApp.obtainWebAppIntent();
        String strObtainConfigProperty = iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_TARGET);
        if (TextUtils.isEmpty(strObtainConfigProperty)) {
            strObtainConfigProperty = "default";
        }
        boolean z5 = z3 || z4;
        int intExtra = intentObtainWebAppIntent.getIntExtra(IntentConst.FROM_STREAM_OPEN_TIMEOUT, WebLoadEvent.Timeout_Page_Finish);
        boolean booleanExtra = intentObtainWebAppIntent.getBooleanExtra(IntentConst.FROM_STREAM_OPEN_AUTOCLOSE, z5);
        int i4 = (strObtainConfigProperty.startsWith("id:") && booleanExtra) ? 10000 : intExtra;
        int i5 = z4 ? Integer.parseInt(iApp.obtainConfigProperty("w2a_delay")) : i3;
        if (BaseInfo.isWap2AppAppid(str) && PdrUtil.isNetPath(str2)) {
            this.c = AbsoluteConst.EVENTS_RENDERING;
        } else {
            this.c = AbsoluteConst.EVENTS_LOADED;
        }
        String strObtainConfigProperty2 = iApp.obtainConfigProperty("event");
        if (!TextUtils.isEmpty(strObtainConfigProperty2)) {
            this.c = strObtainConfigProperty2;
        }
        Logger.d(Logger.MAIN_TAG, "_need_auto_close_splash = " + z3 + ";_delay=" + i3 + ";appid=" + str + ";f_event=" + this.c);
        int i6 = i5;
        int i7 = i4;
        iWebview.addStateListener(new i(strObtainConfigProperty, booleanExtra, iApp, aVar, str2, iWebview, i2, bVar, i5, jCurrentTimeMillis));
        if (booleanExtra) {
            aVar2 = aVar;
            bVar2 = bVar;
            a(i7, aVar2, bVar2, i6);
        } else {
            aVar2 = aVar;
            bVar2 = bVar;
        }
        if (!z || bVar2.isChildOfFrameView) {
            return;
        }
        aVar2.e(bVar2);
    }

    private void a(int i2, io.dcloud.common.core.ui.a aVar, io.dcloud.common.core.ui.b bVar, int i3) {
        if (this.d != null) {
            aVar.obtainMainView().removeCallbacks(this.d);
        }
        this.d = new j(aVar, bVar, i3);
        aVar.obtainMainView().postDelayed(this.d, i2);
    }

    private void a(io.dcloud.common.core.ui.a aVar) {
        if (this.d == null || aVar == null) {
            return;
        }
        aVar.obtainMainView().removeCallbacks(this.d);
        this.d = null;
    }

    public void a(IApp iApp, IWebview iWebview, JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, (Object) null);
            jSONArray.put(1, (Object) null);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(0, (Object) null);
            jSONArray.put(2, jSONArray2);
            jSONArray.put(3, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{iWebview, IFeature.F_UI, "n_createHDWebview", jSONArray});
    }

    public void a(IApp iApp, IWebview iWebview) {
        if (BaseInfo.isWap2AppAppid(iApp.obtainAppId()) && iApp.obtainWebAppIntent().hasExtra(IntentConst.DIRECT_PAGE)) {
            processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{iWebview, IFeature.F_UI, "n_createDirectWebview", null});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IApp iApp, io.dcloud.common.core.ui.a aVar) {
        io.dcloud.common.core.ui.b bVarI;
        if (aVar == null || aVar.o || (bVarI = aVar.i()) == null) {
            return;
        }
        k kVar = new k(bVarI, aVar, iApp);
        Runnable runnable = this.f6419e;
        if (runnable != null) {
            this.f = true;
            MessageHandler.removeCallbacks(runnable);
        }
        MessageHandler.postDelayed(kVar, 100L);
    }

    public void a(IWebview iWebview, IApp iApp, boolean z, io.dcloud.common.core.ui.a aVar, int i2, io.dcloud.common.core.ui.b bVar, int i3, int i4) {
        RunnableC0153l runnableC0153l = new RunnableC0153l(aVar, z, bVar, iWebview, iApp, i3, i4, i2);
        this.f6419e = runnableC0153l;
        MessageHandler.postDelayed(runnableC0153l, 100L);
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0409 A[LOOP:0: B:183:0x0403->B:185:0x0409, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0220  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public io.dcloud.common.core.ui.b a(int r30, io.dcloud.common.DHInterface.IApp r31, io.dcloud.common.core.ui.a r32, io.dcloud.common.core.ui.b r33, io.dcloud.common.DHInterface.IEventCallback r34, java.lang.Object[] r35, io.dcloud.common.DHInterface.IDCloudWebviewClientListener r36) {
        /*
            Method dump skipped, instruction units count: 1311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(int, io.dcloud.common.DHInterface.IApp, io.dcloud.common.core.ui.a, io.dcloud.common.core.ui.b, io.dcloud.common.DHInterface.IEventCallback, java.lang.Object[], io.dcloud.common.DHInterface.IDCloudWebviewClientListener):io.dcloud.common.core.ui.b");
    }

    private void a(io.dcloud.common.core.ui.b bVar, boolean z) {
        int i2 = bVar.obtainApp().getInt(0);
        int i3 = bVar.obtainApp().getInt(1);
        AdaWebViewParent adaWebViewParentObtainWebviewParent = bVar.obtainWebviewParent();
        ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
        ViewOptions viewOptionsObtainFrameOptions2 = adaWebViewParentObtainWebviewParent.obtainFrameOptions();
        io.dcloud.common.core.ui.a aVar = (io.dcloud.common.core.ui.a) bVar.obtainWebAppRootView();
        ViewOptions viewOptionsObtainFrameOptions3 = aVar.obtainFrameOptions();
        viewOptionsObtainFrameOptions2.setParentViewRect(viewOptionsObtainFrameOptions3);
        viewOptionsObtainFrameOptions2.updateViewData(viewOptionsObtainFrameOptions);
        viewOptionsObtainFrameOptions.left = 0;
        viewOptionsObtainFrameOptions.top = 0;
        viewOptionsObtainFrameOptions.anim_top = 0;
        viewOptionsObtainFrameOptions.anim_left = 0;
        ViewHelper.setY(bVar.obtainMainView(), 0.0f);
        ViewHelper.setX(bVar.obtainMainView(), 0.0f);
        viewOptionsObtainFrameOptions.width = i2;
        viewOptionsObtainFrameOptions.height = i3;
        int i4 = viewOptionsObtainFrameOptions2.left;
        int i5 = viewOptionsObtainFrameOptions2.top;
        int i6 = viewOptionsObtainFrameOptions2.width;
        int i7 = viewOptionsObtainFrameOptions2.height;
        adaWebViewParentObtainWebviewParent.setFrameOptions_Birth(ViewOptions.createViewOptionsData(viewOptionsObtainFrameOptions2, viewOptionsObtainFrameOptions3, viewOptionsObtainFrameOptions2));
        viewOptionsObtainFrameOptions2.allowUpdate = false;
        viewOptionsObtainFrameOptions2.maskColor = viewOptionsObtainFrameOptions.maskColor;
        adaWebViewParentObtainWebviewParent.mNeedOrientationUpdate = true;
        viewOptionsObtainFrameOptions.checkValueIsPercentage("left", -1, -1, false, true);
        viewOptionsObtainFrameOptions.checkValueIsPercentage("top", -1, -1, false, true);
        viewOptionsObtainFrameOptions.checkValueIsPercentage("width", -1, -1, false, true);
        viewOptionsObtainFrameOptions.checkValueIsPercentage("height", -1, -1, false, true);
        if (a(i4, i5, i6, i7, aVar.obtainFrameOptions().width, aVar.obtainFrameOptions().height)) {
            Logger.d("winmgr", "createWindow use LayoutParams.MATCH_PARENT !");
            bVar.addFrameItem(bVar.obtainWebviewParent(), new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        bVar.addFrameItem(bVar.obtainWebviewParent(), AdaFrameItem.LayoutParamsUtil.createLayoutParams(i4, i5, i6, i7));
        if (z) {
            bVar.a(i2, i3);
            return;
        }
        int i8 = i4 + i6;
        if (i8 > i2 || i5 + i7 > i3) {
            StringBuilder sb = new StringBuilder();
            sb.append("updateLayoutParams allW=");
            sb.append(i8);
            sb.append(";pdrW=");
            sb.append(i2);
            sb.append(";pdrH=");
            sb.append(i3);
            sb.append(";allH=");
            int i9 = i5 + i7;
            sb.append(i9);
            Logger.d("winmgr", sb.toString());
            bVar.a(Math.max(i8, i2), Math.max(i9, i3));
        }
    }

    private boolean a(int i2, String str, String str2, boolean z) {
        return (TextUtils.isEmpty(str2) || !str2.startsWith("id:") || PdrUtil.isEmpty(str)) ? i2 == 4 ? !TextUtils.isEmpty(str2) && str2.equals(TypeAdapters.AnonymousClass27.SECOND) : i2 == 5 && z : str2.substring(3).equals(str);
    }

    private void a(AdaFrameItem adaFrameItem, IApp iApp) {
        int statusHeight;
        int iStringToColor;
        ViewOptions viewOptionsObtainFrameOptions = adaFrameItem.obtainFrameOptions();
        if (viewOptionsObtainFrameOptions.isStatusbar) {
            if ((PdrUtil.isEmpty(viewOptionsObtainFrameOptions.mStatusbarColor) || iApp.obtainStatusBarMgr().isImmersive) && -1 != (statusHeight = DeviceInfo.getStatusHeight(adaFrameItem.getContext()))) {
                int iHashCode = adaFrameItem.hashCode();
                int statusBarDefaultColor = iApp.obtainStatusBarMgr().getStatusBarDefaultColor();
                if (!PdrUtil.isEmpty(viewOptionsObtainFrameOptions.mStatusbarColor)) {
                    try {
                        iStringToColor = Color.parseColor(viewOptionsObtainFrameOptions.mStatusbarColor);
                    } catch (Exception unused) {
                        iStringToColor = PdrUtil.stringToColor(viewOptionsObtainFrameOptions.mStatusbarColor);
                    }
                    if (PdrUtil.checkStatusbarColor(iStringToColor)) {
                        statusBarDefaultColor = iStringToColor;
                    }
                }
                ViewGroup viewGroup = (ViewGroup) adaFrameItem.obtainMainView();
                if (viewGroup.findViewById(iHashCode) == null && viewOptionsObtainFrameOptions.height != 0) {
                    dv dvVar = new dv(adaFrameItem.getContext());
                    dvVar.setStatusBarHeight(statusHeight);
                    if (adaFrameItem.obtainFrameOptions().titleNView != null) {
                        "transparent".equals(adaFrameItem.obtainFrameOptions().titleNView.optString("type"));
                    }
                    dvVar.setBackgroundColor(statusBarDefaultColor);
                    dvVar.setId(iHashCode);
                    ViewGroup viewGroup2 = (ViewGroup) ((AdaFrameView) adaFrameItem).obtainWebviewParent().obtainMainView();
                    if (viewOptionsObtainFrameOptions.isStatusbarDodifyHeight) {
                        viewGroup.getLayoutParams().height = viewOptionsObtainFrameOptions.height + DeviceInfo.sStatusBarHeight;
                        viewGroup.addView(dvVar);
                    } else {
                        viewGroup.addView(dvVar);
                    }
                    JSONObject jSONObject = viewOptionsObtainFrameOptions.titleNView;
                    if (jSONObject == null || !TitleNViewUtil.isTitleTypeForDef(jSONObject)) {
                        viewGroup2.post(new c(this, adaFrameItem));
                    }
                }
            }
        }
    }

    private io.dcloud.common.core.ui.b a() {
        io.dcloud.common.core.ui.a aVarB = b();
        if (aVarB != null) {
            return aVarB.i();
        }
        return null;
    }

    public void a(io.dcloud.common.core.ui.b bVar) {
        bVar.p();
        bVar.k.b(bVar);
        if (bVar.e()) {
            processEvent(IMgr.MgrType.WindowMgr, 28, bVar.b);
            bVar.b = null;
        }
        bVar.r();
        bVar.i();
        bVar.i = false;
        bVar.h = false;
        bVar.inStack = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.core.ui.b r9, java.lang.Object[] r10) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(io.dcloud.common.core.ui.b, java.lang.Object[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.core.ui.b r17, boolean r18, org.json.JSONObject r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(io.dcloud.common.core.ui.b, boolean, org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fb  */
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
    private void a(io.dcloud.common.core.ui.b r33, io.dcloud.common.core.ui.b r34) {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.core.ui.l.a(io.dcloud.common.core.ui.b, io.dcloud.common.core.ui.b):void");
    }
}
