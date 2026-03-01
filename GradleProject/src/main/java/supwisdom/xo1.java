package supwisdom;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: HTTP.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xo1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f9794a = vn1.c;

    static {
        Charset charset = vn1.b;
    }

    public static boolean a(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }
}
