package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: compiled from: HandlerBox.java */
/* JADX INFO: loaded from: classes.dex */
public class ws extends ys0 {
    public String g;
    public String h;
    public long i;
    public long j;
    public long k;
    public boolean l;
    public long m;

    static {
        HashMap map = new HashMap();
        map.put("odsm", "ObjectDescriptorStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("crsm", "ClockReferenceStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("sdsm", "SceneDescriptionStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("m7sm", "MPEG7Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("ocsm", "ObjectContentInfoStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("ipsm", "IPMP Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("mjsm", "MPEG-J Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        map.put("mdir", "Apple Meta Data iTunes Reader");
        map.put("mp7b", "MPEG-7 binary XML");
        map.put("mp7t", "MPEG-7 XML");
        map.put("vide", "Video Track");
        map.put("soun", "Sound Track");
        map.put("hint", "Hint Track");
        map.put("appl", "Apple specific");
        map.put("meta", "Timed Metadata track - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        Collections.unmodifiableMap(map);
    }

    public ws() {
        super("hdlr");
        this.h = null;
        this.l = true;
    }

    public void a(String str) {
        this.g = str;
    }

    public void b(String str) {
        this.h = str;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String toString() {
        return "HandlerBox[handlerType=" + g() + ";name=" + h() + Operators.ARRAY_END_STR;
    }

    @Override // supwisdom.ws0
    public long a() {
        return this.l ? ms.b(this.h) + 25 : ms.b(this.h) + 24;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        ks.a(byteBuffer, this.m);
        byteBuffer.put(js.a(this.g));
        ks.a(byteBuffer, this.i);
        ks.a(byteBuffer, this.j);
        ks.a(byteBuffer, this.k);
        String str = this.h;
        if (str != null) {
            byteBuffer.put(ms.a(str));
        }
        if (this.l) {
            byteBuffer.put((byte) 0);
        }
    }
}
