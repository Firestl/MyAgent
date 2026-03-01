package supwisdom;

/* JADX INFO: loaded from: classes.dex */
public final class ty extends vy {
    public final String b;
    public final int c;
    public final boolean d;

    public ty(int i, String str) {
        super(i);
        this.b = str;
        this.d = false;
        this.c = 0;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public ty(int i, String str, int i2) {
        super(i);
        this.d = true;
        this.c = i2;
        this.b = str;
    }
}
