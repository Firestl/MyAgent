package io.dcloud.feature.barcode2.decoding;

import android.os.Handler;
import android.os.Looper;
import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.DecodeHintType;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import supwisdom.zv;

/* JADX INFO: loaded from: classes2.dex */
public final class DecodeThread extends Thread {
    public static final String BARCODE_BITMAP = "barcode_bitmap";
    public final IBarHandler activity;
    public Handler handler;
    public final CountDownLatch handlerInitLatch = new CountDownLatch(1);
    public final Hashtable<DecodeHintType, Object> hints;

    public DecodeThread(IBarHandler iBarHandler, Vector<BarcodeFormat> vector, String str, zv zvVar, boolean z) {
        this.activity = iBarHandler;
        Hashtable<DecodeHintType, Object> hashtable = new Hashtable<>(3);
        this.hints = hashtable;
        if (vector == null || vector.isEmpty()) {
            vector = new Vector<>();
            vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
            vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hashtable.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        if (str != null) {
            hashtable.put(DecodeHintType.CHARACTER_SET, str);
        }
        if (z) {
            hashtable.put(DecodeHintType.autoDecodeCharset, Boolean.TRUE);
        }
        hashtable.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, zvVar);
        hashtable.put(DecodeHintType.TRY_HARDER, new Object());
    }

    public Handler getHandler() {
        try {
            this.handlerInitLatch.await();
        } catch (InterruptedException unused) {
        }
        return this.handler;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.handler = new DecodeHandler(this.activity, this.hints);
        this.handlerInitLatch.countDown();
        Looper.loop();
    }
}
