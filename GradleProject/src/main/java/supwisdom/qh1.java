package supwisdom;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build;
import com.lzy.okgo.model.HttpHeaders;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.NetworkPolicy;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: UrlConnectionDownloader.java */
/* JADX INFO: loaded from: classes2.dex */
public class qh1 implements Downloader {
    public static volatile Object b;
    public static final Object c = new Object();
    public static final ThreadLocal<StringBuilder> d = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8925a;

    /* JADX INFO: compiled from: UrlConnectionDownloader.java */
    public static class a extends ThreadLocal<StringBuilder> {
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder();
        }
    }

    /* JADX INFO: compiled from: UrlConnectionDownloader.java */
    public static class b {
        public static Object a(Context context) throws IOException {
            File fileB = rh1.b(context);
            HttpResponseCache installed = HttpResponseCache.getInstalled();
            return installed == null ? HttpResponseCache.install(fileB, rh1.a(fileB)) : installed;
        }
    }

    public qh1(Context context) {
        this.f8925a = context.getApplicationContext();
    }

    public HttpURLConnection a(Uri uri) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(20000);
        return httpURLConnection;
    }

    @Override // com.squareup.picasso.Downloader
    public Downloader.a a(Uri uri, int i) throws IOException {
        String string;
        if (Build.VERSION.SDK_INT >= 14) {
            a(this.f8925a);
        }
        HttpURLConnection httpURLConnectionA = a(uri);
        httpURLConnectionA.setUseCaches(true);
        if (i != 0) {
            if (NetworkPolicy.isOfflineOnly(i)) {
                string = "only-if-cached,max-age=2147483647";
            } else {
                StringBuilder sb = d.get();
                sb.setLength(0);
                if (!NetworkPolicy.shouldReadFromDiskCache(i)) {
                    sb.append("no-cache");
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(i)) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append("no-store");
                }
                string = sb.toString();
            }
            httpURLConnectionA.setRequestProperty(HttpHeaders.HEAD_KEY_CACHE_CONTROL, string);
        }
        int responseCode = httpURLConnectionA.getResponseCode();
        if (responseCode < 300) {
            return new Downloader.a(httpURLConnectionA.getInputStream(), rh1.a(httpURLConnectionA.getHeaderField("X-Android-Response-Source")), httpURLConnectionA.getHeaderFieldInt(HttpHeaders.HEAD_KEY_CONTENT_LENGTH, -1));
        }
        httpURLConnectionA.disconnect();
        throw new Downloader.ResponseException(responseCode + Operators.SPACE_STR + httpURLConnectionA.getResponseMessage(), i, responseCode);
    }

    public static void a(Context context) {
        if (b == null) {
            try {
                synchronized (c) {
                    if (b == null) {
                        b = b.a(context);
                    }
                }
            } catch (IOException unused) {
            }
        }
    }
}
