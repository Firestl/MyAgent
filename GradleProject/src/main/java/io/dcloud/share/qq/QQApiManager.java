package io.dcloud.share.qq;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import io.dcloud.PdrR;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.share.qq.R;
import io.dcloud.share.IFShareApi;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class QQApiManager implements IFShareApi {
    public static final String AUTHORIZE_TEMPLATE = "{authenticated:%s,accessToken:'%s'}";
    public static final String KEY_APPID = "appid";
    public static final String PACKAGENAME = "com.tencent.mobileqq";
    public static final String TAG = "WeiXinApiManager";
    public String APPID;
    public Tencent mTencent;
    public MyIUiListener myIUiListener;
    public String QQ_SHARE_ID = "qq";
    public String QQ_SHARE_DES = Constants.SOURCE_QQ;

    public class MyIUiListener implements IUiListener {
        public String pCallbackId;
        public IWebview pWebViewImpl;

        public MyIUiListener(IWebview iWebview, String str) {
            this.pCallbackId = str;
            this.pWebViewImpl = iWebview;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            QQApiManager.this.callBackError(this.pWebViewImpl, this.pCallbackId, DOMException.toString(DOMException.MSG_USER_CANCEL), -2);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            Deprecated_JSUtil.execCallback(this.pWebViewImpl, this.pCallbackId, obj.toString(), JSUtil.OK, false, false);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            QQApiManager.this.callBackError(this.pWebViewImpl, this.pCallbackId, DOMException.toString(uiError.errorCode, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_qq_name), uiError.errorMessage, IFShareApi.mLink), -100);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
        }
    }

    private String getRealPathFromURI(Context context, Uri uri) {
        return FileUtil.getPathFromUri(context, uri);
    }

    private void setBundleImgUrl(IWebview iWebview, String str, Bundle bundle, boolean z) throws Throwable {
        String str2;
        if (PdrUtil.isNetPath(str)) {
            bundle.putString("imageUrl", str);
            return;
        }
        String strConvert2LocalFullPath = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), str);
        if (strConvert2LocalFullPath.startsWith("content:")) {
            strConvert2LocalFullPath = getRealPathFromURI(iWebview.getContext(), Uri.parse(strConvert2LocalFullPath));
        } else if (!DHFile.exists(strConvert2LocalFullPath) && z) {
            InputStream inputStreamOpenRawResource = iWebview.getActivity().getResources().openRawResource(PdrR.DRAWABLE_ICON);
            if (Build.VERSION.SDK_INT >= 29) {
                str2 = iWebview.getContext().getExternalFilesDir("Images") + "/tmp/" + System.currentTimeMillis();
            } else {
                str2 = iWebview.obtainFrameView().obtainApp().obtainAppTempPath() + System.currentTimeMillis();
            }
            try {
                DHFile.writeFile(inputStreamOpenRawResource, str2);
                inputStreamOpenRawResource.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            strConvert2LocalFullPath = str2;
        } else if (Build.VERSION.SDK_INT >= 29) {
            Context context = iWebview.getContext();
            if (FileUtil.checkPrivatePath(context, strConvert2LocalFullPath)) {
                String str3 = context.getExternalFilesDir("Images") + "/tmp/" + strConvert2LocalFullPath.substring(strConvert2LocalFullPath.lastIndexOf("/"));
                DHFile.copyFile(strConvert2LocalFullPath, str3);
                File file = new File(str3);
                if (file.exists()) {
                    strConvert2LocalFullPath = file.getPath();
                }
            }
        }
        bundle.putString("imageLocalUrl", strConvert2LocalFullPath);
    }

    @Override // io.dcloud.share.IFShareApi
    public void authorize(IWebview iWebview, String str, String str2) {
        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(str2);
        if (jSONObjectCreateJSONObject != null) {
            this.APPID = jSONObjectCreateJSONObject.optString("appid", this.APPID);
            Logger.e("WeiXinApiManager", "authorize: appId" + this.APPID);
        }
        if (PdrUtil.isEmpty(this.APPID)) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -7, DOMException.toString(DOMException.MSG_BUSINESS_PARAMETER_HAS_NOT)), JSUtil.ERROR, true, false);
        } else {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format("{authenticated:%s,accessToken:'%s'}", "true", ""), JSUtil.OK, true, false);
        }
    }

    public void callBackError(IWebview iWebview, String str, String str2, int i) {
        Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(i, str2), JSUtil.ERROR, true, false);
    }

    @Override // io.dcloud.share.IFShareApi
    public void dispose() {
        this.mTencent = null;
        this.myIUiListener = null;
    }

    @Override // io.dcloud.share.IFShareApi
    public void forbid(IWebview iWebview) {
    }

    @Override // io.dcloud.share.IFShareApi
    public String getId() {
        return this.QQ_SHARE_ID;
    }

    @Override // io.dcloud.share.IFShareApi
    public String getJsonObject(IWebview iWebview) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.QQ_SHARE_ID);
            jSONObject.put("description", this.QQ_SHARE_DES);
            jSONObject.put(AbsoluteConst.JSON_SHARE_AUTHENTICATED, false);
            jSONObject.put(AbsoluteConst.JSON_SHARE_ACCESSTOKEN, "");
            jSONObject.put(AbsoluteConst.JSON_SHARE_NATIVECLIENT, PlatformUtil.hasAppInstalled(iWebview.getContext(), "com.tencent.mobileqq"));
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // io.dcloud.share.IFShareApi
    public void initConfig() {
        this.APPID = AndroidResources.getMetaValue("QQ_APPID");
    }

    public void send(Activity activity, String str) {
        initConfig();
        if (this.mTencent == null) {
            this.mTencent = Tencent.createInstance(this.APPID, activity);
        }
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("content");
            String strOptString2 = jSONObject.optString("href");
            String strOptString3 = jSONObject.optString("thumbs");
            String strOptString4 = jSONObject.optString("title");
            if (!TextUtils.isEmpty(strOptString)) {
                bundle.putString("summary", strOptString);
            }
            bundle.putString("title", strOptString4);
            bundle.putInt("req_type", 1);
            bundle.putString("targetUrl", strOptString2);
            bundle.putString("imageLocalUrl", strOptString3);
            this.mTencent.shareToQQ(activity, bundle, new IUiListener() { // from class: io.dcloud.share.qq.QQApiManager.1
                @Override // com.tencent.tauth.IUiListener
                public void onCancel() {
                }

                @Override // com.tencent.tauth.IUiListener
                public void onComplete(Object obj) {
                }

                @Override // com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                }

                @Override // com.tencent.tauth.IUiListener
                public void onWarning(int i) {
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00be  */
    @Override // io.dcloud.share.IFShareApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void send(io.dcloud.common.DHInterface.IWebview r17, java.lang.String r18, java.lang.String r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 520
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.share.qq.QQApiManager.send(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String):void");
    }
}
