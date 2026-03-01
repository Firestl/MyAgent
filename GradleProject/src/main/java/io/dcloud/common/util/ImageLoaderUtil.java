package io.dcloud.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.nostra13.dcloudimageloader.core.assist.QueueProcessingType;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.src.dcloud.adapter.DCloudAdapterUtil;
import java.io.File;
import java.util.ArrayList;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import supwisdom.ax0;
import supwisdom.cx0;
import supwisdom.hx0;
import supwisdom.nw0;
import supwisdom.ox0;
import supwisdom.tx0;
import supwisdom.vw0;
import supwisdom.yw0;
import supwisdom.zw0;

/* JADX INFO: loaded from: classes2.dex */
public class ImageLoaderUtil {
    public static ArrayList<String> downloadUrls = new ArrayList<>();

    public static void addNetIconDownloadUrl(String str) {
        if (!PdrUtil.isNetPath(str) || downloadUrls.contains(str)) {
            return;
        }
        downloadUrls.add(str);
    }

    public static void clearCache() {
        downloadUrls.clear();
        cx0.f().b();
        zw0.f().b();
    }

    public static yw0 getIconDisplayOptions(Context context) {
        yw0.b bVar = new yw0.b();
        bVar.b(true);
        bVar.a(true);
        bVar.a(ImageScaleType.IN_SAMPLE_INT);
        bVar.a(Bitmap.Config.RGB_565);
        bVar.a(new ColorDrawable(0));
        return bVar.a();
    }

    public static String getIconLoaclfolder() {
        return DeviceInfo.sBaseFsRootPath + "icons/";
    }

    public static String getOtherImageLoaclfolder() {
        return DeviceInfo.sBaseFsRootPath + "images/";
    }

    public static yw0 getStreamIconDisplayOptions(Context context) {
        yw0.b bVar = new yw0.b();
        bVar.b(true);
        bVar.a(true);
        bVar.a(ImageScaleType.IN_SAMPLE_INT);
        bVar.a(Bitmap.Config.RGB_565);
        bVar.a(DCloudAdapterUtil.getImageOnLoadingId(context));
        return bVar.a();
    }

    public static void initImageLoader(Context context) {
        if (zw0.f().e()) {
            return;
        }
        File file = new File(getIconLoaclfolder());
        if (!file.exists()) {
            file.mkdirs();
        }
        ax0.b bVar = new ax0.b(context);
        bVar.a(400, 400);
        bVar.a(QueueProcessingType.FIFO);
        bVar.b();
        bVar.a(new vw0(PKIFailureInfo.badSenderNonce));
        bVar.b(PKIFailureInfo.badSenderNonce);
        bVar.d(3);
        bVar.c(3);
        bVar.b();
        bVar.a(getIconDisplayOptions(context));
        bVar.a(new nw0(file));
        bVar.a(new tx0(context));
        bVar.a(new ox0(false));
        zw0.f().a(bVar.a());
    }

    public static void initImageLoaderL(Context context) {
        if (cx0.f().e()) {
            return;
        }
        File file = new File(getOtherImageLoaclfolder());
        if (!file.exists()) {
            file.mkdirs();
        }
        ax0.b bVar = new ax0.b(context);
        bVar.a(400, 400);
        bVar.a(QueueProcessingType.LIFO);
        bVar.b();
        bVar.a(new vw0(PKIFailureInfo.badSenderNonce));
        bVar.b(PKIFailureInfo.badSenderNonce);
        bVar.d(3);
        bVar.c(3);
        bVar.a(100);
        bVar.b();
        bVar.a(getIconDisplayOptions(context));
        bVar.a(new nw0(file));
        bVar.a(new tx0(context));
        bVar.a(new ox0(false));
        cx0.f().a(bVar.a());
    }

    public static boolean isDownload(String str) {
        return downloadUrls.contains(str);
    }

    public static void updateIcon(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File fileA = zw0.f().c().a(str);
        if (fileA.exists()) {
            fileA.delete();
        }
        zw0.f().a(str, (hx0) null);
    }
}
