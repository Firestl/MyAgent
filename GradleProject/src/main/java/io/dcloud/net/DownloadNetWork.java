package io.dcloud.net;

import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.DHInterface.IReqListener;
import io.dcloud.common.DHInterface.IResponseListener;
import io.dcloud.common.adapter.util.DCloudTrustManager;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.net.NetWork;
import io.dcloud.common.util.net.RequestData;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadNetWork extends NetWork {
    public boolean isStop;
    public long mContentLength;
    public Thread mExecSyncTask;
    public Map<String, String> mResponseHeaders;
    public int mRetry;
    public int mStatus;
    public String mUrl;
    public HttpURLConnection mUrlConn;

    public DownloadNetWork(int i, RequestData requestData, IReqListener iReqListener, IResponseListener iResponseListener) {
        super(i, requestData, iReqListener, iResponseListener);
        this.mStatus = 0;
        this.mRetry = 0;
        this.mUrlConn = null;
        this.isStop = false;
        this.mUrl = requestData.getUrl();
    }

    public static Map<String, String> getHttpResponseHeader(HttpURLConnection httpURLConnection) throws UnsupportedEncodingException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (true) {
            String headerField = httpURLConnection.getHeaderField(i);
            if (headerField == null) {
                return linkedHashMap;
            }
            linkedHashMap.put(httpURLConnection.getHeaderFieldKey(i), headerField);
            i++;
        }
    }

    private void initUploadData() {
        try {
            URL url = new URL(this.mUrl);
            Logger.d("httpreq", "request mUrl=" + this.mUrl);
            onStateChanged(IReqListener.NetState.NET_INIT);
            connect(url);
        } catch (Exception e2) {
            e2.printStackTrace();
            onStateChanged(IReqListener.NetState.NET_ERROR);
        }
    }

    private void response(InputStream inputStream) throws Exception {
        onStateChanged(IReqListener.NetState.NET_HANDLE_BEGIN);
        onReceiveing(inputStream);
        if (this.isStop) {
            return;
        }
        onStateChanged(IReqListener.NetState.NET_HANDLE_ING);
        onStateChanged(IReqListener.NetState.NET_HANDLE_END);
    }

    private void setHeaders() {
        this.mRequestData.addHeader(this.mUrlConn);
    }

    @Override // io.dcloud.common.util.net.NetWork
    public void cancelWork() {
        HttpURLConnection httpURLConnection = this.mUrlConn;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mUrlConn = null;
        }
    }

    public void connect(URL url) {
        try {
            this.mUrlConn = (HttpURLConnection) url.openConnection();
            setHeaders();
            if (!PdrUtil.isEquals(this.mRequestData.unTrustedCAType, "refuse") && !PdrUtil.isEquals(this.mRequestData.unTrustedCAType, "warning") && (this.mUrlConn instanceof HttpsURLConnection)) {
                try {
                    SSLSocketFactory sSLSocketFactory = DCloudTrustManager.getSSLSocketFactory();
                    if (sSLSocketFactory != null) {
                        ((HttpsURLConnection) this.mUrlConn).setSSLSocketFactory(sSLSocketFactory);
                    }
                    ((HttpsURLConnection) this.mUrlConn).setHostnameVerifier(DCloudTrustManager.getHostnameVerifier(false));
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            onStateChanged(IReqListener.NetState.NET_REQUEST_BEGIN);
            if (this.isStop) {
                return;
            }
            int responseCode = this.mUrlConn.getResponseCode();
            this.mStatus = responseCode;
            if (responseCode == 206) {
                this.mStatus = 200;
            }
            int i = this.mStatus;
            if (i == 302 || i == 301) {
                String headerField = this.mUrlConn.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
                if (!headerField.equals(url.toString())) {
                    connect(new URL(headerField));
                    return;
                }
            }
            onStateChanged(IReqListener.NetState.NET_CONNECTED);
            InputStream inputStream = this.mUrlConn.getInputStream();
            this.mResponseHeaders = getHttpResponseHeader(this.mUrlConn);
            response(inputStream);
        } catch (Throwable th) {
            if (this.isStop) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.mRetryIntervalTime;
            int i2 = this.mTimes;
            long j2 = jCurrentTimeMillis + ((j * ((long) (1 << i2))) / 2);
            if (i2 <= this.MAX_TIMES) {
                while (System.currentTimeMillis() <= j2) {
                }
                this.mTimes++;
                try {
                    this.mUrlConn.disconnect();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                connect(url);
                return;
            }
            if (th instanceof SocketTimeoutException) {
                onStateChanged(IReqListener.NetState.NET_TIMEOUT);
                return;
            }
            if (!this.isStop) {
                onStateChanged(IReqListener.NetState.NET_ERROR);
                return;
            }
            IReqListener iReqListener = this.mReqListener;
            if (iReqListener instanceof JsDownload) {
                ((JsDownload) iReqListener).saveInDatabase();
            }
        }
    }

    @Override // io.dcloud.common.util.net.NetWork
    public void dispose() {
        this.mTimes = 4;
        this.isStop = true;
        this.mReqListener = null;
        try {
            HttpURLConnection httpURLConnection = this.mUrlConn;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String getResponseHeader(String str) {
        Map<String, String> map = this.mResponseHeaders;
        if (map == null) {
            return "''";
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey().trim())) {
                return entry.getValue();
            }
        }
        return "''";
    }

    public String getResponseHeaders() {
        Map<String, String> map = this.mResponseHeaders;
        if (map == null || map.size() <= 0) {
            return "{}";
        }
        try {
            return new JSONObject(this.mResponseHeaders).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "{}";
        }
    }

    public int onReceiveing(InputStream inputStream) throws Exception {
        IReqListener iReqListener = this.mReqListener;
        if (iReqListener != null) {
            return iReqListener.onReceiving(inputStream);
        }
        return 0;
    }

    public void onResponsing(OutputStream outputStream) {
    }

    public void onStateChanged(IReqListener.NetState netState) {
        IReqListener iReqListener = this.mReqListener;
        if (iReqListener != null) {
            iReqListener.onNetStateChanged(netState, this.isAbort);
        }
    }

    @Override // io.dcloud.common.util.net.NetWork, java.lang.Runnable
    public void run() {
        initUploadData();
    }
}
