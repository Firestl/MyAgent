package java9.util.concurrent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Comparator;
import java.util.Random;
import supwisdom.br1;
import supwisdom.kq1;
import supwisdom.lq1;
import supwisdom.pq1;
import supwisdom.uq1;
import supwisdom.wq1;
import supwisdom.yq1;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadLocalRandom extends Random {
    public static final String BAD_BOUND = "bound must be positive";
    public static final String BAD_RANGE = "bound must be greater than origin";
    public static final String BAD_SIZE = "size must be non-negative";
    public static final double DOUBLE_UNIT = 1.1102230246251565E-16d;
    public static final float FLOAT_UNIT = 5.9604645E-8f;
    public static final long serialVersionUID = 9123313859120073139L;
    public boolean initialized = true;
    public static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("rnd", Long.TYPE), new ObjectStreamField("initialized", Boolean.TYPE)};
    public static final ThreadLocal<Double> nextLocalGaussian = new ThreadLocal<>();
    public static final ThreadLocalRandom instance = new ThreadLocalRandom();

    public static final class a implements lq1.a {
        public a(long j, long j2, double d, double d2) {
        }

        @Override // supwisdom.lq1
        public /* synthetic */ Comparator<? super T> a() {
            return kq1.a(this);
        }

        @Override // supwisdom.lq1
        public int b() {
            return 17728;
        }
    }

    public static final class b implements lq1.b {
        public b(long j, long j2, int i, int i2) {
        }

        @Override // supwisdom.lq1
        public /* synthetic */ Comparator<? super T> a() {
            return kq1.a(this);
        }

        @Override // supwisdom.lq1
        public int b() {
            return 17728;
        }
    }

    public static final class c implements lq1.c {
        public c(long j, long j2, long j3, long j4) {
        }

        @Override // supwisdom.lq1
        public /* synthetic */ Comparator<? super T> a() {
            return kq1.a(this);
        }

        @Override // supwisdom.lq1
        public int b() {
            return 17728;
        }
    }

    public static ThreadLocalRandom current() {
        if (pq1.b() == 0) {
            pq1.g();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double internalNextDouble(double d, double d2) {
        double dNextLong = (nextLong() >>> 11) * 1.1102230246251565E-16d;
        if (d >= d2) {
            return dNextLong;
        }
        double d3 = (dNextLong * (d2 - d)) + d;
        return d3 >= d2 ? Double.longBitsToDouble(Double.doubleToLongBits(d2) - 1) : d3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int internalNextInt(int i, int i2) {
        int i3;
        int iA = pq1.a(nextSeed());
        if (i >= i2) {
            return iA;
        }
        int i4 = i2 - i;
        int i5 = i4 - 1;
        if ((i4 & i5) == 0) {
            i3 = iA & i5;
        } else if (i4 > 0) {
            int iA2 = iA >>> 1;
            while (true) {
                int i6 = iA2 + i5;
                i3 = iA2 % i4;
                if (i6 - i3 >= 0) {
                    break;
                }
                iA2 = pq1.a(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (iA >= i && iA < i2) {
                    return iA;
                }
                iA = pq1.a(nextSeed());
            }
        }
        return i3 + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long internalNextLong(long j, long j2) {
        long jB = pq1.b(nextSeed());
        if (j >= j2) {
            return jB;
        }
        long j3 = j2 - j;
        long j4 = j3 - 1;
        if ((j3 & j4) == 0) {
            return (jB & j4) + j;
        }
        if (j3 > 0) {
            while (true) {
                long j5 = jB >>> 1;
                long j6 = j5 + j4;
                long j7 = j5 % j3;
                if (j6 - j7 >= 0) {
                    return j7 + j;
                }
                jB = pq1.b(nextSeed());
            }
        } else {
            while (true) {
                if (jB >= j && jB < j2) {
                    return jB;
                }
                jB = pq1.b(nextSeed());
            }
        }
    }

    private final long nextSeed() {
        return pq1.i();
    }

    private Object readResolve() {
        return current();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("rnd", pq1.d());
        putFieldPutFields.put("initialized", true);
        objectOutputStream.writeFields();
    }

    @Override // java.util.Random
    public uq1 doubles(long j) {
        if (j >= 0) {
            return br1.a((lq1.a) new a(0L, j, Double.MAX_VALUE, 0.0d), false);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    @Override // java.util.Random
    public wq1 ints(long j) {
        if (j >= 0) {
            return br1.a((lq1.b) new b(0L, j, Integer.MAX_VALUE, 0), false);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    @Override // java.util.Random
    public yq1 longs(long j) {
        if (j >= 0) {
            return br1.a((lq1.c) new c(0L, j, Long.MAX_VALUE, 0L), false);
        }
        throw new IllegalArgumentException(BAD_SIZE);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return pq1.a(nextSeed()) < 0;
    }

    @Override // java.util.Random
    public double nextDouble() {
        return (pq1.b(nextSeed()) >>> 11) * 1.1102230246251565E-16d;
    }

    @Override // java.util.Random
    public float nextFloat() {
        return (pq1.a(nextSeed()) >>> 8) * 5.9604645E-8f;
    }

    @Override // java.util.Random
    public double nextGaussian() {
        Double d = nextLocalGaussian.get();
        if (d != null) {
            nextLocalGaussian.set(null);
            return d.doubleValue();
        }
        while (true) {
            double dNextDouble = (nextDouble() * 2.0d) - 1.0d;
            double dNextDouble2 = (nextDouble() * 2.0d) - 1.0d;
            double d2 = (dNextDouble * dNextDouble) + (dNextDouble2 * dNextDouble2);
            if (d2 < 1.0d && d2 != 0.0d) {
                double dSqrt = StrictMath.sqrt((StrictMath.log(d2) * (-2.0d)) / d2);
                nextLocalGaussian.set(Double.valueOf(dNextDouble2 * dSqrt));
                return dNextDouble * dSqrt;
            }
        }
    }

    @Override // java.util.Random
    public int nextInt() {
        return pq1.a(nextSeed());
    }

    @Override // java.util.Random
    public long nextLong() {
        return pq1.b(nextSeed());
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
    }

    public double nextDouble(double d) {
        if (d <= 0.0d) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        double dB = (pq1.b(nextSeed()) >>> 11) * 1.1102230246251565E-16d * d;
        return dB < d ? dB : Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        int iA = pq1.a(nextSeed());
        int i2 = i - 1;
        if ((i & i2) == 0) {
            return iA & i2;
        }
        while (true) {
            int i3 = iA >>> 1;
            int i4 = i3 + i2;
            int i5 = i3 % i;
            if (i4 - i5 >= 0) {
                return i5;
            }
            iA = pq1.a(nextSeed());
        }
    }

    public long nextLong(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        long jB = pq1.b(nextSeed());
        long j2 = j - 1;
        if ((j & j2) == 0) {
            return jB & j2;
        }
        while (true) {
            long j3 = jB >>> 1;
            long j4 = j3 + j2;
            long j5 = j3 % j;
            if (j4 - j5 >= 0) {
                return j5;
            }
            jB = pq1.b(nextSeed());
        }
    }

    @Override // java.util.Random
    public uq1 doubles() {
        return br1.a((lq1.a) new a(0L, Long.MAX_VALUE, Double.MAX_VALUE, 0.0d), false);
    }

    @Override // java.util.Random
    public wq1 ints() {
        return br1.a((lq1.b) new b(0L, Long.MAX_VALUE, Integer.MAX_VALUE, 0), false);
    }

    @Override // java.util.Random
    public yq1 longs() {
        return br1.a((lq1.c) new c(0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L), false);
    }

    public double nextDouble(double d, double d2) {
        if (d < d2) {
            return internalNextDouble(d, d2);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public uq1 doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (d < d2) {
            return br1.a((lq1.a) new a(0L, j, d, d2), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public wq1 ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (i < i2) {
            return br1.a((lq1.b) new b(0L, j, i, i2), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public yq1 longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (j2 < j3) {
            return br1.a((lq1.c) new c(0L, j, j2, j3), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    public int nextInt(int i, int i2) {
        if (i < i2) {
            return internalNextInt(i, i2);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    public long nextLong(long j, long j2) {
        if (j < j2) {
            return internalNextLong(j, j2);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public uq1 doubles(double d, double d2) {
        if (d < d2) {
            return br1.a((lq1.a) new a(0L, Long.MAX_VALUE, d, d2), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public wq1 ints(int i, int i2) {
        if (i < i2) {
            return br1.a((lq1.b) new b(0L, Long.MAX_VALUE, i, i2), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }

    @Override // java.util.Random
    public yq1 longs(long j, long j2) {
        if (j < j2) {
            return br1.a((lq1.c) new c(0L, Long.MAX_VALUE, j, j2), false);
        }
        throw new IllegalArgumentException(BAD_RANGE);
    }
}
