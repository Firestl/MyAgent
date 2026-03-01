package supwisdom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: WeChatShareUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class kn1 {
    public static kn1 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IWXAPI f8188a;
    public Context b;

    public static kn1 a(Context context) {
        if (c == null) {
            c = new kn1();
        }
        IWXAPI iwxapi = c.f8188a;
        if (iwxapi != null) {
            iwxapi.unregisterApp();
        }
        kn1 kn1Var = c;
        kn1Var.b = context;
        kn1Var.b();
        return c;
    }

    public final void b() {
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this.b, com.igexin.push.core.b.m, true);
        this.f8188a = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.registerApp(com.igexin.push.core.b.m);
    }

    public boolean a(String str, String str2, Bitmap bitmap, String str3, int i) {
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str;
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        bitmap.recycle();
        return a(wXWebpageObject, str2, bitmapCreateScaledBitmap, str3, i);
    }

    public final boolean a(WXMediaMessage.IMediaObject iMediaObject, String str, Bitmap bitmap, String str2, int i) {
        WXMediaMessage wXMediaMessage = new WXMediaMessage(iMediaObject);
        if (str != null) {
            wXMediaMessage.title = str;
        }
        if (str2 != null) {
            wXMediaMessage.description = str2;
        }
        if (bitmap != null) {
            wXMediaMessage.thumbData = a(bitmap, true);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = i;
        return this.f8188a.sendReq(req);
    }

    public boolean a() {
        return this.f8188a.getWXAppSupportAPI() >= 654314752;
    }

    public final byte[] a(Bitmap bitmap, boolean z) {
        int height;
        int height2;
        if (bitmap.getHeight() > bitmap.getWidth()) {
            height = bitmap.getWidth();
            height2 = bitmap.getWidth();
        } else {
            height = bitmap.getHeight();
            height2 = bitmap.getHeight();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(height, height2, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        while (true) {
            canvas.drawBitmap(bitmap, new Rect(0, 0, height, height2), new Rect(0, 0, height, height2), (Paint) null);
            if (z) {
                bitmap.recycle();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            bitmapCreateBitmap.recycle();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Exception unused) {
                height = bitmap.getHeight();
                height2 = bitmap.getHeight();
            }
        }
    }
}
