package supwisdom;

import dc.squareup.okhttp3.internal.http2.Header;
import okio.ByteString;

/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cg1 {
    public static final ByteString d = ByteString.encodeUtf8(Header.RESPONSE_STATUS_UTF8);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f7208e = ByteString.encodeUtf8(Header.TARGET_METHOD_UTF8);
    public static final ByteString f = ByteString.encodeUtf8(Header.TARGET_PATH_UTF8);
    public static final ByteString g = ByteString.encodeUtf8(Header.TARGET_SCHEME_UTF8);
    public static final ByteString h = ByteString.encodeUtf8(Header.TARGET_AUTHORITY_UTF8);
    public static final ByteString i = ByteString.encodeUtf8(":host");
    public static final ByteString j = ByteString.encodeUtf8(":version");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteString f7209a;
    public final ByteString b;
    public final int c;

    public cg1(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof cg1)) {
            return false;
        }
        cg1 cg1Var = (cg1) obj;
        return this.f7209a.equals(cg1Var.f7209a) && this.b.equals(cg1Var.b);
    }

    public int hashCode() {
        return ((527 + this.f7209a.hashCode()) * 31) + this.b.hashCode();
    }

    public String toString() {
        return String.format("%s: %s", this.f7209a.utf8(), this.b.utf8());
    }

    public cg1(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public cg1(ByteString byteString, ByteString byteString2) {
        this.f7209a = byteString;
        this.b = byteString2;
        this.c = byteString.size() + 32 + byteString2.size();
    }
}
