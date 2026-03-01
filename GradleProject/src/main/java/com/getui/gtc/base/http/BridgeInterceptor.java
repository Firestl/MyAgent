package com.getui.gtc.base.http;

import android.net.Network;
import android.os.Build;
import android.util.Log;
import com.efs.sdk.base.Constants;
import com.getui.gtc.base.http.Interceptor;
import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.Response;
import com.lzy.okgo.model.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes.dex */
public class BridgeInterceptor implements Interceptor {
    @Override // com.getui.gtc.base.http.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        URLConnection uRLConnectionOpenConnection;
        Request request = chain.request();
        Request.Builder builder = new Request.Builder(request);
        Network network = request.network();
        if (network == null || Build.VERSION.SDK_INT < 21) {
            uRLConnectionOpenConnection = request.url().openConnection();
        } else {
            Log.d("GTC", "gtc h use n");
            uRLConnectionOpenConnection = network.openConnection(request.url());
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody != null) {
            MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (mediaTypeContentType != null) {
                builder.addHeader("Content-Type", mediaTypeContentType.toString());
            }
            long jContentLength = requestBodyBody.contentLength();
            if (jContentLength != -1) {
                builder.addHeader(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Long.toString(jContentLength)).removeHeader("Transfer-Encoding");
            } else {
                builder.addHeader("Transfer-Encoding", "chunked").removeHeader(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            }
        }
        if (request.header("Host") == null) {
            builder.addHeader("Host", request.url().getHost());
        }
        if (request.header(HttpHeaders.HEAD_KEY_CONNECTION) == null) {
            builder.addHeader(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        }
        boolean z = false;
        if (request.header(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING) == null && request.header(HttpHeaders.HEAD_KEY_RANGE) == null) {
            z = true;
            builder.addHeader(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, Constants.CP_GZIP);
        }
        Response responseProceed = ((RealInterceptorChain) chain).proceed(builder.build(), httpURLConnection);
        Response.Builder builderRequest = new Response.Builder(responseProceed).request(request);
        if (z && Constants.CP_GZIP.equalsIgnoreCase(responseProceed.header(HttpHeaders.HEAD_KEY_CONTENT_ENCODING)) && responseProceed.body() != null) {
            builderRequest.body(ResponseBody.create(responseProceed.body().contentType(), -1L, new GZIPInputStream(responseProceed.body().byteStream()))).removeHeader(HttpHeaders.HEAD_KEY_CONTENT_ENCODING).removeHeader(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
        }
        return builderRequest.build();
    }
}
