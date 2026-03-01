package supwisdom;

import android.util.Log;
import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: AmfString.java */
/* JADX INFO: loaded from: classes.dex */
public class s00 implements m00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f9105a;
    public boolean b;
    public int c;

    public s00() {
        this.c = -1;
    }

    public String a() {
        return this.f9105a;
    }

    public boolean b() {
        return this.b;
    }

    @Override // supwisdom.m00
    public int getSize() {
        if (this.c == -1) {
            try {
                this.c = (b() ? 0 : 1) + 2 + this.f9105a.getBytes("ASCII").length;
            } catch (UnsupportedEncodingException e2) {
                Log.e("AmfString", "AmfString.getSize(): caught exception", e2);
                throw new RuntimeException(e2);
            }
        }
        return this.c;
    }

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        byte[] bytes = this.f9105a.getBytes("ASCII");
        if (!this.b) {
            outputStream.write(AmfType.STRING.getValue());
        }
        j00.a(outputStream, bytes.length);
        outputStream.write(bytes);
    }

    public s00(String str, boolean z) {
        this.c = -1;
        this.f9105a = str;
        this.b = z;
    }

    public s00(String str) {
        this(str, false);
    }

    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
        int iB = j00.b(inputStream);
        this.c = iB + 3;
        byte[] bArr = new byte[iB];
        j00.a(inputStream, bArr);
        this.f9105a = new String(bArr, "ASCII");
    }

    public static String a(InputStream inputStream, boolean z) throws IOException {
        if (!z) {
            inputStream.read();
        }
        byte[] bArr = new byte[j00.b(inputStream)];
        j00.a(inputStream, bArr);
        return new String(bArr, "ASCII");
    }

    public static void a(OutputStream outputStream, String str, boolean z) throws IOException {
        byte[] bytes = str.getBytes("ASCII");
        if (!z) {
            outputStream.write(AmfType.STRING.getValue());
        }
        j00.a(outputStream, bytes.length);
        outputStream.write(bytes);
    }

    public static int a(String str, boolean z) {
        try {
            return (z ? 0 : 1) + 2 + str.getBytes("ASCII").length;
        } catch (UnsupportedEncodingException e2) {
            Log.e("AmfString", "AmfString.SizeOf(): caught exception", e2);
            throw new RuntimeException(e2);
        }
    }
}
