package supwisdom;

import com.xiaomi.mipush.sdk.Constants;
import dc.squareup.okhttp3.internal.http2.Header;
import okio.ByteString;

/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes3.dex */
public final class iu1 {
    public static final ByteString d = ByteString.encodeUtf8(Constants.COLON_SEPARATOR);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f7987e = ByteString.encodeUtf8(Header.RESPONSE_STATUS_UTF8);
    public static final ByteString f = ByteString.encodeUtf8(Header.TARGET_METHOD_UTF8);
    public static final ByteString g = ByteString.encodeUtf8(Header.TARGET_PATH_UTF8);
    public static final ByteString h = ByteString.encodeUtf8(Header.TARGET_SCHEME_UTF8);
    public static final ByteString i = ByteString.encodeUtf8(Header.TARGET_AUTHORITY_UTF8);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteString f7988a;
    public final ByteString b;
    public final int c;

    /* JADX INFO: compiled from: Header.java */
    public interface a {
        void a(us1 us1Var);
    }

    public iu1(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof iu1)) {
            return false;
        }
        iu1 iu1Var = (iu1) obj;
        return this.f7988a.equals(iu1Var.f7988a) && this.b.equals(iu1Var.b);
    }

    public int hashCode() {
        return ((527 + this.f7988a.hashCode()) * 31) + this.b.hashCode();
    }

    public String toString() {
        return kt1.a("%s: %s", this.f7988a.utf8(), this.b.utf8());
    }

    public iu1(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public iu1(ByteString byteString, ByteString byteString2) {
        this.f7988a = byteString;
        this.b = byteString2;
        this.c = byteString.size() + 32 + byteString2.size();
    }
}
