package io.dcloud.share.sina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.URLUtil;
import androidx.core.content.FileProvider;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MultiImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoSourceObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.openapi.IWBAPI;
import com.sina.weibo.sdk.openapi.WBAPIFactory;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ISysEventDispatch;
import io.dcloud.common.DHInterface.ISysEventListener;
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
import io.dcloud.common.util.NetTool;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.share_sina.R;
import io.dcloud.share.IFShareApi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class SinaWeiboApiManager implements IFShareApi {
    public static String APP_KEY = null;
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_REDIRECT_URI = "redirect_uri";
    public static final String PACKAGENAME = "com.sina.weibo";
    public static String REDIRECT_URL = null;
    public static String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog";
    public static final String SHARE_AUTHOR_ERROR = "64002";
    public static final String SHARE_CANEL_ERROR = "64001";
    public static final String SHARE_CONTENT_ERROR = "64003";
    public static final String SINAWEIBO_DES = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_plugin_des);
    public static final String SINAWEIBO_ID = "sinaweibo";
    public static final String URL_REVOKE_OAUTH = "https://api.weibo.com/oauth2/revokeoauth2";
    public Oauth2AccessToken mAccessToken;
    public Activity mActivity;
    public JSONObject mSinaWeibo;
    public IWBAPI mWBAPI;
    public String SEND_CALLBACKID = null;
    public String mInterface = "auto";
    public String tAuthorizeCallbackId = null;

    public class SelfWbAuthListener implements WbAuthListener {
        public IWebview mWebview;

        public SelfWbAuthListener(IWebview iWebview) {
            this.mWebview = null;
            this.mWebview = iWebview;
        }

        private void OnAuthorizeEnd(boolean z, int i, String str) {
            SinaWeiboApiManager sinaWeiboApiManager = SinaWeiboApiManager.this;
            String str2 = sinaWeiboApiManager.tAuthorizeCallbackId;
            if (str2 != null) {
                if (z) {
                    IWebview iWebview = this.mWebview;
                    Deprecated_JSUtil.execCallback(iWebview, str2, sinaWeiboApiManager.getJsonObject(iWebview), JSUtil.OK, true, false);
                } else {
                    Deprecated_JSUtil.execCallback(this.mWebview, SinaWeiboApiManager.this.tAuthorizeCallbackId, StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), str), JSUtil.ERROR, true, false);
                }
                SinaWeiboApiManager.this.tAuthorizeCallbackId = null;
            }
        }

        public void onCancel() {
            OnAuthorizeEnd(false, -2, DOMException.toString(DOMException.MSG_USER_CANCEL));
        }

        public void onComplete(Oauth2AccessToken oauth2AccessToken) {
            SinaWeiboApiManager.this.mAccessToken = oauth2AccessToken;
            if (SinaWeiboApiManager.this.mAccessToken.isSessionValid()) {
                OnAuthorizeEnd(true, -1, null);
            }
        }

        public void onError(UiError uiError) {
            OnAuthorizeEnd(false, -100, DOMException.toString(uiError.errorCode, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_plugin_name), uiError.errorMessage, IFShareApi.mLink));
        }
    }

    private ImageObject getImageObject(IWebview iWebview, JSONObject jSONObject) {
        Bitmap bitmapDecodeFile;
        ImageObject imageObject = new ImageObject();
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pictures");
            String string = JSONUtil.getString(jSONArrayOptJSONArray, 0);
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0 && !PdrUtil.isEmpty(jSONArrayOptJSONArray.getString(0))) {
                string = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), string);
                Bitmap bitmapDecodeStream = string.startsWith("content://") ? BitmapFactory.decodeStream(iWebview.getContext().getContentResolver().openInputStream(Uri.parse(string))) : FileUtil.checkPrivatePath(iWebview.getContext(), string) ? BitmapFactory.decodeFile(string) : FileUtil.needMediaStoreOpenFile(iWebview.getContext()) ? BitmapFactory.decodeStream(FileUtil.getFileInputStream(iWebview.getContext(), string)) : null;
                if (bitmapDecodeStream != null) {
                    imageObject.setImageData(bitmapDecodeStream);
                }
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("thumbs");
            String string2 = JSONUtil.getString(jSONArrayOptJSONArray2, 0);
            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0 && !PdrUtil.isEmpty(jSONArrayOptJSONArray2.getString(0))) {
                String strConvert2LocalFullPath = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), string2);
                if (string.startsWith("content://")) {
                    bitmapDecodeFile = BitmapFactory.decodeStream(iWebview.getContext().getContentResolver().openInputStream(Uri.parse(strConvert2LocalFullPath)));
                } else if (FileUtil.checkPrivatePath(iWebview.getContext(), string)) {
                    bitmapDecodeFile = BitmapFactory.decodeFile(strConvert2LocalFullPath);
                } else if (FileUtil.needMediaStoreOpenFile(iWebview.getContext())) {
                    bitmapDecodeFile = BitmapFactory.decodeStream(FileUtil.getFileInputStream(iWebview.getContext(), string));
                } else {
                    try {
                        bitmapDecodeFile = BitmapFactory.decodeFile(strConvert2LocalFullPath);
                    } catch (Exception unused) {
                        bitmapDecodeFile = null;
                    }
                }
                if (bitmapDecodeFile != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapDecodeFile.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    imageObject.thumbData = byteArrayOutputStream.toByteArray();
                }
            }
            return imageObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private MultiImageObject getMultiImageObject(IWebview iWebview, JSONObject jSONObject) {
        MultiImageObject multiImageObject = new MultiImageObject();
        if (jSONObject == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pictures");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String string = jSONArrayOptJSONArray.getString(i);
                    if (!PdrUtil.isEmpty(string) && !URLUtil.isNetworkUrl(string)) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            String strConvert2LocalFullPath = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), string);
                            String path = new File(iWebview.getContext().getExternalFilesDir(null) + "/" + strConvert2LocalFullPath.substring(strConvert2LocalFullPath.lastIndexOf("/"))).getPath();
                            DHFile.copyFile(strConvert2LocalFullPath, path);
                            arrayList.add(FileProvider.getUriForFile(iWebview.getContext(), iWebview.getContext().getPackageName() + ".fileprovider", new File(path)));
                        } else {
                            String strConvert2LocalFullPath2 = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), string);
                            Uri imageFileUri = FileUtil.getImageFileUri(iWebview.getContext(), strConvert2LocalFullPath2);
                            if (imageFileUri == null) {
                                imageFileUri = Uri.fromFile(new File(strConvert2LocalFullPath2));
                            }
                            arrayList.add(imageFileUri);
                        }
                    }
                }
            }
            multiImageObject.imageList = arrayList;
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return multiImageObject;
    }

    private TextObject getTextObj(JSONObject jSONObject) {
        try {
            String strOptString = jSONObject.optString("content");
            String strOptString2 = jSONObject.optString("title");
            String string = JSONUtil.getString(jSONObject, "href");
            TextObject textObject = new TextObject();
            StringBuilder sb = new StringBuilder();
            sb.append(strOptString);
            if (TextUtils.isEmpty(string)) {
                string = "";
            }
            sb.append(string);
            textObject.text = sb.toString();
            textObject.title = strOptString2;
            return textObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private VideoSourceObject getVideoObject(IWebview iWebview, JSONObject jSONObject) {
        if (!this.mWBAPI.isWBAppSupportMultipleImage()) {
            return null;
        }
        VideoSourceObject videoSourceObject = new VideoSourceObject();
        if (jSONObject == null) {
            return null;
        }
        try {
            String strOptString = jSONObject.optString("media");
            if (!TextUtils.isEmpty(strOptString) && !URLUtil.isNetworkUrl(strOptString)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    String strConvert2LocalFullPath = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), strOptString);
                    String path = new File(iWebview.getContext().getExternalFilesDir(null) + "/" + strConvert2LocalFullPath.substring(strConvert2LocalFullPath.lastIndexOf("/"))).getPath();
                    DHFile.copyFile(strConvert2LocalFullPath, path);
                    videoSourceObject.videoPath = FileProvider.getUriForFile(iWebview.getContext(), iWebview.getContext().getPackageName() + ".fileprovider", new File(path));
                } else {
                    String strConvert2LocalFullPath2 = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), strOptString);
                    Uri videoFileUri = FileUtil.getVideoFileUri(iWebview.getContext(), strConvert2LocalFullPath2);
                    if (videoFileUri == null) {
                        videoFileUri = Uri.fromFile(new File(strConvert2LocalFullPath2));
                    }
                    videoSourceObject.videoPath = videoFileUri;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return videoSourceObject;
    }

    private void initJsonObject(Context context) throws JSONException {
        Oauth2AccessToken oauth2AccessToken = this.mAccessToken;
        String accessToken = oauth2AccessToken != null ? oauth2AccessToken.getAccessToken() : "";
        JSONObject jSONObject = new JSONObject();
        this.mSinaWeibo = jSONObject;
        jSONObject.put("id", SINAWEIBO_ID);
        this.mSinaWeibo.put("description", SINAWEIBO_DES);
        this.mSinaWeibo.put(AbsoluteConst.JSON_SHARE_AUTHENTICATED, !PdrUtil.isEmpty(accessToken));
        this.mSinaWeibo.put(AbsoluteConst.JSON_SHARE_ACCESSTOKEN, accessToken);
        this.mSinaWeibo.put(AbsoluteConst.JSON_SHARE_NATIVECLIENT, PlatformUtil.hasAppInstalled(context, PACKAGENAME));
        this.mActivity = (Activity) context;
    }

    private void initWBAPI(Activity activity) {
        IWBAPI iwbapiCreateWBAPI = WBAPIFactory.createWBAPI(activity);
        this.mWBAPI = iwbapiCreateWBAPI;
        iwbapiCreateWBAPI.registerApp(activity, new AuthInfo(activity, APP_KEY, REDIRECT_URL, SCOPE));
    }

    public void OnSendEnd(IWebview iWebview, boolean z, int i, String str) {
        String str2 = this.SEND_CALLBACKID;
        if (str2 != null) {
            if (z) {
                Deprecated_JSUtil.execCallback(iWebview, str2, "", 1, false, false);
            } else {
                Deprecated_JSUtil.execCallback(iWebview, this.SEND_CALLBACKID, StringUtil.format(DOMException.JSON_ERROR_INFO, Integer.valueOf(i), str), JSUtil.ERROR, true, false);
            }
            this.SEND_CALLBACKID = null;
        }
    }

    @Override // io.dcloud.share.IFShareApi
    public void authorize(IWebview iWebview, String str, String str2) {
        if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -3, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_x86_not_support)), JSUtil.ERROR, true, false);
            return;
        }
        Oauth2AccessToken oauth2AccessToken = this.mAccessToken;
        if (oauth2AccessToken != null && oauth2AccessToken.isSessionValid()) {
            Deprecated_JSUtil.execCallback(iWebview, str, getJsonObject(iWebview), JSUtil.OK, true, false);
            return;
        }
        initWBAPI(iWebview.getActivity());
        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(str2);
        if (jSONObjectCreateJSONObject != null) {
            APP_KEY = jSONObjectCreateJSONObject.optString("appkey", APP_KEY);
            REDIRECT_URL = jSONObjectCreateJSONObject.optString("redirect_uri", REDIRECT_URL);
        }
        if (TextUtils.isEmpty(APP_KEY) || TextUtils.isEmpty(REDIRECT_URL)) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -7, DOMException.toString(DOMException.MSG_BUSINESS_PARAMETER_HAS_NOT)), JSUtil.ERROR, true, false);
            return;
        }
        this.tAuthorizeCallbackId = str;
        final IApp iAppObtainApp = iWebview.obtainApp();
        iAppObtainApp.registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.share.sina.SinaWeiboApiManager.5
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                int iIntValue2 = ((Integer) objArr[1]).intValue();
                Intent intent = (Intent) objArr[2];
                iAppObtainApp.unregisterSysEventListener(this, ISysEventListener.SysEventType.onActivityResult);
                if (SinaWeiboApiManager.this.mWBAPI != null) {
                    SinaWeiboApiManager.this.mWBAPI.authorizeCallback(iIntValue, iIntValue2, intent);
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
        this.mWBAPI.authorize(new SelfWbAuthListener(iWebview));
    }

    @Override // io.dcloud.share.IFShareApi
    public void dispose() {
        this.mActivity = null;
    }

    @Override // io.dcloud.share.IFShareApi
    public void forbid(IWebview iWebview) {
        iWebview.getActivity();
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.share.sina.SinaWeiboApiManager.4
            @Override // java.lang.Runnable
            public void run() {
                if (PdrUtil.isEmpty(SinaWeiboApiManager.this.mAccessToken.getAccessToken())) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("access_token=" + SinaWeiboApiManager.this.mAccessToken.getAccessToken());
                Logger.e("ian", "logout resultStr==" + new String(NetTool.httpPost("https://api.weibo.com/oauth2/revokeoauth2", stringBuffer.toString(), null)));
                SinaWeiboApiManager.this.mAccessToken = null;
            }
        });
    }

    @Override // io.dcloud.share.IFShareApi
    public String getId() {
        return SINAWEIBO_ID;
    }

    @Override // io.dcloud.share.IFShareApi
    public String getJsonObject(IWebview iWebview) {
        try {
            initJsonObject(iWebview.getActivity());
            return this.mSinaWeibo.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public WeiboMultiMessage getWeiboMultiMessage(String str) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        try {
            weiboMultiMessage.textObject = getTextObj(new JSONObject(str));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return weiboMultiMessage;
    }

    @Override // io.dcloud.share.IFShareApi
    public void initConfig() {
        initData();
    }

    public void initData() {
        if (!TextUtils.isEmpty(AndroidResources.getMetaValue("SINA_APPKEY"))) {
            APP_KEY = AndroidResources.getMetaValue("SINA_APPKEY").substring(1);
        }
        REDIRECT_URL = AndroidResources.getMetaValue("SINA_REDIRECT_URI");
    }

    public void send(final Activity activity, final String str) {
        initConfig();
        Oauth2AccessToken oauth2AccessToken = this.mAccessToken;
        if (oauth2AccessToken == null || !oauth2AccessToken.isSessionValid()) {
            final Runnable runnable = new Runnable() { // from class: io.dcloud.share.sina.SinaWeiboApiManager.1
                @Override // java.lang.Runnable
                public void run() {
                    SinaWeiboApiManager.this.send(activity, str);
                }
            };
            authorize(activity, new WbAuthListener() { // from class: io.dcloud.share.sina.SinaWeiboApiManager.2
                public void onCancel() {
                }

                public void onComplete(Oauth2AccessToken oauth2AccessToken2) {
                    SinaWeiboApiManager.this.mAccessToken = oauth2AccessToken2;
                    runnable.run();
                }

                public void onError(UiError uiError) {
                }
            });
            return;
        }
        initWBAPI(activity);
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.mInterface = jSONObject.optString("interface");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pictures");
            String strOptString = jSONObject.optString("content");
            if (URLUtil.isNetworkUrl(JSONUtil.getString(jSONArrayOptJSONArray, 0)) || TextUtils.isEmpty(strOptString)) {
                return;
            }
            this.mWBAPI.shareMessage(getWeiboMultiMessage(str), false);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public WeiboMultiMessage getWeiboMultiMessage(IWebview iWebview, String str, String str2) {
        JSONObject jSONObject;
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        weiboMultiMessage.textObject = getTextObj(jSONObject);
        if (TextUtils.isEmpty(str2) || !str2.equals("text")) {
            if (!TextUtils.isEmpty(str2) && str2.equals("image")) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pictures");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    if (Build.VERSION.SDK_INT >= 24 || jSONArrayOptJSONArray.length() > 1) {
                        weiboMultiMessage.multiImageObject = getMultiImageObject(iWebview, jSONObject);
                    } else {
                        weiboMultiMessage.imageObject = getImageObject(iWebview, jSONObject);
                    }
                }
            } else if (!TextUtils.isEmpty(str2) && str2.equals("video")) {
                weiboMultiMessage.videoSourceObject = getVideoObject(iWebview, jSONObject);
            } else if ((TextUtils.isEmpty(str2) || !str2.equals("web")) && !TextUtils.isEmpty(str2)) {
                Deprecated_JSUtil.execCallback(iWebview, this.tAuthorizeCallbackId, StringUtil.format(DOMException.JSON_ERROR_INFO, -100, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_type_param_error)), JSUtil.ERROR, true, false);
            } else {
                weiboMultiMessage.imageObject = getImageObject(iWebview, jSONObject);
            }
        }
        return weiboMultiMessage;
    }

    @Override // io.dcloud.share.IFShareApi
    public void send(final IWebview iWebview, String str, String str2) {
        if (Build.CPU_ABI.equalsIgnoreCase("x86")) {
            OnSendEnd(iWebview, false, -3, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_x86_not_support));
            return;
        }
        this.SEND_CALLBACKID = str;
        Oauth2AccessToken oauth2AccessToken = this.mAccessToken;
        if (oauth2AccessToken != null && oauth2AccessToken.isSessionValid()) {
            final IApp iAppObtainApp = iWebview.obtainApp();
            initWBAPI(iWebview.getActivity());
            iWebview.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.share.sina.SinaWeiboApiManager.3
                @Override // io.dcloud.common.DHInterface.ISysEventListener
                public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                    Logger.e("ian", "onExecute");
                    if (!PdrUtil.isEmpty(obj)) {
                        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject((String) obj);
                        Logger.e("ian", jSONObjectCreateJSONObject.toString());
                        if (PdrUtil.isEquals("0", jSONObjectCreateJSONObject.optString("_weibo_resp_errcode"))) {
                            SinaWeiboApiManager.this.OnSendEnd(iWebview, true, -1, null);
                        } else if (PdrUtil.isEquals("1", jSONObjectCreateJSONObject.optString("_weibo_resp_errcode"))) {
                            SinaWeiboApiManager.this.OnSendEnd(iWebview, false, -2, DOMException.MSG_USER_CANCEL);
                        } else {
                            String strOptString = jSONObjectCreateJSONObject.optString("_weibo_resp_errstr");
                            SinaWeiboApiManager.this.OnSendEnd(iWebview, true, Integer.valueOf(jSONObjectCreateJSONObject.optString("_weibo_resp_errcode")).intValue(), strOptString);
                        }
                    }
                    IApp iApp = iAppObtainApp;
                    if (iApp != null) {
                        iApp.unregisterSysEventListener(this, ISysEventListener.SysEventType.onNewIntent);
                    }
                    return false;
                }
            }, ISysEventListener.SysEventType.onNewIntent);
            try {
                JSONObject jSONObject = new JSONObject(str2);
                this.mInterface = jSONObject.optString("interface");
                String strOptString = jSONObject.optString("type");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pictures");
                String strOptString2 = jSONObject.optString("content");
                if (URLUtil.isNetworkUrl(JSONUtil.getString(jSONArrayOptJSONArray, 0))) {
                    OnSendEnd(iWebview, false, 15, DOMException.MSG_SHARE_SEND_PIC_ROUTE_ERROR);
                    return;
                }
                if (TextUtils.isEmpty(strOptString2)) {
                    OnSendEnd(iWebview, false, 15, DOMException.MSG_SHARE_SEND_CONTENT_EMPTY_ERROR);
                    return;
                }
                WeiboMultiMessage weiboMultiMessage = getWeiboMultiMessage(iWebview, str2, strOptString);
                if (!this.mWBAPI.isWBAppInstalled()) {
                    if (weiboMultiMessage.imageObject != null && ((weiboMultiMessage.imageObject.imageData != null && weiboMultiMessage.imageObject.imageData.length > 128000) || (weiboMultiMessage.imageObject.thumbData != null && weiboMultiMessage.imageObject.thumbData.length > 128000))) {
                        OnSendEnd(iWebview, false, 15, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_error_web_image_morethan_128KB));
                        return;
                    } else if (!PdrUtil.isEmpty(strOptString) && (strOptString.equals("video") || weiboMultiMessage.multiImageObject != null)) {
                        OnSendEnd(iWebview, false, 15, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_sina_error_web_image_multi));
                        return;
                    }
                }
                this.mWBAPI.shareMessage(weiboMultiMessage, false);
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
                OnSendEnd(iWebview, false, 15, SHARE_CONTENT_ERROR);
                return;
            }
        }
        OnSendEnd(iWebview, false, -10, DOMException.MSG_AUTHORIZE_FAILED);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void authorize(final Activity activity, WbAuthListener wbAuthListener) {
        Oauth2AccessToken oauth2AccessToken = this.mAccessToken;
        if (oauth2AccessToken == null || !oauth2AccessToken.isSessionValid()) {
            initWBAPI(activity);
            if (activity instanceof ISysEventDispatch) {
                ((ISysEventDispatch) activity).registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.share.sina.SinaWeiboApiManager.6
                    @Override // io.dcloud.common.DHInterface.ISysEventListener
                    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                        Object[] objArr = (Object[]) obj;
                        int iIntValue = ((Integer) objArr[0]).intValue();
                        int iIntValue2 = ((Integer) objArr[1]).intValue();
                        Intent intent = (Intent) objArr[2];
                        ((ISysEventDispatch) activity).unRegisterSysEventListener(this, ISysEventListener.SysEventType.onActivityResult);
                        if (SinaWeiboApiManager.this.mWBAPI != null) {
                            SinaWeiboApiManager.this.mWBAPI.authorizeCallback(iIntValue, iIntValue2, intent);
                        }
                        return false;
                    }
                }, ISysEventListener.SysEventType.onActivityResult);
            }
            this.mWBAPI.authorize(wbAuthListener);
        }
    }
}
