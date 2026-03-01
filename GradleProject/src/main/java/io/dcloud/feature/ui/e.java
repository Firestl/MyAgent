package io.dcloud.feature.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import com.huawei.secure.android.common.util.ZipUtil;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.webview.WebResUtil;
import io.dcloud.common.adapter.util.AnimOptions;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.nineoldandroids.animation.Animator;
import io.dcloud.nineoldandroids.animation.ValueAnimator;
import io.dcloud.nineoldandroids.view.ViewHelper;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class e {
    public static HashMap<String, String> d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsMgr f6630a;
    public HashMap<String, io.dcloud.feature.ui.a> b = new HashMap<>(1);
    public String c;

    public class a implements MessageHandler.IMessages {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ io.dcloud.feature.ui.a f6631a;
        public final /* synthetic */ io.dcloud.feature.ui.c b;

        public a(e eVar, io.dcloud.feature.ui.a aVar, io.dcloud.feature.ui.c cVar) {
            this.f6631a = aVar;
            this.b = cVar;
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            this.f6631a.g(this.b);
        }
    }

    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6632a;

        public b(e eVar, View view) {
            this.f6632a = view;
        }

        @Override // io.dcloud.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!(this.f6632a.getLayoutParams() instanceof AbsoluteLayout.LayoutParams)) {
                if (this.f6632a.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                    ViewHelper.setX(this.f6632a, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                    return;
                }
                return;
            }
            AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) this.f6632a.getLayoutParams();
            layoutParams.height = this.f6632a.getHeight();
            layoutParams.width = this.f6632a.getWidth();
            try {
                ViewHelper.setX(this.f6632a, ((Integer) valueAnimator.getAnimatedValue()).intValue());
            } catch (Exception unused) {
                ViewHelper.setX(this.f6632a, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
            this.f6632a.requestLayout();
        }
    }

    public class c implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6633a;
        public final /* synthetic */ io.dcloud.feature.ui.c b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6634e;

        public c(View view, io.dcloud.feature.ui.c cVar, IWebview iWebview, String str, String str2) {
            this.f6633a = view;
            this.b = cVar;
            this.c = iWebview;
            this.d = str;
            this.f6634e = str2;
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            io.dcloud.feature.ui.c cVar;
            if (this.f6633a == null || (cVar = this.b) == null || cVar.r() == null) {
                return;
            }
            int iA = e.this.a(this.f6633a);
            int width = this.f6633a.getWidth();
            if (iA >= PlatformUtil.SCREEN_WIDTH(this.f6633a.getContext()) || iA <= (-width)) {
                this.b.y.popFromViewStack();
            }
            if (this.c != null && !TextUtils.isEmpty(this.d)) {
                String strM = this.b.m();
                if (TextUtils.isEmpty(strM)) {
                    strM = "";
                }
                Deprecated_JSUtil.execCallback(this.c, this.d, StringUtil.format("{\"id\":\"%s\",\"target\":%s}", strM, this.b.h()), JSUtil.OK, true, true);
            }
            if (TextUtils.isEmpty(this.f6634e)) {
                return;
            }
            if ("hide".equals(this.f6634e)) {
                io.dcloud.feature.ui.c cVar2 = this.b;
                cVar2.a(cVar2.r(), "hide", JSONUtil.createJSONArray("[null,null,null]"));
            } else if ("close".equals(this.f6634e)) {
                io.dcloud.feature.ui.c cVar3 = this.b;
                cVar3.a(cVar3.r(), "close", JSONUtil.createJSONArray("[null,null,null]"));
            }
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6635a;

        static {
            int[] iArr = new int[EnumC0178e.values().length];
            f6635a = iArr;
            try {
                iArr[EnumC0178e.findWindowByName.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6635a[EnumC0178e.getTopWebview.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6635a[EnumC0178e.prefetchURL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6635a[EnumC0178e.prefetchURLs.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6635a[EnumC0178e.enumWindow.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6635a[EnumC0178e.getWapLaunchWebview.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6635a[EnumC0178e.getLaunchWebview.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6635a[EnumC0178e.getSecondWebview.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f6635a[EnumC0178e.currentWebview.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f6635a[EnumC0178e.createView.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f6635a[EnumC0178e.setcallbackid.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f6635a[EnumC0178e.debug.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f6635a[EnumC0178e.defaultHardwareAccelerated.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f6635a[EnumC0178e.startAnimation.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f6635a[EnumC0178e.getDisplayWebview.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f6635a[EnumC0178e.__callNativeModuleSync.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f6635a[EnumC0178e.postMessageToUniNView.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* JADX INFO: renamed from: io.dcloud.feature.ui.e$e, reason: collision with other inner class name */
    public enum EnumC0178e {
        findWindowByName,
        enumWindow,
        getLaunchWebview,
        getWapLaunchWebview,
        currentWebview,
        getTopWebview,
        createView,
        setcallbackid,
        debug,
        setLogs,
        isLogs,
        defaultHardwareAccelerated,
        startAnimation,
        getSecondWebview,
        getDisplayWebview,
        updateAppFrameViews,
        prefetchURL,
        prefetchURLs,
        postMessageToUniNView,
        __callNativeModuleSync
    }

    public e(AbsMgr absMgr, String str) {
        this.f6630a = null;
        this.c = null;
        this.f6630a = absMgr;
        this.c = str;
        a();
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.startsWith("./") ? str.substring(2) : str.startsWith(ZipUtil.f3018e) ? str.substring(3) : str.startsWith(".../") ? str.substring(4) : str;
    }

    public synchronized String b(IWebview iWebview, String str, JSONArray jSONArray) {
        return a(iWebview, str, jSONArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x0364 A[Catch: Exception -> 0x0475, TryCatch #10 {Exception -> 0x0475, blocks: (B:88:0x01f5, B:118:0x02c5, B:121:0x02f8, B:133:0x0322, B:139:0x0364, B:141:0x0375, B:126:0x030a, B:128:0x0312, B:129:0x0317, B:131:0x031b, B:142:0x038b, B:143:0x0397, B:153:0x03ca, B:154:0x03cf, B:156:0x03db, B:157:0x03e6, B:158:0x0415, B:160:0x041f, B:161:0x0424, B:162:0x0449, B:164:0x044f, B:165:0x0454, B:167:0x045a, B:168:0x045f, B:170:0x0465, B:172:0x046d, B:144:0x03a1, B:146:0x03af, B:147:0x03b7, B:149:0x03bf), top: B:382:0x0158, inners: #19 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0375 A[Catch: Exception -> 0x0475, TryCatch #10 {Exception -> 0x0475, blocks: (B:88:0x01f5, B:118:0x02c5, B:121:0x02f8, B:133:0x0322, B:139:0x0364, B:141:0x0375, B:126:0x030a, B:128:0x0312, B:129:0x0317, B:131:0x031b, B:142:0x038b, B:143:0x0397, B:153:0x03ca, B:154:0x03cf, B:156:0x03db, B:157:0x03e6, B:158:0x0415, B:160:0x041f, B:161:0x0424, B:162:0x0449, B:164:0x044f, B:165:0x0454, B:167:0x045a, B:168:0x045f, B:170:0x0465, B:172:0x046d, B:144:0x03a1, B:146:0x03af, B:147:0x03b7, B:149:0x03bf), top: B:382:0x0158, inners: #19 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010d A[Catch: Exception -> 0x009c, TRY_ENTER, TRY_LEAVE, TryCatch #16 {Exception -> 0x009c, blocks: (B:9:0x003f, B:12:0x0049, B:14:0x0054, B:16:0x0058, B:64:0x0147, B:68:0x0158, B:70:0x015d, B:72:0x016c, B:80:0x0187, B:83:0x0193, B:84:0x01c2, B:73:0x0175, B:75:0x017c, B:77:0x0180, B:85:0x01ee, B:90:0x0220, B:92:0x0226, B:97:0x0236, B:100:0x0261, B:111:0x0288, B:104:0x0270, B:106:0x0278, B:107:0x027d, B:109:0x0281, B:177:0x047a, B:179:0x0499, B:180:0x049e, B:182:0x04aa, B:192:0x04c3, B:194:0x04cc, B:195:0x04d9, B:197:0x04e0, B:198:0x04e5, B:205:0x051e, B:207:0x0526, B:209:0x0537, B:212:0x0542, B:214:0x0548, B:215:0x0583, B:217:0x05e4, B:218:0x05e8, B:17:0x0063, B:19:0x006e, B:21:0x0072, B:22:0x007d, B:24:0x0088, B:26:0x008c, B:33:0x00a7, B:35:0x00b0, B:50:0x010d, B:56:0x0131), top: B:394:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0119 A[Catch: Exception -> 0x08fd, TRY_ENTER, TRY_LEAVE, TryCatch #14 {Exception -> 0x08fd, blocks: (B:61:0x013d, B:190:0x04bd, B:203:0x0518, B:36:0x00b5, B:40:0x00de, B:44:0x00ee, B:48:0x00fe, B:52:0x0119), top: B:390:0x00b5 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0147 A[Catch: Exception -> 0x009c, TRY_ENTER, TryCatch #16 {Exception -> 0x009c, blocks: (B:9:0x003f, B:12:0x0049, B:14:0x0054, B:16:0x0058, B:64:0x0147, B:68:0x0158, B:70:0x015d, B:72:0x016c, B:80:0x0187, B:83:0x0193, B:84:0x01c2, B:73:0x0175, B:75:0x017c, B:77:0x0180, B:85:0x01ee, B:90:0x0220, B:92:0x0226, B:97:0x0236, B:100:0x0261, B:111:0x0288, B:104:0x0270, B:106:0x0278, B:107:0x027d, B:109:0x0281, B:177:0x047a, B:179:0x0499, B:180:0x049e, B:182:0x04aa, B:192:0x04c3, B:194:0x04cc, B:195:0x04d9, B:197:0x04e0, B:198:0x04e5, B:205:0x051e, B:207:0x0526, B:209:0x0537, B:212:0x0542, B:214:0x0548, B:215:0x0583, B:217:0x05e4, B:218:0x05e8, B:17:0x0063, B:19:0x006e, B:21:0x0072, B:22:0x007d, B:24:0x0088, B:26:0x008c, B:33:0x00a7, B:35:0x00b0, B:50:0x010d, B:56:0x0131), top: B:394:0x003f }] */
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
    public java.lang.String a(io.dcloud.common.DHInterface.IWebview r29, java.lang.String r30, org.json.JSONArray r31) {
        /*
            Method dump skipped, instruction units count: 2384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.e.a(io.dcloud.common.DHInterface.IWebview, java.lang.String, org.json.JSONArray):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(java.lang.String r13, io.dcloud.feature.ui.a r14, io.dcloud.common.DHInterface.IFrameView r15) {
        /*
            r12 = this;
            int r0 = r15.hashCode()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            io.dcloud.common.DHInterface.IWebview r1 = r15.obtainWebView()
            java.lang.String r3 = r1.obtainUrl()
            r7 = 0
            if (r15 == 0) goto L24
            r2 = r15
            io.dcloud.common.adapter.ui.AdaFrameView r2 = (io.dcloud.common.adapter.ui.AdaFrameView) r2
            io.dcloud.common.adapter.util.ViewOptions r4 = r2.obtainFrameOptions()
            if (r4 == 0) goto L24
            io.dcloud.common.adapter.util.ViewOptions r2 = r2.obtainFrameOptions()
            org.json.JSONObject r2 = r2.mJsonViewOption
            r8 = r2
            goto L25
        L24:
            r8 = r7
        L25:
            java.lang.String r1 = r1.obtainFrameId()
            boolean r2 = io.dcloud.common.util.PdrUtil.isEmpty(r1)
            r9 = 2
            if (r2 != 0) goto L32
            r10 = r1
            goto L3b
        L32:
            int r1 = r15.getFrameType()
            if (r1 != r9) goto L3a
            r10 = r13
            goto L3b
        L3a:
            r10 = r3
        L3b:
            io.dcloud.feature.ui.c r11 = new io.dcloud.feature.ui.c
            r1 = r11
            r2 = r14
            r4 = r10
            r5 = r0
            r6 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            android.content.Context r2 = r15.getContext()
            io.dcloud.common.DHInterface.IWebview r4 = r15.obtainWebView()
            r3 = r14
            r1.a(r2, r3, r4, r5, r6)
            int r0 = r15.getFrameType()
            r1 = 0
            r2 = 1
            if (r0 != r9) goto L69
            r0 = r15
            io.dcloud.common.adapter.ui.AdaFrameView r0 = (io.dcloud.common.adapter.ui.AdaFrameView) r0
            android.view.View r0 = r0.obtainMainView()
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L67
            goto L69
        L67:
            r0 = 0
            goto L6a
        L69:
            r0 = 1
        L6a:
            r11.F = r0
            r11.H = r2
            r0 = r15
            io.dcloud.common.adapter.ui.AdaFrameView r0 = (io.dcloud.common.adapter.ui.AdaFrameView) r0
            r0.addFrameViewListener(r11)
            r11.a(r15, r10)
            r14.e(r11)
            r14.a(r13, r11, r1)
            io.dcloud.feature.ui.e$a r13 = new io.dcloud.feature.ui.e$a
            r13.<init>(r12, r14, r11)
            io.dcloud.common.adapter.util.MessageHandler.sendMessage(r13, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.e.b(java.lang.String, io.dcloud.feature.ui.a, io.dcloud.common.DHInterface.IFrameView):void");
    }

    public void b(String str) {
        if (PdrUtil.isEmpty(str)) {
            Iterator<io.dcloud.feature.ui.a> it = this.b.values().iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.b.clear();
            return;
        }
        io.dcloud.feature.ui.a aVar = this.b.get(str);
        if (aVar != null) {
            Logger.d(Logger.MAIN_TAG, "UIWidgetMgr.dispose pAppid=" + str);
            aVar.a();
        }
        this.b.remove(str);
    }

    private void a(String str, io.dcloud.feature.ui.a aVar, IFrameView iFrameView) {
        IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
        String strValueOf = String.valueOf(iWebviewObtainWebView.obtainFrameId());
        String strObtainUrl = iWebviewObtainWebView.obtainUrl();
        String strObtainFrameId = iWebviewObtainWebView.obtainFrameId();
        String str2 = !PdrUtil.isEmpty(strObtainFrameId) ? strObtainFrameId : strObtainUrl;
        io.dcloud.feature.ui.c cVar = new io.dcloud.feature.ui.c(aVar, strObtainUrl, str2, strValueOf, null);
        cVar.a(iFrameView.getContext(), aVar, iWebviewObtainWebView, strValueOf, null);
        cVar.F = false;
        cVar.H = false;
        cVar.a(true);
        iFrameView.addFrameViewListener(cVar);
        cVar.a(iFrameView, str2);
        aVar.e(cVar);
        aVar.a(str, cVar, 0);
    }

    private io.dcloud.feature.ui.c a(io.dcloud.feature.ui.a aVar, IWebview iWebview, JSONArray jSONArray, IApp iApp, String str, boolean z) throws Exception {
        String str2;
        JSONObject jSONObject;
        io.dcloud.feature.ui.c cVarA = aVar.a(iWebview.obtainFrameView());
        String strOptString = jSONArray.optString(0);
        Log.e("UIWidgetMgr", "new -- JSNWindow=" + strOptString);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
        String strOptString2 = jSONArray.optString(2);
        JSONObject jSONObjectOptJSONObject2 = jSONArray.optJSONObject(4);
        JSONArray jSONArrayOptJSONArray = jSONArray.optJSONArray(5);
        if (jSONObjectOptJSONObject == null) {
            jSONObject = new JSONObject("{}");
            str2 = "";
        } else {
            String string = JSONUtil.getString(jSONObjectOptJSONObject, "name");
            if (TextUtils.isEmpty(string)) {
                string = JSONUtil.getString(jSONObjectOptJSONObject, "webviewid");
            }
            str2 = string;
            jSONObject = jSONObjectOptJSONObject;
        }
        io.dcloud.feature.ui.c cVarA2 = a(aVar, iWebview, iApp, strOptString, str2, str, jSONObject, jSONObjectOptJSONObject2, jSONArrayOptJSONArray, z);
        if (cVarA != null) {
            cVarA.b(cVarA2);
        }
        if (strOptString2 != null) {
            cVarA2.b.put(iWebview.getWebviewANID(), strOptString2);
        }
        AnimOptions animOptions = ((AdaFrameItem) cVarA2.y).getAnimOptions();
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) cVarA2.y).obtainFrameOptions();
        cVarA2.J = viewOptionsObtainFrameOptions.hasBackground();
        animOptions.parseTransition(viewOptionsObtainFrameOptions.transition);
        animOptions.parseTransform(viewOptionsObtainFrameOptions.transform);
        return cVarA2;
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
    private io.dcloud.feature.ui.c a(io.dcloud.feature.ui.a aVar, IWebview iWebview, IApp iApp, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, JSONArray jSONArray, boolean z) {
        String str4;
        io.dcloud.feature.ui.c cVarA;
        String str5;
        String str6;
        String str7;
        int i;
        int i2;
        String strOptString;
        String strConvert2LocalFullPath;
        String str8;
        String str9;
        Logger.e("createNWindow pUrl=" + str);
        boolean zOptBoolean = jSONObject.optBoolean("directPage", false);
        int iOptInt = jSONObject.optInt("winType", 0);
        String strConvert2WebviewFullPath = iApp.convert2WebviewFullPath(iOptInt == 0 ? iWebview.obtainFullUrl() : null, str);
        int i3 = zOptBoolean ? 5 : iOptInt;
        if (z) {
            str4 = null;
        } else {
            str4 = strConvert2WebviewFullPath;
            strConvert2WebviewFullPath = null;
        }
        iApp.obtainWebviewBaseUrl();
        a(iWebview, iApp, str4);
        String strObtainAppId = iApp.obtainAppId();
        boolean z2 = !PdrUtil.isEmpty(str);
        if (i3 == 4) {
            cVarA = aVar.a(4);
        } else {
            cVarA = i3 == 5 ? aVar.a(5) : null;
        }
        if (cVarA == null) {
            str5 = strObtainAppId;
            str6 = strConvert2WebviewFullPath;
            str7 = str4;
            i = i3;
            i2 = 5;
            cVarA = new io.dcloud.feature.ui.c(aVar, str4, str2, str3, jSONObject);
        } else {
            str5 = strObtainAppId;
            str6 = strConvert2WebviewFullPath;
            str7 = str4;
            i = i3;
            i2 = 5;
        }
        io.dcloud.feature.ui.c cVar = cVarA;
        cVar.x = jSONObject2;
        AbsMgr absMgr = this.f6630a;
        IMgr.MgrType mgrType = IMgr.MgrType.WindowMgr;
        Object[] objArr = new Object[i2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = iApp;
        objArr[2] = new Object[]{str, jSONObject, str3};
        objArr[3] = iWebview.obtainFrameView();
        objArr[4] = cVar;
        IFrameView iFrameView = (IFrameView) absMgr.processEvent(mgrType, 3, objArr);
        if (z) {
            iFrameView.obtainWebView().setOriginalUrl(str6);
        }
        if (jSONArray != null) {
            cVar.v = jSONArray;
            cVar.w = iWebview;
        }
        cVar.a(iFrameView, str2);
        IWebview iWebviewObtainWebView = cVar.y.obtainWebView();
        if (jSONObject.has("plusrequire")) {
            iWebviewObtainWebView.setWebviewProperty("plusrequire", jSONObject.optString("plusrequire"));
        }
        if (jSONObject.has("replacewebapi")) {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("replacewebapi");
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("geolocation")) {
                iWebviewObtainWebView.setWebviewProperty("geolocation", jSONObjectOptJSONObject.optString("geolocation"));
            }
        } else if (jSONObject.has("geolocation")) {
            iWebviewObtainWebView.setWebviewProperty("geolocation", jSONObject.optString("geolocation"));
        }
        if (jSONObject.has("injection")) {
            iWebviewObtainWebView.setWebviewProperty("injection", String.valueOf(jSONObject.optBoolean("injection")));
        }
        if (jSONObject.has(IApp.ConfigProperty.CONFIG_OVERRIDE_RESOURCE)) {
            iWebviewObtainWebView.setOverrideResourceRequest(jSONObject.optJSONArray(IApp.ConfigProperty.CONFIG_OVERRIDE_RESOURCE));
        }
        if (jSONObject.has(IApp.ConfigProperty.CONFIG_OVERRIDEURL)) {
            iWebviewObtainWebView.setOverrideUrlLoadingData(jSONObject.optJSONObject(IApp.ConfigProperty.CONFIG_OVERRIDEURL));
        }
        if (BaseInfo.isWap2AppAppid(str5) && (iWebviewObtainWebView.obtainFrameView().getFrameType() == 4 || iWebviewObtainWebView.obtainFrameView().getFrameType() == 5)) {
            if (iWebviewObtainWebView.getWebviewProperty("plusrequire").equals("none")) {
                str9 = null;
            } else {
                str9 = null;
                iWebviewObtainWebView.appendPreloadJsFile(iApp.convert2AbsFullPath(null, "_www/__wap2app.js"));
                iWebviewObtainWebView.appendPreloadJsFile(iApp.convert2AbsFullPath(null, "_www/__wap2appconfig.js"));
            }
            iWebviewObtainWebView.setPreloadJsFile(iApp.convert2AbsFullPath(iWebview.obtainFullUrl(), "_www/server_index_append.js"), true);
            String strConvert2AbsFullPath = iApp.convert2AbsFullPath(str9, "_www/server_index_append.css");
            if (new File(strConvert2AbsFullPath).exists()) {
                iWebviewObtainWebView.setCssFile(strConvert2AbsFullPath, str9);
            } else {
                String strConvert2AbsFullPath2 = iApp.convert2AbsFullPath(str9, "_www/__wap2app.css");
                if (new File(strConvert2AbsFullPath2).exists()) {
                    iWebviewObtainWebView.setCssFile(strConvert2AbsFullPath2, str9);
                }
            }
        }
        if (jSONObject.has("appendCss")) {
            strOptString = jSONObject.optString("appendCss");
        } else {
            strOptString = jSONObject.has("preloadcss") ? jSONObject.optString("preloadcss") : null;
        }
        if (TextUtils.isEmpty(strOptString)) {
            strConvert2LocalFullPath = null;
        } else {
            strConvert2LocalFullPath = null;
            iWebviewObtainWebView.setCssFile(null, strOptString);
        }
        if (jSONObject.has("appendJs")) {
            strConvert2LocalFullPath = iApp.convert2LocalFullPath(strConvert2LocalFullPath, jSONObject.optString("appendJs"));
        } else if (jSONObject.has("preloadjs")) {
            strConvert2LocalFullPath = iApp.convert2LocalFullPath(strConvert2LocalFullPath, jSONObject.optString("preloadjs"));
        }
        if (!TextUtils.isEmpty(strConvert2LocalFullPath)) {
            iWebviewObtainWebView.appendPreloadJsFile(strConvert2LocalFullPath);
        }
        if (z2) {
            if (i == 6 && jSONObject.has(IApp.ConfigProperty.CONFIG_ADDITIONAL_HTTPHEADERS)) {
                str8 = str7;
                cVar.y.obtainWebView().setLoadURLHeads(str8, JSONUtil.toMap(jSONObject.optJSONObject(IApp.ConfigProperty.CONFIG_ADDITIONAL_HTTPHEADERS)));
            } else {
                str8 = str7;
            }
            cVar.y.obtainWebView().loadUrl(str8);
        }
        cVar.a(iWebview.getContext(), aVar, iFrameView.obtainWebView(), str3, jSONObject);
        iFrameView.obtainMainView().setVisibility(4);
        if (DeviceInfo.sDeviceSdkVer >= 11) {
            aVar.b(iFrameView);
        }
        aVar.e(cVar);
        cVar.a(jSONObject, false);
        Logger.d(Logger.VIEW_VISIBLE_TAG, str5 + " createNWindow webview_name=" + str2);
        return cVar;
    }

    private void a(IWebview iWebview, IApp iApp, String str) {
        if (!BaseInfo.isBase(iWebview.getContext()) || TextUtils.isEmpty(str)) {
            return;
        }
        String strObtainUrl = iWebview.obtainUrl();
        if (str.startsWith(DeviceInfo.HTTP_PROTOCOL) || strObtainUrl.startsWith(DeviceInfo.HTTP_PROTOCOL) || str.startsWith(DeviceInfo.HTTPS_PROTOCOL) || strObtainUrl.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            return;
        }
        Log.i(AbsoluteConst.HBUILDER_TAG, StringUtil.format(AbsoluteConst.OPENLOG, c(WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(WebResUtil.getOriginalUrl(strObtainUrl)))), c(WebResUtil.getHBuilderPrintUrl(iApp.convert2RelPath(WebResUtil.getOriginalUrl(str))))));
    }

    public static io.dcloud.feature.ui.b a(String str) {
        if (!PdrUtil.isEmpty(str)) {
            try {
                Object objNewInstance = Class.forName(d.get(str.toLowerCase())).newInstance();
                if (objNewInstance instanceof io.dcloud.feature.ui.b) {
                    return (io.dcloud.feature.ui.b) objNewInstance;
                }
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (InstantiationException e4) {
                e4.printStackTrace();
            }
        }
        return null;
    }

    private void a() {
        d = (HashMap) this.f6630a.processEvent(IMgr.MgrType.FeatureMgr, 4, this.c);
    }

    private ValueAnimator a(View view, int i, int i2, String str, IWebview iWebview, String str2, io.dcloud.feature.ui.c cVar) {
        ValueAnimator valueAnimatorOfFloat;
        if (view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) {
            valueAnimatorOfFloat = ValueAnimator.ofInt(i, i2);
        } else {
            valueAnimatorOfFloat = view.getLayoutParams() instanceof FrameLayout.LayoutParams ? ValueAnimator.ofFloat(i, i2) : null;
        }
        valueAnimatorOfFloat.setDuration(200L);
        valueAnimatorOfFloat.addUpdateListener(new b(this, view));
        valueAnimatorOfFloat.addListener(new c(view, cVar, iWebview, str2, str));
        return valueAnimatorOfFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(View view) {
        if (view == null) {
            return 0;
        }
        if (!(view.getLayoutParams() instanceof AbsoluteLayout.LayoutParams) && !(view.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return 0;
        }
        float x = ViewHelper.getX(view);
        return (int) x;
    }
}
