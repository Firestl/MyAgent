package net.ossrs.yasea;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class SrsRecordHandler extends Handler {
    public static final int MSG_RECORD_FINISHED = 3;
    public static final int MSG_RECORD_ILLEGEL_ARGUMENT_EXCEPTION = 4;
    public static final int MSG_RECORD_IO_EXCEPTION = 5;
    public static final int MSG_RECORD_PAUSE = 0;
    public static final int MSG_RECORD_RESUME = 1;
    public static final int MSG_RECORD_STARTED = 2;
    public WeakReference<SrsRecordListener> mWeakListener;

    public interface SrsRecordListener {
        void onRecordFinished(String str);

        void onRecordIOException(IOException iOException);

        void onRecordIllegalArgumentException(IllegalArgumentException illegalArgumentException);

        void onRecordPause();

        void onRecordResume();

        void onRecordStarted(String str);
    }

    public SrsRecordHandler(SrsRecordListener srsRecordListener) {
        this.mWeakListener = new WeakReference<>(srsRecordListener);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SrsRecordListener srsRecordListener = this.mWeakListener.get();
        if (srsRecordListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            srsRecordListener.onRecordPause();
            return;
        }
        if (i == 1) {
            srsRecordListener.onRecordResume();
            return;
        }
        if (i == 2) {
            srsRecordListener.onRecordStarted((String) message.obj);
            return;
        }
        if (i == 3) {
            srsRecordListener.onRecordFinished((String) message.obj);
            return;
        }
        if (i == 4) {
            srsRecordListener.onRecordIllegalArgumentException((IllegalArgumentException) message.obj);
        } else {
            if (i == 5) {
                srsRecordListener.onRecordIOException((IOException) message.obj);
                return;
            }
            throw new RuntimeException("unknown msg " + message.what);
        }
    }

    public void notifyRecordFinished(String str) {
        obtainMessage(3, str).sendToTarget();
    }

    public void notifyRecordIOException(IOException iOException) {
        obtainMessage(5, iOException).sendToTarget();
    }

    public void notifyRecordIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        obtainMessage(4, illegalArgumentException).sendToTarget();
    }

    public void notifyRecordPause() {
        sendEmptyMessage(0);
    }

    public void notifyRecordResume() {
        sendEmptyMessage(1);
    }

    public void notifyRecordStarted(String str) {
        obtainMessage(2, str).sendToTarget();
    }
}
