package supwisdom;

/* JADX INFO: compiled from: Math.java */
/* JADX INFO: loaded from: classes.dex */
public class mt0 {
    public static long a(long j, long j2) {
        while (true) {
            long j3 = j;
            j = j2;
            if (j <= 0) {
                return j3;
            }
            j2 = j3 % j;
        }
    }
}
