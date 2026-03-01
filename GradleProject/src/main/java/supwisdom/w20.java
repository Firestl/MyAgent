package supwisdom;

import java.lang.reflect.Constructor;

/* JADX INFO: compiled from: DefaultExtractorsFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class w20 implements a50 {
    public static final Constructor<? extends y30> f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9579a;
    public int b;
    public int c;
    public int d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9580e;

    static {
        Constructor<? extends y30> constructor;
        try {
            constructor = Class.forName("com.google.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(y30.class).getConstructor(new Class[0]);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            constructor = null;
        }
        f = constructor;
    }

    @Override // supwisdom.a50
    public synchronized y30[] a() {
        y30[] y30VarArr;
        y30VarArr = new y30[f == null ? 11 : 12];
        y30VarArr[0] = new o20(this.f9579a);
        y30VarArr[1] = new c30(this.b);
        y30VarArr[2] = new e30();
        y30VarArr[3] = new t20(this.c);
        y30VarArr[4] = new b40();
        y30VarArr[5] = new z30();
        y30VarArr[6] = new t40(this.d, this.f9580e);
        y30VarArr[7] = new h20();
        y30VarArr[8] = new n30();
        y30VarArr[9] = new o40();
        y30VarArr[10] = new w40();
        if (f != null) {
            try {
                y30VarArr[11] = f.newInstance(new Object[0]);
            } catch (Exception e2) {
                throw new IllegalStateException("Unexpected error creating FLAC extractor", e2);
            }
        }
        return y30VarArr;
    }
}
