package dc.squareup.okhttp3.internal.http;

import com.efs.sdk.base.Constants;
import com.lzy.okgo.cookie.SerializableCookie;
import dc.squareup.okhttp3.Cookie;
import dc.squareup.okhttp3.CookieJar;
import dc.squareup.okhttp3.Headers;
import dc.squareup.okhttp3.Interceptor;
import dc.squareup.okhttp3.MediaType;
import dc.squareup.okhttp3.Request;
import dc.squareup.okhttp3.RequestBody;
import dc.squareup.okhttp3.Response;
import dc.squareup.okhttp3.internal.Util;
import dc.squareup.okhttp3.internal.Version;
import dc.squareup.okio.GzipSource;
import dc.squareup.okio.Okio;
import java.io.IOException;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public final class BridgeInterceptor implements Interceptor {
    public static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator<String>() { // from class: dc.squareup.okhttp3.internal.http.BridgeInterceptor.1
        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
        }
    };
    public final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    public static void addCookies(Request.Builder builder, Map<String, List<String>> map) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if ("Cookie".equalsIgnoreCase(key) || com.lzy.okgo.model.HttpHeaders.HEAD_KEY_COOKIE2.equalsIgnoreCase(key)) {
                if (!entry.getValue().isEmpty()) {
                    builder.addHeader(key, buildCookieHeader(entry.getValue()));
                }
            }
        }
    }

    public static String buildCookieHeader(List<String> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private String cookieHeader(List<Cookie> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("; ");
            }
            Cookie cookie = list.get(i);
            sb.append(cookie.name());
            sb.append('=');
            sb.append(cookie.value());
        }
        return sb.toString();
    }

    public static Map<String, List<String>> toMultimap(Headers headers, String str) {
        TreeMap treeMap = new TreeMap(FIELD_NAME_COMPARATOR);
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String strName = headers.name(i);
            String strValue = headers.value(i);
            ArrayList arrayList = new ArrayList();
            List list = (List) treeMap.get(strName);
            if (list != null) {
                arrayList.addAll(list);
            }
            arrayList.add(strValue);
            treeMap.put(strName, Collections.unmodifiableList(arrayList));
        }
        if (str != null) {
            treeMap.put(null, Collections.unmodifiableList(Collections.singletonList(str)));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    @Override // dc.squareup.okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builderNewBuilder = request.newBuilder();
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody != null) {
            MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (mediaTypeContentType != null) {
                builderNewBuilder.header("Content-Type", mediaTypeContentType.toString());
            }
            long jContentLength = requestBodyBody.contentLength();
            if (jContentLength != -1) {
                builderNewBuilder.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_LENGTH, Long.toString(jContentLength));
                builderNewBuilder.removeHeader("Transfer-Encoding");
            } else {
                builderNewBuilder.header("Transfer-Encoding", "chunked");
                builderNewBuilder.removeHeader(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            }
        }
        boolean z = false;
        if (request.header("Host") == null) {
            builderNewBuilder.header("Host", Util.hostHeader(request.url(), false));
        }
        if (request.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONNECTION) == null) {
            builderNewBuilder.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        }
        if (request.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_ACCEPT_ENCODING) == null && request.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_RANGE) == null) {
            z = true;
            builderNewBuilder.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, Constants.CP_GZIP);
        }
        String strHeader = request.header(SerializableCookie.COOKIE);
        if (strHeader == null) {
            CookieHandler cookieHandler = CookieHandler.getDefault();
            if (cookieHandler != null) {
                try {
                    addCookies(builderNewBuilder, cookieHandler.get(request.url().uri(), toMultimap(request.headers(), null)));
                } catch (Exception unused) {
                }
            }
        } else {
            builderNewBuilder.header("Cookie", strHeader);
        }
        if (request.header("User-Agent") == null) {
            builderNewBuilder.header("User-Agent", Version.userAgent());
        }
        Response responseProceed = chain.proceed(builderNewBuilder.build());
        try {
            CookieHandler cookieHandler2 = CookieHandler.getDefault();
            if (cookieHandler2 != null) {
                cookieHandler2.put(request.url().uri(), toMultimap(responseProceed.headers(), null));
            }
        } catch (Exception unused2) {
        }
        HttpHeaders.receiveHeaders(this.cookieJar, request.url(), responseProceed.headers());
        Response.Builder builderRequest = responseProceed.newBuilder().request(request);
        if (z && Constants.CP_GZIP.equalsIgnoreCase(responseProceed.header(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_ENCODING)) && HttpHeaders.hasBody(responseProceed)) {
            GzipSource gzipSource = new GzipSource(responseProceed.body().source());
            builderRequest.headers(responseProceed.headers().newBuilder().removeAll(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_ENCODING).removeAll(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_LENGTH).build());
            builderRequest.body(new RealResponseBody(responseProceed.header("Content-Type"), -1L, Okio.buffer(gzipSource)));
        }
        return builderRequest.build();
    }
}
