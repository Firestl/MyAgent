package supwisdom;

import android.os.Process;
import android.text.TextUtils;
import com.dcloud.android.downloader.domain.DownloadInfo;
import com.dcloud.android.downloader.exception.DownloadException;
import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public class yt implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vt f9915a;
    public final DownloadInfo b;
    public final a c;

    public interface a {
        void a(long j, boolean z);
    }

    public yt(vt vtVar, DownloadInfo downloadInfo, a aVar) {
        this.f9915a = vtVar;
        this.b = downloadInfo;
        this.c = aVar;
    }

    public final void a() {
        if (this.b.isPause()) {
            throw new DownloadException(7);
        }
    }

    public final void b() throws DownloadException {
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b.getDownloadUrl()).openConnection();
                if (httpURLConnection instanceof HttpsURLConnection) {
                    SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                    if (sSLSocketFactory != null) {
                        ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLSocketFactory);
                    }
                    ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(DCloudTrustManager.getHostnameVerifier(false));
                }
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_RANGE, "bytes=0-");
                httpURLConnection.setInstanceFollowRedirects(false);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    a(httpURLConnection, false);
                    return;
                }
                if (responseCode == 206) {
                    a(httpURLConnection, true);
                    return;
                }
                if (responseCode != 302 && 301 != responseCode) {
                    throw new DownloadException(3, "UnSupported response code:" + responseCode);
                }
                this.b.setLocation(httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION));
                b();
            } catch (ProtocolException e2) {
                throw new DownloadException(4, "Protocol error", e2);
            } catch (IOException e3) {
                throw new DownloadException(5, "IO error", e3);
            }
        } catch (MalformedURLException e4) {
            throw new DownloadException(2, "Bad url.", e4);
        } catch (Exception e5) {
            throw new DownloadException(5, "Unknown error", e5);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        try {
            b();
        } catch (DownloadException e2) {
            this.f9915a.a(e2);
        } catch (Exception e3) {
            this.f9915a.a(new DownloadException(9, e3));
        }
    }

    public final void a(HttpURLConnection httpURLConnection, boolean z) throws DownloadException {
        long contentLength;
        String headerField = httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
        if (!TextUtils.isEmpty(headerField) && !headerField.equals("0") && !headerField.equals("-1")) {
            contentLength = Long.parseLong(headerField);
        } else {
            contentLength = httpURLConnection.getContentLength();
        }
        if (contentLength > 0) {
            a();
            this.c.a(contentLength, z);
            return;
        }
        throw new DownloadException(6, "length <= 0");
    }
}
