package com.loc;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ao implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final File f3635e;
    public final File f;
    public final File g;
    public final File h;
    public long j;
    public Writer m;
    public int p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f3634a = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final Charset b = Charset.forName("US-ASCII");
    public static final Charset c = Charset.forName("UTF-8");
    public static final ThreadFactory r = new ThreadFactory() { // from class: com.loc.ao.1

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f3636a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "disklrucache#" + this.f3636a.getAndIncrement());
        }
    };
    public static ThreadPoolExecutor d = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), r);
    public static final OutputStream t = new OutputStream() { // from class: com.loc.ao.3
        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
        }
    };
    public long l = 0;
    public int n = 1000;
    public final LinkedHashMap<String, c> o = new LinkedHashMap<>(0, 0.75f, true);
    public long q = 0;
    public final Callable<Void> s = new Callable<Void>() { // from class: com.loc.ao.2
        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (ao.this) {
                if (ao.this.m == null) {
                    return null;
                }
                ao.this.l();
                if (ao.this.j()) {
                    ao.this.i();
                    ao.e(ao.this);
                }
                return null;
            }
        }
    };
    public final int i = 1;
    public final int k = 1;

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class a {
        public final c b;
        public final boolean[] c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f3639e;

        /* JADX INFO: renamed from: com.loc.ao$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DiskLruCache.java */
        public class C0080a extends FilterOutputStream {
            public C0080a(OutputStream outputStream) {
                super(outputStream);
            }

            public /* synthetic */ C0080a(a aVar, OutputStream outputStream, byte b) {
                this(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public final void close() {
                try {
                    ((FilterOutputStream) this).out.close();
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public final void flush() {
                try {
                    ((FilterOutputStream) this).out.flush();
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(int i) {
                try {
                    ((FilterOutputStream) this).out.write(i);
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public final void write(byte[] bArr, int i, int i2) {
                try {
                    ((FilterOutputStream) this).out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.c(a.this);
                }
            }
        }

        public a(c cVar) {
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[ao.this.k];
        }

        public /* synthetic */ a(ao aoVar, c cVar, byte b) {
            this(cVar);
        }

        public static /* synthetic */ boolean c(a aVar) {
            aVar.d = true;
            return true;
        }

        public final OutputStream a() throws IOException {
            FileOutputStream fileOutputStream;
            C0080a c0080a;
            if (ao.this.k <= 0) {
                throw new IllegalArgumentException("Expected index 0 to be greater than 0 and less than the maximum value count of " + ao.this.k);
            }
            synchronized (ao.this) {
                if (this.b.f3644e != this) {
                    throw new IllegalStateException();
                }
                byte b = 0;
                if (!this.b.d) {
                    this.c[0] = true;
                }
                File fileB = this.b.b(0);
                try {
                    fileOutputStream = new FileOutputStream(fileB);
                } catch (FileNotFoundException unused) {
                    ao.this.f3635e.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(fileB);
                    } catch (FileNotFoundException unused2) {
                        return ao.t;
                    }
                }
                c0080a = new C0080a(this, fileOutputStream, b);
            }
            return c0080a;
        }

        public final void b() throws IOException {
            if (this.d) {
                ao.this.a(this, false);
                ao.this.c(this.b.b);
            } else {
                ao.this.a(this, true);
            }
            this.f3639e = true;
        }

        public final void c() throws IOException {
            ao.this.a(this, false);
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class b implements Closeable {
        public final String b;
        public final long c;
        public final InputStream[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long[] f3642e;

        public b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.f3642e = jArr;
        }

        public /* synthetic */ b(ao aoVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, byte b) {
            this(str, j, inputStreamArr, jArr);
        }

        public final InputStream a() {
            return this.d[0];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            for (InputStream inputStream : this.d) {
                ao.a(inputStream);
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class c {
        public final String b;
        public final long[] c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public a f3644e;
        public long f;

        public c(String str) {
            this.b = str;
            this.c = new long[ao.this.k];
        }

        public /* synthetic */ c(ao aoVar, String str, byte b) {
            this(str);
        }

        public static IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public static /* synthetic */ void a(c cVar, String[] strArr) throws IOException {
            if (strArr.length != ao.this.k) {
                throw a(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    cVar.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw a(strArr);
                }
            }
        }

        public static /* synthetic */ boolean a(c cVar) {
            cVar.d = true;
            return true;
        }

        public final File a(int i) {
            return new File(ao.this.f3635e, this.b + Operators.DOT_STR + i);
        }

        public final String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.c) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        public final File b(int i) {
            return new File(ao.this.f3635e, this.b + Operators.DOT_STR + i + ".tmp");
        }
    }

    public ao(File file, long j) {
        this.f3635e = file;
        this.f = new File(file, "journal");
        this.g = new File(file, "journal.tmp");
        this.h = new File(file, "journal.bkp");
        this.j = j;
    }

    public static ao a(File file, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        ao aoVar = new ao(file, j);
        if (aoVar.f.exists()) {
            try {
                aoVar.g();
                aoVar.h();
                aoVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aoVar.f, true), b));
                return aoVar;
            } catch (Throwable unused) {
                aoVar.d();
            }
        }
        file.mkdirs();
        ao aoVar2 = new ao(file, j);
        aoVar2.i();
        return aoVar2;
    }

    public static void a() {
        ThreadPoolExecutor threadPoolExecutor = d;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        d.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(a aVar, boolean z) throws IOException {
        c cVar = aVar.b;
        if (cVar.f3644e != aVar) {
            throw new IllegalStateException();
        }
        if (z && !cVar.d) {
            for (int i = 0; i < this.k; i++) {
                if (!aVar.c[i]) {
                    aVar.c();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!cVar.b(i).exists()) {
                    aVar.c();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.k; i2++) {
            File fileB = cVar.b(i2);
            if (!z) {
                a(fileB);
            } else if (fileB.exists()) {
                File fileA = cVar.a(i2);
                fileB.renameTo(fileA);
                long j = cVar.c[i2];
                long length = fileA.length();
                cVar.c[i2] = length;
                this.l = (this.l - j) + length;
            }
        }
        this.p++;
        cVar.f3644e = null;
        if (cVar.d || z) {
            c.a(cVar);
            this.m.write("CLEAN " + cVar.b + cVar.a() + '\n');
            if (z) {
                long j2 = this.q;
                this.q = 1 + j2;
                cVar.f = j2;
            }
        } else {
            this.o.remove(cVar.b);
            this.m.write("REMOVE " + cVar.b + '\n');
        }
        this.m.flush();
        if (this.l > this.j || j()) {
            f().submit(this.s);
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public static void b(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    private synchronized a d(String str) throws IOException {
        k();
        e(str);
        c cVar = this.o.get(str);
        byte b2 = 0;
        if (cVar == null) {
            cVar = new c(this, str, b2);
            this.o.put(str, cVar);
        } else if (cVar.f3644e != null) {
            return null;
        }
        a aVar = new a(this, cVar, b2);
        cVar.f3644e = aVar;
        this.m.write("DIRTY " + str + '\n');
        this.m.flush();
        return aVar;
    }

    public static /* synthetic */ int e(ao aoVar) {
        aoVar.p = 0;
        return 0;
    }

    public static void e(String str) {
        if (f3634a.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + JSUtil.QUOTE);
    }

    public static ThreadPoolExecutor f() {
        try {
            if (d == null || d.isShutdown()) {
                d = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), r);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return d;
    }

    private void g() throws IOException {
        String strA;
        String strSubstring;
        ap apVar = new ap(new FileInputStream(this.f), b);
        try {
            String strA2 = apVar.a();
            String strA3 = apVar.a();
            String strA4 = apVar.a();
            String strA5 = apVar.a();
            String strA6 = apVar.a();
            if (!"libcore.io.DiskLruCache".equals(strA2) || !"1".equals(strA3) || !Integer.toString(this.i).equals(strA4) || !Integer.toString(this.k).equals(strA5) || !"".equals(strA6)) {
                throw new IOException("unexpected journal header: [" + strA2 + ", " + strA3 + ", " + strA5 + ", " + strA6 + Operators.ARRAY_END_STR);
            }
            byte b2 = 0;
            int i = 0;
            while (true) {
                try {
                    strA = apVar.a();
                    int iIndexOf = strA.indexOf(32);
                    if (iIndexOf == -1) {
                        throw new IOException("unexpected journal line: " + strA);
                    }
                    int i2 = iIndexOf + 1;
                    int iIndexOf2 = strA.indexOf(32, i2);
                    if (iIndexOf2 == -1) {
                        strSubstring = strA.substring(i2);
                        if (iIndexOf == 6 && strA.startsWith("REMOVE")) {
                            this.o.remove(strSubstring);
                        }
                        i++;
                    } else {
                        strSubstring = strA.substring(i2, iIndexOf2);
                    }
                    c cVar = this.o.get(strSubstring);
                    if (cVar == null) {
                        cVar = new c(this, strSubstring, b2);
                        this.o.put(strSubstring, cVar);
                    }
                    if (iIndexOf2 != -1 && iIndexOf == 5 && strA.startsWith("CLEAN")) {
                        String[] strArrSplit = strA.substring(iIndexOf2 + 1).split(Operators.SPACE_STR);
                        c.a(cVar);
                        cVar.f3644e = null;
                        c.a(cVar, strArrSplit);
                    } else if (iIndexOf2 == -1 && iIndexOf == 5 && strA.startsWith("DIRTY")) {
                        cVar.f3644e = new a(this, cVar, b2);
                    } else if (iIndexOf2 != -1 || iIndexOf != 4 || !strA.startsWith("READ")) {
                        break;
                    }
                    i++;
                } catch (EOFException unused) {
                    this.p = i - this.o.size();
                    a(apVar);
                    return;
                }
            }
            throw new IOException("unexpected journal line: " + strA);
        } catch (Throwable th) {
            a(apVar);
            throw th;
        }
    }

    private void h() throws IOException {
        a(this.g);
        Iterator<c> it = this.o.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.f3644e == null) {
                while (i < this.k) {
                    this.l += next.c[i];
                    i++;
                }
            } else {
                next.f3644e = null;
                while (i < this.k) {
                    a(next.a(i));
                    a(next.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i() throws IOException {
        if (this.m != null) {
            this.m.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.g), b));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.k));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (c cVar : this.o.values()) {
                bufferedWriter.write(cVar.f3644e != null ? "DIRTY " + cVar.b + '\n' : "CLEAN " + cVar.b + cVar.a() + '\n');
            }
            bufferedWriter.close();
            if (this.f.exists()) {
                a(this.f, this.h, true);
            }
            a(this.g, this.f, false);
            this.h.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f, true), b));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        int i = this.p;
        return i >= 2000 && i >= this.o.size();
    }

    private void k() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() throws IOException {
        while (true) {
            if (this.l <= this.j && this.o.size() <= this.n) {
                return;
            } else {
                c(this.o.entrySet().iterator().next().getKey());
            }
        }
    }

    public final synchronized b a(String str) throws IOException {
        k();
        e(str);
        c cVar = this.o.get(str);
        if (cVar == null) {
            return null;
        }
        if (!cVar.d) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.k];
        for (int i = 0; i < this.k; i++) {
            try {
                inputStreamArr[i] = new FileInputStream(cVar.a(i));
            } catch (FileNotFoundException unused) {
                for (int i2 = 0; i2 < this.k && inputStreamArr[i2] != null; i2++) {
                    a(inputStreamArr[i2]);
                }
                return null;
            }
        }
        this.p++;
        this.m.append((CharSequence) ("READ " + str + '\n'));
        if (j()) {
            f().submit(this.s);
        }
        return new b(this, str, cVar.f, inputStreamArr, cVar.c, (byte) 0);
    }

    public final void a(int i) {
        if (i < 10) {
            i = 10;
        } else if (i > 10000) {
            i = 10000;
        }
        this.n = i;
    }

    public final a b(String str) throws IOException {
        return d(str);
    }

    public final File b() {
        return this.f3635e;
    }

    public final synchronized void c() throws IOException {
        k();
        l();
        this.m.flush();
    }

    public final synchronized boolean c(String str) throws IOException {
        k();
        e(str);
        c cVar = this.o.get(str);
        if (cVar != null && cVar.f3644e == null) {
            for (int i = 0; i < this.k; i++) {
                File fileA = cVar.a(i);
                if (fileA.exists() && !fileA.delete()) {
                    throw new IOException("failed to delete " + fileA);
                }
                this.l -= cVar.c[i];
                cVar.c[i] = 0;
            }
            this.p++;
            this.m.append((CharSequence) ("REMOVE " + str + '\n'));
            this.o.remove(str);
            if (j()) {
                f().submit(this.s);
            }
            return true;
        }
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.m == null) {
            return;
        }
        for (c cVar : new ArrayList(this.o.values())) {
            if (cVar.f3644e != null) {
                cVar.f3644e.c();
            }
        }
        l();
        this.m.close();
        this.m = null;
    }

    public final void d() throws IOException {
        close();
        b(this.f3635e);
    }
}
