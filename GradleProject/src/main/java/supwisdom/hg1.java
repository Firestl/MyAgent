package supwisdom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSource;
import okio.InflaterSource;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: NameValueBlockReader.java */
/* JADX INFO: loaded from: classes2.dex */
public class hg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InflaterSource f7836a;
    public int b;
    public final BufferedSource c;

    /* JADX INFO: compiled from: NameValueBlockReader.java */
    public class a extends ForwardingSource {
        public a(Source source) {
            super(source);
        }

        @Override // okio.ForwardingSource, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (hg1.this.b == 0) {
                return -1L;
            }
            long j2 = super.read(buffer, Math.min(j, hg1.this.b));
            if (j2 == -1) {
                return -1L;
            }
            hg1 hg1Var = hg1.this;
            hg1Var.b = (int) (((long) hg1Var.b) - j2);
            return j2;
        }
    }

    /* JADX INFO: compiled from: NameValueBlockReader.java */
    public class b extends Inflater {
        public b(hg1 hg1Var) {
        }

        @Override // java.util.zip.Inflater
        public int inflate(byte[] bArr, int i, int i2) throws DataFormatException {
            int iInflate = super.inflate(bArr, i, i2);
            if (iInflate != 0 || !needsDictionary()) {
                return iInflate;
            }
            setDictionary(lg1.f8287a);
            return super.inflate(bArr, i, i2);
        }
    }

    public hg1(BufferedSource bufferedSource) {
        InflaterSource inflaterSource = new InflaterSource(new a(bufferedSource), new b(this));
        this.f7836a = inflaterSource;
        this.c = Okio.buffer(inflaterSource);
    }

    public final void b() throws IOException {
        if (this.b > 0) {
            this.f7836a.refill();
            if (this.b == 0) {
                return;
            }
            throw new IOException("compressedLimit > 0: " + this.b);
        }
    }

    public final ByteString c() throws IOException {
        return this.c.readByteString(this.c.readInt());
    }

    public List<cg1> a(int i) throws IOException {
        this.b += i;
        int i2 = this.c.readInt();
        if (i2 < 0) {
            throw new IOException("numberOfPairs < 0: " + i2);
        }
        if (i2 <= 1024) {
            ArrayList arrayList = new ArrayList(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                ByteString asciiLowercase = c().toAsciiLowercase();
                ByteString byteStringC = c();
                if (asciiLowercase.size() != 0) {
                    arrayList.add(new cg1(asciiLowercase, byteStringC));
                } else {
                    throw new IOException("name.size == 0");
                }
            }
            b();
            return arrayList;
        }
        throw new IOException("numberOfPairs > 1024: " + i2);
    }

    public void a() throws IOException {
        this.c.close();
    }
}
