package supwisdom;

/* JADX INFO: compiled from: Pow2.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ty1 {
    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }
}
