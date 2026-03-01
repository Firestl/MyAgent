package com.getui.gtc.base.http;

import com.getui.gtc.base.http.Interceptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: loaded from: classes.dex */
public class RetryInterceptor implements Interceptor {
    public static final int MAX_RETRY_COUNT = 3;
    public volatile boolean canceled;
    public final GtHttpClient client;
    public volatile HttpURLConnection connection;

    public RetryInterceptor(GtHttpClient gtHttpClient) {
        this.client = gtHttpClient;
    }

    private boolean isRecoverable(IOException iOException) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? iOException instanceof SocketTimeoutException : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private boolean recover(IOException iOException, HttpURLConnection httpURLConnection, Request request) {
        return this.client.isRetryOnConnectionFailure() && isRecoverable(iOException);
    }

    public void cancel() {
        this.canceled = true;
        if (this.connection != null) {
            this.connection.disconnect();
        }
    }

    @Override // com.getui.gtc.base.http.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        HttpURLConnection httpURLConnection = (HttpURLConnection) request.url().openConnection();
        this.connection = httpURLConnection;
        int i = 0;
        while (!this.canceled) {
            try {
                Response responseProceed = realInterceptorChain.proceed(request, httpURLConnection);
                httpURLConnection.disconnect();
                return responseProceed;
            } catch (IOException e2) {
                if (i >= 3 || !recover(e2, httpURLConnection, request)) {
                    throw e2;
                }
                httpURLConnection.disconnect();
                httpURLConnection = (HttpURLConnection) request.url().openConnection();
                this.connection = httpURLConnection;
                i++;
            }
        }
        httpURLConnection.disconnect();
        throw new IOException("Canceled");
    }
}
