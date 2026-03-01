package io.dcloud.share.mm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.content.FileProvider;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.tencent.mm.opensdk.channel.MMessageActV2;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelbiz.WXOpenCustomerServiceChat;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.dcloud.PdrR;
import io.dcloud.ProcessMediator;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
import io.dcloud.common.DHInterface.IActivityHandler;
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
import io.dcloud.common.util.IOUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.share.weixin.R;
import io.dcloud.share.IFShareApi;
import io.dcloud.share.IWeiXinFShareApi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.cx0;
import supwisdom.qv;
import supwisdom.yw0;

/* JADX INFO: loaded from: classes3.dex */
public class WeiXinApiManager implements IWeiXinFShareApi {
    public static String APPID = null;
    public static final String AUTHORIZE_TEMPLATE = "{authenticated:%s,accessToken:'%s'}";
    public static final int ERROR_IMAGE_LIMIT = -102;
    public static final int ERROR_IMAGE_ONT_FOUND = -103;
    public static final int ERROR_NOTYPE = -100;
    public static final int ERROR_NOT_COMPLETE = -101;
    public static final int ERROR_NOT_SUPPORT_VERSION_CODE = -104;
    public static final String ERROR_NOT_SUPPORT_VERSION_MSG = "the current wechat client version does not support this function";
    public static final String KEY_APPID = "appid";
    public static final String TAG = "WeiXinApiManager";
    public static final int THUMB_SIZE = 150;
    public static final String WEIXIN_DES = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_wechat_plugin_desc);
    public static final String WEIXIN_ID = "weixin";
    public IWXAPI api;
    public Object[] sendCallbackMsg = null;
    public FeatureMessageDispatcher.MessageListener sSendCallbackMessageListener = new FeatureMessageDispatcher.MessageListener() { // from class: io.dcloud.share.mm.WeiXinApiManager.1
        @Override // io.dcloud.common.DHInterface.FeatureMessageDispatcher.MessageListener
        public void onReceiver(Object obj) {
            if (obj instanceof BaseResp) {
                WeiXinApiManager.this.executeSendCallbackMsg((BaseResp) obj);
                FeatureMessageDispatcher.unregisterListener(WeiXinApiManager.this.sSendCallbackMessageListener);
            }
        }
    };

    public static byte[] bmpToByteArray(Bitmap bitmap, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        if (z) {
            bitmap.recycle();
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return byteArray;
    }

    private byte[] buildThumbData(IWebview iWebview, String str) {
        Bitmap bitmapCpBitmap;
        String strConvert2LocalFullPath = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), str);
        try {
            if (PdrUtil.isNetPath(str)) {
                yw0.b bVar = new yw0.b();
                bVar.b(false);
                bVar.a(false);
                bVar.a(ImageScaleType.NONE);
                bVar.a(Bitmap.Config.RGB_565);
                bVar.a(new ColorDrawable(0));
                bitmapCpBitmap = cx0.f().a(str, bVar.a());
            } else {
                bitmapCpBitmap = scaleLoadPic(iWebview, strConvert2LocalFullPath);
            }
        } catch (Exception e2) {
            Logger.e("buildThumbData Exception=" + e2);
            bitmapCpBitmap = null;
        }
        if (bitmapCpBitmap == null) {
            bitmapCpBitmap = BitmapFactory.decodeResource(iWebview.getActivity().getResources(), PdrR.DRAWABLE_ICON);
        }
        if (bitmapCpBitmap != null) {
            bitmapCpBitmap = cpBitmap(bitmapCpBitmap);
        }
        return bmpToByteArray(bitmapCpBitmap, true);
    }

    private String buildTransaction(String str) {
        if (str == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return str + System.currentTimeMillis();
    }

    public static Bitmap cpBitmap(Bitmap bitmap) {
        if (PdrUtil.isEmpty(bitmap)) {
            return null;
        }
        while (bitmap.getHeight() * bitmap.getRowBytes() >= 32768) {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (bitmap.getWidth() * 2) / 3, (bitmap.getHeight() * 2) / 3, true);
            bitmap.recycle();
            bitmap = bitmapCreateScaledBitmap;
        }
        return bitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0033 A[Catch: Exception -> 0x0069, PHI: r1 r3 r8
  0x0033: PHI (r1v9 android.graphics.Bitmap) = (r1v1 android.graphics.Bitmap), (r1v1 android.graphics.Bitmap), (r1v12 android.graphics.Bitmap) binds: [B:25:0x0045, B:29:0x004b, B:12:0x0031] A[DONT_GENERATE, DONT_INLINE]
  0x0033: PHI (r3v4 boolean) = (r3v2 boolean), (r3v3 boolean), (r3v7 boolean) binds: [B:25:0x0045, B:29:0x004b, B:12:0x0031] A[DONT_GENERATE, DONT_INLINE]
  0x0033: PHI (r8v10 java.io.InputStream) = (r8v8 java.io.InputStream), (r8v9 java.io.InputStream), (r8v11 java.io.InputStream) binds: [B:25:0x0045, B:29:0x004b, B:12:0x0031] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x0069, blocks: (B:3:0x0013, B:13:0x0033, B:33:0x0051, B:34:0x0054, B:35:0x0055, B:38:0x0061), top: B:49:0x0013 }] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] getMiniThumbaData(io.dcloud.common.DHInterface.IWebview r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            r6 = this;
            io.dcloud.common.DHInterface.IFrameView r0 = r7.obtainFrameView()
            io.dcloud.common.DHInterface.IApp r0 = r0.obtainApp()
            java.lang.String r1 = r7.obtainFullUrl()
            java.lang.String r0 = r0.convert2LocalFullPath(r1, r8)
            r1 = 0
            r2 = 1
            r3 = 0
            boolean r4 = io.dcloud.common.util.PdrUtil.isNetPath(r8)     // Catch: java.lang.Exception -> L69
            r5 = 131072(0x20000, float:1.83671E-40)
            if (r4 == 0) goto L55
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3e java.net.MalformedURLException -> L46
            r0.<init>(r8)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3e java.net.MalformedURLException -> L46
            java.io.InputStream r8 = r0.openStream()     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3e java.net.MalformedURLException -> L46
            int r0 = r8.available()     // Catch: java.io.IOException -> L37 java.net.MalformedURLException -> L39 java.lang.Throwable -> L4e
            if (r0 <= r5) goto L2b
            r3 = 1
        L2b:
            if (r8 == 0) goto L31
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r8)     // Catch: java.io.IOException -> L37 java.net.MalformedURLException -> L39 java.lang.Throwable -> L4e
        L31:
            if (r8 == 0) goto L81
        L33:
            r8.close()     // Catch: java.lang.Exception -> L69
            goto L81
        L37:
            r0 = move-exception
            goto L40
        L39:
            r0 = move-exception
            goto L48
        L3b:
            r0 = move-exception
            r8 = r1
            goto L4f
        L3e:
            r0 = move-exception
            r8 = r1
        L40:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4e
            if (r8 == 0) goto L81
            goto L33
        L46:
            r0 = move-exception
            r8 = r1
        L48:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4e
            if (r8 == 0) goto L81
            goto L33
        L4e:
            r0 = move-exception
        L4f:
            if (r8 == 0) goto L54
            r8.close()     // Catch: java.lang.Exception -> L69
        L54:
            throw r0     // Catch: java.lang.Exception -> L69
        L55:
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L69
            r8.<init>(r0)     // Catch: java.lang.Exception -> L69
            int r0 = r8.available()     // Catch: java.lang.Exception -> L69
            if (r0 <= r5) goto L61
            r3 = 1
        L61:
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r8)     // Catch: java.lang.Exception -> L69
            r8.close()     // Catch: java.lang.Exception -> L69
            goto L81
        L69:
            r8 = move-exception
            r8.printStackTrace()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "buildThumbData Exception="
            r0.append(r4)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            io.dcloud.common.adapter.util.Logger.e(r8)
        L81:
            if (r1 != 0) goto L91
            android.app.Activity r7 = r7.getActivity()
            android.content.res.Resources r7 = r7.getResources()
            int r8 = io.dcloud.PdrR.DRAWABLE_ICON
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeResource(r7, r8)
        L91:
            if (r1 == 0) goto L99
            if (r3 == 0) goto L99
            android.graphics.Bitmap r1 = cpBitmap(r1)
        L99:
            byte[] r7 = bmpToByteArray(r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.share.mm.WeiXinApiManager.getMiniThumbaData(io.dcloud.common.DHInterface.IWebview, java.lang.String):byte[]");
    }

    private boolean hasFullConfigData() {
        return !TextUtils.isEmpty(APPID);
    }

    private boolean hasGeneralError(IWebview iWebview, String str) {
        if (!hasFullConfigData()) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -7, DOMException.toString(DOMException.MSG_BUSINESS_PARAMETER_HAS_NOT)), JSUtil.ERROR, true, false);
            return true;
        }
        if (PlatformUtil.isAppInstalled(iWebview.getContext(), "com.tencent.mm")) {
            return false;
        }
        Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format(DOMException.JSON_ERROR_INFO, -8, DOMException.toString(DOMException.MSG_CLIENT_UNINSTALLED)), JSUtil.ERROR, true, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasWXEntryActivity(Context context) {
        try {
            Class.forName(context.getPackageName() + MMessageActV2.DEFAULT_ENTRY_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSendCallBack(io.dcloud.common.DHInterface.IWebview r11, java.lang.String r12, int r13) {
        /*
            r10 = this;
            r0 = -5
            r1 = 1
            r2 = 0
            java.lang.String r3 = "send"
            if (r13 == r0) goto L4c
            r0 = -4
            if (r13 == r0) goto L49
            r0 = -3
            if (r13 == r0) goto L46
            r0 = -2
            if (r13 == r0) goto L43
            r0 = -1
            if (r13 == r0) goto L40
            if (r13 == 0) goto L3e
            switch(r13) {
                case -103: goto L3b;
                case -102: goto L38;
                case -101: goto L29;
                case -100: goto L1a;
                default: goto L18;
            }
        L18:
            r0 = 0
            goto L4f
        L1a:
            io.dcloud.application.DCLoudApplicationImpl r0 = io.dcloud.application.DCLoudApplicationImpl.self()
            android.content.Context r0 = r0.getContext()
            int r3 = io.dcloud.feature.share.weixin.R.string.dcloud_feature_share_wechat_error_param_type_error
            java.lang.String r3 = r0.getString(r3)
            goto L18
        L29:
            io.dcloud.application.DCLoudApplicationImpl r0 = io.dcloud.application.DCLoudApplicationImpl.self()
            android.content.Context r0 = r0.getContext()
            int r3 = io.dcloud.feature.share.weixin.R.string.dcloud_feature_share_wechat_error_param_not_complete
            java.lang.String r3 = r0.getString(r3)
            goto L18
        L38:
            java.lang.String r3 = "Picture maximum 512KB"
            goto L18
        L3b:
            java.lang.String r3 = "Picture not found"
            goto L18
        L3e:
            r0 = 1
            goto L4f
        L40:
            java.lang.String r3 = "General errors"
            goto L18
        L43:
            java.lang.String r3 = "User canceled"
            goto L18
        L46:
            java.lang.String r3 = "Unable to send"
            goto L18
        L49:
            java.lang.String r3 = "Authentication failed"
            goto L18
        L4c:
            java.lang.String r3 = "Unsupport error"
            goto L18
        L4f:
            if (r0 == 0) goto L5d
            int r7 = io.dcloud.common.util.JSUtil.OK
            r8 = 0
            r9 = 0
            java.lang.String r6 = ""
            r4 = r11
            r5 = r12
            io.dcloud.common.util.JSUtil.execCallback(r4, r5, r6, r7, r8, r9)
            goto L8d
        L5d:
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r4 = -100
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0[r2] = r4
            io.dcloud.application.DCLoudApplicationImpl r2 = io.dcloud.application.DCLoudApplicationImpl.self()
            android.content.Context r2 = r2.getContext()
            int r4 = io.dcloud.feature.share.weixin.R.string.dcloud_feature_share_wechat_plugin_name
            java.lang.String r2 = r2.getString(r4)
            java.lang.String r4 = "http://ask.dcloud.net.cn/article/287"
            java.lang.String r13 = io.dcloud.common.constant.DOMException.toString(r13, r2, r3, r4)
            r0[r1] = r13
            java.lang.String r13 = "{code:%d,message:'%s'}"
            java.lang.String r3 = io.dcloud.common.util.StringUtil.format(r13, r0)
            int r4 = io.dcloud.common.util.JSUtil.ERROR
            r5 = 1
            r6 = 0
            r1 = r11
            r2 = r12
            io.dcloud.common.util.JSUtil.execCallback(r1, r2, r3, r4, r5, r6)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.share.mm.WeiXinApiManager.onSendCallBack(io.dcloud.common.DHInterface.IWebview, java.lang.String, int):void");
    }

    private boolean register(IWebview iWebview) {
        if (this.api == null) {
            this.api = WXAPIFactory.createWXAPI(iWebview.getActivity().getApplicationContext(), APPID, true);
        }
        if (PdrUtil.isEmpty(APPID)) {
            return false;
        }
        return this.api.registerApp(APPID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSendCallbackMsg(Object[] objArr) {
        this.sendCallbackMsg = objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reqImageMsg(IWebview iWebview, SendMessageToWX.Req req, JSONArray jSONArray, JSONArray jSONArray2, String str, String str2) {
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        jSONArray.length();
        String strOptString = jSONArray.optString(0);
        String strOptString2 = (jSONArray2 == null || jSONArray2.isNull(0)) ? strOptString : jSONArray2.optString(0);
        if (PdrUtil.isNetPath(strOptString)) {
            WXImageObject wXImageObject = new WXImageObject();
            try {
                wXImageObject.imageData = bmpToByteArray(BitmapFactory.decodeStream(new URL(strOptString).openStream()), true);
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            wXMediaMessage.mediaObject = wXImageObject;
            if (!PdrUtil.isEmpty(strOptString2)) {
                strOptString = strOptString2;
            }
            wXMediaMessage.thumbData = buildThumbData(iWebview, strOptString);
        } else {
            String strConvert2LocalFullPath = iWebview.obtainFrameView().obtainApp().convert2LocalFullPath(iWebview.obtainFullUrl(), strOptString);
            WXImageObject wXImageObject2 = new WXImageObject();
            File file = new File(strConvert2LocalFullPath);
            InputStream imageFileStream = null;
            Context context = iWebview.getContext();
            try {
                if (!file.exists()) {
                    onSendCallBack(iWebview, str2, -103);
                    return false;
                }
                if (Build.VERSION.SDK_INT < 29) {
                    wXImageObject2.imagePath = file.getPath();
                } else if (FileUtil.checkPrivatePath(context, strConvert2LocalFullPath)) {
                    String str3 = context.getExternalCacheDir().getPath() + "/share/" + strConvert2LocalFullPath.substring(strConvert2LocalFullPath.lastIndexOf("/"));
                    DHFile.copyFile(strConvert2LocalFullPath, str3);
                    Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".dc.fileprovider", new File(str3));
                    context.grantUriPermission("com.tencent.mm", uriForFile, 1);
                    wXImageObject2.imagePath = uriForFile.toString();
                } else if (FileUtil.isFilePathForPublic(context, strConvert2LocalFullPath)) {
                    Uri imageFileUri = FileUtil.getImageFileUri(context, strConvert2LocalFullPath);
                    if (imageFileUri != null) {
                        wXImageObject2.imagePath = imageFileUri.toString();
                    } else {
                        imageFileStream = FileUtil.getImageFileStream(context, file);
                    }
                }
                if (imageFileStream != null) {
                    if (imageFileStream.available() >= 524288) {
                        imageFileStream.close();
                        onSendCallBack(iWebview, str2, -102);
                        return false;
                    }
                    wXImageObject2.imageData = IOUtil.getBytes(imageFileStream);
                    imageFileStream.close();
                }
                wXMediaMessage.mediaObject = wXImageObject2;
                wXMediaMessage.thumbData = buildThumbData(iWebview, strOptString2);
            } catch (Exception unused) {
            }
        }
        if (!PdrUtil.isEmpty(str)) {
            wXMediaMessage.title = str;
        }
        req.transaction = buildTransaction("img");
        req.message = wXMediaMessage;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reqMiniMsg(IWebview iWebview, SendMessageToWX.Req req, String str, String str2, String str3, JSONObject jSONObject) {
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.webpageUrl = jSONObject.optString("webUrl");
        wXMiniProgramObject.miniprogramType = jSONObject.optInt("type");
        wXMiniProgramObject.userName = jSONObject.optString("id");
        wXMiniProgramObject.path = jSONObject.optString("path");
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
        if (!PdrUtil.isEmpty(str3)) {
            wXMediaMessage.title = str3;
        } else if (req.scene == 1 && !TextUtils.isEmpty(str2)) {
            wXMediaMessage.title = str2;
        }
        req.scene = 0;
        if (!PdrUtil.isEmpty(str)) {
            wXMediaMessage.thumbData = getMiniThumbaData(iWebview, str);
        }
        wXMediaMessage.description = str2;
        req.transaction = buildTransaction("webpage");
        req.message = wXMediaMessage;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reqMusicMsg(IWebview iWebview, SendMessageToWX.Req req, String str, String str2, String str3, String str4, String str5) {
        WXMusicObject wXMusicObject = new WXMusicObject();
        if (PdrUtil.isEmpty(str)) {
            wXMusicObject.musicUrl = str2;
        } else {
            wXMusicObject.musicUrl = str;
            wXMusicObject.musicDataUrl = str2;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXMusicObject;
        if (!PdrUtil.isEmpty(str5)) {
            wXMediaMessage.title = str5;
        } else if (req.scene == 1 && !TextUtils.isEmpty(str4)) {
            wXMediaMessage.title = str4;
        }
        if (!PdrUtil.isEmpty(str3)) {
            wXMediaMessage.thumbData = buildThumbData(iWebview, str3);
        }
        wXMediaMessage.description = str4;
        req.transaction = buildTransaction("music");
        req.message = wXMediaMessage;
        return true;
    }

    private boolean reqMusicVideo(IWebview iWebview, SendMessageToWX.Req req, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        WXMusicVideoObject wXMusicVideoObject = new WXMusicVideoObject();
        wXMusicVideoObject.musicUrl = str;
        wXMusicVideoObject.musicDataUrl = str2;
        if (!TextUtils.isEmpty(str3)) {
            wXMusicVideoObject.songLyric = str3;
        }
        if (!TextUtils.isEmpty(str5)) {
            wXMusicVideoObject.hdAlbumThumbFilePath = str5;
        }
        wXMusicVideoObject.singerName = "xxx";
        wXMusicVideoObject.albumName = "album_xxx";
        wXMusicVideoObject.musicGenre = "流行歌曲";
        wXMusicVideoObject.issueDate = 1610713585L;
        wXMusicVideoObject.identification = "sample_identification";
        wXMusicVideoObject.duration = 120000;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXMusicVideoObject;
        wXMediaMessage.title = str6;
        wXMediaMessage.description = str7;
        wXMediaMessage.messageExt = "额外信息";
        wXMediaMessage.thumbData = buildThumbData(iWebview, str4);
        req.transaction = buildTransaction("musicVideo");
        req.message = wXMediaMessage;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reqTextMsg(SendMessageToWX.Req req, String str, String str2) {
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXTextObject;
        if (!PdrUtil.isEmpty(str2)) {
            wXMediaMessage.title = str2;
        } else if (req.scene == 1 && !TextUtils.isEmpty(str)) {
            wXMediaMessage.title = str;
        }
        wXMediaMessage.description = str;
        req.transaction = buildTransaction("text");
        req.message = wXMediaMessage;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reqVideoMsg(IWebview iWebview, SendMessageToWX.Req req, String str, String str2, String str3, String str4) {
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXVideoObject;
        if (!PdrUtil.isEmpty(str4)) {
            wXMediaMessage.title = str4;
        } else if (req.scene == 1 && !TextUtils.isEmpty(str3)) {
            wXMediaMessage.title = str3;
        }
        if (!PdrUtil.isEmpty(str2)) {
            wXMediaMessage.thumbData = buildThumbData(iWebview, str2);
        }
        wXMediaMessage.description = str3;
        req.transaction = buildTransaction("video");
        req.message = wXMediaMessage;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean reqWebPageMsg(IWebview iWebview, SendMessageToWX.Req req, String str, String str2, String str3, String str4) {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        if (!PdrUtil.isEmpty(str4)) {
            wXMediaMessage.title = str4;
        } else if (req.scene == 1 && !TextUtils.isEmpty(str3)) {
            wXMediaMessage.title = str3;
        }
        wXMediaMessage.description = str3;
        if (!PdrUtil.isEmpty(str2)) {
            wXMediaMessage.thumbData = buildThumbData(iWebview, str2);
        }
        req.transaction = buildTransaction("webpage");
        req.message = wXMediaMessage;
        return true;
    }

    private void sedMultiplePic(Activity activity, ArrayList<Uri> arrayList, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.tencent.mm", str));
        intent.setAction("android.intent.action.SEND_MULTIPLE");
        intent.setType("image/*");
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        activity.startActivity(intent);
    }

    private void startWXMiniProgramMediator(final IWebview iWebview, final String str, String str2, String str3, int i) {
        Intent intent = new Intent();
        intent.putExtra(ProcessMediator.LOGIC_CLASS, WeiXinMediator.class.getName());
        Bundle bundle = new Bundle();
        bundle.putString("userName", str2);
        bundle.putString("path", str3);
        bundle.putInt("miniprogramType", i);
        intent.putExtra("req", bundle);
        intent.setClassName(iWebview.getActivity(), ProcessMediator.class.getName());
        intent.putExtra(WeiXinMediator.WX_MINI_PROGRAM_KEY, true);
        intent.putExtra(ProcessMediator.PROCESS_ACTIVITY_SOURCE, iWebview.getActivity().getClass().getName());
        iWebview.getActivity().startActivityForResult(intent, 1000);
        iWebview.getActivity().overridePendingTransition(0, 0);
        iWebview.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.share.mm.WeiXinApiManager.4
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                Intent intent2 = (Intent) objArr[2];
                if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == 1000) {
                    Bundle bundleExtra = intent2.getBundleExtra("result");
                    if (bundleExtra == null) {
                        WeiXinApiManager.this.onSendCallBack(iWebview, str, -3);
                    } else {
                        String string = bundleExtra.getString("style");
                        if ("BaseResp".equals(string)) {
                            WXLaunchMiniProgram.Resp resp = new WXLaunchMiniProgram.Resp();
                            resp.fromBundle(bundleExtra);
                            Deprecated_JSUtil.execCallback(iWebview, str, resp.extMsg, JSUtil.OK, false, false);
                        } else {
                            "BaseReq".equals(string);
                        }
                    }
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWeiXinMediator(final IWebview iWebview, final String str, SendMessageToWX.Req req, String str2, String str3, String str4, String str5, int i) {
        Intent intent = new Intent();
        intent.putExtra(ProcessMediator.LOGIC_CLASS, WeiXinMediator.class.getName());
        Bundle bundle = new Bundle();
        req.toBundle(bundle);
        bundle.putString("pImg", str2);
        bundle.putString("pThumbImg", str3);
        bundle.putString("absFullPath", str4);
        bundle.putString("AbsFullPathThumb", str5);
        bundle.putInt("mRunningMode", i);
        intent.putExtra("req", bundle);
        intent.setClassName(iWebview.getActivity(), ProcessMediator.class.getName());
        intent.putExtra("transaction", req.transaction);
        iWebview.getActivity().startActivityForResult(intent, 1000);
        iWebview.getActivity().overridePendingTransition(0, 0);
        iWebview.obtainApp().registerSysEventListener(new ISysEventListener() { // from class: io.dcloud.share.mm.WeiXinApiManager.3
            @Override // io.dcloud.common.DHInterface.ISysEventListener
            public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
                Object[] objArr = (Object[]) obj;
                int iIntValue = ((Integer) objArr[0]).intValue();
                ((Integer) objArr[1]).intValue();
                Intent intent2 = (Intent) objArr[2];
                if (sysEventType == ISysEventListener.SysEventType.onActivityResult && iIntValue == 1000) {
                    Bundle bundleExtra = intent2.getBundleExtra("result");
                    if (bundleExtra == null) {
                        WeiXinApiManager.this.onSendCallBack(iWebview, str, -3);
                    } else {
                        String string = bundleExtra.getString("style");
                        if ("BaseResp".equals(string)) {
                            SendMessageToWX.Resp resp = new SendMessageToWX.Resp();
                            resp.fromBundle(bundleExtra);
                            WeiXinApiManager.this.onSendCallBack(iWebview, str, resp.errCode);
                        } else {
                            "BaseReq".equals(string);
                        }
                    }
                }
                return false;
            }
        }, ISysEventListener.SysEventType.onActivityResult);
    }

    @Override // io.dcloud.share.IFShareApi
    public void authorize(IWebview iWebview, String str, String str2) {
        JSONObject jSONObjectCreateJSONObject = JSONUtil.createJSONObject(str2);
        if (jSONObjectCreateJSONObject != null) {
            APPID = jSONObjectCreateJSONObject.optString("appid", APPID);
            Logger.e("WeiXinApiManager", "authorize: appId" + APPID);
        }
        if (hasGeneralError(iWebview, str)) {
            return;
        }
        boolean zRegister = register(iWebview);
        if (zRegister) {
            Deprecated_JSUtil.execCallback(iWebview, str, StringUtil.format("{authenticated:%s,accessToken:'%s'}", Boolean.valueOf(zRegister), ""), JSUtil.OK, true, false);
        } else {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(-100, DOMException.toString(-4, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_wechat_plugin_name), DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_wechat_error_auth_fail), IFShareApi.mLink)), JSUtil.ERROR, true, false);
        }
    }

    @Override // io.dcloud.share.IFShareApi
    public void dispose() {
        IWXAPI iwxapi = this.api;
        if (iwxapi != null) {
            iwxapi.unregisterApp();
            this.api.detach();
        }
        this.api = null;
    }

    public void executeSendCallbackMsg(BaseResp baseResp) {
        Object[] objArr = this.sendCallbackMsg;
        if (objArr != null) {
            IWebview iWebview = (IWebview) objArr[0];
            String str = (String) objArr[1];
            if (baseResp != null) {
                if (baseResp instanceof WXLaunchMiniProgram.Resp) {
                    Deprecated_JSUtil.execCallback(iWebview, str, ((WXLaunchMiniProgram.Resp) baseResp).extMsg, JSUtil.OK, false, false);
                } else {
                    onSendCallBack(iWebview, str, baseResp.errCode);
                }
            }
        }
    }

    @Override // io.dcloud.share.IFShareApi
    public void forbid(IWebview iWebview) {
        if (this.api == null) {
            this.api = WXAPIFactory.createWXAPI(iWebview.getActivity().getApplicationContext(), APPID, true);
        }
        this.api.unregisterApp();
    }

    @Override // io.dcloud.share.IFShareApi
    public String getId() {
        return WEIXIN_ID;
    }

    @Override // io.dcloud.share.IFShareApi
    public String getJsonObject(IWebview iWebview) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", WEIXIN_ID);
            jSONObject.put("description", WEIXIN_DES);
            jSONObject.put(AbsoluteConst.JSON_SHARE_AUTHENTICATED, register(iWebview));
            jSONObject.put(AbsoluteConst.JSON_SHARE_ACCESSTOKEN, "");
            jSONObject.put(AbsoluteConst.JSON_SHARE_NATIVECLIENT, this.api.isWXAppInstalled());
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // io.dcloud.share.IFShareApi
    public void initConfig() {
        initData();
    }

    public void initData() {
        APPID = AndroidResources.getMetaValue("WX_APPID");
    }

    @Override // io.dcloud.share.IWeiXinFShareApi
    public void launchMiniProgram(IWebview iWebview, String str, String str2) {
        try {
            if (hasGeneralError(iWebview, str2)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("id");
            String strOptString2 = jSONObject.optString("path");
            int iOptInt = jSONObject.optInt("type", 0);
            if (PdrUtil.isEmpty(strOptString)) {
                Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -1, DOMException.MSG_PARAMETER_ERROR), JSUtil.ERROR, true, false);
                return;
            }
            if ((iWebview.getActivity() instanceof IActivityHandler) && ((IActivityHandler) iWebview.getActivity()).isMultiProcessMode()) {
                startWXMiniProgramMediator(iWebview, str2, strOptString, strOptString2, iOptInt);
                return;
            }
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = strOptString;
            req.path = strOptString2;
            req.miniprogramType = iOptInt;
            if (!this.api.sendReq(req)) {
                Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -1, DOMException.MSG_PARAMETER_ERROR), JSUtil.ERROR, true, false);
            } else if (hasWXEntryActivity(iWebview.getContext())) {
                FeatureMessageDispatcher.registerListener(this.sSendCallbackMessageListener);
                registerSendCallbackMsg(new Object[]{iWebview, str2});
            }
        } catch (Exception e2) {
            Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -99, DOMException.MSG_UNKNOWN_ERROR + e2.getMessage()), JSUtil.ERROR, true, false);
        }
    }

    @Override // io.dcloud.share.IWeiXinFShareApi
    public void openCustomerServiceChat(IWebview iWebview, String str, String str2) {
        try {
            if (!hasGeneralError(iWebview, str2)) {
                if (this.api.getWXAppSupportAPI() >= 671090490) {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("url");
                    String string2 = jSONObject.getString("corpid");
                    if (PdrUtil.isEmpty(string) || PdrUtil.isEmpty(string2)) {
                        Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -1, DOMException.MSG_PARAMETER_ERROR), JSUtil.ERROR, true, false);
                    } else {
                        WXOpenCustomerServiceChat.Req req = new WXOpenCustomerServiceChat.Req();
                        req.corpId = string2;
                        req.url = string;
                        if (this.api.sendReq(req)) {
                            Deprecated_JSUtil.execCallback(iWebview, str2, "", JSUtil.OK, false, false);
                        } else {
                            Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -1, DOMException.MSG_PARAMETER_ERROR), JSUtil.ERROR, true, false);
                        }
                    }
                } else {
                    Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -104, ERROR_NOT_SUPPORT_VERSION_MSG), JSUtil.ERROR, true, false);
                }
            }
        } catch (Exception e2) {
            Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format(DOMException.JSON_ERROR_INFO, -99, DOMException.MSG_UNKNOWN_ERROR + e2.getMessage()), JSUtil.ERROR, true, false);
        }
    }

    public Bitmap scaleLoadPic(IWebview iWebview, String str) throws IOException {
        InputStream inputStreamOpenInputStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        if (FileUtil.checkPrivatePath(iWebview.getContext(), str)) {
            BitmapFactory.decodeFile(str, options);
        } else {
            InputStream imageFileStream = FileUtil.getImageFileStream(iWebview.getContext(), new File(str));
            if (imageFileStream != null) {
                BitmapFactory.decodeStream(imageFileStream, null, options);
                imageFileStream.close();
            }
        }
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        Display defaultDisplay = iWebview.getActivity().getWindowManager().getDefaultDisplay();
        int width = i2 / defaultDisplay.getWidth();
        int height = i3 / defaultDisplay.getHeight();
        if (width >= height && width >= 1) {
            i = width;
        } else if (width < height && height >= 1) {
            i = height;
        }
        options.inSampleSize = i;
        options.inJustDecodeBounds = false;
        if (!str.startsWith("content://")) {
            if (FileUtil.checkPrivatePath(iWebview.getContext(), str)) {
                return BitmapFactory.decodeFile(str, options);
            }
            InputStream imageFileStream2 = FileUtil.getImageFileStream(iWebview.getContext(), new File(str));
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(imageFileStream2, null, options);
            imageFileStream2.close();
            return bitmapDecodeStream;
        }
        Uri uri = Uri.parse(str);
        Cursor cursorQuery = iWebview.getContext().getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            inputStreamOpenInputStream = iWebview.getContext().getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            inputStreamOpenInputStream = null;
        }
        cursorQuery.moveToFirst();
        Bitmap bitmapDecodeStream2 = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
        cursorQuery.close();
        return bitmapDecodeStream2;
    }

    @Override // io.dcloud.share.IFShareApi
    public void send(final IWebview iWebview, final String str, final String str2) {
        if (hasGeneralError(iWebview, str)) {
            return;
        }
        new Thread() { // from class: io.dcloud.share.mm.WeiXinApiManager.2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:146:0x028b A[Catch: Exception -> 0x0303, TryCatch #7 {Exception -> 0x0303, blocks: (B:3:0x0006, B:5:0x0049, B:7:0x004f, B:9:0x005b, B:10:0x005e, B:12:0x0066, B:13:0x0069, B:15:0x0071, B:146:0x028b, B:148:0x029a, B:150:0x02a4, B:152:0x02b2, B:154:0x02c2, B:156:0x02cf, B:158:0x02dd, B:159:0x02f4, B:143:0x0279), top: B:176:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:148:0x029a A[Catch: Exception -> 0x0303, TryCatch #7 {Exception -> 0x0303, blocks: (B:3:0x0006, B:5:0x0049, B:7:0x004f, B:9:0x005b, B:10:0x005e, B:12:0x0066, B:13:0x0069, B:15:0x0071, B:146:0x028b, B:148:0x029a, B:150:0x02a4, B:152:0x02b2, B:154:0x02c2, B:156:0x02cf, B:158:0x02dd, B:159:0x02f4, B:143:0x0279), top: B:176:0x0006 }] */
            /* JADX WARN: Type inference failed for: r16v29 */
            /* JADX WARN: Type inference failed for: r16v30 */
            /* JADX WARN: Type inference failed for: r16v5 */
            /* JADX WARN: Type inference failed for: r3v0, types: [org.json.JSONObject] */
            /* JADX WARN: Type inference failed for: r3v1 */
            /* JADX WARN: Type inference failed for: r3v12 */
            /* JADX WARN: Type inference failed for: r3v14 */
            /* JADX WARN: Type inference failed for: r3v15 */
            /* JADX WARN: Type inference failed for: r3v2 */
            /* JADX WARN: Type inference failed for: r3v28 */
            /* JADX WARN: Type inference failed for: r3v29 */
            /* JADX WARN: Type inference failed for: r3v3 */
            /* JADX WARN: Type inference failed for: r3v34 */
            /* JADX WARN: Type inference failed for: r3v35 */
            /* JADX WARN: Type inference failed for: r3v36 */
            /* JADX WARN: Type inference failed for: r3v37 */
            /* JADX WARN: Type inference failed for: r3v4 */
            /* JADX WARN: Type inference failed for: r3v5, types: [int] */
            @Override // java.lang.Thread, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 776
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: io.dcloud.share.mm.WeiXinApiManager.AnonymousClass2.run():void");
            }
        }.start();
    }

    public void sendImage(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("image");
            String strOptString2 = jSONObject.optString("thumbs");
            String strOptString3 = jSONObject.optString("textToImage");
            int iOptInt = jSONObject.optInt("flag");
            initData();
            if (this.api == null) {
                this.api = WXAPIFactory.createWXAPI(activity.getApplicationContext(), APPID, true);
            }
            this.api.registerApp(APPID);
            if (!this.api.isWXAppInstalled()) {
                qv.makeText(activity.getApplicationContext(), (CharSequence) DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_wechat_error_client_not_installed), 0).show();
                return;
            }
            Bitmap bitmapDecodeResource = null;
            Bitmap bitmapDecodeFile = (TextUtils.isEmpty(strOptString) || !new File(strOptString).exists()) ? null : BitmapFactory.decodeFile(strOptString);
            if (bitmapDecodeFile == null && !TextUtils.isEmpty(strOptString3)) {
                bitmapDecodeFile = StringUtil.textToBitmap(activity, strOptString3);
            }
            if (bitmapDecodeFile == null) {
                return;
            }
            WXImageObject wXImageObject = new WXImageObject(bitmapDecodeFile);
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXImageObject);
            wXMediaMessage.mediaObject = wXImageObject;
            if (!TextUtils.isEmpty(strOptString2) && new File(strOptString2).exists()) {
                bitmapDecodeResource = BitmapFactory.decodeFile(strOptString2);
            }
            if (bitmapDecodeResource == null) {
                bitmapDecodeResource = BitmapFactory.decodeResource(activity.getResources(), PdrR.DRAWABLE_ICON);
            }
            wXMediaMessage.setThumbImage(bitmapDecodeResource);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            req.scene = iOptInt;
            this.api.sendReq(req);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void sendText(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("text");
            int iOptInt = jSONObject.optInt("flag");
            initData();
            if (this.api == null) {
                this.api = WXAPIFactory.createWXAPI(activity.getApplicationContext(), APPID, true);
            }
            this.api.registerApp(APPID);
            if (!this.api.isWXAppInstalled()) {
                qv.makeText(activity.getApplicationContext(), (CharSequence) DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_wechat_error_client_not_installed), 0).show();
                return;
            }
            WXTextObject wXTextObject = new WXTextObject();
            wXTextObject.text = strOptString;
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXTextObject);
            wXMediaMessage.mediaObject = wXTextObject;
            wXMediaMessage.description = strOptString;
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            req.scene = iOptInt;
            this.api.sendReq(req);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void send(Activity activity, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("content");
            String string2 = jSONObject.getString("href");
            String strOptString = jSONObject.optString("thumbs");
            int i = jSONObject.getInt("flag");
            initData();
            if (this.api == null) {
                this.api = WXAPIFactory.createWXAPI(activity.getApplicationContext(), APPID, true);
            }
            this.api.registerApp(APPID);
            if (!this.api.isWXAppInstalled()) {
                qv.makeText(activity.getApplicationContext(), (CharSequence) DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_share_wechat_error_client_not_installed), 0).show();
                return;
            }
            WXWebpageObject wXWebpageObject = new WXWebpageObject();
            wXWebpageObject.webpageUrl = string2;
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
            wXMediaMessage.description = string;
            wXMediaMessage.title = string;
            Bitmap bitmapDecodeResource = null;
            if (!TextUtils.isEmpty(strOptString) && new File(strOptString).exists()) {
                bitmapDecodeResource = BitmapFactory.decodeFile(strOptString);
            }
            if (bitmapDecodeResource == null) {
                bitmapDecodeResource = BitmapFactory.decodeResource(activity.getResources(), PdrR.DRAWABLE_ICON);
            }
            wXMediaMessage.setThumbImage(bitmapDecodeResource);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            req.scene = i;
            this.api.sendReq(req);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
