package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: AmfUndefined.java */
/* JADX INFO: loaded from: classes.dex */
public class t00 implements m00 {
    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
    }

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.UNDEFINED.getValue());
    }

    @Override // supwisdom.m00
    public int getSize() {
        return 1;
    }
}
