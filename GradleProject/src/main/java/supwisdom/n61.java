package supwisdom;

import java.io.FilterWriter;
import java.io.Writer;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class n61 extends FilterWriter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8478a;
    public final int b;
    public final int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8479e;
    public int f;

    public n61(Writer writer, int i, String str) {
        super(writer);
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("width < 0");
        }
        if (str == null) {
            throw new NullPointerException("prefix == null");
        }
        this.b = i != 0 ? i : Integer.MAX_VALUE;
        this.c = i >> 1;
        this.f8478a = str.length() == 0 ? null : str;
        a();
    }

    public final void a() {
        this.d = 0;
        this.f8479e = this.c != 0;
        this.f = 0;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) {
        int i2;
        synchronized (((FilterWriter) this).lock) {
            int i3 = 0;
            if (this.f8479e) {
                if (i == 32) {
                    int i4 = this.f + 1;
                    this.f = i4;
                    int i5 = this.c;
                    if (i4 >= i5) {
                        this.f = i5;
                        this.f8479e = false;
                    }
                } else {
                    this.f8479e = false;
                }
            }
            if (this.d == this.b && i != 10) {
                ((FilterWriter) this).out.write(10);
                this.d = 0;
            }
            if (this.d == 0) {
                String str = this.f8478a;
                if (str != null) {
                    ((FilterWriter) this).out.write(str);
                }
                if (!this.f8479e) {
                    while (true) {
                        i2 = this.f;
                        if (i3 >= i2) {
                            break;
                        }
                        ((FilterWriter) this).out.write(32);
                        i3++;
                    }
                    this.d = i2;
                }
            }
            ((FilterWriter) this).out.write(i);
            if (i == 10) {
                a();
            } else {
                this.d++;
            }
        }
    }

    public n61(Writer writer, int i) {
        this(writer, i, "");
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        synchronized (((FilterWriter) this).lock) {
            while (i2 > 0) {
                write(cArr[i]);
                i++;
                i2--;
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) {
        synchronized (((FilterWriter) this).lock) {
            while (i2 > 0) {
                write(str.charAt(i));
                i++;
                i2--;
            }
        }
    }
}
