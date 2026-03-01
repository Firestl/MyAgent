package supwisdom;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.RandomAccess;
import supwisdom.uq0;

/* JADX INFO: compiled from: Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class gq0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f7764a = Charset.forName("UTF-8");
    public static final byte[] b;

    /* JADX INFO: compiled from: Internal.java */
    public interface a extends i<Boolean> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface b extends i<Double> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface c {
        int getNumber();
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface d<T extends c> {
        T a(int i);
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface e {
        boolean a(int i);
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface f extends i<Float> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface g extends i<Integer> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface h extends i<Long> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface i<E> extends List<E>, RandomAccess {
        i<E> a(int i);

        void c();

        boolean f();
    }

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        b = bArr;
        ByteBuffer.wrap(bArr);
        rp0.a(b);
    }

    public static int a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static boolean b(byte[] bArr) {
        return wr0.a(bArr);
    }

    public static String c(byte[] bArr) {
        return new String(bArr, f7764a);
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static int a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static int a(byte[] bArr, int i2, int i3) {
        int iA = a(i3, bArr, i2, i3);
        if (iA == 0) {
            return 1;
        }
        return iA;
    }

    public static int a(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    public static Object a(Object obj, Object obj2) {
        uq0.a aVarB = ((uq0) obj).b();
        aVarB.a((uq0) obj2);
        return aVarB.S();
    }
}
