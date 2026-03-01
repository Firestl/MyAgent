package supwisdom;

import android.os.Process;
import android.util.Log;
import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.domain.DownloadThreadInfo;
import com.dcloud.android.downloader.exception.DownloadException;
import com.dcloud.android.downloader.exception.DownloadPauseException;
import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public class zt implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DownloadThreadInfo f10027a;
    public final vt b;
    public final ut c;
    public final DownloadInfo d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f10028e;
    public long f;
    public InputStream g;

    public interface a {
        void a();

        void b();
    }

    public zt(DownloadThreadInfo downloadThreadInfo, vt vtVar, ut utVar, DownloadInfo downloadInfo, a aVar) {
        this.f10027a = downloadThreadInfo;
        this.b = vtVar;
        this.c = utVar;
        this.d = downloadInfo;
        this.f = downloadThreadInfo.getProgress();
        this.f10028e = aVar;
    }

    public final void a() {
        if (this.d.isPause()) {
            throw new DownloadPauseException(7);
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x01fb: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:82:0x01fb */
    public final void b() throws Throwable {
        NoSuchAlgorithmException e2;
        KeyManagementException e3;
        IOException e4;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        HttpURLConnection httpURLConnection3 = null;
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                httpURLConnection2 = (HttpURLConnection) new URL(this.f10027a.getUri()).openConnection();
            } catch (DownloadPauseException unused) {
            } catch (ProtocolException e5) {
                e = e5;
            } catch (IOException e6) {
                e4 = e6;
            } catch (KeyManagementException e7) {
                e3 = e7;
            } catch (NoSuchAlgorithmException e8) {
                e2 = e8;
            }
            try {
                if (httpURLConnection2 instanceof HttpsURLConnection) {
                    SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                    if (sSLSocketFactory != null) {
                        ((HttpsURLConnection) httpURLConnection2).setSSLSocketFactory(sSLSocketFactory);
                    }
                    ((HttpsURLConnection) httpURLConnection2).setHostnameVerifier(DCloudTrustManager.getHostnameVerifier(false));
                }
                httpURLConnection2.setConnectTimeout(this.c.a());
                httpURLConnection2.setReadTimeout(this.c.h());
                httpURLConnection2.setRequestMethod(this.c.g());
                long start = this.f10027a.getStart() + this.f;
                if (this.d.isSupportRanges()) {
                    if (start > this.f10027a.getEnd()) {
                        this.f = 0L;
                        start = 0;
                    }
                    if (this.c.f() == 1) {
                        httpURLConnection2.setRequestProperty(HttpHeaders.HEAD_KEY_RANGE, "bytes=" + start + "-");
                    } else {
                        httpURLConnection2.setRequestProperty(HttpHeaders.HEAD_KEY_RANGE, "bytes=" + start + "-" + this.f10027a.getEnd());
                    }
                }
                int responseCode = httpURLConnection2.getResponseCode();
                long j = ((long) Integer.parseInt(httpURLConnection2.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_LENGTH))) + start;
                if (this.c.f() == 1 && j != this.f10027a.getEnd()) {
                    if (j - this.f10027a.getEnd() != 1) {
                        throw new DownloadException(5, "IO error Data source change");
                    }
                    start--;
                    this.f--;
                }
                if (responseCode != 206 && responseCode != 200) {
                    throw new DownloadException(8, "UnSupported response code:" + responseCode);
                }
                this.g = httpURLConnection2.getInputStream();
                RandomAccessFile randomAccessFile = new RandomAccessFile(this.d.getPath(), "rwd");
                if (this.c.f() == 1 && randomAccessFile.length() < this.f) {
                    throw new DownloadException(5, "IO error Have small download size");
                }
                randomAccessFile.seek(start);
                byte[] bArr = new byte[4096];
                int i = 0;
                while (true) {
                    a();
                    int i2 = this.g.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    randomAccessFile.write(bArr, 0, i2);
                    i += i2;
                    this.f10027a.setProgress(this.f + ((long) i));
                    this.f10028e.a();
                    Log.d("DownloadThread", "downloadInfo:" + this.d.getId() + " thread:" + this.f10027a.getThreadId() + " progress:" + this.f10027a.getProgress() + ",start:" + this.f10027a.getStart() + ",end:" + this.f10027a.getEnd());
                }
                this.f10028e.b();
                a();
            } catch (DownloadPauseException unused2) {
                httpURLConnection3 = httpURLConnection2;
                if (httpURLConnection3 == null) {
                    return;
                } else {
                    httpURLConnection2 = httpURLConnection3;
                }
            } catch (ProtocolException e9) {
                e = e9;
                throw new DownloadException(4, "Protocol error", e);
            } catch (IOException e10) {
                e4 = e10;
                throw new DownloadException(5, "IO error", e4);
            } catch (KeyManagementException e11) {
                e3 = e11;
                throw new DownloadException(5, "Key management", e3);
            } catch (NoSuchAlgorithmException e12) {
                e2 = e12;
                throw new DownloadException(5, "NO such", e2);
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection3 = httpURLConnection;
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            throw th;
        }
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Process.setThreadPriority(10);
        a();
        try {
            b();
        } catch (DownloadException e2) {
            this.d.setStatus(6);
            this.d.setException(e2);
            this.b.a(this.d);
            this.b.a(e2);
        } catch (Exception e3) {
            DownloadException downloadException = new DownloadException(9, "other error", e3);
            this.d.setStatus(6);
            this.d.setException(downloadException);
            this.b.a(this.d);
            this.b.a(downloadException);
        }
    }
}
