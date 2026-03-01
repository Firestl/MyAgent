package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: AmfBoolean.java */
/* JADX INFO: loaded from: classes.dex */
public class l00 implements m00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8226a;

    public l00(boolean z) {
        this.f8226a = z;
    }

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.BOOLEAN.getValue());
        outputStream.write(this.f8226a ? 1 : 0);
    }

    @Override // supwisdom.m00
    public int getSize() {
        return 2;
    }

    public l00() {
    }

    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
        this.f8226a = inputStream.read() == 1;
    }
}
