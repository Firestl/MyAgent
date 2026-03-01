package net.ossrs.yasea;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class SrsEncodeHandler extends Handler {
    public static final int MSG_ENCODE_ILLEGAL_ARGUMENT_EXCEPTION = 2;
    public static final int MSG_ENCODE_NETWORK_RESUME = 1;
    public static final int MSG_ENCODE_NETWORK_WEAK = 0;
    public WeakReference<SrsEncodeListener> mWeakListener;

    public interface SrsEncodeListener {
        void onEncodeIllegalArgumentException(IllegalArgumentException illegalArgumentException);

        void onNetworkResume();

        void onNetworkWeak();
    }

    public SrsEncodeHandler(SrsEncodeListener srsEncodeListener) {
        this.mWeakListener = new WeakReference<>(srsEncodeListener);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        SrsEncodeListener srsEncodeListener = this.mWeakListener.get();
        if (srsEncodeListener == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            srsEncodeListener.onNetworkWeak();
            return;
        }
        if (i == 1) {
            srsEncodeListener.onNetworkResume();
            return;
        }
        if (i == 2) {
            srsEncodeListener.onEncodeIllegalArgumentException((IllegalArgumentException) message.obj);
        }
        throw new RuntimeException("unknown msg " + message.what);
    }

    public void notifyEncodeIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        obtainMessage(2, illegalArgumentException).sendToTarget();
    }

    public void notifyNetworkResume() {
        sendEmptyMessage(1);
    }

    public void notifyNetworkWeak() {
        sendEmptyMessage(0);
    }
}
