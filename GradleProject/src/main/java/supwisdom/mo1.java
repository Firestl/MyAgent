package supwisdom;

import com.taobao.weex.el.parse.Operators;
import cz.msebera.android.httpclient.message.BasicHeader;

/* JADX INFO: compiled from: AbstractHttpEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class mo1 implements ao1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public xn1 f8412a;
    public xn1 b;
    public boolean c;

    @Override // supwisdom.ao1
    public xn1 a() {
        return this.f8412a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START);
        if (this.f8412a != null) {
            sb.append("Content-Type: ");
            sb.append(this.f8412a.getValue());
            sb.append(',');
        }
        if (this.b != null) {
            sb.append("Content-Encoding: ");
            sb.append(this.b.getValue());
            sb.append(',');
        }
        long jB = b();
        if (jB >= 0) {
            sb.append("Content-Length: ");
            sb.append(jB);
            sb.append(',');
        }
        sb.append("Chunked: ");
        sb.append(this.c);
        sb.append(Operators.ARRAY_END);
        return sb.toString();
    }

    public void a(xn1 xn1Var) {
        this.f8412a = xn1Var;
    }

    public void a(String str) {
        a(str != null ? new BasicHeader("Content-Type", str) : null);
    }
}
