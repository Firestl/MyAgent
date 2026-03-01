package io.dcloud.feature.nativeObj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.nostra13.dcloudimageloader.core.assist.FailReason;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.DHInterface.INativeBitmap;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.ImageLoaderUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;
import supwisdom.cx0;
import supwisdom.hx0;
import supwisdom.jx0;
import supwisdom.yw0;

/* JADX INFO: loaded from: classes3.dex */
public class NativeBitmap implements INativeBitmap {
    public static final int ERROR = 40;
    public static final int SUCCESS = 10;
    public IApp mApp;
    public Bitmap mBitmap;
    public String mExt;
    public GifDrawable mGifDrawable;
    public String mId;
    public String mPath;
    public String mUUid;
    public boolean isNetWorkBitmapDownload = false;
    public ICallBack mSucCallBackLoad = null;
    public ICallBack mErrCallBackLoad = null;
    public Handler mHandler = new Handler() { // from class: io.dcloud.feature.nativeObj.NativeBitmap.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 10) {
                if (i == 40 && NativeBitmap.this.mErrCallBackLoad != null) {
                    NativeBitmap.this.mErrCallBackLoad.onCallBack(0, message.obj);
                }
            } else if (NativeBitmap.this.mSucCallBackLoad != null) {
                NativeBitmap.this.mSucCallBackLoad.onCallBack(0, message.obj);
            }
            super.handleMessage(message);
        }
    };

    public NativeBitmap(IApp iApp, String str, String str2, String str3) {
        this.mExt = BitmapUtils.IMAGE_KEY_SUFFIX;
        this.mId = str;
        this.mUUid = str2;
        this.mPath = str3;
        ImageLoaderUtil.addNetIconDownloadUrl(str3);
        this.mExt = getExt(str3);
        this.mApp = iApp;
    }

    private String bitmap2String(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(getCF(), 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    private Bitmap.CompressFormat getCF() {
        return "png".equals(this.mExt) ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
    }

    private String getExt(String str) {
        if (PdrUtil.isNetPath(str)) {
            if (str.contains(FileUtils.JPEG_FILE_SUFFIX)) {
                return BitmapUtils.IMAGE_KEY_SUFFIX;
            }
            if (str.contains(".png")) {
                return "png";
            }
            if (str.contains(".gif")) {
                return "gif";
            }
            if (str.contains(".webp")) {
                return "webp";
            }
        }
        return TextUtils.isEmpty(str) ? str : str.substring(str.lastIndexOf(Operators.DOT_STR) + 1, str.length()).toLowerCase();
    }

    private void getExtFromBase64(String str) {
        if (str.indexOf(",") != -1) {
            this.mExt = str.split(",")[0].replace("data:image/", "").replace(DataUrlLoader.BASE64_TAG, "");
        }
    }

    private String getFilePath(String str) {
        Uri fileUri;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!TextUtils.isEmpty(str) && PdrUtil.isNetPath(str)) {
            return str;
        }
        if (this.mApp != null) {
            File file = new File(str);
            if (file.exists() || str.startsWith("/storage")) {
                Context context = DCLoudApplicationImpl.self().getContext();
                if (FileUtil.needMediaStoreOpenFile(context) && !FileUtil.checkPrivatePath(context, this.mPath) && (fileUri = FileUtil.getFileUri(context, file, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)) != null) {
                    return fileUri.toString();
                }
            } else if (this.mApp.obtainRunningAppMode() == 1) {
                if (str.startsWith("/")) {
                    str = str.substring(1, str.length());
                }
                return ImageDownloader.Scheme.ASSETS.wrap(str);
            }
        }
        if (str.contains("file://")) {
            return str;
        }
        return "file://" + str;
    }

    private yw0 getImageOptions() {
        yw0.b bVar = new yw0.b();
        bVar.a(true);
        bVar.a(Bitmap.Config.RGB_565);
        bVar.a(ImageScaleType.NONE);
        return bVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveFile(String str, Bitmap bitmap, NativeBitmapSaveOptions nativeBitmapSaveOptions) throws Exception {
        if (bitmap == null || bitmap.getHeight() == 0 || bitmap.getWidth() == 0) {
            return;
        }
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.createNewFile();
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        bitmap.compress("png".equals(getExt(str)) ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, nativeBitmapSaveOptions.mQuality, bufferedOutputStream);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        nativeBitmapSaveOptions.path = file2.getAbsolutePath();
        nativeBitmapSaveOptions.width = bitmap.getWidth();
        nativeBitmapSaveOptions.height = bitmap.getHeight();
        nativeBitmapSaveOptions.size = file2.length();
    }

    private Bitmap string2Bitmap(String str) {
        if (str.indexOf(",") != -1) {
            str = str.substring(str.indexOf(","));
        }
        byte[] bArrDecode = Base64.decode(str, 2);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    @Override // io.dcloud.common.DHInterface.INativeBitmap
    @JavascriptInterface
    public void clear() {
        recycle();
        this.mBitmap = null;
        this.mGifDrawable = null;
    }

    @Override // io.dcloud.common.DHInterface.INativeBitmap
    public Bitmap getBitmap() {
        if (isRecycled()) {
            String filePath = getFilePath(this.mPath);
            if (TextUtils.isEmpty(filePath)) {
                return null;
            }
            if (PdrUtil.isNetPath(filePath) && !this.isNetWorkBitmapDownload) {
                return null;
            }
            this.mBitmap = cx0.f().a(filePath, getImageOptions());
        }
        return this.mBitmap;
    }

    public GifDrawable getGifDrawable() {
        GifDrawable gifDrawable = this.mGifDrawable;
        if ((gifDrawable == null || (gifDrawable != null && gifDrawable.isRecycled())) && !PdrUtil.isEmpty(this.mPath) && this.mApp != null) {
            if (!PdrUtil.isNetPath(this.mPath)) {
                File file = new File(this.mPath);
                if (this.mApp.obtainRunningAppMode() == 1 && !file.exists()) {
                    String strSubstring = this.mPath;
                    if (strSubstring.startsWith("/")) {
                        String str = this.mPath;
                        strSubstring = str.substring(1, str.length());
                    }
                    try {
                        this.mGifDrawable = new GifDrawable(this.mApp.getActivity().getAssets(), strSubstring);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } else if (file.exists()) {
                    try {
                        this.mGifDrawable = new GifDrawable(file);
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } else {
                if (!this.isNetWorkBitmapDownload) {
                    return null;
                }
                File fileA = cx0.f().c().a(this.mPath);
                if (fileA.exists()) {
                    try {
                        this.mGifDrawable = new GifDrawable(fileA);
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }
        return this.mGifDrawable;
    }

    @JavascriptInterface
    public String getId() {
        return this.mId;
    }

    public Bitmap initNetworkBitmap(hx0 hx0Var) {
        if (isRecycled()) {
            this.isNetWorkBitmapDownload = false;
            String filePath = getFilePath(this.mPath);
            if (TextUtils.isEmpty(filePath)) {
                return null;
            }
            cx0.f().a(filePath, hx0Var);
        }
        return this.mBitmap;
    }

    public boolean isGif() {
        if (PdrUtil.isEmpty(this.mExt)) {
            return false;
        }
        return this.mExt.equalsIgnoreCase("gif");
    }

    public boolean isNetWorkBitmap() {
        if (TextUtils.isEmpty(this.mPath) || !PdrUtil.isNetPath(this.mPath)) {
            return false;
        }
        return (this.isNetWorkBitmapDownload && cx0.f().c().a(this.mPath).exists()) ? false : true;
    }

    public boolean isRecycled() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            return bitmap.isRecycled();
        }
        GifDrawable gifDrawable = this.mGifDrawable;
        if (gifDrawable != null) {
            return gifDrawable.isRecycled();
        }
        return true;
    }

    @JavascriptInterface
    public void load(IWebview iWebview, Context context, String str, final ICallBack iCallBack, final ICallBack iCallBack2) {
        this.isNetWorkBitmapDownload = false;
        if (TextUtils.isEmpty(str)) {
            this.mErrCallBackLoad = iCallBack2;
            Message messageObtainMessage = this.mHandler.obtainMessage();
            messageObtainMessage.what = 40;
            messageObtainMessage.obj = context.getString(R.string.dcloud_native_obj_path_cannot_empty);
            this.mHandler.sendMessage(messageObtainMessage);
            return;
        }
        if (str.toLowerCase().startsWith(DeviceInfo.HTTP_PROTOCOL) || str.toLowerCase().startsWith(DeviceInfo.HTTPS_PROTOCOL) || str.toLowerCase().startsWith("ftp://")) {
            this.mErrCallBackLoad = iCallBack2;
            Message messageObtainMessage2 = this.mHandler.obtainMessage();
            messageObtainMessage2.what = 40;
            messageObtainMessage2.obj = context.getString(R.string.dcloud_native_obj_path_not_network);
            this.mHandler.sendMessage(messageObtainMessage2);
            return;
        }
        str.toLowerCase().startsWith("_");
        this.mPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), str);
        if (FileUtil.checkPathAccord(iWebview.getContext(), this.mPath) || FileUtil.isFilePathForPublic(context, this.mPath)) {
            this.mExt = getExt(str);
            cx0.f().a(getFilePath(this.mPath), getImageOptions(), new hx0() { // from class: io.dcloud.feature.nativeObj.NativeBitmap.1
                @Override // supwisdom.hx0
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // supwisdom.hx0
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    NativeBitmap.this.mBitmap = bitmap;
                    NativeBitmap.this.mSucCallBackLoad = iCallBack;
                    NativeBitmap.this.mHandler.sendEmptyMessage(10);
                }

                @Override // supwisdom.hx0
                public void onLoadingFailed(String str2, View view, FailReason failReason) {
                    NativeBitmap.this.mErrCallBackLoad = iCallBack2;
                    NativeBitmap.this.mHandler.sendEmptyMessage(40);
                }

                @Override // supwisdom.hx0
                public void onLoadingStarted(String str2, View view) {
                }
            });
            return;
        }
        this.mErrCallBackLoad = iCallBack2;
        Message messageObtainMessage3 = this.mHandler.obtainMessage();
        messageObtainMessage3.what = 40;
        messageObtainMessage3.obj = DOMException.MSG_PATH_NOT_PRIVATE_ERROR;
        this.mHandler.sendMessage(messageObtainMessage3);
    }

    @JavascriptInterface
    public void loadBase64Data(String str, ICallBack iCallBack, ICallBack iCallBack2) {
        try {
            Bitmap bitmapString2Bitmap = string2Bitmap(str);
            this.mBitmap = bitmapString2Bitmap;
            if (bitmapString2Bitmap != null) {
                getExtFromBase64(str);
                this.mSucCallBackLoad = iCallBack;
                this.mHandler.sendEmptyMessage(10);
            } else {
                this.mErrCallBackLoad = iCallBack2;
                this.mHandler.sendEmptyMessage(40);
            }
        } catch (Exception unused) {
            this.mErrCallBackLoad = iCallBack2;
            this.mHandler.sendEmptyMessage(40);
        }
    }

    public void recycle() {
        recycle(false);
    }

    @JavascriptInterface
    public void save(IApp iApp, String str, final NativeBitmapSaveOptions nativeBitmapSaveOptions, final float f, final ICallBack iCallBack, final ICallBack iCallBack2) {
        if (TextUtils.isEmpty(str)) {
            this.mErrCallBackLoad = iCallBack2;
            Message messageObtainMessage = this.mHandler.obtainMessage();
            messageObtainMessage.what = 40;
            messageObtainMessage.obj = iApp.getActivity().getString(R.string.dcloud_native_obj_path_cannot_empty);
            this.mHandler.sendMessage(messageObtainMessage);
            return;
        }
        if (!str.toLowerCase().startsWith(DeviceInfo.HTTP_PROTOCOL) && !str.toLowerCase().startsWith(DeviceInfo.HTTPS_PROTOCOL) && !str.toLowerCase().startsWith("ftp://")) {
            final String str2 = this.mPath;
            this.mPath = str;
            new Thread() { // from class: io.dcloud.feature.nativeObj.NativeBitmap.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        NativeBitmap.this.getBitmap();
                        if (nativeBitmapSaveOptions.mClip != null) {
                            int width = NativeBitmap.this.mBitmap.getWidth();
                            int height = NativeBitmap.this.mBitmap.getHeight();
                            int iConvertToScreenInt = PdrUtil.convertToScreenInt(nativeBitmapSaveOptions.mClip.optString("left"), width, 0, f);
                            int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(nativeBitmapSaveOptions.mClip.optString("top"), height, 0, f);
                            int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(nativeBitmapSaveOptions.mClip.optString("width"), width, width, f);
                            int iConvertToScreenInt4 = PdrUtil.convertToScreenInt(nativeBitmapSaveOptions.mClip.optString("height"), height, height, f);
                            if (iConvertToScreenInt + iConvertToScreenInt3 > width) {
                                iConvertToScreenInt3 = width - iConvertToScreenInt;
                            }
                            if (iConvertToScreenInt2 + iConvertToScreenInt4 > height) {
                                iConvertToScreenInt4 = height - iConvertToScreenInt2;
                            }
                            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(NativeBitmap.this.mBitmap, iConvertToScreenInt, iConvertToScreenInt2, iConvertToScreenInt3, iConvertToScreenInt4);
                            NativeBitmap nativeBitmap = NativeBitmap.this;
                            nativeBitmap.saveFile(nativeBitmap.mPath, bitmapCreateBitmap, nativeBitmapSaveOptions);
                            bitmapCreateBitmap.recycle();
                            NativeBitmap.this.mPath = str2;
                        } else {
                            NativeBitmap nativeBitmap2 = NativeBitmap.this;
                            nativeBitmap2.saveFile(nativeBitmap2.mPath, NativeBitmap.this.mBitmap, nativeBitmapSaveOptions);
                        }
                        NativeBitmap.this.mSucCallBackLoad = iCallBack;
                        Message messageObtainMessage2 = NativeBitmap.this.mHandler.obtainMessage();
                        messageObtainMessage2.what = 10;
                        messageObtainMessage2.obj = nativeBitmapSaveOptions;
                        NativeBitmap.this.mHandler.sendMessage(messageObtainMessage2);
                    } catch (Exception e2) {
                        NativeBitmap.this.mErrCallBackLoad = iCallBack2;
                        NativeBitmap.this.mHandler.sendEmptyMessage(40);
                        Logger.e("mabo", "saveFile: " + e2.toString());
                    }
                }
            }.start();
        } else {
            this.mErrCallBackLoad = iCallBack2;
            Message messageObtainMessage2 = this.mHandler.obtainMessage();
            messageObtainMessage2.what = 40;
            messageObtainMessage2.obj = iApp.getActivity().getString(R.string.dcloud_native_obj_path_not_network);
            this.mHandler.sendMessage(messageObtainMessage2);
        }
    }

    @Override // io.dcloud.common.DHInterface.INativeBitmap
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setNetWorkBitmapDownload(boolean z) {
        this.isNetWorkBitmapDownload = z;
    }

    @JavascriptInterface
    public String toBase64Data() {
        StringBuilder sb = new StringBuilder();
        sb.append("data:image/");
        sb.append(BitmapUtils.IMAGE_KEY_SUFFIX.equals(this.mExt) ? "jepg" : this.mExt);
        sb.append(";base64,");
        sb.append(bitmap2String(this.mBitmap));
        return sb.toString();
    }

    @JavascriptInterface
    public String toJsString() {
        return "{\"id\":\"" + this.mId + "\",\"__id__\":\"" + this.mUUid + JSUtil.QUOTE + Operators.BLOCK_END_STR;
    }

    public void recycle(boolean z) {
        if (z && TextUtils.isEmpty(this.mPath)) {
            return;
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBitmap.recycle();
            String filePath = getFilePath(this.mPath);
            if (!TextUtils.isEmpty(filePath)) {
                jx0.a(filePath, cx0.f().d());
            }
        }
        GifDrawable gifDrawable = this.mGifDrawable;
        if (gifDrawable == null || gifDrawable.isRecycled()) {
            return;
        }
        this.mGifDrawable.recycle();
    }
}
