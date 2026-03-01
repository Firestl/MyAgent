package supwisdom;

/* JADX INFO: compiled from: RxRingBuffer.java */
/* JADX INFO: loaded from: classes3.dex */
public class dy1 implements yw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f7397a;

    static {
        int i = cy1.b() ? 16 : 128;
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                i = Integer.parseInt(property);
            } catch (NumberFormatException e2) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e2.getMessage());
            }
        }
        f7397a = i;
    }
}
