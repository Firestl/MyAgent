package supwisdom;

import android.util.Base64;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class v9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9491a;
    public final String b;
    public final String c;
    public final List<List<byte[]>> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9492e;
    public final String f;

    public v9(String str, String str2, String str3, List<List<byte[]>> list) {
        na.a(str);
        this.f9491a = str;
        na.a(str2);
        this.b = str2;
        na.a(str3);
        this.c = str3;
        na.a(list);
        this.d = list;
        this.f9492e = 0;
        this.f = a(str, str2, str3);
    }

    public final String a(String str, String str2, String str3) {
        return str + "-" + str2 + "-" + str3;
    }

    public int b() {
        return this.f9492e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f9491a;
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f9491a + ", mProviderPackage: " + this.b + ", mQuery: " + this.c + ", mCertificates:");
        for (int i = 0; i < this.d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append(JSUtil.QUOTE);
            }
            sb.append(" ]");
        }
        sb.append(Operators.BLOCK_END_STR);
        sb.append("mCertificatesArray: " + this.f9492e);
        return sb.toString();
    }

    public List<List<byte[]>> a() {
        return this.d;
    }
}
