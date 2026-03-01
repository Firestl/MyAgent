package io.dcloud.common.adapter.ui.webview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.IDCloudWebviewClientListener;
import io.dcloud.common.DHInterface.IWebViewFactory;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.g.a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class WebViewFactory {
    public static ICallBack otherCallBack;
    public static IWebViewFactory sOtherWebViewFactory;
    public static ArrayList<PerWrapper> sUsePermissionWebviews = new ArrayList<>();
    public static PermissionUtil.StreamPermissionRequest sStreamPermissionRequest = null;
    public static boolean isOther = false;
    public static boolean isSysWebViewCreate = false;
    public static boolean isOtherInitSuccess = false;
    public static boolean isOtherInitialised = false;
    public static boolean isLoadOtherTimeOut = false;
    public static Runnable otherDelyedRunnable = new Runnable() { // from class: io.dcloud.common.adapter.ui.webview.WebViewFactory.1
        @Override // java.lang.Runnable
        public void run() {
            boolean unused = WebViewFactory.isLoadOtherTimeOut = true;
            if (WebViewFactory.otherCallBack != null) {
                WebViewFactory.otherCallBack.onCallBack(1, null);
                ICallBack unused2 = WebViewFactory.otherCallBack = null;
            }
        }
    };
    public static boolean isAllowFileAccessFromFileURLs = true;

    public static String getDefWebViewUA(Context context) {
        String defWebViewUA = getOtherWebViewFactory() != null ? getOtherWebViewFactory().getDefWebViewUA(context) : "";
        if (!PdrUtil.isEmpty(defWebViewUA)) {
            return defWebViewUA;
        }
        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        settings.setSavePassword(false);
        String userAgentString = settings.getUserAgentString();
        webView.destroy();
        return userAgentString;
    }

    public static IWebViewFactory getOtherWebViewFactory() {
        IWebViewFactory iWebViewFactory;
        if (!isIsOtherInitSuccess() || (iWebViewFactory = sOtherWebViewFactory) == null || isSysWebViewCreate) {
            return null;
        }
        return iWebViewFactory;
    }

    public static DCWebView getWebView(Activity activity, AdaWebview adaWebview) {
        DCWebView webView = getOtherWebViewFactory() != null ? getOtherWebViewFactory().getWebView(activity, adaWebview) : null;
        if (webView != null) {
            return webView;
        }
        isSysWebViewCreate = true;
        return new SysWebView(activity, adaWebview);
    }

    public static void initOther(boolean z) {
        isOther = z;
        MessageHandler.postDelayed(otherDelyedRunnable, 3000L);
    }

    public static boolean isIsLoadOtherTimeOut() {
        return isLoadOtherTimeOut;
    }

    public static boolean isIsOtherInitSuccess() {
        return isOtherInitSuccess;
    }

    public static boolean isOther() {
        return isOther;
    }

    public static boolean isOtherInitialised() {
        return isOtherInitialised;
    }

    public static void resetSysWebViewState() {
        isSysWebViewCreate = false;
    }

    public static void setFileAccess(Object obj, IApp iApp, boolean z) {
        if (obj == null || DeviceInfo.sDeviceSdkVer <= 16) {
            return;
        }
        try {
            Class[] clsArr = {Boolean.TYPE};
            Object[] objArr = {Boolean.valueOf(z)};
            String strDecodeString = iApp != null ? iApp.getConfusionMgr().decodeString("eW9+S2ZmZX1fZGN8b3h5a2ZLaWlveXlMeGVnTGNmb19YRnkqNmEzZDg4ZmEtNGJhMC00NzlmLTk0MjItZTVhYWJlMTU4OTdiNzQ=", true, 10) : a.a("eW9+S2ZmZX1fZGN8b3h5a2ZLaWlveXlMeGVnTGNmb19YRnkqNmEzZDg4ZmEtNGJhMC00NzlmLTk0MjItZTVhYWJlMTU4OTdiNzQ=", true, 10);
            String strDecodeString2 = iApp != null ? iApp.getConfusionMgr().decodeString("eG5/SmdnZHxNYmduSmhobnh4TXlkZk1iZ25eWUd4KjZhM2Q4OGZhLTRiYTAtNDc5Zi05NDIyLWU1YWFiZTE1ODk3Yjc1", true, 11) : a.a("eG5/SmdnZHxNYmduSmhobnh4TXlkZk1iZ25eWUd4KjZhM2Q4OGZhLTRiYTAtNDc5Zi05NDIyLWU1YWFiZTE1ODk3Yjc1", true, 11);
            String strDecodeString3 = iApp != null ? iApp.getConfusionMgr().decodeString("f2l4TWBgY3tKZWBpTW9vaX9/KjZhM2Q4OGZhLTRiYTAtNDc5Zi05NDIyLWU1YWFiZTE1ODk3Yjc2", true, 12) : a.a("f2l4TWBgY3tKZWBpTW9vaX9/KjZhM2Q4OGZhLTRiYTAtNDc5Zi05NDIyLWU1YWFiZTE1ODk3Yjc2", true, 12);
            if (isAllowFileAccessFromFileURLs || !z) {
                PlatformUtil.invokeMethod(obj, strDecodeString, clsArr, objArr);
                PlatformUtil.invokeMethod(obj, strDecodeString2, clsArr, objArr);
            }
            PlatformUtil.invokeMethod(obj, strDecodeString3, clsArr, objArr);
        } catch (Exception unused) {
        }
    }

    public static void setOtherCallBack(ICallBack iCallBack) {
        isLoadOtherTimeOut = false;
        otherCallBack = iCallBack;
    }

    public static void setOtherState(boolean z, IWebViewFactory iWebViewFactory) {
        isOtherInitSuccess = z;
        isOtherInitialised = true;
        if (z) {
            sOtherWebViewFactory = iWebViewFactory;
        }
        if (otherCallBack != null) {
            MessageHandler.removeCallbacks(otherDelyedRunnable);
            otherCallBack.onCallBack(1, null);
        }
    }

    public static void setSslHandlerState(Handler handler, int i) {
        PlatformUtil.invokeMethod(handler, i != 1 ? i != 2 ? "" : WXPickersModule.CANCEL : "proceed", new Class[0], new Object[0]);
    }

    public static DCWebView getWebView(Activity activity, AdaWebview adaWebview, IDCloudWebviewClientListener iDCloudWebviewClientListener) {
        DCWebView webView = getOtherWebViewFactory() != null ? getOtherWebViewFactory().getWebView(activity, adaWebview, iDCloudWebviewClientListener) : null;
        if (webView != null) {
            return webView;
        }
        isSysWebViewCreate = true;
        return new SysWebView(activity, adaWebview, iDCloudWebviewClientListener);
    }

    public static DCWebView getWebView(Activity activity, AdaWebview adaWebview, OnPageFinishedCallack onPageFinishedCallack) {
        DCWebView webView = getOtherWebViewFactory() != null ? getOtherWebViewFactory().getWebView(activity, adaWebview, onPageFinishedCallack) : null;
        if (webView != null) {
            return webView;
        }
        isSysWebViewCreate = true;
        return new SysWebView(activity, adaWebview, onPageFinishedCallack);
    }

    public static void setFileAccess(Object obj, boolean z) {
        if (obj != null) {
            setFileAccess(obj, null, z);
        }
    }
}
