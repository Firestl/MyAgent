package io.dcloud.feature.barcode2.decoding;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.dcloud.zxing2.BarcodeFormat;
import io.dcloud.feature.barcode2.camera.CameraManager;
import io.dcloud.feature.barcode2.view.ViewfinderResultPointCallback;
import java.util.ArrayList;
import java.util.Vector;
import supwisdom.xv;
import supwisdom.yv;
import supwisdom.zv;

/* JADX INFO: loaded from: classes2.dex */
public final class CaptureActivityHandler extends Handler {
    public static final int CODE_AUTO_FOCUS = 1000;
    public static final int CODE_DECODE = 1004;
    public static final int CODE_DECODE_FAILED = 1001;
    public static final int CODE_DECODE_SUCCEEDED = 1002;
    public static final int CODE_DECODE_landscape = 1006;
    public static final int CODE_DECODE_portrait = 1005;
    public static final int CODE_QUIT = 1003;
    public static final String TAG = CaptureActivityHandler.class.getSimpleName();
    public final IBarHandler activity;
    public final DecodeThread decodeThread;
    public State state;

    public enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    public CaptureActivityHandler(IBarHandler iBarHandler, Vector<BarcodeFormat> vector, String str, boolean z) {
        this.activity = iBarHandler;
        DecodeThread decodeThread = new DecodeThread(iBarHandler, vector, str, new ViewfinderResultPointCallback(iBarHandler.getViewfinderView()), z);
        this.decodeThread = decodeThread;
        decodeThread.start();
        this.state = State.SUCCESS;
        resume();
    }

    public static Bitmap convertToBMW(Bitmap bitmap, int i, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                int i6 = (width * i4) + i5;
                int i7 = iArr[i6];
                int i8 = (i7 & (-16777216)) >> 24;
                int i9 = (16711680 & i7) >> 16;
                int i10 = (65280 & i7) >> 8;
                iArr[i6] = ((i7 & 255) > i3 ? 255 : 0) | (i8 << 24) | ((i9 > i3 ? 255 : 0) << 16) | ((i10 <= i3 ? 0 : 255) << 8);
                if (iArr[i6] == -1) {
                    iArr[i6] = -1;
                } else {
                    iArr[i6] = -16777216;
                }
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return ThumbnailUtils.extractThumbnail(bitmapCreateBitmap, i, i2);
    }

    public static xv decode(Bitmap bitmap, boolean z) {
        xv xvVarDecode = DecodeHandler.decode(bitmap, z);
        if (xvVarDecode != null) {
            return xvVarDecode;
        }
        xv xvVarTrySmallerBitmap = trySmallerBitmap(bitmap, true, z);
        return xvVarTrySmallerBitmap != null ? xvVarTrySmallerBitmap : tryBiggerBitmap(bitmap, z);
    }

    public static xv tryBiggerBitmap(Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < 1200 && height < 1200) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width + 200, height + 200, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawColor(-1);
            canvas.drawBitmap(bitmap, 100.0f, 100.0f, new Paint());
            bitmap = bitmapCreateBitmap;
        }
        return DecodeHandler.decode(convertToBMW(bitmap, bitmap.getWidth(), bitmap.getHeight(), 180), z);
    }

    public static xv trySmallerBitmap(Bitmap bitmap, boolean z, boolean z2) {
        int size;
        Bitmap bitmapCreateBitmap;
        int size2;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width >= 200 && height >= 200) {
            boolean z3 = width <= height;
            Bitmap bitmapCreateBitmap2 = z3 ? Bitmap.createBitmap(bitmap, 0, 0, width, height / 2) : Bitmap.createBitmap(bitmap, 0, 0, width / 2, height);
            final ArrayList arrayList = new ArrayList();
            xv xvVarDecode = z ? DecodeHandler.decode(bitmapCreateBitmap2, new zv() { // from class: io.dcloud.feature.barcode2.decoding.CaptureActivityHandler.1
                @Override // supwisdom.zv
                public void foundPossibleResultPoint(yv yvVar) {
                    arrayList.add(yvVar);
                }
            }, z2) : DecodeHandler.decode(bitmapCreateBitmap2, z2);
            if (xvVarDecode != null) {
                return xvVarDecode;
            }
            if (z) {
                size = arrayList.size();
                arrayList.clear();
            } else {
                size = 0;
            }
            if (z3) {
                int i = height / 2;
                bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, i, width, i);
            } else {
                int i2 = width / 2;
                bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i2, 0, i2, height);
            }
            xv xvVarDecode2 = z ? DecodeHandler.decode(bitmapCreateBitmap, new zv() { // from class: io.dcloud.feature.barcode2.decoding.CaptureActivityHandler.2
                @Override // supwisdom.zv
                public void foundPossibleResultPoint(yv yvVar) {
                    arrayList.add(yvVar);
                }
            }, z2) : DecodeHandler.decode(bitmapCreateBitmap, z2);
            if (xvVarDecode2 != null) {
                return xvVarDecode2;
            }
            if (z) {
                size2 = arrayList.size();
                arrayList.clear();
            } else {
                size2 = 0;
            }
            Bitmap bitmapCreateBitmap3 = z3 ? Bitmap.createBitmap(bitmap, 0, height / 8, width, (height / 4) * 3) : Bitmap.createBitmap(bitmap, width / 8, 0, (width / 4) * 3, height);
            xv xvVarDecode3 = z ? DecodeHandler.decode(bitmapCreateBitmap3, new zv() { // from class: io.dcloud.feature.barcode2.decoding.CaptureActivityHandler.3
                @Override // supwisdom.zv
                public void foundPossibleResultPoint(yv yvVar) {
                    arrayList.add(yvVar);
                }
            }, z2) : DecodeHandler.decode(bitmapCreateBitmap3, z2);
            if (xvVarDecode3 != null) {
                return xvVarDecode3;
            }
            if (z) {
                int size3 = arrayList.size();
                arrayList.clear();
                if (size > size3 && size > size2) {
                    return trySmallerBitmap(bitmapCreateBitmap2, false, z2);
                }
                if (size3 > size2 && size3 > size) {
                    return trySmallerBitmap(bitmapCreateBitmap3, false, z2);
                }
                if (size2 > size3 && size2 > size) {
                    return trySmallerBitmap(bitmapCreateBitmap, false, z2);
                }
            }
        }
        return null;
    }

    public void autoFocus() {
        CameraManager.get().requestAutoFocus(this, 1000);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1000:
                Log.d(TAG, "Got auto-focus message");
                if (this.state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, 1000);
                }
                break;
            case 1001:
                Log.d(TAG, "CODE_DECODE_FAILED");
                this.state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(this.activity, this.decodeThread.getHandler(), 1004);
                break;
            case 1002:
                Log.d(TAG, "Got decode succeeded message");
                this.state = State.SUCCESS;
                Bundle data = message.getData();
                Bitmap bitmap = data == null ? null : (Bitmap) data.getParcelable("barcode_bitmap");
                this.activity.handleDecode((xv) message.obj, bitmap);
                if (bitmap != null) {
                    bitmap.recycle();
                    System.out.println("barcode.recycle");
                }
                break;
        }
    }

    public void quitSynchronously() {
        this.state = State.DONE;
        CameraManager.get().stopPreview();
        Message.obtain(this.decodeThread.getHandler(), 1003).sendToTarget();
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
        }
        stopDecode();
    }

    public void restartPreviewAndDecode() {
        if (this.state == State.SUCCESS) {
            this.state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(this.activity, this.decodeThread.getHandler(), 1004);
            autoFocus();
        }
    }

    public void resume() {
        CameraManager.get().startPreview();
        this.activity.drawViewfinder();
        restartPreviewAndDecode();
    }

    public void stopDecode() {
        removeMessages(1002);
        removeMessages(1001);
        CameraManager.get().removeAutoFocus();
        this.state = State.SUCCESS;
    }
}
