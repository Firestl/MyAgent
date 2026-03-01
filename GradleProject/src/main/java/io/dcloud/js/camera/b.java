package io.dcloud.js.camera;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IFeature;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class b {
    /* JADX WARN: Removed duplicated region for block: B:35:0x0042 A[Catch: Exception -> 0x003e, TRY_LEAVE, TryCatch #1 {Exception -> 0x003e, blocks: (B:31:0x003a, B:35:0x0042), top: B:39:0x003a }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x003a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.graphics.Bitmap r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L36
            r3 = 100
            r4.compress(r2, r3, r1)     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L36
            r1.close()     // Catch: java.lang.Exception -> L16
            if (r4 == 0) goto L1a
            r4.recycle()     // Catch: java.lang.Exception -> L16
            goto L1a
        L16:
            r4 = move-exception
            r4.printStackTrace()
        L1a:
            return r5
        L1b:
            r5 = move-exception
            goto L21
        L1d:
            r5 = move-exception
            goto L38
        L1f:
            r5 = move-exception
            r1 = r0
        L21:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r1 == 0) goto L2c
            r1.close()     // Catch: java.lang.Exception -> L2a
            goto L2c
        L2a:
            r4 = move-exception
            goto L32
        L2c:
            if (r4 == 0) goto L35
            r4.recycle()     // Catch: java.lang.Exception -> L2a
            goto L35
        L32:
            r4.printStackTrace()
        L35:
            return r0
        L36:
            r5 = move-exception
            r0 = r1
        L38:
            if (r0 == 0) goto L40
            r0.close()     // Catch: java.lang.Exception -> L3e
            goto L40
        L3e:
            r4 = move-exception
            goto L46
        L40:
            if (r4 == 0) goto L49
            r4.recycle()     // Catch: java.lang.Exception -> L3e
            goto L49
        L46:
            r4.printStackTrace()
        L49:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.js.camera.b.a(android.graphics.Bitmap, java.lang.String):java.lang.String");
    }

    public static Bitmap b(String str) {
        return BitmapFactory.decodeFile(str, new BitmapFactory.Options());
    }

    public static int c(String str) {
        int attributeInt;
        try {
            attributeInt = new ExifInterface(str).getAttributeInt(IFeature.F_ORIENTATION, 1);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (attributeInt == 3) {
            return 180;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0 : 270;
        }
        return 90;
    }

    public static String a(String str) {
        int iC = c(str);
        if (iC == 0) {
            return str;
        }
        Bitmap bitmapB = b(str);
        if (bitmapB == null) {
            return null;
        }
        return a(a(iC, bitmapB), str);
    }

    public static Bitmap a(int i, Bitmap bitmap) {
        Bitmap bitmapCreateBitmap;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = bitmap;
        }
        if (bitmap != bitmapCreateBitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Dialog a(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.dialog_transparent);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.dcloud_dialog_loading, (ViewGroup) null);
        viewGroup.findViewById(R.id.loading_background).setBackgroundColor(0);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setView(viewGroup, 0, 0, 0, 0);
        return alertDialogCreate;
    }
}
