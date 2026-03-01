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
public class qz implements wv {
    public static final yv[] b = new yv[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final vz f8975a = new vz();

    public static fw a(fw fwVar) throws NotFoundException {
        int[] iArrD = fwVar.d();
        int[] iArrA = fwVar.a();
        if (iArrD == null || iArrA == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float fA = a(iArrD, fwVar);
        int i = iArrD[1];
        int i2 = iArrA[1];
        int i3 = iArrD[0];
        int i4 = iArrA[0];
        if (i3 >= i4 || i >= i2) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        if (i5 != i4 - i3) {
            i4 = i3 + i5;
        }
        int iRound = Math.round(((i4 - i3) + 1) / fA);
        int iRound2 = Math.round((i5 + 1) / fA);
        if (iRound <= 0 || iRound2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (iRound2 != iRound) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i6 = (int) (fA / 2.0f);
        int i7 = i + i6;
        int i8 = i3 + i6;
        int i9 = (((int) ((iRound - 1) * fA)) + i8) - i4;
        if (i9 > 0) {
            if (i9 > i6) {
                throw NotFoundException.getNotFoundInstance();
            }
            i8 -= i9;
        }
        int i10 = (((int) ((iRound2 - 1) * fA)) + i7) - i2;
        if (i10 > 0) {
            if (i10 > i6) {
                throw NotFoundException.getNotFoundInstance();
            }
            i7 -= i10;
        }
        fw fwVar2 = new fw(iRound, iRound2);
        for (int i11 = 0; i11 < iRound2; i11++) {
            int i12 = ((int) (i11 * fA)) + i7;
            for (int i13 = 0; i13 < iRound; i13++) {
                if (fwVar.b(((int) (i13 * fA)) + i8, i12)) {
                    fwVar2.c(i13, i11);
                }
            }
        }
        return fwVar2;
    }

    @Override // supwisdom.wv
    public void reset() {
    }

    public static float a(int[] iArr, fw fwVar) throws NotFoundException {
        int iC = fwVar.c();
        int iE = fwVar.e();
        int i = iArr[0];
        boolean z = true;
        int i2 = iArr[1];
        int i3 = 0;
        while (i < iE && i2 < iC) {
            if (z != fwVar.b(i, i2)) {
                i3++;
                if (i3 == 5) {
                    break;
                }
                z = !z;
            }
            i++;
            i2++;
        }
        if (i != iE && i2 != iC) {
            return (i - iArr[0]) / 7.0f;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // supwisdom.wv
    public final xv a(sv svVar, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException, NotFoundException {
        yv[] yvVarArrB;
        hw hwVarA;
        if (map != null && map.containsKey(DecodeHintType.PURE_BARCODE)) {
            hwVarA = this.f8975a.a(a(svVar.a()), map);
            yvVarArrB = b;
        } else {
            jw jwVarA = new b00(svVar.a()).a(map);
            hw hwVarA2 = this.f8975a.a(jwVarA.a(), map);
            yvVarArrB = jwVarA.b();
            hwVarA = hwVarA2;
        }
        if (hwVarA.c() instanceof xz) {
            ((xz) hwVarA.c()).a(yvVarArrB);
        }
        xv xvVar = new xv(hwVarA.g(), hwVarA.d(), yvVarArrB, BarcodeFormat.QR_CODE);
        xvVar.f = hwVarA.h;
        List<byte[]> listA = hwVarA.a();
        if (listA != null) {
            xvVar.a(ResultMetadataType.BYTE_SEGMENTS, listA);
        }
        String strB = hwVarA.b();
        if (strB != null) {
            xvVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, strB);
        }
        if (hwVarA.h()) {
            xvVar.a(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(hwVarA.f()));
            xvVar.a(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(hwVarA.e()));
        }
        return xvVar;
    }
}
