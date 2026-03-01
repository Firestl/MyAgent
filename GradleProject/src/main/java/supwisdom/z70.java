package supwisdom;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.i.q;
import com.lzy.okgo.model.HttpHeaders;
import com.taobao.weex.el.parse.Operators;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: DefaultHttpDataSource.java */
/* JADX INFO: loaded from: classes.dex */
public class z70 implements com.google.android.exoplayer2.i.q {
    public static final Pattern q = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    public static final AtomicReference<byte[]> r = new AtomicReference<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9973a;
    public final int b;
    public final int c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q80<String> f9974e;
    public final q.g f;
    public final q.g g;
    public final d80<? super z70> h;
    public u70 i;
    public HttpURLConnection j;
    public InputStream k;
    public boolean l;
    public long m;
    public long n;
    public long o;
    public long p;

    public z70(String str, q80<String> q80Var, d80<? super z70> d80Var, int i, int i2, boolean z, q.g gVar) {
        e80.a(str);
        this.d = str;
        this.f9974e = q80Var;
        this.h = d80Var;
        this.g = new q.g();
        this.b = i;
        this.c = i2;
        this.f9973a = z;
        this.f = gVar;
    }

    @Override // supwisdom.s70
    public long a(u70 u70Var) throws q.c {
        this.i = u70Var;
        long j = 0;
        this.p = 0L;
        this.o = 0L;
        try {
            HttpURLConnection httpURLConnectionB = b(u70Var);
            this.j = httpURLConnectionB;
            try {
                int responseCode = httpURLConnectionB.getResponseCode();
                if (responseCode < 200 || responseCode > 299) {
                    Map<String, List<String>> headerFields = this.j.getHeaderFields();
                    e();
                    q.e eVar = new q.e(responseCode, headerFields, u70Var);
                    if (responseCode != 416) {
                        throw eVar;
                    }
                    eVar.initCause(new com.google.android.exoplayer2.i.g(0));
                    throw eVar;
                }
                String contentType = this.j.getContentType();
                q80<String> q80Var = this.f9974e;
                if (q80Var != null && !q80Var.a(contentType)) {
                    e();
                    throw new q.d(contentType, u70Var);
                }
                if (responseCode == 200) {
                    long j2 = u70Var.d;
                    if (j2 != 0) {
                        j = j2;
                    }
                }
                this.m = j;
                if (u70Var.a(1)) {
                    this.n = u70Var.f9383e;
                } else {
                    long j3 = u70Var.f9383e;
                    if (j3 != -1) {
                        this.n = j3;
                    } else {
                        long jA = a(this.j);
                        this.n = jA != -1 ? jA - this.m : -1L;
                    }
                }
                try {
                    this.k = this.j.getInputStream();
                    this.l = true;
                    d80<? super z70> d80Var = this.h;
                    if (d80Var != null) {
                        d80Var.a(this, u70Var);
                    }
                    return this.n;
                } catch (IOException e2) {
                    e();
                    throw new q.c(e2, u70Var, 1);
                }
            } catch (IOException e3) {
                e();
                throw new q.c("Unable to connect to " + u70Var.f9382a.toString(), e3, u70Var, 1);
            }
        } catch (IOException e4) {
            throw new q.c("Unable to connect to " + u70Var.f9382a.toString(), e4, u70Var, 1);
        }
    }

    @Override // supwisdom.s70
    public Uri b() {
        HttpURLConnection httpURLConnection = this.j;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public final long c() {
        long j = this.n;
        return j == -1 ? j : j - this.p;
    }

    public final void d() throws IOException {
        if (this.o == this.m) {
            return;
        }
        byte[] andSet = r.getAndSet(null);
        if (andSet == null) {
            andSet = new byte[4096];
        }
        while (true) {
            long j = this.o;
            long j2 = this.m;
            if (j == j2) {
                r.set(andSet);
                return;
            }
            int i = this.k.read(andSet, 0, (int) Math.min(j2 - j, andSet.length));
            if (Thread.interrupted()) {
                throw new InterruptedIOException();
            }
            if (i == -1) {
                throw new EOFException();
            }
            this.o += (long) i;
            d80<? super z70> d80Var = this.h;
            if (d80Var != null) {
                d80Var.a(this, i);
            }
        }
    }

    public final void e() {
        HttpURLConnection httpURLConnection = this.j;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e2) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e2);
            }
            this.j = null;
        }
    }

    public final HttpURLConnection b(u70 u70Var) throws IOException {
        HttpURLConnection httpURLConnectionA;
        URL url = new URL(u70Var.f9382a.toString());
        byte[] bArr = u70Var.b;
        long j = u70Var.d;
        long j2 = u70Var.f9383e;
        boolean zA = u70Var.a(1);
        if (!this.f9973a) {
            return a(url, bArr, j, j2, zA, true);
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i > 20) {
                throw new NoRouteToHostException("Too many redirects: " + i2);
            }
            long j3 = j;
            httpURLConnectionA = a(url, bArr, j, j2, zA, false);
            int responseCode = httpURLConnectionA.getResponseCode();
            if (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303 && (bArr != null || (responseCode != 307 && responseCode != 308))) {
                break;
            }
            bArr = null;
            String headerField = httpURLConnectionA.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
            httpURLConnectionA.disconnect();
            url = a(url, headerField);
            i = i2;
            j = j3;
        }
        return httpURLConnectionA;
    }

    public final int b(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.n;
        if (j != -1) {
            long j2 = j - this.p;
            if (j2 == 0) {
                return -1;
            }
            i2 = (int) Math.min(i2, j2);
        }
        int i3 = this.k.read(bArr, i, i2);
        if (i3 == -1) {
            if (this.n == -1) {
                return -1;
            }
            throw new EOFException();
        }
        this.p += (long) i3;
        d80<? super z70> d80Var = this.h;
        if (d80Var != null) {
            d80Var.a(this, i3);
        }
        return i3;
    }

    @Override // supwisdom.s70
    public int a(byte[] bArr, int i, int i2) throws q.c {
        try {
            d();
            return b(bArr, i, i2);
        } catch (IOException e2) {
            throw new q.c(e2, this.i, 2);
        }
    }

    @Override // supwisdom.s70
    public void a() throws q.c {
        try {
            if (this.k != null) {
                a(this.j, c());
                try {
                    this.k.close();
                } catch (IOException e2) {
                    throw new q.c(e2, this.i, 3);
                }
            }
        } finally {
            this.k = null;
            e();
            if (this.l) {
                this.l = false;
                d80<? super z70> d80Var = this.h;
                if (d80Var != null) {
                    d80Var.a(this);
                }
            }
        }
    }

    public final HttpURLConnection a(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        q.g gVar = this.f;
        if (gVar != null) {
            for (Map.Entry<String, String> entry : gVar.a().entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry2 : this.g.a().entrySet()) {
            httpURLConnection.setRequestProperty(entry2.getKey(), entry2.getValue());
        }
        if (j != 0 || j2 != -1) {
            String str = "bytes=" + j + "-";
            if (j2 != -1) {
                str = str + ((j + j2) - 1);
            }
            httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_RANGE, str);
        }
        httpURLConnection.setRequestProperty("User-Agent", this.d);
        if (!z) {
            httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod("POST");
            if (bArr.length == 0) {
                httpURLConnection.connect();
            } else {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
            }
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    public static URL a(URL url, String str) throws IOException {
        if (str != null) {
            URL url2 = new URL(url, str);
            String protocol = url2.getProtocol();
            if ("https".equals(protocol) || "http".equals(protocol)) {
                return url2;
            }
            throw new ProtocolException("Unsupported protocol redirect: " + protocol);
        }
        throw new ProtocolException("Null location redirect");
    }

    public static long a(HttpURLConnection httpURLConnection) {
        long j;
        String headerField = httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
        if (TextUtils.isEmpty(headerField)) {
            j = -1;
        } else {
            try {
                j = Long.parseLong(headerField);
            } catch (NumberFormatException unused) {
                Log.e("DefaultHttpDataSource", "Unexpected Content-Length [" + headerField + Operators.ARRAY_END_STR);
                j = -1;
            }
        }
        String headerField2 = httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_RANGE);
        if (TextUtils.isEmpty(headerField2)) {
            return j;
        }
        Matcher matcher = q.matcher(headerField2);
        if (!matcher.find()) {
            return j;
        }
        try {
            long j2 = (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            if (j < 0) {
                return j2;
            }
            if (j == j2) {
                return j;
            }
            Log.w("DefaultHttpDataSource", "Inconsistent headers [" + headerField + "] [" + headerField2 + Operators.ARRAY_END_STR);
            return Math.max(j, j2);
        } catch (NumberFormatException unused2) {
            Log.e("DefaultHttpDataSource", "Unexpected Content-Range [" + headerField2 + Operators.ARRAY_END_STR);
            return j;
        }
    }

    public static void a(HttpURLConnection httpURLConnection, long j) {
        int i = x80.f9718a;
        if (i == 19 || i == 20) {
            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                if (j == -1) {
                    if (inputStream.read() == -1) {
                        return;
                    }
                } else if (j <= 2048) {
                    return;
                }
                String name = inputStream.getClass().getName();
                if (name.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream") || name.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream")) {
                    Method declaredMethod = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(inputStream, new Object[0]);
                }
            } catch (Exception unused) {
            }
        }
    }
}
