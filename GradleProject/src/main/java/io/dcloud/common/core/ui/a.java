package io.dcloud.common.core.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.dcloud.android.graphics.Region;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IOnCreateSplashView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebAppRootView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaContainerFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.CustomeizedInputConnection;
import io.dcloud.common.adapter.ui.DHImageView;
import io.dcloud.common.adapter.ui.RecordView;
import io.dcloud.common.adapter.ui.WebParentView;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.EventActionInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.SP;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.IntentConst;
import io.dcloud.common.core.permission.PermissionControler;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.TestUtil;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import supwisdom.zu;

/* JADX INFO: loaded from: classes2.dex */
public class a extends AdaContainerFrameItem implements ISysEventListener, IWebAppRootView {
    public DHImageView A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ICallBack f6372a;
    public Stack<io.dcloud.common.core.ui.b> b;
    public ArrayList<io.dcloud.common.core.ui.b> c;
    public io.dcloud.common.core.ui.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public io.dcloud.common.core.ui.b f6373e;
    public io.dcloud.common.core.ui.b f;
    public io.dcloud.common.core.ui.b g;
    public boolean h;
    public String i;
    public IApp j;
    public IActivityHandler k;
    public k l;
    public ICallBack m;
    public long n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public boolean s;
    public ArrayList<ICallBack> t;
    public m u;
    public n v;
    public o w;
    public byte x;
    public String y;
    public String z;

    /* JADX INFO: renamed from: io.dcloud.common.core.ui.a$a, reason: collision with other inner class name */
    public static /* synthetic */ class C0150a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6374a;

        static {
            int[] iArr = new int[ISysEventListener.SysEventType.values().length];
            f6374a = iArr;
            try {
                iArr[ISysEventListener.SysEventType.onPause.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onResume.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onSimStateChanged.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onDeviceNetChanged.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onNewIntent.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onConfigurationChanged.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onKeyboardShow.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onKeyboardHide.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onWebAppBackground.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onWebAppForeground.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onWebAppTrimMemory.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f6374a[ISysEventListener.SysEventType.onSplashclosed.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public class b implements ICallBack {
        public b() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            io.dcloud.common.core.ui.b bVarI;
            String strValueOf = String.valueOf(obj);
            if (TextUtils.isEmpty(strValueOf) || !strValueOf.equals("com.huawei.intent.action.CLICK_STATUSBAR") || a.this.obtainMainView().getParent() == null || (bVarI = a.this.i()) == null) {
                return null;
            }
            bVarI.c();
            return null;
        }
    }

    public class c implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.common.core.ui.b f6376a;

        public c(io.dcloud.common.core.ui.b bVar) {
            this.f6376a = bVar;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            if (a.this.b == null || this.f6376a == null) {
                return;
            }
            Logger.d("DHAppRootView.popFrameView frame" + this.f6376a);
            a.this.b.remove(this.f6376a);
            a.this.a(this.f6376a);
        }
    }

    public class d implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f6377a;

        public d(ArrayList arrayList) {
            this.f6377a = arrayList;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            try {
                if (a.this.b != null) {
                    for (int size = a.this.b.size() - 1; size >= 0; size--) {
                        io.dcloud.common.core.ui.b bVar = (io.dcloud.common.core.ui.b) a.this.b.get(size);
                        if (!this.f6377a.contains(bVar)) {
                            io.dcloud.common.core.ui.l lVar = bVar.mWindowMgr;
                            if (lVar != null) {
                                lVar.processEvent(IMgr.MgrType.WindowMgr, 22, bVar);
                            }
                            bVar.h = true;
                        }
                    }
                }
            } catch (Exception e2) {
                Logger.w("DHAppRootView onConfigurationChanged", e2);
            }
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            IActivityHandler iActivityHandler = a.this.k;
            if (iActivityHandler != null) {
                iActivityHandler.showSplashWaiting();
            }
        }
    }

    public class f implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f6379a;
        public final /* synthetic */ a b;
        public final /* synthetic */ int c;

        public f(boolean z, a aVar, int i) {
            this.f6379a = z;
            this.b = aVar;
            this.c = i;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            Logger.d("approotview", "closeSplashScreen1;autoClose=" + this.f6379a + ";mAppid" + a.this.i);
            a.this.a(this.b, this.c);
        }
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ a f6380a;
        public final /* synthetic */ int b;

        public g(a aVar, int i) {
            this.f6380a = aVar;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.q = false;
            a.this.a(this.f6380a, this.b);
        }
    }

    public class h implements ICallBack {
        public h() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            IApp iApp;
            a.this.j.onSplashClosed();
            a aVar = a.this;
            if (!aVar.o || (iApp = aVar.j) == null) {
                aVar.p = true;
                return null;
            }
            iApp.callSysEventListener(ISysEventListener.SysEventType.onSplashclosed, this);
            return null;
        }
    }

    public class i implements ViewTreeObserver.OnGlobalLayoutListener {
        public i() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            View viewObtainMainView = a.this.obtainMainView();
            a.this.onRootViewGlobalLayout(viewObtainMainView);
            if (viewObtainMainView == null || DeviceInfo.sDeviceSdkVer < 16 || viewObtainMainView.getViewTreeObserver() == null) {
                return;
            }
            viewObtainMainView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            viewObtainMainView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    public class j implements MessageHandler.IMessages {
        public j() {
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            Iterator it = a.this.t.iterator();
            while (it.hasNext()) {
                ((ICallBack) it.next()).onCallBack(-1, null);
            }
            a.this.t.clear();
        }
    }

    public class l extends io.dcloud.common.core.ui.k {
        public Paint s;
        public int t;
        public int u;
        public int v;
        public int w;
        public String x;
        public c y;

        /* JADX INFO: renamed from: io.dcloud.common.core.ui.a$l$a, reason: collision with other inner class name */
        public class C0151a implements ICallBack {
            public C0151a() {
            }

            @Override // io.dcloud.common.DHInterface.ICallBack
            public Object onCallBack(int i, Object obj) {
                l.this.m.c();
                a.this.f6372a = null;
                return null;
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (a.this.c != null) {
                    for (io.dcloud.common.core.ui.b bVar : a.this.c) {
                        if (!bVar.isChildOfFrameView) {
                            bVar.resize();
                        }
                    }
                }
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f6387a = 0;
            public boolean b = false;

            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!this.b) {
                    this.f6387a = 0;
                    return;
                }
                this.f6387a++;
                l.this.invalidate();
                this.f6387a %= 4;
                l.this.postDelayed(this, 500L);
            }
        }

        public l(Context context, a aVar) {
            super(context, aVar);
            Paint paint = new Paint();
            this.s = paint;
            this.x = "";
            this.y = new c();
            int i = (int) (((double) (DeviceInfo.DEFAULT_FONT_SIZE * DeviceInfo.sDensity)) * 1.2d);
            paint.setColor(-13421773);
            this.s.setTextSize(i);
            setTag("AppRootView");
            String string = context.getString(R.string.dcloud_common_in_the_buffer);
            this.x = string;
            this.t = (int) this.s.measureText(string);
            this.u = (int) this.s.measureText("...");
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchConfigurationChanged(Configuration configuration) {
            super.dispatchConfigurationChanged(configuration);
            if (!BaseInfo.sDoingAnimation) {
                this.m.c();
            } else {
                a.this.f6372a = new C0151a();
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
            if (a.this.j.getMaskLayerCount() <= 0) {
                this.y.b = false;
                return;
            }
            c cVar = this.y;
            if (!cVar.b) {
                cVar.b = true;
                cVar.run();
            }
            canvas.drawColor(-2013265920);
            canvas.drawText(this.x, this.v, this.w, this.s);
            int i = this.y.f6387a;
            if (i == 1) {
                canvas.drawText(Operators.DOT_STR, this.v + this.t, this.w, this.s);
            } else if (i == 2) {
                canvas.drawText(PdrUtil.FILE_PATH_ENTRY_BACK, this.v + this.t, this.w, this.s);
            } else if (i == 3) {
                canvas.drawText("...", this.v + this.t, this.w, this.s);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (a.this.j.getMaskLayerCount() > 0) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            a.this.mViewOptions.onScreenChanged();
            PlatformUtil.RESET_H_W();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            this.v = ((a.this.j.getInt(0) - this.t) - this.u) / 2;
            this.w = (int) (((double) a.this.j.getInt(2)) * 0.8d);
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
        @Override // android.view.View
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (AdaWebview.sCustomeizedInputConnection != null && i2 != 0 && i4 != 0) {
                int i5 = getResources().getDisplayMetrics().heightPixels;
                int i6 = getResources().getDisplayMetrics().heightPixels / 4;
                if (Math.abs(i2 - i5) > i6 || Math.abs(i2 - i4) > i6) {
                    if (i2 <= i4 || Math.abs(i2 - i4) <= i6) {
                        AdaWebview.sCustomeizedInputConnection.showRecordView(i2, true);
                    } else {
                        AdaWebview.sCustomeizedInputConnection.closeRecordView();
                    }
                }
            }
            a.this.j.updateScreenInfo(6);
            a.this.j.callSysEventListener(ISysEventListener.SysEventType.onSizeChanged, new int[]{i, i2, i3, i4});
            a.this.mViewOptions.onScreenChanged(i, i2);
            post(new b());
        }
    }

    public class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6388a;

        public void a() {
            throw null;
        }
    }

    public class n implements Comparator<io.dcloud.common.core.ui.b> {
        public n(a aVar) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.b bVar2) {
            if (bVar.getFrameType() == 3) {
                return 1;
            }
            if (bVar2.getFrameType() == 3) {
                return -1;
            }
            int i = bVar.mZIndex - bVar2.mZIndex;
            return i == 0 ? bVar.lastShowTime > bVar2.lastShowTime ? 1 : -1 : i;
        }
    }

    public class o implements Comparator<AdaFrameItem> {
        public o(a aVar) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(AdaFrameItem adaFrameItem, AdaFrameItem adaFrameItem2) {
            boolean z = adaFrameItem instanceof IFrameView;
            if (z && ((IFrameView) adaFrameItem).getFrameType() == 3) {
                return 1;
            }
            if (z && ((IFrameView) adaFrameItem).getFrameType() == 3) {
                return -1;
            }
            int i = adaFrameItem.mZIndex - adaFrameItem2.mZIndex;
            return i == 0 ? adaFrameItem.lastShowTime > adaFrameItem2.lastShowTime ? 1 : -1 : i;
        }
    }

    public a(Context context, IApp iApp, io.dcloud.common.core.ui.b bVar) {
        super(context);
        this.f6372a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f6373e = null;
        this.f = null;
        this.g = null;
        this.h = true;
        this.i = null;
        this.j = null;
        this.l = new k(this);
        this.m = new b();
        this.n = System.currentTimeMillis();
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = true;
        this.t = new ArrayList<>();
        this.v = new n(this);
        this.w = new o(this);
        this.A = null;
        this.o = BaseInfo.sRuntimeMode != null;
        this.j = iApp;
        this.k = DCloudAdapterUtil.getIActivityHandler(iApp.getActivity());
        this.i = iApp.obtainAppId();
        setMainView(new l(context, this));
        this.b = new Stack<>();
        this.c = new ArrayList<>();
        iApp.setWebAppRootView(this);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onPause);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onResume);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onDeviceNetChanged);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onNewIntent);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onConfigurationChanged);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onSimStateChanged);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyboardShow);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppBackground);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppForeground);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyboardHide);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppTrimMemory);
        iApp.registerSysEventListener(this, ISysEventListener.SysEventType.onSplashclosed);
        if (PermissionControler.checkPermission(this.i, IFeature.F_DEVICE.toLowerCase())) {
            String bundleData = SP.getBundleData(context, BaseInfo.PDR, "last_notify_net_type");
            String netWorkType = DeviceInfo.getNetWorkType();
            if (!PdrUtil.isEquals(bundleData, netWorkType)) {
                Logger.d("NetCheckReceiver", "netchange last_net_type:" + bundleData + ";cur_net_type:" + netWorkType);
                SP.setBundleData(context, BaseInfo.PDR, "last_notify_net_type", netWorkType);
            }
        }
        this.k.addClickStatusbarCallBack(this.m);
    }

    private void a(View view) {
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public boolean didCloseSplash() {
        return this.o;
    }

    @Override // io.dcloud.common.adapter.ui.AdaContainerFrameItem, io.dcloud.common.adapter.ui.AdaFrameItem
    public synchronized void dispose() {
        b();
        this.b = null;
        this.c = null;
        m mVar = this.u;
        if (mVar != null) {
            mVar.f6388a = false;
        }
        DHImageView dHImageView = this.A;
        if (dHImageView != null) {
            dHImageView.setImageBitmap(null);
            this.A = null;
        }
        super.dispose();
        this.k.removeClickStatusbarCallBack(this.m);
        RecordView recordView = AdaWebview.mRecordView;
        if (recordView != null) {
            recordView.dispose();
        }
        AdaWebview.mRecordView = null;
    }

    public io.dcloud.common.core.ui.b f() {
        return a(2);
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public IFrameView findFrameViewB(IFrameView iFrameView) {
        if (!this.c.contains(iFrameView)) {
            return null;
        }
        ArrayList<io.dcloud.common.core.ui.b> arrayList = new ArrayList<>();
        a(iFrameView, arrayList);
        a(arrayList);
        if (arrayList.size() <= 1 && arrayList.size() == 1) {
            return arrayList.get(0);
        }
        return null;
    }

    public void g(io.dcloud.common.core.ui.b bVar) {
        MessageHandler.sendMessage(new c(bVar), null);
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public void goHome(IFrameView iFrameView) {
        if (iFrameView instanceof io.dcloud.common.core.ui.b) {
            io.dcloud.common.core.ui.b bVar = (io.dcloud.common.core.ui.b) iFrameView;
            bVar.getAnimOptions().mOption = (byte) 1;
            bVar.getAnimOptions().mAnimType = "none";
            bVar.mWindowMgr.processEvent(IMgr.MgrType.WindowMgr, 2, iFrameView);
        }
    }

    public void h() {
        if (!AndroidResources.sIMEAlive || this.t.size() <= 0) {
            return;
        }
        MessageHandler.sendMessage(new j(), BaseInfo.isUniAppAppid(this.j) ? 50 : 500, null);
    }

    public io.dcloud.common.core.ui.b i() {
        Stack<io.dcloud.common.core.ui.b> stack = this.b;
        io.dcloud.common.core.ui.b bVar = null;
        if (stack != null && !stack.isEmpty()) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                bVar = this.b.get(size);
                if (bVar.obtainMainView().getVisibility() == 0 && !bVar.isChildOfFrameView) {
                    break;
                }
            }
        }
        return bVar;
    }

    public void j() {
        Stack<io.dcloud.common.core.ui.b> stack = this.b;
        if (stack == null || stack.isEmpty()) {
            return;
        }
        Boolean bool = Boolean.FALSE;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            io.dcloud.common.core.ui.b bVar = this.b.get(size);
            View viewObtainMainView = bVar.obtainMainView();
            if (!bVar.isChildOfFrameView) {
                if (bool.booleanValue() || viewObtainMainView.getVisibility() != 0) {
                    viewObtainMainView.setImportantForAccessibility(4);
                } else {
                    viewObtainMainView.setImportantForAccessibility(0);
                    bool = Boolean.TRUE;
                }
            }
        }
    }

    public void k() {
        if (obtainMainView() instanceof io.dcloud.common.core.ui.k) {
            ((io.dcloud.common.core.ui.k) obtainMainView()).c();
        }
    }

    public void l() {
        Collections.sort(this.c, this.v);
    }

    public void m() {
        Collections.sort(this.b, this.v);
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.b.get(i2).obtainMainView().bringToFront();
        }
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public void onAppActive(IApp iApp) {
        b(iApp);
        BaseInfo.sCurrentAppOriginalAppid = iApp.obtainOriginalAppId();
        iApp.getActivity();
        if (this.k != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            if (iApp.obtainStatusBarMgr().isTemporaryFullScreen && !iApp.obtainStatusBarMgr().isImmersive && !iApp.isFullScreen() && !this.o) {
                layoutParams.topMargin = DeviceInfo.sStatusBarHeight;
            }
            this.k.setViewAsContentView(obtainMainView(), layoutParams);
        }
        Logger.d(Logger.MAIN_TAG, iApp.obtainAppId() + " onAppActive setContentView");
        a(obtainMainView());
        FeatureMessageDispatcher.dispatchMessage("app_open", 1);
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public void onAppStart(IApp iApp) {
        this.o = false;
        this.p = false;
        if (iApp != null) {
            a(iApp);
        }
        obtainMainView().getViewTreeObserver().addOnGlobalLayoutListener(new i());
        obtainMainView().setBackgroundColor(-1);
        onAppActive(iApp);
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public void onAppStop(IApp iApp) {
        onAppUnActive(iApp);
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public void onAppUnActive(IApp iApp) {
        m mVar = this.u;
        if (mVar != null) {
            mVar.a();
        }
    }

    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        String str;
        CustomeizedInputConnection customeizedInputConnection;
        str = "default";
        EventActionInfo eventActionInfo = null;
        String str2 = null;
        eventActionInfo = null;
        String str3 = "";
        switch (C0150a.f6374a[sysEventType.ordinal()]) {
            case 1:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, "pause");
                eventActionInfo = new EventActionInfo("pause");
                break;
            case 2:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_RESUME);
                if (!AndroidResources.sIMEAlive && (customeizedInputConnection = AdaWebview.sCustomeizedInputConnection) != null) {
                    customeizedInputConnection.closeRecordView();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(this.n));
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(new Date(System.currentTimeMillis()));
                if (calendar.get(5) != calendar2.get(5)) {
                    BaseInfo.run5appEndTime = 0L;
                    this.j.setConfigProperty("commit", String.valueOf(this.n));
                    this.n = calendar2.getTimeInMillis();
                }
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_RESUME);
                break;
            case 3:
                if (PermissionControler.checkPermission(this.i, IFeature.F_DEVICE.toLowerCase())) {
                    String str4 = StringUtil.format("plus.device.imsi = ['%s'];", DeviceInfo.getUpdateIMSI());
                    str3 = (AbsoluteConst.PROTOCOL_JAVASCRIPT + str4) + StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_IMSI_CHANGE);
                    eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_IMSI_CHANGE, str4);
                }
                break;
            case 4:
                if (!PermissionControler.checkPermission(this.i, IFeature.F_DEVICE.toLowerCase())) {
                    return false;
                }
                String bundleData = SP.getBundleData(getContext(), BaseInfo.PDR, "last_notify_net_type");
                String netWorkType = DeviceInfo.getNetWorkType();
                if (PdrUtil.isEquals(bundleData, netWorkType)) {
                    return false;
                }
                Logger.d("NetCheckReceiver", "netchange last_net_type:" + bundleData + ";cur_net_type:" + netWorkType);
                SP.setBundleData(getContext(), BaseInfo.PDR, "last_notify_net_type", netWorkType);
                String str5 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_NETCHANGE);
                String str6 = StringUtil.format("plus.device.imsi = ['%s'];", DeviceInfo.getUpdateIMSI());
                str3 = AbsoluteConst.PROTOCOL_JAVASCRIPT + str6 + str5;
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_NETCHANGE, str6);
                break;
                break;
            case 5:
                this.j.setRuntimeArgs(String.valueOf(obj));
                String launchType = BaseInfo.getLaunchType(getActivity().getIntent());
                BaseInfo.putLauncherData(this.j.obtainAppId(), launchType);
                String bundleData2 = SP.getBundleData(getContext(), "pdr", this.j.obtainAppId() + AbsoluteConst.LAUNCHTYPE);
                str = TextUtils.isEmpty(bundleData2) ? "default" : bundleData2;
                String str7 = StringUtil.format(AbsoluteConst.JS_RUNTIME_ARGUMENTS, this.j.obtainRuntimeArgs(true));
                String str8 = StringUtil.format(AbsoluteConst.JS_RUNTIME_LAUNCHER, launchType);
                String str9 = StringUtil.format(AbsoluteConst.JS_RUNTIME_ORIGIN, str);
                str3 = (AbsoluteConst.PROTOCOL_JAVASCRIPT + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, str7) + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, str8) + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, str9)) + StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_NEW_INTENT);
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_NEW_INTENT, AbsoluteConst.JS_RUNTIME_V8_BASE + str7 + str8 + str9);
                break;
            case 6:
                this.j.updateScreenInfo(1);
                return false;
            case 7:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_KEYBOARD_SHOW);
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_KEYBOARD_SHOW);
                break;
            case 8:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_KEYBOARD_HIDE);
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_KEYBOARD_HIDE);
                break;
            case 9:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, "background");
                eventActionInfo = new EventActionInfo("background");
                break;
            case 10:
                String launcherData = BaseInfo.getLauncherData(this.j.obtainAppId());
                if (!this.j.obtainWebAppIntent().getBooleanExtra(IntentConst.IS_WEBAPP_REPLY, false)) {
                    this.j.setRuntimeArgs(String.valueOf(obj));
                    String str10 = StringUtil.format(AbsoluteConst.JS_RUNTIME_ARGUMENTS, this.j.obtainRuntimeArgs(true));
                    String str11 = StringUtil.format(AbsoluteConst.JS_RUNTIME_LAUNCHER, launcherData);
                    str3 = AbsoluteConst.PROTOCOL_JAVASCRIPT + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, str10) + StringUtil.format(AbsoluteConst.JS_RUNTIME_BASE, str11);
                    str2 = str10 + str11;
                    str = launcherData;
                }
                str3 = str3 + StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE_PARAMETER, AbsoluteConst.EVENTS_WEBAPP_FOREGROUND, str);
                HashMap map = new HashMap();
                map.put("active", str);
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_WEBAPP_FOREGROUND, str2, map);
                break;
            case 11:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_WEBAPP_TRIMMEMORY);
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_WEBAPP_TRIMMEMORY);
                break;
            case 12:
                str3 = StringUtil.format(AbsoluteConst.EVENTS_DOCUMENT_EXECUTE_TEMPLATE, AbsoluteConst.EVENTS_SPLASH_CLOSED);
                eventActionInfo = new EventActionInfo(AbsoluteConst.EVENTS_SPLASH_CLOSED);
                break;
        }
        if (this.c != null && !TextUtils.isEmpty(str3)) {
            for (int size = this.c.size() - 1; size >= 0; size--) {
                IWebview iWebviewObtainWebView = this.c.get(size).obtainWebView();
                if (iWebviewObtainWebView != null) {
                    if (!(iWebviewObtainWebView instanceof AdaUniWebView) || eventActionInfo == null) {
                        iWebviewObtainWebView.loadUrl(str3);
                    } else {
                        ((AdaUniWebView) iWebviewObtainWebView).fireEvent(eventActionInfo);
                    }
                }
            }
        }
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IWebAppRootView
    public void onRootViewGlobalLayout(View view) {
        if (isDisposed()) {
            return;
        }
        if (AdaWebview.ScreemOrientationChangedNeedLayout) {
            AdaWebview.ScreemOrientationChangedNeedLayout = false;
            this.j.updateScreenInfo(3);
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (Math.abs(width - this.j.getInt(0)) > 100) {
            return;
        }
        int i2 = height - this.j.getInt(1);
        if (!this.j.isVerticalScreen()) {
            i2 = width - this.j.getInt(0);
        }
        if (i2 != 0) {
            this.j.updateScreenInfo(3);
        }
        if (view.getHeight() != this.r && view.getHeight() == this.j.getInt(1)) {
            PlatformUtil.RESET_H_W();
            if (!this.s) {
                BaseInfo.sFullScreenChanged = true;
            }
            this.s = false;
        }
        this.r = view.getHeight();
    }

    public void reload(boolean z) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            IWebview iWebviewObtainWebView = this.c.get(size).obtainWebView();
            if (iWebviewObtainWebView != null) {
                iWebviewObtainWebView.reload();
            }
            if (!z) {
                return;
            }
        }
    }

    public void b(io.dcloud.common.core.ui.b bVar) {
        int i2;
        io.dcloud.common.core.ui.b bVar2;
        byte b2 = bVar.getAnimOptions().mOption;
        byte b3 = 4;
        if ((!bVar.d && !bVar.inStack && bVar.obtainFrameOptions().hasTransparentValue()) || bVar.obtainMainView().getVisibility() != 0) {
            if (b2 == 3 || b2 == 1) {
                ArrayList<io.dcloud.common.core.ui.b> arrayList = new ArrayList<>();
                a(arrayList, bVar);
                bVar.c = arrayList;
                if (b2 != 1) {
                    return;
                }
            } else {
                if (b2 == 2) {
                    return;
                }
                if (b2 == 4 || b2 == 0) {
                    ArrayList<io.dcloud.common.core.ui.b> arrayList2 = new ArrayList<>();
                    b(arrayList2, bVar);
                    bVar.b = arrayList2;
                    return;
                }
            }
        }
        ArrayList<io.dcloud.common.core.ui.b> arrayList3 = new ArrayList<>();
        ArrayList<io.dcloud.common.core.ui.b> arrayList4 = new ArrayList<>();
        Region region = new Region(2);
        int size = this.c.size() - 1;
        while (size >= 0) {
            io.dcloud.common.core.ui.b bVar3 = this.c.get(size);
            if (bVar3.obtainMainView().getVisibility() == 0) {
                if (bVar3.isChildOfFrameView) {
                    i2 = size;
                } else {
                    bVar3.h();
                    ViewOptions viewOptionsObtainFrameOptions = bVar3.obtainFrameOptions();
                    if (b2 == b3 || b2 == 0) {
                        i2 = size;
                        if ((a(region) || region.c() > 2) && bVar != bVar3) {
                            if (a(arrayList3, bVar3)) {
                                break;
                            }
                        } else if (viewOptionsObtainFrameOptions.hasTransparentValue() || !a(region, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height)) {
                            b(arrayList4, bVar3);
                        } else if (a(arrayList3, bVar3)) {
                            break;
                        }
                    } else if (b2 == 2) {
                        ViewOptions viewOptionsObtainFrameOptions_Animate = bVar3.obtainFrameOptions_Animate();
                        if (bVar3 == bVar && viewOptionsObtainFrameOptions_Animate != null) {
                            viewOptionsObtainFrameOptions = viewOptionsObtainFrameOptions_Animate;
                        }
                        if (a(region)) {
                            if (a(arrayList3, bVar3)) {
                                break;
                            }
                            i2 = size;
                        } else {
                            if (viewOptionsObtainFrameOptions.hasTransparentValue()) {
                                bVar2 = bVar3;
                                i2 = size;
                            } else {
                                bVar2 = bVar3;
                                i2 = size;
                                if (a(region, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height)) {
                                    if (a(arrayList3, bVar2)) {
                                        break;
                                    }
                                }
                            }
                            b(arrayList4, bVar2);
                        }
                    } else {
                        i2 = size;
                        if (b2 == 3 || b2 == 1) {
                            if (bVar3 == bVar) {
                                a(arrayList3, bVar3);
                            } else if (a(region)) {
                                a(arrayList3, bVar3);
                            } else if (viewOptionsObtainFrameOptions.hasTransparentValue() || !a(region, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height)) {
                                b(arrayList4, bVar3);
                            } else if (a(arrayList3, bVar3)) {
                                break;
                            }
                        }
                    }
                }
                size = i2 - 1;
                b3 = 4;
            } else {
                i2 = size;
                if (a(arrayList3, bVar3)) {
                    break;
                }
                size = i2 - 1;
                b3 = 4;
            }
        }
        bVar.c = arrayList3;
        bVar.b = arrayList4;
    }

    public int c(io.dcloud.common.core.ui.b bVar) {
        return this.b.indexOf(bVar);
    }

    public boolean d(io.dcloud.common.core.ui.b bVar) {
        for (io.dcloud.common.core.ui.b bVar2 : this.c) {
            if (bVar != bVar2 && !bVar2.isChildOfFrameView && bVar2.obtainMainView().getVisibility() == 0) {
                return true;
            }
        }
        return false;
    }

    public Stack<io.dcloud.common.core.ui.b> e() {
        return this.b;
    }

    public void f(io.dcloud.common.core.ui.b bVar) {
        AnimOptions animOptions = bVar.getAnimOptions();
        animOptions.mOption = this.x;
        animOptions.mAnimType = this.y;
        animOptions.mAnimType_close = this.z;
    }

    public io.dcloud.common.core.ui.b g() {
        return a(4);
    }

    public class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6384a = 0;
        public Vector<io.dcloud.common.core.ui.b> b = new Vector<>();

        public k(a aVar) {
        }

        public void a(io.dcloud.common.core.ui.b bVar) {
            this.b.add(bVar);
            int i = this.f6384a + 1;
            this.f6384a = i;
            if (i > 1) {
                return;
            }
            this.f6384a = 1;
        }

        public void b(io.dcloud.common.core.ui.b bVar) {
            this.b.remove(bVar);
            this.f6384a--;
        }

        public int a() {
            return this.f6384a;
        }
    }

    private void a(io.dcloud.common.core.ui.b bVar, int i2, int i3) {
        Logger.d("DHAppRootView.pushFrameView" + bVar);
        this.b.insertElementAt(bVar, i2);
        addFrameItem(bVar, i3);
    }

    public void c() {
        Logger.d(Logger.ANIMATION_TAG, "AppRootView dispatchConfigurationChanged(横竖屏切换、全屏非全屏切换、虚拟返回键栏隐藏显示) 引发调整栈窗口");
        ArrayList<io.dcloud.common.core.ui.b> arrayList = new ArrayList<>();
        ArrayList<io.dcloud.common.core.ui.b> arrayList2 = new ArrayList<>();
        a(arrayList2, arrayList);
        for (io.dcloud.common.core.ui.b bVar : arrayList2) {
            boolean zContains = this.b.contains(bVar);
            io.dcloud.common.core.ui.l lVar = bVar.mWindowMgr;
            if (lVar != null) {
                lVar.processEvent(IMgr.MgrType.WindowMgr, 8, bVar);
            }
            bVar.i = !zContains;
        }
        MessageHandler.sendMessage(new d(arrayList2), null);
    }

    public int e(io.dcloud.common.core.ui.b bVar) {
        int i2;
        int i3;
        int i4;
        int iIndexOf = this.c.indexOf(bVar);
        int size = this.b.size();
        while (true) {
            size--;
            if (size < 0) {
                i3 = 0;
                break;
            }
            io.dcloud.common.core.ui.b bVar2 = this.b.get(size);
            int iIndexOf2 = this.c.indexOf(bVar2);
            if (iIndexOf2 >= 0 && iIndexOf > iIndexOf2 && bVar2.getFrameType() != 3) {
                i3 = size + 1;
                break;
            }
        }
        if (i3 != 0) {
            ViewGroup viewGroupObtainMainViewGroup = obtainMainViewGroup();
            int childCount = viewGroupObtainMainViewGroup.getChildCount();
            i4 = i3;
            int i5 = 0;
            for (i2 = 0; i2 < childCount; i2++) {
                if (viewGroupObtainMainViewGroup.getChildAt(i2) instanceof zu) {
                    i5++;
                } else {
                    i4++;
                }
                if (i5 >= i3) {
                    break;
                }
            }
        } else {
            i4 = i3;
        }
        a(bVar, i3, i4);
        if (BaseInfo.isUniAppAppid(bVar.obtainApp())) {
            bVar.changeWebParentViewRect();
        }
        return i3;
    }

    public ArrayList<io.dcloud.common.core.ui.b> d() {
        return this.c;
    }

    public void h(io.dcloud.common.core.ui.b bVar) {
        ArrayList<AdaFrameItem> arrayList = bVar.getParentFrameItem().mChildArrayList;
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, this.w);
            ArrayList arrayList2 = new ArrayList();
            for (AdaFrameItem adaFrameItem : arrayList) {
                if (adaFrameItem.obtainMainView() != null) {
                    if (!(adaFrameItem.obtainMainView() instanceof WebParentView)) {
                        adaFrameItem.obtainMainView().bringToFront();
                    }
                } else {
                    arrayList2.add(adaFrameItem);
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.removeAll(arrayList2);
            }
        }
        i(bVar);
    }

    public void i(io.dcloud.common.core.ui.b bVar) {
        if (bVar.getParentFrameItem() == null) {
            return;
        }
        bVar.getParentFrameItem().sortNativeViewBringToFront();
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
    public void reload(String str) {
        String[] strArrSplit = str.split("\\|");
        if (strArrSplit.length > 0 && BaseInfo.isUniNViewBackgroud() && this.c.size() > 0) {
            io.dcloud.common.core.ui.b bVar = this.c.get(0);
            bVar.mWindowMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{bVar.obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "updateServiceReload", null});
        }
        for (int size = this.c.size() - 1; size >= 0; size--) {
            io.dcloud.common.core.ui.b bVar2 = this.c.get(size);
            IWebview iWebviewObtainWebView = bVar2.obtainWebView();
            boolean z = bVar2.obtainFrameOptions().mUniNViewJson != null;
            if (iWebviewObtainWebView != null) {
                for (String str2 : strArrSplit) {
                    if (z) {
                        if (str2.endsWith(".js")) {
                            bVar2.mWindowMgr.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[]{bVar2.obtainApp(), "weex,io.dcloud.feature.weex.WeexFeature", "updateReload", new Object[]{str2}});
                        }
                    } else if (iWebviewObtainWebView.obtainUrl().startsWith(str2)) {
                        iWebviewObtainWebView.reload();
                        break;
                    } else if (!(iWebviewObtainWebView instanceof TabBarWebview) || !((TabBarWebview) iWebviewObtainWebView).checkUrlToReload(str2)) {
                    }
                }
            }
        }
    }

    private void a(ArrayList<io.dcloud.common.core.ui.b> arrayList, ArrayList<io.dcloud.common.core.ui.b> arrayList2) {
        Region region = new Region(2);
        for (int size = this.c.size() - 1; size >= 0; size--) {
            io.dcloud.common.core.ui.b bVar = this.c.get(size);
            if (bVar.obtainMainView().getVisibility() == 0) {
                ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
                if (bVar.isChildOfFrameView) {
                    continue;
                } else if (bVar.f6389a) {
                    a(region, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height);
                    b(arrayList, bVar);
                } else {
                    bVar.h();
                    if (a(region)) {
                        if (a(arrayList2, bVar)) {
                            return;
                        }
                    } else if (!viewOptionsObtainFrameOptions.hasTransparentValue() && a(region, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height)) {
                        if (a(arrayList2, bVar)) {
                            return;
                        }
                    } else {
                        b(arrayList, bVar);
                    }
                }
            } else if (a(arrayList2, bVar)) {
                return;
            }
        }
    }

    public boolean a(Region region) {
        boolean zQuickContains = region.quickContains(0, 0, this.j.getInt(0), this.j.getInt(1));
        if (!region.b() && zQuickContains) {
            region.setEmpty();
            region.a();
            return false;
        }
        return zQuickContains;
    }

    public boolean a(ArrayList<io.dcloud.common.core.ui.b> arrayList, io.dcloud.common.core.ui.b bVar) {
        arrayList.add(bVar);
        return false;
    }

    public boolean a(Region region, int i2, int i3, int i4, int i5) {
        int i6 = i2 + i4;
        int i7 = i3 + i5;
        boolean zQuickContains = region.quickContains(i2, i3, i6, i7);
        if (!zQuickContains) {
            region.op(i2, i3, i6, i7, Region.Op.UNION);
        }
        return zQuickContains;
    }

    public void a(io.dcloud.common.core.ui.b bVar) {
        Logger.d("DHAppRootView.closeFrameView pFrameView=" + bVar);
        bVar.onDestroy();
        removeFrameItem(bVar);
        System.gc();
    }

    public void a(IApp iApp) {
        if (Boolean.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_WAITING))) {
            obtainMainView().postDelayed(new e(), 100L);
        }
    }

    public io.dcloud.common.core.ui.b a(int i2) {
        ArrayList<io.dcloud.common.core.ui.b> arrayList = this.c;
        if (arrayList != null) {
            for (io.dcloud.common.core.ui.b bVar : arrayList) {
                if (bVar.getFrameType() == i2) {
                    return bVar;
                }
            }
        }
        return null;
    }

    public void a(a aVar, io.dcloud.common.core.ui.b bVar, int i2, boolean z, int i3) {
        if (bVar != null) {
            Logger.d("approotview", "closeSplashScreen0 delay=" + i2 + ";autoClose=" + z + ";mAppid" + this.i);
            if (bVar.obtainMainView() != null) {
                MessageHandler.sendMessage(new f(z, aVar, i3), Math.max(i2, 150), bVar);
                return;
            }
            Logger.d("approotview", "closeSplashScreen2;autoClose;mAppid" + this.i);
            a(aVar, i3);
        }
    }

    public void b(ArrayList<io.dcloud.common.core.ui.b> arrayList, io.dcloud.common.core.ui.b bVar) {
        arrayList.add(bVar);
    }

    public void b() {
        Logger.d(this.i + " clearFrameView");
        ArrayList<io.dcloud.common.core.ui.b> arrayList = this.c;
        if (arrayList != null) {
            int size = arrayList.size();
            io.dcloud.common.core.ui.b[] bVarArr = new io.dcloud.common.core.ui.b[size];
            this.c.toArray(bVarArr);
            for (int i2 = 0; i2 < size; i2++) {
                try {
                    bVarArr[i2].onDestroy();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.c.clear();
        }
        clearView();
        Stack<io.dcloud.common.core.ui.b> stack = this.b;
        if (stack != null) {
            stack.clear();
        }
    }

    public void a(a aVar, int i2) {
        Log.e("Html5Plus-SplashClosed", System.currentTimeMillis() + "");
        Log.e(Logger.MAIN_TAG, "closeSplashScreen0 appid=" + this.i + ";" + (i2 > 10) + ";closeSplashDid=" + this.o);
        if (aVar != null && !this.o) {
            if (this.q) {
                return;
            }
            boolean z = this.k != null ? !r3.hasAdService() : true;
            IActivityHandler iActivityHandler = this.k;
            if (iActivityHandler != null) {
                if (!iActivityHandler.hasAdService()) {
                    Object objInvokeMethod = PlatformUtil.invokeMethod("io.dcloud.feature.gg.dcloud.ADHandler", "SplashAdIsEnable", null, new Class[]{Context.class}, new Object[]{aVar.getContext()});
                    if ((objInvokeMethod instanceof Boolean) && ((Boolean) objInvokeMethod).booleanValue()) {
                        long jAbs = Math.abs(System.currentTimeMillis() - BaseInfo.splashCreateTime);
                        if (jAbs < 2500) {
                            this.q = true;
                            MessageHandler.postDelayed(new g(aVar, i2), 2500 - jAbs);
                            return;
                        }
                    }
                }
                if (z) {
                    this.k.closeAppStreamSplash(this.i);
                }
                BaseInfo.setLoadingLaunchePage(false, "closeSplashScreen0");
                try {
                    ((FrameLayout.LayoutParams) obtainMainView().getLayoutParams()).topMargin = 0;
                } catch (Exception e2) {
                    Logger.e("Exception", "e.getMessage()==" + e2.getMessage());
                }
                if (z) {
                    this.j.onSplashClosed();
                } else {
                    this.k.setSplashCloseListener(this.i, new h());
                }
            } else {
                IApp iApp = this.j;
                if (iApp != null) {
                    iApp.diyStatusBarState();
                }
            }
            BaseInfo.run5appEndTime = TestUtil.getUseTime(AbsoluteConst.RUN_5AP_TIME_KEY, "");
            TestUtil.delete(AbsoluteConst.RUN_5AP_TIME_KEY);
            this.j.setConfigProperty("commit", String.valueOf(this.n));
            IOnCreateSplashView onCreateSplashView = this.j.getOnCreateSplashView();
            if (onCreateSplashView != null) {
                onCreateSplashView.onCloseSplash();
            }
            DCKeyboardManager.getInstance().dhAppRootIsReady(this);
            BaseInfo.splashCloseTime = System.currentTimeMillis();
            IApp iApp2 = this.j;
            if ((iApp2 != null && z) || this.p) {
                iApp2.callSysEventListener(ISysEventListener.SysEventType.onSplashclosed, this);
            }
        }
        this.o = true;
    }

    private void b(IApp iApp) {
        iApp.setFullScreen(PdrUtil.parseBoolean(iApp.obtainConfigProperty(IApp.ConfigProperty.CONFIG_FULLSCREEN), false, false));
    }

    public Object a(View view, ICallBack iCallBack) {
        if (!AndroidResources.sIMEAlive) {
            return iCallBack.onCallBack(-1, null);
        }
        DeviceInfo.hideIME(obtainMainView());
        this.t.add(iCallBack);
        return null;
    }

    private void a(ArrayList<io.dcloud.common.core.ui.b> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int i2 = this.j.getInt(0);
        for (io.dcloud.common.core.ui.b bVar : arrayList) {
            ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
            int i3 = viewOptionsObtainFrameOptions.width;
            if (i3 == -1) {
                i3 = i2;
            }
            int i4 = viewOptionsObtainFrameOptions.left;
            if (i4 + i3 <= 0 || i4 >= i2 || viewOptionsObtainFrameOptions.right + i3 <= 0) {
                arrayList2.add(bVar);
            }
        }
        arrayList.removeAll(arrayList2);
    }

    public void a(IFrameView iFrameView, ArrayList<io.dcloud.common.core.ui.b> arrayList) {
        if (this.c.contains(iFrameView)) {
            int iIndexOf = this.c.indexOf(iFrameView);
            if (this.c != null) {
                com.dcloud.android.graphics.Region region = new com.dcloud.android.graphics.Region();
                for (int i2 = iIndexOf - 1; i2 >= 0; i2--) {
                    io.dcloud.common.core.ui.b bVar = this.c.get(i2);
                    if (!bVar.isChildOfFrameView && bVar.obtainMainView().getVisibility() == 0) {
                        arrayList.add(bVar);
                        ViewOptions viewOptionsObtainFrameOptions = bVar.obtainFrameOptions();
                        a(region, viewOptionsObtainFrameOptions.left, viewOptionsObtainFrameOptions.top, viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height);
                    }
                    if (a(region)) {
                        return;
                    }
                }
            }
        }
    }

    public void a(io.dcloud.common.core.ui.b bVar, io.dcloud.common.core.ui.b bVar2) {
        AnimOptions animOptions = bVar.getAnimOptions();
        this.x = animOptions.mOption;
        animOptions.mOption = bVar2.getAnimOptions().mOption;
        this.y = animOptions.mAnimType;
        animOptions.mAnimType = bVar2.getAnimOptions().mAnimType;
        this.z = animOptions.mAnimType_close;
        animOptions.mAnimType_close = bVar2.getAnimOptions().mAnimType_close;
    }

    public DHImageView a(io.dcloud.common.core.ui.b bVar, int i2, boolean z) {
        boolean z2;
        DHImageView dHImageView;
        long jCurrentTimeMillis = System.currentTimeMillis();
        l lVar = (l) obtainMainView();
        if (bVar.mNativeView != null) {
            if (this.A == null) {
                this.A = lVar.getLeftImageView();
            }
            if (bVar.mNativeView.isAnimate()) {
                this.A.removeNativeView();
                bVar.mNativeView = null;
            } else {
                if (this.A.getParent() != lVar) {
                    if (this.A.getParent() != null) {
                        ((ViewGroup) this.A.getParent()).removeView(this.A);
                    }
                    lVar.addView(this.A);
                }
                this.A.addNativeView(bVar, bVar.mNativeView);
                this.A.setImageBitmap(null);
                this.A.bringToFront();
                this.A.setVisibility(0);
                return this.A;
            }
        }
        if (a((ViewGroup) lVar)) {
            DHImageView dHImageView2 = this.A;
            if (dHImageView2 != null) {
                dHImageView2.clear();
                this.A = null;
            }
            return null;
        }
        Bitmap bitmapCaptureView = bVar.mSnapshot;
        if (bitmapCaptureView != null) {
            z2 = false;
        } else {
            if (1 == i2 && (dHImageView = this.A) != null && dHImageView.getBitmap() != null && this.A.getTag() != null && bVar.hashCode() == ((Integer) this.A.getTag()).intValue()) {
                if (this.A.getParent() != lVar) {
                    if (this.A.getParent() != null) {
                        ((ViewGroup) this.A.getParent()).removeView(this.A);
                    }
                    lVar.addView(this.A);
                }
                this.A.removeNativeView();
                this.A.bringToFront();
                this.A.setVisibility(0);
                return this.A;
            }
            bitmapCaptureView = PlatformUtil.captureView(bVar.obtainMainView());
            z2 = true;
        }
        if (bitmapCaptureView != null && !PlatformUtil.isWhiteBitmap(bitmapCaptureView)) {
            if (this.A == null) {
                this.A = lVar.getLeftImageView();
            }
            if (this.A.getParent() != lVar) {
                if (this.A.getParent() != null) {
                    ((ViewGroup) this.A.getParent()).removeView(this.A);
                }
                lVar.addView(this.A);
            }
            this.A.bringToFront();
            this.A.setImageBitmap(bitmapCaptureView);
            this.A.removeNativeView();
            this.A.setVisibility(0);
        } else {
            DHImageView dHImageView3 = this.A;
            if (dHImageView3 != null) {
                dHImageView3.clear();
                this.A = null;
            }
        }
        DHImageView dHImageView4 = this.A;
        if (dHImageView4 != null) {
            if (dHImageView4.isSlipping()) {
                return null;
            }
            this.A.refreshImagerView();
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("==============B截图耗时=");
        long j2 = jCurrentTimeMillis2 - jCurrentTimeMillis;
        sb.append(j2);
        Logger.i("mabo", sb.toString());
        if (j2 >= BaseInfo.sTimeoutCapture) {
            int i3 = BaseInfo.sTimeOutCount + 1;
            BaseInfo.sTimeOutCount = i3;
            if (i3 > BaseInfo.sTimeOutMax) {
                BaseInfo.sAnimationCaptureB = false;
            }
        } else if (z2) {
            BaseInfo.sTimeOutCount = 0;
        }
        return this.A;
    }

    public boolean a(ViewGroup viewGroup) {
        DHImageView dHImageView = this.A;
        return (dHImageView == null || dHImageView.mBitmapHeight <= 0 || ((long) viewGroup.getHeight()) == this.A.mBitmapHeight) ? false : true;
    }
}
