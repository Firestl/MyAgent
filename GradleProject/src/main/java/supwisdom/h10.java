package supwisdom;

import com.github.faucamp.simplertmp.packets.RtmpHeader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: VariableBodyRtmpPacket.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h10 extends f10 {
    public List<m00> b;

    public h10(RtmpHeader rtmpHeader) {
        super(rtmpHeader);
    }

    public void a(String str) {
        a(new s00(str));
    }

    public void b(OutputStream outputStream) throws IOException {
        List<m00> list = this.b;
        if (list == null) {
            p00.b(outputStream);
            return;
        }
        Iterator<m00> it = list.iterator();
        while (it.hasNext()) {
            it.next().a(outputStream);
        }
    }

    public List<m00> d() {
        return this.b;
    }

    public void a(m00 m00Var) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        if (m00Var == null) {
            m00Var = new p00();
        }
        this.b.add(m00Var);
    }

    public void a(InputStream inputStream, int i) throws IOException {
        do {
            m00 m00VarA = n00.a(inputStream);
            a(m00VarA);
            i += m00VarA.getSize();
        } while (i < this.f7557a.d());
    }
}
