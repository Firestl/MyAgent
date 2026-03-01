package supwisdom;

import com.lzy.okgo.model.HttpHeaders;
import com.squareup.okhttp.Protocol;
import dc.squareup.okhttp3.Cache;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.util.JSUtil;
import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import supwisdom.oe1;
import supwisdom.se1;
import supwisdom.ue1;
import supwisdom.xe1;

/* JADX INFO: compiled from: Cache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ce1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final af1 f7180a = new a();
    public final xe1 b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7181e;
    public int f;
    public int g;

    /* JADX INFO: compiled from: Cache.java */
    public class a implements af1 {
        public a() {
        }

        @Override // supwisdom.af1
        public ue1 a(se1 se1Var) throws IOException {
            return ce1.this.a(se1Var);
        }

        @Override // supwisdom.af1
        public void b(se1 se1Var) throws IOException {
            ce1.this.b(se1Var);
        }

        @Override // supwisdom.af1
        public void trackConditionalCacheHit() {
            ce1.this.a();
        }

        @Override // supwisdom.af1
        public jf1 a(ue1 ue1Var) throws IOException {
            return ce1.this.a(ue1Var);
        }

        @Override // supwisdom.af1
        public void a(ue1 ue1Var, ue1 ue1Var2) throws IOException {
            ce1.this.a(ue1Var, ue1Var2);
        }

        @Override // supwisdom.af1
        public void a(kf1 kf1Var) {
            ce1.this.a(kf1Var);
        }
    }

    /* JADX INFO: compiled from: Cache.java */
    public final class b implements jf1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xe1.d f7183a;
        public Sink b;
        public boolean c;
        public Sink d;

        /* JADX INFO: compiled from: Cache.java */
        public class a extends ForwardingSink {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ xe1.d f7185a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Sink sink, ce1 ce1Var, xe1.d dVar) {
                super(sink);
                this.f7185a = dVar;
            }

            @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                synchronized (ce1.this) {
                    if (b.this.c) {
                        return;
                    }
                    b.this.c = true;
                    ce1.b(ce1.this);
                    super.close();
                    this.f7185a.b();
                }
            }
        }

        public b(xe1.d dVar) throws IOException {
            this.f7183a = dVar;
            Sink sinkA = dVar.a(1);
            this.b = sinkA;
            this.d = new a(sinkA, ce1.this, dVar);
        }

        @Override // supwisdom.jf1
        public void abort() {
            synchronized (ce1.this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                ce1.c(ce1.this);
                gf1.a(this.b);
                try {
                    this.f7183a.a();
                } catch (IOException unused) {
                }
            }
        }

        @Override // supwisdom.jf1
        public Sink body() {
            return this.d;
        }
    }

    /* JADX INFO: compiled from: Cache.java */
    public static class c extends ve1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final xe1.f f7186a;
        public final BufferedSource b;
        public final String c;

        /* JADX INFO: compiled from: Cache.java */
        public class a extends ForwardingSource {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ xe1.f f7187a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Source source, xe1.f fVar) {
                super(source);
                this.f7187a = fVar;
            }

            @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                this.f7187a.close();
                super.close();
            }
        }

        public c(xe1.f fVar, String str, String str2) {
            this.f7186a = fVar;
            this.c = str2;
            this.b = Okio.buffer(new a(fVar.a(1), fVar));
        }

        @Override // supwisdom.ve1
        public long b() {
            try {
                if (this.c != null) {
                    return Long.parseLong(this.c);
                }
                return -1L;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // supwisdom.ve1
        public BufferedSource c() {
            return this.b;
        }
    }

    public ce1(File file, long j) {
        this.b = xe1.a(zf1.f9997a, file, Cache.VERSION, 2, j);
    }

    public static /* synthetic */ int b(ce1 ce1Var) {
        int i = ce1Var.c;
        ce1Var.c = i + 1;
        return i;
    }

    public static /* synthetic */ int c(ce1 ce1Var) {
        int i = ce1Var.d;
        ce1Var.d = i + 1;
        return i;
    }

    public static String c(se1 se1Var) {
        return gf1.b(se1Var.i());
    }

    public final void b(se1 se1Var) throws IOException {
        this.b.d(c(se1Var));
    }

    public static int b(BufferedSource bufferedSource) throws IOException {
        try {
            long decimalLong = bufferedSource.readDecimalLong();
            String utf8LineStrict = bufferedSource.readUtf8LineStrict();
            if (decimalLong >= 0 && decimalLong <= 2147483647L && utf8LineStrict.isEmpty()) {
                return (int) decimalLong;
            }
            throw new IOException("expected an int but was \"" + decimalLong + utf8LineStrict + JSUtil.QUOTE);
        } catch (NumberFormatException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public ue1 a(se1 se1Var) {
        try {
            xe1.f fVarB = this.b.b(c(se1Var));
            if (fVarB == null) {
                return null;
            }
            try {
                d dVar = new d(fVarB.a(0));
                ue1 ue1VarA = dVar.a(se1Var, fVarB);
                if (dVar.a(se1Var, ue1VarA)) {
                    return ue1VarA;
                }
                gf1.a(ue1VarA.a());
                return null;
            } catch (IOException unused) {
                gf1.a(fVarB);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    public final jf1 a(ue1 ue1Var) throws IOException {
        xe1.d dVarA;
        String strE = ue1Var.l().e();
        if (pf1.a(ue1Var.l().e())) {
            try {
                b(ue1Var.l());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!strE.equals("GET") || rf1.b(ue1Var)) {
            return null;
        }
        d dVar = new d(ue1Var);
        try {
            dVarA = this.b.a(c(ue1Var.l()));
            if (dVarA == null) {
                return null;
            }
            try {
                dVar.a(dVarA);
                return new b(dVarA);
            } catch (IOException unused2) {
                a(dVarA);
                return null;
            }
        } catch (IOException unused3) {
            dVarA = null;
        }
    }

    public final void a(ue1 ue1Var, ue1 ue1Var2) {
        xe1.d dVarA;
        d dVar = new d(ue1Var2);
        try {
            dVarA = ((c) ue1Var.a()).f7186a.a();
            if (dVarA != null) {
                try {
                    dVar.a(dVarA);
                    dVarA.b();
                } catch (IOException unused) {
                    a(dVarA);
                }
            }
        } catch (IOException unused2) {
            dVarA = null;
        }
    }

    /* JADX INFO: compiled from: Cache.java */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f7188a;
        public final oe1 b;
        public final String c;
        public final Protocol d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f7189e;
        public final String f;
        public final oe1 g;
        public final ne1 h;

        public d(Source source) throws IOException {
            try {
                BufferedSource bufferedSourceBuffer = Okio.buffer(source);
                this.f7188a = bufferedSourceBuffer.readUtf8LineStrict();
                this.c = bufferedSourceBuffer.readUtf8LineStrict();
                oe1.b bVar = new oe1.b();
                int iB = ce1.b(bufferedSourceBuffer);
                for (int i = 0; i < iB; i++) {
                    bVar.a(bufferedSourceBuffer.readUtf8LineStrict());
                }
                this.b = bVar.a();
                xf1 xf1VarA = xf1.a(bufferedSourceBuffer.readUtf8LineStrict());
                this.d = xf1VarA.f9762a;
                this.f7189e = xf1VarA.b;
                this.f = xf1VarA.c;
                oe1.b bVar2 = new oe1.b();
                int iB2 = ce1.b(bufferedSourceBuffer);
                for (int i2 = 0; i2 < iB2; i2++) {
                    bVar2.a(bufferedSourceBuffer.readUtf8LineStrict());
                }
                this.g = bVar2.a();
                if (a()) {
                    String utf8LineStrict = bufferedSourceBuffer.readUtf8LineStrict();
                    if (utf8LineStrict.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + utf8LineStrict + JSUtil.QUOTE);
                    }
                    this.h = ne1.a(bufferedSourceBuffer.readUtf8LineStrict(), a(bufferedSourceBuffer), a(bufferedSourceBuffer));
                } else {
                    this.h = null;
                }
            } finally {
                source.close();
            }
        }

        public void a(xe1.d dVar) throws IOException {
            BufferedSink bufferedSinkBuffer = Okio.buffer(dVar.a(0));
            bufferedSinkBuffer.writeUtf8(this.f7188a);
            bufferedSinkBuffer.writeByte(10);
            bufferedSinkBuffer.writeUtf8(this.c);
            bufferedSinkBuffer.writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.b.b());
            bufferedSinkBuffer.writeByte(10);
            int iB = this.b.b();
            for (int i = 0; i < iB; i++) {
                bufferedSinkBuffer.writeUtf8(this.b.a(i));
                bufferedSinkBuffer.writeUtf8(": ");
                bufferedSinkBuffer.writeUtf8(this.b.b(i));
                bufferedSinkBuffer.writeByte(10);
            }
            bufferedSinkBuffer.writeUtf8(new xf1(this.d, this.f7189e, this.f).toString());
            bufferedSinkBuffer.writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.g.b());
            bufferedSinkBuffer.writeByte(10);
            int iB2 = this.g.b();
            for (int i2 = 0; i2 < iB2; i2++) {
                bufferedSinkBuffer.writeUtf8(this.g.a(i2));
                bufferedSinkBuffer.writeUtf8(": ");
                bufferedSinkBuffer.writeUtf8(this.g.b(i2));
                bufferedSinkBuffer.writeByte(10);
            }
            if (a()) {
                bufferedSinkBuffer.writeByte(10);
                bufferedSinkBuffer.writeUtf8(this.h.a());
                bufferedSinkBuffer.writeByte(10);
                a(bufferedSinkBuffer, this.h.c());
                a(bufferedSinkBuffer, this.h.b());
            }
            bufferedSinkBuffer.close();
        }

        public d(ue1 ue1Var) {
            this.f7188a = ue1Var.l().i();
            this.b = rf1.d(ue1Var);
            this.c = ue1Var.l().e();
            this.d = ue1Var.k();
            this.f7189e = ue1Var.e();
            this.f = ue1Var.h();
            this.g = ue1Var.g();
            this.h = ue1Var.f();
        }

        public final boolean a() {
            return this.f7188a.startsWith(DeviceInfo.HTTPS_PROTOCOL);
        }

        public final List<Certificate> a(BufferedSource bufferedSource) throws IOException {
            int iB = ce1.b(bufferedSource);
            if (iB == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(iB);
                for (int i = 0; i < iB; i++) {
                    String utf8LineStrict = bufferedSource.readUtf8LineStrict();
                    Buffer buffer = new Buffer();
                    buffer.write(ByteString.decodeBase64(utf8LineStrict));
                    arrayList.add(certificateFactory.generateCertificate(buffer.inputStream()));
                }
                return arrayList;
            } catch (CertificateException e2) {
                throw new IOException(e2.getMessage());
            }
        }

        public final void a(BufferedSink bufferedSink, List<Certificate> list) throws IOException {
            try {
                bufferedSink.writeDecimalLong(list.size());
                bufferedSink.writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bufferedSink.writeUtf8(ByteString.of(list.get(i).getEncoded()).base64());
                    bufferedSink.writeByte(10);
                }
            } catch (CertificateEncodingException e2) {
                throw new IOException(e2.getMessage());
            }
        }

        public boolean a(se1 se1Var, ue1 ue1Var) {
            return this.f7188a.equals(se1Var.i()) && this.c.equals(se1Var.e()) && rf1.a(ue1Var, this.b, se1Var);
        }

        public ue1 a(se1 se1Var, xe1.f fVar) {
            String strA = this.g.a("Content-Type");
            String strA2 = this.g.a(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
            se1.b bVar = new se1.b();
            bVar.b(this.f7188a);
            bVar.a(this.c, (te1) null);
            bVar.a(this.b);
            se1 se1VarA = bVar.a();
            ue1.b bVar2 = new ue1.b();
            bVar2.a(se1VarA);
            bVar2.a(this.d);
            bVar2.a(this.f7189e);
            bVar2.a(this.f);
            bVar2.a(this.g);
            bVar2.a(new c(fVar, strA, strA2));
            bVar2.a(this.h);
            return bVar2.a();
        }
    }

    public final void a(xe1.d dVar) {
        if (dVar != null) {
            try {
                dVar.a();
            } catch (IOException unused) {
            }
        }
    }

    public final synchronized void a(kf1 kf1Var) {
        this.g++;
        if (kf1Var.f8170a != null) {
            this.f7181e++;
        } else if (kf1Var.b != null) {
            this.f++;
        }
    }

    public final synchronized void a() {
        this.f++;
    }
}
