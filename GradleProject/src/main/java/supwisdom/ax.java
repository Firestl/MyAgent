package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ax implements wv {
    public static final yv[] b = new yv[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final dx f6991a = new dx();

    public static fw a(fw fwVar) throws NotFoundException {
        int[] iArrB = fwVar.b();
        if (iArrB == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = iArrB[0];
        int i2 = iArrB[1];
        int i3 = iArrB[2];
        int i4 = iArrB[3];
        fw fwVar2 = new fw(30, 33);
        for (int i5 = 0; i5 < 33; i5++) {
            int i6 = (((i5 * i4) + (i4 / 2)) / 33) + i2;
            for (int i7 = 0; i7 < 30; i7++) {
                if (fwVar.b(((((i7 * i3) + (i3 / 2)) + (((i5 & 1) * i3) / 2)) / 30) + i, i6)) {
                    fwVar2.c(i7, i5);
                }
            }
        }
        return fwVar2;
    }

    @Override // supwisdom.wv
    public void reset() {
    }

    @Override // supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        if (map != null && map.containsKey(DecodeHintType.PURE_BARCODE)) {
            hw hwVarA = this.f6991a.a(a(svVar.a()), map);
            xv xvVar = new xv(hwVarA.g(), hwVarA.d(), b, BarcodeFormat.MAXICODE);
            String strB = hwVarA.b();
            if (strB != null) {
                xvVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, strB);
            }
            return xvVar;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
