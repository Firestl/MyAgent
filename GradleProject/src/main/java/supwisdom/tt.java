package supwisdom;

import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.exception.DownloadException;

/* JADX INFO: loaded from: classes.dex */
public interface tt {
    void onDownloadFailed(DownloadInfo downloadInfo, DownloadException downloadException);

    void onDownloadSuccess(DownloadInfo downloadInfo);

    void onDownloading(long j, long j2);

    void onPaused();

    void onRemoved();

    void onStart();

    void onWaited();
}
