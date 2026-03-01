package supwisdom;

import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketException;

/* JADX INFO: compiled from: RtmpHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class i00 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<a> f7894a;

    /* JADX INFO: compiled from: RtmpHandler.java */
    public interface a {
        void onRtmpAudioBitrateChanged(double d);

        void onRtmpAudioStreaming();

        void onRtmpConnected(String str);

        void onRtmpConnecting(String str);

        void onRtmpDisconnected();

        void onRtmpIOException(IOException iOException);

        void onRtmpIllegalArgumentException(IllegalArgumentException illegalArgumentException);

        void onRtmpIllegalStateException(IllegalStateException illegalStateException);

        void onRtmpSocketException(SocketException socketException);

        void onRtmpStopped();

        void onRtmpVideoBitrateChanged(double d);

        void onRtmpVideoFpsChanged(double d);

        void onRtmpVideoStreaming();
    }

    public i00(a aVar) {
        this.f7894a = new WeakReference<>(aVar);
    }

    public void a(String str) {
        obtainMessage(1, str).sendToTarget();
    }

    public void b(String str) {
        obtainMessage(0, str).sendToTarget();
    }

    public void c() {
        sendEmptyMessage(4);
    }

    public void d() {
        sendEmptyMessage(2);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar = this.f7894a.get();
        if (aVar == null) {
            return;
        }
        switch (message.what) {
            case 0:
                aVar.onRtmpConnecting((String) message.obj);
                return;
            case 1:
                aVar.onRtmpConnected((String) message.obj);
                return;
            case 2:
                aVar.onRtmpVideoStreaming();
                return;
            case 3:
                aVar.onRtmpAudioStreaming();
                return;
            case 4:
                aVar.onRtmpStopped();
                return;
            case 5:
                aVar.onRtmpDisconnected();
                return;
            case 6:
                aVar.onRtmpVideoFpsChanged(((Double) message.obj).doubleValue());
                return;
            case 7:
                aVar.onRtmpVideoBitrateChanged(((Double) message.obj).doubleValue());
                return;
            case 8:
                aVar.onRtmpAudioBitrateChanged(((Double) message.obj).doubleValue());
                return;
            case 9:
                aVar.onRtmpSocketException((SocketException) message.obj);
                return;
            case 10:
                aVar.onRtmpIOException((IOException) message.obj);
                return;
            case 11:
                aVar.onRtmpIllegalArgumentException((IllegalArgumentException) message.obj);
                return;
            case 12:
                aVar.onRtmpIllegalStateException((IllegalStateException) message.obj);
                return;
            default:
                throw new RuntimeException("unknown msg " + message.what);
        }
    }

    public void a() {
        sendEmptyMessage(3);
    }

    public void b() {
        sendEmptyMessage(5);
    }

    public void c(double d) {
        obtainMessage(6, Double.valueOf(d)).sendToTarget();
    }

    public void a(double d) {
        obtainMessage(8, Double.valueOf(d)).sendToTarget();
    }

    public void b(double d) {
        obtainMessage(7, Double.valueOf(d)).sendToTarget();
    }

    public void a(SocketException socketException) {
        obtainMessage(9, socketException).sendToTarget();
    }

    public void a(IOException iOException) {
        obtainMessage(10, iOException).sendToTarget();
    }

    public void a(IllegalArgumentException illegalArgumentException) {
        obtainMessage(11, illegalArgumentException).sendToTarget();
    }

    public void a(IllegalStateException illegalStateException) {
        obtainMessage(12, illegalStateException).sendToTarget();
    }
}
