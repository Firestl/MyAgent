package supwisdom;

import com.github.faucamp.simplertmp.amf.AmfType;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: AmfObject.java */
/* JADX INFO: loaded from: classes.dex */
public class r00 implements m00 {
    public static final byte[] c = {0, 0, 9};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, m00> f8993a = new LinkedHashMap();
    public int b = -1;

    public m00 a(String str) {
        return this.f8993a.get(str);
    }

    @Override // supwisdom.m00
    public int getSize() {
        if (this.b == -1) {
            this.b = 1;
            for (Map.Entry<String, m00> entry : this.f8993a.entrySet()) {
                int iA = this.b + s00.a(entry.getKey(), true);
                this.b = iA;
                this.b = iA + entry.getValue().getSize();
            }
            this.b += 3;
        }
        return this.b;
    }

    public void a(String str, boolean z) {
        this.f8993a.put(str, new l00(z));
    }

    public void a(String str, String str2) {
        this.f8993a.put(str, new s00(str2, false));
    }

    public void a(String str, int i) {
        this.f8993a.put(str, new q00(i));
    }

    @Override // supwisdom.m00
    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(AmfType.OBJECT.getValue());
        for (Map.Entry<String, m00> entry : this.f8993a.entrySet()) {
            s00.a(outputStream, entry.getKey(), true);
            entry.getValue().a(outputStream);
        }
        outputStream.write(c);
    }

    @Override // supwisdom.m00
    public void a(InputStream inputStream) throws IOException {
        this.b = 1;
        InputStream bufferedInputStream = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream);
        while (true) {
            bufferedInputStream.mark(3);
            byte[] bArr = new byte[3];
            bufferedInputStream.read(bArr);
            byte b = bArr[0];
            byte[] bArr2 = c;
            if (b == bArr2[0] && bArr[1] == bArr2[1] && bArr[2] == bArr2[2]) {
                this.b += 3;
                return;
            }
            bufferedInputStream.reset();
            String strA = s00.a(inputStream, true);
            this.b += s00.a(strA, true);
            m00 m00VarA = n00.a(bufferedInputStream);
            this.b += m00VarA.getSize();
            this.f8993a.put(strA, m00VarA);
        }
    }
}
