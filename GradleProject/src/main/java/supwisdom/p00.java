package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: AmfNull.java */
/* JADX INFO: loaded from: classes.dex */
public class p00 implements m00 {
    public static void b(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.NULL.getValue());
    }

    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
    }

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.NULL.getValue());
    }

    @Override // supwisdom.m00
    public int getSize() {
        return 1;
    }
}
