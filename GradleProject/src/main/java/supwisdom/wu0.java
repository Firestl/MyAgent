package supwisdom;

import android.text.TextUtils;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.message.BasicHeader;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.JSUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* JADX INFO: compiled from: SimpleMultipartEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public class wu0 implements ao1 {
    public static final byte[] f = Base64.CRLF.getBytes();
    public static final byte[] g = "Content-Transfer-Encoding: binary\r\n".getBytes();
    public static final char[] h = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9664a;
    public final byte[] b;
    public final byte[] c;
    public final List<a> d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ByteArrayOutputStream f9665e = new ByteArrayOutputStream();

    public wu0(vu0 vu0Var) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            char[] cArr = h;
            sb.append(cArr[random.nextInt(cArr.length)]);
        }
        this.f9664a = sb.toString();
        this.b = ("--" + this.f9664a + Base64.CRLF).getBytes();
        this.c = ("--" + this.f9664a + "--" + Base64.CRLF).getBytes();
    }

    public void a(boolean z) {
    }

    public void b(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = "UTF-8";
        }
        a(str, str2, "text/plain; charset=" + str3);
    }

    public final String c(String str) {
        return str == null ? RequestParams.APPLICATION_OCTET_STREAM : str;
    }

    public final byte[] b(String str) {
        return ("Content-Type: " + c(str) + Base64.CRLF).getBytes();
    }

    public void a(String str, String str2, String str3) {
        try {
            this.f9665e.write(this.b);
            this.f9665e.write(a(str));
            this.f9665e.write(b(str3));
            this.f9665e.write(f);
            this.f9665e.write(str2.getBytes());
            this.f9665e.write(f);
        } catch (IOException e2) {
            ru0.f9092a.e("SimpleMultipartEntity", "addPart ByteArrayOutputStream exception", e2);
        }
    }

    @Override // supwisdom.ao1
    public long b() {
        long size = this.f9665e.size();
        Iterator<a> it = this.d.iterator();
        while (it.hasNext()) {
            long jA = it.next().a();
            if (jA < 0) {
                return -1L;
            }
            size += jA;
        }
        return size + ((long) this.c.length);
    }

    /* JADX INFO: compiled from: SimpleMultipartEntity.java */
    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final File f9666a;
        public final byte[] b;

        public a(String str, File file, String str2, String str3) {
            this.b = a(str, TextUtils.isEmpty(str3) ? file.getName() : str3, str2);
            this.f9666a = file;
        }

        public final byte[] a(String str, String str2, String str3) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(wu0.this.b);
                byteArrayOutputStream.write(wu0.this.a(str, str2));
                byteArrayOutputStream.write(wu0.this.b(str3));
                byteArrayOutputStream.write(wu0.g);
                byteArrayOutputStream.write(wu0.f);
            } catch (IOException e2) {
                ru0.f9092a.e("SimpleMultipartEntity", "createHeader ByteArrayOutputStream exception", e2);
            }
            return byteArrayOutputStream.toByteArray();
        }

        public long a() {
            return ((long) this.b.length) + this.f9666a.length() + ((long) wu0.f.length);
        }
    }

    public void a(String str, File file, String str2, String str3) {
        this.d.add(new a(str, file, c(str2), str3));
    }

    public void a(String str, String str2, InputStream inputStream, String str3) throws IOException {
        this.f9665e.write(this.b);
        this.f9665e.write(a(str, str2));
        this.f9665e.write(b(str3));
        this.f9665e.write(g);
        this.f9665e.write(f);
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                this.f9665e.write(bArr, 0, i);
            } else {
                this.f9665e.write(f);
                this.f9665e.flush();
                return;
            }
        }
    }

    public final byte[] a(String str) {
        return ("Content-Disposition: form-data; name=\"" + str + JSUtil.QUOTE + Base64.CRLF).getBytes();
    }

    public final byte[] a(String str, String str2) {
        return ("Content-Disposition: form-data; name=\"" + str + JSUtil.QUOTE + "; filename=\"" + str2 + JSUtil.QUOTE + Base64.CRLF).getBytes();
    }

    @Override // supwisdom.ao1
    public xn1 a() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.f9664a);
    }
}
