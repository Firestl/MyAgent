package com.efs.sdk.net;

import com.efs.sdk.base.Constants;
import com.efs.sdk.net.a.a.e;
import com.efs.sdk.net.a.a.f;
import com.efs.sdk.net.a.a.g;
import com.efs.sdk.net.a.a.h;
import com.lzy.okgo.model.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterOutputStream;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.jetbrains.annotations.Nullable;
import supwisdom.bt1;
import supwisdom.ct1;
import supwisdom.dt1;
import supwisdom.et1;
import supwisdom.ks1;
import supwisdom.ws1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes.dex */
public class OkHttpInterceptor implements ws1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f1893a = g.c();

    public static class a extends et1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final et1 f1894a;
        public final BufferedSource b;

        public a(et1 et1Var, InputStream inputStream) {
            this.f1894a = et1Var;
            this.b = Okio.buffer(Okio.source(inputStream));
        }

        @Override // supwisdom.et1
        public final long contentLength() {
            return this.f1894a.contentLength();
        }

        @Override // supwisdom.et1
        public final xs1 contentType() {
            return this.f1894a.contentType();
        }

        @Override // supwisdom.et1
        public final BufferedSource source() {
            return this.b;
        }
    }

    public static class b implements f.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1895a;
        public final bt1 b;
        public h c;

        public b(String str, bt1 bt1Var, h hVar) {
            this.f1895a = str;
            this.b = bt1Var;
            this.c = hVar;
        }

        @Override // com.efs.sdk.net.a.a.f.c
        public final String a() {
            return this.f1895a;
        }

        @Override // com.efs.sdk.net.a.a.f.b
        public final String b() {
            return this.b.g().toString();
        }

        @Override // com.efs.sdk.net.a.a.f.b
        public final String c() {
            return this.b.e();
        }

        @Override // com.efs.sdk.net.a.a.f.b
        @Nullable
        public final byte[] d() throws IOException {
            ct1 ct1VarA = this.b.a();
            if (ct1VarA == null) {
                return null;
            }
            h hVar = this.c;
            String strA = this.b.a(HttpHeaders.HEAD_KEY_CONTENT_ENCODING);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            com.efs.sdk.net.a.a.a aVar = new com.efs.sdk.net.a.a.a(Constants.CP_GZIP.equals(strA) ? e.a(byteArrayOutputStream) : "deflate".equals(strA) ? new InflaterOutputStream(byteArrayOutputStream) : byteArrayOutputStream);
            hVar.c = aVar;
            hVar.b = byteArrayOutputStream;
            BufferedSink bufferedSinkBuffer = Okio.buffer(Okio.sink(aVar));
            try {
                ct1VarA.writeTo(bufferedSinkBuffer);
                bufferedSinkBuffer.close();
                h hVar2 = this.c;
                hVar2.b();
                return hVar2.b.toByteArray();
            } catch (Throwable th) {
                bufferedSinkBuffer.close();
                throw th;
            }
        }

        @Override // com.efs.sdk.net.a.a.f.a
        public final int e() {
            return this.b.c().c();
        }

        @Override // com.efs.sdk.net.a.a.f.a
        public final String a(int i) {
            return this.b.c().a(i);
        }

        @Override // com.efs.sdk.net.a.a.f.a
        public final String b(int i) {
            return this.b.c().b(i);
        }
    }

    public static class c implements f.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f1896a;
        public final bt1 b;
        public final dt1 c;

        @Nullable
        public final ks1 d;

        public c(String str, bt1 bt1Var, dt1 dt1Var, @Nullable ks1 ks1Var) {
            this.f1896a = str;
            this.b = bt1Var;
            this.c = dt1Var;
            this.d = ks1Var;
        }

        @Override // com.efs.sdk.net.a.a.f.e
        public final String a() {
            return this.f1896a;
        }

        @Override // com.efs.sdk.net.a.a.f.e
        public final int b() {
            return this.c.c();
        }

        @Override // com.efs.sdk.net.a.a.f.a
        public final int e() {
            return this.c.e().c();
        }

        @Override // com.efs.sdk.net.a.a.f.a
        public final String a(int i) {
            return this.c.e().a(i);
        }

        @Override // com.efs.sdk.net.a.a.f.a
        public final String b(int i) {
            return this.c.e().b(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035 A[Catch: all -> 0x002d, TryCatch #3 {all -> 0x002d, blocks: (B:7:0x001d, B:10:0x0024, B:14:0x0035, B:16:0x0050, B:15:0x004b), top: B:59:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004b A[Catch: all -> 0x002d, TryCatch #3 {all -> 0x002d, blocks: (B:7:0x001d, B:10:0x0024, B:14:0x0035, B:16:0x0050, B:15:0x004b), top: B:59:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c7 A[Catch: all -> 0x00aa, TryCatch #2 {all -> 0x00aa, blocks: (B:29:0x00a3, B:35:0x00ae, B:37:0x00b4, B:38:0x00c1, B:40:0x00c7, B:42:0x00d7, B:44:0x00e2, B:46:0x00e6, B:47:0x00ea, B:49:0x00fd, B:50:0x010f, B:51:0x0116), top: B:58:0x00a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010f A[Catch: all -> 0x00aa, TryCatch #2 {all -> 0x00aa, blocks: (B:29:0x00a3, B:35:0x00ae, B:37:0x00b4, B:38:0x00c1, B:40:0x00c7, B:42:0x00d7, B:44:0x00e2, B:46:0x00e6, B:47:0x00ea, B:49:0x00fd, B:50:0x010f, B:51:0x0116), top: B:58:0x00a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // supwisdom.ws1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.dt1 intercept(supwisdom.ws1.a r10) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.efs.sdk.net.OkHttpInterceptor.intercept(supwisdom.ws1$a):supwisdom.dt1");
    }
}
