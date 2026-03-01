package supwisdom;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.ChecksumException;
import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultMetadataType;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class tw implements wv {
    public static final yv[] b = new yv[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final xw f9324a = new xw();

    public static fw a(fw fwVar) throws NotFoundException {
        int[] iArrD = fwVar.d();
        int[] iArrA = fwVar.a();
        if (iArrD == null || iArrA == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iA = a(iArrD, fwVar);
        int i = iArrD[1];
        int i2 = iArrA[1];
        int i3 = iArrD[0];
        int i4 = ((iArrA[0] - i3) + 1) / iA;
        int i5 = ((i2 - i) + 1) / iA;
        if (i4 <= 0 || i5 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i6 = iA / 2;
        int i7 = i + i6;
        int i8 = i3 + i6;
        fw fwVar2 = new fw(i4, i5);
        for (int i9 = 0; i9 < i5; i9++) {
            int i10 = (i9 * iA) + i7;
            for (int i11 = 0; i11 < i4; i11++) {
                if (fwVar.b((i11 * iA) + i8, i10)) {
                    fwVar2.c(i11, i9);
                }
            }
        }
        return fwVar2;
    }

    @Override // supwisdom.wv
    public void reset() {
    }

    public static int a(int[] iArr, fw fwVar) throws NotFoundException {
        int iE = fwVar.e();
        int i = iArr[0];
        int i2 = iArr[1];
        while (i < iE && fwVar.b(i, i2)) {
            i++;
        }
        if (i != iE) {
            int i3 = i - iArr[0];
            if (i3 != 0) {
                return i3;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.wv
    public xv a(sv svVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        yv[] yvVarArrB;
        hw hwVarA;
        if (map != null && map.containsKey(DecodeHintType.PURE_BARCODE)) {
            hwVarA = this.f9324a.a(a(svVar.a()));
            yvVarArrB = b;
        } else {
            jw jwVarA = new zw(svVar.a()).a();
            hw hwVarA2 = this.f9324a.a(jwVarA.a());
            yvVarArrB = jwVarA.b();
            hwVarA = hwVarA2;
        }
        xv xvVar = new xv(hwVarA.g(), hwVarA.d(), yvVarArrB, BarcodeFormat.DATA_MATRIX);
        List<byte[]> listA = hwVarA.a();
        if (listA != null) {
            xvVar.a(ResultMetadataType.BYTE_SEGMENTS, listA);
        }
        String strB = hwVarA.b();
        if (strB != null) {
            xvVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, strB);
        }
        return xvVar;
    }
}
