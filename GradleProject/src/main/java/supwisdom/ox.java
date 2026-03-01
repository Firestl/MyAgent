package supwisdom;

import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class ox implements wv {
    public static float a(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i;
        float f3 = f2 / i2;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f6 = iArr2[i4] * f3;
            float f7 = iArr[i4];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }

    public abstract xv a(int i, ew ewVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException;

    /* JADX WARN: Removed duplicated region for block: B:38:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.xv b(supwisdom.sv r22, java.util.Map<com.dcloud.zxing2.DecodeHintType, ?> r23) throws com.dcloud.zxing2.NotFoundException {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.ox.b(supwisdom.sv, java.util.Map):supwisdom.xv");
    }

    @Override // supwisdom.wv
    public void reset() {
    }

    public static void a(ew ewVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i2 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int iC = ewVar.c();
        if (i < iC) {
            boolean z = !ewVar.a(i);
            while (i < iC) {
                if (ewVar.a(i) ^ z) {
                    iArr[i2] = iArr[i2] + 1;
                } else {
                    i2++;
                    if (i2 == length) {
                        break;
                    }
                    iArr[i2] = 1;
                    z = !z;
                }
                i++;
            }
            if (i2 != length) {
                if (i2 != length - 1 || i != iC) {
                    throw NotFoundException.getNotFoundInstance();
                }
                return;
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        try {
            return b(svVar, map);
        } catch (NotFoundException e2) {
            if ((map != null && map.containsKey(DecodeHintType.TRY_HARDER)) && svVar.d()) {
                sv svVarE = svVar.e();
                xv xvVarB = b(svVarE, map);
                Map<ResultMetadataType, Object> mapC = xvVarB.c();
                int iIntValue = 270;
                if (mapC != null) {
                    ResultMetadataType resultMetadataType = ResultMetadataType.ORIENTATION;
                    if (mapC.containsKey(resultMetadataType)) {
                        iIntValue = (((Integer) mapC.get(resultMetadataType)).intValue() + 270) % 360;
                    }
                }
                xvVarB.a(ResultMetadataType.ORIENTATION, Integer.valueOf(iIntValue));
                yv[] yvVarArrD = xvVarB.d();
                if (yvVarArrD != null) {
                    int iB = svVarE.b();
                    for (int i = 0; i < yvVarArrD.length; i++) {
                        yvVarArrD[i] = new yv((iB - yvVarArrD[i].b()) - 1.0f, yvVarArrD[i].a());
                    }
                }
                return xvVarB;
            }
            throw e2;
        }
    }

    public static void b(ew ewVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean zA = ewVar.a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (ewVar.a(i) != zA) {
                length--;
                zA = !zA;
            }
        }
        if (length < 0) {
            a(ewVar, i + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
