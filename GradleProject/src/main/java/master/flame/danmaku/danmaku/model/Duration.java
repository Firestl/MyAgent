package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes3.dex */
public class Duration {
    public float factor = 1.0f;
    public long mInitialDuration;
    public long value;

    public Duration(long j) {
        this.mInitialDuration = j;
        this.value = j;
    }

    public void setFactor(float f) {
        if (this.factor != f) {
            this.factor = f;
            this.value = (long) (this.mInitialDuration * f);
        }
    }

    public void setValue(long j) {
        this.mInitialDuration = j;
        this.value = (long) (j * this.factor);
    }
}
