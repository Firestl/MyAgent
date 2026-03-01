package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public class kw extends rv {
    public static final byte[] d = new byte[0];
    public byte[] b;
    public final int[] c;

    public kw(uv uvVar) {
        super(uvVar);
        this.b = d;
        this.c = new int[32];
    }

    @Override // supwisdom.rv
    public rv a(uv uvVar) {
        return new kw(uvVar);
    }

    public static int a(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i6) {
                i5 = i7;
                i6 = i9;
            }
        }
        if (i3 <= i5) {
            int i10 = i3;
            i3 = i5;
            i5 = i10;
        }
        if (i3 - i5 <= length / 16) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i11 = i3 - 1;
        int i12 = i11;
        int i13 = -1;
        while (i11 > i5) {
            int i14 = i11 - i5;
            int i15 = i14 * i14 * (i3 - i11) * (i2 - iArr[i11]);
            if (i15 > i13) {
                i12 = i11;
                i13 = i15;
            }
            i11--;
        }
        return i12 << 3;
    }

    @Override // supwisdom.rv
    public fw a() throws NotFoundException {
        uv uvVarC = c();
        int width = uvVarC.getWidth();
        int height = uvVarC.getHeight();
        fw fwVar = new fw(width, height);
        a(width);
        int[] iArr = this.c;
        for (int i = 1; i < 5; i++) {
            byte[] row = uvVarC.getRow((height * i) / 5, this.b);
            int i2 = (width * 4) / 5;
            for (int i3 = width / 5; i3 < i2; i3++) {
                int i4 = (row[i3] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int iA = a(iArr);
        byte[] matrix = uvVarC.getMatrix();
        for (int i5 = 0; i5 < height; i5++) {
            int i6 = i5 * width;
            for (int i7 = 0; i7 < width; i7++) {
                if ((matrix[i6 + i7] & 255) < iA) {
                    fwVar.c(i7, i5);
                }
            }
        }
        return fwVar;
    }

    @Override // supwisdom.rv
    public ew a(int i, ew ewVar) throws NotFoundException {
        uv uvVarC = c();
        int width = uvVarC.getWidth();
        if (ewVar != null && ewVar.c() >= width) {
            ewVar.a();
        } else {
            ewVar = new ew(width);
        }
        a(width);
        byte[] row = uvVarC.getRow(i, this.b);
        int[] iArr = this.c;
        for (int i2 = 0; i2 < width; i2++) {
            int i3 = (row[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        int iA = a(iArr);
        int i4 = 1;
        int i5 = row[0] & 255;
        int i6 = row[1] & 255;
        while (i4 < width - 1) {
            int i7 = i4 + 1;
            int i8 = row[i7] & 255;
            if ((((i6 * 4) - i5) - i8) / 2 < iA) {
                ewVar.d(i4);
            }
            i5 = i6;
            i4 = i7;
            i6 = i8;
        }
        return ewVar;
    }

    public final void a(int i) {
        if (this.b.length < i) {
            this.b = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.c[i2] = 0;
        }
    }
}
