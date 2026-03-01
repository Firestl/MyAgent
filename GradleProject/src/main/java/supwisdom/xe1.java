package supwisdom;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xe1 implements Closeable {
    public static final Pattern s = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final Sink t = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final zf1 f9749a;
    public final File b;
    public final File c;
    public final File d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final File f9750e;
    public final int f;
    public long g;
    public final int h;
    public BufferedSink j;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public final Executor q;
    public long i = 0;
    public final LinkedHashMap<String, e> k = new LinkedHashMap<>(0, 0.75f, true);
    public long p = 0;
    public final Runnable r = new a();

    /* JADX INFO: compiled from: DiskLruCache.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (xe1.this) {
                if ((!xe1.this.n) || xe1.this.o) {
                    return;
                }
                try {
                    xe1.this.i();
                    if (xe1.this.d()) {
                        xe1.this.h();
                        xe1.this.l = 0;
                    }
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public class b extends ye1 {
        public b(Sink sink) {
            super(sink);
        }

        @Override // supwisdom.ye1
        public void a(IOException iOException) {
            xe1.this.m = true;
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public static class c implements Sink {
        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Timeout.NONE;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            buffer.skip(j);
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final e f9752a;
        public final boolean[] b;
        public boolean c;

        /* JADX INFO: compiled from: DiskLruCache.java */
        public class a extends ye1 {
            public a(Sink sink) {
                super(sink);
            }

            @Override // supwisdom.ye1
            public void a(IOException iOException) {
                synchronized (xe1.this) {
                    d.this.c = true;
                }
            }
        }

        public /* synthetic */ d(xe1 xe1Var, e eVar, a aVar) {
            this(eVar);
        }

        public d(e eVar) {
            this.f9752a = eVar;
            this.b = eVar.f9754e ? null : new boolean[xe1.this.h];
        }

        public void b() throws IOException {
            synchronized (xe1.this) {
                if (this.c) {
                    xe1.this.a(this, false);
                    xe1.this.a(this.f9752a);
                } else {
                    xe1.this.a(this, true);
                }
            }
        }

        public Sink a(int i) throws IOException {
            a aVar;
            synchronized (xe1.this) {
                if (this.f9752a.f == this) {
                    if (!this.f9752a.f9754e) {
                        this.b[i] = true;
                    }
                    try {
                        aVar = new a(xe1.this.f9749a.sink(this.f9752a.d[i]));
                    } catch (FileNotFoundException unused) {
                        return xe1.t;
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            return aVar;
        }

        public void a() throws IOException {
            synchronized (xe1.this) {
                xe1.this.a(this, false);
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9753a;
        public final long[] b;
        public final File[] c;
        public final File[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f9754e;
        public d f;
        public long g;

        public /* synthetic */ e(xe1 xe1Var, String str, a aVar) {
            this(str);
        }

        public e(String str) {
            this.f9753a = str;
            this.b = new long[xe1.this.h];
            this.c = new File[xe1.this.h];
            this.d = new File[xe1.this.h];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < xe1.this.h; i++) {
                sb.append(i);
                this.c[i] = new File(xe1.this.b, sb.toString());
                sb.append(".tmp");
                this.d[i] = new File(xe1.this.b, sb.toString());
                sb.setLength(length);
            }
        }

        public final void b(String[] strArr) throws IOException {
            if (strArr.length != xe1.this.h) {
                a(strArr);
                throw null;
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    a(strArr);
                    throw null;
                }
            }
        }

        public void a(BufferedSink bufferedSink) throws IOException {
            for (long j : this.b) {
                bufferedSink.writeByte(32).writeDecimalLong(j);
            }
        }

        public final IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public f a() {
            if (Thread.holdsLock(xe1.this)) {
                Source[] sourceArr = new Source[xe1.this.h];
                long[] jArr = (long[]) this.b.clone();
                for (int i = 0; i < xe1.this.h; i++) {
                    try {
                        sourceArr[i] = xe1.this.f9749a.source(this.c[i]);
                    } catch (FileNotFoundException unused) {
                        for (int i2 = 0; i2 < xe1.this.h && sourceArr[i2] != null; i2++) {
                            gf1.a(sourceArr[i2]);
                        }
                        return null;
                    }
                }
                return new f(xe1.this, this.f9753a, this.g, sourceArr, jArr, null);
            }
            throw new AssertionError();
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class f implements Closeable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9755a;
        public final long b;
        public final Source[] c;

        public /* synthetic */ f(xe1 xe1Var, String str, long j, Source[] sourceArr, long[] jArr, a aVar) {
            this(str, j, sourceArr, jArr);
        }

        public d a() throws IOException {
            return xe1.this.a(this.f9755a, this.b);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Source source : this.c) {
                gf1.a(source);
            }
        }

        public f(String str, long j, Source[] sourceArr, long[] jArr) {
            this.f9755a = str;
            this.b = j;
            this.c = sourceArr;
        }

        public Source a(int i) {
            return this.c[i];
        }
    }

    public xe1(zf1 zf1Var, File file, int i, int i2, long j, Executor executor) {
        this.f9749a = zf1Var;
        this.b = file;
        this.f = i;
        this.c = new File(file, "journal");
        this.d = new File(file, "journal.tmp");
        this.f9750e = new File(file, "journal.bkp");
        this.h = i2;
        this.g = j;
        this.q = executor;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.n && !this.o) {
            for (e eVar : (e[]) this.k.values().toArray(new e[this.k.size()])) {
                if (eVar.f != null) {
                    eVar.f.a();
                }
            }
            i();
            this.j.close();
            this.j = null;
            this.o = true;
            return;
        }
        this.o = true;
    }

    public final void i() throws IOException {
        while (this.i > this.g) {
            a(this.k.values().iterator().next());
        }
    }

    public synchronized boolean isClosed() {
        return this.o;
    }

    public synchronized f b(String str) throws IOException {
        c();
        a();
        e(str);
        e eVar = this.k.get(str);
        if (eVar != null && eVar.f9754e) {
            f fVarA = eVar.a();
            if (fVarA == null) {
                return null;
            }
            this.l++;
            this.j.writeUtf8("READ").writeByte(32).writeUtf8(str).writeByte(10);
            if (d()) {
                this.q.execute(this.r);
            }
            return fVarA;
        }
        return null;
    }

    public void c() throws IOException {
        if (this.n) {
            return;
        }
        if (this.f9749a.exists(this.f9750e)) {
            if (this.f9749a.exists(this.c)) {
                this.f9749a.delete(this.f9750e);
            } else {
                this.f9749a.rename(this.f9750e, this.c);
            }
        }
        if (this.f9749a.exists(this.c)) {
            try {
                g();
                f();
                this.n = true;
                return;
            } catch (IOException e2) {
                ef1.c().a("DiskLruCache " + this.b + " is corrupt: " + e2.getMessage() + ", removing");
                b();
                this.o = false;
            }
        }
        h();
        this.n = true;
    }

    public final boolean d() {
        int i = this.l;
        return i >= 2000 && i >= this.k.size();
    }

    public final BufferedSink e() throws FileNotFoundException {
        return Okio.buffer(new b(this.f9749a.appendingSink(this.c)));
    }

    public final void f() throws IOException {
        this.f9749a.delete(this.d);
        Iterator<e> it = this.k.values().iterator();
        while (it.hasNext()) {
            e next = it.next();
            int i = 0;
            if (next.f == null) {
                while (i < this.h) {
                    this.i += next.b[i];
                    i++;
                }
            } else {
                next.f = null;
                while (i < this.h) {
                    this.f9749a.delete(next.c[i]);
                    this.f9749a.delete(next.d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    public final void g() throws IOException {
        BufferedSource bufferedSourceBuffer = Okio.buffer(this.f9749a.source(this.c));
        try {
            String utf8LineStrict = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict2 = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict3 = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict4 = bufferedSourceBuffer.readUtf8LineStrict();
            String utf8LineStrict5 = bufferedSourceBuffer.readUtf8LineStrict();
            if (!"libcore.io.DiskLruCache".equals(utf8LineStrict) || !"1".equals(utf8LineStrict2) || !Integer.toString(this.f).equals(utf8LineStrict3) || !Integer.toString(this.h).equals(utf8LineStrict4) || !"".equals(utf8LineStrict5)) {
                throw new IOException("unexpected journal header: [" + utf8LineStrict + ", " + utf8LineStrict2 + ", " + utf8LineStrict4 + ", " + utf8LineStrict5 + Operators.ARRAY_END_STR);
            }
            int i = 0;
            while (true) {
                try {
                    c(bufferedSourceBuffer.readUtf8LineStrict());
                    i++;
                } catch (EOFException unused) {
                    this.l = i - this.k.size();
                    if (bufferedSourceBuffer.exhausted()) {
                        this.j = e();
                    } else {
                        h();
                    }
                    gf1.a(bufferedSourceBuffer);
                    return;
                }
            }
        } catch (Throwable th) {
            gf1.a(bufferedSourceBuffer);
            throw th;
        }
    }

    public final synchronized void h() throws IOException {
        if (this.j != null) {
            this.j.close();
        }
        BufferedSink bufferedSinkBuffer = Okio.buffer(this.f9749a.sink(this.d));
        try {
            bufferedSinkBuffer.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
            bufferedSinkBuffer.writeUtf8("1").writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.f).writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.h).writeByte(10);
            bufferedSinkBuffer.writeByte(10);
            for (e eVar : this.k.values()) {
                if (eVar.f != null) {
                    bufferedSinkBuffer.writeUtf8("DIRTY").writeByte(32);
                    bufferedSinkBuffer.writeUtf8(eVar.f9753a);
                    bufferedSinkBuffer.writeByte(10);
                } else {
                    bufferedSinkBuffer.writeUtf8("CLEAN").writeByte(32);
                    bufferedSinkBuffer.writeUtf8(eVar.f9753a);
                    eVar.a(bufferedSinkBuffer);
                    bufferedSinkBuffer.writeByte(10);
                }
            }
            bufferedSinkBuffer.close();
            if (this.f9749a.exists(this.c)) {
                this.f9749a.rename(this.c, this.f9750e);
            }
            this.f9749a.rename(this.d, this.c);
            this.f9749a.delete(this.f9750e);
            this.j = e();
            this.m = false;
        } catch (Throwable th) {
            bufferedSinkBuffer.close();
            throw th;
        }
    }

    public synchronized boolean d(String str) throws IOException {
        c();
        a();
        e(str);
        e eVar = this.k.get(str);
        if (eVar == null) {
            return false;
        }
        return a(eVar);
    }

    public final void e(String str) {
        if (s.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + JSUtil.QUOTE);
    }

    public static xe1 a(zf1 zf1Var, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 > 0) {
            return new xe1(zf1Var, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), gf1.a("OkHttp DiskLruCache", true)));
        }
        throw new IllegalArgumentException("valueCount <= 0");
    }

    public d a(String str) throws IOException {
        return a(str, -1L);
    }

    public final synchronized d a(String str, long j) throws IOException {
        c();
        a();
        e(str);
        e eVar = this.k.get(str);
        a aVar = null;
        if (j != -1 && (eVar == null || eVar.g != j)) {
            return null;
        }
        if (eVar != null && eVar.f != null) {
            return null;
        }
        this.j.writeUtf8("DIRTY").writeByte(32).writeUtf8(str).writeByte(10);
        this.j.flush();
        if (this.m) {
            return null;
        }
        if (eVar == null) {
            eVar = new e(this, str, aVar);
            this.k.put(str, eVar);
        }
        d dVar = new d(this, eVar, aVar);
        eVar.f = dVar;
        return dVar;
    }

    public void b() throws IOException {
        close();
        this.f9749a.deleteContents(this.b);
    }

    public final void c(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf != -1) {
            int i = iIndexOf + 1;
            int iIndexOf2 = str.indexOf(32, i);
            if (iIndexOf2 == -1) {
                strSubstring = str.substring(i);
                if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                    this.k.remove(strSubstring);
                    return;
                }
            } else {
                strSubstring = str.substring(i, iIndexOf2);
            }
            e eVar = this.k.get(strSubstring);
            a aVar = null;
            if (eVar == null) {
                eVar = new e(this, strSubstring, aVar);
                this.k.put(strSubstring, eVar);
            }
            if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
                String[] strArrSplit = str.substring(iIndexOf2 + 1).split(Operators.SPACE_STR);
                eVar.f9754e = true;
                eVar.f = null;
                eVar.b(strArrSplit);
                return;
            }
            if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
                eVar.f = new d(this, eVar, aVar);
                return;
            }
            if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
                return;
            }
            throw new IOException("unexpected journal line: " + str);
        }
        throw new IOException("unexpected journal line: " + str);
    }

    public final synchronized void a(d dVar, boolean z) throws IOException {
        e eVar = dVar.f9752a;
        if (eVar.f == dVar) {
            if (z && !eVar.f9754e) {
                for (int i = 0; i < this.h; i++) {
                    if (dVar.b[i]) {
                        if (!this.f9749a.exists(eVar.d[i])) {
                            dVar.a();
                            return;
                        }
                    } else {
                        dVar.a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                }
            }
            for (int i2 = 0; i2 < this.h; i2++) {
                File file = eVar.d[i2];
                if (z) {
                    if (this.f9749a.exists(file)) {
                        File file2 = eVar.c[i2];
                        this.f9749a.rename(file, file2);
                        long j = eVar.b[i2];
                        long size = this.f9749a.size(file2);
                        eVar.b[i2] = size;
                        this.i = (this.i - j) + size;
                    }
                } else {
                    this.f9749a.delete(file);
                }
            }
            this.l++;
            eVar.f = null;
            if (!(eVar.f9754e | z)) {
                this.k.remove(eVar.f9753a);
                this.j.writeUtf8("REMOVE").writeByte(32);
                this.j.writeUtf8(eVar.f9753a);
                this.j.writeByte(10);
            } else {
                eVar.f9754e = true;
                this.j.writeUtf8("CLEAN").writeByte(32);
                this.j.writeUtf8(eVar.f9753a);
                eVar.a(this.j);
                this.j.writeByte(10);
                if (z) {
                    long j2 = this.p;
                    this.p = 1 + j2;
                    eVar.g = j2;
                }
            }
            this.j.flush();
            if (this.i > this.g || d()) {
                this.q.execute(this.r);
            }
            return;
        }
        throw new IllegalStateException();
    }

    public final boolean a(e eVar) throws IOException {
        if (eVar.f != null) {
            eVar.f.c = true;
        }
        for (int i = 0; i < this.h; i++) {
            this.f9749a.delete(eVar.c[i]);
            this.i -= eVar.b[i];
            eVar.b[i] = 0;
        }
        this.l++;
        this.j.writeUtf8("REMOVE").writeByte(32).writeUtf8(eVar.f9753a).writeByte(10);
        this.k.remove(eVar.f9753a);
        if (d()) {
            this.q.execute(this.r);
        }
        return true;
    }

    public final synchronized void a() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }
}
