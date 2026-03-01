package pl.droidsonroids.gif;

/* JADX INFO: loaded from: classes3.dex */
public class GifOptions {
    public boolean inIsOpaque;
    public char inSampleSize;

    public GifOptions() {
        reset();
    }

    private void reset() {
        this.inSampleSize = (char) 1;
        this.inIsOpaque = false;
    }

    public void setFrom(GifOptions gifOptions) {
        if (gifOptions == null) {
            reset();
        } else {
            this.inIsOpaque = gifOptions.inIsOpaque;
            this.inSampleSize = gifOptions.inSampleSize;
        }
    }

    public void setInIsOpaque(boolean z) {
        this.inIsOpaque = z;
    }

    public void setInSampleSize(int i) {
        if (i < 1 || i > 65535) {
            this.inSampleSize = (char) 1;
        } else {
            this.inSampleSize = (char) i;
        }
    }
}
