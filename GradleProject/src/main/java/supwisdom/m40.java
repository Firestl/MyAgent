package supwisdom;

import java.util.Arrays;

/* JADX INFO: compiled from: NalUnitTargetBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class m40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8355a;
    public boolean b;
    public boolean c;
    public byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8356e;

    public m40(int i, int i2) {
        this.f8355a = i;
        byte[] bArr = new byte[i2 + 3];
        this.d = bArr;
        bArr[2] = 1;
    }

    public void a() {
        this.b = false;
        this.c = false;
    }

    public boolean b() {
        return this.c;
    }

    public boolean b(int i) {
        if (!this.b) {
            return false;
        }
        this.f8356e -= i;
        this.b = false;
        this.c = true;
        return true;
    }

    public void a(int i) {
        e80.b(!this.b);
        boolean z = i == this.f8355a;
        this.b = z;
        if (z) {
            this.f8356e = 3;
            this.c = false;
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.b) {
            int i3 = i2 - i;
            byte[] bArr2 = this.d;
            int length = bArr2.length;
            int i4 = this.f8356e;
            if (length < i4 + i3) {
                this.d = Arrays.copyOf(bArr2, (i4 + i3) * 2);
            }
            System.arraycopy(bArr, i, this.d, this.f8356e, i3);
            this.f8356e += i3;
        }
    }
}
