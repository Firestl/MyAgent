package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/* JADX INFO: compiled from: AmfMap.java */
/* JADX INFO: loaded from: classes.dex */
public class o00 extends r00 {
    @Override // supwisdom.r00, supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.MAP.getValue());
        j00.c(outputStream, this.f8993a.size());
        for (Map.Entry<String, m00> entry : this.f8993a.entrySet()) {
            s00.a(outputStream, entry.getKey(), true);
            entry.getValue().a(outputStream);
        }
        outputStream.write(r00.c);
    }

    @Override // supwisdom.r00, supwisdom.m00
    public int getSize() {
        if (this.b == -1) {
            int size = super.getSize();
            this.b = size;
            this.b = size + 4;
        }
        return this.b;
    }

    @Override // supwisdom.r00, supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
        j00.d(inputStream);
        super.a(inputStream);
        this.b += 4;
    }
}
