package supwisdom;

import dc.squareup.okhttp3.HttpUrl;
import io.dcloud.common.util.net.NetWork;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: compiled from: FormBody.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ss1 extends ct1 {
    public static final xs1 c = xs1.a(NetWork.CONTENT_TYPE_UPLOAD);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<String> f9209a;
    public final List<String> b;

    /* JADX INFO: compiled from: FormBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f9210a;
        public final List<String> b;
        public final Charset c;

        public a() {
            this(null);
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f9210a.add(vs1.a(str, HttpUrl.FORM_ENCODE_SET, false, false, true, true, this.c));
            this.b.add(vs1.a(str2, HttpUrl.FORM_ENCODE_SET, false, false, true, true, this.c));
            return this;
        }

        public a b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f9210a.add(vs1.a(str, HttpUrl.FORM_ENCODE_SET, true, false, true, true, this.c));
            this.b.add(vs1.a(str2, HttpUrl.FORM_ENCODE_SET, true, false, true, true, this.c));
            return this;
        }

        public a(Charset charset) {
            this.f9210a = new ArrayList();
            this.b = new ArrayList();
            this.c = charset;
        }

        public ss1 a() {
            return new ss1(this.f9210a, this.b);
        }
    }

    public ss1(List<String> list, List<String> list2) {
        this.f9209a = kt1.a(list);
        this.b = kt1.a(list2);
    }

    public final long a(@Nullable BufferedSink bufferedSink, boolean z) {
        Buffer buffer = z ? new Buffer() : bufferedSink.buffer();
        int size = this.f9209a.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.writeByte(38);
            }
            buffer.writeUtf8(this.f9209a.get(i));
            buffer.writeByte(61);
            buffer.writeUtf8(this.b.get(i));
        }
        if (!z) {
            return 0L;
        }
        long size2 = buffer.size();
        buffer.clear();
        return size2;
    }

    @Override // supwisdom.ct1
    public long contentLength() {
        return a(null, true);
    }

    @Override // supwisdom.ct1
    public xs1 contentType() {
        return c;
    }

    @Override // supwisdom.ct1
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        a(bufferedSink, false);
    }
}
