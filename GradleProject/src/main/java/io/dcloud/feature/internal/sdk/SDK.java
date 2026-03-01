package io.dcloud.feature.internal.sdk;

import io.dcloud.base.BuildConfig;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.util.PdrUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class SDK {
    public static final String ANDROID_ASSET = "file:///android_asset/";
    public static final String DEFAULT_APPID = "Default_Appid";
    public static final String UNIMP_CAPSULE_BUTTON_CLICK = "unimp_capsule_button_click";
    public static final String UNIMP_ERROR_KEY = "UniMP_Error";
    public static final String UNIMP_EVENT_CALLBACKID = "callbackId";
    public static final String UNIMP_EVENT_CALL_INSTANCEID = "instanceId";
    public static final String UNIMP_JS_TO_NATIVE = "unimp_js_to_native";
    public static final String UNIMP_OPEN = "open_unimp";
    public static final int UNI_CODE_ERROR_APPID = -1003;
    public static final int UNI_CODE_ERROR_NOT_RES = -1001;
    public static final int UNI_CODE_ERROR_NO_V3 = -1002;
    public static boolean isCapsule = false;
    public static boolean isEnableBackground = false;
    public static boolean isUniMPSdk = false;
    public static String mHostInfo;
    public static ICore sCore;
    public static String sDefaultMenuButton;

    public enum IntegratedMode {
        WEBVIEW,
        WEBAPP,
        RUNTIME
    }

    public static void closeWebView(IWebview iWebview) {
        if (isUniMPSDK()) {
            return;
        }
        ((AdaFrameItem) iWebview.obtainFrameView()).getAnimOptions().mOption = (byte) 1;
        sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 2, iWebview.obtainFrameView());
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
    public static IWebview createWebView(IApp iApp, String str, JSONObject jSONObject, IFrameView iFrameView, IEventCallback iEventCallback) {
        if (isUniMPSDK()) {
            return null;
        }
        IWebview iWebviewObtainWebView = ((IFrameView) sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 3, new Object[]{0, iApp, new Object[]{str, jSONObject}, iFrameView, iEventCallback})).obtainWebView();
        iWebviewObtainWebView.loadUrl(str);
        return iWebviewObtainWebView;
    }

    public static String getUniVersion() {
        return BuildConfig.uv;
    }

    public static void initSDK(ICore iCore) {
        sCore = iCore;
    }

    public static boolean isUniMPSDK() {
        if (PdrUtil.isUniMPHostForUniApp()) {
            return isUniMPSdk;
        }
        return true;
    }

    public static ArrayList<IWebview> obtainAllIWebview(String str) {
        ArrayList arrayList = (ArrayList) sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 6, str);
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        ArrayList<IWebview> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((IFrameView) it.next()).obtainWebView());
        }
        return arrayList2;
    }

    public static IApp obtainCurrentApp() {
        if (isUniMPSDK()) {
            return null;
        }
        return (IApp) sCore.dispatchEvent(IMgr.MgrType.AppMgr, 6, obtainCurrentRunnbingAppId());
    }

    public static String obtainCurrentRunnbingAppId() {
        if (isUniMPSDK()) {
            return null;
        }
        return String.valueOf(sCore.dispatchEvent(IMgr.MgrType.AppMgr, 11, null));
    }

    public static IWebview obtainWebview(String str, String str2) {
        for (IWebview iWebview : obtainAllIWebview(str)) {
            if (PdrUtil.isEquals(str2, iWebview.getWebviewUUID())) {
                return iWebview;
            }
        }
        return null;
    }

    public static void popWebView(IWebview iWebview) {
        if (isUniMPSDK()) {
            return;
        }
        ((AdaFrameItem) iWebview.obtainFrameView()).getAnimOptions().mOption = (byte) 1;
        sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 21, iWebview.obtainFrameView());
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
    public static void registerJsApi(String str, String str2, String str3) {
        if (isUniMPSDK()) {
            return;
        }
        sCore.dispatchEvent(IMgr.MgrType.FeatureMgr, 5, new String[]{str, str2, str3});
    }

    public static void requestAllFeature() {
        if (isUniMPSDK()) {
            return;
        }
        sCore.dispatchEvent(IMgr.MgrType.FeatureMgr, 7, null);
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
    public static void requestFeature(String str, String str2, boolean z) {
        if (isUniMPSDK()) {
            return;
        }
        sCore.dispatchEvent(IMgr.MgrType.FeatureMgr, 6, new String[]{str, str2, String.valueOf(z)});
    }

    public static ArrayList<IWebview> obtainAllIWebview() {
        if (isUniMPSDK()) {
            return null;
        }
        return obtainAllIWebview(obtainCurrentRunnbingAppId());
    }
}
