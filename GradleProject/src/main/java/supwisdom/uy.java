package supwisdom;

import com.dcloud.zxing2.FormatException;

/* JADX INFO: loaded from: classes.dex */
public final class uy extends vy {
    public final int b;
    public final int c;

    public uy(int i, int i2, int i3) throws FormatException {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw FormatException.getFormatInstance();
        }
        this.b = i2;
        this.c = i3;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.b == 10;
    }

    public boolean e() {
        return this.c == 10;
    }
}
