package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: AmfNumber.java */
/* JADX INFO: loaded from: classes.dex */
public class q00 implements m00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f8857a;

    public q00(double d) {
        this.f8857a = d;
    }

    public static double b(InputStream inputStream) throws IOException {
        inputStream.read();
        return j00.a(inputStream);
    }

    public double a() {
        return this.f8857a;
    }

    @Override // supwisdom.m00
    public int getSize() {
        return 9;
    }

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.NUMBER.getValue());
        j00.a(outputStream, this.f8857a);
    }

    public q00() {
    }

    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
        this.f8857a = j00.a(inputStream);
    }

    public static void a(OutputStream outputStream, double d) throws IOException {
        outputStream.write(AmfType.NUMBER.getValue());
        j00.a(outputStream, d);
    }
}
