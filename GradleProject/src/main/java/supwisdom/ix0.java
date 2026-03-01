package supwisdom;

import com.taobao.weex.common.Constants;

/* JADX INFO: loaded from: classes2.dex */
public class ix0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7996a;
    public final int b;

    public ix0(int i, int i2) {
        this.f7996a = i;
        this.b = i2;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.f7996a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(9);
        sb.append(this.f7996a);
        sb.append(Constants.Name.X);
        sb.append(this.b);
        return sb.toString();
    }

    public ix0 a(float f) {
        return new ix0((int) (this.f7996a * f), (int) (this.b * f));
    }

    public ix0 a(int i) {
        return new ix0(this.f7996a / i, this.b / i);
    }

    public ix0(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.f7996a = i;
            this.b = i2;
        } else {
            this.f7996a = i2;
            this.b = i;
        }
    }
}
