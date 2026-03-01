package supwisdom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.huawei.secure.android.common.ssl.SSLUtil;
import com.lzy.okgo.model.HttpHeaders;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class tx0 implements ImageDownloader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9328a;
    public final int b = 5000;
    public final int c = 20000;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9329a;

        static {
            int[] iArr = new int[ImageDownloader.Scheme.values().length];
            f9329a = iArr;
            try {
                iArr[ImageDownloader.Scheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9329a[ImageDownloader.Scheme.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9329a[ImageDownloader.Scheme.FILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9329a[ImageDownloader.Scheme.CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9329a[ImageDownloader.Scheme.ASSETS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9329a[ImageDownloader.Scheme.DRAWABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9329a[ImageDownloader.Scheme.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public tx0(Context context) {
        this.f9328a = context.getApplicationContext();
    }

    @Override // com.nostra13.dcloudimageloader.core.download.ImageDownloader
    public InputStream a(String str, Object obj) throws IOException {
        switch (a.f9329a[ImageDownloader.Scheme.ofUri(str).ordinal()]) {
            case 1:
            case 2:
                return g(str, obj);
            case 3:
                return f(str, obj);
            case 4:
                return d(str, obj);
            case 5:
                return c(str, obj);
            case 6:
                return e(str, obj);
            default:
                h(str, obj);
                throw null;
        }
    }

    public HttpURLConnection b(String str, Object obj) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        if (str.startsWith("https")) {
            a((HttpsURLConnection) httpURLConnection);
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        return httpURLConnection;
    }

    public InputStream c(String str, Object obj) throws IOException {
        return this.f9328a.getAssets().open(ImageDownloader.Scheme.ASSETS.crop(str));
    }

    public InputStream d(String str, Object obj) throws FileNotFoundException {
        return this.f9328a.getContentResolver().openInputStream(Uri.parse(str));
    }

    public InputStream e(String str, Object obj) {
        Bitmap bitmap = ((BitmapDrawable) this.f9328a.getResources().getDrawable(Integer.parseInt(ImageDownloader.Scheme.DRAWABLE.crop(str)))).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public InputStream f(String str, Object obj) throws IOException {
        return new BufferedInputStream(new FileInputStream(ImageDownloader.Scheme.FILE.crop(str)), 1195);
    }

    public InputStream g(String str, Object obj) throws IOException {
        HttpURLConnection httpURLConnectionB = b(str, obj);
        for (int i = 0; httpURLConnectionB.getResponseCode() / 100 == 3 && i < 5; i++) {
            httpURLConnectionB = b(httpURLConnectionB.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION), obj);
        }
        return new BufferedInputStream(httpURLConnectionB.getInputStream(), 1195);
    }

    public InputStream h(String str, Object obj) throws IOException {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", str));
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final void a(HttpsURLConnection httpsURLConnection) {
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLUtil.d);
            Object objA = a(this.f9328a.getPackageName() + ".CustomTrustMgr", null, null);
            if (objA != null) {
                sSLContext.init(null, new TrustManager[]{(TrustManager) objA}, new SecureRandom());
                httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                httpsURLConnection.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
