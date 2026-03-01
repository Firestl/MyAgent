package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class uy0 {
    public static x51 a(Object obj) {
        if (obj == null) {
            return k51.f8125a;
        }
        if (obj instanceof Boolean) {
            return y41.a(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Byte) {
            return z41.a(((Byte) obj).byteValue());
        }
        if (obj instanceof Character) {
            return c51.a(((Character) obj).charValue());
        }
        if (obj instanceof Double) {
            return d51.a(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }
        if (obj instanceof Float) {
            return g51.a(Float.floatToIntBits(((Float) obj).floatValue()));
        }
        if (obj instanceof Integer) {
            return h51.a(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return o51.a(((Long) obj).longValue());
        }
        if (obj instanceof Short) {
            return u51.a(((Short) obj).shortValue());
        }
        if (obj instanceof String) {
            return new v51((String) obj);
        }
        if (obj instanceof Class) {
            return new w51(az0.a((Class) obj).b);
        }
        if (obj instanceof az0) {
            return new w51(((az0) obj).b);
        }
        throw new UnsupportedOperationException("Not a constant: " + obj);
    }
}
