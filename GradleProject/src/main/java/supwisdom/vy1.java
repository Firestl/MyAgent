package supwisdom;

/* JADX INFO: compiled from: SpscArrayQueue.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class vy1<E> extends qy1<E> {
    public static final Integer f = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);

    public vy1(int i) {
        super(i);
        Math.min(i / 4, f.intValue());
    }
}
