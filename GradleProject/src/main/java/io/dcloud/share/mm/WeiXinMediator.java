package io.dcloud.share.mm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.dcloud.ProcessMediator;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.PdrUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class WeiXinMediator implements ProcessMediator.Logic {
    public static String WX_MINI_PROGRAM_KEY = "isLaunchMiniProgram";
    public String APPID;
    public IWXAPI api;

    private byte[] buildThumbData(Context context, String str, String str2, int i) {
        Bitmap bitmapScaleLoadPic;
        try {
            if (PdrUtil.isNetPath(str)) {
                InputStream inputStreamOpenStream = new URL(str).openStream();
                bitmapScaleLoadPic = inputStreamOpenStream != null ? BitmapFactory.decodeStream(inputStreamOpenStream) : null;
            } else {
                bitmapScaleLoadPic = scaleLoadPic(context, str2);
            }
            if (bitmapScaleLoadPic != null) {
                bitmapScaleLoadPic = cpBitmap(bitmapScaleLoadPic);
            }
            return WeiXinApiManager.bmpToByteArray(bitmapScaleLoadPic, true);
        } catch (Exception e2) {
            Logger.e("buildThumbData Exception=" + e2);
            return null;
        }
    }

    public static Bitmap cpBitmap(Bitmap bitmap) {
        if (PdrUtil.isEmpty(bitmap)) {
            return null;
        }
        while (bitmap.getHeight() * bitmap.getRowBytes() >= 32768) {
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (bitmap.getWidth() * 2) / 3, (bitmap.getHeight() * 2) / 3, true);
            if (bitmap != bitmapCreateScaledBitmap) {
                bitmap.recycle();
            }
            bitmap = bitmapCreateScaledBitmap;
        }
        return bitmap;
    }

    @Override // io.dcloud.ProcessMediator.Logic
    public void exec(Context context, Intent intent) {
        byte[] bArrBuildThumbData;
        String metaValue = AndroidResources.getMetaValue("WX_APPID");
        this.APPID = metaValue;
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(context, metaValue, true);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(this.APPID);
        Bundle bundleExtra = intent.getBundleExtra("req");
        if (intent.hasExtra(WX_MINI_PROGRAM_KEY) && intent.getBooleanExtra(WX_MINI_PROGRAM_KEY, false)) {
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = bundleExtra.getString("userName");
            req.path = bundleExtra.getString("path");
            req.miniprogramType = bundleExtra.getInt("miniprogramType");
            if (this.api.sendReq(req) || !(context instanceof ProcessMediator)) {
                return;
            }
            ((ProcessMediator) context).finish();
            return;
        }
        SendMessageToWX.Req req2 = new SendMessageToWX.Req();
        req2.fromBundle(bundleExtra);
        String string = bundleExtra.getString("pThumbImg");
        String string2 = bundleExtra.getString("pImg");
        String string3 = bundleExtra.getString("absFullPath");
        String string4 = bundleExtra.getString("AbsFullPathThumb");
        int i = bundleExtra.getInt("mRunningMode");
        if (PdrUtil.isEmpty(string) || PdrUtil.isEmpty(string2) || PdrUtil.isEmpty(string3) || PdrUtil.isEmpty(string4)) {
            this.api.sendReq(req2);
            return;
        }
        WXImageObject wXImageObject = new WXImageObject();
        Bitmap bitmapScaleLoadPic = null;
        if (PdrUtil.isNetPath(string)) {
            try {
                bitmapScaleLoadPic = BitmapFactory.decodeStream(new URL(string).openStream());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            wXImageObject.imageData = WeiXinApiManager.bmpToByteArray(bitmapScaleLoadPic, true);
            if (!PdrUtil.isEmpty(string2)) {
                string = string2;
            }
            bArrBuildThumbData = buildThumbData(context, string, string4, i);
        } else {
            try {
                bitmapScaleLoadPic = scaleLoadPic(context, string3);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            wXImageObject = new WXImageObject(bitmapScaleLoadPic);
            bArrBuildThumbData = buildThumbData(context, string2, string4, i);
        }
        WXMediaMessage wXMediaMessage = req2.message;
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.thumbData = bArrBuildThumbData;
        this.api.sendReq(req2);
    }

    public Bitmap scaleLoadPic(Context context, String str) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        if (FileUtil.checkPrivatePath(context, str)) {
            BitmapFactory.decodeFile(str, options);
        } else {
            InputStream imageFileStream = FileUtil.getImageFileStream(context, new File(str));
            if (imageFileStream != null) {
                BitmapFactory.decodeStream(imageFileStream, null, options);
                imageFileStream.close();
            }
        }
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = i2 / defaultDisplay.getWidth();
        int height = i3 / defaultDisplay.getHeight();
        if (width >= height && width >= 1) {
            i = width;
        } else if (width < height && height >= 1) {
            i = height;
        }
        options.inSampleSize = i;
        options.inJustDecodeBounds = false;
        if (FileUtil.checkPrivatePath(context, str)) {
            return BitmapFactory.decodeFile(str, options);
        }
        InputStream imageFileStream2 = FileUtil.getImageFileStream(context, new File(str));
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(imageFileStream2, null, options);
        imageFileStream2.close();
        return bitmapDecodeStream;
    }
}
