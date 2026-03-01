package io.dcloud.common.adapter.ui.webview;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.core.content.FileProvider;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IActivityDelegate;
import io.dcloud.common.DHInterface.IActivityHandler;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.ui.FileChooseDialog;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.CanvasHelper;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.AppConsoleLogUtil;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.nativeObj.photoview.subscaleview.ImageSource;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class WebJsEvent extends WebChromeClient {
    public static final FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(-1, -1, 17);
    public static final int FILECHOOSER_RESULTCODE = 1;
    public static final String TAG = "webview";
    public FileChooseDialog dialog;
    public AdaWebview mAdaWebview;
    public View mCustomView;
    public WebChromeClient.CustomViewCallback mCustomViewCallback;
    public ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadMessage21Level;
    public boolean rptJsErr;
    public boolean mScreemOrientationChanged = false;
    public boolean mDefaultTitleBarVisibility = false;
    public int mDefaultScreemOrientation = -2;
    public DialogListener mListener = null;
    public Bitmap mDefaultVideoPoster = null;
    public boolean isNeedCloseScreenWakelock = false;
    public int defaultSystemUI = 0;

    public class DialogListener implements DialogInterface.OnClickListener {
        public JsResult mResult = null;

        public DialogListener() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            this.mResult.cancel();
        }
    }

    public WebJsEvent(AdaWebview adaWebview) {
        this.mAdaWebview = null;
        this.rptJsErr = true;
        this.mAdaWebview = adaWebview;
        adaWebview.mProgressIntValue = 0;
        this.rptJsErr = BaseInfo.getCmitInfo(BaseInfo.sLastRunApp).rptJse;
    }

    private void handleConsoleMessage(ConsoleMessage consoleMessage) {
        if (this.mAdaWebview == null) {
            return;
        }
        String strMessage = consoleMessage.message();
        if (isFilterConsoleMessage(strMessage)) {
            return;
        }
        int iLineNumber = consoleMessage.lineNumber();
        String strSourceId = consoleMessage.sourceId();
        String strName = consoleMessage.messageLevel().name();
        consoleMessage.messageLevel();
        if (PdrUtil.isEmpty(strSourceId)) {
            AppConsoleLogUtil.DCLog(strMessage, strName);
            return;
        }
        try {
            strSourceId = this.mAdaWebview.getDCWebView().convertRelPath(strSourceId);
        } catch (Exception unused) {
        }
        AppConsoleLogUtil.DCLog(strMessage + " at " + strSourceId + Constants.COLON_SEPARATOR + iLineNumber, strName);
    }

    private void handleMessage(JsPromptResult jsPromptResult, AdaWebview adaWebview, String str, String str2, String str3, boolean z) {
        jsPromptResult.confirm(this.mAdaWebview.execScript(str, str2, JSONUtil.createJSONArray(str3), z));
    }

    private void initUniLoadUrl() {
        if (this.mAdaWebview.isDisposed() || this.mAdaWebview.obtainApp() == null || !BaseInfo.isUniAppAppid(this.mAdaWebview.obtainApp())) {
            return;
        }
        BaseInfo.isWeexUniJs(this.mAdaWebview.obtainApp());
    }

    private boolean isCallbackId(String str) {
        return str != null && str.startsWith(IApp.ConfigProperty.CONFIG_PLUS);
    }

    private boolean isFilterConsoleMessage(String str) {
        return !PdrUtil.isEmpty(str) && (str.contains("viewport-fit") || str.contains("Ignored attempt to cancel a touchend event with cancelable=false"));
    }

    private boolean isUrlWhiteListed(String str) {
        return true;
    }

    private void openFileChooserLogic(ValueCallback<Uri> valueCallback, String str, String str2) {
        openFileChooserLogic(valueCallback, null, str, str2, null);
    }

    private void setStatusBarVisibility(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().getDecorView().setSystemUiVisibility(this.defaultSystemUI);
        } else {
            this.defaultSystemUI = activity.getWindow().getDecorView().getSystemUiVisibility();
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOpenFileChooser(ValueCallback<Uri> valueCallback, ValueCallback<Uri[]> valueCallback2, String str, String str2, WebChromeClient.FileChooserParams fileChooserParams) {
        this.mUploadMessage = valueCallback;
        this.mUploadMessage21Level = valueCallback2;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (PdrUtil.isEmpty(str)) {
            intent.setType("*/*");
        } else {
            intent.setType(str);
        }
        if (Build.VERSION.SDK_INT >= 21 && fileChooserParams != null && fileChooserParams.getMode() == 1) {
            intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        this.dialog = new FileChooseDialog(this.mAdaWebview.getActivity(), this.mAdaWebview.getActivity(), intent);
        this.mAdaWebview.obtainFrameView().obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.10
            private Uri getUri(Uri uri) {
                Cursor cursorQuery = WebJsEvent.this.mAdaWebview.getContext().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
                if (cursorQuery != null) {
                    if (cursorQuery.moveToFirst()) {
                        try {
                            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
                            if (columnIndexOrThrow > -1) {
                                String string = cursorQuery.getString(columnIndexOrThrow);
                                uri = Uri.parse(string);
                                if (PdrUtil.isEmpty(uri.getScheme())) {
                                    uri = Uri.parse((string.startsWith("/") ? "file://" : ImageSource.FILE_SCHEME) + string);
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                    cursorQuery.close();
                }
                return uri;
            }

            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                List<File> list;
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                int iIntValue2 = ((Integer) objArr[1]).intValue();
                FileChooseDialog fileChooseDialog = WebJsEvent.this.dialog;
                if (fileChooseDialog != null) {
                    fileChooseDialog.dismiss();
                }
                IApp iAppObtainApp = WebJsEvent.this.mAdaWebview.obtainFrameView().obtainApp();
                ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onActivityResult;
                iAppObtainApp.unregisterSysEventListener(this, sysEventType2);
                if (iIntValue2 == 0 || sysEventType != sysEventType2) {
                    WebJsEvent webJsEvent = WebJsEvent.this;
                    ValueCallback<Uri[]> valueCallback3 = webJsEvent.mUploadMessage21Level;
                    if (valueCallback3 != null) {
                        valueCallback3.onReceiveValue(null);
                    } else {
                        ValueCallback<Uri> valueCallback4 = webJsEvent.mUploadMessage;
                        if (valueCallback4 != null) {
                            valueCallback4.onReceiveValue(null);
                        }
                    }
                    return false;
                }
                Intent intent2 = (Intent) objArr[2];
                ArrayList arrayList = new ArrayList();
                if (iIntValue == 1) {
                    if (intent2.getClipData() != null) {
                        ClipData clipData = intent2.getClipData();
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri uri = clipData.getItemAt(i).getUri();
                            if (uri != null && "content".equals(uri.getScheme())) {
                                arrayList.add(getUri(uri));
                            }
                        }
                    } else {
                        Uri data = intent2.getData();
                        if (data != null && "content".equals(data.getScheme())) {
                            data = getUri(data);
                        }
                        arrayList.add(data);
                        Logger.i("webview", "openFileChooserLogic  OnActivityResult url=" + data);
                    }
                } else if (iIntValue == 2 && (list = WebJsEvent.this.dialog.uris) != null) {
                    Iterator<File> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        File next = it.next();
                        if (next.exists()) {
                            arrayList.add(FileProvider.getUriForFile(WebJsEvent.this.mAdaWebview.getActivity(), WebJsEvent.this.mAdaWebview.getActivity().getPackageName() + ".dc.fileprovider", next));
                            break;
                        }
                    }
                }
                Uri[] uriArr = arrayList.size() > 0 ? (Uri[]) arrayList.toArray(new Uri[arrayList.size()]) : null;
                WebJsEvent webJsEvent2 = WebJsEvent.this;
                ValueCallback<Uri[]> valueCallback5 = webJsEvent2.mUploadMessage21Level;
                if (valueCallback5 != null) {
                    valueCallback5.onReceiveValue(uriArr);
                } else {
                    ValueCallback<Uri> valueCallback6 = webJsEvent2.mUploadMessage;
                    if (valueCallback6 != null) {
                        valueCallback6.onReceiveValue(arrayList.isEmpty() ? null : (Uri) arrayList.get(0));
                    }
                }
                return true;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
        try {
            this.dialog.show();
            this.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.11
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    try {
                        WebJsEvent webJsEvent = WebJsEvent.this;
                        ValueCallback<Uri[]> valueCallback3 = webJsEvent.mUploadMessage21Level;
                        if (valueCallback3 != null) {
                            valueCallback3.onReceiveValue(null);
                        } else {
                            ValueCallback<Uri> valueCallback4 = webJsEvent.mUploadMessage;
                            if (valueCallback4 != null) {
                                valueCallback4.onReceiveValue(null);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        } catch (Exception unused) {
            Logger.e("openFileChooserLogic Exception");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c A[Catch: JSONException -> 0x00f2, TryCatch #0 {JSONException -> 0x00f2, blocks: (B:5:0x0013, B:7:0x0019, B:13:0x002c, B:15:0x0030, B:17:0x003c, B:18:0x0042, B:20:0x004e, B:21:0x0054, B:23:0x0091, B:25:0x0097, B:27:0x009d, B:29:0x00c1, B:31:0x00c6, B:33:0x00d2, B:35:0x00d8, B:37:0x00e2, B:38:0x00e7, B:9:0x001f, B:11:0x0025), top: B:43:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004e A[Catch: JSONException -> 0x00f2, TryCatch #0 {JSONException -> 0x00f2, blocks: (B:5:0x0013, B:7:0x0019, B:13:0x002c, B:15:0x0030, B:17:0x003c, B:18:0x0042, B:20:0x004e, B:21:0x0054, B:23:0x0091, B:25:0x0097, B:27:0x009d, B:29:0x00c1, B:31:0x00c6, B:33:0x00d2, B:35:0x00d8, B:37:0x00e2, B:38:0x00e7, B:9:0x001f, B:11:0x0025), top: B:43:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateTitleNViewTitle(java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.ui.webview.WebJsEvent.updateTitleNViewTitle(java.lang.String):void");
    }

    public void destroy() {
        this.mAdaWebview = null;
    }

    @Override // android.webkit.WebChromeClient
    public Bitmap getDefaultVideoPoster() {
        return super.getDefaultVideoPoster();
    }

    public void hideCustomView() {
        Log.d("webview", "Hidding Custom View");
        if (this.mCustomView == null) {
            return;
        }
        if (this.mAdaWebview.obtainMainView() != null) {
            this.mAdaWebview.obtainMainView().setVisibility(0);
        }
        this.mCustomView.setVisibility(8);
        ((ViewGroup) this.mCustomView.getParent()).removeView(this.mCustomView);
        this.mCustomView = null;
        this.mCustomViewCallback.onCustomViewHidden();
        Activity activity = this.mAdaWebview.obtainApp() != null ? this.mAdaWebview.obtainApp().getActivity() : null;
        if (activity != null) {
            if (this.isNeedCloseScreenWakelock && this.mAdaWebview.obtainFrameView() != null && this.mAdaWebview.obtainFrameView().obtainWindowMgr() != null) {
                this.mAdaWebview.obtainFrameView().obtainWindowMgr().processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{this.mAdaWebview, ConstantHelper.LOG_DE, "setWakelock", new JSONArray().put(false)});
            }
            IApp iAppObtainApp = this.mAdaWebview.obtainApp();
            if (iAppObtainApp != null && iAppObtainApp.obtainStatusBarMgr() != null) {
                if (!iAppObtainApp.obtainStatusBarMgr().isFullScreenOrImmersive() || !iAppObtainApp.obtainStatusBarMgr().isFullScreen) {
                    setStatusBarVisibility(activity, true);
                }
                if (iAppObtainApp.obtainStatusBarMgr().isImmersive) {
                    iAppObtainApp.obtainStatusBarMgr().setImmersive(activity, true);
                }
            }
            if (this.mScreemOrientationChanged) {
                this.mScreemOrientationChanged = false;
                AdaWebview.ScreemOrientationChangedNeedLayout = true;
                activity.setRequestedOrientation(this.mDefaultScreemOrientation);
            }
            IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(activity);
            if (iActivityHandler != null) {
                iActivityHandler.setSideBarVisibility(0);
            }
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        handleConsoleMessage(consoleMessage);
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        Logger.i("webview", "onExceededDatabaseQuota url=" + str);
        quotaUpdater.updateQuota(j2 * 2);
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsHidePrompt() {
        Logger.i("webview", "onGeolocationPermissionsHidePrompt");
        super.onGeolocationPermissionsHidePrompt();
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
        if (this.mAdaWebview == null) {
            return;
        }
        Logger.i("webview", "onGeolocationPermissionsShowPrompt origin=" + str);
        IApp iAppObtainApp = this.mAdaWebview.obtainFrameView().obtainApp();
        if (iAppObtainApp != null) {
            PermissionUtil.usePermission(this.mAdaWebview.getActivity(), PermissionUtil.PMS_LOCATION, new PermissionUtil.StreamPermissionRequest(iAppObtainApp) { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.12
                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onDenied(String str2) {
                    callback.invoke(str, false, false);
                }

                @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                public void onGranted(String str2) {
                    callback.invoke(str, true, false);
                }
            });
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        hideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null || PdrUtil.isEmpty(adaWebview.getAppName())) {
            return super.onJsAlert(webView, str, str2, jsResult);
        }
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mAdaWebview.getActivity()).create();
        alertDialogCreate.setTitle(this.mAdaWebview.getAppName());
        alertDialogCreate.setMessage(str2);
        if (this.mListener == null) {
            this.mListener = new DialogListener();
        }
        this.mListener.mResult = jsResult;
        alertDialogCreate.setButton(AndroidResources.getString(R.string.ok), this.mListener);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1 || i != 4) {
                    return false;
                }
                jsResult.cancel();
                alertDialogCreate.dismiss();
                return true;
            }
        });
        alertDialogCreate.show();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsBeforeUnload(webView, str, str2, jsResult);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null || PdrUtil.isEmpty(adaWebview.getAppName())) {
            return super.onJsConfirm(webView, str, str2, jsResult);
        }
        try {
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mAdaWebview.getActivity()).create();
            alertDialogCreate.setMessage(str2);
            alertDialogCreate.setTitle(this.mAdaWebview.getAppName());
            alertDialogCreate.setButton(AndroidResources.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            alertDialogCreate.setButton2(AndroidResources.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            });
            alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.7
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    jsResult.cancel();
                }
            });
            alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.8
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || i != 4) {
                        return false;
                    }
                    jsResult.cancel();
                    alertDialogCreate.dismiss();
                    return true;
                }
            });
            alertDialogCreate.show();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return super.onJsConfirm(webView, str, str2, jsResult);
        }
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
        CharSequence charSequence;
        String str__js__call__native__;
        CharSequence charSequence2;
        CharSequence charSequence3;
        String str4;
        int i;
        String string;
        String string2;
        boolean z;
        AdaWebview adaWebview;
        if (this.mAdaWebview == null) {
            return false;
        }
        boolean zIsUrlWhiteListed = isUrlWhiteListed(str);
        if (zIsUrlWhiteListed && str3 != null && str3.length() > 3 && str3.substring(0, 4).equals("pdr:")) {
            try {
                JSONArray jSONArray = new JSONArray(str3.substring(4));
                string = jSONArray.getString(0);
                string2 = jSONArray.getString(1);
                z = jSONArray.getBoolean(2);
                adaWebview = this.mAdaWebview;
                charSequence2 = "\\\"";
                charSequence3 = JSUtil.QUOTE;
                i = 4;
                str4 = "pdr:";
            } catch (JSONException e2) {
                e = e2;
                charSequence2 = "\\\"";
                charSequence3 = JSUtil.QUOTE;
                str4 = "pdr:";
                i = 4;
            }
            try {
                handleMessage(jsPromptResult, adaWebview, string, string2, str2, z);
                return true;
            } catch (JSONException e3) {
                e = e3;
                if (PdrUtil.isEquals(str3, str3.replace(charSequence2, charSequence3))) {
                    e.printStackTrace();
                    Logger.e("webview", "onJsPrompt js->native message=" + str2 + ";defaultValue=" + str3);
                    return true;
                }
                String strReplace = str2.replace(charSequence2, charSequence3);
                String strSubstring = strReplace.substring(1, strReplace.length() - 1);
                String strSubstring2 = str3.replace(charSequence2, charSequence3).substring(i);
                onJsPrompt(webView, str, strSubstring, str4 + strSubstring2.substring(1, strSubstring2.length() - 1), jsPromptResult);
                return true;
            }
        }
        if (this.mAdaWebview.mReceiveJSValue_android42 == null || !zIsUrlWhiteListed || str3 == null || str3.length() <= 5 || !str3.substring(0, 5).equals("sync:")) {
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mAdaWebview.getActivity()).create();
            alertDialogCreate.setMessage(str2);
            alertDialogCreate.setTitle(this.mAdaWebview.getAppName());
            final EditText editText = new EditText(this.mAdaWebview.getActivity());
            if (str3 != null) {
                editText.setText(str3);
                editText.setSelection(0, str3.length());
                DeviceInfo.showIME(editText);
            }
            alertDialogCreate.setView(editText);
            alertDialogCreate.setButton(AndroidResources.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    jsPromptResult.confirm(editText.getText().toString());
                }
            });
            alertDialogCreate.setButton2(AndroidResources.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    jsPromptResult.cancel();
                }
            });
            alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    if (AndroidResources.sIMEAlive || keyEvent.getAction() != 0 || i2 != 4) {
                        return false;
                    }
                    alertDialogCreate.dismiss();
                    jsPromptResult.cancel();
                    return true;
                }
            });
            alertDialogCreate.show();
            return true;
        }
        try {
            JSONArray jSONArray2 = new JSONArray(str3.substring(5));
            str__js__call__native__ = this.mAdaWebview.mReceiveJSValue_android42.__js__call__native__(jSONArray2.getString(0), jSONArray2.getString(1));
            charSequence = JSUtil.QUOTE;
        } catch (JSONException e4) {
            e = e4;
            charSequence = JSUtil.QUOTE;
        }
        try {
            jsPromptResult.confirm(str__js__call__native__);
            return true;
        } catch (JSONException e5) {
            e = e5;
            if (PdrUtil.isEquals(str3, str3.replace("\\\"", charSequence))) {
                e.printStackTrace();
                Logger.e("webview", "onJsPrompt js->native message=" + str2 + ";defaultValue=" + str3);
                return true;
            }
            String strReplace2 = str2.replace("\\\"", charSequence);
            String strSubstring3 = strReplace2.substring(1, strReplace2.length() - 1);
            String strSubstring4 = str3.replace("\\\"", charSequence).substring(4);
            onJsPrompt(webView, str, strSubstring3, "pdr:" + strSubstring4.substring(1, strSubstring4.length() - 1), jsPromptResult);
            return true;
        }
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(21)
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        permissionRequest.grant(permissionRequest.getResources());
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null) {
            return;
        }
        if (i < 20 && !adaWebview.unReceiveTitle) {
            adaWebview.unReceiveTitle = true;
        }
        adaWebview.mProgressIntValue = i;
        adaWebview.dispatchWebviewStateEvent(3, Integer.valueOf(i));
        this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_PROGRESS_CHANGED, Integer.valueOf(i));
        super.onProgressChanged(webView, i);
    }

    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        Logger.i("webview", "onReachedMaxAppCacheSize");
        super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null) {
            return;
        }
        adaWebview.unReceiveTitle = false;
        adaWebview.dispatchWebviewStateEvent(4, str);
        this.mAdaWebview.mFrameView.dispatchFrameViewEvents(AbsoluteConst.EVENTS_TITLE_UPDATE, str);
        this.mAdaWebview.getDCWebView().setPageTitle(str);
        Logger.i("webview", "onReceivedTitle title=" + str);
        this.mAdaWebview.mLoadCompleted = true;
        updateTitleNViewTitle(str);
        initUniLoadUrl();
        super.onReceivedTitle(webView, str);
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
        Logger.d("super.onReceivedTouchIconUrl(view, url, precomposed");
        super.onReceivedTouchIconUrl(webView, str, z);
    }

    @Override // android.webkit.WebChromeClient
    public void onRequestFocus(WebView webView) {
        Logger.i("webview", "onRequestFocus");
        super.onRequestFocus(webView);
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        showCustomView(view, customViewCallback);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        openFileChooserLogic(null, valueCallback, fileChooserParams.getAcceptTypes() != null ? fileChooserParams.getAcceptTypes()[0] : null, "", fileChooserParams);
        return true;
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooserLogic(valueCallback, null, null);
    }

    public void releaseDefaultVideoPoster() {
        Bitmap bitmap = this.mDefaultVideoPoster;
        if (bitmap != null) {
            bitmap.recycle();
            this.mDefaultVideoPoster = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        View childAt;
        Log.d("webview", "showing Custom View");
        if (this.mCustomView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        view.setBackgroundDrawable(CanvasHelper.getDrawable());
        if (DeviceInfo.sModel.equals("HUAWEI MT1-U06") || DeviceInfo.sModel.equals("SM-T310") || DeviceInfo.sModel.equals("vivo Y51A")) {
            this.mAdaWebview.obtainFrameView().obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.13
                @Override // io.dcloud.common.DHInterface.ISysEventListener
                public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                    ISysEventListener.SysEventType sysEventType2 = ISysEventListener.SysEventType.onKeyUp;
                    if (sysEventType != sysEventType2 || ((Integer) ((Object[]) obj)[0]).intValue() != 4) {
                        return false;
                    }
                    WebJsEvent.this.onHideCustomView();
                    WebJsEvent.this.mAdaWebview.obtainFrameView().obtainApp().unregisterSysEventListener(this, sysEventType2);
                    return true;
                }
            }, ISysEventListener.SysEventType.onKeyUp);
        }
        this.mCustomView = view;
        FrameLayout frameLayoutObtainActivityContentView = null;
        int requestedOrientation = 0;
        if (!(view instanceof ViewGroup) || ((ViewGroup) view).getChildCount() <= 0) {
            childAt = null;
        } else {
            childAt = ((ViewGroup) this.mCustomView).getChildAt(0);
            if (childAt instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) childAt;
                if (viewGroup.getChildCount() > 0) {
                    childAt = viewGroup.getChildAt(0);
                }
            }
        }
        if (childAt != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.setMargins(0, 0, 0, 0);
            layoutParams.gravity = 17;
            childAt.setPadding(0, 0, 0, 0);
            childAt.setLayoutParams(layoutParams);
            childAt.invalidate();
        }
        this.mCustomViewCallback = customViewCallback;
        Activity activity = this.mAdaWebview.obtainApp().getActivity();
        if (this.mAdaWebview.obtainFrameView() != null && this.mAdaWebview.obtainFrameView().obtainWindowMgr() != null) {
            AbsMgr absMgrObtainWindowMgr = this.mAdaWebview.obtainFrameView().obtainWindowMgr();
            IMgr.MgrType mgrType = IMgr.MgrType.FeatureMgr;
            Object objProcessEvent = absMgrObtainWindowMgr.processEvent(mgrType, 1, new Object[]{this.mAdaWebview, ConstantHelper.LOG_DE, "__isWakelockNative__", new JSONArray()});
            if (!(objProcessEvent instanceof String ? Boolean.valueOf(String.valueOf(objProcessEvent)).booleanValue() : false)) {
                this.isNeedCloseScreenWakelock = true;
                this.mAdaWebview.obtainFrameView().obtainWindowMgr().processEvent(mgrType, 1, new Object[]{this.mAdaWebview, ConstantHelper.LOG_DE, "setWakelock", new JSONArray().put(true)});
            }
        }
        if (activity != 0) {
            IActivityHandler iActivityHandler = DCloudAdapterUtil.getIActivityHandler(activity);
            if (iActivityHandler != null) {
                frameLayoutObtainActivityContentView = iActivityHandler.obtainActivityContentView();
                iActivityHandler.closeSideBar();
                iActivityHandler.setSideBarVisibility(8);
            } else if (activity instanceof IActivityDelegate) {
                frameLayoutObtainActivityContentView = ((IActivityDelegate) activity).obtainActivityContentView();
            }
        }
        if (frameLayoutObtainActivityContentView != null) {
            frameLayoutObtainActivityContentView.addView(view, COVER_SCREEN_GRAVITY_CENTER);
            this.mAdaWebview.obtainMainView().setVisibility(8);
            setStatusBarVisibility(activity, false);
            String webviewProperty = this.mAdaWebview.getWebviewProperty(AbsoluteConst.JSON_KEY_VIDEO_FULL_SCREEN);
            if ("landscape".equals(webviewProperty)) {
                requestedOrientation = 6;
            } else if (!"landscape-primary".equals(webviewProperty)) {
                requestedOrientation = "landscape-secondary".equals(webviewProperty) ? 8 : "portrait-primary".equals(webviewProperty) ? 1 : "portrait-secondary".equals(webviewProperty) ? 9 : activity.getRequestedOrientation();
            }
            if (activity.getRequestedOrientation() != requestedOrientation) {
                if (-2 == this.mDefaultScreemOrientation) {
                    this.mDefaultScreemOrientation = activity.getRequestedOrientation();
                }
                this.mScreemOrientationChanged = true;
                AdaWebview.ScreemOrientationChangedNeedLayout = true;
                activity.setRequestedOrientation(requestedOrientation);
            }
        }
    }

    private void openFileChooserLogic(final ValueCallback<Uri> valueCallback, final ValueCallback<Uri[]> valueCallback2, final String str, final String str2, final WebChromeClient.FileChooserParams fileChooserParams) {
        AdaWebview adaWebview = this.mAdaWebview;
        if (adaWebview == null) {
            return;
        }
        PermissionUtil.usePermission(adaWebview.getActivity(), "base", PermissionUtil.PMS_STORAGE, 2, new PermissionUtil.Request() { // from class: io.dcloud.common.adapter.ui.webview.WebJsEvent.9
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str3) {
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str3) {
                WebJsEvent.this.showOpenFileChooser(valueCallback, valueCallback2, str, str2, fileChooserParams);
            }
        });
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooserLogic(valueCallback, str, null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        openFileChooserLogic(valueCallback, str, str2);
    }
}
