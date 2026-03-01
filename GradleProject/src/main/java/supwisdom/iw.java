package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class iw extends lw {
    @Override // supwisdom.lw
    public fw a(fw fwVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        return a(fwVar, i, i2, mw.a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    @Override // supwisdom.lw
    public fw a(fw fwVar, int i, int i2, mw mwVar) throws NotFoundException {
        if (i > 0 && i2 > 0) {
            fw fwVar2 = new fw(i, i2);
            int i3 = i * 2;
            float[] fArr = new float[i3];
            for (int i4 = 0; i4 < i2; i4++) {
                float f = i4 + 0.5f;
                for (int i5 = 0; i5 < i3; i5 += 2) {
                    fArr[i5] = (i5 / 2) + 0.5f;
                    fArr[i5 + 1] = f;
                }
                mwVar.a(fArr);
                lw.a(fwVar, fArr);
                for (int i6 = 0; i6 < i3; i6 += 2) {
                    try {
                        if (fwVar.b((int) fArr[i6], (int) fArr[i6 + 1])) {
                            fwVar2.c(i6 / 2, i4);
                        }
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
            }
            return fwVar2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
