package supwisdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AmfArray.java */
/* JADX INFO: loaded from: classes.dex */
public class k00 implements m00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<m00> f8104a;
    public int b = -1;

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override // supwisdom.m00
    public int getSize() {
        if (this.b == -1) {
            this.b = 5;
            List<m00> list = this.f8104a;
            if (list != null) {
                Iterator<m00> it = list.iterator();
                while (it.hasNext()) {
                    this.b += it.next().getSize();
                }
            }
        }
        return this.b;
    }

    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
        int iD = j00.d(inputStream);
        this.b = 5;
        this.f8104a = new ArrayList(iD);
        for (int i = 0; i < iD; i++) {
            m00 m00VarA = n00.a(inputStream);
            this.b += m00VarA.getSize();
            this.f8104a.add(m00VarA);
        }
    }
}
