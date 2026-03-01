package supwisdom;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class u61 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Writer f9378a;
    public final int b;
    public final StringBuffer c;
    public final StringBuffer d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n61 f9379e;
    public final n61 f;

    public u61(Writer writer, int i, int i2, String str) {
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        if (i < 1) {
            throw new IllegalArgumentException("leftWidth < 1");
        }
        if (i2 < 1) {
            throw new IllegalArgumentException("rightWidth < 1");
        }
        if (str == null) {
            throw new NullPointerException("spacer == null");
        }
        StringWriter stringWriter = new StringWriter(1000);
        StringWriter stringWriter2 = new StringWriter(1000);
        this.f9378a = writer;
        this.b = i;
        this.c = stringWriter.getBuffer();
        this.d = stringWriter2.getBuffer();
        this.f9379e = new n61(stringWriter, i);
        this.f = new n61(stringWriter2, i2, str);
    }

    public static void a(StringBuffer stringBuffer, Writer writer) throws IOException {
        int length = stringBuffer.length();
        if (length == 0 || stringBuffer.charAt(length - 1) == '\n') {
            return;
        }
        writer.write(10);
    }

    public final void b() throws IOException {
        a(this.c, this.f9379e);
        while (this.c.length() != 0) {
            this.f.write(10);
            f();
        }
    }

    public final void c() throws IOException {
        a(this.d, this.f);
        while (this.d.length() != 0) {
            this.f9379e.write(10);
            f();
        }
    }

    public Writer d() {
        return this.f9379e;
    }

    public Writer e() {
        return this.f;
    }

    public final void f() throws IOException {
        int iIndexOf;
        while (true) {
            int iIndexOf2 = this.c.indexOf("\n");
            if (iIndexOf2 < 0 || (iIndexOf = this.d.indexOf("\n")) < 0) {
                return;
            }
            if (iIndexOf2 != 0) {
                this.f9378a.write(this.c.substring(0, iIndexOf2));
            }
            if (iIndexOf != 0) {
                a(this.f9378a, this.b - iIndexOf2);
                this.f9378a.write(this.d.substring(0, iIndexOf));
            }
            this.f9378a.write(10);
            this.c.delete(0, iIndexOf2 + 1);
            this.d.delete(0, iIndexOf + 1);
        }
    }

    public void a() {
        try {
            a(this.c, this.f9379e);
            a(this.d, this.f);
            f();
            b();
            c();
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String a(String str, int i, String str2, String str3, int i2) {
        StringWriter stringWriter = new StringWriter((str.length() + str3.length()) * 3);
        u61 u61Var = new u61(stringWriter, i, i2, str2);
        try {
            u61Var.d().write(str);
            u61Var.e().write(str3);
            u61Var.a();
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new RuntimeException("shouldn't happen", e2);
        }
    }

    public static void a(Writer writer, int i) throws IOException {
        while (i > 0) {
            writer.write(32);
            i--;
        }
    }
}
