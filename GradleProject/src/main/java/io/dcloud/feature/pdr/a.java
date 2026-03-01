package io.dcloud.feature.pdr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.text.TextUtils;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.taobao.weex.common.Constants;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.FileUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: io.dcloud.feature.pdr.a$a, reason: collision with other inner class name */
    public static class RunnableC0175a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6580a;
        public final /* synthetic */ String[] b;

        public RunnableC0175a(IWebview iWebview, String[] strArr) {
            this.f6580a = iWebview;
            this.b = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.a(this.f6580a, this.b);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f6581a;
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f6582e;
        public float f;

        public b(String str, String str2, String str3, String str4, float f, float f2) {
            this.f6582e = f;
            this.f = f2;
            this.f6581a = a.a(str, f2, 0.0f);
            float fA = a.a(str2, this.f6582e, 0.0f);
            this.b = fA;
            float f3 = this.f6582e;
            this.c = a.a(str3, f3, f3 - fA);
            float f4 = this.f;
            float fA2 = a.a(str4, f4, f4 - this.f6581a);
            this.d = fA2;
            float f5 = this.c;
            float f6 = this.b;
            float f7 = f5 + f6;
            float f8 = this.f6582e;
            if (f7 > f8) {
                this.c = f8 - f6;
            }
            float f9 = this.f6581a;
            float f10 = fA2 + f9;
            float f11 = this.f;
            if (f10 > f11) {
                this.d = f11 - f9;
            }
        }

        public boolean a() {
            return this.f6581a <= this.f && this.b <= this.f6582e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x019b A[Catch: all -> 0x020d, TryCatch #3 {, blocks: (B:5:0x0006, B:7:0x000c, B:8:0x0011, B:12:0x001e, B:14:0x0029, B:15:0x002d, B:17:0x0037, B:19:0x003c, B:21:0x0055, B:28:0x00a4, B:31:0x00ab, B:33:0x00af, B:35:0x00b6, B:37:0x00bc, B:39:0x00e9, B:41:0x0105, B:42:0x0110, B:44:0x012e, B:46:0x014e, B:52:0x016b, B:54:0x019b, B:59:0x01f7, B:61:0x01fd, B:55:0x01e0, B:57:0x01e6, B:58:0x01e9, B:48:0x0154, B:50:0x0167, B:23:0x0060, B:24:0x0096, B:18:0x003a, B:65:0x0203), top: B:77:0x0006, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01e0 A[Catch: all -> 0x020d, TryCatch #3 {, blocks: (B:5:0x0006, B:7:0x000c, B:8:0x0011, B:12:0x001e, B:14:0x0029, B:15:0x002d, B:17:0x0037, B:19:0x003c, B:21:0x0055, B:28:0x00a4, B:31:0x00ab, B:33:0x00af, B:35:0x00b6, B:37:0x00bc, B:39:0x00e9, B:41:0x0105, B:42:0x0110, B:44:0x012e, B:46:0x014e, B:52:0x016b, B:54:0x019b, B:59:0x01f7, B:61:0x01fd, B:55:0x01e0, B:57:0x01e6, B:58:0x01e9, B:48:0x0154, B:50:0x0167, B:23:0x0060, B:24:0x0096, B:18:0x003a, B:65:0x0203), top: B:77:0x0006, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01fd A[Catch: all -> 0x020d, TRY_LEAVE, TryCatch #3 {, blocks: (B:5:0x0006, B:7:0x000c, B:8:0x0011, B:12:0x001e, B:14:0x0029, B:15:0x002d, B:17:0x0037, B:19:0x003c, B:21:0x0055, B:28:0x00a4, B:31:0x00ab, B:33:0x00af, B:35:0x00b6, B:37:0x00bc, B:39:0x00e9, B:41:0x0105, B:42:0x0110, B:44:0x012e, B:46:0x014e, B:52:0x016b, B:54:0x019b, B:59:0x01f7, B:61:0x01fd, B:55:0x01e0, B:57:0x01e6, B:58:0x01e9, B:48:0x0154, B:50:0x0167, B:23:0x0060, B:24:0x0096, B:18:0x003a, B:65:0x0203), top: B:77:0x0006, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(io.dcloud.common.DHInterface.IWebview r20, java.lang.String[] r21) {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.pdr.a.a(io.dcloud.common.DHInterface.IWebview, java.lang.String[]):void");
    }

    public static void b(IWebview iWebview, String[] strArr) {
        ThreadPool.self().addThreadTask(new RunnableC0175a(iWebview, strArr));
    }

    public static Bitmap.CompressFormat c(String str) {
        return (str.contains(FileUtils.JPEG_FILE_SUFFIX) || str.contains(".jpeg")) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
    }

    public static boolean b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                if (file.length() >= 5) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6583a;
        public String b;
        public boolean c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f6584e;
        public float f;
        public int g;
        public int h;
        public int i;
        public b j;
        public boolean k = false;
        public long l;

        public boolean a(JSONObject jSONObject, IWebview iWebview, String str) {
            this.f6583a = jSONObject.optString("src");
            this.b = jSONObject.optString("dst");
            if (!a(iWebview, str)) {
                return false;
            }
            this.c = jSONObject.optBoolean("overwrite", false);
            jSONObject.optString("format");
            this.d = jSONObject.optInt(Constants.Name.QUALITY, -1);
            a(iWebview.getContext(), jSONObject.optString("width", "auto"), jSONObject.optString("height", "auto"));
            this.g = jSONObject.optInt("rotate", -1);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("clip");
            if (jSONObjectOptJSONObject == null) {
                return true;
            }
            b bVar = new b(jSONObjectOptJSONObject.optString("top"), jSONObjectOptJSONObject.optString("left"), jSONObjectOptJSONObject.optString("width"), jSONObjectOptJSONObject.optString("height"), this.f6584e, this.f);
            this.j = bVar;
            if (bVar.a()) {
                return true;
            }
            a.a(iWebview, str, DOMException.MSG_PARAMETER_ERROR, -1);
            return false;
        }

        public boolean a(IWebview iWebview, String str) {
            if (!TextUtils.isEmpty(this.f6583a) && !TextUtils.isEmpty(this.b)) {
                String strConvert2AbsFullPath = iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), this.f6583a);
                this.f6583a = strConvert2AbsFullPath;
                if (!a.b(strConvert2AbsFullPath)) {
                    a.a(iWebview, str, DOMException.MSG_FILE_NOT_EXIST, -4);
                    return false;
                }
                this.b = iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), this.b);
                return true;
            }
            a.a(iWebview, str, DOMException.MSG_PARAMETER_ERROR, -1);
            return false;
        }

        public void a(Context context, String str, String str2) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                a.a(context, this.f6583a, options);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            int i = options.outWidth;
            this.h = i;
            int i2 = options.outHeight;
            this.i = i2;
            if (i2 <= 0 || i <= 0) {
                return;
            }
            this.l = new File(this.f6583a).length();
            if (str.equals("auto") && str2.endsWith("auto")) {
                this.k = false;
                this.f6584e = this.h;
                this.f = this.i;
                return;
            }
            if (str.equals("auto")) {
                this.k = true;
                float f = this.i;
                float f2 = PdrUtil.parseFloat(str2, f, f);
                this.f = f2;
                this.f6584e = (this.h * f2) / this.i;
                return;
            }
            if (str2.equals("auto")) {
                this.k = true;
                float f3 = this.h;
                float f4 = PdrUtil.parseFloat(str, f3, f3);
                this.f6584e = f4;
                this.f = (this.i * f4) / this.h;
                return;
            }
            this.k = true;
            float f5 = this.h;
            this.f6584e = PdrUtil.parseFloat(str, f5, f5);
            float f6 = this.i;
            this.f = PdrUtil.parseFloat(str2, f6, f6);
        }
    }

    public static void a(String str, String str2) {
        Object obj;
        String string;
        String attribute;
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            ExifInterface exifInterface2 = new ExifInterface(str2);
            for (Field field : ExifInterface.class.getFields()) {
                String name = field.getName();
                if (!TextUtils.isEmpty(name) && name.startsWith("TAG") && (obj = field.get(ExifInterface.class)) != null && (attribute = exifInterface.getAttribute((string = obj.toString()))) != null) {
                    exifInterface2.setAttribute(string, attribute);
                }
            }
            exifInterface2.saveAttributes();
        } catch (Throwable unused) {
        }
    }

    public static Bitmap a(Context context, String str, BitmapFactory.Options options) throws IOException {
        if (!FileUtil.checkPrivatePath(context, str)) {
            InputStream fileInputStream = FileUtil.getFileInputStream(context, str);
            if (fileInputStream == null) {
                return null;
            }
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
            return bitmapDecodeStream;
        }
        return BitmapFactory.decodeFile(str, options);
    }

    public static void a(IWebview iWebview, String str, String str2, int i) {
        Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(i, str2), JSUtil.ERROR, true, false);
    }

    public static void a(IWebview iWebview, String str, String str2) {
        Deprecated_JSUtil.execCallback(iWebview, str, str2, JSUtil.OK, true, false);
    }

    public static long a(String str, Bitmap bitmap, boolean z, int i) {
        File file = new File(str);
        if (file.exists()) {
            if (file.length() >= 1 && !z) {
                return -1L;
            }
            file.delete();
        } else if (a(str)) {
            file = new File(str);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(c(str), i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return file.length();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return -1L;
        } catch (IOException e3) {
            e3.printStackTrace();
            return -1L;
        }
    }

    public static boolean a(String str) {
        int iLastIndexOf;
        if (!TextUtils.isEmpty(str) && (iLastIndexOf = str.lastIndexOf("/")) != -1 && iLastIndexOf != 0) {
            try {
                File file = new File(str.substring(0, iLastIndexOf));
                if (file.exists()) {
                    return true;
                }
                file.mkdirs();
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static float a(String str, float f, float f2) {
        return str.equals("auto") ? f2 : PdrUtil.parseFloat(str, f, f2);
    }
}
