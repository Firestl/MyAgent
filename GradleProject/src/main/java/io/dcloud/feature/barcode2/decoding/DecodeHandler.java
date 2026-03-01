package io.dcloud.feature.barcode2.decoding;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ReaderException;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.barcode2.BarcodeProxy;
import io.dcloud.feature.barcode2.camera.CameraManager;
import io.dcloud.feature.barcode2.camera.PlanarYUVLuminanceSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Vector;
import supwisdom.kw;
import supwisdom.sv;
import supwisdom.vv;
import supwisdom.xv;
import supwisdom.zv;

/* JADX INFO: loaded from: classes2.dex */
public final class DecodeHandler extends Handler {
    public static final String TAG = DecodeHandler.class.getSimpleName();
    public static boolean mIsVerticalScreen = true;
    public final IBarHandler activity;
    public final vv multiFormatReader;

    public DecodeHandler(IBarHandler iBarHandler, Hashtable<DecodeHintType, Object> hashtable) {
        vv vvVar = new vv();
        this.multiFormatReader = vvVar;
        vvVar.a(hashtable);
        this.activity = iBarHandler;
    }

    private void decode(byte[] bArr, int i, int i2) {
        xv xvVarB;
        PlanarYUVLuminanceSource planarYUVLuminanceSourceBuildLuminanceSource = CameraManager.get().buildLuminanceSource(bArr, i, i2);
        try {
            xvVarB = this.multiFormatReader.b(new sv(new kw(planarYUVLuminanceSourceBuildLuminanceSource)));
            this.multiFormatReader.reset();
        } catch (ReaderException unused) {
            this.multiFormatReader.reset();
            xvVarB = null;
        } catch (Throwable th) {
            this.multiFormatReader.reset();
            throw th;
        }
        xv xvVar = xvVarB;
        if (BarcodeProxy.save) {
            Camera.Parameters parameters = CameraManager.get().getCameraHandler().getParameters();
            try {
                Camera.Size previewSize = parameters.getPreviewSize();
                YuvImage yuvImage = new YuvImage(bArr, parameters.getPreviewFormat(), previewSize.width, previewSize.height, null);
                yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), 90, new FileOutputStream(new File("/sdcard/1/" + System.currentTimeMillis() + "--" + previewSize.width + Operators.MUL + previewSize.height + FileUtils.JPEG_FILE_SUFFIX)));
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            Bitmap bitmapRenderCroppedGreyscaleBitmap = planarYUVLuminanceSourceBuildLuminanceSource.renderCroppedGreyscaleBitmap(true);
            Rect framingRectInPreview = CameraManager.get().getFramingRectInPreview();
            PdrUtil.saveBitmapToFile(bitmapRenderCroppedGreyscaleBitmap, "/sdcard/1/" + System.currentTimeMillis() + "--" + framingRectInPreview.left + Operators.MUL + framingRectInPreview.top + ".png");
            BarcodeProxy.save = false;
            Activity activity = (Activity) BarcodeProxy.context;
            StringBuilder sb = new StringBuilder();
            sb.append("成功 left=");
            sb.append(framingRectInPreview.left);
            sb.append("top:");
            sb.append(framingRectInPreview.top);
            PdrUtil.alert(activity, sb.toString(), bitmapRenderCroppedGreyscaleBitmap);
        }
        if (xvVar == null) {
            Message.obtain(this.activity.getHandler(), 1001).sendToTarget();
            return;
        }
        Message messageObtain = Message.obtain(this.activity.getHandler(), 1002, xvVar);
        Bundle bundle = new Bundle();
        Bitmap bitmapRenderCroppedGreyscaleBitmap2 = planarYUVLuminanceSourceBuildLuminanceSource.renderCroppedGreyscaleBitmap(true);
        if (!mIsVerticalScreen) {
            Matrix matrix = new Matrix();
            matrix.postRotate(-90.0f);
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapRenderCroppedGreyscaleBitmap2, bitmapRenderCroppedGreyscaleBitmap2.getWidth(), bitmapRenderCroppedGreyscaleBitmap2.getHeight(), true);
            bitmapRenderCroppedGreyscaleBitmap2 = Bitmap.createBitmap(bitmapCreateScaledBitmap, 0, 0, bitmapCreateScaledBitmap.getWidth(), bitmapCreateScaledBitmap.getHeight(), matrix, true);
        }
        bundle.putParcelable("barcode_bitmap", bitmapRenderCroppedGreyscaleBitmap2);
        messageObtain.setData(bundle);
        messageObtain.sendToTarget();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1003:
                Looper.myLooper().quit();
                break;
            case 1004:
                decode((byte[]) message.obj, message.arg1, message.arg2);
                break;
            case 1005:
                mIsVerticalScreen = true;
                decode((byte[]) message.obj, message.arg1, message.arg2);
                break;
            case 1006:
                mIsVerticalScreen = false;
                decode((byte[]) message.obj, message.arg1, message.arg2);
                break;
        }
    }

    public static xv decode(Bitmap bitmap, zv zvVar, boolean z) {
        vv vvVar = new vv();
        Hashtable hashtable = new Hashtable(4);
        Vector vector = new Vector();
        if (vector.isEmpty()) {
            vector = new Vector();
            vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
            vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hashtable.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        DecodeHintType decodeHintType = DecodeHintType.TRY_HARDER;
        Boolean bool = Boolean.TRUE;
        hashtable.put(decodeHintType, bool);
        if (zvVar != null) {
            hashtable.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, zvVar);
        }
        if (z) {
            hashtable.put(DecodeHintType.autoDecodeCharset, bool);
        }
        vvVar.a(hashtable);
        try {
            return vvVar.b(new sv(new kw(new BitmapLuminanceSource(bitmap))));
        } catch (NotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static xv decode(Bitmap bitmap, boolean z) {
        return decode(bitmap, (zv) null, z);
    }
}
