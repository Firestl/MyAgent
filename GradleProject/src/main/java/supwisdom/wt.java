package supwisdom;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.domain.DownloadThreadInfo;
import com.dcloud.android.downloader.exception.DownloadException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class wt implements vt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f9655a = new a(this, Looper.getMainLooper());
    public final cu b;

    public class a extends Handler {
        public a(wt wtVar, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            DownloadInfo downloadInfo = (DownloadInfo) message.obj;
            switch (downloadInfo.getStatus()) {
                case 1:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onStart();
                    }
                    break;
                case 2:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onDownloading(downloadInfo.getProgress(), downloadInfo.getSize());
                    }
                    break;
                case 3:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onWaited();
                    }
                    break;
                case 4:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onPaused();
                    }
                    break;
                case 5:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onDownloadSuccess(downloadInfo);
                    }
                    break;
                case 6:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onDownloadFailed(downloadInfo, downloadInfo.getException());
                    }
                    break;
                case 7:
                    if (downloadInfo.getDownloadListener() != null) {
                        downloadInfo.getDownloadListener().onRemoved();
                    }
                    break;
            }
        }
    }

    public wt(cu cuVar) {
        this.b = cuVar;
    }

    @Override // supwisdom.vt
    public void a(DownloadInfo downloadInfo) {
        if (downloadInfo.getStatus() != 7) {
            this.b.b(downloadInfo);
            if (downloadInfo.getDownloadThreadInfos() != null) {
                Iterator<DownloadThreadInfo> it = downloadInfo.getDownloadThreadInfos().iterator();
                while (it.hasNext()) {
                    this.b.a(it.next());
                }
            }
        }
        Message messageObtainMessage = this.f9655a.obtainMessage(downloadInfo.getId());
        messageObtainMessage.obj = downloadInfo;
        messageObtainMessage.sendToTarget();
        Log.d("DownloadResponseImpl", "progress:" + downloadInfo.getProgress() + ",size:" + downloadInfo.getSize());
    }

    @Override // supwisdom.vt
    public void a(DownloadException downloadException) {
    }
}
