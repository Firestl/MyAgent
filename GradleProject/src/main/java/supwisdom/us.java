package supwisdom;

import com.facebook.imageformat.DefaultImageFormatChecker;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FileTypeBox.java */
/* JADX INFO: loaded from: classes.dex */
public class us extends ws0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9435e;
    public long f;
    public List<String> g;

    public us(String str, long j, List<String> list) {
        super(DefaultImageFormatChecker.HEIF_HEADER_PREFIX);
        this.g = Collections.emptyList();
        this.f9435e = str;
        this.f = j;
        this.g = list;
    }

    @Override // supwisdom.ws0
    public long a() {
        return (this.g.size() * 4) + 8;
    }

    public String e() {
        return this.f9435e;
    }

    public long f() {
        return this.f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileTypeBox[");
        sb.append("majorBrand=");
        sb.append(e());
        sb.append(";");
        sb.append("minorVersion=");
        sb.append(f());
        for (String str : this.g) {
            sb.append(";");
            sb.append("compatibleBrand=");
            sb.append(str);
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        byteBuffer.put(js.a(this.f9435e));
        ks.a(byteBuffer, this.f);
        Iterator<String> it = this.g.iterator();
        while (it.hasNext()) {
            byteBuffer.put(js.a(it.next()));
        }
    }
}
